package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.usermanagement.dao.mUserDAO;

@Service("tProjectService")
public class tProjectServiceImpl implements tProjectService {
	@Autowired
    private tProjectDAO tProjectDAO;

    @Autowired
    private mUserDAO userDAO;
    
    /**
     * Get a list Topics by user code
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public List<mTopics> loadTopicListByStaff(String userRole, String userCode) {
        try {
        	return tProjectDAO.loadTopicListByStaff(userRole, userCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Topics by year and user
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public List<mTopics> loadTopicListByYear(String userRole, String userCode,  String reportingrYear) {
    	try {
    		if(userCode != null){
    			return tProjectDAO.loadTopicListByYear(userRole, userCode, reportingrYear);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Topics by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public List<mTopics> loadTopicSummaryListByYear(String reportingrYear){
    	try {
    		if(reportingrYear != null){
    			return tProjectDAO.loadTopicSummaryListByYear(reportingrYear);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a topic
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    @Transactional
    public int saveATopic(String userCode, String topicPubName, String topicCategory, int topicConVertedHours, int topicAutConHours, int topicYear, int topicBudget,
    						String topicReportingAcademicDate,String topicMemberRole,String topicSponsor,String topicApprover,String topicStartDate,String topicEndDate)
    {
    	if(userCode != null){
    		mTopics topic = new mTopics();
    		topic.setPROJDECL_Name(topicPubName);
    		topic.setPROJDECL_ProjCategory_Code(topicCategory);
    		topic.setPROJDECL_User_Code(userCode);
    		topic.setPROJDECL_Year(topicYear);
    		topic.setPROJDECL_ConvertedHours(topicConVertedHours);
    		topic.setPROJDECL_AuthorConvertedHours(topicAutConHours);
    		topic.setPROJDECL_Budget(topicBudget);
    		topic.setPROJDECL_ReportingAcademicDate(topicReportingAcademicDate);
    		topic.setPROJDECL_RoleCode(topicMemberRole);
    		topic.setPROJDECL_Sponsor(topicSponsor);
    		topic.setPROJDECL_ApproveUserCode(topicApprover);
    		topic.setPROJDECL_StartDate(topicStartDate);
    		topic.setPROJDECL_EndDate(topicEndDate);
            int i_SaveATopic = tProjectDAO.saveATopic(topic);
            return i_SaveATopic;
    	}
        return 0;
    }
    
    /**
     * load a paper by usercode and it's id
     * @param String
     * @param int
     * @return object
     */
    @Override
    @Transactional
    public mTopics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId){
    	try {
    		return tProjectDAO.loadATopicByIdAndUserCode(userRole, userCode, topicId);
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a topic
     * @param String
     * @param int
     * @return null
     */
    @Override
    @Transactional
    public void editATopic(String userRole, String userCode, int topicId, String topicPubName, String topicCategory, int topicConVertedHours, Integer topicAutConHours, int topicYear, int topicBudget, 
    						String topicReportingAcademicDate,String topicMemberRole,String topicSponsor,String topicApprover,String topicStartDate,String topicEndDate){
    	mTopics topic = tProjectDAO.loadATopicByIdAndUserCode(userRole, userCode, topicId);
    	if(topic != null){
    		topic.setPROJDECL_ID(topicId);
    		topic.setPROJDECL_Name(topicPubName);
    		topic.setPROJDECL_ProjCategory_Code(topicCategory);
    		topic.setPROJDECL_User_Code(userCode);
    		topic.setPROJDECL_Year(topicYear);
    		topic.setPROJDECL_ConvertedHours(topicConVertedHours);
    		topic.setPROJDECL_AuthorConvertedHours(topicAutConHours);
    		topic.setPROJDECL_Budget(topicBudget);
    		topic.setPROJDECL_ReportingAcademicDate(topicReportingAcademicDate);
    		topic.setPROJDECL_RoleCode(topicMemberRole);
    		topic.setPROJDECL_Sponsor(topicSponsor);
    		topic.setPROJDECL_ApproveUserCode(topicApprover);
    		topic.setPROJDECL_StartDate(topicStartDate);
    		topic.setPROJDECL_EndDate(topicEndDate);
    		tProjectDAO.editATopic(topic);
    	}
    }
    
    /**
     * Remove a topic
     * @param int
     * @return int
     */
    @Override
    @Transactional
    public int removeATopic(int topicId){
    	return tProjectDAO.removeATopic(topicId);
    }
}
