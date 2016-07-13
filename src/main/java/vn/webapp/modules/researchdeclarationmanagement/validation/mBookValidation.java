package vn.webapp.modules.researchdeclarationmanagement.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class mBookValidation {
	
	@NotBlank
	private String bookName;
	
	@NotBlank
	private String bookPublisher;
	
	@NotBlank
	private String bookMonth;
	@DateTimeFormat(pattern="YYYY")
	@NotNull
	private Integer bookYear;
	
	private int bookId;

	private String bookAuthorList;
	
	private String bookISBN;
	
	private MultipartFile bookFileUpload;
	@NotEmpty
    @Pattern(regexp="^[0-9]{4}-[0-9]{4}$")
    private String bookReportingAcademicDate;
	
	public MultipartFile getBookFileUpload() {
		return bookFileUpload;
	}

	public void setBookFileUpload(MultipartFile bookFileUpload) {
		this.bookFileUpload = bookFileUpload;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookMonth() {
		return bookMonth;
	}

	public void setBookMonth(String bookMonth) {
		this.bookMonth = bookMonth;
	}

	public Integer getBookYear() {
		return bookYear;
	}

	public void setBookYear(Integer bookYear) {
		this.bookYear = bookYear;
	}

	public String getBookAuthorList() {
		return bookAuthorList;
	}

	public void setBookAuthorList(String bookAuthorList) {
		this.bookAuthorList = bookAuthorList;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookReportingAcademicDate() {
		return bookReportingAcademicDate;
	}

	public void setBookReportingAcademicDate(String bookReportingAcademicDate) {
		this.bookReportingAcademicDate = bookReportingAcademicDate;
	}
}
