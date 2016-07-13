package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;

public interface tProjectCategoryDAO {
	/**
	 * 
	 * @return
	 */
	public List<mTopicCategory> getList();
    
	/**
	 * 
	 * @param topicCategoryCode
	 * @return
	 */
    public mTopicCategory getTopicCategoryByCode(String topicCategoryCode);
}
