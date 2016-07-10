package com.agileidc.itw.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_RELEASES")
public class ItwReleases implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="SHORTNAME")
	private String shortname;

	@Column(name = "langid")
	private Integer langId;
	
	@Column(name = "DEFAULTVALUE")
	private String defaultvalue;
	
	
	@Column(name = "RELEASEDECRIPTION")
	private String releasedescription;
	
	@Column(name = "RELEASEDATE")
	private Date releasedate;
	
	@Column(name = "ACTIVE")
	private String active;
	
	
	
	
	public String getReleasedescription() {
		return releasedescription;
	}

	public void setReleasedescription(String releasedescription) {
		this.releasedescription = releasedescription;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	

}
