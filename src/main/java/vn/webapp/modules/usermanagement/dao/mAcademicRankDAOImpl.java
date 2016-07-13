/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.mastermanagement.model.mmAcademicRank;
import vn.webapp.modules.usermanagement.model.mAcademicRank;

@Repository("macademicRankDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mAcademicRankDAOImpl extends BaseDao implements mAcademicRankDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<mAcademicRank> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mAcademicRank.class);
            criteria.setFirstResult(0);
            List<mAcademicRank> academicRanks = criteria.list();
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
    
    public mAcademicRank loadByCode(String academicRankCode){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mAcademicRank.class);
            criteria.add(Restrictions.eq("AcademicRank_Code",academicRankCode));
            mAcademicRank academicRank = (mAcademicRank) criteria.uniqueResult();
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
