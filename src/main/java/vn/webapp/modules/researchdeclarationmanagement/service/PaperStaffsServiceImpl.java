/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.researchdeclarationmanagement.dao.PaperStaffsDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;

@Service("paperStaffsService")
public class PaperStaffsServiceImpl implements PaperStaffsService {

    @Autowired
    private PaperStaffsDAO paperStaffsDAO;
    
   /**
    * 
    */
    @Override
    @Transactional
    public List<PaperStaffs> loadPaperListByPaperCode(String paperCode){
        try {
        	return paperStaffsDAO.loadPaperListByPaperCode(paperCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
     @Override
     @Transactional
     public List<PaperStaffs> loadPaperListByPaperCode(List<String> paperCodes){
         try {
         	return paperStaffsDAO.loadPaperListByPaperCode(paperCodes);
         } catch (Exception e) {
        	 System.out.println(e.getMessage());
             return null;
         }
     }
    
    
    /**
     * Save a paper
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    @Transactional
    public int saveAPaperStaff(String PPSTF_Code, String PPSTF_StaffCode, String PPSTF_PaperCode)
    {
    	if(!"".equals(PPSTF_Code) && !"".equals(PPSTF_StaffCode) && !"".equals(PPSTF_PaperCode)){
	    	PaperStaffs paperStaff = new PaperStaffs();
	    	paperStaff.setPPSTF_Code(PPSTF_Code);
	    	paperStaff.setPPSTF_PaperCode(PPSTF_PaperCode);
	    	paperStaff.setPPSTF_StaffCode(PPSTF_StaffCode);
	    	
	        int i_SaveAPaper = paperStaffsDAO.saveAPaperStaff(paperStaff);
	        return i_SaveAPaper;
    	}
    	return 0;
    }

    /**
     * Remove a paper staff
     * @param int
     * @return int
     */
    @Override
    @Transactional
    public int removeAPaperStaff(PaperStaffs paperStaffs){
    	if(paperStaffs != null)
    	{
    		return paperStaffsDAO.removeAPaperStaff(paperStaffs);
    	}else{
    		return 0;
    	}
    }
    
}
