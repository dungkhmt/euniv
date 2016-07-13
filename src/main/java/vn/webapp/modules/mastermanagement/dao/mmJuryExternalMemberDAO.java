package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmJuryExternalMember;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;

public interface mmJuryExternalMemberDAO {
	public List<mmJuryExternalMember> listJuryExternalMembers(String defenseSessionCode, String staffCode);
	
	public mmJuryExternalMember getAJuryExternalMemberByMemberAndDefenseSession(String DefenseSessionCode, String MemCode, String StaffCode);
	
	public int saveJuryExternalMember(mmJuryExternalMember juryExternalMember);
	
	public int removeAJuryExternalMember(mmJuryExternalMember JuryExternalMember);
}
