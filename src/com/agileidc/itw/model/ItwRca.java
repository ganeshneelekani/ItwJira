package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITW_RCA")
public class ItwRca implements Serializable {
	
	private static final long serialVersionUID = -723583078986873510L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "INJECTEDSTAGEID")
	private Integer injectedStageId;
	@Column(name = "INJECTEDBY")
	private Integer injectedBy;
	@Column(name = "DETECTEDSTAGEID")
	private Integer detectedStageId;
	@Column(name = "DETECTEDBY")
	private Integer detectedBy;
	@Column(name = "TASKID")
	private Integer taskId;
	@Column(name = "RCADETAILS")
	private String rcaDetails;
	@Column(name = "FEEDBACKGIVENTO")
	private Integer feedbackGivenTo;
	@Column(name = "CORRECTIVEACTIONTAKEN")
	private String correctiveActionTaken;
	
	
	
	
	
	public Integer getFeedbackGivenTo() {
		return feedbackGivenTo;
	}
	public void setFeedbackGivenTo(Integer feedbackGivenTo) {
		this.feedbackGivenTo = feedbackGivenTo;
	}
	public String getCorrectiveActionTaken() {
		return correctiveActionTaken;
	}
	public void setCorrectiveActionTaken(String correctiveActionTaken) {
		this.correctiveActionTaken = correctiveActionTaken;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInjectedStageId() {
		return injectedStageId;
	}
	public void setInjectedStageId(Integer injectedStageId) {
		this.injectedStageId = injectedStageId;
	}
	public Integer getInjectedBy() {
		return injectedBy;
	}
	public void setInjectedBy(Integer injectedBy) {
		this.injectedBy = injectedBy;
	}
	public Integer getDetectedStageId() {
		return detectedStageId;
	}
	public void setDetectedStageId(Integer detectedStageId) {
		this.detectedStageId = detectedStageId;
	}
	public Integer getDetectedBy() {
		return detectedBy;
	}
	public void setDetectedBy(Integer detectedBy) {
		this.detectedBy = detectedBy;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getRcaDetails() {
		return rcaDetails;
	}
	public void setRcaDetails(String rcaDetails) {
		this.rcaDetails = rcaDetails;
	}
	
	
	
	

}
