package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbleducation")
public class mEducations implements Serializable{
	@Id
	@GeneratedValue
	private int EDU_ID;
	private String 	EDU_Code;
	private String 	EDU_Level;
	private String 	EDU_Institution;
	private String 	EDU_Major;
	private String 	EDU_CompleteDate;
	private String EDU_UserCode;
	public int getEDU_ID() {
		return EDU_ID;
	}
	public void setEDU_ID(int eDU_ID) {
		EDU_ID = eDU_ID;
	}
	public String getEDU_Code() {
		return EDU_Code;
	}
	public void setEDU_Code(String eDU_Code) {
		EDU_Code = eDU_Code;
	}
	public String getEDU_Level() {
		return EDU_Level;
	}
	public void setEDU_Level(String eDU_Level) {
		EDU_Level = eDU_Level;
	}
	public String getEDU_Institution() {
		return EDU_Institution;
	}
	public void setEDU_Institution(String eDU_Institution) {
		EDU_Institution = eDU_Institution;
	}
	public String getEDU_Major() {
		return EDU_Major;
	}
	public void setEDU_Major(String eDU_Major) {
		EDU_Major = eDU_Major;
	}
	public String getEDU_CompleteDate() {
		return EDU_CompleteDate;
	}
	public void setEDU_CompleteDate(String eDU_CompleteDate) {
		EDU_CompleteDate = eDU_CompleteDate;
	}
	public String getEDU_UserCode() {
		return EDU_UserCode;
	}
	public void setEDU_UserCode(String eDU_UserCode) {
		EDU_UserCode = eDU_UserCode;
	}
	@Override
	public String toString() {
		return "mEducations [EDU_ID=" + EDU_ID + ", EDU_Code=" + EDU_Code
				+ ", EDU_Level=" + EDU_Level + ", EDU_Institution="
				+ EDU_Institution + ", EDU_Major=" + EDU_Major
				+ ", EDU_CompleteDate=" + EDU_CompleteDate + ", EDU_UserCode="
				+ EDU_UserCode + "]";
	}
	
}
