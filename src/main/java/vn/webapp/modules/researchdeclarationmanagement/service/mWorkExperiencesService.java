package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mWorkExperiences;

public interface mWorkExperiencesService {
	
	public List<mWorkExperiences>  getList();
	public List<mWorkExperiences>  getListByField(List<field> fields);
	public Boolean deleteWorkExperiences(int WE_ID);
	public mWorkExperiences addWorkExperiences(mWorkExperiences newWorkExperiences);
	public Boolean changeWorkExperiences(int WE_ID, String WE_Position, String WE_Domain, String WE_Institution);


}
