package com.agileidc.itw.bean;

import java.sql.Blob;
import java.util.Comparator;

import com.agileidc.itw.bean.ItwUserBean.Comparators;

import oracle.sql.BLOB;

public class ItwCompanyBean {
	private Integer id;
	private String name;
	private String description;
	private String contact;
	private String phone;
	private String email;
	private String website;
	private String registrationNumber;
	private Blob logo;
	private String mainerror;
	private Integer logoId;
	
	
	  public int compareTo(ItwCompanyBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwCompanyBean> ID = new Comparator<ItwCompanyBean>() {
	            
	            public int compare(ItwCompanyBean o1, ItwCompanyBean o2) {
	                return o2.id.compareTo(o1.id);
	            }
	        };
	 }

	public String getMainerror() {
		return mainerror;
	}

	public void setMainerror(String mainerror) {
		this.mainerror = mainerror;
	}

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
	
	

}
