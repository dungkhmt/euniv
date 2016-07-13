package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblpapercategoryhourbudget")
public class mPapersCategoryHourBudget implements Serializable{
	@Id
    @GeneratedValue
    private int PCAHOBUD_ID;
    private String PCAHOBUD_Code;
    private String PCAHOBUD_PaperCategoryCode;
    private int PCAHOBUD_Hour;
    private int PCAHOBUD_Budget;
    private String PCAHOBUD_AcademicYearCode;
    
    @ManyToOne
    @JoinColumn(name="PCAHOBUD_PaperCategoryCode", referencedColumnName = "PCAT_Code", insertable = false, updatable = false)
    public mPaperCategory paperCate;

	public int getPCAHOBUD_ID() {
		return PCAHOBUD_ID;
	}

	public void setPCAHOBUD_ID(int pCAHOBUD_ID) {
		PCAHOBUD_ID = pCAHOBUD_ID;
	}

	public String getPCAHOBUD_Code() {
		return PCAHOBUD_Code;
	}

	public void setPCAHOBUD_Code(String pCAHOBUD_Code) {
		PCAHOBUD_Code = pCAHOBUD_Code;
	}

	public String getPCAHOBUD_PaperCategoryCode() {
		return PCAHOBUD_PaperCategoryCode;
	}

	public void setPCAHOBUD_PaperCategoryCode(String pCAHOBUD_PaperCategoryCode) {
		PCAHOBUD_PaperCategoryCode = pCAHOBUD_PaperCategoryCode;
	}

	public int getPCAHOBUD_Hour() {
		return PCAHOBUD_Hour;
	}

	public void setPCAHOBUD_Hour(int pCAHOBUD_Hour) {
		PCAHOBUD_Hour = pCAHOBUD_Hour;
	}

	public int getPCAHOBUD_Budget() {
		return PCAHOBUD_Budget;
	}

	public void setPCAHOBUD_Budget(int pCAHOBUD_Budget) {
		PCAHOBUD_Budget = pCAHOBUD_Budget;
	}

	public String getPCAHOBUD_AcademicYearCode() {
		return PCAHOBUD_AcademicYearCode;
	}

	public void setPCAHOBUD_AcademicYearCode(String pCAHOBUD_AcademicYearCode) {
		PCAHOBUD_AcademicYearCode = pCAHOBUD_AcademicYearCode;
	}

	public mPaperCategory getPaperCategory() {
		return paperCate;
	}

	public void setPaperCategory(mPaperCategory paperCate) {
		this.paperCate = paperCate;
	}
	
}
