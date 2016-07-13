/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tblspecializationkeywords")
public class mmSpecializationKeyword implements Serializable{
    
    @Id
    @GeneratedValue
    private int KW_ID;
    private String KW_Code;
    private String KW_EngName;
    private String KW_VNName;
  
    @ManyToOne
    @JoinColumn(name="KW_ScientificFieldCode", referencedColumnName = "SCIF_Code", insertable = false, updatable = false)
    public mmScientificField scientificField;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listSpecializationKeywords",cascade = CascadeType.ALL)  
    Set<mmStaffInput> listStaffInputs = new HashSet<mmStaffInput>();
		
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listSpecializationKeywords",cascade = CascadeType.ALL)  
    Set<mmExternalStaffInput> listExternalStaffInputs = new HashSet<mmExternalStaffInput>();  

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listSpecializationKeywords",cascade = CascadeType.ALL)  
    Set<mmMasterThesisInput> listRawMasterThesis = new HashSet<mmMasterThesisInput>();  

	public int getKW_ID() {
		return KW_ID;
	}

	public void setKW_ID(int kW_ID) {
		KW_ID = kW_ID;
	}

	public String getKW_Code() {
		return KW_Code;
	}

	public void setKW_Code(String kW_Code) {
		KW_Code = kW_Code;
	}

	public String getKW_EngName() {
		return KW_EngName;
	}

	public void setKW_EngName(String kW_EngName) {
		KW_EngName = kW_EngName;
	}

	public String getKW_VNName() {
		return KW_VNName;
	}

	public void setKW_VNName(String kW_VNName) {
		KW_VNName = kW_VNName;
	}

	public mmScientificField getScientificField() {
		return scientificField;
	}

	public void setScientificField(mmScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public Set<mmStaffInput> getListStaffInputs() {
		return listStaffInputs;
	}

	public void setListStaffInputs(Set<mmStaffInput> listStaffInputs) {
		this.listStaffInputs = listStaffInputs;
	}

	public Set<mmExternalStaffInput> getListExternalStaffInputs() {
		return listExternalStaffInputs;
	}

	public void setListExternalStaffInputs(
			Set<mmExternalStaffInput> listExternalStaffInputs) {
		this.listExternalStaffInputs = listExternalStaffInputs;
	}

	public Set<mmMasterThesisInput> getListRawMasterThesis() {
		return listRawMasterThesis;
	}

	public void setListRawMasterThesis(Set<mmMasterThesisInput> listRawMasterThesis) {
		this.listRawMasterThesis = listRawMasterThesis;
	}	
}
