/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmAcademicRank;

public interface mmAcademicRankService {

    public List<mmAcademicRank> list();
    public mmAcademicRank loadByCode(String academicRankCode);
    
}
