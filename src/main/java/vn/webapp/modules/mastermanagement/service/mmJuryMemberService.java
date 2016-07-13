package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;


public interface mmJuryMemberService {
	List<mmJuryMember> listJuryMembers(String defenseSessionCode, String staffCode);
	List<mmJuryMember> listJuryMembers(String staffCode);
	int saveAJuryMember(String juryMemberCode, String defenseSessionCode, String userCode);
	public mmJuryMember getAJuryMemberByMemberAndDefenseSession(String DefenseSessionCode, String sJuryMemberMemCode, String sStaffCode);
	public int removeAJuryMember(String userCode, String sJuryMemberCode);
}
