package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mAppliedProjects;

public interface mAppliedProjectsDAO {
	public List<mAppliedProjects>  getList();
	public List<mAppliedProjects>  getListByField(List<field> fields);
	public Boolean deleteAppliedProjects(int AP_ID);
	public mAppliedProjects addAppliedProjects(mAppliedProjects newAppliedProjects);
	public Boolean changeAppliedProjects(int AP_ID, String AP_Name, String AP_Scope, String AP_Date);

}
