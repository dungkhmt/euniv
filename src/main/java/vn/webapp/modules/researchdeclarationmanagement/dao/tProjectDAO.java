package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;

public interface tProjectDAO {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mTopics> loadTopicListByStaff(String userRole, String userCode);
    
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param reportingrYear
	 * @return
	 */
    public List<mTopics> loadTopicListByYear(String userRole, String userCode, String reportingrYear);
    
    /**
     * 
     * @param reportingrYear
     * @return
     */
    public List<mTopics> loadTopicSummaryListByYear(String reportingrYear);
    
    /**
     * 
     * @param topic
     * @return
     */
    public int saveATopic(mTopics topic);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param topicId
     * @return
     */
    public mTopics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId);
    
    /**
     * 
     * @param topic
     */
    public void editATopic(mTopics topic);
    
    /**
     * 
     * @param topicId
     * @return
     */
    public int removeATopic(int topicId);
}
