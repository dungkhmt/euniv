/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/*
 * Using a customization validator
 */

public class TopicValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String topicCatCode;

    @NotEmpty
    private String topicName;
    
    private int topicId;
    
    private int budget;
    
    @DateTimeFormat(pattern="YYYY")
	@NotNull
    private Integer topicYear;
    
    @NotNull
    @NumberFormat(style = Style.NUMBER)
    @Min(1)
    private Integer topicConHours;
    
    private int topicAutConHours;
    
    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String topicReportingAcademicDate;

	public String getTopicCatCode() {
		return topicCatCode;
	}

	public void setTopicCatCode(String topicCatCode) {
		this.topicCatCode = topicCatCode;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getTopicYear() {
		return topicYear;
	}

	public void setTopicYear(Integer topicYear) {
		this.topicYear = topicYear;
	}

	public Integer getTopicConHours() {
		return topicConHours;
	}

	public void setTopicConHours(Integer topicConHours) {
		this.topicConHours = topicConHours;
	}

	public Integer getTopicAutConHours() {
		return topicAutConHours;
	}

	public void setTopicAutConHours(Integer topicAutConHours) {
		this.topicAutConHours = topicAutConHours;
	}

	public String getTopicReportingAcademicDate() {
		return topicReportingAcademicDate;
	}

	public void setTopicReportingAcademicDate(String topicReportingAcademicDate) {
		this.topicReportingAcademicDate = topicReportingAcademicDate;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void setTopicAutConHours(int topicAutConHours) {
		this.topicAutConHours = topicAutConHours;
	}
	
	
}