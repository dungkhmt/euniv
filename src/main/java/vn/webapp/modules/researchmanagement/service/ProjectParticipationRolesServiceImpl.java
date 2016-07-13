package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.ProjectParticipationRolesDAO;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;

@Service("ProjectParticipationRolesService")
public class ProjectParticipationRolesServiceImpl implements ProjectParticipationRolesService {
	
	@Autowired
    private ProjectParticipationRolesDAO projectParticipationRolesDAO;

    /**
     * Get all list of project statuses
     * @param null
     * @return List
     */
    @Override
    public List<ProjectParticipationRoles> getList(){
    	return projectParticipationRolesDAO.getList();
    }

    /**
     * 
     */
    @Override
    public ProjectParticipationRoles loadAProjectParticipationRolesByCode(String sCode){
    	try {
			if (sCode != null) {
				return projectParticipationRolesDAO.loadAProjectParticipationRoleByCode(sCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    }
}
