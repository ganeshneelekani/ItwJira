package com.agileidc.itw.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwPriorityBean;
import com.agileidc.itw.bean.ItwReleasesBean;
import com.agileidc.itw.dao.ItwPriorityDAOTemp;
import com.agileidc.itw.model.ItwLangTypes;
import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.model.ItwUserIcon;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwPriorityService;
import com.agileidc.itw.web.PriorityValidator;

@Controller
public class ItwPriorityController {

	@Autowired
	private ItwPriorityService itwPriorityService;
	@Autowired
	private ItwLangTypesService itwLangTypeService;
	@Autowired
	private ItwPriorityDAOTemp itwPriorityDAOTemp;

	@RequestMapping(value = "/saveItwPriority", method = RequestMethod.POST)
	public ModelAndView saveItwPriority(
			@ModelAttribute("command") ItwPriorityBean itwPriorityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		PriorityValidator PriorityValidator = new PriorityValidator();
		PriorityValidator.validate(itwPriorityBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("addItwPriority");
		} else {
			try {

				ItwPriority itwPriority = prepareModel(itwPriorityBean, request);

				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				itwPriority.setLangId(langId.intValue());

				itwPriorityService.addItwPriority(itwPriority);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Priority shortname is being added for a new
				// priority in
				// table ITW_PRIORITY
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Priority nameis already in use, enter a different Priority name");
					Map<String, Object> model = new HashMap<String, Object>();

					return new ModelAndView("addItwPriority", model);
				}

			}
			return new ModelAndView("redirect:/itwPriorityConfigList.html");
		}

	}

	@RequestMapping(value = "/itwPrioritys", method = RequestMethod.GET)
	public ModelAndView listItwPrioritys() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("itwPriorityConfigListPage", model);
	}

	@RequestMapping(value = "/addItwPriority", method = RequestMethod.GET)
	public ModelAndView addItwPriority(
			@ModelAttribute("command") ItwPriorityBean itwPriorityBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("addItwPriority", model);
	}

	@RequestMapping(value = "/editItwPriority", method = RequestMethod.GET)
	public ModelAndView editItwPriority(
			@ModelAttribute("command") ItwPriorityBean itwPriorityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		String fileShortname = "";

		ItwPriority itwPrioritytemp = itwPriorityService
				.getItwPriority(itwPriorityBean.getId());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwPriority", prepareItwPriorityBean(itwPriorityService
				.getItwPriority(itwPriorityBean.getId())));

		return new ModelAndView("editItwPriority", model);
	}

	@RequestMapping(value = "/updateItwPriority", method = RequestMethod.POST)
	public ModelAndView updatePriority(
			@ModelAttribute("command") ItwPriorityBean itwPriorityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		PriorityValidator PriorityValidator = new PriorityValidator();
		PriorityValidator.validate(itwPriorityBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("addItwPriority");
		} else {
			ItwPriority itwPrioritytemp = itwPriorityService
					.getItwPriority(itwPriorityBean.getId());
			ItwPriority itwPriority = prepareModelItwPriorityforUpdate(itwPriorityBean);
			String tempShortname = itwPriorityBean.getShortname();

			try {

				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				itwPriority.setLangId(langId.intValue());
				itwPriorityService.addItwPriority(itwPriority);

			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate priority short name is being added for a existing
				// name  in  table ITW_PRIORITY
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Priority already in use, enter a different Prioritys");
					Map<String, Object> model = new HashMap<String, Object>();
					
					ItwPriorityBean tempBean = prepareItwPriorityBean(itwPriorityService.getItwPriority(itwPrioritytemp.getId()));
							
					tempBean.setShortname(tempShortname);
					model.remove("itwPrioritys1");
					model.put("itwPrioritys1",tempBean );
					
					return new ModelAndView("editItwPriority", model);
					
				}
			}

			return new ModelAndView("redirect:/itwPriorityConfigList.html");
		}
	}

	@RequestMapping(value = "/itwPriorityConfigList", method = RequestMethod.GET)
	public ModelAndView PriorityConfigList(HttpServletRequest request) {
		System.out.println("started Priority config List page");
		Map<String, Object> model = new HashMap<String, Object>();

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		if (itwPriorityList != null) {
			List<ItwPriorityBean> itwPriorityBeanList = prepareListofItwPrioritys(itwPriorityList);
			if (itwPriorityBeanList != null) {

				PagedListHolder pagedListHolder = new PagedListHolder(
						itwPriorityBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);

				pagedListHolder.setPage(page);
				int pageSize = 15;
				pagedListHolder.setPageSize(pageSize);
				model.put("pagedListHolder", pagedListHolder);
			} else {
				model.put("pagedListHolder", null);
			}
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("itwPriorityConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwPriority", method = RequestMethod.GET)
	public ModelAndView deleteItwPriority(
			@ModelAttribute("command") ItwPriorityBean itwPriorityBean,
			BindingResult result, HttpServletRequest request) {
		String PriorityShortnametemp = "Priority ID " + itwPriorityBean.getId()
				+ " already in use, cannot delete";
		try {
			System.out.println("Run Time");
			itwPriorityService
					.deleteItwPriority(prepareModelItwPriorityforDelete(itwPriorityBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// role id is being deleted which is in use in table ITW_USERS as a
			// foreign key
			System.out.println("caught runtime exception isss"
					+ runtimeException.toString());

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");

				List<ItwPriorityBean> itwPriorityBeanList = prepareListofItwPrioritys(itwPriorityService
						.listItwPriorities(langId.intValue()));
				if (itwPriorityBean != null) {
					java.util.Collections.sort(itwPriorityBeanList,
							ItwPriorityBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwPriorityBeanList);
					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);
					System.out.println("value of page variable issssss" + page);
					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}
				result.rejectValue("shortname", "shortname.inuse",
						PriorityShortnametemp);
				return new ModelAndView("itwPriorityConfigListPage", model);
			}
		}
		return new ModelAndView("redirect:/itwPriorityConfigList.html");
	}

	private ItwPriority prepareModel(ItwPriorityBean itwPriorityBean,
			HttpServletRequest request) {

		ItwPriority itwPriority = new ItwPriority();
		itwPriority.setShortname(itwPriorityBean.getShortname());
		itwPriority.setId(itwPriorityBean.getId());
		itwPriority.setColorcode(itwPriorityBean.getColorcode());
		itwPriority.setId(null);
		return itwPriority;
	}

	private ItwPriorityBean prepareItwPriorityBean(ItwPriority itwPriority) {

		ItwPriorityBean bean = new ItwPriorityBean();

		bean.setShortname(itwPriority.getShortname());
		bean.setId(itwPriority.getId());
		bean.setColorcode(itwPriority.getColorcode());
		return bean;
	}

	private List<ItwPriorityBean> prepareListofItwPrioritys(
			List<ItwPriority> itwPrioritys) {
		List<ItwPriorityBean> beans = null;
		if (itwPrioritys != null && !itwPrioritys.isEmpty()) {

			beans = new ArrayList<ItwPriorityBean>();
			ItwPriorityBean bean = null;
			for (ItwPriority itwPriority : itwPrioritys) {

				bean = new ItwPriorityBean();
				bean.setId(itwPriority.getId());

				bean.setShortname(itwPriority.getShortname());

				bean.setColorcode(itwPriority.getColorcode());

				beans.add(bean);

			}
		}
		return beans;

	}

	private ItwPriority prepareModelItwPriorityforUpdate(
			ItwPriorityBean itwPriorityBean) {
		ItwPriority itwPriority = new ItwPriority();
		itwPriority.setId(itwPriorityBean.getId());
		itwPriority.setShortname(itwPriorityBean.getShortname());
		itwPriority.setColorcode(itwPriorityBean.getColorcode());
		return itwPriority;
	}

	private ItwPriority prepareModelItwPriorityforDelete(
			ItwPriorityBean itwPriorityBean) {
		ItwPriority itwPriority = new ItwPriority();
		itwPriority.setId(itwPriorityBean.getId());
		return itwPriority;
	}

	private ItwPriorityBean prepareItwPriorityBeanForAdd() {
		ItwPriorityBean bean = new ItwPriorityBean();
		return bean;
	}

}
