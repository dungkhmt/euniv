/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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


import org.springframework.web.multipart.MultipartFile;




/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.mastermanagement.model.mmClasses;
import vn.webapp.modules.mastermanagement.service.mmClassesService;
import vn.webapp.modules.mastermanagement.validation.ClassesValidation;

@Controller("mmClasses")
@RequestMapping(value = {"/mm"})
public class mmClassesController extends BaseWeb {

    @Autowired
    private mmClassesService mmclassesService;

	   /**
	    * Show list all 
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/classes", method = RequestMethod.GET)
	   public String classesList(ModelMap model, HttpSession session) {
	
		   List<mmClasses> classesList = mmclassesService.getListAllClasses();
		   for(mmClasses cls: classesList){
			   System.out.println("ClassesController::classesList, Code = " +  cls.getClasses_Code() + ", ID = " + cls.getClasses_ID());
		   }
		   model.put("classesList", classesList);
		   return "mm.classes";
	   }
	   
	   
	   @RequestMapping(value = "/add-a-class", method = RequestMethod.GET)
	   public String addAClass(ModelMap model, HttpSession session) {
		   model.put("ClassesFormAdd", new ClassesValidation());
		   
		   return "mm.addAClass";
	   }   
   
	   /**
	   *
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/save-a-class", method = RequestMethod.POST)
	  public String saveAClass(@Valid @ModelAttribute("ClassesFormAdd") ClassesValidation classesFormAdd, BindingResult result,  Map model, HttpSession session) {
		 String className = classesFormAdd.getClassName();
		 String classCode = classesFormAdd.getClassCode();
		 String classFaculty = "SOICT";//classesFormAdd.getClassFacultyCode();
		 String classYear = classesFormAdd.getClassYear();
		 //System.out.println("ClassesController::saveAClass, year = " + classYear);
		 if(result.hasErrors()) {		
			 return "mm.addAClass";
	     }else
	     {
	    	 mmclassesService.saveAClass(classCode,className,classFaculty,classYear);
	    	 String status = "Bạn đã thêm thành công!";
			 model.put("status", status);
		     return "mm.addAClass";
	     }
	  }
 
	  /**
	   * 
	   * @param model
	   * @param defenseSessionrId
	   * @param session
	   * @return
	   */
	   @RequestMapping("/classdetail/{id}")
	   public String editAClass(ModelMap model, @PathVariable("id") int classId, HttpSession session) {
		   
		   mmClasses cls = mmclassesService.getClassById(classId);
		   if(cls != null)
		   {
			   model.put("classCode", cls.getClasses_Code());
			   model.put("className", cls.getClasses_Name());
			   model.put("classFacultyCode", cls.getClasses_FacultyCode());
			   model.put("ClassesFormAdd", new ClassesValidation());
			   model.put("classId", classId);
			   model.put("classYear", cls.getClasses_Year());
			   return "mm.editAClass";
		   }
		   return "mm.notFound404";
	   }
 
	   /**
	    * Adding a paper
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/edit-a-class", method = RequestMethod.POST)
	   public String updateAClass(HttpServletRequest request, @Valid @ModelAttribute(
			   "ClassesFormAdd") ClassesValidation classesFormAdd, BindingResult result, 
			   Map model, HttpSession session) {

		   /*
		    * Put data back to view
		    */
		   String sClassCode = classesFormAdd.getClassCode();
		   String sClassName = classesFormAdd.getClassName();
		   int classId = classesFormAdd.getClassId();
		   String sClassFacultyCode = "SOICT";//classesFormAdd.getClassCode();
		   String sClassYear = classesFormAdd.getClassYear();
		   model.put("sClassCode", sClassCode);
		   model.put("sClassName", sClassName);
		   model.put("classId", classId);
		   // Add the saved validationForm to the model  
	      if (result.hasErrors()) {
	           return "mm.editADefenseSession";
	      }else
	      {
	   	   		mmclassesService.editAClass(classId, sClassCode, sClassName, sClassName, sClassFacultyCode, sClassYear);
	   	   		
	   	   		model.put("status", "Chỉnh sửa thành công.");
		   	 
		   	   return "mm.editAClass";
	      }
	   }
	   
	  @RequestMapping(value = "/remove-a-class/{id}", method = RequestMethod.GET)
	  public String removeAClass(ModelMap model, @PathVariable("id") int classId, HttpSession session) {
		  System.out.println("ClassesController::removeAClass, classId = " + classId); 
		  mmClasses cls = mmclassesService.getClassById(classId);
		   if(cls != null){
			   mmclassesService.removeAClass(classId);
			   String status = "Xóa thành công.";
			   List<mmClasses> classesList = mmclassesService.getListAllClasses();
			   model.put("classesList", classesList);
			   model.put("status", status);
			   return "mm.classes";
		   }
		   return "mm.notFound404";
	  }
   
}
