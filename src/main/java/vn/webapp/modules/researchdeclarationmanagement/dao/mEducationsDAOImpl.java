package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
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
	public List<mEducations> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mEducations.class);
			System.out.print(9);
			Junction conditionGroup = Restrictions.disjunction();
			for(field field: fields) {
				List<String> values = field.getValues();
				for(String value: values) {
					conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
				}
			}
			criteria.add(conditionGroup);
			System.out.print(10);
			List<mEducations> list= criteria.list();
			for(int i = 0 ; i < list.size(); ++i) {
				System.out.print(list.get(i).getEDU_UserCode());
			}
			System.out.print(11);
			commit();
			System.out.print(12);
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
			System.out.print(18);
			id = (Integer)getSession().save(newEducation);
			System.out.print(19);
			commit();
			System.out.print(20);
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
		
		try {
           begin();
           mEducations education = (mEducations) getSession().get(mEducations.class, EDU_ID);
           	education.setEDU_Level(EDU_Level);
	   		education.setEDU_Institution(EDU_Institution);
	   		education.setEDU_Major(EDU_Major);
	   		education.setEDU_CompleteDate(EDU_CompleteDate);
           System.out.print(EDU_ID);
           getSession().update(education);
           System.out.print(13);
           commit();
           System.out.print(14);
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
	