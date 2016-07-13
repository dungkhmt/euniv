package vn.webapp.modules.mastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import vn.webapp.modules.mastermanagement.dao.mmJuryMemberDAO;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;

import java.util.ArrayList;
import java.util.List;



@Service("mmJuryMemberService")
public class mmJuryMemberServiceImpl implements mmJuryMemberService{
	@Autowired
	private mmJuryMemberDAO mmjuryMemberDAO;
	
	@Override
	public List<mmJuryMember> listJuryMembers(String defenseSessionCode, String staffCode){
		try{
			return mmjuryMemberDAO.listJuryMembers(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JuryMemberService::listJuryMembers(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<mmJuryMember> listJuryMembers(String staffCode){
		try{
			return mmjuryMemberDAO.listJuryMembers(staffCode);
		}catch(Exception e){
			System.out.println("JuryMemberService::listJuryMembers(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	public mmJuryMember getAJuryMemberByMemberAndDefenseSession(String DefenseSessionCode, String sJuryMemberMemCode, String sStaffCode){
		return mmjuryMemberDAO.getAJuryMemberByMemberAndDefenseSession(DefenseSessionCode, sJuryMemberMemCode, sStaffCode);
	}
	
	@Override
	public int saveAJuryMember(String juryMemberCode, String defenseSessionCode, String userCode){
		mmJuryMember juryMember = new mmJuryMember();
		juryMember.setJuryMem_MemberCode(juryMemberCode);
		juryMember.setJuryMem_DefenseSessionCode(defenseSessionCode);
		juryMember.setJuryMem_StaffCode(userCode);
		juryMember.setJuryMem_Code(defenseSessionCode + "-" + userCode + "-"+juryMemberCode); 
		return mmjuryMemberDAO.saveJuryMember(juryMember);
	}
	
	
	/**
     * 
     */
    @Override
    public int removeAJuryMember(String userCode, String sJuryMemberCode){
    	try{
    		mmJuryMember oCheckExistingRecord = mmjuryMemberDAO.getAJuryMemberByCode(sJuryMemberCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return mmjuryMemberDAO.removeAJuryMemberByCode(oCheckExistingRecord);
    		}
    		return 0;
	}catch(Exception exception)
	{
		System.out.println("Exception: " + exception.getMessage());
		return 0;
	}
    }
	
}
