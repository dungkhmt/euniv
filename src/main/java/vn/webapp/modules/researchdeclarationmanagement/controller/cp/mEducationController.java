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
import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.service.mEducationService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mEducation")
@RequestMapping(value = {"/cp"})
public class mEducationController extends BaseWeb {
	@Autowired
	private mEducationService mEducationService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	String name(){
		return "mEducationController";
	}
	
	/**
	    * Show manage all educations
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/educations", method = RequestMethod.GET)
	public String manageEducations(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EDUCATIONS")) {
			return "cp.manageEducations";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "educations/getEducations", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEducations(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EDUCATIONS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				return new ResponseEntity<>(mEducationService.getList(), HttpStatus.OK);
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {

					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();

					
					List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
					System.out.print(staffs.size());
					List<field> fields = new ArrayList<field>();
					field newField = new field();
					newField.setFieldName("EDU_UserCode");
					for(mStaff staff: staffs) {
						newField.setValue(staff.getStaff_User_Code());
					}
					fields.add(newField);
					
					List<mEducations> list = mEducationService.getListByField(fields);
					return new ResponseEntity<>(list, HttpStatus.OK);
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField = new field();
						newField.setFieldName("EDU_UserCode");
						newField.setValue(userCode);
						fields.add(newField);
						return new ResponseEntity<>(mEducationService.getListByField(fields), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/educations/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateEducation(ModelMap model, HttpSession session, @RequestBody String sEducation){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EDUCATIONS")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject education = (JSONObject) parser.parse(sEducation);
				if(userRole.equals("ROLE_ADMIN")) {
					if(mEducationService.changeEducation(Integer.valueOf(education.get("EDU_ID").toString()), education.get("EDU_Level").toString(), education.get("EDU_Institution").toString(), education.get("EDU_Major").toString(), education.get("EDU_CompleteDate").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
						
						List<field> fields = new ArrayList<field>();
						field field1 = new field();
						field1.setFieldName("EDU_ID");
						field1.setValue(education.get("EDU_ID").toString());
						fields.add(field1);
						List<mEducations> listGetID = mEducationService.getListByField(fields);
						if(listGetID.size() == 1) {
							List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
							
							for(mStaff staff: staffs) {
								if(staff.getStaff_User_Code().equals(listGetID.get(0).getEDU_UserCode())) {
									if(mEducationService.changeEducation(Integer.valueOf(education.get("EDU_ID").toString()), education.get("EDU_Level").toString(), education.get("EDU_Institution").toString(), education.get("EDU_Major").toString(), education.get("EDU_CompleteDate").toString())) {
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
							newField1.setFieldName("EDU_UserCode");
							newField1.setValue(userCode);
							fields.add(newField1);
							
							field newField2 = new field();
							newField2.setFieldName("EDU_ID");
							newField2.setValue(education.get("EDU_ID").toString());
							fields.add(newField2);
							
							List<mEducations> check = mEducationService.getListByField(fields);
							if(check.size() == 1) {
								if(mEducationService.changeEducation(Integer.valueOf(education.get("EDU_ID").toString()), education.get("EDU_Level").toString(), education.get("EDU_Institution").toString(), education.get("EDU_Major").toString(), education.get("EDU_CompleteDate").toString())) {
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
	
	@RequestMapping(value = "/educations/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteEducation(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EDUCATIONS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				if(mEducationService.deleteEducation(id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
					
					List<field> fields = new ArrayList<field>();
					field field1 = new field();
					field1.setFieldName("EDU_ID");
					field1.setValue(String.valueOf(id));
					fields.add(field1);
					List<mEducations> listGetID = mEducationService.getListByField(fields);
					
					if(listGetID.size() == 1) {
						List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
						
						for(mStaff staff: staffs) {
							if(staff.getStaff_User_Code().equals(listGetID.get(0).getEDU_UserCode())) {
								if(mEducationService.deleteEducation(id)) {
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
						newField1.setFieldName("EDU_UserCode");
						newField1.setValue(userCode);
						fields.add(newField1);
						
						field newField2 = new field();
						newField2.setFieldName("EDU_ID");
						newField2.setValue(String.valueOf(id));
						fields.add(newField2);
						
						List<mEducations> check = mEducationService.getListByField(fields);
						if(check.size() == 1) {
							if(mEducationService.deleteEducation(id)) {
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
	
	@RequestMapping(value = "/educations/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addEducation(ModelMap model, HttpSession session, @RequestBody String sEducation){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EDUCATIONS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject education = (JSONObject) parser.parse(sEducation);
				
				
				mEducations newEducation = new mEducations();
				newEducation.setEDU_Level(education.get("EDU_Level").toString());
				newEducation.setEDU_Major(education.get("EDU_Major").toString());
				newEducation.setEDU_Institution(education.get("EDU_Institution").toString());
				newEducation.setEDU_CompleteDate(education.get("EDU_CompleteDate").toString());
				newEducation.setEDU_UserCode(userCode);
				mEducations nEducation = mEducationService.addEducation(newEducation);
				if(nEducation == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(nEducation, HttpStatus.CREATED);
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

