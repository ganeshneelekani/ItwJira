package com.agileidc.itw.web;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwClientBean;

public class ItwClientValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwClientBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ItwClientBean client = (ItwClientBean) obj;
		if ((client.getClientname() == null)
				|| (client.getClientname().equals(""))
				|| (client.getClientname().length() == 0)) {
			e.rejectValue("clientname", "clientname.required",
					"Clientname cannot be empty");
		}

		if (client.getClientname().length() > 100) {
			System.out.println("Client Name Length should not exceed 100 characters");
			e.rejectValue("clientname", "clientname.required",
					"Client Name Length should not exceed 100 characters");
		}

	}

}