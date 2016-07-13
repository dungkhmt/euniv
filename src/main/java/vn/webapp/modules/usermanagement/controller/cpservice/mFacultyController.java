package vn.webapp.modules.usermanagement.controller.cpservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.service.mDepartmentService;
import vn.webapp.modules.usermanagement.service.mFacultyService;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmServiceFactulty")
@RequestMapping(value = { "/cpservice" })
public class mFacultyController extends BaseWeb {
	@Autowired
	private mFacultyService facultyService;
	
	@Autowired
	private mDepartmentService departmentService;
	
	@Autowired
	private mStaffService staffService;

	@ResponseBody
	@RequestMapping(value = "/getdepartments", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String deparmentssList(@RequestParam(value = "sFacultyCode", defaultValue = "0") String sFacultyCode) {
		String sReturn = "";
		// Get department lists
		if(!sFacultyCode.equals(""))
		{
			List<mDepartment> listDepartment = departmentService.loadADepartmentByFaculty(sFacultyCode);
			
			if(listDepartment != null)
			{
				sReturn = "<select class='form-control' name='department' onchange='showStaff(this);'>";
				sReturn += "<option value=''>Chọn bộ môn</option>";
				for(mDepartment department : listDepartment)
				{
					sReturn += "<option value='"+department.getDepartment_Code()+"'>"+department.getDepartment_Name()+"</option>";
				}
				sReturn += "</select>";
			}
			
		}
		return sReturn;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getstaffs", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String staffsList(@RequestParam(value = "sDepartmentCode", defaultValue = "0") String sDepartmentCode) {
		String sReturn = "";
		// Get department lists
		if(!sDepartmentCode.equals(""))
		{
			List<mStaff> listStaff = staffService.listStaffsByDepartment(sDepartmentCode);
			if(listStaff != null){
				sReturn = "<select class='form-control' name='staff' id='staff' multiple>";
				sReturn += "<option value=''>Chọn thành viên</option>";
				for(mStaff staff : listStaff)
				{
					sReturn += "<option value='"+staff.getStaff_Code()+"'>"+staff.getStaff_Name()+"</option>";
				}
				sReturn += "</select>";
			}
		}
		return sReturn;
	}
	
	public String name(){
		return "mFacultyController";
	}
	@ResponseBody
	@RequestMapping(value = "/getsinglestaffs", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getSingleStaff(@RequestParam(value = "sDepartmentCode", defaultValue = "0") String sDepartmentCode) {
		String sReturn = "";
		System.out.println(name() + "::getSingleStaff, departmentCode = " + sDepartmentCode);
		// Get department lists
		if(!sDepartmentCode.equals(""))
		{
			List<mStaff> listStaff = staffService.listStaffsByDepartment(sDepartmentCode);
			System.out.println(name() + "::getSingleStaff, listStaff = " + listStaff.size());
			if(listStaff != null){
				sReturn = "<select class='form-control' name='members' id='members'>";
				for(mStaff staff : listStaff)
				{
					sReturn += "<option value='"+staff.getStaff_Code()+"'>"+staff.getStaff_Name()+"</option>";
				}
				sReturn += "</select>";
			}
		}
		return sReturn;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loaddepartments", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String deparmentsList(@RequestParam(value = "sFacultyCode", defaultValue = "0") String sFacultyCode) {
		String sReturn = "";
		// Get department lists
		if(!sFacultyCode.equals(""))
		{
			List<mDepartment> listDepartment = departmentService.loadADepartmentByFaculty(sFacultyCode);
			
			if(listDepartment != null)
			{
				sReturn = "<select class='form-control' name='staffDepartment' id='staffDepartment'>";
				sReturn += "<option value=''>Chọn bộ môn</option>";
				for(mDepartment department : listDepartment)
				{
					sReturn += "<option value='"+department.getDepartment_Code()+"'>"+department.getDepartment_Name()+"</option>";
				}
				sReturn += "</select>";
			}
			
		}
		return sReturn;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadthreaddepartments", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getThreadDeparmentsList(@RequestParam(value = "sFacultyCode", defaultValue = "0") String sFacultyCode) {
		String sReturn = "";
		// Get department lists
		if(!sFacultyCode.equals(""))
		{
			List<mDepartment> listDepartment = departmentService.loadADepartmentByFaculty(sFacultyCode);
			
			if(listDepartment != null)
			{
				sReturn = "<select class='form-control' style='width:200px;' name='threadDepartment' id='threadDepartment' onchange='showStaff(this);'>";
				sReturn += "<option value=''>Chọn bộ môn</option>";
				for(mDepartment department : listDepartment)
				{
					sReturn += "<option value='"+department.getDepartment_Code()+"'>"+department.getDepartment_Name()+"</option>";
				}
				sReturn += "</select>";
			}
			
		}
		return sReturn;
	}

	@ResponseBody
	@RequestMapping(value = "/getthreadstaffs", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getThreadStaffsList(@RequestParam(value = "sDepartmentCode", defaultValue = "0") String sDepartmentCode) {
		String sReturn = "";
		// Get department lists
		if(!sDepartmentCode.equals(""))
		{
			List<mStaff> listStaff = staffService.listStaffsByDepartment(sDepartmentCode);
			if(listStaff != null){
				sReturn = "<select class='form-control' style='width:200px;' name='threadStaff' id='threadStaff'>";
				sReturn += "<option value=''>Chọn cán bộ</option>";
				for(mStaff staff : listStaff)
				{
					sReturn += "<option value='"+staff.getStaff_Code()+"'>"+staff.getStaff_Name()+"</option>";
				}
				sReturn += "</select>";
			}
		}
		return sReturn;
	}
}
