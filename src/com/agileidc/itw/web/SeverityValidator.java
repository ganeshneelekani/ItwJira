package com.agileidc.itw.web;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwSeverityBean;

public class SeverityValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwSeverityBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		
		

		
		ItwSeverityBean project = (ItwSeverityBean) obj;

		if ((project.getShortname().length() > 10)) {
			e.rejectValue("shortname", "shortname.required",
					"Severity length should be less than 10 character");
			
		}
		
		if ((project.getShortname().isEmpty())) {
			e.rejectValue("shortname", "shortname.required", "Severity cannot be empty");
		}
		if((project.getSla()==null) || (project.getSla().toString().equals("null"))) {
			
			e.rejectValue("sla", "sla.required", "SLA cannot be empty");
			
		}
		
		
	
		

	}
}