package vn.webapp.modules.researchdeclarationmanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblprojectcategory")
public class mTopicCategory implements Serializable{
	@Id
    @GeneratedValue
    private int PROJCAT_ID;
    private String PROJCAT_Name;
    private String PROJCAT_Description;
    private String PROJCAT_Code;
    
    @OneToMany(mappedBy="topicCategory")
    private Set<mTopics> topics;
	public int getPROJCAT_ID() {
		return PROJCAT_ID;
	}

	public void setPROJCAT_ID(int pROJCAT_ID) {
		PROJCAT_ID = pROJCAT_ID;
	}

	public String getPROJCAT_Name() {
		return PROJCAT_Name;
	}

	public void setPROJCAT_Name(String pROJCAT_Name) {
		PROJCAT_Name = pROJCAT_Name;
	}

	public String getPROJCAT_Description() {
		return PROJCAT_Description;
	}

	public void setPROJCAT_Description(String pROJCAT_Description) {
		PROJCAT_Description = pROJCAT_Description;
	}

	public String getPROJCAT_Code() {
		return PROJCAT_Code;
	}

	public void setPROJCAT_Code(String pROJCAT_Code) {
		PROJCAT_Code = pROJCAT_Code;
	}

	public Set<mTopics> getTopics() {
		return topics;
	}

	public void setTopics(Set<mTopics> topics) {
		this.topics = topics;
	}
}
