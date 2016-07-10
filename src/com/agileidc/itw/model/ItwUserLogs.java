package com.agileidc.itw.model;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_USER_LOGS")
public class ItwUserLogs implements Serializable{

	private static final long serialVersionUID = -723583058586873589L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "userid")
	private String userid;

	@Column(name = "ipaddress")
	private String ipaddress;

	@Column(name = "session1")
	private String session1;

	@Column(name = "browserdetails")
	private String browserdetails;
    
	@Column(name = "onlinestatus")
	private String onlinestatus;
	
	@Column(name = "endorsed")
	private String endorsed;
	
	@Column(name = "endorsetypeid")
	private Integer endorsetypeid;

	@Column(name = "logintime")
	private Timestamp logintime;
	
	@Column(name = "logouttime")
	private Timestamp logouttime;
	
	@Column(name = "lastactivity")
	private Integer lastactivity;
   
	
    
	@Column(name = "creationdate")
	private Timestamp creationdate;
	
	@Column(name = "lastupdatedtime")
	private Timestamp lastupdatedtime;
	
	
	
	@Column(name = "logoutreason")
	private String logoutreason;
	
	
	@Column(name = "field_1")
	private String field1;
	
	@Column(name = "field_2")
	private String field2;
	
	@Column(name = "field_3")
	private String field3;
	
	@Column(name = "field_4")
	private String field4;
	
	@Column(name = "field_5")
	private String field5;
	
	@Column(name = "field_6")
	private Integer field6;
	
	@Column(name = "field_7")
	private Date field7;


	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSession1() {
		return session1;
	}

	public void setSession1(String session1) {
		this.session1 = session1;
	}

	public String getBrowserdetails() {
		return browserdetails;
	}

	public void setBrowserdetails(String browserdetails) {
		this.browserdetails = browserdetails;
	}

	public String getOnlinestatus() {
		return onlinestatus;
	}

	public void setOnlinestatus(String onlinestatus) {
		this.onlinestatus = onlinestatus;
	}

	public String getEndorsed() {
		return endorsed;
	}

	public void setEndorsed(String endorsed) {
		this.endorsed = endorsed;
	}

	public Integer getEndorsetypeid() {
		return endorsetypeid;
	}

	public void setEndorsetypeid(Integer endorsetypeid) {
		this.endorsetypeid = endorsetypeid;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp l) {
		this.logintime = l;
	}

	public Timestamp getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Timestamp logouttime) {
		this.logouttime = logouttime;
	}

	public Integer getLastactivity() {
		return lastactivity;
	}

	public void setLastactivity(Integer lastactivity) {
		this.lastactivity = lastactivity;
	}

	

	public Timestamp getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}

	public Timestamp getLastupdatedtime() {
		return lastupdatedtime;
	}

	public void setLastupdatedtime(Timestamp lastupdatedtime) {
		this.lastupdatedtime = lastupdatedtime;
	}

	

	public String getLogoutreason() {
		return logoutreason;
	}

	public void setLogoutreason(String logoutreason) {
		this.logoutreason = logoutreason;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public Integer getField6() {
		return field6;
	}

	public void setField6(Integer field6) {
		this.field6 = field6;
	}

	public Date getField7() {
		return field7;
	}

	public void setField7(Date field7) {
		this.field7 = field7;
	}
	
	
	
	
}