package sn.security.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class DossierMedical implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id ;
 private String numDossier;
 private Date dateConsultation ;
 @ManyToOne
 private Patient patient ;
 @OneToMany(mappedBy="dossierMedical",fetch=FetchType.LAZY)
 private Collection<Consultation> consultations;
public DossierMedical() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNumDossier() {
	return numDossier;
}
public void setNumDossier(String numDossier) {
	this.numDossier = numDossier;
}
public Date getDateConsultation() {
	return dateConsultation;
}
public void setDateConsultation(Date dateConsultation) {
	this.dateConsultation = dateConsultation;
}
public Patient getPatient() {
	return patient;
}
public void setPatient(Patient patient) {
	this.patient = patient;
}
public Collection<Consultation> getConsultations() {
	return consultations;
}
public void setConsultations(Collection<Consultation> consultations) {
	this.consultations = consultations;
}
 
 
}
