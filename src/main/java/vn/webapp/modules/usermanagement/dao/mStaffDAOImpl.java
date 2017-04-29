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
    		Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
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
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Faculty_Code", staffFaculty));
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<mStaff> listStaffsByDepartment(String departmentCode){
    	try {
    		Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Department_Code", departmentCode));
            criteria.addOrder(Order.asc("Staff_Name"));
            List<mStaff> staff = criteria.list();
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
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
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get an staff by staffname
     * @param staffname
     * @return object
     */
    public String name(){
    	return "mStaffDAOImpl";
    }
    @Override
    public mStaff getByUserCode(String userCode) {
    	try {
    		System.out.println(name() + "::getByUserCode");
            Criteria criteria = getSession().createCriteria(mStaff.class);
            System.out.println(name() + "::getByUserCode, create criteria OK");
            criteria.add(Restrictions.eq("Staff_User_Code", userCode));
            mStaff staff = (mStaff) criteria.uniqueResult();
            System.out.println(name() + "::getByUserCode, uniqueResult OK");
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
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
            Criteria criteria = getSession().createCriteria(mStaff.class);
            criteria.add(Restrictions.eq("Staff_ID", staffId));
            mStaff staff = (mStaff) criteria.uniqueResult();
            return staff;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
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
            sessionFactory.getCurrentSession().update(staff);
         } catch (HibernateException e) {
             e.printStackTrace();
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
            int id = (int)sessionFactory.getCurrentSession().save(staff);
            return id;           
         } catch (HibernateException e) {
             e.printStackTrace();
             return 0;
         }
    }
    
    /**
     * Remove an user by id
     * @param String
     * @return int
     */
    @Override
    public int removeAStaff(mStaff staff) {
		try {
			sessionFactory.getCurrentSession().delete(staff);
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
}
