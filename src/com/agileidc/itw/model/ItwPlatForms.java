package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_PLATFORMS")
public class ItwPlatForms implements Serializable{

	private static final long serialVersionUID = -723583058586873520L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="SHORTNAME")
	private String shortname;

	@Column(name = "PRECEDINGID")
	private Integer precedingId;
	
	@Column(name = "LANGID")
	private Integer langid;
	
	@Column(name = "DEFAULTVALUE")
	private String defaultvalue;
	
	
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public Integer getPrecedingId() {
		return precedingId;
	}

	public void setPrecedingId(Integer precedingId) {
		this.precedingId = precedingId;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortName() {
		return shortname;
	}

	public void setShortName(String shortname) {
		this.shortname = shortname;
	}
	
	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
	}

}
