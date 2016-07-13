package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblroles")

public class Role implements Serializable {
	@Id
    @GeneratedValue
    private int ROLE_ID;
	private String ROLE_CODE;
	private String ROLE_NAME;
	private String ROLE_DESCRIPTION;
	public int getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(int rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getROLE_CODE() {
		return ROLE_CODE;
	}
	public void setROLE_CODE(String rOLE_CODE) {
		ROLE_CODE = rOLE_CODE;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public String getROLE_DESCRIPTION() {
		return ROLE_DESCRIPTION;
	}
	public void setROLE_DESCRIPTION(String rOLE_DESCRIPTION) {
		ROLE_DESCRIPTION = rOLE_DESCRIPTION;
	}
	
}
