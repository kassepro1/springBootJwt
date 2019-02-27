package sn.security.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import sn.security.entities.AppRole;
import sn.security.entities.Consultation;
import sn.security.entities.Poste;
import sn.security.entities.Service;
public class UserForm implements Serializable {
	private String username;
	private String password ;
	private String repassword;
	private String email;
	private Boolean actived;
	private String nom;
	private String prenom;
	private String tel;
	private List <Service> service= new ArrayList<Service>();
	private Collection<Consultation> consultations;
	private List <Poste>  poste=new ArrayList<Poste>();
	private List <AppRole> roles = new ArrayList<AppRole>();
	@JsonProperty("roles")
	private void unpackNested(Integer roles[]) {
	   for(int i=0;i<roles.length;i++) {
		   AppRole r = new AppRole();
		  int id =roles[i];
		   System.out.println("Role "+id);
		  r.setId(id);
		  System.out.println("AppRole "+r.getId());
		  this.roles.add(r);
	   }
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
	@JsonProperty("poste")
	private void unpackNestedPoste(Integer poste[]) {
	   
	   for(int i=0;i<poste.length;i++) {
		   Poste r = new Poste();
		  int id =poste[i];
		  r.setId(id);
		  this.poste.add(r);
	   }
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
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
//	public Service getService() {
//		return service;
//	}
//	public void setService(Service service) {
//		this.service = service;
//	}
	public Collection<Consultation> getConsultations() {
		return consultations;
	}
	public void setConsultations(Collection<Consultation> consultations) {
		this.consultations = consultations;
	}
//	public Poste getPoste() {
//		return poste;
//	}
//	public void setPoste(Poste poste) {
//		this.poste = poste;
//	}
	public List<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}
	public List<Service> getService() {
		return service;
	}
	public void setService(List<Service> service) {
		this.service = service;
	}
	public List<Poste> getPoste() {
		return poste;
	}
	public void setPoste(List<Poste> poste) {
		this.poste = poste;
	}
	

}
