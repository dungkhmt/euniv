package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchdeclarationmanagement.model.mJournal;

@Repository("mJournalDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class mJournalDAOImpl extends BaseDao implements mJournalDAO {
	
	/**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    public List<mJournal> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(mJournal.class);
            criteria.setFirstResult(0);
            List<mJournal> journal = criteria.list();
            commit();
            return journal;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
}
