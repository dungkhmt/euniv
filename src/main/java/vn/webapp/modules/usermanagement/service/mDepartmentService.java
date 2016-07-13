package vn.webapp.modules.usermanagement.service;

import java.util.List;
import vn.webapp.modules.usermanagement.model.mDepartment;

public interface mDepartmentService {
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
}
