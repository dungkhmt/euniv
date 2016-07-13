package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;

public interface DetailCommentSubmittedProjectsDAO{

	/**
	 * 
	 * @param commentsOfSubmittedProjects
	 * @return
	 */
	public int saveDetailsCommentsOfSubmittedProjects(DetailCommentSubmittedProjects commentsOfSubmittedProjects); 

	/**
	 * 
	 * @param commentsOfSubmittedProjects
	 */
	public void editDetailsCommentsOfSubmittedProjects(DetailCommentSubmittedProjects commentsOfSubmittedProjects);
	
	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public DetailCommentSubmittedProjects loadDetailsCommentsOfSubmittedProjectsByProjectCode(String sStaffCode, String sProjectCode);
	
	public List<DetailCommentSubmittedProjects> loadAll();
	
	public List<DetailCommentSubmittedProjects> loadByProjectCode(String projectCode);
	
	/**
	 * 
	 * @param sProjectCode
	 * @return
	 */
	public List<DetailCommentSubmittedProjects> loadListDetailsCommentsOfSubmittedProjectsByProjectCode(String sProjectCode);

}
