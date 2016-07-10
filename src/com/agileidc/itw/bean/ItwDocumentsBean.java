package com.agileidc.itw.bean;

import java.sql.Blob;

public class ItwDocumentsBean {
	private Integer id;
	private String name;
	private Integer taskId;
	private Blob content;
	private String mimeType;
	private String description;	
	private String mainerror;
	
	
	
	
	
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMainerror() {
		return mainerror;
	}
	public void setMainerror(String mainerror) {
		this.mainerror = mainerror;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	
	
	
}
