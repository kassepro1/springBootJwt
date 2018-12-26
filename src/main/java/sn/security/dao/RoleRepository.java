package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.AppRole;


public interface RoleRepository extends JpaRepository<AppRole, Integer>{
	
	public AppRole findByRole(String role);
}