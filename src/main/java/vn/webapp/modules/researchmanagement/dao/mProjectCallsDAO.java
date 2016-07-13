package vn.webapp.modules.researchmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchmanagement.model.mProjectCalls;

public interface mProjectCallsDAO {
	/**
	 * 
	 * @return
	 */
	public List<mProjectCalls> loadProjectCallsList();

	/**
	 * 
	 * @param thread
	 * @return
	 */
	public int saveAProjectCall(mProjectCalls projectCalls); 

	/**
	 * 
	 * @param iProjectCallId
	 * @return
	 */
	public mProjectCalls loadAProjectCallById(int iProjectCallId);
	
	/**
	 * 
	 * @param sProjectCallCode
	 * @return
	 */
	public mProjectCalls loadAProjectCallByCode(String sProjectCallCode);

	/**
	 * 
	 * @param projectCalls
	 */
	public void editAProjectCall(mProjectCalls projectCalls);

	/**
	 * 
	 * @param iProjectCallId
	 * @return
	 */
	public int removeAProjectCall(mProjectCalls projectCalls);
	
	/**
	 * 
	 * @param sPROJCALL_NAME
	 * @return
	 */
	public mProjectCalls loadAProjectCallByName(String sPROJCALL_NAME);
	
	/**
	 * 
	 * @param projectCallId
	 * @param sPROJCALL_NAME
	 * @return
	 */
	public int checkingExistProjectCallByName(int projectCallId, String sPROJCALL_NAME);
}
