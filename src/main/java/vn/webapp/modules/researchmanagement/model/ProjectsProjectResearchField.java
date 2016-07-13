package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectprojectresearchfields")
public class ProjectsProjectResearchField implements Serializable{
	@Id
    @GeneratedValue
    private int PRJPRJRSHF_ID;
	private String PRJPRJRSHF_PROJCode;
	private String PRJPRJRSHF_PRJRSHFCode;
	
	public int getPRJPRJRSHF_ID() {
		return PRJPRJRSHF_ID;
	}
	public void setPRJPRJRSHF_ID(int pRJPRJRSHF_ID) {
		PRJPRJRSHF_ID = pRJPRJRSHF_ID;
	}
	public String getPRJPRJRSHF_PROJCode() {
		return PRJPRJRSHF_PROJCode;
	}
	public void setPRJPRJRSHF_PROJCode(String pRJPRJRSHF_PROJCode) {
		PRJPRJRSHF_PROJCode = pRJPRJRSHF_PROJCode;
	}
	public String getPRJPRJRSHF_PRJRSHFCode() {
		return PRJPRJRSHF_PRJRSHFCode;
	}
	public void setPRJPRJRSHF_PRJRSHFCode(String pRJPRJRSHF_PRJRSHFCode) {
		PRJPRJRSHF_PRJRSHFCode = pRJPRJRSHF_PRJRSHFCode;
	}
}
