/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import org.hibernate.validator.constraints.NotEmpty;

/*
 * Using a customization validator
 */

public class UniversityValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String universityName;

    @NotEmpty
    private String universityCode;

    private int universityID;   
    
	public int getUniversityID() {
		return universityID;
	}

	public void setUniversityID(int universityID) {
		this.universityID = universityID;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}      
}