package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;

public interface mCommentsOfSubmittedProjectsService {
	/**
	 * 
	 */
	public List<mCommentsOfSubmittedProjects> loadAllCommentsOfSubmittedProjects();
	
	public List<mCommentsOfSubmittedProjects> loadCommentsOfSubmittedProjectByProjectCode(String COMPROJ_PRJCODE);
	
	/**
	 * 
	 * @param COMPROJ_STAFFCODE
	 * @param COMPROJ_PRJCODE
	 * @return
	 */
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectByStaffCodeProjectCode(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE);
	
	/**
	 * 
	 * @param COMPROJ_STAFFCODE
	 * @param COMPROJ_PRJCODE
	 * @param COMPROJ_COMMENT
	 * @param COMPROJ_UpdateDate
	 * @param COMPROJ_Lock
	 * @return
	 */
	public int saveCommentsOfSubmittedProjects(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE, String COMPROJ_COMMENT, String COMPROJ_UpdateDate, boolean COMPROJ_Lock); 

	/**
	 * 
	 * @param commentsOfSubmittedProjects
	 * @return
	 */
	public int submitCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects);
	
	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectsById(int iCommentsOfSubmittedProjectsId);
	
	/**
	 * Editing the comment of a comments of submitted projects
	 */
	public void editCommentsOfSubmittedProjects(int COMPROJ_ID, String COMPROJ_COMMENT);
	
	/**
	 * 
	 * @param COMPROJ_ID
	 * @return
	 */
	public int deleteCommentsOfSubmittedProjects(int COMPROJ_ID);
	
	
	/**
	 * 
	 * @param StaffCode
	 * @param PRJCode
	 * @param PRJCallCode
	 * @param Eval_Motivation
	 * @param Eval_Innovation
	 * @param Eval_Applicability
	 * @param Eval_RearchMethodology
	 * @param Eval_ResearchContent
	 * @param Eval_Paper
	 * @param Eval_Product
	 * @param Eval_Patent
	 * @param Eval_Graduate_Student
	 * @param Eval_Young_Rearcher
	 * @param Eval_Education_Graduate
	 * @param Eval_Reasonable_Budget
	 * @param Eval_Classification
	 * @param Eval_Conclusion
	 * @return
	 */
	public int saveDetailsCommentsOfSubmittedProjects(String StaffCode, String PRJCode, String PRJCallCode, int Eval_Motivation, int Eval_Innovation, int Eval_Applicability,int Eval_RearchMethodology,
													     int Eval_ResearchContent, int Eval_Paper, int Eval_Product,
													     int Eval_Patent, int Eval_Graduate_Student, int Eval_Young_Rearcher,
													     int Eval_Education_Graduate, int Eval_Reasonable_Budget, String Eval_Classification,String Eval_Conclusion, int projectId);
	
	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public DetailCommentSubmittedProjects loadDetailsCommentsOfSubmittedProjectsByProjectCode(String sStafftCode, String sProjectCode);
	
	/**
	 * 
	 * @param sProjectCode
	 * @return
	 */
	public List<DetailCommentSubmittedProjects> loadListDetailsCommentsOfSubmittedProjectsByProjectCode(String sProjectCode);
}
