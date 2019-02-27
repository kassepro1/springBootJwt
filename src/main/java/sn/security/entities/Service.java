package sn.security.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name="service")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Service implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String libelle;
	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	private Collection<Consultation> consultations;
	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	private Collection<AppUser> utilisateur;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(Long id, String libelle, Collection<Consultation> consultations, Collection<AppUser> utilisateur) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.consultations = consultations;
		this.utilisateur = utilisateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Collection<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Collection<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Collection<AppUser> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Collection<AppUser> utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.libelle;
	}

	
	
}
