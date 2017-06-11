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
import vn.webapp.modules.researchdeclarationmanagement.model.mAwards;
import vn.webapp.modules.researchdeclarationmanagement.service.mAwardsService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mAwardsController")
@RequestMapping(value = {"/cp"})
public class mAwardsController extends BaseWeb {
	@Autowired
	private mAwardsService mAwardsService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mAwardsController";
	}
	
	/**
	    * Show manage all awards
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/awards", method = RequestMethod.GET)
	public String manageAwards(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "AWARDS")) {
			return "cp.awards";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "awards/getAwards", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAwards(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "AWARDS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				return new ResponseEntity<>(mAwardsService.getList(), HttpStatus.OK);
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {

					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();

					
					List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
					System.out.print(staffs.size());
					List<field> fields = new ArrayList<field>();
					field newField = new field();
					newField.setFieldName("AW_StaffCode");
					for(mStaff staff: staffs) {
						newField.setValue(staff.getStaff_User_Code());
					}
					fields.add(newField);
					
					List<mAwards> list = mAwardsService.getListByField(fields);
					return new ResponseEntity<>(list, HttpStatus.OK);
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						List<field> fields = new ArrayList<field>();
						field newField = new field();
						newField.setFieldName("AW_StaffCode");
						newField.setValue(userCode);
						fields.add(newField);
						return new ResponseEntity<>(mAwardsService.getListByField(fields), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					}
				}
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/awards/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateAwards(ModelMap model, HttpSession session, @RequestBody String sAwards){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "AWARDS")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject awards = (JSONObject) parser.parse(sAwards);
				if(userRole.equals("ROLE_ADMIN")) {
					if(mAwardsService.changeAwards(Integer.valueOf(awards.get("AW_ID").toString()), awards.get("AW_Name").toString(), awards.get("AW_Date").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				} else {
					if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
						String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
						
						List<field> fields = new ArrayList<field>();
						field field1 = new field();
						field1.setFieldName("AW_ID");
						field1.setValue(awards.get("AW_ID").toString());
						fields.add(field1);
						List<mAwards> listGetID = mAwardsService.getListByField(fields);
						if(listGetID.size() == 1) {
							List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
							
							for(mStaff staff: staffs) {
								if(staff.getStaff_User_Code().equals(listGetID.get(0).getAW_StaffCode())) {
									if(mAwardsService.changeAwards(Integer.valueOf(awards.get("AW_ID").toString()), awards.get("AW_Name").toString(), awards.get("AW_Date").toString())) {
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
							newField1.setFieldName("AW_StaffCode");
							newField1.setValue(userCode);
							fields.add(newField1);
							
							field newField2 = new field();
							newField2.setFieldName("AW_ID");
							newField2.setValue(awards.get("AW_ID").toString());
							fields.add(newField2);
							
							List<mAwards> check = mAwardsService.getListByField(fields);
							if(check.size() == 1) {
								if(mAwardsService.changeAwards(Integer.valueOf(awards.get("AW_ID").toString()), awards.get("AW_Name").toString(), awards.get("AW_Date").toString())) {
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
	
	@RequestMapping(value = "/awards/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteAwards(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "AWARDS")) {
			if(userRole.equals("ROLE_ADMIN")) {
				if(mAwardsService.deleteAwards(id)) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} else {
				if(userRole.equals("ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY")) {
					String currentUserFaculty = session.getAttribute("currentUserFaculty").toString();
					
					List<field> fields = new ArrayList<field>();
					field field1 = new field();
					field1.setFieldName("AW_ID");
					field1.setValue(String.valueOf(id));
					fields.add(field1);
					List<mAwards> listGetID = mAwardsService.getListByField(fields);
					
					if(listGetID.size() == 1) {
						List<mStaff> staffs = staffService.listStaffsByFalcuty(currentUserFaculty);
						
						for(mStaff staff: staffs) {
							if(staff.getStaff_User_Code().equals(listGetID.get(0).getAW_StaffCode())) {
								if(mAwardsService.deleteAwards(id)) {
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
						newField1.setFieldName("AW_StaffCode");
						newField1.setValue(userCode);
						fields.add(newField1);
						
						field newField2 = new field();
						newField2.setFieldName("AW_ID");
						newField2.setValue(String.valueOf(id));
						fields.add(newField2);
						
						List<mAwards> check = mAwardsService.getListByField(fields);
						if(check.size() == 1) {
							if(mAwardsService.deleteAwards(id)) {
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
	
	@RequestMapping(value = "/awards/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addAppliedProjects(ModelMap model, HttpSession session, @RequestBody String sAwards){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "AWARDS")) {
			System.out.print(1);
			JSONParser parser = new JSONParser();
			try {
				JSONObject awards= (JSONObject) parser.parse(sAwards);
				System.out.print(awards);
				
				mAwards newAwards = new mAwards();
				newAwards.setAW_Name(awards.get("AW_Name").toString());
				newAwards.setAW_Date(awards.get("AW_Date").toString());
				newAwards.setAW_StaffCode(userCode);
				System.out.print(3);
				mAwards nAwards = mAwardsService.addAwards(newAwards);
				System.out.print(4);
				if(nAwards == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(nAwards, HttpStatus.CREATED);
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

