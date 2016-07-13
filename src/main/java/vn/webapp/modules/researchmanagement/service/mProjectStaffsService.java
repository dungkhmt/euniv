package vn.webapp.modules.researchmanagement.service;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;

public interface mProjectStaffsService {
	/**
	 * 
	 * @param projectStaffCode
	 * @return
	 */
	public List<mProjectStaffs> loadAProjectStaffByProjectCode(String projectStaffCode);
}
