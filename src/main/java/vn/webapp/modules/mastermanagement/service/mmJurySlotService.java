package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmJurySlot;


public interface mmJurySlotService {
	List<mmJurySlot> listJurySlots(String defenseSessionCode, String staffCode);
	List<mmJurySlot> listJurySlots(String staffCode);
	int saveAJurySlot(int jurySlot_Index, String jurySlotName, String defenseSessionCode, String userCode);
	public int removeAJurySlot(String userCode, String sJurySlotCode);
}
