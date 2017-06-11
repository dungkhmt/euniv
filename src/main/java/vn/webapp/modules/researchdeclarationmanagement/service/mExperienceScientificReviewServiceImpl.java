package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mExperienceScientificReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.mExperienceScientificReviewDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.field;

@Service("mExperienceScientificReviewService")
public class mExperienceScientificReviewServiceImpl implements mExperienceScientificReviewService{

	@Autowired
	mExperienceScientificReviewDAO mExperienceScientificReviewDAO;

	@Override
	public List<mExperienceScientificReview> getList() {
		return mExperienceScientificReviewDAO.getList();
	}

	@Override
	public List<mExperienceScientificReview> getListByField(
			List<field> fields) {
		return mExperienceScientificReviewDAO.getListByField(fields);
	}

	@Override
	public mExperienceScientificReview addSupervision(
			mExperienceScientificReview newExperienceScientificReview) {
		return mExperienceScientificReviewDAO.addExperienceScientificReview(newExperienceScientificReview);
	}

	@Override
	public Boolean deleteExperienceScientificReview(int ESV_ID) {
		return mExperienceScientificReviewDAO.deleteExperienceScientificReview(ESV_ID);
	}

	@Override
	public Boolean changeExperienceScientificReview(int ESV_ID,
			String ESV_Name, int ESV_NumberTimes) {
		return mExperienceScientificReviewDAO.changeExperienceScientificReview(ESV_ID, ESV_Name, ESV_NumberTimes);
	}
	
	
	
}
