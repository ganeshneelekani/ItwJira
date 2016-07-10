package com.agileidc.itw.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwMessageDao;
import com.agileidc.itw.dao.ItwModuleDao;
import com.agileidc.itw.model.ItwChatGroupIssue;
import com.agileidc.itw.model.ItwMessage;
import com.agileidc.itw.model.ItwModule;

@Service("itwMessageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwMessageServiceImpl implements ItwMessageService {

	@Autowired
	private ItwMessageDao itwMessageDao;
	@Autowired
	private ItwChatGroupIssueService itwChatGroupIssueService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void sendMessageToThisUser(String receiverId, String message,
			String senderId, Integer issueId) {
		itwMessageDao.sendMessageToThisUser(receiverId, message, senderId,
				issueId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void sendMessageToAllUsers(String senderSessionId, String message,
			String senderId, Integer issueId) {

		List<ItwChatGroupIssue> itwChatGroupIssueList = itwChatGroupIssueService
				.getItwChatGroupIssueBySessionId(senderSessionId, 
						issueId);
		for (ItwChatGroupIssue itwChatGroupIssue : itwChatGroupIssueList) {
			System.out.println("sending message to user " + itwChatGroupIssue.getUserId() + " from user " +senderId + " message is " + message );
			itwMessageDao.sendMessageToThisUserOnly(itwChatGroupIssue.getUserId(), message, senderId, issueId);

		}
		itwMessageDao.sendMessageToThisUserOnly(senderId, message, senderId,
				issueId);
		System.out.println("sending message to user " + senderId + " from user " +senderId + " message is " + message );
		

	}

	/**
	 * This is the method to be used to get the messages of a logged user by
	 * passing user's userid as loggedUserid .
	 */

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<ItwMessage> getMyLatestMessages(String loggedUserId,
			Integer issueId) {
		return itwMessageDao.getMyLatestMessages(loggedUserId, issueId);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<ItwMessage> getAllMessagesForId(String loggedUserId,
			Integer issueId) {
		return itwMessageDao.getAllMessagesForId(loggedUserId, issueId);
	}

	/**
	 * This is the method to be used to get the previous messages of a logged
	 * user by passing user's userid as loggedUserid .
	 */
	public List<ItwMessage> getMyPrevMessages(String loggedUserId, String minVal) {
		return itwMessageDao.getMyPrevMessages(loggedUserId, minVal);
	}

	/**
	 * This is the method to be used to get the next messages of a logged user
	 * by passing user's userid as loggedUserid .
	 */
	public List<ItwMessage> getMyNextMessages(String loggedUserId, String minVal) {
		return itwMessageDao.getMyNextMessages(loggedUserId, minVal);
	}

}
