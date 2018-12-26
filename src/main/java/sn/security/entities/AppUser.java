package sn.security.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class AppUser {
@Id @GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String username;
private String password ;
@ManyToMany(cascade={CascadeType.PERSIST,
CascadeType.REMOVE},fetch = FetchType.EAGER)
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


}
