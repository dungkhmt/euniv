package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.field;
import vn.webapp.modules.researchdeclarationmanagement.model.mSupervision;

@Repository("mSupervision")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mSupervisionDAOImpl extends BaseDao implements mSupervisionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFacetory(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	String name() {
		return "mSupervisionImpl";
	}
	
	@Override
	public List<mSupervision> getList() {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mSupervision.class);
			List<mSupervision> list = criteria.list();
			commit();
			return list;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			rollback();
			close();
		} finally {
			flush();
			close();
		}
		return null;
	}

	@Override
	public List<mSupervision> getListByField(List<field> fields) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mSupervision.class);
			
			
			for(field field: fields) {
				Junction conditionGroup = Restrictions.disjunction();
				List<String> values = field.getValues();
				Type typeField = mSupervision.class.getDeclaredField(field.getFieldName()).getGenericType();
				for(String value: values) {
					if(typeField.toString().equals("int")) {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), Integer.valueOf(value)));
					} else {
						conditionGroup.add(Restrictions.eq(field.getFieldName(), value));
					}
					
				}
				criteria.add(conditionGroup);
			}
			
			List<mSupervision> supervisons = criteria.list();
			commit();
			return supervisons;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			rollback();
			close();
		} finally {
			flush();
			close();
		}
		return null;
	}

	@Override
	public Boolean deleteSupervision(int SUP_ID) {
		mSupervision supervision = new mSupervision();
		supervision.setSUP_ID(SUP_ID);
		try {
			begin();
			getSession().delete(supervision);
			commit();
			
			return true;
			
			} catch (Exception e) {
			e.printStackTrace();
			rollback();
			close();
		}
		return null;
	}

	@Override
	public mSupervision addSupervision(mSupervision newSupervision) {
		try {
			begin();
			int id = 0;
			id = (Integer)getSession().save(newSupervision);
			commit();
			newSupervision.setSUP_ID(id);
			NumberFormat nf = new DecimalFormat("000000");
			newSupervision.setSUP_CODE("SUP"+newSupervision.getSUP_StaffCode()+nf.format(id));
			return newSupervision;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			close();
		}
		return null;
	}

	@Override
	public Boolean changeSupervision(int SUP_ID,
			String SUP_StudentName, String SUP_Cosupervision,
			String SUP_Institution, String SUP_ThesisTitle,
			String SUP_SpecializationCode, String SUP_TraingPeriod,
			String SUP_DefensedDate) {
		try {
			begin();
			mSupervision supervison = (mSupervision) getSession().get(mSupervision.class, SUP_ID);
			supervison.setSUP_Cosupervision(SUP_Cosupervision);
			supervison.setSUP_DefensedDate(SUP_DefensedDate);
			supervison.setSUP_Institution(SUP_Institution);
			supervison.setSUP_SpecializationCode(SUP_SpecializationCode);
			supervison.setSUP_StudentName(SUP_StudentName);
			supervison.setSUP_ThesisTitle(SUP_ThesisTitle);
			supervison.setSUP_TraingPeriod(SUP_TraingPeriod);
			getSession().update(supervison);
		   commit();
		   return true;
		} catch (Exception e) {
			e.printStackTrace();
            rollback();
            close();
            return false;
		}
	}
	
}