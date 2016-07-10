package com.agileidc.itw.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwUserBean;

public class ItwUserValidator implements Validator {
	
	
	
	private Pattern pattern;
	  private Matcher matcher;

	  private static final String PASSWORD_PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public boolean supports(Class clazz) {
		return ItwUserBean.class.equals(clazz);
	}
	
	public ItwUserValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	  }
	
	public boolean validate(final String password){
		 
		  matcher = pattern.matcher(password);
		  return matcher.matches();

	  }
	

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for ItwUserBean.....");

		ItwUserBean user = (ItwUserBean) obj;
		if ((user.getUserid() == null) || (user.getUserid().equals(""))
				|| (user.getUserid().length() == 0)) {
			e.rejectValue("userid", "userid.required", "Userid cannot be empty");
		}
		if ((user.getUserid() != null) && (user.getUserid().length() > 0)) {
			try {

				InternetAddress internetAddress = new InternetAddress(
						user.getUserid());

				internetAddress.validate();

			} catch (AddressException addrException) {

				e.rejectValue("userid", "userid.invalidformat",
						"User Id should be in email format");

			}
		}
		if ((user.getEmailid() == null) || (user.getEmailid().equals(""))
				|| (user.getEmailid().length() == 0)) {
			e.rejectValue("emailid", "emailid.required",
					"Email Id cannot be empty");
		}
		
		if ((user.getEmailid() != null) && (user.getEmailid().length() > 0)) {
			try {

				InternetAddress internetAddress = new InternetAddress(
						user.getEmailid());

				internetAddress.validate();

			} catch (AddressException addrException) {

				e.rejectValue("emailid", "emailid.invalidformat",
						"Email Id should be in email format");

			}
		}
		if ((user.getPassword() == null) || (user.getPassword().length() == 0)
				|| (user.getPassword().equals(""))) {

			e.rejectValue("password", "password.required",
					"Password cannot be empty");
		}
		
		System.out.println("User password========== "+user.getPassword());
		
		System.out.println("User password========== "+validate(user.getPassword()));
		if (!validate(user.getPassword()))
				 {
			
			System.out.println("Entered pattern ");

			e.rejectValue("password", "password.required",
					"Password "
					+"#  must contains one digit from 0-9"
  		+"#   must contains one lowercase characters"
  		+"#   must contains one uppercase characters"
  		+"#   must contains one special symbols in the list "+"@#$%" 
  				+"     		#     match anything with previous condition checking "
  				+"	#        length at least 6 characters and maximum of 20") ;
		}
		if (!(user.getPassword().equals(user.getConfirmpassword()))) {

			e.rejectValue("password", "password.nomatch",
					"Password and Confirm Password do not match");
		}
		
		String errorCode = "Password should not be empty or no spaces in password";
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", errorCode );

		if ((user.getFirstname() == null) || (user.getFirstname().equals(""))
				|| (user.getFirstname().length() == 0)) {
			e.rejectValue("firstname", "firstname.required",
					"Firstname cannot be empty");
		}
		if ((user.getFirstname().length() > 100)) {
			e.rejectValue("firstname", "firstname.lengthexceeded",
					"Firstname cannot be more than 100 characters");
		}
		if ((user.getLastname() == null) || (user.getLastname().equals(""))
				|| (user.getLastname().length() == 0)) {
			e.rejectValue("lastname", "lastname.required",
					"Lastname cannot be empty");
		}
		if ((user.getLastname().length() > 100)) {
			e.rejectValue("lastname", "lastname.lengthexceeded",
					"Lastname cannot be more than 100 characters");
		}
		
		if ((user.getEmailid().length() > 100)) {
			e.rejectValue("emailid", "emailid.lengthexceeded",
					"Email Id cannot be more than 100 characters");
		}

		try {
			InternetAddress internetAddress = new InternetAddress(
					user.getEmailid());

			internetAddress.validate();

		} catch (AddressException addrException) {

			e.rejectValue("emailid", "emailid.invalidformat",
					"Email Id is not in valid format");

		}
		if (user.getGenderid() == 0) {

			e.rejectValue("genderid", "gender.required",
					"Gender has to be selected");
		}
		if (user.getPrimaryroleid() == 0) {

			e.rejectValue("primaryroleid", "primaryroleid.required",
					"Role has to be selected");
		}
		if (user.getTypeid() == 0) {

			e.rejectValue("typeid", "typeid.required",
					"User Type has to be selected");
		}
		if (user.getCompanyid() == 0) {

			e.rejectValue("companyid", "companyid.required",
					"Company has to be selected");
		}
		
	}
}