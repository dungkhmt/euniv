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
import vn.webapp.modules.researchdeclarationmanagement.model.mWorkExperiences;

@Repository("mWorkExperiencesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mWorkExperiencesDAOImpl extends BaseDao implements mWorkExperiencesDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mWorkExperiencesDAOImpl";
    }
	@Override
	public List<mWorkExperiences> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mWorkExperiences.class);
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
			List<mWorkExperiences> list = criteria.list();
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
	public List<mWorkExperiences> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mWorkExperiences.class);
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
			List<mWorkExperiences> list= criteria.list();
			for(int i = 0 ; i < list.size(); ++i) {
				System.out.print(list.get(i).getWE_StaffCode());
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
	public Boolean deleteWorkExperiences(int WE_ID) {
		mWorkExperiences workexperiences = new mWorkExperiences();
		workexperiences.setWE_ID(WE_ID);
		try{
			begin();
			getSession().delete(workexperiences);
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
	public mWorkExperiences addWorkExperiences(mWorkExperiences newWorkExperiences) {
		try{
			begin();
			int id = 0;
			System.out.print(18);
			id = (Integer)getSession().save(newWorkExperiences);
			System.out.print(19);
			commit();
			System.out.print(20);
			newWorkExperiences.setWE_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newWorkExperiences.setWE_CODE("WE"+nf.format(id));
			return newWorkExperiences;
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
	public Boolean changeWorkExperiences(int WE_ID, String WE_Position, 
			String WE_Domain, String WE_Institution) {
		
		try {
           begin();
           mWorkExperiences workexperiences = (mWorkExperiences) getSession().get(mWorkExperiences.class, WE_ID);
           workexperiences.setWE_Position(WE_Position);
           workexperiences.setWE_Domain(WE_Domain);
           workexperiences.setWE_Institution(WE_Institution);
           System.out.print(WE_ID);
           getSession().update(workexperiences);
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
	