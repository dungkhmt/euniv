package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;

@Repository("mCommentsOfSubmittedProjectsDAO")
@SuppressWarnings({ "unchecked","rawtypes" })
public class mCommentsOfSubmittedProjectsDAOImpl extends BaseDao implements mCommentsOfSubmittedProjectsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	@Override
	public List<mCommentsOfSubmittedProjects> loadAllCommentsOfSubmittedProjects(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mCommentsOfSubmittedProjects.class, "commentsOfSubmittedProjects");
			List<mCommentsOfSubmittedProjects> commentsOfSubmittedProjects = criteria.list();
			commit();
			return commentsOfSubmittedProjects;
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
	 * 
	 */
	@Override
	public int saveCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects){
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(commentsOfSubmittedProjects);
			commit();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}

	/**
	 * 
	 * @param iProjectCallId
	 * @return
	 */
	@Override
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectsById(int COMPROJ_ID){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mCommentsOfSubmittedProjects.class);
			criteria.add(Restrictions.eq("COMPROJ_ID", COMPROJ_ID));
			mCommentsOfSubmittedProjects commentsOfSubmittedProjects = (mCommentsOfSubmittedProjects) criteria.uniqueResult();
			commit();
			return commentsOfSubmittedProjects;
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
	
	@Override
	public mCommentsOfSubmittedProjects loadCommentsOfSubmittedProjectByStaffCodeProjectCode(String COMPROJ_STAFFCODE, String COMPROJ_PRJCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mCommentsOfSubmittedProjects.class);
			criteria.add(Restrictions.eq("COMPROJ_STAFFCODE", COMPROJ_STAFFCODE));
			criteria.add(Restrictions.eq("COMPROJ_PRJCODE", COMPROJ_PRJCODE));
			mCommentsOfSubmittedProjects commentsOfSubmittedProjects = (mCommentsOfSubmittedProjects) criteria.uniqueResult();
			commit();
			return commentsOfSubmittedProjects;
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
	@Override
	public List<mCommentsOfSubmittedProjects> loadCommentsOfSubmittedProjectByProjectCode(String COMPROJ_PRJCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mCommentsOfSubmittedProjects.class);
			criteria.add(Restrictions.eq("COMPROJ_PRJCODE", COMPROJ_PRJCODE));
			List<mCommentsOfSubmittedProjects> commentsOfSubmittedProjects = criteria.list();//(mCommentsOfSubmittedProjects) criteria.uniqueResult();
			commit();
			return commentsOfSubmittedProjects;
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
	
	@Override
	public List<mCommentsOfSubmittedProjects> loadListCommentsOfSubmittedProjectsByStaffCode(String COMPROJ_STAFFCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mCommentsOfSubmittedProjects.class);
			criteria.add(Restrictions.eq("COMPROJ_STAFFCODE", COMPROJ_STAFFCODE));
			List<mCommentsOfSubmittedProjects> commentsOfSubmittedProjects = criteria.list();
			commit();
			return commentsOfSubmittedProjects;
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
	 * Edit a commentsOfSubmittedProjects
	 * 
	 * @param object
	 * @return void
	 */
	@Override
	public void editCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects){
		try {
			begin();
			getSession().update(commentsOfSubmittedProjects);
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
		} finally {
			flush();
			close();
		}
	}

	/**
	 * Remove a Project Call
	 * 
	 * @param paperId
	 * @return
	 */
	@Override
	public int deleteCommentsOfSubmittedProjects(mCommentsOfSubmittedProjects commentsOfSubmittedProjects){
		try {
			begin();
			getSession().delete(commentsOfSubmittedProjects);
			commit();
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}
	
}
