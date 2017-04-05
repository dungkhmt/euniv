package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;
import vn.webapp.modules.researchdeclarationmanagement.service.mEducationService;

@Controller("mEducation")
@RequestMapping(value = {"/cp"})
public class mEducationController {
	@Autowired
	private mEducationService mEducationService;
	
	String name(){
		return "mEducationController";
	}
	
	
	@RequestMapping(value = "/getEducations", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getStatus(ModelMap model, HttpSession session){
		return new ResponseEntity<>(mEducationService.getList(),HttpStatus.OK);
	}

}
