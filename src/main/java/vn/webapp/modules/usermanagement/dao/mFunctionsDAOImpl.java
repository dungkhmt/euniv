/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 13th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mFunction;

@Repository("mmFunctionnsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mFunctionsDAOImpl extends BaseDao implements mFunctionsDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get functions list
     * @param null
     * @return List
     */
    @Override
    public List<mFunction> loadFunctionsList(){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mFunction.class);
            List<mFunction> funcsList = criteria.list();
            commit();
            return funcsList;
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
     * Get functions list
     * @param null
     * @return List
     */
    @Override
    public List<mFunction> loadFunctionsParentHierachyList(){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mFunction.class);
            criteria.add(Restrictions.eq("FUNC_PARENTID", 0));
            List<mFunction> funcsList = criteria.list();
            commit();
            return funcsList;
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
     * Get functions list
     * @param null
     * @return List
     */
    @Override
    public List<mFunction> loadFunctionsChildHierachyList(){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mFunction.class);
            criteria.add(Restrictions.gt("FUNC_PARENTID", 0));
            List<mFunction> funcsList = criteria.list();
            commit();
            return funcsList;
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
