/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;

public interface mFuncsPermissionDAO {
	/**
	 * 
	 * @return
	 */
	public List<mFuncsPermission> loadFunctionsPermissionByUserList(String sUserCode);
    
	/**
	 * 
	 * @param sFunctionCode
	 * @param sUserCode
	 * @return
	 */
	public mFuncsPermission loadFunctionsPermissionByCodeAndUser(String sFunctionCode, String sUserCode);
	
	/**
	 * 
	 * @param oFunctions
	 * @return
	 */
	public int saveAFunction(mFuncsPermission oFunctions);
	
	/**
	 * 
	 * @param oFunctions
	 * @return
	 */
	public int removeAFunction(mFuncsPermission oFunctions);
	
}
