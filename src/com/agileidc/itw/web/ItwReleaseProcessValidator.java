package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwReleaseProcessBean;

public class ItwReleaseProcessValidator implements Validator {

	public boolean supports(Class clazz) {
		return ItwReleaseProcessBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		
		System.out.println("======1============");
		ItwReleaseProcessBean ReleaseProcess = (ItwReleaseProcessBean) obj;
		if ( ReleaseProcess.getDescription().length() == 0) {
		
			System.out.println("======2============");
			e.rejectValue("description", "description.required",
					"description cannot be empty");
		}
		
		if (ReleaseProcess.getStep()==null) {
		
			System.out.println("======3============");
			e.rejectValue("step", "step.required",
					"step cannot be empty");

		}
		
	}


	
}