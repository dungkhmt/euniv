/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;
import vn.webapp.modules.usermanagement.model.mFunction;

public interface mFunctionsDAO {
	
	/**
	 * 
	 * @return
	 */
	public List<mFunction> loadFunctionsList();
	
	public List<mFunction> loadFunctionsParentHierachyList();
	
	public List<mFunction> loadFunctionsChildHierachyList();
	
}
