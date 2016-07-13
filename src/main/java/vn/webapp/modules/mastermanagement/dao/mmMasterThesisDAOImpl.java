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

import vn.webapp.modules.mastermanagement.model.mmMasterThesis;
import vn.webapp.modules.mastermanagement.model.mmMasterThesisInput;

@Repository("mmmasterThesisDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmMasterThesisDAOImpl extends BaseDao implements mmMasterThesisDAO {

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
    public List<mmMasterThesis> listMasterThesis(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmMasterThesis.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<mmMasterThesis> masterThesis = criteria.list();
            commit();
            return masterThesis;
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
    public mmMasterThesis getMasterThesisById(int masterThesis_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterThesis.class);
            criteria.add(Restrictions.eq("Thesis_ID", masterThesis_Id));
            
            mmMasterThesis masterThesis = (mmMasterThesis) criteria.uniqueResult();
            commit();
            return masterThesis;
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
    public mmMasterThesisInput getRawMasterThesisById(int masterThesis_Id){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterThesisInput.class);
            criteria.add(Restrictions.eq("Thesis_ID", masterThesis_Id));
            
            mmMasterThesisInput masterThesis = (mmMasterThesisInput) criteria.uniqueResult();
            commit();
            return masterThesis;
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
    
    public mmMasterThesis getMasterThesisByCode(String MasterThesis_Code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterThesis.class);
            criteria.add(Restrictions.eq("Thesis_Code", MasterThesis_Code));
            
            mmMasterThesis masterThesis = (mmMasterThesis) criteria.uniqueResult();
            commit();
            return masterThesis;
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
     * Edit a master thesis
     * @param Object
     * @return int
     */
    @Override
    public void editAMasterThesis(mmMasterThesisInput masterThesis){
    	try {
            begin();
            getSession().update(masterThesis);
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
     * Save a master thesis
     * @param Object
     * @return int
     */
    @Override
    public int saveAMasterThesis(mmMasterThesisInput masterThesis){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(masterThesis);
            commit();
            System.out.println("OK");
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
    public int removeAMasterThesis(int masterThesisId){
    	mmMasterThesis masterThesis = new mmMasterThesis();
    	masterThesis.setThesis_ID(masterThesisId);
    	try {
            begin();
            getSession().delete(masterThesis);
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
