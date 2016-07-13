/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;
import vn.webapp.modules.usermanagement.model.mStaff;

public interface mStaffDAO {

	/**
	 * 
	 * @return
	 */
	public List<mStaff> listStaffs();
	
	/**
	 * 
	 * @param staffFaculty
	 * @return
	 */
	public List<mStaff> listStaffsByFalcuty(String staffFaculty);
	
	/**
	 * 
	 * @param departmentCode
	 * @return
	 */
	public List<mStaff> listStaffsByDepartment(String departmentCode);
	
	/**
	 * 
	 * @param staffFaculty
	 * @param departmentCode
	 * @return
	 */
	public List<mStaff> listStaffsByFalcutyAndDepartment(String staffFaculty, String departmentCode);
	
	/**
	 * 
	 * @param userCode
	 * @return
	 */
    public mStaff getByUserCode(String userCode);
    
    /**
     * 
     * @param staffId
     * @return
     */
    public mStaff getStaffById(int staffId);
    
    /**
     * 
     * @param staff
     */
    public void editAStaff(mStaff staff);
    
    /**
     * 
     * @param staff
     * @return
     */
    public int saveAStaff(mStaff staff);
    
    /**
     * 
     * @param staffId
     * @return
     */
    public int removeAStaff(int staffId);
}
