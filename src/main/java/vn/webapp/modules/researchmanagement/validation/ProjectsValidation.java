package vn.webapp.modules.researchmanagement.validation;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;

public class ProjectsValidation{
	/** Set rules for fields */
	private int projectId;

	@NotEmpty
	private String projectName;

	private String projectContent;

	private String projectMotivation;

	private String projectResult;
	
	private int projectBudget;
	
	private String projectStartDate;
	
	private String projectEndDate;
	
	private String projectLeader;
	
	private String projectSurvey;
	
	private String projectObjective;
	
	private String projectMemberTasks;
	
	private String projectMemberBudget;
	
	private String projectMemberName;
	
	private String projectMemberRole;
	
	private String falcutyAddress;
	
   	private String budgetMaterial;
	
	private int projectMemberWorkingDays;
	
	private String currentProjectCode;
	
	private String projectStatusCode;
	
	@NotEmpty
	private String projectCallCode;
	
	private MultipartFile projectFileUpload;
	

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getProjectMotivation() {
		return projectMotivation;
	}

	public void setProjectMotivation(String projectMotivation) {
		this.projectMotivation = projectMotivation;
	}

	public String getProjectResult() {
		return projectResult;
	}

	public void setProjectResult(String projectResult) {
		this.projectResult = projectResult;
	}

	public int getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(int projectBudget) {
		this.projectBudget = projectBudget;
	}

	public String getProjectCallCode() {
		return projectCallCode;
	}

	public void setProjectCallCode(String projectCallCode) {
		this.projectCallCode = projectCallCode;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public String getProjectSurvey() {
		return projectSurvey;
	}

	public void setProjectSurvey(String projectSurvey) {
		this.projectSurvey = projectSurvey;
	}

	public String getProjectObjective() {
		return projectObjective;
	}

	public void setProjectObjective(String projectObjective) {
		this.projectObjective = projectObjective;
	}

	public String getProjectMemberTasks() {
		return projectMemberTasks;
	}

	public void setProjectMemberTasks(String projectMemberTasks) {
		this.projectMemberTasks = projectMemberTasks;
	}

	public String getProjectMemberBudget() {
		return projectMemberBudget;
	}

	public void setProjectMemberBudget(String projectMemberBudget) {
		this.projectMemberBudget = projectMemberBudget;
	}

	public String getProjectMemberName() {
		return projectMemberName;
	}

	public void setProjectMemberName(String projectMemberName) {
		this.projectMemberName = projectMemberName;
	}

	public String getProjectMemberRole() {
		return projectMemberRole;
	}

	public void setProjectMemberRole(String projectMemberRole) {
		this.projectMemberRole = projectMemberRole;
	}

	public int getProjectMemberWorkingDays() {
		return projectMemberWorkingDays;
	}

	public void setProjectMemberWorkingDays(int projectMemberWorkingDays) {
		this.projectMemberWorkingDays = projectMemberWorkingDays;
	}

	public String getFalcutyAddress() {
		return falcutyAddress;
	}

	public void setFalcutyAddress(String falcutyAddress) {
		this.falcutyAddress = falcutyAddress;
	}

	public String getCurrentProjectCode() {
		return currentProjectCode;
	}

	public void setCurrentProjectCode(String currentProjectCode) {
		this.currentProjectCode = currentProjectCode;
	}

	public String getProjectStatusCode() {
		return projectStatusCode;
	}

	public void setProjectStatusCode(String projectStatusCode) {
		this.projectStatusCode = projectStatusCode;
	}

	public MultipartFile getProjectFileUpload() {
		return projectFileUpload;
	}

	public void setProjectFileUpload(MultipartFile projectFileUpload) {
		this.projectFileUpload = projectFileUpload;
	}

	public String getBudgetMaterial() {
		return budgetMaterial;
	}

	public void setBudgetMaterial(String budgetMaterial) {
		this.budgetMaterial = budgetMaterial;
	}
}
