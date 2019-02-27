package sn.security.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AppUser implements Serializable{
@Id @GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String username;
private String password ;
private String email;
private Boolean actived;
private String nom;
private String prenom;
private String tel;
@ManyToOne
private Service service;

@OneToMany(mappedBy="utilisateur",fetch=FetchType.LAZY)
private Collection<Consultation> consultations;
@ManyToOne
private Poste poste;
@ManyToMany(cascade={ CascadeType.ALL },fetch = FetchType.EAGER)
private List <AppRole> roles = new ArrayList<AppRole>();
public AppUser(Long id, String username, String password) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
@JsonIgnore
public String getPassword() {
	return password;
}
@JsonSetter
public void setPassword(String password) {
	this.password = password;
}
public AppUser() {
	super();
	// TODO Auto-generated constructor stub
}
public List<AppRole> getRoles() {
	return roles;
}
public void setRoles(List<AppRole> roles) {
	this.roles = roles;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Boolean getActived() {
	return actived;
}
public void setActived(Boolean actived) {
	this.actived = actived;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Service getService() {
	return service;
}
public void setService(Service service) {
	this.service = service;
}
public Collection<Consultation> getConsultations() {
	return consultations;
}
public void setConsultations(Collection<Consultation> consultations) {
	this.consultations = consultations;
}
public Poste getPoste() {
	return poste;
}
public void setPoste(Poste poste) {
	this.poste = poste;
}


}
