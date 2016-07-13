package vn.webapp.modules.researchmanagement.validation;

import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import vn.webapp.validation.UploadFileMaxSize;

public class mThreadValidation {
	/** Set rules for fields */
	private int threadId;

	private String threadCode;
	
	@NotEmpty
	private String threadCatCode;

	@NotEmpty
	private String threadName;

	private int threadBudget;

	@NotEmpty
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/[0-9]{4}$")
	private String threadStartDate;

	@NotEmpty
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/[0-9]{4}$")
	private String threadEndDate;

	@NotEmpty
	@Pattern(regexp = "^[0-9]{4}-[0-9]{4}$")
	private String threadReportingAcademicDate;

	private String threadContent;

	private String threadMotivation;

	private String threadResult;
	
	private String staff;
	
	private List<String> staffsRole;
	
	private List<String> RoleList;

	@UploadFileMaxSize(20971520)
	private MultipartFile threadSourceFile;

	private String threadStatus;

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getThreadCatCode() {
		return threadCatCode;
	}

	public void setThreadCatCode(String threadCatCode) {
		this.threadCatCode = threadCatCode;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getThreadBudget() {
		return threadBudget;
	}

	public void setThreadBudget(int threadBudget) {
		this.threadBudget = threadBudget;
	}

	public String getThreadStartDate() {
		return threadStartDate;
	}

	public void setThreadStartDate(String threadStartDate) {
		this.threadStartDate = threadStartDate;
	}

	public String getThreadEndDate() {
		return threadEndDate;
	}

	public void setThreadEndDate(String threadEndDate) {
		this.threadEndDate = threadEndDate;
	}

	public String getThreadReportingAcademicDate() {
		return threadReportingAcademicDate;
	}

	public void setThreadReportingAcademicDate(
			String threadReportingAcademicDate) {
		this.threadReportingAcademicDate = threadReportingAcademicDate;
	}

	public String getThreadMotivation() {
		return threadMotivation;
	}

	public void setThreadMotivation(String threadMotivation) {
		this.threadMotivation = threadMotivation;
	}

	public String getThreadResult() {
		return threadResult;
	}

	public void setThreadResult(String threadResult) {
		this.threadResult = threadResult;
	}

	public MultipartFile getThreadSourceFile() {
		return threadSourceFile;
	}

	public void setThreadSourceFile(MultipartFile threadSourceFile) {
		this.threadSourceFile = threadSourceFile;
	}

	public String getThreadStatus() {
		return threadStatus;
	}

	public void setThreadStatus(String threadStatus) {
		this.threadStatus = threadStatus;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public List<String> getStaffsRole() {
		return staffsRole;
	}

	public void setStaffsRole(List<String> staffsRole) {
		this.staffsRole = staffsRole;
	}

	public List<String> getRoleList() {
		return RoleList;
	}

	public void setRoleList(List<String> roleList) {
		RoleList = roleList;
	}
}
