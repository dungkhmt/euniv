package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;

public interface mPaperService {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
	
	public List<mPapers> loadPaperListByStaffYear(String userCode, String year);
	
	public List<mPapers> loadPaperListByStaff(String userRole, String userCode);
	
	/**
	 * 
	 * @param paperStaff
	 * @param paperCategory
	 * @param paperAcademicYear
	 * @return
	 */
	public List<mPapers> loadPaperListSummary(String paperStaff, String paperCategory, String paperAcademicYear);
    
	/**
	 * 
	 * @param userRole
	 * @param currentUserCode
	 * @param reportingrYear
	 * @return
	 */
    public List<mPapers> loadPaperListByYear(String userRole, String currentUserCode, String reportingrYear);
    
    /**
     * 
     * @param reportingrYear
     * @return
     */
    public List<mPapers> loadPaperSummaryListByYear(String reportingrYear);
    
    /**
     * 
     * @param currentUserName
     * @param paperCatCode
     * @param paperPubName
     * @param paperJConfName
     * @param paperISSN
     * @param paperPubConHours
     * @param paperAutConHours
     * @param paperYear
     * @param paperJIndexCode
     * @param paperVolumn
     * @param paperAuthors
     * @param paperReportingAcademicDate
     * @param paperSourceUploadFile
     * @return
     */
    public int saveAPaper(String currentUserName, String paperCatCode, String paperPubName, String paperJConfName, String paperISSN, int paperPubConHours, int paperAutConHours, int paperYear, String paperJIndexCode, 
    						String paperVolumn, String paperAuthors, String paperReportingAcademicDate, String paperSourceUploadFile, String[] projectMembers, String ApproveStatus, String paperMonth);
    
    /**
     * 
     */
    public mPapers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId);
    public mPapers loadAPaperById(int paperId);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param paperId
     * @param paperCate
     * @param publicationName
     * @param journalName
     * @param ISSN
     * @param publicConvertedHours
     * @param authorConvertedHours
     * @param paperYear
     * @param volumn
     * @param authors
     * @param journalIndex
     * @param paperReportingAcademicDate
     * @param paperSourceUploadFile
     */
    //public void editAPaper(String userRole, String userCode, int paperId, String paperCate, String publicationName, String journalName, String ISSN, int publicConvertedHours, 
    //						int authorConvertedHours, int paperYear, String volumn, String authors, String journalIndex, String paperReportingAcademicDate, String paperSourceUploadFile, String[] projectMembers, String paperMonth );
    
    public void editAPaper(int paperId, String paperCate, String publicationName, String journalName, String ISSN, int publicConvertedHours, int authorConvertedHours, int paperYear, 
			String volumn, String authors, String journalIndex, String paperReportingAcademicDate, String paperSourceUploadFile, String[] projectMembers, String paperMonth );

    /**
     * 
     * @param paperId
     * @return
     */
    public int removeAPaper(int paperId);
}
