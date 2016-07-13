/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.dao.mmStaffDAO;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.mastermanagement.model.mmDepartment;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.model.mmUsers;

@Service("mmstaffService")
public class mmStaffServiceImpl implements mmStaffService {

    @Autowired
    private mmStaffDAO staffDAO;    
       
    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mmStaff> listStaffs(){
    	try {
            return staffDAO.listStaffs();
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
    public List<mmStaff> listStaffsByDepartment(String departmentCode){
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
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<mmStaff> listStaffsByFaculty(String facultyCode){
    	try {
    		if(facultyCode != null){
    			return staffDAO.listStaffsByFaculty(facultyCode);
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
    public mmStaff loadStaffByUserCode(final String userCode) {
        try {
            return staffDAO.getByUserCode(userCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an professor by id
     * @param String
     * @return object
     */
     
    public mmStaff loadStaffById(String userRole, int staff_ID){
    	try {
            return staffDAO.getStaffById(userRole, staff_ID);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public mmStaffInput getStaffInputById(String userRole, int staff_Id){
    	return staffDAO.getStaffInputById(userRole, staff_Id);
    }
    
    public mmStaff loadStaffByStaffCode(final String staffCode){
    	try {
            return staffDAO.getByStaffCode(staffCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a staff
     * @param String
     * @return int
     */
    @Override
    public void editAStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, mmDepartment staffDepartment, mmUsers user, String userRole, mmStaffCategory staffCategory, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank){
    	
    	 mmStaffInput staff = staffDAO.getStaffInputById(userRole, staff_ID);
    	 if(staff != null){
    		 //staff.setUniversity(staffUniversity);
    		 
    		 staff.setStaff_Department_Code(staffDepartment.getDepartment_Code());
	     	 staff.setStaff_AsciiName(staffName);
	     	 staff.setStaff_Name(staffName);
	     	 staff.setStaff_Phone(staffPhone);
	     	 staff.setStaff_Category_Code(staffCategory.getStaff_Category_Code());
	     	 //staff.setStaff_Category_Code(staffCategory.getStaff_Category_Code());
	     	 //staff.setUser(user);
	     	 staff.setStaff_User_Code(user.getUsername());
	     	 staff.setStaff_Email(staffEmail);
	     	 staff.setStaff_Code(staffCode);
	     	 staff.setListSpecializationKeywords(specializationKeywords);
	     	 staff.setStaff_AcademicRank(academicRank.getAcademicRank_Code());
	     	 
	     	 
	     	
	    	staffDAO.editAStaff(staff);
	    	return;
	    }
    	else
    		return;
    }
    
    /**
     * Save a staff
     * @param String
     * @return int
     */
    @Override
    public int saveAStaff(String staffCode, String staffName, String staffEmail, String staffPhone, mmDepartment staffDepartment, mmUsers user, String userRole, mmStaffCategory staffCategory, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank){
    	
    	mmStaffInput staff = new mmStaffInput();
		//staff.setDepartment(staffDepartment);
		staff.setStaff_Department_Code(staffDepartment.getDepartment_Code());
    	staff.setStaff_Faculty_Code(staffDepartment.getFaculty().getFaculty_Code());
		staff.setStaff_AsciiName(staffName);
		staff.setStaff_Name(staffName);
		staff.setStaff_Phone(staffPhone);
		staff.setStaff_Category_Code(staffCategory.getStaff_Category_Code());
		//staff.setStaffCategory(staffCategory);
		staff.setStaff_User_Code(user.getUsername());
	    staff.setStaff_Email(staffEmail);
		staff.setStaff_Code(staffCode);
		staff.setListSpecializationKeywords(specializationKeywords);
		staff.setStaff_AcademicRank(academicRank.getAcademicRank_Code());

    	return staffDAO.saveAStaff(staff);
    }
    
    /**
     * Remove a staff
     * @param int
     * @return int
     */
    @Override
    public int removeAStaff(int staffId){
    	return staffDAO.removeAStaff(staffId);
    }
}
