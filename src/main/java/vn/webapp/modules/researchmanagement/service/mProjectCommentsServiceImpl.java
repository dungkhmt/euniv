package vn.webapp.modules.researchmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.mProjectCommentsDAO;
import vn.webapp.modules.researchmanagement.model.mProjectComments;

@Service("mProjectCommentsService")
public class mProjectCommentsServiceImpl implements mProjectCommentsService {

	@Autowired
	private mProjectCommentsDAO projectCommentsDAO;

	/**
	 * Get a list Threads by user code
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mProjectComments> loadprojectCommentsList(){
		try {
			return projectCommentsDAO.loadprojectCommentsList();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public int saveAProjectComment(String sCommentCode, String sUserCode, String sCommentContent, String sProjectCode, String sCommentUpdatedDate, int iLock){
		try {
			if (sCommentContent != null) {
				mProjectComments projectComment = new mProjectComments();
				projectComment.setCOMPROJ_CODE(sCommentCode);
				projectComment.setCOMPROJ_COMMENT(sCommentContent);
				projectComment.setCOMPROJ_Lock(iLock);
				projectComment.setCOMPROJ_PRJCODE(sProjectCode);
				projectComment.setCOMPROJ_STAFFCODE(sUserCode);
				projectComment.setCOMPROJ_UpdateDate(sCommentUpdatedDate);
				projectCommentsDAO.saveAProjectComment(projectComment);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * load a project id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	@Override
	public mProjectComments loadAProjectCommentById(int iProjectCommentId){
		try {
			return projectCommentsDAO.loadAProjectCommentById(iProjectCommentId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public List<mProjectComments> loadAProjectCommentByProjectCode(String sProjectCode){
		try {
			return projectCommentsDAO.loadAProjectCommentByProjectCode(sProjectCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Edit a project call
	 * 
	 * @param String
	 * @param int
	 * @return null
	 */
	@Override
	public void editAProjectComment(int iProjectCommentId, String sCommentCode, String sUserCode, String sCommentContent, String sProjectCode, String sCommentUpdatedDate, int iLock){
		try {
			mProjectComments mProjectComment = projectCommentsDAO.loadAProjectCommentById(iProjectCommentId);
			if (mProjectComment != null) {
				mProjectComment.setCOMPROJ_CODE(sCommentCode);
				mProjectComment.setCOMPROJ_COMMENT(sCommentContent);
				mProjectComment.setCOMPROJ_Lock(iLock);
				mProjectComment.setCOMPROJ_PRJCODE(sProjectCode);
				mProjectComment.setCOMPROJ_STAFFCODE(sUserCode);
				mProjectComment.setCOMPROJ_UpdateDate(sCommentUpdatedDate);
				projectCommentsDAO.editAProjectComment(mProjectComment);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * Remove a project comment
	 * 
	 * @param int
	 * @return int
	 */
	@Override
	public int removeAProjectComment(int iProjectCommentId){
		mProjectComments mProjectComments = projectCommentsDAO.loadAProjectCommentById(iProjectCommentId);
		return projectCommentsDAO.removeAProjectComment(mProjectComments);
	}

}
