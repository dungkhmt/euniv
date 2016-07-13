package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblbooksdeclaration")
public class mBooks implements Serializable{
	@Id
	@GeneratedValue
	private int BOK_ID;
	private String 	BOK_Code;
	private String 	BOK_UserCode;
	private String 	BOK_BookName;
	private String 	BOK_Publisher;
	private int BOK_PublishedMonth;
	private int BOK_PublishedYear;
	private String 	BOK_Authors;
	private String BOK_ISBN;
	private String 	BOK_SourceFile;
	private String BOK_ReportingAcademicDate;
	private String BOK_ApproveStatus;
	private String BOK_Approve_UserCode;
	public int getBOK_ID() {
		return BOK_ID;
	}
	public void setBOK_ID(int bOK_ID) {
		BOK_ID = bOK_ID;
	}
	public String getBOK_Code() {
		return BOK_Code;
	}
	public void setBOK_Code(String bOK_Code) {
		BOK_Code = bOK_Code;
	}
	public String getBOK_UserCode() {
		return BOK_UserCode;
	}
	public void setBOK_UserCode(String bOK_UserCode) {
		BOK_UserCode = bOK_UserCode;
	}
	public String getBOK_BookName() {
		return BOK_BookName;
	}
	public void setBOK_BookName(String bOK_BookName) {
		BOK_BookName = bOK_BookName;
	}
	public String getBOK_Publisher() {
		return BOK_Publisher;
	}
	public void setBOK_Publisher(String bOK_Publisher) {
		BOK_Publisher = bOK_Publisher;
	}
	public int getBOK_PublishedMonth() {
		return BOK_PublishedMonth;
	}
	public void setBOK_PublishedMonth(int bOK_PublishedMonth) {
		BOK_PublishedMonth = bOK_PublishedMonth;
	}
	public int getBOK_PublishedYear() {
		return BOK_PublishedYear;
	}
	public void setBOK_PublishedYear(int bOK_PublishedYear) {
		BOK_PublishedYear = bOK_PublishedYear;
	}
	public String getBOK_Authors() {
		return BOK_Authors;
	}
	public void setBOK_Authors(String bOK_Authors) {
		BOK_Authors = bOK_Authors;
	}
	public String getBOK_ISBN() {
		return BOK_ISBN;
	}
	public void setBOK_ISBN(String bOK_ISBN) {
		BOK_ISBN = bOK_ISBN;
	}
	public String getBOK_SourceFile() {
		return BOK_SourceFile;
	}
	public void setBOK_SourceFile(String bOK_SourceFile) {
		BOK_SourceFile = bOK_SourceFile;
	}
	public String getBOK_ReportingAcademicDate() {
		return BOK_ReportingAcademicDate;
	}
	public void setBOK_ReportingAcademicDate(String bOK_ReportingAcademicDate) {
		BOK_ReportingAcademicDate = bOK_ReportingAcademicDate;
	}
	public String getBOK_ApproveStatus() {
		return BOK_ApproveStatus;
	}
	public void setBOK_ApproveStatus(String bOK_ApproveStatus) {
		BOK_ApproveStatus = bOK_ApproveStatus;
	}
	public String getBOK_Approve_UserCode() {
		return BOK_Approve_UserCode;
	}
	public void setBOK_Approve_UserCode(String bOK_Approve_UserCode) {
		BOK_Approve_UserCode = bOK_Approve_UserCode;
	}
	@Override
	public String toString() {
		return "mBooks [BOK_ID=" + BOK_ID + ", BOK_Code=" + BOK_Code + ", BOK_UserCode=" + BOK_UserCode
				+ ", BOK_BookName=" + BOK_BookName + ", BOK_Publisher=" + BOK_Publisher + ", BOK_PublishedMonth="
				+ BOK_PublishedMonth + ", BOK_PublishedYear=" + BOK_PublishedYear + ", BOK_Authors=" + BOK_Authors
				+ ", BOK_ISBN=" + BOK_ISBN + ", BOK_SourceFile=" + BOK_SourceFile + ", BOK_ReportingAcademicDate="
				+ BOK_ReportingAcademicDate + ", BOK_ApproveStatus=" + BOK_ApproveStatus + ", BOK_Approve_UserCode="
				+ BOK_Approve_UserCode + "]";
	}
	
	
}
