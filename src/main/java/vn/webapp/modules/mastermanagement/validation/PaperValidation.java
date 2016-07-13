/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import org.springframework.web.multipart.MultipartFile;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/*
 * Using a customization validator
 */

public class PaperValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String paperCatCode;

    @NotEmpty
    private String paperPubName;
    
    @NotEmpty
    private String paperJConfName;
    
    @NumberFormat(style = Style.NUMBER)
    private int paperVolumn;
    
    private int paperId;
    
    private String paperAuthorList;
    
    @DateTimeFormat(pattern="YYYY")
	@NotNull
    private Integer paperYear;
    
    private String paperISSN;
    
    /*@NotEmpty*/
    private String paperJIndexCode;
    
    @NotNull
    @NumberFormat(style = Style.NUMBER)
    @Min(1)
    private int paperPubConHours;
    
    /*@NotNull
    @NumberFormat(style = Style.NUMBER)
    @Min(1)*/
    private int paperAutConHours;
    
    @NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String patentReportingAcademicDate;
    
    @UploadFileRequired
    @UploadFileNotEmpty
    @UploadFileMaxSize(20971520)
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

	public int getPaperVolumn() {
		return paperVolumn;
	}

	public void setPaperVolumn(int paperVolumn) {
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

}