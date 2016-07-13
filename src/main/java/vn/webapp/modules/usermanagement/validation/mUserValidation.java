package vn.webapp.modules.usermanagement.validation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import vn.webapp.validation.EditPass;

public class mUserValidation {
	/** Set rules for fields*/
    @NotEmpty
    //@Pattern(regexp="^[0-9a-zA-Z_.@]+$")
    @Size(min = 6, max = 100)
    private String username;
    
    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;

    @NotEmpty
    @Email
    private String email;
    
    /** fields are not needed any rules */
    private int activated;
    private String role;
    private int userId;
    private int userRoleId;
    private int staffId;
    private String userCode;
    
    @EditPass(min = 6, max = 15)
    private String epassword;
    
    @NotEmpty
    private String staffFaculty;
    
    @NotEmpty
    private String staffDepartment;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
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

	public String getStaffFaculty() {
		return staffFaculty;
	}

	public void setStaffFaculty(String staffFaculty) {
		this.staffFaculty = staffFaculty;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
