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

@Entity
@Table(name = "tblstudent")
public class mmStudent implements Serializable{
    
    @Id
    @GeneratedValue
    private int Student_ID;
    private String Student_Code;
    private String Student_Name;
    private String Student_Email;
    private String Student_Phone;
	private int Student_StatusID;
	private String Student_ClassesCode;
     
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="Student_ClassesCode", referencedColumnName = "Classes_Code", insertable=false, updatable=false)
	private mmMasterClass masterClass;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Student_Code", referencedColumnName = "Thesis_StudentCode", insertable=false, updatable=false)
    private mmMasterThesis masterThesis;
	
	public mmMasterThesis getMasterThesis() {
		return masterThesis;
	}

	public void setMasterThesis(mmMasterThesis masterThesis) {
		this.masterThesis = masterThesis;
	}

	public int getStudent_ID() {
		return Student_ID;
	}

	public void setStudent_ID(int student_ID) {
		Student_ID = student_ID;
	}

	public String getStudent_Name() {
		return Student_Name;
	}

	public void setStudent_Name(String student_Name) {
		Student_Name = student_Name;
	}

	public int getStudent_StatusID() {
		return Student_StatusID;
	}

	public void setStudent_StatusID(int student_StatusID) {
		Student_StatusID = student_StatusID;
	}

	public String getStudent_Phone() {
		return Student_Phone;
	}


	public void setStudent_Phone(String student_Phone) {
		Student_Phone = student_Phone;
	}

	public String getStudent_Email() {
		return Student_Email;
	}

	public void setStudent_Email(String student_Email) {
		Student_Email = student_Email;
	}

	public mmMasterClass getMasterClass() {
		return masterClass;
	}

	public void setMasterClass(mmMasterClass masterClass) {
		this.masterClass = masterClass;
	}

	public String getStudent_ClassesCode() {
		return Student_ClassesCode;
	}

	public void setStudent_ClassesCode(String student_ClassesCode) {
		Student_ClassesCode = student_ClassesCode;
	}

	public String getStudent_Code() {
		return Student_Code;
	}

	public void setStudent_Code(String student_Code) {
		Student_Code = student_Code;
	}

}
