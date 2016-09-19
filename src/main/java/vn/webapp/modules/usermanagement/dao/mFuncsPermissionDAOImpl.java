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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mFuncsPermission;
import vn.webapp.modules.usermanagement.model.mUsers;

@Repository("mmFuncsPermissionDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class mFuncsPermissionDAOImpl extends BaseDao implements
		mFuncsPermissionDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get permissions by user code
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mFuncsPermission> loadFunctionsPermissionByUserList(
			String sUserCode) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(mFuncsPermission.class);
			criteria.add(Restrictions.eq("USERFUNC_USERCODE", sUserCode));
			criteria.addOrder(Order.asc("USERFUNC_ID"));
			List<mFuncsPermission> funcsPermission = criteria.list();
			return funcsPermission;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get permissions by user code and function code
	 * 
	 * @param null
	 * @return object
	 */
	@Override
	public mFuncsPermission loadFunctionsPermissionByCodeAndUser(
			String sFunctionCode, String sUserCode) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(mFuncsPermission.class);
			criteria.add(Restrictions.eq("USERFUNC_USERCODE", sUserCode));
			criteria.add(Restrictions.eq("USERFUNC_FUNCCODE", sFunctionCode));
			mFuncsPermission funcsPermission = (mFuncsPermission) criteria
					.uniqueResult();
			return funcsPermission;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Save a function
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAFunction(mFuncsPermission oFunctions) {
		try {
			int id = (int) sessionFactory.getCurrentSession().save(oFunctions);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 */
	@Override
	public int removeAFunction(mFuncsPermission oFunctions) {
		try {
			sessionFactory.getCurrentSession().delete(oFunctions);
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
