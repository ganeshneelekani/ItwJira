package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.UserLoginBean;

public class LoginValidator implements Validator {

	public boolean supports(Class clazz) {
		return UserLoginBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");

		UserLoginBean user = (UserLoginBean) obj;
		if ((user.getUserid() == null) || (user.getUserid().equals(""))
				|| (user.getUserid().length() == 0)) {
			e.rejectValue("userid", "userid.required", "Email Id cannot be empty");
		}
		if ((user.getPassword() == null) || (user.getPassword().length() == 0)
				|| (user.getPassword().equals(""))) {

			e.rejectValue("password", "password.required",
					"Password cannot be empty");
		}

	}
}