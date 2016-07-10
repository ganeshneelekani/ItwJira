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


import com.agileidc.itw.bean.ItwBrowserTypesBean;
import com.agileidc.itw.bean.ItwRoleBean;
import com.agileidc.itw.bean.ItwBrowserTypesBean;
import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.model.ItwBrowserTypes;
import com.agileidc.itw.model.ItwBrowserTypes;
import com.agileidc.itw.service.ItwBrowserTypesService;
import com.agileidc.itw.web.ItwBrowserTypesValidator;
import com.agileidc.itw.web.ItwBrowserTypesValidator;

@Controller
public class ItwBrowserTypesController {

	@Autowired
	private ItwBrowserTypesService itwBrowserTypesService;

	@RequestMapping(value = "/itwBrowserTypesConfigList", method = RequestMethod.GET)
	public ModelAndView moduleConfigList(HttpServletRequest request) {
		System.out.println("started module config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<ItwBrowserTypesBean> itwBrowserTypesBean=prepareListofItwBrowserTypes(itwBrowserTypesService.listItwBrowserTypes());
		
		Collections.sort(itwBrowserTypesBean, ItwBrowserTypesBean.Comparators.ID);
		
		PagedListHolder pagedListHolder = new PagedListHolder(itwBrowserTypesBean);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		System.out.println("value of page variable issssss" + page);
		pagedListHolder.setPage(page);
		int pageSize = 15;
		pagedListHolder.setPageSize(pageSize);
		model.put("pagedListHolder", pagedListHolder);
	
		return new ModelAndView("itwBrowserTypesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwBrowserTypes", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwBrowserTypesBean itwBrowserTypesBean,
			BindingResult result) {
		ItwBrowserTypesValidator browsersValidator = new ItwBrowserTypesValidator();
		browsersValidator.validate(itwBrowserTypesBean, result);

		if (result.hasErrors()) {
			return new ModelAndView("addItwBrowserTypes");
		} else {
			System.out.println("Inside saveItwBrowserTypes");
			ItwBrowserTypes itwBrowserTypes = prepareModelItwBrowserTypes(itwBrowserTypesBean);
			try{
			itwBrowserTypesService.addItwBrowserTypes(itwBrowserTypes);
			}
			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("browsername", "browsername.unique",
							"Browser Type is already in use, enter a different Browser Type");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwBrowserTypesBean itwBrowserTypesBean1 = prepareItwBrowserTypesBeanForAdd();
					model.remove("itwBrowserTypes1");
					model.put("itwBrowserTypes1", itwBrowserTypesBean1);
					return new ModelAndView("addItwBrowserTypes", model);
				}
			}
			return new ModelAndView("redirect:/itwBrowserTypesConfigList.html");
		}

	}

	private ItwBrowserTypesBean prepareItwBrowserTypesBeanForAdd() {
		ItwBrowserTypesBean bean = new ItwBrowserTypesBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwBrowserTypes", method = RequestMethod.POST)
	public ModelAndView updateBrowserTypes(
			@ModelAttribute("command") ItwBrowserTypesBean itwBrowserTypesBean,
			BindingResult result) {
		ItwBrowserTypesValidator browsersValidator = new ItwBrowserTypesValidator();
		browsersValidator.validate(itwBrowserTypesBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwBrowserTypes");
		} else {
			ItwBrowserTypes itwBrowserTypestemp = itwBrowserTypesService
					.getItwBrowserTypes(itwBrowserTypesBean.getId());
			try {
				ItwBrowserTypes itwBrowserTypes = prepareModelItwBrowserTypesforUpdate(itwBrowserTypesBean);
				itwBrowserTypesService.addItwBrowserTypes(itwBrowserTypes);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("browsername", "browsername.unique",
							"BrowserType already in use, enter a different BrowserType");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwBrowserTypesBean itwBrowserTypesBean1 = prepareItwBrowserTypesBeanForAdd();
					model.remove("itwBrowserTypes1");
					model.put("itwBrowserTypes1", itwBrowserTypesBean1);
					return new ModelAndView("addItwBrowserTypes", model);
				}
			}

			return new ModelAndView("redirect:/itwBrowserTypesConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwBrowserTypes", method = RequestMethod.GET)
	public ModelAndView addItwBrowserTypes(
			@ModelAttribute("command") ItwBrowserTypesBean itwBrowserTypesBean,
			BindingResult result) {
		System.out.println("Inside addItwBrowserTypes");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwBrowserTypeslist",
				prepareListofItwBrowserTypes(itwBrowserTypesService
						.listItwBrowserTypes()));
		return new ModelAndView("addItwBrowserTypes", model);

	}

	@RequestMapping(value = "/deleteItwBrowserTypes", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwBrowserTypesBean itwBrowserTypesBean,
			BindingResult result,HttpServletRequest request) {
		System.out.println("Inside delete------------");
		
		String browserNametemp = "BrowserTypes ID " + itwBrowserTypesBean.getId() +" already in use, cannot delete";
		try{
		itwBrowserTypesService
				.deleteItwBrowserTypes(prepareModelItwBrowserTypes(itwBrowserTypesBean));
		
		}
		catch (RuntimeException runtimeException) {
			// TODO: handle exception
		
			System.out.println("entered runtime exception catch block 1");
			if (runtimeException.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				List<ItwBrowserTypesBean> itwBrowserTypesBeanList = prepareListofItwBrowserTypes(itwBrowserTypesService
						.listItwBrowserTypes());
				if (itwBrowserTypesBean != null) {
					java.util.Collections.sort(itwBrowserTypesBeanList, ItwBrowserTypesBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(itwBrowserTypesBeanList);
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					System.out.println("value of page variable issssss" + page);
					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}

				result.rejectValue("browsername", "browsername.inuse",browserNametemp);
				return new ModelAndView("itwBrowserTypesConfigList", model);
		
		}
		}
	
		return new ModelAndView("redirect:/itwBrowserTypesConfigList.html");
	}

	@RequestMapping(value = "/editItwBrowserTypes", method = RequestMethod.GET)
	public ModelAndView editItwBrowserTypes(
			@ModelAttribute("command") ItwBrowserTypesBean itwBrowserTypesBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwBrowserTypes", prepareItwBrowserTypesBean(itwBrowserTypesService
				.getItwBrowserTypes(itwBrowserTypesBean.getId())));

		return new ModelAndView("editItwBrowserTypes", model);
	}

	private ItwBrowserTypes prepareModelItwBrowserTypes(
			ItwBrowserTypesBean itwBrowserTypesBean) {
		ItwBrowserTypes itwBrowserTypes = new ItwBrowserTypes();
		itwBrowserTypes.setBrowsername(itwBrowserTypesBean.getBrowsername());

		itwBrowserTypes.setId(itwBrowserTypesBean.getId());
		itwBrowserTypesBean.setId(null);
		return itwBrowserTypes;
	}

	private ItwBrowserTypes prepareModelItwBrowserTypesforUpdate(
			ItwBrowserTypesBean itwBrowserTypesBean) {
		ItwBrowserTypes itwBrowserTypes = new ItwBrowserTypes();

		itwBrowserTypes.setId(itwBrowserTypesBean.getId());
		itwBrowserTypes.setBrowsername(itwBrowserTypesBean.getBrowsername());
		itwBrowserTypesBean.setId(null);

		return itwBrowserTypes;
	}

	private List<ItwBrowserTypesBean> prepareListofItwBrowserTypes(
			List<ItwBrowserTypes> itwBrowserTypess) {
		List<ItwBrowserTypesBean> beans = null;
		if (itwBrowserTypess != null && !itwBrowserTypess.isEmpty()) {
			beans = new ArrayList<ItwBrowserTypesBean>();
			ItwBrowserTypesBean bean = null;
			for (ItwBrowserTypes itwBrowserTypes : itwBrowserTypess) {
				bean = new ItwBrowserTypesBean();
				bean.setId(itwBrowserTypes.getId());
				bean.setBrowsername(itwBrowserTypes.getBrowsername());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwBrowserTypesBean prepareItwBrowserTypesBean(ItwBrowserTypes itwBrowserTypes) {
		ItwBrowserTypesBean bean = new ItwBrowserTypesBean();
		bean.setBrowsername(itwBrowserTypes.getBrowsername());

		bean.setId(itwBrowserTypes.getId());
		return bean;
	}
}
