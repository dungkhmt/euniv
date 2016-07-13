package vn.webapp.modules.mastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import vn.webapp.modules.mastermanagement.dao.mmJurySlotDAO;
import vn.webapp.modules.mastermanagement.model.mmJurySlot;

import java.util.ArrayList;
import java.util.List;



@Service("mmJurySlotService")
public class mmJurySlotServiceImpl implements mmJurySlotService{
	@Autowired
	private mmJurySlotDAO mmjurySlotDAO;
	
	@Override
	public List<mmJurySlot> listJurySlots(String defenseSessionCode, String staffCode){
		try{
			return mmjurySlotDAO.listJurySlots(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JurySlotService::listJurySlots(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<mmJurySlot> listJurySlots(String staffCode){
		try{
			return mmjurySlotDAO.listJurySlots(staffCode);
		}catch(Exception e){
			System.out.println("JurySlotService::listJurySlots(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	@Override
	public int saveAJurySlot(int jurySlot_Index, String jurySlotName, String defenseSessionCode, String userCode){
		String jurySlotCode = jurySlotName+"_"+defenseSessionCode;
		mmJurySlot aJurySlot = mmjurySlotDAO.getJurySlotByCode(jurySlotCode);
		if(aJurySlot == null)
		{
			mmJurySlot jurySlot = new mmJurySlot();
			jurySlot.setJurySlot_Name(jurySlotName);
			jurySlot.setJurySlot_Code(jurySlotCode);
			jurySlot.setJurySlot_DefenseSessionCode(defenseSessionCode);
			jurySlot.setJurySlot_StaffCode(userCode);
			jurySlot.setJurySlot_Index(jurySlot_Index); 
			return mmjurySlotDAO.saveJurySlot(jurySlot);
		}
		return 0;
	}
	
	@Override
	public int removeAJurySlot(String userCode, String sJurySlotCode)
	{
		try{
			mmJurySlot oCheckExistingRecord = mmjurySlotDAO.getJurySlotByUserCode(sJurySlotCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return mmjurySlotDAO.removeAJurySlot(oCheckExistingRecord);
    		}
    		return 0;
		}catch(Exception exception)
		{
			System.out.println("Exception: " + exception.getMessage());
			return 0;
		}
	}
	
}
