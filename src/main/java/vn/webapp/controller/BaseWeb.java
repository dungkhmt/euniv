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

import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
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
	public static List<mFunction> mFuncsPermissionList;
	public static List<mFunction> mFuncsChildrenPermissionList;
	public static List<mFunction> mFuncsParentsPermissionList;

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
	
	
	public static final String PROJECT_ROOT_DIR = "C:/euniv-deploy/";

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
	
	public String name(){
		return "BaseWeb";
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
		// set UserCode
		BaseWeb.sUserCode = session.getAttribute("currentUserCode").toString();
		// set User Role
		BaseWeb.sUserRole = session.getAttribute("currentUserRole").toString();
		// set User name
		BaseWeb.sUserName = session.getAttribute("currentUserName").toString();
		// set User permissions
		BaseWeb.mFuncsPermissionList = funcsPermissionService
				.loadFunctionsList();
		// set User permissions
		BaseWeb.mFuncsChildrenPermissionList = funcsPermissionService
				.loadFunctionsChildHierachyList();
		// set User permissions
		BaseWeb.mFuncsParentsPermissionList = funcsPermissionService
				.loadFunctionsParentHierachyList();
	}

	/**
	 * 
	 * @param map
	 * @param session
	 */
	@ModelAttribute
	public void addGlobalAttr(ModelMap map, HttpSession session) {
		// set permission
		this.setPermission(session);

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
		
		map.put("funcsChildrenList", funcsEditChildrenList);
		map.put("funcsParentsList", funcsEditParentsList);
	}
}
