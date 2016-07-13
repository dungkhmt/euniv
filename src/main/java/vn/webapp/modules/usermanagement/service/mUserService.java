/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.HashMap;
import java.util.List;

import vn.webapp.modules.usermanagement.model.mUser;
import vn.webapp.modules.usermanagement.model.mUsers;

public interface mUserService {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public mUsers loadUserById(int userId);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
    public mUser loadUserByUsername(String username);
    
    /**
     * 
     * @param username
     * @param id
     * @return
     */
    public mUsers loadUserByUsernameAndId(String username, int id);

    /**
     * 
     * @return
     */
    public List<mUsers> list();
    
    /**
     * 
     * @param id
     * @return
     */
    public HashMap<String, String> viewDetail(int id);
    
    /**
     * 
     * @param id
     * @return
     */
    public int removeAnUser(String loginUserRole, int id, String userCode);
    
    /**
     * 
     * @param username
     * @param password
     * @param salt
     * @param email
     * @param role
     * @param activated
     * @return
     */
    public int saveAUser(String username, String password, String salt, String email, String role, int activated, String[] aFunctionsPermitted);
    
    /**
     * 
     * @param userId
     * @param username
     * @param password
     * @param email
     * @param role
     * @param activated
     * @param userRoleId
     * @param staffId
     * @param userDepartment
     */
    public void editAnUser(int userId, String username, String password, String email, String role, int activated, int userRoleId, int staffId, String userDepartment, String[] aFunctionsPermitted);
    
    /**
     * 
     * @param username
     * @param password
     */
    public void saveSettings(String username, String password);
}
