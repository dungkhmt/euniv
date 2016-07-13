package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author incre
 *
 */
@Entity
@Table(name = "tblprojecttasks")
public class ProjectTasks implements Serializable{
	@Id
    @GeneratedValue
    private int PRJTSK_ID;
    private String PRJTSK_Code;
    private String PRJTSK_StaffCode;
    private String PRJTSK_RoleCode;
    private String PRJTSK_Proj_Code;
    private String PRJTSK_Task;
    private int PRJTSK_NRBDay;
    private int PRJTSK_Cost;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRJTSK_RoleCode", referencedColumnName = "PROJPARTIROLE_Code", insertable=false, updatable=false)
    private ProjectParticipationRoles participationRoles;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRJTSK_StaffCode", referencedColumnName = "Staff_Code", insertable=false, updatable=false)
    private mStaffThread staffProject;
    
	public int getPRJTSK_ID() {
		return PRJTSK_ID;
	}
	public void setPRJTSK_ID(int pRJTSK_ID) {
		PRJTSK_ID = pRJTSK_ID;
	}
	public String getPRJTSK_Code() {
		return PRJTSK_Code;
	}
	public void setPRJTSK_Code(String pRJTSK_Code) {
		PRJTSK_Code = pRJTSK_Code;
	}
	public String getPRJTSK_StaffCode() {
		return PRJTSK_StaffCode;
	}
	public void setPRJTSK_StaffCode(String pRJTSK_StaffCode) {
		PRJTSK_StaffCode = pRJTSK_StaffCode;
	}
	public String getPRJTSK_RoleCode() {
		return PRJTSK_RoleCode;
	}
	public void setPRJTSK_RoleCode(String pRJTSK_RoleCode) {
		PRJTSK_RoleCode = pRJTSK_RoleCode;
	}
	public String getPRJTSK_Proj_Code() {
		return PRJTSK_Proj_Code;
	}
	public void setPRJTSK_Proj_Code(String pRJTSK_Proj_Code) {
		PRJTSK_Proj_Code = pRJTSK_Proj_Code;
	}
	public String getPRJTSK_Task() {
		return PRJTSK_Task;
	}
	public void setPRJTSK_Task(String pRJTSK_Task) {
		PRJTSK_Task = pRJTSK_Task;
	}
	public int getPRJTSK_NRBDay() {
		return PRJTSK_NRBDay;
	}
	public void setPRJTSK_NRBDay(int pRJTSK_NRBDay) {
		PRJTSK_NRBDay = pRJTSK_NRBDay;
	}
	public int getPRJTSK_Cost() {
		return PRJTSK_Cost;
	}
	public void setPRJTSK_Cost(int pRJTSK_Cost) {
		PRJTSK_Cost = pRJTSK_Cost;
	}
	public ProjectParticipationRoles getParticipationRoles() {
		return participationRoles;
	}
	public void setParticipationRoles(ProjectParticipationRoles participationRoles) {
		this.participationRoles = participationRoles;
	}
	public mStaffThread getStaffProject() {
		return staffProject;
	}
	public void setStaffProject(mStaffThread staffProject) {
		this.staffProject = staffProject;
	}
}
