package com.agileidc.itw.bean;

import java.util.Comparator;

import com.agileidc.itw.bean.ItwModuleBean.Comparators;

public class ItwBrowserTypesBean implements Comparable<ItwBrowserTypesBean>{

	private Integer id;

	private String browsername;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrowsername() {
		return browsername;
	}

	public void setBrowsername(String browsername) {
		this.browsername = browsername;
	}
	public int compareTo(ItwBrowserTypesBean o) {
		return Comparators.ID.compare(this, o);
	}
	public static class Comparators {

		public static Comparator<ItwBrowserTypesBean> ID = new Comparator<ItwBrowserTypesBean>() {

			public int compare(ItwBrowserTypesBean o1, ItwBrowserTypesBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}