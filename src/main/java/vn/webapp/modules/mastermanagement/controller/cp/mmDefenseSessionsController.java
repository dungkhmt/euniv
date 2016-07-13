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
import vn.webapp.modules.mastermanagement.model.mmDefenseSession;
import vn.webapp.modules.mastermanagement.service.mmDefenseSessionService;
import vn.webapp.modules.mastermanagement.validation.DefensesSessionValidation;

@Controller("mmDefenseSession")
@RequestMapping(value = {"/mm"})
public class mmDefenseSessionsController extends BaseWeb {

    @Autowired
    private mmDefenseSessionService mmdefenseSessionService;

	   /**
	    * Show list all 
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/defensesession", method = RequestMethod.GET)
	   public String defensesessionList(ModelMap model, HttpSession session) {
	
		   List<mmDefenseSession> defenseSessionList = mmdefenseSessionService.getListAllDefenseSession();
		   model.put("defenseSessionList", defenseSessionList);
		   return "mm.defensesession";
	   }
	   
	   @RequestMapping(value = "/add-a-defensesession", method = RequestMethod.GET)
	   public String addADefenseSession(ModelMap model, HttpSession session) {
		   model.put("DefenseSessionFormAdd", new DefensesSessionValidation());
		   return "mm.addADefenseSession";
	   }   
   
	   /**
	   *
	   * @param model
	   * @return
	   */
	  @RequestMapping(value = "/save-a-defensesession", method = RequestMethod.POST)
	  public String saveADefenseSession(@Valid @ModelAttribute("DefenseSessionFormAdd") DefensesSessionValidation defenseSessionFormAdd, BindingResult result,  Map model, HttpSession session) {
		 String defensesSessionName = defenseSessionFormAdd.getDefenseSessionName();
		 String defensesSessionCode = defenseSessionFormAdd.getDefenseSessionCode();
		 int defenseSessionEnabled = defenseSessionFormAdd.getDefensessionEnabled();
		 
		 if(result.hasErrors()) {		
			 return "mm.addADefenseSession";
	     }else
	     {
	    	 mmdefenseSessionService.saveADefenseSession(defensesSessionCode, defensesSessionName, defenseSessionEnabled);
	    	 String status = "Bạn đã thêm thành công!";
			 model.put("status", status);
		     return "mm.addADefenseSession";
	     }
	  }
 
	  /**
	   * 
	   * @param model
	   * @param defenseSessionrId
	   * @param session
	   * @return
	   */
	   @RequestMapping("/defensesessiondetail/{id}")
	   public String editADefenseSession(ModelMap model, @PathVariable("id") int defenseSessionrId, HttpSession session) {
		   
		   mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(defenseSessionrId);
		   if(defenseSession != null)
		   {
			   model.put("sDefenseSessionCode", defenseSession.getDEFSESS_Code());
			   model.put("sDefenseSessionName", defenseSession.getDEFSESS_Name());
			   model.put("defenseSessionEnable", defenseSession.getDEFSESS_Enabled());
			   model.put("DefenseSessionFormAdd", new DefensesSessionValidation());
			   model.put("defenseSessionrId", defenseSessionrId);
			   return "mm.editADefenseSession";
		   }
		   return "mm.notFound404";
	   }
 
	   /**
	    * Adding a paper
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/edit-a-defensesession", method = RequestMethod.POST)
	   public String updateAPaper(HttpServletRequest request, @Valid @ModelAttribute("DefenseSessionFormAdd") DefensesSessionValidation defenseSessionFormAdd, BindingResult result, Map model, HttpSession session) {

		   /*
		    * Put data back to view
		    */
		   String sDefenseSessionCode = defenseSessionFormAdd.getDefenseSessionCode();
		   String sDefenseSessionName = defenseSessionFormAdd.getDefenseSessionName();
		   int defenseSessionEnable = defenseSessionFormAdd.getDefensessionEnabled();
		   int defenseSessionId = defenseSessionFormAdd.getDefensessionId();
		   
		   model.put("sDefenseSessionCode", sDefenseSessionCode);
		   model.put("sDefenseSessionName", sDefenseSessionName);
		   model.put("defenseSessionEnable", defenseSessionEnable);
		   model.put("defenseSessionId", defenseSessionId);
		   // Add the saved validationForm to the model  
	      if (result.hasErrors()) {
	           return "mm.editADefenseSession";
	      }else
	      {
	   	   		mmdefenseSessionService.editADefenseSession(defenseSessionId, sDefenseSessionCode, sDefenseSessionName, defenseSessionEnable);
	   	   		model.put("status", "Chỉnh sửa thành công.");
		   	 
		   	   return "mm.editADefenseSession";
	      }
	   }
	   
	  @RequestMapping(value = "/remove-a-defensesession/{id}", method = RequestMethod.GET)
	  public String removeADefenseSession(ModelMap model, @PathVariable("id") int defenseSessiontId, HttpSession session) {
		   
		  mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(defenseSessiontId);
		   if(defenseSession != null){
			   mmdefenseSessionService.removeADefenseSession(defenseSessiontId);
			   String status = "Xóa thành công.";
			   List<mmDefenseSession> defenseSessionList = mmdefenseSessionService.getListAllDefenseSession();
			   model.put("defenseSessionList", defenseSessionList);
			   model.put("status", status);
			   return "mm.defensesession";
		   }
		   return "mm.notFound404";
	  }
   
}
