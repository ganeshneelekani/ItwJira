package com.agileidc.itw.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ITW_TWEETS")
public class ItwTweets implements Serializable{

	private static final long serialVersionUID = -723583058586873980L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ISSUEID")
	private Integer issueId;
	
	@Column(name = "PROJECTID")
	private Integer projectId;
	
	@Column(name = "USERID")
	private Integer userid;
	
	@Column(name="TWEETMSG")
	private String tweetmsg;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TWEETTIME", insertable=false)

	private java.util.Date tweettime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTweetmsg() {
		return tweetmsg;
	}

	public void setTweetmsg(String tweetmsg) {
		this.tweetmsg = tweetmsg;
	}
	
	public java.util.Date getTweettime() {
		return tweettime;
	}

	public void setTweettime(java.util.Date tweettime) {
		this.tweettime = tweettime;
	}

}
