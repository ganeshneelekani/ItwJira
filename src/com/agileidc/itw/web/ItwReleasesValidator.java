package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwReleasesBean;
import com.agileidc.itw.bean.UserLoginBean;

public class ItwReleasesValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwReleasesBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		ItwReleasesBean user = (ItwReleasesBean) obj;

		if ((user.getShortname() == null)
				|| (user.getShortname().length() == 0)
				|| (user.getShortname().equals(""))) {
			System.out.println("Release Name is entered null");
			e.rejectValue("shortname", "shortname.required",
					"Release name cannot be empty");
		}
		if (user.getShortname().length() > 100) {
			System.out
					.println("Release Name Length should not exceed 100 character");
			e.rejectValue("shortname", "shortname.required",
					"Release Name Length should not exceed 100 character");
		}
		if ((user.getReleasedescription() == null)
				|| (user.getReleasedescription().length() == 0)
				|| (user.getReleasedescription().equals(""))) {
			System.out.println("releasedescription Name is entered null");
			e.rejectValue("releasedescription", "releasedescription.required",
					"Releasedescription name cannot be empty");
		}
		
		if (user.getReleasedate().isEmpty()) {
			System.out.println("------INSIDE END DATE-----------");
			e.rejectValue("enddate", "enddate.required", "End date is required");
		}
		
		if (user.getReleasedate().isEmpty()) {
			System.out.println("------INSIDE END DATE-----------");
			e.rejectValue("enddate", "enddate.required", "End date is required");
		}

	}
}