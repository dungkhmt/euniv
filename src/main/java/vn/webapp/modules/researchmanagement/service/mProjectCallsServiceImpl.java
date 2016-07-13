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
import vn.webapp.modules.researchmanagement.dao.mProjectCallsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStaffsDAO;
import vn.webapp.modules.researchmanagement.dao.mProjectStatusDAO;
import vn.webapp.modules.researchmanagement.dao.nProjectDAO;
import vn.webapp.modules.researchmanagement.model.mJuryOfAnnouncedProjectCall;
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

@Service("mProjectCallsService")
public class mProjectCallsServiceImpl implements mProjectCallsService {

	@Autowired
	private mProjectCallsDAO projectCallsDAO;

	@Autowired
	private nProjectService projectService;
	
	@Autowired
	private mJuryOfAnnouncedProjectCallService juryOfAnnouncedProjectCallService;
	
	/**
	 * Get a list Threads by user code
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mProjectCalls> loadProjectCallsList(){
		try {
			return projectCallsDAO.loadProjectCallsList();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public int saveAProjectCall(String sPROJCALL_CODE, String sPROJCALL_PROJCATCODE, String sPROJCALL_NAME, String sPROJCALL_DATE, String sPROJCALL_STATUS){
		try {
			if (sPROJCALL_CODE != null && sPROJCALL_NAME != null) {
				mProjectCalls projectCalls = new mProjectCalls();
				projectCalls.setPROJCALL_CODE(sPROJCALL_CODE);
				projectCalls.setPROJCALL_PROJCATCODE(sPROJCALL_PROJCATCODE);
				projectCalls.setPROJCALL_NAME(sPROJCALL_NAME);
				projectCalls.setPROJCALL_DATE(sPROJCALL_DATE);
				projectCalls.setPROJCALL_STATUS(sPROJCALL_STATUS);
				
				// Create 
				int i_InsertedAProjectCallId = projectCallsDAO.saveAProjectCall(projectCalls);
				
				/*
				mProjectCalls insertedProjectCalls = projectCallsDAO.loadAProjectCallById(i_InsertedAProjectCallId);
				LocalDate o_fFormatDateByFormat = DateUtil.o_fFormatDateByFormatType2(sPROJCALL_DATE);
				sPROJCALL_CODE = "T"+o_fFormatDateByFormat.getYear()+i_InsertedAProjectCallId;
				insertedProjectCalls.setPROJCALL_CODE(sPROJCALL_CODE);
				insertedProjectCalls.setPROJCALL_PROJCATCODE(sPROJCALL_PROJCATCODE);
				insertedProjectCalls.setPROJCALL_NAME(sPROJCALL_NAME);
				insertedProjectCalls.setPROJCALL_DATE(sPROJCALL_DATE);
				
				projectCallsDAO.editAProjectCall(insertedProjectCalls);
				*/
				
				return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * load a project call id
	 * 
	 * @param String
	 * @param int
	 * @return object
	 */
	@Override
	public mProjectCalls loadAProjectCallById(int iProjectCallId){
		try {
			return projectCallsDAO.loadAProjectCallById(iProjectCallId);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public mProjectCalls loadAProjectCallByCode(String sProjectCallCode){
		try {
			return projectCallsDAO.loadAProjectCallByCode(sProjectCallCode);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public mProjectCalls loadAProjectCallByName(String sProjectCallName){
		try {
			return projectCallsDAO.loadAProjectCallByName(sProjectCallName);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int checkingExistProjectCallByName(int projectCallId, String sPROJCALL_NAME, String sPROJCALL_CODE){
		try {
			//return projectCallsDAO.checkingExistProjectCallByName(projectCallId, sPROJCALL_NAME);
			List<mProjectCalls> projectCalls = projectCallsDAO.loadProjectCallsList();
			for(mProjectCalls pc: projectCalls)if(pc.getPROJCALL_ID() != projectCallId){
				if(pc.getPROJCALL_CODE().equals(sPROJCALL_CODE)) return 1;
				if(pc.getPROJCALL_NAME().equals(sPROJCALL_NAME)) return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

	/**
	 * Edit a project call
	 * 
	 * @param String
	 * @param int
	 * @return null
	 */
	public String name(){
		return "mProjectCallServiceImpl";
	}
	@Override
	public void editAProjectCall(int iProjectCallId, String sPROJCALL_CODE, String PROJCALL_PROJCATCODE, String PROJCALL_NAME, String PROJCALL_DATE, String sPROJCALL_STATUS){
		try {
			mProjectCalls projectCalls = projectCallsDAO.loadAProjectCallById(iProjectCallId);
			if (projectCalls != null) {
				String oldProjectCallCode = projectCalls.getPROJCALL_CODE();
				
				projectCalls.setPROJCALL_CODE(sPROJCALL_CODE);
				projectCalls.setPROJCALL_DATE(PROJCALL_DATE);
				projectCalls.setPROJCALL_PROJCATCODE(PROJCALL_PROJCATCODE);
				projectCalls.setPROJCALL_NAME(PROJCALL_NAME);
				projectCalls.setPROJCALL_STATUS(sPROJCALL_STATUS);
				projectCallsDAO.editAProjectCall(projectCalls);
				
				// update projectCallCode of all related tables if old and new projectCallCode are different
				if(!oldProjectCallCode.equals(sPROJCALL_CODE)){
					
					// update project code of tblprojects
					List<mThreads> projects = projectService.listAll();
					for(mThreads t: projects)if(t.getPROJ_PRJCall_Code().equals(oldProjectCallCode)){
						t.setPROJ_PRJCall_Code(sPROJCALL_CODE);
						projectService.saveAThread(t);
						System.out.println(name() + "::editAProjectCall, update projectCallCode of project " + t.getPROJ_Code() + ", new projectCallCode = " + t.getPROJ_PRJCall_Code());
					}
					
					
					List<mJuryOfAnnouncedProjectCall> juries = 
							juryOfAnnouncedProjectCallService.
							loadListJuryOfAnnouncedProjectCallByProjectCallCode(oldProjectCallCode);
					
					for(mJuryOfAnnouncedProjectCall jpc: juries){
						jpc.setJUSUPRJ_PRJCALLCODE(sPROJCALL_CODE);
						juryOfAnnouncedProjectCallService.editJuryOfAnnouncedProjectCall(jpc);
						
						System.out.println(name() + "::editAProjectCall, update projectCallCode of project " + jpc.getJUSUPRJ_STAFFCODE() + ", new JuryOfAnnouncedProjecCall = " + jpc.getJUSUPRJ_PRJCALLCODE());
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * Remove a thread
	 * 
	 * @param int
	 * @return int
	 */
	@Override
	public int removeAProjectCall(int iProjectCallId){
		mProjectCalls projectCalls = projectCallsDAO.loadAProjectCallById(iProjectCallId);
		return projectCallsDAO.removeAProjectCall(projectCalls);
	}
	
	/**
	 * 
	 */
	public int i_fCheckExistProjectCall(String sPROJCALL_NAME){
		try {
			mProjectCalls projectCalls = projectCallsDAO.loadAProjectCallByName(sPROJCALL_NAME);
			if(projectCalls != null){
				return 1;
			}else{
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}

}
