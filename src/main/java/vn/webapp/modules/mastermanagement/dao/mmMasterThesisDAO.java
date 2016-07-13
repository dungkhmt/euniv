/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmMasterThesis;
import vn.webapp.modules.mastermanagement.model.mmMasterThesisInput;

public interface mmMasterThesisDAO {

	public List<mmMasterThesis> listMasterThesis();
	
	public mmMasterThesis getMasterThesisById(int MasterThesis_Id);
	
	public mmMasterThesisInput getRawMasterThesisById(int MasterThesis_Id);
	
	public mmMasterThesis getMasterThesisByCode(String MasterThesis_Code);
	
	public void editAMasterThesis(mmMasterThesisInput MasterThesis);
    
    public int saveAMasterThesis(mmMasterThesisInput MasterThesis);
    
    public int removeAMasterThesis(int MasterThesisId);
    
}
