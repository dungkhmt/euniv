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

import org.hibernate.Hibernate;
import org.hibernate.validator.cfg.context.Cascadable;


/**
 * @author Admin
 *
 */
@Entity
@Table(name = "tblstaffs")
public class mmStaffInput implements Serializable{
    
    @Id
    @GeneratedValue
    private int Staff_ID;
    
    //private String Staff_University_Code;
    private String Staff_Code;
    private String Staff_Name;
    private String Staff_AsciiName;
    private String Staff_Email;
    private String Staff_Department_Code;
    private String Staff_Phone;
    private String Staff_User_Code;
    private String Staff_Faculty_Code;
    private String Staff_Category_Code;
    private String Staff_AcademicRank;

	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
    @JoinTable(name = "tblstaffspecializationkeywords", 
    		   joinColumns = { @JoinColumn(name = "STFKW_StaffCode", referencedColumnName = "Staff_Code") }, 
    		   inverseJoinColumns = { @JoinColumn(name = "STFKW_KeywordCode", referencedColumnName = "KW_Code") })  
    private Set<mmSpecializationKeyword> listSpecializationKeywords = new HashSet<mmSpecializationKeyword>();
    
    
	public String getStaff_Category_Code() {
		return Staff_Category_Code;
	}

	public void setStaff_Category_Code(String staff_Category_Code) {
		Staff_Category_Code = staff_Category_Code;
	}

	public int getStaff_ID() {
		return Staff_ID;
	}

	public Set<mmSpecializationKeyword> getListSpecializationKeywords() {
		return listSpecializationKeywords;
	}

	public void setListSpecializationKeywords(
			Set<mmSpecializationKeyword> listSpecializationKeywords) {
		this.listSpecializationKeywords = listSpecializationKeywords;
	}

	/*public Set<MasterThesis> getListMasterThesis() {
		return listMasterThesis;
	}

	public void setListMasterThesis(Set<MasterThesis> listMasterThesis) {
		this.listMasterThesis = listMasterThesis;
	}*/

	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}

	public String getStaff_Code() {
		return Staff_Code;
	}

	public void setStaff_Code(String staff_Code) {
		Staff_Code = staff_Code;
	}

	public String getStaff_Name() {
		return Staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}

	public String getStaff_AsciiName() {
		return Staff_AsciiName;
	}

	public void setStaff_AsciiName(String staff_AsciiName) {
		Staff_AsciiName = staff_AsciiName;
	}

	public String getStaff_Email() {
		return Staff_Email;
	}

	public void setStaff_Email(String staff_Email) {
		Staff_Email = staff_Email;
	}

	public String getStaff_Phone() {
		return Staff_Phone;
	}

	public void setStaff_Phone(String staff_Phone) {
		Staff_Phone = staff_Phone;
	}

	/*public mmStaffCategory getStaffCategory() {
		return staffCategory;
	}

	public void setStaffCategory(mmStaffCategory staffCategory) {
		this.staffCategory = staffCategory;
	}

	/*public String getStaff_University_Code() {
		return Staff_University_Code;
	}

	public void setStaff_University_Code(String staff_University_Code) {
		Staff_University_Code = staff_University_Code;
	}*/
	public String getStaff_Department_Code() {
		return Staff_Department_Code;
	}

	public void setStaff_Department_Code(String staff_Department_Code) {
		Staff_Department_Code = staff_Department_Code;
	}

	/*public String getStaff_Category_Code() {
		return Staff_Category_Code;
	}

	public void setStaff_Category_Code(String staff_Category_Code) {
		Staff_Category_Code = staff_Category_Code;
	}*/

	public String getStaff_User_Code() {
		return Staff_User_Code;
	}

	public void setStaff_User_Code(String staff_User_Code) {
		Staff_User_Code = staff_User_Code;
	}

	
 	public String getStaff_Faculty_Code() {
		return Staff_Faculty_Code;
	}

	public void setStaff_Faculty_Code(String staff_Faculty_Code) {
		Staff_Faculty_Code = staff_Faculty_Code;
	}

	public String getStaff_AcademicRank() {
		return Staff_AcademicRank;
	}

	public void setStaff_AcademicRank(String staff_AcademicRank) {
		Staff_AcademicRank = staff_AcademicRank;
	}
	
}
