/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.cfg.context.Cascadable;


@Entity
@Table(name = "tblstaffs")
public class mmStaffJuryMember implements Serializable{
    
    @Id
    @GeneratedValue
    private int Staff_ID;
    private String Staff_Code;
    private String Staff_Name;
    private String Staff_AsciiName;
    private String Staff_Email;
    private String Staff_Department_Code;
    private String Staff_Phone;
    private String Staff_Category_Code;
    private String Staff_User_Code;
    
    
 	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Staff_Department_Code", referencedColumnName = "Department_Code", insertable=false, updatable = false)
    private mmDepartment department;
    
	public int getStaff_ID() {
		return Staff_ID;
	}

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

	public String getStaff_Department_Code() {
		return Staff_Department_Code;
	}

	public void setStaff_Department_Code(String staff_Department_Code) {
		Staff_Department_Code = staff_Department_Code;
	}

	public String getStaff_Phone() {
		return Staff_Phone;
	}

	public void setStaff_Phone(String staff_Phone) {
		Staff_Phone = staff_Phone;
	}

	public String getStaff_Category_Code() {
		return Staff_Category_Code;
	}

	public void setStaff_Category_Code(String staff_Category_Code) {
		Staff_Category_Code = staff_Category_Code;
	}

	public String getStaff_User_Code() {
		return Staff_User_Code;
	}

	public void setStaff_User_Code(String staff_User_Code) {
		Staff_User_Code = staff_User_Code;
	}

	public mmDepartment getDepartment() {
		return department;
	}

	public void setDepartment(mmDepartment department) {
		this.department = department;
	}    
	
}
