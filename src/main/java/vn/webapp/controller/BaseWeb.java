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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.webapp.modules.usermanagement.model.mEditFunctions;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mFunction;
import vn.webapp.modules.usermanagement.model.mStaff;
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
                            
    public static final String PROJECT_ROOT_DIR = "C:/euniv-deploy/";
    
    @Autowired
    private mFuncsPermissionService funcsPermissionService;
    
    @Autowired
    private mStaffService staffService;
    
    public BaseWeb() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     * Set permission
     * @param session
     * @throws Exception 
     */
    public void setPermission(HttpSession session)
    {
	    	// set UserCode
	    	BaseWeb.sUserCode = session.getAttribute("currentUserCode").toString();
	    	// set User Role
	    	BaseWeb.sUserRole = session.getAttribute("currentUserRole").toString();
	    	// set User name
	    	BaseWeb.sUserName = session.getAttribute("currentUserName").toString();
	    	// set User permissions 
	    	BaseWeb.mFuncsPermissionList = funcsPermissionService.loadFunctionsList();
	    	// set User permissions 
	    	BaseWeb.mFuncsChildrenPermissionList = funcsPermissionService.loadFunctionsChildHierachyList();
	    	// set User permissions
	    	BaseWeb.mFuncsParentsPermissionList = funcsPermissionService.loadFunctionsParentHierachyList();
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
                baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - 1).toString();
                break;
            case "":
                baseUrl = request.getRequestURL().toString(); 
                break;
            default:
                baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
                break;
        }
        // Get current username 
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!"".equals(username)) map.put("username", username);
        
        System.out.println("BaseWeb::addGlobalAttr, username = " + username);
        
        mStaff staff = staffService.loadStaffByUserCode(username);
        if(staff == null){
        	System.out.println("BaseWeb::addGlobalAttr, NO Staff corresponding to username " + username);
        }
        String facultyCode = staff.getStaff_Faculty_Code();
        
        session.setAttribute("facultyCode", facultyCode);
        map.put("facultyCode", facultyCode);
        //System.out.println("BaseWeb::addGlobalAttr, facultyCode = " + facultyCode);
        
       assetsUrl = baseUrl + "/assets";
       map.put("baseUrl", baseUrl);
       map.put("assetsUrl", assetsUrl);
        
       List<mEditFunctions> funcsEditParentsList = new ArrayList<>();
 	   List<mEditFunctions> funcsEditChildrenList = new ArrayList<>();
 	   List<mFunction> funcsChildrenPermissionList = BaseWeb.mFuncsChildrenPermissionList;
 	   List<mFunction> funcsParentsPermissionList = BaseWeb.mFuncsParentsPermissionList;
 	   List<mFuncsPermission> mCurrentUserFuncsPermissionList = funcsPermissionService.loadFunctionsPermissionByUserList(BaseWeb.sUserCode);
 	  
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
 		   if(mCurrentUserFuncsPermissionList.size() > 0){
 			   for (mFuncsPermission currentFunction : mCurrentUserFuncsPermissionList) {
 				   if(currentFunction.getUSERFUNC_FUNCCODE().equals(mFunction.getFUNC_CODE()))
 				   {
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
 		   if(mCurrentUserFuncsPermissionList.size() > 0){
 			   for (mFuncsPermission currentFunction : mCurrentUserFuncsPermissionList) {
 				   if(currentFunction.getUSERFUNC_FUNCCODE().equals(mFunction.getFUNC_CODE()))
 				   {
 					   temp.setSELECTED(1);
 				   }
 			   }
 		   }
 		   funcsEditParentsList.add(temp);
 	   }
 	   
 	   map.put("funcsChildrenList", funcsEditChildrenList);
 	   map.put("funcsParentsList", funcsEditParentsList);
    }
}
