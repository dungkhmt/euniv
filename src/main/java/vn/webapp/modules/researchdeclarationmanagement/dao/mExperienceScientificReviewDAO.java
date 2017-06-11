package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mExperienceScientificReview;

public interface mExperienceScientificReviewDAO {
public List<mExperienceScientificReview> getList();

	
	public List<mExperienceScientificReview> getListByField(List<field> fields);

	public Boolean deleteExperienceScientificReview(int ESV_ID);

	public mExperienceScientificReview addExperienceScientificReview(mExperienceScientificReview newExperienceScientificReview);

	public Boolean changeExperienceScientificReview(int ESV_ID,
			String ESV_Name, int ESV_NumberTimes);
}
