/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.modules.mastermanagement.model.mmRooms;

@Repository("mmroomsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class mmRoomsDAOImpl extends BaseDao implements mmRoomsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<mmRooms> listRooms(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmRooms.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<mmRooms> rooms = criteria.list();
            commit();
            return rooms;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    @Override
    public mmRooms getByCode(String roomCode){
    	
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(mmRooms.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("R_Code", roomCode));
            mmRooms room = (mmRooms)criteria.uniqueResult();
            commit();
            return room;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    	
    }
}
