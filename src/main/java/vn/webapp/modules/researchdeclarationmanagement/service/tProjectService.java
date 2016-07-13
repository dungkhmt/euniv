package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;

public interface tProjectService {
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
     * @param userCode
     * @param topicPubName
     * @param topicCategory
     * @param topicConVertedHours
     * @param topicAutConHours
     * @param topicYear
     * @param topicBudget
     * @param topicReportingAcademicDate
     * @return
     */
    public int saveATopic(String userCode, String topicPubName, String topicCategory, int topicConVertedHours, int topicAutConHours, int topicYear, int topicBudget, 
    						String topicReportingAcademicDate,String topicMemberRole,String topicSponsor,String topicApprover,String topicStartDate,String topicEndDate);
    
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
     * @param userRole
     * @param userCode
     * @param topicId
     * @param topicPubName
     * @param topicCategory
     * @param topicConVertedHours
     * @param topicAutConHours
     * @param topicYear
     * @param topicBudget
     * @param topicReportingAcademicDate
     */
    public void editATopic(String userRole, String userCode, int topicId, String topicPubName, String topicCategory, int topicConVertedHours, Integer topicAutConHours, int topicYear, int topicBudget, 
    						String topicReportingAcademicDate,String topicMemberRole,String topicSponsor,String topicApprover,String topicStartDate,String topicEndDate);
    
    /**
     * 
     * @param topicId
     * @return
     */
    public int removeATopic(int topicId);
}
