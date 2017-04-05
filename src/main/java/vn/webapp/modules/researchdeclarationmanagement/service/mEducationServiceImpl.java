package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mEducationsDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;

@Service("mEducationService")
public class mEducationServiceImpl implements mEducationService {
	
	@Autowired
	mEducationsDAO mEducationsDAO;

	@Override
	public List<mEducations> getList() {
		return mEducationsDAO.getList();
	}

	@Override
	public List<mEducations> getListByField(String[] field, String[] value) {
		return mEducationsDAO.getListByField(field, value);
	}

	@Override
	public Boolean deleteEducation(int EDU_ID) {
		return mEducationsDAO.deleteEducation(EDU_ID);
	}

	@Override
	public mEducations addEducation(mEducations newEducation) {
		return mEducationsDAO.addEducation(newEducation);
	}

	@Override
	public Boolean changeEducation(int EDU_ID, String EDU_Level,
			String EDU_Institution, String EDU_Major, String EDU_CompleteDate) {
		return mEducationsDAO.changeEducation(EDU_ID, EDU_Level, EDU_Institution, EDU_Major, EDU_CompleteDate);
	}
	
}