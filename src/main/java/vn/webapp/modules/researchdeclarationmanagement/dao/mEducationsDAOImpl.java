package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mEducations;

@Repository("mEducationDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mEducationsDAOImpl extends BaseDao implements mEducationsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mBookDAOImpl";
    }
	@Override
	public List<mEducations> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mEducations.class);
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
			List<mEducations> list = criteria.list();
			commit();
			return list;
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
	@Override
	public List<mEducations> getListByField(String[] field, String[] value) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mEducations.class);
			for(int i = 0; i < field.length; ++i) {
				criteria.add(Restrictions.eq(field[i], value[i]));
			}
			List<mEducations> list= criteria.list();
			commit();
			return list;
		} catch (HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally {
			flush();
			close();
		}
	}
	@Override
	public Boolean deleteEducation(int EDU_ID) {
		mEducations education = new mEducations();
		education.setEDU_ID(EDU_ID);
		try{
			begin();
			getSession().delete(education);
			commit();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return false;
		}finally{
			flush();
			close();
		}
	}
	@Override
	public mEducations addEducation(mEducations newEducation) {
		try{
			begin();
			int id = 0;
			id = (int)getSession().save(newEducation);
			commit();
			newEducation.setEDU_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newEducation.setEDU_UserCode("EDU"+newEducation.getEDU_UserCode()+nf.format(id));
			return newEducation;
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
	@Override
	public Boolean changeEducation(int EDU_ID, String EDU_Level,
			String EDU_Institution, String EDU_Major, String EDU_CompleteDate) {
		mEducations education = new mEducations();
		education.setEDU_ID(EDU_ID);
		education.setEDU_Level(EDU_Level);
		education.setEDU_Institution(EDU_Institution);
		education.setEDU_Major(EDU_Major);
		education.setEDU_CompleteDate(EDU_CompleteDate);
		try {
           begin();
           getSession().merge(education);
           commit();
           return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return false;
        } finally {
            flush();
            close();
        }
	}
}
	