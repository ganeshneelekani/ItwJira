package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwStagesTypesBean implements Comparable<ItwStagesTypesBean>{

	private Integer id;

	private String shortname;
	
	private String precedingId;
	private String defaultvalue;
	private String precedingname;
	private Integer langId;
	
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

	public int compareTo(ItwStagesTypesBean o) {
		return Comparators.ID.compare(this, o);
	}
	public String getPrecedingId() {
		return precedingId;
	}

	public void setPrecedingId(String precedingId) {
		this.precedingId = precedingId;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	
	public String getPrecedingname() {
		return precedingname;
	}

	public void setPrecedingname(String precedingname) {
		this.precedingname = precedingname;
	}
	
	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	public static class Comparators {

		public static Comparator<ItwStagesTypesBean> ID = new Comparator<ItwStagesTypesBean>() {

			public int compare(ItwStagesTypesBean o1, ItwStagesTypesBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}