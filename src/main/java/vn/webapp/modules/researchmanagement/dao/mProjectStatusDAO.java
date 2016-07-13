package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;

public interface mProjectStatusDAO {
	/**
	 * 
	 * @return
	 */
	public List<mProjectStatus> getList();
    
	/**
	 * 
	 * @param projectStatusCode
	 * @return
	 */
    public mProjectStatus loadAProjectStatusByProjectCode(String projectStatusCode);
}
