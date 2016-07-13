/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbldepartment")
public class mDepartment implements Serializable{
	@Id
    @GeneratedValue
    private int Department_ID;
    private String Department_Code;
    private String Department_Name;
    private String Department_AsciiName;
    private String Department_Faculty_Code;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Department_Faculty_Code", referencedColumnName = "Faculty_Code",insertable = false, updatable = false)
    private mFaculty faculty;

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

	public String getDepartment_Faculty_Code() {
		return Department_Faculty_Code;
	}

	public void setDepartment_Faculty_Code(String department_Faculty_Code) {
		Department_Faculty_Code = department_Faculty_Code;
	}
    
    public mFaculty getFaculty() {
		return faculty;
	}

	public void setFaculty(mFaculty faculty) {
		this.faculty = faculty;
	}
}
