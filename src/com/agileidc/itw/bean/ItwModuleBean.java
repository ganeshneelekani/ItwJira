package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwModuleBean implements Comparable<ItwModuleBean>{

	private Integer id;

	private String shortname;

	

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

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public int compareTo(ItwModuleBean o) {
		return Comparators.ID.compare(this, o);
	}
	public static class Comparators {

		public static Comparator<ItwModuleBean> ID = new Comparator<ItwModuleBean>() {

			public int compare(ItwModuleBean o1, ItwModuleBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}