package com.agileidc.itw.service;

import java.util.Date;
import java.util.List;

import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.model.ItwTweets;

public interface ItwTaskBugService {

	public void addItwTaskBug(ItwTaskBug itwTaskBug, ItwTweets itwTweets);

	public List<ItwTaskBug> listItwTaskBugs();

	public List<ItwTaskBug> listOpenItwTaskBugs();

	public ItwTaskBug getItwTaskBug(int id);

	// added for Search option
	public List<ItwTaskBug> listGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch, Integer projectId);

	
	
	public List<ItwTaskBug> allListGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch);
	
	
	public void deleteItwTaskBug(ItwTaskBug itwTaskBug);

	public Integer getItwTaskBugCount(int id);

	public Integer getItwTaskBugForDate(String date);

	public Integer getItwTaskClosedBugForDate(String date, int statusid);

	public Integer getItwTaskSeverityBugForDate(String date, String maxdate,
			int severityId);

	public Integer getItwTaskSeverityClosedBugForDate(String date,
			int severityId, int statusId);

	public List<ItwTaskBug> getItwTaskClosedBugForSeverity(int severityId,
			int statusId, String lastdate);

	public Integer getItwTaskBugSeverityCount(int severityId, String lastDate);

	public int getItwTaskMaxId(int userid);

	// Ganesh Added
	public List<ItwTaskBug> getItwTaskBugReleaseId(int id);

	public List<ItwTaskBug> listItwTaskBugsForProject(int projectcId);

	public List<ItwTaskBug> listOpenItwTaskBugsForProject(int projectId);
}