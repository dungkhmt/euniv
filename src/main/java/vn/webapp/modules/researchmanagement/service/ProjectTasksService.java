package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.ProjectTasks;

public interface ProjectTasksService {
	/**
	 * 
	 * @return
	 */
	public List<ProjectTasks> getList();
    
	/**
	 * 
	 * @param projectStatusCode
	 * @return
	 */
    public ProjectTasks loadAProjectTaskByCode(String sCode);
    
    /**
     * 
     * @param sProjectCode
     * @return
     */
    public List<ProjectTasks> loadAProjectTaskByProjectCode(String sProjectCode);
    
    /**
     * 
     * @param sProjectCode
     * @return
     */
    public List<List<String>> getProjectTaskByProjectCode(String sProjectCode);
    
}
