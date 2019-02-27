package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.Service;

public interface ServicesRepository extends JpaRepository<Service, Long>{

}
