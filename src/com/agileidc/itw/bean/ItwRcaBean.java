package com.agileidc.itw.bean;

import java.util.ArrayList;
import java.util.List;

import com.agileidc.itw.web.IdList;

public class ItwRcaBean {

	private Integer id;

	private Integer injectedStageId;
	private String injectedStageIdDisplay;
	private Integer injectedBy;
	private String injectedByDisplay;

	private Integer detectedStageId;
	private String detectedStageIdDisplay;
	private Integer detectedBy;
	private String detectedByDisplay;
	private String rcaDetails;
	private Integer taskId;

	private Integer feedbackGivenTo;
	private String feedbackGivenToDisplay;

	private String correctiveActionTaken;

	List<IdList> itwInjectedStagesidList = new ArrayList<IdList>();
	List<IdList> itwInjectedByidList = new ArrayList<IdList>();

	List<IdList> itwDetectedStagesidList = new ArrayList<IdList>();
	List<IdList> itwDetectedByidList = new ArrayList<IdList>();

	List<IdList> feedbackGivenToList = new ArrayList<IdList>();

	public List<IdList> getFeedbackGivenToList() {
		return feedbackGivenToList;
	}

	public void setFeedbackGivenToList(List<IdList> feedbackGivenToList) {
		this.feedbackGivenToList = feedbackGivenToList;
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

	public String getInjectedStageIdDisplay() {
		return injectedStageIdDisplay;
	}

	public void setInjectedStageIdDisplay(String injectedStageIdDisplay) {
		this.injectedStageIdDisplay = injectedStageIdDisplay;
	}

	public Integer getInjectedBy() {
		return injectedBy;
	}

	public void setInjectedBy(Integer injectedBy) {
		this.injectedBy = injectedBy;
	}

	public String getInjectedByDisplay() {
		return injectedByDisplay;
	}

	public void setInjectedByDisplay(String injectedByDisplay) {
		this.injectedByDisplay = injectedByDisplay;
	}

	public Integer getDetectedStageId() {
		return detectedStageId;
	}

	public void setDetectedStageId(Integer detectedStageId) {
		this.detectedStageId = detectedStageId;
	}

	public String getDetectedStageIdDisplay() {
		return detectedStageIdDisplay;
	}

	public void setDetectedStageIdDisplay(String detectedStageIdDisplay) {
		this.detectedStageIdDisplay = detectedStageIdDisplay;
	}

	public Integer getDetectedBy() {
		return detectedBy;
	}

	public void setDetectedBy(Integer detectedBy) {
		this.detectedBy = detectedBy;
	}

	public String getDetectedByDisplay() {
		return detectedByDisplay;
	}

	public void setDetectedByDisplay(String detectedByDisplay) {
		this.detectedByDisplay = detectedByDisplay;
	}

	public String getRcaDetails() {
		return rcaDetails;
	}

	public void setRcaDetails(String rcaDetails) {
		this.rcaDetails = rcaDetails;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public List<IdList> getItwInjectedStagesidList() {
		return itwInjectedStagesidList;
	}

	public void setItwInjectedStagesidList(List<IdList> itwInjectedStagesidList) {
		this.itwInjectedStagesidList = itwInjectedStagesidList;
	}

	public List<IdList> getItwInjectedByidList() {
		return itwInjectedByidList;
	}

	public void setItwInjectedByidList(List<IdList> itwInjectedByidList) {
		this.itwInjectedByidList = itwInjectedByidList;
	}

	public List<IdList> getItwDetectedStagesidList() {
		return itwDetectedStagesidList;
	}

	public void setItwDetectedStagesidList(List<IdList> itwDetectedStagesidList) {
		this.itwDetectedStagesidList = itwDetectedStagesidList;
	}

	public List<IdList> getItwDetectedByidList() {
		return itwDetectedByidList;
	}

	public void setItwDetectedByidList(List<IdList> itwDetectedByidList) {
		this.itwDetectedByidList = itwDetectedByidList;
	}

	public String getCorrectiveActionTaken() {
		return correctiveActionTaken;
	}

	public void setCorrectiveActionTaken(String correctiveActionTaken) {
		this.correctiveActionTaken = correctiveActionTaken;
	}

	public Integer getFeedbackGivenTo() {
		return feedbackGivenTo;
	}

	public void setFeedbackGivenTo(Integer feedbackGivenTo) {
		this.feedbackGivenTo = feedbackGivenTo;
	}

	public String getFeedbackGivenToDisplay() {
		return feedbackGivenToDisplay;
	}

	public void setFeedbackGivenToDisplay(String feedbackGivenToDisplay) {
		this.feedbackGivenToDisplay = feedbackGivenToDisplay;
	}

}
