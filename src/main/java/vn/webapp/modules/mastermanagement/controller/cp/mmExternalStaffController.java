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
import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.model.mmUsers;
import vn.webapp.modules.mastermanagement.service.mmAcademicRankService;
import vn.webapp.modules.mastermanagement.service.mmDepartmentService;
import vn.webapp.modules.mastermanagement.service.mmExternalStaffService;
import vn.webapp.modules.mastermanagement.service.mmFacultyService;
import vn.webapp.modules.mastermanagement.service.mmScientificFieldService;
import vn.webapp.modules.mastermanagement.service.mmSpecializationKeywordService;
import vn.webapp.modules.mastermanagement.service.mmStaffCategoryService;
import vn.webapp.modules.mastermanagement.service.mmStaffService;
import vn.webapp.modules.mastermanagement.service.mmUniversityService;
import vn.webapp.modules.mastermanagement.service.mmUserService;
import vn.webapp.modules.mastermanagement.validation.StaffValidation;

@Controller("cpExternalStaff")
@RequestMapping(value = {"/mm"})
public class mmExternalStaffController extends BaseWeb {
	
	@Autowired
	private mmAcademicRankService mmacademicRankService;

    @Autowired
    private mmUniversityService mmuniversityService;
  
    @Autowired
    private mmUserService mmuserService;

    @Autowired
    private mmScientificFieldService mmscientificFieldService;

    @Autowired
    private mmSpecializationKeywordService mmspecializationKeywordService;
    
    @Autowired
    private mmExternalStaffService mmexternalStaffService;


	@RequestMapping(value = "/externalprofessors", method = RequestMethod.GET)
	public String paperList(ModelMap model, HttpSession session) {

		List<mmExternalStaff> externalprofessorList = mmexternalStaffService.listExternalStaffs();
		model.put("externalprofessorList", externalprofessorList);		
		return "mm.externalprofessors";
	}
	
	@RequestMapping(value = "/add-an-externalprofessor", method = RequestMethod.GET)
	public String addProfessor(ModelMap model, HttpSession session) {

		/*
		 * Get current user name and role
		 */
		String currentUserName = session.getAttribute("currentUserName").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
		List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
		listUniversities.remove(0);
		/*for(mmUniversity university:listUniversities){
			if(university.getUniversity_Role().equals("HOST"))
				listUniversities.remove(university);
		}
		/*
		 * Put data back to view
		 */
		model.put("academicRankList", mmacademicRankService.list());			 
		model.put("staffFormAdd", new StaffValidation());
		model.put("universityList", listUniversities);
		model.put("specializationKeywordList", specializationKeywordList);
		return "mm.addAnExternalProfessor";
	}
	
	@RequestMapping(value = "/save-an-externalprofessor", method = RequestMethod.POST)
	public String saveAnExternalProfessor(@Valid @ModelAttribute("staffFormAdd") StaffValidation staffFormAdd, BindingResult result,  Map model, HttpSession session) {
		 List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
		   
		 String staffEmail = staffFormAdd.getStaffEmail();
		 String staffName = staffFormAdd.getStaffName();
		 String staffPhone = staffFormAdd.getStaffPhone();
		 String staffUniversityCode = staffFormAdd.getStaffUniversity();
		 String staffAcademicRankCode = staffFormAdd.getStaffAcademicRank();
		 
		 ArrayList<String> staffKeywords = staffFormAdd.getStaffKeywords();
		 HashSet<mmSpecializationKeyword> specializationKeywords = new HashSet<mmSpecializationKeyword>();
		 
		 if(staffKeywords != null){
			 for(String code:staffKeywords){
				 mmSpecializationKeyword speKW = mmspecializationKeywordService.getSpecializationKeywordByCode(code);
				 specializationKeywords.add(speKW);
			 }
		 }
		
		 List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
		 listUniversities.remove(0);
			
		 model.put("universityList", listUniversities);			 
		 model.put("academicRankList", mmacademicRankService.list());
		 model.put("staffEmail", staffEmail);
		 model.put("staffName", staffName);
		 model.put("staffPhone", staffPhone);
		 model.put("specializationKeywordList", specializationKeywordList);
		 model.put("specializationKeywords", specializationKeywords);
		 model.put("staffUniversityCode", staffUniversityCode); 
			 
		 if(result.hasErrors()) {		
			 return "mm.addAProfessor";
	     }else
	     {
	    	 model.put("staffFormEdit", new StaffValidation());
	    	 String staffCatCode = "LEC";
	    	 String userCode = session.getAttribute("currentUserCode").toString();
	    	 String userRole = session.getAttribute("currentUserRole").toString();
	    	 
	    	 mmAcademicRank academicRank = mmacademicRankService.loadByCode(staffAcademicRankCode);
	    	 mmUniversity university = mmuniversityService.loadAUniversityByCodes(staffUniversityCode);
	    	 String staffCode = staffEmail;
	    	  
	    	 int staffId = mmexternalStaffService.saveAExternalStaff(staffCode, staffName, staffEmail, staffPhone, userRole, specializationKeywords, academicRank, university);
	    	 model.put("staffId", staffId);
	    	 model.put("staff", mmexternalStaffService.getExternalStaffById(userRole, staffId));
	    	 String status = "Bạn đã lưu thành công thông tin của giảng viên";
		     model.put("status", status);
	    	 
		     return "mm.editAnExternalProfessor";
	     }
	  }
	
	@RequestMapping("/externalprofessor-detail/{id}")
	public String editAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
		   
		   /*
		    * Put data back to view
		    */
		   String userRole = session.getAttribute("currentUserRole").toString();
		   mmExternalStaff externalstaff = mmexternalStaffService.getExternalStaffById(userRole, professorId);
		   //System.out.println(userRole);
		   model.put("staffFormEdit", new StaffValidation());
		   	
		   if(externalstaff != null)
		   {
			   model.put("staff", externalstaff);
			   model.put("staffEmail", externalstaff.getEXTSTAF_Email());
			   model.put("staffName", externalstaff.getEXTSTAF_Name());
			   model.put("staffPhone", externalstaff.getEXTSTAF_Phone());
			   model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
			   model.put("staffId", professorId);
			   model.put("specializationKeywords", mmspecializationKeywordService.loadStaffSpecializationKeywordList(externalstaff.getEXTSTAF_Code()));
			   List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
			   listUniversities.remove(0);
						/*
				 * Put data back to view
				 */
				model.put("academicRankList", mmacademicRankService.list());			 
			    model.put("universityList", listUniversities);
				
			   
		       return "mm.editAnExternalProfessor";
		   }
		   return "cp.notFound404";
	   }
	
	@RequestMapping(value = "/edit-externalstaff", method = RequestMethod.POST)
	public String editStaffInfo(@Valid @ModelAttribute("staffFormEdit") StaffValidation staffFormEdit, BindingResult result,  Map model, HttpSession session) {
		 
		 int staffId = staffFormEdit.getStaffId();
		 String staffEmail = staffFormEdit.getStaffEmail();
		 String staffName = staffFormEdit.getStaffName();
		 String staffPhone = staffFormEdit.getStaffPhone();
		 String staffAcademicRank = staffFormEdit.getStaffAcademicRank();
		 String staffUniversityCode = staffFormEdit.getStaffUniversity();
		 
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
		 model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
		 model.put("specializationKeywords", specializationKeywords);
		 List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
			
		 listUniversities.remove(0);
				/*
			 * Put data back to view
			 */
			model.put("academicRankList", mmacademicRankService.list());			 
		    model.put("universityList", listUniversities);
			  
		 
		 if(result.hasErrors()) {		
			 System.out.print("Failed");
			 return "mm.editAnExternalProfessor";
	     }else
	     {
	    	 String staffCatCode = "LEC";
		 	 String userCode = session.getAttribute("currentUserCode").toString();
		 	 String userRole = session.getAttribute("currentUserRole").toString();
		 	 
		 	 mmAcademicRank academicRank = mmacademicRankService.loadByCode(staffAcademicRank);
		 	 String staffCode = staffEmail;
		 	 
		 	 mmUniversity university = mmuniversityService.loadAUniversityByCodes(staffUniversityCode);
		 	 mmexternalStaffService.editAExternalStaff(staffId, staffCode, staffName, staffEmail, staffPhone, userRole, specializationKeywords, academicRank, university); 	 
		     mmExternalStaff staff = mmexternalStaffService.getExternalStaffById(userRole, staffId);
		     model.put("staff", staff);
		     String status = "Bạn đã lưu thành công thông tin của giảng viên";
		     model.put("status", status);
	    	
	    	 return "mm.editAnExternalProfessor";
	     }
	  }
	
	@RequestMapping(value = "/remove-an-externalprofessor/{id}", method = RequestMethod.GET)
	  public String removeAProfessor(ModelMap model, @PathVariable("id") int professorId, HttpSession session) {
		   String userRole = session.getAttribute("currentUserRole").toString();
		   mmExternalStaff externalStaff = mmexternalStaffService.getExternalStaffById(userRole, professorId);
		   if(externalStaff != null){
			   mmexternalStaffService.removeAExternalStaff(professorId);			   
			   String status = "Bạn đã xóa thành công giảng viên";
			   model.put("status", status);
			   List<mmExternalStaff> externalprofessorList = mmexternalStaffService.listExternalStaffs();
			   model.put("externalprofessorList", externalprofessorList);		
			   return "mm.externalprofessors";
			   
		   }
		   return "cp.notFound404";
	  }

}
