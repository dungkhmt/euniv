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

public class PaperExcellValidation {

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