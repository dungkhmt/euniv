/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.mastermanagement.dao.mmDefenseSessionsDAO;
import vn.webapp.modules.mastermanagement.dao.mmStudentDAO;
import vn.webapp.modules.mastermanagement.model.mmDefenseSession;

@Service("mmDefenseSessionsService")
public class mmDefenseSessionServiceImpl implements mmDefenseSessionService {

	@Autowired
	private mmStudentDAO mmstudentDAO;

	@Autowired
	private mmDefenseSessionsDAO mmdefenseSessionsDAO;

	/**
	 * Get active list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<mmDefenseSession> listDefenseSession() {
		try {
			return mmdefenseSessionsDAO.getListDefenseSession();
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
	public List<mmDefenseSession> getListAllDefenseSession() {
		try {
			return mmdefenseSessionsDAO.getListAllDefenseSession();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void editADefenseSession(int iDefenseSessionId, String sDefenseSessionCode, String sDefenseSessionName, int iActive){
		mmDefenseSession defenseSession = mmdefenseSessionsDAO.getDefenseSessionById(iDefenseSessionId);
		if (defenseSession != null) {
			defenseSession.setDEFSESS_Code(sDefenseSessionCode);
			defenseSession.setDEFSESS_Name(sDefenseSessionName);
			defenseSession.setDEFSESS_Enabled(iActive);
			mmdefenseSessionsDAO.editADefenseSession(defenseSession);
			return;
		} else
			return;
	}

	/**
	 * 
	 */
	@Override
	public int saveADefenseSession(String sDefenseSessionCode, String sDefenseSessionName, int iActive){
		mmDefenseSession defenseSession = new mmDefenseSession();
		defenseSession.setDEFSESS_Code(sDefenseSessionCode);
		defenseSession.setDEFSESS_Name(sDefenseSessionName);
		defenseSession.setDEFSESS_Enabled(iActive);

    	return mmdefenseSessionsDAO.saveADefenseSession(defenseSession);
	}

	/**
	 * 
	 */
	@Override
	public int removeADefenseSession(int iDefenseSessionID) {
		return mmdefenseSessionsDAO.removeADefenseSession(iDefenseSessionID);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public mmDefenseSession getDefenseSessionById(int iDefenseSessionID)
	{
		return mmdefenseSessionsDAO.getDefenseSessionById(iDefenseSessionID);
	}
	
	public mmDefenseSession getDefenseSessionByCode(String iDefenseSessionCode){		
		return mmdefenseSessionsDAO.getDefenseSessionByCode(iDefenseSessionCode);		
	}
}
