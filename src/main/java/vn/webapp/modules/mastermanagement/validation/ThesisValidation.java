/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/*
 * Using a customization validator
 */

public class ThesisValidation {

    /** Set rules for fields*/
	@NotEmpty
	private String studentCode;
	
    @NotEmpty
    private String thesisName;

    public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	@NotEmpty
    private String supervisor;
    
    @NotEmpty
    private ArrayList<String> staffKeywords;

	public String getThesisName() {
		return thesisName;
	}

	public void setThesisName(String thesisName) {
		this.thesisName = thesisName;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public ArrayList<String> getStaffKeywords() {
		return staffKeywords;
	}

	public void setStaffKeywords(ArrayList<String> staffKeywords) {
		this.staffKeywords = staffKeywords;
	}

	   
}