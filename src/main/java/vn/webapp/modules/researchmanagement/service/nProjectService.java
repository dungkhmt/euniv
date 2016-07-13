package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.ProjectsProjectResearchField;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.model.xProjects;

public interface nProjectService {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	
	public int saveAThread(mThreads thread); 
	
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
	public List<Projects> loadSubmittedProjectsListByStaff(String userRole, String userCode);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<Projects> loadApproveProjectsList(String userRole, String userCode);
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public Projects loadProjectsById(int projectId);
	
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
	public List<mTopics> loadTopicListByYear(String userRole, String userCode,
			String reportingrYear);

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
	 * @param sThreadStatus
	 * @param sThreadCategory
	 * @param sThreadYear
	 * @param sThreadFaculty
	 * @param sThreadDepartment
	 * @param sThreadStaff
	 * @return
	 */
	public List<mThreads> filerThreadsListNoPagination(String userRole, String userCode,
			String sThreadStatus,
			String sThreadCategory, String sThreadYear, String sThreadFaculty,
			String sThreadDepartment, String sThreadStaff);
	
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
	public List<mThreads> listAll();

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
	public int countItems(String userRole, String userCode, String sThreadStatus, String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff);

	/**
	 * 
	 * @param userCode
	 * @param threadName
	 * @param threadCategory
	 * @param threadContent
	 * @param threadStartDate
	 * @param threadEndDate
	 * @param threadMotivation
	 * @param threadReportingDate
	 * @param threadResult
	 * @param threadStatus
	 * @param threadBudget
	 * @param threadSourceUploadFileSrc
	 * @param threadCode
	 * @param listStaffs
	 * @param listStaffRoles
	 * @return
	 */
	public int saveAThread(String userCode, String threadName, String threadCategory, String threadContent, String threadStartDate, 
							String threadEndDate, String threadMotivation, String threadReportingDate, String threadResult, String threadStatus, 
							int threadBudget, String threadSourceUploadFileSrc, String threadCode, List<String> listStaffs, List<String> listStaffRoles);
	
	
	/**
	 * 
	 * @param userCode
	 * @param projectCallCode
	 * @param projectName
	 * @param projectContent
	 * @param projectMotivation
	 * @param projectResult
	 * @param projectBudget
	 * @return
	 */
	public int saveAProject(String userRole, String userCode,String projectCallCode,String projectName,
							String projectContent,String projectMotivation,String projectResult,int budgetMaterial, int totalBudget,
							String projectCode,String facultyAdd,String projectSurvey,String projectObjective,String startDate,
							String endDate, String projectCategory, String projectResearchFieldCode, String sourceFile, String[] projectResearchFieldCodeList);
	
	/**
	 * Saving member tasks for each project
	 * @param projectCode
	 * @param projectMembers
	 * @param projectMemberRole
	 * @param projectMemberTasks
	 * @param projectMemberWorkingDays
	 * @param projectMemberBudget
	 * @return
	 */
	public int saveMemberTasks(String projectCode, String[] projectMembers,String[] projectMemberRole,String[] projectMemberTasks,String[] projectMemberWorkingDays,String[] projectMemberBudget, String currentProjectCode);
	
	/**
	 * 
	 * @param sProjectCode
	 */
	public void removeProjectTasks(String sProjectCode);

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
	 * @param threadId
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
	 * @param userRole
	 * @param userCode
	 * @param threadName
	 * @param threadCategory
	 * @param threadContent
	 * @param threadStartDate
	 * @param threadEndDate
	 * @param threadMotivation
	 * @param threadReportingDate
	 * @param threadResult
	 * @param threadStatus
	 * @param threadBudget
	 * @param threadSourceUploadFileSrc
	 * @param threadCode
	 * @param threadId
	 * @param listStaffs
	 * @param listStaffRoles
	 */
	public void editAThread(String userRole, String userCode, String threadName, String threadCategory, String threadContent, 
							String threadStartDate, String threadEndDate, String threadMotivation, String threadReportingDate, String threadResult, 
							String threadStatus, int threadBudget, String threadSourceUploadFileSrc, String threadCode, int threadId, List<String> listStaffs, List<String> listStaffRoles);
	
	/**
	 * Sending a project to council , after this action project can not be changed.
	 * @param project
	 */
	public void sendAProject(Projects project, boolean editSumitted);

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param projectCallCode
	 * @param projectName
	 * @param projectContent
	 * @param projectMotivation
	 * @param projectResult
	 * @param projectBudget
	 * @param projectCode
	 * @param bEditSumittedProject
	 */
	public void editAProject(int projectId,String userRole,String userCode,String projectCallCode,String projectName,String projectContent, String projectMotivation,String projectResult,
								int budgetMaterial,String projectCode,String startDate,String endDate, String facultyAdd,String projectSurvey,String projectObjective, boolean bEditSumittedProject, 
								int totalBudget, String projectResearchFieldCode, String sourceFileUpload, String[] projectResearchFieldCodeList, String sendIt);
	
	/**
	 * 
	 * @param project
	 */
	public void editAnApproveProject(Projects project);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param threadId
	 * @param threadStatus
	 */
	public void editStatusThread(String userRole, String userCode, int threadId, String threadStatus);
	
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
	 * @param threadFaculty
	 * @param threadDepartment
	 * @param threadStaff
	 * @param yearForGenerating
	 * @return
	 */
	public List<mThreads> loadThreadsListForReporting(String threadCategory, String threadStatus, String threadFaculty, String threadDepartment, String threadStaff, String yearForGenerating);
	
	/**
	 * 
	 * @param facultyCode
	 * @return
	 */
	public String getFacultyName(String facultyCode);
	
	/**
	 * 
	 * @param departmentCode
	 * @return
	 */
	public String getDepartmentName(String departmentCode);
	
	/**
	 * Listing all projects by its project call code
	 * @param PROJ_PRJCall_Code
	 * @return
	 */
	public List<Projects> loadProjectByProjectCallId(String PROJ_PRJCall_Code);
	
	public List<Projects> loadProjectByProjectCallAndFaculty(String projectCallCode, String facutyCode);
	
	/**
	 * 
	 * @param projectCallCode
	 */
	public void generateProjectCodes(String projectCallCode);
	
	/**
	 * 
	 * @param PROJ_Code
	 * @return
	 */
	public List<xProjects> loadListSubmittedProjectsForSummary();
	
	/**
	 * 
	 * @param sProjectCode
	 * @return
	 */
	public List<ProjectsProjectResearchField> loadProjectsProjectResearchFieldListByProjectCode(String sProjectCode);
	
	/**
	 * 
	 * @param projectsProjectResearchField
	 * @return
	 */
	public int removeAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField);
}
