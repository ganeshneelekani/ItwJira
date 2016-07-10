package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_STATUS_TYPES")
public class ItwStatusTypes implements Serializable{

	private static final long serialVersionUID = -723583058586873679L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="SHORTNAME")
	private String shortname;
	
	@Column(name = "LANGID")
	private Integer langId;
	
	@Column(name = "PRECEDINGID")
	private String precedingId;
	
	@Column(name = "DEFAULTVALUE")
	private String defaultvalue;

	@Column(name = "STATUSTYPE")
	private Integer statusType;
	
	
	public String getPrecedingId() {
		return precedingId;
	}
	

	public void setPrecedingId(String precedingId) {
		this.precedingId = precedingId;
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

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

}
