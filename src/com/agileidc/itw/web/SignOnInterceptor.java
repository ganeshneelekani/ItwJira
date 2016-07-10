package com.agileidc.itw.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;


public class SignOnInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		System.out.println("signOn interceptor called");
		if (userSession == null) {
			System.out.println("no user session hence forward to login page");
			ModelAndView modelAndView = new ModelAndView("signOn");
			
			throw new ModelAndViewDefiningException(modelAndView);
		}
		else {
			System.out.println("User session is there continue");
			return true;
		}*/
		return true;
	}

}
