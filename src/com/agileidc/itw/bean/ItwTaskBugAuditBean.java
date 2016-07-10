package com.agileidc.itw.bean;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.agileidc.itw.model.ItwImage;
import com.agileidc.itw.web.IdList;
import com.agileidc.itw.web.ModuleList;

public class ItwTaskBugAuditBean implements Comparable<ItwTaskBugAuditBean>  {
	private Integer id;

	private String reasonforOpen;
	private String shortname = null;

	private String type1 = null;

	private String type1display = null;

	private String lock1 = null;

	private Integer assigneeid = null;
	private String assigneeiddisplay = null;

	private Integer moduleid = null;
	private String moduleiddisplay = null;

	private Integer statusid = null;
	private String statusiddisplay;

	private Integer projectid = null;
	private String projectiddisplay;

	private Integer releaseid;

	private Integer machinetypeid;

	private Integer platformid;
	private String platformiddisplay;

	private Integer browserid;

	private Integer targetmilestoneid;

	private Integer severityid;
	private String severityiddisplay;

	private Integer priorityid;
	private String priorityiddisplay;

	private String attachment;

	private Blob icon;

	private String mimetype;

	private String name;

	private Integer size1;

	private String resolution;

	private String summary;

	private String shortsummary;

	private String efforts;

	private String currentloggedinuserid;

	private Date deadline;
	private String deadlinedisplay;

	private Integer additionalRef1;

	private Integer additionalRef2;

	private Integer additionalRef3;

	private Integer createdby;
	
	private String createdbydisplay;

	private java.util.Date createdtime;

	private Integer lastupdatedby;
	
	private String lastupdatedbydisplay;

	private java.util.Date lastupdatedtime;

	private String bulkupload;

	private String bulkuploadfilename;
	
	private IdList idList;
	private ItwImage itwImage;

	private Integer taskId = null;
	
	private String lastUpdateDisplayTimeInGMT;
	
	List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
	List<IdList> itwSeverityIdList = new ArrayList<IdList>();
	List<IdList> itwType1List = new ArrayList<IdList>();
	
	List<IdList> itwPriorityidList = new ArrayList<IdList>();
	List<IdList> itwModuleidList = new ArrayList<IdList>();
	List<IdList> itwPlatformidList = new ArrayList<IdList>();
	List<IdList> itwProjectidList = new ArrayList<IdList>();
	List<IdList> itwStatusidList = new ArrayList<IdList>();
	
	
	private String approvedBy;
	private String approvedStatus;
	private String dependsOn;
	private String url = null;
	
	 private String version;

	  public int compareTo(ItwTaskBugAuditBean o) {
	        return Comparators.ID.compare(this, o);
	    }
	 
	 public static class Comparators {

	        public static Comparator<ItwTaskBugAuditBean> ID = new Comparator<ItwTaskBugAuditBean>() {
	            
	            public int compare(ItwTaskBugAuditBean o1, ItwTaskBugAuditBean o2) {
	                return o2.id.compareTo(o1.id);
	            }
	        };
	 }
	
public IdList getSeverityIdList() {
		
		return idList;
	}

	public void setSeverityIdList(IdList idList) {
		this.idList = idList;
	}
	
	

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

public String getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(String dependsOn) {
		this.dependsOn = dependsOn;
	}

public List<IdList> getItwSeverityIdList() {
		
		return itwSeverityIdList;
	}

	public void setItwSeverityIdList(List<IdList> itwSeverityIdList) {
		this.itwSeverityIdList = itwSeverityIdList;
	}
	

public List<ModuleList> getItwModuleList() {
		
		return itwModuleList;
	}



	public IdList getIdList() {
	return idList;
}

public void setIdList(IdList idList) {
	this.idList = idList;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getVersion() {
	return version;
}

public void setVersion(String version) {
	this.version = version;
}

	public void setItwModuleList(List<ModuleList> itwModuleList) {
		this.itwModuleList = itwModuleList;
	}
	
public List<IdList> getItwModuleidList() {
		
		return itwModuleidList;
	}

	public void setItwModuleidList(List<IdList> itwModuleidList) {
		this.itwModuleidList = itwModuleidList;
	}


public List<IdList> getItwType1List() {
		
		return itwType1List;
	}

	public void setItwType1List(List<IdList> itwType1List) {
		this.itwType1List = itwType1List;
	}

public List<IdList> getItwPriorityidList() {
		
		return itwPriorityidList;
	}

	public void setItwPriorityidList(List<IdList> itwPriorityidList) {
		this.itwPriorityidList = itwPriorityidList;
	}

public List<IdList> getItwPlatformidList() {
		
		return itwPlatformidList;
	}

	public void setItwPlatformidList(List<IdList> itwPlatformidList) {
		this.itwPlatformidList = itwPlatformidList;
	}

public List<IdList> getItwProjectidList() {
		
		return itwProjectidList;
	}

	public void setItwProjectidList(List<IdList> itwProjectidList) {
		this.itwProjectidList = itwProjectidList;
	}

public List<IdList> getItwStatusidList() {
		
		return itwStatusidList;
	}

	public void setItwStatusidList(List<IdList> itwStatusidList) {
		this.itwStatusidList = itwStatusidList;
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
	
	
	public String getCreatedbydisplay() {
		return createdbydisplay;
	}

	public void setCreatedbydisplay(String createdbydisplay) {
		this.createdbydisplay = createdbydisplay;
	}
	public String getModuleiddisplay() {
		return moduleiddisplay;
	}

	public void setModuleiddisplay(String moduleiddisplay) {
		this.moduleiddisplay = moduleiddisplay;
	}

	public String getDeadlinedisplay() {
		return deadlinedisplay;
	}

	public void setDeadlinedisplay(String deadlinedisplay) {
		this.deadlinedisplay = deadlinedisplay;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getCurrentloggedinuserid() {
		return currentloggedinuserid;
	}

	public void setCurrentloggedinuserid(String currentloggedinuserid) {
		this.currentloggedinuserid = currentloggedinuserid;
	}

	public String getType1display() {
		return type1display;
	}

	public void setType1display(String type1display) {
		this.type1display = type1display;
	}
	
	public ItwImage getItwImage() {
		return itwImage;
	}

	public void setItwImage(ItwImage itwImage) {
		this.itwImage = itwImage;
	}

	public String getPlatformiddisplay() {
		return platformiddisplay;
	}

	public void setPlatformiddisplay(String platformiddisplay) {
		this.platformiddisplay = platformiddisplay;
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

	public void setAssigneeiddisplay(String assigneeiddisplay) {
		this.assigneeiddisplay = assigneeiddisplay;
	}

	public String getAssigneeiddisplay() {
		return assigneeiddisplay;
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

	public String getStatusiddisplay() {
		return statusiddisplay;
	}

	public void setStatusiddisplay(String statusiddisplay) {
		this.statusiddisplay = statusiddisplay;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public String getProjectiddisplay() {
		return projectiddisplay;
	}

	public void setProjectiddisplay(String projectiddisplay) {
		this.projectiddisplay = projectiddisplay;
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

	public void setSeverityiddisplay(String severityiddisplay) {
		this.severityiddisplay = severityiddisplay;
	}

	public String getSeverityiddisplay() {
		return severityiddisplay;
	}

	public Integer getPriorityid() {
		return priorityid;
	}

	public void setPriorityiddisplay(String priorityiddisplay) {
		this.priorityiddisplay = priorityiddisplay;
	}

	public String getPriorityiddisplay() {
		return priorityiddisplay;
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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getLastupdatedbydisplay() {
		return lastupdatedbydisplay;
	}

	public void setLastupdatedbydisplay(String lastupdatedbydisplay) {
		this.lastupdatedbydisplay = lastupdatedbydisplay;
	}

	public String getLastUpdateDisplayTimeInGMT() {
		return lastUpdateDisplayTimeInGMT;
	}

	public void setLastUpdateDisplayTimeInGMT(String lastUpdateDisplayTimeInGMT) {
		this.lastUpdateDisplayTimeInGMT = lastUpdateDisplayTimeInGMT;
	}

	public String getReasonforOpen() {
		return reasonforOpen;
	}

	public void setReasonforOpen(String reasonforOpen) {
		this.reasonforOpen = reasonforOpen;
	}
	
	
}
