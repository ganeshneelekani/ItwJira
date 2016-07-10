package com.agileidc.itw.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwUserGroupBean;

public class UserGroupValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwUserGroupBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for UserGrouplogin.....");
		ItwUserGroupBean project = (ItwUserGroupBean) obj;
		/*
		 * if ((project.getClientid() == null)) {
		 * 
		 * e.rejectValue("clientid", "clientid.required",
		 * "ClientId cannot be empty"); }
		 */
	
			
		
		
		
		if ((project.getGroupname()  == null) || (project.getGroupname() .length() == 0)
				|| (project.getGroupname() .equals(""))) {
			e.rejectValue("groupname", "groupname.required",
					"Group name cannot be empty");
		}
		
		
		if (project.getGroupname().length() > 255) {
			System.out.println("UserGroup Name Length should not exceed 255 character");
			e.rejectValue("shortname", "clientname.required",
					"UserGroup Name Length should not exceed 255 character");
		}
		
		
		
		
		if (project.getUsernames()  == null) {

			e.rejectValue("usernames", "usernames.required",
					"User Names Cannot be empty.");
		}
	}
		
}