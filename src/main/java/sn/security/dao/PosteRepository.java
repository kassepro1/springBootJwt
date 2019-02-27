package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.Poste;

public interface PosteRepository extends JpaRepository<Poste, Integer> {

}
