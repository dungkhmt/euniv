/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmDefenseSession;


public interface mmDefenseSessionsDAO {

	public List<mmDefenseSession> getListDefenseSession();
	
	public List<mmDefenseSession> getListAllDefenseSession();
	
	public void editADefenseSession(mmDefenseSession defenseSession);
    
    public int saveADefenseSession(mmDefenseSession defenseSession);
    
    public int removeADefenseSession(int iDefenseSessionId);
    
    public mmDefenseSession getDefenseSessionById(int iDefenseSessionId);
    
    public mmDefenseSession getDefenseSessionByCode(String iDefenseSessionCode);
}
