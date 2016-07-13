package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;

@Repository("tProjectCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class tProjectCategoryDAOImpl extends BaseDao implements tProjectCategoryDAO {
	/**
     * Get all list Topic Category
     * @param null
     * @return List
     */
	@Override
	public List<mTopicCategory> getList() {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mTopicCategory.class);
            criteria.setFirstResult(0);
            List<mTopicCategory> topicCategory = criteria.list();
            commit();
            return topicCategory;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
	/**
     * Get a paper category by code
     * @param String
     * @return object
     */
	@Override
	public mTopicCategory getTopicCategoryByCode(String topicCategoryCode) {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mTopicCategory.class, "topicCategory");
            criteria.add(Restrictions.eq("topicCategory.PROJCAT_Code", topicCategoryCode));
            mTopicCategory topicCategory = (mTopicCategory) criteria.uniqueResult();
            commit();
            return topicCategory;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
}
