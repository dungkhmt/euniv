package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmJuryExternalMember;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;


public interface mmJuryExternalMemberService {
	public List<mmJuryExternalMember> listJuryExternalMembers(String defenseSessionCode, String staffCode);
	public mmJuryExternalMember getAJuryExternalMemberByMemberAndDefenseSession(String DefenseSessionCode, String MemCode, String StaffCode);
	public int saveAJuryExternalMember(String juryExternalMemberCode, String defenseSessionCode, String userCode);
	public int removeAJuryExternalMember(mmJuryExternalMember JuryExternalMember);
}
