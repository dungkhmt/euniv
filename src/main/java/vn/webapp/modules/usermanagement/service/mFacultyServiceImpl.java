/**
 * @author : HaTN 
 * @address : HUST K51
 * @modified : October 14th, 2015
 */
package vn.webapp.modules.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.usermanagement.dao.mFacultyDAO;
import vn.webapp.modules.usermanagement.model.mFaculty;

@Service("mFacultyService")
public class mFacultyServiceImpl implements mFacultyService {
	
	@Autowired
	private mFacultyDAO facultyDAO;

	/**
	 * 
	 * @return
	 */
	@Override
	public List<mFaculty> loadFacultyList() {
		try {
			return facultyDAO.loadFacultyList();

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param facultyId
	 * @return
	 */
	@Override
	public mFaculty loadAFacultyById(int facultyId) {
		try {
			if(facultyId > 0)
			{
				return facultyDAO.loadAFacultyById(facultyId);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param facultyCode
	 * @return
	 */
	@Override
	public mFaculty loadAFacultyByCode(String facultyCode) {
		try {
			if(facultyCode != null)
			{
				return facultyDAO.loadAFacultyByCode(facultyCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param facultyCode
	 * @param facultyName
	 * @param facultyAsciiName
	 * @return
	 */
	@Override
	public int saveAFaculty(String facultyCode, String facultyName, String facultyAsciiName) {
		mFaculty faculty = new mFaculty();
		faculty.setFaculty_Code(facultyCode);
		faculty.setFaculty_Name(facultyName);
		faculty.setFaculty_AsciiName(facultyAsciiName);
		int i_SaveAPatent = facultyDAO.saveAFaculty(faculty);
		return i_SaveAPatent;
	}

	/**
	 * 
	 * @param facultyId
	 * @param facultyCode
	 * @param facultyName
	 * @param facultyAsciiName
	 */
	@Override
	public void editAFaculty(int facultyId, String facultyCode, String facultyName, String facultyAsciiName) {
		mFaculty patent = facultyDAO.loadAFacultyById(facultyId);
		if (patent != null) {
			patent.setFaculty_ID(facultyId);
			patent.setFaculty_Code(facultyCode);
			patent.setFaculty_Name(facultyName);
			patent.setFaculty_AsciiName(facultyAsciiName);

			facultyDAO.editAFaculty(patent);
		}
	}

	/**
	 * 
	 * @param facultyId
	 * @return
	 */
	@Override
	public int removeAFaculty(int facultyId) {
		return facultyDAO.removeAFaculty(facultyId);
	}
}
