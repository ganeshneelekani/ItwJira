package com.agileidc.itw.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_ONLINEUSERS")
public class ItwOnlineUsers implements Serializable {

	private static final long serialVersionUID = -723583058586873389L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "USERID")
	public String userId;

	@Column(name = "LIVESTATUS")
	public int liveStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(int liveStatus) {
		this.liveStatus = liveStatus;
	}
}