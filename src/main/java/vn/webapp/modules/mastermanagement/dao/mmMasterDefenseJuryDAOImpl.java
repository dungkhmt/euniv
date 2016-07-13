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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmMasterClass;
import vn.webapp.modules.mastermanagement.model.mmMasterDefenseJuryThesis;

@Repository("mmmasterDefenseJuryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmMasterDefenseJuryDAOImpl extends BaseDao implements mmMasterDefenseJuryDAO{

	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get masterDefenseJuryThesis list
     * @param String
     * @return object
     */
    @Override
    public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesis(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class);
            criteria.setFirstResult(0);
            List<mmMasterDefenseJuryThesis> masterDefenseJuryThesis = criteria.list();
            commit();
            return masterDefenseJuryThesis;
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
    
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisById(int Id){
    	
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class);
            criteria.add(Restrictions.eq("MASDEFJury_ID", Id));
            mmMasterDefenseJuryThesis masterDefenseJuryThesis = (mmMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
    
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCode(String ThesisCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class);
            criteria.add(Restrictions.eq("MASDEFJury_ThesisCode", ThesisCode));
            mmMasterDefenseJuryThesis masterDefenseJuryThesis = (mmMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * Get masterDefenseJuryThesis by owner list
     * @param String
     * @return object
     */
    @Override
    public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class);
            criteria.add(Restrictions.eq("MASDEFJury_StaffCode", ownerCode));
            //criteria.setFirstResult(0);
            List<mmMasterDefenseJuryThesis> masterDefenseJuryThesis = criteria.list();
            commit();
            return masterDefenseJuryThesis;
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
    
    public List<mmMasterDefenseJuryThesis> getListMasterDefenseJuryThesisByDefenseSessionAndOwner(String defenseSessionCode,String ownerCode){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class, "MasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_DefenseSessionCode", defenseSessionCode));
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            List<mmMasterDefenseJuryThesis> masterDefenseJuryThesis = criteria.list();
            commit();
            return masterDefenseJuryThesis;
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
     * 
     */
    @Override
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByIdAndOwner(String masterDefenseJuryCode, String ownerCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class, "MasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_Code", masterDefenseJuryCode));
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            mmMasterDefenseJuryThesis masterDefenseJuryThesis = (mmMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * 
     */
    @Override
    public mmMasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode)
    {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmMasterDefenseJuryThesis.class, "MasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_ThesisCode", masterThesisCode));
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            mmMasterDefenseJuryThesis masterDefenseJuryThesis = (mmMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * Edit a MasterDefenseJuryThesis
     * @param Object
     * @return int
     */
    @Override
    public void updateAMasterDefenseJuryThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
            begin();
            getSession().update(masterDefenseJuryThesis);
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
     * Edit a MasterDefenseJuryThesis
     * @param Object
     * @return int
     */
    @Override
    public int saveAMasterThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
    		System.out.println("DAO OK");
            begin();
            int id = 0; 
            id = (int)getSession().save(masterDefenseJuryThesis);
            commit();
            return id;
         } catch (HibernateException e) {
        	 System.out.println("DAO");
             e.printStackTrace();
             rollback();
             close();
         } finally {
             flush();
             close();
         }
		return 0;
    }
	
    /**
     * 
     */
    /*@Override
    public mmRawMasterDefenseJuryThesis getRawMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode)
    {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmRawMasterDefenseJuryThesis.class, "RawMasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("RawMasterDefenseJuryThesis.MASDEFJury_Code", masterThesisCode));
            criteria.add(Restrictions.eq("RawMasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            mmRawMasterDefenseJuryThesis masterDefenseJuryThesis = (mmRawMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }*/
    
    /**
     * 
     */
    @Override
    public int removeAMasterThesis(mmMasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
            begin();
            getSession().delete(masterDefenseJuryThesis);
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
