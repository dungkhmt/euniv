package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.lang.reflect.Type;
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
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

@Repository("mEducationDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mEducationsDAOImpl extends BaseDao implements mEducationsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mEducationDAOImpl";
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
			
			for(field field: fields) {
				Junction conditionGroup = Restrictions.disjunction();
				List<String> values = field.getValues();
				Type typeField = mEducations.class.getDeclaredField(field.getFieldName()).getGenericType();
				for(String value: values) {
					if(typeField.toString().equals("int")) {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), Integer.valueOf(value)));
					} else {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
					}
					
				}
				criteria.add(conditionGroup);
			}
			
			List<mEducations> list= criteria.list();
			for(int i = 0 ; i < list.size(); ++i) {
				System.out.print(list.get(i).getEDU_UserCode());
			}
			commit();
			return list;
		} catch (HibernateException | NoSuchFieldException | SecurityException e){
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
			id = (Integer)getSession().save(newEducation);
			commit();
			newEducation.setEDU_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newEducation.setEDU_Code("EDU"+newEducation.getEDU_UserCode()+nf.format(id));
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
           getSession().update(education);
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
	