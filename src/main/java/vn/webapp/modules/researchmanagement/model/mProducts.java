package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblproducts")
public class mProducts implements Serializable{
	@Id
    @GeneratedValue
    private int PROD_ID;
    private String PROD_Code;
    private String PROD_ProjCode;
    private String PROD_Name;
    private String PROD_StartDate;
    private String PROD_EndDate;
    private String PROD_SourceFile;
    private String PROD_Status_Code;
    private String PROD_User_Code;
    private int PROD_Budget;

    @ManyToOne
    @JoinColumn(name="PROD_ProjCode", referencedColumnName = "PROJ_Code", insertable = false, updatable = false)
    public mThreads threads;
    
	public int getPROD_ID() {
		return PROD_ID;
	}

	public void setPROD_ID(int pROD_ID) {
		PROD_ID = pROD_ID;
	}

	public String getPROD_Code() {
		return PROD_Code;
	}

	public void setPROD_Code(String pROD_Code) {
		PROD_Code = pROD_Code;
	}

	public String getPROD_ProjCode() {
		return PROD_ProjCode;
	}

	public void setPROD_ProjCode(String pROD_ProjCode) {
		PROD_ProjCode = pROD_ProjCode;
	}

	public String getPROD_Name() {
		return PROD_Name;
	}

	public void setPROD_Name(String pROD_Name) {
		PROD_Name = pROD_Name;
	}

	public String getPROD_StartDate() {
		return PROD_StartDate;
	}

	public void setPROD_StartDate(String pROD_StartDate) {
		PROD_StartDate = pROD_StartDate;
	}

	public String getPROD_EndDate() {
		return PROD_EndDate;
	}

	public void setPROD_EndDate(String pROD_EndDate) {
		PROD_EndDate = pROD_EndDate;
	}

	public String getPROD_SourceFile() {
		return PROD_SourceFile;
	}

	public void setPROD_SourceFile(String pROD_SourceFile) {
		PROD_SourceFile = pROD_SourceFile;
	}

	public String getPROD_Status_Code() {
		return PROD_Status_Code;
	}

	public void setPROD_Status_Code(String pROD_Status_Code) {
		PROD_Status_Code = pROD_Status_Code;
	}

	public int getPROD_Budget() {
		return PROD_Budget;
	}

	public void setPROD_Budget(int pROD_Budget) {
		PROD_Budget = pROD_Budget;
	}

	public String getPROD_User_Code() {
		return PROD_User_Code;
	}

	public void setPROD_User_Code(String pROD_User_Code) {
		PROD_User_Code = pROD_User_Code;
	}

	public mThreads getThread() {
		return threads;
	}

	public void setThread(mThreads threads) {
		this.threads = threads;
	}
}
