package vn.webapp.modules.mastermanagement.dao;

import java.util.List;



import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmJuryMember;

@Repository("mmJuryMemberDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmJuryMemberDAOImpl extends BaseDao implements mmJuryMemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<mmJuryMember> listJuryMembers(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryMem_StaffCode", staffCode));
			List<mmJuryMember> listJuryMembers = criteria.list();
			commit();
			return listJuryMembers;
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
	public mmJuryMember getAJuryMemberByCode(String sJuryMemberCode, String sStaffCode){
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_Code", sJuryMemberCode));
			criteria.add(Restrictions.eq("JuryMem_StaffCode", sStaffCode));
			mmJuryMember aJuryMember = (mmJuryMember)criteria.uniqueResult();
			commit();
			return aJuryMember;
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
	public mmJuryMember getAJuryMemberByMemberAndDefenseSession(String DefenseSessionCode, String sJuryMemberMemCode, String sStaffCode){
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_MemberCode", sJuryMemberMemCode));
			criteria.add(Restrictions.eq("JuryMem_StaffCode", sStaffCode));
			criteria.add(Restrictions.eq("JuryMem_DefenseSessionCode", DefenseSessionCode));
			mmJuryMember aJuryMember = (mmJuryMember)criteria.uniqueResult();
			commit();
			return aJuryMember;
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
	public List<mmJuryMember> listJuryMembers(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryMember.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryMem_StaffCode", staffCode));
			List<mmJuryMember> listJuryMembers = criteria.list();
			commit();
			return listJuryMembers;
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
	public int saveJuryMember(mmJuryMember juryMember){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(juryMember);
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
	public int removeAJuryMemberByCode(mmJuryMember aJuryMember){
		try {
            begin();
            getSession().delete(aJuryMember);
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
