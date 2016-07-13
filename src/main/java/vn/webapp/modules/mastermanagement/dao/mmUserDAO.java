/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmUserRoles;
import vn.webapp.modules.mastermanagement.model.mmUsers;

public interface mmUserDAO {
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public mmUsers getByUsername(String userName);
    
	/**
	 * 
	 * @param userName
	 * @param id
	 * @return
	 */
    public mmUsers getByUsernameAndId(String userName, int id);

    /**
     * 
     * @return
     */
    public List<mmUsers> list();
    
    /**
     * 
     * @param id
     * @return
     */
    public mmUsers viewDetail(int id);
    
    /**
     * 
     * @param userName
     * @return
     */
    public mmUserRoles viewDetailUserRole(String userName);
    
    /**
     * 
     * @param userRoleId
     * @return
     */
    public mmUserRoles viewDetailUserRoleId(int userRoleId);
    
    /**
     * 
     * @param id
     * @return
     */
    public int removeUser(String id);
    
    /**
     * 
     * @param user
     * @return
     */
    public int saveAUser(mmUsers user);
    
    /**
     * 
     * @param userRole
     * @return
     */
    public int saveAUserRole(mmUserRoles userRole);
    
    /**
     * 
     * @param user
     */
    public void editAnUser(mmUsers user);
    
    /**
     * 
     * @param userRole
     */
    public void editAnUserRole(mmUserRoles userRole);
    
    /**
     * 
     * @param userId
     * @return
     */
    public mmUsers loadUserById(int userId);
    
    /**
     * 
     * @return
     */
    public int count();
}
