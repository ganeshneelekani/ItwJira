package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserLogs;

public interface ItwUserDao {

	public void addItwUser(ItwUser itwUser) throws HibernateException;

	public void updateItwUser(ItwUser itwUser) throws HibernateException;

	
	public void  updateItwUserCount(ItwUser itwUser) throws HibernateException;
	
	
	public void updateItwUserPass(String uid,String pass,int onetimepass) throws HibernateException;
	public List<ItwUser> listItwUsers(Integer langId);

	public ItwUser getItwUser(int id);

	public List<ItwUser> getUsers(String uid,String pass);
	
	public void deleteItwUser(ItwUser itwUser);

	public List<ItwUser> getAllUsers(String loggedUserId);

	public List<ItwUserLogs> getOnlineUsers(String loggedUserId);
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId);

	/**
	 * This is the method to be used to change users live status as user is
	 * logged in or not.
	 */
	public void changeMyOnlineStatus(String loggedUserId, int status);
	public List<ItwUser> getItwUserByShortName(String string);
}
