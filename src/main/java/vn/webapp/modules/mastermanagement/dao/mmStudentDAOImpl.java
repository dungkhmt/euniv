/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.modules.mastermanagement.model.mmStudent;

@Repository("mmstudentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmStudentDAOImpl extends BaseDao implements mmStudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get Student list
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudents(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStudent.class);
            List<mmStudent> Students = criteria.list();
            commit();
            return Students;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Get Student list by department
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudentsByClasses(String classesCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStudent.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Student_ClassesCode", classesCode));
            List<mmStudent> Student = criteria.list();
            commit();
            return Student;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Get Student list by department
     * @param String
     * @return object
     */
    @Override
    public List<mmStudent> listStudentsByStatus(int statusID){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmStudent.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Student_StatusID", statusID));
            List<mmStudent> Student = criteria.list();
            commit();
            return Student;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Load A Student by id 
     * @param int
     * @return object
     */
    @Override
    public mmStudent getStudentById(int Student_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStudent.class);
            criteria.add(Restrictions.eq("Student_ID", Student_Id));
            mmStudent professor = (mmStudent) criteria.uniqueResult();
            commit();
            return professor;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    @Override
    public mmStudent getStudentByCode(String StudentCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStudent.class);
            criteria.add(Restrictions.eq("Student_Code", StudentCode));
            mmStudent professor = (mmStudent) criteria.uniqueResult();
            commit();
            return professor;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Edit a Student
     * @param Object
     * @return int
     */
    @Override
    public void editAStudent(mmStudent Student){
    	try {
            begin();
            getSession().update(Student);
            commit();
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
         } finally {
             flush();
             close();
         }
    }
    
    /**
     * Save a Student
     * @param Object
     * @return int
     */
    @Override
    public int saveAStudent(mmStudent student){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(student);
            commit();
            return id;           
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
             return 0;
         } finally {
             flush();
             close();
         }
    }
    
    @Override
    public int removeAStudent(int StudentId){
    	mmStudent Student = new mmStudent();
    	Student.setStudent_ID(StudentId);
    	try {
            begin();
            getSession().delete(Student);
            commit();
            return 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
    }
    
}
