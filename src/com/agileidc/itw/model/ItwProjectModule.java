package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_PROJECT_MODULES")
public class ItwProjectModule implements Serializable{

	private static final long serialVersionUID = -723583058586873622L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="PROJECTID")
	private Integer projectid;

	@Column(name="MODULEID")
	private Integer moduleid;

	
	@Column(name = "LANGID")
	private Integer langId;
	

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public Integer getModuleid() {
		return moduleid;
	}
	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}

	

	


}
