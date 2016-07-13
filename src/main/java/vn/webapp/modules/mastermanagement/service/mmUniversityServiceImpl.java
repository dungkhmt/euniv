/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.dao.mmUniversityDAO;
import vn.webapp.modules.mastermanagement.model.mmUniversity;

@Service("mmuniversityService")
public class mmUniversityServiceImpl implements mmUniversityService {

    @Autowired
    private mmUniversityDAO mmuniversityDAO;

    
    /**
     * Get an user by university
     * @param String
     * @return object
     * @throws UniversityNotFoundException
     */
    @Override
    public List<mmUniversity> loadUniversityList() {
        try {
            return mmuniversityDAO.loadUniversityList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a university by its code
     * @param null
     * @return object
     */
    @Override
    public mmUniversity loadAUniversityByCodes(String universityCode){
    	try {
    		if(universityCode.equals("")){
    			return null;
    		}
            return mmuniversityDAO.loadAUniversityByCodes(universityCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public mmUniversity loadAUniversityByID(int universityID){
    	return mmuniversityDAO.loadAUniversityByID(universityID);
    }
    
    public void editAUniversity(int universityID, String universityName, String universityCode){
    	mmUniversity university = mmuniversityDAO.loadAUniversityByID(universityID);
    	if(university != null){
    		university.setUniversity_AsciiName(universityName);
    		university.setUniversity_Code(universityCode);
    		university.setUniversity_Name(universityName);
    		university.setUniversity_Role("PARTNER");
    		mmuniversityDAO.editAUniversity(university);
    	}
    }
    
    public int saveAUniversity(String universityName, String universityCode){
    	
    	mmUniversity university = new mmUniversity();
    	university.setUniversity_AsciiName(universityName);
    	university.setUniversity_Code(universityCode);
    	university.setUniversity_Name(universityName);
    	university.setUniversity_Role("PARTNER");
    	mmuniversityDAO.saveAUniversity(university);
    	return 1;   	
    	
    }
    
    public int removeAUniversity(int UniversityId){
    	return mmuniversityDAO.removeAUniversity(UniversityId);    	
    }    
}
