package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwChartStatusBean;

public class ItwChartStatusValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwChartStatusBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwChartStatusBean user = (ItwChartStatusBean) obj;
		
		if ((user.getShortname() == null) || (user.getShortname().length() == 0)
				|| (user.getShortname().equals(""))) {
			System.out.println("Chart Status  is entered null");
			e.rejectValue("shortname", "shortname.required",
					"Chart Status name cannot be empty");
		}
		if (user.getShortname().length() >40)
		 {
		System.out.println("Chart Status Length should not exceed 100 characters");
		e.rejectValue("shortname", "shortname.required",
				"Chart Status Length should not exceed 100 characters");
	}
		
	}
}