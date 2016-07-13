package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.ProjectParticipationRoles;

@Repository("ProjectParticipationRolesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProjectParticipationRolesDAOImpl extends BaseDao implements ProjectParticipationRolesDAO {
	
	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<ProjectParticipationRoles> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(ProjectParticipationRoles.class);
            criteria.setFirstResult(0);
            List<ProjectParticipationRoles> projectParticipationRoles = criteria.list();
            commit();
            return projectParticipationRoles;
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
	public ProjectParticipationRoles loadAProjectParticipationRoleByCode(String sCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(ProjectParticipationRoles.class);
			criteria.add(Restrictions.eq("PROJPARTIROLE_Code", sCode));
			ProjectParticipationRoles projectParticipationRole = (ProjectParticipationRoles) criteria.uniqueResult();
			commit();
			return projectParticipationRole;
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
