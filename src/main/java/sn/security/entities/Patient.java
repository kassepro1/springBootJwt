package sn.security.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name="patient")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Patient implements Serializable {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id  
 private Long id;
 private String numerodossier;
 private String nom;
 private String prenom;
 private String tel;
 private String email;
 private String adresse;
 private int age ;
 @Temporal(TemporalType.DATE)
 private Date datenais;
 @OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
 private Collection<Consultation> consultations;
 @OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
 private Collection<DossierMedical> dossiersMedical;
 
 
 
 
public Patient() {
	super();
	// TODO Auto-generated constructor stub
}
public Patient(Long id, String numerodossier, String nom, String tel, Date datenais) {
	super();
	this.id = id;
	this.numerodossier = numerodossier;
	this.nom = nom;
	this.tel = tel;
	this.datenais = datenais;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNumerodossier() {
	return numerodossier;
}
public void setNumerodossier(String numerodossier) {
	this.numerodossier = numerodossier;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Date getDatenais() {
	return datenais;
}
public void setDatenais(Date datenais) {
	this.datenais = datenais;
}
public Collection<Consultation> getConsultations() {
	return consultations;
}
public void setConsultations(Collection<Consultation> consultations) {
	this.consultations = consultations;
}
public Collection<DossierMedical> getDossiersMedical() {
	return dossiersMedical;
}
public void setDossiersMedical(Collection<DossierMedical> dossiersMedical) {
	this.dossiersMedical = dossiersMedical;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return prenom+" "+nom;
}
 
}
