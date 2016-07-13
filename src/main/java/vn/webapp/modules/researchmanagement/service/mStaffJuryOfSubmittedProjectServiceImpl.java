package vn.webapp.modules.researchmanagement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.dao.mStaffJuryOfSubmittedProjectDAO;
import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;


@Service("mStaffJuryOfSubmittedProjectService")
public class mStaffJuryOfSubmittedProjectServiceImpl implements mStaffJuryOfSubmittedProjectService {
	@Autowired
	private mStaffJuryOfSubmittedProjectDAO staffJuryOfSubmittedProjectDAO;
	/**
	 * 
	 */
	@Override
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProject() {
		try {
			return staffJuryOfSubmittedProjectDAO.loadAllStaffJuryOfSubmittedProject();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProjectByJuryCode(String juryCode){
		return staffJuryOfSubmittedProjectDAO.loadAllStaffJuryOfSubmittedProjectByJuryCode(juryCode);
	}
	/**
	 * 
	 */
	public mStaffJuryOfSubmittedProject loadAStaffJuryOfSubmittedProjectById(int STFJUPRJ_ID){
		try {
			return staffJuryOfSubmittedProjectDAO.loadAStaffJuryOfSubmittedProjectById(STFJUPRJ_ID);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByStaffCode(String STFJUPRJ_STAFFJURCODE){
		try {
			return staffJuryOfSubmittedProjectDAO.loadListStaffJuryOfSubmittedProjectByStaffCode(STFJUPRJ_STAFFJURCODE);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 */
	@Override
	public int saveStaffJuryOfSubmittedProject(String STFJUPRJ_STAFFJURCODE,String STFJUPRJ_PRJCODE, 
			String selectedProjectCallCode, String STFJUPRJ_JURY_CODE) {
		if (STFJUPRJ_STAFFJURCODE.length() >= 1 && STFJUPRJ_PRJCODE.length() >= 1) {
			
			mStaffJuryOfSubmittedProject staffJury = staffJuryOfSubmittedProjectDAO.loadAStaffJuryOfSubmittedProjectByStaffAndProjectCode(STFJUPRJ_STAFFJURCODE, STFJUPRJ_PRJCODE);
			if(staffJury != null)
			{
				return 1;
			}else{
				staffJury = new mStaffJuryOfSubmittedProject();
				staffJury.setSTFJUPRJ_PRJCODE(STFJUPRJ_PRJCODE);
				staffJury.setSTFJUPRJ_STAFFJURCODE(STFJUPRJ_STAFFJURCODE);
				staffJury.setSTFJUPRJ_CODE(STFJUPRJ_PRJCODE + "-" + STFJUPRJ_STAFFJURCODE);
				staffJury.setSTFJUPRJ_PRJCALLCODE(selectedProjectCallCode);
				staffJury.setSTFJUPRJ_JURY_CODE(STFJUPRJ_JURY_CODE);
				int resultSaveStaffJuryOfSubmittedProject = staffJuryOfSubmittedProjectDAO.saveStaffJuryOfSubmittedProject(staffJury);
				return resultSaveStaffJuryOfSubmittedProject;
			}
		}
		return 0;
	}
	
	/**
	 * 
	 */
	public void editStaffJuryOfSubmittedProject(int STFJUPRJ_ID, String STFJUPRJ_STAFFJURCODE,String STFJUPRJ_PRJCODE){
		try {
			mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject = staffJuryOfSubmittedProjectDAO.loadAStaffJuryOfSubmittedProjectById(STFJUPRJ_ID);
			
			if(staffJuryOfSubmittedProject != null){
				
				staffJuryOfSubmittedProject.setSTFJUPRJ_PRJCODE(STFJUPRJ_PRJCODE);
				staffJuryOfSubmittedProject.setSTFJUPRJ_STAFFJURCODE(STFJUPRJ_STAFFJURCODE);
				
				
				staffJuryOfSubmittedProjectDAO.editStaffJuryOfSubmittedProject(staffJuryOfSubmittedProject);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	public int deleteStaffJuryOfSubmittedProject(int STFJUPRJ_ID){
		if(STFJUPRJ_ID > 0){
			mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject = staffJuryOfSubmittedProjectDAO.loadAStaffJuryOfSubmittedProjectById(STFJUPRJ_ID);
			return staffJuryOfSubmittedProjectDAO.deleteStaffJuryOfSubmittedProject(staffJuryOfSubmittedProject);
		}
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByProjectCallCode(String projectCallCode){
		try {
			if(!"".equals(projectCallCode)){
				return staffJuryOfSubmittedProjectDAO.loadListStaffJuryOfSubmittedProjectByProjectCallCode(projectCallCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
}
