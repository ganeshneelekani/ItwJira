package com.agileidc.itw.bean;

import java.sql.Blob;
import java.util.Comparator;

public class ItwImageBean {
	private Integer id;
	private Integer taskId;
	private Blob image;
	private String mimeType;
	
	private String mainerror;
	
	private String description;	
	
	  public int compareTo(ItwImageBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwImageBean> ID = new Comparator<ItwImageBean>() {
	            
	            public int compare(ItwImageBean o1, ItwImageBean o2) {
	                return o2.id.compareTo(o1.id);
	            }
	        };
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

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	
	

}
