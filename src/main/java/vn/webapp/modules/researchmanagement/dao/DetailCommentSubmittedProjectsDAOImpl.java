package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.DetailCommentSubmittedProjects;
import vn.webapp.modules.researchmanagement.model.mCommentsOfSubmittedProjects;

@Repository("DetailCommentSubmittedProjectsDAO")
@SuppressWarnings({ "unchecked","rawtypes" })
public class DetailCommentSubmittedProjectsDAOImpl extends BaseDao implements DetailCommentSubmittedProjectsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 
	 */
	@Override
	public int saveDetailsCommentsOfSubmittedProjects(DetailCommentSubmittedProjects commentsOfSubmittedProjects){
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
	 * Edit a commentsOfSubmittedProjects
	 * 
	 * @param object
	 * @return void
	 */
	@Override
	public void editDetailsCommentsOfSubmittedProjects(DetailCommentSubmittedProjects commentsOfSubmittedProjects){
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
	 * 
	 */
	@Override
	public DetailCommentSubmittedProjects loadDetailsCommentsOfSubmittedProjectsByProjectCode(String sStaffCode, String sProjectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(DetailCommentSubmittedProjects.class);
			criteria.add(Restrictions.eq("CMTSUBPRJ_StaffCode", sStaffCode));
			criteria.add(Restrictions.eq("CMTSUBPRJ_PRJCode", sProjectCode));
			DetailCommentSubmittedProjects commentsOfSubmittedProjects = (DetailCommentSubmittedProjects) criteria.uniqueResult();
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
	public List<DetailCommentSubmittedProjects> loadAll(){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(DetailCommentSubmittedProjects.class);
			List<DetailCommentSubmittedProjects> commentsOfSubmittedProjects = criteria.list();
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
	public List<DetailCommentSubmittedProjects> loadByProjectCode(String projectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(DetailCommentSubmittedProjects.class);
			criteria.add(Restrictions.eq("CMTSUBPRJ_PRJCode", projectCode));
			List<DetailCommentSubmittedProjects> commentsOfSubmittedProjects = criteria.list();
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
	public List<DetailCommentSubmittedProjects> loadListDetailsCommentsOfSubmittedProjectsByProjectCode(String sProjectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(DetailCommentSubmittedProjects.class);
			criteria.add(Restrictions.eq("CMTSUBPRJ_PRJCode", sProjectCode));
			List<DetailCommentSubmittedProjects> commentsOfSubmittedProjects = criteria.list();
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
}
