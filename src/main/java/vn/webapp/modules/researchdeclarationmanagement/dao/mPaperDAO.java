package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;

public interface mPaperDAO {
	/**
	 * 
	 * @param userRole
	 * @param userCode
	 * @return
	 */
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
	 * @param userCode
	 * @param reportingrYear
	 * @return
	 */
    public List<mPapers> loadPaperListByYear(String userRole, String userCode, String reportingrYear);
    
    /**
     * 
     * @param reportingrYear
     * @return
     */
    public List<mPapers> loadPaperSummaryListByYear(String reportingrYear);
    
    /**
     * 
     * @param paper
     * @return
     */
    public int saveAPaper(mPapers paper);
    
    /**
     * 
     * @param userRole
     * @param userCode
     * @param paperId
     * @return
     */
    public mPapers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId);
    
    /**
     * 
     * @param paperId
     * @return
     */
    public mPapers loadAPaperById(int paperId);
    
    /**
     * 
     * @param paper
     */
    public void editAPaper(mPapers paper);
    
    /**
     * 
     * @param paperId
     * @return
     */
    public int removeAPaper(int paperId);
}
