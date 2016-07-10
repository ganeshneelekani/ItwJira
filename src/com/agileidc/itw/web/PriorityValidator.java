package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwPriorityBean;

public class PriorityValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwPriorityBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		ItwPriorityBean project = (ItwPriorityBean) obj;
		if ((project.getColorcode().isEmpty())) {

			e.rejectValue("colorcode", "colorcode.required",
					"colorcode cannot be empty");
		}
		if ((project.getShortname().length() > 50)) {

			e.rejectValue("shortname", "shortname.required",
					"Priority length should not exceed 50 characters");
		}
		if ((project.getShortname().isEmpty())) {

			e.rejectValue("shortname", "shortname.required", "Priority cannot be empty");
		}
		if ((project.getColorcode().length() > 7)) {

			e.rejectValue("colorcode", "colorcode.required",
					"colorcode length should not exceed 7 character");
		}
		
	
		
		
		

	}
}