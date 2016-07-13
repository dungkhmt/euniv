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

import vn.webapp.modules.mastermanagement.model.mmAcademicRank;

@Repository("mmacademicRankDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmAcademicRankDAOImpl extends BaseDao implements mmAcademicRankDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mmAcademicRank> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmAcademicRank.class);
            criteria.setFirstResult(0);
            List<mmAcademicRank> academicRanks = criteria.list();
            commit();
            return academicRanks;
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
    
    public mmAcademicRank loadByCode(String academicRankCode){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mmAcademicRank.class);
            criteria.add(Restrictions.eq("AcademicRank_Code",academicRankCode));
            mmAcademicRank academicRank = (mmAcademicRank) criteria.uniqueResult();
            commit();
            return academicRank;
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
