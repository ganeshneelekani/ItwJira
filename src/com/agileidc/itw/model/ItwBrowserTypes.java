package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_BROWSERTYPE")
public class ItwBrowserTypes implements Serializable{

	private static final long serialVersionUID = -723583058586873520L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="BROWSER_NAME")
	private String browsername;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrowsername() {
		return browsername;
	}

	public void setBrowsername(String browsername) {
		this.browsername = browsername;
	}


	

}
