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
@Table(name = "tblfaculty")
public class mmFaculty implements Serializable{
	
	@Id
	@GeneratedValue
	private int Faculty_ID;
	private String Faculty_Code;
	private String Faculty_Name;
	private String Faculty_AsciiName;
		
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="Faculty_University_Code", referencedColumnName = "University_Code", insertable = false, updatable = false)
	private mmUniversity university;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="faculty", cascade = CascadeType.ALL)
	private Set<mmDepartment> listDepartment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="faculty", cascade = CascadeType.ALL)
	private Set<mmMasterClass> listMasterClass;
	
	public Set<mmMasterClass> getListMasterClass() {
		return listMasterClass;
	}

	public void setListMasterClass(Set<mmMasterClass> listMasterClass) {
		this.listMasterClass = listMasterClass;
	}
	public int getFaculty_ID() {
		return Faculty_ID;
	}

	public void setFaculty_ID(int faculty_ID) {
		Faculty_ID = faculty_ID;
	}

	public String getFaculty_Code() {
		return Faculty_Code;
	}

	public void setFaculty_Code(String faculty_Code) {
		Faculty_Code = faculty_Code;
	}
	public String getFaculty_Name() {
		return Faculty_Name;
	}

	public void setFaculty_Name(String faculty_Name) {
		Faculty_Name = faculty_Name;
	}

	public String getFaculty_AsciiName() {
		return Faculty_AsciiName;
	}

	public void setFaculty_AsciiName(String faculty_AsciiName) {
		Faculty_AsciiName = faculty_AsciiName;
	}

	public mmUniversity getUniversity() {
		return university;
	}

	public void setUniversity(mmUniversity university) {
		this.university = university;
	}

	public Set<mmDepartment> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(Set<mmDepartment> listDepartment) {
		this.listDepartment = listDepartment;
	}	
}
