package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.webapp.modules.usermanagement.model.mStaff;

@Entity
@Table(name = "tblpapersdeclaration")
public class mPapers implements Serializable{
	@Id
    @GeneratedValue
    private int PDECL_ID;
	private String PDECL_Code;
    private String PDECL_PaperCategory_Code;
    private String PDECL_User_Code;
    private String PDECL_PublicationName;
    private String PDECL_JournalConferenceName;
    private String PDECL_Volumn;
    private String PDECL_Month;
    private int PDECL_Year;
    private String PDECL_ISSN;
    private String PDECL_IndexCode;
    private int PDECL_PublicationConvertedHours;
    private int PDECL_AuthorConvertedHours;
    private String PDECL_AuthorList;
    private String PDECL_ReportingAcademicDate;
    private String PDECL_SourceFile;
    private String PDECL_ApproveStatus;
    private String PDECL_Approve_UserCode;

	@ManyToOne
    @JoinColumn(name="PDECL_PaperCategory_Code", referencedColumnName = "PCAT_Code", insertable = false, updatable = false)
    public mPaperCategory paperCategory;


	@ManyToOne
	@JoinColumn(name="PDECL_User_Code", referencedColumnName = "Staff_Code", insertable=false, updatable=false)
	public mStaff staff;
	
	
	
    public mStaff getStaff() {
		return staff;
	}

	public void setStaff(mStaff staff) {
		this.staff = staff;
	}

	public String getPDECL_ApproveStatus() {
		return PDECL_ApproveStatus;
	}

	public void setPDECL_ApproveStatus(String pDECL_ApproveStatus) {
		PDECL_ApproveStatus = pDECL_ApproveStatus;
	}

	public String getPDECL_Approve_UserCode() {
		return PDECL_Approve_UserCode;
	}

	public void setPDECL_Approve_UserCode(String pDECL_Approve_UserCode) {
		PDECL_Approve_UserCode = pDECL_Approve_UserCode;
	}

	
	

	public String getPDECL_Code() {
		return PDECL_Code;
	}

	public void setPDECL_Code(String pDECL_Code) {
		PDECL_Code = pDECL_Code;
	}

	public int getPDECL_ID() {
		return PDECL_ID;
	}

	public void setPDECL_ID(int pDECL_ID) {
		PDECL_ID = pDECL_ID;
	}

	public String getPDECL_PaperCategory_Code() {
		return PDECL_PaperCategory_Code;
	}

	public void setPDECL_PaperCategory_Code(String pDECL_PaperCategory_Code) {
		PDECL_PaperCategory_Code = pDECL_PaperCategory_Code;
	}

	
	public String getPDECL_User_Code() {
		return PDECL_User_Code;
	}

	public void setPDECL_User_Code(String pDECL_User_Code) {
		PDECL_User_Code = pDECL_User_Code;
	}

	public String getPDECL_PublicationName() {
		return PDECL_PublicationName;
	}

	public void setPDECL_PublicationName(String pDECL_PublicationName) {
		PDECL_PublicationName = pDECL_PublicationName;
	}

	public String getPDECL_JournalConferenceName() {
		return PDECL_JournalConferenceName;
	}

	public void setPDECL_JournalConferenceName(String pDECL_JournalConferenceName) {
		PDECL_JournalConferenceName = pDECL_JournalConferenceName;
	}

	public String getPDECL_Volumn() {
		return PDECL_Volumn;
	}

	public void setPDECL_Volumn(String pDECL_Volumn) {
		PDECL_Volumn = pDECL_Volumn;
	}

	public int getPDECL_Year() {
		return PDECL_Year;
	}

	public void setPDECL_Year(int pDECL_Year) {
		PDECL_Year = pDECL_Year;
	}

	public String getPDECL_ISSN() {
		return PDECL_ISSN;
	}

	public void setPDECL_ISSN(String pDECL_ISSN) {
		PDECL_ISSN = pDECL_ISSN;
	}

	public String getPDECL_IndexCode() {
		return PDECL_IndexCode;
	}

	public void setPDECL_IndexCode(String pDECL_IndexCode) {
		PDECL_IndexCode = pDECL_IndexCode;
	}

	public int getPDECL_PublicationConvertedHours() {
		return PDECL_PublicationConvertedHours;
	}

	public void setPDECL_PublicationConvertedHours(
			int pDECL_PublicationConvertedHours) {
		PDECL_PublicationConvertedHours = pDECL_PublicationConvertedHours;
	}

	public int getPDECL_AuthorConvertedHours() {
		return PDECL_AuthorConvertedHours;
	}

	public void setPDECL_AuthorConvertedHours(int pDECL_AuthorConvertedHours) {
		PDECL_AuthorConvertedHours = pDECL_AuthorConvertedHours;
	}

	public String getPDECL_AuthorList() {
		return PDECL_AuthorList;
	}

	public void setPDECL_AuthorList(String pDECL_AuthorList) {
		PDECL_AuthorList = pDECL_AuthorList;
	}

	public mPaperCategory getPaperCategory() {
		return paperCategory;
	}

	public void setPaperCategory(mPaperCategory paperCategory) {
		this.paperCategory = paperCategory;
	}

	public String getPDECL_ReportingAcademicDate() {
		return PDECL_ReportingAcademicDate;
	}

	public void setPDECL_ReportingAcademicDate(String pDECL_ReportingAcademicDate) {
		PDECL_ReportingAcademicDate = pDECL_ReportingAcademicDate;
	}

	public String getPDECL_SourceFile() {
		return PDECL_SourceFile;
	}

	public void setPDECL_SourceFile(String pDECL_SourceFile) {
		PDECL_SourceFile = pDECL_SourceFile;
	}

	public String getPDECL_Month() {
		return PDECL_Month;
	}

	public void setPDECL_Month(String pDECL_Month) {
		PDECL_Month = pDECL_Month;
	}
}
