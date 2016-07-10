
package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUserLogs;

public interface ItwUserLogsService {
	
	public void addItwUserLogs (ItwUserLogs itwUserLogs) throws HibernateException;

	public List<ItwUserLogs> listItwUserLogs();
	
	public void updateItwUserLogs(ItwUserLogs itwUserLogs) throws HibernateException;
	
	public ItwUserLogs getItwUserLogs(int id);
	//public ItwUserLogs getItwOnlineUserLogs(int id);
	
	public void deleteItwUserLogs(ItwUserLogs itwUserLogs);
	public List<ItwUserLogs> getAllUserLogs(String loggedUserId);
	public List<ItwUserLogs> getAllOnlineUserLogs(String loggedUserId);

	public List<ItwOnlineUsers> getOnlineUsers(String loggedUserId);
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId);

	/**
	 * This is the method to be used to change users live status as user is
	 * logged in or not.
	 */
	public void changeMyOnlineStatus(String loggedUserId, int status);
}
