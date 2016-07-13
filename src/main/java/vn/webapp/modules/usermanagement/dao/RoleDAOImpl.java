package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.Role;
import vn.webapp.modules.usermanagement.model.mUsers;
@Repository("RoleDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleDAOImpl extends BaseDao implements RoleDAO {

	@Override
	public List<Role> list() {
		try {
            begin();
            Criteria criteria = getSession().createCriteria(Role.class);
            List<Role> roles = criteria.list();
            commit();
            return roles;
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
