package vn.webapp.modules.researchmanagement.controller.cpservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.ProjectStage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vn.webapp.controller.BaseRest;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchmanagement.model.Projects;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProducts;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.service.mJuryOfAnnouncedProjectCallService;
import vn.webapp.modules.researchmanagement.service.mJuryRoleOfSubmittedProjectsService;
import vn.webapp.modules.researchmanagement.service.mProductService;
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.service.mProjectStaffsService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmServiceProject")
@RequestMapping(value = {"/cpservice"})
public class nProjectController extends BaseRest {
	@Autowired
    private mStaffService staffService;
	
	@Autowired
	private mProductService productService;
	
	@Autowired
	private nProjectService threadService;
	
	@Autowired
	private mProjectStaffsService projectStaffsService;
	
	@Autowired
	private mProjectStatusService projectStatusService;
	
	@Autowired
	private tProjectCategoryService tProjectCategoryService;
	
	@Autowired
	private nProjectService projectService;
	
	@Autowired
	private mProjectCallsService projectCallsService;
	
	@Autowired
	private mJuryRoleOfSubmittedProjectsService juryRoleOfSubmittedProjectsService;
	
	@Autowired
	private mFacultyService facultyService;
	
	@Autowired
	private mJuryOfAnnouncedProjectCallService juryOfAnnouncedProjectCallService ;
    
	public String name() {
		return "cpservice/nProjectController";
	}
	@ResponseBody
    @RequestMapping(value = "generate-project-codes", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String generateProjectCode(HttpServletRequest  request, HttpSession session, 
    		@RequestParam(value = "projectCallCode", defaultValue = "0") String projectCallCode) {
		String json = "{}";
		System.out.println(name() + "::generateProjectCode, projectCallCode = " + projectCallCode);
		
		threadService.generateProjectCodes(projectCallCode);
		return json;
	}
	
	/**
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "threads", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String dataTable1(HttpServletRequest  request, HttpSession session ) {
    	double t0 = System.currentTimeMillis();
    	//System.out.println(name() + "::dataTable1.....");
    	// Get main user info
    	String userCode = session.getAttribute("currentUserCode").toString();
    	String userRole = session.getAttribute("currentUserRole").toString();
    	
    	// Get input filter values
    	String sThreadCategory = request.getParameter("threadCatCode");
    	String sThreadStatus = request.getParameter("threadStatus");
    	String sThreadYear = request.getParameter("threadYear");
    	String sThreadFaculty = request.getParameter("threadFaculty");
    	String sThreadDepartment = request.getParameter("threadDepartment");
    	String sThreadStaff = request.getParameter("threadStaff");
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	//Fetch Page display length
    	Integer iNumberOfItemsOnPage = Integer.valueOf(request.getParameter("iDisplayLength"));
    	Integer iStartItem = Integer.valueOf(request.getParameter("iDisplayStart"));
    	
    	//Create page list data
    	List<mThreadsList> threadsList = createPaginationData(userRole, userCode, iStartItem, iNumberOfItemsOnPage, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);

    	// Total items
    	int iTotalItems = threadService.countItems(userRole, userCode, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
		//List<Threads> threads = threadService.filerThreadsList(userRole, userCode, 0, 100000000, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
    	t0 = System.currentTimeMillis();
    	//List<mThreads> threads = threadService.filerThreadsListNoPagination(userRole, userCode, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
		//iTotalItems = threads.size();
		
		double t = System.currentTimeMillis() - t0;
		t = t *0.001;
		//System.out.println("cpservice/ThreadController::dataTable1, iTotalItems = " + iTotalItems + " year = " + sThreadYear);
    	System.out.println("nProjectController::dataTable1, exe time = " + t + " s");
    	
    	//Search functionality: Returns filtered list based on search parameter
    	threadsList = getListBasedOnSearchParameter(searchParameter, threadsList);
    	
    	mThreadsJsonObject threadsJsonObject = new mThreadsJsonObject();
		//Set Total display record
    	threadsJsonObject.setiTotalDisplayRecords(iTotalItems);
		//Set Total record
    	threadsJsonObject.setiTotalRecords(iTotalItems);
    	threadsJsonObject.setAaData(threadsList);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(threadsJsonObject);
	
		return json2;
    }
    
    private List<mThreadsList> getListBasedOnSearchParameter(String searchParameter, List<mThreadsList> threadsList) {
		
		if (null != searchParameter && !searchParameter.equals("")) {
			List<mThreadsList> threadsListForSearch = new ArrayList<mThreadsList>();
			searchParameter = searchParameter.toUpperCase();
			
			for (mThreadsList thread : threadsList) {
				if (thread.getName().toUpperCase().indexOf(searchParameter)!= -1 || thread.getStatus().toUpperCase().indexOf(searchParameter)!= -1
						|| thread.getStart_date().toUpperCase().indexOf(searchParameter)!= -1 || thread.getEnd_date().toUpperCase().indexOf(searchParameter)!= -1
						) {
					threadsListForSearch.add(thread);					
				}
				
			}
			threadsList = threadsListForSearch;
			threadsListForSearch = null;
		}
		return threadsList;
	}
    
    /**
     * 
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "jury-submitted-projects", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String dataTable2(HttpServletRequest  request, HttpSession session) {
    	//Fetch the page number from client
    	Integer pageNumber = 0;
    	
    	// Get main user info
    	String userCode = session.getAttribute("currentUserCode").toString();
    	String userRole = session.getAttribute("currentUserRole").toString();
    	
    	// Get input filter values
    	String projectCallCode = request.getParameter("projectCallCode");
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	//Fetch Page display length
    	Integer iNumberOfItemsOnPage = Integer.valueOf(request.getParameter("iDisplayLength"));
    	Integer iStartItem = Integer.valueOf(request.getParameter("iDisplayStart"));
    	
    	//Create page list data
    	List<mJurySubmittedProjectsList> projectShow = createPaginationDataProject(userRole, userCode, iStartItem, iNumberOfItemsOnPage, projectCallCode);
    	
    	// Total items
    	int iTotalItems = 10;//productService.countItems(userRole, userCode, sProductStatus, sProductCategory);
		
    	//Search functionality: Returns filtered list based on search parameter
    	//productsList = get2ndListBasedOnSearchParameter(searchParameter, juryOfAnnouncedProjectCallList);
    	
    	mJurySubmittedProjectJsonObject jurySubmittedProjectJsonObject = new mJurySubmittedProjectJsonObject();
		//Set Total display record
    	jurySubmittedProjectJsonObject.setiTotalDisplayRecords(iTotalItems);
		//Set Total record
    	jurySubmittedProjectJsonObject.setiTotalRecords(iTotalItems);
    	jurySubmittedProjectJsonObject.setAaData(projectShow);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(jurySubmittedProjectJsonObject);
	
		return json2;
    }
 
    /**
     * 
     * @param searchParameter
     * @param productsList
     * @return
     */
    /*private List<mJuryOfAnnouncedProjectCall> get2ndListBasedOnSearchParameter(String searchParameter, List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList) {
    	if (null != searchParameter && !searchParameter.equals("")) {
			List<mJuryOfAnnouncedProjectCall> productsListForSearch = new ArrayList<mJuryOfAnnouncedProjectCall>();
			searchParameter = searchParameter.toUpperCase();
			
			for (mJuryOfAnnouncedProjectCall product : juryOfAnnouncedProjectCallList) {
				if (product.getName().toUpperCase().indexOf(searchParameter)!= -1 || product.getStatus().toUpperCase().indexOf(searchParameter)!= -1
						|| product.getStart_date().toUpperCase().indexOf(searchParameter)!= -1 || product.getEnd_date().toUpperCase().indexOf(searchParameter)!= -1
						) {
					productsListForSearch.add(product);					
				}
				
			}
			productsList = productsListForSearch;
			productsListForSearch = null;
		}
		return productsList;
    }*/
    
    private List<mJurySubmittedProjectsList> createPaginationDataProject(String userRole,String userCode, Integer iStartItem, Integer iNumberOfItemsOnPage, String projectCallCode) {
		// Get list products
		if(iStartItem == null || iNumberOfItemsOnPage == null){
			// Set default value
			iStartItem = 0;
			iNumberOfItemsOnPage = 10;
		}
		
		List<mJurySubmittedProjectsList> projectShow = new ArrayList<mJurySubmittedProjectsList>();

		
		// Get project call list
		List<mProjectCalls> projectCallList = projectCallsService.loadProjectCallsList();
		
		HashMap<String, String> projectCallHashMap = new HashMap<String, String>();
		for(int i = 0; i <  projectCallList.size(); i++){
			projectCallHashMap.put(projectCallList.get(i).getPROJCALL_CODE(),projectCallList.get(i).getPROJCALL_NAME());
		}
		
		// Get staff list
		List<mStaff> staffList = staffService.listStaffs();
		
		HashMap<String, String> staffHashMap = new HashMap<String, String>();
		for(int i = 0; i < staffList.size(); i++){
			staffHashMap.put(staffList.get(i).getStaff_Code(), staffList.get(i).getStaff_Name());
		}
				
		//Get jury role of submitted projects
		List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjecsList = juryRoleOfSubmittedProjectsService.loadAllJuryRoleOfSubmittedProjects();
		
		HashMap<String, String> roleHashMap = new HashMap<String, String>();
		for(int i = 0; i < juryRoleOfSubmittedProjecsList.size(); i++){
			roleHashMap.put(juryRoleOfSubmittedProjecsList.get(i).getJUPRJROL_CODE(), juryRoleOfSubmittedProjecsList.get(i).getJUPRJROL_NAME());
		}
				
		//Get jury of announced project call
		List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCallList;
		if(!"".equals(projectCallCode))
		{
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByProjectCallCode(projectCallCode);
		}else{
			juryOfAnnouncedProjectCallList = juryOfAnnouncedProjectCallService.loadAllJuryOfAnnouncedProjectCall();
		}
		
		for(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall : juryOfAnnouncedProjectCallList){
			mJurySubmittedProjectsList productViewElement = new mJurySubmittedProjectsList();
			productViewElement.setProjectcall_name(projectCallHashMap.get(juryOfAnnouncedProjectCall.getJUSUPRJ_PRJCALLCODE()));
			productViewElement.setMember_name(staffHashMap.get(juryOfAnnouncedProjectCall.getJUSUPRJ_STAFFCODE()));
			productViewElement.setRole(roleHashMap.get(juryOfAnnouncedProjectCall.getJUPSURJ_ROLECODE()));
			productViewElement.setId(juryOfAnnouncedProjectCall.getJUSUPRJ_ID());
			
			projectShow.add(productViewElement);
		}
				
		return projectShow;
	}
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param iStartItem
     * @param iNumberOfItemsOnPage
     * @param sProductStatus
     * @param sProductCategory
     * @return
     */
	private List<mThreadsList> listProjectsMultiCriteria(String userRole,String userCode, Integer iStartItem, Integer iNumberOfItemsOnPage, String sThreadStatus, 
			String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff) {
		List<mThreadsList> projects = new ArrayList<mThreadsList>();
		
	
		return projects;
	}
	
	private List<mThreadsList> createPaginationData(String userRole,String userCode, Integer iStartItem, Integer iNumberOfItemsOnPage, String sThreadStatus, 
			String sThreadCategory, String sThreadYear, String sThreadFaculty, String sThreadDepartment, String sThreadStaff) {
		// Get list products
		if(iStartItem == null || iNumberOfItemsOnPage == null){
			// Set default value
			iStartItem = 0;
			iNumberOfItemsOnPage = 10;
		}
		double t0 = System.currentTimeMillis(); 
    	List<mThreads> threadsList = threadService.filerThreadsList(userRole, userCode, iStartItem, iNumberOfItemsOnPage, sThreadStatus, sThreadCategory, sThreadYear, sThreadFaculty, sThreadDepartment, sThreadStaff);
    	double t= System.currentTimeMillis() - t0;
    	t = t*0.001;
    	System.out.println(name() + "::createPaginationData, time for threadService.filterThreadsList = " + t);
    	
		List<mThreadsList> threadShow = new ArrayList<mThreadsList>();
		String sCateName = "";
		String sStatusName = "";
		if(threadsList != null)for (mThreads thread : threadsList) {
			sCateName = this.getCateName(userRole, userCode, thread.getPROJ_ProjCat_Code());
			sStatusName = this.getStatusName(thread.getPROJ_Status_Code());
			
			mThreadsList threadViewElement = new mThreadsList();
			threadViewElement.setUser_code(thread.getPROJ_User_Code());
			threadViewElement.setProjectcode(thread.getPROJ_Code());
			threadViewElement.setName(thread.getPROJ_Name());
			threadViewElement.setCategory(sCateName);
			threadViewElement.setStatus(sStatusName);
			threadViewElement.setStart_date(thread.getPROJ_StartDate());
			threadViewElement.setEnd_date(thread.getPROJ_EndDate());
			threadViewElement.setBudget(thread.getPROJ_TotalBudget());
			threadViewElement.setId(thread.getPROJ_ID());
			threadViewElement.setContent(thread.getPROJ_Content());
			threadViewElement.setResult(thread.getPROJ_Result());
			
			threadShow.add(threadViewElement);
		}
		
		return threadShow;
	}
	
	/**
	 * Get category name by its code
	 * @param userRole
	 * @param userCode
	 * @param sCateCode
	 * @return
	 */
	private String getCateName(String userRole, String userCode, String sCateCode)
	{
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();
		String sCateName = "";
		//System.out.println("nProjectController::getCateName(" + sCateCode +"), topicCategory = " + topicCategory.size());
		if(!topicCategory.equals(""))
		{
			for(mTopicCategory category : topicCategory)
			{
				if(sCateCode.equals(category.getPROJCAT_Code())){
					sCateName = category.getPROJCAT_Name();
					break;
				}
			}
		}
		return sCateName;
	}
	
	/**
	 * Get status name
	 * @param sStatusCode
	 * @return
	 */
	private String getStatusName(String sStatusCode)
	{
		List<mProjectStatus> projectStatuses = projectStatusService.list();
		String sStatusName = "";
		if(!projectStatuses.equals(""))
		{
			for(mProjectStatus status : projectStatuses)
			{
				if(sStatusCode.equals(status.getPROJSTAT_Code())){
					sStatusName = status.getPROJSTAT_Description();
					break;
				}
			}
		}
		return sStatusName;
	}
	
	/**
	 * 
	 * @param sProjectCallCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getprojectslist", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getProjectsList(@RequestParam(value = "sProjectCallCode", defaultValue = "0") String sProjectCallCode) {
		String sReturn = "";
		System.out.println("");
		// Get department lists
		if(!"".equals(sProjectCallCode))
		{
			// Get all projects in project calls whose present is the current present
			List<Projects> projectList = projectService.loadProjectByProjectCallId(sProjectCallCode);
			sReturn = "<select size='20' data-validation='required' data-validation-error-msg='Trường thông tin này là bắt buộc' multiple class='form-control' path='STFJUPRJ_PRJCODE' name='STFJUPRJ_PRJCODE' id='STFJUPRJ_PRJCODE'>";
			if(projectList != null){
				for(Projects project : projectList)
				{
					sReturn += "<option value='"+project.getPROJ_Code()+"'>"+project.getPROJ_Code() + " - " +project.getPROJ_Name()+"</option>";
				}
			}else{
				sReturn += "<option value=''>Chọn</option>";
			}
			sReturn += "</select>";
		}
		return sReturn;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getstaffslist", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getStaffsList(@RequestParam(value = "sProjectCallCode", defaultValue = "0") String sProjectCallCode) {
		String sReturn = "";
		// Get department lists
		if(!"".equals(sProjectCallCode))
		{
			// Get all projects in project calls whose present is the current present
			List<mJuryOfAnnouncedProjectCall> juryOfAnnouncedProjectCall = juryOfAnnouncedProjectCallService.loadListJuryOfAnnouncedProjectCallByProjectCallCode(sProjectCallCode);
			sReturn = "<select class='form-control' data-validation='required' data-validation-error-msg='Trường thông tin này là bắt buộc' path='STFJUPRJ_STAFFJURCODE' name='STFJUPRJ_STAFFJURCODE' id='STFJUPRJ_STAFFJURCODE'>";
			if(juryOfAnnouncedProjectCall != null){
				for(mJuryOfAnnouncedProjectCall iteam : juryOfAnnouncedProjectCall)
				{
					sReturn += "<option value='"+iteam.getJUSUPRJ_STAFFCODE()+"'>"+iteam.getJUSUPRJ_STAFFCODE()+"</option>";
				}
			}else{
				sReturn += "<option value=''>Chọn</option>";
			}
			sReturn += "</select>";
		}
		return sReturn;
	}
}
