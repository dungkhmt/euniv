package vn.webapp.modules.researchmanagement.service;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchmanagement.dao.mProductDAO;
import vn.webapp.modules.researchmanagement.model.mProducts;
import vn.webapp.modules.usermanagement.dao.mUserDAO;

@Service("mProductService")
public class mProductServiceImpl implements mProductService {
	@Autowired
	private mProductDAO productDAO;

	@Autowired
	private mUserDAO userDAO;

	/**
	 * Get a list products by user code
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mProducts> loadProductsListByStaff(String userRole, String userCode) {
		try {
			return productDAO.loadProductsListByStaff(userRole, userCode);
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
	@Override
	public List<mProducts> filerProductsList(String userRole, String userCode, Integer iStartItem, Integer iNumberOfItems, String sProductStatus, String sProductCategory){
		try {
			return productDAO.filerProductsList(userRole, userCode, iStartItem, iNumberOfItems, sProductStatus, sProductCategory);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Count items
	 * @param 
	 * @return int
	 */
	public int countItems(String userRole, String userCode, String sProductStatus, String sProductCategory){
		try {
			return productDAO.countItems(userRole, userCode, sProductStatus, sProductCategory);
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
	public int saveAProduct(String userCode,String productName,String productProject,String productStartDate,String productEndDate,
								String productStatus,int productBudget,String productCode,String productSourceUploadFileSrc) {
		if (userCode != null) {
			mProducts product = new mProducts();
			product.setPROD_Budget(productBudget);
			product.setPROD_Code(productCode);
			product.setPROD_EndDate(productEndDate);
			product.setPROD_Name(productName);
			product.setPROD_ProjCode(productProject);
			product.setPROD_SourceFile(productSourceUploadFileSrc);
			product.setPROD_StartDate(productStartDate);
			product.setPROD_Status_Code(productStatus);
			product.setPROD_User_Code(userCode);
			int i_SaveAproduct = productDAO.saveAProduct(product);
			return i_SaveAproduct;
		}
		return 0;
	}

	/**
	 * load a product by usercode and it's id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	@Override
	public mProducts loadAProductByIdAndUserCode(String userRole, String userCode, int productId) {
		try {
			return productDAO.loadAProductByIdAndUserCode(userRole, userCode, productId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Edit a product
	 * 
	 * @param String
	 * @param int
	 * @return null
	 */
	@Override
	public void editAProduct(String userRole, String userCode,String productName,String productProject,String productStartDate,String productEndDate,
								String productStatus,int productBudget,String productCode,String productSourceUploadFileSrc, int productId) {
		mProducts product = productDAO.loadAProductByIdAndUserCode(userRole, userCode, productId);
		if (product != null) {
			product.setPROD_Budget(productBudget);
			product.setPROD_Code(productCode);
			product.setPROD_EndDate(productEndDate);
			product.setPROD_Name(productName);
			product.setPROD_ProjCode(productProject);
			product.setPROD_StartDate(productStartDate);
			product.setPROD_Status_Code(productStatus);
			product.setPROD_User_Code(userCode);
			
			// Reset source file
			if(productSourceUploadFileSrc.equals(""))
	    	{
				product.setPROD_SourceFile(product.getPROD_SourceFile());
	    	}else{
	    		
	    		String sOldSourceFile = product.getPROD_SourceFile();
		   		if((sOldSourceFile != null)){
		   			File oldFile = new File(sOldSourceFile);
			   		oldFile.delete();
		   		}
		   		product.setPROD_SourceFile(productSourceUploadFileSrc);
	    	}
			
			productDAO.editAProduct(product);
		}
	}

	/**
	 * Remove a topic
	 * 
	 * @param int
	 * @return int
	 */
	@Override
	public int removeAProduct(int productId) {
		return productDAO.removeAProduct(productId);
	}
}
