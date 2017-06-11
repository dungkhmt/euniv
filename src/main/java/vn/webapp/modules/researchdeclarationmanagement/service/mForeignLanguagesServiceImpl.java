package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mForeignLanguagesDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mForeignLanguages;

@Service("mForeignLanguagesService")
public class mForeignLanguagesServiceImpl implements mForeignLanguagesService {
	
	@Autowired
	mForeignLanguagesDAO mForeignLanguagesDAO;

	@Override
	public List<mForeignLanguages> getList() {
		return mForeignLanguagesDAO.getList();
	}

	@Override
	public List<mForeignLanguages> getListByField(List<field> fields) {
		return mForeignLanguagesDAO.getListByField(fields);
	}

	@Override
	public Boolean deleteForeignLanguages(int LG_ID) {
		return mForeignLanguagesDAO.deleteForeignLanguages(LG_ID);
	}

	@Override
	public mForeignLanguages addForeignLanguages(mForeignLanguages newForeignLanguages) {
		return mForeignLanguagesDAO.addForeignLanguages(newForeignLanguages);
	}

	@Override
	public Boolean changeForeignLanguages(int LG_ID, String LG_Name, String LG_Listen, 
			String LG_Speak, String LG_Reading, String LG_Writing) {
		return mForeignLanguagesDAO.changeForeignLanguages(LG_ID, LG_Name, LG_Listen, LG_Speak, LG_Reading, LG_Writing);
	}
	
}