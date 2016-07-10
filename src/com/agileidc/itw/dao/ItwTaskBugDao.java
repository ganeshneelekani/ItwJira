package com.agileidc.itw.dao;



import java.util.Date;
import java.util.List;

import com.agileidc.itw.model.ItwTaskBug;

public interface ItwTaskBugDao {
	
	public void addItwTaskBug(ItwTaskBug itwTaskBug);
	public List<ItwTaskBug> getItwTaskBugReleaseId(int id);
	public List<ItwTaskBug> listItwTaskBugs();
	public List<ItwTaskBug> listOpenItwTaskBugs();
	public ItwTaskBug getItwTaskBug(int id);
	
	public List<ItwTaskBug> listGetItwTaskBug(Integer SearchedValue,String SeleactedSearch,Integer projectId);
	
	public List<ItwTaskBug> allListGetItwTaskBug(Integer SearchedValue,String SeleactedSearch);
	
	public List<ItwTaskBug> listOpenItwTaskBugsForProject(int projectId);
	
	public Integer getItwTaskBugCount(int id);
	
	public void deleteItwTaskBug(ItwTaskBug itwTaskBug);
	
	public Integer getItwTaskBugForDate(String date);
	
	public Integer getItwTaskClosedBugForDate(String date,int statusid);
	
	public Integer getItwTaskSeverityBugForDate(String date,String maxdate,int severityId);
	
	public Integer getItwTaskSeverityClosedBugForDate(String date,int severityId,int statusId);
	
	public List<ItwTaskBug> getItwTaskClosedBugForSeverity(int severityId,int statusId,String lastdate);
	
	public Integer getItwTaskBugSeverityCount(int severityId,String lastDate);
	
	public int getItwTaskMaxId(int assigneeid);
	
	public List<ItwTaskBug> listItwTaskBugsForProject(int projectcId);
}
