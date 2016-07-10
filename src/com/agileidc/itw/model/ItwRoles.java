package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_ROLES")
public class ItwRoles implements Serializable{

	private static final long serialVersionUID = -723583058586873520L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="ROLENAME")
	private String rolename;
	

	@Column(name="LANGID")
	private Integer langid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
	}


}
