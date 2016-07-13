package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbljurymembers")
public class mmJuryMember implements Serializable{

	@Id
	@GeneratedValue
	private int JuryMem_ID;
	private String JuryMem_Code;
	private String JuryMem_MemberCode;
	private String JuryMem_DefenseSessionCode;
	private String JuryMem_StaffCode;
	
	public int getJuryMem_ID() {
		return JuryMem_ID;
	}
	public void setJuryMem_ID(int juryMem_ID) {
		JuryMem_ID = juryMem_ID;
	}
	
	public String getJuryMem_Code() {
		return JuryMem_Code;
	}
	
	public void setJuryMem_Code(String juryMem_Code) {
		JuryMem_Code = juryMem_Code;
	}
	
	public String getJuryMem_MemberCode() {
		return JuryMem_MemberCode;
	}
	
	public void setJuryMem_MemberCode(String juryMem_MemberCode) {
		JuryMem_MemberCode = juryMem_MemberCode;
	}
	
	public String getJuryMem_DefenseSessionCode() {
		return JuryMem_DefenseSessionCode;
	}
	
	public void setJuryMem_DefenseSessionCode(String juryMem_DefenseSessionCode) {
		JuryMem_DefenseSessionCode = juryMem_DefenseSessionCode;
	}
	
	public String getJuryMem_StaffCode() {
		return JuryMem_StaffCode;
	}
	
	public void setJuryMem_StaffCode(String juryMem_StaffCode) {
		JuryMem_StaffCode = juryMem_StaffCode;
	}	
	
}
