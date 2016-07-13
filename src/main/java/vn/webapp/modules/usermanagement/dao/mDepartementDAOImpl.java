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
import vn.webapp.modules.usermanagement.model.mDepartment;

@Repository("mDepartmentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mDepartementDAOImpl extends BaseDao implements mDepartmentDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<mDepartment> loadDepartmentList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mDepartment.class);
            criteria.addOrder(Order.asc("Department_Name"));
            List<mDepartment> department = criteria.list();
            commit();
            return department;
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
    public List<mDepartment> loadDepartmentListByFaculty(String facultyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mDepartment.class);
            criteria.add(Restrictions.eq("Department_Faculty_Code", facultyCode));
            criteria.addOrder(Order.asc("Department_Name"));
            List<mDepartment> department = criteria.list();
            commit();
            return department;
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
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public mDepartment loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mDepartment.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Code", departmentCode));
            criteria.add(Restrictions.eq("Department.Department_Faculty_Code", falcutyCode));
            mDepartment department = (mDepartment) criteria.uniqueResult();
            commit();
            return department;
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
     * Get a department by falcuty
     * @param null
     * @return object
     */
    @Override
    public List<mDepartment> loadADepartmentByFaculty(String falcutyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mDepartment.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Faculty_Code", falcutyCode));
            criteria.addOrder(Order.asc("Department.Department_Name"));
            List<mDepartment> department = criteria.list();
            commit();
            return department;
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
     * Get a department by their code
     * @param null
     * @return object
     */
    @Override
    public List<mDepartment> loadADepartmentBySetOfCode(String[] sCodes){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mDepartment.class, "Department");
            criteria.add(Restrictions.in("Department.Department_Code", sCodes));
            List<mDepartment> department = criteria.list();
            commit();
            return department;
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
