/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.usermanagement.dao.mStaffDAO;
import vn.webapp.modules.usermanagement.model.mAcademicRank;
import vn.webapp.modules.usermanagement.model.mStaff;

@Service("mStaffService")
public class mStaffServiceImpl implements mStaffService {
	
	@Autowired
    private mStaffDAO staffDAO;
    
    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffs(){
    	try {
            return staffDAO.listStaffs();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffsByFalcuty(String staffFaculty){
    	try {
    		if(!staffFaculty.equals(null)){
    			return staffDAO.listStaffsByFalcuty(staffFaculty);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffsByDepartment(String departmentCode){
    	try {
    		if(departmentCode != null){
    			return staffDAO.listStaffsByDepartment(departmentCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an user by username
     * @param String
     * @return object
     */
    @Override
    public mStaff loadStaffByUserCode(final String userCode) {
        try {
            return staffDAO.getByUserCode(userCode);
            
        } catch (Exception e) {
        	e.printStackTrace();
            //System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a staff
     * @param String
     * @return int
     */
    @Override
    public void editAStaff(int StaffId, String staffName, String staffEmail, String staffPhone, String staffDepartment, 
    						String userCode, String staffCatCode, String userFacultyCode, String staffGender, String staffDateOfBirth, mAcademicRank academicRank){
    	
    	mStaff staff = new mStaff();
    	staff.setStaff_Department_Code(staffDepartment);
    	staff.setStaff_AsciiName(staffName);
    	staff.setStaff_Name(staffName);
    	staff.setStaff_Phone(staffPhone);
    	staff.setStaff_User_Code(userCode);
    	staff.setStaff_Category_Code(staffCatCode);
    	staff.setStaff_Email(staffEmail);
    	staff.setStaff_Code(userCode);
    	staff.setStaff_ID(StaffId);
    	staff.setStaff_Faculty_Code(userFacultyCode);
    	staff.setStaff_Gender(staffGender);
    	staff.setStaff_DateOfBirth(staffDateOfBirth);
    	//staff.setAcademicRank(academicRank);
    	staff.setStaff_AcademicRank(academicRank.getAcademicRank_Code());
    	//System.out.println("mStaffService::editAStaff, academicRank = " + staff.getAcademicRank().getAcademicRank_Code());
    	
    	staffDAO.editAStaff(staff);
    }
    
    /**
     * Save a staff
     * @param String
     * @return int
     */
    @Override
    public int saveAStaff(String staffName, String staffEmail, String staffPhone, 
    						String staffDepartment, String userCode, String staffCatCode, String staffFaculty){
    	
    	mStaff staff = new mStaff();
    	staff.setStaff_Department_Code(staffDepartment);
    	staff.setStaff_AsciiName(staffName);
    	staff.setStaff_Name(staffName);
    	staff.setStaff_Phone(staffPhone);
    	staff.setStaff_User_Code(userCode);
    	staff.setStaff_Category_Code(staffCatCode);
    	staff.setStaff_Email(staffEmail);
    	staff.setStaff_Code(userCode);
    	staff.setStaff_Faculty_Code(staffFaculty);
    	staff.setStaff_Department_Code(staffDepartment);
    	return staffDAO.saveAStaff(staff);
    }

	@Override
	public List<mStaff> listStaffsByFalcutyAndDepartment(String facultyCode,
			String departmentCode) {
		// TODO Auto-generated method stub
		return staffDAO.listStaffsByFalcutyAndDepartment(facultyCode, departmentCode);
	}
}
