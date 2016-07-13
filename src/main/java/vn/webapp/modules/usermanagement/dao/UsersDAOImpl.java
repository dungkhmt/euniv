package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mUsers;

@Repository("myusersDAO")
public class UsersDAOImpl extends BaseDao implements UsersDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Get a list all users
     * @param null
     * @return List
     */
    @Override
    public List<mUsers> list() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mUsers.class);
            List<mUsers> users = criteria.list();
            commit();
            return users;
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
