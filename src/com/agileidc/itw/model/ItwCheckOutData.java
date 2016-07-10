package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_CHECKOUT_DATA")
public class ItwCheckOutData implements Serializable {

	private static final long serialVersionUID = -723383058586873510L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "userid")
	private String userId;

	@Column(name = "action_type")
	private Integer actionType;

	@Column(name = "action_time")
	private java.util.Date actionTime;

	@Column(name = "node_id")
	private String nodeId;
	
	@Column(name = "issueid")
	private Integer issueId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(java.util.Date actionTime) {
		this.actionTime = actionTime;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

}
