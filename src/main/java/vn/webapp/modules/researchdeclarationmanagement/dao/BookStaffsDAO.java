package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;

public interface BookStaffsDAO {
	public List<BookStaffs> loadBookListByBookCode(String bookCode);
	public int saveABookStaff(BookStaffs bookStaff);
	public int removeABookStaff(int bookStaffId);
}
