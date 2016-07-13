package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatentCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

@Repository("mPatentCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPatentCategoryDAOImpl extends BaseDao implements mPatentCategoryDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     */
    @Override
    public List<mPatentCategory> loadPatentCategoryList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatentCategory.class, "patentCategory");
            criteria.addOrder(Order.desc("patentCategory.PATCAT_ID"));
            List<mPatentCategory> patentCates = criteria.list();
            commit();
            return patentCates;
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
     * @param null
     * @return List
     */
    @Override
    public mPatentCategory loadPatentCategoryListByCode(String patentCateCode){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatentCategory.class, "patentCategory");
            criteria.add(Restrictions.eq("patentCategory.PATCAT_Code", patentCateCode));
            criteria.addOrder(Order.desc("patentCategory.PATCAT_ID"));
            mPatentCategory patentCates = (mPatentCategory) criteria.uniqueResult();
            commit();
            return patentCates;
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
     * @param object
     * @return int
     */
    @Override
    public mPatentCategory loadAPatentCategoryById(int patentCateId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatentCategory.class);
            criteria.add(Restrictions.eq("PATCAT_ID", patentCateId));
            mPatentCategory patentCategory = (mPatentCategory) criteria.uniqueResult();
            commit();
            return patentCategory;
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
