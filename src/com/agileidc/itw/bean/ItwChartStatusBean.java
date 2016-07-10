package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwChartStatusBean implements Comparable<ItwChartStatusBean> {

	private Integer id;

	private String shortname;

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

	public int compareTo(ItwChartStatusBean o) {
		return Comparators.ID.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<ItwChartStatusBean> ID = new Comparator<ItwChartStatusBean>() {

			public int compare(ItwChartStatusBean o1, ItwChartStatusBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

}