package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblappliedprojects")
public class mAppliedProjects implements Serializable{
	@Id
	@GeneratedValue
	private int AP_ID;
	private String 	AP_Code;
	private String 	AP_Name;
	private String 	AP_Scope;
	private String 	AP_Date;
	private String  AP_StaffCode;
	
	public int getAP_ID() {
		return AP_ID;
	}
	public void setAP_ID(int aP_ID) {
		AP_ID = aP_ID;
	}
	public String getAP_Code() {
		return AP_Code;
	}
	public void setAP_Code(String aP_Code) {
		AP_Code = aP_Code;
	}
	public String getAP_Name() {
		return AP_Name;
	}
	public void setAP_Name(String aP_Name) {
		AP_Name = aP_Name;
	}
	public String getAP_Scope() {
		return AP_Scope;
	}
	public void setAP_Scope(String aP_Scope) {
		AP_Scope = aP_Scope;
	}
	public String getAP_Date() {
		return AP_Date;
	}
	public void setAP_Date(String aP_Date) {
		AP_Date = aP_Date;
	}
	public String getAP_StaffCode() {
		return AP_StaffCode;
	}
	public void setAP_StaffCode(String aP_StaffCode) {
		AP_StaffCode = aP_StaffCode;
	}
	
	@Override
	public String toString() {
		return "mAppliedProjects [AP_ID=" + AP_ID + ", AP_Code=" + AP_Code
				+ ", AP_Name=" + AP_Name + ", AP_Scope=" + AP_Scope
				+ ", AP_Date=" + AP_Date + ", AP_StaffCode=" + AP_StaffCode
				+ "]";
	}
	
	
}
