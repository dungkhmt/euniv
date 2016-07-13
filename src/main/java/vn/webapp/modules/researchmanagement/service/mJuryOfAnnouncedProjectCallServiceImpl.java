package vn.webapp.modules.researchmanagement.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.dao.mJuryOfAnnouncedProjectCallDAO;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.usermanagement.dao.mStaffDAO;
import vn.webapp.modules.usermanagement.model.mStaff;




@Service("mJuryOfAnnouncedProjectCallService")
public class mJuryOfAnnouncedProjectCallServiceImpl implements mJuryOfAnnouncedProjectCallService {
	@Autowired
	private mJuryOfAnnouncedProjectCallDAO juryOfAnnouncedProjectCallDAO;

	@Autowired
	private mStaffDAO staffDAO;
	
	/**
	 * 
	 */
	@Override
	public List<mJuryOfAnnouncedProjectCall> loadAllJuryOfAnnouncedProjectCall() {
		try {
			return juryOfAnnouncedProjectCallDAO.loadAllJuryOfAnnouncedProjectCall();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	public mJuryOfAnnouncedProjectCall loadAJuryOfAnnouncedProjectCallById(int JUSUPRJ_ID){
		try {
			return juryOfAnnouncedProjectCallDAO.loadAJuryOfAnnouncedProjectCallById(JUSUPRJ_ID);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByPresentCode(String JUSUPRJ_STAFFCODE){
		try {
			return juryOfAnnouncedProjectCallDAO.loadListJuryOfAnnouncedProjectCallByPresentCode(JUSUPRJ_STAFFCODE);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int saveJuryOfAnnouncedProjectCall(String JUSUPRJ_STAFFCODE,String JUSUPRJ_PRJCALLCODE, String JUPSURJ_ROLECODE, String JUSUPRJ_JURYRESEARCHPROJECTCODE) {
		if (JUSUPRJ_STAFFCODE.length() >= 1 && JUSUPRJ_PRJCALLCODE.length() >= 1 && JUPSURJ_ROLECODE.length() >= 1) {
			
			mJuryOfAnnouncedProjectCall jury = new mJuryOfAnnouncedProjectCall();
			
			jury.setJUSUPRJ_STAFFCODE(JUSUPRJ_STAFFCODE);
			jury.setJUSUPRJ_PRJCALLCODE(JUSUPRJ_PRJCALLCODE);
			jury.setJUPSURJ_ROLECODE(JUPSURJ_ROLECODE);
			jury.setJUSUPRJ_JURYRESEARCHPROJECTCODE(JUSUPRJ_JURYRESEARCHPROJECTCODE);
			
			//System.out.println("Service");
			//System.out.println("Staff code : " + JUSUPRJ_STAFFCODE);
			//System.out.println("Project call code : " + JUSUPRJ_PRJCALLCODE);
			//System.out.println("Role code : " + JUPSURJ_ROLECODE);

			int resultSaveJuryOfAnnouncedProjectCall = juryOfAnnouncedProjectCallDAO.saveJuryOfAnnouncedProjectCall(jury);
			
			
			return resultSaveJuryOfAnnouncedProjectCall;
		}
		return 0;
	}
	
	public void editJuryOfAnnouncedProjectCall(int JUSUPRJ_ID, String JUSUPRJ_STAFFCODE,String JUSUPRJ_PRJCALLCODE,String JUPSURJ_ROLECODE){
		try {
			mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall = juryOfAnnouncedProjectCallDAO.loadAJuryOfAnnouncedProjectCallById(JUSUPRJ_ID);
			if(juryOfAnnouncedProjectCall != null){
				juryOfAnnouncedProjectCall.setJUSUPRJ_PRJCALLCODE(JUSUPRJ_PRJCALLCODE);
				juryOfAnnouncedProjectCall.setJUSUPRJ_STAFFCODE(JUSUPRJ_STAFFCODE);
				juryOfAnnouncedProjectCall.setJUPSURJ_ROLECODE(JUPSURJ_ROLECODE);
				
				juryOfAnnouncedProjectCallDAO.editJuryOfAnnouncedProjectCall(juryOfAnnouncedProjectCall);
			}
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void editJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall jpc){
		juryOfAnnouncedProjectCallDAO.editJuryOfAnnouncedProjectCall(jpc);
	}
	/**
	 * 
	 */
	public int deleteJuryOfAnnouncedProjectCall(int JUSUPRJ_ID){
		
		mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall = juryOfAnnouncedProjectCallDAO.loadAJuryOfAnnouncedProjectCallById(JUSUPRJ_ID);
		return juryOfAnnouncedProjectCallDAO.deleteJuryOfAnnouncedProjectCall(juryOfAnnouncedProjectCall);
	}
	
	/**
	 * 
	 * @return
	 */
	public String name(){
		return "mJuryOfAnnouncedProjectCallServiceImpl";
	}
	
	/**
	 * 
	 */
	public List<mStaff> loadStaffsOfJuryOfAJuryResearchProjec(String juryCode){
		List<mJuryOfAnnouncedProjectCall> juries = loadListJuryOfAnnouncedProjectCallByJuryCode(juryCode);
		List<mStaff> staffs = new ArrayList<mStaff>();
		List<mStaff> allStaffs = staffDAO.listStaffs();
		HashMap<String, mStaff> mCode2Staff = new HashMap<String, mStaff>();
		for(mStaff st: allStaffs)
			mCode2Staff.put(st.getStaff_Code(), st);
		for(mJuryOfAnnouncedProjectCall jury: juries){
			if(jury.getJUSUPRJ_JURYRESEARCHPROJECTCODE().equals(juryCode))
				staffs.add(mCode2Staff.get(jury.getJUSUPRJ_STAFFCODE()));
		}
		return staffs;
	}
	public List<mStaff> loadStaffsOfJuryOfAProjecCall(String projectCallCode){
		List<mStaff> staffs = new ArrayList<mStaff>();
		List<mStaff> allStaffs = staffDAO.listStaffs();
		List<mJuryOfAnnouncedProjectCall> allJuryOfAnnouncedProjectCallList = loadAllJuryOfAnnouncedProjectCall();
		HashSet<String> S = new HashSet<String>();
		for(mJuryOfAnnouncedProjectCall J: allJuryOfAnnouncedProjectCallList)
			if(J.getJUSUPRJ_PRJCALLCODE().equals(projectCallCode))
				S.add(J.getJUSUPRJ_STAFFCODE());
		System.out.println(name() + "::loadStaffOfJuryOfAProjectCall, projectCallCode = " + 
				projectCallCode + ", allJuryOfAnnoucedProjectCallList,sz = " + 
				allJuryOfAnnouncedProjectCallList.size() + ", S = " + S);
		for(mStaff st: allStaffs)
			if(S.contains(st.getStaff_Code())){
				staffs.add(st);
				System.out.println(name() + "::loadStaffOfJuryOfAProjectCall, add staff + " + st.getStaff_Code());
			}
		return staffs;
	}
	
	/**
	 * 
	 */
	
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByJuryCode(String juryCode){
		return juryOfAnnouncedProjectCallDAO.loadListJuryOfAnnouncedProjectCallByJuryCode(juryCode);
	}
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByProjectCallCode(String projectCallCode){
		try {
			if(!"".equals(projectCallCode))
			{
				return juryOfAnnouncedProjectCallDAO.loadListJuryOfAnnouncedProjectCallByProjectCallCode(projectCallCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	public mJuryOfAnnouncedProjectCall loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(String projectCallCode, String staffCode){
		try {
			if(!"".equals(projectCallCode) && !"".equals(staffCode))
			{
				return juryOfAnnouncedProjectCallDAO.loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(projectCallCode, staffCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
}
