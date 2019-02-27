package sn.security.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sn.security.entities.Consultation;
import sn.security.web.Stat;

public interface ConsultationRepository extends JpaRepository<Consultation,Long>{
  public Consultation findConsultationById(Long Id);
  @Query("select c from Consultation c where c.patient.id =:idP ")
  public List<Consultation> getConsultationsByPatient(@Param("idP") Long idP);
  @Query("select c from Consultation c where c.dateCons =:day ")
  public List<Consultation> getConsultationsByDay(@Param("day") Date day);
  @Query(nativeQuery = true, value =
		  " SELECT " +
		  " c.dateCons AS label, COUNT(c.id) AS value " +
		  " FROM " +
		  " consultation c " +
		  " GROUP BY " +
		  " c.dateCons ")  
  public List<Stat> getNbConsultationByDay();
  //@Query(value = "select s.libelle , count(c.id) from consultation c , service s where s.id=c.service_id  group by  s.libelle", nativeQuery = true) 
 // public List<Stat> getConsultationByService();
  
}
