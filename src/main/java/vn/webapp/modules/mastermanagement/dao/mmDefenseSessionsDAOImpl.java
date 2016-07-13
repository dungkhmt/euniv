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

import vn.webapp.modules.mastermanagement.model.mmDefenseSession;


@Repository("mmdefenseSessionDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmDefenseSessionsDAOImpl extends BaseDao implements mmDefenseSessionsDAO{

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
    public List<mmDefenseSession> getListDefenseSession(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDefenseSession.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("DEFSESS_Enabled", 1));
            List<mmDefenseSession> defenseSession = criteria.list();
            commit();
            return defenseSession;
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
    public List<mmDefenseSession> getListAllDefenseSession(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDefenseSession.class);
            criteria.setFirstResult(0);
            List<mmDefenseSession> defenseSession = criteria.list();
            commit();
            return defenseSession;
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
    public void editADefenseSession(mmDefenseSession defenseSession){
    	try {
            begin();
            getSession().update(defenseSession);
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
    public int saveADefenseSession(mmDefenseSession defenseSession){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(defenseSession);
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
    public int removeADefenseSession(int iDefenseSessionId){
    	mmDefenseSession defenseSession = new mmDefenseSession();
    	defenseSession.setDEFSESS_ID(iDefenseSessionId);
    	try {
            begin();
            getSession().delete(defenseSession);
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
    public mmDefenseSession getDefenseSessionById(int iDefenseSessionId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDefenseSession.class);
            criteria.add(Restrictions.eq("DEFSESS_ID", iDefenseSessionId));
            mmDefenseSession professor = (mmDefenseSession) criteria.uniqueResult();
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
    
    @Override
    public mmDefenseSession getDefenseSessionByCode(String iDefenseSessionCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmDefenseSession.class);
            criteria.add(Restrictions.eq("DEFSESS_Code", iDefenseSessionCode));
            mmDefenseSession professor = (mmDefenseSession) criteria.uniqueResult();
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
}
