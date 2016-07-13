package vn.webapp.modules.mastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import vn.webapp.modules.mastermanagement.dao.mmJuryExternalMemberDAO;
import vn.webapp.modules.mastermanagement.dao.mmJuryMemberDAO;
import vn.webapp.modules.mastermanagement.model.mmJuryExternalMember;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;

import java.util.ArrayList;
import java.util.List;



@Service("mmJuryExternalMemberService")
public class mmJuryExternalMemberServiceImpl implements mmJuryExternalMemberService{
	@Autowired
	private mmJuryExternalMemberDAO mmjuryExternalMemberDAO;
	
	@Override
	public List<mmJuryExternalMember> listJuryExternalMembers(String defenseSessionCode, String staffCode){
		return mmjuryExternalMemberDAO.listJuryExternalMembers(defenseSessionCode, staffCode);
	}
	
	@Override
	public mmJuryExternalMember getAJuryExternalMemberByMemberAndDefenseSession(String DefenseSessionCode, String MemCode, String StaffCode){
		return mmjuryExternalMemberDAO.getAJuryExternalMemberByMemberAndDefenseSession(DefenseSessionCode, MemCode, StaffCode);
	}
	
	@Override
	public int saveAJuryExternalMember(String juryExternalMemberCode, String defenseSessionCode, String userCode){
		mmJuryExternalMember juryMember = new mmJuryExternalMember();
		juryMember.setJEM_MemberCode(juryExternalMemberCode);
		juryMember.setJEM_DefenseSessionCode(defenseSessionCode);
		juryMember.setJEM_StaffCode(userCode);
		juryMember.setJEM_Code(defenseSessionCode + "-" + userCode + "-"+juryExternalMemberCode); 
		return mmjuryExternalMemberDAO.saveJuryExternalMember(juryMember);
	}
	
	@Override
	public int removeAJuryExternalMember(mmJuryExternalMember JuryExternalMember){
		return mmjuryExternalMemberDAO.removeAJuryExternalMember(JuryExternalMember);
	}
	
		
}
