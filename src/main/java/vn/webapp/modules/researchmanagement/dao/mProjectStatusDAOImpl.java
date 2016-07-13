package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;

@Repository("mProjectStatusDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mProjectStatusDAOImpl extends BaseDao implements mProjectStatusDAO {
	
	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mProjectStatus> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mProjectStatus.class);
            criteria.setFirstResult(0);
            List<mProjectStatus> projectStatuses = criteria.list();
            commit();
            return projectStatuses;
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
	public mProjectStatus loadAProjectStatusByProjectCode(String projectStatusCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStatus.class);
			criteria.add(Restrictions.eq("PROJSTAT_Code", projectStatusCode));
			mProjectStatus projectStatus = (mProjectStatus) criteria.uniqueResult();
			commit();
			return projectStatus;
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
