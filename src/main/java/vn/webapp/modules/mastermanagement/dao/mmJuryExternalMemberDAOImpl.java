package vn.webapp.modules.mastermanagement.dao;

import java.util.List;





import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmJuryExternalMember;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;

@Repository("mmJuryExternalMemberDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmJuryExternalMemberDAOImpl extends BaseDao implements mmJuryExternalMemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<mmJuryExternalMember> listJuryExternalMembers(String defenseSessionCode,String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryExternalMember.class);
			criteria.add(Restrictions.eq("JEM_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JEM_StaffCode", staffCode));
			List<mmJuryExternalMember> listJuryExternalMembers = criteria.list();
			commit();
			return listJuryExternalMembers;
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
	public mmJuryExternalMember getAJuryExternalMemberByMemberAndDefenseSession(String DefenseSessionCode, String MemCode, String StaffCode){
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryExternalMember.class);
			criteria.add(Restrictions.eq("JEM_DefenseSessionCode", DefenseSessionCode));
			criteria.add(Restrictions.eq("JEM_MemberCode", MemCode));			
			criteria.add(Restrictions.eq("JEM_StaffCode", StaffCode));
			mmJuryExternalMember aJuryMember = (mmJuryExternalMember)criteria.uniqueResult();
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
	public int saveJuryExternalMember(mmJuryExternalMember juryExternalMember){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(juryExternalMember);
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
	public int removeAJuryExternalMember(mmJuryExternalMember JuryExternalMember){
		try {
            begin();
            getSession().delete(JuryExternalMember);
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
