package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

public interface mSupervisionDAO {
	public List<mSupervision> getList();

	
	public List<mSupervision> getListByField(List<field> fields);

	public Boolean deleteSupervision(int SUP_ID);

	public mSupervision addSupervision(mSupervision newSupervision);

	public Boolean changeSupervision(int SUP_ID,
			String SUP_StudentName, String SUP_Cosupervision,
			String SUP_Institution, String SUP_ThesisTitle,
			String SUP_SpecializationCode, String SUP_TraingPeriod,
			String SUP_DefensedDate);
}
