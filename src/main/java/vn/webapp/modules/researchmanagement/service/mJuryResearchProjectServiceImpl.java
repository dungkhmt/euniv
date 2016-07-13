package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.dao.mJuryResearchProjectDAO;
import vn.webapp.modules.researchmanagement.model.mJuryResearchProject;

@Service("mJuryResearchProjectService")
public class mJuryResearchProjectServiceImpl implements
		mJuryResearchProjectService {

	@Autowired
	private mJuryResearchProjectDAO juryResearchProjectDAO;
	
	public mJuryResearchProject listAJuryByCode(String juryCode){
		return juryResearchProjectDAO.listAJuryByCode(juryCode);
	}
	
	public mJuryResearchProject listAJuryByID(int id){
		return juryResearchProjectDAO.listAJuryByID(id);
	}
	public int removeAJuryResearchProject(mJuryResearchProject jury){
		return juryResearchProjectDAO.removeAJuryResearchProject(jury);
	}
	public List<mJuryResearchProject> listAllJuriesByUserCode(String userCode){
		return juryResearchProjectDAO.listAllJuriesByUserCode(userCode);
	}
	@Override
	public List<mJuryResearchProject> listAllJuries() {
		// TODO Auto-generated method stub
		return juryResearchProjectDAO.listAllJuries();
	}

	@Override
	public int saveAJury(mJuryResearchProject jury) {
		// TODO Auto-generated method stub
		return juryResearchProjectDAO.saveAJury(jury);
	}

	@Override
	public void editAJury(mJuryResearchProject jury) {
		// TODO Auto-generated method stub
		juryResearchProjectDAO.editAJury(jury);
	}

}
