package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;

public interface mBookService {
	public List<mBooks>  loadBookListByStaff(String userRole,String  userCode);
	public mBooks loadABookByIdAndUserCode(String userRole,String userCode,int bookId);
	public List<mBooks> loadBookListByYear(String userRole,String userCode,String yearGenerate);
	public void editABook(String userRole,String userCode,int bookId,String bookName,String authors,String publicationName,String ISBN,int bookMonth,int bookYear,String bookSourceUploadFile,String bookReportingAcademicDate,String[] projectMembers);
	public int saveABook(String userCode, String bookName, String bookPublisher, int bookMonth, int bookYear, 
			String bookAuthors, String bookISBN, String bookSourceFile, String[] projectMembers, String bookReportingAcademicDate);
	public mBooks loadABookById(int bookId);
	public int removeABook(int bookId);
	
}
