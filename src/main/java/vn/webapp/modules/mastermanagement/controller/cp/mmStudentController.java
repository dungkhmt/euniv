/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cp;

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
import vn.webapp.modules.mastermanagement.model.mmFaculty;
import vn.webapp.modules.mastermanagement.model.mmMasterClass;
import vn.webapp.modules.mastermanagement.model.mmStudent;
import vn.webapp.modules.mastermanagement.service.mmDepartmentService;
import vn.webapp.modules.mastermanagement.service.mmFacultyService;
import vn.webapp.modules.mastermanagement.service.mmMasterClassService;
import vn.webapp.modules.mastermanagement.service.mmStudentService;
import vn.webapp.modules.mastermanagement.service.mmUserService;
import vn.webapp.modules.mastermanagement.validation.StudentValidation;


@Controller("mmStudent")
@RequestMapping(value = { "/mm" })
public class mmStudentController extends BaseWeb {

	@Autowired
	private mmStudentService mmstudentService;

	@Autowired
	private mmDepartmentService mmdepartmentService;

	@Autowired
	private mmFacultyService mmfacultyService;

	@Autowired
	private mmUserService mmuserService;

	@Autowired
	private mmMasterClassService mmmasterClassService;

	/**
	 * Show list all student
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String studentList(ModelMap model, HttpSession session) {

		List<mmStudent> studentList = mmstudentService.listStudents();
		model.put("studentList", studentList);
		return "mm.students";
	}

	@RequestMapping(value = "/add-a-student", method = RequestMethod.GET)
	public String addAStudent(ModelMap model, HttpSession session) {

		/*
		 * Get current user name and role
		 */
		String currentUserName = session.getAttribute("currentUserName").toString();
		String userRole = session.getAttribute("currentUserRole").toString();

		/*
		 * Get paper's category
		 */

		List<mmMasterClass> classesList = mmmasterClassService.loadMasterClassList();
		/*
		 * Put data back to view
		 */
		model.put("classesList", classesList);
		model.put("studentFormAdd", new StudentValidation());
		return "mm.addAStudent";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save-a-student", method = RequestMethod.POST)
	public String saveAStudent(
			@Valid @ModelAttribute("studentFormAdd") StudentValidation studentFormAdd,
			BindingResult result, Map model, HttpSession session) {
		List<mmMasterClass> classesList = mmmasterClassService
				.loadMasterClassList();

		String studentEmail = studentFormAdd.getStudentEmail();
		String studentName = studentFormAdd.getStudentName();
		String studentPhone = studentFormAdd.getStudentPhone();
		String studentClassCode = studentFormAdd.getStudentClasses();
		String studentCode = studentFormAdd.getStudentCode();

		model.put("studentEmail", studentEmail);
		model.put("studentName", studentName);
		model.put("studentPhone", studentPhone);
		model.put("studentCode", studentCode);
		model.put("classesList", classesList);
		if (result.hasErrors()) {
			return "mm.addAStudent";
		} else {
			mmFaculty faculty = mmfacultyService.loadAFacultyByCodes("SOICT");
			mmstudentService.saveAStudent(studentCode, studentName, studentEmail,
					studentPhone, studentClassCode, faculty, 0);
			String status = "Bạn đã lưu thành công thông tin của học viên";
			model.put("status", status);
			List<mmStudent> studentList = mmstudentService.listStudents();
			model.put("studentList", studentList);

			return "mm.addAStudent";
		}
	}

	@RequestMapping(value = "/studentdetail/{id}", method = RequestMethod.GET)
	public String viewStudentDetail(ModelMap model,
			@PathVariable("id") int studentId, HttpSession session) {
		mmStudent student = mmstudentService.loadStudentById(studentId);
		List<mmMasterClass> classesList = mmmasterClassService
				.loadMasterClassList();
		model.put("classesList", classesList);
		model.put("studentFormAdd", new StudentValidation());
		if (student != null) {
			model.put("studentClassCode", student.getStudent_ClassesCode());
			model.put("studentEmail", student.getStudent_Email());
			model.put("studentName", student.getStudent_Name());
			model.put("studentPhone", student.getStudent_Phone());
			model.put("studentCode", student.getStudent_Code());
			model.put("studentId", studentId);
			return "mm.editAStudent";
		}
		return "mm.notFound404";
	}
	
	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update-a-student", method = RequestMethod.POST)
	public String updateAStudent(
			@Valid @ModelAttribute("studentFormAdd") StudentValidation studentFormAdd,
			BindingResult result, Map model, HttpSession session) {
		List<mmMasterClass> classesList = mmmasterClassService
				.loadMasterClassList();

		String studentEmail = studentFormAdd.getStudentEmail();
		String studentName = studentFormAdd.getStudentName();
		String studentPhone = studentFormAdd.getStudentPhone();
		String studentClassCode = studentFormAdd.getStudentClasses();
		String studentCode = studentFormAdd.getStudentCode();
		int studentId = studentFormAdd.getStudentId();
		model.put("studentEmail", studentEmail);
		model.put("studentName", studentName);
		model.put("studentPhone", studentPhone);
		model.put("studentCode", studentCode);
		model.put("classesList", classesList);
		model.put("studentClassCode", studentClassCode);
		
		if (result.hasErrors()) {
			return "mm.editAStudent";
		} else {
			mmFaculty faculty = mmfacultyService.loadAFacultyByCodes("SOICT");
			mmStudent student = mmstudentService.loadStudentById(studentId);
			if(student != null)
			{
				student.setStudent_Code(studentCode);
				student.setStudent_Email(studentEmail);
				student.setStudent_Phone(studentPhone);
				student.setStudent_ClassesCode(studentClassCode);
				student.setStudent_Name(studentName);
				
				mmstudentService.editAStudent(student);
				String status = "Bạn chỉnh sửa thành công thông tin của học viên";
				model.put("status", status);
				List<mmStudent> studentList = mmstudentService.listStudents();
				model.put("studentList", studentList);
			}
			return "mm.editAStudent";
		}
	}

	@RequestMapping(value = "/remove-a-student/{id}", method = RequestMethod.GET)
	public String removeAStudent(ModelMap model,
			@PathVariable("id") int studentId, HttpSession session) {
		String userCode = session.getAttribute("currentUserCode").toString();
		String userRole = session.getAttribute("currentUserRole").toString();
		mmStudent student = mmstudentService.loadStudentById(studentId);
		if (student != null) {
			mmstudentService.removeAStudent(studentId);
			List<mmStudent> studentList = mmstudentService.listStudents();
			model.put("studentList", studentList);
			String status = "Bạn đã xóa thành công học viên";
			model.put("status", status);
			return "mm.students";
		}
		return "mm.notFound404";
	}

}
