package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatentCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.validation.mPatentValidation;
import vn.webapp.modules.usermanagement.service.mUserService;

@Controller("cpmPatent")
@RequestMapping(value = {"/cp"})
public class mPatentController extends BaseWeb {
	@Autowired
    private mPatentService patentService;
    
    @Autowired
    private mUserService userService;
    
    @Autowired
    private mAcademicYearService academicYearService;
    
    @Autowired
    private mPatentCategoryService patentCategoryService;
    
    static final String status = "active";
    
    /**
    * Show list all patents
    * @param model
    * @return
    */
   @RequestMapping(value = "/patents", method = RequestMethod.GET)
   public String patentsList(ModelMap model, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   List<mPatents> patentsList = patentService.loadPatentListByStaff(userRole, userCode);
	   model.put("patentsList", patentsList);
	   model.put("patents", status);
	   return "cp.patents";
   }
   
   
   /**
    * Adding a patent
    * @param model
    * @return
    */
   @RequestMapping(value = "/add-a-patent", method = RequestMethod.GET)
   public String addAPatent(ModelMap model, HttpSession session) {
	   // Get list reportingYear
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   List<mPatentCategory> mPatentCategoryList = patentCategoryService.loadPatentCategoryList();
	   
	   // Put data back to view
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("patentCategoryList", mPatentCategoryList);
	   model.put("patentFormAdd", new mPatentValidation());
	   model.put("patents", status);
       return "cp.addAPatent";
   }
   
   /**
    * Save a patent
    * @param patentValid
    * @param result
    * @param model
    * @param session
    * @return String
    */
   @RequestMapping(value="save-a-patent", method=RequestMethod.POST)
   public String saveAPatent(@Valid @ModelAttribute("patentFormAdd") mPatentValidation patentValid, BindingResult result,  Map model, HttpSession session) {
	   // Get list reportingYear
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   List<mPatentCategory> mPatentCategoryList = patentCategoryService.loadPatentCategoryList();
	   
	   model.put("patentCategoryList", mPatentCategoryList);
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("patents", status);
	   if(result.hasErrors()) {
           return "cp.addAPatent";
       }else
       {
    	   // Prepare data for inserting DB
    	   String userCode 						= session.getAttribute("currentUserCode").toString();
    	   String patentName 					= patentValid.getPatentName();
    	   int patentConVertedHours 			= patentValid.getPatentConHours();
    	   int patentAutConHours 				= patentValid.getPatentAutConHours();
    	   String patentDateOfIssue 			= patentValid.getPatentDateOfIssue();
    	   String patentAuthors	 				= patentValid.getPatentAuthors();
    	   String patentType	 				= patentValid.getPatentType();
    	   String patentNumber	 				= patentValid.getPatentNumber();
    	   String patentReportingAcademicDate 	= patentValid.getPatentReportingAcademicDate();
    	   
    	   int i_InsertAPatent = patentService.saveAPatent(userCode, patentName, patentConVertedHours, patentAutConHours, 
															patentDateOfIssue, patentAuthors,  patentType, patentNumber, patentReportingAcademicDate);
    	   if(i_InsertAPatent > 0){
    		   //model.put("status", "Successfully saved a patent.");
    		   return "redirect:" + this.baseUrl + "/cp/patents.html";
    	   }
           return "cp.addAPatent";
       }
   }
   
   
   /**
    * show patent info
    * @param model
    * @param patentId
    * @param session
    * @return
    */
   @RequestMapping("/patentdetail/{id}")
   public String editAPatent(ModelMap model, @PathVariable("id") int patentId, HttpSession session) {
	   // Get list reportingYear
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   List<mPatentCategory> mPatentCategoryList = patentCategoryService.loadPatentCategoryList();
	   
	   model.put("patentCategoryList", mPatentCategoryList);
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("patents", status);
	   
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   mPatents patent = patentService.loadAPatentByIdAndUserCode(userRole, userCode, patentId);
	   if(patent != null)
	   {
		   // Put journal list and paper category to view
		   model.put("patentFormEdit", new mPatentValidation());
		   model.put("patent", patent);
		   model.put("patentId", patentId);
		   
		   return "cp.editAPatent";
	   }
	   return "cp.notFound404";
   }
   
   /**
    * Adding a patent
    * @param model
    * @return
    */
   @RequestMapping(value = "/edit-a-patent", method = RequestMethod.POST)
   public String updateAPatent(@Valid @ModelAttribute("patentFormEdit") mPatentValidation patentFormEdit, BindingResult result, Map model, HttpSession session) {
	   // Get list reportingYear
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   List<mPatentCategory> mPatentCategoryList = patentCategoryService.loadPatentCategoryList();
	   
	   model.put("patentCategoryList", mPatentCategoryList);
	   model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
	   model.put("patents", status);
	   if (result.hasErrors()) {
    	   model.put("patentName", patentFormEdit.getPatentName());
    	   model.put("patentConVertedHours", patentFormEdit.getPatentConHours());
		   model.put("patentAutConHours", patentFormEdit.getPatentAutConHours());
		   model.put("patentDateOfIssue", patentFormEdit.getPatentDateOfIssue());
		   model.put("patentAuthors", patentFormEdit.getPatentAuthors());
		   model.put("patentType", patentFormEdit.getPatentType());
		   model.put("patentNumber", patentFormEdit.getPatentNumber());
		   
          return "cp.editAPatent";
      }else
      {
    	  String userRole = session.getAttribute("currentUserRole").toString();
    	  String userCode = session.getAttribute("currentUserCode").toString();
   	      // Prepare data for inserting DB
    	  String patentName 				= patentFormEdit.getPatentName();
   	   	  int patentConVertedHours 			= patentFormEdit.getPatentConHours();
   	   	  int patentAutConHours 			= patentFormEdit.getPatentAutConHours();
   	   	  String patentDateOfIssue 			= patentFormEdit.getPatentDateOfIssue();
   	   	  String patentAuthors	 			= patentFormEdit.getPatentAuthors();
   	   	  String patentType	 				= patentFormEdit.getPatentType();
   	   	  String patentNumber	 			= patentFormEdit.getPatentNumber();
   	   	  
   	   	  // TODO
	   	  String patentReportingAcademicDate 	= patentFormEdit.getPatentReportingAcademicDate();
   	   	  int patentId	 						= patentFormEdit.getPatentId();
          
    	  patentService.editAPatent(userRole, userCode, patentId, patentName, patentConVertedHours, patentAutConHours, 
    			  					patentDateOfIssue, patentAuthors, patentType, patentNumber, patentReportingAcademicDate);
          //model.put("status", "Successfully edited project.");
          return "redirect:" + this.baseUrl + "/cp/patents.html";
      }
   }
   
   /**
    * Show list all patents
    * @param model
    * @return
    */
   @RequestMapping(value = "/remove-a-patent/{id}", method = RequestMethod.GET)
   public String removeAPatent(ModelMap model, @PathVariable("id") int patentId, HttpSession session) {
	   model.put("patents", status);
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   mPatents patent = patentService.loadAPatentByIdAndUserCode(userRole, userCode, patentId);
	   if(patent != null){
		   patentService.removeAPatent(patentId);
		   List<mPatents> patentsList = patentService.loadPatentListByStaff(userRole, userCode);
		   model.put("patentsList", patentsList);
		   return "cp.patents";
	   }
	   return "cp.notFound404";
   }
}
