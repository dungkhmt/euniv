package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmJuryRoom;

public interface mmJuryRoomService {
	List<mmJuryRoom> listJuryRooms(String defenseSessionCode, String staffCode);
	List<mmJuryRoom> listJuryRooms(String staffCode);
	int saveAJuryRoom(int juryRoom_Index, String juryRoomCode, String defenseSessionCode, String userCode);
	public int removeAJuryRoom(String userCode, String sJuryRoomCode);
	public int removeAJuryRoom(String defenseSessionCode, String userCode, String sJuryRoomCode);
}
