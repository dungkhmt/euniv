/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbldefensesessions")
public class mmDefenseSession implements Serializable{
    
    @Id
    @GeneratedValue
    private int DEFSESS_ID;
    private String DEFSESS_Code;
    private String DEFSESS_Name;
    private int DEFSESS_Enabled;
    
	public int getDEFSESS_ID() {
		return DEFSESS_ID;
	}
	public void setDEFSESS_ID(int dEFSESS_ID) {
		DEFSESS_ID = dEFSESS_ID;
	}
	public String getDEFSESS_Code() {
		return DEFSESS_Code;
	}
	public void setDEFSESS_Code(String dEFSESS_Code) {
		DEFSESS_Code = dEFSESS_Code;
	}
	public String getDEFSESS_Name() {
		return DEFSESS_Name;
	}
	public void setDEFSESS_Name(String dEFSESS_Name) {
		DEFSESS_Name = dEFSESS_Name;
	}
	public int getDEFSESS_Enabled() {
		return DEFSESS_Enabled;
	}
	public void setDEFSESS_Enabled(int dEFSESS_Enabled) {
		DEFSESS_Enabled = dEFSESS_Enabled;
	}
}
