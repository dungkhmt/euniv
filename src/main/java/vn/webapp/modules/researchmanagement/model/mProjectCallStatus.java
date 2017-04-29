package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectcallstatus")

public class mProjectCallStatus implements Serializable{
	@Id
    @GeneratedValue
    private int 	PROJCALLSTAT_ID;
	private String 	PROJCALLSTAT_Code;
	private String 	PROJCALLSTAT_Name;
	public int getPROJCALLSTAT_ID() {
		return PROJCALLSTAT_ID;
	}
	public void setPROJCALLSTAT_ID(int pROJCALLSTAT_ID) {
		PROJCALLSTAT_ID = pROJCALLSTAT_ID;
	}
	public String getPROJCALLSTAT_Code() {
		return PROJCALLSTAT_Code;
	}
	public void setPROJCALLSTAT_Code(String pROJCALLSTAT_Code) {
		PROJCALLSTAT_Code = pROJCALLSTAT_Code;
	}
	public String getPROJCALLSTAT_Name() {
		return PROJCALLSTAT_Name;
	}
	public void setPROJCALLSTAT_Name(String pROJCALLSTAT_Name) {
		PROJCALLSTAT_Name = pROJCALLSTAT_Name;
	}
	
}
