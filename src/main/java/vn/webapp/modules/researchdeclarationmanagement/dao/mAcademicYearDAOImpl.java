package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;

@Repository("mAcademicYearDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mAcademicYearDAOImpl extends BaseDao implements mAcademicYearDAO{
	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mAcademicYear> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mAcademicYear.class);
            criteria.setFirstResult(0);
            List<mAcademicYear> academicYear = criteria.list();
            commit();
            return academicYear;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
}
