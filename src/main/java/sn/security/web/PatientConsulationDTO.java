package sn.security.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import sn.security.entities.AppUser;
import sn.security.entities.Service;

public class PatientConsulationDTO {
	
	private String numDossier;
	private String email;
	private String nom;
	private String tel;
	private String prenom;
	private String adresse;
	private int age;
	private String commentaire;
	private Date dateNaiss;
	private String prescription;
	private Date dateConsultation;
	private List<Service> service = new ArrayList<Service>();;
	private AppUser user;
	
	public PatientConsulationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@JsonProperty("service")
	private void unpackNestedService(Integer service[]) {
	   
	   for(int i=0;i<service.length;i++) {
		   Service r = new Service();
		  Long id =service[i].longValue();
		  r.setId(id);
		  this.service.add(r);
	   }
	}
	public List<Service> getService() {
		return service;
	}
	public void setService(List<Service> service) {
		this.service = service;
	}
	public String getNumDossier() {
		return numDossier;
	}
	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public Date getDateConsultation() {
		return dateConsultation;
	}
	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}
	
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	
	

}
