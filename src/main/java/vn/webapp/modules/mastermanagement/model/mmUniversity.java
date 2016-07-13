package vn.webapp.modules.mastermanagement.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbluniversity")
public class mmUniversity implements Serializable{
	
	@Id
	@GeneratedValue
	private int University_ID;
	private String University_Code;
	private String University_Name;
	private String University_AsciiName;
	private String University_Role;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy="university", cascade = CascadeType.ALL)
	private Set<mmFaculty> listFaculty;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="university", cascade = CascadeType.ALL)
	private Set<mmExternalStaff> listExternalStaff = new HashSet<mmExternalStaff>();
   
	
	public Set<mmFaculty> getListFaculty() {
		return listFaculty;
	}
	public void setListFaculty(Set<mmFaculty> listFaculty) {
		this.listFaculty = listFaculty;
	}
	public int getUniversity_ID() {
		return University_ID;
	}
	public void setUniversity_ID(int university_ID) {
		University_ID = university_ID;
	}
	public String getUniversity_Code() {
		return University_Code;
	}
	public void setUniversity_Code(String university_Code) {
		University_Code = university_Code;
	}
	public String getUniversity_Name() {
		return University_Name;
	}
	public void setUniversity_Name(String university_Name) {
		University_Name = university_Name;
	}
	public String getUniversity_AsciiName() {
		return University_AsciiName;
	}
	public void setUniversity_AsciiName(String university_AsciiName) {
		University_AsciiName = university_AsciiName;
	}
	public String getUniversity_Role() {
		return University_Role;
	}
	public void setUniversity_Role(String university_Role) {
		University_Role = university_Role;
	}
	public Set<mmExternalStaff> getListExternalStaff() {
		return listExternalStaff;
	}
	public void setListExternalStaff(Set<mmExternalStaff> listExternalStaff) {
		this.listExternalStaff = listExternalStaff;
	}
	
	
}