/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;

@Repository("paperStaffsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class PaperStaffsDAOImpl extends BaseDao implements PaperStaffsDAO {

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
    public List<PaperStaffs> loadPaperListByPaperCode(String paperCode){
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PaperStaffs.class);
            criteria.add(Restrictions.eq("PPSTF_PaperCode", paperCode));
            criteria.addOrder(Order.desc("PPSTF_StaffCode"));
            List<PaperStaffs> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<PaperStaffs> loadPaperListByPaperCode(List<String> paperCodes){
    	try {
    		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PaperStaffs.class, "paperStaffs");
            criteria.add(Restrictions.in("paperStaffs.PPSTF_PaperCode", paperCodes));
            List<PaperStaffs> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a paper
     * @param object
     * @return int
     */
    @Override
    public int saveAPaperStaff(PaperStaffs paperStaff) 
    {
        try {
           int id = (int)sessionFactory.getCurrentSession().save(paperStaff);
           return id;           
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return 0;
        }
    }
    
    /**
     * Remove a paper class
     * @param paperStaffId
     * @return
     */
    @Override
    public int removeAPaperStaff(PaperStaffs paperStaff){
        try {
            sessionFactory.getCurrentSession().delete(paperStaff);
            return 1;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return 0;
        }
    }
    
}
