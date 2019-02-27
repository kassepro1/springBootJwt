package sn.security.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AppRole  implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;
private String role;

public AppRole() {
	super();
	// TODO Auto-generated constructor stub
}


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}


@Override
public String toString() {
	// TODO Auto-generated method stub
	return id+"-"+role;
}


}
