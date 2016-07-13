package vn.webapp.modules.researchmanagement.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mAcademicYearDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectCategoryDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchmanagement.dao.DetailCommentSubmittedProjectsDAO;
import vn.webapp.modules.researchmanagement.dao.ProjectTasksDAO;
import vn.webapp.modules.researchmanagement.dao.ProjectsProjectResearchFieldDAO;
import vn.webapp.modules.researchmanagement.dao.mCommentsOfSubmittedProjectsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStaffsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStatusDAO;
import vn.webapp.modules.researchmanagement.dao.mStaffJuryOfSubmittedProjectDAO;
import vn.webapp.modules.researchmanagement.dao.nProjectDAO;
import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.ProjectsProjectResearchField;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.model.xProjects;
import vn.webapp.modules.usermanagement.dao.mDepartmentDAO;
import vn.webapp.modules.usermanagement.dao.mFacultyDAO;
import vn.webapp.modules.usermanagement.dao.mStaffDAO;
import vn.webapp.modules.usermanagement.dao.mUserDAO;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;

@Service("nProjectService")
public class nProjectServiceImpl implements nProjectService {
	@Autowired
	private tProjectDAO tProjectDAO;
	
	@Autowired
	private nProjectDAO projectDAO;

	@Autowired
	private tProjectCategoryDAO tProjectCategoryDAO;

	@Autowired
	private nProjectDAO threadDAO;

	@Autowired
	private mUserDAO userDAO;

	@Autowired
	private mProjectStaffsDAO projectStaffsDAO;

	@Autowired
	private mDepartmentDAO departmentDAO;

	@Autowired
	private mFacultyDAO facultyDAO;

	@Autowired
	private mProjectStatusDAO projectStatusDAO;

	@Autowired
	private mStaffDAO staffDAO;
	
	@Autowired
	private ProjectTasksDAO projectTasksDAO;

	@Autowired
	private mCommentsOfSubmittedProjectsDAO commentSubmittedProjectDAO;
	
	@Autowired
	private DetailCommentSubmittedProjectsDAO detailCommentsSubmittedProjectDAO;
	
	@Autowired
	private mStaffJuryOfSubmittedProjectDAO staffJurySubmittedProjectDAO;
	
	@Autowired
	private mAcademicYearDAO yearDAO;
	
	@Autowired
	private ProjectsProjectResearchFieldDAO projectsProjectResearchFieldDAO;
	
	static private String PROJECT_LEADER = "PROJECT_LEADER";
	
	static private String ROLE_USER = "ROLE_USER";

	/**
	 * Get a list Threads by user code
	 * 
	 * @param String
	 * @return object
	 */
	
	public int saveAThread(mThreads thread){
		return projectDAO.saveAThread(thread);
	}
	@Override
	public List<mThreads> loadThreadsListByStaff(String userRole, String userCode) {
		try {

			return threadDAO.loadThreadsListByStaff(userRole, userCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Projects> loadListProjectsByCode(String PROJ_Code){
		try {
			return projectDAO.loadListProjectsByCode(PROJ_Code);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	/**
	 * Get a list Projects by user code
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<Projects> loadProjectsListByStaff(String userRole, String userCode) {
		try {
			return threadDAO.loadProjectsListByStaff(userRole, userCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list Threads by user code and status
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<Projects> loadProjectsListByStaffAndStatus(String userRole, String userCode, String status)
	{
		try {
			return projectDAO.loadProjectsListByStaffAndStatus(userRole, userCode, status);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list Threads by user code and status
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<Projects> loadApproveProjectsList(String userRole, String userCode)
	{
		try {
			return projectDAO.loadApproveProjectsList(userRole, userCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public List<Projects> loadSubmittedProjectsListByStaff(String userRole, String userCode) {
		try {
			return threadDAO.loadSubmittedProjectsListByStaff(userRole, userCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 
	 */
	@Override
	public Projects loadProjectsById(int projectId){
		//try {
			return projectDAO.loadProjectById(projectId);
		//} catch (Exception e) {
		//	System.out.println("Exception: " + e.getMessage());
		//	return null;
		//}
	}

	public List<Projects> loadProjectByProjectCallAndFaculty(String projectCallCode, String facutyCode){
		return projectDAO.loadProjectByProjectCallAndFaculty(projectCallCode, facutyCode);
	}
	/**
	 * Get a list Topics by year and user
	 * 
	 * @param String
	 * @return object
	 * @throws UsernameNotFoundException
	 */
	@Override
	public List<mTopics> loadTopicListByYear(String userRole, String userCode,
			String reportingrYear) {
		try {
			if (userCode != null) {
				return tProjectDAO.loadTopicListByYear(userRole, userCode,
						reportingrYear);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get a list Topics by year
	 * 
	 * @param String
	 * @return object
	 * @throws UsernameNotFoundException
	 */
	@Override
	public List<mTopics> loadTopicSummaryListByYear(String reportingrYear) {
		try {
			if (reportingrYear != null) {
				return tProjectDAO.loadTopicSummaryListByYear(reportingrYear);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get a list products by user code
	 * 
	 * @param String
	 * @return object
	 */
	public List<mThreads> listAll(){
		return threadDAO.listAll();
	}
	
	@Override
	public List<mThreads> filerThreadsListNoPagination(String userRole, String userCode,
			String sThreadStatus,
			String sThreadCategory, String sThreadYear, String sThreadFaculty,
			String sThreadDepartment, String sThreadStaff) {

		try {
			/*
			System.out.println("nProjectsService.filterThreadsListNoPagination, userRole = " + userRole +
					", userCode = " + userCode + 
					", sThreadStatus = " + sThreadStatus +
					", sThreadCategory + " + sThreadCategory + 
					", sThreadYear = " + sThreadYear +
					", sThreadFaculty + " + sThreadFaculty +
					", sThreadDepartment + " + sThreadDepartment+
					", sThreadStaff = " + sThreadStaff);
			*/
			List<mTopicCategory> projectCategories = tProjectCategoryDAO.getList();
			HashSet<String> categories = new HashSet<String>();
			if (sThreadCategory != null && !sThreadCategory.equals("")) {
				categories.add(sThreadCategory);
				//System.out.println("ThreadServiceImpl::filterThreadList, unique projectCategory " + sThreadCategory);
			} else {
				for (mTopicCategory tc : projectCategories) {
					categories.add(tc.getPROJCAT_Code());
				}
				//System.out.println("ThreadServiceImpl::filterThreadList, categories = ");
				//for (String s : categories) System.out.println(s);
			}
			List<mFaculty> faculties = facultyDAO.loadFacultyList();

			List<mDepartment> dept = departmentDAO.loadDepartmentList();
			/*for (mDepartment d : dept) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> " + d.getDepartment_Code() + "\t" + d.getDepartment_Faculty_Code());
			}*/
			List<mStaff> staffs = staffDAO.listStaffs();
			/*for (mStaff st : staffs) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> " + st.getStaff_Code() + "\t" + st.getStaff_Department_Code());
			}*/
			
			boolean chooseStaff = false;
			boolean chooseDepartment = false;
			boolean chooseFaculty = false;
			HashSet<String> facultyCodes = new HashSet<String>();
			if (sThreadFaculty != null && !sThreadFaculty.equals("")) {
				facultyCodes.add(sThreadFaculty);
				chooseFaculty = true;
				//System.out.println("ThreadServiceImpl::filterThreadList, unique faculty = "+ sThreadFaculty);
			} else {
				for (mFaculty f : faculties) {
					facultyCodes.add(f.getFaculty_Code());
				}
			}
			
			HashSet<String> staffCodes = new HashSet<String>();
			if (sThreadStaff != null && !sThreadStaff.equals("")) {
				staffCodes.add(sThreadStaff);
				chooseStaff = true;
				//System.out.println("ThreadServiceImpl::filterThreadList, unique staffCode = "+ sThreadStaff);
			} else {
				HashSet<String> deptCodes = new HashSet<String>();
				if (sThreadDepartment != null && !sThreadDepartment.equals("")) {
					deptCodes.add(sThreadDepartment);
					chooseDepartment = true;
					//System.out.println("ThreadServiceImpl::filterThreadList, unique department = " + sThreadDepartment);
				} else {
					/*
					HashSet<String> facultyCodes = new HashSet<String>();
					if (sThreadFaculty != null && !sThreadFaculty.equals("")) {
						facultyCodes.add(sThreadFaculty);
						//System.out.println("ThreadServiceImpl::filterThreadList, unique faculty = "+ sThreadFaculty);
					} else {
						for (mFaculty f : faculties) {
							facultyCodes.add(f.getFaculty_Code());
						}

						//System.out.print("ThreadServiceImpl::filterThreadList, multi departments = ");
						//for (String s : deptCodes) System.out.print(s + "\t");
						//System.out.println();
					}
					*/
					for(mDepartment d : dept){
						if(facultyCodes.contains(d.getDepartment_Faculty_Code()))
							deptCodes.add(d.getDepartment_Code());
					}
				}
				
				for (mStaff st : staffs) {
					if (deptCodes.contains(st.getStaff_Department_Code()))
						staffCodes.add(st.getStaff_Code());
				}
				//System.out.println("ThreadServiceImpl::filterThreadList, multi staffs = ");
				//for (String s : staffCodes)System.out.println(s);
			}
			if(chooseFaculty && !chooseDepartment && !chooseStaff){// list all projects registered at the choose faculty
				staffCodes.clear();
				for (mStaff st : staffs) {
					staffCodes.add(st.getStaff_Code());
				}
			}
			
			List<mProjectStatus> statuses = projectStatusDAO.getList();
			HashSet<String> statusCodes = new HashSet<String>();
			if (sThreadStatus != null && !sThreadStatus.equals(""))
				statusCodes.add(sThreadStatus);
			else
				for (mProjectStatus stat : statuses) {
					//System.out.println("ThreadServiceImpl::filterThreadsList --> "+ stat.getPROJSTAT_Code()+ "\t"+ stat.getPROJSTAT_Description());
					statusCodes.add(stat.getPROJSTAT_Code());
				}

			
			
			List<mThreads> allThreads = threadDAO.filerThreadsList(userRole, userCode, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
			/*for (mThreads t : allThreads) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "+ t.getPROJ_Name() + "\t" + t.getPROJ_Status_Code());
			}*/

			//System.out.println("nProjectService::filterThreadListNoPagination, allThreads = " + allThreads.size());
			
			List<mProjectStaffs> allProjectStaffs = projectStaffsDAO.listAll();
			/*for (mProjectStaffs ps : allProjectStaffs) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "+ ps.getPROJSTAFF_Proj_Code() + "\t"+ ps.getPROJSTAFF_Staff_Code());
			}*/

			List<mAcademicYear> years = yearDAO.getList();
			HashSet<String> yearCodes = new HashSet<String>();
			if (sThreadYear != null && !sThreadYear.equals(""))
				yearCodes.add(sThreadYear);
			else
				for (mAcademicYear y : years) {
					yearCodes.add(y.getACAYEAR_Code());
				}

			HashSet<String> threadCodes = new HashSet<String>();
			for (mProjectStaffs ps : allProjectStaffs) {
				if (staffCodes.contains(ps.getPROJSTAFF_Staff_Code()))
					threadCodes.add(ps.getPROJSTAFF_Proj_Code());
			}
			for(mThreads t: allThreads){
				if(staffCodes.contains(t.getPROJ_User_Code())
						&& facultyCodes.contains(t.getPROJ_FacultyCode())
						)
					threadCodes.add(t.getPROJ_Code());
			}
			//System.out.println("nProjectService::filterThreadListNoPagination, staffCodes = " + staffCodes);
			//System.out.println("nProjectService::filterThreadListNoPagination, statusCodes = " + statusCodes);
			//System.out.println("nProjectService::filterThreadListNoPagination, categories = " + categories);
			//System.out.println("nProjectService::filterThreadListNoPagination, threadCodes = " + threadCodes);
			//System.out.println("nProjectService::filterThreadListNoPagination, facultyCodes = " + facultyCodes);

			/*System.out.println("ThreadServiceImpl::filterThreads, prepare filtering, categories = ");
			for (String ct : categories) System.out.println(ct);
			System.out.println();*/

			List<mThreads> FL = new ArrayList<mThreads>();
			for (mThreads t : allThreads) {
				/*
				System.out.println("ThreadServiceImpl::filterThreads, consider project "+ t.getPROJ_Code() + ", Name = " + 
			t.getPROJ_Name()+ 
						", category "+ t.getPROJ_ProjCat_Code() + ", userCode = " + t.getPROJ_User_Code() + 
						", login UserCode = " + userCode + ", statusCode = " + t.getPROJ_Status_Code() +
						", facultyCode = " + t.getPROJ_FacultyCode());
				*/
				if (threadCodes.contains(t.getPROJ_Code())
						//&& yearCodes.contains(t.getPROJ_AcaYear_Code())
						&& statusCodes.contains(t.getPROJ_Status_Code())){
						//&& categories.contains(t.getPROJ_ProjCat_Code())) {
				
					boolean ok = true;
					if(!userRole.equals("ROLE_ADMIN") && !userRole.equals("SUPER_ADMIN") )
						ok = t.getPROJ_User_Code().equals(userCode);
					
					if(ok){
						FL.add(t);
						//System.out.println("ThreadServiceImpl::filterThreads -> ACCEPT "+ t.getPROJ_Name());
					}
				}
			}

			//System.out.println("ThreadServiceImpl::filterThreads -> TOTAL ACCEPT "+ FL.size());

			return FL;
			// return threadDAO.filerThreadsList(userRole, userCode, iStartItem,
			// iNumberOfItems, sThreadStatus, sThreadCategory, sThreadYear,
			// sThreadFaculty, sThreadDepartment, sThreadStaff);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<mThreads> filerThreadsList(String userRole, String userCode,
			Integer iStartItem, Integer iNumberOfItems, String sThreadStatus,
			String sThreadCategory, String sThreadYear, String sThreadFaculty,
			String sThreadDepartment, String sThreadStaff) {

		/*System.out.println("ThreadServiceImpl::filterThreadsList, userCode = "
				+ userCode + "\n" + ", userRole = " + userRole + "\n"
				+ ", sTheadStatus = " + sThreadStatus + "\n"
				+ ", sThreadCategory = " + sThreadCategory + "\n"
				+ ", sThreadYear = " + sThreadYear + "\n"
				+ ", sThreadFaculty = " + sThreadFaculty + "\n"
				+ ", sThreadDepartment = " + sThreadDepartment + "\n"
				+ ", sThreadSatff = " + sThreadStaff);*/
		try {
			List<mThreads> FL = filerThreadsListNoPagination(userRole, userCode, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
			//List<mThreads> FL = listAll();
			
			ArrayList<mThreads> pFL = new ArrayList<mThreads>();// extract from iStartItem to iStartItems + iNumberItems - 1
			int iEndItem = iStartItem + iNumberOfItems < FL.size() ? iStartItem + iNumberOfItems : FL.size();
			for(int i = iStartItem; i < iEndItem; i++){
				pFL.add(FL.get(i));
			}
			//return FL;
			return pFL;
			
			/*
			List<TopicCategory> projectCategories = projectCategory.getList();
			HashSet<String> categories = new HashSet<String>();
			if (sThreadCategory != null && !sThreadCategory.equals("")) {
				categories.add(sThreadCategory);
				System.out
						.println("ThreadServiceImpl::filterThreadList, unique projectCategory "
								+ sThreadCategory);
			} else {
				for (TopicCategory tc : projectCategories) {
					categories.add(tc.getPROJCAT_Code());
				}
				System.out
						.println("ThreadServiceImpl::filterThreadList, categories = ");
				for (String s : categories)
					System.out.println(s);
			}
			List<Faculty> faculties = facultyDAO.loadFacultyList();

			List<Department> dept = departmentDAO.loadDepartmentList();
			for (Department d : dept) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "
						+ d.getDepartment_Code() + "\t"
						+ d.getDepartment_Faculty_Code());
			}
			List<Staff> staffs = staffDAO.listStaffs();
			for (Staff st : staffs) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "
						+ st.getStaff_Code() + "\t"
						+ st.getStaff_Department_Code());
			}

			HashSet<String> staffCodes = new HashSet<String>();
			if (sThreadStaff != null && !sThreadStaff.equals("")) {
				staffCodes.add(sThreadStaff);
				System.out
						.println("ThreadServiceImpl::filterThreadList, unique staffCode = "
								+ sThreadStaff);
			} else {
				HashSet<String> deptCodes = new HashSet<String>();
				if (sThreadDepartment != null && !sThreadDepartment.equals("")) {
					deptCodes.add(sThreadDepartment);
					System.out
							.println("ThreadServiceImpl::filterThreadList, unique department = "
									+ sThreadDepartment);
				} else {
					HashSet<String> facultyCodes = new HashSet<String>();
					if (sThreadFaculty != null && !sThreadFaculty.equals("")) {
						facultyCodes.add(sThreadFaculty);
						System.out
								.println("ThreadServiceImpl::filterThreadList, unique faculty = "
										+ sThreadFaculty);
						
						
					} else {
						for (Faculty f : faculties) {
							facultyCodes.add(f.getFaculty_Code());
						}

						System.out
								.print("ThreadServiceImpl::filterThreadList, multi departments = ");
						for (String s : deptCodes)
							System.out.print(s + "\t");
						System.out.println();
					}
					
					for(Department d : dept){
						if(facultyCodes.contains(d.getDepartment_Faculty_Code()))
							deptCodes.add(d.getDepartment_Code());
					}
				}

				for (Staff st : staffs) {
					if (deptCodes.contains(st.getStaff_Department_Code()))
						staffCodes.add(st.getStaff_Code());
				}
				System.out
						.println("ThreadServiceImpl::filterThreadList, multi staffs = ");
				for (String s : staffCodes)
					System.out.println(s);
			}

			List<ProjectStatus> statuses = projectStatusDAO.getList();
			HashSet<String> statusCodes = new HashSet<String>();
			if (sThreadStatus != null && !sThreadStatus.equals(""))
				statusCodes.add(sThreadStatus);
			else
				for (ProjectStatus stat : statuses) {
					System.out
							.println("ThreadServiceImpl::filterThreadsList --> "
									+ stat.getPROJSTAT_Code()
									+ "\t"
									+ stat.getPROJSTAT_Description());
					statusCodes.add(stat.getPROJSTAT_Code());
				}

			List<Threads> allThreads = threadDAO.filerThreadsList(userRole,
					userCode, iStartItem, iNumberOfItems, sThreadStatus,
					sThreadCategory, sThreadYear, sThreadFaculty,
					sThreadDepartment, sThreadStaff);
			for (Threads t : allThreads) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "
						+ t.getPROJ_Name() + "\t" + t.getPROJ_Status_Code());

			}

			List<ProjectStaffs> allProjectStaffs = projectStaffsDAO.listAll();
			for (ProjectStaffs ps : allProjectStaffs) {
				System.out.println("ThreadServiceImpl::filterThreadsList --> "
						+ ps.getPROJSTAFF_Proj_Code() + "\t"
						+ ps.getPROJSTAFF_Staff_Code());

			}

			List<AcademicYear> years = yearDAO.getList();
			HashSet<String> yearCodes = new HashSet<String>();
			if (sThreadYear != null && !sThreadYear.equals(""))
				yearCodes.add(sThreadYear);
			else
				for (AcademicYear y : years) {
					yearCodes.add(y.getACAYEAR_Code());
				}

			HashSet<String> threadCodes = new HashSet<String>();
			for (ProjectStaffs ps : allProjectStaffs) {
				if (staffCodes.contains(ps.getPROJSTAFF_Staff_Code()))
					threadCodes.add(ps.getPROJSTAFF_Proj_Code());
			}

			System.out
					.println("ThreadServiceImpl::filterThreads, prepare filtering, categories = ");
			for (String ct : categories)
				System.out.println(ct);
			System.out.println();

			List<Threads> FL = new ArrayList<Threads>();
			for (Threads t : allThreads) {
				System.out
						.println("ThreadServiceImpl::filterThreads, consider project "
								+ t.getPROJ_Name()
								+ ", category "
								+ t.getPROJ_ProjCat_Code() + ", userCode = " + t.getPROJ_User_Code() + ", login UserCode = " + userCode);
				if (threadCodes.contains(t.getPROJ_Code())
						&& yearCodes.contains(t.getPROJ_AcaYear_Code())
						&& statusCodes.contains(t.getPROJ_Status_Code())
						&& categories.contains(t.getPROJ_ProjCat_Code())) {
				
					boolean ok = true;
					if(!userRole.equals("ROLE_ADMIN"))
						ok = t.getPROJ_User_Code().equals(userCode);
					
					if(ok){
						FL.add(t);

					System.out
							.println("ThreadServiceImpl::filterThreads -> ACCEPT "
									+ t.getPROJ_Name());
					}
				}
			}

			System.out
					.println("ThreadServiceImpl::filterThreads -> TOTAL ACCEPT "
							+ FL.size());

			ArrayList<Threads> pFL = new ArrayList<Threads>();// extract from iStartItem to iStartItems + iNumberItems - 1
			int iEndItem = iStartItem + iNumberOfItems < FL.size() ? iStartItem + iNumberOfItems : FL.size();
			for(int i = iStartItem; i < iEndItem; i++){
				pFL.add(FL.get(i));
			}
			//return FL;
			return pFL;
			*/
			
			// return threadDAO.filerThreadsList(userRole, userCode, iStartItem,
			// iNumberOfItems, sThreadStatus, sThreadCategory, sThreadYear,
			// sThreadFaculty, sThreadDepartment, sThreadStaff);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Count items
	 * 
	 * @param
	 * @return int
	 */
	@Override
	public int countItems(String userRole, String userCode,
			String sThreadStatus, String sThreadCategory, String sThreadYear,
			String sThreadFaculty, String sThreadDepartment, String sThreadStaff) {
		try {
			return threadDAO.countItems(userRole, userCode, sThreadStatus,
					sThreadCategory, sThreadYear, sThreadFaculty,
					sThreadDepartment, sThreadStaff);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * Save a topic
	 * 
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * @return int
	 */
	@Override
	public int saveAThread(String userCode, String threadName,
			String threadCategory, String threadContent,
			String threadStartDate, String threadEndDate,
			String threadMotivation, String threadReportingDate,
			String threadResult, String threadStatus, int threadBudget,
			String threadSourceUploadFileSrc, String threadCode,
			List<String> listStaffs, List<String> listStaffRoles) {
		if (userCode != null) {
			mThreads thread = new mThreads();
			thread.setPROJ_AcaYear_Code(threadReportingDate);
			thread.setPROJ_Code(threadCode);
			thread.setPROJ_Content(threadContent);
			thread.setPROJ_EndDate(threadEndDate);
			thread.setPROJ_Motivation(threadMotivation);
			thread.setPROJ_Name(threadName);
			thread.setPROJ_ProjCat_Code(threadCategory);
			thread.setPROJ_Result(threadResult);
			thread.setPROJ_SourceFile(threadSourceUploadFileSrc);
			thread.setPROJ_StartDate(threadStartDate);
			thread.setPROJ_Status_Code(threadStatus);
			thread.setPROJ_TotalBudget(threadBudget);
			thread.setPROJ_User_Code(userCode);
			int i_SaveAThread = threadDAO.saveAThread(thread);

			// Set project to staffs
			mThreads insertedThread = threadDAO.loadAThreadByIdAndUserCode(nProjectServiceImpl.ROLE_USER,userCode, i_SaveAThread);
			
			// Save leader of a thread
			if(insertedThread != null){
				mProjectStaffs oExistedProjectLeader = projectStaffsDAO.loadProjectStaffByUserCode(insertedThread.getPROJ_Code(), userCode, nProjectServiceImpl.PROJECT_LEADER);
				if(oExistedProjectLeader == null){
					mProjectStaffs projectStaff = new mProjectStaffs();
					String projectStaffCode = userCode + insertedThread.getPROJ_Code();
					projectStaff.setPROJSTAFF_Code(projectStaffCode);
					projectStaff.setPROJSTAFF_Proj_Code(insertedThread.getPROJ_Code());
					projectStaff.setPROJSTAFF_Role_Code(this.PROJECT_LEADER);
					projectStaff.setPROJSTAFF_Staff_Code(userCode);
					projectStaffsDAO.saveAStaff(projectStaff);
				}
			}
			
			// Save members of a thread
			if (listStaffs != null && listStaffRoles != null && insertedThread != null) {
				for (int i = 0; i < listStaffs.size(); i++) {
					if (!listStaffs.get(i).equals("")) {
						mProjectStaffs projectStaff = new mProjectStaffs();
						String projectStaffCode = listStaffs.get(i) + insertedThread.getPROJ_Code();
						projectStaff.setPROJSTAFF_Code(projectStaffCode);
						projectStaff.setPROJSTAFF_Proj_Code(insertedThread.getPROJ_Code());
						projectStaff.setPROJSTAFF_Role_Code(listStaffRoles.get(i));
						projectStaff.setPROJSTAFF_Staff_Code(listStaffs.get(i));
						projectStaffsDAO.saveAStaff(projectStaff);
					}
				}
			}
			return i_SaveAThread;
		}
		return 0;
	}
	
	/**
	 * Adding new project
	 */
	@Override
	public int saveAProject(String userRole, String userCode,String projectCallCode,String projectName,
								String projectContent,String projectMotivation,String projectResult,int budgetMaterial, int totalBudget, String projectCode,
								String facultyAdd,String projectSurvey,String projectObjective,String startDate,String endDate, String projectCategory, 
								String projectResearchFieldCode, String sourceFile, String[] projectResearchFieldCodeList){
		if(!"".equals(userCode)&& !"".equals(projectCode) && !"".equals(projectName) && !"".equals(projectCallCode))
		{
			Projects beInsertedProject = new Projects();
			beInsertedProject.setPROJ_User_Code(userCode);
			beInsertedProject.setPROJ_PRJCall_Code(projectCallCode);
			beInsertedProject.setPROJ_Name(projectName);
			beInsertedProject.setPROJ_Content(projectContent);
			beInsertedProject.setPROJ_Motivation(projectMotivation);
			beInsertedProject.setPROJ_Result(projectResult);
			beInsertedProject.setPROJ_BudgetMaterial(budgetMaterial);
			beInsertedProject.setPROJ_TotalBudget(totalBudget);
			beInsertedProject.setPROJ_Code(projectCode);
			beInsertedProject.setPROJ_FacultyCode(facultyAdd);
			beInsertedProject.setPROJ_Survey(projectSurvey);
			beInsertedProject.setPROJ_StartDate(startDate);
			beInsertedProject.setPROJ_EndDate(endDate);
			beInsertedProject.setPROJ_Objective(projectObjective);
			beInsertedProject.setPROJ_ProjCat_Code(projectCategory);
			beInsertedProject.setPROJ_ResearchFieldCode(projectResearchFieldCode);
			beInsertedProject.setPROJ_SourceFile(sourceFile);
			int iInsertedProjectId = threadDAO.saveAProject(beInsertedProject);
			if(iInsertedProjectId > 0 )
			{
				Projects beUpdatedProject = threadDAO.loadAProjectByIdAndUserCode(userRole, userCode, iInsertedProjectId);
				projectCode = projectCallCode+iInsertedProjectId;
				beUpdatedProject.setPROJ_Code(projectCode);
				threadDAO.editAProject(beUpdatedProject);
				
				// Save project search field to each project
				if(projectResearchFieldCodeList.length > 0)
				{
					for (String projectResearchField : projectResearchFieldCodeList) {
						ProjectsProjectResearchField projectsProjectResearchField = new ProjectsProjectResearchField();
						projectsProjectResearchField.setPRJPRJRSHF_PRJRSHFCode(projectResearchField);
						projectsProjectResearchField.setPRJPRJRSHF_PROJCode(projectCode);
						projectsProjectResearchFieldDAO.saveAProjectSearchField(projectsProjectResearchField);
					}
				}
				return iInsertedProjectId;
			}
		}else{
			System.out.println("nProjectService::saveAProject, projectName = " + projectName + ", projectCode = " + projectCode + 
					"DO NOTHING");
			
		}
		return 0;
	}
	
	/**
	 * Adding tasks for each member who's working for a project
	 */
	public int saveMemberTasks(String projectCode, String[] projectMembers,String[] projectMemberRole,String[] projectMemberTasks,String[] projectMemberWorkingDays,String[] projectMemberBudget, String currentProjectCode){
		if(projectMembers == null) return 0;
		int iTotalTasks = projectMembers.length;
		if(projectCode != "")
		{
			// Removing old tasks
			String sProjectCode = (currentProjectCode != "") ? currentProjectCode : projectCode;
			List<ProjectTasks> currentProjectTasks = projectTasksDAO.loadAProjectTaskByProjectCode(sProjectCode);
			if(currentProjectTasks != null)
			{
				for (ProjectTasks aProjectTask : currentProjectTasks) {
					projectTasksDAO.removeAProjectTask(aProjectTask);
				}
			}
			if(iTotalTasks > 0)
			{
				
				// Adding new tasks
				ProjectTasks aProjectTask = new ProjectTasks();
				for(int iIterator = 0; iIterator < iTotalTasks; iIterator++)
				{
					String sTaskCode = "PRJTSK_" + projectCode + "_" + iIterator;
					aProjectTask.setPRJTSK_Code(sTaskCode);
					aProjectTask.setPRJTSK_Cost(Integer.parseInt(projectMemberBudget[iIterator]));
					aProjectTask.setPRJTSK_NRBDay(Integer.parseInt(projectMemberWorkingDays[iIterator]));
					aProjectTask.setPRJTSK_Proj_Code(projectCode);
					aProjectTask.setPRJTSK_RoleCode(projectMemberRole[iIterator]);
					aProjectTask.setPRJTSK_StaffCode(projectMembers[iIterator]);
					aProjectTask.setPRJTSK_Task(projectMemberTasks[iIterator]);
					projectTasksDAO.saveAProjectTask(aProjectTask);
				}
				return 1;
			}
		}
		return 0;
	}
	
	
	/**
	 * 
	 * @param projectCode
	 * @return
	 */
	public void removeProjectTasks(String sProjectCode){
		try{
			if(!("".equals(sProjectCode))){
				List<ProjectTasks> currentProjectTasks = projectTasksDAO.loadAProjectTaskByProjectCode(sProjectCode);
				if(currentProjectTasks != null)
				{
					for (ProjectTasks aProjectTask : currentProjectTasks) {
						projectTasksDAO.removeAProjectTask(aProjectTask);
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * load a thread by usercode and it's id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	@Override
	public mThreads loadAThreadByIdAndUserCode(String userRole, String userCode, int threadId) {
		try {
			return threadDAO.loadAThreadByIdAndUserCode(userRole, userCode, threadId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * load a project by usercode and it's id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	public Projects loadAProjectByIdAndUserCode(String userRole, String userCode, int projectId){
		try {
			return threadDAO.loadAProjectByIdAndUserCode(userRole, userCode, projectId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Loading a sumitted project by id
	 * @param userRole
	 * @param userCode
	 * @param projectId
	 * @return
	 */
	public Projects loadASumittedProjectByIdAndUserCode(String userRole, String userCode, int projectId){
		try {
			return threadDAO.loadASumittedProjectByIdAndUserCode(userRole, userCode, projectId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Edit a thread
	 * 
	 * @param String
	 * @param int
	 * @return null
	 */
	@Override
	public void editStatusThread(String userRole, String userCode, int threadId, String threadStatus){
		mThreads thread = threadDAO.loadAThreadByIdAndUserCode(userRole, userCode, threadId);
		if (thread != null) {
			// tProjectDAO.editATopic(topic);
			thread.setPROJ_ID(threadId);
			thread.setPROJ_Status_Code(threadStatus);
			//thread.setPROJ_User_Code(userCode);
			threadDAO.editAThread(thread);

		}
	}
	
	@Override
	public void editAThread(String userRole, String userCode,
			String threadName, String threadCategory, String threadContent,
			String threadStartDate, String threadEndDate,
			String threadMotivation, String threadReportingDate,
			String threadResult, String threadStatus, int threadBudget,
			String threadSourceUploadFileSrc, String threadCode, int threadId,
			List<String> listStaffs, List<String> listStaffRoles) {
		mThreads thread = threadDAO.loadAThreadByIdAndUserCode(userRole, userCode, threadId);
		if (thread != null) {
			// tProjectDAO.editATopic(topic);
			thread.setPROJ_AcaYear_Code(threadReportingDate);
			thread.setPROJ_Code(threadCode);
			thread.setPROJ_Content(threadContent);
			thread.setPROJ_EndDate(threadEndDate);
			thread.setPROJ_ID(threadId);
			thread.setPROJ_Motivation(threadMotivation);
			thread.setPROJ_Name(threadName);
			thread.setPROJ_ProjCat_Code(threadCategory);
			thread.setPROJ_Result(threadResult);
			thread.setPROJ_StartDate(threadStartDate);

			thread.setPROJ_Status_Code(threadStatus);
			thread.setPROJ_TotalBudget(threadBudget);
			//thread.setPROJ_User_Code(userCode);
			if (threadSourceUploadFileSrc.equals("")) {
				thread.setPROJ_SourceFile(thread.getPROJ_SourceFile());
			} else {

				String sOldSourceFile = thread.getPROJ_SourceFile();
				if ((sOldSourceFile != null)) {
					File oldFile = new File(sOldSourceFile);
					oldFile.delete();
				}
				thread.setPROJ_SourceFile(threadSourceUploadFileSrc);
			}
			threadDAO.editAThread(thread);

			if (listStaffs != null && listStaffRoles != null) {
				// Remove old project Staffs
				List<mProjectStaffs> previousProjectStaffs = projectStaffsDAO.loadAProjectStaffByProjectCodeForEdit(thread.getPROJ_Code());
				for (mProjectStaffs projectStaff : previousProjectStaffs) {
					projectStaffsDAO.removeAProjectStaff(projectStaff.getPROJSTAFF_ID());
				}

				// Save new project Staffs
				for (int i = 0; i < listStaffs.size(); i++) {
					if (!listStaffs.get(i).equals("")) {
						mProjectStaffs projectStaff = new mProjectStaffs();
						String projectStaffCode = listStaffs.get(i)+ thread.getPROJ_Code();
						projectStaff.setPROJSTAFF_Code(projectStaffCode);
						projectStaff.setPROJSTAFF_Proj_Code(thread.getPROJ_Code());
						projectStaff.setPROJSTAFF_Role_Code(listStaffRoles.get(i));
						projectStaff.setPROJSTAFF_Staff_Code(listStaffs.get(i));
						projectStaffsDAO.saveAStaff(projectStaff);
					}
				}
			}
		}
	}
	
	/**
	 * Sending a project to council , after this action project can not be changed.
	 * @param project
	 */
	public void sendAProject(Projects project, boolean editSumitted){
		if(project != null)
		{
			if(editSumitted == true)
			{
				project.setPROJ_Locked2(1);
			}else{
				project.setPROJ_Locked1(1);
				project.setPROJ_Status_Code("SUBMIT");
			}
			threadDAO.editAProject(project);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void editAProject(int projectId, String userRole, String userCode, String projectCallCode, String projectName, String projectContent, String projectMotivation, String projectResult, 
								int budgetMaterial, String projectCode,String startDate,String endDate,String facultyAdd, String projectSurvey,String projectObjective, boolean bEditSumittedProject, int projectBudget,
								String projectResearchFieldCode, String sourceFileUpload, String[] projectResearchFieldCodeList, String sendIt){
		Projects project = threadDAO.loadAProjectByIdAndUserCode(userRole, userCode, projectId);
		if (project != null) {
			if(bEditSumittedProject == true)
			{
				int iTotalBudget = projectBudget + project.getPROJ_BudgetMaterial();
				project.setPROJ_Code(projectCode);
				project.setPROJ_ContentChanged(projectContent);
				project.setPROJ_MotivationChanged(projectMotivation);
				project.setPROJ_ResultChanged(projectResult);
				project.setPROJ_BudgetChanged(iTotalBudget);
				project.setPROJ_ObjectiveChanged(projectObjective);
				project.setPROJ_SurveyChanged(projectSurvey);
			}else{
				project.setPROJ_Code(projectCode);
				project.setPROJ_Content(projectContent);
				project.setPROJ_Motivation(projectMotivation);
				project.setPROJ_Name(projectName);
				project.setPROJ_Result(projectResult);
				project.setPROJ_BudgetMaterial(budgetMaterial);
				project.setPROJ_TotalBudget(projectBudget);
				project.setPROJ_PRJCall_Code(projectCallCode);
				project.setPROJ_StartDate(startDate);
				project.setPROJ_EndDate(endDate);
				project.setPROJ_FacultyCode(facultyAdd);
				project.setPROJ_Objective(projectObjective);
				project.setPROJ_Survey(projectSurvey);
				project.setPROJ_ResearchFieldCode(projectResearchFieldCode);
				project.setPROJ_SourceFile(sourceFileUpload);
				if(!"".equals(sendIt)){
					project.setPROJ_Locked1(1);
					project.setPROJ_Status_Code("SUBMIT");
				}
			}
			threadDAO.editAProject(project);
			
			if(projectResearchFieldCodeList.length > 0)
			{
				List<ProjectsProjectResearchField> currentList = projectsProjectResearchFieldDAO.loadProjectsProjectResearchFieldListByProjectCode(project.getPROJ_Code());
				for (ProjectsProjectResearchField projectsProjectResearchField : currentList) {
					projectsProjectResearchFieldDAO.removeAProjectSearchField(projectsProjectResearchField);
				}
				
				for (String projectResearchField : projectResearchFieldCodeList) {
					ProjectsProjectResearchField newList = new ProjectsProjectResearchField();
					newList.setPRJPRJRSHF_PROJCode(project.getPROJ_Code());
					newList.setPRJPRJRSHF_PRJRSHFCode(projectResearchField);
					projectsProjectResearchFieldDAO.saveAProjectSearchField(newList);
				}
			}
		}
	}
	
	@Override
	public void editAnApproveProject(Projects project){
		threadDAO.editAProject(project);
	}

	/**
	 * Remove a thread
	 * 
	 * @param int
	 * @return int
	 */
	@Override
	public int removeAThread(int threadId) {
		return threadDAO.removeAThread(threadId);
	}
	
	/**
	 * Remove a thread
	 * 
	 * @param int
	 * @return int
	 */
	@Override
	public int removeAProject(int projectId) {
		return threadDAO.removeAProject(projectId);
	}
	
	/**
	 * Edit a thread
	 * 
	 * @param String
	 * @param int
	 * @return null
	 */
	@Override
	public List<mThreads> loadThreadsListForReporting(String threadCategory, String threadStatus, 
			String threadFaculty, String threadDepartment, String threadStaff, String yearForGenerating){
		try {
			System.out.println(name() + "::loadThreadListForReporting, threadCategory = " + 
		threadCategory + ", threadStatus = " + threadStatus + ", threadFaculty = " + 
					threadFaculty + ", threadDepartment = " + threadDepartment + ", threadStaff = " + 
		threadStaff + ", yearFor Generating = " + yearForGenerating);
			List<mThreads> o_ReturnedThreadFilterList = new ArrayList<mThreads>();
			if(threadStaff != "")
			{
				// Get List staff code by faculty and department
				HashSet<String> staffCodeList = new HashSet<String>();
				List<mStaff> listStaff = staffDAO.listStaffsByFalcutyAndDepartment(threadFaculty, threadDepartment);
				for(mStaff oStaff : listStaff)
				{
					staffCodeList.add(oStaff.getStaff_Code());
					//System.out.println(name() + "::loadThreadListForReporting, collect staff = " + oStaff.getStaff_Code());
				}
				List<mProjectStaffs> projectStaffLists = projectStaffsDAO.loadAProjectStaffInListStaffs(staffCodeList);
				HashSet<String> projectCodeList = new HashSet<String>();
				for(mProjectStaffs projectStaff : projectStaffLists)
				{
					projectCodeList.add(projectStaff.getPROJSTAFF_Proj_Code());
				}
				
				List<mThreads> o_ThreadList = threadDAO.loadThreadsListForReporting(threadCategory, threadStatus, threadStaff, yearForGenerating);
				
				if(o_ThreadList.size()>0 && projectCodeList.size() > 0){
					for (mThreads threads : o_ThreadList) {
						if(projectCodeList.contains(threads.getPROJ_Code()))
						{
							o_ReturnedThreadFilterList.add(threads);
						}
					}
				}else{
					o_ReturnedThreadFilterList = o_ThreadList;
				}
			}
			return o_ReturnedThreadFilterList;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param facultyCode
	 * @return
	 */
	@Override
	public String getFacultyName(String facultyCode)
	{
		try{
		List<mFaculty> FacultyList = facultyDAO.loadFacultyList();
		if(FacultyList != null)
		{
			for(mFaculty aFaculty : FacultyList)
			{
				if(facultyCode.equals(aFaculty.getFaculty_Code())){
					return aFaculty.getFaculty_Name();
				}
			}
		}
		}catch(Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 * @param departmentCode
	 * @return
	 */
	@Override
	public String getDepartmentName(String departmentCode)
	{
		try{
		List<mDepartment> DepartmenList = departmentDAO.loadDepartmentList();
		if(DepartmenList != null)
		{
			for(mDepartment aDepartment : DepartmenList)
			{
				if(departmentCode.equals(aDepartment.getDepartment_Code())){
					return aDepartment.getDepartment_Name();
				}
			}
		}
		}catch(Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
		return "";
	}
	
	
	public List<Projects> loadProjectByProjectCallId(String PROJ_PRJCall_Code){
		try {

			return threadDAO.loadProjectByProjectCallId(PROJ_PRJCall_Code);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}		
	}
	
	public String name(){
		return "nProjectService";
	}
	public void generateProjectCodes(String projectCallCode){
		
		List<Projects> projects = loadProjectByProjectCallId(projectCallCode);
		
		
		HashMap<String, String> mapOldCode2NewCode = new HashMap<String, String>();
		
		for(int i = 0; i < projects.size(); i++){
			Projects p = projects.get(i);
			// tblprojecttasks
			List<ProjectTasks> projectTasks = projectTasksDAO.loadAProjectTaskByProjectCode(p.getPROJ_Code());
			
			// tblcommentssubmittedprojects
			List<mStaffJuryOfSubmittedProject> staffJuryProjects = staffJurySubmittedProjectDAO.
					loadListStaffJuryOfSubmittedProjectByProjectCode(p.getPROJ_Code());
			
			// tblstaffjurysubmittedprojects
			List<mCommentsOfSubmittedProjects> commentsProject = commentSubmittedProjectDAO.
					loadCommentsOfSubmittedProjectByProjectCode(p.getPROJ_Code());
			
			List<DetailCommentSubmittedProjects> detailComments = detailCommentsSubmittedProjectDAO.loadByProjectCode(p.getPROJ_Code());
			
			int ID = i+1;
			String sID = "";
			if(ID < 10) sID = "00" + ID; 
			else if(10 <= ID && ID <= 99) sID = "0" + ID;
			else sID = "" + ID;
			String newCode = p.getPROJ_PRJCall_Code() + "-" + sID;
			
			mapOldCode2NewCode.put(p.getPROJ_Code(), newCode);
			
			
			System.out.println(name() + "::generateProjectCodes, oldCode = " + p.getPROJ_Code() + ", new Code = " + newCode);
			
			p.setPROJ_Code(newCode);
			threadDAO.editAProject(p);
			
			for(ProjectTasks pt: projectTasks){
				pt.setPRJTSK_Proj_Code(newCode);
				projectTasksDAO.editAProjectTask(pt);
				System.out.println(name() + "::generateProjectCodes, --> updated projectTask " + pt.getPRJTSK_Code() + 
						", new ProjectCode = " + newCode + ", staffCode = " + pt.getPRJTSK_StaffCode());
			}
			
			for(mStaffJuryOfSubmittedProject sjsp: staffJuryProjects){
				sjsp.setSTFJUPRJ_PRJCODE(newCode);
				staffJurySubmittedProjectDAO.editStaffJuryOfSubmittedProject(sjsp);//.saveStaffJuryOfSubmittedProject(sjsp);
				System.out.println(name() + "::generateProjectCodes update staffJurySubmittedProject newCode = " + newCode);
			}
			
			for(mCommentsOfSubmittedProjects cm: commentsProject){
				cm.setCOMPROJ_PRJCODE(newCode);
				commentSubmittedProjectDAO.editCommentsOfSubmittedProjects(cm);//.saveCommentsOfSubmittedProjects(cm);
				System.out.println(name() + "::generateProjectCodes, update commentSubmittedProject newCode = " + newCode);
			}
			
			for(DetailCommentSubmittedProjects dcp: detailComments){
				dcp.setCMTSUBPRJ_PRJCode(newCode);
				detailCommentsSubmittedProjectDAO.editDetailsCommentsOfSubmittedProjects(dcp);
				System.out.println(name() + "::generateProjectCodes update detailCommentsSubmittedProject newCode = " + newCode);
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public List<xProjects> loadListSubmittedProjectsForSummary(){
		try {
			return projectDAO.loadListSubmittedProjectsForSummary();
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public List<ProjectsProjectResearchField> loadProjectsProjectResearchFieldListByProjectCode(String sProjectCode){
		try {
			return projectsProjectResearchFieldDAO.loadProjectsProjectResearchFieldListByProjectCode(sProjectCode);
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int removeAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField){
		try {
			return projectsProjectResearchFieldDAO.removeAProjectSearchField(projectsProjectResearchField);
		} catch (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}
