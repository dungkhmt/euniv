package vn.webapp.modules.usermanagement.model;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.webapp.modules.usermanagement.model.mRole;

public class mUser implements UserDetails{
	private static final long serialVersionUID = 1L;
    private String Username;
    private String Password;
    private String Salt;
    private int Enabled;
    private String Email;
    private String User_Code;
    private String User_Role;

    /* Spring Security fields*/
    private List<mRole> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getEnabled() {
		return Enabled;
	}

	public void setEnabled(int enabled) {
		Enabled = enabled;
	}

	public String getUser_Code() {
		return User_Code;
	}

	public void setUser_Code(String user_Code) {
		User_Code = user_Code;
	}

	public String getUser_Role() {
		return User_Role;
	}

	public void setUser_Role(String user_Role) {
		User_Role = user_Role;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<mRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [Username=");
        builder.append(Username);
        builder.append(", Email=");
        builder.append(Email);
        builder.append(", Password=");
        builder.append(Password);
        builder.append(", authorities=");
        builder.append(authorities);
        builder.append(", accountNonExpired=");
        builder.append(accountNonExpired);
        builder.append(", accountNonLocked=");
        builder.append(accountNonLocked);
        builder.append(", credentialsNonExpired=");
        builder.append(credentialsNonExpired);
        builder.append(", enabled=");
        builder.append(enabled);
        builder.append("]");
        return builder.toString();
    }
}
