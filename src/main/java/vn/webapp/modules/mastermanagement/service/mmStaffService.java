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

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.mastermanagement.model.mmDepartment;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.model.mmUsers;

public interface mmStaffService {

	public List<mmStaff> listStaffs();
	
	public List<mmStaff> listStaffsByDepartment(String departmentCode);
	
	public List<mmStaff> listStaffsByFaculty(String facultyCode);
	
    public mmStaff loadStaffByUserCode(final String userCode);
    
    public mmStaff loadStaffByStaffCode(final String staffCode);
    
    public mmStaff loadStaffById(String userRole, int staff_ID);
    
    public mmStaffInput getStaffInputById(String userRole, int staff_Id);
    
    public void editAStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, mmDepartment staffDepartment, mmUsers user, String userRole, mmStaffCategory staffCategory, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank);
    
    public int saveAStaff(String staffCode, String staffName, String staffEmail, String staffPhone, mmDepartment staffDepartment, mmUsers user, String userRole, mmStaffCategory staffCategory, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank);

    public int removeAStaff(int staffID);
}
