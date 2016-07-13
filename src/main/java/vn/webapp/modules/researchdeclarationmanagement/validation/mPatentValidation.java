package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mPatentValidation {
	/** Set rules for fields*/
    @NotEmpty
    private String patentName;
    
    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
    private String patentDateOfIssue;
    
    private String patentType;
    private String patentNumber;
    private String patentAuthors;
    private int patentId;
    private int patentAutConHours;
    private int patentConHours;
    
    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String patentReportingAcademicDate;
    
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getPatentDateOfIssue() {
		return patentDateOfIssue;
	}
	public void setPatentDateOfIssue(String patentDateOfIssue) {
		this.patentDateOfIssue = patentDateOfIssue;
	}
	public String getPatentType() {
		return patentType;
	}
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	public String getPatentNumber() {
		return patentNumber;
	}
	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}
	public String getPatentAuthors() {
		return patentAuthors;
	}
	public void setPatentAuthors(String patentAuthors) {
		this.patentAuthors = patentAuthors;
	}
	public int getPatentId() {
		return patentId;
	}
	public void setPatentId(int patentId) {
		this.patentId = patentId;
	}
	public int getPatentAutConHours() {
		return patentAutConHours;
	}
	public void setPatentAutConHours(int patentAutConHours) {
		this.patentAutConHours = patentAutConHours;
	}
	public int getPatentConHours() {
		return patentConHours;
	}
	public void setPatentConHours(int patentConHours) {
		this.patentConHours = patentConHours;
	}
	public String getPatentReportingAcademicDate() {
		return patentReportingAcademicDate;
	}
	public void setPatentReportingAcademicDate(String patentReportingAcademicDate) {
		this.patentReportingAcademicDate = patentReportingAcademicDate;
	}
}
