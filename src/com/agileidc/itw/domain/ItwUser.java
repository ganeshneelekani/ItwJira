/**
 * 
 */
package com.agileidc.itw.domain;
 

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Lob;



/**
 * @author Rajeev
 * 
 */
public class ItwUser {
	
	private Integer id;

	
	private String firstname;

	
	private String lastname;

	
	private String gender;

	
	private byte[] icon;

	
	private String emailid;

	
	private Integer primaryroleid;

	
	private Integer typeid;

	
	private Integer companyid;

	
	private String password;

	
	private String userid;
	
	
	
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getFirstname() {
		return firstname;
	}

	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
	public String getLastname() {
		return lastname;
	}

	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public String getGender() {
		return gender;
	}

	
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public byte[] getIcon() {
		return icon;
	}

	
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	
	public String getEmailid() {
		return emailid;
	}

	
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	
	public Integer getPrimaryroleid() {
		return primaryroleid;
	}

	
	public void setPrimaryroleid(Integer primaryroleid) {
		this.primaryroleid = primaryroleid;
	}

	
	public Integer getTypeid() {
		return typeid;
	}

	
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	
	public Integer getCompanyid() {
		return companyid;
	}

	
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
}
