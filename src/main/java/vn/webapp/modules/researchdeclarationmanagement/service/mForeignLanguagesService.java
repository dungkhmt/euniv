package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mForeignLanguages;

public interface mForeignLanguagesService {
	
	public List<mForeignLanguages>  getList();
	public List<mForeignLanguages>  getListByField(List<field> fields);
	public Boolean deleteForeignLanguages(int LG_ID);
	public mForeignLanguages  addForeignLanguages(mForeignLanguages newForeignLanguages);
	public Boolean changeForeignLanguages(int LG_ID, String LG_Name, String LG_Listen, String LG_Speak, String LG_Reading, String LG_Writing);


}


