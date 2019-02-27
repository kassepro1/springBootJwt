package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import sn.security.entities.Patient;
@RestResource
public interface PatientRepository extends JpaRepository<Patient,Long> {

}
