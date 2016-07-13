package vn.webapp.modules.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.usermanagement.model.mStaff;
import vn.webapp.modules.usermanagement.model.mUsers;

@Repository("mStaffDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mStaffDAOImpl extends BaseDao implements mStaffDAO{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffs(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffsByFalcuty(String staffFaculty){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Faculty_Code", staffFaculty));
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by department
     * @param String
     * @return object
     */
    
    public String name(){
    	return "mStaffDAOImpl";
    }
    @Override
    public List<mStaff> listStaffsByDepartment(String departmentCode){
    	//System.out.println(name() + "::listStaffsByDepartment, departmentCode = " + departmentCode);
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Department_Code", departmentCode));
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by faculty department
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffsByFalcutyAndDepartment(String staffFaculty, String departmentCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            if(null != staffFaculty && !staffFaculty.equals("")){
            	criteria.add(Restrictions.eq("Staff_Faculty_Code", staffFaculty));
            }
            if(null != departmentCode && !departmentCode.equals("")){
            	criteria.add(Restrictions.eq("Staff_Department_Code", departmentCode));
            }
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            commit();
            return staff;
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
     * Get an staff by staffname
     * @param staffname
     * @return object
     */
    @Override
    public mStaff getByUserCode(String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.add(Restrictions.eq("Staff_User_Code", userCode));
            mStaff staff = (mStaff) criteria.uniqueResult();
            commit();
            return staff;
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
     * Get an staff by Id
     * @param staffname
     * @return object
     */
    @Override
    public mStaff getStaffById(int staffId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.add(Restrictions.eq("Staff_ID", staffId));
            mStaff staff = (mStaff) criteria.uniqueResult();
            commit();
            return staff;
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
     * Edit a staff
     * @param Object
     * @return int
     */
    @Override
    public void editAStaff(mStaff staff){
    	try {
            begin();
            getSession().update(staff);
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
     * Save a staff
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaff(mStaff staff){
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
     * Remove an user by id
     * @param String
     * @return int
     */
    @Override
    public int removeAStaff(int staffId) {
    	if(staffId > 0)
    	{
	    	mStaff staff = new mStaff();
	    	staff.setStaff_ID(staffId);
			try {
				begin();
				getSession().delete(staff);
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
    	return 0;
    }
    
}
