/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmAcademicYear;

@Repository("mmacademicYearDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmAcademicYearDAOImpl extends BaseDao implements mmAcademicYearDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mmAcademicYear> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmAcademicYear.class);
            criteria.setFirstResult(0);
            List<mmAcademicYear> academicYear = criteria.list();
            commit();
            return academicYear;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
    
}
