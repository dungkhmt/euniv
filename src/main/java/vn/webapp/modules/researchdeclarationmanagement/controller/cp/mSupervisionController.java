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
import vn.webapp.modules.researchdeclarationmanagement.service.mSupervisionService;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;
import vn.webapp.modules.researchdeclarationmanagement.service.mEducationService;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mSupervisionController")
@RequestMapping(value = {"/cp"})
public class mSupervisionController extends BaseWeb {
	@Autowired
	private mSupervisionService mSupervisionService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mSupervisionController";
	}
	
	/**
	    * Show manage all supervision
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/supervisions", method = RequestMethod.GET)
	public String manageSupervision(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-SUPERVISION")) {
			return "cp.manageSupervisions";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "supervisions/getSupervisions", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getSupervisions(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-SUPERVISION")) {
			List<field> fields = new ArrayList<field>();
			field newField = new field();
			newField.setFieldName("SUP_StaffCode");
			newField.setValue(userCode);
			fields.add(newField);
			return new ResponseEntity<>(mSupervisionService.getListByField(fields), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@RequestMapping(value = "/supervisions/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateSupervision(ModelMap model, HttpSession session, @RequestBody String sSupervision){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-SUPERVISION")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject supervison = (JSONObject) parser.parse(sSupervision);
				List<field> fields = new ArrayList<field>();
				field newField1 = new field();
				newField1.setFieldName("SUP_StaffCode");
				
				newField1.setValue(userCode);
				fields.add(newField1);
				
				field newField2 = new field();
				newField2.setFieldName("SUP_ID");
				newField2.setValue(supervison.get("SUP_ID").toString());
				fields.add(newField2);
				List<mSupervision> check = mSupervisionService.getListByField(fields);
				System.out.print(check.size());
				if(check.size() == 1) {
					System.out.print("Tooi day");
					System.out.print(supervison.get("SUP_ID").toString());
					if(mSupervisionService.changeSupervision(Integer.valueOf(supervison.get("SUP_ID").toString()), supervison.get("SUP_StudentName").toString(), supervison.get("SUP_Cosupervision").toString(), supervison.get("SUP_Institution").toString(), supervison.get("SUP_ThesisTitle").toString(), supervison.get("SUP_SpecializationCode").toString(), supervison.get("SUP_TraingPeriod").toString(), supervison.get("SUP_DefensedDate").toString())) {
						return new ResponseEntity<>(HttpStatus.ACCEPTED);
					} else {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	@RequestMapping(value = "/supervisions/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteSupervision(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		System.out.print(id);
		System.out.print(userCode);
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-SUPERVISION")) {
			List<field> fields = new ArrayList<field>();
			field newField1 = new field();
			newField1.setFieldName("SUP_StaffCode");
			newField1.setValue(userCode);
			fields.add(newField1);
			
			field newField2 = new field();
			newField2.setFieldName("SUP_ID");
			newField2.setValue(String.valueOf(id));
			fields.add(newField2);
			
			List<mSupervision> check = mSupervisionService.getListByField(fields);
			if(check!= null && check.size() == 1) {
				if(mSupervisionService.deleteSupervision(id)) {
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
		
	}
	
	@RequestMapping(value = "/supervisions/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addSupervision(ModelMap model, HttpSession session, @RequestBody String sSupervision){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-SUPERVISION")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject supervision = (JSONObject) parser.parse(sSupervision);
				
				mSupervision newSupervision = new mSupervision();
				newSupervision.setSUP_Cosupervision(supervision.get("SUP_Cosupervision").toString());
				newSupervision.setSUP_DefensedDate(supervision.get("SUP_DefensedDate").toString());
				newSupervision.setSUP_Institution(supervision.get("SUP_Institution").toString());
				newSupervision.setSUP_SpecializationCode(supervision.get("SUP_SpecializationCode").toString());
				newSupervision.setSUP_StudentName(supervision.get("SUP_StudentName").toString());
				newSupervision.setSUP_ThesisTitle(supervision.get("SUP_ThesisTitle").toString());
				newSupervision.setSUP_TraingPeriod(supervision.get("SUP_TraingPeriod").toString());
				newSupervision.setSUP_StaffCode(userCode);
				
				newSupervision = mSupervisionService.addSupervision(newSupervision);
				if(newSupervision == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(newSupervision, HttpStatus.CREATED);
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

