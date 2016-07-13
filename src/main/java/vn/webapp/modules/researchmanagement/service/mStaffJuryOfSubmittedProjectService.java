package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;

public interface mStaffJuryOfSubmittedProjectService {
	
	/**
	 * Loading juries of all announced project calls (getting all records from table : tblstaffjurysubmittedprojects) 
	 * @return : List of objects of JuryOfAnnouncedProjectCall
	 */
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProject();
	
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProjectByJuryCode(String juryCode);
	
	/**
	 * 
	 * @param STFJUPRJ_STAFFJURCODE
	 * @return
	 */
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByStaffCode(String STFJUPRJ_STAFFJURCODE);
	
	/**
	 * Loading a jury of announced project call (getting a record from table : tblstaffjurysubmittedprojects)
	 * @param JUPRJROL_ID
	 * @return
	 */
	public mStaffJuryOfSubmittedProject loadAStaffJuryOfSubmittedProjectById(int STFJUPRJ_ID);
	
	/**
	 * Saving a staff jury of submitted project (saving a record in table: tblstaffjurysubmittedprojects )
	 * @param STFJUPRJ_STAFFJURCODE
	 * @param STFJUPRJ_PRJCODE
	 * @return
	 */
	public int saveStaffJuryOfSubmittedProject(String STFJUPRJ_STAFFJURCODE,String STFJUPRJ_PRJCODE, 
			String selectedProjectCallCode, String STFJUPRJ_JURY_CODE);

	/**
	 * Editing a staff jury of submitted project  (editing a record in table: tblstaffjurysubmittedprojects )
	 * @param STFJUPRJ_ID
	 * @param STFJUPRJ_STAFFJURCODE
	 * @param STFJUPRJ_PRJCODE
	 */
	public void editStaffJuryOfSubmittedProject(int STFJUPRJ_ID, String STFJUPRJ_STAFFJURCODE,String STFJUPRJ_PRJCODE);
	
	/**
	 * Deleting a staff jury of submitted project by its id (deleting a record from its id in table: tblstaffjurysubmittedprojects)
	 * @param STFJUPRJ_ID
	 * @return
	 */
	public int deleteStaffJuryOfSubmittedProject(int STFJUPRJ_ID);
	
	/**
	 * 
	 * @param projectCallCode
	 * @return
	 */
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByProjectCallCode(String projectCallCode);
}
