package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmUniversity;

public interface mmUniversityDAO {

    public List<mmUniversity> loadUniversityList();
    
    public mmUniversity loadAUniversityByCodes(String universityCode);
    
    public mmUniversity loadAUniversityByID(int universityID);
    
    public void editAUniversity(mmUniversity University);
    
    public int saveAUniversity(mmUniversity University);
    
    public int removeAUniversity(int UniversityId);
    
}
