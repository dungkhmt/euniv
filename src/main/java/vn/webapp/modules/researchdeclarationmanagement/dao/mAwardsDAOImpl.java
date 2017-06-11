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
import vn.webapp.modules.researchdeclarationmanagement.model.mAwards;

@Repository("mAwardsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mAwardsDAOImpl extends BaseDao implements mAwardsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mAwardsDAOImpl";
    }
	@Override
	public List<mAwards> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mAwards.class);
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
			List<mAwards> list = criteria.list();
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
	public List<mAwards> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mAwards.class);
			
			for(field field: fields) {
				Junction conditionGroup = Restrictions.disjunction();
				List<String> values = field.getValues();
				for(String value: values) {
					conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
				}
				criteria.add(conditionGroup);
			}
			List<mAwards> list= criteria.list();

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
	public Boolean deleteAwards(int AW_ID) {
		mAwards awards = new mAwards();
		awards.setAW_ID(AW_ID);
		try{
			begin();
			getSession().delete(awards);
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
	public mAwards addAwards(mAwards newAwards) {
		try{
			begin();
			int id = 0;
			
			id = (Integer)getSession().save(newAwards);
			
			commit();
			
			newAwards.setAW_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newAwards.setAW_Code("AW" + newAwards.getAW_StaffCode()+ nf.format(id));
			return newAwards;
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
	public Boolean changeAwards(int AW_ID, String AW_Name, String AW_Date) {
		
		try {
           begin();
           mAwards awards = (mAwards) getSession().get(mAwards.class, AW_ID);
           awards.setAW_Name(AW_Name);
           awards.setAW_Date(AW_Date);
          // System.out.print(AP_ID);
           getSession().update(awards);
          
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
	