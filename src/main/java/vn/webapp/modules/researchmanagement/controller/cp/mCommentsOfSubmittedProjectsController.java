

package vn.webapp.modules.researchmanagement.controller.cp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.DateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
//For projects
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.researchmanagement.validation.DetailCommentsSubmittedProjectsValidation;
import vn.webapp.modules.researchmanagement.validation.mCommentsOfSubmittedProjectsValidation;
import vn.webapp.modules.researchmanagement.validation.mJuryOfAnnouncedProjectCallValidation;
import vn.webapp.modules.researchmanagement.validation.mProjectCallsValidation;
import vn.webapp.modules.researchmanagement.validation.mThreadExcellValidation;
import vn.webapp.modules.usermanagement.controller.cp.mUserController;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;
import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
//For comments of submitted projects
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.model.xDetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.xProjects;
import vn.webapp.modules.researchmanagement.service.ProjectParticipationRolesService;
import vn.webapp.modules.researchmanagement.service.ProjectResearchFieldService;
import vn.webapp.modules.researchmanagement.service.ProjectTasksService;
import vn.webapp.modules.researchmanagement.service.mCommentsOfSubmittedProjectsService;
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.service.mProjectCommentsService;
import vn.webapp.modules.researchmanagement.service.mProjectStaffsService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
//For staff juries of submitted projects
import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;
import vn.webapp.modules.researchmanagement.service.mStaffJuryOfSubmittedProjectService;

@Controller("cpmCommentsOfSubmittedProjects")
@RequestMapping(value = { "/cp" })
public class mCommentsOfSubmittedProjectsController extends BaseWeb {
	
	@Autowired
	private mCommentsOfSubmittedProjectsService commentsOfSubmittedProjectsService;
	
	
	@Autowired
	private mStaffJuryOfSubmittedProjectService staffJuryOfSubmittedProjectService;
	
	@Autowired 
	private nProjectService projectService;
	
	@Autowired
	private tProjectService tProjectService;

	@Autowired
	private nProjectService threadService;

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
	private mProjectStatusService projectStatusService;
	
	@Autowired
	private mProjectStaffsService projectStaffsService;
	
	@Autowired
	private mFacultyService facultyService;
	
	@Autowired
	private mProjectCallsService projectCallsService;
	
	@Autowired
	private ProjectParticipationRolesService projectParticipationRolesService;
	
	@Autowired
	private ProjectTasksService projectTasksService;
	
	@Autowired
	private mProjectCommentsService projectCommentsService;
	
	@Autowired
	ProjectResearchFieldService projectResearchFieldService;

	static final String status = "active";
	 
	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value = "/review-submitted-projects", method = RequestMethod.GET)
	public String commentsOfSubmittedProjectsList(ModelMap model, HttpSession session) {
		
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		System.out.println("User " + userCode);
		
		//List of staff juries of submitted projects whose reviewer is the current user
		List<mStaffJuryOfSubmittedProject> staffJuryOfSubmittedProjectList = staffJuryOfSubmittedProjectService.loadListStaffJuryOfSubmittedProjectByStaffCode(userCode);
		
		//List of projects 
		List<Projects> projectList = new ArrayList<Projects>();
		for(int i = 0; i < staffJuryOfSubmittedProjectList.size(); i++){
			projectList.addAll(projectService.loadListProjectsByCode(staffJuryOfSubmittedProjectList.get(i).getSTFJUPRJ_PRJCODE()));
		}
		System.out.println("Size of projectList : " + projectList.size());
		
		// Put data back to view
		model.put("projectList", projectList);
		 
		return "cp.listProjectsToBeReviewed";
	}
	
	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add-comments-of-submitted-projects/{id}", method = RequestMethod.GET)
	public String addAProjectCall(ModelMap model, @PathVariable("id") int projectId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		System.out.println("mCommentOfSubmittedProjectController::addAProjectCall, userCode = " + userCode + ", userRole = " + userRole + ", projectId = " + projectId);
		Projects project = projectService.loadAProjectByIdAndUserCode("ROLE_ADMIN", userCode, projectId);
		
		String sComment ="";
		mCommentsOfSubmittedProjects commentsOfSubmittedProject = commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(userCode, project.getPROJ_Code());
		if(commentsOfSubmittedProject != null){
			sComment = commentsOfSubmittedProject.getCOMPROJ_COMMENT();
		}
		
		System.out.println("sComment : " + sComment);
		
		model.put("project", project);
		model.put("sComment", sComment);
		model.put("commentsOfSubmittedProjectsFormAdd", new mCommentsOfSubmittedProjectsValidation());
		
		return "cp.addCommentsOfSubmittedProjects";
	}
	 
	@RequestMapping(value = "/save-comments-of-submitted-projects", method = RequestMethod.POST)
	public String saveCommentsOfSubmittedProjects( HttpServletRequest request, @Valid @ModelAttribute("commentsOfSubmittedProjectsFormAdd") mCommentsOfSubmittedProjectsValidation commentsOfSubmittedProjectsValid, BindingResult result, ModelMap model, HttpSession session) {
	 	String action = request.getParameter("action");
		System.out.println("ACTION : " + action);
		System.out.println("Length of action : " + action.length());
		if(action == "save"){
			System.out.println("XXXX");
		}
		
		if(action == "submit"){
			System.out.println("YYYY");
		}
	 	
		if (result.hasErrors()) {
			System.out.println("Errors");
			return "cp.addCommentsOfSubmittedProjects";
		} else {
			// Prepare data for inserting DB
			String userCode = session.getAttribute("currentUserCode").toString();
			
			String COMPROJ_PRJCODE = commentsOfSubmittedProjectsValid.getCOMPROJ_PRJCODE();
			String COMPROJ_COMMENT = commentsOfSubmittedProjectsValid.getCOMPROJ_COMMENT();

			//get current date time with Date()
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String COMPROJ_UpdateDate = dateFormat.format(date); 
			
			mCommentsOfSubmittedProjects commentsOfSubmittedProject = commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(userCode, COMPROJ_PRJCODE);
			
			
			if(commentsOfSubmittedProject==null){
				//Insert an item
				if(action.equals("save")){
					//save
					commentsOfSubmittedProjectsService.saveCommentsOfSubmittedProjects(userCode, COMPROJ_PRJCODE, COMPROJ_COMMENT, COMPROJ_UpdateDate, false);
				}else{
					//submit
					commentsOfSubmittedProjectsService.saveCommentsOfSubmittedProjects(userCode, COMPROJ_PRJCODE, COMPROJ_COMMENT, COMPROJ_UpdateDate, true);
				}
			}else{
				
				if(commentsOfSubmittedProject.isCOMPROJ_Lock() == false){
					System.out.println("It is not yet locked");
					
					//Update comments of an item
					commentsOfSubmittedProjectsService.editCommentsOfSubmittedProjects(commentsOfSubmittedProject.getCOMPROJ_ID(), COMPROJ_COMMENT);
					
					if(action.equals("submit")){
						System.out.println("Come here");
						
						mCommentsOfSubmittedProjects temp = commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectsById(commentsOfSubmittedProject.getCOMPROJ_ID());
						commentsOfSubmittedProjectsService.submitCommentsOfSubmittedProjects(temp);
					}
					
				}
			}
			
			//Go back to list of projects to be reviewed			
			//List of staff juries of submitted projects whose reviewer is the current user
			List<mStaffJuryOfSubmittedProject> staffJuryOfSubmittedProjectList = staffJuryOfSubmittedProjectService.loadListStaffJuryOfSubmittedProjectByStaffCode(userCode);
			
			//List of projects 
			List<Projects> projectList = new ArrayList<Projects>();
			for(int i = 0; i < staffJuryOfSubmittedProjectList.size(); i++){
				projectList.addAll(projectService.loadListProjectsByCode(staffJuryOfSubmittedProjectList.get(i).getSTFJUPRJ_PRJCODE()));
			}
			
			// Put data back to view
			model.put("projectList", projectList);
			 
			return "cp.listProjectsToBeReviewed";
		}
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/details-comment-submitted-projects", method = RequestMethod.GET)
	public String detailsCommentsOfSubmittedProjectsList(ModelMap model, HttpSession session) {
		
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		//List of staff juries of submitted projects whose reviewer is the current user
		List<mStaffJuryOfSubmittedProject> staffJuryOfSubmittedProjectList = staffJuryOfSubmittedProjectService.loadListStaffJuryOfSubmittedProjectByStaffCode(userCode);
		
		//List of projects 
		List<Projects> projectList = new ArrayList<Projects>();
		for(int i = 0; i < staffJuryOfSubmittedProjectList.size(); i++){
			projectList.addAll(projectService.loadListProjectsByCode(staffJuryOfSubmittedProjectList.get(i).getSTFJUPRJ_PRJCODE()));
		}
		
		// Put data back to view
		model.put("projectList", projectList);
		 
		return "cp.detailsCommentsSubmittedProjects";
	}
	
	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add-details-comments-submitted-projects/{id}", method = RequestMethod.GET)
	public String addADetailCommentProject(ModelMap model, @PathVariable("id") int projectId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString(); 
		Projects project = projectService.loadAProjectByIdAndUserCode("ROLE_ADMIN", userCode, projectId);
		DetailCommentSubmittedProjects detailCommentSubmittedProjects = new DetailCommentSubmittedProjects();
		if(project != null)
		{
			detailCommentSubmittedProjects = commentsOfSubmittedProjectsService.loadDetailsCommentsOfSubmittedProjectsByProjectCode(userCode, project.getPROJ_Code());
			String sComment ="";
			mCommentsOfSubmittedProjects commentsOfSubmittedProject = commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(userCode, project.getPROJ_Code());
			if(commentsOfSubmittedProject != null){
				sComment = commentsOfSubmittedProject.getCOMPROJ_COMMENT();
			}
		}
		model.put("detailCommentSubmittedProjects", detailCommentSubmittedProjects);
		model.put("project", project);
		model.put("projectId", projectId);
		model.put("detailCommentsSubmittedProjectsFormAdd", new DetailCommentsSubmittedProjectsValidation());
		
		return "cp.addADetailCommentProject";
	}
	
	/**
	 * 
	 * @param request
	 * @param detailCommentsSubmittedProjectsFormAdd
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save-detail-comments-submitted-projects", method = RequestMethod.POST)
	public String saveDetailCommentsSubmittedProjects( HttpServletRequest request, @Valid @ModelAttribute("detailCommentsSubmittedProjectsFormAdd") DetailCommentsSubmittedProjectsValidation detailCommentsSubmittedProjectsFormAdd, 
														BindingResult result, ModelMap model, HttpSession session) {

		int projectId = detailCommentsSubmittedProjectsFormAdd.getProjectId();
		String Eval_Conclusion = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Conclusion();
		model.put("projectId", projectId);
		model.put("conclusion", Eval_Conclusion);
		if (result.hasErrors()) {
			model.put("err", "Lỗi cập nhật. Hãy thử lại với thông tin chính xác.");
			return "cp.addADetailCommentProject";
		} else {
			// Prepare data for inserting DB
			String userCode = session.getAttribute("currentUserCode").toString();
			
			int Eval_Motivation = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Motivation();
			int Eval_Innovation = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Innovation();
			int Eval_Applicability = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Applicability();
			int Eval_RearchMethodology = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_RearchMethodology();
			int Eval_ResearchContent = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_ResearchContent();
			int Eval_Paper = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Paper();
			int Eval_Product = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Product();
			int Eval_Patent = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Patent();
			int Eval_Graduate_Student = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Graduate_Student();
			int Eval_Young_Rearcher = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Young_Rearcher();
			int Eval_Education_Graduate = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Education_Graduate();
			int Eval_Reasonable_Budget = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Reasonable_Budget();
			String Eval_Classification = detailCommentsSubmittedProjectsFormAdd.getCMTSUBPRJ_Eval_Classification();
			
			Projects project = projectService.loadProjectsById(projectId);
			if(project != null)
			//if(project.getPROJ_Code() != null)
			{
				commentsOfSubmittedProjectsService.saveDetailsCommentsOfSubmittedProjects(userCode, project.getPROJ_Code(), project.getPROJ_PRJCall_Code(), Eval_Motivation, Eval_Innovation, Eval_Applicability, 
						Eval_RearchMethodology, Eval_ResearchContent, Eval_Paper, Eval_Product, Eval_Patent, Eval_Graduate_Student, Eval_Young_Rearcher, Eval_Education_Graduate, Eval_Reasonable_Budget, Eval_Classification, Eval_Conclusion, projectId);
				
				model.put("status", "Cập nhật thông tin thành công.");
			}else{
				model.put("err", "Lỗi cập nhật. Hãy thử lại với thông tin chính xác.");
			}
			return "redirect:" + this.baseUrl + "/cp/details-comment-submitted-projects.html";
		}
	}
	
	
	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/list-submitted-projects-statisitcs-comment-params", method = RequestMethod.GET)
	public String getListProjectsStatisticsParams(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		String facultyCode = session.getAttribute("facultyCode").toString();

		System.out
				.println(name()
						+ "::getListProjectsStatisticsParams, userCode = "
						+ userCode + ", userRole = " + userRole
						+ ", facultyCode = " + facultyCode);
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		//List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mFaculty> threadFaculties = new ArrayList<mFaculty>();
		if (userRole.equals("ROLE_ADMIN") || userRole.equals("SUPER_ADMIN"))
			threadFaculties = facultyService.loadFacultyList();
		else if (userRole
				.equals(mUserController.ROLE_ADMIN_RESEARCH_MANAGEMENT_FACULTY)) {
			// threadFaculties = new ArrayList<mFaculty>();
			mFaculty faculty = facultyService.loadAFacultyByCode(facultyCode);
			if (faculty != null)
				threadFaculties.add(faculty);
			else {
				System.out.println(name()
						+ "::getListProjectsStatisticsParams, faculty "
						+ facultyCode + " NOT EXIST!!!");
			}
		}
		List<mDepartment> threadDepartments = departmentService.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();
		List<mProjectCalls> projectCallsList = projectCallsService.loadProjectCallsList();
				
		model.put("threadExcellForm", new mThreadExcellValidation());
		model.put("threadsList", threadsList);
		model.put("threadCategory", threadCategory);
		model.put("threadStatuses", threadStatuses);
		model.put("projectCallsList", projectCallsList);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);

		return "cp.projectsListCommentsStatisiticsParams";
	}
	public String name(){
		return "mCommentsOfSubmittedProjectController";
	}
	@RequestMapping(value = "/submitted-projects-result-summary", method = RequestMethod.POST)
	public String CommentsOfSubmittedProjectsResultSummaryList(ModelMap model, 
			HttpSession session,
			HttpServletRequest request, 
			@Valid @ModelAttribute("threadExcellForm") mThreadExcellValidation threadExcellForm
			) {
		
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		String projectCallCode = (threadExcellForm.getThreadYear() != null) ? threadExcellForm.getThreadYear() : "";
		String statusCode = (threadExcellForm.getThreadStatus()  != null) ? threadExcellForm.getThreadStatus() : "";
		String facultyCode = (threadExcellForm.getThreadFaculty() != null) ? threadExcellForm.getThreadFaculty() : "";
		String departmentCode = (threadExcellForm.getThreadDepartment() != null) ? threadExcellForm.getThreadDepartment() : "";
		String staffCode = (threadExcellForm.getThreadStaff() != null) ? threadExcellForm.getThreadStaff() : "";
		
		System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList projectCallCode : " + projectCallCode);
		System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList statusCode : " + statusCode);
		System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList facultyCode : " + facultyCode);
		System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList departmentCode : " + departmentCode);
		System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList staffCode : " + staffCode);

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for(mStaff st: staffs){
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}
		List<mProjectCalls> prjCalls = projectCallsService.loadProjectCallsList();
		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		for(mProjectCalls pc: prjCalls){
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(), pc.getPROJCALL_NAME());
		}
		
		List<mProjectStatus> status = projectStatusService.list();
		HashMap<String, String> mStatusCode2Name = new HashMap<String, String>();
		for(mProjectStatus ps: status){
			mStatusCode2Name.put(ps.getPROJSTAT_Code(), ps.getPROJSTAT_Description());
		}
		
		List<mFaculty> faculties = facultyService.loadFacultyList();
		
		HashSet<String> setProjectCallCode = new HashSet<String>();
		HashSet<String> setStaffCode = new HashSet<String>();
		HashSet<String> setStatusCode = new HashSet<String>();
		HashSet<String> setFacultyCode = new HashSet<String>();
		
		if(staffCode == "" || staffCode.equals("")){
			for(mStaff st: staffs){
				setStaffCode.add(st.getStaff_Code());
			}
		}else{
			setStaffCode.add(staffCode);
		}
		
		if(projectCallCode == "" || projectCallCode.equals("")){
			for(mProjectCalls pc: prjCalls){
				setProjectCallCode.add(pc.getPROJCALL_CODE());
			}
		}else{
			setProjectCallCode.add(projectCallCode);
		}
		
		if(statusCode == "" || statusCode.equals("")){
			for(mProjectStatus ps: status){
				setStatusCode.add(ps.getPROJSTAT_Code());
			}
		}else{
			setStatusCode.add(statusCode);
		}
		
		if (userRole.equals(mUserController.ROLE_ADMIN)
				|| userRole.equals(mUserController.SUPER_ADMIN)) {
			if (facultyCode == "" || facultyCode.equals("")) {
				for (mFaculty f : faculties) {
					setFacultyCode.add(f.getFaculty_Code());
				}
			} else {
				setFacultyCode.add(facultyCode);
			}
		} else {
			String userFacultyCode = session.getAttribute("facultyCode").toString();
			setFacultyCode.add(userFacultyCode);
		}
		
		
		//List of staff juries of submitted projects whose reviewer is the current user
		List<mStaffJuryOfSubmittedProject> staffJuryOfSubmittedProjectList = staffJuryOfSubmittedProjectService.loadListStaffJuryOfSubmittedProjectByStaffCode(userCode);
		
		//List<xProjects> xProjects = projectService.loadListSubmittedProjectsForSummary();
		List<xProjects> allxProjects = projectService.loadListSubmittedProjectsForSummary();
		List<xProjects> xProjects = new ArrayList<xProjects>();
		if(allxProjects == null){
			System.out.println(name() + "::CommentsOfSubmittedProjectsResultSummaryList, allxProject is null");
			
		}else
		for(xProjects t: allxProjects){
			if(setProjectCallCode.contains(t.getPROJ_PRJCall_Code())
					&& setStatusCode.contains(t.getPROJ_Status_Code())
					&& setFacultyCode.contains(t.getPROJ_FacultyCode())
					&& setStaffCode.contains(t.getPROJ_User_Code())
					)
				xProjects.add(t);
		}
		
		HashSet<List<String>> listProjectSummary = new HashSet<List<String>>();
		try{
		if(xProjects != null)
		{
			int i = 0;
			for (xProjects oPrj : xProjects) {
				List<String> listTemp = new ArrayList<String>();
				int iAverageTotalPoints = 1;
				Set<xDetailCommentSubmittedProjects> detailCommentsSubmittedProjectList = oPrj.getDetailCommentSubmittedProjects();
				if(detailCommentsSubmittedProjectList != null){
					int iTotalPoints = 0;
					int iIterator = 0;
					for (xDetailCommentSubmittedProjects xDetailCommentSubmittedProjects : detailCommentsSubmittedProjectList) {
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Applicability();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Education_Graduate();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Graduate_Student();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Innovation();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Motivation();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Paper();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Patent();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Product();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_RearchMethodology();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Reasonable_Budget();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_ResearchContent();
						iTotalPoints += xDetailCommentSubmittedProjects.getCMTSUBPRJ_Eval_Young_Rearcher();
						iIterator++;
					}
					iAverageTotalPoints = (iIterator > 0) ? iTotalPoints/iIterator : 0;
				}
				listTemp.add(oPrj.getPROJ_Name()); // Project Name
				listTemp.add(oPrj.getStaff().getStaff_Name()); // Staff Name
				listTemp.add(Integer.toString(iAverageTotalPoints)); // Averange Name
				String sCommentTemp = "";
				if(oPrj.getCommentsOfSubmittedProjects() != null)
				{
					sCommentTemp = Jsoup.parse(oPrj.getCommentsOfSubmittedProjects().getCOMPROJ_COMMENT()).text(); 
				}
				listTemp.add(sCommentTemp); // Comment
				listTemp.add(oPrj.getPROJ_ID() + "");
				// Add element to the list
				listProjectSummary.add(listTemp);
				i++;
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println("XXX : " + e.getMessage());
		}
		
		//List of projects 
		List<Projects> projectList = new ArrayList<Projects>();
		for(int i = 0; i < staffJuryOfSubmittedProjectList.size(); i++){
			projectList.addAll(projectService.loadListProjectsByCode(staffJuryOfSubmittedProjectList.get(i).getSTFJUPRJ_PRJCODE()));
		}
		
		// Put data back to view
		model.put("projectList", listProjectSummary);
		return "cp.commentsSubmittedProjectsResultSummary";
	}
 
}
