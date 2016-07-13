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

import vn.webapp.modules.mastermanagement.model.mmMasterClass;



@Repository("mmmasterClassesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmMasterClassDAOImpl extends BaseDao implements mmMasterClassDAO {

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
    public List<mmMasterClass> listMasterClass(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmMasterClass.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<mmMasterClass> classes = criteria.list();
            commit();
            return classes;
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
    
    public mmMasterClass loadMasterClassByCode(String masterClassCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmMasterClass.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Classes_Code", masterClassCode));
            mmMasterClass classes = (mmMasterClass) criteria.uniqueResult();
            commit();
            return classes;
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
