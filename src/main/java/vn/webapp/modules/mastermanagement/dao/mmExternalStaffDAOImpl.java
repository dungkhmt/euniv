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

import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmExternalStaffInput;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;

@Repository("mmexternalstaffDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmExternalStaffDAOImpl extends BaseDao implements mmExternalStaffDAO {

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
    
    public List<mmExternalStaff> listExternalStaffs(){
    	
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmExternalStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<mmExternalStaff> externalstaffs = criteria.list();
            commit();
            return externalstaffs;
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
	
	public List<mmExternalStaff> listExternalStaffsByUniversity(String universityCode){
		
		try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmExternalStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("EXTSTAF_UniversityCode", universityCode));            
            List<mmExternalStaff> externalstaffs = criteria.list();
            commit();
            return externalstaffs;
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
		
	public mmExternalStaff getExternalStaffById(String userRole, int staff_Id){
		
		try {
            begin();
            Criteria criteria = getSession().createCriteria(mmExternalStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("EXTSTAF_ID", staff_Id));            
            if(!(userRole.equals("ROLE_ADMIN")||userRole.equals("SUPER_ADMIN"))){
            	return null;
            }
            mmExternalStaff professor = (mmExternalStaff) criteria.uniqueResult();
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
	
	public mmExternalStaffInput getExternalStaffInputById(String userRole, int staff_Id){
		
		try {
            begin();
            Criteria criteria = getSession().createCriteria(mmExternalStaffInput.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("EXTSTAF_ID", staff_Id));            
            if(!(userRole.equals("ROLE_ADMIN")||userRole.equals("SUPER_ADMIN"))){
            	return null;
            }
            mmExternalStaffInput professor = (mmExternalStaffInput) criteria.uniqueResult();
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
	
	public mmExternalStaff getByExternalStaffCode(String externalStaffCode){
		
		try {
            begin();
            Criteria criteria = getSession().createCriteria(mmExternalStaff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("EXTSTAF_Code", externalStaffCode));            
            mmExternalStaff professor = (mmExternalStaff) criteria.uniqueResult();
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
		   
    public void editAExternalStaff(mmExternalStaffInput externalstaff){
    	try {
            begin();
            getSession().update(externalstaff);
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
    
    public int saveAExternalStaff(mmExternalStaffInput externalstaff){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(externalstaff);
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
    
    public int removeAExternalStaff(int staffId){
    	mmExternalStaff staff = new mmExternalStaff();
    	staff.setEXTSTAF_ID(staffId);
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
