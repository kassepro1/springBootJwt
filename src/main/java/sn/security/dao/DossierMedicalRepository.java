package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.DossierMedical;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
	public DossierMedical findDossierMedicalBynumDossier(String numDossier);

}
