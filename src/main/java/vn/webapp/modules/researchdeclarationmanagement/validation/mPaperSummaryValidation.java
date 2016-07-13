package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;
import vn.webapp.validation.UploadFileNotEmpty;
import vn.webapp.validation.UploadFileRequired;

public class mPaperSummaryValidation {
	/** Set rules for fields*/
    private String paperCategory;
	
	private String paperReportingAcademicYear;
	
	private String paperFaculty;

	private String paperDepartment;
	
	private String threadStaff;

	public String getPaperCategory() {
		return paperCategory;
	}

	public void setPaperCategory(String paperCategory) {
		this.paperCategory = paperCategory;
	}

	public String getPaperReportingAcademicYear() {
		return paperReportingAcademicYear;
	}

	public void setPaperReportingAcademicYear(String paperReportingAcademicYear) {
		this.paperReportingAcademicYear = paperReportingAcademicYear;
	}

	public String getPaperFaculty() {
		return paperFaculty;
	}

	public void setPaperFaculty(String paperFaculty) {
		this.paperFaculty = paperFaculty;
	}

	public String getPaperDepartment() {
		return paperDepartment;
	}

	public void setPaperDepartment(String paperDepartment) {
		this.paperDepartment = paperDepartment;
	}

	public String getThreadStaff() {
		return threadStaff;
	}

	public void setThreadStaff(String threadStaff) {
		this.threadStaff = threadStaff;
	}

}
