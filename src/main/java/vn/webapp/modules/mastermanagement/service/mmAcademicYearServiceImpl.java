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
import org.springframework.stereotype.Service;



import vn.webapp.modules.mastermanagement.dao.mmAcademicYearDAO;
import vn.webapp.modules.mastermanagement.model.mmAcademicYear;

@Service("mmacademicYearService")
public class mmAcademicYearServiceImpl implements mmAcademicYearService {

    @Autowired
    private mmAcademicYearDAO mmacademicYearDAO;

    /**
     * Get all list of academic year
     * @param null
     * @return List
     */
    @Override
    public List<mmAcademicYear> list(){
    	return mmacademicYearDAO.getList();
    }
}
