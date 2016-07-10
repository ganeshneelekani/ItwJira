
package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwTaskBugAudit;
import com.agileidc.itw.model.ItwTweets;

public interface ItwTaskBugAuditService {
	
	public void addItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit);

	public List<ItwTaskBugAudit> listItwTaskBugAudits(int id);
	
	
	public ItwTaskBugAudit getItwTaskBugAudit(int id);
	
	public void deleteItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit);
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsAscend(int statusId);
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsFirstRecord(int statusId);
}