package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;

/**
 * @author incre
 *
 */
@Entity
@Table(name = "tblprojects")
public class xProjects implements Serializable{
	@Id
    @GeneratedValue
    private int PROJ_ID;
    private String PROJ_Code;
    private String PROJ_AcaYear_Code;
    private String PROJ_Name;
    private String PROJ_ProjCat_Code;
    private String PROJ_StartDate;
    private String PROJ_EndDate;
    private String PROJ_Survey; // Tổng quan tình hình đề tài
    private String PROJ_Motivation; // Tính cấp thiết
    private String PROJ_Objective; // Mục tiêu của đề tài
    private String PROJ_Content;
    private String PROJ_Result;
    private String PROJ_SourceFile;
    private String PROJ_Status_Code;
    private int PROJ_TotalBudget;
    private String PROJ_User_Code;
    private String PROJ_FacultyCode; // Khoa/viện
    private int PROJ_Locked1; // Khóa khi gửi lần 1
    private String PROJ_SurveyChanged; // Thay đổi tổng quan
    private String PROJ_MotivationChanged; // Thay đổi tính cấp thiết
    private String PROJ_ObjectiveChanged; // Thay đổi mục tiêu 
    private String PROJ_ContentChanged; // Thay đổi nội dung
    private String PROJ_ResultChanged; // Thay đổi kết quả
    private int PROJ_BudgetChanged; // Thay đổi tiền dự án
    private int PROJ_Locked2; // Khóa khi nộp lần cuối
    private String PROJ_PRJCall_Code; // Mã đợt gọi đề tài
    private int PROJ_BudgetMaterial; // Chi mua vật tư nguyên vật liệu
    private int PROJ_BudgetMaterialChanged; // Chi mua vật tư nguyên vật liệu
    private String PROJ_ResearchFieldCode;// ma linh vuc
    
    public String getPROJ_ResearchFieldCode() {
		return PROJ_ResearchFieldCode;
	}

	public void setPROJ_ResearchFieldCode(String pROJ_ResearchFieldCode) {
		PROJ_ResearchFieldCode = pROJ_ResearchFieldCode;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJ_User_Code", referencedColumnName = "Staff_User_Code", insertable=false, updatable=false)
    private mStaffThread staff;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJ_Code", referencedColumnName = "COMPROJ_PRJCODE", insertable=false, updatable=false)
    private mCommentsOfSubmittedProjects commentsOfSubmittedProjects;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="xprojects", cascade = CascadeType.ALL)
    private Set<xDetailCommentSubmittedProjects> detailCommentSubmittedProjects;

	public int getPROJ_ID() {
		return PROJ_ID;
	}

	public void setPROJ_ID(int pROJ_ID) {
		PROJ_ID = pROJ_ID;
	}

	public String getPROJ_Code() {
		return PROJ_Code;
	}

	public void setPROJ_Code(String pROJ_Code) {
		PROJ_Code = pROJ_Code;
	}

	public String getPROJ_AcaYear_Code() {
		return PROJ_AcaYear_Code;
	}

	public void setPROJ_AcaYear_Code(String pROJ_AcaYear_Code) {
		PROJ_AcaYear_Code = pROJ_AcaYear_Code;
	}

	public String getPROJ_Name() {
		return PROJ_Name;
	}

	public void setPROJ_Name(String pROJ_Name) {
		PROJ_Name = pROJ_Name;
	}

	public String getPROJ_ProjCat_Code() {
		return PROJ_ProjCat_Code;
	}

	public void setPROJ_ProjCat_Code(String pROJ_ProjCat_Code) {
		PROJ_ProjCat_Code = pROJ_ProjCat_Code;
	}

	public String getPROJ_StartDate() {
		return PROJ_StartDate;
	}

	public void setPROJ_StartDate(String pROJ_StartDate) {
		PROJ_StartDate = pROJ_StartDate;
	}

	public String getPROJ_EndDate() {
		return PROJ_EndDate;
	}

	public void setPROJ_EndDate(String pROJ_EndDate) {
		PROJ_EndDate = pROJ_EndDate;
	}

	public String getPROJ_Motivation() {
		return PROJ_Motivation;
	}

	public void setPROJ_Motivation(String pROJ_Motivation) {
		PROJ_Motivation = pROJ_Motivation;
	}

	public String getPROJ_Content() {
		return PROJ_Content;
	}

	public void setPROJ_Content(String pROJ_Content) {
		PROJ_Content = pROJ_Content;
	}

	public String getPROJ_Result() {
		return PROJ_Result;
	}

	public void setPROJ_Result(String pROJ_Result) {
		PROJ_Result = pROJ_Result;
	}

	public String getPROJ_SourceFile() {
		return PROJ_SourceFile;
	}

	public void setPROJ_SourceFile(String pROJ_SourceFile) {
		PROJ_SourceFile = pROJ_SourceFile;
	}

	public String getPROJ_Status_Code() {
		return PROJ_Status_Code;
	}

	public void setPROJ_Status_Code(String pROJ_Status_Code) {
		PROJ_Status_Code = pROJ_Status_Code;
	}

	public int getPROJ_TotalBudget() {
		return PROJ_TotalBudget;
	}

	public void setPROJ_TotalBudget(int pROJ_TotalBudget) {
		PROJ_TotalBudget = pROJ_TotalBudget;
	}

	public String getPROJ_User_Code() {
		return PROJ_User_Code;
	}

	public void setPROJ_User_Code(String pROJ_User_Code) {
		PROJ_User_Code = pROJ_User_Code;
	}

	public String getPROJ_FacultyCode() {
		return PROJ_FacultyCode;
	}

	public void setPROJ_FacultyCode(String pROJ_FacultyCode) {
		PROJ_FacultyCode = pROJ_FacultyCode;
	}

	public int getPROJ_Locked1() {
		return PROJ_Locked1;
	}

	public void setPROJ_Locked1(int pROJ_Locked1) {
		PROJ_Locked1 = pROJ_Locked1;
	}

	public String getPROJ_MotivationChanged() {
		return PROJ_MotivationChanged;
	}

	public void setPROJ_MotivationChanged(String pROJ_MotivationChanged) {
		PROJ_MotivationChanged = pROJ_MotivationChanged;
	}

	public String getPROJ_ContentChanged() {
		return PROJ_ContentChanged;
	}

	public void setPROJ_ContentChanged(String pROJ_ContentChanged) {
		PROJ_ContentChanged = pROJ_ContentChanged;
	}

	public String getPROJ_ResultChanged() {
		return PROJ_ResultChanged;
	}

	public void setPROJ_ResultChanged(String pROJ_ResultChanged) {
		PROJ_ResultChanged = pROJ_ResultChanged;
	}

	public int getPROJ_BudgetChanged() {
		return PROJ_BudgetChanged;
	}

	public void setPROJ_BudgetChanged(int pROJ_BudgetChanged) {
		PROJ_BudgetChanged = pROJ_BudgetChanged;
	}

	public int getPROJ_Locked2() {
		return PROJ_Locked2;
	}

	public void setPROJ_Locked2(int pROJ_Locked2) {
		PROJ_Locked2 = pROJ_Locked2;
	}

	public mStaffThread getStaff() {
		return staff;
	}

	public void setStaff(mStaffThread staff) {
		this.staff = staff;
	}

	public String getPROJ_PRJCall_Code() {
		return PROJ_PRJCall_Code;
	}

	public void setPROJ_PRJCall_Code(String pROJ_PRJCall_Code) {
		PROJ_PRJCall_Code = pROJ_PRJCall_Code;
	}

	public String getPROJ_Survey() {
		return PROJ_Survey;
	}

	public void setPROJ_Survey(String pROJ_Survey) {
		PROJ_Survey = pROJ_Survey;
	}

	public String getPROJ_Objective() {
		return PROJ_Objective;
	}

	public void setPROJ_Objective(String pROJ_Objective) {
		PROJ_Objective = pROJ_Objective;
	}

	public String getPROJ_SurveyChanged() {
		return PROJ_SurveyChanged;
	}

	public void setPROJ_SurveyChanged(String pROJ_SurveyChanged) {
		PROJ_SurveyChanged = pROJ_SurveyChanged;
	}

	public String getPROJ_ObjectiveChanged() {
		return PROJ_ObjectiveChanged;
	}

	public void setPROJ_ObjectiveChanged(String pROJ_ObjectiveChanged) {
		PROJ_ObjectiveChanged = pROJ_ObjectiveChanged;
	}

	public int getPROJ_BudgetMaterial() {
		return PROJ_BudgetMaterial;
	}

	public void setPROJ_BudgetMaterial(int pROJ_BudgetMaterial) {
		PROJ_BudgetMaterial = pROJ_BudgetMaterial;
	}

	public int getPROJ_BudgetMaterialChanged() {
		return PROJ_BudgetMaterialChanged;
	}

	public void setPROJ_BudgetMaterialChanged(int pROJ_BudgetMaterialChanged) {
		PROJ_BudgetMaterialChanged = pROJ_BudgetMaterialChanged;
	}

	public mCommentsOfSubmittedProjects getCommentsOfSubmittedProjects() {
		return commentsOfSubmittedProjects;
	}

	public void setCommentsOfSubmittedProjects(
			mCommentsOfSubmittedProjects commentsOfSubmittedProjects) {
		this.commentsOfSubmittedProjects = commentsOfSubmittedProjects;
	}

	
	public Set<xDetailCommentSubmittedProjects> getDetailCommentSubmittedProjects() {
		return detailCommentSubmittedProjects;
	}

	public void setDetailCommentSubmittedProjects(
			Set<xDetailCommentSubmittedProjects> detailCommentSubmittedProjects) {
		this.detailCommentSubmittedProjects = detailCommentSubmittedProjects;
	}
	
	
}
