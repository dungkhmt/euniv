/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tbldepartment")
public class mmDepartment implements Serializable{
    
    @Id
    @GeneratedValue
    private int Department_ID;
    private String Department_Code;
    private String Department_Name;
    private String Department_AsciiName;
    
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="Department_Faculty_Code", referencedColumnName = "Faculty_Code", insertable = false, updatable = false)
	private mmFaculty faculty;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="department", cascade = CascadeType.ALL)
	private Set<mmStaff> listStaff;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="department", cascade = CascadeType.ALL)
   	private Set<mmStaffJuryMember> listStaffJuryMember;

	public Set<mmStaff> getListStaff() {
		return listStaff;
	}
	
	public void setListStaff(Set<mmStaff> listStaff) {
		this.listStaff = listStaff;
	}

	public int getDepartment_ID() {
		return Department_ID;
	}

	public void setDepartment_ID(int department_ID) {
		Department_ID = department_ID;
	}

	public String getDepartment_Code() {
		return Department_Code;
	}

	public void setDepartment_Code(String department_Code) {
		Department_Code = department_Code;
	}

	public String getDepartment_Name() {
		return Department_Name;
	}

	public void setDepartment_Name(String department_Name) {
		Department_Name = department_Name;
	}

	public String getDepartment_AsciiName() {
		return Department_AsciiName;
	}

	public void setDepartment_AsciiName(String department_AsciiName) {
		Department_AsciiName = department_AsciiName;
	}

	public mmFaculty getFaculty() {
		return faculty;
	}

	public void setFaculty(mmFaculty faculty) {
		this.faculty = faculty;
	}

	public Set<mmStaffJuryMember> getListStaffJuryMember() {
		return listStaffJuryMember;
	}

	public void setListStaffJuryMember(Set<mmStaffJuryMember> listStaffJuryMember) {
		this.listStaffJuryMember = listStaffJuryMember;
	}
	

	/*public Set<MasterClass> getListMasterClass() {
		return listMasterClass;
	}

	public void setListMasterClass(Set<MasterClass> listMasterClass) {
		this.listMasterClass = listMasterClass;
	}*/
}
