package com.agileidc.itw.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwProjectModuleBean;

public class ProjectModuleValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwProjectModuleBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for ProjectModulelogin.....");
		ItwProjectModuleBean ProjectModule = (ItwProjectModuleBean) obj;
	
		
		
		/*    
		if ((ProjectModule.getRoleid()===null)) {

			e.rejectValue("roleid", "roleid.required",
					"RolesName required");
		}

		
		if (ProjectModule.getUserid() == null || ProjectModule.getUserid().equals("Select User")) {

			System.out.println("User ID is null------------");

			e.rejectValue("userid", "userid.required",
					"UserName cannot be empty.");
		}
		*/
		
	    
	/*	if ((ProjectModule.getRolename().equals("") ||ProjectModule.getRolename().length()==0)) {

			e.rejectValue("rolename", "rolename.required",
					"RolesName required");
		}

		
		if (ProjectModule.getUsername().equals("") || ProjectModule.getUserid().equals("Select User")) {

			System.out.println("UserName is null------------");

			e.rejectValue("username", "username.required",
					"UserName cannot be empty.");
		}
		*/
		if ((ProjectModule.getModulename()==null)) {

			e.rejectValue("modulename", "modulename.required",
					"Module Name required");
		}

		
		if (ProjectModule.getProjectname() == null || ProjectModule.getProjectname().equals("Select Project")) {

			System.out.println("User ID is null------------");

			e.rejectValue("projectname", "projectname.required",
					"Project cannot be empty.");
		}
	
	
	}
}