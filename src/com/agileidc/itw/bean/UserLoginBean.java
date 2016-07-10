package com.agileidc.itw.bean;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
public class UserLoginBean {
	
	private String userid;
	private String j_username;
	private String j_password;
	
	private String password;
	
	private String mainerror;
	private String newpassword;
	private String confirmpassword;
	
	
	public String getMainerror() {
		return mainerror;
	}
	public void setMainerror(String mainerror) {
		this.mainerror = mainerror;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

}
