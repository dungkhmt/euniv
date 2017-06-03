package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mSupervisionDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

@Service("mSupervisionServiceImpla")
public class mSupervisionServiceImpl implements mSupervisionService {
	@Autowired
	mSupervisionDAO mSupervisonDAO;
	
	@Override
	public List<mSupervision> getList() {
		return mSupervisonDAO.getList();
	}

	@Override
	public List<mSupervision> getListByField(List<field> fields) {
		return mSupervisonDAO.getListByField(fields);
	}

	@Override
	public mSupervision addSupervision(mSupervision newSupervision) {
		return mSupervisonDAO.addSupervision(newSupervision);
	}

	@Override
	public Boolean deleteSupervision(int SUP_ID) {
		return mSupervisonDAO.deleteSupervision(SUP_ID);
	}

	@Override
	public Boolean changeSupervision(int SUP_ID,
			String SUP_StudentName, String SUP_Cosupervision,
			String SUP_Institution, String SUP_ThesisTitle,
			String SUP_SpecializationCode, String SUP_TraingPeriod,
			String SUP_DefensedDate) {
		return mSupervisonDAO.changeSupervision(SUP_ID, SUP_StudentName, SUP_Cosupervision, SUP_Institution, SUP_ThesisTitle, SUP_SpecializationCode, SUP_TraingPeriod, SUP_DefensedDate);
	}
	
}