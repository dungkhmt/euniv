/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmClasses;

public interface mmClassesDAO {

	public List<mmClasses> getListmmClasses();
	
	public List<mmClasses> getListAllmmClasses();
	
	public void editAClass(mmClasses aClass);
    
    public int saveAClass(mmClasses aClass);
    
    public int removeAClass(int iClassId);
    
    public mmClasses getClassById(int iClassId);
}
