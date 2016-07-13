/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.model.mmStaffInput;

public interface mmStaffDAO {

	public List<mmStaff> listStaffs();
	
	public List<mmStaff> listStaffsByDepartment(String departmentCode);
	
	public List<mmStaff> listStaffsByFaculty(String facultyCode);
	
	public mmStaff getStaffById(String userRole, int staff_Id);
	
	public mmStaffInput getStaffInputById(String userRole, int staff_Id);
	
	public mmStaff getByStaffCode(String staffCode);
	
	public mmStaff getByUserCode(String userCode);
    
    public void editAStaff(mmStaffInput staff);
    
    public int saveAStaff(mmStaffInput staff);
    
    public int removeAStaff(int staffId);
    
}
