package vn.webapp.modules.mastermanagement.service;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmMasterClass;


public interface mmMasterClassService {
	
	  public List<mmMasterClass> loadMasterClassList();
	  
	  public mmMasterClass loadMasterClassByCode(String masterClassesCode);

}
