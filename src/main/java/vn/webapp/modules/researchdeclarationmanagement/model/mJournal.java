package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbljournalindex")
public class mJournal implements Serializable{
	@Id
    @GeneratedValue
    private int JNAL_ID;
    private String JNAL_IndexCode;
    private String JNAL_Description;

	public int getJNAL_ID() {
		return JNAL_ID;
	}

	public void setJNAL_ID(int jNAL_ID) {
		JNAL_ID = jNAL_ID;
	}

	public String getJNAL_IndexCode() {
		return JNAL_IndexCode;
	}

	public void setJNAL_IndexCode(String jNAL_IndexCode) {
		JNAL_IndexCode = jNAL_IndexCode;
	}

	public String getJNAL_Description() {
		return JNAL_Description;
	}

	public void setJNAL_Description(String jNAL_Description) {
		JNAL_Description = jNAL_Description;
	}
}
