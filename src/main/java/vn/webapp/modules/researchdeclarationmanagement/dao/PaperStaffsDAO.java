/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import vn.webapp.modules.researchdeclarationmanagement.model.PaperStaffs;

public interface PaperStaffsDAO {

    public List<PaperStaffs> loadPaperListByPaperCode(String paperCode);
    
    public List<PaperStaffs> loadPaperListByPaperCode(List<String> paperCodes);
    
    public int saveAPaperStaff(PaperStaffs paperStaff);

    public int removeAPaperStaff(PaperStaffs paperStaff);
}
