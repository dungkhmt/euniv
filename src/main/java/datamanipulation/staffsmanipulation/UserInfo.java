package datamanipulation.staffsmanipulation;

public class UserInfo {
	public int ID;
	public String Username;
	public String Password;
	public String Salt;
	public String email;
	public String Enabled;
	public String UserCode;
	
	public UserInfo(int ID, String Username, String Password, String Salt, String email, String Enabled, String UserCode){
		this.ID = ID;
		this.Username = Username;
		this.Password = Password;
		this.Salt = Salt;
		this.email = email;
		this.Enabled = Enabled;
		this.UserCode = UserCode;
	}
}	
