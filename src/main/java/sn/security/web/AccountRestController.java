package sn.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.security.entities.AppUser;
import sn.security.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm user) {
		AppUser appUser = new AppUser();
		if(user.getPassword().equals(user.getRepassword())) throw new RuntimeException("password must be equal");
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		AppUser userExist= accountService.findUtilisateurByUserName(user.getUsername());
		if(userExist!=null) throw new RuntimeException("user is already exist");
		accountService.addUser(appUser);
		accountService.addRoleToUser(appUser.getUsername(),"USER");
		return appUser;
	}

}
