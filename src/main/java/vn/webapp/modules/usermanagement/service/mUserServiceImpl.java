/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.usermanagement.dao.mFuncsPermissionDAO;
import vn.webapp.modules.usermanagement.dao.mStaffDAO;
import vn.webapp.modules.usermanagement.dao.mUserDAO;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.model.mUser;
import vn.webapp.modules.usermanagement.model.mUserRoles;
import vn.webapp.modules.usermanagement.model.mUsers;

@Service("mUserService")
public class mUserServiceImpl implements mUserService, UserDetailsService{
	
	@Autowired
    private mUserDAO userDAO;
    
    @Autowired
    private mStaffDAO staffDAO;
    
    @Autowired
    private mFuncsPermissionDAO funcsPermissionDAO;

    public void setUserDAO(mUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /**
     * Get an user by id
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public mUsers loadUserById(int userId){
        try {
            return userDAO.loadUserById(userId);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public mUser loadUserByUsername(String username){
        try {
        	mUsers users = userDAO.getByUsername(username);
            mUserRoles userRole = userDAO.viewDetailUserRole(username);
            if (users != null) {
                mUser user = new mUser();
                user.setUsername(users.getUsername());
                user.setPassword(users.getPassword());
                user.setSalt(users.getSalt());
                user.setEmail(users.getEmail());
                user.setUser_Code(users.getUser_Code());
                if(userRole != null){
                	user.setUser_Role(userRole.getRole());
                }
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an user by username and id
     * @param String
     * @param int
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public mUsers loadUserByUsernameAndId(String username, int id){
        try {
        	if(!username.equals("") && id > 0){
	        	mUsers users = userDAO.getByUsernameAndId(username, id);
	            return users;
        	}
        	return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    

    /**
     * Get list all users
     * @param null
     * @return List
     */
    @Override
    @Transactional
    public List<mUsers> list() {
        return userDAO.list();
    }

    /**
     * Get an user by id
     * @param int
     * @return object
     */
    @Override
    @Transactional
    public HashMap<String, String> viewDetail(int id) {
    	mUsers user = userDAO.viewDetail(id);
    	if(user != null)
    	{
    		HashMap<String, String> userInfo = new HashMap<>();
    		userInfo.put("username", user.getUsername());
    		userInfo.put("email", user.getEmail());
    		userInfo.put("userId", String.valueOf(user.getUser_ID()));
    		userInfo.put("activated", String.valueOf(user.getEnabled()));
    		
    		userInfo.put("username", user.getUsername());
    		userInfo.put("password", user.getPassword());
    		
    		mUserRoles userRole = userDAO.viewDetailUserRole(user.getUsername());
    		if(userRole != null)
    		{
    			userInfo.put("userRole", userRole.getRole());
    			userInfo.put("userRoleId", String.valueOf(userRole.getUserRole_ID()));
    		}
    		
    		mStaff staff = staffDAO.getByUserCode(user.getUser_Code());
    		if(staff != null)
    		{
    			userInfo.put("fullname",staff.getStaff_Name());
    			userInfo.put("staffId", String.valueOf(staff.getStaff_ID()));
    			userInfo.put("staffFacultyCode", String.valueOf(staff.getStaff_Faculty_Code()));
    			userInfo.put("staffDepartmentCode", String.valueOf(staff.getStaff_Department_Code()));
    		}
    		userInfo.put("userCode", user.getUser_Code());
    		return userInfo;
    	}
    	return null;
    }

    /**
     * Get list all users
     * @param int
     * @return int
     */
    @Override
    @Transactional
    public int removeAnUser(String loginUserRole, int id, String userCode) {
    	if(id > 0){
     	   // 1. Remove user info in tblusers
     	   int isRemoveUser = userDAO.removeAnUser(loginUserRole, id);
     	   
     	   // 2. Remove user role 
     	   mUserRoles userRole = userDAO.viewDetailUserRole(userCode);
     	   if(userRole != null){
     		   int isRemoveRole = userDAO.removeAnUserRole(userRole);
     	   }
     	   
     	   // 3. Remove user info in tblstaffs
     	   mStaff staff = staffDAO.getByUserCode(userCode);
     	   if(staff != null)
     	   {
     		   staffDAO.removeAStaff(staff);
     	   }
     	   
     	   // 4. Remove user info in tbluserfunctions
     	   List<mFuncsPermission> functionList =  funcsPermissionDAO.loadFunctionsPermissionByUserList(userCode);
     	   if(functionList != null)
     	   {
     		   for (mFuncsPermission funcsPermission : functionList) {
     			   funcsPermissionDAO.removeAFunction(funcsPermission);
     		   }
     	   }
     	   return isRemoveUser;
     	}
        return 0;
    }

    /**
     * Save an user
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    @Transactional
    public int saveAUser(String username, String password, String salt, String email, String role, int enabled, String[] aFunctionsPermitted)
    {
        mUsers user = new mUsers();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(salt);
        user.setEnabled(enabled);
        user.setUser_Code(username);
        
        mUserRoles userRole = new mUserRoles();
        userRole.setRole(role);
        userRole.setUsername(username);
        
        int i_SaveUser = userDAO.saveAUser(user);
        int i_SaveUserRole = userDAO.saveAUserRole(userRole);
        if(aFunctionsPermitted != null)
        {
        	String sUserCode = user.getUser_Code();
        	for (String sFunction : aFunctionsPermitted) {
        		mFuncsPermission newFunction = new mFuncsPermission();
    			String sCode = sUserCode+"_"+sFunction;
    			newFunction.setUSERFUNC_FUNCCODE(sFunction);
    			newFunction.setUSERFUNC_USERCODE(sUserCode);
    			newFunction.setUSERFUNC_CODE(sCode);
    			funcsPermissionDAO.saveAFunction(newFunction);
			}
        }
        return i_SaveUser & i_SaveUserRole;
    }
    
    /**
     * Edit an user
     * @param String
     * @param String
     * @param String
     * @param String
     * @return void
     */
    @Override
    @Transactional
    public void editAnUser(int userId, String username, String fullname, String password, String email, 
    						String role, int activated, int userRoleId, int staffId, String userDepartment, String[] aFunctionsPermitted){
    	// Set User to update
    	mUsers user = userDAO.loadUserById(userId);
    	String sUserCode = user.getUser_Code();
        user.setEmail(email);
        user.setUsername(username);
        user.setEnabled(activated);
        user.setUser_Code(username);
        if(password != ""){
        	user.setPassword(password);
        }
        // Edit User
        userDAO.editAnUser(user);
        
        // Set UserRoles to update
        mUserRoles userRole = userDAO.viewDetailUserRoleId(userRoleId);
        userRole.setRole(role);
        userRole.setUsername(username);
        // Edit User role
        userDAO.editAnUserRole(userRole);
        
        // Set Staff to update
        mStaff staff = staffDAO.getStaffById(staffId);
        staff.setStaff_Name(fullname);
        staff.setStaff_User_Code(sUserCode);
        staff.setStaff_Code(sUserCode);
        //staff.setStaff_AsciiName(sUserCode);
        staff.setStaff_Department_Code(userDepartment);
        
        staffDAO.editAStaff(staff);
        
        // TODO
        // Set functions permission
        if(aFunctionsPermitted  != null)
        {
        	// Removing old functions list
        	List<mFuncsPermission> existingFunctionList = funcsPermissionDAO.loadFunctionsPermissionByUserList(sUserCode);
        	if(existingFunctionList != null)
        	{
        		for (mFuncsPermission mFuncsPermission : existingFunctionList) {
        			funcsPermissionDAO.removeAFunction(mFuncsPermission);
				}
        	}
    		
        	// Adding new Functions
        	for (String sFunction : aFunctionsPermitted) {
    			mFuncsPermission newFunction = new mFuncsPermission();
    			String sCode = sUserCode+"_"+sFunction;
    			newFunction.setUSERFUNC_FUNCCODE(sFunction);
    			newFunction.setUSERFUNC_USERCODE(sUserCode);
    			newFunction.setUSERFUNC_CODE(sCode);
    			funcsPermissionDAO.saveAFunction(newFunction);
			}
        }
        
    }
    
    /**
     * Edit an user
     * @param String
     * @param String
     * @return void
     */
    @Override
    @Transactional
    public void saveSettings(String username, String password){
    	mUsers newSetting = new mUsers();
    	if(!password.equals("")){
	    	mUsers user = userDAO.getByUsername(username);
	    	newSetting.setUser_ID(user.getUser_ID());
	    	newSetting.setEmail(user.getEmail());
	    	newSetting.setUsername(username);
	    	newSetting.setEnabled(user.getEnabled());
	    	newSetting.setUser_Code(user.getUser_Code());
	    	newSetting.setPassword(password);
	    	newSetting.setSalt(user.getSalt());
	    	
	    	userDAO.editAnUser(newSetting);
    	}
    }
}
