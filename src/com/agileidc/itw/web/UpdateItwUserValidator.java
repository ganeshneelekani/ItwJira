
package com.agileidc.itw.web;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwUserBean;

public class UpdateItwUserValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwUserBean.class.equals(clazz);
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
		if ((user.getEmailid() == null) || (user.getEmailid().equals(""))
				|| (user.getEmailid().length() == 0)) {
			e.rejectValue("emailid", "emailid.required",
					"Emailid cannot be empty");
		}
		if ((user.getEmailid().length() > 100)) {
			e.rejectValue("emailid", "emailid.lengthexceeded",
					"Emailid cannot be more than 100 characters");
		}

		try {
InternetAddress internetAddress = new InternetAddress(
					user.getEmailid());

			internetAddress.validate();

		} catch (AddressException addrException) {

			e.rejectValue("emailid", "emailid.invalidformat",
					"Email Id is not in valid format");

		}
		

	}
}