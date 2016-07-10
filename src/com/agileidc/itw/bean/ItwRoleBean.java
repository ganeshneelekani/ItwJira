package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwRoleBean implements Comparable<ItwRoleBean> {

	private Integer id;
	private String rolename;
	private Integer langid;

	private String langType;
	public int compareTo(ItwRoleBean o) {
		return Comparators.ID.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<ItwRoleBean> ID = new Comparator<ItwRoleBean>() {

			public int compare(ItwRoleBean o1, ItwRoleBean o2) {
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




	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

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
	
	

}
