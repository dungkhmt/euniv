package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

public interface mSupervisionService {
	public List<mSupervision> getList();
	public List<mSupervision> getListByField(List<field> fields);
	public mSupervision addSupervision(mSupervision newSupervision);
	public Boolean deleteSupervision(int SUP_ID);
	public Boolean changeSupervision(int SUP_ID,
			String SUP_StudentName, String SUP_Cosupervision,
			String SUP_Institution, String SUP_ThesisTitle,
			String SUP_SpecializationCode, String SUP_TraingPeriod,
			String SUP_DefensedDate);
	
}