package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;

public interface ProjectParticipationRolesService {
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
    public ProjectParticipationRoles loadAProjectParticipationRolesByCode(String sCode);
    
}
