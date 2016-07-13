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
import vn.webapp.modules.mastermanagement.dao.mmDepartmentDAO;
import vn.webapp.modules.mastermanagement.model.mmDepartment;

@Service("mmdepartmentService")
public class mmDepartmentServiceImpl implements mmDepartmentService {

    @Autowired
    private mmDepartmentDAO mmdepartmentDAO;

    
    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mmDepartment> loadDepartmentList() {
        try {
            return mmdepartmentDAO.loadDepartmentList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public mmDepartment loadDepartmentByCode(String departmentCode){
    	try {
    		if(departmentCode.equals("")){
    			return null;
    		}
            return mmdepartmentDAO.loadDepartmentByCode(departmentCode);
            
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
    public mmDepartment loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
    		if(departmentCode.equals("") || falcutyCode.equals("")){
    			return null;
    		}
            return mmdepartmentDAO.loadADepartmentByCodes(departmentCode, falcutyCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
