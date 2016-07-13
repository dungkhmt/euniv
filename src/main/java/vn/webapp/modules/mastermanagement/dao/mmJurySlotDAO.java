package vn.webapp.modules.mastermanagement.dao;


import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmJurySlot;

public interface mmJurySlotDAO {
	public List<mmJurySlot> listJurySlots(String defenseSessionCode, String staffCode);
	public mmJurySlot getJurySlotByCode(String sJurySlotCode);
	public mmJurySlot getJurySlotByUserCode(String sJurySlotCode, String userCode);
	public List<mmJurySlot> listJurySlots(String staffCode);
	public int saveJurySlot(mmJurySlot jurySlot);
	public int removeAJurySlot(mmJurySlot jurySlot);
}
