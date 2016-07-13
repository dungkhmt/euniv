/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.mastermanagement.dao.mmExternalStaffDAO;
import vn.webapp.modules.mastermanagement.dao.mmJurySlotDAO;
import vn.webapp.modules.mastermanagement.dao.mmMasterDefenseJuryDAO;
import vn.webapp.modules.mastermanagement.dao.mmMasterThesisDAO;
import vn.webapp.modules.mastermanagement.dao.mmRoomsDAO;
import vn.webapp.modules.mastermanagement.dao.mmStaffDAO;
import vn.webapp.modules.mastermanagement.model.mmMasterDefenseJuryThesis;

@Service("mmmasterDefenseJuryService")
public class mmMasterDefenseJuryServiceImpl implements mmMasterDefenseJuryService {

    @Autowired
    private mmMasterDefenseJuryDAO mmmasterDefenseJuryDAO;
    
    @Autowired
    private mmMasterThesisDAO mmmasterThesisDAO;
    
    @Autowired
    private mmStaffDAO mmstaffDAO;
    
    @Autowired
    private mmExternalStaffDAO mmexternalstaffDAO;
    
    @Autowired
    private mmRoomsDAO mmroomsDAO;
    
    @Autowired
    private mmJurySlotDAO mmjuryslotDAO;
    
    
    public List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesisByDefenseSession(String defenseSessionCode, String userCode){
    	
    	List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesis = mmmasterDefenseJuryDAO.getListMasterDefenseJuryThesisByDefenseSessionAndOwner(defenseSessionCode, userCode);
    	for(mmMasterDefenseJuryThesis masterDefenseJuryThesis:listMasterDefenseJuryThesis){
    		masterDefenseJuryThesis.setMasterThesis(mmmasterThesisDAO.getMasterThesisByCode(masterDefenseJuryThesis.getMASDEFJury_ThesisCode()));    		
    		masterDefenseJuryThesis.setExaminer1(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner1Code()));
    		masterDefenseJuryThesis.setExaminer2(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner2Code()));
    		masterDefenseJuryThesis.setPresident(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_PresidentCode()));
    		masterDefenseJuryThesis.setSecretary(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_SecretaryCode()));    		
    		masterDefenseJuryThesis.setMember(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_MemberCode()));
    		masterDefenseJuryThesis.setRoom(mmroomsDAO.getByCode(masterDefenseJuryThesis.getMASDEFJury_RoomCode()));
    		masterDefenseJuryThesis.setSlot(mmjuryslotDAO.getJurySlotByCode(masterDefenseJuryThesis.getMASDEFJury_SlotCode()));
    	}
    	return listMasterDefenseJuryThesis;    	
    }
    
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCode(String ThesisCode){
    	
    	try {
    	
    		mmMasterDefenseJuryThesis masterDefenseJuryThesis = mmmasterDefenseJuryDAO.getMasterDefenseJuryThesisByThesisCode(ThesisCode);
    		masterDefenseJuryThesis.setMasterThesis(mmmasterThesisDAO.getMasterThesisByCode(masterDefenseJuryThesis.getMASDEFJury_ThesisCode()));    		
    		masterDefenseJuryThesis.setExaminer1(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner1Code()));
    		masterDefenseJuryThesis.setExaminer2(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner2Code()));
    		masterDefenseJuryThesis.setPresident(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_PresidentCode()));
    		masterDefenseJuryThesis.setSecretary(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_SecretaryCode()));    		
    		masterDefenseJuryThesis.setMember(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_MemberCode()));
    		masterDefenseJuryThesis.setRoom(mmroomsDAO.getByCode(masterDefenseJuryThesis.getMASDEFJury_RoomCode()));
    		masterDefenseJuryThesis.setSlot(mmjuryslotDAO.getJurySlotByCode(masterDefenseJuryThesis.getMASDEFJury_SlotCode()));
    		
    		return masterDefenseJuryThesis;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisById(int Id){
    	
    	try {
        	
    		mmMasterDefenseJuryThesis masterDefenseJuryThesis = mmmasterDefenseJuryDAO.getMasterDefenseJuryThesisById(Id);
    		masterDefenseJuryThesis.setMasterThesis(mmmasterThesisDAO.getMasterThesisByCode(masterDefenseJuryThesis.getMASDEFJury_ThesisCode()));    		
    		masterDefenseJuryThesis.setExaminer1(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner1Code()));
    		masterDefenseJuryThesis.setExaminer2(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_Examiner2Code()));
    		masterDefenseJuryThesis.setPresident(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_PresidentCode()));
    		masterDefenseJuryThesis.setSecretary(mmstaffDAO.getByStaffCode(masterDefenseJuryThesis.getMASDEFJury_SecretaryCode()));    		
    		masterDefenseJuryThesis.setMember(mmexternalstaffDAO.getByExternalStaffCode(masterDefenseJuryThesis.getMASDEFJury_MemberCode()));
    		masterDefenseJuryThesis.setRoom(mmroomsDAO.getByCode(masterDefenseJuryThesis.getMASDEFJury_RoomCode()));
    		masterDefenseJuryThesis.setSlot(mmjuryslotDAO.getJurySlotByCode(masterDefenseJuryThesis.getMASDEFJury_SlotCode()));
    		
    		return masterDefenseJuryThesis;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    	
    }
    
  
    /**
     * 
     */
    @Override
    public boolean updateAMasterDefenseJuryThesis(String[] masterDefenseThesis, String[] defenseder01, String[] defenseder02, String[] president, String[] secretary, 
											String[] commissioner, String[] slot, String[] room, String[] no, String userCode, String sessionCode){
      	int totalRecordBeingSaved = masterDefenseThesis.length;
	    	for(int iIterator = 0; iIterator < totalRecordBeingSaved; iIterator++)
	    	{
	    		mmMasterDefenseJuryThesis aRecordDefenseJuryBeingSaved = new mmMasterDefenseJuryThesis();
	    		mmMasterDefenseJuryThesis oCheckExistingRecord = mmmasterDefenseJuryDAO.getMasterDefenseJuryThesisByThesisCode(masterDefenseThesis[iIterator]);
	    		if(oCheckExistingRecord != null)
	    		{
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ID(oCheckExistingRecord.getMASDEFJury_ID()); // Set Jury's ID
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_Code(oCheckExistingRecord.getMASDEFJury_Code()); // Set Jury's code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ThesisCode(oCheckExistingRecord.getMASDEFJury_ThesisCode()); // Set Jury's DefenseThesis code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_StaffCode(userCode);
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_DefenseSessionCode(sessionCode);
	    			
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner1Code(defenseder01[iIterator]); // Update defender 1
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner2Code(defenseder02[iIterator]); // Update defender 2
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_PresidentCode(president[iIterator]); // Update president
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SecretaryCode(secretary[iIterator]); // Update secreatry
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_MemberCode(commissioner[iIterator]); // Update member
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SlotCode(slot[iIterator]); // Update slot
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_RoomCode(room[iIterator]); // Update room
		    		
		    		mmmasterDefenseJuryDAO.updateAMasterDefenseJuryThesis(aRecordDefenseJuryBeingSaved);
	    		}
	    	}
	    	return true;
    	
    }
    
    /**
     * 
     */
    @Override
    public int saveAMasterThesis(String sMasterThesisCode, String sDefenseSessionCode, String userCode){
    	try{
    			
	    		mmMasterDefenseJuryThesis aRecordDefenseJuryBeingSaved = new mmMasterDefenseJuryThesis();
	    		mmMasterDefenseJuryThesis oCheckExistingRecord = mmmasterDefenseJuryDAO.getMasterDefenseJuryThesisByThesisCodeAndOwner(sMasterThesisCode, userCode);
	    		if(oCheckExistingRecord == null)
	    		{
	    			String sDefenseJuryCode = userCode + sMasterThesisCode + sDefenseSessionCode;
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_Code(sDefenseJuryCode); // Set Jury's code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ThesisCode(sMasterThesisCode); // Set Jury's DefenseThesis code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_StaffCode(userCode);
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_DefenseSessionCode(sDefenseSessionCode);
	    			
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner1Code(userCode); // Update defender 1
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner2Code(userCode); // Update defender 2
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_PresidentCode(userCode); // Update president
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_SecretaryCode(userCode); // Update secreatry
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_MemberCode(userCode); // Update member
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_SlotCode("N/A"); // Update slot
		    		//aRecordDefenseJuryBeingSaved.setMASDEFJury_RoomCode("D3-201"); // Update room
		    		
		    		
		    		return mmmasterDefenseJuryDAO.saveAMasterThesis(aRecordDefenseJuryBeingSaved);
	    		}
	    		return 0;
    	}catch(Exception exception)
    	{
    		System.out.println("Service Exception: " + exception.getMessage());
    		return 0;
    	}
    }
    
    /**
     * 
     */
    @Override
    public int removeAMasterThesis(String userCode, String sMasterThesisCode){
    	try{
    		mmMasterDefenseJuryThesis oCheckExistingRecord = mmmasterDefenseJuryDAO.getMasterDefenseJuryThesisByThesisCodeAndOwner(sMasterThesisCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return mmmasterDefenseJuryDAO.removeAMasterThesis(oCheckExistingRecord);
    		}
    		return 0;
	}catch(Exception exception)
	{
		System.out.println("Exception: " + exception.getMessage());
		return 0;
	}
    }
}
