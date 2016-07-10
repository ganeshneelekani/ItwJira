package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwChatGroupIssue;

public interface ItwChatGroupIssueService {
	
	public void addItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue);
	public void addSenderRecieverToItwChatGroup(ItwChatGroupIssue itwChatGroupIssue, String sender,String reciever);

	public List<ItwChatGroupIssue> listItwChatGroupIssue();
	
	public ItwChatGroupIssue getItwChatGroupIssue(int id);
	public List<ItwChatGroupIssue> getItwChatGroupIssueBySessionId(String sessionId, Integer issueId) ;
	
	public void deleteItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue) throws HibernateException;
}
