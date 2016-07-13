package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mJuryResearchProject;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;

@Repository("mJuryResearchProjectDAO")
@SuppressWarnings({ "unchecked" })

public class mJuryResearchProjectDAOImpl extends BaseDao implements
		mJuryResearchProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int removeAJuryResearchProject(mJuryResearchProject jury){
		try {
			begin();
			getSession().delete(jury);
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
	public mJuryResearchProject listAJuryByCode(String juryCode){
		// TODO Auto-generated method stub
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryResearchProject.class);
			criteria.add(Restrictions.eq("JURPRJ_Code",juryCode));
			mJuryResearchProject jury = (mJuryResearchProject)criteria.uniqueResult();
			commit();
			return jury;
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
	public mJuryResearchProject listAJuryByID(int id){
		// TODO Auto-generated method stub
				try {
					begin();
					Criteria criteria = getSession().createCriteria(mJuryResearchProject.class);
					criteria.add(Restrictions.eq("JURPRJ_ID",id));
					mJuryResearchProject jury = (mJuryResearchProject)criteria.uniqueResult();
					commit();
					return jury;
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
	public List<mJuryResearchProject> listAllJuriesByUserCode(String userCode){
		// TODO Auto-generated method stub
				try {
					begin();
					Criteria criteria = getSession().createCriteria(mJuryResearchProject.class);
					criteria.add(Restrictions.eq("JURPRJ_UserCode", userCode));
					List<mJuryResearchProject> juries = criteria.list();
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
	@Override
	public List<mJuryResearchProject> listAllJuries() {
		// TODO Auto-generated method stub
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mJuryResearchProject.class);
			List<mJuryResearchProject> juries = criteria.list();
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

	@Override
	public int saveAJury(mJuryResearchProject jury) {
		// TODO Auto-generated method stub
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(jury);
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

	@Override
	public void editAJury(mJuryResearchProject jury) {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().update(jury);
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

}
