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

public class StaffValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String staffName;

    @NotEmpty
    @Email
    private String staffEmail;
    
    private String staffPhone;
    
    private String staffDepartment;
    
    private String staffUniversity;
    
    private String staffAcademicRank;
        
    private int staffId;
    
    private ArrayList<String> staffKeywords;

	public String getStaffAcademicRank() {
		return staffAcademicRank;
	}

	public void setStaffAcademicRank(String staffAcademicRank) {
		this.staffAcademicRank = staffAcademicRank;
	}

	public String getStaffName() {
		return staffName;
	}

	public ArrayList<String> getStaffKeywords() {
		return staffKeywords;
	}

	public void setStaffKeywords(ArrayList<String> keywords) {
		this.staffKeywords = keywords;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	public String getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffUniversity() {
		return staffUniversity;
	}

	public void setStaffUniversity(String staffUniversity) {
		this.staffUniversity = staffUniversity;
	}    
}