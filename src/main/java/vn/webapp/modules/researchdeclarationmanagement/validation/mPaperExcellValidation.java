package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mPaperExcellValidation {
	/** Set rules for fields*/
	/*@Pattern(regexp="^[1-9]\\d*$")
    @NotEmpty
    private String paperYear;*/
	
	@NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String reportingAcademicDate;
	

	public String getReportingAcademicDate() {
		return reportingAcademicDate;
	}

	public void setReportingAcademicDate(String reportingAcademicDate) {
		this.reportingAcademicDate = reportingAcademicDate;
	}
}
