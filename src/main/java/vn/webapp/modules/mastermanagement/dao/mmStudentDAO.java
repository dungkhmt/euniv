package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import vn.webapp.modules.mastermanagement.model.mmStudent;

public interface mmStudentDAO {
	
	public List<mmStudent> listStudents();
	
	public List<mmStudent> listStudentsByClasses(String classesCode);
	
	public List<mmStudent> listStudentsByStatus(int statusID);
		
	public mmStudent getStudentById(int Student_Id);
	
	public mmStudent getStudentByCode(String StudentCode);
	
	public void editAStudent(mmStudent Student);
    
    public int saveAStudent(mmStudent Student);
    
    public int removeAStudent(int StudentId);

}
