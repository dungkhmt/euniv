/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.researchdeclarationmanagement.model;

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

import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.researchmanagement.model.mStaffThread;


@Entity
@Table(name = "tblpaperstaffs")
public class PaperStaffs implements Serializable{
    
    @Id
    @GeneratedValue
    private int PPSTF_ID;
    private String PPSTF_Code;
    private String PPSTF_StaffCode;
    private String PPSTF_PaperCode;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PPSTF_StaffCode", referencedColumnName = "Staff_Code", insertable=false, updatable=false)
    private mStaffThread staff;
    
	public mStaffThread getStaff() {
		return staff;
	}
	public void setStaff(mStaffThread staff) {
		this.staff = staff;
	}
	public int getPPSTF_ID() {
		return PPSTF_ID;
	}
	public void setPPSTF_ID(int pPSTF_ID) {
		PPSTF_ID = pPSTF_ID;
	}
	public String getPPSTF_Code() {
		return PPSTF_Code;
	}
	public void setPPSTF_Code(String pPSTF_Code) {
		PPSTF_Code = pPSTF_Code;
	}
	public String getPPSTF_StaffCode() {
		return PPSTF_StaffCode;
	}
	public void setPPSTF_StaffCode(String pPSTF_StaffCode) {
		PPSTF_StaffCode = pPSTF_StaffCode;
	}
	public String getPPSTF_PaperCode() {
		return PPSTF_PaperCode;
	}
	public void setPPSTF_PaperCode(String pPSTF_PaperCode) {
		PPSTF_PaperCode = pPSTF_PaperCode;
	}
}
