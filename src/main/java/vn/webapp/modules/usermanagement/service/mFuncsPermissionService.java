/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mFunction;

public interface mFuncsPermissionService {
	/**
	 * 
	 * @return
	 */
	public List<mFuncsPermission> loadFunctionsPermissionByUserList(String sUserCode);
	
	/**
	 * 
	 * @param sUserCode
	 * @return
	 */
	public mFuncsPermission loadFunctionsPermissionByCodeAndUser(String sFuncCode, String sUserCode);
    
	/**
	 * 
	 * @param null
	 * @return
	 */
	public List<mFunction> loadFunctionsList();
	
	/**
	 * 
	 * @return
	 */
	public List<mFunction> loadFunctionsParentHierachyList();
	
	/**
	 * 
	 * @return
	 */
	public List<mFunction> loadFunctionsChildHierachyList();
	
	/**
	 * 
	 * @param sFuncCode
	 * @param sUserCode
	 * @return
	 */
	public int saveFunctionsPermission(String sFuncCode, String sUserCode);
   
}
