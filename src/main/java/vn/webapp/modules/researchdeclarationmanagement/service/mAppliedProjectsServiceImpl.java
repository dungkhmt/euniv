package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mAppliedProjectsDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mAppliedProjects;

@Service("mAppliedProjectsService")
public class mAppliedProjectsServiceImpl implements mAppliedProjectsService {
	
	@Autowired
	mAppliedProjectsDAO mAppliedProjectsDAO;

	@Override
	public List<mAppliedProjects> getList() {
		return mAppliedProjectsDAO.getList();
	}

	@Override
	public List<mAppliedProjects> getListByField(List<field> fields) {
		return mAppliedProjectsDAO.getListByField(fields);
	}

	@Override
	public Boolean deleteAppliedProjects(int AP_ID) {
		return mAppliedProjectsDAO.deleteAppliedProjects(AP_ID);
	}

	@Override
	public mAppliedProjects addAppliedProjects(mAppliedProjects newAppliedProjects) {
		return mAppliedProjectsDAO.addAppliedProjects(newAppliedProjects);
	}

	@Override
	public Boolean changeAppliedProjects(int AP_ID, String AP_Name,
			String AP_Scope, String AP_Date) {
		return mAppliedProjectsDAO.changeAppliedProjects(AP_ID, AP_Name, AP_Scope, AP_Date);
	}
	
}