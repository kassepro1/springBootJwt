package sn.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.security.dao.RoleRepository;
import sn.security.dao.UserRepository;
import sn.security.entities.AppRole;
import sn.security.entities.AppUser;



@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public AppUser addUser(AppUser u) {
		// TODO Auto-generated method stub
		String hpw = bCryptPasswordEncoder.encode(u.getPassword());
		u.setPassword(hpw);
		return userRepository.save(u);
	}

	@Override
	public AppRole addRole(AppRole r) {
		// TODO Auto-generated method stub
		return roleRepository.save(r);
	}

	@Override
	public void addRoleToUser(String username,int role) {
		AppRole r = roleRepository.findById(role).get();
		AppUser u = userRepository.findByUsername(username);
		u.getRoles().add(r);
		
	}

	@Override
	public AppUser findUtilisateurByUserName(String username) {
		   return userRepository.findByUsername(username);

	}



	

}
