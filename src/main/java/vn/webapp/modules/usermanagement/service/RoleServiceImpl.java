package vn.webapp.modules.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.usermanagement.dao.RoleDAO;
import vn.webapp.modules.usermanagement.dao.mFacultyDAO;
import vn.webapp.modules.usermanagement.model.Role;
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return roleDAO.list();
	}

}
