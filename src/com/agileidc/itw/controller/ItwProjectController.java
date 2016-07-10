package com.agileidc.itw.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.agileidc.itw.model.ItwClient;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.service.ItwClientService;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwModuleService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.web.ProjectValidator;

@Controller
public class ItwProjectController {

	@Autowired
	private ItwModuleService itwModuleService;
	@Autowired
	private ItwProjectService itwProjectService;
	@Autowired
	private ItwClientService itwClientService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;
	//String langDesc = null;
	List<ItwProjectBean> strings;
	List<ItwModuleBean> itwModuleBean = null;
	List<ItwClientBean> itwClientBean = null;

	@RequestMapping(value = "/saveItwProject", method = RequestMethod.POST)
	public ModelAndView saveItwProject(
			@ModelAttribute("command") ItwProjectBean itwProjectBean,
			BindingResult result, HttpServletRequest request) {
		//langDesc = request.getParameter("langDesc");
		ProjectValidator projectValidator = new ProjectValidator();
		projectValidator.validate(itwProjectBean, result);
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();
			
			if (itwProjectBean.getClientname() != null) {
				List<ItwClientBean> itwClientBean = addprepareListofItwClient(itwClientService
						.listItwClient(langId));

				model.put("itwClient", itwClientBean);
			}

			if (itwProjectBean.getModulename() != null) {

				String string = null;
				String temp = new String(itwProjectBean.getModulename());
				strings = new ArrayList<ItwProjectBean>();
				ItwProjectBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwProjectBean();
					string = tempArray[i];
					bean.setModulename(string);
					strings.add(bean);

				}

				List<ItwModuleBean> itwModuleBean1 = prepareListofItwModule(
						itwModuleService.listItwModule(langId), temp);

				List<ItwClientBean> itwClientBean = addprepareListofItwClient(itwClientService
						.listItwClient(langId));

				model.put("itwClient", itwClientBean);

				model.put("itwProjectModules", strings);
				model.put("itwModule", itwModuleBean1);

			}
			if (itwProjectBean.getModulename() == null) {
				List<ItwModuleBean> itwModuleBean2 = prepareListofItwModule(itwModuleService
						.listItwModule(langId));
				List<ItwClientBean> itwClientBean = addprepareListofItwClient(itwClientService
						.listItwClient(langId));
				model.put("itwClient", itwClientBean);
				model.put("itwModule", itwModuleBean2);
			} else {
				// model.put("itwModule", null);
			}
			return new ModelAndView("addItwProject", model);

		} else {
			try {

				String s2 = itwProjectBean.getModulename().replace('[', '\0')
						.replace(']', '\0');

				itwProjectBean.setModulename(s2);

				ItwProject itwProject = prepareModel(itwProjectBean, langId);

				itwProjectService.addItwProject(itwProject);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a new role in
				// table ITW_ROLES

				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"ShortName already in use, enter a different ShortName");
					Map<String, Object> model = new HashMap<String, Object>();

					return new ModelAndView("addItwProject", model);
				}

			}
			String langType = itwProjectBean.getLangDesc();
			return new ModelAndView(
					"redirect:/projectConfigList.html?langDesc=" + langType);
		}

	}

	@RequestMapping(value = "/itwProjects", method = RequestMethod.GET)
	public ModelAndView listItwProjects() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("projectConfigListPage", model);
	}

	@RequestMapping(value = "/addItwProject", method = RequestMethod.GET)
	public ModelAndView addItwProject(
			@ModelAttribute("command") ItwProjectBean itwProjectBean,
			BindingResult result, HttpServletRequest request) {
		//langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");
		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwModuleBean> itwModuleBean = addprepareListofItwModule(itwModuleService
				.listItwModule(langId));

		List<ItwClientBean> itwClientBean = addprepareListofItwClient(itwClientService
				.listItwClient(langId));

		if (itwClientBean != null) {
			model.put("itwClient", itwClientBean);
		}

		else {
			model.put("itwClient", null);
		}
		if (itwModuleBean != null) {

			model.put("itwModule", itwModuleBean);

		} else {
			model.put("itwModule", null);
		}
		return new ModelAndView("addItwProject", model);
	}

	@RequestMapping(value = "/editItwProject", method = RequestMethod.GET)
	public ModelAndView editItwProject(
			@ModelAttribute("command") ItwProjectBean itwProjectBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");
		
		Map<String, Object> model = new HashMap<String, Object>();

		ItwProjectBean itwProjectBean2 = prepareItwProjectBean(itwProjectService
				.getItwProject(itwProjectBean.getId()), request);

		model.put("itwProjectModules", strings);
		model.put("itwProject", itwProjectBean2);

		if (itwModuleBean != null) {

			model.put("itwModule", itwModuleBean);

		} else {
			model.put("itwModule", null);

		}
		if (itwClientBean != null) {

			model.put("itwClient", itwClientBean);

		} else {

			model.put("itwClient", null);
		}


		return new ModelAndView("editItwProject", model);
	}

	@RequestMapping(value = "/updateItwProject", method = RequestMethod.POST)
	public ModelAndView updateProject(
			@ModelAttribute("command") ItwProjectBean itwProjectBean,
			BindingResult result, HttpServletRequest request) {

		
		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");
		ProjectValidator projectValidator = new ProjectValidator();
		projectValidator.validate(itwProjectBean, result);

		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();

			if (itwProjectBean.getModulename() != null) {

				String string = null;

				String temp = new String(itwProjectBean.getModulename());

				System.out.println(temp + "   List of Modules   ");
				strings = new ArrayList<ItwProjectBean>();
				ItwProjectBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwProjectBean();
					string = tempArray[i];
					System.out.println(string);
					bean.setModulename(string);

					strings.add(bean);
				}

				List<ItwModuleBean> itwModuleBean1 = prepareListofItwModule(
						itwModuleService.listItwModule(langId), temp);
				List<ItwClientBean> itwClientBean = prepareListofItwClient(itwClientService
						.listItwClient(langId));

				model.put("itwProjectModules", strings);
				model.put("itwModule", itwModuleBean1);
				model.put("itwClient", itwClientBean);

			}

			// model.put("itwProject", itwProjectBean2);

			else if (itwProjectBean.getModulename() == null) {

				List<ItwModuleBean> itwModuleBean2 = prepareListofItwModule(itwModuleService
						.listItwModule(langId));
				List<ItwClientBean> itwClientBean = prepareListofItwClient(itwClientService
						.listItwClient(langId));
				model.put("itwModule", itwModuleBean2);

				model.put("itwClient", itwClientBean);

			} else {
				model.put("itwModule", null);

			}
			return new ModelAndView("editItwProject", model);

		} else {

			ItwProject itwProjecttemp = itwProjectService
					.getItwProject(itwProjectBean.getId());
			ItwProject itwProject = prepareModelItwProjectforUpdate(itwProjectBean);

			itwProjectService.addItwProject(itwProject);

			String langType = itwProjectBean.getLangDesc();
			return new ModelAndView(
					"redirect:/projectConfigList.html?langDesc=" + langType);
		}
	}

	@RequestMapping(value = "/projectConfigList", method = RequestMethod.GET)
	public ModelAndView projectConfigList(HttpServletRequest request) {
		System.out.println("started project config List page");
		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");

		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwProjectBean> itwProjectBean = prepareListofItwProjects(itwProjectService
				.listItwProjects(langId));

		if (itwProjectBean != null) {
			// java.util.Collections.sort(itwProjectBean,
			// ItwProjectBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwProjectBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("projectConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwProject", method = RequestMethod.GET)
	public ModelAndView deleteItwProject(
			@ModelAttribute("command") ItwProjectBean itwProjectBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");

		//itwProjectBean.setLangDesc(langDesc);

		String projectNametemp = "Project ID " + itwProjectBean.getId()
				+ " already in use, cannot delete";

		try {
			System.out.println("Run Time");
			itwProjectService
					.deleteItwProject(prepareModelItwProjectforDelete(itwProjectBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// role id is being deleted which is in use in table ITW_USERS as a
			// foreign key

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				List<ItwProjectBean> itwProjectBeanList = prepareListofItwProjects(itwProjectService
						.listItwProjects(langId));
				if (itwProjectBean != null) {
					// java.util.Collections.sort(itwProjectBeanList,
					// ItwProjectBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwProjectBeanList);
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
						projectNametemp);
				return new ModelAndView("projectConfigListPage", model);
			}

		}

		return new ModelAndView("redirect:/projectConfigList.html");
	}

	private ItwProject prepareModel(ItwProjectBean itwProjectBean, int langId) {

		String startDate = itwProjectBean.getStartdate();
		String endDate = itwProjectBean.getEnddate();
		System.out.println(startDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;
		java.util.Date endDate1 = null;
		try {
			startDate1 = sdf1.parse(startDate);
			endDate1 = sdf1.parse(endDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = new Date(startDate1.getTime());
		java.sql.Date sqlEndDate = new Date(endDate1.getTime());

		ItwProject itwProject = new ItwProject();

		itwProject.setClientid(itwProjectBean.getClientid());
		itwProject.setShortname(itwProjectBean.getShortname());
		itwProjectBean.setId(itwProjectBean.getId());
		itwProject.setStartdate(sqlStartDate);
		itwProject.setEnddate(sqlEndDate);
		itwProject.setLangId(langId);

		System.out.println(itwProjectBean.getModulename());

		String[] splitId = itwProjectBean.getModulename().split(",");

		List<ItwModule> modules = new ArrayList<ItwModule>();
		ItwModule module = new ItwModule();
		StringBuffer idModuleStr = new StringBuffer();

		for (int j = 0; j < splitId.length; j++) {
			List<ItwModule> listItwModule = itwModuleService
					.getItwModuleByShortName(splitId[j]);
			for (ItwModule itwModule : listItwModule) {
				idModuleStr = idModuleStr.append(itwModule.getId()).append(",");
			}
		}

		List<ItwClient> listItwClient = itwClientService
				.getItwClientByShortName(itwProjectBean.getClientname());

		List<ItwClient> client = new ArrayList<ItwClient>();
		ItwClient clients = new ItwClient();

		Iterator<ItwClient> iterator = listItwClient.iterator();
		while (iterator.hasNext()) {
			ItwClient itwClient = (ItwClient) iterator.next();
			itwProject.setClientid(itwClient.getId());
		}

		// itwProject.setClientname(idClient.substring(0,
		// idModuleStr.length() - 1));
		itwProject.setModulename(idModuleStr.substring(0,
				idModuleStr.length() - 1));

		return itwProject;
	}

	private ItwProjectBean prepareItwProjectBean(ItwProject itwProject, HttpServletRequest request) {

		java.sql.Date startDate = itwProject.getStartdate();
		java.sql.Date endDate = itwProject.getEnddate();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String startDateStr = df.format(startDate);
		String endDateStr = df.format(endDate);

		String string = null;

		String temp = new String(itwProject.getModulename());

		strings = new ArrayList<ItwProjectBean>();
		ItwProjectBean bean = new ItwProjectBean();

		String[] splitId = itwProject.getModulename().split(",");

		List<ItwModule> modules = new ArrayList<ItwModule>();

		ItwModule module = new ItwModule();
		for (int i = 0; i < splitId.length; i++) {

			module = itwModuleService
					.getItwModule(Integer.parseInt(splitId[i]));
			modules.add(module);
		}

		StringBuffer buffer = new StringBuffer();

		Iterator<ItwModule> iterator = modules.iterator();
		while (iterator.hasNext()) {
			ItwModule itwModule = (ItwModule) iterator.next();
			buffer.append(itwModule.getShortname()).append(",");
		}

		bean.setModulename(buffer.substring(0, buffer.length() - 1));

		String[] tempArray = buffer.substring(0, buffer.length() - 1)
				.split(",");
		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");
		for (int i = 0; i < tempArray.length; i++) {
			bean = new ItwProjectBean();
			string = tempArray[i];
			System.out.println(string);
			bean.setModulename(string);

			strings.add(bean);

			itwModuleBean = prepareListofItwModule(
					itwModuleService.listItwModule(langId), buffer.substring(0,
							buffer.length() - 1));

			ItwClient client = new ItwClient();
			client = itwClientService.getItwClient(itwProject.getClientid());

			bean.setClientname(client.getClientname());
						itwClientBean = prepareListofItwClient(
					itwClientService.listItwClient(langId),
					client.getClientname());

			bean.setLangId(itwProject.getLangId());
			bean.setStartdate(startDateStr);
			
			bean.setEnddate(endDateStr);
			bean.setShortname(itwProject.getShortname());
			bean.setId(itwProject.getId());

		}

		Iterator<ItwProjectBean> iterator1 = strings.iterator();
		while (iterator.hasNext()) {
			ItwProjectBean itwProjectBean1 = (ItwProjectBean) iterator1.next();
		}

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

				// bean.setClientid(itwProject.getClientid());
				System.out.println(itwProject.getShortname());

				ItwClient client = new ItwClient();
				client = itwClientService
						.getItwClient(itwProject.getClientid());

				bean.setClientname(client.getClientname());
				bean.setStartdate(sdate);
				bean.setEnddate(edate);
				bean.setLangId(itwProject.getLangId());

				String[] splitId = itwProject.getModulename().split(",");

				List<ItwModule> modules = new ArrayList<ItwModule>();

				ItwModule module = new ItwModule();
				for (int i = 0; i < splitId.length; i++) {

					module = itwModuleService.getItwModule(Integer
							.parseInt(splitId[i]));

					modules.add(module);
				}

				StringBuffer buffer = new StringBuffer();

				Iterator<ItwModule> iterator = modules.iterator();
				while (iterator.hasNext()) {
					ItwModule itwModule = (ItwModule) iterator.next();

					buffer.append(itwModule.getShortname()).append(",");
				}

				bean.setModulename(buffer.substring(0, buffer.length() - 1));
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwProject prepareModelItwProjectforUpdate(
			ItwProjectBean itwProjectBean) {
		System.out.println("Inside Update");

		String startDate = itwProjectBean.getStartdate();
		String endDate = itwProjectBean.getEnddate();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;
		java.util.Date endDate1 = null;
		try {
			startDate1 = sdf1.parse(startDate);
			endDate1 = sdf1.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Inside Update 2");
		java.sql.Date sqlStartDate = new Date(startDate1.getTime());
		java.sql.Date sqlEndDate = new Date(endDate1.getTime());
		ItwProject itwProject = new ItwProject();
		itwProject.setShortname(itwProjectBean.getShortname());

		List<ItwClient> listItwClient = itwClientService
				.getItwClientByShortName(itwProjectBean.getClientname());
		for (ItwClient itwClient : listItwClient) {
			itwProject.setClientid(itwClient.getId());
		}

		itwProject.setStartdate(sqlStartDate);
		itwProject.setEnddate(sqlEndDate);
		itwProject.setLangId(itwProjectBean.getLangId());
		itwProject.setId(itwProjectBean.getId());

		String tempStr = itwProjectBean.getModulename();
		StringBuffer idModuleStr = new StringBuffer();

		String[] parts = tempStr.split(",");
		int i = parts.length;

		for (int j = 0; j < i; j++) {
			List<ItwModule> listItwModule = itwModuleService
					.getItwModuleByShortName(parts[j]);
			for (ItwModule itwModule : listItwModule) {
				idModuleStr = idModuleStr.append(itwModule.getId()).append(",");
			}
		}

		itwProject.setModulename(idModuleStr.substring(0,
				idModuleStr.length() - 1));
		itwProjectBean.setId(null);
		return itwProject;
	}

	private ItwProject prepareModelItwProjectforDelete(
			ItwProjectBean itwProjectBean) {

		ItwProject itwProject = new ItwProject();
		itwProject.setId(itwProjectBean.getId());
		return itwProject;
	}

	private List<ItwModuleBean> prepareListofItwModule(
			List<ItwModule> itwModules, String temp) {

		List<ItwModuleBean> beans = null;
		if (itwModules != null && !itwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule itwModule : itwModules) {
				bean = new ItwModuleBean();

				if (!(temp != null && temp.contains(itwModule.getShortname()))) {
					bean.setId(itwModule.getId());

					bean.setShortname(itwModule.getShortname());
					bean.setLangid(itwModule.getLangid());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	private List<ItwClientBean> prepareListofItwClient(
			List<ItwClient> itwClients, String temp) {
		List<ItwClientBean> beans = null;
		if (itwClients != null && !itwClients.isEmpty()) {
			beans = new ArrayList<ItwClientBean>();
			ItwClientBean bean = null;
			for (ItwClient itwClient : itwClients) {
				bean = new ItwClientBean();

				if (!(temp != null && temp.contains(itwClient.getClientname()))) {
					bean.setId(itwClient.getId());

					bean.setClientname(itwClient.getClientname());
					bean.setLangid(itwClient.getLangid());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	private List<ItwModuleBean> addprepareListofItwModule(
			List<ItwModule> itwModules) {
		List<ItwModuleBean> beans = null;
		if (itwModules != null && !itwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule itwModule : itwModules) {
				bean = new ItwModuleBean();
				bean.setId(itwModule.getId());
				bean.setShortname(itwModule.getShortname());

				bean.setLangid(itwModule.getLangid());

				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwClientBean> addprepareListofItwClient(
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

	private List<ItwModuleBean> prepareListofItwModule(
			List<ItwModule> itwModules) {
		List<ItwModuleBean> beans = null;
		if (itwModules != null && !itwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule itwModule : itwModules) {
				bean = new ItwModuleBean();
				bean.setId(itwModule.getId());
				bean.setShortname(itwModule.getShortname());
				bean.setLangid(itwModule.getLangid());

				beans.add(bean);

			}
		}
		return beans;
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
}
