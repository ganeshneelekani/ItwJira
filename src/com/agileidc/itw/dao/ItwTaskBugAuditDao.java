package com.agileidc.itw.dao;



import java.util.List;

import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.model.ItwTaskBugAudit;

public interface ItwTaskBugAuditDao {
	
	public void addItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit);

	public List<ItwTaskBugAudit> listItwTaskBugAudits(int id);
	
	public ItwTaskBugAudit getItwTaskBugAudit(int id);
	
	public void deleteItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit);
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsAscend(int statusId);
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsFirstRecord(int statusId);
	
	
}
