package vn.webapp.modules.researchmanagement.validation;

public class mThreadApproveValidation {
	
	/** Set rules for fields */
	private String threadStatus;

	private int threadId;

	private String threadCode;
	
	
	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}

	public String getThreadStatus() {
		return threadStatus;
	}

	public void setThreadStatus(String threadStatus) {
		this.threadStatus = threadStatus;
	}

}
