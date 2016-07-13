package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectComments;

public interface mProjectCommentsService {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mProjectComments> loadprojectCommentsList();

	/**
	 * 
	 * @param sPROJCALL_CODE
	 * @param PROJCALL_PROJCATCODE
	 * @param PROJCALL_NAME
	 * @param PROJCALL_DATE
	 * @return
	 */
	public int saveAProjectComment(String sCommentCode, String sUserCode, String sCommentContent, String sProjectCode, String sCommentUpdatedDate, int iLock);

	/**
	 * 
	 * @return
	 */
	public mProjectComments loadAProjectCommentById(int iProjectCommentId);
	
	/**
	 * 
	 * @return
	 */
	public List<mProjectComments> loadAProjectCommentByProjectCode(String sProjectCode);

	/**
	 * 
	 * @param iProjectCallId
	 * @param sPROJCALL_CODE
	 * @param PROJCALL_PROJCATCODE
	 * @param PROJCALL_NAME
	 * @param PROJCALL_DATE
	 */
	public void editAProjectComment(int iProjectCommentId, String sCommentCode, String sUserCode, String sCommentContent, String sProjectCode, String sCommentUpdatedDate, int iLock);
	
	/**
	 * 
	 * @param threadId
	 * @return
	 */
	public int removeAProjectComment(int iProjectCommentId);
	
}
