package vn.webapp.modules.usermanagement.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class mStaffValidation {
	/** Set rules for fields*/
    @NotEmpty
    private String staffName;
    
    @NotEmpty
    private String staffAcademicRank;    

    @NotEmpty
    @Email
    private String staffEmail;
    
    private String staffPhone;
    
    @NotEmpty
    private String staffDepartment;
    
    private int staffId;
    
    private String staffGender;
    
    @NotEmpty
    private String staffFaculty;
    
    private String staffDateOfBirth;

	public String getStaffName() {
		return staffName; 
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

	@Pattern(regexp="^[0-9 ]+$")
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

	public String getStaffGender() {
		return staffGender;
	}

	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}

	public String getStaffFaculty() {
		return staffFaculty;
	}

	public void setStaffFaculty(String staffFaculty) {
		this.staffFaculty = staffFaculty;
	}

	public String getStaffDateOfBirth() {
		return staffDateOfBirth;
	}

	public void setStaffDateOfBirth(String staffDateOfBirth) {
		this.staffDateOfBirth = staffDateOfBirth;
	}

	public String getStaffAcademicRank() {
		return staffAcademicRank;
	}

	public void setStaffAcademicRank(String staffAcademicRank) {
		this.staffAcademicRank = staffAcademicRank;
	}
	
}
