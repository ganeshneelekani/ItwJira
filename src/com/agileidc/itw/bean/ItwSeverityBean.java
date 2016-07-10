package com.agileidc.itw.bean;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.agileidc.itw.web.IdList;

public class ItwSeverityBean {
	private Integer id;
	private String shortname;
	private String colorcode;
	private Blob icon;
	private Integer logoId;

	private Integer precedingId;
	private String defaultvalue;
	private String colorcodename;
	
	private Integer colourCodeId;
	
	List<IdList> itwcolorcodeidList = new ArrayList<IdList>();
	
	public List<IdList> getItwcolorcodeidList() {
		return itwcolorcodeidList;
	}

	public void setItwcolorcodeidList(List<IdList> itwcolorcodeidList) {
		this.itwcolorcodeidList = itwcolorcodeidList;
	}

	private Integer sla;
	
	  public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public int compareTo(ItwSeverityBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwSeverityBean> ID = new Comparator<ItwSeverityBean>() {
	            
	            public int compare(ItwSeverityBean o1, ItwSeverityBean o2) {
	                return o2.id.compareTo(o1.id);
	            }
	        };
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
		return logoId;
	}

	public void setIconId(Integer logoId) {
		this.logoId = logoId;
	}

	public Integer getPrecedingId() {
		return precedingId;
	}

	public void setPrecedingId(Integer precedingId) {
		this.precedingId = precedingId;
	}

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	public String getColorcodename() {
		return colorcodename;
	}

	public void setColorcodename(String colorcodename) {
		this.colorcodename = colorcodename;
	}

	public Integer getColourCodeId() {
		return colourCodeId;
	}

	public void setColourCodeId(Integer colourCodeId) {
		this.colourCodeId = colourCodeId;
	}

	

	
}
