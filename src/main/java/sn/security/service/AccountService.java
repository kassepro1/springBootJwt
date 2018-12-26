package sn.security.service;

import sn.security.entities.AppRole;
import sn.security.entities.AppUser;

public interface AccountService {
	public AppUser addUser(AppUser u);
	public AppRole addRole(AppRole r);
	public void addRoleToUser(String username,String  roleName);
	public AppUser findUtilisateurByUserName(String username);
	
	

}
