package com.agileidc.itw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.dao.ItwUserDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;
import com.agileidc.itw.model.ItwUserLogs;

@Service("itwUserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwUserServiceImpl implements ItwUserService {

	@Autowired
	private ItwUserDao itwUserDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwUser(ItwUser itwUser,ItwUserIcon itwUserIcon) throws HibernateException {
		if (itwUserIcon.getIcon() != null)
		{
			itwUserIconDao.addItwUserIcon(itwUserIcon);
			itwUserDao.addItwUser(itwUser);
		}
		else
		{
			itwUserDao.updateItwUser(itwUser);
		}
	
	}
	
	public void updateItwUser(ItwUser itwUser){
		itwUserDao.updateItwUserCount(itwUser);
	}
	
	public List<ItwUser> listItwUsers(Integer langId) {
		System.out.println("entered ItwUserService");
		return itwUserDao.listItwUsers(langId);
	}
	
	public List<ItwUser> getUsers(String uid,String pass){
		return itwUserDao.getUsers(uid,pass);
	}
	
	
	public void updateItwUserPass(String uid,String pass,int onetimepass){
		itwUserDao.updateItwUserPass(uid,pass,onetimepass);
	}

	public ItwUser getItwUser(int id) {
		return itwUserDao.getItwUser(id);
	}
	
	public void deleteItwUser(ItwUser itwUser) {
		itwUserDao.deleteItwUser(itwUser);
	}
	
	public List<ItwUser> getAllUsers(String loggedUserId){
		return itwUserDao.getAllUsers(loggedUserId);
	}

	public List<ItwUserLogs> getOnlineUsers(String loggedUserId){
		return itwUserDao.getOnlineUsers(loggedUserId);
	}
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId){
		return itwUserDao.getOfflineUsers(loggedUserId);
	}

	/**
	 * This is the method to be used to change users live status as user is
	 * logged in or not.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void changeMyOnlineStatus(String loggedUserId, int status){
		itwUserDao.changeMyOnlineStatus(loggedUserId, status);
	}
	
	@Override
	public List<ItwUser> getItwUserByShortName(String string) {
		return itwUserDao.getItwUserByShortName(string);
	}

}
