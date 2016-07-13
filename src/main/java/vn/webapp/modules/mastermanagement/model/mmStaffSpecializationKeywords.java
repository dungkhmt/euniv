/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tblstaffspecializationkeywords")
public class mmStaffSpecializationKeywords implements Serializable{
    
    @Id
    @GeneratedValue
    private int STFKW_ID;
    private String STFKW_StaffCode;
    private String STFKW_KeywordCode;
	public int getSTFKW_ID() {
		return STFKW_ID;
	}
	public void setSTFKW_ID(int sTFKW_ID) {
		STFKW_ID = sTFKW_ID;
	}
	public String getSTFKW_StaffCode() {
		return STFKW_StaffCode;
	}
	public void setSTFKW_StaffCode(String sTFKW_StaffCode) {
		STFKW_StaffCode = sTFKW_StaffCode;
	}
	public String getSTFKW_KeywordCode() {
		return STFKW_KeywordCode;
	}
	public void setSTFKW_KeywordCode(String sTFKW_KeywordCode) {
		STFKW_KeywordCode = sTFKW_KeywordCode;
	}
    
}
