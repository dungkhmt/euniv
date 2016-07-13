/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblfaculty")
public class mFaculty implements Serializable{
	
	@Id
    @GeneratedValue
    private int Faculty_ID;
    private String Faculty_Code;
    private String Faculty_Name;
    private String Faculty_AsciiName;

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
}
