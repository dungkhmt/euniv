package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbljurysubmittedprojects")
public class mJuryOfAnnouncedProjectCall implements Serializable{
	@Id
    @GeneratedValue
    private int JUSUPRJ_ID;
	private String JUSUPRJ_STAFFCODE;
	private String JUSUPRJ_JURYRESEARCHPROJECTCODE;
    public String getJUSUPRJ_JURYRESEARCHPROJECTCODE() {
		return JUSUPRJ_JURYRESEARCHPROJECTCODE;
	}
	public void setJUSUPRJ_JURYRESEARCHPROJECTCODE(
			String jUSUPRJ_JURYRESEARCHPROJECTCODE) {
		JUSUPRJ_JURYRESEARCHPROJECTCODE = jUSUPRJ_JURYRESEARCHPROJECTCODE;
	}
	private String JUSUPRJ_PRJCALLCODE;
    private String JUPSURJ_ROLECODE;
	public int getJUSUPRJ_ID() {
		return JUSUPRJ_ID;
	}
	public void setJUSUPRJ_ID(int jUSUPRJ_ID) {
		JUSUPRJ_ID = jUSUPRJ_ID;
	}
	public String getJUSUPRJ_PRJCALLCODE() {
		return JUSUPRJ_PRJCALLCODE;
	}
	public void setJUSUPRJ_PRJCALLCODE(String jUSUPRJ_PRJCALLCODE) {
		JUSUPRJ_PRJCALLCODE = jUSUPRJ_PRJCALLCODE;
	}
	public String getJUPSURJ_ROLECODE() {
		return JUPSURJ_ROLECODE;
	}
	public void setJUPSURJ_ROLECODE(String jUPSURJ_ROLECODE) {
		JUPSURJ_ROLECODE = jUPSURJ_ROLECODE;
	}
	public String getJUSUPRJ_STAFFCODE() {
		return JUSUPRJ_STAFFCODE;
	}
	public void setJUSUPRJ_STAFFCODE(String jUSUPRJ_STAFFCODE) {
		JUSUPRJ_STAFFCODE = jUSUPRJ_STAFFCODE;
	}
	
	
  }
