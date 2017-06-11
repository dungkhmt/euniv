package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;

public interface mEducationService {
	
	public List<mEducations>  getList();
	public List<mEducations>  getListByField(List<field> fields);
	public Boolean deleteEducation(int EDU_ID);
	public mEducations addEducation(mEducations newEducation);
	public Boolean changeEducation(int EDU_ID, String EDU_Level, String EDU_Institution, String EDU_Major, String EDU_CompleteDate);


}
