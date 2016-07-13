package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;



@Repository("mJuryOfAnnouncedProjectCallDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class mJuryOfAnnouncedProjectCallDAOImpl extends BaseDao implements mJuryOfAnnouncedProjectCallDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	@Override
	public List<mJuryOfAnnouncedProjectCall> loadAllJuryOfAnnouncedProjectCall() {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class, "juriesOfAnnouncedProjectCall");
			
			List<mJuryOfAnnouncedProjectCall> juries = criteria.list();
			commit();
			return juries;
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
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByPresentCode(String JUSUPRJ_STAFFCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class);
			criteria.add(Restrictions.eq("JUPSURJ_ROLECODE", "CT"));
			criteria.add(Restrictions.eq("JUSUPRJ_STAFFCODE", JUSUPRJ_STAFFCODE));
			List<mJuryOfAnnouncedProjectCall> projectCallList = criteria.list();
			commit();
			return projectCallList;
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
	public int saveJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall) {
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(juryOfAnnouncedProjectCall);
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
	 */
	@Override
	public int deleteJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall){
		try {
			begin();
			getSession().delete(juryOfAnnouncedProjectCall);
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
	public mJuryOfAnnouncedProjectCall loadAJuryOfAnnouncedProjectCallById(int JUSUPRJ_ID){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class);
			criteria.add(Restrictions.eq("JUSUPRJ_ID", JUSUPRJ_ID));
			mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCalls = (mJuryOfAnnouncedProjectCall) criteria.uniqueResult();
			commit();
			return juryOfAnnouncedProjectCalls;
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
	public void editJuryOfAnnouncedProjectCall(mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCall){
		try {
			begin();
			getSession().update(juryOfAnnouncedProjectCall);
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
	 * 
	 */
	
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByJuryCode(String juryCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class);
			criteria.add(Restrictions.eq("JUSUPRJ_JURYRESEARCHPROJECTCODE", juryCode));
			List<mJuryOfAnnouncedProjectCall> projectCallList = criteria.list();
			commit();
			return projectCallList;
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
	public List<mJuryOfAnnouncedProjectCall> loadListJuryOfAnnouncedProjectCallByProjectCallCode(String projectCallCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class);
			criteria.add(Restrictions.eq("JUSUPRJ_PRJCALLCODE", projectCallCode));
			List<mJuryOfAnnouncedProjectCall> projectCallList = criteria.list();
			commit();
			return projectCallList;
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
	public mJuryOfAnnouncedProjectCall loadListJuryOfAnnouncedProjectCallByProjectCallAndStaffCode(String projectCallCode, String staffCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryOfAnnouncedProjectCall.class);
			criteria.add(Restrictions.eq("JUSUPRJ_PRJCALLCODE", projectCallCode));
			criteria.add(Restrictions.eq("JUSUPRJ_STAFFCODE", staffCode));
			mJuryOfAnnouncedProjectCall juryOfAnnouncedProjectCalls = (mJuryOfAnnouncedProjectCall) criteria.uniqueResult();
			commit();
			return juryOfAnnouncedProjectCalls;
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
