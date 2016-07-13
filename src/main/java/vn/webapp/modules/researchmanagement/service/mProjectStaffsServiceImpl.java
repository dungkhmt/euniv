package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.mProjectStaffsDAO;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;

@Service("mProjectStaffsService")
public class mProjectStaffsServiceImpl implements mProjectStaffsService {
	
	@Autowired
    private mProjectStaffsDAO projectStaffsDAO;

    /**
     * Get 
     * @param null
     * @return List
     */
    @Override
    public List<mProjectStaffs> loadAProjectStaffByProjectCode(String projectStaffCode){
    	try {
			if (projectStaffCode != null) {
				return projectStaffsDAO.loadAProjectStaffByProjectCode(projectStaffCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    	
    }
}
