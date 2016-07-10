package com.agileidc.itw.web;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.UserLoginBean;
import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUserLogs;
import com.agileidc.itw.service.ItwUserLogsService;
import com.agileidc.itw.service.ItwUserService;

public class ItwInvalidateSessionController implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();

		System.out.println("ITW web app is creating a new session ID="
				+ session.getId() + " MaxInactiveInterval="
				+ session.getMaxInactiveInterval());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) session
				.getAttribute("userSession");
		if (userSession != null) {
			String userid = userSession.getItwUser().getUserid();
			System.out
					.println("the userid session is being timed out before.......... "
							+ userid);
			changeOnlineStatus1(se, userid);
			// session has been invalidated and all session data
			// (except Id)is no longer available
			System.out.println(getTime() + " (session) Destroyed:ID="
					+ session.getId());
			
			ApplicationContext ctx = ApplicationContextProvider1
					.getApplicationContext();

			ItwUserLogsService itwUserLogsService = (ItwUserLogsService) ctx
					.getBean("itwUserLogsService");
			ItwUserLogs itwUserLogs = new ItwUserLogs();
			
			java.util.Date date= new java.util.Date();
			Timestamp logoutTime=new Timestamp(date.getTime());
			itwUserLogs.setLogouttime(logoutTime);
			itwUserLogs.setLastupdatedtime(logoutTime);
			itwUserLogs.setOnlinestatus("N");
			itwUserLogs.setUserid(userid);
            
			itwUserLogs.setLogoutreason("Session TimeOut");
         
			itwUserLogsService.updateItwUserLogs(itwUserLogs);
		}
	}

	private String getTime() {
		return new Date(System.currentTimeMillis()).toString();
	}

	public void changeOnlineStatus1(HttpSessionEvent sessionEvent, String userid) {

		HttpSession session = sessionEvent.getSession();

		/*
		 * ApplicationContext ctx = WebApplicationContextUtils.
		 * getWebApplicationContext(session.getServletContext());
		 */
		ApplicationContext ctx = ApplicationContextProvider1
				.getApplicationContext();
		ItwUserService itwUserService = (ItwUserService) ctx
				.getBean("itwUserService");
		itwUserService.changeMyOnlineStatus(userid, 0);
		System.out
				.println("the userid session is being timed out after.......... "
						+ userid);

		// return new ModelAndView("signOn");
	}
}
