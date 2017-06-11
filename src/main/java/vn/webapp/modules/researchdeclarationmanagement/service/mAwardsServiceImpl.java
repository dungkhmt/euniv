package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mAwardsDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mAwards;

@Service("mAwardsService")
public class mAwardsServiceImpl implements mAwardsService {
	
	@Autowired
	mAwardsDAO mAwardsDAO;

	@Override
	public List<mAwards> getList() {
		return mAwardsDAO.getList();
	}

	@Override
	public List<mAwards> getListByField(List<field> fields) {
		return mAwardsDAO.getListByField(fields);
	}

	@Override
	public Boolean deleteAwards(int AW_ID) {
		return mAwardsDAO.deleteAwards(AW_ID);
	}

	@Override
	public mAwards addAwards(mAwards newAwards) {
		return mAwardsDAO.addAwards(newAwards);
	}

	@Override
	public Boolean changeAwards(int AW_ID, String AW_Name, String AW_Date) {
		return mAwardsDAO.changeAwards(AW_ID, AW_Name, AW_Date);
	}
	
}