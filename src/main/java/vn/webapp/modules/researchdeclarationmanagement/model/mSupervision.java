package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblsupervision")
public class mSupervision implements Serializable{
	@Id
	@GeneratedValue
	private int SUP_ID;
	private String 	SUP_CODE;
	private String 	SUP_StaffCode;
	private String 	SUP_StudentName;
	private String 	SUP_Cosupervision;
	private String 	SUP_Institution;
	private String SUP_ThesisTitle;
	private String SUP_SpecializationCode;
	private String SUP_TraingPeriod;
	private String SUP_DefensedDate;
	public int getSUP_ID() {
		return SUP_ID;
	}
	public void setSUP_ID(int sUP_ID) {
		SUP_ID = sUP_ID;
	}
	public String getSUP_CODE() {
		return SUP_CODE;
	}
	public void setSUP_CODE(String sUP_CODE) {
		SUP_CODE = sUP_CODE;
	}
	public String getSUP_StaffCode() {
		return SUP_StaffCode;
	}
	public void setSUP_StaffCode(String sUP_StaffCode) {
		SUP_StaffCode = sUP_StaffCode;
	}
	public String getSUP_StudentName() {
		return SUP_StudentName;
	}
	public void setSUP_StudentName(String sUP_StudentName) {
		SUP_StudentName = sUP_StudentName;
	}
	public String getSUP_Cosupervision() {
		return SUP_Cosupervision;
	}
	public void setSUP_Cosupervision(String sUP_Cosupervision) {
		SUP_Cosupervision = sUP_Cosupervision;
	}
	public String getSUP_Institution() {
		return SUP_Institution;
	}
	public void setSUP_Institution(String sUP_Institution) {
		SUP_Institution = sUP_Institution;
	}
	public String getSUP_ThesisTitle() {
		return SUP_ThesisTitle;
	}
	public void setSUP_ThesisTitle(String sUP_ThesisTitle) {
		SUP_ThesisTitle = sUP_ThesisTitle;
	}
	public String getSUP_SpecializationCode() {
		return SUP_SpecializationCode;
	}
	public void setSUP_SpecializationCode(String sUP_SpecializationCode) {
		SUP_SpecializationCode = sUP_SpecializationCode;
	}
	public String getSUP_TraingPeriod() {
		return SUP_TraingPeriod;
	}
	public void setSUP_TraingPeriod(String sUP_TraingPeriod) {
		SUP_TraingPeriod = sUP_TraingPeriod;
	}
	public String getSUP_DefensedDate() {
		return SUP_DefensedDate;
	}
	public void setSUP_DefensedDate(String sUP_DefensedDate) {
		SUP_DefensedDate = sUP_DefensedDate;
	}
	
}
