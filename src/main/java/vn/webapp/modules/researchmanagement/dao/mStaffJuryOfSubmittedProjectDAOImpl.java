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
import vn.webapp.modules.researchmanagement.model.mStaffJuryOfSubmittedProject;
import vn.webapp.modules.researchmanagement.model.mJuryRoleOfSubmittedProjects;



@Repository("mStaffJuryOfSubmittedProjectDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class mStaffJuryOfSubmittedProjectDAOImpl extends BaseDao implements mStaffJuryOfSubmittedProjectDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	@Override
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProject() {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class, "staffJuriesOfSubmittedProject");
			
			List<mStaffJuryOfSubmittedProject> staffJuries = criteria.list();
			commit();
			return staffJuries;
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
	public List<mStaffJuryOfSubmittedProject> loadAllStaffJuryOfSubmittedProjectByJuryCode(String juryCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class, "staffJuriesOfSubmittedProject");
			
			criteria.add(Restrictions.eq("STFJUPRJ_JURY_CODE",juryCode));
			
			List<mStaffJuryOfSubmittedProject> staffJuries = criteria.list();
			commit();
			return staffJuries;
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
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByStaffCode(String STFJUPRJ_STAFFJURCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class, "staffJuriesOfSubmittedProject");
			criteria.add(Restrictions.eq("STFJUPRJ_STAFFJURCODE", STFJUPRJ_STAFFJURCODE));
			List<mStaffJuryOfSubmittedProject> staffJuries = criteria.list();
			commit();
			return staffJuries;
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
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByProjectCode(String STFJUPRJ_PROJCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class, "staffJuriesOfSubmittedProject");
			criteria.add(Restrictions.eq("STFJUPRJ_PRJCODE", STFJUPRJ_PROJCODE));
			List<mStaffJuryOfSubmittedProject> staffJuries = criteria.list();
			commit();
			return staffJuries;
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
	public int saveStaffJuryOfSubmittedProject(mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject) {
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(staffJuryOfSubmittedProject);
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
	public int deleteStaffJuryOfSubmittedProject(mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject){
		try {
			begin();
			getSession().delete(staffJuryOfSubmittedProject);
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
	public mStaffJuryOfSubmittedProject loadAStaffJuryOfSubmittedProjectById(int STFJUPRJ_ID){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class);
			criteria.add(Restrictions.eq("STFJUPRJ_ID", STFJUPRJ_ID));
			mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject = (mStaffJuryOfSubmittedProject) criteria.uniqueResult();
			commit();
			return staffJuryOfSubmittedProject;
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
	public void editStaffJuryOfSubmittedProject(mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject){
		try {
			begin();
			getSession().update(staffJuryOfSubmittedProject);
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
	@Override
	public mStaffJuryOfSubmittedProject loadAStaffJuryOfSubmittedProjectByStaffAndProjectCode(String STFJUPRJ_STAFFJURCODE, String STFJUPRJ_PROJCODE){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class);
			criteria.add(Restrictions.eq("STFJUPRJ_STAFFJURCODE", STFJUPRJ_STAFFJURCODE));
			criteria.add(Restrictions.eq("STFJUPRJ_PRJCODE", STFJUPRJ_PROJCODE));
			mStaffJuryOfSubmittedProject staffJuryOfSubmittedProject = (mStaffJuryOfSubmittedProject) criteria.uniqueResult();
			commit();
			return staffJuryOfSubmittedProject;
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
	public List<mStaffJuryOfSubmittedProject> loadListStaffJuryOfSubmittedProjectByProjectCallCode(String projectCallCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mStaffJuryOfSubmittedProject.class, "staffJuriesOfSubmittedProject");
			criteria.add(Restrictions.eq("STFJUPRJ_PRJCALLCODE", projectCallCode));
			List<mStaffJuryOfSubmittedProject> staffJuries = criteria.list();
			commit();
			return staffJuries;
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
