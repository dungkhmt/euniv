package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mProducts;

public interface mProductDAO {
	public List<mProducts> loadProductsListByStaff(String userRole, String userCode);
	
	public List<mProducts> filerProductsList(String userRole, String userCode, Integer iStartItem, Integer iNumberOfItems, String sProductStatus, String sProductCategory);
	
	public int countItems(String userRole, String userCode, String sProductStatus, String sProductCategory);

	public int saveAProduct(mProducts product);

	public mProducts loadAProductByIdAndUserCode(String userRole, String userCode, int productId);

	public void editAProduct(mProducts product);

	public int removeAProduct(int productId);
}
