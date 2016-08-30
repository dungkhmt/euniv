package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.SetPaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.SetPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchdeclarationmanagement.service.PaperStaffsService;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mBookService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperCategoryHourBudgetService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchdeclarationmanagement.validation.mBookExcellValidation;
import vn.webapp.modules.researchdeclarationmanagement.validation.mExcel01CN02CNValidation;
import vn.webapp.modules.researchdeclarationmanagement.validation.mPaperExcellValidation;
import vn.webapp.modules.researchdeclarationmanagement.validation.mSummaryExcelValidation;
import vn.webapp.modules.usermanagement.controller.cp.mUserController;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmSummary")
@RequestMapping(value = {"/cp"})
public class mSummaryController extends BaseWeb {
	@Autowired
    private tProjectService tProjectService;
    
    @Autowired
    private tProjectCategoryService tProjectCategoryService;
    
    @Autowired
    private mJournalService journalService;
    
    @Autowired
    private mStaffService staffService;
    
    @Autowired
    private mBookService bookService;
    
    @Autowired
    private mDepartmentService departmentService;
    
    @Autowired
    private mPatentService patentService;
    
    @Autowired
    private mAcademicYearService academicYearService;
    
    @Autowired
    private mPaperService paperService;
    
    @Autowired
    private PaperStaffsService paperStaffsService;
    
    @Autowired
    private mPaperCategoryHourBudgetService paperCategoryHourBudgetService; 
    
    static final String status = "summary";
    
   
   	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/summaryExcel", method = RequestMethod.POST)
	public ModelAndView downloadSummaryExcel(@Valid @ModelAttribute("summaryExcelForm") mSummaryExcelValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<mDepartment> departmentList = departmentService.loadDepartmentList();
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			String departmentCode = summaryValidExcell.getDepartment();
			
			mDepartment department = departmentService.loadADepartmentByCodes(departmentCode, "SOICT");
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    
		    List<mStaff> staffs = staffService.listStaffsByDepartment(departmentCode);
		    List<mTopics> topicsList = tProjectService.loadTopicSummaryListByYear(yearForGenerating);
		    List<mPapers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    int iTotalPaperConvertedHours = 0;
    		int iTotalPaperJournal = 0;
    		int iTotalPaperConferenceJournal = 0;
    		
    		int iTotalTopicConvertedHours = 0;
    		int iTotalTopicNational = 0;
    		int iTotalTopicMinistry = 0;
    		int iTotalTopicFoundation = 0;
    		int iTotalTopicInternational = 0;
    		int iTotalTopicUniversity = 0;
    		
    		String staffName = "";
    		List<List<String>> summaryPapersList = new ArrayList<>();
    		List<List<String>> summaryProjectsList = new ArrayList<>();
    		Map<String, List<mPapers>> summaryAllStaffsList = new HashMap<String, List<mPapers>>(); 
    		
		    if(staffs != null && papersList != null){
		    	
		    	for(mStaff staff : staffs){
		    		// Set and reset info for a staff
		    		iTotalPaperConvertedHours = 0;
		    		iTotalPaperJournal = 0;
		    		iTotalPaperConferenceJournal = 0;
		    		
		    		iTotalTopicConvertedHours = 0;
		    		iTotalTopicNational = 0;
		    		iTotalTopicMinistry = 0;
		    		iTotalTopicFoundation = 0;
		    		iTotalTopicInternational = 0;
		    		iTotalTopicUniversity = 0;
		    		
		    		staffName = staff.getStaff_Name();
		    		List<String> summaryPaper = new ArrayList<>();
		    		List<String> summaryTopic = new ArrayList<>();
		    		List<mPapers> summaryStaff = new ArrayList<>();
		    		// Set data papers
		    		for(mPapers paper : papersList)
		    		{
		    			List<PaperStaffs> paperStaffs = paperStaffsService.loadPaperListByPaperCode(paper.getPDECL_Code());
		    			for(PaperStaffs ps: paperStaffs){
		    				if(ps.getPPSTF_StaffCode().equals(staff.getStaff_Code())){
		    					iTotalPaperConvertedHours += paper.getPDECL_AuthorConvertedHours();
			    				if(paper.getPDECL_PaperCategory_Code().equals("JINT_SCI") || 
			    						paper.getPDECL_PaperCategory_Code().equals("JINT_Other") ||
			    						paper.getPDECL_PaperCategory_Code().equals("JDOM_Other") ||
			    						paper.getPDECL_PaperCategory_Code().equals("JINT_SCIE"))
			    				{
			    					iTotalPaperJournal++;
			    				}else{
			    					iTotalPaperConferenceJournal++;
			    				}
			    				summaryStaff.add(paper);
		    				}
		    			}
		    			/*
		    			if(staff.getStaff_User_Code().equals(paper.getPDECL_User_Code())){
		    				iTotalPaperConvertedHours += paper.getPDECL_AuthorConvertedHours();
		    				if(paper.getPDECL_PaperCategory_Code().equals("JINT_SCI") || paper.getPDECL_PaperCategory_Code().equals("JINT_Other") || paper.getPDECL_PaperCategory_Code().equals("JINT_SCIE"))
		    				{
		    					iTotalPaperJournal++;
		    				}else{
		    					iTotalPaperConferenceJournal++;
		    				}
		    				summaryStaff.add(paper);
		    			}
		    			*/
		    		}
		    		// Build a paper summary
		    		summaryPaper.add(staffName);
		    		summaryPaper.add(Integer.toString(iTotalPaperJournal));
		    		summaryPaper.add(Integer.toString(iTotalPaperConferenceJournal));
		    		summaryPaper.add(Integer.toString(iTotalPaperConvertedHours));
		    		
		    		// Build list of paper summary
		    		summaryPapersList.add(summaryPaper);
		    		
		    		// Build list of staff summary 
		    		if(summaryStaff != null){
		    			summaryAllStaffsList.put(staffName, summaryStaff);
		    		}
		    		
		    		// Set data topics
		    		for(mTopics topic : topicsList){
		    			if(staff.getStaff_User_Code().equals(topic.getPROJDECL_User_Code())){
		    				iTotalTopicConvertedHours += topic.getPROJDECL_AuthorConvertedHours();
		    				if(topic.getPROJDECL_ProjCategory_Code().equals("NATIONAL")){
		    					iTotalTopicNational++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("EMINISTRY")){
		    					iTotalTopicMinistry++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("SMINISTRY")){
		    					iTotalTopicFoundation++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("INTERNATIONAL")){
		    					iTotalTopicInternational++;
		    				}else if(topic.getPROJDECL_ProjCategory_Code().equals("UNIVERSTITY")){
		    					iTotalTopicUniversity++;
		    				}
		    			}
		    		}
		    		summaryTopic.add(staffName);
		    		summaryTopic.add(Integer.toString(iTotalTopicNational));
		    		summaryTopic.add(Integer.toString(iTotalTopicMinistry));
		    		summaryTopic.add(Integer.toString(iTotalTopicFoundation));
		    		summaryTopic.add(Integer.toString(iTotalTopicInternational));
		    		summaryTopic.add(Integer.toString(iTotalTopicUniversity));
		    		summaryTopic.add("");
		    		summaryTopic.add(Integer.toString(iTotalTopicConvertedHours));
		    		summaryProjectsList.add(summaryTopic);
		    	}
		    }
		    
		    /**
		     * Put data to view
		     */
		    model.put("summaryPapersList", summaryPapersList);
		    model.put("summaryProjectsList", summaryProjectsList);
		    model.put("summaryAllStaffsList", summaryAllStaffsList);
			model.put("yearOfPaper", yearForGenerating);
			model.put("departmentCode", departmentCode);
			if(department != null){
			    model.put("staffDepartementName",department.getDepartment_Name());
			}
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelSummaryView", "topicsList", topicsList);
	     }
	}
	
	/**
    * Generating summary
    * @param model
    * @return
    */
   @RequestMapping(value = "/summary", method = RequestMethod.GET)
   public String generateASummary(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   String userCode = (String)session.getAttribute("currentUserCode");
	   String facultyCode = (String)session.getAttribute("facultyCode");
	   String userRole = (String)session.getAttribute("currentUserRole");
	   
	   System.out.println(name() + "::generateASummany, userCode = " + userCode + 
			   ", userRole = " + userRole + ", facultyCode = " + facultyCode);
	   
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   //List<mDepartment> departmentList = departmentService.loadDepartmentList();
	   List<mDepartment> departmentList = null;
	   
	   if(userRole.equals(mUserController.ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY))
		   departmentList = departmentService.loadADepartmentByFaculty(facultyCode);
	   else if(userRole.equals(mUserController.ROLE_ADMIN))
		   departmentList = departmentService.loadDepartmentList();
	   
	   /**
	    * Put back to a suitable view
	    */
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("departmentList", departmentList);
	   model.put("summaryExcelForm", new mSummaryExcelValidation());
       return "cp.generateSummary";
   }
   
   /**
    * Generate excel form 01CN-02CN
    */
   public String name(){
	   return "SummaryController";
   }
   @RequestMapping(value = "/generate-excel-01cn-02cn", method = RequestMethod.GET)
   public String generateExcel01CN02CN(ModelMap model, HttpSession session){
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String facultyCode = session.getAttribute("facultyCode").toString();
	   
	   System.out.println(name() + "::generateExcel01CN02CN, userCode = " + userCode + ", facultyCode = " + facultyCode);
	   mStaff staff = staffService.loadStaffByUserCode(userCode);
	   
	   List<mAcademicYear> lstYears = academicYearService.list();
	   List<mStaff> lstStaffs = null;
	   if(userRole.equals("ROLE_USER")){
		   lstStaffs = new ArrayList<mStaff>();		   
		   lstStaffs.add(staff);
	   }else{
		   if(userRole.equals(mUserController.ROLE_ADMIN) || userRole.equals(mUserController.SUPER_ADMIN))
			   lstStaffs = staffService.listStaffs();
		   else if(userRole.equals(mUserController.ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY))
				   lstStaffs = staffService.listStaffsByFalcuty(facultyCode);
	   }
	   model.put("reportingYear", lstYears);
	   model.put("listStaffs", lstStaffs);
	   model.put("staffname", staff.getStaff_Name());
	   
	   //model.put("generateExcelForm01CN02CN", new PaperExcellValidation());
	   model.put("generateExcelForm01CN02CN", new mExcel01CN02CNValidation());
	   return "cp.generateForm01CN02CN";
   }
   
   
   @RequestMapping(value = "/generateExcel01CN02CN", method = RequestMethod.POST)
   public ModelAndView downloadExcel01CN02CN(@Valid @ModelAttribute("generateExcelForm01CN02CN") mExcel01CN02CNValidation paperValidExcel, BindingResult result, Map model, HttpSession session){
	   if(result.hasErrors()) {
	         return new ModelAndView("cp.generateSummary");
	     }else {
	    	 String userCode = paperValidExcel.getStaffCode(); 
	    	 mStaff staff = staffService.loadStaffByUserCode(userCode);
	    	 String yearGenerate = paperValidExcel.getReportingAcademicDate();
	 		//String userName = session.getAttribute("currentUserName").toString();
	 		//String userCode = session.getAttribute("currentUserCode").toString();
	 		String userRole = session.getAttribute("currentUserRole").toString();
	 		
	 		model.put("yearOfPaper", paperValidExcel.getReportingAcademicDate());
	 		if(staff != null){
	 			model.put("staffCode", userCode);
	 		    model.put("staffEmail", staff.getStaff_Email());
	 		    model.put("staffName", staff.getStaff_Name());
	 		    model.put("staffPhone", staff.getStaff_Phone());
	 		    model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
	 		    model.put("staffDepartementName", staff.getDepartment().getDepartment_Name());
	 		    model.put("staffDepartementCode", staff.getDepartment().getDepartment_Code());
	 		}
	 		
	 		List<mPapers> listPapers = paperService.loadPaperListByYear(userRole, userCode, yearGenerate);
	 		List<mTopics> listTopics = tProjectService.loadTopicListByYear(userRole, userCode, yearGenerate);
	 		List<mPatents> listPatents = patentService.loadPatentListByYear(userRole, userCode, yearGenerate);
	 		model.put("listPapers", listPapers);
	 		model.put("listTopics", listTopics);
	 		model.put("listPatents", listPatents);
	 		return new ModelAndView("excel01CN02CN","listPapers", listPapers);
	     }
	   
   }
   
   /**
    * Generating kv 03
    * @param model
    * @return
    */
   @RequestMapping(value = "/summary-kv03", method = RequestMethod.GET)
   public String generateASummaryKV03(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   /**
	    * Put back to a suitable view
	    */
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("summaryExcelFormKV03", new mPaperExcellValidation());
       return "cp.generateSummaryKV03";
   }
   
   /**
    * Generating kv 04
    * @param model
    * @return
    */
   @RequestMapping(value = "/summary-kv04", method = RequestMethod.GET)
   public String generateASummaryKV04(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   /**
	    * Put back to a suitable view
	    */
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("summaryExcelFormKV04", new mPaperExcellValidation());
       return "cp.generateSummaryKV04";
   }
   
   /**
    * 
    * @param model
    * @param session
    * @return
    */
   @RequestMapping(value = "/summary-kv-list-papers", method = RequestMethod.GET)
   public String generateListPapers(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   /**
	    * Put back to a suitable view
	    */
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("listpapers", new mPaperExcellValidation());
       return "cp.generateListPapers";
   }
   
   /**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/summaryExcelKV03", method = RequestMethod.POST)
	public ModelAndView downloadSummaryExcelKV03(@Valid @ModelAttribute("summaryExcelFormKV03") mPaperExcellValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<mDepartment> departmentList = departmentService.loadDepartmentList();
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    
		    List<mPapers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
		    
		    List<mPapers> papersListJDOM_Other = new ArrayList<mPapers>(); // Tap chi trong nuoc
		    List<mPapers> papersListJINT = new ArrayList<mPapers>(); // Tap chi nuoc ngoai
		    List<mPapers> papersListCDOM_Other = new ArrayList<mPapers>(); // Hoi nghi trong nuoc
		    List<mPapers> papersListCINT_Other = new ArrayList<mPapers>(); // Hoi nghi nuoc ngoai
		    
		    if(papersList != null)
		    {
			    for(mPapers oPaper : papersList)
			    {
			    	
			    	if(oPaper.getPaperCategory().getPCAT_Code().equals("CINT_Other")){
			    		papersListCINT_Other.add(oPaper);
			    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("CDOM_Other"))
			    	{
			    		papersListCDOM_Other.add(oPaper);
			    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("JDOM_Other"))
			    	{
			    		papersListJDOM_Other.add(oPaper);
			    	}else{
			    		papersListJINT.add(oPaper);
			    	}
			    }
		    }
		    
		    model.put("papersListJDOM_Other", papersListJDOM_Other);
		    model.put("papersListJINT", papersListJINT);
		    model.put("papersListCDOM_Other", papersListCDOM_Other);
		    model.put("papersListCINT_Other", papersListCINT_Other);
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    model.put("yearOfPaper", yearForGenerating);
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelSummaryViewKV03", "papersList", papersList);
	     }
	}
	
	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/newSummaryExcelKV03", method = RequestMethod.POST)
	public ModelAndView downloadNewSummaryExcelKV03(@Valid @ModelAttribute("summaryExcelFormKV03") mPaperExcellValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<mDepartment> departmentList = departmentService.loadDepartmentList();
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    
		    List<mPapers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
		    
		    List<mPapers> papersListJulyToDec =  new ArrayList<mPapers>();
		    List<mPapers> papersListJanToJune =  new ArrayList<mPapers>();
		    
		    List<mPapers> papersListJDOM_Other1 = new ArrayList<mPapers>(); // Tap chi trong nuoc
		    List<mPapers> papersListJINT1 = new ArrayList<mPapers>(); // Tap chi nuoc ngoai
		    List<mPapers> papersListCDOM_Other1 = new ArrayList<mPapers>(); // Hoi nghi trong nuoc
		    List<mPapers> papersListCINT_Other1 = new ArrayList<mPapers>(); // Hoi nghi nuoc ngoai
		    
		    List<mPapers> papersListJDOM_Other2 = new ArrayList<mPapers>(); // Tap chi trong nuoc
		    List<mPapers> papersListJINT2 = new ArrayList<mPapers>(); // Tap chi nuoc ngoai
		    List<mPapers> papersListCDOM_Other2 = new ArrayList<mPapers>(); // Hoi nghi trong nuoc
		    List<mPapers> papersListCINT_Other2 = new ArrayList<mPapers>(); // Hoi nghi nuoc ngoai
		    
		    if(papersList != null)
		    {
			    for(mPapers oPaper : papersList)
			    {
			    	if(oPaper.getPDECL_Year() > 0 &&  oPaper.getPDECL_Month() != null){
				    	if(oPaper.getPDECL_Year() == 2015 && Integer.parseInt(oPaper.getPDECL_Month()) >=7 && Integer.parseInt(oPaper.getPDECL_Month()) <= 12){
				    		if(oPaper.getPaperCategory().getPCAT_Code().equals("CINT_Other")){
					    		papersListCINT_Other1.add(oPaper);
					    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("CDOM_Other"))
					    	{
					    		papersListCDOM_Other1.add(oPaper);
					    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("JDOM_Other"))
					    	{
					    		papersListJDOM_Other1.add(oPaper);
					    	}else{
					    		papersListJINT1.add(oPaper);
					    	}
				    	}else if(oPaper.getPDECL_Year() == 2016 && Integer.parseInt(oPaper.getPDECL_Month()) >=1 && Integer.parseInt(oPaper.getPDECL_Month()) <= 6){
				    		if(oPaper.getPaperCategory().getPCAT_Code().equals("CINT_Other")){
					    		papersListCINT_Other2.add(oPaper);
					    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("CDOM_Other"))
					    	{
					    		papersListCDOM_Other2.add(oPaper);
					    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("JDOM_Other"))
					    	{
					    		papersListJDOM_Other2.add(oPaper);
					    	}else{
					    		papersListJINT2.add(oPaper);
					    	}
				    	}
			    	}
			    	
			    }
		    }
		    
		    model.put("papersListJDOM_Other1", papersListJDOM_Other1);
		    model.put("papersListJINT1", papersListJINT1);
		    model.put("papersListCDOM_Other1", papersListCDOM_Other1);
		    model.put("papersListCINT_Other1", papersListCINT_Other1);
		    
		    model.put("papersListJDOM_Other2", papersListJDOM_Other2);
		    model.put("papersListJINT2", papersListJINT2);
		    model.put("papersListCDOM_Other2", papersListCDOM_Other2);
		    model.put("papersListCINT_Other2", papersListCINT_Other2);
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    model.put("yearOfPaper", yearForGenerating);
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("newExcelSummaryViewKV03", "papersList", papersList);
	     }
	}
	
	/**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/summaryExcelKV04", method = RequestMethod.POST)
	public ModelAndView downloadSummaryExcelKV04(@Valid @ModelAttribute("summaryExcelFormKV04") mPaperExcellValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<mDepartment> departmentList = departmentService.loadDepartmentList();
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    
		    List<mPapers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
		    for(mPapers p: papersList){
		    	System.out.println(name() + "::downloadSummaryExcelKV04, GET paper " + p.getPDECL_PublicationName() + 
		    			", volumn = " + p.getPDECL_Volumn());
		    }
		    List<mPapersCategoryHourBudget> papersCategoryHourBudgets = paperCategoryHourBudgetService.loadPaperCategoryHourBudgetByYear(yearForGenerating);
		    
		    List<mPapers> papersListCINT_Other = new ArrayList<mPapers>(); // Hoi nghi nuoc ngoai
		    List<mPapers> papersListCDOM_Other = new ArrayList<mPapers>(); // Hoi nghi trong nuoc
		    List<mPapers> papersListJDOM_Other = new ArrayList<mPapers>(); // Tap chi trong nuoc
		    List<mPapers> papersListJINT = new ArrayList<mPapers>(); // Tap chi nuoc ngoai
		    
		    List<SetPapers> SetPapersListCINT_Other = new ArrayList<SetPapers>(); // Hoi nghi nuoc ngoai
		    List<SetPapers> SetPapersListCDOM_Other = new ArrayList<SetPapers>(); // Hoi nghi trong nuoc
		    List<SetPapers> SetPapersListJDOM_Other = new ArrayList<SetPapers>(); // Tap chi trong nuoc
		    List<SetPapers> SetPapersListJINT = new ArrayList<SetPapers>(); // Tap chi nuoc ngoai
		    
		    List<String> listCINT_OtherPaperCodes = new ArrayList<>();
		    List<String> listCDOM_OtherPaperCodes = new ArrayList<>();
		    List<String> listJDOM_OtherPaperCodes = new ArrayList<>();
		    List<String> listJINT_PaperCodes = new ArrayList<>();
		    
		    // Preparing budget value;
			int i_JDOMOther_Budget = 0;
			int i_CINTOther_Budget = 0;
			int i_CDOMOther_Budget = 0;
			int i_JINT_Budget = 0;
			
		    if(papersList != null)
		    {
			    for(mPapers oPaper : papersList)
			    {
			    	if(oPaper.getPaperCategory().getPCAT_Code().equals("CINT_Other")){
			    		listCINT_OtherPaperCodes.add(oPaper.getPDECL_Code());
			    		papersListCINT_Other.add(oPaper);
			    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("CDOM_Other"))
			    	{
			    		listCDOM_OtherPaperCodes.add(oPaper.getPDECL_Code());
			    		papersListCDOM_Other.add(oPaper);
			    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("JDOM_Other"))
			    	{
			    		listJDOM_OtherPaperCodes.add(oPaper.getPDECL_Code());
			    		papersListJDOM_Other.add(oPaper);
			    	}else if(oPaper.getPaperCategory().getPCAT_Code().equals("JINT_Other")){
			    		listJINT_PaperCodes.add(oPaper.getPDECL_Code());
			    		papersListJINT.add(oPaper);
			    	}
			    }

			    List<PaperStaffs> paperStaffCINT_OtherList = paperStaffsService.loadPaperListByPaperCode(listCINT_OtherPaperCodes);
			    List<PaperStaffs> paperStaffCDOM_OtherList = paperStaffsService.loadPaperListByPaperCode(listCDOM_OtherPaperCodes);
			    List<PaperStaffs> paperStaffJDOM_OtherList = paperStaffsService.loadPaperListByPaperCode(listJDOM_OtherPaperCodes);
			    List<PaperStaffs> paperStaffJINTList = paperStaffsService.loadPaperListByPaperCode(listJINT_PaperCodes);
			    
			    
				if(papersCategoryHourBudgets != null && papersCategoryHourBudgets.size() > 0)
				{
					for (mPapersCategoryHourBudget papersCategoryHourBudget : papersCategoryHourBudgets) {
						if("CINT_Other".equals(papersCategoryHourBudget.getPCAHOBUD_PaperCategoryCode()))
						{
							i_CINTOther_Budget = papersCategoryHourBudget.getPCAHOBUD_Budget();
							
						}else if("CDOM_Other".equals(papersCategoryHourBudget.getPCAHOBUD_PaperCategoryCode()))
						{
							i_CDOMOther_Budget = papersCategoryHourBudget.getPCAHOBUD_Budget();
							
						}else if("JDOM_Other".equals(papersCategoryHourBudget.getPCAHOBUD_PaperCategoryCode()))
						{
							i_JDOMOther_Budget = papersCategoryHourBudget.getPCAHOBUD_Budget();
							
						}else{
							i_JINT_Budget = papersCategoryHourBudget.getPCAHOBUD_Budget();
						}
					}
				}

				if(papersListCDOM_Other != null && paperStaffCDOM_OtherList != null){
			    	SetPapersListCDOM_Other = prepareSetPagesData(papersListCDOM_Other, paperStaffCDOM_OtherList, i_CDOMOther_Budget);
			    }
				
				if(papersListCINT_Other != null && paperStaffCINT_OtherList != null){
			    	SetPapersListCINT_Other = prepareSetPagesData(papersListCINT_Other, paperStaffCINT_OtherList, i_CINTOther_Budget);
			    }
				
				if(papersListJDOM_Other != null && paperStaffJDOM_OtherList != null){
			    	SetPapersListJDOM_Other = prepareSetPagesData(papersListJDOM_Other, paperStaffJDOM_OtherList, i_JDOMOther_Budget);
			    }
				
			    if(papersListJINT != null && paperStaffJINTList != null){
			    	SetPapersListJINT = prepareSetPagesData(papersListJINT, paperStaffJINTList, i_JINT_Budget);
			    }
		    }
		    
		    for(SetPapers p: SetPapersListCDOM_Other){
		    	System.out.println(name() + "::downloadSummaryExcelKV04, GET CDOM SetPapers " + p.getPaperPublicName());
		    }
		    for(SetPapers p: SetPapersListCINT_Other){
		    	System.out.println(name() + "::downloadSummaryExcelKV04, GET CINT SetPapers " + p.getPaperPublicName());
		    }
		    for(SetPapers p: SetPapersListJDOM_Other){
		    	System.out.println(name() + "::downloadSummaryExcelKV04, GET JDOM SetPapers " + p.getPaperPublicName());
		    }
		    for(SetPapers p: SetPapersListJINT){
		    	System.out.println(name() + "::downloadSummaryExcelKV04, GET JINT SetPapers " + p.getPaperPublicName());
		    }
		    
		    
		    model.put("SetPapersListJINT", SetPapersListJINT);
		    model.put("SetPapersListJDOM_Other", SetPapersListJDOM_Other);
		    model.put("SetPapersListCINT_Other", SetPapersListCINT_Other);
		    model.put("SetPapersListCDOM_Other", SetPapersListCDOM_Other);
		    
		    model.put("i_JDOMOther_Budget", i_JDOMOther_Budget);
		    model.put("i_CINTOther_Budget", i_CINTOther_Budget);
		    model.put("i_CDOMOther_Budget", i_CDOMOther_Budget);
		    model.put("i_JINT_Budget", i_JINT_Budget);
		    
		    model.put("papersListJDOM_Other", papersListJDOM_Other);
		    model.put("papersListJINT", papersListJINT);
		    model.put("papersListCDOM_Other", papersListCDOM_Other);
		    model.put("papersListCINT_Other", papersListCINT_Other);
		    model.put("papersCategoryHourBudget", papersCategoryHourBudgets);
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    model.put("yearOfPaper", yearForGenerating);
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelSummaryViewKV04", "papersList", papersList);
	     }
	}
	
	private List<SetPapers> prepareSetPagesData(List<mPapers> listPapers, List<PaperStaffs> paperStaffsList, int budget)
	{
		List<SetPapers> SetPapersList = new ArrayList<SetPapers>();
		
		int i_BudgetPerMember = 0;
    	int i_InternalNumberAuthors = 0;
    	int i_NumberAuthors = 0;
	    for (mPapers aPaper : listPapers) {
	    	String[] listAuthors = aPaper.getPDECL_AuthorList().split(",");
	    	i_NumberAuthors = listAuthors.length;
	    	i_InternalNumberAuthors = 0;
	    	SetPapers tempPaper = new SetPapers();
	    	
	    	tempPaper.setPaperAuthorsName(aPaper.getPDECL_AuthorList());
	    	tempPaper.setPaperPublicName(aPaper.getPDECL_PublicationName());
	    	tempPaper.setPaperJournalConferencecName(aPaper.getPDECL_JournalConferenceName());
	    	tempPaper.setPaperVolumn(aPaper.getPDECL_Volumn() + aPaper.getPDECL_Year());
	    	tempPaper.setPaperISSN(aPaper.getPDECL_ISSN());
	    	tempPaper.setPaperGeneralBudget(Integer.toString(budget));
	    	tempPaper.setPaperTotalNumberOfAuthors(listAuthors.length);
	    	Set<SetPaperStaffs> tempPaperStaffs = new HashSet<>();				    	
	    	for (PaperStaffs APaperStaff : paperStaffsList) {
	    		System.out.println(name() + "::prepareSetPagesData, paper " + aPaper.getPDECL_PublicationName() + " consider staff " + APaperStaff.getPPSTF_StaffCode());
	    		SetPaperStaffs tempSetPaperStaff = new SetPaperStaffs();
				if(aPaper.getPDECL_Code().equals(APaperStaff.getPPSTF_PaperCode())){
					tempSetPaperStaff.setPaperStaffName(APaperStaff.getStaff().getStaff_Name());
					tempSetPaperStaff.setPaperStaffBudget(Integer.toString(0));
					i_InternalNumberAuthors++;
					tempPaperStaffs.add(tempSetPaperStaff);
					System.out.println(name() + "::prepareSetPagesData, paper " + aPaper.getPDECL_PublicationName() + " with staff " + APaperStaff.getPPSTF_StaffCode());
				}
			}
	    	System.out.println(name() + "::prepareSetPagesData, nbInternalNumberAuthors = " + i_InternalNumberAuthors + ", nb authors = " + i_NumberAuthors);
	    	
	    	tempPaper.setPaperTotalNumberOfInternalAuthors(i_InternalNumberAuthors);
	    	if(i_InternalNumberAuthors > 0){
	    		//tempPaper.setPaperBudgetForOneAuthor(budget/i_InternalNumberAuthors);// BUG
	    		tempPaper.setPaperBudgetForOneAuthor(budget/i_NumberAuthors);// fixed by DungPQ
	    	}else{
	    		tempPaper.setPaperBudgetForOneAuthor(0);
	    	}
	    	//tempPaper.setPaperBudgetOfInternalAuthors(budget*(i_InternalNumberAuthors/listAuthors.length));// BUG
	    	tempPaper.setPaperBudgetOfInternalAuthors((budget*i_InternalNumberAuthors)/i_NumberAuthors);// fixed by DungPQ
	    	tempPaper.setPaperStaffs(tempPaperStaffs);
	    	SetPapersList.add(tempPaper);
		}
		return SetPapersList;
	}
	
	/**
    * Generating kv 01
    * @param model
    * @return
    */
   @RequestMapping(value = "/summary-kv01", method = RequestMethod.GET)
   public String generateASummaryKV01(ModelMap model, HttpSession session) {
	   /**
	    * Get List Academic Year and DepartmentList
	    */
	   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
	   /**
	    * Put back to a suitable view
	    */
	   
	   
	   model.put("reportingAcademicDate", patentReportingAcademicDateList);
	   model.put("summaryExcelFormKV01", new mPaperExcellValidation());
       return "cp.generateSummaryKV01";
   }
   
   /**
	 * Handle request to download an Excel 97-2003 document 
	 */
	@RequestMapping(value = "/summaryExcelKV01", method = RequestMethod.POST)
	public ModelAndView downloadSummaryExcelKV01(@Valid @ModelAttribute("summaryExcelFormKV01") mPaperExcellValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
		
		String userCode = (String)session.getAttribute("currentUserCode");
		String userRole = (String)session.getAttribute("currentUserRole");
		String facultyCode = (String)session.getAttribute("facultyCode");
		
		
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		List<mDepartment> departmentList = null;
		if(userRole.equals(mUserController.ROLE_ADMIN) || userRole.equals(mUserController.SUPER_ADMIN) )
			departmentList = departmentService.loadDepartmentList();
		else 
			departmentList = departmentService.loadADepartmentByFaculty(facultyCode);
		
		
		System.out.println(name() + "::downloadSummaryExcelKV01, userCode = " + userCode);
		/**
	    * Put back to a suitable view
	    */
	    model.put("reportingAcademicDate", patentReportingAcademicDateList);
	    model.put("departmentList", departmentList);
		 
	    // create some sample data
		 if(result.hasErrors()) {
	          return new ModelAndView("cp.generateSummary");
	     }else
	     {
	    	/**
	    	 * Get list of all Projects (Topics)
	    	 */
			String yearForGenerating = summaryValidExcell.getReportingAcademicDate();
			List<mPapers> papersList = paperService.loadPaperSummaryListByYear(yearForGenerating);
			//List<mPatents> patentsList = patentService.loadPatentSummaryListByYear(yearForGenerating);
			List<mTopics> topicList = tProjectService.loadTopicSummaryListByYear(yearForGenerating);
			
			Map<String, Map<String, List<Integer>>> summaryAllDepartmentStaffList = new HashMap<String, Map<String, List<Integer>>>();
		    List<mDepartment> departments = null;//departmentService.loadDepartmentList();
			if(userRole.equals(mUserController.ROLE_ADMIN) || userRole.equals(mUserController.SUPER_ADMIN) )
				departments = departmentService.loadDepartmentList();
			else 
				departments = departmentService.loadADepartmentByFaculty(facultyCode);
		    
		    /**
		     * Preparing data for generating
		     */
		    int iTotalConvertedHours = 0; // Total for the 3rd column
		    int iTotalStaffConvertedHours = 0; // Total for the 3rd header
		    int iTotalPaperConvertedHours = 0; // Total for paper
		    int iTotalPaperOfAStaffConvertedHours = 0; // Total for all papers of a Staff
		    //int iTotalPatentConvertedHours = 0; // Total for patent
		    //int iTotalPatentOfAStaffConvertedHours = 0; // Total for all patents of a Staff
		    int iTotalProjectConvertedHours = 0; // Total for all projects
		    int iTotalProjectOfAStaffConvertedHours = 0; // Total for all projects of a Staff
		    
		    String staffName = "";
		    if(departments != null)
		    {
		    	for(mDepartment department : departments)
		    	{
				   List<mStaff> staffs = staffService.listStaffsByDepartment(department.getDepartment_Code());
				   Map<String, List<Integer>> summaryAllStaffsListTemp = new HashMap<String, List<Integer>>();
				   for(mStaff staff : staffs)
				   {
					   //if(staff.getStaff_Code().equals("khanh.nguyenkim@hust.edu.vn"))
						//	   System.out.println(name() + "::downloadSummaryExcelKV01, department " + department.getDepartment_Name() + ", staff " + staff.getStaff_Name());
					   // Set and reset info for a staff
					   staffName = staff.getStaff_Name();
					   iTotalPaperOfAStaffConvertedHours = 0;
					  // iTotalPatentOfAStaffConvertedHours = 0;
					   iTotalProjectOfAStaffConvertedHours = 0;
					   List<mPapers> summaryStaff = new ArrayList<>();
					   List<Integer> listConvertedHours = new ArrayList<>();
					   for(mPapers paper : papersList)
					   {
						   	List<PaperStaffs>  paperStaffs = paperStaffsService.loadPaperListByPaperCode(paper.getPDECL_Code());
						    boolean ok = false;
						    for(PaperStaffs ps: paperStaffs)
						    	if(ps.getPPSTF_StaffCode().equals(staff.getStaff_Code())) ok = true;
						    
						    //if(paper.getPDECL_Code().equals("nga.nguyenthithanh@hust.edu.vn233")){
						    //	System.out.println(name() + "::downloadSummaryExcelKV01, paperStaffs.sz = " + paperStaffs.size());
						    //	for(PaperStaffs ps: paperStaffs)
						    //		System.out.println(ps.getPPSTF_StaffCode() + " staff " + staff.getStaff_Code() + ", ok = " + ok);
						    //}
						    
						    	//if(staff.getStaff_User_Code().equals(paper.getPDECL_User_Code())){
						    if(ok){
			    				//if(paper.getPDECL_User_Code().equals("trung.tranviet@hust.edu.vn")){
			    				//if(paper.getPDECL_User_Code().equals("khanh.nguyenkim@hust.edu.vn")){
			    				//	System.out.println(name() + "::downloadSummaryExcelKV01, paper " + paper.getPDECL_User_Code() + 
			    				//			", " + paper.getPDECL_PublicationName() + ", hour = " + paper.getPDECL_AuthorConvertedHours());
			    					
			    					iTotalPaperConvertedHours += paper.getPDECL_AuthorConvertedHours();
			    					iTotalPaperOfAStaffConvertedHours += paper.getPDECL_AuthorConvertedHours();
			    				//}
			    			}
					   }
					   
					   /*
					   for(Patents patent : patentsList)
					   {
						   if(staff.getStaff_User_Code().equals(patent.getPAT_User_Code())){
							    iTotalPatentConvertedHours += patent.getPAT_AuthorConvertedHours();
							    iTotalPatentOfAStaffConvertedHours += patent.getPAT_AuthorConvertedHours();
			    			}
					   }
					   */
					   for(mTopics t : topicList)
					   {
						   if(staff.getStaff_User_Code().equals(t.getPROJDECL_User_Code())){
							    iTotalProjectConvertedHours += t.getPROJDECL_AuthorConvertedHours();
							    iTotalProjectOfAStaffConvertedHours += t.getPROJDECL_AuthorConvertedHours();
			    			}
					   }
					   //iTotalStaffConvertedHours = iTotalPaperOfAStaffConvertedHours + iTotalPatentOfAStaffConvertedHours;
					   iTotalStaffConvertedHours = iTotalPaperOfAStaffConvertedHours + iTotalProjectOfAStaffConvertedHours;
					   listConvertedHours.add(iTotalPaperOfAStaffConvertedHours);
					   //listConvertedHours.add(iTotalPatentOfAStaffConvertedHours);
					   listConvertedHours.add(iTotalProjectOfAStaffConvertedHours);
					   
					   listConvertedHours.add(iTotalStaffConvertedHours);
					   
					   summaryAllStaffsListTemp.put(staffName, listConvertedHours);
				   }
				   summaryAllDepartmentStaffList.put(department.getDepartment_Name(), summaryAllStaffsListTemp);
		    	}
		    	//iTotalConvertedHours = iTotalPaperConvertedHours + iTotalPatentConvertedHours;
		    	iTotalConvertedHours = iTotalPaperConvertedHours + iTotalProjectConvertedHours;
		    }
			
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    //String userRole = session.getAttribute("currentUserRole").toString();
		    
		    /**
		     * Preparing data for papers summary view 
		     */
		    model.put("iTotalPaperConvertedHours", iTotalPaperConvertedHours);
		    //model.put("iTotalPatentConvertedHours", iTotalPatentConvertedHours);
		    model.put("iTotalProjectConvertedHours", iTotalProjectConvertedHours);
		    model.put("iTotalConvertedHours", iTotalConvertedHours);
		    model.put("summaryAllDepartmentStaffList", summaryAllDepartmentStaffList);
		    model.put("yearOfPaper", yearForGenerating);
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelSummaryViewKV01", "papersList", papersList);
	     }
	}
	
	/**
	    * Generating isi 02
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/summary-isi02", method = RequestMethod.GET)
	   public String generateASummaryISI02(ModelMap model, HttpSession session) {
		   /**
		    * Get List Academic Year and DepartmentList
		    */
		   List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
		   /**
		    * Put back to a suitable view
		    */
		   model.put("reportingAcademicDate", patentReportingAcademicDateList);
		   model.put("summaryExcelFormISI02", new mPaperExcellValidation());
	       return "cp.generateSummaryISI02";
	   }
	   
	   /**
		 * Handle request to download an Excel 97-2003 document 
		 */
		@RequestMapping(value = "/summaryExcelISI02", method = RequestMethod.POST)
		public ModelAndView downloadSummaryExcelISI02(@Valid @ModelAttribute("summaryExcelFormISI02") mPaperExcellValidation summaryValidExcell, BindingResult result, Map model, HttpSession session) {
			 
		    // create some sample data
			 if(result.hasErrors()) {
		          return new ModelAndView("cp.generateSummary");
		     }else
		     {
		    	/**
		    	 * Get list of all Projects (Topics)
		    	 */
		    	 String yearGenerate = summaryValidExcell.getReportingAcademicDate();
		 		String userName = session.getAttribute("currentUserName").toString();
		 		String userCode = session.getAttribute("currentUserCode").toString();
		 		String userRole = session.getAttribute("currentUserRole").toString();
		 		
		 		System.out.println("ISIPapersExcelController::generateISIPaper, userName = " + userName + ".....");
		 		
		 		model.put("year", yearGenerate);
		 		
		 		//List<mPapers> listPapers = paperService.loadPaperListByYear("SUPER_ADMIN", userCode, yearGenerate);
		 		List<mPapers> listPapers = paperService.loadPaperSummaryListByYear(yearGenerate);
		 		model.put("listPapers", listPapers);
		 		
		 		return new ModelAndView("excelSummaryViewISI02","",null);
		     }
		}
		/**
		 * generate A Summary book
		 * 
		 * @param model
		 * @param session
		 * @return
		 */
		@RequestMapping(value="/summary-kv02-book",method=RequestMethod.GET)
		public String generateASummaryBookKV02(ModelMap model,HttpSession session){
		/**
			    * Get List Academic Year 
			    */
			List<mAcademicYear> bookReportingAcademicDateList = academicYearService.list();
			  /**
			    * Put back to a suitable view
			    */
			 model.put("reportingAcademicDate", bookReportingAcademicDateList);
			 model.put("summaryFormExcel02KVBook", new mBookExcellValidation());
			return "cp.generateSummary02KVBook";
		}
		
		@RequestMapping(value = "/summaryExcel02KVBook", method = RequestMethod.POST)
		public ModelAndView downloadSummary02KVBook(@Valid @ModelAttribute("summaryFormExcel02KVBook") mBookExcellValidation summaryFormExcel02KVBook, BindingResult result, Map model, HttpSession session) {
			 
		    // create some sample data
			 if(result.hasErrors()) {
				 List<mAcademicYear> bookReportingAcademicDateList = academicYearService.list();
				 model.put("reportingAcademicDate", bookReportingAcademicDateList);
				 model.put("mBookExcellValidation", new mBookExcellValidation());
				 return new ModelAndView("cp.generateSummary02KVBook");
		     }else
		     {
		    	/**
		    	 * Get list of all Projects (Topics)
		    	 */
		    	 String yearGenerate = summaryFormExcel02KVBook.getReportingAcademicDate();
		 		String userName = session.getAttribute("currentUserName").toString();
		 		String userCode = session.getAttribute("currentUserCode").toString();
		 		String userRole = session.getAttribute("currentUserRole").toString();
		 		
		 		System.out.println("ISIPapersExcelController::generateISIPaper, userName = " + userName + ".....");
		 		
		 		model.put("reportingAcademicYear", yearGenerate);
		 		
		 		List<mBooks> listBooks = bookService.loadBookListByYear("SUPER_ADMIN", userCode, yearGenerate);
		 		model.put("listBooks", listBooks);
		 		
		 		return new ModelAndView("excelSummaryView02KVBook","",null);
		     }
		}
}
