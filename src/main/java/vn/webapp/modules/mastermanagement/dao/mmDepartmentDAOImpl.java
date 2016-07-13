/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.mastermanagement.model.mmDepartment;

@Repository("mmdepartmentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmDepartmentDAOImpl extends BaseDao implements mmDepartmentDAO {

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
    public List<mmDepartment> loadDepartmentList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDepartment.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<mmDepartment> department = criteria.list();
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
    public mmDepartment loadDepartmentByCode(String departmentCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDepartment.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Code", departmentCode));
            mmDepartment department = (mmDepartment) criteria.uniqueResult();
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
    public mmDepartment loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDepartment.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Code", departmentCode));
            criteria.add(Restrictions.eq("Department.Department_Faculty_Code", falcutyCode));
            mmDepartment department = (mmDepartment) criteria.uniqueResult();
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
