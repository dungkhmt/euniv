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
import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
@Repository("bookStaffsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class BookStaffsDAOImpl extends BaseDao implements BookStaffsDAO {
	 @Autowired
	 private SessionFactory sessionFactory;

	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	 }

	@Override
	public List<BookStaffs> loadBookListByBookCode(String bookCode) {
		// TODO Auto-generated method stub
		try {
            begin();
            
            Criteria criteria = getSession().createCriteria(BookStaffs.class);
            criteria.add(Restrictions.eq("BOKSTF_BookCode", bookCode));
            criteria.addOrder(Order.desc("BOKSTF_StaffCode"));
            List<BookStaffs> books = criteria.list();
            commit();
            return books;
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
	public int saveABookStaff(BookStaffs bookStaff) {
		// TODO Auto-generated method stub
		try {
	           begin();
	           int id = 0; 
	           id = (int)getSession().save(bookStaff);
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
	public int removeABookStaff(int bookStaffId) {
		BookStaffs bookStaff = new BookStaffs();
    	bookStaff.setBOKSTF_ID(bookStaffId); 
        try {
            begin();
            getSession().delete(bookStaff);
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
