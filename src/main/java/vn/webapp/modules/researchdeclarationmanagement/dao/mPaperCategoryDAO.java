package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;

public interface mPaperCategoryDAO {
	/**
	 * 
	 * @return
	 */
	public List<mPaperCategory> getList();
    
	/**
	 * 
	 * @param paperCateCode
	 * @return
	 */
    public mPaperCategory getPaperCateByCode(String paperCateCode);
}
