package com.agileidc.itw.bean;


import java.util.Comparator;

public class ItwPriorityBean {
	private Integer id;
	private String shortname;
	private String colorcode;
	private Integer precedingId;
	private String defaultvalue;
	
	
	  public Integer getPrecedingId() {
		return precedingId;
	}

	public void setPrecedingId(Integer precedingId) {
		this.precedingId = precedingId;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public int compareTo(ItwPriorityBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwPriorityBean> ID = new Comparator<ItwPriorityBean>() {
	            
	            public int compare(ItwPriorityBean o1, ItwPriorityBean o2) {
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
	
}
