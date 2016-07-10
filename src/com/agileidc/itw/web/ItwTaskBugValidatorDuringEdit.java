package com.agileidc.itw.web;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwTaskBugBean;
import com.agileidc.itw.helper.DateValidator;

;
public class ItwTaskBugValidatorDuringEdit implements Validator {

	public boolean supports(Class clazz) {
		return ItwTaskBugBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for ItwTaskBugBean.....");

		ItwTaskBugBean itwTaskBugBean = (ItwTaskBugBean) obj;
		if ((itwTaskBugBean.getShortname() == null)
				|| (itwTaskBugBean.getShortname().equals(""))
				|| (itwTaskBugBean.getShortname().length() == 0)) {
			e.rejectValue("shortname", "shortname.required",
					"Shortname cannot be empty");
		}
		if (itwTaskBugBean.getType1().equals("0")) {

			e.rejectValue("type1", "type1display.required",
					"Type has to be selected");
		}
		if ((itwTaskBugBean.getEfforts() == null)
				|| (itwTaskBugBean.getEfforts().equals(""))
				|| (itwTaskBugBean.getEfforts().length() == 0)) {

			e.rejectValue("efforts", "efforts.required",
					"Efforts Estimated cannot be empty");
		}
		if (itwTaskBugBean.getSeverityid() == 0) {
			e.rejectValue("severityid", "severityiddisplay.required",
					"Severity has to be selected");
		}
		if (itwTaskBugBean.getPriorityid() == 0) {
			e.rejectValue("priorityid", "priorityiddisplay.required",
					"Priority has to be selected");
		}
		java.sql.Date dte = null;
		
		
			
			
		try {
			String str = itwTaskBugBean.getDeadlinedisplay();
			System.out.println("input date format is " + str);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dt = formatter.parse(str);
			dte = new java.sql.Date(dt.getTime());
			DateValidator dateValidator = new DateValidator();
			if (!dateValidator.isThisDateValid(
					itwTaskBugBean.getDeadlinedisplay(), "MM/dd/yyyy")) {
				System.out.println("entered date 11");
				e.rejectValue("deadlinedisplay", "deadlinedisplay.invalid",
						"Dead Line date format or value is invalid");
			} else {
				int tempindex1 = itwTaskBugBean.getDeadlinedisplay()
						.lastIndexOf("/") + 1;
				String tempstr1 = itwTaskBugBean.getDeadlinedisplay()
						.substring(tempindex1);
				System.out.println("partial date string is tempstr1 "
						+ tempstr1);
				if ((tempstr1.length() != 2) && (tempstr1.length() != 4)) {
					System.out.println("entered date 22");

					e.rejectValue("deadlinedisplay", "deadlinedisplay.invalid",
							"Dead Line date format or value is invalid");
				}
				if (tempstr1.length() == 2) {
					System.out.println("entered date 33");
					String tempstr2 = itwTaskBugBean.getDeadlinedisplay()
							.substring(0, tempindex1);
					System.out.println("partial date string is tempstr2 "
							+ tempstr2);
					String tempstr3 = tempstr2 + "20" + tempstr1;
					System.out.println("partial date string is tempstr3 "
							+ tempstr3);
					itwTaskBugBean.setDeadlinedisplay(tempstr3);
				}

			}
			System.out.println("date entered is      input date " + dte);
		}

		catch (Exception ex) {
			System.out.println("caught invalid date exception2");
			System.out.println("entered date 44");
			e.rejectValue("deadlinedisplay", "deadlinedisplay.invalid",
					"Dead Line date format or value is invalid");
		}
		

		if (itwTaskBugBean.getPlatformid() == 0) {
			e.rejectValue("platformid", "platformiddisplay.required",
					"Platform has to be selected");
		}
		/*if (itwTaskBugBean.getStatusid() == 0) {
			e.rejectValue("statusid", "statusiddisplay.required",
					"Status has to be selected");
		}*/
		if (itwTaskBugBean.getProjectid() != null) {
			if (itwTaskBugBean.getProjectid() == 0) {
				e.rejectValue("projectid", "projectiddisplay.required",
						"Project has to be selected");
			}
		}
		
		if ((itwTaskBugBean.getSummary() == null)
				|| (itwTaskBugBean.getSummary().equals(""))
				|| (itwTaskBugBean.getSummary().length() == 0)) {
			e.rejectValue("summary", "summary.required",
					"Summary cannot be empty");
		}

	}
	
	public void validategetReasonforOpen(Object obj, Errors e) {
	ItwTaskBugBean itwTaskBugBean = (ItwTaskBugBean) obj;
	if ((itwTaskBugBean.getReasonforOpen() == null)
			|| (itwTaskBugBean.getReasonforOpen().equals(""))
			|| (itwTaskBugBean.getReasonforOpen().length() == 0)) {
		e.rejectValue("reasonforOpen", "reasonforOpen.required",
				"Reason for Open cannot be empty");
	}
	}
	
	public void validateforSearch(Object obj, Errors e) {
	
		e.rejectValue("reasonforOpen", "reasonforOpen.required",
				"Issue Id should be number");
		
		
		}
}