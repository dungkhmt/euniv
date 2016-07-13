package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectComments;

@Repository("mProjectCommentsDAO")
@SuppressWarnings({ "unchecked" })
public class mProjectCommentsDAOImpl extends BaseDao implements mProjectCommentsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mProjectComments> loadprojectCommentsList(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectComments.class, "projectcomments");
			List<mProjectComments> projectComments = criteria.list();
			commit();
			return projectComments;
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
	 * Save a project call
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAProjectComment(mProjectComments projectComment){
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(projectComment);
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
	 * Load A Project Call by id
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public mProjectComments loadAProjectCommentById(int iProjectCommentId){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectComments.class);
			criteria.add(Restrictions.eq("COMPROJ_ID", iProjectCommentId));
			mProjectComments projectComments = (mProjectComments) criteria.uniqueResult();
			commit();
			return projectComments;
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
	 * @param object
	 * @return int
	 */
	@Override
	public List<mProjectComments> loadAProjectCommentByProjectCode(String sProjectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectComments.class);
			criteria.add(Restrictions.eq("COMPROJ_PRJCODE", sProjectCode));
			List<mProjectComments> projectComments = criteria.list();
			commit();
			return projectComments;
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
	 * Edit a projectCalls
	 * 
	 * @param object
	 * @return void
	 */
	@Override
	public void editAProjectComment(mProjectComments projectComment){
		try {
			begin();
			getSession().update(projectComment);
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
	public int removeAProjectComment(mProjectComments projectComment){
		try {
			begin();
			getSession().delete(projectComment);
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
