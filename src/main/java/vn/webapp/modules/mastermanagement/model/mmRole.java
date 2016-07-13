/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.model;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @since 10:13:48 AM
 */
public class mmRole implements GrantedAuthority{
    private static final long serialVersionUID = 1L;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [name=");
		builder.append(name);	
		builder.append("]");
		return builder.toString();
	}
}
