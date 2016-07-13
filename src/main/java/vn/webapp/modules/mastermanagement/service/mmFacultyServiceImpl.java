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

import vn.webapp.modules.mastermanagement.dao.mmFacultyDAO;
import vn.webapp.modules.mastermanagement.model.mmFaculty;

@Service("mmfacultyService")
public class mmFacultyServiceImpl implements mmFacultyService {

    @Autowired
    private mmFacultyDAO mmfacultyDAO;

    
    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mmFaculty> loadFacultyList() {
        try {
            return mmfacultyDAO.loadFacultyList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public mmFaculty loadAFacultyByCodes(String facultyCode){
    	try {
    		if(facultyCode.equals("")){
    			return null;
    		}
            return mmfacultyDAO.loadAFacultyByCodes(facultyCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
