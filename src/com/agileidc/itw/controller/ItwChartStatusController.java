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

import com.agileidc.itw.bean.ItwChartStatusBean;
import com.agileidc.itw.model.ItwChartStatus;
import com.agileidc.itw.service.ItwChartStatusService;
import com.agileidc.itw.web.ItwChartStatusValidator;

@Controller
public class ItwChartStatusController {

	@Autowired
	private ItwChartStatusService itwChartStatusService;

	@RequestMapping(value = "/itwChartStatusConfigList", method = RequestMethod.GET)
	public ModelAndView ChartStatusConfigList(HttpServletRequest request) {
		System.out.println("started ChartStatus config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwChartStatusBean> itwChartStatusBean = prepareListofItwChartStatus(itwChartStatusService
				.listItwChartStatus(langId.intValue()));
		if (itwChartStatusBean != null) {
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwChartStatusBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		}

		else {

			System.out.println("Page holder is null------------------");
			model.put("pagedListHolder", null);

		}
		return new ModelAndView("itwChartStatusConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwChartStatus", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwChartStatusBean itwChartStatusBean,
			BindingResult result, HttpServletRequest request) {
		ItwChartStatusValidator shortsValidator = new ItwChartStatusValidator();
		shortsValidator.validate(itwChartStatusBean, result);

		if (result.hasErrors()) {
			return new ModelAndView("addItwChartStatus");
		} else {
			System.out.println("Inside saveItwChartStatus");
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			ItwChartStatus itwChartStatus = prepareModelItwChartStatus(
					itwChartStatusBean, langId.intValue());
			try {
				itwChartStatusService.addItwChartStatus(itwChartStatus);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Chart Status  is already in use, enter a different Chart Status");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwChartStatusBean itwChartStatusBean1 = prepareItwChartStatusBeanForAdd();
					model.remove("itwChartStatus1");
					model.put("itwChartStatus1", itwChartStatusBean1);
					return new ModelAndView("addItwChartStatus", model);
				}
			}
			return new ModelAndView("redirect:/itwChartStatusConfigList.html");
		}

	}

	private ItwChartStatusBean prepareItwChartStatusBeanForAdd() {
		ItwChartStatusBean bean = new ItwChartStatusBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwChartStatus", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwChartStatusBean itwChartStatusBean,
			BindingResult result, HttpServletRequest request) {
		ItwChartStatusValidator shortsValidator = new ItwChartStatusValidator();
		shortsValidator.validate(itwChartStatusBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwChartStatus");
		} else {
			ItwChartStatus itwChartStatustemp = itwChartStatusService
					.getItwChartStatus(itwChartStatusBean.getId());
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwChartStatus itwChartStatus = prepareModelItwChartStatusforUpdate(
						itwChartStatusBean, langId.intValue());
				itwChartStatusService.addItwChartStatus(itwChartStatus);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Entered Chart Status is already in use, enter a different Chart Status");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwChartStatusBean itwChartStatusBean1 = prepareItwChartStatusBeanForAdd();
					model.remove("itwChartStatus1");
					model.put("itwChartStatus1", itwChartStatusBean1);
					return new ModelAndView("addItwChartStatus", model);
				}
			}

			return new ModelAndView("redirect:/itwChartStatusConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwChartStatus", method = RequestMethod.GET)
	public ModelAndView addItwChartStatus(
			@ModelAttribute("command") ItwChartStatusBean itwChartStatusBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwChartStatus");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		model.put("itwChartStatuslist",
				prepareListofItwChartStatus(itwChartStatusService
						.listItwChartStatus(langId.intValue())));
		return new ModelAndView("addItwChartStatus", model);

	}

	@RequestMapping(value = "/deleteItwChartStatus", method = RequestMethod.GET)
	public ModelAndView deleteItwChartStatus(
			@ModelAttribute("command") ItwChartStatusBean itwChartStatusBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside delete------------");

		String shortNametemp = "ChartStatus ID " + itwChartStatusBean.getId()
				+ " already in use, cannot delete";
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		try {
			itwChartStatusService
					.deleteItwChartStatus(itwChartStatusBean.getId());

		} catch (RuntimeException runtimeException) {
			// TODO: handle exception

			System.out.println("entered runtime exception catch block 1");
			// if (runtimeException.getCause() instanceof
			// java.sql.SQLIntegrityConstraintViolationException) {
			System.out.println("entered runtime exception catch block 2"
					+ runtimeException.toString());
			Map<String, Object> model = new HashMap<String, Object>();
			// Integer langId =
			// (Integer)request.getSession().getAttribute("langId");
			List<ItwChartStatusBean> itwChartStatusBeanList = prepareListofItwChartStatus(itwChartStatusService
					.listItwChartStatus(langId.intValue()));
			if (itwChartStatusBean != null) {
				java.util.Collections.sort(itwChartStatusBeanList,
						ItwChartStatusBean.Comparators.ID);

				PagedListHolder pagedListHolder = new PagedListHolder(
						itwChartStatusBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				System.out.println("value of page variable issssss" + page);
				pagedListHolder.setPage(page);
				int pageSize = 5;
				pagedListHolder.setPageSize(pageSize);
				model.put("pagedListHolder", pagedListHolder);
			} else {
				model.put("pagedListHolder", null);
			}

			result.rejectValue("shortname", "shortname.inuse", shortNametemp);
			return new ModelAndView("itwChartStatusConfigListPage", model);

			// }
		}

		return new ModelAndView("redirect:/itwChartStatusConfigList.html");
	}

	@RequestMapping(value = "/editItwChartStatus", method = RequestMethod.GET)
	public ModelAndView editItwChartStatus(
			@ModelAttribute("command") ItwChartStatusBean itwChartStatusBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwChartStatus",
				prepareItwChartStatusBean(itwChartStatusService
						.getItwChartStatus(itwChartStatusBean.getId())));

		return new ModelAndView("editItwChartStatus", model);
	}

	private ItwChartStatus prepareModelItwChartStatus(
			ItwChartStatusBean itwChartStatusBean, int langId) {
		ItwChartStatus itwChartStatus= new ItwChartStatus();
		itwChartStatus.setShortName(itwChartStatusBean.getShortname());

		itwChartStatus.setId(itwChartStatusBean.getId());
		itwChartStatus.setLangid(langId);
		itwChartStatusBean.setId(null);
		return itwChartStatus;
	}

	private ItwChartStatus prepareModelItwChartStatusforUpdate(
			ItwChartStatusBean itwChartStatusBean, int langId) {
		ItwChartStatus itwChartStatus= new ItwChartStatus();

		itwChartStatus.setId(itwChartStatusBean.getId());
		itwChartStatus.setShortName(itwChartStatusBean.getShortname());
		itwChartStatus.setLangid(langId);
		itwChartStatusBean.setId(null);

		return itwChartStatus;
	}

	private List<ItwChartStatusBean> prepareListofItwChartStatus(
			List<ItwChartStatus> itwChartStatuss) {
		List<ItwChartStatusBean> beans = null;
		if (itwChartStatuss != null && !itwChartStatuss.isEmpty()) {
			beans = new ArrayList<ItwChartStatusBean>();
			ItwChartStatusBean bean = null;
			for (ItwChartStatus itwChartStatus : itwChartStatuss) {
				bean = new ItwChartStatusBean();
				bean.setId(itwChartStatus.getId());
				bean.setShortname(itwChartStatus.getShortName());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwChartStatusBean prepareItwChartStatusBean(
			ItwChartStatus itwChartStatus) {
		ItwChartStatusBean bean = new ItwChartStatusBean();
		bean.setShortname(itwChartStatus.getShortName());

		bean.setId(itwChartStatus.getId());
		return bean;
	}
}
