package com.agileidc.itw.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwTaskBugDao;
import com.agileidc.itw.dao.ItwTweetsDao;
import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.model.ItwTweets;

@Service("itwTaskBugService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwTaskBugServiceImpl implements ItwTaskBugService {

	@Autowired
	private ItwTaskBugDao itwTaskBugDao;
	@Autowired
	private ItwTweetsDao itwTweetsDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwTaskBug(ItwTaskBug itwTaskBug, ItwTweets itwTweets) {
		if (itwTaskBug.getId() == null) {
			itwTaskBugDao.addItwTaskBug(itwTaskBug);
			itwTweetsDao.addItwTweets(itwTweets);
		} else {
			itwTaskBugDao.addItwTaskBug(itwTaskBug);
			itwTweetsDao.addItwTweetsWithIssueId(itwTweets, itwTaskBug.getId());
		}
	}

	public List<ItwTaskBug> getItwTaskBugReleaseId(int id) {
		return itwTaskBugDao.getItwTaskBugReleaseId(id);
	}

	public List<ItwTaskBug> listItwTaskBugs() {

		return itwTaskBugDao.listItwTaskBugs();
	}

	public List<ItwTaskBug> listOpenItwTaskBugs() {

		return itwTaskBugDao.listOpenItwTaskBugs();
	}

	public ItwTaskBug getItwTaskBug(int id) {
		return itwTaskBugDao.getItwTaskBug(id);
	}

	public List<ItwTaskBug> listGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch, Integer projectId) {
		return itwTaskBugDao.listGetItwTaskBug(SearchedValue, SeleactedSearch,
				projectId);
	}
	
	public List<ItwTaskBug> allListGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch) {
		return itwTaskBugDao.allListGetItwTaskBug(SearchedValue, SeleactedSearch);
	}

	public void deleteItwTaskBug(ItwTaskBug itwTaskBug) {
		itwTaskBugDao.deleteItwTaskBug(itwTaskBug);
	}

	public Integer getItwTaskBugCount(int id) {
		return itwTaskBugDao.getItwTaskBugCount(id);
	}

	public Integer getItwTaskBugForDate(String date) {

		return itwTaskBugDao.getItwTaskBugForDate(date);
	}

	public Integer getItwTaskClosedBugForDate(String date, int statusid) {

		return itwTaskBugDao.getItwTaskClosedBugForDate(date, statusid);
	}

	public Integer getItwTaskSeverityBugForDate(String date, String maxdate,
			int severityId) {

		return itwTaskBugDao.getItwTaskSeverityBugForDate(date, maxdate,
				severityId);
	}

	public Integer getItwTaskSeverityClosedBugForDate(String date,
			int severityId, int statusId) {

		return itwTaskBugDao.getItwTaskSeverityClosedBugForDate(date,
				severityId, statusId);

	}

	public List<ItwTaskBug> getItwTaskClosedBugForSeverity(int severityId,
			int statusId, String lastdate) {

		return itwTaskBugDao.getItwTaskClosedBugForSeverity(severityId,
				statusId, lastdate);
	}

	public Integer getItwTaskBugSeverityCount(int severityId, String lastDate) {
		return itwTaskBugDao.getItwTaskBugSeverityCount(severityId, lastDate);
	}

	public int getItwTaskMaxId(int assigneid) {

		return itwTaskBugDao.getItwTaskMaxId(assigneid);

	}

	public List<ItwTaskBug> listItwTaskBugsForProject(int projectcId) {

		return itwTaskBugDao.listItwTaskBugsForProject(projectcId);
	}

	public List<ItwTaskBug> listOpenItwTaskBugsForProject(int projectId) {

		return itwTaskBugDao.listOpenItwTaskBugsForProject(projectId);
	}

}
