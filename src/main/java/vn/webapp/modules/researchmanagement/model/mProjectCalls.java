package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectcalls")
public class mProjectCalls implements Serializable{
	@Id
    @GeneratedValue
    private int PROJCALL_ID;
    private String PROJCALL_CODE;
    private String PROJCALL_PROJCATCODE;
    private String PROJCALL_NAME;
    private String PROJCALL_DATE;
    private String PROJCALL_STATUS;
    
	public int getPROJCALL_ID() {
		return PROJCALL_ID;
	}
	public void setPROJCALL_ID(int pROJCALL_ID) {
		PROJCALL_ID = pROJCALL_ID;
	}
	public String getPROJCALL_CODE() {
		return PROJCALL_CODE;
	}
	public void setPROJCALL_CODE(String pROJCALL_CODE) {
		PROJCALL_CODE = pROJCALL_CODE;
	}
	public String getPROJCALL_PROJCATCODE() {
		return PROJCALL_PROJCATCODE;
	}
	public void setPROJCALL_PROJCATCODE(String pROJCALL_PROJCATCODE) {
		PROJCALL_PROJCATCODE = pROJCALL_PROJCATCODE;
	}
	public String getPROJCALL_NAME() {
		return PROJCALL_NAME;
	}
	public void setPROJCALL_NAME(String pROJCALL_NAME) {
		PROJCALL_NAME = pROJCALL_NAME;
	}
	public String getPROJCALL_DATE() {
		return PROJCALL_DATE;
	}
	public void setPROJCALL_DATE(String pROJCALL_DATE) {
		PROJCALL_DATE = pROJCALL_DATE;
	}
	public String getPROJCALL_STATUS() {
		return PROJCALL_STATUS;
	}
	public void setPROJCALL_STATUS(String pROJCALL_STATUS) {
		PROJCALL_STATUS = pROJCALL_STATUS;
	}
}
