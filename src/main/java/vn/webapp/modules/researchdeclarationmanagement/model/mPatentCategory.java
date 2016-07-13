package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblpatentcategory")
public class mPatentCategory implements Serializable{
	@Id
    @GeneratedValue
    private int PATCAT_ID;
    private String PATCAT_Code;
    private String PATCAT_Name;
	public int getPATCAT_ID() {
		return PATCAT_ID;
	}
	public void setPATCAT_ID(int pATCAT_ID) {
		PATCAT_ID = pATCAT_ID;
	}
	public String getPATCAT_Code() {
		return PATCAT_Code;
	}
	public void setPATCAT_Code(String pATCAT_Code) {
		PATCAT_Code = pATCAT_Code;
	}
	public String getPATCAT_Name() {
		return PATCAT_Name;
	}
	public void setPATCAT_Name(String pATCAT_Name) {
		PATCAT_Name = pATCAT_Name;
	}
}
