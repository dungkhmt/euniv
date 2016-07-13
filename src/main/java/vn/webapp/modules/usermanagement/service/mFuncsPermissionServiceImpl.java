/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;
import vn.webapp.modules.usermanagement.dao.mDepartmentDAO;
import vn.webapp.modules.usermanagement.dao.mFuncsPermissionDAO;
import vn.webapp.modules.usermanagement.dao.mFunctionsDAO;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mFunction;

@Service("mFuncsPermissionService")
public class mFuncsPermissionServiceImpl implements mFuncsPermissionService {
	
	@Autowired
    private mDepartmentDAO departmentDAO;
	
	@Autowired
    private mFuncsPermissionDAO funcsPermissionDAO;
	
	@Autowired
    private mFunctionsDAO functionsDAO;

    /**
     * Get permissions by user code
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mFuncsPermission> loadFunctionsPermissionByUserList(String sUserCode) {
        try {
            return funcsPermissionDAO.loadFunctionsPermissionByUserList(sUserCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public mFuncsPermission loadFunctionsPermissionByCodeAndUser(String sFuncCode, String sUserCode){
        try {
            return funcsPermissionDAO.loadFunctionsPermissionByCodeAndUser(sFuncCode, sUserCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get function list
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mFunction> loadFunctionsList(){
    	try {
    		return functionsDAO.loadFunctionsList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public List<mFunction> loadFunctionsParentHierachyList(){
    	try {
    		return functionsDAO.loadFunctionsParentHierachyList();
    	
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public List<mFunction> loadFunctionsChildHierachyList(){
    	try {
    		return functionsDAO.loadFunctionsChildHierachyList();
    	
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    /**
     * 
     */
    @Override
    public int saveFunctionsPermission(String sFuncCode, String sUserCode){
		if (!"".equals(sFuncCode) && !"".equals(sUserCode)) {
			mFuncsPermission oFunc = new mFuncsPermission();
			String sCode = sUserCode+"_"+sFuncCode;
			oFunc.setUSERFUNC_CODE(sCode);
			oFunc.setUSERFUNC_USERCODE(sUserCode);
			oFunc.setUSERFUNC_FUNCCODE(sFuncCode);
			
			return funcsPermissionDAO.saveAFunction(oFunc);
		}
		return 0;
	}
    
}
