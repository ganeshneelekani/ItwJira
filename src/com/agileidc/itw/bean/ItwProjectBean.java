package com.agileidc.itw.bean;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

import com.agileidc.itw.bean.ItwProjectBean.Comparators;


public class ItwProjectBean  {
	private Integer id;
	private String shortname;
	private Integer clientid;
	private String clientname;
	
	private String startdate;
	private String enddate;
	private Integer langId;
	private String modulename;
	
	public Integer getLangId() {
		return langId;
	}
	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	private String langDesc;
    private String mainerror;
    
    

	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public int compareTo(ItwProjectBean o) {
		return Comparators.ID.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<ItwProjectBean> ID = new Comparator<ItwProjectBean>() {

			public int compare(ItwProjectBean o1, ItwProjectBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}
	
	
	

	
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getMainerror() {
		return mainerror;
	}
	public void setMainerror(String mainerror) {
		this.mainerror = mainerror;
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
	
	
	public Integer getClientid() {
		return clientid;
	}
	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
	
}
