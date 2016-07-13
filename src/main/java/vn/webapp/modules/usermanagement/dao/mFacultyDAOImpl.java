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
import vn.webapp.modules.usermanagement.model.mFaculty;

@Repository("mFacultyDAO")
@SuppressWarnings({ "unchecked" })
public class mFacultyDAOImpl extends BaseDao implements mFacultyDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	@Override
	public List<mFaculty> loadFacultyList() {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mFaculty.class);
			criteria.addOrder(Order.asc("Faculty_Name"));
			List<mFaculty> faculty = criteria.list();
			commit();
			return faculty;
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
	public mFaculty loadAFacultyById(int facultyId) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mFaculty.class,"Faculty");
			criteria.add(Restrictions.eq("Faculty.Faculty_ID", facultyId));
			mFaculty faculty = (mFaculty) criteria.uniqueResult();
			commit();
			return faculty;
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
	public mFaculty loadAFacultyByCode(String facultyCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mFaculty.class,"Faculty");
			criteria.add(Restrictions.eq("Faculty.Faculty_Code", facultyCode));
			mFaculty faculty = (mFaculty)criteria.uniqueResult();
			commit();
			return faculty;
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
	public int saveAFaculty(mFaculty faculty) {
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(faculty);
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
	public void editAFaculty(mFaculty faculty) {
		try {
			begin();
			getSession().update(faculty);
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
	public int removeAFaculty(int facultyId) {
		mFaculty faculty = new mFaculty();
		faculty.setFaculty_ID(facultyId);
		try {
			begin();
			getSession().delete(faculty);
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
	public List<mFaculty> getListFacultyBySetOfCode(String[] sfacultyCodes) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mFaculty.class,"Faculty");
			criteria.add(Restrictions.in("Faculty.Faculty_Code", sfacultyCodes));
			List<mFaculty> faculty = criteria.list();
			commit();
			return faculty;
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
