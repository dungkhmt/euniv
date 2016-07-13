package vn.webapp.modules.mastermanagement.dao;

import java.util.List;



import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmJurySlot;

@Repository("mmJurySlotDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmJurySlotDAOImpl extends BaseDao implements mmJurySlotDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<mmJurySlot> listJurySlots(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JurySlot_StaffCode", staffCode));
			List<mmJurySlot> listJurySlots = criteria.list();
			commit();
			return listJurySlots;
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
	public List<mmJurySlot> listJurySlots(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_StaffCode", staffCode));
			List<mmJurySlot> listJurySlots = criteria.list();
			commit();
			return listJurySlots;
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
	public mmJurySlot getJurySlotByCode(String sJurySlotCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_Code", sJurySlotCode));
			mmJurySlot jurySlot = (mmJurySlot)criteria.uniqueResult();
			commit();
			return jurySlot;
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
	public mmJurySlot getJurySlotByUserCode(String sJurySlotCode, String userCode)
	{
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJurySlot.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JurySlot_Code", sJurySlotCode));
			criteria.add(Restrictions.eq("JurySlot_StaffCode", userCode));
			mmJurySlot jurySlot = (mmJurySlot)criteria.uniqueResult();
			commit();
			return jurySlot;
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
	public int saveJurySlot(mmJurySlot jurySlot){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(jurySlot);
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
	
	@Override
	public int removeAJurySlot(mmJurySlot jurySlot){
		try {
            begin();
            getSession().delete(jurySlot);
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
}
