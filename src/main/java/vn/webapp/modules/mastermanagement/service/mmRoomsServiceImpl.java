/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.dao.mmRoomsDAO;
import vn.webapp.modules.mastermanagement.model.mmRooms;

@Service("mmroomsService")
public class mmRoomsServiceImpl implements mmRoomsService {

    @Autowired
    private mmRoomsDAO mmroomsDAO;

    
    @Override
    public List<mmRooms> listRooms() {
        try {
            return mmroomsDAO.listRooms();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
