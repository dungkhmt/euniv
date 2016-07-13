package vn.webapp.modules.researchmanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author incre
 *
 */
@Entity
@Table(name = "tblprojectparticipationroles")
public class ProjectParticipationRoles implements Serializable{
	@Id
    @GeneratedValue
    private int PROJPARTIROLE_ID;
    private String PROJPARTIROLE_Code;
    private String PROJPARTIROLE_Description;
    
	public int getPROJPARTIROLE_ID() {
		return PROJPARTIROLE_ID;
	}
	public void setPROJPARTIROLE_ID(int pROJPARTIROLE_ID) {
		PROJPARTIROLE_ID = pROJPARTIROLE_ID;
	}
	public String getPROJPARTIROLE_Code() {
		return PROJPARTIROLE_Code;
	}
	public void setPROJPARTIROLE_Code(String pROJPARTIROLE_Code) {
		PROJPARTIROLE_Code = pROJPARTIROLE_Code;
	}
	public String getPROJPARTIROLE_Description() {
		return PROJPARTIROLE_Description;
	}
	public void setPROJPARTIROLE_Description(String pROJPARTIROLE_Description) {
		PROJPARTIROLE_Description = pROJPARTIROLE_Description;
	}
    
}
