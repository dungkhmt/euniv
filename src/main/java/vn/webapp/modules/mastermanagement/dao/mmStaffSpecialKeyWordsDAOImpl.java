/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmStaffSpecializationKeywords;

@Repository("mmstaffSpecialKeywordsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmStaffSpecialKeyWordsDAOImpl extends BaseDao implements mmStaffSpecialKeyWordsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Load staffSpecializationKeywords
     * @param int
     * @return object
     */
    @Override
    public mmStaffSpecializationKeywords getStaffSpecializationKeywordsByStaffAndCode(String sUserCode, String sKeywordCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaffSpecializationKeywords.class);
            criteria.add(Restrictions.eq("STFKW_StaffCode", sUserCode));
            criteria.add(Restrictions.eq("STFKW_KeywordCode", sKeywordCode));
            mmStaffSpecializationKeywords staffSpecializationKeywords = (mmStaffSpecializationKeywords) criteria.uniqueResult();
            commit();
            return staffSpecializationKeywords;
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
     * Save a staffSpecializationKeywords
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaffSpecializationKeywords(mmStaffSpecializationKeywords staffSpecializationKeywords){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(staffSpecializationKeywords);
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
    
    /*@Override
    public int removeAMasterThesis(int masterThesisId){
    	MasterThesis masterThesis = new MasterThesis();
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
    }*/
    
}
