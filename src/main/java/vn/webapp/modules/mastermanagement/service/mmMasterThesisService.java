/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.HashSet;
import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmDefenseSession;
import vn.webapp.modules.mastermanagement.model.mmMasterThesis;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStudent;

public interface mmMasterThesisService {

	public List<mmMasterThesis> listMasterThesis();
	
	public mmMasterThesis loadMasterThesisById(int masterThesis_ID);
	
	public mmMasterThesis loadMasterThesisByCode(String MasterThesis_Code);
	  
    public void editAMasterThesis(int ThesisID, String ThesisCode, String ThesisName, mmStudent student, mmStaff supervisor, HashSet<mmSpecializationKeyword> specializationKeywords);
    
    public int saveAMasterThesis(String ThesisCode, String ThesisName, String studentCode, String supervisor, HashSet<mmSpecializationKeyword> specializationKeywords, mmStudent student);

    public int removeAMasterThesis(int ThesisID);
    
    public List<mmDefenseSession> listDefenseSession();
}
