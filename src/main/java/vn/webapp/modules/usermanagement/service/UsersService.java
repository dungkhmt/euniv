package vn.webapp.modules.usermanagement.service;

import java.util.List;

import vn.webapp.modules.usermanagement.model.mUsers;

public interface UsersService {
	
//	public Users loadUserById(int userId);
//	
//    public User loadUserByUsername(String username);
//    
//    public Users loadUserByUsernameAndId(String username, int id);

    public List<mUsers> list();
    
//    public HashMap<String, String> viewDetail(int id);
//    
//    public int removeUser(String id);
//    
//    public int saveAUser(String username, String password, String salt, String email, String role, int activated);
//    
//    public void editAnUser(int userId, String username, String password, String email, String role, int activated, int userRoleId, int staffId, String userDepartment);
//    
//    public void saveSettings(String username, String password);
}
