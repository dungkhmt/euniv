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

import vn.webapp.modules.mastermanagement.dao.mmStaffCategoryDAO;
import vn.webapp.modules.mastermanagement.model.mmStaffCategory;

@Service("mmstaffCategoryService")
public class mmStaffCategoryServiceImpl implements mmStaffCategoryService{

    @Autowired
    private mmStaffCategoryDAO mmstaffCategoryDAO;

    public void setStaffCategoryDAO(mmStaffCategoryDAO staffCategoryDAO) {
        this.mmstaffCategoryDAO = staffCategoryDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<mmStaffCategory> list(){
    	return mmstaffCategoryDAO.getList();
    }
    
    public mmStaffCategory getByCode(String staffCategoryCode){
    	return mmstaffCategoryDAO.getByCode(staffCategoryCode);
    }
}
