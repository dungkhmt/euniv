package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProjectComments;

public interface mProjectCommentsDAO {

	/**
	 * 
	 * @return
	 */
	public List<mProjectComments> loadprojectCommentsList();

	/**
	 * 
	 * @param projectComment
	 * @return
	 */
	public int saveAProjectComment(mProjectComments projectComment);

	/**
	 * 
	 * @param iProjectCommentId
	 * @return
	 */
	public mProjectComments loadAProjectCommentById(int iProjectCommentId);
	
	/**
	 * 
	 * @param sProjectCode
	 * @return
	 */
	public List<mProjectComments> loadAProjectCommentByProjectCode(String sProjectCode);

	/**
	 * 
	 * @param projectComment
	 */
	public void editAProjectComment(mProjectComments projectComment);

	/**
	 * 
	 * @param projectComment
	 * @return
	 */
	public int removeAProjectComment(mProjectComments projectComment);
}
