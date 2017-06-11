package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mWorkExperiencesDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mWorkExperiences;

@Service("mWorkExperiencesService")
public class mWorkExperiencesServiceImpl implements mWorkExperiencesService {
	
	@Autowired
	mWorkExperiencesDAO mWorkExperiencesDAO;

	@Override
	public List<mWorkExperiences> getList() {
		return mWorkExperiencesDAO.getList();
	}

	@Override
	public List<mWorkExperiences> getListByField(List<field> fields) {
		return mWorkExperiencesDAO.getListByField(fields);
	}

	@Override
	public Boolean deleteWorkExperiences(int WE_ID) {
		return mWorkExperiencesDAO.deleteWorkExperiences(WE_ID);
	}

	@Override
	public mWorkExperiences addWorkExperiences(mWorkExperiences newWorkExperiences) {
		return mWorkExperiencesDAO.addWorkExperiences(newWorkExperiences);
	}

	@Override
	public Boolean changeWorkExperiences(int WE_ID, String WE_Position,
				String WE_Domain, String WE_Institution) {
		return mWorkExperiencesDAO.changeWorkExperiences(WE_ID, WE_Position, WE_Domain, WE_Institution);
	}
	
}