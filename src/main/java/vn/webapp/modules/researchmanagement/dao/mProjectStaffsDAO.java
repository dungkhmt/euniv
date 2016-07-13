package vn.webapp.modules.researchmanagement.dao;

import java.util.HashSet;
import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;

public interface mProjectStaffsDAO {
	/**
	 * 
	 * @param staff
	 * @return
	 */
	public int saveAStaff(mProjectStaffs staff);
    
	/**
	 * 
	 * @param projectCode
	 * @param userCode
	 * @param userRole
	 * @return
	 */
    public mProjectStaffs loadProjectStaffByUserCode(String projectCode, String userCode, String userRole);
    
    /**
     * 
     * @param projectStaffCode
     * @return
     */
    public List<mProjectStaffs> loadAProjectStaffByProjectCode(String projectStaffCode);
    
    /**
     * 
     * @param staffCodeList
     * @return
     */
    public List<mProjectStaffs> loadAProjectStaffInListStaffs(HashSet<String> staffCodeList);
    
    /**
     * 
     * @param projectStaffCode
     * @return
     */
    public List<mProjectStaffs> loadAProjectStaffByProjectCodeForEdit(String projectStaffCode);
    
    /**
     * 
     * @return
     */
    public List<mProjectStaffs> listAll();
    
    /**
     * 
     * @param projectStaffId
     * @return
     */
    public int removeAProjectStaff(int projectStaffId);
}
