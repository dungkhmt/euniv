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
import vn.webapp.modules.researchdeclarationmanagement.model.mForeignLanguages;

@Repository("mForeignLanguagesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mForeignLanguagesDAOImpl extends BaseDao implements mForeignLanguagesDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mForeignLanguagesDAOImpl";
    }
	@Override
	public List<mForeignLanguages> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mForeignLanguages.class);
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
			List<mForeignLanguages> list = criteria.list();
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
	public List<mForeignLanguages> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mForeignLanguages.class);
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
			List<mForeignLanguages> list= criteria.list();
			for(int i = 0 ; i < list.size(); ++i) {
				System.out.print(list.get(i).getLG_UserCode());
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
	public Boolean deleteForeignLanguages(int LG_ID) {
		mForeignLanguages foreignlanguages = new mForeignLanguages();
		foreignlanguages.setLG_ID(LG_ID);
		try{
			begin();
			getSession().delete(foreignlanguages);
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
	public mForeignLanguages addForeignLanguages(mForeignLanguages newForeignLanguages) {
		try{
			begin();
			int id = 0;
			id = (Integer)getSession().save(newForeignLanguages);
			
			commit();
			
			newForeignLanguages.setLG_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newForeignLanguages.setLG_Code("LG"+newForeignLanguages.getLG_UserCode()+nf.format(id));
			return newForeignLanguages;
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
	public Boolean changeForeignLanguages(int LG_ID, String LG_Name, String LG_Listen , String LG_Speak, String LG_Reading, String LG_Writing) {
		
		try {
           begin();
           mForeignLanguages ForeignLanguages = (mForeignLanguages) getSession().get(mForeignLanguages.class, LG_ID);
           ForeignLanguages.setLG_Name(LG_Name);
           ForeignLanguages.setLG_Listen(LG_Listen);
           ForeignLanguages.setLG_Speak(LG_Speak);
           ForeignLanguages.setLG_Reading(LG_Reading);
           ForeignLanguages.setLG_Writing(LG_Writing);
           
           System.out.print(LG_ID);
           getSession().update(ForeignLanguages);
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
	