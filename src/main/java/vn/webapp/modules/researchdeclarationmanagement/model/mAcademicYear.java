package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblacademicyear")
public class mAcademicYear implements Serializable{
	
	@Id
    @GeneratedValue
    private int ACAYEAR_ID;
    private String ACAYEAR_Code;
    private String ACAYEAR_FromDate;
    private String ACAYEAR_ToDate;

	public int getACAYEAR_ID() {
		return ACAYEAR_ID;
	}

	public void setACAYEAR_ID(int aCAYEAR_ID) {
		ACAYEAR_ID = aCAYEAR_ID;
	}

	public String getACAYEAR_Code() {
		return ACAYEAR_Code;
	}

	public void setACAYEAR_Code(String aCAYEAR_Code) {
		ACAYEAR_Code = aCAYEAR_Code;
	}

	public String getACAYEAR_FromDate() {
		return ACAYEAR_FromDate;
	}

	public void setACAYEAR_FromDate(String aCAYEAR_FromDate) {
		ACAYEAR_FromDate = aCAYEAR_FromDate;
	}

	public String getACAYEAR_ToDate() {
		return ACAYEAR_ToDate;
	}

	public void setACAYEAR_ToDate(String aCAYEAR_ToDate) {
		ACAYEAR_ToDate = aCAYEAR_ToDate;
	}
}
