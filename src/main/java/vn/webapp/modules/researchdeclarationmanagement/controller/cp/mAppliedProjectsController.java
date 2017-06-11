package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mAppliedProjects;
import vn.webapp.modules.researchdeclarationmanagement.service.mAppliedProjectsService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mAppliedProjectsController")
@RequestMapping(value = {"/cp"})
public class mAppliedProjectsController extends BaseWeb {
	@Autowired
	private mAppliedProjectsService mAppliedProjectsService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mAppliedProjectsController";
	}
	
	/**
	    * Show manage all appliedprojects
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/appliedprojects", method = RequestMethod.GET)
	public String manageAppliedProjects(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "APPLIED-PROJECTS")) {
			return "cp.appliedprojects";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "appliedprojects/getAppliedProjects", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAppliedProjects(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "APPLIED-PROJECTS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				return new ResponseEntity<>(mAppliedProjectsService.getList(), HttpStatus.OK);
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {

					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();

					
					List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
					System.out.print(staffs.size());
					List<field> fields = new ArrayList<field>();
					field newField = new field();
					newField.setFieldName("AP_StaffCode");
					for(mStaff staff: staffs) {
						newField.setValue(staff.getStaff_User_Code());
					}
					fields.add(newField);
					
					List<mAppliedProjects> list = mAppliedProjectsService.getListByField(fields);
					return new ResponseEntity<>(list, HttpStatus.OK);
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField = new field();
						newField.setFieldName("AP_StaffCode");
						newField.setValue(userCode);
						fields.add(newField);
						return new ResponseEntity<>(mAppliedProjectsService.getListByField(fields), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/appliedprojects/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateAppliedProjects(ModelMap model, HttpSession session, @RequestBody String sAppliedProjects){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "APPLIED-PROJECTS")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject appliedprojects = (JSONObject) parser.parse(sAppliedProjects);
				if(userRole.equals("ROLE_ADMIN")) {
					if(mAppliedProjectsService.changeAppliedProjects(Integer.valueOf(appliedprojects.get("AP_ID").toString()), appliedprojects.get("AP_Name").toString(), appliedprojects.get("AP_Scope").toString(), appliedprojects.get("AP_Date").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
						
						List<field> fields = new ArrayList<field>();
						field field1 = new field();
						field1.setFieldName("AP_ID");
						field1.setValue(appliedprojects.get("AP_ID").toString());
						fields.add(field1);
						List<mAppliedProjects> listGetID = mAppliedProjectsService.getListByField(fields);
						if(listGetID.size() == 1) {
							List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
							
							for(mStaff staff: staffs) {
								if(staff.getStaff_User_Code().equals(listGetID.get(0).getAP_StaffCode())) {
									if(mAppliedProjectsService.changeAppliedProjects(Integer.valueOf(appliedprojects.get("AP_ID").toString()), appliedprojects.get("AP_Name").toString(), appliedprojects.get("AP_Scope").toString(), appliedprojects.get("AP_Date").toString())) {
										return new ResponseEntity<>(HttpStatus.ACCEPTED);
									} else {
										return new ResponseEntity<>(HttpStatus.NOT_FOUND);
									}
								}
							}
						}
						
					} else {
						if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
							List<field> fields = new ArrayList<field>();
							field newField1 = new field();
							newField1.setFieldName("AP_StaffCode");
							newField1.setValue(userCode);
							fields.add(newField1);
							
							field newField2 = new field();
							newField2.setFieldName("AP_ID");
							newField2.setValue(appliedprojects.get("AP_ID").toString());
							fields.add(newField2);
							
							List<mAppliedProjects> check = mAppliedProjectsService.getListByField(fields);
							if(check.size() == 1) {
								if(mAppliedProjectsService.changeAppliedProjects(Integer.valueOf(appliedprojects.get("AP_ID").toString()), appliedprojects.get("AP_Name").toString(),appliedprojects.get("AP_Scope").toString(), appliedprojects.get("AP_Date").toString())) {
									return new ResponseEntity<>(HttpStatus.ACCEPTED);
								}
							}
						} else {
							return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/appliedprojects/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteWorkExperiences(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "APPLIED-PROJECTS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				if(mAppliedProjectsService.deleteAppliedProjects(id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
					
					List<field> fields = new ArrayList<field>();
					field field1 = new field();
					field1.setFieldName("AP_ID");
					field1.setValue(String.valueOf(id));
					fields.add(field1);
					List<mAppliedProjects> listGetID = mAppliedProjectsService.getListByField(fields);
					
					if(listGetID.size() == 1) {
						List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
						
						for(mStaff staff: staffs) {
							if(staff.getStaff_User_Code().equals(listGetID.get(0).getAP_StaffCode())) {
								if(mAppliedProjectsService.deleteAppliedProjects(id)) {
									return new ResponseEntity<>(HttpStatus.ACCEPTED);
								} else {
									return new ResponseEntity<>(HttpStatus.NOT_FOUND);
								}
							}
						}
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField1 = new field();
						newField1.setFieldName("AP_StaffCode");
						newField1.setValue(userCode);
						fields.add(newField1);
						
						field newField2 = new field();
						newField2.setFieldName("AP_ID");
						newField2.setValue(String.valueOf(id));
						fields.add(newField2);
						
						List<mAppliedProjects> check = mAppliedProjectsService.getListByField(fields);
						if(check.size() == 1) {
							if(mAppliedProjectsService.deleteAppliedProjects(id)) {
								return new ResponseEntity<>(HttpStatus.ACCEPTED);
							}
						}
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
		
	}
	
	@RequestMapping(value = "/appliedprojects/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addAppliedProjects(ModelMap model, HttpSession session, @RequestBody String sAppliedProjects){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "APPLIED-PROJECTS")) {
			System.out.print(1);
			JSONParser parser = new JSONParser();
			try {
				JSONObject appliedprojects= (JSONObject) parser.parse(sAppliedProjects);
				System.out.print(appliedprojects);
				
				mAppliedProjects newAppliedProjects = new mAppliedProjects();
				newAppliedProjects.setAP_Name(appliedprojects.get("AP_Name").toString());
				newAppliedProjects.setAP_Scope(appliedprojects.get("AP_Scope").toString());
				newAppliedProjects.setAP_Date(appliedprojects.get("AP_Date").toString());
				newAppliedProjects.setAP_StaffCode(userCode);
				System.out.print(3);
				mAppliedProjects nAppliedProjects = mAppliedProjectsService.addAppliedProjects(newAppliedProjects);
				System.out.print(4);
				if(nAppliedProjects == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(nAppliedProjects, HttpStatus.CREATED);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			
		
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
		
		
}

