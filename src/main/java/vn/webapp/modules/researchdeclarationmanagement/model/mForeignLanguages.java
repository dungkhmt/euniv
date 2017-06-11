package vn.webapp.modules.researchdeclarationmanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblforeignlanguages")
public class mForeignLanguages implements Serializable{
	@Id
	@GeneratedValue
	private int LG_ID;
	private String LG_Code;
	private String LG_Name;
	private String LG_Listen;
	private String LG_Speak;
	private String LG_Reading;
	private String LG_Writing;
	private String LG_UserCode;
	public int getLG_ID() {
		return LG_ID;
	}
	public void setLG_ID(int lG_ID) {
		LG_ID = lG_ID;
	}
	public String getLG_Code() {
		return LG_Code;
	}
	public void setLG_Code(String lG_Code) {
		LG_Code = lG_Code;
	}
	public String getLG_Name() {
		return LG_Name;
	}
	public void setLG_Name(String lG_Name) {
		LG_Name = lG_Name;
	}
	public String getLG_Listen() {
		return LG_Listen;
	}
	public void setLG_Listen(String lG_Listen) {
		LG_Listen = lG_Listen;
	}
	public String getLG_Speak() {
		return 	LG_Speak;
	}
	public void setLG_Speak(String lG_Speak) {
		LG_Speak = 	lG_Speak;
	}
	public String getLG_Reading() {
		return LG_Reading;
	}
	public void setLG_Reading(String lG_Reading) {
		LG_Reading = lG_Reading;
	}
	public String getLG_Writing() {
		return LG_Writing;
	}
	public void setLG_Writing(String lG_Writing) {
		LG_Writing = lG_Writing;
	}
	public String getLG_UserCode() {
		return LG_UserCode;
	}
	public void setLG_UserCode(String lG_UserCode) {
		LG_UserCode = lG_UserCode;
	}
	@Override
	public String toString() {
		return "mForeignLanguages [LG_ID=" + LG_ID + ", LG_Code=" + LG_Code
				+ ", LG_Name=" + LG_Name + ", LG_Listen="+ LG_Listen + ", "
				+ "LG_Speak=" + LG_Speak + ", LG_Reading=" + LG_Reading + ","
				+ ", LG_Writing=" + LG_Writing + ","+ " LG_UserCode="+ LG_UserCode + "]";
	}
	
}
