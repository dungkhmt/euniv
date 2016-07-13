/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import vn.webapp.modules.mastermanagement.model.mmStaffSpecializationKeywords;


public interface mmStaffSpecialKeyWordsDAO {
	
	public mmStaffSpecializationKeywords getStaffSpecializationKeywordsByStaffAndCode(String sUserCode, String sKeywordCode);
	
    public int saveAStaffSpecializationKeywords(mmStaffSpecializationKeywords staffSpecializationKeywords);
    
    //public int removeAMasterThesis(int MasterThesisId);
    
}
