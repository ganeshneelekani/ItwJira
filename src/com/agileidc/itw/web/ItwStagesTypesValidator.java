package com.agileidc.itw.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwStagesTypesBean;

public class ItwStagesTypesValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwStagesTypesBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for StagesTypes.....");
		/*
		 * ValidationUtils.rejectIfEmpty(e, "userid", "userid.empty");
		 * ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		 */
		ItwStagesTypesBean stage = (ItwStagesTypesBean) obj;

		if ((stage.getShortname().isEmpty())) {
			System.out.println("Stage name is entered null");
			e.rejectValue("shortname", "shortname.required",
					"Stage Name cannot be empty");
		}
		if (stage.getShortname().length() > 30) {
			System.out.println("Stage Name Length should below 30 character");
			e.rejectValue("shortname", "shortname.required",
					"Stage Name Length should below 30 character");
		}

		/*
		 * if((stage.getPrecedingname().isEmpty()) ) {
		 * System.out.println("Precedingname is entered null");
		 * e.rejectValue("precedingname","precedingname.required",
		 * "Precedingname cannot be empty"); }
		 */
		/*if ((stage.getPrecedingname() == null)
				|| stage.getPrecedingname().equals("ROOT")) {
			System.out.println("Precedingname is entered null");
			e.rejectValue("precedingname", "precedingname.required",
					"Precedingname cannot be empty");
		}*/
	}
	
	public void validateStage(Object obj, Errors e) {
		ItwStagesTypesBean stage = (ItwStagesTypesBean) obj;
	
		if ((stage.getPrecedingname() == null)
				|| stage.getPrecedingname().equals("ROOT")) {
			System.out.println("Precedingname is entered null");
			e.rejectValue("precedingname", "precedingname.required",
					"Precedingname cannot be empty");
		}
	}

}