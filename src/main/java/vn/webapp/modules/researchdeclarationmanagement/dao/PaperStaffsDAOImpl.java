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
            begin();
            Criteria criteria = getSession().createCriteria(PaperStaffs.class, "paperStaffs");
            criteria.add(Restrictions.eq("paperStaffs.PPSTF_PaperCode", paperCode));
            criteria.addOrder(Order.desc("paperStaffs.PPSTF_StaffCode"));
            List<PaperStaffs> papers = criteria.list();
            commit();
            return papers;
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
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<PaperStaffs> loadPaperListByPaperCode(List<String> paperCodes){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(PaperStaffs.class, "paperStaffs");
            criteria.add(Restrictions.in("paperStaffs.PPSTF_PaperCode", paperCodes));
            List<PaperStaffs> papers = criteria.list();
            commit();
            return papers;
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
     * Save a paper
     * @param object
     * @return int
     */
    @Override
    public int saveAPaperStaff(PaperStaffs paperStaff) 
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(paperStaff);
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
    
    /**
     * Remove a paper class
     * @param paperStaffId
     * @return
     */
    @Override
    public int removeAPaperStaff(int paperStaffId){
		PaperStaffs paperStaff = new PaperStaffs();
    	paperStaff.setPPSTF_ID(paperStaffId); 
        try {
            begin();
            getSession().delete(paperStaff);
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
