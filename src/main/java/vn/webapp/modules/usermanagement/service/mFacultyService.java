package vn.webapp.modules.usermanagement.service;

import java.util.List;
import vn.webapp.modules.usermanagement.model.mFaculty;
	/**
	 * 
	 * @author incre
	 *
	 */
	public interface mFacultyService {
		
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
	 * @param facultyCode
	 * @param facultyName
	 * @param facultyAsciiName
	 * @return
	 */
	public int saveAFaculty(String facultyCode, String facultyName, String facultyAsciiName);

	/**
	 * 
	 * @param facultyId
	 * @param facultyCode
	 * @param facultyName
	 * @param facultyAsciiName
	 */
	public void editAFaculty(int facultyId, String facultyCode, String facultyName, String facultyAsciiName);

	/**
	 * 
	 * @param facultyId
	 * @return
	 */
	public int removeAFaculty(int facultyId);
}
