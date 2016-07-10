package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwClientBean implements Comparable<ItwClientBean>{

	private Integer id;

	private String clientname;

	

	private Integer langid;

	private String langType;
	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public int compareTo(ItwClientBean o) {
		return Comparators.ID.compare(this, o);
	}
	public static class Comparators {

		public static Comparator<ItwClientBean> ID = new Comparator<ItwClientBean>() {

			public int compare(ItwClientBean o1, ItwClientBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}