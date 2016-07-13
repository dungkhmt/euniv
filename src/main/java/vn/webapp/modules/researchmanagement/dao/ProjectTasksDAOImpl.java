package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.researchmanagement.model.Projects;

@Repository("ProjectTasksDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProjectTasksDAOImpl extends BaseDao implements ProjectTasksDAO {
	
	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<ProjectTasks> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(ProjectTasks.class);
            criteria.setFirstResult(0);
            List<ProjectTasks> projectTasks = criteria.list();
            commit();
            return projectTasks;
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
	public ProjectTasks loadAProjectTaskByCode(String sCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(ProjectTasks.class);
			criteria.add(Restrictions.eq("PRJTSK_Code", sCode));
			ProjectTasks projectTask = (ProjectTasks) criteria.uniqueResult();
			commit();
			return projectTask;
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
	public List<ProjectTasks> loadAProjectTaskByProjectCode(String sProjectCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(ProjectTasks.class);
			criteria.add(Restrictions.eq("PRJTSK_Proj_Code", sProjectCode));
			criteria.addOrder(Order.asc("PRJTSK_ID"));
			List<ProjectTasks> projectTasks = criteria.list();
			commit();
			return projectTasks;
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
	 * Saving project tasks
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAProjectTask(ProjectTasks projectTask){
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(projectTask);
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
	 * Remove a project task
	 * 
	 * @param projectId
	 * @return
	 */
	@Override
	public int removeAProjectTask(ProjectTasks projectTask){
		try {
			begin();
			getSession().delete(projectTask);
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
	public void editAProjectTask(ProjectTasks pt){
		try{
			begin();
			getSession().update(pt);
			commit();
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();close();
		}finally{
			flush();close();
		}
	}
}
