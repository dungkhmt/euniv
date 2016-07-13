/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.List;
import java.util.Set;

import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;

public interface mmSpecializationKeywordService {

    public List<mmSpecializationKeyword> loadSpecializationKeywordList();    
    public List<mmSpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code);
    public mmSpecializationKeyword getSpecializationKeywordByCode(String KW_Code);
    public List<mmSpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode);
    public List<mmSpecializationKeyword> loadMasterThesisSpecializationKeywordList(String thesisCode);
    
}
