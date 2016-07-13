package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbljurysubmittedprojectroles")
public class mJuryRoleOfSubmittedProjects implements Serializable{
	@Id
    @GeneratedValue
    private int JUPRJROL_ID;
    private String JUPRJROL_CODE;
    private String JUPRJROL_NAME;
	public int getJUPRJROL_ID() {
		return JUPRJROL_ID;
	}
	public void setJUPRJROL_ID(int jUPRJROL_ID) {
		JUPRJROL_ID = jUPRJROL_ID;
	}
	public String getJUPRJROL_CODE() {
		return JUPRJROL_CODE;
	}
	public void setJUPRJROL_CODE(String jUPRJROL_CODE) {
		JUPRJROL_CODE = jUPRJROL_CODE;
	}
	public String getJUPRJROL_NAME() {
		return JUPRJROL_NAME;
	}
	public void setJUPRJROL_NAME(String jUPRJROL_NAME) {
		JUPRJROL_NAME = jUPRJROL_NAME;
	}
  }
