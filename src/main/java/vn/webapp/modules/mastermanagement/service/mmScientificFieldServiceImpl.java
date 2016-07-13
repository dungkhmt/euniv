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
import vn.webapp.modules.mastermanagement.dao.mmScientificFieldDAO;
import vn.webapp.modules.mastermanagement.model.mmScientificField;

@Service("mmscientificFieldService")
public class mmScientificFieldServiceImpl implements mmScientificFieldService {

    @Autowired
    private mmScientificFieldDAO mmscientificFieldDAO;

    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<mmScientificField> loadScientificFieldList() {
        try {
            return mmscientificFieldDAO.loadScientificFieldList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
        
}
