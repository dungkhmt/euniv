package vn.webapp.modules.mastermanagement.dao;

import java.util.List;




import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmJuryRoom;

@Repository("mmJuryRoomDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mmJuryRoomDAOImpl extends BaseDao implements mmJuryRoomDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<mmJuryRoom> listJuryRooms(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", staffCode));
			List<mmJuryRoom> listJuryRooms = criteria.list();
			commit();
			return listJuryRooms;
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
	public mmJuryRoom getJuryRoomByCode(String sJuryRoomCode, String defenseSessionCode, String userCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_Code", sJuryRoomCode));
			criteria.add(Restrictions.eq("JuryRoom_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", userCode));
			mmJuryRoom juryRoom = (mmJuryRoom)criteria.uniqueResult();
			commit();
			return juryRoom;
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
	public mmJuryRoom getJuryRoomByUserCode(String sJuryRoomCode, String userCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_Code", sJuryRoomCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", userCode));
			mmJuryRoom juryRoom = (mmJuryRoom)criteria.uniqueResult();
			commit();
			return juryRoom;
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
	public mmJuryRoom getJuryRoomByUserCode(String defenseSessionCode, String sJuryRoomCode, String userCode){
		
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_Code", sJuryRoomCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", userCode));
			criteria.add(Restrictions.eq("JuryRoom_DefenseSessionCode", defenseSessionCode));
			mmJuryRoom juryRoom = (mmJuryRoom)criteria.uniqueResult();
			commit();
			return juryRoom;
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
	public List<mmJuryRoom> listJuryRooms(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(mmJuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", staffCode));
			List<mmJuryRoom> listJuryRooms = criteria.list();
			commit();
			return listJuryRooms;
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
	public int saveJuryRoom(mmJuryRoom juryRoom){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(juryRoom);
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
	public int removeAJuryRoomByCode(mmJuryRoom aJuryRoom){
		try {
            begin();
            getSession().delete(aJuryRoom);
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
