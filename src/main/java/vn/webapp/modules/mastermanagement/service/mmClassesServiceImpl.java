/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.mastermanagement.dao.mmClassesDAO;
import vn.webapp.modules.mastermanagement.model.mmClasses;


@Service("mmClassesService")
public class mmClassesServiceImpl implements mmClassesService {

	@Autowired
	private mmClassesDAO mmclassesDAO;

	/**
	 * Get active list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mmClasses> listClasses() {
		try {
			return mmclassesDAO.getListmmClasses();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get all list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mmClasses> getListAllClasses() {
		try {
			return mmclassesDAO.getListAllmmClasses();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void editAClass(int iClassId, String sClassCode, String sClassName, String sClassAsciiName,
    		String sClassFacultyCode, String sYear){
		mmClasses cls = mmclassesDAO.getClassById(iClassId);
		if (cls != null) {
			cls.setClasses_Code(sClassCode);
			cls.setClasses_Name(sClassName);
			cls.setClasses_AsciiName(sClassAsciiName);
			cls.setClasses_FacultyCode(sClassFacultyCode);
			cls.setClasses_Year(sYear);
			mmclassesDAO.editAClass(cls);
			return;
		} else
			return;
	}

	/**
	 * 
	 */
	@Override
	public int saveAClass(String sClassCode, String sClassName, String sClassFacultyCode, String sClassYear){
		mmClasses cls = new mmClasses();
		cls.setClasses_Code(sClassCode);
		cls.setClasses_Name(sClassName);
		cls.setClasses_AsciiName(sClassName);
		cls.setClasses_FacultyCode(sClassFacultyCode);
		cls.setClasses_Year(sClassYear);
		//System.out.println("ClassesServiceImpl::saveAClass, year = " + sClassYear);
    	return mmclassesDAO.saveAClass(cls);
	}

	/**
	 * 
	 */
	@Override
	public int removeAClass(int iClassID) {
		return mmclassesDAO.removeAClass(iClassID);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public mmClasses getClassById(int iClassID)
	{
		return mmclassesDAO.getClassById(iClassID);
	}
}
