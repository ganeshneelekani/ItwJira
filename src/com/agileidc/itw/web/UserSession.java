package com.agileidc.itw.web;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.agileidc.itw.model.ItwUser;

/**
 * @author Rajeev
 * 
 */
public class UserSession implements HttpSessionBindingListener {

	private ItwUser itwUser;
	private static Map<String, HttpSession> usersSessions= new HashMap<String, HttpSession>();
	
	
	public static Map<String, HttpSession> getUsersSessions() {
		return usersSessions;
	}


	public UserSession(com.agileidc.itw.model.ItwUser tmp_itwUser) {
		this.itwUser = tmp_itwUser;
	}

	public ItwUser getItwUser() {
		return itwUser;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		usersSessions.put(this.itwUser.getUserid(), event.getSession());
	
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		usersSessions.remove(this.itwUser.getUserid());
	}

}
