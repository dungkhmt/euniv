package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblawards")
public class mAwards implements Serializable{
	@Id
	@GeneratedValue
	private int AW_ID;
	private String 	AW_Code;
	private String 	AW_Name;
	private String 	AW_Date;
	private String  AW_StaffCode;
	
	public int getAW_ID() {
		return AW_ID;
	}
	public void setAW_ID(int aW_ID) {
		AW_ID = aW_ID;
	}
	public String getAW_Code() {
		return AW_Code;
	}
	public void setAW_Code(String aW_Code) {
		AW_Code = aW_Code;
	}
	public String getAW_Name() {
		return AW_Name;
	}
	public void setAW_Name(String aW_Name) {
		AW_Name = aW_Name;
	}
	public String getAW_Date() {
		return AW_Date;
	}
	public void setAW_Date(String aW_Date) {
		AW_Date = aW_Date;
	}
	public String getAW_StaffCode() {
		return AW_StaffCode;
	}
	public void setAW_StaffCode(String aW_StaffCode) {
		AW_StaffCode = aW_StaffCode;
	}
	
	@Override
	public String toString() {
		return "mAwards [AW_ID=" + AW_ID + ", AW_Code=" + AW_Code
				+ ", AW_Name=" + AW_Name + ", AW_Date=" + AW_Date
				+ ", AW_StaffCode=" + AW_StaffCode + "]";
	}

	
	
}
