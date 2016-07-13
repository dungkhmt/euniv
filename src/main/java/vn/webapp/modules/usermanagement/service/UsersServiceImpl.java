package vn.webapp.modules.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.usermanagement.dao.UsersDAO;
import vn.webapp.modules.usermanagement.model.mUsers;

@Service("myusersService")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
    private UsersDAO userDAO;
	/**
     * Get list all users
     * @param null
     * @return List
     */
    @Override
    public List<mUsers> list() {
    	return userDAO.list();
    }
}
