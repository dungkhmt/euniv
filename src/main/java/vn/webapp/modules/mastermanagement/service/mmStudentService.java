/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmFaculty;
import vn.webapp.modules.mastermanagement.model.mmStudent;


public interface mmStudentService {

	public List<mmStudent> listStudents();
	
	public List<mmStudent> listStudentsByClasses(String classesCode);
	
	public List<mmStudent> listStudentsByStatus(int statusID);
	
	public mmStudent loadStudentByCode(String StudentCode);
	
	public mmStudent loadStudentById(int Student_ID);
    
    public void editAStudent(mmStudent Student);
    
    public int saveAStudent(String StudentCode, String StudentName, String StudentEmail, String StudentPhone, String studentClassCode, mmFaculty faculty, int StatusID );
    
    public int removeAStudent(int StudentID);
}
