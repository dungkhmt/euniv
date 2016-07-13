/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/*
 * Using a customization validator
 */

public class SummaryExcelValidation {

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