package com.agileidc.itw.bean;


import java.util.List;


public class ItwReworkBean {
	
	
	private List<DefectFlowBean> defectFlowBeanList;
	private  long totalHours;
	private  long totalminutes;
	private  long tempMinutes;
	private  Integer grandTotal;
	private  String  reworkDisplay;
	private String previousStateDisplay;
	private String nextStateDisplay;
	
	public List<DefectFlowBean> getDefectFlowBeanList() {
		return defectFlowBeanList;
	}
	public void setDefectFlowBeanList(List<DefectFlowBean> defectFlowBeanList) {
		this.defectFlowBeanList = defectFlowBeanList;
	}
	public long getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(long totalHours) {
		this.totalHours = totalHours;
	}
	public long getTotalminutes() {
		return totalminutes;
	}
	public void setTotalminutes(long totalminutes) {
		this.totalminutes = totalminutes;
	}
	public long getTempMinutes() {
		return tempMinutes;
	}
	public void setTempMinutes(long tempMinutes) {
		this.tempMinutes = tempMinutes;
	}
	public Integer getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Integer grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getReworkDisplay() {
		return reworkDisplay;
	}
	public void setReworkDisplay(String reworkDisplay) {
		this.reworkDisplay = reworkDisplay;
	}
	public String getPreviousStateDisplay() {
		return previousStateDisplay;
	}
	public void setPreviousStateDisplay(String previousStateDisplay) {
		this.previousStateDisplay = previousStateDisplay;
	}
	public String getNextStateDisplay() {
		return nextStateDisplay;
	}
	public void setNextStateDisplay(String nextStateDisplay) {
		this.nextStateDisplay = nextStateDisplay;
	}
	
	
	
	
}
