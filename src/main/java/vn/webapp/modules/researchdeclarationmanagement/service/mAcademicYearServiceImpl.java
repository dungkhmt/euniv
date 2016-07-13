package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mAcademicYearDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mAcademicYear;

@Service("mAcademicYearService")
public class mAcademicYearServiceImpl implements mAcademicYearService {
	@Autowired
    private mAcademicYearDAO academicYearDAO;

    /**
     * Get all list of academic year
     * @param null
     * @return List
     */
    @Override
    public List<mAcademicYear> list(){
    	return academicYearDAO.getList();
    }
}
