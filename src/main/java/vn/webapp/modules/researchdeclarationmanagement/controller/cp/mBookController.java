package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.model.BookStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.service.BookStaffsService;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mBookService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPaperService;
import vn.webapp.modules.researchdeclarationmanagement.validation.mBookValidation;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;
@Controller("cpmBook")
@RequestMapping(value = {"/cp"})
public class mBookController extends BaseWeb {
	static final String status = "active";
	@Autowired
	private mStaffService staffService;
	@Autowired
	private mBookService bookService;
	@Autowired
    private mAcademicYearService academicYearService;
	@Autowired
    private mFacultyService facultyService;
	@Autowired
	private mPaperService	paperStaffsSrv;
	@Autowired
	private BookStaffsService bookStaffsService;
	String name(){
		return "mBookController";
	}
    /**
    * Show list all books
    * @param model
    * @return 
    */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(ModelMap model, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		System.out.println(name()+"userCode "+userCode);
		List<mBooks> booksList= bookService.loadBookListByStaff(userRole, userCode);
		List<mStaff> staffs = staffService.listStaffs();
		HashMap<String, String> mStaffCode2Name = new HashMap<String, String>();
		for(mStaff st: staffs)
			   mStaffCode2Name.put(st.getStaff_Code(), st.getStaff_Name());
		for(mBooks b: booksList)
			   b.setBOK_UserCode(mStaffCode2Name.get(b.getBOK_UserCode()));
		System.out.println(booksList);
		model.put("booksList", booksList);
		//
		model.put("books", status);
		return "cp.books";
	}
	
	/**
	    * view deital a book
	    * @param model
	    * @return 
	    */
	@RequestMapping("/bookdetail/{id}")
	public String editAPaper(ModelMap model, @PathVariable("id") int bookId, HttpSession session){
		List<mAcademicYear> bookReportingAcademicDateList = academicYearService.list();
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		List<mStaff> staffList = staffService.listStaffs();
		model.put("bookReportingAcademicDateList", bookReportingAcademicDateList);
		model.put("books", status);
		model.put("listFaculty", listFaculty);
		model.put("staffList", staffList);
		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mBooks book = bookService.loadABookByIdAndUserCode(userRole, userCode, bookId);
		if(book!=null){
			List<BookStaffs> listBookStaffs = bookStaffsService.loadBookListByBookCode(book.getBOK_Code());
			String fileUploadName = book.getBOK_SourceFile();
			
			model.put("bookName", book.getBOK_BookName());
			model.put("bookPublisher", book.getBOK_Publisher());
			model.put("bookYear", book.getBOK_PublishedYear());
			model.put("bookMonth",book.getBOK_PublishedMonth());
			model.put("bookAuthorList", book.getBOK_Authors());
			model.put("listBookStaffs", listBookStaffs);
			model.put("bookISBN", book.getBOK_ISBN());
			model.put("bookFileUpload", fileUploadName);
			model.put("bookFormEdit", new mBookValidation());
			model.put("bookId", bookId);
			model.put("bookReportingAcademicDate",book.getBOK_ReportingAcademicDate());
			return "cp.editABook";
		}
		return "cp.notFound404";
	}
	/**
	    * edit a book
	    * @param model
	    * @return
	    */
	@RequestMapping(value ="/edit-a-book", method= RequestMethod.POST)
	public String updateABook(HttpServletRequest request, @Valid @ModelAttribute("bookFormEdit") mBookValidation bookFormEdit,HttpSession session,BindingResult result, Map model){
		String userRole = session.getAttribute("currentUserRole").toString();
	   	String userCode = session.getAttribute("currentUserCode").toString();
	   	
		if(result.hasErrors()){
			/*
			 * wrong put back to view
			 */
			List<mAcademicYear> patentReportingAcademicDateList = academicYearService.list();
			List<mFaculty> listFaculty = facultyService.loadFacultyList();
			List<mStaff> staffList = staffService.listStaffs();
			model.put("patentReportingAcademicDateList", patentReportingAcademicDateList);
			model.put("books", status);
			model.put("listFaculty", listFaculty);
			model.put("staffList", staffList);
			int bookId = bookFormEdit.getBookId();
			mBooks book = bookService.loadABookByIdAndUserCode(userRole, userCode, bookId);
			List<BookStaffs> listBookStaffs = new ArrayList();
			if(book != null){
				   listBookStaffs = bookStaffsService.loadBookListByBookCode(book.getBOK_Code());
			}
			model.put("bookName", bookFormEdit.getBookName());
			model.put("bookPublisher", bookFormEdit.getBookPublisher());
			model.put("bookYear", bookFormEdit.getBookYear());
			model.put("bookMonth",bookFormEdit.getBookMonth());
			model.put("bookAuthorList", bookFormEdit.getBookAuthorList());
			model.put("listBookStaffs", listBookStaffs);
			model.put("bookISBN", bookFormEdit.getBookISBN());
			model.put("bookReportingAcademicDate",book.getBOK_ReportingAcademicDate());
			//model.put("bookFileUpload", fileUploadName);
			model.put("bookFormEdit", new mBookValidation());
			System.out.println("1");
			return "cp.editABook";
			
		} else {
			/*
			 * Sucess update database
			 */
			System.out.println(2);
			/**
			 *Upload file 
			 */
			
			MultipartFile bookSourceUploadFile = bookFormEdit.getBookFileUpload();
			String fileName= bookSourceUploadFile.getOriginalFilename();
			String bookSourceUploadFileSrc = "";
			//String bookCate= bookFormEdit.get
			try{
				mBooks book = bookService.loadABookByIdAndUserCode(userRole, userCode, bookFormEdit.getBookId());
				String[] projectMembers = request.getParameterValues("projectMembers");
		    	   if(projectMembers != null && projectMembers.length > 0){
				
				
				byte[] bytes= bookSourceUploadFile.getBytes();
				String path = request.getServletContext().getRealPath("uploads");
				System.out.println("BookController::editABook, path = " + path);
				
				if(!fileName.equals("")){
					fileName = establishFileNameStoredDataBase(fileName);
					String fullfilename = establishFullFileNameForUpload(fileName, userCode);
					File serverFile = new File(fullfilename);
					
					BufferedOutputStream stream= new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					bookSourceUploadFileSrc = fileName;
				} else {
					bookSourceUploadFileSrc= book.getBOK_SourceFile();
				}
				System.out.println("BookController::editABook, bookCourseUpLoadFileSrc = " + bookSourceUploadFileSrc);
				/**
	    	  	 * Prepare data for inserting DB
	    	  	 */
				String bookName= bookFormEdit.getBookName();
				String bookReportingAcademicDate= bookFormEdit.getBookReportingAcademicDate();
				String authors= bookFormEdit.getBookAuthorList();
				/*String[] bookAuthorsList 	= authors.trim().split("\\,");
				Integer numberOfAuthors 		= bookAuthorsList.length;
				for(int i=0; i<bookAuthorsList.length; i++){
			   		   if(bookAuthorsList[i].equals("")){
			   			   numberOfAuthors--;
			   		   }
		   	   		}*/
				String publicationName= bookFormEdit.getBookPublisher();
				String ISBN= bookFormEdit.getBookISBN();
				int bookMonth= Integer.parseInt(bookFormEdit.getBookMonth());
				int bookYear= bookFormEdit.getBookYear();
				int bookId= bookFormEdit.getBookId();
				bookService.editABook(userRole,userCode,bookId,bookName,authors,publicationName,ISBN,bookMonth,bookYear,bookSourceUploadFileSrc,bookReportingAcademicDate,projectMembers);
				return "redirect:" + this.baseUrl + "/cp/books.html";
		    	   } else{
		    		   model.put("err", "Cần phải thêm tác giả của sách.");
		    	   }
			}catch(Exception e){
				model.put("status", " You failed to upload " + fileName + " => " + e.getMessage());
			}
		}
		return "cp.editABook";
	}
	
	/**
	    * Add a book
	    * @param model
	    * @return 
	    */
	@RequestMapping(value = "/add-a-book")
	public String addBook(ModelMap model){
		
		List<mFaculty> listFaculty = facultyService.loadFacultyList();
		List<mStaff> listStaff = staffService.listStaffs();
		List<mAcademicYear> bookReportingAcademicDateList = academicYearService.list();
		
		model.put("listFaculty", listFaculty);
		model.put("staffList", listStaff);
		model.put("bookReportingAcademicDateList", bookReportingAcademicDateList);
		model.put("bookFormAdd", new mBookValidation());
		
		return "cp.addABook";
	}
	
	private String establishFileNameStoredDataBase(String filename){
		Date currentDate = new Date();
		SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("HHmmssddMMyyyy");
		String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
		
		return "giaotrinh-"+ sCurrentDate + "-"+filename;
	}
	
	private String establishFullFileNameForUpload(String filename, String userCode){
		String realPathtoUploads = PROJECT_ROOT_DIR + File.separator + userCode + File.separator + "books";
		File dir = new File(realPathtoUploads);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		String fullfilename = realPathtoUploads + File.separator + filename;
		
		System.out.println(name()+"::establishFullFileNameForUpload--fullfilename "+ fullfilename);
		
		return fullfilename;
	}
	
	@RequestMapping(value = "/save-a-book", method = RequestMethod.POST)
	public String saveABook(HttpServletRequest request,HttpSession session, ModelMap model, @Valid @ModelAttribute("bookFormAdd") mBookValidation bookValid, BindingResult result){
		
		String userCode = session.getAttribute("currentUserCode").toString();
		
		if(result.hasErrors()){
			
			return "cp.addABook";
		
		}else{
			
			String bookName = bookValid.getBookName();
			System.out.println(name()+"::saveABook--bookName"+bookName);
			
			String bookPublisher = bookValid.getBookPublisher();
			System.out.println(name()+"::saveABook--bookPublisher"+bookPublisher);
			
			String bookAuthors = bookValid.getBookAuthorList();
			String[] bookAuthorList = bookAuthors.trim().split(",");
			System.out.println(name()+"::saveABook--bookAuthorList"+bookAuthorList.toString());
			//int
			
			String[] projectMembers = request.getParameterValues("projectMembers");
//			System.out.println(name()+"::saveABook--projectMembers");
//	    	for(int i=0; i<projectMembers.length; i++){
//	    		System.out.print(projectMembers[i]+", ");
//	    	}
	    	
			int bookYear = bookValid.getBookYear();
			System.out.println(name()+"::saveABook--bookYear "+bookYear);
			
			int bookMonth = Integer.parseInt(bookValid.getBookMonth());
			System.out.println(name()+"::saveABook--bookMonth "+bookMonth);
			
			String bookISBN = bookValid.getBookISBN();
			System.out.println(name()+"::saveABook--bookISBN "+bookISBN);
			
			String bookReportingAcademicDate = bookValid.getBookReportingAcademicDate();
			System.out.println(name()+"::saveABook--bookReportingAcademicDate "+bookReportingAcademicDate);
			
			MultipartFile bookSourceUploadFile = bookValid.getBookFileUpload();
			String fileName = bookSourceUploadFile.getOriginalFilename();
			String bookSourceUploadFileSrc = "";
			
			try {
				
				byte[] bytes = bookSourceUploadFile.getBytes();
				fileName = establishFileNameStoredDataBase(fileName);
				String fullfilename = establishFullFileNameForUpload(fileName, userCode);
				File serverFile = new File(fullfilename);
				
				System.out
				.println(name()
						+ "::saveABook, upload file with fileName (stored in DataBase) = "
						+ fileName + ", fullfilename = "
						+ fullfilename);
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				if(serverFile.exists()){
					bookSourceUploadFileSrc = fileName;
				}
				
				int id_InsertABook = bookService.saveABook(userCode, bookName, bookPublisher, 
						bookMonth, bookYear, bookAuthors, bookISBN, fileName, projectMembers, bookReportingAcademicDate);
				
				if(id_InsertABook > 0){
					return "redirect:" + this.baseUrl + "/cp/books.html";
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.put("status", "You failed to upload " + fileName + " => " + e.getMessage());
			}
			
			return "cp.addABook";
		
		}
	}
	
	@RequestMapping(value = "/remove-a-book/{id}", method = RequestMethod.GET)
	public String removeABook(ModelMap model, @PathVariable("id") int bookId, HttpSession session){
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		mBooks book = bookService.loadABookById(bookId);
		
		if(book != null){
			bookService.removeABook(bookId);
			List<BookStaffs> listBookStaffs = bookStaffsService.loadBookListByBookCode(book.getBOK_Code());
			if(listBookStaffs != null){
				for(BookStaffs bookStaffs : listBookStaffs){
					bookStaffsService.removeABookStaff(bookStaffs.getBOKSTF_ID());
				}
			}
		}
		List<mBooks> booksList = bookService.loadBookListByStaff(userRole, userCode);
		model.put("booksList", booksList);
		
		System.out.println(name()+"::removeABook--done");
		return "cp.books";
	}
	
}
