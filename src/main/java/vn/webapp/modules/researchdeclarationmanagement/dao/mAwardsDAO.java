package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mAwards;

public interface mAwardsDAO {
	public List<mAwards>  getList();
	public List<mAwards>  getListByField(List<field> fields);
	public Boolean deleteAwards(int AW_ID);
	public mAwards  addAwards(mAwards newAwards);
	public Boolean changeAwards(int AW_ID, String AW_Name, String AW_Date);

}
