package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;

public interface mPaperCategoryService {
	/**
	 * 
	 * @return
	 */
	public List<mPaperCategory> list();
    
	/**
	 * 
	 * @param paperCateCode
	 * @return
	 */
    public mPaperCategory getPaperCateByCode(String paperCateCode);
}
