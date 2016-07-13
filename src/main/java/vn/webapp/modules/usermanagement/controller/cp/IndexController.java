package vn.webapp.modules.usermanagement.controller.cp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.usermanagement.model.mEditFunctions;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mFunction;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.model.mUser;
import vn.webapp.modules.usermanagement.service.mFuncsPermissionService;
import vn.webapp.modules.usermanagement.service.mStaffService;
import vn.webapp.modules.usermanagement.service.mUserService;

@Controller("cpIndex")
@RequestMapping(value = {"/cp"})
public class IndexController {
	@Autowired
    private mUserService userService;
    
    @Autowired
    private mStaffService staffService;
    
    @Autowired
    private mFuncsPermissionService funcsPermissionService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
        return "signin";
    }

    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String home(ModelMap map, HttpSession session) {
	   String username = SecurityContextHolder.getContext().getAuthentication().getName();
	   session.setAttribute("currentUserName", username);
	   mUser user = userService.loadUserByUsername(username);
	   if(user != null){
		   String sUserCode = user.getUser_Code();
		   mStaff staff = staffService.loadStaffByUserCode(sUserCode);
		   String userFaculty = "";
		   String userDepartment = "";
		   String staffName = "";
		   if(staff != null)
		   {
			   userFaculty = staff.getDepartment().getDepartment_Faculty_Code();
			   userDepartment = staff.getDepartment().getDepartment_Code();
			   staffName = staff.getStaff_Name();
		   }
		   session.setAttribute("currentstaffName", staffName);
		   session.setAttribute("currentUserFaculty", userFaculty);
		   session.setAttribute("currentUserDepartment", userDepartment);
		   session.setAttribute("currentUserRole", user.getUser_Role());
		   session.setAttribute("currentUserCode", sUserCode);

   		   // New settings for permission functions
   		   List<mEditFunctions> funcsEditParentsList = new ArrayList<>();
	   	   List<mEditFunctions> funcsEditChildrenList = new ArrayList<>();
	   	   List<mFunction> funcsChildrenPermissionList = funcsPermissionService.loadFunctionsChildHierachyList();
	   	   List<mFunction> funcsParentsPermissionList = funcsPermissionService.loadFunctionsParentHierachyList();
	   	   List<mFuncsPermission> mCurrentUserFuncsPermissionList = funcsPermissionService.loadFunctionsPermissionByUserList(sUserCode);
	   	  
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
        return "cp.home";
   }
   
   
}
