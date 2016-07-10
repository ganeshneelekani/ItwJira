package com.agileidc.itw.model;


import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;

@Entity
@Table(name="ITW_USERS")
public class ItwUser implements Serializable{

	private static final long serialVersionUID = -723583058586873489L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "gender")
	private String gender;

	@Column(name = "icon")
	@Lob
	 private Blob icon;

	@Column(name = "emailid")
	private String emailid;

	@Column(name = "primaryroleid")
	private Integer primaryroleid;

	@Column(name = "typeid")
	private Integer typeid;

	@Column(name = "companyid")
	private Integer companyid;

	@Column(name = "password")
	private String password;

	@Column(name = "userid")
	private String userid;
	
	@Column(name = "iconid")
	private Integer iconid;
	
	@Column(name="LANGID")
	private Integer langid;
	
	@Column(name="ENABLED")
	private Integer enabled;
	
	
	@Column(name="FAILEDCOUNT")
	private Integer failedcount;
	
	
	@Column(name = "AUTHORITY")
	private String authority;
	
	@Column(name="ONETIMEPASSWORD")
	private Integer onetimepass;
	
	
	
	public Integer getOnetimepass() {
		return onetimepass;
	}

	public void setOnetimepass(Integer onetimepass) {
		this.onetimepass = onetimepass;
	}

	public void setFailedcount(Integer failedcount) {
		this.failedcount = failedcount;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	
	public void setIconid(Integer iconid) {
		this.iconid = iconid;
	}

	public Integer getIconid() {
		return iconid;
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

	
	public Blob getIcon() {
		return icon;
	}

	
	public void setIcon(Blob icon) {
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
	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
	}

	public Integer getFailedcount() {
		return failedcount;
	}

	public void setFailedcount(int failedcount) {
		this.failedcount = failedcount;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}