/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.mastermanagement.dao.mmAcademicRankDAO;
import vn.webapp.modules.mastermanagement.dao.mmExternalStaffDAO;
import vn.webapp.modules.mastermanagement.dao.mmUniversityDAO;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmExternalStaffInput;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmUniversity;

@Service("mmexternalstaffService")
public class mmExternalStaffServiceImpl implements mmExternalStaffService {

    @Autowired
    private mmExternalStaffDAO externalstaffDAO;    
    
    @Autowired
    private mmUniversityDAO universityDAO;    
    
    @Autowired
    private mmAcademicRankDAO academicrankDAO;    
       
    @Override
    public List<mmExternalStaff> listExternalStaffs(){
    	List<mmExternalStaff> listExternalStaffs = externalstaffDAO.listExternalStaffs();
    	/*for(mmExternalStaff externalStaff:listExternalStaffs){
    		mmUniversity university = universityDAO.loadAUniversityByCodes(externalStaff.getEXTSTAF_UniversityCode());
    		mmAcademicRank academicRank = academicrankDAO.loadByCode(externalStaff.getEXTSTAF_AcademicRank());
    		externalStaff.setAcademicRank(academicRank);
    		externalStaff.setUniversity(university);   		
    	}*/    	
    	return listExternalStaffs;
    }
	
    @Override
    public List<mmExternalStaff> listExternalStaffsByUniversity(String universityCode){
    	List<mmExternalStaff> listExternalStaffs = externalstaffDAO.listExternalStaffsByUniversity(universityCode);
    	/*for(mmExternalStaff externalStaff:listExternalStaffs){
    		mmUniversity university = universityDAO.loadAUniversityByCodes(externalStaff.getEXTSTAF_UniversityCode());
    		mmAcademicRank academicRank = academicrankDAO.loadByCode(externalStaff.getEXTSTAF_AcademicRank());
    		externalStaff.setAcademicRank(academicRank);
    		externalStaff.setUniversity(university);   		
    	}*/    	
    	return listExternalStaffs;
	}
		
    @Override
    public mmExternalStaff getExternalStaffById(String userRole, int staff_Id){
    	mmExternalStaff externalStaff = externalstaffDAO.getExternalStaffById(userRole, staff_Id);
    	/*mmUniversity university = universityDAO.loadAUniversityByCodes(externalStaff.getEXTSTAF_UniversityCode());
		mmAcademicRank academicRank = academicrankDAO.loadByCode(externalStaff.getEXTSTAF_AcademicRank());
		externalStaff.setAcademicRank(academicRank);
		externalStaff.setUniversity(university);*/   		
		return externalStaff;
	}
	
    @Override
    public mmExternalStaff getByExternalStaffCode(String externalStaffCode){
    	mmExternalStaff externalStaff = externalstaffDAO.getByExternalStaffCode(externalStaffCode);
    	/*mmUniversity university = universityDAO.loadAUniversityByCodes(externalStaff.getEXTSTAF_UniversityCode());
		mmAcademicRank academicRank = academicrankDAO.loadByCode(externalStaff.getEXTSTAF_AcademicRank());
		externalStaff.setAcademicRank(academicRank);
		externalStaff.setUniversity(university); 
		System.out.println(academicRank.getAcademicRank_AsciiName());*/
		return externalStaff; 
	}
		   
    @Override
    public void editAExternalStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, String userRole, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank, mmUniversity university){
    	
		mmExternalStaffInput staff = externalstaffDAO.getExternalStaffInputById(userRole,staff_ID);
		if (staff != null) {
			
			staff.setEXTSTAF_Code(staffCode);
			staff.setEXTSTAF_Email(staffEmail);
			staff.setEXTSTAF_Name(staffName);
			staff.setEXTSTAF_Phone(staffPhone);
			//staff.setAcademicRank(academicRank);
			staff.setListSpecializationKeywords(specializationKeywords);
			//staff.setUniversity(university);
			staff.setEXTSTAF_AcademicRank(academicRank.getAcademicRank_Code());
			staff.setEXTSTAF_UniversityCode(university.getUniversity_Code());
			
			externalstaffDAO.editAExternalStaff(staff);
			return;
		} else
			return;
    	
    }
    
    @Override    
    public int saveAExternalStaff(String staffCode, String staffName, String staffEmail, String staffPhone, String userRole, HashSet<mmSpecializationKeyword> specializationKeywords, mmAcademicRank academicRank, mmUniversity university){
    	
    	mmExternalStaffInput staff = new mmExternalStaffInput();   
    	
    	staff.setEXTSTAF_Code(staffCode);
		staff.setEXTSTAF_Email(staffEmail);
		staff.setEXTSTAF_Name(staffName);
		staff.setEXTSTAF_Phone(staffPhone);
		staff.setEXTSTAF_AcademicRank(academicRank.getAcademicRank_Code());
		staff.setEXTSTAF_UniversityCode(university.getUniversity_Code());
		//staff.setAcademicRank(academicRank);		
		staff.setListSpecializationKeywords(specializationKeywords);
		//staff.setUniversity(university);
				
		return externalstaffDAO.saveAExternalStaff(staff);	
    }
    
    @Override    
    public int removeAExternalStaff(int staffId){
    	return externalstaffDAO.removeAExternalStaff(staffId);    	
    }    
}
