package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.ProjectsProjectResearchField;

@Repository("ProjectsProjectResearchFieldDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProjectsProjectResearchFieldDAOImpl extends BaseDao implements ProjectsProjectResearchFieldDAO {
	
	/**
	 * 
	 */
	public List<ProjectsProjectResearchField> loadProjectsProjectResearchFieldListByProjectCode(String sProjectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(ProjectsProjectResearchField.class);
			criteria.add(Restrictions.eq("PRJPRJRSHF_PROJCode", sProjectCode));
			List<ProjectsProjectResearchField> projectsProjectResearchFieldList = criteria.list();
			commit();
			return projectsProjectResearchFieldList;
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
	 * Saving 
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField){
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(projectsProjectResearchField);
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
	 * Remove 
	 * 
	 * @param projectId
	 * @return
	 */
	@Override
	public int removeAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField){
		try {
			begin();
			getSession().delete(projectsProjectResearchField);
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
	public void editAProjectSearchField(ProjectsProjectResearchField projectsProjectResearchField){
		try{
			begin();
			getSession().update(projectsProjectResearchField);
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();close();
		}finally{
			flush();close();
		}
	}
}
