package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.ProjectsProjectResearchField;

public interface ProjectsProjectResearchFieldDAO {
	
	 /**
     * 
     * @param sProjectCode
     * @return
     */
    public List<ProjectsProjectResearchField> loadProjectsProjectResearchFieldListByProjectCode(String sProjectCode);
    
	/**
     * 
     * @param projectTask
     * @return
     */
    public int saveAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField);
    
    /**
     * 
     * @param projectTask
     * @return
     */
    public int removeAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField);
    
    /**
     * 
     * @param pt
     */
    public void editAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField);
    
}
