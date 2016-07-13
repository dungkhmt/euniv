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

public class StudentValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String studentName;

    @NotEmpty
    private String studentCode;
    
    public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	@NotEmpty
    @Email
    private String studentEmail;
    
    private String studentPhone;
    
    private String studentClasses;
    
    private int studentId;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(String studentClasses) {
		this.studentClasses = studentClasses;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}    
}