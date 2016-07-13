/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.researchmanagement.controller.cp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchmanagement.service.mJuryResearchProjectService;
//For project call
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.model.mJuryResearchProject;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;

//For jury role of submitted project
import vn.webapp.modules.researchmanagement.service.mJuryRoleOfSubmittedProjectsService;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;

//For jury of announced project call
import vn.webapp.modules.researchmanagement.service.mJuryOfAnnouncedProjectCallService;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.usermanagement.controller.cp.mUserController;
import vn.webapp.modules.usermanagement.model.mFaculty;
//For staff
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;

//Validation
import vn.webapp.modules.researchmanagement.validation.mJuryOfAnnouncedProjectCallValidation;
import vn.webapp.modules.researchmanagement.validation.mJuryResearchProjectValidation;
import vn.webapp.modules.researchmanagement.validation.mProjectCallsValidation;

@Controller("cpmJuryOfAnnouncedProjectCall")
@RequestMapping(value = { "/cp" })
public class mJuryOfAnnouncedProjectCallController extends BaseWeb {

	@Autowired
	private mJuryResearchProjectService juryResearchProjectService;

	@Autowired
	private mStaffService staffService;

	@Autowired
	private mFacultyService facultyService;

	@Autowired
	private mProjectCallsService projectCallsService;

	@Autowired
	private mJuryRoleOfSubmittedProjectsService juryRoleOfSubmittedProjectsService;

	@Autowired
	private mJuryOfAnnouncedProjectCallService juryOfAnnouncedProjectCallService;

	static final String status = "active";

	/**
	 * Show list all threads
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit-a-jury-research-project.html", method = RequestMethod.POST)
	public String updateAJuryResearchProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("juryResearchProjectFormEdit") mJuryResearchProjectValidation juryResearchProjectValid,
			BindingResult result, Map model, HttpSession session) {
		String userCode = (String) session.getAttribute("currentUserCode");
		System.out.println(name() + "::updateAJuryResearchProject, userCode = "
				+ userCode + ", juryID = "
				+ juryResearchProjectValid.getJuryResearchProjectID());
		int juryID = juryResearchProjectValid.getJuryResearchProjectID();
		mJuryResearchProject jury = juryResearchProjectService
				.listAJuryByID(juryID);

		if (jury != null) {
			jury.setJURPRJ_Name(juryResearchProjectValid
					.getJuryResearchProjectName());
			jury.setJURPRJ_PROJCall_Code(juryResearchProjectValid
					.getProjectCallCode());
			juryResearchProjectService.editAJury(jury);

			List<mJuryResearchProject> juries = juryResearchProjectService
					.listAllJuries();

			// Put data back to view
			model.put("juries", juries);
			return "cp.juryOfResearchProjectManagement";
		} else {
			return "cp.notFound404";
		}
	}

	@RequestMapping("/jury-research-project-detail/{id}")
	public String juryResearchProjectDetail(ModelMap model,
			@PathVariable("id") int id, HttpSession session) {
		String userCode = (String) session.getAttribute("currentUserCode");
		
		mJuryResearchProject jury = juryResearchProjectService
				.listAJuryByID(id);

		List<mProjectCalls> projectCalls = projectCallsService
				.loadProjectCallsList();
		System.out.println(name() + "::juryResearchProjectDetail, userCode = "
				+ userCode + ", juryResearchProjectId = " + id + ", projectCalls.sz = " + projectCalls.size());
		for(mProjectCalls pc: projectCalls){
			System.out.println(pc.getPROJCALL_NAME() + " --- code " + pc.getPROJCALL_CODE());
		}
		
		// Put data back to view
		//model.put("projectcalls", status);
		if (jury != null) {
			model.put("juryResearchProjectFormEdit",
					new mJuryResearchProjectValidation());
			model.put("juryResearchProjectId", id);
			model.put("jury", jury);
			model.put("projectCalls", projectCalls);
			return "cp.editAJuryResearchProject";
		}
		return "cp.notFound404";
	}

	@RequestMapping(value = "/remove-a-jury-research-project/{id}", method = RequestMethod.GET)
	public String removeAJuryResearchProject(ModelMap model,
			@PathVariable("id") int id, HttpSession session) {
		String userCode = (String) session.getAttribute("currentUserCode");
		System.out.println(name() + "::removeAJuryResearchProject, userCode = "
				+ userCode);
		mJuryResearchProject jury = juryResearchProjectService
				.listAJuryByID(id);
		// Return value to view
		model.put("projectcalls", status);
		if (jury != null) {
			juryResearchProjectService.removeAJuryResearchProject(jury);
			// Get topic's category
			List<mJuryResearchProject> juries = juryResearchProjectService
					.listAllJuries();

			// Put data back to view
			model.put("juries", juries);
			return "cp.juryOfResearchProjectManagement";
		}
		return "cp.notFound404";
	}

	@RequestMapping(value = "/save-a-jury-research-project.html", method = RequestMethod.POST)
	public String saveAJuryResearchProject(
			HttpServletRequest request,
			@Valid @ModelAttribute("juryResearchProjectFormAdd") mJuryResearchProjectValidation juryProjectValid,
			BindingResult result, Map model, HttpSession session) {

		String userCode = (String) session.getAttribute("currentUserCode");

		String juryName = juryProjectValid.getJuryResearchProjectName();
		String projectCallCode = juryProjectValid.getProjectCallCode();
		System.out.println(name() + "::saveAJuryResearchProject, userCode = "
				+ userCode + ", juryName = " + juryName
				+ ", projectCallCode = " + projectCallCode);
		mJuryResearchProject jury = new mJuryResearchProject();
		jury.setJURPRJ_Name(juryName);
		jury.setJURPRJ_PROJCall_Code(projectCallCode);
		jury.setJURPRJ_Active(1);
		jury.setJURPRJ_UserCode(userCode);

		int id = juryResearchProjectService.saveAJury(jury);
		jury.setJURPRJ_Code(projectCallCode + "-" + userCode + "-" + id);

		juryResearchProjectService.editAJury(jury);

		List<mJuryResearchProject> juries = juryResearchProjectService
				.listAllJuries();
		model.put("juries", juries);
		return "cp.juryOfResearchProjectManagement";
	}

	@RequestMapping(value = "/add-a-jury-research-project.html", method = RequestMethod.GET)
	public String addAJuryResearchProject(ModelMap model, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		List<mProjectCalls> projectCalls = projectCallsService
				.loadProjectCallsList();

		model.put("projectCalls", projectCalls);
		model.put("juryResearchProjectFormAdd",
				new mJuryResearchProjectValidation());
		return "cp.addAJuryOfResearchProject";
	}

	@RequestMapping(value = "/jury-of-research-project-management.html", method = RequestMethod.GET)
	public String juryOfReserchProjectManagement(ModelMap model,
			HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<mStaff> staffs = staffService.listStaffs();
		List<mProjectCalls> projectCalls = projectCallsService.loadProjectCallsList();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		HashMap<String, String> mProjectCallCode2Name = new HashMap<String, String>();
		for(mStaff st: staffs){
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		}
		for(mProjectCalls pc: projectCalls){
			mProjectCallCode2Name.put(pc.getPROJCALL_CODE(), pc.getPROJCALL_NAME());
		}
		
		List<mJuryResearchProject> juries = juryResearchProjectService
				.listAllJuries();
		for(mJuryResearchProject jury: juries){
			jury.setJURPRJ_PROJCall_Code(mProjectCallCode2Name.get(jury.getJURPRJ_PROJCall_Code()));
			jury.setJURPRJ_UserCode(mStaffCode2Name.get(jury.getJURPRJ_UserCode()));
		}
		model.put("juries", juries);

		return "cp.juryOfResearchProjectManagement";
	}

	@RequestMapping(value = "/list-jury-of-announced-project-call", method = RequestMethod.GET)
	public String jurySubmittedProjectList(ModelMap model, HttpSession session) {

		// Get project call list
		List<mProjectCalls> projectCallList = projectCallsService
				.loadProjectCallsList();

		// Get staff list
		List<mStaff> staffList = staffService.listStaffs();

		// Get jury role of submitted projects
		List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjecsList = juryRoleOfSubmittedProjectsService
				.loadAllJuryRoleOfSubmittedProjects();

		model.put("projectCallList", projectCallList);
		model.put("staffList", staffList);
		model.put("juryRoleOfSubmittedProjecsList",
				juryRoleOfSubmittedProjecsList);

		return "cp.listJuryOfAnnouncedProjectCall";
	}

	public String name() {
		return "mJuryOfAnnouncedProjectCallController";
	}

	@ResponseBody
	@RequestMapping(value = "/getJuryOfProjectCall", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getJuryOfAProjectCall(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "projectCallCode", defaultValue = "0") String projectCallCode) {
		String html = "";
		System.out.println(name()
				+ "::getJuryOfAProjectCall, projectCallCode = "
				+ projectCallCode);

		List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService
				.loadListJuryOfAnnouncedProjectCallByProjectCallCode(projectCallCode);

		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for (mStaff st : staffs)
			mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());

		html += "<thead>" + "<tr>"
				+ "<th title=\"Name of project call \" > Đợt gọi đề tài </th>"
				+ "<th title=\"Name of staff \">Thành viên </th>"
				+ "<th title=\"Name of role \">Vai trò</th>" + "<th></th>"
				+ "</tr>" + "</thead>";

		html += "<tbody>";
		for (mJuryOfAnnouncedProjectCall jury : juryOfAnnouncedProjectCallList) {
			html += "<tr class=\"gradeX\">";

			html += "<td><c:out value=\"" + projectCallCode + "\"/></td>";
			html += "<td><c:out value=\"" + jury.getJUSUPRJ_STAFFCODE()
					+ "\"/></td>";
			html += "<td><c:out value=\"" + jury.getJUPSURJ_ROLECODE()
					+ "\"/></td>";

			// html += "<td><c:out value=" + projectCallCode + "/></td>";
			// html += "<td><c:out value=" + jury.getJUSUPRJ_STAFFCODE() +
			// "/></td>";
			// html += "<td><c:out value=" + jury.getJUPSURJ_ROLECODE() +
			// "/></td>";

			html += "<td class=\"center\">"
					+ "<button type=\"button\" id=\"removeJuryOfAnnouncedProjectCall\" onclick=\"v_fRemoveJuryOfAnnouncedProjectCall("
					+ jury.getJUSUPRJ_ID()
					+ ");\" class=\"btn btn-danger btn-xs\" title=\"Remove\">Xoá</button>"
					+ "<br>" + "</td>";
			html += "</tr>";
		}
		html += "</tbody>";

		System.out.println(name() + "::getJuryOfAProjectCall, return html = "
				+ html);
		return html;
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add-jury-submitted-projects", method = RequestMethod.GET)
	public String addJuryOfSubmittedProjects(ModelMap model, HttpSession session) {
		String userCode = (String)session.getAttribute("currentUserCode");
		String userRole = (String)session.getAttribute("currentUserRole");
		System.out.println(name() + "::addJuryOfSubmittedProjects, userCode = " + userCode + ", userRole = " + userRole);
		
		// Get project call list
		List<mProjectCalls> projectCallList = projectCallsService
				.loadProjectCallsList();
		
		List<mJuryResearchProject> juries = null;
		if(userRole.equals(mUserController.SUPER_ADMIN) || userRole.equals(mUserController.ROLE_ADMIN))
			juries = juryResearchProjectService.listAllJuries();
		else
			juries = juryResearchProjectService.listAllJuriesByUserCode(userCode);
		
		model.put("projectCallList", projectCallList);
		model.put("juries", juries);
		
		return "cp.addJuryOfAnnouncedProjectCall";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/list-jury-submitted-projects", method = RequestMethod.POST)
	public String showListJuryOfSubmittedProjects(HttpServletRequest request,
			ModelMap model, HttpSession session) {

		// Get data
		
		String juryResearchProjectCode = request.getParameter("JURPRJ_Code");
		
		mJuryResearchProject jury = juryResearchProjectService.listAJuryByCode(juryResearchProjectCode);
		
		String projectCallCode = jury.getJURPRJ_PROJCall_Code();//request.getParameter("JUSUPRJ_PRJCALLCODE");
		
		mProjectCalls projectCall = projectCallsService.loadAProjectCallByCode(projectCallCode);
		
		System.out.println(name() + "::showListJuryOfSubmittedProjects, projectCallCode = " + projectCallCode + 
				", juryResearchProjectCode = " + juryResearchProjectCode);
		
		
		// Get project call list
		List<mProjectCalls> projectCallList = projectCallsService
				.loadProjectCallsList();
		HashMap<String, String> projectCallHashMap = new HashMap<String, String>();
		for (int i = 0; i < projectCallList.size(); i++) {
			projectCallHashMap.put(projectCallList.get(i).getPROJCALL_CODE(),
					projectCallList.get(i).getPROJCALL_NAME());
		}
		
		
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		// Get staff list
		List<mStaff> staffList = staffService.listStaffs();

		HashMap<String, String> staffHashMap = new HashMap<String, String>();
		for (int i = 0; i < staffList.size(); i++) {
			staffHashMap.put(staffList.get(i).getStaff_Code(), staffList.get(i)
					.getStaff_Name());
		}

		// Get jury role of submitted projects
		List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjecsList = juryRoleOfSubmittedProjectsService
				.loadAllJuryRoleOfSubmittedProjects();

		HashMap<String, String> roleHashMap = new HashMap<String, String>();
		for (int i = 0; i < juryRoleOfSubmittedProjecsList.size(); i++) {
			roleHashMap.put(juryRoleOfSubmittedProjecsList.get(i)
					.getJUPRJROL_CODE(), juryRoleOfSubmittedProjecsList.get(i)
					.getJUPRJROL_NAME());
		}

		List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList = null;
		// Get jury of announced project call
		if (!"".equals(projectCallCode)) {
			//juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByProjectCallCode(projectCallCode);
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByJuryCode(juryResearchProjectCode);
			
		} else {
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService
					.loadAllJuryOfAnnouncedProjectCall();
		}

		for (int i = 0; i < juryOfAnnouncedProjectCallList.size(); i++) {
			juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_PRJCALLCODE(
					projectCallHashMap.get(juryOfAnnouncedProjectCallList
							.get(i).getJUSUPRJ_PRJCALLCODE()));
			juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_STAFFCODE(
					staffHashMap.get(juryOfAnnouncedProjectCallList.get(i)
							.getJUSUPRJ_STAFFCODE()));
			juryOfAnnouncedProjectCallList.get(i).setJUPSURJ_ROLECODE(
					roleHashMap.get(juryOfAnnouncedProjectCallList.get(i)
							.getJUPSURJ_ROLECODE()));
		}

		model.put("jury", jury);
		model.put("projectCall", projectCall);
		model.put("projectCallCode", projectCallCode);
		model.put("juryCode", juryResearchProjectCode);
		model.put("staffList", staffList);
		model.put("listFaculty", listFaculty);
		model.put("juryRoleOfSubmittedProjecsList",
				juryRoleOfSubmittedProjecsList);
		model.put("juryOfAnnouncedProjectCallFormAdd",
				new mJuryOfAnnouncedProjectCallValidation());
		model.put("juryOfAnnouncedProjectCallList",
				juryOfAnnouncedProjectCallList);

		return "cp.listJuryOfAnnouncedProjectCall";
	}

	/**
	 * 
	 * @param request
	 * @param juryOfAnnouncedProjectCallValid
	 * @param result
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save-jury-of-announced-project-call", method = RequestMethod.POST)
	public String saveJuryOfAnnouncedProjectCall(
			HttpServletRequest request,
			@Valid @ModelAttribute("juryOfAnnouncedProjectCallFormAdd") mJuryOfAnnouncedProjectCallValidation juryOfAnnouncedProjectCallValid,
			BindingResult result, ModelMap model, HttpSession session) {

		String userCode = (String)session.getAttribute("currentUSerCode");
		
		if (result.hasErrors()) {
			return "cp.listJuryOfAnnouncedProjectCall";
		} else {
			// Prepare data for inserting DB
			String JUSUPRJ_STAFFCODE = juryOfAnnouncedProjectCallValid
					.getJUSUPRJ_STAFFCODE();
			String JUPSURJ_ROLECODE = juryOfAnnouncedProjectCallValid
					.getJUPSURJ_ROLECODE();
			// Get data
			String JUSUPRJ_PRJCALLCODE = request
					.getParameter("JUSUPRJ_PRJCALLCODE");
			
			String JUSUPRJ_JURYRESEARCHPROJECTCODE = request
					.getParameter("JUSUPRJ_JURYRESEARCHPROJECTCODE");
			
			System.out.println(name() + "::saveJuryOfAnnouncedProjectCall, juryCode = " + JUSUPRJ_JURYRESEARCHPROJECTCODE);
			
			mJuryResearchProject jury = juryResearchProjectService.listAJuryByCode(JUSUPRJ_JURYRESEARCHPROJECTCODE);
			mProjectCalls projectCall = projectCallsService.loadAProjectCallByCode(jury.getJURPRJ_PROJCall_Code());
			
			System.out.println(name() + "::saveJuryOfAnnouncedProjectCall, juryCode = " + JUSUPRJ_JURYRESEARCHPROJECTCODE + ", userCode = " + userCode);
			mJuryOfAnnouncedProjectCall oJuryProject = juryOfAnnouncedProjectCallService
					.loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(
							JUSUPRJ_PRJCALLCODE, JUSUPRJ_STAFFCODE);
			if (oJuryProject != null) {
				model.put("err", "Thành viên này đã tồn tại.");
			} else {
				// Save item
				int iSaveResult = juryOfAnnouncedProjectCallService
						.saveJuryOfAnnouncedProjectCall(JUSUPRJ_STAFFCODE,
								JUSUPRJ_PRJCALLCODE, JUPSURJ_ROLECODE, JUSUPRJ_JURYRESEARCHPROJECTCODE);

				if (iSaveResult > 0) {
					model.put("code", 1);
					model.put(
							"success",
							"Staff with code "
									+ JUSUPRJ_STAFFCODE
									+ " has become a member of examinater for project call of code "
									+ JUSUPRJ_PRJCALLCODE
									+ " as the role of code "
									+ JUPSURJ_ROLECODE);
				}
			}

			// PREPARING DATA FOR SHOWING
			// Get project call list
			List<mProjectCalls> projectCallList = projectCallsService
					.loadProjectCallsList();

			HashMap<String, String> projectCallHashMap = new HashMap<String, String>();
			for (int i = 0; i < projectCallList.size(); i++) {
				projectCallHashMap.put(projectCallList.get(i)
						.getPROJCALL_CODE(), projectCallList.get(i)
						.getPROJCALL_NAME());
			}

			// Get staff list
			List<mStaff> staffList = staffService.listStaffs();

			HashMap<String, String> staffHashMap = new HashMap<String, String>();
			for (int i = 0; i < staffList.size(); i++) {
				staffHashMap.put(staffList.get(i).getStaff_Code(), staffList
						.get(i).getStaff_Name());
			}

			// Get jury role of submitted projects
			List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjecsList = juryRoleOfSubmittedProjectsService
					.loadAllJuryRoleOfSubmittedProjects();

			HashMap<String, String> roleHashMap = new HashMap<String, String>();
			for (int i = 0; i < juryRoleOfSubmittedProjecsList.size(); i++) {
				roleHashMap.put(juryRoleOfSubmittedProjecsList.get(i)
						.getJUPRJROL_CODE(), juryRoleOfSubmittedProjecsList
						.get(i).getJUPRJROL_NAME());
			}

			// Get jury of announced project call
			List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList;
			if (!"".equals(JUSUPRJ_PRJCALLCODE)) {
				//juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByProjectCallCode(JUSUPRJ_PRJCALLCODE);
				juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByJuryCode(jury.getJURPRJ_Code());
				
			} else {
				juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService
						.loadAllJuryOfAnnouncedProjectCall();
			}

			for (int i = 0; i < juryOfAnnouncedProjectCallList.size(); i++) {
				juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_PRJCALLCODE(
						projectCallHashMap.get(juryOfAnnouncedProjectCallList
								.get(i).getJUSUPRJ_PRJCALLCODE()));
				juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_STAFFCODE(
						staffHashMap.get(juryOfAnnouncedProjectCallList.get(i)
								.getJUSUPRJ_STAFFCODE()));
				juryOfAnnouncedProjectCallList.get(i).setJUPSURJ_ROLECODE(
						roleHashMap.get(juryOfAnnouncedProjectCallList.get(i)
								.getJUPSURJ_ROLECODE()));
			}

			List<mFaculty> listFaculty = facultyService.loadFacultyList();
			model.put("projectCallCode", JUSUPRJ_PRJCALLCODE);
			model.put("staffList", staffList);
			model.put("listFaculty", listFaculty);
			model.put("juryRoleOfSubmittedProjecsList",
					juryRoleOfSubmittedProjecsList);
			model.put("juryOfAnnouncedProjectCallFormAdd",
					new mJuryOfAnnouncedProjectCallValidation());
			model.put("juryOfAnnouncedProjectCallList",
					juryOfAnnouncedProjectCallList);

			model.put("jury", jury);
			model.put("projectCall", projectCall);
			
			return "cp.listJuryOfAnnouncedProjectCall";
		}
	}

	/**
	 * Remove a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete-jury-of-announced-project-call/{id}", method = RequestMethod.GET)
	public String removeAProjectCall(ModelMap model,
			@PathVariable("id") int juryOfAnnouncedProjectCallId,
			HttpSession session) {

		mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall = juryOfAnnouncedProjectCallService
				.loadAJuryOfAnnouncedProjectCallById(juryOfAnnouncedProjectCallId);

		mJuryResearchProject jury = juryResearchProjectService.listAJuryByCode(juryOfAnnouncedProjectCall.getJUSUPRJ_JURYRESEARCHPROJECTCODE());
		mProjectCalls projectCall = projectCallsService.loadAProjectCallByCode(juryOfAnnouncedProjectCall.getJUSUPRJ_PRJCALLCODE());
		
		
		
		String projectCallCode = "";
		if (juryOfAnnouncedProjectCall != null) {
			projectCallCode = juryOfAnnouncedProjectCall
					.getJUSUPRJ_PRJCALLCODE();
			// Delete the selected item
			juryOfAnnouncedProjectCallService
					.deleteJuryOfAnnouncedProjectCall(juryOfAnnouncedProjectCallId);
		}

		// PREPARING DATA FOR SHOWING
		// Get project call list
		List<mProjectCalls> projectCallList = projectCallsService
				.loadProjectCallsList();

		HashMap<String, String> projectCallHashMap = new HashMap<String, String>();
		for (int i = 0; i < projectCallList.size(); i++) {
			projectCallHashMap.put(projectCallList.get(i).getPROJCALL_CODE(),
					projectCallList.get(i).getPROJCALL_NAME());
		}

		// Get staff list
		List<mStaff> staffList = staffService.listStaffs();

		HashMap<String, String> staffHashMap = new HashMap<String, String>();
		for (int i = 0; i < staffList.size(); i++) {
			staffHashMap.put(staffList.get(i).getStaff_Code(), staffList.get(i)
					.getStaff_Name());
		}

		// Get jury role of submitted projects
		List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjecsList = juryRoleOfSubmittedProjectsService
				.loadAllJuryRoleOfSubmittedProjects();

		HashMap<String, String> roleHashMap = new HashMap<String, String>();
		for (int i = 0; i < juryRoleOfSubmittedProjecsList.size(); i++) {
			roleHashMap.put(juryRoleOfSubmittedProjecsList.get(i)
					.getJUPRJROL_CODE(), juryRoleOfSubmittedProjecsList.get(i)
					.getJUPRJROL_NAME());
		}

		// Get jury of announced project call
		List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList;
		if (!"".equals(projectCallCode)) {
			//juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByProjectCallCode(projectCallCode);
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByJuryCode(jury.getJURPRJ_Code());
			
			
		} else {
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService
					.loadAllJuryOfAnnouncedProjectCall();
		}

		for (int i = 0; i < juryOfAnnouncedProjectCallList.size(); i++) {
			juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_PRJCALLCODE(
					projectCallHashMap.get(juryOfAnnouncedProjectCallList
							.get(i).getJUSUPRJ_PRJCALLCODE()));
			juryOfAnnouncedProjectCallList.get(i).setJUSUPRJ_STAFFCODE(
					staffHashMap.get(juryOfAnnouncedProjectCallList.get(i)
							.getJUSUPRJ_STAFFCODE()));
			juryOfAnnouncedProjectCallList.get(i).setJUPSURJ_ROLECODE(
					roleHashMap.get(juryOfAnnouncedProjectCallList.get(i)
							.getJUPSURJ_ROLECODE()));
		}
		List<mFaculty> listFaculty = facultyService.loadFacultyList();

		model.put("projectCallCode",
				(!"".equals(projectCallCode)) ? projectCallCode
						: projectCallList.get(0).getPROJCALL_CODE());
		model.put("staffList", staffList);
		model.put("listFaculty", listFaculty);
		model.put("juryRoleOfSubmittedProjecsList",
				juryRoleOfSubmittedProjecsList);
		model.put("juryOfAnnouncedProjectCallFormAdd",
				new mJuryOfAnnouncedProjectCallValidation());
		model.put("juryOfAnnouncedProjectCallList",
				juryOfAnnouncedProjectCallList);

		model.put("jury", jury);
		model.put("projectCall", projectCall);
		
		return "cp.listJuryOfAnnouncedProjectCall";
	}

}
