/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mAcademicRank;
import vn.webapp.modules.usermanagement.model.mStaff;

public interface mStaffService {
	
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
	 * @param userCode
	 * @return
	 */
    public mStaff loadStaffByUserCode(final String userCode);
    
    /**
     * 
     * @param StaffId
     * @param staffName
     * @param staffEmail
     * @param staffPhone
     * @param staffDepartment
     * @param userCode
     * @param staffCatCode
     * @param userFacultyCode
     * @param staffGender
     * @param staffDateOfBirth
     */
    public void editAStaff(int StaffId, String staffName, String staffEmail, String staffPhone, String staffDepartment, 
    						String userCode, String staffCatCode, String userFacultyCode, String staffGender, String staffDateOfBirth, mAcademicRank academicRank);
    
    /**
     * 
     * @param staffName
     * @param staffEmail
     * @param staffPhone
     * @param staffDepartment
     * @param userCode
     * @param staffCatCode
     * @param staffFaculty
     * @return
     */
    public int saveAStaff(String staffName, String staffEmail, String staffPhone, 
    						String staffDepartment, String userCode, String staffCatCode, String staffFaculty);
}
