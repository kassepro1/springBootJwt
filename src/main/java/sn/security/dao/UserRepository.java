package sn.security.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.AppUser;


public interface UserRepository extends JpaRepository<AppUser,Integer> {
	public AppUser findByUsername(String username);
}