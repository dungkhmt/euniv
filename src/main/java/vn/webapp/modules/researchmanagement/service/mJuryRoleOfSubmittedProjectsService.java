package vn.webapp.modules.researchmanagement.service;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;

public interface mJuryRoleOfSubmittedProjectsService{
	
	/**
	 * 
	 * @param null
	 * @return List
	 */
	public List<mJuryRoleOfSubmittedProjects> loadAllJuryRoleOfSubmittedProjects();


	/**
	 * 
	 * @param String
	 * @param String
	 * @return int
	 */
	//public int saveJuryRoleOfSubmittedProject(String JUSUPRJ_STAFFCODE, String JUSUPRJ_PRJCALLCODE, String JUPSURJ_ROLECODE);
	
}
