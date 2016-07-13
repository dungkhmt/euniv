package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.usermanagement.model.mStaff;

public interface mJuryOfAnnouncedProjectCallService {
	
	/**
	 * Loading juries of all announced project calls (getting all records from table : tbljurysubmittedprojects) 
	 * @return : List of objects of JuryOfAnnouncedProjectCall
	 */
	public List<mJuryOfAnnouncedProjectCall> loadAllJuryOfAnnouncedProjectCall();
	
	/**
	 * Loading a jury of announced project call (getting a record from table : tbljurysubmittedprojects)
	 * @param iProjectCallId
	 * @return
	 */
	public mJuryOfAnnouncedProjectCall loadAJuryOfAnnouncedProjectCallById(int JUSUPRJ_ID);
	
	/**
	 * Loading a list of juries of announced project calls whose presents are of code JUSUPRJ_STAFFCODE 
	 * @param JUSUPRJ_STAFFCODE
	 * @return
	 */
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByPresentCode(String JUSUPRJ_STAFFCODE);
	/**
	 * Saving a jury of announced project call (saving a record in table: tbljurysubmittedprojects )
	 * @param JUSUPRJ_STAFFCODE
	 * @param JUSUPRJ_PRJCALLCODE
	 * @param JUPSURJ_ROLECODE
	 * @return
	 */
	public int saveJuryOfAnnouncedProjectCall(String JUSUPRJ_STAFFCODE,String JUSUPRJ_PRJCALLCODE,String JUPSURJ_ROLECODE, String JUSUPRJ_JURYRESEARCHPROJECTCODE);

	/**
	 * Editing a jury of announced project call (editing a record in table: tbljurysubmittedprojects )
	 * @param JUSUPRJ_STAFFCODE
	 * @param JUSUPRJ_PRJCALLCODE
	 * @param JUPSURJ_ROLECODE
	 */
	public void editJuryOfAnnouncedProjectCall(int JUSUPRJ_ID, String JUSUPRJ_STAFFCODE,String JUSUPRJ_PRJCALLCODE,String JUPSURJ_ROLECODE);
	
	public void editJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall jpc);
	
	/**
	 * Deleting a jury of announced project call by its id (deleting a record from its id in table: tbljurysubmittedprojects)
	 * @param JUSUPRJ_ID
	 * @return
	 */
	public int deleteJuryOfAnnouncedProjectCall(int JUSUPRJ_ID);
	
	/**
	 * list all staffs of the jury of a specified project call
	 * @param projectCallCode
	 * @return
	 */
	public List<mStaff> loadStaffsOfJuryOfAProjecCall(String projectCallCode);
	
	public List<mStaff> loadStaffsOfJuryOfAJuryResearchProjec(String juryCode);
	
	/**
	 * 
	 * @param projectCallCode
	 * @return
	 */
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByProjectCallCode(String projectCallCode);
	
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByJuryCode(String juryCode);
	
	/*
	 * 
	 */
	public mJuryOfAnnouncedProjectCall loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(String projectCallCode, String staffCode);
	
}
