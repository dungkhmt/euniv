package vn.webapp.modules.mastermanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbluserroles")
public class mmUserRoles implements Serializable{
	@Id
    @GeneratedValue
    private int UserRole_ID;
    private String Username;
    private String Role;
    
	public int getUserRole_ID() {
		return UserRole_ID;
	}
	public void setUserRole_ID(int userRole_ID) {
		UserRole_ID = userRole_ID;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
}
