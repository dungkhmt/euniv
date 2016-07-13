package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblpatentdeclaration")
public class mPatents implements Serializable{
	@Id
    @GeneratedValue
    private int PAT_ID;
    private String PAT_User_Code;
    private String PAT_Authors;
    private String PAT_Type;
    private String PAT_Number;
    private String PAT_Name;
    private String PAT_DateOfIssue;
    private int PAT_ConvertedHours;
    private int PAT_AuthorConvertedHours;
    private String PAT_ReportingAcademicDate;
    private String PAT_ApproveUserCode;

	public int getPAT_ID() {
		return PAT_ID;
	}

	public void setPAT_ID(int pAT_ID) {
		PAT_ID = pAT_ID;
	}

	public String getPAT_User_Code() {
		return PAT_User_Code;
	}

	public void setPAT_User_Code(String pAT_User_Code) {
		PAT_User_Code = pAT_User_Code;
	}

	public String getPAT_Authors() {
		return PAT_Authors;
	}

	public void setPAT_Authors(String pAT_Authors) {
		PAT_Authors = pAT_Authors;
	}

	public String getPAT_Type() {
		return PAT_Type;
	}

	public void setPAT_Type(String pAT_Type) {
		PAT_Type = pAT_Type;
	}

	public String getPAT_Number() {
		return PAT_Number;
	}

	public void setPAT_Number(String pAT_Number) {
		PAT_Number = pAT_Number;
	}

	public String getPAT_Name() {
		return PAT_Name;
	}

	public void setPAT_Name(String pAT_Name) {
		PAT_Name = pAT_Name;
	}

	public String getPAT_DateOfIssue() {
		return PAT_DateOfIssue;
	}

	public void setPAT_DateOfIssue(String pAT_DateOfIssue) {
		PAT_DateOfIssue = pAT_DateOfIssue;
	}

	public int getPAT_ConvertedHours() {
		return PAT_ConvertedHours;
	}

	public void setPAT_ConvertedHours(int pAT_ConvertedHours) {
		PAT_ConvertedHours = pAT_ConvertedHours;
	}

	public int getPAT_AuthorConvertedHours() {
		return PAT_AuthorConvertedHours;
	}

	public void setPAT_AuthorConvertedHours(int pAT_AuthorConvertedHours) {
		PAT_AuthorConvertedHours = pAT_AuthorConvertedHours;
	}

	public String getPAT_ReportingAcademicDate() {
		return PAT_ReportingAcademicDate;
	}

	public void setPAT_ReportingAcademicDate(String pAT_ReportingAcademicDate) {
		PAT_ReportingAcademicDate = pAT_ReportingAcademicDate;
	}

	public String getPAT_ApproveUserCode() {
		return PAT_ApproveUserCode;
	}

	public void setPAT_ApproveUserCode(String pAT_ApproveUserCode) {
		PAT_ApproveUserCode = pAT_ApproveUserCode;
	}
}
