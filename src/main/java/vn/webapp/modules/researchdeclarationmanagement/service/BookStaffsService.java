package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;

public interface BookStaffsService {
	public List<BookStaffs> loadBookListByBookCode(String bookCode);
	public int removeABookStaff(int BOKSTF_ID);
}
