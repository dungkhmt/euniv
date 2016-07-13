package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mJournalDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mJournal;

@Service("mJournalService")
public class mJournalServiceImpl implements mJournalService{
	@Autowired
    private mJournalDAO journalDAO;

    public void setJournalDAO(mJournalDAO journalDAO) {
        this.journalDAO = journalDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<mJournal> list(){
    	return journalDAO.getList();
    }
}
