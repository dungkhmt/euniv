package vn.webapp.modules.researchmanagement.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.mJuryRoleOfSubmittedProjectsDAO;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProducts;


@Service("mJuryRoleSubmittedProjectsService")
public class mJuryRoleOfSubmittedProjectsServiceImpl implements mJuryRoleOfSubmittedProjectsService {
	@Autowired
	private mJuryRoleOfSubmittedProjectsDAO juryRoleOfSubmittedProjectsDAO;


	/**
	 * Get all jury roles of the submitted projects
	 * 
	 */
	@Override
	public List<mJuryRoleOfSubmittedProjects> loadAllJuryRoleOfSubmittedProjects() {
		try {
			return juryRoleOfSubmittedProjectsDAO.loadAllJuryRoleSubmittedProjects();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	

}
