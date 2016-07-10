package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_ACTIVITY_TYPE")
public class ItwActivityType implements Serializable {
	
	private static final long serialVersionUID = -723583058586873510L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "shortname")
	private String shortname;
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

}
