
package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwTaskBugAuditDao;
import com.agileidc.itw.model.ItwTaskBugAudit;

@Service("itwTaskBugAuditService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwTaskBugAuditServiceImpl implements ItwTaskBugAuditService {

	@Autowired
	private ItwTaskBugAuditDao itwTaskBugAuditDao;
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit) {
		itwTaskBugAuditDao.addItwTaskBugAudit(itwTaskBugAudit);
		
	}
	
	public List<ItwTaskBugAudit> listItwTaskBugAudits(int id) {
		
		return itwTaskBugAuditDao.listItwTaskBugAudits(id);
	}
	
	public ItwTaskBugAudit getItwTaskBugAudit(int id) {
		return itwTaskBugAuditDao.getItwTaskBugAudit(id);
	}
	
	public void deleteItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit) {
		itwTaskBugAuditDao.deleteItwTaskBugAudit(itwTaskBugAudit);
	}
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsAscend(int statusId){
		
		return itwTaskBugAuditDao.listItwTaskBugAuditsAscend(statusId);
		
	}
	
	public List<ItwTaskBugAudit> listItwTaskBugAuditsFirstRecord(int statusId){
		
		return itwTaskBugAuditDao.listItwTaskBugAuditsFirstRecord(statusId);
		
	}

}
