package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tblJuryRooms")
public class mmJuryRoom implements Serializable{
	
	@Id
	@GeneratedValue
	private int JuryRoom_ID;
	private int JuryRoom_Index;
	private String JuryRoom_Code;
	private String JuryRoom_DefenseSessionCode;
	private String JuryRoom_StaffCode;
	public int getJuryRoom_ID() {
		return JuryRoom_ID;
	}
	public void setJuryRoom_ID(int juryRoom_ID) {
		JuryRoom_ID = juryRoom_ID;
	}
	public int getJuryRoom_Index() {
		return JuryRoom_Index;
	}
	public void setJuryRoom_Index(int juryRoom_Index) {
		JuryRoom_Index = juryRoom_Index;
	}
	public String getJuryRoom_Code() {
		return JuryRoom_Code;
	}
	public void setJuryRoom_Code(String juryRoom_Code) {
		JuryRoom_Code = juryRoom_Code;
	}
	public String getJuryRoom_DefenseSessionCode() {
		return JuryRoom_DefenseSessionCode;
	}
	public void setJuryRoom_DefenseSessionCode(String juryRoom_DefenseSessionCode) {
		JuryRoom_DefenseSessionCode = juryRoom_DefenseSessionCode;
	}
	public String getJuryRoom_StaffCode() {
		return JuryRoom_StaffCode;
	}
	public void setJuryRoom_StaffCode(String juryRoom_StaffCode) {
		JuryRoom_StaffCode = juryRoom_StaffCode;
	}
	
}
