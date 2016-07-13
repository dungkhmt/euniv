package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPatentCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

public interface mPatentCategoryService {
	/**
	 * 
	 * @return
	 */
	public List<mPatentCategory> loadPatentCategoryList();
    
	/**
	 * 
	 * @return
	 */
	public mPatentCategory loadPatentCategoryListByCode(String patentCateCode);
    
    /**
     * 
     * @return
     */
	public mPatentCategory loadAPatentCategoryById(int patentCateId);
}
