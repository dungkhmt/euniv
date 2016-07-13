package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;;

public interface mCommentsOfSubmittedProjectsDAO{
	
	/**
	 * 
	 * @return
	 */
	public List<mCommentsOfSubmittedProjects> loadAllCommentsOfSubmittedProjects();

	/**
	 * 
	 * @param COMPROJ_STAFFCODE
	 * @return
	 */
	public List<mCommentsOfSubmittedProjects> loadListCommentsOfSubmittedProjectsByStaffCode(String COMPROJ_STAFFCODE);
	
	/**
	 * 
	 * @param COMPROJ_STAFFCODE
	 * @param COMPROJ_PRJCODE
	 * @return
	 */
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectByStaffCodeProjectCode(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE);

	public List<mCommentsOfSubmittedProjects> loadCommentsOfSubmittedProjectByProjectCode(String COMPROJ_PRJCODE);

	/**
	 * 
	 * @param commentsOfSubmittedProjects
	 * @return
	 */
	public int saveCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects); 

	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectsById(int iCommentsOfSubmittedProjectsId);
	

	/**
	 * 
	 * @param commentsOfSubmittedProjects
	 */
	public void editCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects);
	
	/**
	 * 
	 * @param iProjectCallId
	 * @return
	 */
	public int deleteCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects);
	
}
