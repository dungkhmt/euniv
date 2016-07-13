package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;;

public interface mJuryOfAnnouncedProjectCallDAO {
	
	/**
	 * Loading juries of all announced project calls (getting all records from table : tbljurysubmittedprojects) 
	 * @return : List of objects of JuryOfAnnouncedProjectCall
	 */
	public List<mJuryOfAnnouncedProjectCall> loadAllJuryOfAnnouncedProjectCall();
	
	/**
	 * Loading a jury of announced project call by its id (getting a record from table : tbljurysubmittedprojects)
	 * @param JUSUPRJ_ID
	 * @return
	 */
	public mJuryOfAnnouncedProjectCall loadAJuryOfAnnouncedProjectCallById(int JUSUPRJ_ID);
	
	/**
	 * Loading a list of juries of announced project calls whose presents are of code JUSUPRJ_STAFFCODE 
	 * (getting list of records from table : tbljurysubmittedprojects)
	 * @param JUSUPRJ_STAFFCODE
	 * @return
	 */
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByPresentCode(String JUSUPRJ_STAFFCODE);
	
	/**
	 * Saving a jury of announced project call (saving a record in table: tbljurysubmittedprojects )
	 * @param juryOfAnnouncedProjectCall
	 * @return
	 */
	public int saveJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall);
	
	/**
	 * Deleting a jury of announced project call (deleting a record from table : tbljurysubmittedprojects) 
	 * @param juryOfAnnouncedProjectCall
	 * @return
	 */
	public int deleteJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall);
	
	/**
	 * Editing a jury of announced project call (updating a record from table :tbljurysubmittedprojects)
	 * @param juryOfAnnouncedProjectCall
	 */
	public void editJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall);
	
	/**
	 * 
	 * @param projectCallCode
	 * @return
	 */
	
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByJuryCode(String juryCode);
	
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByProjectCallCode(String projectCallCode);
	
	
	/**
	 * 
	 * @param projectCallCode
	 * @return
	 */
	public mJuryOfAnnouncedProjectCall loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(String projectCallCode, String staffCode);
}
