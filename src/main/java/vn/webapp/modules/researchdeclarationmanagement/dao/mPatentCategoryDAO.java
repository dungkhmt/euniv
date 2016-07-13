package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPatentCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

public interface mPatentCategoryDAO {
	
	/**
	 * @return
	 */
	public List<mPatentCategory> loadPatentCategoryList();
	
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public mPatentCategory loadPatentCategoryListByCode(String patentCateCode);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param patentId
     * @return
     */
    public mPatentCategory loadAPatentCategoryById(int patentCateId);

}
