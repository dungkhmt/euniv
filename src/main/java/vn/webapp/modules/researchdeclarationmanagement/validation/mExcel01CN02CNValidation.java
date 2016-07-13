package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mExcel01CN02CNValidation {
	@NotEmpty
	private String staffCode;
	
	@NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String reportingAcademicDate;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getReportingAcademicDate() {
		return reportingAcademicDate;
	}

	public void setReportingAcademicDate(String reportingAcademicDate) {
		this.reportingAcademicDate = reportingAcademicDate;
	}
}
