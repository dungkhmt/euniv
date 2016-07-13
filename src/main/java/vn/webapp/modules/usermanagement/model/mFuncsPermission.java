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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbluserfunctions")
public class mFuncsPermission implements Serializable{
	@Id
    @GeneratedValue
    private int USERFUNC_ID;
    private String USERFUNC_CODE;
    private String USERFUNC_USERCODE;
    private String USERFUNC_FUNCCODE;
    
    @OneToOne
    @JoinColumn(name = "USERFUNC_FUNCCODE", referencedColumnName = "FUNC_CODE",insertable = false, updatable = false)
    private mFunction oFunctions;

	public int getUSERFUNC_ID() {
		return USERFUNC_ID;
	}

	public void setUSERFUNC_ID(int uSERFUNC_ID) {
		USERFUNC_ID = uSERFUNC_ID;
	}

	public String getUSERFUNC_CODE() {
		return USERFUNC_CODE;
	}

	public void setUSERFUNC_CODE(String uSERFUNC_CODE) {
		USERFUNC_CODE = uSERFUNC_CODE;
	}

	public String getUSERFUNC_USERCODE() {
		return USERFUNC_USERCODE;
	}

	public void setUSERFUNC_USERCODE(String uSERFUNC_USERCODE) {
		USERFUNC_USERCODE = uSERFUNC_USERCODE;
	}

	public String getUSERFUNC_FUNCCODE() {
		return USERFUNC_FUNCCODE;
	}

	public void setUSERFUNC_FUNCCODE(String uSERFUNC_FUNCCODE) {
		USERFUNC_FUNCCODE = uSERFUNC_FUNCCODE;
	}

	public mFunction getoFunctions() {
		return oFunctions;
	}

	public void setoFunctions(mFunction oFunctions) {
		this.oFunctions = oFunctions;
	}

    
	
}
