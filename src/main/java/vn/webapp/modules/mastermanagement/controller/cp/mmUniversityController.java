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
import vn.webapp.modules.mastermanagement.model.mmUniversity;
import vn.webapp.modules.mastermanagement.service.mmClassesService;
import vn.webapp.modules.mastermanagement.service.mmUniversityService;
import vn.webapp.modules.mastermanagement.validation.ClassesValidation;
import vn.webapp.modules.mastermanagement.validation.UniversityValidation;

@Controller("mmUniversity")
@RequestMapping(value = {"/mm"})
public class mmUniversityController extends BaseWeb {

    @Autowired
    private mmUniversityService mmuniversityService;

	   /**
	    * Show list all 
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/partnerUniversities", method = RequestMethod.GET)
	   public String classesList(ModelMap model, HttpSession session) {
	
		List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
		listUniversities.remove(0);
		/*for (mmUniversity university : listUniversities) {
			if (university.getUniversity_Role().equals("HOST"))
				listUniversities.remove(university);
		}*/
		model.put("universityList", listUniversities);
		return "mm.partnerUniversities";
	   }
	   
	   
	   @RequestMapping(value = "/add-a-partneruniversity", method = RequestMethod.GET)
	   public String addAClass(ModelMap model, HttpSession session) {
		   model.put("universityFormAdd", new UniversityValidation());
		   
		   return "mm.addAnUniversity";
	   }   
   
	   /**
	   *
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/save-a-partneruniversity", method = RequestMethod.POST)
	  public String saveAClass(@Valid @ModelAttribute("universityFormAdd") UniversityValidation universityFormAdd, BindingResult result,  Map model, HttpSession session) {
		 String universityName = universityFormAdd.getUniversityName();
		 String universityCode = universityFormAdd.getUniversityCode();
		 
		 if(result.hasErrors()) {		
			 return "mm.addAnUniversity";
	     }else
	     {
	    	 int universityID = mmuniversityService.saveAUniversity(universityName, universityCode);
	    	 String status = "Bạn đã thêm thành công trường đối tác!";
	    	 model.put("universityFormEdit", new UniversityValidation());			   
			 model.put("status", status);
			 model.put("universityName", universityName);
			 model.put("universityCode", universityCode);
			 model.put("universityID", universityID);
		     return "mm.editAnUniversity";
	     }
	  }
 
	  /**
	   * 
	   * @param model
	   * @param defenseSessionrId
	   * @param session
	   * @return
	   */
	   @RequestMapping("/partneruniversity-detail/{id}")
	   public String editAUniversity(ModelMap model, @PathVariable("id") int universityId, HttpSession session) {
		   
		   mmUniversity university = mmuniversityService.loadAUniversityByID(universityId);
		   if(university != null)
		   {
			   model.put("universityFormEdit", new UniversityValidation());			   
			   model.put("universityName", university.getUniversity_Name());
			   model.put("universityCode", university.getUniversity_Code());
			   model.put("universityID", university.getUniversity_ID());
			   return "mm.editAnUniversity";
		   }
		   return "cp.notFound404";
	   }
 
	   /**
	    * Adding a paper
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/edit-a-partneruniversity", method = RequestMethod.POST)
	   public String updateAUniversity(HttpServletRequest request, @Valid @ModelAttribute("universityFormEdit") UniversityValidation universityFormEdit, BindingResult result,Map model, HttpSession session) {

		  String universityName = universityFormEdit.getUniversityName();
		  String universityCode = universityFormEdit.getUniversityCode();
		  int universityId = universityFormEdit.getUniversityID();
		  if (result.hasErrors()) {
			  return "mm.editAnUniversity";
	      }else
	      {
	    	  String status = "Bạn đã lưu thành công thông tin!";
		      model.put("status", status);
				 
	    	  model.put("universityFormEdit", new UniversityValidation());			   
			  model.put("universityName", universityName);
			  model.put("universityCode", universityCode);
			  model.put("universityID", universityId);	
			  mmuniversityService.editAUniversity(universityId, universityName, universityCode);
	    	  return "mm.editAnUniversity";
	      }
	   }
	   
	  @RequestMapping(value = "/remove-a-partneruniversity/{id}", method = RequestMethod.GET)
	  public String removeAClass(ModelMap model, @PathVariable("id") int universityId, HttpSession session) {
		  
		  mmUniversity university = mmuniversityService.loadAUniversityByID(universityId);
		   if(university != null)
		   {
			mmuniversityService.removeAUniversity(universityId);   
			List<mmUniversity> listUniversities = mmuniversityService.loadUniversityList();
			listUniversities.remove(0);
			model.put("universityList", listUniversities);
			return "mm.partnerUniversities";
			   
		   }		  
		  return "cp.notFound404";
	  }
   
}
