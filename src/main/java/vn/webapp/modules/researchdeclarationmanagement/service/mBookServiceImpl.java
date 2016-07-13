package vn.webapp.modules.researchdeclarationmanagement.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchdeclarationmanagement.dao.BookStaffsDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.mBookDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.usermanagement.dao.mUserDAO;
import vn.webapp.modules.usermanagement.model.mUsers;

@Service("mBookService")
public class mBookServiceImpl implements mBookService {
	@Autowired
	private mBookDAO bookDAO;
	@Autowired
	private BookStaffsDAO bookStaffsDAO;
	@Autowired
	private mUserDAO userDao;
	
	
	@Override
	public List<mBooks> loadBookListByStaff(String userRole, String userCode) {
		// TODO Auto-generated method stub
		return bookDAO.loadBookListByStaff(userRole, userCode);
	}
	@Override
	public mBooks loadABookByIdAndUserCode(String userRole, String userCode, int bookId) {
		// TODO Auto-generated method stub
		return bookDAO.loadABookByIdAndUserCode(userRole, userCode, bookId);
	}
	@Override
	public void editABook(String userRole, String userCode, int bookId,String bookName, String authors, String publicationName,
			String ISBN, int bookMonth, int bookYear,String bookSourceUploadFile,String[] projectMembers) {
		// TODO Auto-generated method stub
		mBooks book= bookDAO.loadABookByIdAndUserCode(userRole, userCode, bookId);
		if(book!=null){
			book.setBOK_ID(bookId);
			book.setBOK_BookName(bookName);
			book.setBOK_Authors(authors);
			book.setBOK_Publisher(publicationName);
			book.setBOK_PublishedMonth(bookMonth);
			book.setBOK_PublishedYear(bookYear);
			book.setBOK_ISBN(ISBN);
			book.setBOK_UserCode(userCode);
			book.setBOK_Code(book.getBOK_Code());
			book.setBOK_ReportingAcademicDate(book.getBOK_ReportingAcademicDate());
			if(bookSourceUploadFile.equals("")){
				book.setBOK_SourceFile(book.getBOK_SourceFile());
			} else {
				String sOldSourceFile= book.getBOK_SourceFile();
				if((sOldSourceFile!=null)){
					File oldFile = new File(sOldSourceFile);
			   		oldFile.delete();
				}
				
				book.setBOK_SourceFile(bookSourceUploadFile);
			}
			bookDAO.editABook(book);
			if(projectMembers.length > 0){
	    		String BOKSTF_BookCode = book.getBOK_Code();
	    		String BOKSTF_Code = "";
	    		System.out.println(name()+"bookcode" +BOKSTF_BookCode);
	    		List<BookStaffs> oldBookStaffsList = bookStaffsDAO.loadBookListByBookCode(BOKSTF_BookCode);
	    		if(oldBookStaffsList != null && oldBookStaffsList.size() > 0)
	    		{
	    			for (BookStaffs bookStaff : oldBookStaffsList) {
	    				bookStaffsDAO.removeABookStaff(bookStaff.getBOKSTF_ID());
					}
	    		}
		    	BookStaffs bookStaffs = new BookStaffs();
	            for (String projectMember : projectMembers) {
	            	BOKSTF_Code = projectMember+BOKSTF_BookCode;
	            	bookStaffs.setBOKSTF_Code(BOKSTF_Code);
	            	bookStaffs.setBOKSTF_BookCode(BOKSTF_BookCode);
	            	bookStaffs.setBOKSTF_StaffCode(projectMember);
		    	    bookStaffsDAO.saveABookStaff(bookStaffs);
		    	}
	    	}
		}
		
	}
	
	@Override
	public int saveABook(String userCode, String bookName,
			String bookPublisher, int bookMonth, int bookYear,
			String bookAuthors, String bookISBN, String bookSourceFile, String[] projectMembers) {
		// TODO Auto-generated method stub
		mUsers user = userDao.getByUsername(userCode);
		
		if(user.getUser_Code() != null){
			mBooks book = new mBooks();
			book.setBOK_BookName(bookName);
			book.setBOK_UserCode(userCode);
			book.setBOK_Publisher(bookPublisher);
			book.setBOK_SourceFile(bookSourceFile);
			book.setBOK_Authors(bookAuthors);
			book.setBOK_ISBN(bookISBN);
			book.setBOK_PublishedMonth(bookMonth);
			book.setBOK_PublishedYear(bookYear);
			
			int id_saveABook = bookDAO.saveABook(book);
			
			if(id_saveABook > 0){
				String BOK_Code = user.getUser_Code() + id_saveABook;
				
				book.setBOK_Code(BOK_Code);
				bookDAO.editABook(book);
				
				if(projectMembers != null && projectMembers.length > 0){
					String BOKSTF_Code = "";
					BookStaffs bookStaffs = new BookStaffs();
					for(String projecMember : projectMembers){
						BOKSTF_Code = projecMember + BOK_Code;
						bookStaffs.setBOKSTF_Code(BOKSTF_Code);
						bookStaffs.setBOKSTF_StaffCode(projecMember);
						bookStaffs.setBOKSTF_BookCode(BOK_Code);
						bookStaffsDAO.saveABookStaff(bookStaffs);
					}
				}
			}
			
			return id_saveABook;
		}
		
		return 0;
	}
	@Override
	public mBooks loadABookById(int bookId) {
		// TODO Auto-generated method stub
		return bookDAO.loadABookById(bookId);
	}
	@Override
	public int removeABook(int bookId) {
		// TODO Auto-generated method stub
		return bookDAO.removeABook(bookId);
	}
	String name(){
		return "mBookServiceImpl";
	}
}
