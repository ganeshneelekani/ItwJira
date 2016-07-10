package com.agileidc.itw.bean;


public class ItwUserRolesBean {

	private static final long serialVersionUID = -723585158586873622L;
	
	private Integer id;
	
	private Integer roleid;
	private Integer userid;
	private Integer langId;
private String username;
private String rolename;
private String langDesc;


	
	public String getLangDesc() {
	return langDesc;
}

public void setLangDesc(String langDesc) {
	this.langDesc = langDesc;
}

	public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getRolename() {
	return rolename;
}

public void setRolename(String rolename) {
	this.rolename = rolename;
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	
	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	


	
}