package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class mProjectExcellStatisticsValidation {
	
	 /** Set rules for fields*/
		
		
	private String projectCallCode;

	public String getProjectCallCode() {
		return projectCallCode;
	}

	public void setProjectCallCode(String projectCallCode) {
		this.projectCallCode = projectCallCode;
	}
		
}
