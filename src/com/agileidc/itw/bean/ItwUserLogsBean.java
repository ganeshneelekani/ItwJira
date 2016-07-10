package com.agileidc.itw.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

public class ItwUserLogsBean implements Comparable<ItwUserLogsBean> {

	private Integer id;
	private String userid;
	private String ipaddress;
	private String session1;
	private String browserdetails;
	private String onlinestatus;
	private String endorsed;
	private Integer endorsetypeid;
	private Timestamp logintime;
	private Timestamp logouttime;
	private Integer lastactivity;
	
	private Timestamp creationdate;
	private Timestamp lastupdatedtime;
	
	private String logoutreason;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private Date field6;
	private Integer field7;

	
	

	    public int compareTo(ItwUserLogsBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwUserLogsBean> ID = new Comparator<ItwUserLogsBean>() {
	            
	            public int compare(ItwUserLogsBean o1, ItwUserLogsBean o2) {
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

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
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

	public Date getField6() {
		return field6;
	}

	public void setField6(Date field6) {
		this.field6 = field6;
	}

	public Integer getField7() {
		return field7;
	}

	public void setField7(Integer field7) {
		this.field7 = field7;
	}
	
	 
	 
}
