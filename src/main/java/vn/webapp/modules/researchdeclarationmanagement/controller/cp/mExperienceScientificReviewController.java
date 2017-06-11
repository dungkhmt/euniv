package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mExperienceScientificReview;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;
import vn.webapp.modules.researchdeclarationmanagement.service.mExperienceScientificReviewService;
import vn.webapp.modules.researchdeclarationmanagement.service.mSupervisionService;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("mExperienceScientificReviewController")
@RequestMapping(value = {"/cp"})
public class mExperienceScientificReviewController extends BaseWeb {
	@Autowired
	private mExperienceScientificReviewService mExperienceScientificReviewService;
	
	@Autowired
    private mStaffService staffService;
	
	@Autowired
    private mFuncsPermissionService funcsPermissionService;
	
	public String name(){
		return "mExperienceScientificReviewController";
	}
	
	/**
	    * Show manage all experiencescientificreview
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/experiencescientificreview", method = RequestMethod.GET)
	public String manageExperienceScientificReview(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EXPERIENCESCIENTIFICREVIEW")) {
			return "cp.manageExperienceScientificReview";
		} else {
			return "cp.notFound404";
		}
		
	}
	
	
	@RequestMapping(value = "experiencescientificreview/getExperiencescientificreviews", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getExperiencescientificreview(ModelMap model, HttpSession session){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EXPERIENCESCIENTIFICREVIEW")) {
			List<field> fields = new ArrayList<field>();
			field newField = new field();
			newField.setFieldName("ESV_StaffCode");
			newField.setValue(userCode);
			fields.add(newField);
			return new ResponseEntity<>(mExperienceScientificReviewService.getListByField(fields), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@RequestMapping(value = "/experiencescientificreview/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateExperienceScientificReview(ModelMap model, HttpSession session, @RequestBody String sExperienceScientificReview){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EXPERIENCESCIENTIFICREVIEW")) {
			JSONParser parser = new JSONParser();
			try {
				
				JSONObject experienceScientificReview = (JSONObject) parser.parse(sExperienceScientificReview);
				List<field> fields = new ArrayList<field>();
				field newField1 = new field();
				newField1.setFieldName("ESV_StaffCode");
				
				newField1.setValue(userCode);
				fields.add(newField1);
				
				field newField2 = new field();
				newField2.setFieldName("ESV_ID");
				newField2.setValue(experienceScientificReview.get("ESV_ID").toString());
				fields.add(newField2);
				List<mExperienceScientificReview> check = mExperienceScientificReviewService.getListByField(fields);
				if(check.size() == 1) {
					if(mExperienceScientificReviewService.changeExperienceScientificReview(Integer.valueOf(experienceScientificReview.get("ESV_ID").toString()), experienceScientificReview.get("ESV_Name").toString(), Integer.valueOf(experienceScientificReview.get("ESV_NumberTimes").toString()))) {
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
	
	@RequestMapping(value = "/experiencescientificreview/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteExperienceScientificReview(ModelMap model,HttpServletRequest request, HttpSession session){
		int id = Integer.parseInt(request.getParameter("id"));
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EXPERIENCESCIENTIFICREVIEW")) {
			List<field> fields = new ArrayList<field>();
			field newField1 = new field();
			newField1.setFieldName("ESV_StaffCode");
			newField1.setValue(userCode);
			fields.add(newField1);
			
			field newField2 = new field();
			newField2.setFieldName("ESV_ID");
			newField2.setValue(String.valueOf(id));
			fields.add(newField2);
			
			List<mExperienceScientificReview> check = mExperienceScientificReviewService.getListByField(fields);
			if(check!= null && check.size() == 1) {
				if(mExperienceScientificReviewService.deleteExperienceScientificReview(id)) {
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
		
	}
	
	@RequestMapping(value = "/experiencescientificreview/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addSupervision(ModelMap model, HttpSession session, @RequestBody String sExperienceScientificReview){

		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		if(funcsPermissionService.checkAccess(userCode, "MANAGE-EXPERIENCESCIENTIFICREVIEW")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject experienceScientificReview = (JSONObject) parser.parse(sExperienceScientificReview);
				
				mExperienceScientificReview newExperienceScientificReview = new mExperienceScientificReview();
				newExperienceScientificReview.setESV_Name(experienceScientificReview.get("ESV_Name").toString());
				newExperienceScientificReview.setESV_NumberTimes(Integer.valueOf(experienceScientificReview.get("ESV_NumberTimes").toString()));
				newExperienceScientificReview.setESV_StaffCode(userCode);
				
				newExperienceScientificReview = mExperienceScientificReviewService.addSupervision(newExperienceScientificReview);
				if(newExperienceScientificReview == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>(newExperienceScientificReview, HttpStatus.CREATED);
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
