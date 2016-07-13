/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : December 27th, 2015
 */
package vn.webapp.modules.researchmanagement.controller.cp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

import vn.webapp.controller.BaseWeb;
import vn.webapp.libraries.DateUtil;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.service.mAcademicYearService;
import vn.webapp.modules.researchdeclarationmanagement.service.mJournalService;
import vn.webapp.modules.researchdeclarationmanagement.service.mPatentService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectCategoryService;
import vn.webapp.modules.researchdeclarationmanagement.service.tProjectService;
import vn.webapp.modules.researchmanagement.model.mProducts;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.researchmanagement.service.mProductService;
import vn.webapp.modules.researchmanagement.service.mProjectCallsService;
import vn.webapp.modules.researchmanagement.service.mProjectStatusService;
import vn.webapp.modules.researchmanagement.service.nProjectService;
import vn.webapp.modules.researchmanagement.validation.mProductValidation;
import vn.webapp.modules.researchmanagement.validation.mProjectCallsValidation;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmProjectCalls")
@RequestMapping(value = { "/cp" })
public class mProjectCallsController extends BaseWeb {
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
	private mProjectCallsService projectCallsService;

	@Autowired
	private mProjectStatusService projectStatusService;

	static final String status = "active";

	/**
	 * Show list all threads
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/project-call-open", method = RequestMethod.GET)
	public String projectCallsList(ModelMap model, HttpSession session) {
		// Get list of project calls
		List<mProjectCalls> projectCallsList = projectCallsService.loadProjectCallsList();
		
		// Put data back to view
		model.put("projectCallsList", projectCallsList);
		model.put("projectcalls", status);
		return "cp.projectcalls";
	}

	/**
	 * Adding a thread
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-a-projectcall", method = RequestMethod.GET)
	public String addAProjectCall(ModelMap model, HttpSession session) {

		// Get topic's category
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();
		
		model.put("topicCategory", topicCategory);
		model.put("projectCallFormAdd", new mProjectCallsValidation());
		model.put("projectcalls", status);
		return "cp.addAProjectCall";
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
	public String name(){
		return "mProjectCallController";
	}
	@RequestMapping(value = "save-a-projectcall", method = RequestMethod.POST)
	public String saveAProjectCall( HttpServletRequest request, 
			@Valid @ModelAttribute("projectCallFormAdd") mProjectCallsValidation projectCallValid, 
			BindingResult result, Map model, HttpSession session) {
		
		String userCode = (String)session.getAttribute("currentUserCode");
		System.out.println(name() + "::saveAProjectCall, userCode = " + userCode);
		// Get topic's category
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();

		
		// Put data back to view
		model.put("topicCategory", topicCategory);
		model.put("projectcalls", status);
		if (result.hasErrors()) {
			return "cp.addAProjectCall";
		} else {
			// Prepare data for inserting DB
			String PROJCALL_NAME 		= projectCallValid.getProjectCallName();
			String sPROJCALL_CODE 		= projectCallValid.getProjectCallCode();
			mProjectCalls projectCalls = projectCallsService.loadAProjectCallByName(PROJCALL_NAME);
			
			if(projectCalls != null){
				model.put("err", "Đợt gọi đề tài " + PROJCALL_NAME + " đã tồn tại");
				return "cp.addAProjectCall";
			}
			projectCalls = projectCallsService.loadAProjectCallByCode(sPROJCALL_CODE);
			if(projectCalls != null){
				model.put("err", "Mã Đợt gọi đề tài " + sPROJCALL_CODE + " đã tồn tại");
				return "cp.addAProjectCall";
			}
			
				String PROJCALL_PROJCATCODE = projectCallValid.getProjectCallCatCode();
				String PROJCALL_DATE 		= DateUtil.s_fConvertDateFormatType1(projectCallValid.getProjectCallDate());
				//String sPROJCALL_CODE 		= projectCallValid.getProjectCallCode();//"T"+DateUtil.s_fGetCurrentDate();
				String sPROJCALL_STATUS 	= projectCallValid.getProjectCallStatus();
				System.out.println(name() + "::saveAProjectCall, userCode = " + userCode + 
						", projectCallCode = " + sPROJCALL_CODE + 
						", projectCallName = " + PROJCALL_NAME);
				int i_InsertAProjectCall 	= projectCallsService.saveAProjectCall(sPROJCALL_CODE, PROJCALL_PROJCATCODE, PROJCALL_NAME, PROJCALL_DATE, sPROJCALL_STATUS);
				if (i_InsertAProjectCall > 0) {
					model.put("status", "Lưu thành công");
				}else{
					model.put("err", "Lưu không thành công. Hãy thử lại.");
				}
			
			return "cp.addAProjectCall";
		}
	}


	@RequestMapping("/projectcalldetail/{id}")
	public String editAProjectCall(ModelMap model, @PathVariable("id") int iProjectCallId, HttpSession session) {
		// Get topic's category
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();
		mProjectCalls projectCalls = projectCallsService.loadAProjectCallById(iProjectCallId);
		
		// Put data back to view
		model.put("topicCategory", topicCategory);
		model.put("projectcalls", status);
		if (projectCalls != null) {
			String sProjectCallDate = DateUtil.s_fConvertDateFormatType2(projectCalls.getPROJCALL_DATE());
			// Put journal list and topic category to view
			model.put("projectCallFormEdit", new mProjectCallsValidation());
			model.put("projectCallId", iProjectCallId);
			model.put("projectCalls", projectCalls);
			model.put("sProjectCallDate", sProjectCallDate);
			return "cp.editAProjectCall";
		}
		return "cp.notFound404";
	}
	
	 /**
	 * Editing a thread
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/edit-a-projectcall", method = RequestMethod.POST)
	 public String updateAProjectCall(HttpServletRequest request, @Valid @ModelAttribute("projectCallFormEdit") mProjectCallsValidation projectCallValid, BindingResult result, Map model, HttpSession session) {
		
		// Get topic's category
		List<mTopicCategory> topicCategory = tProjectCategoryService.list();

		String userCode = (String)session.getAttribute("currentUserCode");
		
		 // Put data back to view
		 model.put("topicCategory", topicCategory);
		 model.put("projectcalls", status);
		 if (result.hasErrors()) {
			 return "cp.editAProjectCall";
		 }else
		 {
			 // Prepare data for inserting DB
			 int iPROJCALL_ID				= projectCallValid.getProjectCallId();
			 String sPROJCALL_NAME 			= projectCallValid.getProjectCallName();
			 String sPROJCALL_CODE = projectCallValid.getProjectCallCode();//"T"+o_fFormatDateByFormat.getYear()+iPROJCALL_ID;
			 
			 int iIsExisting = projectCallsService.checkingExistProjectCallByName(iPROJCALL_ID, sPROJCALL_NAME, sPROJCALL_CODE);
			 if(iIsExisting == 0)
			 {
				 String sPROJCALL_DATE 			= DateUtil.s_fConvertDateFormatType1(projectCallValid.getProjectCallDate());
				 String sPROJCALL_PROJCATCODE 	= projectCallValid.getProjectCallCatCode();
				 String sPROJCALL_STATUS		= projectCallValid.getProjectCallStatus();
				 
				 LocalDate o_fFormatDateByFormat = DateUtil.o_fFormatDateByFormatType1(projectCallValid.getProjectCallDate());
				 

					System.out.println(name() + "::updateAProjectCall, userCode = " + userCode + 
							", projectCallCode = " + sPROJCALL_CODE + 
							", projectCallName = " + sPROJCALL_NAME);

				 projectCallsService.editAProjectCall(iPROJCALL_ID, sPROJCALL_CODE, sPROJCALL_PROJCATCODE, sPROJCALL_NAME, sPROJCALL_DATE, sPROJCALL_STATUS);
				 
				 model.put("status", "Chỉnh sửa thành công.");
			 }else{
				 model.put("err", "Đợt gọi đề tài đã tồn tại");
			 }
			 
			 return "cp.editAProjectCall";
		 }
	 }
	
	 /**
	 * Remove a thread
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/remove-a-projectcall/{id}", method = RequestMethod.GET)
	 public String removeAProjectCall(ModelMap model, @PathVariable("id") int iProjectCallId, HttpSession session) {
		 
		 mProjectCalls projectCalls = projectCallsService.loadAProjectCallById(iProjectCallId);
		 // Return value to view
		 model.put("projectcalls", status);
		 if(projectCalls != null){
			 projectCallsService.removeAProjectCall(iProjectCallId);
			// Get topic's category
			List<mProjectCalls> projectCallsList = projectCallsService.loadProjectCallsList();
			
			// Put data back to view
			model.put("projectCallsList", projectCallsList);
			return "cp.projectcalls";
		 }
		 return "cp.notFound404";
	 }
	 
}
