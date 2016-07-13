package vn.webapp.modules.researchmanagement.service;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProducts;

public interface mProductService {
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mProducts> loadProductsListByStaff(String userRole, String userCode);

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param iStartItem
	 * @param iNumberOfItems
	 * @param sProductStatus
	 * @param sProductCategory
	 * @return
	 */
	public List<mProducts> filerProductsList(String userRole, String userCode, Integer iStartItem, Integer iNumberOfItems, String sProductStatus, String sProductCategory);
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param sProductStatus
	 * @param sProductCategory
	 * @return
	 */
	public int countItems(String userRole, String userCode, String sProductStatus, String sProductCategory);

	/**
	 * 
	 * @param userCode
	 * @param productName
	 * @param productProject
	 * @param productStartDate
	 * @param productEndDate
	 * @param productStatus
	 * @param productBudget
	 * @param productCode
	 * @param productSourceUploadFileSrc
	 * @return
	 */
	public int saveAProduct(String userCode,String productName,String productProject,String productStartDate,String productEndDate,
							String productStatus,int productBudget,String productCode,String productSourceUploadFileSrc);

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param productId
	 * @return
	 */
	public mProducts loadAProductByIdAndUserCode(String userRole, String userCode, int productId);

	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param productName
	 * @param productProject
	 * @param productStartDate
	 * @param productEndDate
	 * @param productStatus
	 * @param productBudget
	 * @param productCode
	 * @param productSourceUploadFileSrc
	 * @param productId
	 */
	public void editAProduct(String userRole, String userCode,String productName,String productProject,String productStartDate,String productEndDate,
								String productStatus,int productBudget,String productCode,String productSourceUploadFileSrc, int productId);

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public int removeAProduct(int productId);
}
