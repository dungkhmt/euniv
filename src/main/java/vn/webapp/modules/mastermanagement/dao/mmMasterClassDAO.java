package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmMasterClass;

public interface mmMasterClassDAO {

	public List<mmMasterClass> listMasterClass();
	
	public mmMasterClass loadMasterClassByCode(String masterClassCode);
	
}
