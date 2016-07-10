package com.agileidc.itw.bean;

import com.agileidc.itw.helper.ChatDateTime;

public class ItwMessageBean {
	private Integer id;

	private String senderId;

	private String receiverId;

	private String message;

	private ChatDateTime time;
	
	private Integer issueId;

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

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChatDateTime getTime() {
		return time;
	}

	public void setTime(ChatDateTime time) {
		this.time = time;
	}
}
