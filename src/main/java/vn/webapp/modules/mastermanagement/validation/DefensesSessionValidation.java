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

public class DefensesSessionValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String defenseSessionName;

    @NotEmpty
    private String defenseSessionCode;
    
    private int defensessionEnabled;
    
    private int defensessionId;
    
	public String getDefenseSessionName() {
		return defenseSessionName;
	}

	public void setDefenseSessionName(String defenseSessionName) {
		this.defenseSessionName = defenseSessionName;
	}

	public String getDefenseSessionCode() {
		return defenseSessionCode;
	}

	public void setDefenseSessionCode(String defenseSessionCode) {
		this.defenseSessionCode = defenseSessionCode;
	}

	public int getDefensessionEnabled() {
		return defensessionEnabled;
	}

	public void setDefensessionEnabled(int defensessionEnabled) {
		this.defensessionEnabled = defensessionEnabled;
	}

	public int getDefensessionId() {
		return defensessionId;
	}

	public void setDefensessionId(int defensessionId) {
		this.defensessionId = defensessionId;
	}
}