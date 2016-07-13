package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

public interface mPatentDAO {
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
     * @param patent
     * @return
     */
    public int saveAPatent(mPatents patent);
    
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
     * @param patent
     */
    public void editAPatent(mPatents patent);
    
    /**
     * 
     * @param patentId
     * @return
     */
    public int removeAPatent(int patentId);	
}
