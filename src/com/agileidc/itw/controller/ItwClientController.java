package com.agileidc.itw.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.agileidc.itw.bean.ItwClientBean;
import com.agileidc.itw.bean.ItwModuleBean;
import com.agileidc.itw.bean.ItwProjectBean;
import com.agileidc.itw.bean.ItwReleasesBean;
import com.agileidc.itw.model.ItwClient;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.service.ItwClientService;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.web.ItwClientValidator;

@Controller
public class ItwClientController {

	@Autowired
	private ItwClientService itwClientService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;
	@Autowired
	private ItwProjectService itwProjectService;

	@RequestMapping(value = "/ItwClientConfigList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwClientBean> itwClientBean = prepareListofItwClient(itwClientService
				.listItwClient(langId.intValue()));
		if (itwClientBean != null) {
			PagedListHolder pagedListHolder = new PagedListHolder(itwClientBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("ItwClientConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwClient", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwClientBean itwClientBean,
			BindingResult result, HttpServletRequest request) {
		ItwClientValidator shortsValidator = new ItwClientValidator();
		shortsValidator.validate(itwClientBean, result);

		if (result.hasErrors()) {
			return new ModelAndView("addItwClient");
		} else {
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			ItwClient itwClient = prepareModelItwClient(itwClientBean,
					langId.intValue());
			try {
				itwClientService.addItwClient(itwClient);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate client name is being added for a new client in
				// table ITW_CLIENT
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("clientname", "clientname.unique",
							"Client is already in use, enter a different Client");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwClientBean itwClientBean1 = prepareItwClientBeanForAdd();

					model.remove("itwClient1");
					model.put("itwClient1", itwClientBean1);
					return new ModelAndView("addItwClient", model);
				}
			}

			String langType = itwClientBean.getLangType();

			return new ModelAndView("redirect:/ItwClientConfigList.html");
		}

	}

	private ItwClientBean prepareItwClientBeanForAdd() {
		ItwClientBean bean = new ItwClientBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwClient", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwClientBean itwClientBean,
			BindingResult result, HttpServletRequest request) {
		ItwClientValidator shortsValidator = new ItwClientValidator();
		shortsValidator.validate(itwClientBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwClient");
		} else {
			ItwClient itwClienttemp = itwClientService
					.getItwClient(itwClientBean.getId());
			String tempClientname = itwClientBean.getClientname();
			try {
				
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwClient itwClient = prepareModelItwClientforUpdate(
						itwClientBean, langId.intValue());
				itwClientService.addItwClient(itwClient);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate client name is being added for a existing client in
				// table ITW_CLIENT
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("clientname", "clientname.unique",
							"Client already in use, enter a different Client");
					Map<String, Object> model = new HashMap<String, Object>();
					//ItwClientBean itwClientBean1 = prepareItwClientBeanForAdd();
					ItwClientBean tempBean = prepareItwClientBean(itwClientService
							.getItwClient(itwClienttemp.getId()));
					tempBean.setClientname(tempClientname);
					
					model.remove("itwClient");
					model.put("itwClient", tempBean);
					return new ModelAndView("editItwClient", model);
				}
			}

			return new ModelAndView("redirect:/ItwClientConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwClient", method = RequestMethod.GET)
	public ModelAndView addItwClient(
			@ModelAttribute("command") ItwClientBean itwClientBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwClient");

		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("addItwClient", model);

	}

	@RequestMapping(value = "/deleteItwClient", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwClientBean itwClientBean,
			BindingResult result, HttpServletRequest request) {

		// String langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		// itwClientBean.setLangType(langDesc);
		String shortNametemp = "Client ID " + itwClientBean.getId()
				+ " already in use, cannot delete";

		// langDesc = request.getParameter("langDesc");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwProjectBean> itwProjectBean = prepareListofItwProjects(itwProjectService
				.listItwProjects(langId.intValue()));
		boolean setValue = false;
		if (itwProjectBean != null) {
			Iterator<ItwProjectBean> iterator = itwProjectBean.iterator();

			String tempProject = null;
			ItwProjectBean itwProjectBean2 = null;
			StringBuffer out = new StringBuffer();
			while (iterator.hasNext()) {
				itwProjectBean2 = (ItwProjectBean) iterator.next();
				out.append(itwProjectBean2.getClientid() + ";");

				System.out.println(out + "APPENDED VALUES");

			}

			if (out.toString().contains(itwClientBean.getId().toString() + ";")) {

				System.out.println("client ID CANT BE DELETED");
				tempProject = itwProjectBean2.getModulename();
				setValue = true;
			}
		}
		try {

			if (!setValue) {
				// Integer langId =
				// (Integer)request.getSession().getAttribute("langId");
				itwClientService.deleteItwClient(prepareModelItwClient(
						itwClientBean, langId.intValue()));
			} else {

				throw new java.sql.SQLIntegrityConstraintViolationException();

			}
		} catch (RuntimeException runtimeException) {
			// TODO: handle exception

			System.out.println("entered runtime exception catch block 1");
			if (runtimeException.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				// Map<String, Object> model = new HashMap<String, Object>();
				// Integer langId =
				// (Integer)request.getSession().getAttribute("langId");
				List<ItwClientBean> itwClientBeanList = prepareListofItwClient(itwClientService
						.listItwClient(langId.intValue()));
				if (itwClientBean != null) {
					/*
					 * java.util.Collections.sort(itwClientBeanList,
					 * ItwClientBean.Comparators.ID);
					 */
					PagedListHolder pagedListHolder = new PagedListHolder(
							itwClientBeanList);
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
			}
			System.out.println(shortNametemp);
			result.rejectValue("clientname", "clientname.inuse", shortNametemp);
			return new ModelAndView("redirect:/ItwClientConfigList.html", model);

		} catch (SQLIntegrityConstraintViolationException e1) {
			// TODO Auto-generated catch block

			System.out
					.println("-----------------------------------------------------");
			Map<String, Object> model1 = new HashMap<String, Object>();
			List<ItwClientBean> itwClientBeanList = prepareListofItwClient(itwClientService
					.listItwClient(langId.intValue()));
			if (itwClientBean != null) {

				PagedListHolder pagedListHolder = new PagedListHolder(
						itwClientBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				System.out.println("value of page variable issssss" + page);
				pagedListHolder.setPage(page);

				int pageSize = 5;
				pagedListHolder.setPageSize(pageSize);

				model.put("pagedListHolder", pagedListHolder);
				result.rejectValue("clientname", "clientname.inuse",
						shortNametemp);
				return new ModelAndView("ItwClientConfigListPage", model);
			}

		}

		return new ModelAndView("redirect:/ItwClientConfigList.html");

	}

	@RequestMapping(value = "/editItwClient", method = RequestMethod.GET)
	public ModelAndView editItwClient(
			@ModelAttribute("command") ItwClientBean itwClientBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwClient", prepareItwClientBean(itwClientService
				.getItwClient(itwClientBean.getId())));

		return new ModelAndView("editItwClient", model);
	}

	private ItwClient prepareModelItwClient(ItwClientBean itwClientBean,
			int langId) {

		ItwClient itwClient = new ItwClient();
		itwClient.setClientname(itwClientBean.getClientname());

		itwClient.setId(itwClientBean.getId());
		itwClient.setLangid(langId);
		itwClientBean.setId(null);
		return itwClient;
	}

	private ItwClient prepareModelItwClientforUpdate(
			ItwClientBean itwClientBean, int langId) {
		ItwClient itwClient = new ItwClient();

		itwClient.setId(itwClientBean.getId());
		itwClient.setClientname(itwClientBean.getClientname());
		itwClient.setLangid(langId);
		itwClientBean.setId(null);

		return itwClient;
	}

	private List<ItwClientBean> prepareListofItwClient(
			List<ItwClient> itwClients) {
		List<ItwClientBean> beans = null;
		if (itwClients != null && !itwClients.isEmpty()) {
			beans = new ArrayList<ItwClientBean>();
			ItwClientBean bean = null;
			for (ItwClient itwClient : itwClients) {
				bean = new ItwClientBean();
				bean.setId(itwClient.getId());
				bean.setClientname(itwClient.getClientname());
				bean.setLangid(itwClient.getLangid());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwClientBean prepareItwClientBean(ItwClient itwClient) {
		ItwClientBean bean = new ItwClientBean();
		bean.setClientname(itwClient.getClientname());
		bean.setLangid(itwClient.getLangid());
		bean.setId(itwClient.getId());
		return bean;
	}

	private List<ItwProjectBean> prepareListofItwProjects(
			List<ItwProject> itwProjects) {
		List<ItwProjectBean> beans = null;
		if (itwProjects != null && !itwProjects.isEmpty()) {

			beans = new ArrayList<ItwProjectBean>();
			ItwProjectBean bean = null;
			for (ItwProject itwProject : itwProjects) {

				java.sql.Date startDate = itwProject.getStartdate();
				java.sql.Date endDate = itwProject.getEnddate();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String sdate = df.format(startDate);
				String edate = df.format(endDate);

				bean = new ItwProjectBean();
				bean.setId(itwProject.getId());
				bean.setShortname(itwProject.getShortname());
				bean.setClientid(itwProject.getClientid());
				bean.setStartdate(sdate);
				bean.setEnddate(edate);
				bean.setLangId(itwProject.getLangId());

				bean.setModulename(itwProject.getModulename());
				beans.add(bean);

			}
		}
		return beans;
	}
}
