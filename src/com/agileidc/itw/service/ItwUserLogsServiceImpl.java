package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwUserLogsDao;
import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUserLogs;

@Service("itwUserLogsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwUserLogsServiceImpl implements ItwUserLogsService {

	@Autowired
	private ItwUserLogsDao itwUserLogsDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwUserLogs(ItwUserLogs itwUserLogs)
			throws HibernateException {

		itwUserLogsDao.addItwUserLogs(itwUserLogs);

	}

	public List<ItwUserLogs> listItwUserLogs() {
		System.out.println("entered ItwUserService");
		return itwUserLogsDao.listItwUserLogs();
	}

	public ItwUserLogs getItwUserLogs(int id) {
		return itwUserLogsDao.getItwUserLogs(id);
	}

	public void deleteItwUserLogs(ItwUserLogs itwUserLogs) {
		itwUserLogsDao.deleteItwUserLogs(itwUserLogs);
	}

	public List<ItwUserLogs> getAllUserLogs(String loggedUserId) {
		return itwUserLogsDao.getAllUserLogs(loggedUserId);
	}
	public List<ItwUserLogs> getAllOnlineUserLogs(String loggedUserId) {
		return itwUserLogsDao.getAllOnlineUserLogs(loggedUserId);
	}

	public List<ItwOnlineUsers> getOnlineUsers(String loggedUserId) {
		return itwUserLogsDao.getOnlineUsers(loggedUserId);
	}

	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId) {
		return itwUserLogsDao.getOfflineUsers(loggedUserId);
	}

	/**
	 * This is the method to be used to change users live status as user is
	 * logged in or not.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void changeMyOnlineStatus(String loggedUserId, int status) {
		itwUserLogsDao.changeMyOnlineStatus(loggedUserId, status);
	}

	
	public void updateItwUserLogs(ItwUserLogs itwUserLogs)
			throws HibernateException {
		itwUserLogsDao.updateItwUserLogs(itwUserLogs);
		
	}
	
	
	

}
