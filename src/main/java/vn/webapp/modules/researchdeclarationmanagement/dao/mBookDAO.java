package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;

public interface mBookDAO {
	public List<mBooks>  loadBookListByStaff(String userRole,String  userCode);
	public mBooks loadABookByIdAndUserCode(String userRole,String userCode,int bookId);
	public List<mBooks> loadBookListByYear(String userRole,String userCode,String yearGenerate);
	public void editABook(mBooks book);
	public int saveABook(mBooks book);
	public mBooks loadABookById(int bookId);
	public int removeABook(int bookId);
	
}
