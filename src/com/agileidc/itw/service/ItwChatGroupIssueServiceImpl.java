package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwChatGroupIssueDao;
import com.agileidc.itw.dao.ItwUserLogsDao;
import com.agileidc.itw.model.ItwChatGroupIssue;
import com.agileidc.itw.model.ItwUserLogs;

@Service("itwChatGroupIssueService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwChatGroupIssueServiceImpl implements ItwChatGroupIssueService {

	@Autowired
	private ItwChatGroupIssueDao itwChatGroupIssueDao;
	@Autowired
	private ItwUserLogsDao itwUserLogsDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue) {
		itwChatGroupIssueDao.addItwChatGroupIssue(itwChatGroupIssue);
	}

	public List<ItwChatGroupIssue> getItwChatGroupIssueBySessionId(String sessionId, Integer issueId) {
		return itwChatGroupIssueDao.getItwChatGroupIssueBySessionId(sessionId, issueId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addSenderRecieverToItwChatGroup(
			ItwChatGroupIssue itwChatGroupIssue, String sender, String reciever) {

		List<ItwChatGroupIssue> itwChatGroupIssueList = itwChatGroupIssueDao
				.getItwChatGroupIssueBySessionId(
						itwChatGroupIssue.getSessionId(),
						itwChatGroupIssue.getIssueId());

		itwChatGroupIssueDao.addItwChatGroupIssue(itwChatGroupIssue);

		List<ItwUserLogs> iItwUserLogsDaoList = itwUserLogsDao
				.getItwUserLogsByUserId(reciever);
		String sessiodIdOfReciever = null;
		if (iItwUserLogsDaoList != null) {
			for (ItwUserLogs itwUserLogs : iItwUserLogsDaoList) {
				sessiodIdOfReciever = itwUserLogs.getSession1();

			}
			ItwChatGroupIssue itwChatGroupIssue1 = new ItwChatGroupIssue();
			itwChatGroupIssue1.setSessionId(sessiodIdOfReciever);
			itwChatGroupIssue1.setUserId(sender);
			itwChatGroupIssue1.setIssueId(itwChatGroupIssue.getIssueId());
			itwChatGroupIssueDao.addItwChatGroupIssue(itwChatGroupIssue1);
		}
		String sender1 = null;
		if (itwChatGroupIssueList != null) {
			for (ItwChatGroupIssue itwChatGroupIssue2 : itwChatGroupIssueList) {
				sender1 = itwChatGroupIssue2.getUserId();
				ItwChatGroupIssue itwChatGroupIssue3 = new ItwChatGroupIssue();
				itwChatGroupIssue3.setSessionId(sessiodIdOfReciever);
				itwChatGroupIssue3.setUserId(sender1);
				itwChatGroupIssue3.setIssueId(itwChatGroupIssue.getIssueId());
				itwChatGroupIssueDao.addItwChatGroupIssue(itwChatGroupIssue3);

				List<ItwUserLogs> iItwUserLogsDaoList1 = itwUserLogsDao
						.getItwUserLogsByUserId(sender1);
				String sessiodIdOfSender = null;
				if (iItwUserLogsDaoList1 != null) {
					for (ItwUserLogs itwUserLogs : iItwUserLogsDaoList1) {
						sessiodIdOfSender = itwUserLogs.getSession1();

					}
					ItwChatGroupIssue itwChatGroupIssue4 = new ItwChatGroupIssue();
					itwChatGroupIssue4.setSessionId(sessiodIdOfSender);
					itwChatGroupIssue4.setUserId(reciever);
					itwChatGroupIssue4.setIssueId(itwChatGroupIssue
							.getIssueId());
					itwChatGroupIssueDao
							.addItwChatGroupIssue(itwChatGroupIssue4);
				}
			}
		}

	}

	public List<ItwChatGroupIssue> listItwChatGroupIssue() {
		return itwChatGroupIssueDao.listItwChatGroupIssue();

	}

	public ItwChatGroupIssue getItwChatGroupIssue(int id) {
		return itwChatGroupIssueDao.getItwChatGroupIssue(id);
	}

	public void deleteItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue)
			throws HibernateException {
		itwChatGroupIssueDao.deleteItwChatGroupIssue(itwChatGroupIssue);
	}

}
