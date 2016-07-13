package vn.webapp.modules.researchmanagement.dao;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mProjectStaffs;

@Repository("mProjectStaffsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mProjectStaffsDAOImpl extends BaseDao implements mProjectStaffsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    /**
     * Save a staff
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaff(mProjectStaffs staff){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(staff);
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
    
    /**
     * load projectStaff by user role and code
     * @param Object
     * @return int
     */
    @Override
    public mProjectStaffs loadProjectStaffByUserCode(String projectCode, String userCode, String userRole){
    	try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStaffs.class);
			criteria.add(Restrictions.eq("PROJSTAFF_Proj_Code", projectCode));
			criteria.add(Restrictions.eq("PROJSTAFF_Staff_Code", userCode));
			criteria.add(Restrictions.eq("PROJSTAFF_Role_Code", userRole));
			mProjectStaffs projectStaffs = (mProjectStaffs)criteria.uniqueResult();
			commit();
			return projectStaffs;
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
	 * Load A ProjectStaff by project code 
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public List<mProjectStaffs> loadAProjectStaffByProjectCode(String projectStaffCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStaffs.class);
			criteria.add(Restrictions.eq("PROJSTAFF_Proj_Code", projectStaffCode));
			List<mProjectStaffs> projectStaffs = criteria.list();
			commit();
			return projectStaffs;
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
	 * Load A ProjectStaff by project code 
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public List<mProjectStaffs> loadAProjectStaffInListStaffs(HashSet<String> staffCodeList)
	{
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStaffs.class);
			criteria.add(Restrictions.in("PROJSTAFF_Staff_Code", staffCodeList));
			List<mProjectStaffs> projectStaffs = criteria.list();
			commit();
			return projectStaffs;
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
	 * Load A ProjectStaff by project code 
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public List<mProjectStaffs> loadAProjectStaffByProjectCodeForEdit(String projectStaffCode){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStaffs.class);
			criteria.add(Restrictions.eq("PROJSTAFF_Proj_Code", projectStaffCode));
			criteria.add(Restrictions.ne("PROJSTAFF_Role_Code", "PROJECT_LEADER"));
			List<mProjectStaffs> projectStaffs = criteria.list();
			commit();
			return projectStaffs;
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
	public List<mProjectStaffs> listAll(){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mProjectStaffs.class);
			List<mProjectStaffs> lst = criteria.list();
			commit();
			return  lst;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	/**
	 * Remove a projectStaff
	 * 
	 * @param paperId
	 * @return
	 */
	@Override
	public int removeAProjectStaff(int projectStaffId) {
		mProjectStaffs projectStaff = new mProjectStaffs();
		projectStaff.setPROJSTAFF_ID(projectStaffId);
		try {
			begin();
			getSession().delete(projectStaff);
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
