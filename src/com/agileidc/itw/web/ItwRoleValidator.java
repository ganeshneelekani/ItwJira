package com.agileidc.itw.web;


import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwRoleBean;

public class ItwRoleValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwRoleBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ItwRoleBean role = (ItwRoleBean) obj;
		if ((role.getRolename() == null) || (role.getRolename().equals(""))
				|| (role.getRolename().length() == 0)) {
			e.rejectValue("rolename", "rolename.required",
					"Rolename cannot be empty");
		}
		
		if ((role.getRolename().length()>50)) {
			e.rejectValue("rolename", "rolename.required",
					"Rolename  Length should not exceed 100 characters");
		}

	}
}