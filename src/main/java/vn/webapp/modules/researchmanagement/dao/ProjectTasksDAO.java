package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;

public interface ProjectTasksDAO {
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
     * @param projectTask
     * @return
     */
    public int saveAProjectTask(ProjectTasks projectTask);
    
    /**
     * 
     * @param projectTask
     * @return
     */
    public int removeAProjectTask(ProjectTasks projectTask);
    
    public void editAProjectTask(ProjectTasks pt);
}
