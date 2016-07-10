package com.agileidc.itw.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwReleaseDocumentBean;

public class ReleaseDocumentValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwReleaseDocumentBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out
				.println("entered validator written for ReleaseDocumentlogin.....");
		ItwReleaseDocumentBean ReleaseDocument = (ItwReleaseDocumentBean) obj;
		/*
		 * if ((ReleaseDocument.getClientid() == null)) {
		 * 
		 * e.rejectValue("clientid", "clientid.required",
		 * "ClientId cannot be empty"); }
		 */

		Date date = null;
		DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		formatter.setLenient(false);
		Date startDate = new Date();
		Date endDate = new Date();

		String releaseDate = ReleaseDocument.getReleaseDate();

		System.out.println(startDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;

		try {
			startDate1 = sdf1.parse(releaseDate);

		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		int day, month, year;
		int second, minute, hour;
		GregorianCalendar date1 = new GregorianCalendar();

		day = date1.get(Calendar.DAY_OF_MONTH);
		month = date1.get(Calendar.MONTH);
		year = date1.get(Calendar.YEAR);

		second = date1.get(Calendar.SECOND);
		minute = date1.get(Calendar.MINUTE);
		hour = date1.get(Calendar.HOUR);
		// date = formatter.parse(inputDate);

		if ((ReleaseDocument.getReleaseName().isEmpty())) {

			e.rejectValue("releaseName", "releaseName.required",
					"releaseName cannot be empty");
		}

		if ((ReleaseDocument.getTitle().isEmpty())) {

			e.rejectValue("objectImpacted", "objectImpacted.required",
					"objectImpacted cannot be empty");
		}

		if ((ReleaseDocument.getCreatedBy().isEmpty())) {

			
			System.out.println("=========1==================");
			e.rejectValue("createdBy", "createdBy.required",
					"createdBy cannot be empty");
		}
		if ((ReleaseDocument.getRevisedBy().isEmpty())) {

			System.out.println("=========2==================");
			e.rejectValue("revisedBy", "revisedBy.required",
					"revisedBy cannot be empty");
		}

		if ((ReleaseDocument.getBuisnessImpact().toString().isEmpty())) {

			System.out.println("=========3==================");
			e.rejectValue("buisnessImpact", "buisnessImpact.required",
					"buisnessImpact cannot be empty");
		}

		if ((ReleaseDocument.getSourceCodeVersion().isEmpty())) {

			System.out.println("=========4==================");
			e.rejectValue("sourceCodeVersion", "sourceCodeVersion.required",
					"sourceCodeVersion cannot be empty");
		}
		if ((ReleaseDocument.getIntroduction().isEmpty())) {

			System.out.println("=========5==================");
			e.rejectValue("introduction", "introduction.required",
					"Introduction cannot be empty");
		}
		if ((ReleaseDocument.getScope().isEmpty())) {

			System.out.println("=========6==================");
			e.rejectValue("scope", "scope.required", "scope cannot be empty");
		}
		if ((ReleaseDocument.getSystemRequirements().isEmpty())) {

			System.out.println("=========7==================");
			e.rejectValue("systemRequirements", "systemRequirements.required",
					"systemRequirements cannot be empty");
		}

		if ((ReleaseDocument.getEnvoirnmentEffected().isEmpty())) {

			System.out.println("=========8==================");
			e.rejectValue("envoirnmentEffected",
					"envoirnmentEffected.required",
					"envoirnmentEffected cannot be empty");
		}

		if ((ReleaseDocument.getOperatingsystemssupported().isEmpty())) {

			System.out.println("=========9==================");
			e.rejectValue("operatingsystemssupported",
					"operatingsystemssupported.required",
					"operatingsystemssupported cannot be empty");
		}

		if ((ReleaseDocument.getPrerequisites().isEmpty())) {

			System.out.println("=========10==================");
			e.rejectValue("prerequisites", "prerequisites.required",
					"prerequisites cannot be empty");
		}

		if ((ReleaseDocument.getKnownIssues().isEmpty())) {

			System.out.println("=========11==================");
			e.rejectValue("knownIssues", "knownIssues.required",
					"knownIssues cannot be empty");
		}

		if ((ReleaseDocument.getAssumptionDependencies().isEmpty())) {

			System.out.println("=========12==================");
			e.rejectValue("assumptionDependencies",
					"assumptionDependencies.required",
					"assumptionDependencies cannot be empty");
		}

		

		if ((ReleaseDocument.getSpecialInstructions().isEmpty())) {

			System.out.println("=========14==================");
			e.rejectValue("specialInstructions",
					"specialInstructions.required",
					"specialInstructions cannot be empty");
		}

		

		if (ReleaseDocument.getReleaseDate().isEmpty()
				|| ReleaseDocument.getReleaseDate() == "") {

			e.rejectValue("releaseDate", "releaseDate.required",
					"Start Date is required.");
		}

		if (ReleaseDocument.getReleaseName().length() > 255) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 255 character");
			e.rejectValue("releaseName", "releaseName.required",
					"releaseName Name Length should not exceed 255 character");
		}
		if (ReleaseDocument.getCreatedBy().length() > 30) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 30 character");
			e.rejectValue("createdBy", "createdBy.required",
					"CreatedBy Length should not exceed 30 character");
		}
		if (ReleaseDocument.getEnvoirnmentEffected().length() > 4000) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 30 character");
			e.rejectValue("envoirnmentEffected",
					"envoirnmentEffected.required",
					"EnvoirnmentEffected Name Length should not exceed 4000 character");
		}
		if (ReleaseDocument.getRevisedBy().length() > 30) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 30 character");
			e.rejectValue("revisedBy", "revisedBy.required",
					"RevisedBy Name Length should not exceed 30 character");
		}

		if (ReleaseDocument.getIntroduction().length() > 1000) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 1000 character");
			e.rejectValue("releaseName", "releaseName.required",
					"releaseName Name Length should not exceed 1000 character");
		}
		if (ReleaseDocument.getTitle().length() > 1000) {
			System.out
					.println("ReleaseDocument Name Length should not exceed 1000 character");
			e.rejectValue("title", "title.required",
					"Title should not exceed 1000 character");
		}
		if (ReleaseDocument.getScope().length() > 1000) {
			System.out
					.println("scope Name Length should not exceed 1000 character");
			e.rejectValue("scope", "scope.required",
					"scope Length should not exceed 1000 character");
		}
		if (ReleaseDocument.getSystemRequirements().length() > 1000) {
			System.out
					.println("systemRequirements  Length should not exceed 1000 character");
			e.rejectValue("systemRequirements", "systemRequirements.required",
					"systemRequirements Name Length should not exceed 1000 character");
		}

	}

}