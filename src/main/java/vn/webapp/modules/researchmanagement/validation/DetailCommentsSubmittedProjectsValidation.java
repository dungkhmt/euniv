package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Max;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class DetailCommentsSubmittedProjectsValidation{
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
	private int CMTSUBPRJ_Eval_Motivation;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Innovation;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Applicability;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_RearchMethodology;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_ResearchContent;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Paper;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Product;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Patent;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Graduate_Student;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Young_Rearcher;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Education_Graduate;
	
	@NumberFormat(style = Style.NUMBER)
	@Max(value=99)
    private int CMTSUBPRJ_Eval_Reasonable_Budget;
	
    private String CMTSUBPRJ_Eval_Classification;
    private String CMTSUBPRJ_Eval_Conclusion;
    
    private int projectId;
    
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
