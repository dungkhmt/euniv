/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.hibernate.validator.cfg.context.Cascadable;


/**
 * @author Admin
 *
 */
@Entity
@Table(name = "tblexternalstaffs")
public class mmExternalStaffInput implements Serializable{
    
    @Id
    @GeneratedValue
    private int EXTSTAF_ID;
    
    //private String Staff_University_Code;
    private String EXTSTAF_Code;
    private String EXTSTAF_Name;
    private String EXTSTAF_Email;    
    private String EXTSTAF_Phone;
    private String EXTSTAF_UniversityCode;
    private String EXTSTAF_AcademicRank;
    
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
    @JoinTable(name = "tblstaffspecializationkeywords", 
    		   joinColumns = { @JoinColumn(name = "STFKW_StaffCode", referencedColumnName = "EXTSTAF_Code") }, 
    		   inverseJoinColumns = { @JoinColumn(name = "STFKW_KeywordCode", referencedColumnName = "KW_Code") })  
    private Set<mmSpecializationKeyword> listSpecializationKeywords = new HashSet<mmSpecializationKeyword>();
        
    	
	public int getEXTSTAF_ID() {
		return EXTSTAF_ID;
	}

	public void setEXTSTAF_ID(int eXTSTAF_ID) {
		EXTSTAF_ID = eXTSTAF_ID;
	}

	public String getEXTSTAF_Code() {
		return EXTSTAF_Code;
	}

	public void setEXTSTAF_Code(String eXTSTAF_Code) {
		EXTSTAF_Code = eXTSTAF_Code;
	}

	public String getEXTSTAF_Name() {
		return EXTSTAF_Name;
	}

	public void setEXTSTAF_Name(String eXTSTAF_Name) {
		EXTSTAF_Name = eXTSTAF_Name;
	}

	public String getEXTSTAF_Email() {
		return EXTSTAF_Email;
	}

	public void setEXTSTAF_Email(String eXTSTAF_Email) {
		EXTSTAF_Email = eXTSTAF_Email;
	}

	public String getEXTSTAF_Phone() {
		return EXTSTAF_Phone;
	}

	public void setEXTSTAF_Phone(String eXTSTAF_Phone) {
		EXTSTAF_Phone = eXTSTAF_Phone;
	}

	public Set<mmSpecializationKeyword> getListSpecializationKeywords() {
		return listSpecializationKeywords;
	}

	public void setListSpecializationKeywords(
			Set<mmSpecializationKeyword> listSpecializationKeywords) {
		this.listSpecializationKeywords = listSpecializationKeywords;
	}

	public String getEXTSTAF_UniversityCode() {
		return EXTSTAF_UniversityCode;
	}

	public void setEXTSTAF_UniversityCode(String eXTSTAF_UniversityCode) {
		EXTSTAF_UniversityCode = eXTSTAF_UniversityCode;
	}

	public String getEXTSTAF_AcademicRank() {
		return EXTSTAF_AcademicRank;
	}

	public void setEXTSTAF_AcademicRank(String eXTSTAF_AcademicRank) {
		EXTSTAF_AcademicRank = eXTSTAF_AcademicRank;
	}	
}
