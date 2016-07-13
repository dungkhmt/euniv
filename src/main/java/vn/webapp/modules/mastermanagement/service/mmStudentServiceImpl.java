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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dto.DataPage;
import vn.webapp.modules.mastermanagement.model.mmFaculty;
import vn.webapp.modules.mastermanagement.model.mmStudent;
import vn.webapp.modules.mastermanagement.dao.mmStudentDAO;


@Service("mmStudentService")
public class mmStudentServiceImpl implements mmStudentService {

    @Autowired
    private mmStudentDAO mmStudentDAO;
    
    
    /**
     * Get Student list
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudents(){
    	try {
            return mmStudentDAO.listStudents();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get Student list by class
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudentsByClasses(String classesCode){
    	try {
    		if(classesCode != null){
    			return mmStudentDAO.listStudentsByClasses(classesCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get Student list by class
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudentsByStatus(int statusID){
    	try {
    		return mmStudentDAO.listStudentsByStatus(statusID);    		
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an professor by id
     * @param String
     * @return object
     */
     
    public mmStudent loadStudentById(int Student_ID){
    	try {
            return mmStudentDAO.getStudentById(Student_ID);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public mmStudent loadStudentByCode(String StudentCode){
    	try {
            return mmStudentDAO.getStudentByCode(StudentCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a Student
     * @param String
     * @return int
     */
    @Override
    public void editAStudent(mmStudent Student){    	
    	
    	mmStudentDAO.editAStudent(Student);
    }
    
    /**
     * Save a Student
     * @param String
     * @return int
     */
    @Override
    public int saveAStudent(String StudentCode, String StudentName, String StudentEmail, String StudentPhone, String studentClassCode, mmFaculty faculty, int StatusID ){
    	
    	mmStudent Student = new mmStudent();
    	Student.setStudent_Code(StudentCode);
    	Student.setStudent_Name(StudentName);
    	Student.setStudent_Email(StudentEmail);
    	Student.setStudent_Phone(StudentPhone);
    	Student.setStudent_ClassesCode(studentClassCode);
    	Student.setStudent_StatusID(StatusID);
    	
    	return mmStudentDAO.saveAStudent(Student);
    }
    
    /**
     * Remove a Student
     * @param int
     * @return int
     */
    @Override
    public int removeAStudent(int StudentId){
    	return mmStudentDAO.removeAStudent(StudentId);
    }
}
