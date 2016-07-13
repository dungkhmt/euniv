/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tblrooms")
public class mmRooms implements Serializable{
    
    @Id
    @GeneratedValue
    private int R_ID;
    private String R_Code;
    private String R_Building;
    private String R_Note;
    private int R_Capacity;
    private int R_Cluster_Code;
    private int R_Floor;
	public int getR_ID() {
		return R_ID;
	}
	public void setR_ID(int r_ID) {
		R_ID = r_ID;
	}
	public String getR_Code() {
		return R_Code;
	}
	public void setR_Code(String r_Code) {
		R_Code = r_Code;
	}
	public String getR_Building() {
		return R_Building;
	}
	public void setR_Building(String r_Building) {
		R_Building = r_Building;
	}
	public String getR_Note() {
		return R_Note;
	}
	public void setR_Note(String r_Note) {
		R_Note = r_Note;
	}
	public int getR_Capacity() {
		return R_Capacity;
	}
	public void setR_Capacity(int r_Capacity) {
		R_Capacity = r_Capacity;
	}
	public int getR_Cluster_Code() {
		return R_Cluster_Code;
	}
	public void setR_Cluster_Code(int r_Cluster_Code) {
		R_Cluster_Code = r_Cluster_Code;
	}
	public int getR_Floor() {
		return R_Floor;
	}
	public void setR_Floor(int r_Floor) {
		R_Floor = r_Floor;
	}
}
