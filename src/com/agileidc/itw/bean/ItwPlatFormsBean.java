package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwPlatFormsBean implements Comparable<ItwPlatFormsBean>{

	private Integer id;

	private String shortname;
	
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

	public int compareTo(ItwPlatFormsBean o) {
		return Comparators.ID.compare(this, o);
	}
	public static class Comparators {

		public static Comparator<ItwPlatFormsBean> ID = new Comparator<ItwPlatFormsBean>() {

			public int compare(ItwPlatFormsBean o1, ItwPlatFormsBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}