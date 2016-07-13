/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmStaffCategory;

@Repository("mmstaffCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmStaffCategoryDAOImpl extends BaseDao implements mmStaffCategoryDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mmStaffCategory> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaffCategory.class);
            criteria.setFirstResult(0);
            List<mmStaffCategory> staffCategory = criteria.list();
            commit();
            return staffCategory;
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
    
    public mmStaffCategory getByCode(String staffCategoryCode){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmStaffCategory.class, "StaffCategory");
            criteria.add(Restrictions.eq("StaffCategory.Staff_Category_Code", staffCategoryCode));
            mmStaffCategory staffCategory = (mmStaffCategory) criteria.uniqueResult();
            commit();
            return staffCategory;
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
    
}
