package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwPlatFormsBean;

public class ItwPlatFormsValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwPlatFormsBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwPlatFormsBean user = (ItwPlatFormsBean) obj;
		
		if ((user.getShortname() == null) || (user.getShortname().length() == 0)
				|| (user.getShortname().equals(""))) {
			System.out.println("PlatForm Name is entered null");
			e.rejectValue("shortname", "shortname.required",
					"PlatForm name cannot be empty");
		}
		if (user.getShortname().length() >100)
		 {
		System.out.println("PlatForm Name Length should not exceed 100 characters");
		e.rejectValue("shortname", "shortname.required",
				"PlatForm Name Length should not exceed 100 characters");
	}
		
	}
}