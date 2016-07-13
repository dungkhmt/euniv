/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmMasterDefenseJuryThesis;

public interface mmMasterDefenseJuryDAO {

	public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesis();
	
	public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisById(int Id);
	
	public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCode(String ThesisCode);
	
	public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode);
	
	public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesisByDefenseSessionAndOwner(String defenseSessionCode,String ownerCode);
	
	public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByIdAndOwner(String masterDefenseJuryCode, String ownerCode);
	
	public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode);
	
	public void updateAMasterDefenseJuryThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis);
	
	public int saveAMasterThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis);
	
	//public mmRawMasterDefenseJuryThesis getRawMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode);
	
	public int removeAMasterThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis);
	
}
