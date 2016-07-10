package com.agileidc.itw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwModuleBean;
import com.agileidc.itw.service.ItwModuleService;

@Controller
public class PageHelpController {


	
	
	@RequestMapping(value = "/pageIssueListHelp", method = RequestMethod.GET)
	public ModelAndView addItwModule(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwModule");

		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("pageIssueListHelp", model);

	}

	
}
