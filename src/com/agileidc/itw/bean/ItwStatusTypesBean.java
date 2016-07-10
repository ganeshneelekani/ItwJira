package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwStatusTypesBean implements Comparable<ItwStatusTypesBean> {

	private Integer id;
	private String shortname;
	private String precedingId;
	private String defaultvalue;
	private Integer langId;
	private Integer statusType;
	private String statusname;
	private String precedingname;

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

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
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

	public int compareTo(ItwStatusTypesBean o) {
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

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

	public static class Comparators {

		public static Comparator<ItwStatusTypesBean> ID = new Comparator<ItwStatusTypesBean>() {

			public int compare(ItwStatusTypesBean o1, ItwStatusTypesBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

}