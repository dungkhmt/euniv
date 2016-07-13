package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mPatentDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

@Service("mPatentService")
public class mPatentServiceImpl implements mPatentService {
	@Autowired
    private mPatentDAO patentDAO;

    /**
     * Get a list Patents by user code
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPatents> loadPatentListByStaff(String userRole, String userCode) {
        try {
        	return patentDAO.loadPatentListByStaff(userRole, userCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Patent by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPatents> loadPatentListByYear(String userRole, String userCode, String reportingrYear) {
    	try {
    		if(userCode != null){
    			return patentDAO.loadPatentListByYear(userRole, userCode, reportingrYear);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Summary Patent by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPatents> loadPatentSummaryListByYear(String reportingrYear) {
    	try {
    		return patentDAO.loadPatentSummaryListByYear(reportingrYear);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a Patent
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    public int saveAPatent(String userCode, String patentName, int patentConVertedHours, int patentAutConHours, 
    						String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate)
    {
    	if(userCode != null){
    		mPatents patent = new mPatents();
    		patent.setPAT_Name(patentName);
    		patent.setPAT_Authors(patentAuthors);
    		patent.setPAT_DateOfIssue(patentDateOfIssue);
    		patent.setPAT_Number(patentNumber);
    		patent.setPAT_Type(patentType);
    		patent.setPAT_User_Code(userCode);
    		patent.setPAT_ConvertedHours(patentConVertedHours);
    		patent.setPAT_AuthorConvertedHours(patentAutConHours);
    		patent.setPAT_ReportingAcademicDate(patentReportingAcademicDate);
            int i_SaveAPatent = patentDAO.saveAPatent(patent);
            return i_SaveAPatent;
    	}
        return 0;
    }
    
    /**
     * load a patent by usercode and it's id
     * @param String
     * @param int
     * @return object
     */
    @Override
    public mPatents loadAPatentByIdAndUserCode(String userRole, String userCode, int patentId){
    	try {
    		return patentDAO.loadAPatentByIdAndUserCode(userRole, userCode, patentId);
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a patent
     * @param String
     * @param int
     * @return null
     */
    @Override
    public void editAPatent(String userRole, String userCode, int patentId, String patentName, int patentConVertedHours, 
								int patentAutConHours, String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate){
    	mPatents patent = patentDAO.loadAPatentByIdAndUserCode(userRole, userCode, patentId);
    	if(patent != null){
    		patent.setPAT_ID(patentId);
    		patent.setPAT_Name(patentName);
    		patent.setPAT_Authors(patentAuthors);
    		patent.setPAT_DateOfIssue(patentDateOfIssue);
    		patent.setPAT_Number(patentNumber);
    		patent.setPAT_Type(patentType);
    		patent.setPAT_User_Code(userCode);
    		patent.setPAT_ConvertedHours(patentConVertedHours);
    		patent.setPAT_AuthorConvertedHours(patentAutConHours);
    		patent.setPAT_ReportingAcademicDate(patentReportingAcademicDate);
    		
    		patentDAO.editAPatent(patent);
    	}
    }
    
    /**
     * Remove a patent
     * @param int
     * @return int
     */
    @Override
    public int removeAPatent(int patentId){
    	return patentDAO.removeAPatent(patentId);
    }
}
