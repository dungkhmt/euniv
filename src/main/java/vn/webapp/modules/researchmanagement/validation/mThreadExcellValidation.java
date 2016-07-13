package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mThreadExcellValidation {
	
	 /** Set rules for fields*/
		/*@Pattern(regexp="^[1-9]\\d*$")
	    @NotEmpty
	    private String paperYear;*/
		
		@NotEmpty
	    private String threadYear;
		
		private String threadStatus;
		
		private String threadCatCode;

		private String threadFaculty;
		
		private String threadDepartment;
		
		private String threadStaff;

		
		public String getThreadYear() {
			return threadYear;
		}

		public void setThreadYear(String threadYear) {
			this.threadYear = threadYear;
		}

		public String getThreadStatus() {
			return threadStatus;
		}

		public void setThreadStatus(String threadStatus) {
			this.threadStatus = threadStatus;
		}

		public String getThreadCatCode() {
			return threadCatCode;
		}

		public void setThreadCatCode(String threadCatCode) {
			this.threadCatCode = threadCatCode;
		}

		public String getThreadFaculty() {
			return threadFaculty;
		}

		public void setThreadFaculty(String threadFaculty) {
			this.threadFaculty = threadFaculty;
		}

		public String getThreadDepartment() {
			return threadDepartment;
		}

		public void setThreadDepartment(String threadDepartment) {
			this.threadDepartment = threadDepartment;
		}

		public String getThreadStaff() {
			return threadStaff;
		}

		public void setThreadStaff(String threadStaff) {
			this.threadStaff = threadStaff;
		}
}
