/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : February 04th, 2016
 */
package vn.webapp.modules.researchmanagement.controller.cp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.libraries.FileUtil;
import vn.webapp.libraries.Money2StringConvertor;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;
import vn.webapp.modules.researchmanagement.model.ProjectResearchField;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.ProjectsProjectResearchField;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectComments;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.service.ProjectParticipationRolesService;
import vn.webapp.modules.researchmanagement.service.ProjectResearchFieldService;
import vn.webapp.modules.researchmanagement.service.ProjectTasksService;
import vn.webapp.modules.researchmanagement.service.mCommentsOfSubmittedProjectsService;
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.service.mProjectCommentsService;
import vn.webapp.modules.researchmanagement.service.mProjectStaffsService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.researchmanagement.validation.ProjectsValidation;
import vn.webapp.modules.researchmanagement.validation.mProjectCallsValidation;
import vn.webapp.modules.researchmanagement.validation.mProjectExcellStatisticsValidation;
import vn.webapp.modules.researchmanagement.validation.mThreadApproveValidation;
import vn.webapp.modules.researchmanagement.validation.mThreadExcellValidation;
import vn.webapp.modules.researchmanagement.validation.mThreadValidation;
import vn.webapp.modules.usermanagement.controller.cp.mUserController;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpnProject")
@RequestMapping(value = { "/cp" })
public class nProjectController extends BaseWeb {
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
	mCommentsOfSubmittedProjectsService commentsOfSubmittedProjectsService;

	@Autowired
	ProjectResearchFieldService projectResearchFieldService;

	static final String status = "active";

	public static final String _sHTMLTemplate = "html/template.html";
	public static final String _sHTMLCompletedContent = "html/completed.html";
	public static final String _sOutPutFile = "results/content.pdf";

	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Show list all threads
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/download-proposal/{id}", method = RequestMethod.GET)
	public void downloadProposal(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		model.put("papers", status);
		// mPapers paper = paperService.loadAPaperByIdAndUserCode(userRole,
		// userCode, paperId);
		Projects project = threadService.loadProjectsById(projectId);
		// if(paper.getPDECL_SourceFile() != null){

		String userCodeOfProject = project.getPROJ_User_Code();
		System.out.println(name() + "::downloadProposal, userCodeOfProject = "
				+ userCodeOfProject + ", userCode = " + userCode
				+ ", userRole = " + userRole + ", projectId = " + projectId);
		if (project.getPROJ_SourceFile() != null) {
			ServletContext context = request.getServletContext();

			// String fullfilename =
			// establishFullFileNameForUpload(project.getPROJ_SourceFile(),
			// userCode, request);
			String fullfilename = establishFullFileNameForDownload(
					project.getPROJ_SourceFile(), userCodeOfProject, request);
			// File downloadFile = new File(paper.getPDECL_SourceFile());
			File downloadFile = new File(fullfilename);

			System.out.println(name() + "::downloadProposal, userCode = "
					+ userCode + ", userRole = " + userRole
					+ ", userCodeOfProject = " + userCodeOfProject
					+ ", projectId = " + projectId + ", path_filename = "
					+ fullfilename);
			if (downloadFile.exists()
					&& !project.getPROJ_SourceFile().equals("")) {
				FileInputStream inputStream = new FileInputStream(downloadFile);

				// String mimeType =
				// context.getMimeType(paper.getPDECL_SourceFile());
				String mimeType = context.getMimeType(project
						.getPROJ_SourceFile());
				if (mimeType == null) {
					// set to binary type if MIME mapping not found
					mimeType = "application/octet-stream";
				}

				// set content attributes for the response
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				// set headers for the response
				String headerKey = "Content-Disposition";
				String headerValue = String.format(
						"attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);

				// get output stream of the response
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;

				// write bytes read from the input stream into the output stream
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outStream.close();
			} else {
				System.out.println(name() + "::downloadProposal, userCode = "
						+ userCode + ", userRole = " + userRole
						+ ", projectId = " + projectId + ", file "
						+ fullfilename + " does not exist!!!!!!!!");
			}
		}
	}

	@RequestMapping(value = "/list-projects", method = RequestMethod.GET)
	public String getListProjects(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<Projects> projectsList = threadService.loadProjectsListByStaff(
				userRole, userCode);
		// List<Projects> projectsList =
		// threadService.loadSubmittedProjectsListByStaff(userRole, userCode);
		System.out.println(name() + "::getListProjects, userCode = " + userCode
				+ ", userRole = " + userRole);

		List<mProjectCalls> projectCalls = projectCallsService
				.loadProjectCallsList();
		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		for (mProjectCalls pc : projectCalls) {
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(),
					pc.getPROJCALL_NAME());
		}
		for (Projects p : projectsList) {
			p.setPROJ_PRJCall_Code(mProjectCallCode2Name.get(p
					.getPROJ_PRJCall_Code()));
		}

		model.put("projectsList", projectsList);
		model.put("projects", status);
		return "cp.projectsList";
	}

	@RequestMapping(value = "/generate-project-code-params.html", method = RequestMethod.GET)
	public String generateProjectCodeParams(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		model.put("threadExcellForm", new mThreadExcellValidation());
		model.put("threadsList", threadsList);
		model.put("threadCategory", threadCategory);
		model.put("threadStatuses", threadStatuses);
		model.put("projectCallsList", projectCallsList);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);

		return "cp.generateProjectCodeParams";
	}

	@RequestMapping(value = "/list-projects-statisitcs-params", method = RequestMethod.GET)
	public String getListProjectsStatisticsParams(ModelMap model,
			HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		String facultyCode = session.getAttribute("facultyCode").toString();

		System.out
				.println(name()
						+ "::getListProjectsStatisticsParams, userCode = "
						+ userCode + ", userRole = " + userRole
						+ ", facultyCode = " + facultyCode);
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();

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
		for (mFaculty f : threadFaculties) {
			System.out.println(name()
					+ "::getListProjectsStatisticsParams, get faculty = "
					+ f.getFaculty_Code() + ", " + f.getFaculty_Name());
		}
		System.out.println(name()
				+ "::getListProjectsStatisticsParams, faculties.sz = "
				+ threadFaculties.size());

		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		model.put("threadExcellForm", new mThreadExcellValidation());
		model.put("threadsList", threadsList);
		model.put("threadCategory", threadCategory);
		model.put("threadStatuses", threadStatuses);
		model.put("projectCallsList", projectCallsList);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);

		return "cp.projectsListStatisiticsParams";
	}

	@RequestMapping(value = "/generate-project-code", method = RequestMethod.POST)
	public String generateProjectCodes(
			ModelMap model,
			HttpSession session,
			HttpServletRequest request,
			@Valid @ModelAttribute("threadExcellForm") mThreadExcellValidation threadExcellForm) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		String projectCallCode = (threadExcellForm.getThreadYear() != null) ? threadExcellForm
				.getThreadYear() : "";
		String statusCode = (threadExcellForm.getThreadStatus() != null) ? threadExcellForm
				.getThreadStatus() : "";
		String facultyCode = (threadExcellForm.getThreadFaculty() != null) ? threadExcellForm
				.getThreadFaculty() : "";
		String departmentCode = (threadExcellForm.getThreadDepartment() != null) ? threadExcellForm
				.getThreadDepartment() : "";
		String staffCode = (threadExcellForm.getThreadStaff() != null) ? threadExcellForm
				.getThreadStaff() : "";

		System.out.println(name() + "::generateProjectCodes projectCallCode : "
				+ projectCallCode);
		System.out.println(name() + "::generateProjectCodes statusCode : "
				+ statusCode);
		System.out.println(name() + "::generateProjectCodes facultyCode : "
				+ facultyCode);
		System.out.println(name() + "::generateProjectCodes departmentCode : "
				+ departmentCode);
		System.out.println(name() + "::generateProjectCodes staffCode : "
				+ staffCode);

		threadService.generateProjectCodes(projectCallCode);

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for (mStaff st : staffs) {
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}
		List<mProjectCalls> prjCalls = projectCallsService
				.loadProjectCallsList();
		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		for (mProjectCalls pc : prjCalls) {
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(),
					pc.getPROJCALL_NAME());
		}

		List<mProjectStatus> status = projectStatusService.list();
		HashMap<String, String> mStatusCode2Name = new HashMap<String, String>();
		for (mProjectStatus ps : status) {
			mStatusCode2Name.put(ps.getPROJSTAT_Code(),
					ps.getPROJSTAT_Description());
		}

		List<mFaculty> faculties = facultyService.loadFacultyList();

		HashSet<String> setProjectCallCode = new HashSet<String>();
		HashSet<String> setStaffCode = new HashSet<String>();
		HashSet<String> setStatusCode = new HashSet<String>();
		HashSet<String> setFacultyCode = new HashSet<String>();

		if (staffCode == "" || staffCode.equals("")) {
			for (mStaff st : staffs) {
				setStaffCode.add(st.getStaff_Code());
			}
		} else {
			setStaffCode.add(staffCode);
		}

		if (projectCallCode == "" || projectCallCode.equals("")) {
			for (mProjectCalls pc : prjCalls) {
				setProjectCallCode.add(pc.getPROJCALL_CODE());
			}
		} else {
			setProjectCallCode.add(projectCallCode);
		}

		if (statusCode == "" || statusCode.equals("")) {
			for (mProjectStatus ps : status) {
				setStatusCode.add(ps.getPROJSTAT_Code());
			}
		} else {
			setStatusCode.add(statusCode);
		}

		if (facultyCode == "" || facultyCode.equals("")) {
			for (mFaculty f : faculties) {
				setFacultyCode.add(f.getFaculty_Code());
			}
		} else {
			setFacultyCode.add(facultyCode);
		}

		List<mThreads> allProjectsList = threadService.listAll();// threadService.loadProjectsListByStaff(userRole,
																	// userCode);

		List<mThreads> projectsList = new ArrayList<mThreads>();
		for (mThreads t : allProjectsList) {
			if (setProjectCallCode.contains(t.getPROJ_PRJCall_Code())
					&& setStatusCode.contains(t.getPROJ_Status_Code())
					&& setFacultyCode.contains(t.getPROJ_FacultyCode())
					&& setStaffCode.contains(t.getPROJ_User_Code()))
				projectsList.add(t);

		}

		for (mThreads t : projectsList) {
			t.setPROJ_PRJCall_Code(mProjectCallCode2Name.get(t
					.getPROJ_PRJCall_Code()));
			t.setPROJ_User_Code(mStaffCode2Name.get(t.getPROJ_User_Code()));
			t.setPROJ_Status_Code(mStatusCode2Name.get(t.getPROJ_Status_Code()));
		}
		System.out.println(name() + "::getListProjectsStatistics, userCode = "
				+ userCode + ", userRole = " + userRole);

		model.put("projectsList", projectsList);
		model.put("projectCallsList", prjCalls);
		model.put("projects", status);
		return "cp.projectsListStatisitics";
	}

	@RequestMapping(value = "/list-projects-statisitcs", method = RequestMethod.POST)
	public String getListProjectsStatistics(
			ModelMap model,
			HttpSession session,
			HttpServletRequest request,
			@Valid @ModelAttribute("threadExcellForm") mThreadExcellValidation threadExcellForm) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		String projectCallCode = (threadExcellForm.getThreadYear() != null) ? threadExcellForm
				.getThreadYear() : "";
		String statusCode = (threadExcellForm.getThreadStatus() != null) ? threadExcellForm
				.getThreadStatus() : "";
		String facultyCode = (threadExcellForm.getThreadFaculty() != null) ? threadExcellForm
				.getThreadFaculty() : "";
		String departmentCode = (threadExcellForm.getThreadDepartment() != null) ? threadExcellForm
				.getThreadDepartment() : "";
		String staffCode = (threadExcellForm.getThreadStaff() != null) ? threadExcellForm
				.getThreadStaff() : "";

		System.out.println(name()
				+ "::getListProjectsStatistics projectCallCode : "
				+ projectCallCode);
		System.out.println(name() + "::getListProjectsStatistics statusCode : "
				+ statusCode);
		System.out.println(name()
				+ "::getListProjectsStatistics facultyCode : " + facultyCode);
		System.out.println(name()
				+ "::getListProjectsStatistics departmentCode : "
				+ departmentCode);
		System.out.println(name() + "::getListProjectsStatistics staffCode : "
				+ staffCode);

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for (mStaff st : staffs) {
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}
		List<mProjectCalls> prjCalls = projectCallsService
				.loadProjectCallsList();
		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		for (mProjectCalls pc : prjCalls) {
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(),
					pc.getPROJCALL_NAME());
		}

		List<mProjectStatus> status = projectStatusService.list();
		HashMap<String, String> mStatusCode2Name = new HashMap<String, String>();
		for (mProjectStatus ps : status) {
			mStatusCode2Name.put(ps.getPROJSTAT_Code(),
					ps.getPROJSTAT_Description());
		}

		List<mFaculty> faculties = facultyService.loadFacultyList();

		HashSet<String> setProjectCallCode = new HashSet<String>();
		HashSet<String> setStaffCode = new HashSet<String>();
		HashSet<String> setStatusCode = new HashSet<String>();
		HashSet<String> setFacultyCode = new HashSet<String>();

		if (staffCode == "" || staffCode.equals("")) {
			for (mStaff st : staffs) {
				setStaffCode.add(st.getStaff_Code());
			}
		} else {
			setStaffCode.add(staffCode);
		}

		if (projectCallCode == "" || projectCallCode.equals("")) {
			for (mProjectCalls pc : prjCalls) {
				setProjectCallCode.add(pc.getPROJCALL_CODE());
			}
		} else {
			setProjectCallCode.add(projectCallCode);
		}

		if (statusCode == "" || statusCode.equals("")) {
			for (mProjectStatus ps : status) {
				setStatusCode.add(ps.getPROJSTAT_Code());
			}
		} else {
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
			String userFacultyCode = session.getAttribute("facultyCode")
					.toString();
			setFacultyCode.add(userFacultyCode);
		}

		List<mThreads> allProjectsList = threadService.listAll();// threadService.loadProjectsListByStaff(userRole,
																	// userCode);

		System.out.print(name()
				+ "::getListProjectsStatistics, setProjectCallCodes = ");
		for (String code : setProjectCallCode)
			System.out.print(code + ", ");
		System.out.println();

		System.out.print(name()
				+ "::getListProjectsStatistics, setStatusCodes = ");
		for (String code : setStatusCode)
			System.out.print(code + ", ");
		System.out.println();

		System.out.print(name()
				+ "::getListProjectsStatistics, setFacultyCodes = ");
		for (String code : setFacultyCode)
			System.out.print(code + ", ");
		System.out.println();

		System.out.print(name()
				+ "::getListProjectsStatistics, setStaffCodes = ");
		for (String code : setStaffCode)
			System.out.print(code + ", ");
		System.out.println();

		List<mThreads> projectsList = new ArrayList<mThreads>();
		for (mThreads t : allProjectsList) {
			System.out.println(name() + "::getListProjectsStatistics consider project for filter " + t.getPROJ_Code());
			if (setProjectCallCode.contains(t.getPROJ_PRJCall_Code())
					&& setStatusCode.contains(t.getPROJ_Status_Code())
					&& setFacultyCode.contains(t.getPROJ_FacultyCode())
					&& setStaffCode.contains(t.getPROJ_User_Code()))
				projectsList.add(t);

		}

		for (mThreads t : projectsList) {
			t.setPROJ_PRJCall_Code(mProjectCallCode2Name.get(t
					.getPROJ_PRJCall_Code()));
			t.setPROJ_User_Code(mStaffCode2Name.get(t.getPROJ_User_Code()));
			t.setPROJ_Status_Code(mStatusCode2Name.get(t.getPROJ_Status_Code()));
		}
		System.out.println(name() + "::getListProjectsStatistics, userCode = "
				+ userCode + ", userRole = " + userRole);

		model.put("projectsList", projectsList);
		model.put("projectCallsList", prjCalls);
		model.put("projects", status);
		return "cp.projectsListStatisitics";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/modify-submitted-projects", method = RequestMethod.GET)
	public String getListSubmittedProjects(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		String facultyCode = session.getAttribute("currentUserFaculty")
				.toString();

		// List<Projects> projectsList =
		// threadService.loadSubmittedProjectsListByStaff(userRole, userCode);
		// List<Projects> projectsList =
		// threadService.loadProjectsListByStaff(userRole, userCode);
		List<Projects> projectsList = listProjectsWithFullInformation(userRole,
				userCode);
		List<List<String>> listProjects = new ArrayList<>();
		if (projectsList != null) {
			for (Projects project : projectsList) {
				List<String> tempPro = new ArrayList<>();
				List<DetailCommentSubmittedProjects> detailsCommentsOfSubmittedProjectsByProjectCode = commentsOfSubmittedProjectsService
						.loadListDetailsCommentsOfSubmittedProjectsByProjectCode(project
								.getPROJ_Code());

				tempPro.add(project.getPROJ_Name());
				tempPro.add(project.getPROJ_PRJCall_Code());
				tempPro.add(Integer.toString(project.getPROJ_ID()));
				tempPro.add(Integer.toString(project.getPROJ_Locked1()));
				tempPro.add(Integer.toString(project.getPROJ_Locked2()));
				if (detailsCommentsOfSubmittedProjectsByProjectCode != null
						&& detailsCommentsOfSubmittedProjectsByProjectCode
								.size() > 0) {
					tempPro.add("SHOWED");
				} else {
					tempPro.add("");
				}

				listProjects.add(tempPro);
			}
		}

		model.put("projectsList", listProjects);
		model.put("projects", status);
		return "cp.submittedProjectsList";
	}

	/**
	 * Show list all projects need to be approved
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approve-projects", method = RequestMethod.GET)
	public String getListApprovingProjects(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<Projects> projectsList = threadService.loadApproveProjectsList(
				userRole, userCode);

		model.put("projectsList", projectsList);
		model.put("projects", status);
		return "cp.projectsapprove";
	}

	/**
	 * Show list all projects need to be approved
	 * 
	 * @param model
	 * @return
	 */

	public List<Projects> listProjectsWithFullInformation(String userRole,
			String userCode) {
		// List<Projects> projectsList =
		// threadService.loadSubmittedProjectsListByStaff(userRole, userCode);
		List<Projects> projectsList = threadService.loadProjectsListByStaff(
				userRole, userCode);
		/*
		 * List<mStaff> staffs = staffService.listStaffs(); HashMap<String,
		 * String> mStaffCode2Name = new HashMap<String, String>(); for (mStaff
		 * st : staffs) { mStaffCode2Name.put(st.getStaff_Code(),
		 * st.getStaff_Name()); }
		 */

		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		List<mProjectCalls> projectCalls = projectCallsService
				.loadProjectCallsList();
		for (mProjectCalls pc : projectCalls) {
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(),
					pc.getPROJCALL_NAME());
		}

		for (Projects p : projectsList) {
			mStaff st = staffService.loadStaffByUserCode(p.getPROJ_User_Code());
			// p.setPROJ_User_Code(mStaffCode2Name.get(p.getPROJ_User_Code()));
			p.setPROJ_User_Code(st.getStaff_Name());
			p.setPROJ_PRJCall_Code(mProjectCallCode2Name.get(p
					.getPROJ_PRJCall_Code()));
		}

		return projectsList;
	}
	public List<mThreads> listProjectsWithFullInformation() {
		// List<Projects> projectsList =
		// threadService.loadSubmittedProjectsListByStaff(userRole, userCode);
		List<mThreads> projectsList = threadService.listAll();
		/*
		 * List<mStaff> staffs = staffService.listStaffs(); HashMap<String,
		 * String> mStaffCode2Name = new HashMap<String, String>(); for (mStaff
		 * st : staffs) { mStaffCode2Name.put(st.getStaff_Code(),
		 * st.getStaff_Name()); }
		 */

		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		List<mProjectCalls> projectCalls = projectCallsService
				.loadProjectCallsList();
		for (mProjectCalls pc : projectCalls) {
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(),
					pc.getPROJCALL_NAME());
		}

		for (mThreads p : projectsList) {
			mStaff st = staffService.loadStaffByUserCode(p.getPROJ_User_Code());
			// p.setPROJ_User_Code(mStaffCode2Name.get(p.getPROJ_User_Code()));
			p.setPROJ_User_Code(st.getStaff_Name());
			p.setPROJ_PRJCall_Code(mProjectCallCode2Name.get(p
					.getPROJ_PRJCall_Code()));
		}

		return projectsList;
	}

	@RequestMapping(value = "/collect-comments", method = RequestMethod.GET)
	public String getProjectsForShowingComments(ModelMap model,
			HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		// List<Projects> projectsList =
		// threadService.loadSubmittedProjectsListByStaff(userRole, userCode);
		//List<Projects> projectsList = listProjectsWithFullInformation(userRole,	userCode);
		List<mThreads> projectsList = listProjectsWithFullInformation();
		
		model.put("projectsList", projectsList);
		model.put("projects", status);
		return "cp.listcomments";
	}

	/**
	 * Show list all threads
	 * 
	 * @param model
	 * @return
	 */
	public String name() {
		return "nProjectController";
	}

	@RequestMapping(value = "/threads", method = RequestMethod.GET)
	public String topicsList(ModelMap model, HttpSession session) {
		double t0 = System.currentTimeMillis();
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		List<mAcademicYear> threadAcademicYears = academicYearService.list();
		List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		model.put("threadExcellForm", new mThreadExcellValidation());
		model.put("threadsList", threadsList);
		model.put("threadCategory", threadCategory);
		model.put("threadStatuses", threadStatuses);
		model.put("projectCallsList", projectCallsList);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);
		double t = System.currentTimeMillis() - t0;
		t = t * 0.001;
		System.out.println(name() + "::topicsList, threadsList.sz = "
				+ threadsList.size() + ", threadFaculties = "
				+ threadFaculties.size());
		System.out.println("time = " + t + " (s)");
		return "cp.threads";
	}

	@RequestMapping(value = "/threads-listadd", method = RequestMethod.GET)
	public String topicsListToAdd(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		System.out.println(name() + "::topicsList, userCode = " + userCode
				+ ", userRole = " + userRole);

		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		List<mAcademicYear> threadAcademicYears = academicYearService.list();
		List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();

		model.put("threadsList", threadsList);
		model.put("threadCategory", threadCategory);
		model.put("threadStatuses", threadStatuses);
		model.put("threadAcademicYears", threadAcademicYears);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);
		return "cp.threads-listadd";
	}

	@RequestMapping(value = "/threads-approve", method = RequestMethod.GET)
	public String topicsListToApprove(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		List<mAcademicYear> threadAcademicYears = academicYearService.list();
		List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();

		List<mThreads> submittedThread = new ArrayList<mThreads>();
		for (mThreads t : threadsList) {
			if ("SUBMIT".equals(t.getPROJ_Status_Code()))
				submittedThread.add(t);
		}
		for (mThreads t : submittedThread) {
			System.out
					.println("ThreadController::topicListToApprove, submitted thread "
							+ t.getPROJ_Name());
		}

		List<mProjectStatus> submittedThreadStatuses = new ArrayList<mProjectStatus>();
		for (mProjectStatus ps : threadStatuses) {
			if ("SUBMIT".equals(ps.getPROJSTAT_Code()))
				submittedThreadStatuses.add(ps);
		}
		// model.put("threadsList", threadsList);
		model.put("threadsList", submittedThread);
		model.put("threadCategory", threadCategory);
		// model.put("threadStatuses", threadStatuses);
		model.put("threadStatuses", submittedThreadStatuses);
		model.put("threadAcademicYears", threadAcademicYears);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);
		return "cp.threads-approve";
	}

	@RequestMapping(value = "/threads-evaluate", method = RequestMethod.GET)
	public String topicsListToEvaluate(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(
				userRole, userCode);
		// Get topic's category
		List<mTopicCategory> threadCategory = tProjectCategoryService.list();
		// Get list project statuses
		List<mProjectStatus> threadStatuses = projectStatusService.list();
		List<mAcademicYear> threadAcademicYears = academicYearService.list();
		List<mFaculty> threadFaculties = facultyService.loadFacultyList();
		List<mDepartment> threadDepartments = departmentService
				.loadDepartmentList();
		List<mStaff> threadStaffs = staffService.listStaffs();

		List<mThreads> submittedThread = new ArrayList<mThreads>();
		for (mThreads t : threadsList) {
			if ("APPROVED".equals(t.getPROJ_Status_Code()))
				submittedThread.add(t);
		}
		for (mThreads t : submittedThread) {
			System.out
					.println("ThreadController::topicListToApprove, submitted thread "
							+ t.getPROJ_Name());
		}

		List<mProjectStatus> submittedThreadStatuses = new ArrayList<mProjectStatus>();
		for (mProjectStatus ps : threadStatuses) {
			if ("APPROVED".equals(ps.getPROJSTAT_Code()))
				submittedThreadStatuses.add(ps);
		}
		// model.put("threadsList", threadsList);
		model.put("threadsList", submittedThread);
		model.put("threadCategory", threadCategory);
		// model.put("threadStatuses", threadStatuses);
		model.put("threadStatuses", submittedThreadStatuses);
		model.put("threadAcademicYears", threadAcademicYears);
		model.put("threadFaculties", threadFaculties);
		model.put("threadDepartments", threadDepartments);
		model.put("threadStaffs", threadStaffs);
		model.put("threads", status);
		return "cp.threads-evaluate";
	}

	/**
	 * Adding a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-a-thread", method = RequestMethod.GET)
	public String addAThread(ModelMap model, HttpSession session) {
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		// Get topic's category
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list project statuses
		List<mProjectStatus> projectStatuses = projectStatusService.list();

		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("threadCategory", topicCategory);
		model.put("projectStatuses", projectStatuses);
		model.put("threadFormAdd", new mThreadValidation());
		model.put("thread", status);
		return "cp.addAThread";
	}

	/**
	 * Adding a project
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-a-newproject", method = RequestMethod.GET)
	public String addAProject(ModelMap model, HttpSession session) {
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		// Get list staffs
		List<mStaff> staffList = staffService.listStaffs();
		// Get list member roles
		List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService
				.getList();

		List<ProjectResearchField> projectResearchFields = projectResearchFieldService
				.list();
		String currentStaffName = session.getAttribute("currentstaffName")
				.toString();
		String currentUserName = (!"".equals(currentStaffName)) ? currentStaffName
				: session.getAttribute("currentUserName").toString();
		String currentUserFaculty = session.getAttribute("currentUserFaculty")
				.toString();
		// Put data back to view
		model.put("staffList", staffList);
		model.put("currentUserName", currentUserName);
		model.put("currentUserFaculty", currentUserFaculty);
		model.put("memberRolesList", memberRolesList);
		model.put("listFaculty", listFaculty);
		model.put("projectCallsList", projectCallsList);
		model.put("projectsAddForm", new ProjectsValidation());
		model.put("projectResearchFieldList", projectResearchFields);

		model.put("projects", status);
		return "cp.addAProject";
	}

	private String establishFileNameStoredDataBase(String filename) {
		Date currentDate = new Date();
		SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat(
				"HHmmssddMMyyyy");
		String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
		return "thuyetminh-" + sCurrentDate + filename;

	}

	private String establishFullFileNameForUpload(String filename,
			String userCode, HttpServletRequest request) {

		String uploadDir = "/uploads" + File.separator + userCode
				+ File.separator + "projects";
		// String realPathtoUploads =
		// request.getServletContext().getRealPath(uploadDir);
		String realPathtoUploads = PROJECT_ROOT_DIR + File.separator + userCode
				+ File.separator + "projects";
		File dir = new File(realPathtoUploads);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// Set name file
		// filename =
		// establishFileNameStoredDataBase(filename);//"thuyetminh-"+sCurrentDate+filename;
		String fullfilename = realPathtoUploads + File.separator + filename;
		System.out.println(name()
				+ "::establishFullFileNameForDownload, PROJECT_ROOT_DIR = "
				+ PROJECT_ROOT_DIR + ", fullfilename = " + fullfilename);
		return fullfilename;
	}

	private String establishFullFileNameForDownload(String filename,
			String userCode, HttpServletRequest request) {

		String uploadDir = "/uploads" + File.separator + userCode
				+ File.separator + "projects";
		// String realPathtoUploads =
		// request.getServletContext().getRealPath(uploadDir);
		String realPathtoUploads = PROJECT_ROOT_DIR + File.separator + userCode
				+ File.separator + "projects";

		File dir = new File(realPathtoUploads);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// System.out.println(name()
		// + "::establishFullFileNameForDownload(filename = " + filename
		// + ", dir = " + dir.getAbsolutePath());
		// Set name file
		// filename = "thuyetminh-"+sCurrentDate+filename;
		// String fullfilename = dir.getAbsolutePath() + File.separator +
		// filename;
		String fullfilename = realPathtoUploads + File.separator + filename;

		System.out.println(name()
				+ "::establishFullFileNameForDownload, PROJECT_ROOT_DIR = "
				+ PROJECT_ROOT_DIR + ", fullfilename = " + fullfilename);
		return fullfilename;
	}

	@RequestMapping(value = "save-a-project", method = RequestMethod.POST)
	public String saveAProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectsAddForm") ProjectsValidation projectValid,
			BindingResult result, Map model, HttpSession session) {
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		// Get list staffs
		List<mStaff> staffList = staffService.listStaffs();
		// Get list member roles
		List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService
				.getList();
		// Get list project research field
		List<ProjectResearchField> projectResearchFields = projectResearchFieldService
				.list();

		// Put data back to view
		model.put("staffList", staffList);
		model.put("currentUserName", session.getAttribute("currentUserName")
				.toString());
		model.put("memberRolesList", memberRolesList);
		model.put("listFaculty", listFaculty);
		model.put("projectCallsList", projectCallsList);
		model.put("projectResearchFieldList", projectResearchFields);
		model.put("projects", status);
		if (result.hasErrors()) {
			return "cp.addAProject";
		} else {
			// Prepare data for inserting DB
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();
			String projectCallCode = projectValid.getProjectCallCode();
			String projectName = projectValid.getProjectName();
			String startDate = projectValid.getProjectStartDate();
			String endDate = projectValid.getProjectEndDate();
			int budgetMaterial = (!"".equals(projectValid.getBudgetMaterial())) ? Integer
					.parseInt(projectValid.getBudgetMaterial()) : 0;
			String facultyAdd = projectValid.getFalcutyAddress();
			String projectContent = projectValid.getProjectContent();
			String projectResult = projectValid.getProjectResult();
			String projectSurvey = projectValid.getProjectSurvey();
			String projectMotivation = projectValid.getProjectMotivation();
			String projectObjective = projectValid.getProjectObjective();
			String projectCode = "PROJECT-CODE-" + projectCallCode;
			String currentProjectCode = projectCode;
			String projectCategory = "";
			String projectResearchFieldCode = ""; // This field in the table
													// tplprojects will be empty

			mProjectCalls selectedProjectCall = projectCallsService
					.loadAProjectCallByCode(projectCallCode);
			if ("OPEN_FOR_SUBMISSION".equals(selectedProjectCall
					.getPROJCALL_STATUS())) {
				for (mProjectCalls pc : projectCallsList) {
					if (pc.getPROJCALL_CODE().equals(projectCallCode)) {
						projectCategory = pc.getPROJCALL_PROJCATCODE();
						break;
					}
				}
				try {
					// Members
					String[] projectMembers = request
							.getParameterValues("projectMembers");
					String[] projectMemberRole = request
							.getParameterValues("projectMemberRole");
					String[] projectMemberTasks = request
							.getParameterValues("projectMemberTasks");
					String[] projectMemberWorkingDays = request
							.getParameterValues("projectMemberWorkingDays");
					String[] projectMemberBudget = request
							.getParameterValues("projectMemberBudget");
					String[] projectResearchFieldCodeList = request
							.getParameterValues("projectResearchFieldCodeList");

					if (projectMembers.length > 0) {
						/**
						 * Uploading file
						 */
						MultipartFile paperSourceUploadFile = projectValid
								.getProjectFileUpload();
						String fileName = paperSourceUploadFile
								.getOriginalFilename();
						String paperSourceUploadFileSrc = "";
						try {
							// Creating Date in java with today's date.
							/*
							 * Date currentDate = new Date(); SimpleDateFormat
							 * dateformatyyyyMMdd = new
							 * SimpleDateFormat("HHmmssddMMyyyy"); String
							 * sCurrentDate =
							 * dateformatyyyyMMdd.format(currentDate);
							 */

							byte[] bytes = paperSourceUploadFile.getBytes();
							String uploadDir = "/uploads" + File.separator
									+ userCode + File.separator + "projects";
							String realPathtoUploads = request
									.getServletContext().getRealPath(uploadDir);
							File dir = new File(realPathtoUploads);
							if (!dir.exists()) {
								dir.mkdirs();
							}

							// Set name file
							// fileName = "thuyetminh-"+sCurrentDate+fileName;
							// String fullfilename = dir.getAbsolutePath()+
							// File.separator + fileName;

							fileName = establishFileNameStoredDataBase(fileName);
							String fullfilename = establishFullFileNameForUpload(
									fileName, userCode, request);

							File serverFile = new File(fullfilename);
							System.out
									.println(name()
											+ "::saveAProject, upload file with fileName (stored in DataBase) = "
											+ fileName + ", fullfilename = "
											+ fullfilename);
							// Save data into file
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();

							// Set name for saving into DB
							if (serverFile.exists()) {
								paperSourceUploadFileSrc = fileName;
							}
						} catch (IOException e) {
							System.out.println("Can not upload file");
						} catch (Exception e) {
							System.out.println(e.getStackTrace());
						}

						int totalBudget = budgetMaterial;
						for (int i = 0; i < projectMemberBudget.length; i++) {
							totalBudget += Integer
									.valueOf(projectMemberBudget[i]);
						}

						int i_InsertAProject = threadService.saveAProject(
								userRole, userCode, projectCallCode,
								projectName, projectContent, projectMotivation,
								projectResult, budgetMaterial, totalBudget,
								projectCode, facultyAdd, projectSurvey,
								projectObjective, startDate, endDate,
								projectCategory, projectResearchFieldCode,
								paperSourceUploadFileSrc,
								projectResearchFieldCodeList);
						if (i_InsertAProject > 0) {
							model.put("status", "Thêm mới thành công!");
							projectCode = projectCallCode + i_InsertAProject;
							threadService.saveMemberTasks(projectCode,
									projectMembers, projectMemberRole,
									projectMemberTasks,
									projectMemberWorkingDays,
									projectMemberBudget, currentProjectCode);
						}
						return "redirect:" + this.baseUrl
								+ "/cp/list-projects.html";
					}
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
					model.put("err",
							"Cần phải thêm thành viên và lĩnh vực vào đề tài.");
				}
			} else {
				model.put("err", "Xin lỗi! Đợt gọi đề tài đã bị đóng.");
			}
			return "cp.addAProject";
		}
	}

	/**
	 * Save a thread
	 * 
	 * @param threadValid
	 * @param result
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "save-a-thread", method = RequestMethod.POST)
	public String saveTheThread(
			HttpServletRequest request,
			@Valid @ModelAttribute("threadFormAdd") mThreadValidation threadValid,
			BindingResult result, Map model, HttpSession session) {
		// Get topic's category
		List<mTopicCategory> topicCategoryList = tProjectCategoryService.list();
		// Get list reportingYear
		List<mAcademicYear> topicReportingAcademicDateList = academicYearService
				.list();
		// Get list project statuses
		List<mProjectStatus> projectStatuses = projectStatusService.list();

		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		// Put data back to view
		model.put("threadReportingAcademicDate", topicReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("threadCategory", topicCategoryList);
		model.put("projectStatuses", projectStatuses);
		model.put("thread", status);
		if (result.hasErrors()) {
			return "cp.addAThread";
		} else {
			// Prepare data for inserting DB
			String userCode = session.getAttribute("currentUserCode")
					.toString();
			String threadName = threadValid.getThreadName();
			String threadCategory = threadValid.getThreadCatCode();
			String threadContent = threadValid.getThreadContent();
			String threadStartDate = threadValid.getThreadStartDate();
			String threadEndDate = threadValid.getThreadEndDate();
			String threadMotivation = threadValid.getThreadMotivation();
			String threadReportingDate = threadValid
					.getThreadReportingAcademicDate();
			String threadResult = threadValid.getThreadResult();
			String threadStatus = threadValid.getThreadStatus();
			String joiningStaffs = threadValid.getStaff();
			List<String> listStaffs = threadValid.getStaffsRole();
			List<String> listStaffRoles = threadValid.getRoleList();
			int threadBudget = threadValid.getThreadBudget();
			String threadSourceUploadFileSrc = "";
			String threadCode = "thread";

			// Creating Date in java with today's date.
			Date currentDate = new Date();
			// change date into string yyyyMMdd format example "20110914"
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat(
					"HHmmssddMMyyyy");
			String sCurrentDate = dateformatyyyyMMdd.format(currentDate);

			threadCode = threadCode + userCode.concat(sCurrentDate);
			/**
			 * Uploading file
			 */
			MultipartFile threadSourceUploadFile = threadValid
					.getThreadSourceFile();
			String fileName = threadSourceUploadFile.getOriginalFilename();
			if (fileName != "") {
				try {
					byte[] bytes = threadSourceUploadFile.getBytes();
					String path = request.getServletContext().getRealPath(
							"uploads");
					File dir = new File(path + "/threads");
					if (!dir.exists()) {
						dir.mkdirs();
					}

					// Create a file
					String currentUserName = session.getAttribute(
							"currentUserName").toString();
					fileName = currentUserName + "_" + sCurrentDate + "_"
							+ fileName;
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + fileName);
					if (serverFile != null) {
						threadSourceUploadFileSrc = dir.getAbsolutePath()
								+ File.separator + fileName;
					}
					// Save data into file
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					model.put("status", "You failed to upload " + fileName);
				}
			}

			int i_InsertATopic = threadService.saveAThread(userCode,
					threadName, threadCategory, threadContent, threadStartDate,
					threadEndDate, threadMotivation, threadReportingDate,
					threadResult, threadStatus, threadBudget,
					threadSourceUploadFileSrc, threadCode, listStaffs,
					listStaffRoles);
			if (i_InsertATopic > 0) {
				model.put("status", "Successfully saved a topic");
			}
			// return "cp.addAThread";
			return "redirect:" + this.baseUrl + "/cp/threads-listadd.html";
		}
	}

	/**
	 * 
	 * @param model
	 * @param threadId
	 * @param session
	 * @return
	 */
	@RequestMapping("/threaddetail/{id}")
	public String editAThread(ModelMap model, @PathVariable("id") int threadId,
			HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list statues
		List<mProjectStatus> projectStatuses = projectStatusService.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		List<mProjectStaffs> listProjectStaffs = projectStaffsService
				.loadAProjectStaffByProjectCode(thread.getPROJ_Code());
		List<mStaff> listStaffs = new ArrayList<mStaff>();
		if (listProjectStaffs != null) {
			for (mProjectStaffs projectStaff : listProjectStaffs) {
				mStaff staff = staffService.loadStaffByUserCode(projectStaff
						.getPROJSTAFF_Staff_Code());
				listStaffs.add(staff);
			}
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("projectStatuses", projectStatuses);
		model.put("listStaffs", listStaffs);
		model.put("thread", status);
		if (thread != null) {
			// Get topic's category
			List<mTopicCategory> topicCategoryList = tProjectCategoryService
					.list();

			// Put journal list and topic category to view
			model.put("threadCategory", topicCategoryList);
			model.put("threadFormEdit", new mThreadValidation());
			model.put("threadId", threadId);
			model.put("threadCatCode", thread.getPROJ_ProjCat_Code());
			model.put("reportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadCode", thread.getPROJ_Code());
			model.put("threadName", thread.getPROJ_Name());
			model.put("threadEndDate", thread.getPROJ_EndDate());
			model.put("threadMotivation", thread.getPROJ_Motivation());
			model.put("threadReportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadStartDate", thread.getPROJ_StartDate());
			model.put("threadStatus", thread.getPROJ_Status_Code());
			model.put("threadBudget", thread.getPROJ_TotalBudget());
			model.put("threadContent", thread.getPROJ_Content());

			return "cp.editAThread";
		}
		return "cp.notFound404";
	}

	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitedprojectdetail/{id}")
	public String editASubmittedProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		// Projects project =
		// threadService.loadASumittedProjectByIdAndUserCode(userRole,userCode,
		// projectId);
		Projects project = threadService.loadProjectsById(projectId);

		List<mProjectStatus> statuses = projectStatusService.list();
		HashMap<String, String> mStatusCode2Name = new HashMap<String, String>();
		for (mProjectStatus pt : statuses) {
			mStatusCode2Name.put(pt.getPROJSTAT_Code(),
					pt.getPROJSTAT_Description());
		}
		project.setPROJ_Status_Code(mStatusCode2Name.get(project
				.getPROJ_Status_Code()));

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for (mStaff st : staffs) {
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}

		List<DetailCommentSubmittedProjects> detailCommentSubmittedProjects = commentsOfSubmittedProjectsService
				.loadListDetailsCommentsOfSubmittedProjectsByProjectCode(project
						.getPROJ_Code());
		for (DetailCommentSubmittedProjects cm : detailCommentSubmittedProjects) {
			cm.setCMTSUBPRJ_StaffCode(mStaffCode2Name.get(cm
					.getCMTSUBPRJ_StaffCode()));
		}
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		// Put data back to view
		model.put("projectCallsList", projectCallsList);
		model.put("detailCommentSubmittedProjectsList",
				detailCommentSubmittedProjects);
		model.put("projects", status);
		if (project != null) {
			// Get summary comment
			String summaryComment = "";
			mCommentsOfSubmittedProjects commentsOfSubmittedProject = commentsOfSubmittedProjectsService
					.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(
							userCode, project.getPROJ_Code());
			if (commentsOfSubmittedProject != null) {
				summaryComment = (!"".equals(commentsOfSubmittedProject
						.getCOMPROJ_COMMENT())) ? commentsOfSubmittedProject
						.getCOMPROJ_COMMENT() : "";
			}

			// Put journal list and topic category to view
			model.put("projectEdit", project);
			model.put("projectFormEdit", new ProjectsValidation());
			model.put("projectId", projectId);
			model.put("summaryComment", summaryComment);
			return "cp.viewDetailCommentASumittedProject";
		}
		return "cp.notFound404";
	}

	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/approveprojectdetail/{id}")
	public String editApproveProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		Projects project = threadService.loadASumittedProjectByIdAndUserCode(
				userRole, userCode, projectId);

		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		// Put data back to view
		model.put("projectCallsList", projectCallsList);
		model.put("projects", status);
		if (project != null) {
			List<mProjectComments> projectComments = projectCommentsService
					.loadAProjectCommentByProjectCode(project.getPROJ_Code());
			mProjectComments projectComment1 = null;
			mProjectComments projectComment2 = null;
			if (projectComments != null && projectComments.size() > 1) {
				projectComment1 = projectComments.get(0);
				projectComment2 = projectComments.get(1);
			}
			model.put("projectComment1", projectComment1);
			model.put("projectComment2", projectComment2);
			// Put journal list and topic category to view
			model.put("projectEdit", project);
			model.put("projectFormEdit", new ProjectsValidation());
			model.put("projectId", projectId);
			return "cp.approveaproject";
		}
		return "cp.notFound404";
	}

	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/projectcommentsdetail/{id}")
	public String editCommentProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();

		// Projects project =
		// threadService.loadASumittedProjectByIdAndUserCode(userRole,userCode,
		// projectId);
		Projects project = threadService.loadProjectsById(projectId);

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for (mStaff st : staffs) {
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		// Put data back to view
		model.put("projectCallsList", projectCallsList);
		model.put("projects", status);
		if (project != null) {
			List<mProjectComments> projectComments = projectCommentsService
					.loadAProjectCommentByProjectCode(project.getPROJ_Code());
			/*
			 * mProjectComments projectComment1 = null; mProjectComments
			 * projectComment2 = null; if(projectComments != null &&
			 * projectComments.size() > 0) { projectComment1 =
			 * projectComments.get(0); if(projectComments.size() > 1)
			 * projectComment2 = projectComments.get(1); else{ projectComment2 =
			 * new mProjectComments(); } }
			 */
			// Put journal list and topic category to view
			// model.put("projectComment1", projectComment1);
			// model.put("projectComment2", projectComment2);

			String projectCode = project.getPROJ_Code();
			String summaryComment = "";

			// mCommentsOfSubmittedProjects commentsOfSubmittedProject =
			// commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(userCode,
			// projectCode);
			List<mCommentsOfSubmittedProjects> commentsOfSubmittedProject = commentsOfSubmittedProjectsService
					.loadCommentsOfSubmittedProjectByProjectCode(projectCode);

			if (commentsOfSubmittedProject != null) {
				for (mCommentsOfSubmittedProjects cm : commentsOfSubmittedProject) {
					// summaryComment =
					// (!"".equals(commentsOfSubmittedProject.getCOMPROJ_COMMENT()))
					// ? commentsOfSubmittedProject.getCOMPROJ_COMMENT() : "";
					summaryComment = (!"".equals(cm.getCOMPROJ_COMMENT())) ? cm
							.getCOMPROJ_COMMENT() : "";
				}

			}

			List<DetailCommentSubmittedProjects> listDetailCommentSubmittedProjects = commentsOfSubmittedProjectsService
					.loadListDetailsCommentsOfSubmittedProjectsByProjectCode(projectCode);
			for (DetailCommentSubmittedProjects cm : listDetailCommentSubmittedProjects) {
				String staffCode = cm.getCMTSUBPRJ_StaffCode();
				cm.setCMTSUBPRJ_StaffCode(mStaffCode2Name.get(staffCode));
			}
			System.out.println(name()
					+ "::editCommentProject listDetailComments.sz = "
					+ listDetailCommentSubmittedProjects.size()
					+ ", summaryComments = " + summaryComment);

			model.put("listDetailCommentSubmittedProjects",
					listDetailCommentSubmittedProjects);
			model.put("projectEdit", project);
			model.put("summaryComment", summaryComment);
			model.put("projectFormEdit", new ProjectsValidation());
			model.put("projectId", projectId);
			return "cp.projectcomments";
		}
		return "cp.notFound404";
	}

	/**
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/aprojectdetail/{id}")
	public String editAProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		// Projects project =
		// threadService.loadAProjectByIdAndUserCode(userRole,userCode,
		// projectId);
		Projects project = threadService.loadProjectsById(projectId);
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		// Get list staffs
		List<mStaff> staffList = staffService.listStaffs();
		// Get list member roles
		List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService
				.getList();
		String currentStaffName = session.getAttribute("currentstaffName")
				.toString();
		String currentUserName = (!"".equals(currentStaffName)) ? currentStaffName
				: session.getAttribute("currentUserName").toString();
		String currentUserFaculty = session.getAttribute("currentUserFaculty")
				.toString();

		model.put("projects", status);
		if (project != null) {
			List<ProjectTasks> projectTasks = projectTasksService
					.loadAProjectTaskByProjectCode(project.getPROJ_Code());

			// Prepare research List
			List<ProjectsProjectResearchField> selectedProjectResearchFields = threadService
					.loadProjectsProjectResearchFieldListByProjectCode(project
							.getPROJ_Code());
			List<ProjectResearchField> projectResearchFields = projectResearchFieldService
					.list();
			List<List<String>> listResearchFields = new ArrayList<>();
			for (ProjectResearchField projectResearchField : projectResearchFields) {
				List<String> tempList = new ArrayList<>();
				tempList.add(projectResearchField.getPRJRSHF_Code());
				tempList.add(projectResearchField.getPRJRSHF_Name());
				boolean selected = false;
				for (ProjectsProjectResearchField selectedProjectResearchField : selectedProjectResearchFields) {
					if (projectResearchField.getPRJRSHF_Code().equals(
							selectedProjectResearchField
									.getPRJPRJRSHF_PRJRSHFCode())) {
						selected = true;
						break;
					}
				}
				if (selected) {
					tempList.add("SELECTED");
				} else {
					tempList.add("");
				}
				listResearchFields.add(tempList);
			}
			/*
			 * if(selectedProjectResearchFields.size() > 0 &&
			 * projectResearchFields.size() > 0) { for (ProjectResearchField
			 * projectResearchField : projectResearchFields) { List<String>
			 * tempList = new ArrayList<>();
			 * tempList.add(projectResearchField.getPRJRSHF_Code());
			 * tempList.add(projectResearchField.getPRJRSHF_Name()); boolean
			 * selected = false; for (ProjectsProjectResearchField
			 * selectedProjectResearchField : selectedProjectResearchFields) {
			 * if(projectResearchField.getPRJRSHF_Code().equals(
			 * selectedProjectResearchField.getPRJPRJRSHF_PRJRSHFCode())){
			 * tempList.add("SELECTED"); }else{ tempList.add(""); } }
			 * listResearchFields.add(tempList); } }else
			 * if(projectResearchFields.size() > 0) { for (ProjectResearchField
			 * projectResearchField : projectResearchFields) { List<String>
			 * tempList = new ArrayList<>();
			 * tempList.add(projectResearchField.getPRJRSHF_Code());
			 * tempList.add(projectResearchField.getPRJRSHF_Name());
			 * listResearchFields.add(tempList); } }
			 */

			// Put data back to view
			model.put("listResearchFields", listResearchFields);
			model.put("projectEdit", project);
			model.put("projectFormEdit", new ProjectsValidation());
			model.put("projectId", projectId);
			model.put("projectTasks", projectTasks);
			model.put("staffList", staffList);
			model.put("currentUserName", currentUserName);
			model.put("memberRolesList", memberRolesList);
			model.put("listFaculty", listFaculty);
			model.put("projectCallsList", projectCallsList);
			model.put("projectResearchFieldList", projectResearchFields);
			return "cp.editAProject";
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
	@RequestMapping("/generatepdf/{id}")
	public String generatePDFProject(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@PathVariable("id") int projectId, HttpSession session)
			throws IOException, DocumentException {
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();

		System.out.println(name() + "::generatePDFProject, projectID = "
				+ projectId);

		// Projects project =
		// threadService.loadAProjectByIdAndUserCode(userRole,userCode,
		// projectId);
		Projects project = threadService.loadProjectsById(projectId);

		System.out.println(name() + "::generatePDFProject, project code = "
				+ project.getPROJ_Code() + ", userCode = "
				+ project.getPROJ_User_Code() + ", name = "
				+ project.getPROJ_Name());

		final ServletContext servletContext = request.getSession()
				.getServletContext();
		final File tempDirectory = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();
		String sProjectPDFFileName = project.getPROJ_ID() + "_"
				+ project.getPROJ_User_Code() + ".pdf";

		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		// Put data back to view
		model.put("projectCallsList", projectCallsList);
		model.put("projects", status);
		if (project != null) {
			// Put journal list and topic category to view

			// replace content by the content collected from project tasks
			List<ProjectTasks> projectTasks = projectTasksService
					.loadAProjectTaskByProjectCode(project.getPROJ_Code());
			String content = "<ul>\n";
			for (ProjectTasks pt : projectTasks) {
				content += "<li>" + pt.getPRJTSK_Task() + "</li>\n";
			}
			content += "</ul>";
			System.out.println(name()
					+ "::generatePDFProject, collect content = " + content);
			project.setPROJ_Content(content);

			model.put("projectEdit", project);
			model.put("projectFormEdit", new ProjectsValidation());
			model.put("projectId", projectId);

			this.prepareContent(project);
			PDFGenerator.v_fGenerator(temperotyFilePath + "\\"
					+ sProjectPDFFileName);
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos = convertPDFToByteArrayOutputStream(temperotyFilePath
						+ "\\" + sProjectPDFFileName);
				// response.setContentType("application/pdf");
				// response.setHeader("Content-Disposition",
				// "attachment:filename=report.pdf");
				OutputStream os = response.getOutputStream();
				baos.writeTo(os);
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	private void prepareContent(Projects project) throws IOException {
		if (project != null) {
			try {
				String sCurrentYear = DateUtil
						.s_fGetCurrentDateTimeByTemplate("yyyy");
				String sProjectYear = DateUtil
						.s_fExtractYearFromAcademicYear(project
								.getPROJ_AcaYear_Code());
				mStaff oStaffInfo = staffService.loadStaffByUserCode(project
						.getPROJ_User_Code());
				String sLeaderName = (oStaffInfo.getStaff_Name() != null) ? oStaffInfo
						.getStaff_Name() : "LEADER'S NAME";
				String sLeaderEmail = (oStaffInfo.getStaff_Email() != null) ? oStaffInfo
						.getStaff_Email() : "LEADER'S EMAIL";
				String sLeaderDepartment = (oStaffInfo.getDepartment()
						.getDepartment_Name() != null) ? oStaffInfo
						.getDepartment().getDepartment_Name() : "DEPARTMENT";
				String sLeaderFaculty = (oStaffInfo.getDepartment()
						.getFaculty().getFaculty_Name() != null) ? oStaffInfo
						.getDepartment().getFaculty().getFaculty_Name()
						: "FACULTY";
				String sLeaderPhoneNo = (oStaffInfo.getStaff_Phone() != null) ? oStaffInfo
						.getStaff_Phone() : "PHONE NUMBER";
				String sYear = (sProjectYear != null) ? sProjectYear
						: sCurrentYear;
				String sProjectName = (project.getPROJ_Name() != null) ? project
						.getPROJ_Name() : "PROJECT'S NAME";
				String sStartDate = (project.getPROJ_StartDate() != null) ? project
						.getPROJ_StartDate() : "DD/MM/YYYY";
				String sEndDate = (project.getPROJ_EndDate() != null) ? project
						.getPROJ_EndDate() : "DD/MM/YYYY";
				String sProjectCode = (project.getPROJ_Code() != null) ? project
						.getPROJ_Code() : "PROJECT'S CODE";
				String sProjectMotivation = (project.getPROJ_Motivation() != null) ? project
						.getPROJ_Motivation() : "PROJECT'S MOTIVATION";
				String sProjectContent = (project.getPROJ_Content() != null) ? project
						.getPROJ_Content() : "PROJECT'S CONTENT";
				String sProjectResult = (project.getPROJ_Result() != null) ? project
						.getPROJ_Result() : "PROJECT'S RESULT";
				;
				String sProjectSurvey = (project.getPROJ_Survey() != null) ? project
						.getPROJ_Survey() : "PROJECT'S SURVEY";
				int iBudgetMaterial = (project.getPROJ_BudgetMaterial() > 0) ? project
						.getPROJ_BudgetMaterial() : 0;

				String sProjectMembersList = "";
				String sProjectTasksList = "";
				int iTotalWorkingDays = 0;
				int iTotalTaskFees = 0;
				int iTotalFee = iBudgetMaterial;
				// List<ProjectTasks> projectTasks =
				// projectTasksService.loadAProjectTaskByProjectCode(sProjectCode);
				List<List<String>> projectTasks = projectTasksService
						.getProjectTaskByProjectCode(sProjectCode);
				// HashSet<List<String>> memberNameList = new HashSet<>();
				ArrayList<List<String>> memberNameList = new ArrayList<>();
				if (projectTasks != null) {
					for (List<String> projectTask : projectTasks) {
						List<String> memberTemp = new ArrayList<>();
						memberTemp.add(projectTask.get(0)); // Name
						memberTemp.add(projectTask.get(1)); // School-Faculty
						memberNameList.add(memberTemp);

						// Showing tasks
						sProjectTasksList += "<tr>";
						sProjectTasksList += "<td><div class='content'>"
								+ projectTask.get(0) + "</div></td>";
						sProjectTasksList += "<td><div class='content'>"
								+ projectTask.get(3) + "</div></td>";
						sProjectTasksList += "<td><div class='content'>"
								+ projectTask.get(4) + "</div></td>";
						sProjectTasksList += "<td><div class='content  center'>"
								+ projectTask.get(5) + "</div></td>";
						sProjectTasksList += "<td align='center'><div class='content center'>"
								+ Money2StringConvertor
										.addDot2Moyney(projectTask.get(6))
								+ "</div></td>";
						// sProjectTasksList +=
						// "<td><div class='content'></div></td>";
						sProjectTasksList += "</tr>";

						iTotalWorkingDays += Integer.parseInt(projectTask
								.get(5));
						iTotalTaskFees += Integer.parseInt(projectTask.get(6));
					}
					// Calculating total fee
					iTotalFee += iTotalTaskFees;
				}

				if (memberNameList.size() > 0) {
					int iNo = 1;
					for (List<String> list : memberNameList) {
						// Showing project members
						sProjectMembersList += "<tr>";
						sProjectMembersList += "<td width='5%'><div class='content'>"
								+ iNo + ".</div></td>";
						sProjectMembersList += "<td colspan='2'><div class='content'>"
								+ list.get(0) + "</div></td>";
						sProjectMembersList += "<td width='45%'><div class='content'>"
								+ list.get(1) + "</div></td>";
						sProjectMembersList += "<td width='15%'><div class='content'></div></td>";
						sProjectMembersList += "</tr>";
						iNo++;
					}
				}

				String sProjectObjective = (project.getPROJ_Objective() != null) ? project
						.getPROJ_Objective() : "PROJECT'S OBJECTIVE";
				String sTasksBudgetWords = Money2StringConvertor
						.convert2TextStartUpcase(Integer.toString(iTotalFee));
				String sLeaderDegree = "";
				String sLeaderRole = "";// "Giảng viên";

				String sProjectApplicability = "IN REAL LIFE...";
				ClassLoader classLoader = getClass().getClassLoader();
				// Getting content from template file
				File o_FontFile = new File(classLoader.getResource(
						nProjectController._sHTMLTemplate).getFile());
				String sFilePath = o_FontFile.getAbsolutePath();
				StringBuilder sTemplateContent = FileUtil
						.sGetFileContent(sFilePath);

				// Replace year
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___YEAR___", sYear);

				// Replace project name
				sProjectName = sProjectName.replaceAll("&", "&amp;"); // Fix
																		// defect
																		// name
																		// has
																		// problem
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_NAME___", sProjectName);

				// Replace project start date
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___START_DATE___", sStartDate);

				// Replace project end date
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___END_DATE___", sEndDate);

				// Replace project code
				sProjectCode = "				";// HAVE NOT generate automatically project
				// code, use BLANK
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_CODE___", sProjectCode);

				// Replace project leader's name
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___LEAD_NAME___", sLeaderName);

				// Replace project leader's degree
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___DEGREE___", sLeaderDegree);

				// Replace project leader's role
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___ROLE___", sLeaderRole);

				// Replace project leader's department
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___DEPARTMENT_ADDRESS___", sLeaderDepartment);

				// Replace project leader's faculty
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___FALCUTLY_ADDRESS___", sLeaderFaculty);

				// Replace project leader's office phone no
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___OFFICE_PHONENO___", "");

				// Replace project leader's email
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___EMAIL___", sLeaderEmail);

				// Replace project leader's private phone no
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PRIVATE_PHONENO___", "");// sLeaderPhoneNo);

				// Replace project leader's mobile phone
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___MOBILE___", sLeaderPhoneNo);

				// Replace project members list
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_MEMBERS_LIST___", sProjectMembersList);

				// Replace project survey
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_SURVEY___", sProjectSurvey);

				// Replace project motivation
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_MOTIVATION___", sProjectMotivation);

				// Replace project objective
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_OBJECTIVE___", sProjectObjective);

				// Replace project content
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_CONTENT___", sProjectContent);

				// Replace project result
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___PROJECT_RESULT___", sProjectResult);

				// Replace project total budget
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_BUDGET___", Money2StringConvertor
								.addDot2Moyney(Integer.toString(iTotalFee)));

				// Replace project total budget
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_BUDGET_WORDS___", Money2StringConvertor
								.convert2TextStartUpcase(Integer
										.toString(iTotalFee)));

				// Replace project tasks list
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TASKS_LIST___", sProjectTasksList);

				// Replace project members working days
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_MEMBERS_WORKINGDAYS___",
						Integer.toString(iTotalWorkingDays));

				// Replace project members fees
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_MEMBERS_FEE___",
						Money2StringConvertor.addDot2Moyney(Integer
								.toString(iTotalTaskFees)));

				// Replace project material fee
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___MATERIAL_FEE___", Money2StringConvertor
								.addDot2Moyney(Integer
										.toString(iBudgetMaterial)));

				// Replace project tasks budget
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_TASKS_BUDGET___", Money2StringConvertor
								.addDot2Moyney(Integer.toString(iTotalFee)));

				// Replace project tasks budget
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___TOTAL_TASKS_BUDGET_WORDS___", sTasksBudgetWords);

				// Replace year sign
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___YEAR_SIGN___", sCurrentYear);

				// Replace project signature
				sTemplateContent = FileUtil.sReplaceAll(sTemplateContent,
						"___SIGNATURE___", sLeaderName);

				// Write completed content into file
				File o_CompletedContentFile = new File(classLoader.getResource(
						nProjectController._sHTMLCompletedContent).getFile());

				FileUtil.v_fWriteContentIntoAFile(o_CompletedContentFile,
						sTemplateContent);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Sending project - lock for 1st time
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/sendproject/{id}")
	public String sendAProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		Projects project = threadService.loadAProjectByIdAndUserCode(userRole,
				userCode, projectId);
		// List<mProjectStatus> statuses = projectStatusService.list();

		// Put data back to view
		model.put("projects", status);
		if (project != null) {
			threadService.sendAProject(project, false);
			model.put("status", "Gửi đề tài thành công!");
			return "redirect:" + this.baseUrl + "/cp/list-projects.html";
		}
		return "cp.notFound404";
	}

	/**
	 * Submitting project - lock for 2nd time
	 * 
	 * @param model
	 * @param projectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitproject/{id}")
	public String submitAProject(ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		Projects project = threadService.loadAProjectByIdAndUserCode(userRole,
				userCode, projectId);

		// Put data back to view
		model.put("projects", status);
		if (project != null) {
			threadService.sendAProject(project, true);
			model.put("status", "Nộp đề tài thành công!");
			return "redirect:" + this.baseUrl
					+ "/cp/modify-submitted-projects.html";
		}
		return "cp.notFound404";
	}

	/**
	 * 
	 * @param model
	 * @param threadId
	 * @param session
	 * @return
	 */
	@RequestMapping("/threadshow/{id}")
	public String showAThread(ModelMap model, @PathVariable("id") int threadId,
			HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		List<mProjectStaffs> listProjectStaffs = projectStaffsService
				.loadAProjectStaffByProjectCode(thread.getPROJ_Code());
		List<mStaff> listStaffs = new ArrayList<mStaff>();
		if (listProjectStaffs != null) {
			for (mProjectStaffs projectStaff : listProjectStaffs) {
				mStaff staff = staffService.loadStaffByUserCode(projectStaff
						.getPROJSTAFF_Staff_Code());
				listStaffs.add(staff);
			}
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("listStaffs", listStaffs);
		model.put("thread", status);
		if (thread != null) {
			model.put("threadFormEdit", new mThreadValidation());
			model.put("threadId", threadId);
			model.put("threadCatName", tProjectCategoryService
					.getTopicCategoryByCode(thread.getPROJ_ProjCat_Code())
					.getPROJCAT_Name());
			model.put("threadAcaYearCode", thread.getPROJ_AcaYear_Code());
			model.put("threadCode", thread.getPROJ_Code());
			model.put("threadName", thread.getPROJ_Name());
			model.put("threadEndDate", thread.getPROJ_EndDate());
			model.put("threadMotivation", thread.getPROJ_Motivation());
			model.put("threadReportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadStartDate", thread.getPROJ_StartDate());
			model.put(
					"threadStatus",
					projectStatusService.loadAProjectStatusByProjectCode(
							thread.getPROJ_Status_Code())
							.getPROJSTAT_Description());
			model.put("threadBudget", thread.getPROJ_TotalBudget());
			model.put("threadContent", thread.getPROJ_Content());

			return "cp.showAThread";
		}
		return "cp.notFound404";
	}

	@RequestMapping("/threadshowtoupdate/{id}")
	public String showAThreadToUpdate(ModelMap model,
			@PathVariable("id") int threadId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		List<mProjectStaffs> listProjectStaffs = projectStaffsService
				.loadAProjectStaffByProjectCode(thread.getPROJ_Code());
		List<mStaff> listStaffs = new ArrayList<mStaff>();
		if (listProjectStaffs != null) {
			for (mProjectStaffs projectStaff : listProjectStaffs) {
				mStaff staff = staffService.loadStaffByUserCode(projectStaff
						.getPROJSTAFF_Staff_Code());
				listStaffs.add(staff);
			}
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("listStaffs", listStaffs);
		model.put("thread", status);
		if (thread != null) {
			model.put("threadFormEdit", new mThreadValidation());
			model.put("threadId", threadId);
			model.put("threadCatName", tProjectCategoryService
					.getTopicCategoryByCode(thread.getPROJ_ProjCat_Code())
					.getPROJCAT_Name());
			model.put("threadAcaYearCode", thread.getPROJ_AcaYear_Code());
			model.put("threadCode", thread.getPROJ_Code());
			model.put("threadName", thread.getPROJ_Name());
			model.put("threadEndDate", thread.getPROJ_EndDate());
			model.put("threadMotivation", thread.getPROJ_Motivation());
			model.put("threadReportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadStartDate", thread.getPROJ_StartDate());
			model.put(
					"threadStatus",
					projectStatusService.loadAProjectStatusByProjectCode(
							thread.getPROJ_Status_Code())
							.getPROJSTAT_Description());
			model.put("threadBudget", thread.getPROJ_TotalBudget());
			model.put("threadContent", thread.getPROJ_Content());

			return "cp.showAThreadToUpdate";
		}
		return "cp.notFound404";
	}

	@RequestMapping("/threadshowtoapprove/{id}")
	public String showAThreadToApprove(ModelMap model,
			@PathVariable("id") int threadId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		List<mProjectStaffs> listProjectStaffs = projectStaffsService
				.loadAProjectStaffByProjectCode(thread.getPROJ_Code());
		List<mStaff> listStaffs = new ArrayList<mStaff>();
		if (listProjectStaffs != null) {
			for (mProjectStaffs projectStaff : listProjectStaffs) {
				mStaff staff = staffService.loadStaffByUserCode(projectStaff
						.getPROJSTAFF_Staff_Code());
				listStaffs.add(staff);
			}
		}

		List<mProjectStatus> projectStatus = projectStatusService.list();
		List<mProjectStatus> selectedStatus = new ArrayList<mProjectStatus>();
		for (mProjectStatus ps : projectStatus) {
			if (ps.getPROJSTAT_Code().equals("APPROVED")
					|| ps.getPROJSTAT_Code().equals("REJECT"))
				selectedStatus.add(ps);
		}
		for (mProjectStatus ps : selectedStatus) {
			System.out
					.println("ThreadController::showAThreadToApprove, selected ps = "
							+ ps.getPROJSTAT_Code());
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("listStaffs", listStaffs);
		model.put("thread", status);
		if (thread != null) {
			model.put("projectFormApprove", new mThreadValidation());
			model.put("threadId", threadId);
			model.put("threadCatName", tProjectCategoryService
					.getTopicCategoryByCode(thread.getPROJ_ProjCat_Code())
					.getPROJCAT_Name());
			model.put("threadAcaYearCode", thread.getPROJ_AcaYear_Code());
			model.put("threadCode", thread.getPROJ_Code());
			model.put("threadName", thread.getPROJ_Name());
			model.put("threadEndDate", thread.getPROJ_EndDate());
			model.put("threadMotivation", thread.getPROJ_Motivation());
			model.put("threadReportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadStartDate", thread.getPROJ_StartDate());
			// model.put("threadStatus",
			// projectStatusService.loadAProjectStatusByProjectCode(thread.getPROJ_Status_Code()).getPROJSTAT_Description());
			model.put("projectStatus", selectedStatus);
			model.put("threadBudget", thread.getPROJ_TotalBudget());
			model.put("threadContent", thread.getPROJ_Content());

			System.out
					.println("ThreadController::showAThreadToApprove, return cp.showAThreadToApprove");
			return "cp.showAThreadToApprove";
		}
		return "cp.notFound404";
	}

	@RequestMapping("/threadshowtoevaluate/{id}")
	public String showAThreadToEvaluate(ModelMap model,
			@PathVariable("id") int threadId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		List<mProjectStaffs> listProjectStaffs = projectStaffsService
				.loadAProjectStaffByProjectCode(thread.getPROJ_Code());
		List<mStaff> listStaffs = new ArrayList<mStaff>();
		if (listProjectStaffs != null) {
			for (mProjectStaffs projectStaff : listProjectStaffs) {
				mStaff staff = staffService.loadStaffByUserCode(projectStaff
						.getPROJSTAFF_Staff_Code());
				listStaffs.add(staff);
			}
		}

		List<mProjectStatus> projectStatus = projectStatusService.list();
		List<mProjectStatus> selectedStatus = new ArrayList<mProjectStatus>();
		for (mProjectStatus ps : projectStatus) {
			if (ps.getPROJSTAT_Code().equals("SUCCESS")
					|| ps.getPROJSTAT_Code().equals("FAILED"))
				selectedStatus.add(ps);
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("listStaffs", listStaffs);
		model.put("thread", status);
		if (thread != null) {
			model.put("projectFormApprove", new mThreadValidation());
			model.put("threadId", threadId);
			model.put("threadCatName", tProjectCategoryService
					.getTopicCategoryByCode(thread.getPROJ_ProjCat_Code())
					.getPROJCAT_Name());
			model.put("threadAcaYearCode", thread.getPROJ_AcaYear_Code());
			model.put("threadCode", thread.getPROJ_Code());
			model.put("threadName", thread.getPROJ_Name());
			model.put("threadEndDate", thread.getPROJ_EndDate());
			model.put("threadMotivation", thread.getPROJ_Motivation());
			model.put("threadReportingDate", thread.getPROJ_AcaYear_Code());
			model.put("threadStartDate", thread.getPROJ_StartDate());
			// model.put("threadStatus",
			// projectStatusService.loadAProjectStatusByProjectCode(thread.getPROJ_Status_Code()).getPROJSTAT_Description());
			model.put("projectStatus", selectedStatus);
			model.put("threadBudget", thread.getPROJ_TotalBudget());
			model.put("threadContent", thread.getPROJ_Content());

			System.out
					.println("ThreadController::showAThreadToApprove, return cp.showAThreadToApprove");
			return "cp.showAThreadToEvaluate";
		}
		return "cp.notFound404";
	}

	/**
	 * Editing a project
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-project", method = RequestMethod.POST)
	public String updateAProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormEdit") ProjectsValidation projectFormEdit,
			BindingResult result, Map model, HttpSession session) {
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		int projectEditId = projectFormEdit.getProjectId();
		Projects project = threadService.loadAProjectByIdAndUserCode(userRole,
				userCode, projectEditId);
		if (project != null) {
			// Prepare research List
			List<ProjectsProjectResearchField> selectedProjectResearchFields = threadService
					.loadProjectsProjectResearchFieldListByProjectCode(project
							.getPROJ_Code());
			List<ProjectResearchField> projectResearchFields = projectResearchFieldService
					.list();
			List<List<String>> listResearchFields = new ArrayList<>();
			for (ProjectResearchField projectResearchField : projectResearchFields) {
				List<String> tempList = new ArrayList<>();
				tempList.add(projectResearchField.getPRJRSHF_Code());
				tempList.add(projectResearchField.getPRJRSHF_Name());
				boolean selected = false;
				for (ProjectsProjectResearchField selectedProjectResearchField : selectedProjectResearchFields) {
					if (projectResearchField.getPRJRSHF_Code().equals(
							selectedProjectResearchField
									.getPRJPRJRSHF_PRJRSHFCode())) {
						selected = true;
						break;
					}
				}
				if (selected) {
					tempList.add("SELECTED");
				} else {
					tempList.add("");
				}
				listResearchFields.add(tempList);
			}
			model.put("listResearchFields", listResearchFields);
		}
		List<ProjectTasks> projectTasks = projectTasksService
				.loadAProjectTaskByProjectCode(project.getPROJ_Code());
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		// Get list staffs
		List<mStaff> staffList = staffService.listStaffs();
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();
		// Get list member roles
		List<ProjectParticipationRoles> memberRolesList = projectParticipationRolesService
				.getList();

		// Put data back to view
		model.put("memberRolesList", memberRolesList);
		model.put("listFaculty", listFaculty);
		model.put("staffList", staffList);
		model.put("projectTasks", projectTasks);
		model.put("projectCallsList", projectCallsList);
		model.put("projects", status);
		model.put("projectEdit", project);
		if (result.hasErrors()) {
			return "cp.editAProject";
		} else {
			// Prepare data for inserting DB
			String projectName = projectFormEdit.getProjectName();
			String projectCallCode = projectFormEdit.getProjectCallCode();
			String projectContent = projectFormEdit.getProjectContent();
			String projectMotivation = projectFormEdit.getProjectMotivation();
			String projectResult = projectFormEdit.getProjectResult();
			int budgetMaterial = (!"".equals(projectFormEdit
					.getBudgetMaterial())) ? Integer.parseInt(projectFormEdit
					.getBudgetMaterial()) : 0;
			String projectCode = projectCallCode + projectEditId;
			String startDate = projectFormEdit.getProjectStartDate();
			String endDate = projectFormEdit.getProjectEndDate();
			String facultyAdd = projectFormEdit.getFalcutyAddress();
			String projectSurvey = projectFormEdit.getProjectSurvey();
			String projectObjective = projectFormEdit.getProjectObjective();
			String currentProjectCode = projectFormEdit.getCurrentProjectCode();
			boolean bEditSumittedProject = false;
			String projectResearchFieldCode = "";

			mProjectCalls selectedProjectCall = projectCallsService
					.loadAProjectCallByCode(projectCallCode);
			if (project != null) {
				if ("OPEN_FOR_SUBMISSION".equals(selectedProjectCall
						.getPROJCALL_STATUS())) {
					try {
						// Members
						String[] projectMembers = request
								.getParameterValues("projectMembers");
						String[] projectMemberRole = request
								.getParameterValues("projectMemberRole");
						String[] projectMemberTasks = request
								.getParameterValues("projectMemberTasks");
						String[] projectMemberWorkingDays = request
								.getParameterValues("projectMemberWorkingDays");
						String[] projectMemberBudget = request
								.getParameterValues("projectMemberBudget");
						String[] projectResearchFieldCodeList = request
								.getParameterValues("projectResearchFieldCodeList");

						int totalBudget = budgetMaterial;
						for (int i = 0; i < projectMemberBudget.length; i++) {
							totalBudget += Integer
									.valueOf(projectMemberBudget[i]);
						}

						if (projectMembers.length > 0) {
							/**
							 * Uploading file
							 */
							MultipartFile paperSourceUploadFile = projectFormEdit
									.getProjectFileUpload();
							String fileName = paperSourceUploadFile
									.getOriginalFilename();
							String paperSourceUploadFileSrc = "";
							try {
								// Creating Date in java with today's date.
								/*
								 * Date currentDate = new Date();
								 * SimpleDateFormat dateformatyyyyMMdd = new
								 * SimpleDateFormat("HHmmssddMMyyyy"); String
								 * sCurrentDate =
								 * dateformatyyyyMMdd.format(currentDate);
								 */

								byte[] bytes = paperSourceUploadFile.getBytes();
								String uploadDir = "/uploads" + File.separator
										+ userCode + File.separator
										+ "projects";
								String realPathtoUploads = request
										.getServletContext().getRealPath(
												uploadDir);
								File dir = new File(realPathtoUploads);
								if (!dir.exists()) {
									dir.mkdirs();
								}

								if (!"".equals(fileName)) {
									// Set name file
									// fileName =
									// "thuyetminh-"+sCurrentDate+fileName;
									// File serverFile = new
									// File(dir.getAbsolutePath()+
									// File.separator +
									// fileName);

									// delete old file
									String oldFullFileName = establishFullFileNameForDownload(
											project.getPROJ_SourceFile(),
											userCode, request);
									File deleteFile = new File(oldFullFileName);
									if(deleteFile.exists()){
										deleteFile.delete();
									}
									
									fileName = establishFileNameStoredDataBase(fileName);
									String fullFileName = establishFullFileNameForUpload(
											fileName, userCode, request);
									File serverFile = new File(fullFileName);

									// Save data into file
									BufferedOutputStream stream = new BufferedOutputStream(
											new FileOutputStream(serverFile));
									stream.write(bytes);
									stream.close();

									/*
									 * already done above // Set name for saving
									 * into DB
									 
									if (serverFile.exists()) {
										paperSourceUploadFileSrc = fileName;
										String oldFileName = project
												.getPROJ_SourceFile();
										if (!"".equals(oldFileName)) {
											File oldFile = new File(
													dir.getAbsolutePath()
															+ File.separator
															+ oldFileName);
											if (oldFile.exists())
												oldFile.delete();
										}
									}
									*/
								} else {
									fileName = project.getPROJ_SourceFile();
								}
							} catch (IOException e) {
								System.out.println("Can not upload file");
							} catch (Exception e) {
								System.out.println(e.getStackTrace());
							}
							// Get sendInfo
							String sendIt = "";
							if (request.getParameter("sendIt") != null) {
								sendIt = request.getParameter("sendIt");
							}
							// Editing project info
							threadService.editAProject(projectEditId, userRole,
									userCode, projectCallCode, projectName,
									projectContent, projectMotivation,
									projectResult, budgetMaterial, projectCode,
									startDate, endDate, facultyAdd,
									projectSurvey, projectObjective,
									bEditSumittedProject, totalBudget,
									projectResearchFieldCode, fileName,
									projectResearchFieldCodeList, sendIt);
							// Editting tasks info
							threadService.saveMemberTasks(projectCode,
									projectMembers, projectMemberRole,
									projectMemberTasks,
									projectMemberWorkingDays,
									projectMemberBudget, currentProjectCode);
							return "redirect:" + this.baseUrl
									+ "/cp/list-projects.html";
						}
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
						System.out.println(e.getStackTrace());
						model.put("err",
								"Cần phải thêm thành viên và lĩnh vực vào đề tài.");
					}
				} else {
					model.put("err", "Xin lỗi! Đợt gọi đề tài đã bị đóng.");
				}
			} else {
				System.out.println("Can't modify this info.");
			}
			return "cp.editAProject";
		}
	}

	/**
	 * Editing a submitted project
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-submittedproject", method = RequestMethod.POST)
	public String updateASubmittedProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormEdit") ProjectsValidation projectFormEdit,
			BindingResult result, Map model, HttpSession session) {

		System.exit(-1);
		model.put("projects", status);
		if (result.hasErrors()) {
			return "cp.editAProject";
		} else {
			// Prepare data for inserting DB
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();
			String projectName = projectFormEdit.getProjectName();
			String projectCallCode = projectFormEdit.getProjectCallCode();
			String projectContent = projectFormEdit.getProjectContent();
			String projectMotivation = projectFormEdit.getProjectMotivation();
			String projectResult = projectFormEdit.getProjectResult();
			int projectBudget = projectFormEdit.getProjectBudget();
			int projectEditId = projectFormEdit.getProjectId();
			String projectCode = projectCallCode + projectEditId;
			String startDate = projectFormEdit.getProjectStartDate();
			String endDate = projectFormEdit.getProjectEndDate();
			String facultyAdd = projectFormEdit.getFalcutyAddress();
			String projectSurvey = projectFormEdit.getProjectSurvey();
			String projectObjective = projectFormEdit.getProjectObjective();
			String projectResearchFieldCode = "";
			boolean bEditSumittedProject = true;
			int projectMaterialBudget = 0;
			String[] projectResearchFieldCodeList = null;

			threadService.editAProject(projectEditId, userRole, userCode,
					projectCallCode, projectName, projectContent,
					projectMotivation, projectResult, projectMaterialBudget,
					projectCode, startDate, endDate, facultyAdd, projectSurvey,
					projectObjective, bEditSumittedProject, projectBudget,
					projectResearchFieldCode, "", projectResearchFieldCodeList,
					"");
			return "redirect:" + this.baseUrl
					+ "/cp/modify-submitted-projects.html";
		}
	}

	/**
	 * Editing a approve project
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-approveproject", method = RequestMethod.POST)
	public String updateAnApproveProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormEdit") ProjectsValidation projectFormEdit,
			BindingResult result, Map model, HttpSession session) {

		model.put("projects", status);
		if (result.hasErrors()) {
			return "cp.editAProject";
		} else {
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();
			int projectEditId = projectFormEdit.getProjectId();
			Projects projectBeingEditted = threadService
					.loadASumittedProjectByIdAndUserCode(userRole, userCode,
							projectEditId);
			// Prepare data for inserting DB
			if (projectBeingEditted != null) {
				projectBeingEditted.setPROJ_Status_Code(projectFormEdit
						.getProjectStatusCode());
				threadService.editAnApproveProject(projectBeingEditted);
				return "redirect:" + this.baseUrl + "/cp/approve-projects.html";
			}
			return "cp.editAProject";
		}
	}

	/**
	 * Editing a project comment
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-projectcomment", method = RequestMethod.POST)
	public String updateAProjectComment(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormEdit") ProjectsValidation projectFormEdit,
			BindingResult result,
			Map model,
			HttpSession session,
			@RequestParam(value = "summaryComment", required = false) String summaryComment) {
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		int projectEditId = projectFormEdit.getProjectId();

		System.out.println(name() + "::updateAProjectComments, userCode = "
				+ userCode + ", project Id = " + projectEditId
				+ ", summaryComments = " + summaryComment);

		// Projects projectBeingEditted =
		// threadService.loadASumittedProjectByIdAndUserCode(userRole, userCode,
		// projectEditId);
		Projects projectBeingEditted = threadService
				.loadProjectsById(projectEditId);
		model.put("projectEdit", projectBeingEditted);
		model.put("projects", status);
		if (result.hasErrors()) {
			System.out.println(name() + "::updateAProjectComments, userCode = "
					+ userCode + ", project Id = " + projectEditId
					+ ", result.hasErrors --> return cp.projectcomments");
			return "cp.projectcomments";
		} else {
			if (projectBeingEditted != null) {
				String isConfirmed = request.getParameter("confirmed");
				projectBeingEditted.setPROJ_Status_Code(projectFormEdit
						.getProjectStatusCode());
				if (projectBeingEditted.getPROJ_Status_Code().equals(
						"ACCEPT_REVISION"))
					projectBeingEditted.setPROJ_Locked1(0);

				// Updating status for the project
				threadService.editAnApproveProject(projectBeingEditted);

				// Adding comments
				// mCommentsOfSubmittedProjects commentsOfSubmittedProject =
				// commentsOfSubmittedProjectsService.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(userCode,
				// projectBeingEditted.getPROJ_Code());
				List<mCommentsOfSubmittedProjects> commentsOfSubmittedProjects = commentsOfSubmittedProjectsService
						.loadCommentsOfSubmittedProjectByProjectCode(projectBeingEditted
								.getPROJ_Code());

				// if(!"".equals(commentsOfSubmittedProject.getCOMPROJ_CODE())){
				if (commentsOfSubmittedProjects != null && commentsOfSubmittedProjects.size() > 0) {
					System.out
							.println(name()
									+ "::updateAProjectComment, commentsOfSubmittedProjects.sz = "
									+ commentsOfSubmittedProjects.size()
									+ ", new SummaryCommments = "
									+ summaryComment);
					mCommentsOfSubmittedProjects commentsOfSubmittedProject = commentsOfSubmittedProjects
							.get(0);
					commentsOfSubmittedProjectsService
							.editCommentsOfSubmittedProjects(
									commentsOfSubmittedProject.getCOMPROJ_ID(),
									summaryComment);
				} else {
					String currentDate = DateUtil.s_fGetCurrentDateByFormat("");
					commentsOfSubmittedProjectsService
							.saveCommentsOfSubmittedProjects(userCode,
									projectBeingEditted.getPROJ_Code(),
									summaryComment, currentDate, false);
				}
				return "redirect:" + this.baseUrl + "/cp/collect-comments.html";
			} else {
				System.out.println(name()
						+ "::updateAProjectComments, project Id = "
						+ projectEditId + ", NOT EXIST!!!!!!!");
			}
			return "cp.projectcomments";
		}
	}

	/**
	 * Editing a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-thread", method = RequestMethod.POST)
	public String updateAThead(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormEdit") mThreadValidation threadFormEdit,
			BindingResult result, Map model, HttpSession session) {

		// Get topic's category
		List<mTopicCategory> topicCategoryList = tProjectCategoryService.list();
		// Get list reportingYear
		List<mAcademicYear> threadReportingAcademicDateList = academicYearService
				.list();
		// Get list statues
		List<mProjectStatus> projectStatuses = projectStatusService.list();
		// Get list faculty
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		if (!threadFormEdit.getThreadCode().equals("")) {
			List<mProjectStaffs> listProjectStaffs = projectStaffsService
					.loadAProjectStaffByProjectCode(threadFormEdit
							.getThreadCode());
			List<mStaff> listStaffs = new ArrayList<mStaff>();
			if (listProjectStaffs != null) {
				for (mProjectStaffs projectStaff : listProjectStaffs) {
					mStaff staff = staffService
							.loadStaffByUserCode(projectStaff
									.getPROJSTAFF_Staff_Code());
					listStaffs.add(staff);
				}
			}
			model.put("listStaffs", listStaffs);
		}

		// Put data back to view
		model.put("threadReportingAcademicDate",
				threadReportingAcademicDateList);
		model.put("listFaculty", listFaculty);
		model.put("threadCategory", topicCategoryList);
		model.put("projectStatuses", projectStatuses);
		model.put("thread", status);
		if (result.hasErrors()) {
			return "cp.editAThread";
		} else {
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();

			// Prepare data for inserting DB
			String threadName = threadFormEdit.getThreadName();
			String threadCatCode = threadFormEdit.getThreadCatCode();
			String threadEndDate = threadFormEdit.getThreadEndDate();
			String threadMotivation = threadFormEdit.getThreadMotivation();
			String threadReportingDate = threadFormEdit
					.getThreadReportingAcademicDate();
			String threadResult = threadFormEdit.getThreadResult();
			String threadStartDate = threadFormEdit.getThreadStartDate();
			String threadStatus = threadFormEdit.getThreadStatus();
			int threadBudget = threadFormEdit.getThreadBudget();
			String threadCode = threadFormEdit.getThreadCode();
			String threadContent = threadFormEdit.getThreadContent();
			List<String> listStaffs = threadFormEdit.getStaffsRole();
			List<String> listStaffRoles = threadFormEdit.getRoleList();
			String joiningStaffs = threadFormEdit.getStaff();
			String threadSourceUploadFileSrc = "";
			int threadId = threadFormEdit.getThreadId();

			/**
			 * Uploading file
			 */
			MultipartFile threadSourceUploadFile = threadFormEdit
					.getThreadSourceFile();
			String fileName = threadSourceUploadFile.getOriginalFilename();
			if (fileName != "") {
				try {
					// Creating Date in java with today's date.
					Date currentDate = new Date();
					// change date into string yyyyMMdd format example
					// "20110914"
					SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat(
							"HHmmssddMMyyyy");
					String sCurrentDate = dateformatyyyyMMdd
							.format(currentDate);

					byte[] bytes = threadSourceUploadFile.getBytes();
					String path = request.getServletContext().getRealPath(
							"uploads");
					File dir = new File(path + "/threads");
					if (!dir.exists()) {
						dir.mkdirs();
					}

					// Create a file
					String currentUserName = session.getAttribute(
							"currentUserName").toString();
					fileName = currentUserName + "_" + sCurrentDate + "_"
							+ fileName;
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + fileName);
					if (serverFile != null) {
						threadSourceUploadFileSrc = dir.getAbsolutePath()
								+ File.separator + fileName;
					}
					// Save data into file
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					model.put("status", "You failed to upload " + fileName);
				}
			}

			threadService.editAThread(userRole, userCode, threadName,
					threadCatCode, threadContent, threadStartDate,
					threadEndDate, threadMotivation, threadReportingDate,
					threadResult, threadStatus, threadBudget,
					threadSourceUploadFileSrc, threadCode, threadId,
					listStaffs, listStaffRoles);
			model.put("status", "Successfully edited project");
			// return "cp.editAThread";
			return "redirect:" + this.baseUrl + "/cp/threads-listadd.html";
		}
	}

	/**
	 * 
	 * @param request
	 * @param projectFormApprove
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/approve-a-project", method = RequestMethod.POST)
	public String approveAProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormApprove") mThreadApproveValidation projectFormApprove,
			BindingResult result, Map model, HttpSession session) {

		if (!projectFormApprove.getThreadCode().equals("")) {
			List<mProjectStaffs> listProjectStaffs = projectStaffsService
					.loadAProjectStaffByProjectCode(projectFormApprove
							.getThreadCode());
			List<mStaff> listStaffs = new ArrayList<mStaff>();
			if (listProjectStaffs != null) {
				for (mProjectStaffs projectStaff : listProjectStaffs) {
					mStaff staff = staffService
							.loadStaffByUserCode(projectStaff
									.getPROJSTAFF_Staff_Code());
					listStaffs.add(staff);
				}
			}
			model.put("listStaffs", listStaffs);
		}

		// Put data back to view
		model.put("thread", status);
		if (result.hasErrors()) {
			return "cp.editAThread";
		} else {
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();

			// Prepare data for inserting DB
			String threadStatus = projectFormApprove.getThreadStatus();
			int threadId = projectFormApprove.getThreadId();

			threadService.editStatusThread(userRole, userCode, threadId,
					threadStatus);

			model.put("status", "Successfully edited project");
			// return editAThread((ModelMap)model, threadId, session);
			// return "cp.editAThread";
			return "redirect:" + this.baseUrl + "/cp/threads-approve.html";
		}
	}

	@RequestMapping(value = "/evaluate-a-project", method = RequestMethod.POST)
	public String evaluateAProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("projectFormApprove") mThreadApproveValidation projectFormApprove,
			BindingResult result, Map model, HttpSession session) {
		// return approveAProject(request, projectFormApprove, result, model,
		// session);
		if (!projectFormApprove.getThreadCode().equals("")) {
			List<mProjectStaffs> listProjectStaffs = projectStaffsService
					.loadAProjectStaffByProjectCode(projectFormApprove
							.getThreadCode());
			List<mStaff> listStaffs = new ArrayList<mStaff>();
			if (listProjectStaffs != null) {
				for (mProjectStaffs projectStaff : listProjectStaffs) {
					mStaff staff = staffService
							.loadStaffByUserCode(projectStaff
									.getPROJSTAFF_Staff_Code());
					listStaffs.add(staff);
				}
			}
			model.put("listStaffs", listStaffs);
		}

		// Put data back to view
		model.put("thread", status);
		if (result.hasErrors()) {
			return "cp.editAThread";
		} else {
			String userRole = session.getAttribute("currentUserRole")
					.toString();
			String userCode = session.getAttribute("currentUserCode")
					.toString();

			// Prepare data for inserting DB
			String threadStatus = projectFormApprove.getThreadStatus();
			int threadId = projectFormApprove.getThreadId();

			threadService.editStatusThread(userRole, userCode, threadId,
					threadStatus);

			model.put("status", "Successfully edited project");
			// return editAThread((ModelMap)model, threadId, session);
			// return "cp.editAThread";
			return "redirect:" + this.baseUrl + "/cp/threads-evaluate.html";
		}
	}

	/**
	 * Remove a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/remove-a-thread/{id}", method = RequestMethod.GET)
	public String removeAThread(ModelMap model,
			@PathVariable("id") int threadId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		model.put("threads", status);
		if (thread != null) {
			threadService.removeAThread(threadId);
			List<mThreads> threadsList = threadService.loadThreadsListByStaff(
					userRole, userCode);
			model.put("threadsList", threadsList);
			return "cp.threads";
		}
		return "cp.notFound404";
	}

	/**
	 * Remove a project
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/remove-a-project/{id}", method = RequestMethod.GET)
	public String removeAProject(HttpServletRequest request, ModelMap model,
			@PathVariable("id") int projectId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		Projects project = threadService.loadAProjectByIdAndUserCode(userRole,
				userCode, projectId);
		model.put("projects", status);
		if (project != null) {
			try {
				// Remove file
				String fileSource = project.getPROJ_SourceFile();
				if (!"".equals(fileSource)) {
					String uploadDir = "/uploads" + File.separator + userCode
							+ File.separator + "projects";
					String realPathtoUploads = request.getServletContext()
							.getRealPath(uploadDir);
					File dir = new File(realPathtoUploads);
					File oldFile = new File(dir.getAbsolutePath()
							+ File.separator + fileSource);
					if (oldFile.exists())
						oldFile.delete();
				}
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
			// Remove data from DB
			List<ProjectsProjectResearchField> projectsProjectResearchFieldList = threadService
					.loadProjectsProjectResearchFieldListByProjectCode(project
							.getPROJ_Code());
			if (projectsProjectResearchFieldList != null) {
				for (ProjectsProjectResearchField projectsProjectResearchField : projectsProjectResearchFieldList) {
					threadService
							.removeAProjectSearchField(projectsProjectResearchField);
				}
			}
			threadService.removeAProject(projectId);
			threadService.removeProjectTasks(project.getPROJ_Code());
			List<Projects> projectsList = threadService
					.loadProjectsListByStaff(userRole, userCode);
			model.put("projectsList", projectsList);
			return "cp.projectsList";
		}
		return "cp.notFound404";
	}

	/**
	 * Download a file source
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/download-thread/{id}", method = RequestMethod.GET)
	public void downloadPaper(ModelMap model, @PathVariable("id") int threadId,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		model.put("threads", status);
		mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole,
				userCode, threadId);
		if (thread != null)
			if (thread.getPROJ_SourceFile() != null) {
				ServletContext context = request.getServletContext();

				File downloadFile = new File(thread.getPROJ_SourceFile());
				if (downloadFile.exists()) {
					FileInputStream inputStream = new FileInputStream(
							downloadFile);

					String mimeType = context.getMimeType(thread
							.getPROJ_SourceFile());
					if (mimeType == null) {
						// set to binary type if MIME mapping not found
						mimeType = "application/octet-stream";
					}

					// set content attributes for the response
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());

					// set headers for the response
					String headerKey = "Content-Disposition";
					String headerValue = String.format(
							"attachment; filename=\"%s\"",
							downloadFile.getName());
					response.setHeader(headerKey, headerValue);

					// get output stream of the response
					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;

					// write bytes read from the input stream into the output
					// stream
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}

					inputStream.close();
					outStream.close();
				}
			}

	}

	@RequestMapping(value = "/projectExcell-statistics", method = RequestMethod.POST)
	public ModelAndView downloadExcelThreadsStatistics(
			@Valid @ModelAttribute("threadExcellForm") mProjectExcellStatisticsValidation projectValidExcell,
			BindingResult result, Map model, HttpSession session) {
		// List<mAcademicYear> patentReportingAcademicDateList =
		// academicYearService.list();
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();
		String projectCallCode = projectValidExcell.getProjectCallCode();

		model.put("projectCallsList", projectCallsList);
		// model.put("reportingAcademicDate", patentReportingAcademicDateList);
		// create some sample data
		if (result.hasErrors()) {
			return new ModelAndView("cp.threads");
		} else {
			/**
			 * Get list of all Projects (Topics)
			 */
			// String yearForGenerating = threadValidExcell.getThreadYear();
			// String threadCategory = threadValidExcell.getThreadCatCode();
			// String threadStatus = threadValidExcell.getThreadStatus();
			// String threadFaculty = threadValidExcell.getThreadFaculty();
			// String threadDepartment =
			// threadValidExcell.getThreadDepartment();
			// String threadStaff = threadValidExcell.getThreadStaff();
			// Get list of Threads

			List<mStaff> staffs = staffService.listStaffs();
			HashMap<String, mStaff> mCode2Staff = new HashMap<String, mStaff>();
			for (mStaff st : staffs) {
				mCode2Staff.put(st.getStaff_Code(), st);
			}

			System.out.println(name()
					+ "::downloadExcelThreadsStatistics, projectCallCode = "
					+ projectCallCode);

			// List<mThreads> threadsList =
			// threadService.loadThreadsListForReporting(threadCategory,
			// threadStatus, threadFaculty, threadDepartment, threadStaff,
			// yearForGenerating);
			List<mThreads> threadsList = threadService.listAll();

			List<List<String>> summaryThreadList = new ArrayList<>();
			if (threadsList != null) {
				for (mThreads threads : threadsList) {
					String leaderFaculty = threadService.getFacultyName(threads
							.getStaff().getStaff_Faculty_Code());
					String leaderDepartment = threadService
							.getDepartmentName(threads.getStaff()
									.getStaff_Department_Code());
					mStaff staff = mCode2Staff.get(threads.getPROJ_User_Code());
					List<ProjectTasks> tasks = projectTasksService
							.loadAProjectTaskByProjectCode(threads
									.getPROJ_Code());

					int totalBudget = threads.getPROJ_BudgetMaterial();

					HashSet<String> members = new HashSet<String>();
					for (ProjectTasks pTask : tasks) {
						String mCode = pTask.getPRJTSK_StaffCode();
						totalBudget += pTask.getPRJTSK_Cost();
						if (!mCode.equals(staff.getStaff_User_Code()))
							members.add(mCode);
					}

					// String sFacultyname = threadService.
					List<String> summaryThread = new ArrayList<>();
					// summaryThread.add(threads.getPROJ_User_Code());
					String memberList = staff.getStaff_Name();
					if (members.size() > 0) {
						memberList += " (Thành viên: ";
						for (String st : members)
							memberList += st + ", ";
						memberList += ")";
					}
					summaryThread.add(memberList);
					summaryThread.add(leaderFaculty);
					summaryThread.add(leaderDepartment);
					summaryThread.add(threads.getPROJ_Name());
					// summaryThread.add(Integer.toString(threads.getPROJ_TotalBudget()));
					summaryThread.add(Integer.toString(totalBudget));
					summaryThread.add(threads.getPROJ_Content());
					summaryThread.add(threads.getPROJ_Result());
					summaryThreadList.add(summaryThread);
				}
				/**
				 * Get list of all Patents
				 */

			}

			HashSet<String> listMembers = new HashSet<>();
			List<ProjectTasks> listProjectTasks = projectTasksService.getList();
			if (listProjectTasks.size() > 0) {
				for (ProjectTasks projectTask : listProjectTasks) {
					listMembers.add(projectTask.getStaffProject()
							.getStaff_Name());
				}
			}

			model.put("listMembers", listMembers);
			model.put("summaryThreadList", summaryThreadList);
			model.put("yearOfPaper", "1111");
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelThreadsView");
		}
	}

	/**
	 * Handle request to download an Excel 97-2003 document
	 */

	@RequestMapping(value = "/threadsExcell", method = RequestMethod.POST)
	public ModelAndView downloadExcelThreads(
			@Valid @ModelAttribute("threadExcellForm") mThreadExcellValidation threadValidExcell,
			BindingResult result, Map model, HttpSession session) {
		List<mAcademicYear> patentReportingAcademicDateList = academicYearService
				.list();
		List<mProjectCalls> projectCallsList = projectCallsService
				.loadProjectCallsList();

		model.put("projectCallsList", projectCallsList);
		model.put("reportingAcademicDate", patentReportingAcademicDateList);
		// create some sample data
		if (result.hasErrors()) {
			return new ModelAndView("cp.threads");
		} else {
			/**
			 * Get list of all Projects (Topics)
			 */
			String yearForGenerating = threadValidExcell.getThreadYear();
			String threadCategory = threadValidExcell.getThreadCatCode();
			String threadStatus = threadValidExcell.getThreadStatus();
			String threadFaculty = threadValidExcell.getThreadFaculty();
			String threadDepartment = threadValidExcell.getThreadDepartment();
			String threadStaff = threadValidExcell.getThreadStaff();
			// Get list of Threads

			List<mStaff> staffs = staffService.listStaffs();
			HashMap<String, mStaff> mCode2Staff = new HashMap<String, mStaff>();
			for (mStaff st : staffs) {
				mCode2Staff.put(st.getStaff_Code(), st);
			}

			System.out.println(name()
					+ "::downloadExcelThread, threadFaculty = " + threadFaculty
					+ ", threadStaff = " + threadStaff);
			List<mThreads> threadsList = threadService
					.loadThreadsListForReporting(threadCategory, threadStatus,
							threadFaculty, threadDepartment, threadStaff,
							yearForGenerating);

			List<List<String>> summaryThreadList = new ArrayList<>();
			if (threadsList != null) {
				for (mThreads threads : threadsList) {
					String leaderFaculty = threadService.getFacultyName(threads
							.getStaff().getStaff_Faculty_Code());
					String leaderDepartment = threadService
							.getDepartmentName(threads.getStaff()
									.getStaff_Department_Code());
					mStaff staff = mCode2Staff.get(threads.getPROJ_User_Code());
					List<ProjectTasks> tasks = projectTasksService
							.loadAProjectTaskByProjectCode(threads
									.getPROJ_Code());

					int totalBudget = threads.getPROJ_BudgetMaterial();

					HashSet<String> members = new HashSet<String>();
					for (ProjectTasks pTask : tasks) {
						String mCode = pTask.getPRJTSK_StaffCode();
						totalBudget += pTask.getPRJTSK_Cost();
						if (!mCode.equals(staff.getStaff_User_Code()))
							members.add(mCode);
					}

					// String sFacultyname = threadService.
					List<String> summaryThread = new ArrayList<>();
					// summaryThread.add(threads.getPROJ_User_Code());
					String memberList = staff.getStaff_Name();
					if (members.size() > 0) {
						memberList += " (Thành viên: ";
						for (String st : members)
							memberList += st + ", ";
						memberList += ")";
					}
					summaryThread.add(memberList);
					summaryThread.add(leaderFaculty);
					summaryThread.add(leaderDepartment);
					summaryThread.add(threads.getPROJ_Name());
					// summaryThread.add(Integer.toString(threads.getPROJ_TotalBudget()));
					summaryThread.add(Integer.toString(totalBudget));
					summaryThread.add(threads.getPROJ_Content());
					summaryThread.add(threads.getPROJ_Result());
					summaryThreadList.add(summaryThread);
				}
				/**
				 * Get list of all Patents
				 */

			}

			HashSet<String> listMembers = new HashSet<>();
			List<ProjectTasks> listProjectTasks = projectTasksService.getList();
			if (listProjectTasks.size() > 0) {
				for (ProjectTasks projectTask : listProjectTasks) {
					listMembers.add(projectTask.getStaffProject()
							.getStaff_Name());
				}
			}

			model.put("listMembers", listMembers);
			model.put("summaryThreadList", summaryThreadList);
			model.put("yearOfPaper", yearForGenerating);
			// return a view which will be resolved by an excel view resolver
			return new ModelAndView("excelThreadsView");
		}
	}
}
