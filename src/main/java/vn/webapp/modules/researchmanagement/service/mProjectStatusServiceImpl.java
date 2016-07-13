package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.mProjectStatusDAO;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;

@Service("mProjectStatusService")
public class mProjectStatusServiceImpl implements mProjectStatusService {
	
	@Autowired
    private mProjectStatusDAO projectStatusDAO;

    /**
     * Get all list of project statuses
     * @param null
     * @return List
     */
    @Override
    public List<mProjectStatus> list(){
    	return projectStatusDAO.getList();
    }

    /**
     * 
     */
    @Override
    public mProjectStatus loadAProjectStatusByProjectCode(String projectStatusCode){
    	try {
			if (projectStatusCode != null) {
				return projectStatusDAO.loadAProjectStatusByProjectCode(projectStatusCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    	
    }
}
