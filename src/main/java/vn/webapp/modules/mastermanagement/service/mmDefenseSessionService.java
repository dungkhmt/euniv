/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmDefenseSession;

public interface mmDefenseSessionService {

	public List<mmDefenseSession> listDefenseSession();
	
	public List<mmDefenseSession> getListAllDefenseSession();
	
    public void editADefenseSession(int iDefenseSessionId, String sDefenseSessionCode, String sDefenseSessionName, int iActive);
    
    public int saveADefenseSession(String sDefenseSessionCode, String sDefenseSessionName, int iActive);
    
    public int removeADefenseSession(int iDefenseSessionID);
    
    public mmDefenseSession getDefenseSessionById(int iDefenseSessionID);
    
    public mmDefenseSession getDefenseSessionByCode(String iDefenseSessionCode);
}
