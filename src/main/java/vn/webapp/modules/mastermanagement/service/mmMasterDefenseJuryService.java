/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmMasterDefenseJuryThesis;

public interface mmMasterDefenseJuryService {
    
	public List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesisByDefenseSession(String defenseSessionCode, String userCode);
	
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCode(String ThesisCode);
    
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisById(int Id);
     
    public boolean updateAMasterDefenseJuryThesis(String[] masterDefenseThesis, String[] defenseder01, String[] defenseder02, String[] president, String[] secretary, 
    										String[] commissioner, String[] slot, String[] room, String[] no, String userCode, String sessionCode);

    public int saveAMasterThesis(String sMasterThesisCode, String sDefenseSessionCode, String userCode);
    
    public int removeAMasterThesis(String userCode, String sMasterThesisCode);
    
}
