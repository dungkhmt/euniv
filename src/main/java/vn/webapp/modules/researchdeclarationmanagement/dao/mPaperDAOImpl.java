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
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.usermanagement.model.mStaff;

@Repository("mPaperDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPaperDAOImpl extends BaseDao implements mPaperDAO{
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
    public List<mPapers> listAll() {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<mPapers> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 
     */
    @Override
    public List<mPapers> loadPaperListByStaff(String userRole, String userCode) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
            //if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            //}
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<mPapers> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public List<mPapers> loadPaperListSummary(String paperStaff, String paperCategory, String paperAcademicYear) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
            if(paperStaff != null && (!"".equals(paperStaff))){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", paperStaff));
            }
            if(paperCategory != null && (!"".equals(paperCategory))){
            	criteria.add(Restrictions.eq("papers.PDECL_PaperCategory_Code", paperCategory));
            }
            if(paperAcademicYear != null && (!"".equals(paperAcademicYear))){
            	criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", paperAcademicYear));
            }
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<mPapers> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get papers list by year and user
     * @param null
     * @return List
     */
    @Override
    public List<mPapers> loadPaperListByYear(String userRole, String userCode, String reportingrYear){
    	try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
            if(!userRole.equals("SUPER_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            }
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<mPapers> papers = criteria.list();
            return papers;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get papers list by year
     * @param null
     * @return List
     */
    @Override
    public List<mPapers> loadPaperSummaryListByYear(String reportingrYear){
    	try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<mPapers> papers = criteria.list();
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
    public int saveAPaper(mPapers paper) 
    {
        try {
           int id = (int)sessionFactory.getCurrentSession().save(paper);
           return id; 
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return 0;
        }
    }
    
    /**
     * Load A Paper by id and User code
     * @param object
     * @return int
     */
    @Override
    public mPapers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId){
    	try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class);
            criteria.add(Restrictions.eq("PDECL_ID", paperId));
            //if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("PDECL_User_Code", userCode));
            //}
            mPapers paper = (mPapers) criteria.uniqueResult();
            return paper;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public mPapers loadAPaperById(int paperId){
    	try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class);
            criteria.add(Restrictions.eq("PDECL_ID", paperId));
            mPapers paper = (mPapers) criteria.uniqueResult();
            return paper;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a paper
     * @param object
     * @return int
     */
    @Override
    public void editAPaper(mPapers paper){
        try {
           sessionFactory.getCurrentSession().update(paper);
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove a paper
     * @param paperId
     * @return
     */
    @Override
    public int removeAPaper(int paperId){
    	mPapers paper = new mPapers();
    	paper.setPDECL_ID(paperId);    
        try {
            sessionFactory.getCurrentSession().delete(paper);
            return 1;
        } catch (HibernateException e) {
        	System.out.println(e.getMessage());
            return 0;
        }
    }
}
