package vn.webapp.modules.usermanagement.controller.cp;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.usermanagement.model.mAcademicRank;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mAcademicRankService;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;
import vn.webapp.modules.usermanagement.service.mUserService;
import vn.webapp.modules.usermanagement.validation.mStaffValidation;

@Controller("cpmStaff")
@RequestMapping(value = {"/cp"})
public class mStaffController extends BaseWeb {
	
	@Autowired
    private mAcademicRankService academicRankService;
	
	@Autowired
    private mStaffService staffService;
    
    @Autowired
    private mDepartmentService departmentService;
    
    @Autowired
    private mFacultyService facultyService;
    
    @Autowired
    private mUserService userService;
    
    static final String SUPER_ADMIN = "SUPER_ADMIN";

    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/profile", method = RequestMethod.GET)
   public String staffInfo(ModelMap model, HttpSession session) {
	   String currentUserName = session.getAttribute("currentUserName").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String userCode = session.getAttribute("currentUserCode").toString();
	   mStaff staff = staffService.loadStaffByUserCode(userCode);
	   
	   String currentUserFacultyCode = session.getAttribute("currentUserFaculty").toString();
	   // Add the saved validationForm to the model
	   //List<mFaculty> facultyList = this.getFacultyByUserRole(userRole, currentUserFacultyCode);
	   List<mFaculty> facultyList = facultyService.loadFacultyList();
	   List<mDepartment> departmentList = this.getDepartmentByUserRole(userRole, currentUserFacultyCode);
	   
	   model.put("staffFormEdit", new mStaffValidation());
	   if(staff != null){
		   model.put("staffEmail", staff.getStaff_Email());
		   model.put("staffName", staff.getStaff_Name());
		   model.put("staffPhone", staff.getStaff_Phone());
		   model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
		   model.put("staffDepartmentName", staff.getDepartment().getDepartment_Name());
		   model.put("staffDepartmentCode", staff.getDepartment().getDepartment_Code());
		   model.put("staffFacultyCode", staff.getStaff_Faculty_Code());
		   model.put("staffFacultyName", staff.getDepartment().getFaculty().getFaculty_Name());		   
		   model.put("staffGender", staff.getStaff_Gender());
		   model.put("staffDateOfBirth", staff.getStaff_DateOfBirth());
		   //model.put("academicRank", staff.getAcademicRank());
		   if(staff.getAcademicRank() != null){
		   model.put("academicRankCode", staff.getAcademicRank().getAcademicRank_Code());
		   model.put("academicRankName", staff.getAcademicRank().getAcademicRank_VNName());
		   }else{
			   model.put("academicRankCode", "");
			   model.put("academicRankName", "");
			      
		   }
		   
	   }
	   model.put("departmentList", departmentList);
	   model.put("facultyList", facultyList);
	   model.put("academicRankList", academicRankService.list());
	   
       return "cp.profile";
   }
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-staff-detail", method = RequestMethod.POST)
  public String saveStaffInfo(HttpServletRequest request, @Valid @ModelAttribute("staffFormEdit") mStaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {

	  String userRole = session.getAttribute("currentUserRole").toString();
	  String currentUserFacultyCode = session.getAttribute("currentUserFaculty").toString();
	  // Add the saved validationForm to the model
	  //List<mFaculty> facultyList = this.getFacultyByUserRole(userRole, currentUserFacultyCode);
	  List<mFaculty> facultyList = facultyService.loadFacultyList();
	  List<mDepartment> departmentList = this.getDepartmentByUserRole(userRole, currentUserFacultyCode);
	  String staffEmail = staffFormEdit.getStaffEmail();
	  String staffName = staffFormEdit.getStaffName();
	  String staffPhone = staffFormEdit.getStaffPhone();
	  String staffDepartment = staffFormEdit.getStaffDepartment();
	  String staffFaculty = staffFormEdit.getStaffFaculty();
	  String staffGender = staffFormEdit.getStaffGender();
	  String staffDateOfBirth = staffFormEdit.getStaffDateOfBirth();
	  String staffFacultyCode = staffFormEdit.getStaffFaculty();
	  String staffAcademicRankCode = staffFormEdit.getStaffAcademicRank();
	  
	  // Re-edit in case of error
	  String staffOldFacultyName = request.getParameter("staffOldFacultyName");
	  String staffOldDepartmentName = request.getParameter("staffOldDepartmentName");
	  String staffOldAcademicRank = request.getParameter("staffOldAcademicRank");
	  String staffOldName = request.getParameter("staffOldName");
	  String staffOldEmail = request.getParameter("staffOldEmail");
	  String staffOldPhone = request.getParameter("staffOldPhone");

	  model.put("staffEmail", (!"".equals(staffEmail)) ? staffEmail : staffOldEmail);
	  model.put("staffName", (!"".equals(staffName)) ? staffName : staffOldName);
	  model.put("staffPhone", (!"".equals(staffPhone)) ? staffPhone : staffOldPhone);
	  model.put("facultyList", facultyList);
	  model.put("academicRankList", academicRankService.list());
	  model.put("staffDateOfBirth", staffDateOfBirth);
	  model.put("staffGender", staffGender);
	  model.put("staffFacultyCode", staffFacultyCode);
	  model.put("staffDepartmentCode", staffDepartment);
	  model.put("departmentList", departmentList);
	  
	  //mAcademicRank academicRank = academicRankService.loadByCode(staffAcademicRankCode);
	  //model.put("academicRank", academicRankService.loadByCode(staffAcademicRankCode));
	  model.put("academicRankCode", staffAcademicRankCode);
	  //model.put("academicRankName", academicRank.getAcademicRank_VNName());
	  
	  if(result.hasErrors()) {
		  String errorName = result.getFieldError().getField();
		  if("staffFaculty".equals(errorName) || "staffDepartment".equals(errorName))
		  {
			  model.put("resetFaculty", 1);
		  }else{
			  model.put("resetFaculty", 0);
		  }
		  model.put("staffFacultyName", staffOldFacultyName);
		  model.put("staffDepartmentName", staffOldDepartmentName);
		  model.put("academicRankName", staffOldAcademicRank);
		  model.put("error", 1);
		  return "cp.profile";
	  }else
	  {
		  	String userFacultyCode = (staffFaculty != null) ? staffFaculty : session.getAttribute("currentUserFaculty").toString();
    	 	String userCode = session.getAttribute("currentUserCode").toString();
    	 	mStaff staff = staffService.loadStaffByUserCode(userCode);
    	 	if(staff != null){
    	 		String staffCatCode = "LEC";
    	 		int staffID = staff.getStaff_ID();
    	 		mAcademicRank academicRank = academicRankService.loadByCode(staffAcademicRankCode);
    	 		staffService.editAStaff(staffID, staffName, staffEmail, staffPhone, staffDepartment, userCode, staffCatCode, userFacultyCode, staffGender, staffDateOfBirth, academicRank);
    	 		staff = staffService.loadStaffByUserCode(userCode);
    	 		model.put("staffFacultyName", staff.getDepartment().getFaculty().getFaculty_Name());
    	 		model.put("staffDepartmentName", staff.getDepartment().getDepartment_Name());
    	 		model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
    	 		model.put("academicRankName", academicRank.getAcademicRank_VNName());
    	 	}
    	 	return "cp.profile";
	  }
  }
  
  /**
   * Get faculty List by user role
   * @param userRole
   * @param session
   * @return
   */
  /*public List<mFaculty> getFacultyByUserRole(String userRole, String currentUserFacultyCode)
  {
	  List<mFaculty> facultyList = new ArrayList<mFaculty>();
	  if(userRole.equals(this.SUPER_ADMIN) || currentUserFacultyCode.equals(null)){
		   facultyList = facultyService.loadFacultyList();
	   }else{
		   facultyList = facultyService.loadAFacultyByCode(currentUserFacultyCode);
	   }
	  return facultyList;
  }
  
  /**
   * Get faculty List by user role
   * @param userRole
   * @param session
   * @return
   */
  public List<mDepartment> getDepartmentByUserRole(String userRole, String currentUserFacultyCode)
  {
	  List<mDepartment> departmentList = new ArrayList<mDepartment>();
	  if(userRole.equals(this.SUPER_ADMIN) || currentUserFacultyCode.equals(null)){
		   departmentList = departmentService.loadDepartmentList();
	   }else{
		   departmentList = departmentService.loadDepartmentListByFaculty(currentUserFacultyCode);
	   }
	  return departmentList;
  }
}
