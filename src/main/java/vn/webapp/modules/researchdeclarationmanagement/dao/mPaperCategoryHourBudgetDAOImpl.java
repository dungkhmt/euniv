package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;

@Repository("mPaperCategoryHourBudgetDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mPaperCategoryHourBudgetDAOImpl extends BaseDao implements mPaperCategoryHourBudgetDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * @param null
     * @return List
     */
    @Override
    public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgets(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPapersCategoryHourBudget.class, "paperCategoryBudget");
            List<mPapersCategoryHourBudget> paperCategoryBudget = criteria.list();
            commit();
            return paperCategoryBudget;
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
     * @param null
     * @return List
     */
    @Override
    public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgetByYear(String reportingrYear)
    {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPapersCategoryHourBudget.class, "paperCategoryBudget");
            criteria.add(Restrictions.eq("paperCategoryBudget.PCAHOBUD_AcademicYearCode", reportingrYear));
            List<mPapersCategoryHourBudget> paperCategoryBudget = criteria.list();
            commit();
            return paperCategoryBudget;
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
     * @param null
     * @return List
     */
    @Override
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCategoryAndYear(String paperCategoryCode, String reportingrYear){
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mPapersCategoryHourBudget.class, "paperCategoryBudget");
            criteria.add(Restrictions.eq("paperCategoryBudget.PCAHOBUD_PaperCategoryCode", paperCategoryCode));
            criteria.add(Restrictions.eq("paperCategoryBudget.PCAHOBUD_AcademicYearCode", reportingrYear));
            mPapersCategoryHourBudget paperCategoryBudget = (mPapersCategoryHourBudget) criteria.uniqueResult();
            commit();
            return paperCategoryBudget;
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
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCode(String code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPapersCategoryHourBudget.class, "paperCategoryBudget");
            criteria.add(Restrictions.eq("paperCategoryBudget.PCAHOBUD_Code", code));
            mPapersCategoryHourBudget paperCategoryBudget = (mPapersCategoryHourBudget) criteria.uniqueResult();
            commit();
            return paperCategoryBudget;
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
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetById(int id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mPapersCategoryHourBudget.class, "paperCategoryBudget");
            criteria.add(Restrictions.eq("paperCategoryBudget.PCAHOBUD_ID", id));
            mPapersCategoryHourBudget paperCategoryBudget = (mPapersCategoryHourBudget) criteria.uniqueResult();
            commit();
            return paperCategoryBudget;
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
