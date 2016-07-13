package vn.webapp.modules.researchmanagement.controller.cpservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vn.webapp.controller.BaseRest;
import vn.webapp.modules.researchmanagement.model.mProducts;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.service.mProductService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmServiceProduct")
@RequestMapping(value = {"/cpservice"})
public class mProductController extends BaseRest {
	@Autowired
    private mStaffService staffService;
	
	@Autowired
	private mProductService productService;
	
	@Autowired
	private nProjectService threadService;
	
	@Autowired
	private mProjectStatusService projectStatusService;
    
	/**
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "products", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String dataTable1(HttpServletRequest  request, HttpSession session) {
    	//Fetch the page number from client
    	Integer pageNumber = 0;
    	
    	// Get main user info
    	String userCode = session.getAttribute("currentUserCode").toString();
    	String userRole = session.getAttribute("currentUserRole").toString();
    	
    	// Get input filter values
    	String sProductStatus = request.getParameter("productStatus");
    	String sProductCategory = request.getParameter("productCatCode");
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	//Fetch Page display length
    	Integer iNumberOfItemsOnPage = Integer.valueOf(request.getParameter("iDisplayLength"));
    	Integer iStartItem = Integer.valueOf(request.getParameter("iDisplayStart"));
    	
    	//Create page list data
    	List<mProductsList> productsList = createPaginationData(userRole, userCode, iStartItem, iNumberOfItemsOnPage, sProductStatus, sProductCategory);

    	// Total items
    	int iTotalItems = productService.countItems(userRole, userCode, sProductStatus, sProductCategory);
		
    	//Search functionality: Returns filtered list based on search parameter
    	productsList = getListBasedOnSearchParameter(searchParameter, productsList);
    	
    	mProductsJsonObject productsJsonObject = new mProductsJsonObject();
		//Set Total display record
		productsJsonObject.setiTotalDisplayRecords(iTotalItems);
		//Set Total record
		productsJsonObject.setiTotalRecords(iTotalItems);
		productsJsonObject.setAaData(productsList);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(productsJsonObject);
	
		return json2;
    }
    
    /**
     * 
     * @param searchParameter
     * @param productsList
     * @return
     */
    private List<mProductsList> getListBasedOnSearchParameter(String searchParameter, List<mProductsList> productsList) {
		
		if (null != searchParameter && !searchParameter.equals("")) {
			List<mProductsList> productsListForSearch = new ArrayList<mProductsList>();
			searchParameter = searchParameter.toUpperCase();
			
			for (mProductsList product : productsList) {
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
	private List<mProductsList> createPaginationData(String userRole,String userCode, Integer iStartItem, Integer iNumberOfItemsOnPage, String sProductStatus, String sProductCategory) {
		// Get list products
		if(iStartItem == null || iNumberOfItemsOnPage == null){
			// Set default value
			iStartItem = 0;
			iNumberOfItemsOnPage = 10;
		}
    	List<mProducts> productsList = productService.filerProductsList(userRole, userCode, iStartItem, iNumberOfItemsOnPage, sProductStatus, sProductCategory);
		
		List<mProductsList> productShow = new ArrayList<mProductsList>();
		String sCateName = "";
		String sStatusName = "";
		for (mProducts product : productsList) {
			sCateName = this.getCateName(userRole, userCode, product.getPROD_ProjCode());
			sStatusName = this.getStatusName(product.getPROD_Status_Code());
			
			mProductsList productViewElement = new mProductsList();
			productViewElement.setName(product.getPROD_Name());
			productViewElement.setCategory(sCateName);
			productViewElement.setStatus(sStatusName);
			productViewElement.setStart_date(product.getPROD_StartDate());
			productViewElement.setEnd_date(product.getPROD_EndDate());
			productViewElement.setBudget(product.getPROD_Budget());
			productViewElement.setId(product.getPROD_ID());
			
			productShow.add(productViewElement);
		}
		
		return productShow;
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
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		String sCateName = "";
		if(!threadsList.equals(""))
		{
			for(mThreads thread : threadsList)
			{
				if(sCateCode.equals(thread.getPROJ_Code())){
					sCateName = thread.getPROJ_Name();
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
}
