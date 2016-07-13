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
import org.springframework.web.servlet.ModelAndView;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchdeclarationmanagement.validation.mTopicExcellValidation;
import vn.webapp.modules.researchdeclarationmanagement.validation.mTopicValidation;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;
import vn.webapp.modules.researchmanagement.service.ProjectParticipationRolesService;
import vn.webapp.modules.researchmanagement.validation.mThreadExcellValidation;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmTopic")
@RequestMapping(value = {"/cp"})
public class oProjectController extends BaseWeb {
	@Autowired
    private tProjectService tProjectService;
    
    @Autowired
    private tProjectCategoryService tProjectCategoryService;
    
    @Autowired
    private mJournalService journalService;
    
    @Autowired
    private mStaffService staffService;
    
    @Autowired
    private mDepartmentService departmentService;
    
    @Autowired
    private mPatentService patentService;
    
    @Autowired
    private mAcademicYearService academicYearService;
    
    @Autowired
	private ProjectParticipationRolesService projectParticipationRolesService;
    
    static final String status = "active";
    
    /**
    * Show list all topics
    * @param model
    * @return
    */
   @RequestMapping(value = "/topics", method = RequestMethod.GET)
   public String topicsList(ModelMap model, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   List<mTopics> topicsList = tProjectService.loadTopicListByStaff(userRole, userCode);
	   model.put("topicsList", topicsList);
	   model.put("topics", status);
	   return "cp.topics";
   }
   
   
   /**
    * Adding a topic
    * @param model
    * @return
    */
   @RequestMapping(value = "/add-a-topic", method = RequestMethod.GET)
   public String addATopic(ModelMap model, HttpSession session) {
	   // Get current user name and role
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   
	   // Get topic's category
	   List<mTopicCategory> topicCategory = tProjectCategoryService.list();
	   // Get list reportingYear
	   List<mAcademicYear> topicReportingAcademicDateList = academicYearService.list();
	   
	   List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService.getList();
	   
	   // Put data back to view
	   model.put("topicReportingAcademicDate", topicReportingAcademicDateList);
	   model.put("topicCategory", topicCategory);
	   model.put("memberRolesList", memberRolesList);
	   model.put("topicFormAdd", new mTopicValidation());
	   model.put("topics", status);
       return "cp.addATopic";
   }
   
   /**
    * Save a topic
    * @param topicValid
    * @param result
    * @param model
    * @param session
    * @return String
    */
   @RequestMapping(value="save-a-topic", method=RequestMethod.POST)
   public String saveATopic(@Valid @ModelAttribute("topicFormAdd") mTopicValidation topicValid, BindingResult result,  Map model, HttpSession session) {
	   
	   // Get topic's category
	   List<mTopicCategory> topicCategoryList = tProjectCategoryService.list();
	   // Get list reportingYear
	   List<mAcademicYear> topicReportingAcademicDateList = academicYearService.list();
	   
	   // Put data back to view
	   model.put("topicReportingAcademicDate", topicReportingAcademicDateList);
	   model.put("topicCategory", topicCategoryList);
	   model.put("topics", status);
	   if(result.hasErrors()) {
           return "cp.addATopic";
       }else
       {
    	   // Prepare data for inserting DB
    	   String userCode 						= session.getAttribute("currentUserCode").toString();
    	   String topicPubName 					= topicValid.getTopicName();
    	   String topicCategory 				= topicValid.getTopicCatCode();
    	   String topicReportingAcademicDate 	= topicValid.getTopicReportingAcademicDate();
    	   int topicConVertedHours 				= topicValid.getTopicConHours();
    	   int topicAutConHours 				= topicValid.getTopicAutConHours();
    	   int topicYear 						= topicValid.getTopicYear();
    	   int topicBudget	 					= topicValid.getBudget();
    	   String topicMemberRole				= topicValid.getTopicMemberRole();
    	   String topicStartDate				= topicValid.getTopicStartDate();
    	   String topicEndDate				    = topicValid.getTopicEndDate();
    	   String topicSponsor				    = topicValid.getTopicSponsor();
    	   String topicApprover				    = topicValid.getTopicApproveUser();
    	   
    	   int i_InsertATopic = tProjectService.saveATopic(userCode, topicPubName, topicCategory, topicConVertedHours, topicAutConHours, 
    			   											topicYear, topicBudget, topicReportingAcademicDate, topicMemberRole, topicSponsor, topicApprover, topicStartDate, topicEndDate);
    	   if(i_InsertATopic > 0){
    		   //model.put("status", "Successfully saved a topic.");
    		   return "redirect:" + this.baseUrl + "/cp/topics.html";
    	   }
           return "cp.addATopic";
       }
   }
   
   	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/topicsExcell", method = RequestMethod.POST)
	public ModelAndView downloadExcel(@Valid @ModelAttribute("topicExcellForm") mTopicExcellValidation topicValidExcell, BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateTopics");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = topicValidExcell.getReportingAcademicDate();
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    List<mTopics> topicsList = tProjectService.loadTopicListByYear(userRole, currentUserCode, yearForGenerating);
		    
		    /**
		     * Get list of all Patents
		     */
		    List<mPatents> patentsList = patentService.loadPatentListByYear(userRole, currentUserCode, yearForGenerating);
		    model.put("patentsList", patentsList);
		    /**
		     * Get staff's information
		     */
			mStaff staff = staffService.loadStaffByUserCode(currentUserCode);
			model.put("yearOfPaper", yearForGenerating);
			if(staff != null){
			    List<mDepartment> departmentList = departmentService.loadDepartmentList();
			    model.put("staffEmail", staff.getStaff_Email());
			    model.put("staffName", staff.getStaff_Name());
			    model.put("staffPhone", staff.getStaff_Phone());
			    model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
			    model.put("staffDepartementName", staff.getDepartment().getDepartment_Name());
			    model.put("staffDepartementCode", staff.getDepartment().getDepartment_Code());
			}
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelTopicsView", "topicsList", topicsList);
	     }
	}
	
	/**
    * Adding a topic
    * @param model
    * @return
    */
   @RequestMapping(value = "/gen-a-topic", method = RequestMethod.GET)
   public String generateAPaper(ModelMap model, HttpSession session) {
	   
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("topicExcellForm", new mTopicExcellValidation());
       return "cp.generateTopics";
   }
   
   @RequestMapping("/topicdetail/{id}")
   public String editATopic(ModelMap model, @PathVariable("id") int topicId, HttpSession session) {
	   
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   mTopics topic = tProjectService.loadATopicByIdAndUserCode(userRole, userCode, topicId);
	   // Get list reportingYear
	   List<mAcademicYear> topicReportingAcademicDateList = academicYearService.list();
	   List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService.getList();
	   
	   // Put data back to view
	   model.put("memberRolesList", memberRolesList);
	   model.put("topicReportingAcademicDate", topicReportingAcademicDateList);
	   model.put("topics", status);
	   if(topic != null)
	   {
		   // Get topic's category
		   List<mTopicCategory> topicCategoryList = tProjectCategoryService.list();
		   
		   // Put journal list and topic category to view
		   model.put("topicCategory", topicCategoryList);
		   model.put("topicFormEdit", new mTopicValidation());
		   model.put("topic", topic);
		   model.put("topicId", topicId);
		   model.put("topicCatCode", topic.getPROJDECL_ProjCategory_Code());
		   model.put("reportingDate", topic.getPROJDECL_ReportingAcademicDate());
		   
		   return "cp.editATopic";
	   }
	   return "cp.notFound404";
   }
   
   /**
    * Adding a topic
    * @param model
    * @return
    */
   @RequestMapping(value = "/edit-a-topic", method = RequestMethod.POST)
   public String updateATopic(@Valid @ModelAttribute("topicFormEdit") mTopicValidation topicFormEdit, BindingResult result, Map model, HttpSession session) {
	   
	  // Get topic's category
	  List<mTopicCategory> topicCategoryList = tProjectCategoryService.list();
	  // Get list reportingYear
	  List<mAcademicYear> topicReportingAcademicDateList = academicYearService.list();
	   
	  // Put data back to view
	  model.put("topicReportingAcademicDate", topicReportingAcademicDateList);
	  model.put("topicCategory", topicCategoryList);
	  model.put("topics", status);
      if (result.hasErrors()) {
    	   model.put("topicCatCode", topicFormEdit.getTopicCatCode());
		   model.put("topicName", topicFormEdit.getTopicName());
		   model.put("topicConHours", topicFormEdit.getTopicConHours());
		   model.put("topicAutConHours", topicFormEdit.getTopicAutConHours());
		   model.put("topicYear", topicFormEdit.getTopicYear());
		   model.put("budget", topicFormEdit.getBudget());
		   
          return "cp.editATopic";
      }else
      {
    	  String userRole = session.getAttribute("currentUserRole").toString();
    	  String userCode = session.getAttribute("currentUserCode").toString();
    	  
   	      // Prepare data for inserting DB
	   	  String topicPubName 				= topicFormEdit.getTopicName();
	   	  String topicCategory 				= topicFormEdit.getTopicCatCode();
	   	  String topicReportingAcademicDate = topicFormEdit.getTopicReportingAcademicDate();
	   	  int topicConVertedHours 			= topicFormEdit.getTopicConHours();
	   	  int topicAutConHours 				= (topicFormEdit.getTopicAutConHours() != null) ? topicFormEdit.getTopicAutConHours() : 0;
	   	  int topicYear 					= topicFormEdit.getTopicYear();
	   	  int topicBudget	 				= (topicFormEdit.getBudget() != null) ? topicFormEdit.getBudget() : 0;
    	  int topicId 						= topicFormEdit.getTopicId();
    	  String topicMemberRole			= topicFormEdit.getTopicMemberRole();
   	   	  String topicStartDate				= topicFormEdit.getTopicStartDate();
   	   	  String topicEndDate				= topicFormEdit.getTopicEndDate();
   	      String topicSponsor				= topicFormEdit.getTopicSponsor();
   	      String topicApprover				= topicFormEdit.getTopicApproveUser();
          
    	  tProjectService.editATopic(userRole, userCode, topicId, topicPubName, topicCategory, topicConVertedHours, topicAutConHours, topicYear, topicBudget, 
    			  						topicReportingAcademicDate, topicMemberRole, topicSponsor, topicApprover, topicStartDate, topicEndDate);
          //model.put("status", "Successfully edited project");
          return "redirect:" + this.baseUrl + "/cp/topics.html";
      }
   }
   
   /**
    * Remove a topic
    * @param model
    * @return
    */
   @RequestMapping(value = "/remove-a-topic/{id}", method = RequestMethod.GET)
   public String removeATopic(ModelMap model, @PathVariable("id") int topicId, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   mTopics topic = tProjectService.loadATopicByIdAndUserCode(userRole, userCode, topicId);
	   model.put("topics", status);
	   if(topic != null){
		   tProjectService.removeATopic(topicId);
		   List<mTopics> topicsList = tProjectService.loadTopicListByStaff(userRole, userCode);
		   model.put("topicsList", topicsList);
		   return "cp.topics";
	   }
	   return "cp.notFound404";
   }
   
}
