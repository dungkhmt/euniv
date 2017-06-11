/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mJournal;
import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperCategoryHourBudgetService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;
import vn.webapp.modules.researchmanagement.model.ProjectResearchField;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.service.ProjectParticipationRolesService;
import vn.webapp.modules.researchmanagement.service.ProjectResearchFieldService;
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mEditFunctions;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mFunction;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;

public class BaseWeb {

	@Autowired
	private HttpServletRequest request;
	protected String baseUrl;
	protected String assetsUrl;
	protected static String sUserCode;
	protected static String sUserName;
	protected static String sUserRole;
	protected static String facultyCode;
	public static List<mFunction> mFuncsPermissionList = null;
	public static List<mFunction> mFuncsChildrenPermissionList = null;
	public static List<mFunction> mFuncsParentsPermissionList = null;

	public static List<mStaff> glb_staffs = null;
	public static HashMap<String, mStaff> glb_mCode2Staff = null;

	public static List<mFaculty> glb_faculties = null;
	public static HashMap<String, mFaculty> glb_mCode2Faculty = null;

	public static List<mDepartment> glb_departments = null;
	public static HashMap<String, mDepartment> glb_mCode2Department = null;

	public static List<mProjectStatus> glb_projectStatus = null;
	public static HashMap<String, mProjectStatus> glb_mCode2ProjectStatus = null;
	
	public static List<mProjectCalls> glb_projectCalls = null;
	public static HashMap<String, mProjectCalls> glb_mCode2ProjectCall = null;
	
	public static List<mTopicCategory> glb_projectCategories = null;
	public static HashMap<String, mTopicCategory> glb_mCode2ProjectCategory = null;
	
	public static List<ProjectResearchField> glb_projectResearchFields = null;
	public static HashMap<String, ProjectResearchField> glb_mCode2ProjectResearchField = null;
	
	public static List<ProjectParticipationRoles> glb_projectParticipationRoles = null;
	public static HashMap<String, ProjectParticipationRoles> glb_mCode2ProjectParticipationRole = null;
	

	public static List<mAcademicYear> glb_academicYear = null;
	public static HashMap<String, mAcademicYear> glb_mCode2AcademicYear = null;
	
	public static List<mPaperCategory> glb_paperCategories = null;
	
	public static List<mJournal> glb_journalList = null;
	
	public static List<mPapersCategoryHourBudget> glb_papersCategoryHourBudget = null;
	   
	   
	public static final String PROJECT_ROOT_DIR = "C:/euniv-deploy/";

    @Autowired
    private mAcademicYearService academicYearService;

	@Autowired
	private mFuncsPermissionService funcsPermissionService;

	@Autowired
	private mStaffService staffService;

	@Autowired
	private mFacultyService facultyService;

	@Autowired
	private mDepartmentService departmentService;

	@Autowired
	private mProjectStatusService projectStatusService;
	
	@Autowired
	private mProjectCallsService projectCallService;
	
	@Autowired
	private tProjectCategoryService projectCategoryService;
	
	@Autowired
	private ProjectResearchFieldService projectResearchFieldService;
	
	@Autowired
	private ProjectParticipationRolesService projectParticipationRolesService;

    @Autowired
    private mPaperCategoryService paperCategoryService;
    
    @Autowired
    private mJournalService journalService;

    @Autowired
    private mPaperCategoryHourBudgetService paperCategoryHourBudgetService;

    
	public String name(){
		return "BaseWeb";
	}
	
	public void loadPaperCategoryHourBudget(){
		System.out.println(name() + "::loadPaperCategoryHourBudget");
		glb_papersCategoryHourBudget = paperCategoryHourBudgetService.loadPaperCategoryHourBudgets();
	}
	public void loadJournalList(){
		System.out.println(name() + "::loadJournalList");
		glb_journalList = journalService.list();
	}
	public void loadPaperCategories(){
		System.out.println(name() + "::loadPaperCategories");
		glb_paperCategories = paperCategoryService.list();
	}
	
	public void loadAcademicYears(){
		System.out.println(name() + "::loadAcademicYear");
		glb_academicYear = academicYearService.list();
		glb_mCode2AcademicYear = new HashMap<String, mAcademicYear>();
		for(mAcademicYear y: glb_academicYear){
			glb_mCode2AcademicYear.put(y.getACAYEAR_Code(), y);
		}
	}
	public void loadProjectParticipationRoles(){
		System.out.println(name() + "::loadProjectParticipationRoles");
		glb_projectParticipationRoles = projectParticipationRolesService.getList();
		glb_mCode2ProjectParticipationRole = new HashMap<String, ProjectParticipationRoles>();
		for(ProjectParticipationRoles ppr: glb_projectParticipationRoles){
			glb_mCode2ProjectParticipationRole.put(ppr.getPROJPARTIROLE_Code(), ppr);
		}
	}
	public void loadProjectResearchFields(){
		System.out.println(name() + "::loadProjectResearchFields");
		glb_projectResearchFields = projectResearchFieldService.list();
		glb_mCode2ProjectResearchField = new HashMap<String, ProjectResearchField>();
		for(ProjectResearchField prf: glb_projectResearchFields){
			glb_mCode2ProjectResearchField.put(prf.getPRJRSHF_Code(), prf);
		}
	}
	public void loadProjectCategories(){
		System.out.println(name() + "::loadProjectCategories");
		glb_projectCategories = projectCategoryService.list();
		glb_mCode2ProjectCategory = new HashMap<String, mTopicCategory>();
		for(mTopicCategory tc: glb_projectCategories){
			glb_mCode2ProjectCategory.put(tc.getPROJCAT_Code(), tc);
		}
	}
	public void loadProjectCalls(){
		System.out.println(name() + "::loadProjectCalls");
		glb_projectCalls = projectCallService.loadProjectCallsList();
		glb_mCode2ProjectCall = new HashMap<String, mProjectCalls>();
		for(mProjectCalls pc: glb_projectCalls){
			glb_mCode2ProjectCall.put(pc.getPROJCALL_CODE(), pc);
		}
	}
	public void loadProjectStatus(){
		System.out.println(name() + "::loadProjectStatus");
		glb_projectStatus = projectStatusService.list();
		glb_mCode2ProjectStatus = new HashMap<String, mProjectStatus>();
		for(mProjectStatus ps: glb_projectStatus){
			glb_mCode2ProjectStatus.put(ps.getPROJSTAT_Code(), ps);
		}
	}
	public void loadStaffs() {
		System.out.println(name() + "::loadStaffs");
		glb_staffs = staffService.listStaffs();
		glb_mCode2Staff = new HashMap<String, mStaff>();
		for (mStaff st : glb_staffs) {
			glb_mCode2Staff.put(st.getStaff_Code(), st);
			if(st.getStaff_Code().equals("dung.phamquang@hust.edu.vn")){
				System.out.println(name() + "::loadStaff, mCode2Satff.put(" + st.getStaff_Code() + ")");
			}
		}
	}

	public void loadFaculties() {
		System.out.println(name() + "::loadFaculties");
		glb_faculties = facultyService.loadFacultyList();
		glb_mCode2Faculty = new HashMap<String, mFaculty>();
		for (mFaculty f : glb_faculties) {
			glb_mCode2Faculty.put(f.getFaculty_Code(), f);
		}
	}

	public void loadDepartment() {
		System.out.println(name() + "::loadDepartments");
		glb_departments = departmentService.loadDepartmentList();
		glb_mCode2Department = new HashMap<String, mDepartment>();
		for (mDepartment d : glb_departments) {
			glb_mCode2Department.put(d.getDepartment_Code(), d);
		}
	}

	public BaseWeb() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * Set permission
	 * 
	 * @param session
	 * @throws Exception
	 */
	public void setPermission(HttpSession session) {
		System.out.println(name() + "::setPermission");
		
		// set UserCode
		BaseWeb.sUserCode = session.getAttribute("currentUserCode").toString();
		System.out.println(name() + "::setPermission, sUserCode = " + sUserCode);
		
		// set User Role
		BaseWeb.sUserRole = session.getAttribute("currentUserRole").toString();
		System.out.println(name() + "::setPermission, sUserRole = " + sUserRole);
				
		// set User name
		BaseWeb.sUserName = session.getAttribute("currentUserName").toString();
		System.out.println(name() + "::setPermission, sUserName = " + sUserName);
		
		if(mFuncsPermissionList == null){// PQD
		// set User permissions
		BaseWeb.mFuncsPermissionList = funcsPermissionService
				.loadFunctionsList();
		System.out.println(name() + "::setPermission, loadFunctionList OK");
		
		// set User permissions
		BaseWeb.mFuncsChildrenPermissionList = funcsPermissionService
				.loadFunctionsChildHierachyList();
		System.out.println(name() + "::setPermission, loadFunctionChildHierachyList OK");
		
		// set User permissions
		BaseWeb.mFuncsParentsPermissionList = funcsPermissionService
				.loadFunctionsParentHierachyList();
		System.out.println(name() + "::setPermission, loadFunctionParentHierachyList OK");
		}
		
	}

	/**
	 * 
	 * @param map
	 * @param session
	 */
	@ModelAttribute
	public void addGlobalAttr(ModelMap map, HttpSession session) {
		System.out.println("BaseWeb::addGlobalAttr");

		
		// set permission
		this.setPermission(session);
		System.out.println("BaseWeb::addGlobalAttr, setPermission OK");
		// set base url
		switch (request.getRequestURI()) {
		case "/":
			baseUrl = request.getRequestURL()
					.substring(0, request.getRequestURL().length() - 1)
					.toString();
			break;
		case "":
			baseUrl = request.getRequestURL().toString();
			break;
		default:
			baseUrl = request.getRequestURL().toString()
					.replace(request.getRequestURI(), request.getContextPath());
			break;
		}
		// Get current username
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!"".equals(username))
			map.put("username", username);

		System.out.println("BaseWeb::addGlobalAttr, username = " + username);


		mStaff staff = staffService.loadStaffByUserCode(username);
		if (staff == null) {
			System.out
					.println("BaseWeb::addGlobalAttr, NO Staff corresponding to username "
							+ username);
		}
		System.out.println("BaseWeb::addGlobalAttr, get staff OK");
		
		String facultyCode = staff.getStaff_Faculty_Code();
		
		session.setAttribute("facultyCode", facultyCode);
		map.put("facultyCode", facultyCode);
		// System.out.println("BaseWeb::addGlobalAttr, facultyCode = " +
		// facultyCode);

		assetsUrl = baseUrl + "/assets";
		map.put("baseUrl", baseUrl);
		map.put("assetsUrl", assetsUrl);

		List<mEditFunctions> funcsEditParentsList = new ArrayList<>();
		List<mEditFunctions> funcsEditChildrenList = new ArrayList<>();
		List<mFunction> funcsChildrenPermissionList = BaseWeb.mFuncsChildrenPermissionList;
		List<mFunction> funcsParentsPermissionList = BaseWeb.mFuncsParentsPermissionList;
		List<mFuncsPermission> mCurrentUserFuncsPermissionList = funcsPermissionService
				.loadFunctionsPermissionByUserList(BaseWeb.sUserCode);

		System.out.println("BaseWeb::addGlobalAttr, loadFunctionsPermissionByUserList OK");
		
		for (mFunction mFunction : funcsChildrenPermissionList) {
			mEditFunctions temp = new mEditFunctions();
			temp.setFUNC_ID(mFunction.getFUNC_ID());
			temp.setFUNC_CODE(mFunction.getFUNC_CODE());
			temp.setFUNC_NAME(mFunction.getFUNC_NAME());
			temp.setFUNC_PARENTID(mFunction.getFUNC_PARENTID());
			temp.setFUNC_URL(mFunction.getFUNC_URL());
			temp.setSELECTED(0);
			temp.setFUNC_SELECTED_CLASS(mFunction.getFUNC_SELECTED_CLASS());
			temp.setFUNC_TITLE_CLASS(mFunction.getFUNC_TITLE_CLASS());
			temp.setFUNC_HAS_CHILDREN(mFunction.getFUNC_HAS_CHILDREN());
			if (mCurrentUserFuncsPermissionList.size() > 0) {
				for (mFuncsPermission currentFunction : mCurrentUserFuncsPermissionList) {
					if (currentFunction.getUSERFUNC_FUNCCODE().equals(
							mFunction.getFUNC_CODE())) {
						temp.setSELECTED(1);
					}
				}
			}
			funcsEditChildrenList.add(temp);
		}

		for (mFunction mFunction : funcsParentsPermissionList) {
			mEditFunctions temp = new mEditFunctions();
			temp.setFUNC_ID(mFunction.getFUNC_ID());
			temp.setFUNC_CODE(mFunction.getFUNC_CODE());
			temp.setFUNC_NAME(mFunction.getFUNC_NAME());
			temp.setFUNC_PARENTID(mFunction.getFUNC_PARENTID());
			temp.setFUNC_URL(mFunction.getFUNC_URL());
			temp.setSELECTED(0);
			temp.setFUNC_SELECTED_CLASS(mFunction.getFUNC_SELECTED_CLASS());
			temp.setFUNC_TITLE_CLASS(mFunction.getFUNC_TITLE_CLASS());
			temp.setFUNC_HAS_CHILDREN(mFunction.getFUNC_HAS_CHILDREN());
			if (mCurrentUserFuncsPermissionList.size() > 0) {
				for (mFuncsPermission currentFunction : mCurrentUserFuncsPermissionList) {
					if (currentFunction.getUSERFUNC_FUNCCODE().equals(
							mFunction.getFUNC_CODE())) {
						temp.setSELECTED(1);
					}
				}
			}
			funcsEditParentsList.add(temp);
		}
		if (glb_staffs == null) {
			loadStaffs();
		}
		if (glb_faculties == null) {
			loadFaculties();
		}
		if (glb_departments == null) {
			loadDepartment();
		}

		if(glb_projectStatus == null){
			loadProjectStatus();
		}
		
		if(glb_projectCalls == null){
			loadProjectCalls();
		}
		
		if(glb_projectCategories == null){
			loadProjectCategories();
		}
		
		if(glb_academicYear == null){
			loadAcademicYears();
		}
		if(glb_projectResearchFields == null){
			loadProjectResearchFields();
		}
		
		if(glb_projectParticipationRoles == null){
			loadProjectParticipationRoles();
		}
		
		if(glb_paperCategories == null){
			loadPaperCategories();
		}
		
		if(glb_papersCategoryHourBudget == null){
			loadPaperCategoryHourBudget();
		}
		
		if(glb_journalList == null){
			loadJournalList();
		}
		
		map.put("funcsChildrenList", funcsEditChildrenList);
		map.put("funcsParentsList", funcsEditParentsList);
		
		System.out.println("BaseWeb::addGlobalAttr --> OK");
	}
}
