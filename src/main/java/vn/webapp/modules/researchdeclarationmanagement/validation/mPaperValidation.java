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

public class mPaperValidation {
	/** Set rules for fields*/
    @NotEmpty
    private String paperCatCode;

    @NotEmpty
    private String paperPubName;
    
    @NotEmpty
    private String paperJConfName;
    
    //@NumberFormat(style = Style.NUMBER)
    private String paperVolumn;
    
    private int paperId;
    
    private String paperAuthorList;
    
    @NotNull
    @NumberFormat(style = Style.NUMBER)
    private String paperMonth;
    
    @DateTimeFormat(pattern="YYYY")
	@NotNull
    private Integer paperYear;
    
    private String paperISSN;
    
    /*@NotEmpty*/
    private String paperJIndexCode;
    
    @NotNull
    @NumberFormat(style = Style.NUMBER)
    @Min(0)
    private int paperPubConHours;
    
    /*@NotNull
    @NumberFormat(style = Style.NUMBER)
    @Min(1)*/
    private int paperAutConHours;
    
    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String patentReportingAcademicDate;
    
    /*@UploadFileRequired
    @UploadFileNotEmpty
    @UploadFileMaxSize(20971520)*/
    private MultipartFile paperFileUpload;

	public String getPaperCatCode() {
		return paperCatCode;
	}

	public void setPaperCatCode(String paperCatCode) {
		this.paperCatCode = paperCatCode;
	}

	public String getPaperPubName() {
		return paperPubName;
	}

	public void setPaperPubName(String paperPubName) {
		this.paperPubName = paperPubName;
	}

	public String getPaperJConfName() {
		return paperJConfName;
	}

	public void setPaperJConfName(String paperJConfName) {
		this.paperJConfName = paperJConfName;
	}

	public String getPaperVolumn() {
		return paperVolumn;
	}

	public void setPaperVolumn(String paperVolumn) {
		this.paperVolumn = paperVolumn;
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public Integer getPaperYear() {
		return paperYear;
	}

	public void setPaperYear(Integer paperYear) {
		this.paperYear = paperYear;
	}

	public String getPaperISSN() {
		return paperISSN;
	}

	public void setPaperISSN(String paperISSN) {
		this.paperISSN = paperISSN;
	}

	public String getPaperJIndexCode() {
		return paperJIndexCode;
	}

	public void setPaperJIndexCode(String paperJIndexCode) {
		this.paperJIndexCode = paperJIndexCode;
	}

	public int getPaperPubConHours() {
		return paperPubConHours;
	}

	public void setPaperPubConHours(int paperPubConHours) {
		this.paperPubConHours = paperPubConHours;
	}

	public int getPaperAutConHours() {
		return paperAutConHours;
	}

	public void setPaperAutConHours(int paperAutConHours) {
		this.paperAutConHours = paperAutConHours;
	}

	public String getPaperAuthorList() {
		return paperAuthorList;
	}

	public void setPaperAuthorList(String paperAuthorList) {
		this.paperAuthorList = paperAuthorList;
	}

	public String getPatentReportingAcademicDate() {
		return patentReportingAcademicDate;
	}

	public void setPatentReportingAcademicDate(String patentReportingAcademicDate) {
		this.patentReportingAcademicDate = patentReportingAcademicDate;
	}

	public MultipartFile getPaperFileUpload() {
		return paperFileUpload;
	}

	public void setPaperFileUpload(MultipartFile paperFileUpload) {
		this.paperFileUpload = paperFileUpload;
	}

	public String getPaperMonth() {
		return paperMonth;
	}

	public void setPaperMonth(String paperMonth) {
		this.paperMonth = paperMonth;
	}

}
