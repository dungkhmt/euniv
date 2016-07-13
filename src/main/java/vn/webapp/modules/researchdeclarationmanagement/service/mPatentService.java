package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

public interface mPatentService {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	public List<mPatents> loadPatentListByStaff(String userRole, String userCode);
    
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @param reportingrYear
	 * @return
	 */
    public List<mPatents> loadPatentListByYear(String userRole, String userCode, String reportingrYear);
    
    /**
     * 
     * @param reportingrYear
     * @return
     */
    public List<mPatents> loadPatentSummaryListByYear(String reportingrYear);
    
    /**
     * 
     * @param userCode
     * @param patentName
     * @param patentConVertedHours
     * @param patentAutConHours
     * @param patentDateOfIssue
     * @param patentAuthors
     * @param patentType
     * @param patentNumber
     * @param patentReportingAcademicDate
     * @return
     */
    public int saveAPatent(String userCode, String patentName, int patentConVertedHours, int patentAutConHours, 
							String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param patentId
     * @return
     */
    public mPatents loadAPatentByIdAndUserCode(String userRole, String userCode, int patentId);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param patentId
     * @param patentName
     * @param patentConVertedHours
     * @param patentAutConHours
     * @param patentDateOfIssue
     * @param patentAuthors
     * @param patentType
     * @param patentNumber
     * @param patentReportingAcademicDate
     */
    public void editAPatent(String userRole, String userCode, int patentId, String patentName, int patentConVertedHours, 
    						int patentAutConHours, String patentDateOfIssue, String patentAuthors, String patentType, String patentNumber, String patentReportingAcademicDate);
    
    /**
     * 
     * @param patentId
     * @return
     */
    public int removeAPatent(int patentId);
}
