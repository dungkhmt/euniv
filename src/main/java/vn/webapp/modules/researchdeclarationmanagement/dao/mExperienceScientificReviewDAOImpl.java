package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;
import vn.webapp.modules.researchdeclarationmanagement.model.mExperienceScientificReview;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

@Repository("mExperienceScientificReviewDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mExperienceScientificReviewDAOImpl extends BaseDao implements mExperienceScientificReviewDAO {

	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mExperienceScientificReviewDAOImpl";
    }
	
	@Override
	public List<mExperienceScientificReview> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mExperienceScientificReview.class);
			
			List<mExperienceScientificReview> list = criteria.list();
			commit();
			return list;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public List<mExperienceScientificReview> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mExperienceScientificReview.class);
			
			for(field field: fields) {
				Junction conditionGroup = Restrictions.disjunction();
				List<String> values = field.getValues();
				Type typeField = mExperienceScientificReview.class.getDeclaredField(field.getFieldName()).getGenericType();
				for(String value: values) {
					if(typeField.toString().equals("int")) {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), Integer.valueOf(value)));
					} else {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
					}
					
				}
				criteria.add(conditionGroup);
			}
			
			List<mExperienceScientificReview> list= criteria.list();
			commit();
			return list;
		} catch (Exception e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	}

	@Override
	public Boolean deleteExperienceScientificReview(int ESV_ID) {
		mExperienceScientificReview experienceScientificReview = new mExperienceScientificReview();
		experienceScientificReview.setESV_ID(ESV_ID);
		try {
			begin();
			getSession().delete(experienceScientificReview);
			commit();
			
			return true;
			
			} catch (Exception e) {
			e.printStackTrace();
			rollback();
			close();
		}
		return null;
	}

	@Override
	public mExperienceScientificReview addExperienceScientificReview(
			mExperienceScientificReview newExperienceScientificReview) {
		try {
			begin();
			int id = 0;
			id = (Integer)getSession().save(newExperienceScientificReview);
			commit();
			newExperienceScientificReview.setESV_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newExperienceScientificReview.setESV_CODE("ESV"+nf.format(id));
			return newExperienceScientificReview;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			close();
		}
		return null;
	}

	@Override
	public Boolean changeExperienceScientificReview(int ESV_ID,
			String ESV_Name, int ESV_NumberTimes) {
		try {
			begin();
			mExperienceScientificReview experienceScientificReview = (mExperienceScientificReview) getSession().get(mExperienceScientificReview.class, ESV_ID);
			experienceScientificReview.setESV_ID(ESV_ID);
			experienceScientificReview.setESV_Name(ESV_Name);
			experienceScientificReview.setESV_NumberTimes(ESV_NumberTimes);
			getSession().update(experienceScientificReview);
		   commit();
		   return true;
		} catch (Exception e) {
			e.printStackTrace();
            rollback();
            close();
            return false;
		}
	}
	
}
