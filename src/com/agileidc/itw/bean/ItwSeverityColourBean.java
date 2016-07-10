package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwSeverityColourBean implements Comparable<ItwSeverityColourBean>{

	private Integer id;

	private String shortname;

	private String colourCode;

	

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

	public int compareTo(ItwSeverityColourBean o) {
		return Comparators.ID.compare(this, o);
	}
	public String getColourCode() {
		return colourCode;
	}

	public void setColourCode(String colourCode) {
		this.colourCode = colourCode;
	}
	public static class Comparators {

		public static Comparator<ItwSeverityColourBean> ID = new Comparator<ItwSeverityColourBean>() {

			public int compare(ItwSeverityColourBean o1, ItwSeverityColourBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}