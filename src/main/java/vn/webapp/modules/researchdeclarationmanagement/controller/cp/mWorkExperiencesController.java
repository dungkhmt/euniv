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
import vn.webapp.modules.researchdeclarationmanagement.model.mWorkExperiences;
import vn.webapp.modules.researchdeclarationmanagement.service.mWorkExperiencesService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mWorkExperiencesController")
@RequestMapping(value = {"/cp"})
public class mWorkExperiencesController extends BaseWeb {
	@Autowired
	private mWorkExperiencesService mWorkExperiencesService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mWorkExperiencesController";
	}
	
	/**
	    * Show manage all educations
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/workexperiences", method = RequestMethod.GET)
	public String manageWorkExperiences(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "WORK-EXPERIENCES")) {
			return "cp.workexperiences";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "workexperiences/getWorkExperiences", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getWorkExperiences(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "WORK-EXPERIENCES")) {
			if(userRole.equals("ROLE_ADMIN")) {
				return new ResponseEntity<>(mWorkExperiencesService.getList(), HttpStatus.OK);
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {

					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();

					
					List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
					System.out.print(staffs.size());
					List<field> fields = new ArrayList<field>();
					field newField = new field();
					newField.setFieldName("WE_StaffCode");
					for(mStaff staff: staffs) {
						newField.setValue(staff.getStaff_User_Code());
					}
					fields.add(newField);
					
					List<mWorkExperiences> list = mWorkExperiencesService.getListByField(fields);
					return new ResponseEntity<>(list, HttpStatus.OK);
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField = new field();
						newField.setFieldName("WE_StaffCode");
						newField.setValue(userCode);
						fields.add(newField);
						return new ResponseEntity<>(mWorkExperiencesService.getListByField(fields), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/workexperiences/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateWorkExperiences(ModelMap model, HttpSession session, @RequestBody String sWorkExperiences){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "WORK-EXPERIENCES")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject workexperiences = (JSONObject) parser.parse(sWorkExperiences);
				if(userRole.equals("ROLE_ADMIN")) {
					if(mWorkExperiencesService.changeWorkExperiences(Integer.valueOf(workexperiences.get("WE_ID").toString()), workexperiences.get("WE_Position").toString(), workexperiences.get("WE_Domain").toString(), workexperiences.get("WE_Institution").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
						
						List<field> fields = new ArrayList<field>();
						field field1 = new field();
						field1.setFieldName("WE_ID");
						field1.setValue(workexperiences.get("WE_ID").toString());
						fields.add(field1);
						List<mWorkExperiences> listGetID = mWorkExperiencesService.getListByField(fields);
						if(listGetID.size() == 1) {
							List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
							
							for(mStaff staff: staffs) {
								if(staff.getStaff_User_Code().equals(listGetID.get(0).getWE_StaffCode())) {
									if(mWorkExperiencesService.changeWorkExperiences(Integer.valueOf(workexperiences.get("WE_ID").toString()), workexperiences.get("WE_Position").toString(), workexperiences.get("WE_Domain").toString(), workexperiences.get("WE _Institution").toString())) {
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
							newField1.setFieldName("WE_StaffCode");
							newField1.setValue(userCode);
							fields.add(newField1);
							
							field newField2 = new field();
							newField2.setFieldName("WE_ID");
							newField2.setValue(workexperiences.get("WE_ID").toString());
							fields.add(newField2);
							
							List<mWorkExperiences> check = mWorkExperiencesService.getListByField(fields);
							if(check.size() == 1) {
								if(mWorkExperiencesService.changeWorkExperiences(Integer.valueOf(workexperiences.get("WE_ID").toString()), workexperiences.get("WE_Position").toString(),workexperiences.get("WE_Domain").toString(), workexperiences.get("WE_Institution").toString())) {
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
	
	@RequestMapping(value = "/workexperiences/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteWorkExperiences(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "WORK-EXPERIENCES")) {
			if(userRole.equals("ROLE_ADMIN")) {
				if(mWorkExperiencesService.deleteWorkExperiences(id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
					
					List<field> fields = new ArrayList<field>();
					field field1 = new field();
					field1.setFieldName("WE_ID");
					field1.setValue(String.valueOf(id));
					fields.add(field1);
					List<mWorkExperiences> listGetID = mWorkExperiencesService.getListByField(fields);
					
					if(listGetID.size() == 1) {
						List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
						
						for(mStaff staff: staffs) {
							if(staff.getStaff_User_Code().equals(listGetID.get(0).getWE_StaffCode())) {
								if(mWorkExperiencesService.deleteWorkExperiences(id)) {
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
						newField1.setFieldName("WE_StaffCode");
						newField1.setValue(userCode);
						fields.add(newField1);
						
						field newField2 = new field();
						newField2.setFieldName("WE_ID");
						newField2.setValue(String.valueOf(id));
						fields.add(newField2);
						
						List<mWorkExperiences> check = mWorkExperiencesService.getListByField(fields);
						if(check.size() == 1) {
							if(mWorkExperiencesService.deleteWorkExperiences(id)) {
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
	
	@RequestMapping(value = "/workexperiences/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addWorkExperiences(ModelMap model, HttpSession session, @RequestBody String sWorkExperiences){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "WORK-EXPERIENCES")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject workexperiences = (JSONObject) parser.parse(sWorkExperiences);
				
				
				mWorkExperiences newWorkExperiences = new mWorkExperiences();
				newWorkExperiences.setWE_Position(workexperiences.get("WE_Position").toString());
				newWorkExperiences.setWE_Domain(workexperiences.get("WE_Domain").toString());
				newWorkExperiences.setWE_Institution(workexperiences.get("WE_Institution").toString());
				newWorkExperiences.setWE_StaffCode(userCode);
				mWorkExperiences nWorkExperiences = mWorkExperiencesService.addWorkExperiences(newWorkExperiences);
				if(nWorkExperiences == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(nWorkExperiences, HttpStatus.CREATED);
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

