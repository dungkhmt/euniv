package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblexperiencescientificreview")
public class mExperienceScientificReview implements Serializable {

	@Id
	@GeneratedValue
	private int ESV_ID;
	private String ESV_Name;
	private int ESV_NumberTimes;
	private String ESV_StaffCode;
	private String ESV_CODE;
	public int getESV_ID() {
		return ESV_ID;
	}
	public void setESV_ID(int eSV_ID) {
		ESV_ID = eSV_ID;
	}
	public String getESV_Name() {
		return ESV_Name;
	}
	public void setESV_Name(String eSV_Name) {
		ESV_Name = eSV_Name;
	}
	public int getESV_NumberTimes() {
		return ESV_NumberTimes;
	}
	public void setESV_NumberTimes(int eSV_NumberTimes) {
		ESV_NumberTimes = eSV_NumberTimes;
	}
	public String getESV_StaffCode() {
		return ESV_StaffCode;
	}
	public void setESV_StaffCode(String eSV_StaffCode) {
		ESV_StaffCode = eSV_StaffCode;
	}
	public String getESV_CODE() {
		return ESV_CODE;
	}
	public void setESV_CODE(String eSV_CODE) {
		ESV_CODE = eSV_CODE;
	}
	@Override
	public String toString() {
		return "mExperienceScientificReview [ESV_ID=" + ESV_ID + ", ESV_Name="
				+ ESV_Name + ", ESV_NumberTimes=" + ESV_NumberTimes
				+ ", ESV_StaffCode=" + ESV_StaffCode + ", ESV_CODE=" + ESV_CODE
				+ "]";
	}
	
}
