package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;

@Repository("mPatentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPatentDAOImpl extends BaseDao implements mPatentDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get list
     * @param null
     * @return List
     */
    @Override
    public List<mPatents> loadPatentListByStaff(String userRole, String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatents.class, "patents");
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("patents.PAT_User_Code", userCode));
            }
            criteria.addOrder(Order.desc("patents.PAT_ID"));
            List<mPatents> patents = criteria.list();
            commit();
            return patents;
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
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<mPatents> loadPatentListByYear(String userRole, String userCode, String reportingrYear){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatents.class, "patents");
            if(!userRole.equals("SUPER_ADMIN")){
            	criteria.add(Restrictions.eq("patents.PAT_User_Code", userCode));
            }
            criteria.add(Restrictions.eq("patents.PAT_ReportingAcademicDate", reportingrYear));
            criteria.addOrder( Order.asc("patents.PAT_ID") );
            List<mPatents> patents = criteria.list();
            commit();
            return patents;
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
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<mPatents> loadPatentSummaryListByYear(String reportingrYear){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatents.class, "patents");
            criteria.add(Restrictions.eq("patents.PAT_ReportingAcademicDate", reportingrYear));
            criteria.addOrder( Order.asc("patents.PAT_ID") );
            List<mPatents> patents = criteria.list();
            commit();
            return patents;
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
     * Save a patent
     * @param object
     * @return int
     */
    @Override
    public int saveAPatent(mPatents patent)
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(patent);
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
     * Load A Patent by id and User code
     * @param object
     * @return int
     */
    @Override
    public mPatents loadAPatentByIdAndUserCode(String userRole, String userCode, int patentId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPatents.class);
            criteria.add(Restrictions.eq("PAT_ID", patentId));
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("PAT_User_Code", userCode));
            }
            mPatents patent = (mPatents) criteria.uniqueResult();
            commit();
            return patent;
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
     * Edit a patent
     * @param object
     * @return int
     */
    @Override
    public void editAPatent(mPatents patent){
        try {
           begin();
           getSession().update(patent);
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
     * Remove a patent
     * @param int
     * @return
     */
    @Override
    public int removeAPatent(int patentId){
    	mPatents patent = new mPatents();
    	patent.setPAT_ID(patentId);   
        try {
            begin();
            getSession().delete(patent);
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
}
