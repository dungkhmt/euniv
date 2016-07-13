package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mSummaryExcelValidation {
	/** Set rules for fields*/
	@NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String reportingAcademicDate;
	
	@NotEmpty
	private String department;

	public String getReportingAcademicDate() {
		return reportingAcademicDate;
	}

	public void setReportingAcademicDate(String reportingAcademicDate) {
		this.reportingAcademicDate = reportingAcademicDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
