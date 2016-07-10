package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwReleasesBean implements Comparable<ItwReleasesBean>{

	private Integer id;
	private Integer langid;
	private String shortname;
	private String defaultvalue;
	private String releasedescription;
	private String releasedate;
	private String active;
	
	
	
	public String getReleasedescription() {
		return releasedescription;
	}

	public void setReleasedescription(String releasedescription) {
		this.releasedescription = releasedescription;
	}

	

	public String getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
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

	public int compareTo(ItwReleasesBean o) {
		return Comparators.ID.compare(this, o);
	}
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	public static class Comparators {

		public static Comparator<ItwReleasesBean> ID = new Comparator<ItwReleasesBean>() {

			public int compare(ItwReleasesBean o1, ItwReleasesBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	
}