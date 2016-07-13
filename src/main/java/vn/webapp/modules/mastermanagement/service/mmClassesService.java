/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmClasses;


public interface mmClassesService {

	public List<mmClasses> listClasses();
	
	public List<mmClasses> getListAllClasses();
	
    public void editAClass(int iClassId, String sClassCode, String sClassName, String sClassAsciiName,
    		String sClassFacultyCode, String sYear);
    
    public int saveAClass(String sClassCode, String sClassName, String sClassFacultyCode, String sClassYear);
    
    public int removeAClass(int iClassID);
    
    public mmClasses getClassById(int iClassID);
}
