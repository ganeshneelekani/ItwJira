package com.agileidc.itw.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



import java.sql.Blob;



@Entity
@Table(name="Itw_Company")
public class ItwCompany implements Serializable{

	private static final long serialVersionUID = -723583058586873660L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "logoid")
	private Integer logoId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="website")
	private String website;
	
	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;
	@Lob
	@Column(name="logo")
	private Blob logo;
	
	@Column(name = "langid")
	private Integer langId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	public Integer getLogoId() {
		return logoId;
	}

	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	
	
}
