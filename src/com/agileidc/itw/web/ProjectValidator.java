package com.agileidc.itw.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.agileidc.itw.bean.ItwProjectBean;

public class ProjectValidator implements Validator {

	/**
	 * This Validator validates just Person instances
	 */
	public boolean supports(Class clazz) {
		return ItwProjectBean.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		System.out.println("entered validator written for Projectlogin.....");
		ItwProjectBean project = (ItwProjectBean) obj;
		/*
		 * if ((project.getClientid() == null)) {
		 * 
		 * e.rejectValue("clientid", "clientid.required",
		 * "ClientId cannot be empty"); }
		 */
		
		Date date = null;
		 DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
		    formatter.setLenient(false);
		   // Date startDate = new Date();
		   // Date endDate = new Date();
		    
		    String startDate = project.getStartdate();
			String endDate = project.getEnddate();
			System.out.println(startDate);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date startDate1 = null;
			java.util.Date endDate1 = null;
			try {
				startDate1 = sdf1.parse(startDate);
				endDate1 = sdf1.parse(endDate);
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
		 //   date = formatter.parse(inputDate);
		    
		   
	        
		    
		if ((project.getShortname().isEmpty())) {

			e.rejectValue("shortname", "shortname.required",
					"ProjectName cannot be empty");
		}

		
		if (project.getShortname().length() > 255) {
			System.out.println("Project Name Length should not exceed 255 character");
			e.rejectValue("shortname", "clientname.required",
					"Project Name Length should not exceed 255 character");
		}
		if (project.getStartdate().isEmpty()) {

				
			e.rejectValue("startdate", "startdate.required",
					"Start Date is required.");
		}
		
		if(!project.getStartdate().isEmpty()){
			
			System.out.println("------INSIDE START DATE-----------");
			 try {
				date = formatter.parse(project.getStartdate());
				 String s1[]=project.getStartdate().split("/");
				  for(int i=0;i<s1.length;i++)
				  {
					  if(Integer.parseInt(s1[0])>12||Integer.parseInt(s1[1])>31) { throw  new ParseException(project.getStartdate(), i);}
					 
				  }
				
			} catch (ParseException e1) {
				
				e.rejectValue("startdate", "startdate.required",
						"Start Date is not in the format MM/DD/YYYY.");
				
			}
		}

		if (project.getEnddate().isEmpty()) {
			System.out.println("------INSIDE END DATE-----------");
			e.rejectValue("enddate", "enddate.required",
					"End date cannot be before start date.");
		}
		if(!project.getEnddate().isEmpty()){
			 try {
				date = formatter.parse(project.getEnddate());
				 String s1[]=project.getStartdate().split("/");
				  for(int i=0;i<s1.length;i++)
				  {
					  if(Integer.parseInt(s1[0])>12||Integer.parseInt(s1[1])>31) { throw  new ParseException(project.getStartdate(), i);}
					 
				  }
			} catch (ParseException e1) {
				
				e.rejectValue("enddate", "enddate.required",
						"End Date is not in the format MM/DD/YYYY.");
				
			}
		}
		
		
		
		if (project.getModulename() == null) {

			e.rejectValue("modulename", "modulename.required",
					"Module Cannot be empty.");
		}

		if (project.getClientname() == null) {

			System.out.println("Client name is null------------");

			e.rejectValue("clientname", "clientname.required",
					"Client  Cannot be empty.");
		}

		if (project.getClientname().isEmpty()
				|| project.getClientname().length() == 0
				|| project.getClientname().equals("Select Clients")) {

			System.out.println("Client name is null------------");

			e.rejectValue("clientname", "clientname.required",
					"Client  Cannot be empty.");
		}
		
		  
		
		System.out.println("before date compare in validator");
		if(endDate1.compareTo(startDate1)<0){
			
			System.out.println("within date compare in validator");
			e.rejectValue("enddate", "enddate.required",
					"End Date should be greater than Start Date");
			
			System.out.println("after1 date compare in validator");
		}
		System.out.println("after2 date compare in validator");

	}
}