package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mProjectCallStatus;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;

public interface mProjectCallStatusService {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mProjectCallStatus> loadProjectCallStatusList();

	
}
