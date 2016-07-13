package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmRooms;

public interface mmRoomsDAO {

	public List<mmRooms> listRooms();
	
	public mmRooms getByCode(String roomCode);
	
}
