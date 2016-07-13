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
@Table(name = "tblacademicrank")
public class mmAcademicRank implements Serializable{
	
	@Id
	@GeneratedValue
	private int AcademicRank_ID;
	private String AcademicRank_Code;
	private String AcademicRank_VNName;
	private String AcademicRank_AsciiName;
	private String AcademicRank_VNAbbr;
	private int AcademicRank_Score;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="academicRank", cascade = CascadeType.ALL)
	private Set<mmStaff> listStaffs = new HashSet<mmStaff>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="academicRank", cascade = CascadeType.ALL)
	private Set<mmExternalStaff> listExternalStaffs = new HashSet<mmExternalStaff>();
	
	
	public int getAcademicRank_ID() {
		return AcademicRank_ID;
	}
	public void setAcademicRank_ID(int academicRank_ID) {
		AcademicRank_ID = academicRank_ID;
	}
	public String getAcademicRank_Code() {
		return AcademicRank_Code;
	}
	public void setAcademicRank_Code(String academicRank_Code) {
		AcademicRank_Code = academicRank_Code;
	}
	public String getAcademicRank_VNName() {
		return AcademicRank_VNName;
	}
	public void setAcademicRank_VNName(String academicRank_VNName) {
		AcademicRank_VNName = academicRank_VNName;
	}
	public String getAcademicRank_AsciiName() {
		return AcademicRank_AsciiName;
	}
	public void setAcademicRank_AsciiName(String academicRank_AsciiName) {
		AcademicRank_AsciiName = academicRank_AsciiName;
	}
	public String getAcademicRank_VNAbbr() {
		return AcademicRank_VNAbbr;
	}
	public void setAcademicRank_VNAbbr(String academicRank_VNAbbr) {
		AcademicRank_VNAbbr = academicRank_VNAbbr;
	}
	public int getAcademicRank_Score() {
		return AcademicRank_Score;
	}
	public void setAcademicRank_Score(int academicRank_Score) {
		AcademicRank_Score = academicRank_Score;
	}
	public Set<mmStaff> getListStaffs() {
		return listStaffs;
	}
	public void setListStaffs(Set<mmStaff> listStaffs) {
		this.listStaffs = listStaffs;
	}
	public Set<mmExternalStaff> getListExternalStaffs() {
		return listExternalStaffs;
	}
	public void setListExternalStaffs(Set<mmExternalStaff> listExternalStaffs) {
		this.listExternalStaffs = listExternalStaffs;
	}	
}
