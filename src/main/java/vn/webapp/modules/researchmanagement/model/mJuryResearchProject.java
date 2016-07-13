package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbljuryresearchprojects")

public class mJuryResearchProject implements Serializable {
	@Id
    @GeneratedValue
    private int JURPRJ_ID;
	private String JURPRJ_Code;
	private String JURPRJ_Name;
	private String JURPRJ_PROJCall_Code;
	private String JURPRJ_UserCode;
	private int JURPRJ_Active;
	public int getJURPRJ_ID() {
		return JURPRJ_ID;
	}
	public void setJURPRJ_ID(int jURPRJ_ID) {
		JURPRJ_ID = jURPRJ_ID;
	}
	public String getJURPRJ_Code() {
		return JURPRJ_Code;
	}
	public void setJURPRJ_Code(String jURPRJ_Code) {
		JURPRJ_Code = jURPRJ_Code;
	}
	public String getJURPRJ_Name() {
		return JURPRJ_Name;
	}
	public void setJURPRJ_Name(String jURPRJ_Name) {
		JURPRJ_Name = jURPRJ_Name;
	}
	public String getJURPRJ_PROJCall_Code() {
		return JURPRJ_PROJCall_Code;
	}
	public void setJURPRJ_PROJCall_Code(String jURPRJ_PROJCall_Code) {
		JURPRJ_PROJCall_Code = jURPRJ_PROJCall_Code;
	}
	public String getJURPRJ_UserCode() {
		return JURPRJ_UserCode;
	}
	public void setJURPRJ_UserCode(String jURPRJ_UserCode) {
		JURPRJ_UserCode = jURPRJ_UserCode;
	}
	public int getJURPRJ_Active() {
		return JURPRJ_Active;
	}
	public void setJURPRJ_Active(int jURPRJ_Active) {
		JURPRJ_Active = jURPRJ_Active;
	}
	
}
