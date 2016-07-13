/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblfunctions")
public class mFunction implements Serializable{
	
	@Id
    @GeneratedValue
    private int FUNC_ID;
    private String FUNC_CODE;
    private String FUNC_NAME;
    private String FUNC_URL;
    private int FUNC_PARENTID;
    private String FUNC_TITLE_CLASS;
    private String FUNC_SELECTED_CLASS;
    private int FUNC_HAS_CHILDREN;
    
	public int getFUNC_ID() {
		return FUNC_ID;
	}
	public void setFUNC_ID(int fUNC_ID) {
		FUNC_ID = fUNC_ID;
	}
	public String getFUNC_CODE() {
		return FUNC_CODE;
	}
	public void setFUNC_CODE(String fUNC_CODE) {
		FUNC_CODE = fUNC_CODE;
	}
	public String getFUNC_NAME() {
		return FUNC_NAME;
	}
	public void setFUNC_NAME(String fUNC_NAME) {
		FUNC_NAME = fUNC_NAME;
	}
	public String getFUNC_URL() {
		return FUNC_URL;
	}
	public void setFUNC_URL(String fUNC_URL) {
		FUNC_URL = fUNC_URL;
	}
	public int getFUNC_PARENTID() {
		return FUNC_PARENTID;
	}
	public void setFUNC_PARENTID(int fUNC_PARENTID) {
		FUNC_PARENTID = fUNC_PARENTID;
	}
	public String getFUNC_TITLE_CLASS() {
		return FUNC_TITLE_CLASS;
	}
	public void setFUNC_TITLE_CLASS(String fUNC_TITLE_CLASS) {
		FUNC_TITLE_CLASS = fUNC_TITLE_CLASS;
	}
	public String getFUNC_SELECTED_CLASS() {
		return FUNC_SELECTED_CLASS;
	}
	public void setFUNC_SELECTED_CLASS(String fUNC_SELECTED_CLASS) {
		FUNC_SELECTED_CLASS = fUNC_SELECTED_CLASS;
	}
	public int getFUNC_HAS_CHILDREN() {
		return FUNC_HAS_CHILDREN;
	}
	public void setFUNC_HAS_CHILDREN(int fUNC_HAS_CHILDREN) {
		FUNC_HAS_CHILDREN = fUNC_HAS_CHILDREN;
	}
	
}
