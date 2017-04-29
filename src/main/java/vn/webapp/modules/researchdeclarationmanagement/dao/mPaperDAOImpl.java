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
        	begin();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
        	Criteria criteria = getSession().createCriteria(mPapers.class, "papers");
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<mPapers> papers = criteria.list();
            commit();
            return papers;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     */
    @Override
    public List<mPapers> loadPaperListByStaff(String userRole, String userCode) {
        try {
        	begin();
           // Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
        	 Criteria criteria = getSession().createCriteria(mPapers.class, "papers");
            //if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            //}
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<mPapers> papers = criteria.list();
            commit();
            return papers;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public List<mPapers> loadPaperListSummary(String paperStaff, String paperCategory, String paperAcademicYear) {
        try {
            begin();
        	//Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
        	Criteria criteria = getSession().createCriteria(mPapers.class);
        	
            if(paperStaff != null && (!"".equals(paperStaff))){
            	criteria.add(Restrictions.eq("PDECL_User_Code", paperStaff));
            }
            if(paperCategory != null && (!"".equals(paperCategory))){
            	criteria.add(Restrictions.eq("PDECL_PaperCategory_Code", paperCategory));
            }
            if(paperAcademicYear != null && (!"".equals(paperAcademicYear))){
            	criteria.add(Restrictions.eq("PDECL_ReportingAcademicDate", paperAcademicYear));
            }
            criteria.addOrder(Order.desc("PDECL_ID"));
            List<mPapers> papers = criteria.list();
            commit();
            return papers;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
            e.printStackTrace();
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
    		begin();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
    		Criteria criteria = getSession().createCriteria(mPapers.class, "papers");
            if(!userRole.equals("SUPER_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            }
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<mPapers> papers = criteria.list();
            commit();
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
    		begin();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class, "papers");
    		Criteria criteria = getSession().createCriteria(mPapers.class, "papers");
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<mPapers> papers = criteria.list();
            commit();
            return papers;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
            e.printStackTrace();
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
        	begin();
           //int id = (int)sessionFactory.getCurrentSession().save(paper);
        	int id = (int)getSession().save(paper);
        	commit();
           return id; 
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
        	rollback();
        	close();
            return 0;
        }finally{
        	flush();
        	close();
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
    		begin();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class);
    		Criteria criteria = getSession().createCriteria(mPapers.class);
            criteria.add(Restrictions.eq("PDECL_ID", paperId));
            //if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("PDECL_User_Code", userCode));
            //}
            mPapers paper = (mPapers) criteria.uniqueResult();
            commit();
            return paper;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
           e.printStackTrace();
        	return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public mPapers loadAPaperById(int paperId){
    	try {
    		begin();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mPapers.class);
    		Criteria criteria = getSession().createCriteria(mPapers.class);
            criteria.add(Restrictions.eq("PDECL_ID", paperId));
            mPapers paper = (mPapers) criteria.uniqueResult();
            commit();
            return paper;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
            return null;
        }
    }

    public String name(){
    	return "mPaperDAOImpl";
    }
    /**
     * Edit a paper
     * @param object
     * @return int
     */
    @Override
    public void editAPaper(mPapers paper){
    	System.out.println(name() + "::editAPaper, paperId = " + paper.getPDECL_ID() + ", userCode = " + paper.getPDECL_User_Code());
        try {
        	begin();
           //sessionFactory.getCurrentSession().update(paper);
           getSession().update(paper);
        	commit();
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
        	e.printStackTrace();
        	rollback();
			close();
		} finally {
			flush();
			close();
		}
    }
    
    /**
     * Remove a paper
     * @param paperId
     * @return
     */
    @Override
    public int removeAPaper(int paperId){
    	//mPapers paper = new mPapers();
    	//paper.setPDECL_ID(paperId);
    	mPapers paper = loadAPaperById(paperId);
        try {
        	begin();
            //sessionFactory.getCurrentSession().delete(paper);
        	getSession().delete(paper);
        	commit();
            return 1;
        } catch (HibernateException e) {
        	//System.out.println(e.getMessage());
            e.printStackTrace();
            rollback();
            close();
        	return 0;
        }finally{
        	flush();
        	close();
        }
    }
}
