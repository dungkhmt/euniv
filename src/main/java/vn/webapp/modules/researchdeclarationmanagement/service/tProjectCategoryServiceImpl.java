package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectCategoryDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;

@Service("tProjectCategoryService")
public class tProjectCategoryServiceImpl implements tProjectCategoryService{
	@Autowired
    private tProjectCategoryDAO tProjectCategoryDAO;

    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<mTopicCategory> list(){
    	return tProjectCategoryDAO.getList();
    }
    
    /**
     * get a paper category by code
     * @param String
     * @return object
     */
    @Override
    public mTopicCategory getTopicCategoryByCode(String topicCategoryCode){
    	if(topicCategoryCode != null){
    		return tProjectCategoryDAO.getTopicCategoryByCode(topicCategoryCode);
    	}
    	return null;
    }
}
