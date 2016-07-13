package vn.webapp.modules.researchmanagement.controller.cpservice;

public class mThreadsList {
	private String user_code;
    public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	private String projectcode;
	public String getProjectcode() {
		return projectcode;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	private String name;
    private String category;
    private String content;
    private String status;
    private String result;
    private int budget;
    private String start_date;
    private String end_date;
    private int id;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
