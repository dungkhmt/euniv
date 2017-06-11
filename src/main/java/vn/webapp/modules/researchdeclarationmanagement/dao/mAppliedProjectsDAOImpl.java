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
import vn.webapp.modules.researchdeclarationmanagement.model.mAppliedProjects;

@Repository("mAppliedProjectsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mAppliedProjectsDAOImpl extends BaseDao implements mAppliedProjectsDAO {
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    String name(){
    	return "mAppliedProjectsDAOImpl";
    }
	@Override
	public List<mAppliedProjects> getList() {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mAppliedProjects.class);
			//criteria.createAlias("O_BatchCode", "O_BatchCode");
			//criteria.addOrder(Order.asc("O_BatchCode.value"));
			List<mAppliedProjects> list = criteria.list();
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
	public List<mAppliedProjects> getListByField(List<field> fields) {
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mAppliedProjects.class);
			
			for(field field: fields) {
				Junction conditionGroup = Restrictions.disjunction();
				List<String> values = field.getValues();
				for(String value: values) {
					conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
				}
				criteria.add(conditionGroup);
			}
			List<mAppliedProjects> list= criteria.list();

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
	public Boolean deleteAppliedProjects(int AP_ID) {
		mAppliedProjects appliedprojects = new mAppliedProjects();
		appliedprojects.setAP_ID(AP_ID);
		try{
			begin();
			getSession().delete(appliedprojects);
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
	public mAppliedProjects addAppliedProjects(mAppliedProjects newAppliedProjects) {
		try{
			begin();
			int id = 0;
			
			id = (Integer)getSession().save(newAppliedProjects);
			
			commit();
			
			newAppliedProjects.setAP_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newAppliedProjects.setAP_Code("AP" + newAppliedProjects.getAP_StaffCode()+ nf.format(id));
			return newAppliedProjects;
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
	public Boolean changeAppliedProjects(int AP_ID, String AP_Name,
			String AP_Scope, String AP_Date) {
		
		try {
           begin();
           mAppliedProjects appliedprojects = (mAppliedProjects) getSession().get(mAppliedProjects.class, AP_ID);
           appliedprojects.setAP_Name(AP_Name);
           appliedprojects.setAP_Scope(AP_Scope);
           appliedprojects.setAP_Date(AP_Date);
          // System.out.print(AP_ID);
           getSession().update(appliedprojects);
          
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
	