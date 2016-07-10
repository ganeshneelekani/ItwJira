package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_CHART_STATUS")
public class ItwChartStatus implements Serializable {

	private static final long serialVersionUID = -723583058589873520L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "SHORTNAME")
	private String shortname;

	@Column(name = "LANGID")
	private Integer langid;

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
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
