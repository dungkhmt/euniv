/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmClasses;

@Repository("mmclassesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmClassesDAOImpl extends BaseDao implements mmClassesDAO{

	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get defenseSession list
     * @param String
     * @return object
     */
    @Override
    public List<mmClasses> getListmmClasses(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmClasses.class);
            criteria.setFirstResult(0);
            List<mmClasses> mmClasses = criteria.list();
            commit();
            return mmClasses;
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
     * Get defenseSession list
     * @param String
     * @return object
     */
    @Override
    public List<mmClasses> getListAllmmClasses(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmClasses.class);
            criteria.setFirstResult(0);
            List<mmClasses> mmClasses = criteria.list();
            commit();
            return mmClasses;
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
    public void editAClass(mmClasses aClass){
    	try {
            begin();
            getSession().update(aClass);
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
    
    @Override
    public int saveAClass(mmClasses aClass){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(aClass);
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
    public int removeAClass(int iClassId){
    	mmClasses cls = new mmClasses();
    	cls.setClasses_ID(iClassId);
    	try {
            begin();
            getSession().delete(cls);
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
    
    /**
     * Load A DefenseSession by id 
     * @param int
     * @return object
     */
    @Override
    public mmClasses getClassById(int iClassId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmClasses.class);
            criteria.add(Restrictions.eq("Classes_ID", iClassId));
            mmClasses cls = (mmClasses) criteria.uniqueResult();
            commit();
            return cls;
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
