package vn.webapp.modules.researchmanagement.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.libraries.DateUtil;
import vn.webapp.modules.researchdeclarationmanagement.dao.mAcademicYearDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectCategoryDAO;
import vn.webapp.modules.researchdeclarationmanagement.dao.tProjectDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopicCategory;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;
import vn.webapp.modules.researchmanagement.dao.mProjectCallStatusDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectCallsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStaffsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStatusDAO;
import vn.webapp.modules.researchmanagement.dao.nProjectDAO;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
import vn.webapp.modules.researchmanagement.model.mProjectCallStatus;
import vn.webapp.modules.researchmanagement.model.mProjectCalls;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;
import vn.webapp.modules.researchmanagement.model.mProjectStatus;
import vn.webapp.modules.researchmanagement.model.mThreads;
import vn.webapp.modules.usermanagement.dao.mDepartmentDAO;
import vn.webapp.modules.usermanagement.dao.mFacultyDAO;
import vn.webapp.modules.usermanagement.dao.mStaffDAO;
import vn.webapp.modules.usermanagement.dao.mUserDAO;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;
import vn.webapp.modules.usermanagement.model.mStaff;

@Service("mProjectCallStatusService")
public class mProjectCallStatusServiceImpl implements mProjectCallStatusService {

	@Autowired
	private mProjectCallStatusDAO projectCallStatusDAO;

	@Override
	public List<mProjectCallStatus> loadProjectCallStatusList(){
		try {
			return projectCallStatusDAO.loadProjectCallStatusList();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}


}
