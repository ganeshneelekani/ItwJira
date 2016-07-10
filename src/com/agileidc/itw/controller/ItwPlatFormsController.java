package com.agileidc.itw.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwPlatFormsBean;
import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.service.ItwPlatFormsService;
import com.agileidc.itw.web.ItwPlatFormsValidator;

@Controller
public class ItwPlatFormsController {

	@Autowired
	private ItwPlatFormsService itwPlatFormsService;

	@RequestMapping(value = "/itwPlatFormsConfigList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {
		System.out.println("started platforms config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer)request.getSession().getAttribute("langId");
		List<ItwPlatFormsBean> itwPlatFormsBean=prepareListofItwPlatForms(itwPlatFormsService.listItwPlatForms(langId.intValue()));
		
		PagedListHolder pagedListHolder = new PagedListHolder(itwPlatFormsBean);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		System.out.println("value of page variable issssss" + page);
		pagedListHolder.setPage(page);
		int pageSize = 15;
		pagedListHolder.setPageSize(pageSize);
		model.put("pagedListHolder", pagedListHolder);
	
		return new ModelAndView("itwPlatFormsConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwPlatForms", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwPlatFormsBean itwPlatFormsBean,
			BindingResult result, HttpServletRequest request) {
		ItwPlatFormsValidator shortsValidator = new ItwPlatFormsValidator();
		shortsValidator.validate(itwPlatFormsBean, result);

		if (result.hasErrors()) {
			return new ModelAndView("addItwPlatForms");
		} else {
			System.out.println("Inside saveItwPlatForms");
			Integer langId = (Integer)request.getSession().getAttribute("langId");
			ItwPlatForms itwPlatForms = prepareModelItwPlatForms(itwPlatFormsBean, langId.intValue());
			try{
			itwPlatFormsService.addItwPlatForms(itwPlatForms);
			}
			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Platform  is already in use, enter a different Platform");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwPlatFormsBean itwPlatFormsBean1 = prepareItwPlatFormsBeanForAdd();
					model.remove("itwPlatForms1");
					model.put("itwPlatForms1", itwPlatFormsBean1);
					return new ModelAndView("addItwPlatForms", model);
				}
			}
			return new ModelAndView("redirect:/itwPlatFormsConfigList.html");
		}

	}

	private ItwPlatFormsBean prepareItwPlatFormsBeanForAdd() {
		ItwPlatFormsBean bean = new ItwPlatFormsBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwPlatForms", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwPlatFormsBean itwPlatFormsBean,
			BindingResult result, HttpServletRequest request) {
		ItwPlatFormsValidator shortsValidator = new ItwPlatFormsValidator();
		shortsValidator.validate(itwPlatFormsBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwPlatForms");
		} else {
			ItwPlatForms itwPlatFormstemp = itwPlatFormsService
					.getItwPlatForms(itwPlatFormsBean.getId());
			try {
				Integer langId = (Integer)request.getSession().getAttribute("langId");
				ItwPlatForms itwPlatForms = prepareModelItwPlatFormsforUpdate(itwPlatFormsBean, langId.intValue());
				itwPlatFormsService.addItwPlatForms(itwPlatForms);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Entered Platform is already in use, enter a different Platform");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwPlatFormsBean itwPlatFormsBean1 = prepareItwPlatFormsBeanForAdd();
					model.remove("itwPlatForms1");
					model.put("itwPlatForms1", itwPlatFormsBean1);
					return new ModelAndView("addItwPlatForms", model);
				}
			}

			return new ModelAndView("redirect:/itwPlatFormsConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwPlatForms", method = RequestMethod.GET)
	public ModelAndView addItwPlatForms(
			@ModelAttribute("command") ItwPlatFormsBean itwPlatFormsBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwPlatForms");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer)request.getSession().getAttribute("langId");
		model.put("itwPlatFormslist",
				prepareListofItwPlatForms(itwPlatFormsService
						.listItwPlatForms(langId.intValue())));
		return new ModelAndView("addItwPlatForms", model);

	}

	@RequestMapping(value = "/deleteItwPlatForms", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwPlatFormsBean itwPlatFormsBean,
			BindingResult result,HttpServletRequest request) {
		System.out.println("Inside delete------------");
		
		String shortNametemp = "PlatForms ID " + itwPlatFormsBean.getId() +" already in use, cannot delete";
		Integer langId = (Integer)request.getSession().getAttribute("langId");
		try{
		itwPlatFormsService
				.deleteItwPlatForms(prepareModelItwPlatForms(itwPlatFormsBean, langId.intValue()));
		
		}
		catch (RuntimeException runtimeException) {
			// TODO: handle exception
		
			System.out.println("entered runtime exception catch block 1");
			if (runtimeException.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				//Integer langId = (Integer)request.getSession().getAttribute("langId");
				List<ItwPlatFormsBean> itwPlatFormsBeanList = prepareListofItwPlatForms(itwPlatFormsService
						.listItwPlatForms(langId.intValue()));
				if (itwPlatFormsBean != null) {
					java.util.Collections.sort(itwPlatFormsBeanList, ItwPlatFormsBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(itwPlatFormsBeanList);
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					System.out.println("value of page variable issssss" + page);
					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}

				result.rejectValue("shortname", "shortname.inuse",shortNametemp);
				return new ModelAndView("itwPlatFormsConfigList", model);
		
		}
		}
	
		return new ModelAndView("redirect:/itwPlatFormsConfigList.html");
	}

	@RequestMapping(value = "/editItwPlatForms", method = RequestMethod.GET)
	public ModelAndView editItwPlatForms(
			@ModelAttribute("command") ItwPlatFormsBean itwPlatFormsBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwPlatForms", prepareItwPlatFormsBean(itwPlatFormsService
				.getItwPlatForms(itwPlatFormsBean.getId())));

		return new ModelAndView("editItwPlatForms", model);
	}

	private ItwPlatForms prepareModelItwPlatForms(
			ItwPlatFormsBean itwPlatFormsBean, int langId) {
		ItwPlatForms itwPlatForm = new ItwPlatForms();
		itwPlatForm.setShortName(itwPlatFormsBean.getShortname());

		itwPlatForm.setId(itwPlatFormsBean.getId());
		itwPlatForm.setLangid(langId);
		itwPlatFormsBean.setId(null);
		return itwPlatForm;
	}

	private ItwPlatForms prepareModelItwPlatFormsforUpdate(
			ItwPlatFormsBean itwPlatFormsBean, int langId) {
		ItwPlatForms itwPlatForm = new ItwPlatForms();

		itwPlatForm.setId(itwPlatFormsBean.getId());
		itwPlatForm.setShortName(itwPlatFormsBean.getShortname());
		itwPlatForm.setLangid(langId);
		itwPlatFormsBean.setId(null);

		return itwPlatForm;
	}

	private List<ItwPlatFormsBean> prepareListofItwPlatForms(
			List<ItwPlatForms> itwPlatFormss) {
		List<ItwPlatFormsBean> beans = null;
		if (itwPlatFormss != null && !itwPlatFormss.isEmpty()) {
			beans = new ArrayList<ItwPlatFormsBean>();
			ItwPlatFormsBean bean = null;
			for (ItwPlatForms itwPlatForms : itwPlatFormss) {
				bean = new ItwPlatFormsBean();
				bean.setId(itwPlatForms.getId());
				bean.setShortname(itwPlatForms.getShortName());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwPlatFormsBean prepareItwPlatFormsBean(ItwPlatForms itwPlatForms) {
		ItwPlatFormsBean bean = new ItwPlatFormsBean();
		bean.setShortname(itwPlatForms.getShortName());

		bean.setId(itwPlatForms.getId());
		return bean;
	}
}
