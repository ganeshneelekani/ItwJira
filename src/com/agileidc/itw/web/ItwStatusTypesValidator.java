package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwStatusTypesBean;
import com.agileidc.itw.bean.UserLoginBean;

public class ItwStatusTypesValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwStatusTypesBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Status.....");
		
		ItwStatusTypesBean user = (ItwStatusTypesBean) obj;
		
		if ((user.getShortname() == null) || (user.getShortname().length() == 0)
				|| (user.getShortname().equals(""))) {
			System.out.println("Status is entered null");
			e.rejectValue("shortname", "shortname.required",
					"Status cannot be empty");
		}
		if (user.getShortname().length() >50)
		 {
		System.out.println("Status Length cannot exceed 50 characters");
		e.rejectValue("shortname", "shortname.required",
				"Status Length cannot exceed 50 characters");
	}
		
		/*if((user.getPrecedingname()==null||user.getPrecedingname().equals("ROOT"))  ) {
   		 System.out.println("Precedingname is entered null");
            e.rejectValue("precedingname","precedingname.required", "Precedingname cannot be empty");
        }
		*/

		if((user.getStatusname()==null)  ) {
   		 System.out.println("statusname is entered null");
            e.rejectValue("statusname","statusname.required", "Chart Status is not configured");
        }
		
	}

	public void validatestatus(Object obj, Errors e) {

		System.out.println("entered validator written for Userlogin.....");
		
		ItwStatusTypesBean user = (ItwStatusTypesBean) obj;
		
	
		if((user.getPrecedingname()==null||user.getPrecedingname().equals("ROOT"))  ) {
   		 System.out.println("Precedingname is entered null");
            e.rejectValue("precedingname","precedingname.required", "Precedingname cannot be empty");
        }
		
	}
}