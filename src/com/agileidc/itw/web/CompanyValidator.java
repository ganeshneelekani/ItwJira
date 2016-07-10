package com.agileidc.itw.web;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwCompanyBean;

public class CompanyValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwCompanyBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Companylogin.....");
		
		ItwCompanyBean project = (ItwCompanyBean) obj;
		if ((project.getDescription().isEmpty())) {

			e.rejectValue("description", "description.required",
					"Description cannot be empty");
		}
		if ((project.getDescription().length() > 1000)) {

			e.rejectValue("description", "description.required",
					"Description length should be less than 1000 character");
		}
		if ((project.getName().isEmpty())) {

			e.rejectValue("name", "name.required", "Name cannot be empty");
		}
		if ((project.getName().length() > 100)) {

			e.rejectValue("name", "name.required",
					"Name length should be less than 100 character");
		}
		if ((project.getContact().isEmpty())) {

			e.rejectValue("contact", "contact.required",
					"Contact cannot be empty");
		}
		if ((project.getContact().length() > 50)) {

			e.rejectValue("contact", "contact.required",
					"Contact length should be less than 50 character");
		}
		if ((project.getPhone().isEmpty())) {

			e.rejectValue("phone", "phone.required", "Phone cannot be empty");
		}
		if ((project.getPhone().length() > 50)) {

			e.rejectValue("phone", "phone.required",
					"Phone length should be less than 50 character");
		}
		if ((project.getEmail().isEmpty())) {

			e.rejectValue("email", "email.required", "Email cannot be empty");
		}
		if ((project.getEmail().length() > 50)) {

			e.rejectValue("email", "email.required",
					"Email length should be less than 50 character");
		}
		if ((project.getWebsite().isEmpty())) {

			e.rejectValue("website", "website.required",
					"Website cannot be empty");
		}
		if ((project.getWebsite().length() > 50)) {

			e.rejectValue("website", "website.required",
					"Website length should be less than 50 character");
		}
		if ((project.getRegistrationNumber().isEmpty())) {

			e.rejectValue("registrationNumber", "registrationNumber.required",
					"RegistrationNumber cannot be empty");
		}
		if ((project.getWebsite().length() > 50)) {

			e.rejectValue("registrationNumber", "registrationNumber.required",
					"RegistrationNumber length should be less than 50 character");
		}
		
		
		

	}
}