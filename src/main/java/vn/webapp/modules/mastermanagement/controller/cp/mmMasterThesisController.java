/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmDepartment;
import vn.webapp.modules.mastermanagement.model.mmMasterThesis;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStudent;
import vn.webapp.modules.mastermanagement.service.mmDepartmentService;
import vn.webapp.modules.mastermanagement.service.mmMasterThesisService;
import vn.webapp.modules.mastermanagement.service.mmScientificFieldService;
import vn.webapp.modules.mastermanagement.service.mmSpecializationKeywordService;
import vn.webapp.modules.mastermanagement.service.mmStaffService;
import vn.webapp.modules.mastermanagement.service.mmStudentService;
import vn.webapp.modules.mastermanagement.service.mmUserService;
import vn.webapp.modules.mastermanagement.validation.ThesisEditValidation;
import vn.webapp.modules.mastermanagement.validation.ThesisValidation;


@Controller("mmMasterThesis")
@RequestMapping(value = {"/mm"})
public class mmMasterThesisController extends BaseWeb {
	
	@Autowired
    private mmMasterThesisService mmmasterThesisService;
	
	@Autowired
    private mmStaffService mmstaffService;

    @Autowired
    private mmStudentService mmstudentService;
    
    @Autowired
    private mmDepartmentService mmdepartmentService;
    
    @Autowired
    private mmUserService mmuserService;

    @Autowired
    private mmScientificFieldService mmscientificFieldService;

    @Autowired
    private mmSpecializationKeywordService mmspecializationKeywordService;
    
    /**
    *
    * @param model
    * @return
    */
   @RequestMapping(value = "/listStudentToAssignThesis", method = RequestMethod.GET)
   public String studentList(ModelMap model, HttpSession session) {

	   List<mmStudent> studentList = mmstudentService.listStudentsByStatus(0);
	   model.put("studentList", studentList);
	   return "mm.listToAssignThesis";
   }
   
   @RequestMapping("/assignthesis/{id}")
   public String AssignThesis(ModelMap model, @PathVariable("id") int studentId, HttpSession session) {
	   
	   /*
	    * Put data back to view
	    */
	   mmStudent student = mmstudentService.loadStudentById(studentId);
	   
	   model.put("thesisFormAssign", new ThesisValidation());
	   	
	   if(student != null)
	   {
		   model.put("student", student);
		   model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
		   model.put("professorList", mmstaffService.listStaffs());
		   model.put("departmentList", mmdepartmentService.loadDepartmentList());
		   
		   /*List<String> KWCodes = staffService.listKWCodeByStaff(staff.getStaff_Code());
		   List<SpecializationKeyword> specializationKeywordList = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   specializationKeywordList.add(KW);
		   }
		   model.put("specializationKeywords", specializationKeywordList);
		   
		   
		   System.out.println("SpecializationListSize: " + staff.getListSpecializationKeywords().size());
		   System.out.println("SpecializationListSize: " + specializationKeywordList.size());*/
		   
	       return "mm.assignThesis";
	   }
	   return "mm.notFound404";
   }
   
   @RequestMapping("/editthesis/{id}")
   public String EditThesis(ModelMap model, @PathVariable("id") int id, HttpSession session) {
	   
	   mmMasterThesis masterThesis = mmmasterThesisService.loadMasterThesisById(id);
	   if(masterThesis == null){
		   return "mm.notFound404";
	   }
	   mmStudent student = masterThesis.getStudent();  
	   model.put("thesisFormEdit", new ThesisEditValidation());
	   if(student != null)
	   {
		   model.put("thesis", masterThesis);
		   model.put("specializationKeywordList", mmspecializationKeywordService.loadSpecializationKeywordList());
		   model.put("professorList", mmstaffService.listStaffs());
		   model.put("departmentList", mmdepartmentService.loadDepartmentList());
		   model.put("specializationKeywords", mmspecializationKeywordService.loadMasterThesisSpecializationKeywordList(masterThesis.getThesis_Code()));
		   return "mm.editThesis";
	   }
	   return "mm.notFound404";	   
   }
	
   
   @RequestMapping(value = "/save-a-thesis", method = RequestMethod.POST)
   public String saveAProfessor(@Valid @ModelAttribute("thesisFormAssign") ThesisValidation thesisFormAssign, 
		   							BindingResult result,  Map model, HttpSession session) {
 	 List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
 	 List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
 	 
 	 String studentCode = thesisFormAssign.getStudentCode();
 	 String thesisName = thesisFormAssign.getThesisName();
 	 String supervisorCode = thesisFormAssign.getSupervisor();
 	 ArrayList<String> staffKeywords = thesisFormAssign.getStaffKeywords();
 	 HashSet<mmSpecializationKeyword> specializationKeywords = new HashSet<mmSpecializationKeyword>();
 	 for(String code:staffKeywords){
 		 mmSpecializationKeyword speKW = mmspecializationKeywordService.getSpecializationKeywordByCode(code);
 		 specializationKeywords.add(speKW);
 	 }
 	 
 	 model.put("thesisName", thesisName);
 	 model.put("specializationKeywordList", specializationKeywordList);
 	 model.put("specializationKeywords", specializationKeywords);
 	 if(result.hasErrors()) {	
 		 String status = "Bạn đã lưu khong thành công thông tin đề tài";
 		 model.put("status", status);
 		 return "mm.assignThesis";
      }else
      {
    	 mmStudent student = mmstudentService.loadStudentByCode(studentCode);
    	 mmStaff supervisor = mmstaffService.loadStaffByStaffCode(supervisorCode);
    	 String ThesisCode = studentCode + supervisorCode;
    	 
    	 if(student != null && supervisor != null){
    		 mmmasterThesisService.saveAMasterThesis(ThesisCode, thesisName, studentCode, supervisorCode, specializationKeywords, student);
 	     	 String status = "Bạn đã lưu thành công thông tin đề tài";
 	     	 model.put("status", status);
    	 }
    	 if(supervisor == null){
    		 System.out.println("Supervisor null");
    		 System.out.println(supervisorCode);
    	 }
    	 if(student == null){
    		 System.out.println("Student null");
    	 }
     	 
       List<mmStudent> studentList = mmstudentService.listStudentsByStatus(0);
  	   model.put("studentList", studentList);
  	   return "mm.listToAssignThesis";
      }
   }
   
   @RequestMapping(value = "/edit-a-thesis", method = RequestMethod.POST)
   public String editThesis(@Valid @ModelAttribute("thesisFormEdit") ThesisEditValidation thesisFormEdit, 
		   							BindingResult result,  Map model, HttpSession session) {
 	 //List<mmDepartment> departmentList = mmdepartmentService.loadDepartmentList();
 	 List<mmSpecializationKeyword> specializationKeywordList = mmspecializationKeywordService.loadSpecializationKeywordList();
 	 
 	 int thesisID = thesisFormEdit.getThesisID();
 	 String studentCode = thesisFormEdit.getStudentCode();
 	 String thesisName = thesisFormEdit.getThesisName();
 	 String supervisorCode = thesisFormEdit.getSupervisor();
 	 ArrayList<String> staffKeywords = thesisFormEdit.getStaffKeywords();
 	 HashSet<mmSpecializationKeyword> specializationKeywords = new HashSet<mmSpecializationKeyword>();
 	 for(String code:staffKeywords){
 		 mmSpecializationKeyword speKW = mmspecializationKeywordService.getSpecializationKeywordByCode(code);
 		 specializationKeywords.add(speKW);
 	 }
 	 
 	 //model.put("thesisName", thesisName);
 	 model.put("specializationKeywordList", specializationKeywordList);
 	 model.put("specializationKeywords", specializationKeywords);
 	 if(result.hasErrors()) {	
 		 String status = "Bạn đã lưu khong thành công thông tin đề tài";
 		 model.put("status", status);
 		 return "mm.assignThesis";
      }else
      {
    	 mmStudent student = mmstudentService.loadStudentByCode(studentCode);
    	 mmStaff supervisor = mmstaffService.loadStaffByStaffCode(supervisorCode);
    	 String ThesisCode = studentCode + supervisorCode;
    	 
    	 if(student != null && supervisor != null){
    		 mmmasterThesisService.editAMasterThesis(thesisID, ThesisCode, thesisName, student, supervisor, specializationKeywords);
 	     	 String status = "Bạn đã lưu thành công thông tin đề tài";
 	     	 model.put("status", status);
    	 }
    	 if(supervisor == null){
    		 System.out.println("Supervisor null");
    		 System.out.println(supervisorCode);
    	 }
    	 if(student == null){
    		 System.out.println("Student null");
    	 }
     	 
       List<mmMasterThesis> listMasterThesis = mmmasterThesisService.listMasterThesis();
  	   model.put("listMasterThesis", listMasterThesis);
  	   
  	   return "mm.listThesis";  	   
      }
   }
   
   
   @RequestMapping(value = "/listThesis", method = RequestMethod.GET)
   public String thesisList(ModelMap model, HttpSession session) {

	   List<mmMasterThesis> listMasterThesis = mmmasterThesisService.listMasterThesis();
	   model.put("listMasterThesis", listMasterThesis);
	   
	   return "mm.listThesis";
   }
   
   /*@RequestMapping(value = "/schedule", method = RequestMethod.GET)
   public String schedule(ModelMap model, HttpSession session) throws Exception{

	   PrintWriter fo = new PrintWriter("E:\\test.txt" , "UTF-8");
	   fo.println("juries");
	   List<MasterThesis> listMasterThesis = masterThesisService.listMasterThesis();
	   fo.println(listMasterThesis.size());
	   fo.println("StudentID SupervisorID Examiner1ID Examiner2ID PresidentID SecretaryID AdditionalMemberID Slot Room");
	   
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   Staff supervisor = staffService.loadStaffByStaffCode(masterThesis.getThesis_SupervisorCode());		   
		   String temp = student.getStudent_ID() + " " + supervisor.getStaff_ID() + " 0 0 0 0 0 0 0";  
		   fo.println(temp);		   
	   }
	   List<Staff> listInternalProfessor = staffService.listStaffsByUniversity("HUSTKHMT");
	   List<Staff> listExternalProfessor = staffService.listStaffsByUniversity("DIFFKHMT");
	   HashSet<Staff> listProfessor = new HashSet<Staff>();
	   
	   //internal professors
	   fo.println("internal professors");
	   fo.println(listInternalProfessor.size());
	   String temp = "";
	   
	   for(Staff internalProfessor:listInternalProfessor){
		   System.out.println(internalProfessor.getStaff_Name());
		   temp += internalProfessor.getStaff_ID();
		   temp += " ";
		   listProfessor.add(internalProfessor);
	   }
	   fo.println(temp);
	   
	   //external professors
	   fo.println("external professors");
	   fo.println(listExternalProfessor.size());
	   temp = "";
	   for(Staff externalProfessor:listExternalProfessor){
		   System.out.println(externalProfessor.getStaff_Name());
		   temp += externalProfessor.getStaff_ID();
		   temp += " ";
		   listProfessor.add(externalProfessor);
	   }
	   fo.println(temp);
	   
	   fo.println("subject match between professors and student");
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   //fo.println(student.getStudent_Name());
		   //fo.println(masterThesis.getThesis_Name());
		   
		   
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			  //fo.println(KW.getKW_VNName());
		   }	
		   
		   
		   for(Staff pro:listProfessor){
			   KWCodes = staffService.listKWCodeByStaff(pro.getStaff_Code());
			   List<SpecializationKeyword> speKWprofessor = new ArrayList<SpecializationKeyword>();
			   for(String KWCode:KWCodes){
				   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
				   speKWprofessor.add(KW);
			   }
			   
			   //fo.println(pro.getStaff_Name());
			   //fo.println(speKWprofessor.size());
			   for(SpecializationKeyword s:speKWprofessor){
				   fo.println(s.getKW_VNName());
			   }
			   
			   int i = 0;
			   for(SpecializationKeyword speKWs:speKWstudent){
				   for(SpecializationKeyword speKWp:speKWprofessor){
					   if(speKWs.getKW_Code().equals(speKWp.getKW_Code())){
						   i++;
					   }
				   }
			   }
			   temp = student.getStudent_ID() + " "+pro.getStaff_ID()+" "+ i ;
			   fo.println(temp);
		   }
		   
		   
		   //fo.println(temp);		   
	   }
	   
	   fo.println("subject match between students");
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   String speKWstudentlist = "";
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			   speKWstudentlist += KW.getKW_VNName()+ " ";
			  //fo.println(KW.getKW_VNName());
		   }	
		   
		   for(MasterThesis masterThesisRef:listMasterThesis){
			   if(!masterThesisRef.getThesis_Code().equals(masterThesis.getThesis_Code())){				
			   
				   Student studentRef = studentService.loadStudentByCode(masterThesisRef.getThesis_StudentCode());
			   	   KWCodes = masterThesisService.loadKWCodeByThesis(masterThesisRef.getThesis_Code());
				   List<SpecializationKeyword> speKWstudentRef = new ArrayList<SpecializationKeyword>();
				   String speKWstudentReflist = "";
				   for(String KWCode:KWCodes){
					   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
					   speKWstudentRef.add(KW);
					   speKWstudentReflist += KW.getKW_VNName() + " ";
				   }
				   //fo.println(student.getStudent_Name() +": "+speKWstudentlist+  "vs" + studentRef.getStudent_Name() +": "+speKWstudentReflist);	   
				   
			  
				   int i = 0;
				   for(SpecializationKeyword speKWs:speKWstudent){
					   for(SpecializationKeyword speKWsref:speKWstudentRef){
						   if(speKWs.getKW_Code().equals(speKWsref.getKW_Code())){
							   i++;
						   }
					   }
				   }
				   temp = student.getStudent_ID() + " "+studentRef.getStudent_ID()+" "+ i ;
				   fo.println(temp);
			   }
		   }		   
	   }
	   fo.println("number of slots & number of rooms");
	   fo.println("5 4");
	   
	   fo.println("Professor Information");
	   fo.println("ProID|ProName");
	   fo.println("SpecializationKeywords");
	   fo.println(listProfessor.size());
	   for(Staff pro:listProfessor){
		   fo.println(pro.getStaff_ID()+"|"+pro.getStaff_Name());
		   temp = "";
		   List<String> KWCodes = staffService.listKWCodeByStaff(pro.getStaff_Code());
		   List<SpecializationKeyword> speKWprofessor = new ArrayList<SpecializationKeyword>();
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWprofessor.add(KW);
			   temp += KW.getKW_VNName()+"|";
		   }
		   fo.println(temp);
	   }
	   fo.println("Student Information");
	   fo.println("StudentID|StudentName|ThesisName");
	   fo.println("SpecializationKeywords");
	   fo.println(listMasterThesis.size());
	   for(MasterThesis masterThesis:listMasterThesis){
		   Student student = studentService.loadStudentByCode(masterThesis.getThesis_StudentCode());
		   fo.println(student.getStudent_ID()+"|"+student.getStudent_Name()+"|"+masterThesis.getThesis_Name());
		   List<String> KWCodes = masterThesisService.loadKWCodeByThesis(masterThesis.getThesis_Code());
		   List<SpecializationKeyword> speKWstudent = new ArrayList<SpecializationKeyword>();
		   temp = "";
		   for(String KWCode:KWCodes){
			   SpecializationKeyword KW = specializationKeywordService.getSpecializationKeywordByCode(KWCode);
			   speKWstudent.add(KW);
			   temp += KW.getKW_VNName()+"|";			  
		   }	
		   fo.println(temp);
	   }	   
	   
	   fo.close();
	   
	   return "mm.listThesis";
	   
   }*/
   
}
