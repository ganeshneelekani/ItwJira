package com.agileidc.itw.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.agileidc.itw.bean.ItwReleasesBean;
import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.service.ItwReleasesService;
import com.agileidc.itw.web.ItwReleasesValidator;

@Controller
public class ItwReleasesController {

	@Autowired
	private ItwReleasesService itwReleasesService;

	@RequestMapping(value = "/itwReleasesConfigList", method = RequestMethod.GET)
	public ModelAndView moduleConfigList(HttpServletRequest request) {
		System.out.println("started module config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwReleasesBean> itwReleasesBean = prepareListofItwReleases(itwReleasesService
				.listItwReleases(langId.intValue()));

		if (itwReleasesBean != null) {
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwReleasesBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		}
		return new ModelAndView("itwReleasesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwReleases", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwReleasesBean itwReleasesBean,
			BindingResult result, HttpServletRequest request) {
		ItwReleasesValidator shortsValidator = new ItwReleasesValidator();
		shortsValidator.validate(itwReleasesBean, result);
		
		System.out.println("====Active =====" +itwReleasesBean.getActive());

		if (result.hasErrors()) {
			return new ModelAndView("addItwReleases");
		} else {
			System.out.println("Inside saveItwReleases");
			ItwReleases itwReleases = prepareModelItwReleases(itwReleasesBean);
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				itwReleases.setLangId(langId);
				itwReleasesService.addItwReleases(itwReleases);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Release  is already in use, enter a different Release");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwReleasesBean itwReleasesBean1 = prepareItwReleasesBeanForAdd();
					model.remove("itwReleases1");
					model.put("itwReleases1", itwReleasesBean1);
					return new ModelAndView("addItwReleases", model);
				}
			}
			return new ModelAndView("redirect:/itwReleasesConfigList.html");
		}

	}

	private ItwReleasesBean prepareItwReleasesBeanForAdd() {
		ItwReleasesBean bean = new ItwReleasesBean();

		return bean;
	}
	
	@RequestMapping(value = "/updateItwReleases", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwReleasesBean itwReleasesBean,
			BindingResult result, HttpServletRequest request) {
		ItwReleasesValidator shortsValidator = new ItwReleasesValidator();
		shortsValidator.validate(itwReleasesBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwReleases");
		} else {
			ItwReleases itwReleasestemp = itwReleasesService
					.getItwReleases(itwReleasesBean.getId());
			String tempShortname = itwReleasesBean.getShortname();
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwReleases itwReleases = prepareModelItwReleasesforUpdate(itwReleasesBean);
				itwReleases.setLangId(langId);
				itwReleasesService.addItwReleases(itwReleases);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Release already in use, enter a different Releases");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwReleasesBean tempBean = prepareItwReleasesBean(itwReleasesService
							.getItwReleases(itwReleasestemp.getId()));
					tempBean.setShortname(tempShortname);
					model.put("itwReleases", tempBean);

					return new ModelAndView("editItwReleases", model);
				}
			}

			return new ModelAndView("redirect:/itwReleasesConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwReleases", method = RequestMethod.GET)
	public ModelAndView addItwReleases(
			@ModelAttribute("command") ItwReleasesBean itwReleasesBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwReleases");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		model.put("itwReleaseslist",
				prepareListofItwReleases(itwReleasesService
						.listItwReleases(langId.intValue())));
		return new ModelAndView("addItwReleases", model);

	}

	@RequestMapping(value = "/deleteItwReleases", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwReleasesBean itwReleasesBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside delete------------");

		String shortNametemp = "Releases ID " + itwReleasesBean.getId()
				+ " already in use, cannot delete";
		try {
			itwReleasesService
					.deleteItwReleases(prepareModelItwReleases(itwReleasesBean));

		} catch (RuntimeException runtimeException) {

			if (runtimeException.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {

				Map<String, Object> model = new HashMap<String, Object>();
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				List<ItwReleasesBean> itwReleasesBeanList = prepareListofItwReleases(itwReleasesService
						.listItwReleases(langId.intValue()));
				if (itwReleasesBean != null) {
					java.util.Collections.sort(itwReleasesBeanList,
							ItwReleasesBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwReleasesBeanList);
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
						shortNametemp);
				return new ModelAndView("itwReleasesConfigList", model);

			}
		}

		return new ModelAndView("redirect:/itwReleasesConfigList.html");
	}

	@RequestMapping(value = "/editItwReleases", method = RequestMethod.GET)
	public ModelAndView editItwReleases(
			@ModelAttribute("command") ItwReleasesBean itwReleasesBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwReleases", prepareItwReleasesBean(itwReleasesService
				.getItwReleases(itwReleasesBean.getId())));

		return new ModelAndView("editItwReleases", model);
	}

	private ItwReleases prepareModelItwReleases(ItwReleasesBean itwReleasesBean) {
	
		
		String startDate = itwReleasesBean.getReleasedate();
		System.out.println(startDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;
		
		try {
			startDate1 = sdf1.parse(startDate);
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = new Date(startDate1.getTime());

		ItwReleases itwReleases = new ItwReleases();
		itwReleases.setShortname(itwReleasesBean.getShortname());
		itwReleases.setReleasedate(sqlStartDate);

		itwReleases.setReleasedescription(itwReleasesBean
				.getReleasedescription());
		itwReleases.setId(itwReleasesBean.getId());
		itwReleasesBean.setId(null);
		itwReleases.setActive(itwReleasesBean.getActive());
		
		return itwReleases;
	}

	private ItwReleases prepareModelItwReleasesforUpdate(
			ItwReleasesBean itwReleasesBean) {
		
		String startDate = itwReleasesBean.getReleasedate();
		System.out.println(startDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;
		
		try {
			startDate1 = sdf1.parse(startDate);
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = new Date(startDate1.getTime());

		ItwReleases itwReleases = new ItwReleases();

		itwReleases.setId(itwReleasesBean.getId());
		itwReleases.setShortname(itwReleasesBean.getShortname());
		itwReleases.setReleasedate(sqlStartDate);

		itwReleases.setReleasedescription(itwReleasesBean
				.getReleasedescription());
		itwReleasesBean.setId(null);
		itwReleases.setActive(itwReleasesBean.getActive());

		return itwReleases;
	}

	private List<ItwReleasesBean> prepareListofItwReleases(
			List<ItwReleases> itwReleasess) {
		List<ItwReleasesBean> beans = null;
		if (itwReleasess != null && !itwReleasess.isEmpty()) {
			

			
			beans = new ArrayList<ItwReleasesBean>();
			ItwReleasesBean bean = null;
			
			for (ItwReleases itwReleases : itwReleasess) {
				
				java.sql.Date startDate = itwReleases.getReleasedate();

						DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						String sdate = df.format(startDate);

						java.sql.Date sqlStartDate = new Date(startDate.getTime());

				bean = new ItwReleasesBean();
				bean.setId(itwReleases.getId());
				bean.setShortname(itwReleases.getShortname());
				bean.setReleasedate(sdate);
				bean.setActive(itwReleases.getActive());

				bean.setReleasedescription(itwReleases
						.getReleasedescription());
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwReleasesBean prepareItwReleasesBean(ItwReleases itwReleases) {
		
		
		
		ItwReleasesBean bean = new ItwReleasesBean();
		java.sql.Date startDate = itwReleases.getReleasedate();

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String sdate = df.format(startDate);

		java.sql.Date sqlStartDate = new Date(startDate.getTime());

		
		bean.setShortname(itwReleases.getShortname());
		bean.setReleasedate(sdate);
		bean.setActive(itwReleases.getActive());

		bean.setReleasedescription(itwReleases
				.getReleasedescription());
		bean.setId(itwReleases.getId());
		return bean;
	}
}
