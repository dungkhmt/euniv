package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.BookStaffsDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.PaperStaffsDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;

@Service("bookStaffsService")
public class BookStaffsServiceImpl implements BookStaffsService{
	@Autowired
	private BookStaffsDAO dao;
	@Override
	public List<BookStaffs> loadBookListByBookCode(String bookCode) {
		// TODO Auto-generated method stub
		return dao.loadBookListByBookCode(bookCode);
	}
	@Override
	public int removeABookStaff(int BOKSTF_ID) {
		// TODO Auto-generated method stub
		return dao.removeABookStaff(BOKSTF_ID);
	}

}
