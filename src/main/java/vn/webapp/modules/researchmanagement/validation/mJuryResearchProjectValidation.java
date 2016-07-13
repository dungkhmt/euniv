package vn.webapp.modules.researchmanagement.validation;

public class mJuryResearchProjectValidation {
	private int juryResearchProjectID;
	public int getJuryResearchProjectID() {
		return juryResearchProjectID;
	}
	public void setJuryResearchProjectID(int juryResearchProjectID) {
		this.juryResearchProjectID = juryResearchProjectID;
	}
	private String juryResearchProjectName;
	private String projectCallCode;
	public String getJuryResearchProjectName() {
		return juryResearchProjectName;
	}
	public void setJuryResearchProjectName(String juryResearchProjectName) {
		this.juryResearchProjectName = juryResearchProjectName;
	}
	public String getProjectCallCode() {
		return projectCallCode;
	}
	public void setProjectCallCode(String projectCallCode) {
		this.projectCallCode = projectCallCode;
	}
}
