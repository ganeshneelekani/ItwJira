package com.agileidc.itw.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_SEVERITY")
public class ItwSeverity implements Serializable {

	private static final long serialVersionUID = -723583058586873512L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	
	@Column(name = "iconid")
	private Integer iconId;
	
	
	@Column(name = "shortname")
	private String shortname;

	@Column(name = "colorcode")
	private String colorcode;

	@Column(name = "icon")
	private Blob icon;

	@Column(name = "PRECEDINGID")
	private Integer precedingId;
	
	@Column(name = "LANGID")
	private Integer langId;
	
	@Column(name = "SLA")
	private Integer sla;
	
	@Column(name = "colorcodeid")
	private Integer colorcodeid;
	
	@Column(name = "DEFAULTVALUE")
	private String defaultvalue;
	
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

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public Blob getIcon() {
		return icon;
	}

	public void setIcon(Blob icon) {
		this.icon = icon;
	}

	public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	public Integer getPrecedingId() {
		return precedingId;
	}

	public void setPrecedingId(Integer precedingId) {
		this.precedingId = precedingId;
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

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	public Integer getColorcodeid() {
		return colorcodeid;
	}

	public void setColorcodeid(Integer colorcodeid) {
		this.colorcodeid = colorcodeid;
	}

	
}