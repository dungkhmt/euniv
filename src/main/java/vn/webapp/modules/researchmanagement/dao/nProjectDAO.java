package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.model.xProjects;

public interface nProjectDAO {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mThreads> loadThreadsListByStaff(String userRole, String userCode);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<Projects> loadProjectsListByStaff(String userRole, String userCode);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<Projects> loadProjectsListByStaffAndStatus(String userRole, String userCode, String status);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<Projects> loadApproveProjectsList(String userRole, String userCode);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<Projects> loadSubmittedProjectsListByStaff(String userRole, String userCode);
	
	/**
	 * 
	 * @param PROJ_Code
	 * @return
	 */
	public List<Projects> loadListProjectsByCode(String PROJ_Code);

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param reportingrYear
	 * @return
	 */
	public List<mTopics> loadTopicListByYear(String userRole, String userCode, String reportingrYear);

	/**
	 * 
	 * @param reportingrYear
	 * @return
	 */
	public List<mTopics> loadTopicSummaryListByYear(String reportingrYear);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param iStartItem
	 * @param iNumberOfItems
	 * @param sThreadStatus
	 * @param sThreadCategory
	 * @param sThreadYear
	 * @param sThreadFaculty
	 * @param sThreadDepartment
	 * @param sThreadStaff
	 * @return
	 */
	public List<mThreads> filerThreadsList(String userRole, String userCode, Integer iStartItem, Integer iNumberOfItems, String sThreadStatus, String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param sThreadStatus
	 * @param sThreadCategory
	 * @param sThreadYear
	 * @param sThreadFaculty
	 * @param sThreadDepartment
	 * @param sThreadStaff
	 * @return
	 */
	public List<mThreads> listAll();

	public List<mThreads> filerThreadsList(String userRole, String userCode, String sThreadStatus, String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param sThreadStatus
	 * @param sThreadCategory
	 * @param sThreadYear
	 * @param sThreadFaculty
	 * @param sThreadDepartment
	 * @param sThreadStaff
	 * @return
	 */
	public int countItems(String userRole, String userCode, String sThreadStatus, String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff);

	/**
	 * 
	 * @param thread
	 * @return
	 */
	public int saveAThread(mThreads thread); 
	
	
	/**
	 * 
	 * @param project
	 * @return
	 */
	public int saveAProject(Projects project); 

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param threadId
	 * @return
	 */
	public mThreads loadAThreadByIdAndUserCode(String userRole, String userCode, int threadId);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param projectId
	 * @return
	 */
	public Projects loadAProjectByIdAndUserCode(String userRole, String userCode, int projectId);
	
	/**
	 * Loading a sumitted project by id
	 * @param userRole
	 * @param userCode
	 * @param projectId
	 * @return
	 */
	public Projects loadASumittedProjectByIdAndUserCode(String userRole, String userCode, int projectId);
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public Projects loadProjectById(int projectId);

	/**
	 * 
	 * @param thread
	 */
	public void editAThread(mThreads thread);
	
	/**
	 * 
	 * @param project
	 */
	public void editAProject(Projects project);

	/**
	 * 
	 * @param threadId
	 * @return
	 */
	public int removeAThread(int threadId);
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public int removeAProject(int projectId);
	
	/**
	 * 
	 * @param threadCategory
	 * @param threadStatus
	 * @param threadStaff
	 * @param yearForGenerating
	 * @return
	 */
	public List<mThreads> loadThreadsListForReporting(String threadCategory, String threadStatus, String threadStaff, String yearForGenerating);
	
	/**
	 * 
	 * @param PROJ_PRJCall_Code
	 * @return
	 */
	public List<Projects> loadProjectByProjectCallId(String PROJ_PRJCall_Code);
	
	public List<Projects> loadProjectByProjectCallAndFaculty(String projectCallCode, String facutyCode);
	
	/**
	 * 
	 * @param PROJ_Code
	 * @return
	 */
	public List<xProjects> loadListSubmittedProjectsForSummary();

}
