package com.agileidc.itw.model;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ITW_PROJECT")
public class ItwProject implements Serializable {
	
	private static final long serialVersionUID = -723583058586873511L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "shortname")
	private String shortname;
	
    @DateTimeFormat(pattern="MM/dd/yyyy" )
	@Column(name = "clientid")
	private Integer clientid;
    
    @DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name = "startdate")
	private Date startdate;
	
	@Column(name = "enddate")
	private Date enddate;
	
	@Column(name = "LANGID")
	private Integer langId;
	
	@Column(name = "MODULE")
	private   String modulename;
	private   String clientname;
	
	
	public String getClientname() {
		return clientname;
	}


	public void setClientname(String clientname) {
		this.clientname = clientname;
	}


	public Integer getLangId() {
		return langId;
	}


	public void setLangId(Integer langId) {
		this.langId = langId;
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

	
	



	public Integer getClientid() {
		return clientid;
	}


	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getStartdate() {
		return startdate;
	}

	
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}

	
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}


	public String getModulename() {
		return modulename;
	}


	public void setModulename(String modulename) {
		this.modulename = modulename;
	}


	
	
}
