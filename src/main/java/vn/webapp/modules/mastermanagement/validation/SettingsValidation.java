/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import vn.webapp.validation.FieldMatch;

/*
 * Using a customization validator
 */

@FieldMatch.List({
    @FieldMatch(first = "password", second = "rpassword", errorMessage = "{Password.fieldmatch}"),
})
public class SettingsValidation {

    /** Set rules for fields*/
    
    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;

    @Size(min = 6, max = 15)
    private String rpassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRpassword() {
		return rpassword;
	}

	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}

}