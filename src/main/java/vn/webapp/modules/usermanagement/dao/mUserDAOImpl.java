/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mUserRoles;
import vn.webapp.modules.usermanagement.model.mUsers;

@Repository("mUserDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class mUserDAOImpl extends BaseDao implements mUserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get an user by username
	 * 
	 * @param username
	 * @return object
	 */
	@Override
	public mUsers getByUsername(String userName) {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class);
			Criteria criteria = getSession().createCriteria(mUsers.class);
			criteria.add(Restrictions.eq("Username", userName));
			mUsers user = (mUsers) criteria.uniqueResult();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get an user by username and id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	@Override
	public mUsers getByUsernameAndId(String userName, int id) {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class);
			Criteria criteria = getSession().createCriteria(mUsers.class);
			criteria.add(Restrictions.eq("Username", userName));
			criteria.add(Restrictions.ne("User_ID", id));
			mUsers user = (mUsers) criteria.uniqueResult();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get a list all users
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mUsers> list() {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class);
			Criteria criteria = getSession().createCriteria(mUsers.class);
			criteria.setFirstResult(0);
			criteria.addOrder(Order.desc("User_ID"));
			List<mUsers> users = criteria.list();
			return users;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get an user by id
	 * 
	 * @param int
	 * @return object
	 */
	@Override
	public mUsers viewDetail(int id) {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class, "user");
			Criteria criteria = getSession().createCriteria(mUsers.class, "user");
			criteria.add(Restrictions.eq("user.User_ID", id));
			mUsers user = (mUsers) criteria.uniqueResult();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get an user role by username
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public mUserRoles viewDetailUserRole(String userName) {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUserRoles.class, "userRole");
			Criteria criteria = getSession().createCriteria(mUserRoles.class, "userRole");
			criteria.add(Restrictions.eq("userRole.Username", userName));
			mUserRoles userRole = (mUserRoles) criteria.uniqueResult();
			return userRole;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get an user role by username
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public mUserRoles viewDetailUserRoleId(int userRoleId) {
		try {
			//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUserRoles.class, "userRole");
			Criteria criteria = getSession().createCriteria(mUserRoles.class, "userRole");
			criteria.add(Restrictions.eq("userRole.UserRole_ID", userRoleId));
			mUserRoles userRole = (mUserRoles) criteria.uniqueResult();
			return userRole;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Remove an user by id
	 * 
	 * @param String
	 * @return int
	 */
	@Override
	public int removeAnUser(String loginUserRole, int id) {
		mUsers user = new mUsers();
		user.setUser_ID(id);
		try {
			if ("ROLE_ADMIN".equals(loginUserRole)
					|| "SUPER_ADMIN".equals(loginUserRole)) {
				//sessionFactory.getCurrentSession().delete(user);
				getSession().delete(user);
				return 1;
			}
			return 0;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	     * 
	     */
	@Override
	public int removeAnUserRole(mUserRoles userRole) {
		try {
			//sessionFactory.getCurrentSession().delete(userRole);
			getSession().delete(userRole);
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Save an user
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAUser(mUsers user) {
		try {
			//int id = (int) sessionFactory.getCurrentSession().save(user);
			int id = (int)getSession().save(user);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Save an userrole
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAUserRole(mUserRoles userRole) {
		try {
			//int id = (int) sessionFactory.getCurrentSession().save(userRole);
			int id = (int) getSession().save(userRole);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Edit an user
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public void editAnUser(mUsers user) {
		try {
			//sessionFactory.getCurrentSession().update(user);
			getSession().update(user);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edit an user role
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public void editAnUserRole(mUserRoles userRole) {
		try {
            //sessionFactory.getCurrentSession().update(userRole);
			getSession().update(userRole);
         } catch (HibernateException e) {
             e.printStackTrace();	            
         }
	}

	/**
	 * Get an user by id
	 * 
	 * @param username
	 * @return object
	 */
	@Override
	public mUsers loadUserById(int userId) {
		try {
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class);
			Criteria criteria = getSession().createCriteria(mUsers.class);
            criteria.add(Restrictions.eq("User_ID", userId));
            mUsers user = (mUsers) criteria.uniqueResult();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
	}

	/**
	 * Count number of users
	 * 
	 * @param null
	 * @return int
	 */
	@Override
	public int count() {
		try {
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mUsers.class);
			Criteria criteria = getSession().createCriteria(mUsers.class);
            List<mUsers> users = criteria.list();
            return users.size();
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        }
	}
}
