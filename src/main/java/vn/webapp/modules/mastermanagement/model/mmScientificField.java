/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tblscientificfield")
public class mmScientificField implements Serializable{
    
    @Id
    @GeneratedValue
    private int SCIF_ID;
    private String SCIF_Code;
    private String SCIF_VNName;
    private String SCIF_EngName;
    
    @OneToMany(mappedBy="scientificField")
    private Set<mmSpecializationKeyword> specializationKeywords;
    
	public int getSCIF_ID() {
		return SCIF_ID;
	}
	public void setSCIF_ID(int sCIF_ID) {
		SCIF_ID = sCIF_ID;
	}
	public String getSCIF_Code() {
		return SCIF_Code;
	}
	public void setSCIF_Code(String sCIF_Code) {
		SCIF_Code = sCIF_Code;
	}
	public String getSCIF_VNName() {
		return SCIF_VNName;
	}
	public void setSCIF_VNName(String sCIF_VNName) {
		SCIF_VNName = sCIF_VNName;
	}
	public String getSCIF_EngName() {
		return SCIF_EngName;
	}
	public void setSCIF_EngName(String sCIF_EngName) {
		SCIF_EngName = sCIF_EngName;
	}   	    
}
