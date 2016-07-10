package com.agileidc.itw.bean;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class ItwReleaseProcessBean {
	
	
	private Integer id;
	private String description;
	private Blob file;
	private Blob updatefile;
	private Integer step;
	
	private java.io.InputStream imagefile;
    private MultipartFile picture;  
    public MultipartFile getPicture()  
    {  
        return picture;  
    }  
    public void setPicture(MultipartFile picture)  
    {  
        this.picture = picture;  
    }  
	private Integer langId;
	
	
	
	public java.io.InputStream getImagefile() {
		return imagefile;
	}
	public void setImagefile(java.io.InputStream imagefile) {
		this.imagefile = imagefile;
	}
	public Integer getLangId() {
		return langId;
	}
	public void setLangId(Integer langId) {
		this.langId = langId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public Blob getUpdatefile() {
		return updatefile;
	}
	public void setUpdatefile(Blob updatefile) {
		this.updatefile = updatefile;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	
	
	
	
	

}
