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
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;

@Repository("mmspecializationKeywordDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmSpecializationKeywordDAOImpl extends BaseDao implements mmSpecializationKeywordDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get specialization keyword list
     * @param null
     * @return List
     */
    @Override
    public List<mmSpecializationKeyword> loadSpecializationKeywordList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mmSpecializationKeyword.class);
            List<mmSpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            return specializationKeywordList;
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
     * Get specialization keyword list by scientific field
     * @param null
     * @return List
     */
    @Override
    public List<mmSpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmSpecializationKeyword.class, "specializationKeyword");
            criteria.add(Restrictions.eq("specializationKeyword.KW_ScientificFieldCode", SCIF_Code));
            List<mmSpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            return specializationKeywordList;
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
     * Get specialization keyword list by code
     * @param null
     * @return List
     */
    @Override
    public mmSpecializationKeyword getSpecializationKeywordByCode(String KW_Code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmSpecializationKeyword.class, "specializationKeyword");
            criteria.add(Restrictions.eq("specializationKeyword.KW_Code", KW_Code));
            List<mmSpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            if(specializationKeywordList != null){
            	return specializationKeywordList.get(0);
            }
            else
            	return null;
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
    public List<mmSpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode){
    	try {
            begin();
            SQLQuery q = getSession().createSQLQuery("SELECT DISTINCT KW_ID,KW_Code,KW_EngName,KW_VNName,KW_ScientificFieldCode FROM `tblspecializationkeywords` AS t1 JOIN (SELECT * FROM `tblstaffspecializationkeywords` WHERE STFKW_StaffCode = '"+staffCode+"') AS t2 ON t1.KW_Code = t2.STFKW_KeywordCode");
    		q.addEntity(mmSpecializationKeyword.class);
            commit();
            return q.list();
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
    public List<mmSpecializationKeyword> loadMasterThesisSpecializationKeywordList(String thesisCode){
    	try {
            begin();
            SQLQuery q = getSession().createSQLQuery("SELECT DISTINCT KW_ID,KW_Code,KW_EngName,KW_VNName,KW_ScientificFieldCode FROM `tblspecializationkeywords` AS t1 JOIN (SELECT * FROM `tblmasterthesisspecializationkeywords` WHERE MTKW_ThesisCode = '"+thesisCode+"') AS t2 ON t1.KW_Code = t2.MTKW_KWCode");
    		q.addEntity(mmSpecializationKeyword.class);
            commit();
            return q.list();
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
