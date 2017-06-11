
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
import vn.webapp.modules.researchdeclarationmanagement.model.mForeignLanguages;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.service.mForeignLanguagesService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mForeignLanguages")
@RequestMapping(value = {"/cp"})
public class mForeignLanguagesController extends BaseWeb {
	@Autowired
	private mForeignLanguagesService mForeignLanguagesService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mForeignLanguagesController";
	}
	
	/**
	    * Show manage all foreign languages
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/foreignlanguages", method = RequestMethod.GET)
	public String manageForeignLanguages(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "FOREIGN-LANGUAGES-EDUCATIONS")) {
			return "cp.foreignLanguages";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "/foreignlanguages/getForeignLanguages", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getForeignLanguages(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "FOREIGN-LANGUAGES-EDUCATIONS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				return new ResponseEntity<>(mForeignLanguagesService.getList(), HttpStatus.OK);
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {

					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();

					
					List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
					System.out.print(staffs.size());
					List<field> fields = new ArrayList<field>();
					field newField = new field();
					newField.setFieldName("LG_UserCode");
					for(mStaff staff: staffs) {
						newField.setValue(staff.getStaff_User_Code());
					}
					fields.add(newField);
					
					List<mForeignLanguages> list = mForeignLanguagesService.getListByField(fields);
					return new ResponseEntity<>(list, HttpStatus.OK);
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField = new field();
						newField.setFieldName("LG_UserCode");
						newField.setValue(userCode);
						fields.add(newField);
						return new ResponseEntity<>(mForeignLanguagesService.getListByField(fields), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/foreignlanguages/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateForeignLanguages(ModelMap model, HttpSession session, @RequestBody String sForeignLanguages){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "FOREIGN-LANGUAGES-EDUCATIONS")) {
			JSONParser parser = new JSONParser();
			try {
				System.out.print(sForeignLanguages);
				JSONObject foreignlanguages = (JSONObject) parser.parse(sForeignLanguages);
				if(userRole.equals("ROLE_ADMIN")) {
					if(mForeignLanguagesService.changeForeignLanguages(Integer.valueOf(foreignlanguages.get("LG_ID").toString()), foreignlanguages.get("LG_Name").toString(), foreignlanguages.get("LG_Listen").toString(), foreignlanguages.get("LG_Speak").toString(), foreignlanguages.get("LG_Reading").toString(),foreignlanguages.get("LG_Writing").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
						
						List<field> fields = new ArrayList<field>();
						field field1 = new field();
						field1.setFieldName("LG_ID");
						field1.setValue(foreignlanguages.get("LG_ID").toString());
						fields.add(field1);
						List<mForeignLanguages> listGetID = mForeignLanguagesService.getListByField(fields);
						if(listGetID.size() == 1) {
							List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
							
							for(mStaff staff: staffs) {
								if(staff.getStaff_User_Code().equals(listGetID.get(0).getLG_UserCode())) {
									if(mForeignLanguagesService.changeForeignLanguages(Integer.valueOf(foreignlanguages.get("LG_ID").toString()), foreignlanguages.get("LG_Name").toString(), foreignlanguages.get("LG_Listen").toString(), foreignlanguages.get("LG_Speak").toString(), foreignlanguages.get("LG_Reading").toString(),foreignlanguages.get("LG_Writing").toString())) {
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
							newField1.setFieldName("LG_UserCode");
							newField1.setValue(userCode);
							fields.add(newField1);
							
							field newField2 = new field();
							newField2.setFieldName("LG_ID");
							newField2.setValue(foreignlanguages.get("LG_ID").toString());
							fields.add(newField2);
							
							List<mForeignLanguages> check = mForeignLanguagesService.getListByField(fields);
							if(check.size() == 1) {
								if(mForeignLanguagesService.changeForeignLanguages(Integer.valueOf(foreignlanguages.get("LG_ID").toString()), foreignlanguages.get("LG_Name").toString(), foreignlanguages.get("LG_Listen").toString(), foreignlanguages.get("LG_Speak").toString(), foreignlanguages.get("LG_Reading").toString(),foreignlanguages.get("LG_Writing").toString())) {
									return new ResponseEntity<>(HttpStatus.ACCEPTED);
								}
							}
						} else {
							return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
						}if(mForeignLanguagesService.changeForeignLanguages(Integer.valueOf(foreignlanguages.get("LG_ID").toString()), foreignlanguages.get("LG_Name").toString(), foreignlanguages.get("LG_Listen").toString(), foreignlanguages.get("LG_Speak").toString(), foreignlanguages.get("LG_Reading").toString(),foreignlanguages.get("LG_Writing").toString()));
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
	
	@RequestMapping(value = "/foreignlanguages/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteForeignLanguages(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "FOREIGN-LANGUAGES-EDUCATIONS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				if(mForeignLanguagesService.deleteForeignLanguages(id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
					
					List<field> fields = new ArrayList<field>();
					field field1 = new field();
					field1.setFieldName("LG_ID");
					field1.setValue(String.valueOf(id));
					fields.add(field1);
					List<mForeignLanguages> listGetID = mForeignLanguagesService.getListByField(fields);
					
					if(listGetID.size() == 1) {
						List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
						
						for(mStaff staff: staffs) {
							if(staff.getStaff_User_Code().equals(listGetID.get(0).getLG_UserCode())) {
								if(mForeignLanguagesService.deleteForeignLanguages(id)) {
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
						newField1.setFieldName("LG_UserCode");
						newField1.setValue(userCode);
						fields.add(newField1);
						
						field newField2 = new field();
						newField2.setFieldName("LG_ID");
						newField2.setValue(String.valueOf(id));
						fields.add(newField2);
						
						List<mForeignLanguages> check = mForeignLanguagesService.getListByField(fields);
						if(check.size() == 1) {
							if(mForeignLanguagesService.deleteForeignLanguages(id)) {
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
	
	@RequestMapping(value = "/foreignlanguages/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addForeignLanguages(ModelMap model, HttpSession session, @RequestBody String sForeignLanguages){
		System.out.println("1");
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "FOREIGN-LANGUAGES-EDUCATIONS")) {
			JSONParser parser = new JSONParser();
			try {
				System.out.println(sForeignLanguages);
				JSONObject foreignlanguages = (JSONObject) parser.parse(sForeignLanguages);
				System.out.println("1");
				mForeignLanguages newForeignLanguages = new mForeignLanguages();
				newForeignLanguages.setLG_Name(foreignlanguages.get("LG_Name").toString());
				newForeignLanguages.setLG_Listen(foreignlanguages.get("LG_Listen").toString());
				newForeignLanguages.setLG_Speak(foreignlanguages.get("LG_Speak").toString());
				newForeignLanguages.setLG_Reading(foreignlanguages.get("LG_Reading").toString());
				newForeignLanguages.setLG_Writing(foreignlanguages.get("LG_Writing").toString());
				newForeignLanguages.setLG_UserCode(userCode);
				mForeignLanguages nForeignLanguages = mForeignLanguagesService.addForeignLanguages(newForeignLanguages);
				if(nForeignLanguages == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(nForeignLanguages, HttpStatus.CREATED);
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

