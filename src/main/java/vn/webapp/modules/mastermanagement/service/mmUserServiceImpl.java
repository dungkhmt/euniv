/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.dao.mmUserDAO;
import vn.webapp.modules.mastermanagement.model.mmUser;
import vn.webapp.modules.mastermanagement.model.mmUserRoles;
import vn.webapp.modules.mastermanagement.model.mmUsers;

@Service("mmuserService")
public class mmUserServiceImpl implements mmUserService, UserDetailsService {

    @Autowired
    private mmUserDAO mmuserDAO;

    public void setUserDAO(mmUserDAO userDAO) {
        this.mmuserDAO = userDAO;
    }
    
    /**
     * Get an user by id
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public mmUsers loadUserById(final int userId) throws UsernameNotFoundException {
        try {
            return mmuserDAO.loadUserById(userId);
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
    public mmUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
        	mmUsers users = mmuserDAO.getByUsername(username);
            mmUserRoles userRole = mmuserDAO.viewDetailUserRole(username);
            if (users != null) {
                mmUser user = new mmUser();
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
    
    @Override
    public mmUsers loadUserByUserCode(final String username) throws UsernameNotFoundException {
    
    	return mmuserDAO.getByUsername(username);
    	
    }
    
    /**
     * Get an user by username and id
     * @param String
     * @param int
     * @return object
     * @throws UsernameNotFoundException
     */
    public mmUsers loadUserByUsernameAndId(final String username, int id) throws UsernameNotFoundException {
    	mmUsers users = null;
        try {
            users = mmuserDAO.getByUsernameAndId(username, id);
            return users;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return users;
        }
    }
    

    /**
     * Get list all users
     * @param null
     * @return List
     */
    @Override
    public DataPage<mmUsers> list() {
        DataPage<mmUsers> dataUser = new DataPage<>();
        dataUser.setData(mmuserDAO.list());
        return dataUser;
    }

    /**
     * Get an user by id
     * @param int
     * @return object
     */
    @Override
    public HashMap<String, String> viewDetail(int id) {
    	mmUsers user = mmuserDAO.viewDetail(id);
    	if(user != null)
    	{
    		HashMap<String, String> userInfo = new HashMap<>();
    		userInfo.put("username", user.getUsername());
    		userInfo.put("email", user.getEmail());
    		userInfo.put("userId", String.valueOf(user.getUser_ID()));
    		userInfo.put("activated", String.valueOf(user.getEnabled()));
    		
    		userInfo.put("username", user.getUsername());
    		userInfo.put("password", user.getPassword());
    		
    		mmUserRoles userRole = mmuserDAO.viewDetailUserRole(user.getUsername());
    		if(userRole.getUserRole_ID() > 0)
    		{
    			userInfo.put("userRole", userRole.getRole());
    			userInfo.put("userRoleId", String.valueOf(userRole.getUserRole_ID()));
    		}
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
    public int removeUser(String id) {
        return mmuserDAO.removeUser(id);
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
    public int saveAUser(String username, String password, String salt, String email, String role, int enabled)
    {
        mmUsers user = new mmUsers();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt(salt);
        user.setEnabled(enabled);
        user.setUser_Code(username);
        
        mmUserRoles userRole = new mmUserRoles();
        userRole.setRole(role);
        userRole.setUsername(username);
        
        int i_SaveUser = mmuserDAO.saveAUser(user);
        int i_SaveUserRole = mmuserDAO.saveAUserRole(userRole);
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
    public void editAnUser(int userId, String username, String password, String email, String role, int activated, int userRoleId){
    	// Update user
    	mmUsers user = mmuserDAO.loadUserById(userId);
        user.setEmail(email);
        user.setUsername(username);
        user.setEnabled(activated);
        user.setUser_Code(username);
        if(password != ""){
        	user.setPassword(password);
        }
        
        mmUserRoles userRole = mmuserDAO.viewDetailUserRole(username);
        userRole.setUserRole_ID(userRoleId);
        userRole.setRole(role);
        userRole.setUsername(username);
        
        mmuserDAO.editAnUser(user);
        mmuserDAO.editAnUserRole(userRole);
    }
    
    /**
     * Edit an user
     * @param String
     * @param String
     * @return void
     */
    @Override
    public void saveSettings(String username, String password){
    	mmUsers newSetting = new mmUsers();
    	if(!password.equals("")){
	    	mmUsers user = mmuserDAO.getByUsername(username);
	    	newSetting.setUser_ID(user.getUser_ID());
	    	newSetting.setEmail(user.getEmail());
	    	newSetting.setUsername(username);
	    	newSetting.setEnabled(user.getEnabled());
	    	newSetting.setUser_Code(user.getUser_Code());
	    	newSetting.setPassword(password);
	    	newSetting.setSalt(user.getSalt());
	    	
	    	mmuserDAO.editAnUser(newSetting);
    	}
    }
}
