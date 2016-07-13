package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;

public interface tProjectCategoryService {
	/**
	 * 
	 * @return
	 */
	public List<mTopicCategory> list();
    
	/**
	 * 
	 * @param topicCategoryCode
	 * @return
	 */
    public mTopicCategory getTopicCategoryByCode(String topicCategoryCode);
}
