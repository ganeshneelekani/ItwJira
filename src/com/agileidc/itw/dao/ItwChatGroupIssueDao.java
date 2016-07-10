package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.agileidc.itw.model.ItwChatGroupIssue;
import com.agileidc.itw.model.ItwUserLogs;

public interface ItwChatGroupIssueDao {

	public void addItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue);

	public List<ItwChatGroupIssue> listItwChatGroupIssue();

	public ItwChatGroupIssue getItwChatGroupIssue(int id);

	public List<ItwChatGroupIssue> getItwChatGroupIssueBySessionId(String sessionId,
			Integer issueId);

	public void deleteItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue)
			throws HibernateException;
}