package com.agileidc.itw.bean;

import java.util.Date;


public class DefectFlowBean {
	
	private Integer previousStateId;
	private Integer nextStateId;
	private String reworkFlag;
	private Integer reworkCount;
	private String previousState;
	private String nextState;
	private Date previousTime;
	private String previousTimeDisplay;
	private String nextTimeDisplay;
	
	private Date nextTime;
	private String elapse;
	private long elapseMinutes;
	
	private int alreadyUsed;
	public int getAlreadyUsed() {
		return alreadyUsed;
	}
	public void setAlreadyUsed(int alreadyUsed) {
		this.alreadyUsed = alreadyUsed;
	}
	public String getPreviousState() {
		return previousState;
	}
	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}
	public String getNextState() {
		return nextState;
	}
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}
	public Date getPreviousTime() {
		return previousTime;
	}
	public void setPreviousTime(Date previousTime) {
		this.previousTime = previousTime;
	}
	public Date getNextTime() {
		return nextTime;
	}
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}
	public String getElapse() {
		return elapse;
	}
	public void setElapse(String diffHours) {
		elapse = diffHours;
	}
	public Integer getPreviousStateId() {
		return previousStateId;
	}
	public void setPreviousStateId(Integer previousStateId) {
		this.previousStateId = previousStateId;
	}
	public Integer getNextStateId() {
		return nextStateId;
	}
	public void setNextStateId(Integer nextStateId) {
		this.nextStateId = nextStateId;
	}
	public String getReworkFlag() {
		return reworkFlag;
	}
	public void setReworkFlag(String reworkFlag) {
		this.reworkFlag = reworkFlag;
	}
	public Integer getReworkCount() {
		return reworkCount;
	}
	public void setReworkCount(Integer reworkCount) {
		this.reworkCount = reworkCount;
	}
	public long getElapseMinutes() {
		return elapseMinutes;
	}
	public void setElapseMinutes(long l) {
		this.elapseMinutes = l;
	}
	public String getPreviousTimeDisplay() {
		return previousTimeDisplay;
	}
	public void setPreviousTimeDisplay(String previousTimeDisplay) {
		this.previousTimeDisplay = previousTimeDisplay;
	}
	public String getNextTimeDisplay() {
		return nextTimeDisplay;
	}
	public void setNextTimeDisplay(String nextTimeDisplay) {
		this.nextTimeDisplay = nextTimeDisplay;
	}
	
	
	
	
}
