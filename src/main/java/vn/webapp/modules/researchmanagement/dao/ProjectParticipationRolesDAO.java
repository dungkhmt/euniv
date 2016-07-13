package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;

public interface ProjectParticipationRolesDAO {
	/**
	 * 
	 * @return
	 */
	public List<ProjectParticipationRoles> getList();
    
	/**
	 * 
	 * @param projectStatusCode
	 * @return
	 */
    public ProjectParticipationRoles loadAProjectParticipationRoleByCode(String sCode);
}
