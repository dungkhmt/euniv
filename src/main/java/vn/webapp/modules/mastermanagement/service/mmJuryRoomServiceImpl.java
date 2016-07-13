package vn.webapp.modules.mastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import vn.webapp.modules.mastermanagement.dao.mmJuryRoomDAO;
import vn.webapp.modules.mastermanagement.model.mmJuryRoom;

import java.util.ArrayList;
import java.util.List;


@Service("mmJuryRoomService")
public class mmJuryRoomServiceImpl implements mmJuryRoomService{
	@Autowired
	private mmJuryRoomDAO mmjuryRoomDAO;
	
	@Override
	public List<mmJuryRoom> listJuryRooms(String defenseSessionCode, String staffCode){
		try{
			return mmjuryRoomDAO.listJuryRooms(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JuryRoomService::listJuryRooms(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<mmJuryRoom> listJuryRooms(String staffCode){
		try{
			return mmjuryRoomDAO.listJuryRooms(staffCode);
		}catch(Exception e){
			System.out.println("JuryRoomService::listJuryRooms(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	@Override
	public int saveAJuryRoom(int juryRoom_Index, String juryRoomCode, String defenseSessionCode, String userCode){
		
		mmJuryRoom aJuryRoom = mmjuryRoomDAO.getJuryRoomByCode(juryRoomCode, defenseSessionCode, userCode);
		if(aJuryRoom == null)
		{
			mmJuryRoom juryRoom = new mmJuryRoom();
			juryRoom.setJuryRoom_Code(juryRoomCode);
			juryRoom.setJuryRoom_DefenseSessionCode(defenseSessionCode);
			juryRoom.setJuryRoom_StaffCode(userCode);
			juryRoom.setJuryRoom_Index(juryRoom_Index); 
			return mmjuryRoomDAO.saveJuryRoom(juryRoom);
		}
		return 0;
	}
	
	/**
     * 
     */
    @Override
    public int removeAJuryRoom(String userCode, String sJuryRoomCode){
    	try{
    		mmJuryRoom oCheckExistingRecord = mmjuryRoomDAO.getJuryRoomByUserCode(sJuryRoomCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return mmjuryRoomDAO.removeAJuryRoomByCode(oCheckExistingRecord);
    		}
    		return 0;
		}catch(Exception exception)
		{
			System.out.println("Exception: " + exception.getMessage());
			return 0;
		}
    }
    
    @Override
    public int removeAJuryRoom(String defenseSessionCode, String userCode, String sJuryRoomCode){
    	try{
    		mmJuryRoom oCheckExistingRecord = mmjuryRoomDAO.getJuryRoomByUserCode(defenseSessionCode, sJuryRoomCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return mmjuryRoomDAO.removeAJuryRoomByCode(oCheckExistingRecord);
    		}
    		return 0;
		}catch(Exception exception)
		{
			System.out.println("Exception: " + exception.getMessage());
			return 0;
		}
    }
	
}
