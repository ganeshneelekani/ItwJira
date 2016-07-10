package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwBrowserTypesBean;

public class ItwBrowserTypesValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwBrowserTypesBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwBrowserTypesBean user = (ItwBrowserTypesBean) obj;
		
		if ((user.getBrowsername() == null) || (user.getBrowsername().length() == 0)
				|| (user.getBrowsername().equals(""))) {
			System.out.println("Short name is entered null");
			e.rejectValue("browsername", "browsername.required",
					"Browser name cannot be empty");
		}
		if (user.getBrowsername().length() >50)
		 {
		System.out.println("Short name Length should below 50 character");
		e.rejectValue("browsername", "browsername.required",
				"Browser name Length should below 50 character");
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