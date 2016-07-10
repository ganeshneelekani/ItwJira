package com.agileidc.itw.web;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwDocumentsBean;

public class ItwDocumentValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwDocumentsBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwDocumentsBean document = (ItwDocumentsBean) obj;
		
		if ((document.getDescription() == null) || (document.getDescription().length() == 0)
				|| (document.getDescription().equals(""))) {
			System.out.println("Description is entered null");
			e.rejectValue("description", "description.required",
					"Description cannot be empty");
		}
		if (document.getDescription().length() > 200)
		 {
		
			e.rejectValue("description", "descriptio.length",
					"Description text cannot be greater than 200 characters");
	}
		
	}
}