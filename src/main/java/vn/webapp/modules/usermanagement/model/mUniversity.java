package vn.webapp.modules.usermanagement.model;


import java.io.Serializable;
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
public class mUniversity implements Serializable{
	
	@Id
	@GeneratedValue
	private int University_ID;
	private String University_Code;
	private String University_VNName;
	private String University_AsciiName;
	private String University_Role;
	
	
	
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
	public String getUniversity_VNName() {
		return University_VNName;
	}
	public void setUniversity_VNName(String university_VNName) {
		University_VNName = university_VNName;
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
}