/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/*
 * Using a customization validator
 */

public class UserEditValidation {

    /** Set rules for fields*/
    @NotEmpty
    @Size(min = 6, max = 15)
    private String username;

    @NotEmpty
    @Email
    private String email;
    
    /** fields are not needed any rules */
    private int activated;
    private String role;
    private int userId;
    private int userRoleId;
    
    // Custom validation
    
    @EditPass(min = 6, max = 15)
    private String epassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
}