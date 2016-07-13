package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mPaperCategory;

@Repository("mPaperCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPaperCategoryDAOImpl extends BaseDao implements mPaperCategoryDAO {
	/**
     * Get all list Paper Category
     * @param null
     * @return List
     */
	@Override
    public List<mPaperCategory> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPaperCategory.class);
            criteria.setFirstResult(0);
            List<mPaperCategory> paperCategory = criteria.list();
            commit();
            return paperCategory;
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
    public mPaperCategory getPaperCateByCode(String paperCateCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPaperCategory.class, "paperCategory");
            criteria.add(Restrictions.eq("paperCategory.PCAT_Code", paperCateCode));
            mPaperCategory paperCategory = (mPaperCategory) criteria.uniqueResult();
            commit();
            return paperCategory;
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
