package com.agileidc.itw.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.bean.UserLoginBean;

public class ItwChangePasswordValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public boolean supports(Class clazz) {
		return ItwUserBean.class.equals(clazz);
	}

	public ItwChangePasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean validate(final String password) {

		matcher = pattern.matcher(password);
		return matcher.matches();

	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for ItwUserBean.....");

		UserLoginBean user = (UserLoginBean) obj;
		
		
		if (!validate(user.getNewpassword()))
				 {
			
			System.out.println("Entered pattern ");

			e.rejectValue("confirmpassword", "confirmpassword.required",
					"Password "
					+"#  must contains one digit from 0-9"
  		+"# must contains one lowercase characters"
  		+"# must contains one uppercase characters"
  		+"# must contains one special symbols in the list "+"@#$%" 
  				+" # match anything with previous condition checking "
  				+"	# length at least 6 characters and maximum of 20") ;
		}
		
		
	}
}