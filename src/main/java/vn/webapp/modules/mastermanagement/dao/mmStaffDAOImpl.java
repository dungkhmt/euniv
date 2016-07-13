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

import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;

@Repository("mmstaffDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmStaffDAOImpl extends BaseDao implements mmStaffDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mmStaff> listStaffs(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<mmStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<mmStaff> listStaffsByDepartment(String departmentCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Department_Code", departmentCode));
            List<mmStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<mmStaff> listStaffsByFaculty(String facultyCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("Staff_Faculty_Code", facultyCode));
            List<mmStaff> staff = criteria.list();            
            commit();
            return staff;
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
     * Load A Professor by id 
     * @param int
     * @return object
     */
    @Override
    public mmStaff getStaffById(String userRole, int staff_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class);
            criteria.add(Restrictions.eq("Staff_ID", staff_Id));
            if(!(userRole.equals("ROLE_ADMIN")||userRole.equals("SUPER_ADMIN"))){
            	return null;
            }
            mmStaff professor = (mmStaff) criteria.uniqueResult();
            commit();
            return professor;
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
    
    public mmStaffInput getStaffInputById(String userRole, int staff_Id){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaffInput.class);
            criteria.add(Restrictions.eq("Staff_ID", staff_Id));
            if(!(userRole.equals("ROLE_ADMIN")||userRole.equals("SUPER_ADMIN"))){
            	return null;
            }
            mmStaffInput professor = (mmStaffInput) criteria.uniqueResult();
            commit();
            return professor;
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
     * Get an staff by staffname
     * @param staffname
     * @return object
     */
    @Override
    public mmStaff getByUserCode(String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class);
            criteria.add(Restrictions.eq("Staff_User_Code", userCode));
            mmStaff staff = (mmStaff) criteria.uniqueResult();
            commit();
            return staff;
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
    public mmStaff getByStaffCode(String staffCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaff.class);
            criteria.add(Restrictions.eq("Staff_Code", staffCode));
            mmStaff staff = (mmStaff) criteria.uniqueResult();
            commit();
            return staff;
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
     * Edit a staff
     * @param Object
     * @return int
     */
    @Override
    public void editAStaff(mmStaffInput staff){
    	try {
            begin();
            getSession().update(staff);
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
     * Save a staff
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaff(mmStaffInput staff){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(staff);
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
    
    @Override
    public int removeAStaff(int staffId){
    	mmStaff staff = new mmStaff();
    	staff.setStaff_ID(staffId);
    	try {
            begin();
            getSession().delete(staff);
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
