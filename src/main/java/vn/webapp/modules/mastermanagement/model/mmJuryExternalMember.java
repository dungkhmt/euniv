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
@Table(name="tbljuryexternalmembers")
public class mmJuryExternalMember implements Serializable{

	@Id
	@GeneratedValue
	private int JEM_ID;
	private String JEM_Code;
	private String JEM_MemberCode;
	private String JEM_DefenseSessionCode;
	private String JEM_StaffCode;	
	
	public int getJEM_ID() {
		return JEM_ID;
	}
	public void setJEM_ID(int jEM_ID) {
		JEM_ID = jEM_ID;
	}
	public String getJEM_Code() {
		return JEM_Code;
	}
	public void setJEM_Code(String jEM_Code) {
		JEM_Code = jEM_Code;
	}
	public String getJEM_MemberCode() {
		return JEM_MemberCode;
	}
	public void setJEM_MemberCode(String jEM_MemberCode) {
		JEM_MemberCode = jEM_MemberCode;
	}
	public String getJEM_DefenseSessionCode() {
		return JEM_DefenseSessionCode;
	}
	public void setJEM_DefenseSessionCode(String jEM_DefenseSessionCode) {
		JEM_DefenseSessionCode = jEM_DefenseSessionCode;
	}
	public String getJEM_StaffCode() {
		return JEM_StaffCode;
	}
	public void setJEM_StaffCode(String jEM_StaffCode) {
		JEM_StaffCode = jEM_StaffCode;
	}	
}
