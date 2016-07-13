package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblbookstaffs")
public class BookStaffs implements Serializable{
	@Id
	@GeneratedValue
	private int BOKSTF_ID;
	private String BOKSTF_Code;
	private String BOKSTF_StaffCode;
	private String BOKSTF_BookCode;
	
	public int getBOKSTF_ID() {
		return BOKSTF_ID;
	}
	public void setBOKSTF_ID(int bOKSTF_ID) {
		BOKSTF_ID = bOKSTF_ID;
	}
	public String getBOKSTF_Code() {
		return BOKSTF_Code;
	}
	public void setBOKSTF_Code(String bOKSTF_Code) {
		BOKSTF_Code = bOKSTF_Code;
	}
	public String getBOKSTF_StaffCode() {
		return BOKSTF_StaffCode;
	}
	public void setBOKSTF_StaffCode(String bOKSTF_StaffCode) {
		BOKSTF_StaffCode = bOKSTF_StaffCode;
	}
	public String getBOKSTF_BookCode() {
		return BOKSTF_BookCode;
	}
	public void setBOKSTF_BookCode(String bOKSTF_BookCode) {
		BOKSTF_BookCode = bOKSTF_BookCode;
	}
	
}
