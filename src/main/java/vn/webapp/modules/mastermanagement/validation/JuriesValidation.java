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

/*
 * Using a customization validator
 */

public class JuriesValidation {

    private String[] defenseder01;
    /*private String[] defenseder02;
    private String[] president;
    private String[] secretary;
    private String[] commissioner;
    private String[] slot;
    private String[] room;
    private String[] no;
    private String[] sclass;*/
    
	public String[] getDefenseder01() {
		return defenseder01;
	}
	public void setDefenseder01(String[] defenseder01) {
		this.defenseder01 = defenseder01;
	}
	/*public String[] getDefenseder02() {
		return defenseder02;
	}
	public void setDefenseder02(String[] defenseder02) {
		this.defenseder02 = defenseder02;
	}
	public String[] getPresident() {
		return president;
	}
	public void setPresident(String[] president) {
		this.president = president;
	}
	public String[] getSecretary() {
		return secretary;
	}
	public void setSecretary(String[] secretary) {
		this.secretary = secretary;
	}
	public String[] getCommissioner() {
		return commissioner;
	}
	public void setCommissioner(String[] commissioner) {
		this.commissioner = commissioner;
	}
	public String[] getSlot() {
		return slot;
	}
	public void setSlot(String[] slot) {
		this.slot = slot;
	}
	public String[] getRoom() {
		return room;
	}
	public void setRoom(String[] room) {
		this.room = room;
	}
	public String[] getNo() {
		return no;
	}
	public void setNo(String[] no) {
		this.no = no;
	}
	public String[] getSclass() {
		return sclass;
	}
	public void setSclass(String[] sclass) {
		this.sclass = sclass;
	}*/
}