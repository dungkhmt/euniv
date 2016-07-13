package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectstaffs")
public class mProjectStaffs implements Serializable{
	@Id
    @GeneratedValue
    private int PROJSTAFF_ID;
    private String PROJSTAFF_Code;
    private String PROJSTAFF_Staff_Code;
    private String PROJSTAFF_Proj_Code;
    private String PROJSTAFF_Role_Code;

	public int getPROJSTAFF_ID() {
		return PROJSTAFF_ID;
	}

	public void setPROJSTAFF_ID(int pROJSTAFF_ID) {
		PROJSTAFF_ID = pROJSTAFF_ID;
	}

	public String getPROJSTAFF_Code() {
		return PROJSTAFF_Code;
	}

	public void setPROJSTAFF_Code(String pROJSTAFF_Code) {
		PROJSTAFF_Code = pROJSTAFF_Code;
	}

	public String getPROJSTAFF_Staff_Code() {
		return PROJSTAFF_Staff_Code;
	}

	public void setPROJSTAFF_Staff_Code(String pROJSTAFF_Staff_Code) {
		PROJSTAFF_Staff_Code = pROJSTAFF_Staff_Code;
	}

	public String getPROJSTAFF_Proj_Code() {
		return PROJSTAFF_Proj_Code;
	}

	public void setPROJSTAFF_Proj_Code(String pROJSTAFF_Proj_Code) {
		PROJSTAFF_Proj_Code = pROJSTAFF_Proj_Code;
	}

	public String getPROJSTAFF_Role_Code() {
		return PROJSTAFF_Role_Code;
	}

	public void setPROJSTAFF_Role_Code(String pROJSTAFF_Role_Code) {
		PROJSTAFF_Role_Code = pROJSTAFF_Role_Code;
	}

}
