/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.webapp.modules.usermanagement.dao.mDepartmentDAO;
import vn.webapp.modules.usermanagement.model.mDepartment;

@Service("mDepartmentService")
public class mDepartmentServiceImpl implements mDepartmentService {
	
	@Autowired
    private mDepartmentDAO departmentDAO;

    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mDepartment> loadDepartmentList() {
        try {
            return departmentDAO.loadDepartmentList();
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
    public List<mDepartment> loadDepartmentListByFaculty(String facultyCode){
    	try {
    		if(facultyCode != null)
    		{
    			return departmentDAO.loadDepartmentListByFaculty(facultyCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public mDepartment loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
    		if(departmentCode.equals("") || falcutyCode.equals("")){
    			return null;
    		}
            return departmentDAO.loadADepartmentByCodes(departmentCode, falcutyCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public List<mDepartment> loadADepartmentByFaculty(String falcutyCode){
    	try {
    		if(falcutyCode.equals("")){
    			return null;
    		}
            return departmentDAO.loadADepartmentByFaculty(falcutyCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
}
