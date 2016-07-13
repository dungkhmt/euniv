package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;

@Repository("mJuryRoleOfSubmittedProjectsDAO")
@SuppressWarnings({ "unchecked" })
public class mJuryRoleOfSubmittedProjectsDAOImpl extends BaseDao implements mJuryRoleOfSubmittedProjectsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get roles of submitted-project juries 
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mJuryRoleOfSubmittedProjects> loadAllJuryRoleSubmittedProjects(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryRoleOfSubmittedProjects.class, "juryRoleOfSubmittedProjects");
			List<mJuryRoleOfSubmittedProjects> juryRoleOfSubmittedProjects = criteria.list();
			commit();
			return juryRoleOfSubmittedProjects;
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
