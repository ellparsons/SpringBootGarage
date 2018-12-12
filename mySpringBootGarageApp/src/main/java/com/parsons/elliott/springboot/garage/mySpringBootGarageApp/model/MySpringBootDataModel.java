package com.parsons.elliott.springboot.garage.mySpringBootGarageApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate","lastModified"}, allowGetters = true)
public class MySpringBootDataModel implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String make;
	
	@NotBlank
	private String model;
	 
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	@Column(nullable = false, updatable =false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;

	
	public MySpringBootDataModel(String type, String make, String model) 
	{
		this.type = type;   
		this.make = make;  
		this.model = model;  
	}
	public MySpringBootDataModel() 
	{
	}
	public Long getId() {
		return this.id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMake() {
		return this.make;
	}
	public void setMake(String make) { 
		this.make = make;
	}
	public String getModel() {
		return this.model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date setCreationDate() {
		return this.creationDate;
	}
	public void getCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date setLastModified() {
		return this.lastModified;
	}
	public void getLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
}
