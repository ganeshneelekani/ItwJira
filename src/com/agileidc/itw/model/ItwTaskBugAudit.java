package com.agileidc.itw.model;


import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name="ITW_TASK_BUGS_AUDIT")
public class ItwTaskBugAudit implements Serializable {
	private static final long serialVersionUID = -723583058586873499L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	 private Integer id;
	@Column(name = "shortname")
	    private String shortname;
	@Column(name = "type1")
	    private String type1;
	@Column(name = "lock1")
	    private String lock1;
	@Column(name = "assigneeid")
	    private Integer assigneeid;
	@Column(name = "moduleid")
	    private Integer moduleid;
	@Column(name = "statusid")
	    private Integer statusid;
	@Column(name = "projectid")
	    private Integer projectid;
	@Column(name = "releaseid")
	    private Integer releaseid;
	@Column(name = "machinetypeid")
	    private Integer machinetypeid;
	@Column(name = "platformid")
	    private Integer platformid;
	@Column(name = "browserid")
	    private Integer browserid;
	@Column(name = "targetmilestoneid")
	    private Integer targetmilestoneid;
	@Column(name = "severityid")
	    private Integer severityid;
	@Column(name = "priorityid")
	    private Integer priorityid;
	@Column(name = "attachment")
	    private String attachment;
	@Column(name = "icon")
	@Lob
	    private Blob icon;
	
	@Column(name = "mimetype")
	    private String mimetype;
	@Column(name = "name")
	    private String name;
	
	@Column(name = "VERSION")
    private String version;
	@Column(name = "URL")
	private String url = null;
	
	@Column(name = "DEPENDSON")
    private String dependsOn;
	@Column(name = "REASONFOROPEN")
	private String reasonforOpen;
	
	@Column(name = "APPROVEDBY")
	private String approvedBy;
	
	@Column(name = "APPROVRDSTATUS")
	private String approvedStatus;
	
	
	
	
	

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getReasonforOpen() {
		return reasonforOpen;
	}

	public void setReasonforOpen(String reasonforOpen) {
		this.reasonforOpen = reasonforOpen;
	}

	@Column(name = "size1")
	    private Integer size1;
	@Column(name = "resolution")
	    private String resolution;
	@Column(name = "summary")
	    private String summary;
	@Column(name = "shortsummary")
	    private String shortsummary;
	@Column(name = "efforts")
	    private String efforts;
	@Column(name = "deadline")
	    private Date deadline;
	@Column(name = "additionalRef1")
	    private Integer additionalRef1;
	@Column(name = "additionalRef2")
	    private Integer additionalRef2;
	@Column(name = "additionalRef3")
	    private Integer additionalRef3;
	@Column(name = "createdby")
	    private Integer createdby;
	@Column(name = "createdtime")
	    private java.util.Date createdtime;
	@Column(name = "lastupdatedby")
	    private Integer lastupdatedby;
	@Column(name = "lastupdatedtime")
	    private java.util.Date lastupdatedtime;
	@Column(name = "bulkupload")
	    private String bulkupload;
	@Column(name = "bulkuploadfilename")
	    private String bulkuploadfilename;
	
	@Column(name = "taskid")
	 private Integer taskId;
	
	

	    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

		public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getShortname() {
	        return shortname;
	    }

	    public void setShortname(String shortname) {
	        this.shortname = shortname;
	    }

	    public String getType1() {
	        return type1;
	    }

	    public void setType1(String type1) {
	        this.type1 = type1;
	    }


	    public String getLock1() {
	        return lock1;
	    }

	    public void setLock1(String lock1) {
	        this.lock1 = lock1;
	    }

	    public Integer getAssigneeid() {
	        return assigneeid;
	    }

	    public void setAssigneeid(Integer assigneeid) {
	        this.assigneeid = assigneeid;
	    }

	    public Integer getModuleid() {
	        return moduleid;
	    }


	    public void setModuleid(Integer moduleid) {
	        this.moduleid = moduleid;
	    }

	    public Integer getStatusid() {
	        return statusid;
	    }

	    public void setStatusid(Integer statusid) {
	        this.statusid = statusid;
	    }

	    public Integer getProjectid() {
	        return projectid;
	    }

	    public void setProjectid(Integer projectid) {
	        this.projectid = projectid;
	    }

	    public Integer getReleaseid() {
	        return releaseid;
	    }

	    public void setReleaseid(Integer releaseid) {
	        this.releaseid = releaseid;
	    }

	
	    public Integer getMachinetypeid() {
	        return machinetypeid;
	    }

	    public void setMachinetypeid(Integer machinetypeid) {
	        this.machinetypeid = machinetypeid;
	    }

	    public Integer getPlatformid() {
	        return platformid;
	    }


	    public void setPlatformid(Integer platformid) {
	        this.platformid = platformid;
	    }


	    public Integer getBrowserid() {
	        return browserid;
	    }


	    public void setBrowserid(Integer browserid) {
	        this.browserid = browserid;
	    }

	    public Integer getTargetmilestoneid() {
	        return targetmilestoneid;
	    }

	    public void setTargetmilestoneid(Integer targetmilestoneid) {
	        this.targetmilestoneid = targetmilestoneid;
	    }


	    public Integer getSeverityid() {
	        return severityid;
	    }


	    public void setSeverityid(Integer severityid) {
	        this.severityid = severityid;
	    }


	    public Integer getPriorityid() {
	        return priorityid;
	    }


	    public void setPriorityid(Integer priorityid) {
	        this.priorityid = priorityid;
	    }

	    public String getAttachment() {
	        return attachment;
	    }


	    public void setAttachment(String attachment) {
	        this.attachment = attachment;
	    }

	    public Blob getIcon() {
	        return icon;
	    }


	    public void setIcon(Blob icon) {
	        this.icon = icon;
	    }


	    public String getMimetype() {
	        return mimetype;
	    }

	    public void setMimetype(String mimetype) {
	        this.mimetype = mimetype;
	    }


	    public String getName() {
	        return name;
	    }


	    public void setName(String name) {
	        this.name = name;
	    }

	    public Integer getSize1() {
	        return size1;
	    }


	    public void setSize1(Integer size1) {
	        this.size1 = size1;
	    }


	    public String getResolution() {
	        return resolution;
	    }


	    public void setResolution(String resolution) {
	        this.resolution = resolution;
	    }

	    public String getSummary() {
	        return summary;
	    }


	    public void setSummary(String summary) {
	        this.summary = summary;
	    }


	    public String getShortsummary() {
	        return shortsummary;
	    }

	    public void setShortsummary(String shortsummary) {
	        this.shortsummary = shortsummary;
	    }

	    public String getEfforts() {
	        return efforts;
	    }

	    public void setEfforts(String efforts) {
	        this.efforts = efforts;
	    }

	    public Date getDeadline() {
	        return deadline;
	    }

	    public void setDeadline(Date deadline) {
	        this.deadline = deadline;
	    }

	    public Integer getAdditionalRef1() {
	        return additionalRef1;
	    }


	    public void setAdditionalRef1(Integer additionalRef1) {
	        this.additionalRef1 = additionalRef1;
	    }

	    public Integer getAdditionalRef2() {
	        return additionalRef2;
	    }


	    public void setAdditionalRef2(Integer additionalRef2) {
	        this.additionalRef2 = additionalRef2;
	    }

	    public Integer getAdditionalRef3() {
	        return additionalRef3;
	    }

	    public void setAdditionalRef3(Integer additionalRef3) {
	        this.additionalRef3 = additionalRef3;
	    }

	    public Integer getCreatedby() {
	        return createdby;
	    }

	   
	    public void setCreatedby(Integer createdby) {
	        this.createdby = createdby;
	    }

	   	    public java.util.Date getCreatedtime() {
	        return createdtime;
	    }

	    public void setCreatedtime(java.util.Date createdtime) {
	        this.createdtime = createdtime;
	    }

	    public Integer getLastupdatedby() {
	        return lastupdatedby;
	    }

	   
	    public void setLastupdatedby(Integer lastupdatedby) {
	        this.lastupdatedby = lastupdatedby;
	    }

	 
	    public java.util.Date getLastupdatedtime() {
	        return lastupdatedtime;
	    }

	   
	    public void setLastupdatedtime(java.util.Date lastupdatedtime) {
	        this.lastupdatedtime = lastupdatedtime;
	    }

	  
	    public String getBulkupload() {
	        return bulkupload;
	    }

	  
	    public void setBulkupload(String bulkupload) {
	        this.bulkupload = bulkupload;
	    }

	 
	    public String getBulkuploadfilename() {
	        return bulkuploadfilename;
	    }

	   
	    public void setBulkuploadfilename(String bulkuploadfilename) {
	        this.bulkuploadfilename = bulkuploadfilename;
	    }

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getDependsOn() {
			return dependsOn;
		}

		public void setDependsOn(String dependsOn) {
			this.dependsOn = dependsOn;
		}
	    
	    
}
