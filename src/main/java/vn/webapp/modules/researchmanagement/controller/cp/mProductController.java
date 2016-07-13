/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.researchmanagement.controller.cp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchmanagement.model.mProducts;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.service.mProductService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.researchmanagement.validation.mProductValidation;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmProduct")
@RequestMapping(value = { "/cp" })
public class mProductController extends BaseWeb {
	@Autowired
	private tProjectService tProjectService;

	@Autowired
	private mProductService productService;
	
	@Autowired
	private nProjectService threadService;

	@Autowired
	private tProjectCategoryService tProjectCategoryService;

	@Autowired
	private mJournalService journalService;

	@Autowired
	private mStaffService staffService;

	@Autowired
	private mDepartmentService departmentService;

	@Autowired
	private mPatentService patentService;

	@Autowired
	private mAcademicYearService academicYearService;

	@Autowired
	private mProjectStatusService projectStatusService;

	static final String status = "active";
	
	/**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
    
    static String sUserCode = "";
    static String sUserRole = "";


	/**
	 * Show list all threads
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String topicsList(ModelMap model, HttpSession session) {
		// Get main user info
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		// Get topic's category
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		// Get list project statuses
		List<mProjectStatus> projectStatuses = projectStatusService.list();
		// Get list products
		List<mProducts> productsList = productService.loadProductsListByStaff(userRole, userCode);
		
		// Put data back to view
		model.put("threadsList", threadsList);
		model.put("projectStatuses", projectStatuses);
		model.put("productsList", productsList);
		model.put("products", status); 
		return "cp.products";
	}

	/**
	 * Adding a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-a-product", method = RequestMethod.GET)
	public String addAThread(ModelMap model, HttpSession session) {
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		// Get topic's category
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		// Get list project statuses
		List<mProjectStatus> projectStatuses = projectStatusService.list();


		List<mThreads> submittedThread = new ArrayList<mThreads>();
		for(mThreads t : threadsList){
			if(t.getPROJ_Status_Code().equals("SUBMIT"))
				submittedThread.add(t);
		}
		// Put data back to view
		model.put("threadsList", submittedThread);
		model.put("projectStatuses", projectStatuses);
		model.put("productFormAdd", new mProductValidation());
		model.put("thread", status);
		return "cp.addAProduct";
	}

	/**
	 * Save a product
	 * 
	 * @param productValid
	 * @param result
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "save-a-product", method = RequestMethod.POST)
	public String saveAProduct( HttpServletRequest request, @Valid @ModelAttribute("productFormAdd") mProductValidation productValid, BindingResult result, Map model, HttpSession session) {
		
		// Get current user name and role
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		
		// Get topic's category
		List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		// Get list project statuses
		List<mProjectStatus> projectStatuses = projectStatusService.list();

		// Put data back to view
		model.put("threadsList", threadsList);
		model.put("projectStatuses", projectStatuses);
		model.put("product", status);
		if (result.hasErrors()) {
			return "cp.addAProduct";
		} else {
			// Prepare data for inserting DB
			String productName 			= productValid.getproductName();
			String productStartDate 	= productValid.getproductStartDate();
			String productEndDate 		= productValid.getproductEndDate();
			String productProject 		= productValid.getproductCatCode();
			String productStatus 		= productValid.getproductStatus();
			int productBudget 			= productValid.getproductBudget();
			String productCode 			= "product";
			String productSourceUploadFileSrc = "";

			// Creating Date in java with today's date.
			Date currentDate = new Date();
			// change date into string yyyyMMdd format example "20110914"
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("HHmmssddMMyyyy");
			String sCurrentDate = dateformatyyyyMMdd.format(currentDate);

			productCode = productCode + userCode.concat(sCurrentDate);
			/**
			 * Uploading file
			 */
			/*
			MultipartFile productSourceUploadFile = productValid.getproductSourceFile();
			String fileName = productSourceUploadFile.getOriginalFilename();
			if (fileName != "") {
				try {
					byte[] bytes = productSourceUploadFile.getBytes();
					String path = request.getServletContext().getRealPath("uploads");
					File dir = new File(path + "/products");
					if (!dir.exists()) {
						dir.mkdirs();
					}

					// Create a file
					String currentUserName = session.getAttribute("currentUserName").toString();
					fileName = currentUserName + "_" + sCurrentDate + "_" + fileName;
					File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
					if (serverFile != null) {
						productSourceUploadFileSrc = dir.getAbsolutePath() + File.separator + fileName;
					}
					// Save data into file
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					model.put("status", "You failed to upload " + fileName);
				}
			}
			*/
			
			int i_InsertAProduct = productService.saveAProduct(userCode, productName, productProject, productStartDate, productEndDate,
																productStatus, productBudget, productCode,productSourceUploadFileSrc);
			if (i_InsertAProduct > 0) {
				model.put("status", "Successfully saved a topic");
			}
			return "cp.addAProduct";
		}
	}


	@RequestMapping("/productdetail/{id}")
	public String editAProduct(ModelMap model, @PathVariable("id") int productId, HttpSession session) {

		String userRole = session.getAttribute("currentUserRole").toString();
		String userCode = session.getAttribute("currentUserCode").toString();
		mProducts product = productService.loadAProductByIdAndUserCode(userRole, userCode, productId);
		// Get list statues
		List<mProjectStatus> projectStatuses = projectStatusService.list();
		// Put data back to view
		model.put("projectStatuses", projectStatuses);
		model.put("thread", status);
		if (product != null) {
			// Get topic's category
			List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
			// Put journal list and topic category to view
			model.put("threadsList", threadsList);
			model.put("productFormEdit", new mProductValidation());
			model.put("productId", productId);
			model.put("productThreadCode", product.getThread().getPROJ_Code());
			model.put("productCode", product.getPROD_Code());
			model.put("productName", product.getPROD_Name());
			model.put("productEndDate", product.getPROD_EndDate());
			model.put("productStartDate", product.getPROD_StartDate());
			model.put("productStatus", product.getPROD_Status_Code());
			model.put("productBudget", product.getPROD_Budget());
			return "cp.editAProduct";
		}
		return "cp.notFound404";
	}
	
	 /**
	 * Editing a thread
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/edit-a-product", method = RequestMethod.POST)
	 public String updateAThead(HttpServletRequest request, @Valid @ModelAttribute("productFormEdit") mProductValidation productValid, BindingResult result, Map model, HttpSession session) {
	
		 // Get current user name and role
		 String userCode = session.getAttribute("currentUserCode").toString();
		 String userRole = session.getAttribute("currentUserRole").toString();
		
		 // Get topic's category
		 List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		 // Get list project statuses
		 List<mProjectStatus> projectStatuses = projectStatusService.list();

		 // Put data back to view
		 model.put("threadsList", threadsList);
		 model.put("projectStatuses", projectStatuses);
		 model.put("product", status);
		 if (result.hasErrors()) {
			 return "cp.editAProduct";
		 }else
		 {
			 // Prepare data for inserting DB
			 // Prepare data for inserting DB
			 String productName 		= productValid.getproductName();
			 String productStartDate 	= productValid.getproductStartDate();
			 String productEndDate 		= productValid.getproductEndDate();
			 String productProject 		= productValid.getproductCatCode();
			 String productStatus 		= productValid.getproductStatus();
			 int productBudget 			= productValid.getproductBudget();
			 String productCode 		= productValid.getproductCode();
			 int productId				= productValid.getproductId();
			 String productSourceUploadFileSrc = "";
			 /**
			 * Uploading file
			 */
			 
			 /*
			 MultipartFile productSourceUploadFile = productValid.getproductSourceFile();
			 String fileName = productSourceUploadFile.getOriginalFilename();
			 try {
				 // Creating Date in java with today's date.
				 Date currentDate = new Date();
				 // change date into string yyyyMMdd format example "20110914"
				 SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("HHmmssddMMyyyy");
				 String sCurrentDate = dateformatyyyyMMdd.format(currentDate);
				 
				 byte[] bytes = productSourceUploadFile.getBytes();
				 String path = request.getServletContext().getRealPath("uploads");
				 File dir = new File(path + "/products");
				 if (!dir.exists()) {
					dir.mkdirs();
				 }
				 if (!fileName.equals("")) {
					// Create a file
					String currentUserName = session.getAttribute("currentUserName").toString();
					fileName = currentUserName + "_" + sCurrentDate + "_" + fileName;
					System.out.println("file name :" + fileName);
					File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
					// Save data into file
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					if(serverFile.exists()){
						productSourceUploadFileSrc = dir.getAbsolutePath()+ File.separator + fileName;
					}
				 }
			 } catch (Exception e) {
				model.put("status", "You failed to upload " + fileName);
			 }
			 */
			 
			 productService.editAProduct(userRole, userCode, productName, productProject, productStartDate, productEndDate,
											productStatus, productBudget, productCode, productSourceUploadFileSrc, productId);
			 model.put("status", "Successfully edited product");
			 return "cp.editAProduct";
		 }
	 }
	
	 /**
	 * Remove a thread
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/remove-a-product/{id}", method = RequestMethod.GET)
	 public String removeAThread(ModelMap model, @PathVariable("id") int productId, HttpSession session) {
		 String userCode = session.getAttribute("currentUserCode").toString();
		 String userRole = session.getAttribute("currentUserRole").toString();
		 mProducts product = productService.loadAProductByIdAndUserCode(userRole, userCode, productId);
		 // Get topic's category
		 List<mThreads> threadsList = threadService.loadThreadsListByStaff(userRole, userCode);
		 // Get list project statuses
		 List<mProjectStatus> projectStatuses = projectStatusService.list();
		 
		 // Return value to view
		 model.put("threadsList", threadsList);
		 model.put("projectStatuses", projectStatuses);
		 model.put("product", status);
		 if(product != null){
			 productService.removeAProduct(productId);
			 List<mProducts> productsList = productService.loadProductsListByStaff(userRole, userCode);
			 model.put("productsList", productsList);
			 return "cp.products";
		 }
		 return "cp.notFound404";
	 }
	 
	 /**
	    * Download a file source
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/download-product/{id}", method = RequestMethod.GET)
	   public void downloadPaper(ModelMap model, @PathVariable("id") int threadId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		   String userCode = session.getAttribute("currentUserCode").toString();
		   String userRole = session.getAttribute("currentUserRole").toString();
		   model.put("threads", status);
		   mThreads thread = threadService.loadAThreadByIdAndUserCode(userRole, userCode, threadId);
		   if(thread.getPROJ_SourceFile() != null){
			   ServletContext context = request.getServletContext();
			   
			   File downloadFile = new File(thread.getPROJ_SourceFile());
			   if(downloadFile.exists()){
			       FileInputStream inputStream = new FileInputStream(downloadFile);
			       
			       String mimeType = context.getMimeType(thread.getPROJ_SourceFile());
			        if (mimeType == null) {
			            // set to binary type if MIME mapping not found
			            mimeType = "application/octet-stream";
			        }
			        
			        // set content attributes for the response
			        response.setContentType(mimeType);
			        response.setContentLength((int) downloadFile.length());
			        
			        // set headers for the response
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			        response.setHeader(headerKey, headerValue);
			        
			        // get output stream of the response
			        OutputStream outStream = response.getOutputStream();
			 
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			 
			        // write bytes read from the input stream into the output stream
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			 
			        inputStream.close();
			        outStream.close();
			   }
		   }
		   
	   }
}
