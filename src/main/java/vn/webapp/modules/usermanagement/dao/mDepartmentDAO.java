/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 13th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mDepartment;

public interface mDepartmentDAO {
	/**
	 * 
	 * @return
	 */
	public List<mDepartment> loadDepartmentList();
    
	/**
	 * 
	 * @param facultyCode
	 * @return
	 */
    public List<mDepartment> loadDepartmentListByFaculty(String facultyCode);
    
    /**
     * 
     * @param departmentCode
     * @param falcutyCode
     * @return
     */
    public mDepartment loadADepartmentByCodes(String departmentCode, String falcutyCode);
    
    /**
     * 
     * @param falcutyCode
     * @return
     */
    public List<mDepartment> loadADepartmentByFaculty(String falcutyCode);
    
    /**
     * 
     * @param sCodes
     * @return
     */
    public List<mDepartment> loadADepartmentBySetOfCode(String[] sCodes);
}
