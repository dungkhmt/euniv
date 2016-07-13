package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mProjectStatus;

public interface mProjectStatusService {
	/**
	 * 
	 * @return
	 */
	public List<mProjectStatus> list();
    
	/**
	 * 
	 * @param projectStatusCode
	 * @return
	 */
    public mProjectStatus loadAProjectStatusByProjectCode(String projectStatusCode);
    
}
