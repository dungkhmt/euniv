/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 13th, 2015
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mFaculty;

public interface mFacultyDAO {
	/**
	 * 
	 * @return
	 */
	public List<mFaculty> loadFacultyList();

	/**
	 * 
	 * @param facultyId
	 * @return
	 */
	public mFaculty loadAFacultyById(int facultyId);
	
	/**
	 * 
	 * @param facultyCode
	 * @return
	 */
	public mFaculty loadAFacultyByCode(String facultyCode);

	/**
	 * 
	 * @param faculty
	 * @return
	 */
	public int saveAFaculty(mFaculty faculty);

	/**
	 * 
	 * @param faculty
	 */
	public void editAFaculty(mFaculty faculty);

	/**
	 * 
	 * @param facultyId
	 * @return
	 */
	public int removeAFaculty(int facultyId);
	
	/**
	 * 
	 * @param sfacultyCodes
	 * @return
	 */
	public List<mFaculty> getListFacultyBySetOfCode(String[] sfacultyCodes);
}
