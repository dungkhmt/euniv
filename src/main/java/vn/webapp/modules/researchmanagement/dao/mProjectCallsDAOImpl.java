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

@Repository("mProjectCallsDAO")
@SuppressWarnings({ "unchecked" })
public class mProjectCallsDAOImpl extends BaseDao implements mProjectCallsDAO {
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
	public List<mProjectCalls> loadProjectCallsList(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCalls.class, "projectcalls");
			List<mProjectCalls> projectCalls = criteria.list();
			commit();
			return projectCalls;
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
	public int saveAProjectCall(mProjectCalls projectCalls){
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(projectCalls);
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
	public mProjectCalls loadAProjectCallById(int iProjectCallId){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCalls.class);
			criteria.add(Restrictions.eq("PROJCALL_ID", iProjectCallId));
			mProjectCalls projectCalls = (mProjectCalls) criteria.uniqueResult();
			commit();
			return projectCalls;
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
	 * Load A Project Call by code
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public mProjectCalls loadAProjectCallByCode(String sProjectCallCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCalls.class);
			criteria.add(Restrictions.eq("PROJCALL_CODE", sProjectCallCode));
			mProjectCalls projectCalls = (mProjectCalls) criteria.uniqueResult();
			commit();
			return projectCalls;
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
	public void editAProjectCall(mProjectCalls projectCalls){
		try {
			begin();
			getSession().update(projectCalls);
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
	public int removeAProjectCall(mProjectCalls projectCalls){
		try {
			begin();
			getSession().delete(projectCalls);
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
	
	/**
	 * 
	 */
	@Override
	public mProjectCalls loadAProjectCallByName(String sPROJCALL_NAME){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCalls.class);
			criteria.add(Restrictions.eq("PROJCALL_NAME", sPROJCALL_NAME));
			mProjectCalls projectCalls = (mProjectCalls) criteria.uniqueResult();
			commit();
			return projectCalls;
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
	public int checkingExistProjectCallByName(int projectCallId, String sPROJCALL_NAME){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectCalls.class);
			criteria.add(Restrictions.ne("PROJCALL_ID", projectCallId));
			criteria.add(Restrictions.eq("PROJCALL_NAME", sPROJCALL_NAME));
			mProjectCalls projectCalls = (mProjectCalls) criteria.uniqueResult();
			commit();
			return (projectCalls != null) ? 1 : 0;
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
