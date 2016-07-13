/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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




/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.mastermanagement.model.mmDepartment;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.model.mmUsers;
import vn.webapp.modules.mastermanagement.service.mmAcademicRankService;
import vn.webapp.modules.mastermanagement.service.mmDepartmentService;
import vn.webapp.modules.mastermanagement.service.mmFacultyService;
import vn.webapp.modules.mastermanagement.service.mmScientificFieldService;
import vn.webapp.modules.mastermanagement.service.mmSpecializationKeywordService;
import vn.webapp.modules.mastermanagement.service.mmStaffCategoryService;
import vn.webapp.modules.mastermanagement.service.mmStaffService;
import vn.webapp.modules.mastermanagement.service.mmUniversityService;
import vn.webapp.modules.mastermanagement.service.mmUserService;
import vn.webapp.modules.mastermanagement.validation.StaffValidation;

@Controller("cpStaff")
@RequestMapping(value = {"/mm"})
public class mmStaffController extends BaseWeb {
	
	@Autowired
	private mmAcademicRankService mmacademicRankService;

    @Autowired
    private mmStaffService mmstaffService;
    
    @Autowired
    private mmUniversityService mmuniversityService;
    
    @Autowired
    private mmFacultyService mmfacultyService;
    
    @Autowired
    private mmDepartmentService mmdepartmentService;
    
    @Autowired
    private mmUserService mmuserService;

    @Autowired
    private mmScientificFieldService mmscientificFieldService;

    @Autowired
    private mmSpecializationKeywordService mmspecializationKeywordService;
    
    @Autowired
    private mmStaffCategoryService mmstaffCategoryService;
    
    /**
    *
    * @param model
    * @return
    */
      
   /**
    * Show list all professor
    * @param model
    * @return
    */
   @RequestMapping(value = "/professors", method = RequestMethod.GET)
   public String paperList(ModelMap model, HttpSession session) {

	   //List<mmStaff> professorList = mmstaffService.listStaffs();
	   //List<mmStaff> professorList = mmstaffService.listStaffsByFaculty("SOICT");
	   List<mmStaff> professorList = mmstaffService.listStaffsByDepartment("SOICT-BMKHMT");
	   model.put("professorList", professorList);
	   model.put("departmentList",mmdepartmentService.loadDepartmentList());
	   return "mm.professors";
   }
   
   @RequestMapping("/professordetail/{id}")
   public String editAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
	   
	   /*
	    * Put data back to view
	    */
	   String userRole = session.getAttribute("currentUserRole").toString();
	   mmStaff staff = mmstaffService.loadStaffById(userRole, professorId);
	   //System.out.println(userRole);
	   model.put("staffFormEdit", new StaffValidation());
	   	
	   if(staff != null)
	   {
		   List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
		   model.put("staff", staff);
		   model.put("staffEmail", staff.getStaff_Email());
		   model.put("staffName", staff.getStaff_Name());
		   model.put("staffPhone", staff.getStaff_Phone());
		   model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
		   model.put("departmentList", departmentList);
		   model.put("facultyList", mmfacultyService.loadFacultyList());
		   model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
		   model.put("staffId", professorId);
		   model.put("academicRankList", mmacademicRankService.list());
		   model.put("staffDepartmentCode", staff.getDepartment().getDepartment_Code());
		   model.put("specializationKeywords", mmspecializationKeywordService.loadStaffSpecializationKeywordList(staff.getStaff_Code()));
		   		   
	       return "mm.editAProfessor";
	   }
	   return "cp.notFound404";
   }
   
   @RequestMapping(value = "/add-a-professor", method = RequestMethod.GET)
   public String addProfessor(ModelMap model, HttpSession session) {
	  
	   /*
	    * Get current user name and role
	    */
	   String currentUserName = session.getAttribute("currentUserName").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   
	   /*
	    * Get paper's category
	    */
	   
	   List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
	   List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
	   /*
	    * Put data back to view
	    */
	   model.put("academicRankList", mmacademicRankService.list());	
	   model.put("facultyList", mmfacultyService.loadFacultyList());		  
	   model.put("departmentList", departmentList);
	   model.put("staffFormAdd", new StaffValidation());	   
	   model.put("specializationKeywordList", specializationKeywordList);
	   return "mm.addAProfessor";
   }
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/save-a-professor", method = RequestMethod.POST)
  public String saveAProfessor(@Valid @ModelAttribute("staffFormAdd") StaffValidation staffFormAdd, BindingResult result,  Map model, HttpSession session) {
	 List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
	 List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
	   
	 String staffEmail = staffFormAdd.getStaffEmail();
	 String staffName = staffFormAdd.getStaffName();
	 String staffPhone = staffFormAdd.getStaffPhone();
	 String staffDepartmentCode = staffFormAdd.getStaffDepartment();
	 String staffAcademicRankCode = staffFormAdd.getStaffAcademicRank();
	 
	 ArrayList<String> staffKeywords = staffFormAdd.getStaffKeywords();
	 HashSet<mmSpecializationKeyword> specializationKeywords = new HashSet<mmSpecializationKeyword>();
	 
	 if(staffKeywords != null){
		 for(String code:staffKeywords){
			 mmSpecializationKeyword speKW = mmspecializationKeywordService.getSpecializationKeywordByCode(code);
			 specializationKeywords.add(speKW);
		 }
	 }
	 
	 
	 model.put("facultyList", mmfacultyService.loadFacultyList());
	 model.put("academicRankList", mmacademicRankService.list());
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	 model.put("departmentList", departmentList);
	 model.put("specializationKeywordList", specializationKeywordList);
	 model.put("specializationKeywords", specializationKeywords);
	 model.put("staffDepartmentCode", staffDepartmentCode); 
		 
	 if(result.hasErrors()) {		
		 return "mm.addAProfessor";
     }else
     {
    	 model.put("staffFormEdit", new StaffValidation());
    	 String staffCatCode = "LEC";
    	 String userCode = session.getAttribute("currentUserCode").toString();
    	 String userRole = session.getAttribute("currentUserRole").toString();
    	 
    	 mmStaffCategory staffCategory = mmstaffCategoryService.getByCode(staffCatCode);
    	 mmDepartment staffDepartment = mmdepartmentService.loadDepartmentByCode(staffDepartmentCode);
    	 mmAcademicRank academicRank = mmacademicRankService.loadByCode(staffAcademicRankCode);
    	 	 /*int temp;
    	 	 for(temp = 0; temp < staffEmail.length(); temp++){
    	 		 if(staffEmail.charAt(temp) == '@')
    	 			 break;
    	 	 }
    	 	 System.out.print(temp);
    	 	 //String staffCode = staffEmail.substring(0, temp);*/
    	 String staffCode = staffEmail;
    	 	 
    	 mmUsers user = new mmUsers();
    	 user.setUsername(staffCode);
    		 
    	 int staffId = mmstaffService.saveAStaff(staffCode, staffName, staffEmail, staffPhone, staffDepartment, user, userRole, staffCategory, specializationKeywords,academicRank);
    	 model.put("staffId", staffId);
    	 model.put("staff", mmstaffService.loadStaffById(userRole, staffId));
    	 String status = "Bạn đã lưu thành công thông tin của giảng viên";
	     model.put("status", status);
    	 
	     return "mm.editAProfessor";
     }
  }
   
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-staff", method = RequestMethod.POST)
  public String editStaffInfo(@Valid @ModelAttribute("staffFormEdit") StaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {
	 
	 int staffId = staffFormEdit.getStaffId();
	 String staffEmail = staffFormEdit.getStaffEmail();
	 String staffName = staffFormEdit.getStaffName();
	 String staffPhone = staffFormEdit.getStaffPhone();
	 String staffDepartmentCode = staffFormEdit.getStaffDepartment();
	 String staffAcademicRank = staffFormEdit.getStaffAcademicRank();
	 ArrayList<String> staffKeywords = staffFormEdit.getStaffKeywords();
	 HashSet<mmSpecializationKeyword> specializationKeywords = new HashSet<mmSpecializationKeyword>();
	
	 if(staffKeywords != null){
		 for(String code:staffKeywords){
			 mmSpecializationKeyword speKW = mmspecializationKeywordService.getSpecializationKeywordByCode(code);
			 specializationKeywords.add(speKW);
		 }
	 }
	 
	 
	 model.put("staffId", staffId);
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	 model.put("departmentList", mmdepartmentService.loadDepartmentList());
	 model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
	 model.put("specializationKeywords", specializationKeywords);
	 model.put("facultyList", mmfacultyService.loadFacultyList());
	 model.put("academicRankList", mmacademicRankService.list());
	   
	 
	 if(result.hasErrors()) {		
		 System.out.print("Failed");
		 return "mm.editAProfessor";
     }else
     {
    	 String staffCatCode = "LEC";
	 	 String userCode = session.getAttribute("currentUserCode").toString();
	 	 String userRole = session.getAttribute("currentUserRole").toString();
	 	 
	 	 mmStaffCategory staffCategory = mmstaffCategoryService.getByCode(staffCatCode);
	 	 mmDepartment staffDepartment = mmdepartmentService.loadDepartmentByCode(staffDepartmentCode);
	 	 mmAcademicRank academicRank = mmacademicRankService.loadByCode(staffAcademicRank);
	 	 String staffCode = staffEmail;
	 	 
	 	 mmUsers user = new mmUsers();
	 	 user.setUsername(staffCode);	 	 
	 	 mmstaffService.editAStaff(staffId, staffCode, staffName, staffEmail, staffPhone, staffDepartment, user, userRole, staffCategory, specializationKeywords,academicRank); 	 
	     model.put("staffDepartementCode", staffDepartmentCode);
	     mmStaff staff = mmstaffService.loadStaffById(userRole, staffId);
	     model.put("staff", staff);
	     String status = "Bạn đã lưu thành công thông tin của giảng viên";
	     model.put("status", status);
    	
    	 return "mm.editAProfessor";
     }
  }
   
   /**
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/edit-staff-detail", method = RequestMethod.POST)
  public String saveStaffInfo(@Valid @ModelAttribute("staffFormEdit") StaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {
	 List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
	 String staffEmail = staffFormEdit.getStaffEmail();
	 String staffName = staffFormEdit.getStaffName();
	 String staffPhone = staffFormEdit.getStaffPhone();
	 String staffDepartment = staffFormEdit.getStaffDepartment();
	 
	 System.out.println("staff Name : " + staffName);
	 model.put("staffEmail", staffEmail);
	 model.put("staffName", staffName);
	 model.put("staffPhone", staffPhone);
	
	 if(result.hasErrors()) {
		 model.put("error", 1);
		 return "mm.profile";
     }else
     {
    	 String userCode = session.getAttribute("currentUserCode").toString();
    	 mmStaff staff = mmstaffService.loadStaffByUserCode(userCode);
    	 if(staff != null){
	    	 String staffCatCode = "LEC";
	    	 int staffID = staff.getStaff_ID();
	    	 	    	 
	    	 staff.setStaff_AsciiName(staffName);
	     	 staff.setStaff_Name(staffName);
	     	 staff.setStaff_Phone(staffPhone);
	     	 //staff.setStaff_User_Code(userCode);
	     	 //staff.setStaff_Category_Code(staffCatCode);
	     	 staff.setStaff_Email(staffEmail);
	     	 staff.setStaff_Code(userCode);
	     	 
	    	 //staffService.editAStaff(staff);
	    	 model.put("staffCategory", staff.getStaffCategory().getStaff_Category_Name());
	    	 model.put("staffDepartementCode", staffDepartment);
	    	 model.put("departmentList", departmentList);
    	 }
    	 return "mm.profile";
     }
  }
  
  @RequestMapping(value = "/remove-a-professor/{id}", method = RequestMethod.GET)
  public String removeAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   mmStaff staff = mmstaffService.loadStaffById(userRole, professorId);
	   if(staff != null){
		   mmstaffService.removeAStaff(professorId);
		   List<mmStaff> professorList = mmstaffService.listStaffs();
		   model.put("professorList", professorList);
		   String status = "Bạn đã xóa thành công giảng viên";
		   model.put("status", status);
		   return "mm.professors";
	   }
	   return "cp.notFound404";
  }

}
