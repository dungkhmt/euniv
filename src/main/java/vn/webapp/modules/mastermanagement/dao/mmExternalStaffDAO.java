/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmExternalStaffInput;

public interface mmExternalStaffDAO {

	public List<mmExternalStaff> listExternalStaffs();
	
	public List<mmExternalStaff> listExternalStaffsByUniversity(String universityCode);
		
	public mmExternalStaff getExternalStaffById(String userRole, int staff_Id);
	
	public mmExternalStaffInput getExternalStaffInputById(String userRole, int staff_Id);
	
	public mmExternalStaff getByExternalStaffCode(String externalStaffCode);
		   
    public void editAExternalStaff(mmExternalStaffInput externalstaff);
    
    public int saveAExternalStaff(mmExternalStaffInput externalstaff);
    
    public int removeAExternalStaff(int staffId);
    
}
