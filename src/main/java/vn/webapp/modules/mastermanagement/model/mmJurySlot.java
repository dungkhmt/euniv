package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbljuryslots")
public class mmJurySlot implements Serializable{
	
	@Id
	@GeneratedValue
	private int JurySlot_ID;
	private int JurySlot_Index;
	private String JurySlot_Code;
	private String JurySlot_Name;
	private String JurySlot_DefenseSessionCode;
	private String JurySlot_StaffCode;
	public int getJurySlot_ID() {
		return JurySlot_ID;
	}
	public void setJurySlot_ID(int jurySlot_ID) {
		JurySlot_ID = jurySlot_ID;
	}
	public int getJurySlot_Index() {
		return JurySlot_Index;
	}
	public void setJurySlot_Index(int jurySlot_Index) {
		JurySlot_Index = jurySlot_Index;
	}
	public String getJurySlot_Code() {
		return JurySlot_Code;
	}
	public void setJurySlot_Code(String jurySlot_Code) {
		JurySlot_Code = jurySlot_Code;
	}
	public String getJurySlot_DefenseSessionCode() {
		return JurySlot_DefenseSessionCode;
	}
	public void setJurySlot_DefenseSessionCode(String jurySlot_DefenseSessionCode) {
		JurySlot_DefenseSessionCode = jurySlot_DefenseSessionCode;
	}
	public String getJurySlot_StaffCode() {
		return JurySlot_StaffCode;
	}
	public void setJurySlot_StaffCode(String jurySlot_StaffCode) {
		JurySlot_StaffCode = jurySlot_StaffCode;
	}
	public String getJurySlot_Name() {
		return JurySlot_Name;
	}
	public void setJurySlot_Name(String jurySlot_Name) {
		JurySlot_Name = jurySlot_Name;
	}	
}
