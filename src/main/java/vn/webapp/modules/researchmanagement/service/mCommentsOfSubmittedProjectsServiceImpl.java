package vn.webapp.modules.researchmanagement.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.libraries.DateUtil;
import vn.webapp.modules.researchmanagement.dao.DetailCommentSubmittedProjectsDAO;
//For comments of submitted projects 
import vn.webapp.modules.researchmanagement.dao.mCommentsOfSubmittedProjectsDAO;
import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;;


@Service("mCommentsOfSubmittedProjectsService")
public class mCommentsOfSubmittedProjectsServiceImpl implements mCommentsOfSubmittedProjectsService {

	@Autowired
	private mCommentsOfSubmittedProjectsDAO commentsOfSubmittedProjectsDAO;
	
	@Autowired
	private DetailCommentSubmittedProjectsDAO detailCommentSubmittedProjectsDAO; 

	/**
	 * 
	 */
	@Override
	public List<mCommentsOfSubmittedProjects> loadAllCommentsOfSubmittedProjects(){
		try {
			return commentsOfSubmittedProjectsDAO.loadAllCommentsOfSubmittedProjects();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<mCommentsOfSubmittedProjects> loadCommentsOfSubmittedProjectByProjectCode(String COMPROJ_PRJCODE){
		try {
			return commentsOfSubmittedProjectsDAO.loadCommentsOfSubmittedProjectByProjectCode(COMPROJ_PRJCODE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 */
	@Override
	public int saveCommentsOfSubmittedProjects(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE, String COMPROJ_COMMENT, String COMPROJ_UpdateDate, boolean COMPROJ_Lock){
		try {
			if (COMPROJ_STAFFCODE != null && COMPROJ_PRJCODE != null) {
				mCommentsOfSubmittedProjects commentsOfSubmittedProjects = new mCommentsOfSubmittedProjects();
				
				commentsOfSubmittedProjects.setCOMPROJ_CODE(COMPROJ_PRJCODE + "-" + COMPROJ_STAFFCODE);
				commentsOfSubmittedProjects.setCOMPROJ_PRJCODE(COMPROJ_PRJCODE);
				commentsOfSubmittedProjects.setCOMPROJ_STAFFCODE(COMPROJ_STAFFCODE);
				commentsOfSubmittedProjects.setCOMPROJ_COMMENT(COMPROJ_COMMENT);
				commentsOfSubmittedProjects.setCOMPROJ_UpdateDate(COMPROJ_UpdateDate);
				commentsOfSubmittedProjects.setCOMPROJ_Lock(COMPROJ_Lock);
				
				
				int iInsertResult = commentsOfSubmittedProjectsDAO.saveCommentsOfSubmittedProjects(commentsOfSubmittedProjects);
				
				return iInsertResult;
			}else{
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * 
	 */
	public int submitCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects){
		try {
			commentsOfSubmittedProjects.setCOMPROJ_Lock(true);
			commentsOfSubmittedProjectsDAO.editCommentsOfSubmittedProjects(commentsOfSubmittedProjects);
				
			return 1;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}
	/**
	 * 
	 */
	@Override
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectsById(int iCommentsOfSubmittedProjectsId){
		try {
			return commentsOfSubmittedProjectsDAO.loadCommentsOfSubmittedProjectsById(iCommentsOfSubmittedProjectsId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectByStaffCodeProjectCode(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE){
		try {
			return commentsOfSubmittedProjectsDAO.loadCommentsOfSubmittedProjectByStaffCodeProjectCode(COMPROJ_STAFFCODE, COMPROJ_PRJCODE);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void editCommentsOfSubmittedProjects(int COMPROJ_ID, String COMPROJ_COMMENT){
		try {
			mCommentsOfSubmittedProjects commentsOfSubmittedProjects = commentsOfSubmittedProjectsDAO.loadCommentsOfSubmittedProjectsById(COMPROJ_ID);
			//System.out.println("Comments at service " + commentsOfSubmittedProjects.getCOMPROJ_COMMENT());
			
			if (commentsOfSubmittedProjects != null) {
				commentsOfSubmittedProjects.setCOMPROJ_COMMENT(COMPROJ_COMMENT);
				
				commentsOfSubmittedProjectsDAO.editCommentsOfSubmittedProjects(commentsOfSubmittedProjects);
			}
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * 
	 */
	@Override
	public int deleteCommentsOfSubmittedProjects(int COMPROJ_ID){
		mCommentsOfSubmittedProjects commentsOfSubmittedProjects = commentsOfSubmittedProjectsDAO.loadCommentsOfSubmittedProjectsById(COMPROJ_ID);
		return commentsOfSubmittedProjectsDAO.deleteCommentsOfSubmittedProjects(commentsOfSubmittedProjects);
	}
	
	
	/**
	 * 
	 */
	@Override
	public int saveDetailsCommentsOfSubmittedProjects(String StaffCode, String PRJCode, String PRJCallCode, int Eval_Motivation, int Eval_Innovation, int Eval_Applicability,int Eval_RearchMethodology,
													     int Eval_ResearchContent, int Eval_Paper, int Eval_Product,
													     int Eval_Patent, int Eval_Graduate_Student, int Eval_Young_Rearcher,
													     int Eval_Education_Graduate, int Eval_Reasonable_Budget, String Eval_Classification,String Eval_Conclusion, int projectId)
	{
		try {
			if (!"".equals(StaffCode) && !"".equals(PRJCode) && projectId > 0) {
				DetailCommentSubmittedProjects detailCommentSubmittedProjects = detailCommentSubmittedProjectsDAO.loadDetailsCommentsOfSubmittedProjectsByProjectCode(StaffCode, PRJCode);
				//if(detailCommentSubmittedProjects.getCMTSUBPRJ_PRJCode() != null)
				if(detailCommentSubmittedProjects != null)
				{
					detailCommentSubmittedProjects.setCMTSUBPRJ_StaffCode(StaffCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_PRJCode(PRJCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_PRJCallCode(PRJCallCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Motivation(Eval_Motivation);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Innovation(Eval_Innovation);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Applicability(Eval_Applicability);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_RearchMethodology(Eval_RearchMethodology);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_ResearchContent(Eval_ResearchContent);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Paper(Eval_Paper);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Product(Eval_Product);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Patent(Eval_Patent);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Graduate_Student(Eval_Graduate_Student);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Young_Rearcher(Eval_Young_Rearcher);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Education_Graduate(Eval_Education_Graduate);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Reasonable_Budget(Eval_Reasonable_Budget);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Classification(Eval_Classification);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Conclusion(Eval_Conclusion);
				
					detailCommentSubmittedProjectsDAO.editDetailsCommentsOfSubmittedProjects(detailCommentSubmittedProjects);
					return 1;
				}else{
					detailCommentSubmittedProjects = new DetailCommentSubmittedProjects();
					detailCommentSubmittedProjects.setCMTSUBPRJ_StaffCode(StaffCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_PRJCode(PRJCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_PRJCallCode(PRJCallCode);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Motivation(Eval_Motivation);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Innovation(Eval_Innovation);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Applicability(Eval_Applicability);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_RearchMethodology(Eval_RearchMethodology);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_ResearchContent(Eval_ResearchContent);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Paper(Eval_Paper);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Product(Eval_Product);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Patent(Eval_Patent);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Graduate_Student(Eval_Graduate_Student);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Young_Rearcher(Eval_Young_Rearcher);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Education_Graduate(Eval_Education_Graduate);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Reasonable_Budget(Eval_Reasonable_Budget);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Classification(Eval_Classification);
					detailCommentSubmittedProjects.setCMTSUBPRJ_Eval_Conclusion(Eval_Conclusion);

					int iInsertResult = detailCommentSubmittedProjectsDAO.saveDetailsCommentsOfSubmittedProjects(detailCommentSubmittedProjects);
					return iInsertResult;
				}
				
			}else{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public DetailCommentSubmittedProjects loadDetailsCommentsOfSubmittedProjectsByProjectCode(String sStafftCode, String sProjectCode){
		try {
			return detailCommentSubmittedProjectsDAO.loadDetailsCommentsOfSubmittedProjectsByProjectCode(sStafftCode, sProjectCode);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param iCommentsOfSubmittedProjectsId
	 * @return
	 */
	public List<DetailCommentSubmittedProjects> loadListDetailsCommentsOfSubmittedProjectsByProjectCode(String sProjectCode){
		try {
			return detailCommentSubmittedProjectsDAO.loadListDetailsCommentsOfSubmittedProjectsByProjectCode(sProjectCode);
		} catch (Exception e) {
			return null;
		}
	}
}
