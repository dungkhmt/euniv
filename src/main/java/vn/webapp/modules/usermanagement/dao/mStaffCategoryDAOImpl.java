package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mStaffCategory;

@Repository("mStaffCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mStaffCategoryDAOImpl extends BaseDao implements mStaffCategoryDAO{
	
	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
	@Override
    public List<mStaffCategory> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mStaffCategory.class);
            criteria.setFirstResult(0);
            List<mStaffCategory> staffCategory = criteria.list();
            commit();
            return staffCategory;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
}
