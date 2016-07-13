package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.ProjectResearchField;
import vn.webapp.modules.researchmanagement.model.Projects;

@Repository("ProjectResearchFieldDAO")
@SuppressWarnings({ "unchecked" })
public class ProjectResearchFieldDAOImpl extends BaseDao implements ProjectResearchFieldDAO {

	@Override
	public List<ProjectResearchField> list() {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(ProjectResearchField.class);
			List<ProjectResearchField> fieldList = criteria.list();
			commit();
			return fieldList;
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
