/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.usermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.mastermanagement.dao.mmAcademicRankDAO;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.usermanagement.dao.mAcademicRankDAO;
import vn.webapp.modules.usermanagement.model.mAcademicRank;

@Service("macademicRankService")
public class mAcademicRankServiceImpl implements mAcademicRankService {

    @Autowired
    private mAcademicRankDAO macademicRankDAO;

    /**
     * Get all list of academic year
     * @param null
     * @return List
     */
    @Override
    public List<mAcademicRank> list(){
    	return macademicRankDAO.getList();
    }
    @Override
    public mAcademicRank loadByCode(String academicRankCode){
    	return macademicRankDAO.loadByCode(academicRankCode);
    }
}
