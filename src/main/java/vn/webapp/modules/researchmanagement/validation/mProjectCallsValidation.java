package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;

public class mProjectCallsValidation {
	/** Set rules for fields */
	private int projectCallId;

	private String projectCallCode;
	
	@NotEmpty
	private String projectCallCatCode;

	@NotEmpty
	private String projectCallName;
	
	@NotEmpty
	private String projectCallStatus;

	@NotEmpty
	//@Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$")
	private String projectCallDate;

	public int getProjectCallId() {
		return projectCallId;
	}

	public void setProjectCallId(int projectCallId) {
		this.projectCallId = projectCallId;
	}

	public String getProjectCallCode() {
		return projectCallCode;
	}

	public void setProjectCallCode(String projectCallCode) {
		this.projectCallCode = projectCallCode;
	}

	public String getProjectCallCatCode() {
		return projectCallCatCode;
	}

	public void setProjectCallCatCode(String projectCallCatCode) {
		this.projectCallCatCode = projectCallCatCode;
	}

	public String getProjectCallName() {
		return projectCallName;
	}

	public void setProjectCallName(String projectCallName) {
		this.projectCallName = projectCallName;
	}

	public String getProjectCallDate() {
		return projectCallDate;
	}

	public void setProjectCallDate(String projectCallDate) {
		this.projectCallDate = projectCallDate;
	}

	public String getProjectCallStatus() {
		return projectCallStatus;
	}

	public void setProjectCallStatus(String projectCallStatus) {
		this.projectCallStatus = projectCallStatus;
	}
	
}
