package com.agileidc.itw.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.agileidc.itw.helper.ChatDateTime;

@Entity
@Table(name = "ITW_MESSAGES")
public class ItwMessage implements Serializable {

	private static final long serialVersionUID = -723583058586874189L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "SENDER")
	private String senderId;

	@Column(name = "RECEIVER")
	private String receiverId;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "TIME")
	private String time;

	@Column(name = "MSGREAD")
	private String msgRead;

	@Column(name = "ISSUEID")
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

	public String getMsgRead() {
		return msgRead;
	}

	public void setMsgRead(String msgRead) {
		this.msgRead = msgRead;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}