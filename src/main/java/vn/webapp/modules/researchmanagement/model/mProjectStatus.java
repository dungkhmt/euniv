package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectstatuses")
public class mProjectStatus implements Serializable{
	
	@Id
    @GeneratedValue
    private int PROJSTAT_ID;
    private String PROJSTAT_Code;
    private String PROJSTAT_Description;
    
	public int getPROJSTAT_ID() {
		return PROJSTAT_ID;
	}

	public void setPROJSTAT_ID(int pROJSTAT_ID) {
		PROJSTAT_ID = pROJSTAT_ID;
	}

	public String getPROJSTAT_Code() {
		return PROJSTAT_Code;
	}

	public void setPROJSTAT_Code(String pROJSTAT_Code) {
		PROJSTAT_Code = pROJSTAT_Code;
	}

	public String getPROJSTAT_Description() {
		return PROJSTAT_Description;
	}

	public void setPROJSTAT_Description(String pROJSTAT_Description) {
		PROJSTAT_Description = pROJSTAT_Description;
	}
}
