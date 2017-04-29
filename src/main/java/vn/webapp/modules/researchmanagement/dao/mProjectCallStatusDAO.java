package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mProjectCallStatus;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;

public interface mProjectCallStatusDAO {
	/**
	 * 
	 * @return
	 */
	public List<mProjectCallStatus> loadProjectCallStatusList();

}
