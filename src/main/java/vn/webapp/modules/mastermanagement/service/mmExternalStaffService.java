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
import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.model.mmUsers;

public interface mmExternalStaffService {

	public List<mmExternalStaff> listExternalStaffs();
	
	public List<mmExternalStaff> listExternalStaffsByUniversity(String universityCode);
		
	public mmExternalStaff getExternalStaffById(String userRole, int staff_Id);
	
	public mmExternalStaff getByExternalStaffCode(String externalStaffCode);
		   
    public void editAExternalStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, String userRole, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank, mmUniversity university);
    
    public int saveAExternalStaff(String staffCode, String staffName, String staffEmail, String staffPhone, String userRole, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank, mmUniversity university);
    
    public int removeAExternalStaff(int staffId);
    
}
