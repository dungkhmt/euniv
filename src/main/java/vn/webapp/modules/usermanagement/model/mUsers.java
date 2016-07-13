package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblusers")
public class mUsers implements Serializable {
	@Id
    @GeneratedValue
    private int User_ID;
    private String Username;
    private String Password;
    private String Salt;
    private int Enabled;
    private String Email;
    private String User_Code;

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSalt() {
		return Salt;
	}

	public void setSalt(String salt) {
		Salt = salt;
	}

	public int getEnabled() {
		return Enabled;
	}

	public void setEnabled(int enabled) {
		Enabled = enabled;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUser_Code() {
		return User_Code;
	}

	public void setUser_Code(String user_Code) {
		User_Code = user_Code;
	}
}
