/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;
import vn.webapp.modules.usermanagement.model.mUserRoles;
import vn.webapp.modules.usermanagement.model.mUsers;

public interface mUserDAO {
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public mUsers getByUsername(String userName);
    
	/**
	 * 
	 * @param userName
	 * @param id
	 * @return
	 */
    public mUsers getByUsernameAndId(String userName, int id);

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
    public mUsers viewDetail(int id);
    
    /**
     * 
     * @param userName
     * @return
     */
    public mUserRoles viewDetailUserRole(String userName);
    
    /**
     * 
     * @param userRoleId
     * @return
     */
    public mUserRoles viewDetailUserRoleId(int userRoleId);
    
    /**
     * 
     * @param id
     * @return
     */
    public int removeAnUser(String loginUserRole, int id);
    
    /**
     * 
     * @param user
     * @return
     */
    public int saveAUser(mUsers user);
    
    /**
     * 
     * @param userRole
     * @return
     */
    public int saveAUserRole(mUserRoles userRole);
    
    /**
     * 
     * @param user
     */
    public void editAnUser(mUsers user);
    
    /**
     * 
     * @param userRole
     */
    public void editAnUserRole(mUserRoles userRole);
    
    /**
     * 
     * @param userId
     * @return
     */
    public mUsers loadUserById(int userId);
    
    /**
     * 
     * @return
     */
    public int count();
}
