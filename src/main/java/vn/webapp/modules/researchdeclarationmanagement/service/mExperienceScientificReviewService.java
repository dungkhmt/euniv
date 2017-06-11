package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mExperienceScientificReview;

public interface mExperienceScientificReviewService {

	public List<mExperienceScientificReview> getList();
	public List<mExperienceScientificReview> getListByField(List<field> fields);
	public mExperienceScientificReview addSupervision(mExperienceScientificReview newExperienceScientificReview);
	public Boolean deleteExperienceScientificReview(int ESV_ID);
	public Boolean changeExperienceScientificReview(int ESV_ID,
			String ESV_Name, int ESV_NumberTimes);
}
