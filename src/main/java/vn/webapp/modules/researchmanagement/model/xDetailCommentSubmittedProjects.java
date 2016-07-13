package vn.webapp.modules.researchmanagement.model;

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

import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;

@Entity
@Table(name = "tbldetailcommentsubmittedprojects")
public class xDetailCommentSubmittedProjects implements Serializable{
	@Id
    @GeneratedValue
    private int CMTSUBPRJ_ID;
    private String CMTSUBPRJ_StaffCode;
    private String CMTSUBPRJ_PRJCode;
    private String CMTSUBPRJ_PRJCallCode;
    private int CMTSUBPRJ_Eval_Motivation;
    private int CMTSUBPRJ_Eval_Innovation;
    private int CMTSUBPRJ_Eval_Applicability;
    private int CMTSUBPRJ_Eval_RearchMethodology;
    private int CMTSUBPRJ_Eval_ResearchContent;
    private int CMTSUBPRJ_Eval_Paper;
    private int CMTSUBPRJ_Eval_Product;
    private int CMTSUBPRJ_Eval_Patent;
    private int CMTSUBPRJ_Eval_Graduate_Student;
    private int CMTSUBPRJ_Eval_Young_Rearcher;
    private int CMTSUBPRJ_Eval_Education_Graduate;
    private int CMTSUBPRJ_Eval_Reasonable_Budget;
    private String CMTSUBPRJ_Eval_Classification;
    private String CMTSUBPRJ_Eval_Conclusion;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="CMTSUBPRJ_PRJCode", referencedColumnName = "PROJ_Code", insertable = false, updatable = false)
    public xProjects xprojects;
    
	public int getCMTSUBPRJ_ID() {
		return CMTSUBPRJ_ID;
	}
	public void setCMTSUBPRJ_ID(int cMTSUBPRJ_ID) {
		CMTSUBPRJ_ID = cMTSUBPRJ_ID;
	}
	public String getCMTSUBPRJ_StaffCode() {
		return CMTSUBPRJ_StaffCode;
	}
	public void setCMTSUBPRJ_StaffCode(String cMTSUBPRJ_StaffCode) {
		CMTSUBPRJ_StaffCode = cMTSUBPRJ_StaffCode;
	}
	public String getCMTSUBPRJ_PRJCode() {
		return CMTSUBPRJ_PRJCode;
	}
	public void setCMTSUBPRJ_PRJCode(String cMTSUBPRJ_PRJCode) {
		CMTSUBPRJ_PRJCode = cMTSUBPRJ_PRJCode;
	}
	public String getCMTSUBPRJ_PRJCallCode() {
		return CMTSUBPRJ_PRJCallCode;
	}
	public void setCMTSUBPRJ_PRJCallCode(String cMTSUBPRJ_PRJCallCode) {
		CMTSUBPRJ_PRJCallCode = cMTSUBPRJ_PRJCallCode;
	}
	public int getCMTSUBPRJ_Eval_Motivation() {
		return CMTSUBPRJ_Eval_Motivation;
	}
	public void setCMTSUBPRJ_Eval_Motivation(int cMTSUBPRJ_Eval_Motivation) {
		CMTSUBPRJ_Eval_Motivation = cMTSUBPRJ_Eval_Motivation;
	}
	public int getCMTSUBPRJ_Eval_Innovation() {
		return CMTSUBPRJ_Eval_Innovation;
	}
	public void setCMTSUBPRJ_Eval_Innovation(int cMTSUBPRJ_Eval_Innovation) {
		CMTSUBPRJ_Eval_Innovation = cMTSUBPRJ_Eval_Innovation;
	}
	public int getCMTSUBPRJ_Eval_Applicability() {
		return CMTSUBPRJ_Eval_Applicability;
	}
	public void setCMTSUBPRJ_Eval_Applicability(int cMTSUBPRJ_Eval_Applicability) {
		CMTSUBPRJ_Eval_Applicability = cMTSUBPRJ_Eval_Applicability;
	}
	public int getCMTSUBPRJ_Eval_RearchMethodology() {
		return CMTSUBPRJ_Eval_RearchMethodology;
	}
	public void setCMTSUBPRJ_Eval_RearchMethodology(
			int cMTSUBPRJ_Eval_RearchMethodology) {
		CMTSUBPRJ_Eval_RearchMethodology = cMTSUBPRJ_Eval_RearchMethodology;
	}
	public int getCMTSUBPRJ_Eval_ResearchContent() {
		return CMTSUBPRJ_Eval_ResearchContent;
	}
	public void setCMTSUBPRJ_Eval_ResearchContent(int cMTSUBPRJ_Eval_ResearchContent) {
		CMTSUBPRJ_Eval_ResearchContent = cMTSUBPRJ_Eval_ResearchContent;
	}
	public int getCMTSUBPRJ_Eval_Paper() {
		return CMTSUBPRJ_Eval_Paper;
	}
	public void setCMTSUBPRJ_Eval_Paper(int cMTSUBPRJ_Eval_Paper) {
		CMTSUBPRJ_Eval_Paper = cMTSUBPRJ_Eval_Paper;
	}
	public int getCMTSUBPRJ_Eval_Product() {
		return CMTSUBPRJ_Eval_Product;
	}
	public void setCMTSUBPRJ_Eval_Product(int cMTSUBPRJ_Eval_Product) {
		CMTSUBPRJ_Eval_Product = cMTSUBPRJ_Eval_Product;
	}
	public int getCMTSUBPRJ_Eval_Patent() {
		return CMTSUBPRJ_Eval_Patent;
	}
	public void setCMTSUBPRJ_Eval_Patent(int cMTSUBPRJ_Eval_Patent) {
		CMTSUBPRJ_Eval_Patent = cMTSUBPRJ_Eval_Patent;
	}
	public int getCMTSUBPRJ_Eval_Graduate_Student() {
		return CMTSUBPRJ_Eval_Graduate_Student;
	}
	public void setCMTSUBPRJ_Eval_Graduate_Student(
			int cMTSUBPRJ_Eval_Graduate_Student) {
		CMTSUBPRJ_Eval_Graduate_Student = cMTSUBPRJ_Eval_Graduate_Student;
	}
	public int getCMTSUBPRJ_Eval_Young_Rearcher() {
		return CMTSUBPRJ_Eval_Young_Rearcher;
	}
	public void setCMTSUBPRJ_Eval_Young_Rearcher(int cMTSUBPRJ_Eval_Young_Rearcher) {
		CMTSUBPRJ_Eval_Young_Rearcher = cMTSUBPRJ_Eval_Young_Rearcher;
	}
	public int getCMTSUBPRJ_Eval_Education_Graduate() {
		return CMTSUBPRJ_Eval_Education_Graduate;
	}
	public void setCMTSUBPRJ_Eval_Education_Graduate(
			int cMTSUBPRJ_Eval_Education_Graduate) {
		CMTSUBPRJ_Eval_Education_Graduate = cMTSUBPRJ_Eval_Education_Graduate;
	}
	public int getCMTSUBPRJ_Eval_Reasonable_Budget() {
		return CMTSUBPRJ_Eval_Reasonable_Budget;
	}
	public void setCMTSUBPRJ_Eval_Reasonable_Budget(
			int cMTSUBPRJ_Eval_Reasonable_Budget) {
		CMTSUBPRJ_Eval_Reasonable_Budget = cMTSUBPRJ_Eval_Reasonable_Budget;
	}
	public String getCMTSUBPRJ_Eval_Classification() {
		return CMTSUBPRJ_Eval_Classification;
	}
	public void setCMTSUBPRJ_Eval_Classification(
			String cMTSUBPRJ_Eval_Classification) {
		CMTSUBPRJ_Eval_Classification = cMTSUBPRJ_Eval_Classification;
	}
	public String getCMTSUBPRJ_Eval_Conclusion() {
		return CMTSUBPRJ_Eval_Conclusion;
	}
	public void setCMTSUBPRJ_Eval_Conclusion(String cMTSUBPRJ_Eval_Conclusion) {
		CMTSUBPRJ_Eval_Conclusion = cMTSUBPRJ_Eval_Conclusion;
	}
	public xProjects getXprojects() {
		return xprojects;
	}
	public void setXprojects(xProjects xprojects) {
		this.xprojects = xprojects;
	}
	
	
}
