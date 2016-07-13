/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.dao.mmSpecializationKeywordDAO;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;

@Service("mmspecializationKeywordService")
public class mmSpecializationKeywordServiceImpl implements mmSpecializationKeywordService {

    @Autowired
    private mmSpecializationKeywordDAO mmspecializationKeywordDAO;

    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<mmSpecializationKeyword> loadSpecializationKeywordList() {
        try {
            return mmspecializationKeywordDAO.loadSpecializationKeywordList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<mmSpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code) {
        try {
            return mmspecializationKeywordDAO.loadSpecializationKeywordByScientificField(SCIF_Code);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }        
    
    /**
     * @param String
     * @return object
     */
     public mmSpecializationKeyword getSpecializationKeywordByCode(String KW_Code){
    	 try {
             return mmspecializationKeywordDAO.getSpecializationKeywordByCode(KW_Code);
             
         } catch (Exception e) {
             System.out.println("Exception: " + e.getMessage());
             return null;
         }
     }
     public List<mmSpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode){
    	 return mmspecializationKeywordDAO.loadStaffSpecializationKeywordList(staffCode);
     }
     public List<mmSpecializationKeyword> loadMasterThesisSpecializationKeywordList(String thesisCode){
    	 return mmspecializationKeywordDAO.loadMasterThesisSpecializationKeywordList(thesisCode);
     }
}
