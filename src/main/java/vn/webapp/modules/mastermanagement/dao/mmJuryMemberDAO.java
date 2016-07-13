package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmJuryMember;

public interface mmJuryMemberDAO {
	public List<mmJuryMember> listJuryMembers(String defenseSessionCode, String staffCode);
	
	public mmJuryMember getAJuryMemberByCode(String sJuryMemberCode, String sStaffCode);
	
	public mmJuryMember getAJuryMemberByMemberAndDefenseSession(String DefenseSessionCode, String sJuryMemberMemCode, String sStaffCode);
	
	public List<mmJuryMember> listJuryMembers(String staffCode);
	
	public int saveJuryMember(mmJuryMember juryMember);
	
	public int removeAJuryMemberByCode(mmJuryMember aJuryMember);
}
