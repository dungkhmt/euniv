package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mProjectCallStatus;

@Repository("mProjectCallStatusDAO")
@SuppressWarnings({ "unchecked" })
public class mProjectCallStatusDAOImpl extends BaseDao implements mProjectCallStatusDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get topic list by user
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mProjectCallStatus> loadProjectCallStatusList(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCallStatus.class, "projectcallstatus");
			List<mProjectCallStatus> projectCallStatus = criteria.list();
			commit();
			return projectCallStatus;
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
