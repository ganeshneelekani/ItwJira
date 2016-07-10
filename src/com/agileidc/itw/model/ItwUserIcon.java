package com.agileidc.itw.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="ITW_USERS_ICON")
public class ItwUserIcon implements Serializable{

	private static final long serialVersionUID = -723583058586873720L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	

	@Column(name = "icon")
	@Lob
	 private Blob icon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blob getIcon() {
		return icon;
	}

	
	public void setIcon(Blob icon) {
		this.icon = icon;
	}

}

