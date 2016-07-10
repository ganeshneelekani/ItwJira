package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwModuleBean;

public class ItwModuleValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwModuleBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwModuleBean user = (ItwModuleBean) obj;
		
		if ((user.getShortname() == null) || (user.getShortname().length() == 0)
				|| (user.getShortname().equals(""))) {
			System.out.println("Module Name is entered null");
			e.rejectValue("shortname", "shortname.required",
					"Module name cannot be empty");
		}
		if (user.getShortname().length() >50)
		 {
		
		e.rejectValue("shortname", "shortname.required",
				"Module Name Length should not exceed 50 character");
	}
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(e, "userid",
		 * "userid.required"); ValidationUtils.rejectIfEmptyOrWhitespace(e,
		 * "password", "password.required");
		 */
		/*
		 * UserLoginBean p = (UserLoginBean) obj; if (p.getAge() < 0) {
		 * e.rejectValue("age", "negativevalue"); } else if (p.getAge() > 110) {
		 * e.rejectValue("age", "too.darn.old"); }
		 */
	}
}