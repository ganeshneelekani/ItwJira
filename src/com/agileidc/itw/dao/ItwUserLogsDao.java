package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUserLogs;

public interface ItwUserLogsDao {

	public void addItwUserLogs(ItwUserLogs itwUserLogs) throws HibernateException;

	public void updateItwUserLogs(ItwUserLogs itwUserLogs) throws HibernateException;

	public List<ItwUserLogs> listItwUserLogs();

	public ItwUserLogs getItwUserLogs(int id);
	public List<ItwUserLogs> getItwUserLogsByUserId(String userId);
	//public List<ItwUserLogs> getItwUserLogsBySessionId(String sessionId) ;
	public void deleteItwUserLogs(ItwUserLogs itwUserLogs);

	public List<ItwUserLogs> getAllUserLogs(String loggedUserLogsId);
	public List<ItwUserLogs> getAllOnlineUserLogs(String loggedUserLogsId);

	public List<ItwOnlineUsers> getOnlineUsers(String loggedUserId);
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId);

	
	public void changeMyOnlineStatus(String loggedUserId, int status);
}
