package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblworkexperiences")
public class mWorkExperiences implements Serializable{
	@Id
	@GeneratedValue
	private int WE_ID;
	private String 	WE_CODE;
	private String 	WE_StaffCode;
	private String 	WE_Institution;
	private String 	WE_Position;
	private String 	WE_Domain;
	

	public int getWE_ID() {
		return WE_ID;
	}
	public void setWE_ID(int wE_ID) {
		WE_ID = wE_ID;
	}
	public String getWE_CODE() {
		return WE_CODE;
	}
	public void setWE_CODE(String wE_CODE) {
		WE_CODE = wE_CODE;
	}
	public String getWE_StaffCode() {
		return WE_StaffCode;
	}
	public void setWE_StaffCode(String wE_StaffCode) {
		WE_StaffCode = wE_StaffCode;
	}
	public String getWE_Institution() {
		return WE_Institution;
	}
	public void setWE_Institution(String wE_Institution) {
		WE_Institution = wE_Institution;
	}
	public String getWE_Position() {
		return WE_Position;
	}
	public void setWE_Position(String wE_Position) {
		WE_Position = wE_Position;
	}
	public String getWE_Domain() {
		return WE_Domain;
	}
	public void setWE_Domain(String wE_Domain) {
		WE_Domain = wE_Domain;
	}
	
	@Override
	public String toString() {
		return "mWorkExperiences [WE_ID=" + WE_ID + ", WE_CODE=" + WE_CODE
				+ ", WE_StaffCode=" + WE_StaffCode + ", WE_Institution="
				+ WE_Institution + ", WE_Position=" + WE_Position
				+ ", WE_Domain=" + WE_Domain + "]";
	}
		
}
