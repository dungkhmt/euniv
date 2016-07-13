package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectresearchfields")
public class ProjectResearchField implements Serializable{
	@Id
    @GeneratedValue
    private int PRJRSHF_ID;
	private String PRJRSHF_Code;
	private String PRJRSHF_Name;
	public int getPRJRSHF_ID() {
		return PRJRSHF_ID;
	}
	public void setPRJRSHF_ID(int pRJRSHF_ID) {
		PRJRSHF_ID = pRJRSHF_ID;
	}
	public String getPRJRSHF_Code() {
		return PRJRSHF_Code;
	}
	public void setPRJRSHF_Code(String pRJRSHF_Code) {
		PRJRSHF_Code = pRJRSHF_Code;
	}
	public String getPRJRSHF_Name() {
		return PRJRSHF_Name;
	}
	public void setPRJRSHF_Name(String pRJRSHF_Name) {
		PRJRSHF_Name = pRJRSHF_Name;
	}
	
}
