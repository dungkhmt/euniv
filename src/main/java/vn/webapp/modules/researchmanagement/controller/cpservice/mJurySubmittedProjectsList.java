package vn.webapp.modules.researchmanagement.controller.cpservice;

public class mJurySubmittedProjectsList {
	private String projectcall_name;
    private String member_name;
    private String role;
    private int id;
    
	public String getProjectcall_name() {
		return projectcall_name;
	}
	public void setProjectcall_name(String projectcall_name) {
		this.projectcall_name = projectcall_name;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
