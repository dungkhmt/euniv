package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmJuryRoom;

public interface mmJuryRoomDAO {
	List<mmJuryRoom> listJuryRooms(String defenseSessionCode, String staffCode);
	mmJuryRoom getJuryRoomByCode(String sJuryRoomCode, String defenseSessionCode, String userCode);
	mmJuryRoom getJuryRoomByUserCode(String sJuryRoomCode, String userCode);
	mmJuryRoom getJuryRoomByUserCode(String defenseSessionCode, String sJuryRoomCode, String userCode);
	List<mmJuryRoom> listJuryRooms(String staffCode);
	int saveJuryRoom(mmJuryRoom juryRoom);
	public int removeAJuryRoomByCode(mmJuryRoom aJuryRoom);
}
