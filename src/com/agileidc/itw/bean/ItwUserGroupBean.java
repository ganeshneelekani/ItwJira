package com.agileidc.itw.bean;



public class ItwUserGroupBean  {
	private Integer id;

	private String groupname;
	
	private Integer langId;
	
	private   String usernames;
	private   String usernamesdisplay;
	
	

	public String getUsernamesdisplay() {
		return usernamesdisplay;
	}

	public void setUsernamesdisplay(String usernamesdisplay) {
		this.usernamesdisplay = usernamesdisplay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public String getUsernames() {
		return usernames;
	}

	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}
	
	
	
		
}
