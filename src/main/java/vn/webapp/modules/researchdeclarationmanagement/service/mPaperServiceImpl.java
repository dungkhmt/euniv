package vn.webapp.modules.researchdeclarationmanagement.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.controller.cp.mPaperController;
import vn.webapp.modules.researchdeclarationmanagement.dao.PaperStaffsDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.mPaperDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.usermanagement.dao.mUserDAO;
import vn.webapp.modules.usermanagement.model.mUsers;

@Service("mPaperService")
public class mPaperServiceImpl implements mPaperService{
	@Autowired
    private mPaperDAO paperDAO;

    @Autowired
    private mUserDAO userDAO;
    
    @Autowired
    private PaperStaffsDAO paperStaffsDAO;
    
    /**
     * Get a list Papers by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPapers> loadPaperListByStaff(String userRole, String userCode) {
        try {
        	return paperDAO.loadPaperListByStaff(userRole, userCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<mPapers> loadPaperListSummary(String paperStaff, String paperCategory, String paperAcademicYear) {
        try {
        	return paperDAO.loadPaperListSummary(paperStaff, paperCategory, paperAcademicYear);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Papers by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPapers> loadPaperListByYear(String userRole, String currentUserCode, String reportingrYear){
    	try {
        	if(currentUserCode != null){
        		return paperDAO.loadPaperListByYear(userRole, currentUserCode, reportingrYear);
        	}
        	return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Papers Summary by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<mPapers> loadPaperSummaryListByYear(String reportingrYear){
    	try {
        	if(reportingrYear != null){
        		return paperDAO.loadPaperSummaryListByYear(reportingrYear);
        	}
        	return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a paper
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    public int saveAPaper(String currentUserName, String paperCatCode, String paperPubName, String paperJConfName, String paperISSN, int paperPubConHours, int paperAutConHours, int paperYear, String paperJIndexCode, 
    						String paperVolumn, String paperAuthors, String paperReportingAcademicDate, String paperSourceUploadFile, String[] projectMembers, String ApproveStatus, String paperMonth)
    {
    	mUsers user = userDAO.getByUsername(currentUserName);
    	if(user.getUser_Code()  != null){
    		mPapers paper = new mPapers();
            paper.setPDECL_PaperCategory_Code(paperCatCode);
            paper.setPDECL_User_Code(user.getUser_Code());
            paper.setPDECL_Code("DEFAULT_CODE");
            paper.setPDECL_PublicationName(paperPubName);
            paper.setPDECL_JournalConferenceName(paperJConfName);
            paper.setPDECL_Volumn(paperVolumn);
            paper.setPDECL_Year(paperYear);
            paper.setPDECL_ISSN(paperISSN);
            paper.setPDECL_IndexCode(paperJIndexCode);
            paper.setPDECL_PublicationConvertedHours(paperPubConHours);
            paper.setPDECL_AuthorConvertedHours(paperAutConHours);
            paper.setPDECL_AuthorList(paperAuthors);
            paper.setPDECL_ReportingAcademicDate(paperReportingAcademicDate);
            paper.setPDECL_SourceFile(paperSourceUploadFile);
            paper.setPDECL_ApproveStatus(ApproveStatus);
            paper.setPDECL_Month(paperMonth);
            int i_SaveAPaper = paperDAO.saveAPaper(paper);
            
            if(i_SaveAPaper > 0 && projectMembers.length > 0){
            	String PPSTF_PaperCode = user.getUser_Code()+i_SaveAPaper;
            	String PPSTF_Code = "";
            	paper.setPDECL_Code(PPSTF_PaperCode);
            	paperDAO.editAPaper(paper);
            	
            	PaperStaffs paperStaffs = new PaperStaffs();
	            for (String projectMember : projectMembers) {
	            	PPSTF_Code = projectMember+PPSTF_PaperCode;
	            	paperStaffs.setPPSTF_Code(PPSTF_Code);
	            	paperStaffs.setPPSTF_PaperCode(PPSTF_PaperCode);
	            	paperStaffs.setPPSTF_StaffCode(projectMember);
		    	    paperStaffsDAO.saveAPaperStaff(paperStaffs);
		    	}
            }
            
            return i_SaveAPaper;
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
    public mPapers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId){
    	try {
    		return paperDAO.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a paper
     * @param String
     * @param int
     * @return null
     */
    @Override
    public void editAPaper(String userRole, String userCode, int paperId, String paperCate, String publicationName, String journalName, String ISSN, int publicConvertedHours, int authorConvertedHours, int paperYear, 
    						String volumn, String authors, String journalIndex, String paperReportingAcademicDate, String paperSourceUploadFile, String[] projectMembers, String paperMonth ){
    	mPapers paper = paperDAO.loadAPaperByIdAndUserCode(userRole, userCode, paperId);
    	if(paper != null){
	    	paper.setPDECL_ID(paperId);;
	    	paper.setPDECL_PaperCategory_Code(paperCate);;
	    	paper.setPDECL_AuthorConvertedHours(authorConvertedHours);
	    	paper.setPDECL_ISSN(ISSN);
	    	paper.setPDECL_PublicationConvertedHours(publicConvertedHours);
	    	paper.setPDECL_User_Code(userCode);
	    	paper.setPDECL_Volumn(volumn);
	    	paper.setPDECL_Year(paperYear);
	    	paper.setPDECL_JournalConferenceName(journalName);
	    	paper.setPDECL_IndexCode(journalIndex);
	    	paper.setPDECL_PublicationName(publicationName);
	    	paper.setPDECL_AuthorList(authors);
	    	paper.setPDECL_ReportingAcademicDate(paperReportingAcademicDate);
	    	paper.setPDECL_Month(paperMonth);
	    	if(paperSourceUploadFile.equals(""))
	    	{
	    		paper.setPDECL_SourceFile(paper.getPDECL_SourceFile());
	    	}else{
	    		
	    		String sOldSourceFile = paper.getPDECL_SourceFile();
		   		if((sOldSourceFile != null)){
		   			File oldFile = new File(sOldSourceFile);
			   		oldFile.delete();
		   		}
		   		paper.setPDECL_SourceFile(paperSourceUploadFile);
	    	}
	    	paperDAO.editAPaper(paper);
	    	
	    	if(projectMembers.length > 0){
	    		String PPSTF_PaperCode = paper.getPDECL_Code();
	    		String PPSTF_Code = "";
	    		List<PaperStaffs> oldPaperStaffsList = paperStaffsDAO.loadPaperListByPaperCode(PPSTF_PaperCode);
	    		if(oldPaperStaffsList != null && oldPaperStaffsList.size() > 0)
	    		{
	    			for (PaperStaffs paperStaff : oldPaperStaffsList) {
	    				paperStaffsDAO.removeAPaperStaff(paperStaff.getPPSTF_ID());
					}
	    		}
		    	PaperStaffs paperStaffs = new PaperStaffs();
	            for (String projectMember : projectMembers) {
	            	PPSTF_Code = projectMember+PPSTF_PaperCode;
	            	paperStaffs.setPPSTF_Code(PPSTF_Code);
	            	paperStaffs.setPPSTF_PaperCode(PPSTF_PaperCode);
	            	paperStaffs.setPPSTF_StaffCode(projectMember);
		    	    paperStaffsDAO.saveAPaperStaff(paperStaffs);
		    	}
	    	}
    	}
    }
    
    /**
     * Remove a paper
     * @param int
     * @return int
     */
    @Override
    public int removeAPaper(int paperId){
    	return paperDAO.removeAPaper(paperId);
    }
}
