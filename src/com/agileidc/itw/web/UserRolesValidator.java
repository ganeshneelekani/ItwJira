package com.agileidc.itw.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwUserRolesBean;

public class UserRolesValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwUserRolesBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for UserRoleslogin.....");
		ItwUserRolesBean UserRoles = (ItwUserRolesBean) obj;
	
		
		
		/*    
		if ((UserRoles.getRoleid()===null)) {

			e.rejectValue("roleid", "roleid.required",
					"RolesName required");
		}

		
		if (UserRoles.getUserid() == null || UserRoles.getUserid().equals("Select User")) {

			System.out.println("User ID is null------------");

			e.rejectValue("userid", "userid.required",
					"UserName cannot be empty.");
		}
		*/
		
	    
	/*	if ((UserRoles.getRolename().equals("") ||UserRoles.getRolename().length()==0)) {

			e.rejectValue("rolename", "rolename.required",
					"RolesName required");
		}

		
		if (UserRoles.getUsername().equals("") || UserRoles.getUserid().equals("Select User")) {

			System.out.println("UserName is null------------");

			e.rejectValue("username", "username.required",
					"UserName cannot be empty.");
		}
		*/
		if ((UserRoles.getRolename()==null)) {

			e.rejectValue("rolename", "rolename.required",
					"RolesName required");
		}

		
		if (UserRoles.getUsername() == null || UserRoles.getUsername().equals("Select User")) {

			System.out.println("User ID is null------------");

			e.rejectValue("username", "username.required",
					"UserName cannot be empty.");
		}
	
	
	}
}