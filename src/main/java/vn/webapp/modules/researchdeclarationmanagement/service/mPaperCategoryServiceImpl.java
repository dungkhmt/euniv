package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mPaperCategoryDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;

@Service("mPaperCategoryService")
public class mPaperCategoryServiceImpl implements mPaperCategoryService{
	@Autowired
    private mPaperCategoryDAO paperCategoryDAO;

    public void setPaperCategoryDAO(mPaperCategoryDAO paperCategoryDAO) {
        this.paperCategoryDAO = paperCategoryDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<mPaperCategory> list(){
    	return paperCategoryDAO.getList();
    }
    
    /**
     * get a paper category by code
     * @param String
     * @return object
     */
    @Override
    public mPaperCategory getPaperCateByCode(String paperCateCode){
    	if(paperCateCode != null){
    		return paperCategoryDAO.getPaperCateByCode(paperCateCode);
    	}
    	return null;
    }
}
