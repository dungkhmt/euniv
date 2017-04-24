package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.border.Border;
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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.libraries.FileUtil;
import vn.webapp.libraries.Money2StringConvertor;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mBookService;
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
	private mBookService bookService;
	
	@Autowired
    private mPaperService paperService;
    
    static final String status = "active";
    
    public static final String _sHTMLTemplate2 = "html/profile_template.html";
	public static final String _sHTMLCompletedContent2 = "html/completed.html";
	public static final String _sOutPutFile2 = "results/science-profile.pdf";
	public static final String FONT = "fonts/vi-fonts/times.ttf";
    private static Font titleFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, 15);
    private static Font contentFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, 13);
    
    /**
    * Show list all topics
    * @param model
    * @return
    */
   @RequestMapping(value = "/topics", method = RequestMethod.GET)
   public String topicsList(ModelMap model, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   List<mStaff> staffs = staffService.listStaffs();
	   HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
	   for(mStaff st: staffs)
		   mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
	   
	   List<mTopics> topicsList = tProjectService.loadTopicListByStaff(userRole, userCode);
	   
	   for(mTopics p: topicsList){
		   p.setPROJDECL_User_Code(mStaffCode2Name.get(p.getPROJDECL_User_Code()));
	   }
	   
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
    	   int topicYear 						= 0;//topicValid.getTopicYear();
    	   int topicBudget	 					= topicValid.getBudget();
    	   String topicMemberRole				= topicValid.getTopicMemberRole();
    	   String topicStartDate				= topicValid.getTopicStartDate();
    	   String topicEndDate				    = topicValid.getTopicEndDate();
    	   String topicSponsor				    = topicValid.getTopicSponsor();
    	   String topicApprover				    = "";//topicValid.getTopicApproveUser();
    	   
    	   int i_InsertATopic = tProjectService.saveATopic(userCode, topicPubName, topicCategory, topicConVertedHours, topicAutConHours, 
    			   											topicYear, topicBudget, topicReportingAcademicDate, topicMemberRole, topicSponsor, topicApprover, topicStartDate, topicEndDate);
    	   if(i_InsertATopic > 0){
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
	    	// Get list of all Projects (Topics)
			String yearForGenerating = topicValidExcell.getReportingAcademicDate();
			String currentUserName = session.getAttribute("currentUserName").toString();
			String currentUserCode = session.getAttribute("currentUserCode").toString();
		    String userRole = session.getAttribute("currentUserRole").toString();
		    List<mTopics> topicsList = tProjectService.loadTopicListByYear(userRole, currentUserCode, yearForGenerating);
		    
		    // Get list of all Patents
		    List<mPatents> patentsList = patentService.loadPatentListByYear(userRole, currentUserCode, yearForGenerating);
		    model.put("patentsList", patentsList);
		    
		    // Get staff's information
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
   
   /**
    * 
    * @param model
    * @param topicId
    * @param session
    * @return
    */
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
		   model.put("topicYear", "0");
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
	   	  int topicYear 					= 0;//topicFormEdit.getTopicYear();
	   	  int topicBudget	 				= (topicFormEdit.getBudget() != null) ? topicFormEdit.getBudget() : 0;
    	  int topicId 						= topicFormEdit.getTopicId();
    	  String topicMemberRole			= topicFormEdit.getTopicMemberRole();
   	   	  String topicStartDate				= topicFormEdit.getTopicStartDate();
   	   	  String topicEndDate				= topicFormEdit.getTopicEndDate();
   	      String topicSponsor				= topicFormEdit.getTopicSponsor();
   	      String topicApprover				= "";//topicFormEdit.getTopicApproveUser();
          
    	  tProjectService.editATopic(userRole, userCode, topicId, topicPubName, topicCategory, topicConVertedHours, topicAutConHours, topicYear, topicBudget, 
    			  						topicReportingAcademicDate, topicMemberRole, topicSponsor, topicApprover, topicStartDate, topicEndDate);
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

		mStaff staff = staffService.loadStaffByUserCode(userCode);
		List<mPapers> papersList = paperService.loadPaperListByStaff(userRole, userCode);
		List<mPatents> patentsList = patentService.loadPatentListByStaff(userRole, userCode);
		List<mBooks> booksList = bookService.loadBookListByStaff(userRole, userCode);
		List<mTopics> topicsList = tProjectService.loadTopicListByStaff(userRole, userCode);
		
		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();
		String sProjectPDFFileName = staff.getStaff_Code() + "_" + staff.getStaff_ID() + ".pdf";

		List<Projects> projects = threadService.loadProjectsListByStaff(userRole,userCode);
		
		model.put("projects", status);
		
		// Put journal list and topic category to view
		model.put("projectFormEdit", new ProjectsValidation());
		model.put("projectId", 1);

		this.prepareContent(topicsList, staff, papersList, patentsList, booksList);
		PDFGenerator.v_fGenerator(temperotyFilePath + "\\"+ sProjectPDFFileName);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath+ "\\" + sProjectPDFFileName);
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
	private void prepareContent(List<mTopics> projects, mStaff staff, List<mPapers> papersList, List<mPatents> patentsList, List<mBooks> booksList) throws IOException {
		
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
			if(booksList != null && booksList.size() > 0){
				iCounter = 0;
				for (mBooks books : booksList) {
					iCounter++;
					staffBooks += "<tr><td class='col-1'><p class='content'>"+iCounter+"</p></td>";
					staffBooks += "<td class='col-2'><p class='content'><br />"+books.getBOK_BookName()+"</p></td>";
					staffBooks += "<td class='col-3'><p class='content'><br />"+books.getBOK_Publisher()+"</p></td>";
					staffBooks += "<td class='col-4'><p class='content'><br />"+books.getBOK_PublishedYear()+"</p></td>";
					staffBooks += "<td class='col-5'><p class='content'><br />Chủ biên</p></td></tr>";
				}
			}
			
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
				//String projectPaperCateName = "";
				for (mTopics project : projects) {
					iCounter++;
					//projectPaperCateName = (project.getTopicCategory() != null) ? project.getTopicCategory().getPROJCAT_Name() : "N/A";
					String projectPaperCateName = project.getPROJDECL_Sponsor();
					String roleCode = project.getPROJDECL_RoleCode();
					ProjectParticipationRoles role = projectParticipationRolesService.loadAProjectParticipationRolesByCode(roleCode);
					
					staffProjects += "<tr><td class='col-1'><p class='content'>"+iCounter+"</p></td>";
					staffProjects += "<td class='col-2'><p class='content'><br />"+project.getPROJDECL_Name()+"</p></td>";
					staffProjects += "<td class='col-3'><p class='content'><br />"+projectPaperCateName+"</p></td>";
					staffProjects += "<td class='col-4'><p class='content'><br />" + role.getPROJPARTIROLE_Description() + "</p></td>";
					staffProjects += "<td class='col-5'><p class='content'><br />"+project.getPROJDECL_StartDate()+" - "+project.getPROJDECL_EndDate()+"</p></td></tr>";
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
	@RequestMapping(value = "/profile-science-test.pdf")
	public void generatePDFProfileProjectTest(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws IOException, DocumentException {
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();

		mStaff staff = staffService.loadStaffByUserCode(userCode);
		//System.out.print(staff.getStaff_Code());
		//List<mPapers> papersList = paperService.loadPaperListByStaff(userRole, userCode);
		//List<mPatents> patentsList = patentService.loadPatentListByStaff(userRole, userCode);
		//List<mBooks> booksList = bookService.loadBookListByStaff(userRole, userCode);
		//List<mTopics> topicsList = tProjectService.loadTopicListByStaff(userRole, userCode);
		
		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();
		String sProjectPDFFileName = staff.getStaff_Code() + "_" + staff.getStaff_ID() + ".pdf";

		List<Projects> projects = threadService.loadProjectsListByStaff(userRole,userCode);
		
		model.put("projects", status);
		
		// Put journal list and topic category to view
		model.put("projectFormEdit", new ProjectsValidation());
		model.put("projectId", 1);

		//this.prepareContent(topicsList, staff, papersList, patentsList, booksList);
		String title = "a";
		
		Document document = new Document();
		
		ParagraphBorder border = new ParagraphBorder();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(temperotyFilePath + "\\"+ sProjectPDFFileName));
		//add content
		//writer.setPageEvent(border);
		document.open();
		document.setMargins(36, 72, 108, 180);
		
		 Paragraph preface = new Paragraph();
	        // We add one empty lines
			 addTitlePage(document);
			 //border.setActive(true);
	         addContent(document);

	        document.add(preface);
	        //border.setActive(false);
		
        document.close();
		//PDFGenerator.v_fGenerator(temperotyFilePath + "\\"+ sProjectPDFFileName);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath+ "\\" + sProjectPDFFileName);
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void addContent(Document document) {
		BaseFont baseWingdings;
		try {
			baseWingdings = BaseFont.createFont("fonts/window/wingdings.ttf", BaseFont.IDENTITY_H, false);
			Font font = new Font(baseWingdings, 16f, Font.BOLD);
		    char checked='\u00FE';
		    char unchecked='\u00A8';
		    
		    PdfPCell[] bodyContent = new PdfPCell[19];
		    
		    bodyContent[0] = createPdfPCell(new Paragraph("1. Họ và tên: "+"Lê Văn Đức", contentFont), Element.ALIGN_LEFT, Rectangle.BOX);
		    
		    PdfPCell[] bodyBirth = new PdfPCell[2];
		    bodyBirth[0] = createPdfPCell(new Paragraph("2. Năm sinh: "+"10/02/1996", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
		    bodyBirth[1] = createPdfPCell(new Paragraph("3. Nam/Nữ: "+"Nam", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
		    bodyContent[1] = createPdfPCell(createTable(new float[]{1, 1},  100, new PdfPCell[0] , bodyBirth), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
		    
		    
		    
			Paragraph preface = new Paragraph();
			//Paragraph name = 

//	        Paragraph birth = new Paragraph();
//	        birth.add(new Chunk("2. Năm sinh: "+"1996", contentFont));
//	        birth.setTabSettings(new TabSettings(300f));
//	        birth.add(Chunk.TABBING);
//	        birth.add(new Chunk("3. Nam/Nữ: "+"Nam", contentFont));
	        
	        Paragraph academicRank = new Paragraph();
	        academicRank.add(new Chunk("4. Học hàm: "+"", contentFont));
	        academicRank.setTabSettings(new TabSettings(300f));
	        academicRank.add(Chunk.TABBING);
	        academicRank.add(new Chunk("Năm được phong: "+"", contentFont));
	        academicRank.add(Chunk.NEWLINE);
	        academicRank.add(new Chunk("Học vị: "+"Tiến sĩ", contentFont));
	        academicRank.setTabSettings(new TabSettings(300f));
	        academicRank.add(Chunk.TABBING);
	        academicRank.add(new Chunk("Năm đạt học vị: "+"2016", contentFont));
	        
	        Paragraph recentResearchField = new Paragraph();
	        recentResearchField.setTabSettings(new TabSettings(200f));
	        recentResearchField.add(new Chunk("5. Lĩnh vực nghiên cứu trong 5 năm gần đây: ", contentFont));
	        
	        String[] fields = {"Khoa học tự nhiên", "Khoa học Kỹ thuật và Công nghệ", "Khoa học Y dược", "Khoa học Xã hội", "Khoa học Nhân văn", "Khoa học Nông nghiệp"};
	        Boolean[] value = {true, false, false, false, false, false};
	        PdfPCell[] contentsRecentResearch = new PdfPCell[fields.length+value.length];
	      
	        for(int i = 0; i < fields.length; ++i) {
	        	contentsRecentResearch[2*i] = createPdfPCell(new Paragraph(fields[i], contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        	contentsRecentResearch[2*i+1] = createPdfPCell(new Paragraph(value[i]?String.valueOf(checked):String.valueOf(unchecked),font), Element.ALIGN_LEFT, i==fields.length-1?Rectangle.NO_BORDER:Rectangle.RIGHT);
	        }
	        recentResearchField.add(createTable(new float[] {4,1,4,1,4,1}, 100, new PdfPCell[0], contentsRecentResearch));
	        
	        PdfPCell[] contentsResearch = new PdfPCell[3];
	        contentsResearch[0] = createPdfPCell(new Paragraph("Mã chuyên ngành KH&CN", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        String[] code = {"1", "0", "0", "2", "9"};
	        PdfPCell[] contentsCode = new PdfPCell[code.length];
	        float[] widthCode = new float[code.length];
	        for(int i = 0; i < code.length; ++i) {
	        	contentsCode[i] = createPdfPCell(new Paragraph(code[i], contentFont), Element.ALIGN_CENTER, Rectangle.BOX);
	        	widthCode[i] = 1;
	        }
	        contentsResearch[1] = createPdfPCell(createTable(new float[] {1,1,1,1,1}, 10, new PdfPCell[0], contentsCode), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentsResearch[2] = createPdfPCell(new Paragraph("Tên gọi: VẬT LÝ CÁC CHẤT CÔ ĐẶC", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        recentResearchField.add(createTable(new float[] {2,1,3}, 100, new PdfPCell[0], contentsResearch));
	        
	        Paragraph title = new Paragraph();
	        title.add(new Chunk("6. Chức danh nghiên cứu: "+"", contentFont));
	        title.add(Chunk.NEWLINE);
	        title.add(new Chunk("Chức vụ hiện nay: "+"", contentFont));
	        
	        Paragraph adress = new Paragraph();
	        adress.add(new Chunk("7. Địa chỉ nhà riêng: "+"", contentFont));
	        String[] phoneName = {"Điện thoại NR:", "CQ:", "Mobile:"};
	        PdfPCell[] contentsphoneName = new PdfPCell[phoneName.length*2];
	        
	        for(int i = 0; i < phoneName.length; ++i) {
	        	contentsphoneName[2*i] = createPdfPCell(new Paragraph(phoneName[i], contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        	contentsphoneName[2*i+1] = createPdfPCell(new Paragraph(""), Element.ALIGN_LEFT, i==phoneName.length-1?Rectangle.NO_BORDER:Rectangle.RIGHT);
	        }
	        PdfPCell[] contentEmail = new PdfPCell[1];
	        contentEmail[0] = createPdfPCell(new Paragraph("Email: " + "", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        adress.add(createTable(new float[] {1,1,1,1,1,1}, 100, new PdfPCell[0], contentsphoneName));
	        adress.add(createTable(new float[] {1}, 100, new PdfPCell[0], contentEmail));
	        
	        Paragraph workingAgency = new Paragraph();
	        workingAgency.add(new Chunk("8. Cơ quan công tác:", contentFont));
	        PdfPCell[] contentWorkingAgency = new PdfPCell[8];
	        contentWorkingAgency[0] = createPdfPCell(new Paragraph("Tên cơ quan:", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[1] = createPdfPCell(new Paragraph("", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[2] = createPdfPCell(new Paragraph("Tên người đứng đầu:", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[3] = createPdfPCell(new Paragraph("", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[4] = createPdfPCell(new Paragraph("Địa chỉ cơ quan:", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[5] = createPdfPCell(new Paragraph("", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[6] = createPdfPCell(new Paragraph("Điện thoại:", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        contentWorkingAgency[7] = createPdfPCell(new Paragraph("Fax:", contentFont), Element.ALIGN_LEFT, Rectangle.NO_BORDER);
	        workingAgency.add(createTable(new float[]{1,1}, 100, new PdfPCell[0], contentWorkingAgency));
	        
	        Paragraph education = new Paragraph();
	        education.add(new Chunk("9. Quá trình đào tạo", contentFont));
	        
	        String[] nameHeaderEducation = {"Bậc đào tạo", "Nơi đào tạo (Trường, Viện. Nước)", "Chuyên ngành", "Ngày/Tháng/Năm tốt nghiệp (TS, Thạc sĩ, KS, CN,...)"};
	        PdfPCell[] contentEducationHeader = new PdfPCell[nameHeaderEducation.length];
	        
	        String[] nameBodyEducation = {"Đại học", "", "", "", "Thạc sỹ", "", "", "", "Tiến sỹ", "", "", "", "Thực tập sinh khoa học", "", "", ""};
	        PdfPCell[] contentEducationBody = new PdfPCell[nameBodyEducation.length];
	        
	        for(int i = 0; i < nameHeaderEducation.length; ++i) {
	        	contentEducationHeader[i] = createPdfPCell(new Paragraph(nameHeaderEducation[i], contentFont), Element.ALIGN_CENTER, Rectangle.BOX);
	        }
	        
	        for(int i = 0; i < nameBodyEducation.length; ++i) {
	        	contentEducationBody[i] = createPdfPCell(new Paragraph(nameBodyEducation[i], contentFont), Element.ALIGN_LEFT, Rectangle.BOX);
	        }


	        education.add(createTable(new float[]{3,3,2,2}, 100, contentEducationHeader, contentEducationBody));
	        
	        Paragraph language = new Paragraph();
	        language.add(new Chunk("10. Trình độ ngoại ngữ (mỗi mục đề nghị ghi rõ mức độ: Tốt/Khá/TB)", contentFont));
	        
	        String[] nameHeaderLanguage = {"TT", "Tên ngoại ngữ", "Nghe", "Nói", "Đọc", "Viết"};
	        PdfPCell[] contentLanguageHeader = new PdfPCell[nameHeaderLanguage.length];
	        
	        String[] nameBodyLanguage = {"1", "Tiếng Nga", "Tốt", "Tốt", "Tốt", "Tốt", "2", "Tiếng Anh", "", "", "", ""};
	        PdfPCell[] contentLanguageBody = new PdfPCell[nameBodyLanguage.length];
	        
	        for(int i = 0; i < nameHeaderLanguage.length; ++i) {
	        	contentLanguageHeader[i] = createPdfPCell(new Paragraph(nameHeaderLanguage[i], contentFont), Element.ALIGN_CENTER, Rectangle.BOX);
	        }
	        
	        for(int i = 0; i < nameBodyLanguage.length; ++i) {
	        	contentLanguageBody[i] = createPdfPCell(new Paragraph(nameBodyLanguage[i], contentFont), Element.ALIGN_CENTER, Rectangle.BOX);
	        }


	        language.add(createTable(new float[]{1,4,2,2,2,2}, 100, contentLanguageHeader, contentLanguageBody));
	        
	        preface.add(name);
	        preface.add(birth);
	        preface.add(academicRank);
	        preface.add(recentResearchField);
	        preface.add(adress);
	        preface.add(workingAgency);
	        preface.add(education);
	        preface.add(language);
	        
	        document.add(preface);
		} catch (DocumentException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private PdfPTable createTable(float[] width,float widthPercentage, PdfPCell[] headers, PdfPCell[] contents) {
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(widthPercentage);
		
		for(PdfPCell header: headers) {
			table.addCell(header);
		}
		
		for(PdfPCell content: contents) {
			table.addCell(content);
		}
		
		return table;
	}
	
	private PdfPCell createPdfPCell(Paragraph content, int horizontalAlignment, int border) {
		PdfPCell newPdfPCell = new PdfPCell(content);
		
		newPdfPCell.setHorizontalAlignment(horizontalAlignment);
		newPdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		newPdfPCell.setBorder(border);
		 
        // padding
		newPdfPCell.setPaddingLeft(3f);
		newPdfPCell.setPaddingRight(3f);
		newPdfPCell.setPaddingTop(3f);
		newPdfPCell.setPaddingBottom(3f);
		
		newPdfPCell.setBorderWidth(1);
 
        // height
		newPdfPCell.setMinimumHeight(18f);

		return newPdfPCell;
	}

	private PdfPCell createPdfPCell(PdfPTable content, int horizontalAlignment, int border) {
		PdfPCell newPdfPCell = new PdfPCell(content);
		
		newPdfPCell.setHorizontalAlignment(horizontalAlignment);
		newPdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		newPdfPCell.setBorder(border);
		 
        // padding
		newPdfPCell.setPaddingLeft(5f);
		newPdfPCell.setPaddingRight(5f);
		newPdfPCell.setPaddingTop(5f);
		newPdfPCell.setPaddingBottom(5f);
		
		newPdfPCell.setBorderWidth(1);
 
        // height
		newPdfPCell.setMinimumHeight(18f);

		return newPdfPCell;
	}

	private void addTitlePage(Document document) {
		Paragraph preface = new Paragraph();
		Paragraph name = new Paragraph("LÝ LỊCH KHOA HỌC \n CHUYÊN GIA KHOA HỌC VÀ CÔNG NGHỆ", titleFont);
        name.setAlignment(Element.ALIGN_CENTER);
        preface.add(name);
        try {
			document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
