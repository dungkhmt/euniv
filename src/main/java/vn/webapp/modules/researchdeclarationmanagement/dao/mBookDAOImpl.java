package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.controller.BaseWeb;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;

@Repository("mBookDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mBookDAOImpl extends BaseDao implements mBookDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mBookDAOImpl";
    }
	@Override
	public List<mBooks> loadBookListByStaff(String userRole, String userCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mBooks.class);
			criteria.add(Restrictions.eq("BOK_UserCode", userCode));
			criteria.addOrder(Order.desc("BOK_ID"));
			List<mBooks> books= criteria.list();
			System.out.println(name()+"loadBookListByStaff userCode"+userCode);
			System.out.println(name()+"loadBookListByStaff"+books.size());
			commit();
			return books;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
		
	}
	@Override
	public mBooks loadABookByIdAndUserCode(String userRole, String userCode, int bookId) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mBooks.class);
			criteria.add(Restrictions.eq("BOK_ID", bookId));
			if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("BOK_UserCode", userCode));
            }
			mBooks book= (mBooks) criteria.uniqueResult();
			commit();
			return book;
		}catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally{
			flush();
			close();
			
		}
		
	}
	@Override
	public void editABook(mBooks book) {
		// TODO Auto-generated method stub
		 try {
	           begin();
	           getSession().update(book);
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
	public int saveABook(mBooks book) {
		// TODO Auto-generated method stub
		try{
			
			begin();
			int id = 0;
			id = (int)getSession().save(book);
			commit();
			return id;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally{
			flush();
			close();
		}
	}
	@Override
	public mBooks loadABookById(int bookId) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mBooks.class);
			criteria.add(Restrictions.eq("BOK_ID", bookId));
			mBooks book = (mBooks) criteria.uniqueResult();
			commit();
			return book;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	@Override
	public int removeABook(int bookId) {
		// TODO Auto-generated method stub
		mBooks book = new mBooks();
		book.setBOK_ID(bookId);
		try{
			begin();
			getSession().delete(book);
			commit();
			return 1;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return 0;
		}finally{
			flush();
			close();
		}
	}
	@Override
	public List<mBooks> loadBookListSummary(String bookStaff, String bookAcademicYear) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mBooks.class);
			if(bookStaff != null && (!"".equals(bookStaff))){
				criteria.add(Restrictions.eq("BOK_UserCode", bookStaff));
			}
			if(bookAcademicYear != null && (!"".equals(bookAcademicYear))){
				criteria.add(Restrictions.eq("BOK_ReportingAcademicDate", bookAcademicYear));
			}
			criteria.addOrder(Order.desc("BOK_ID"));
			List<mBooks> books = criteria.list();
			commit();
			return books;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	
}
