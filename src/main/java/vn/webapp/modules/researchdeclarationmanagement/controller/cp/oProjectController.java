package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.itextpdf.text.DocumentException;

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.libraries.FileUtil;
import vn.webapp.libraries.Money2StringConvertor;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchdeclarationmanagement.validation.mTopicExcellValidation;
import vn.webapp.modules.researchdeclarationmanagement.validation.mTopicValidation;
import vn.webapp.modules.researchmanagement.controller.cp.PDFGenerator;
import vn.webapp.modules.researchmanagement.controller.cp.nProjectController;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.service.ProjectParticipationRolesService;
import vn.webapp.modules.researchmanagement.service.ProjectTasksService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.researchmanagement.validation.ProjectsValidation;
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
    
    @Autowired
	private nProjectService threadService;
    
	@Autowired
	private ProjectTasksService projectTasksService;
	
	@Autowired
    private mPaperService paperService;
    
    static final String status = "active";
    
    public static final String _sHTMLTemplate2 = "html/profile_template.html";
	public static final String _sHTMLCompletedContent2 = "html/completed.html";
	public static final String _sOutPutFile2 = "results/science-profile.pdf";
    
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
   
   /**
	 * Generating PDF
	 * 
	 * @param model
	 * @param threadId
	 * @param session
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	@RequestMapping("/profile-science")
	public String generatePDFProfileProject(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws IOException, DocumentException {
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();

		// Projects project =
		// threadService.loadAProjectByIdAndUserCode(userRole,userCode,
		// projectId);
		//Projects project = threadService.loadProjectsById(80);
		mStaff staff = staffService.loadStaffByUserCode(userCode);
		List<mPapers> papersList = paperService.loadPaperListByStaff(userRole, userCode);
		List<mPatents> patentsList = patentService.loadPatentListByStaff(userRole, userCode);
		
		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();
		String sProjectPDFFileName = staff.getStaff_Code() + "_" + staff.getStaff_ID() + ".pdf";
		List<Projects> projects = threadService.loadProjectsListByStaff(userRole,userCode);
		model.put("projects", status);
		
		// Put journal list and topic category to view

		model.put("projectFormEdit", new ProjectsValidation());
		model.put("projectId", 1);

		this.prepareContent(projects, staff, papersList, patentsList);
		PDFGenerator.v_fGenerator(temperotyFilePath + "\\"+ sProjectPDFFileName);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath+ "\\" + sProjectPDFFileName);
			// response.setContentType("application/pdf");
			// response.setHeader("Content-Disposition",
			// "attachment:filename=report.pdf");
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cp.editAProject";
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(
			String fileName) {

		FileInputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
	
	/**
	 * 
	 * @param project
	 * @throws IOException
	 */
	private void prepareContent(List<Projects> projects, mStaff staff, List<mPapers> papersList, List<mPatents> patentsList) throws IOException {
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			// Getting content from template file
			File o_FontFile = new File(classLoader.getResource(oProjectController._sHTMLTemplate2).getFile());
			String sFilePath = o_FontFile.getAbsolutePath();
			StringBuilder sTemplateContent = FileUtil.sGetFileContent(sFilePath);

			int iCounter = 0;
			String staffName = "";
			String staffGender = "";
			String staffBOD = "";
			String staffDegree = "";
			String staffEmail = "";
			String staffPhoneNo = "";
			String staffPapers = ""; // Bai bao
			String staffBooks = ""; // Giao trinh
			String staffPatents = ""; // Bang sang che
			String staffProjects = ""; // De tai
			if(staff != null){
				staffName = staff.getStaff_Name();
				staffGender = "male".equalsIgnoreCase(staff.getStaff_Gender()) ? "Nam" : "Nữ";
				staffBOD = staff.getStaff_DateOfBirth();
				staffDegree = staff.getAcademicRank().getAcademicRank_VNAbbr();
				staffEmail = staff.getStaff_Email();
				staffPhoneNo = staff.getStaff_Phone();
			}
			
			/* Personal info */
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___USER_NAME___", staffName);
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___GENDER___", staffGender);
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___BIRTH_OF_DATE___", staffBOD);
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___DEGREE___", staffDegree);
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___EMAIL___", staffEmail);
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___PHONE___", staffPhoneNo);

			/* Papers info */
			if(papersList != null && papersList.size() > 0)
			{
				iCounter = 0;
				for (mPapers mPapers : papersList) {
					iCounter++;
					staffPapers += "<tr><td class='col-1'><p class='content'>"+iCounter+"</p></td>";
					staffPapers += "<td class='col-2'><p class='content'><br />"+mPapers.getPDECL_PublicationName()+"</p></td>";
					staffPapers += "<td class='col-3'><p class='content'><br />"+mPapers.getPaperCategory().getPCAT_Name()+"</p></td>";
					staffPapers += "<td class='col-4'><p class='content'><br />"+mPapers.getPDECL_Year()+"</p></td></tr>";
				}
			}
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___STAFF_PAPERS___", staffPapers);
			
			/* Books info */
			//TODO
			staffBooks += "<tr><td class='col-1'><p class='content'>1</p></td>";
			staffBooks += "<td class='col-2'><p class='content'><br />Tìm kiếm cục bộ dựa trên ràng buộc</p></td>";
			staffBooks += "<td class='col-3'><p class='content'><br />Trường ĐHBKHN</p></td>";
			staffBooks += "<td class='col-4'><p class='content'><br />2017</p></td>";
			staffBooks += "<td class='col-5'><p class='content'><br />Chủ biên</p></td></tr>";
			
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___STAFF_BOOKS___", staffBooks);
			
			/* Patents info */
			if(patentsList != null && patentsList.size() > 0)
			{
				iCounter = 0;
				for (mPatents patent : patentsList) {
					iCounter++;
					staffPatents += "<tr><td class='col-1'><p class='content'>"+iCounter+"</p></td>";
					staffPatents += "<td class='col-2'><p class='content'><br />"+patent.getPAT_Name()+"</p></td>";
					staffPatents += "<td class='col-3'><p class='content'><br />"+patent.getPAT_Type()+"</p></td>";
					staffPatents += "<td class='col-4'><p class='content'><br />"+patent.getPAT_DateOfIssue()+"</p></td>";
					staffPatents += "<td class='col-5'><p class='content'><br />Chủ nhiệm</p></td></tr>";
				}
			}
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___STAFF_PATENTS___", staffPatents);
			
			/* Projects info */
			if(projects != null && projects.size() > 0){
				iCounter = 0;
				for (Projects project : projects) {
					iCounter++;
					staffProjects += "<tr><td class='col-1'><p class='content'>"+iCounter+"</p></td>";
					staffProjects += "<td class='col-2'><p class='content'><br />"+project.getPROJ_Name()+"</p></td>";
					staffProjects += "<td class='col-3'><p class='content'><br />"+project.getPROJ_ProjCat_Code()+"</p></td>";
					staffProjects += "<td class='col-4'><p class='content'><br />Chủ nhiệm</p></td>";
					staffProjects += "<td class='col-5'><p class='content'><br />"+project.getPROJ_StartDate()+" - "+project.getPROJ_EndDate()+"</p></td></tr>";
				}
			}
			sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,"___STAFF_PROJECTS___", staffProjects);
			
			// Write completed content into file
			File o_CompletedContentFile = new File(classLoader.getResource(oProjectController._sHTMLCompletedContent2).getFile());

			FileUtil.v_fWriteContentIntoAFile(o_CompletedContentFile,sTemplateContent);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
}
