package vn.webapp.modules.researchmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.dao.ProjectResearchFieldDAO;
import vn.webapp.modules.researchmanagement.model.ProjectResearchField;
@Service("ProjectResearchFieldService")
public class ProjectResearchFieldServiceImpl implements
		ProjectResearchFieldService {
	@Autowired
	ProjectResearchFieldDAO projectResearchFieldDAO;
	
	@Override
	public List<ProjectResearchField> list() {
		// TODO Auto-generated method stub
		return projectResearchFieldDAO.list();
	}

}
