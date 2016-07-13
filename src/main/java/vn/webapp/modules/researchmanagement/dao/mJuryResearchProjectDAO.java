package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchmanagement.model.mJuryResearchProject;

public interface mJuryResearchProjectDAO {
	public int removeAJuryResearchProject(mJuryResearchProject jury);
	public List<mJuryResearchProject> listAllJuries();
	public List<mJuryResearchProject> listAllJuriesByUserCode(String userCode);
	public mJuryResearchProject listAJuryByID(int id);
	public mJuryResearchProject listAJuryByCode(String juryCode);
	public int saveAJury(mJuryResearchProject jury);
	public void editAJury(mJuryResearchProject jury);
	
}
