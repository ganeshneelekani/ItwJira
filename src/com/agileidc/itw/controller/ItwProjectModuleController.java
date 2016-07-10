package com.agileidc.itw.controller;

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

import com.agileidc.itw.bean.ItwModuleBean;
import com.agileidc.itw.bean.ItwProjectBean;
import com.agileidc.itw.bean.ItwProjectModuleBean;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwProjectModule;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwModuleService;
import com.agileidc.itw.service.ItwProjectModuleService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.web.ProjectModuleValidator;

@Controller
public class ItwProjectModuleController {

	@Autowired
	private ItwProjectService itwProjectService;
	@Autowired
	private ItwProjectModuleService itwProjectModuleService;
	@Autowired
	private ItwModuleService itwModuleService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;
	// String langDesc = null;
	List<ItwProjectModuleBean> strings, UserId;
	List<ItwProjectBean> ItwProjectBean = null;
	List<ItwModuleBean> ItwModuleBean = null;
	List<ItwProject> Users = new ArrayList<ItwProject>();
	List<ItwModule> Roles = null;
	List<ItwModule> roles = null;
	Integer id;
	Integer uservalue = 0;

	@RequestMapping(value = "/itwProjectModuleConfigarationList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {
		System.out.println("started ProjectModule config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		// String langDesc = request.getParameter("langDesc");

		List<ItwProjectModuleBean> ItwProjectModuleBean = prepareListofItwProjectModule(itwProjectModuleService
				.listItwProjectModule(langId));
		if (ItwProjectModuleBean != null) {
			// Collections.sort(ItwProjectModuleBean,
			// ItwProjectModuleBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					ItwProjectModuleBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("ProjectModulesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwUserModules", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwProjectModuleBean saveItwProjectModuleBean,
			BindingResult result, HttpServletRequest request) {

		System.out.println(saveItwProjectModuleBean.getProjectname()
				+ " PROJECT NAME");
		// String langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		ProjectModuleValidator shortsValidator = new ProjectModuleValidator();
		shortsValidator.validate(saveItwProjectModuleBean, result);
		Integer value = saveItwProjectModuleBean.getId();
		if (result.hasErrors()) {

			if (saveItwProjectModuleBean.getModulename() != null
					&& (saveItwProjectModuleBean.getProjectname() == null || saveItwProjectModuleBean
							.getProjectname().equals("Select Project"))) {

				List<ItwProjectBean> ItwProjectBean = prepareListofItwProject(
						itwProjectService.listItwProjects(langId),
						saveItwProjectModuleBean.getProjectid());

				List<ItwModuleBean> ItwModuleBean = prepareListofItwModule(
						itwModuleService.listItwModule(langId),
						saveItwProjectModuleBean.getModuleid());

				String string = null;
				String temp = new String(
						saveItwProjectModuleBean.getProjectname());
				strings = new ArrayList<ItwProjectModuleBean>();
				ItwProjectModuleBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwProjectModuleBean();
					string = tempArray[i];
					bean.setProjectname(string);
					strings.add(bean);

				}

				model.put("ItwProjectModule",
						saveItwProjectModuleBean.getProjectname());
				model.put("ItwProject", ItwProjectBean);
				model.put("ItwModule", ItwModuleBean);
				model.put("ItwProjectModuleUsers", strings);
			}

			if (!(saveItwProjectModuleBean.getProjectname() == null || saveItwProjectModuleBean
					.getProjectname().equals("Select Project"))
					&& saveItwProjectModuleBean.getModulename() == null) {

				List<ItwModuleBean> ItwModuleBean = addprepareListofItwModule(itwModuleService
						.listItwModule(langId));
				ItwProjectBean = prepareListofItwProject(
						itwProjectService.listItwProjects(langId),
						saveItwProjectModuleBean.getProjectid());
				model.put("ItwProjectModule",
						saveItwProjectModuleBean.getProjectname());
				model.put("ItwProject", ItwProjectBean);
				model.put("ItwModule", ItwModuleBean);

			}
			if (saveItwProjectModuleBean.getProjectname().equals(
					"Select Project")
					&& saveItwProjectModuleBean.getProjectname() == null) {

				System.out.println("========3===========");
				List<ItwProjectBean> ItwProjectBean = prepareListofItwProject(itwProjectService
						.listItwProjects(langId));
				List<ItwModuleBean> ItwModuleBean = addprepareListofItwModule(itwModuleService
						.listItwModule(langId));

				model.put("ItwModule", ItwModuleBean);
				model.put("ItwProject", ItwProjectBean);
			} else {
			}
			return new ModelAndView("addItwProjectModules", model);
		} else {

			try {

				System.out.println(saveItwProjectModuleBean.getProjectname()
						+ "   Shortname        ");
				System.out.println(saveItwProjectModuleBean.getModulename()
						+ "   mODULE nAME     ");
				List<ItwModule> listItwModule1;
				String[] splitIdRoles = saveItwProjectModuleBean
						.getModulename().split(",");
				for (int j = 0; j < splitIdRoles.length; j++) {
					listItwModule1 = itwModuleService
							.getItwModuleByShortName(splitIdRoles[j]);
					Iterator<ItwModule> rolevalue = listItwModule1.iterator();

					while (rolevalue.hasNext()) {
						ItwModule ItwModule = (ItwModule) rolevalue.next();
						System.out.println(ItwModule.getId()
								+ "  Module VALUE ");
						ItwProjectModule itwProjectModule = prepareModelItwProjectModule(
								saveItwProjectModuleBean, langId);
						itwProjectModule.setModuleid(ItwModule.getId());

						itwProjectModuleService
								.addItwProjectModule(itwProjectModule);
					}

				}

			} catch (RuntimeException runtimeException) {

				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue(
							"projectname",
							"projectname.unique",
							"Project Name and Module Name combination already in use, enter a different Project Name and Module Name");

					List<ItwModule> ItwModule = itwModuleService
							.getItwModuleByShortName(saveItwProjectModuleBean
									.getModulename());

					Iterator<ItwModule> iterator = ItwModule.iterator();
					while (iterator.hasNext()) {
						ItwModule ItwModule2 = (ItwModule) iterator.next();
						saveItwProjectModuleBean
								.setModuleid(ItwModule2.getId());
					}
					List<ItwProject> newItwProject = itwProjectService
							.getItwProjectByShortName(saveItwProjectModuleBean
									.getProjectname());

					Iterator<ItwProject> itwProject = newItwProject.iterator();
					while (itwProject.hasNext()) {
						ItwProject itwProject1 = (ItwProject) itwProject.next();
						saveItwProjectModuleBean.setProjectid(itwProject1
								.getId());
					}

					List<ItwProjectBean> ItwProjectBean = prepareListofItwProject(itwProjectService
							.listItwProjects(langId));

					List<ItwModuleBean> ItwModuleBean = prepareListofItwModule(
							itwModuleService.listItwModule(langId),
							saveItwProjectModuleBean.getModulename());
					String string = null;
					String temp = new String(
							saveItwProjectModuleBean.getModulename());
					strings = new ArrayList<ItwProjectModuleBean>();
					ItwProjectModuleBean bean = null;
					String[] tempArray = temp.split(",");
					for (int i = 0; i < tempArray.length; i++) {
						bean = new ItwProjectModuleBean();
						string = tempArray[i];
						bean.setModulename(string);
						strings.add(bean);

					}

					model.put("itwProjectName",
							saveItwProjectModuleBean.getProjectname());
					model.put("ItwProject", ItwProjectBean);
					model.put("ItwModule", ItwModuleBean);
					model.put("itwnewProjectModule", strings);

					return new ModelAndView("addItwProjectModules", model);
				}
			}

			String langType = saveItwProjectModuleBean.getLangDesc();

			return new ModelAndView(
					"redirect:/itwProjectModuleConfigarationList.html?langDesc="
							+ langType);
		}

	}

	@RequestMapping(value = "/updateItwProjectModules", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwProjectModuleBean ItwProjectModuleBean,
			BindingResult result, HttpServletRequest request) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();

		ProjectModuleValidator shortsValidator = new ProjectModuleValidator();
		shortsValidator.validate(ItwProjectModuleBean, result);

		Integer value = ItwProjectModuleBean.getId();

		if (result.hasErrors()) {

			if (ItwProjectModuleBean.getProjectname() != null
					&& (ItwProjectModuleBean.getProjectname() == null || ItwProjectModuleBean
							.getProjectname().equals("Select Project"))) {

				List<ItwProjectBean> ItwProjectBean = prepareListofItwProject(
						itwProjectService.listItwProjects(langId),
						ItwProjectModuleBean.getModuleid());

				List<ItwModuleBean> ItwModuleBean = prepareListofItwModule(
						itwModuleService.listItwModule(langId),
						ItwProjectModuleBean.getModuleid());
				String string = null;
				String temp = new String(ItwProjectModuleBean.getProjectname());
				strings = new ArrayList<ItwProjectModuleBean>();
				ItwProjectModuleBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwProjectModuleBean();
					string = tempArray[i];
					bean.setModulename(string);
					strings.add(bean);

				}
				model.put("ItwProjectModule",
						ItwProjectModuleBean.getProjectname());
				model.put("ItwProject", ItwProjectBean);
				model.put("ItwModule", ItwModuleBean);
				model.put("ItwProjectModuleUsers", strings);
			}

			if (!(ItwProjectModuleBean.getProjectname() == null || ItwProjectModuleBean
					.getProjectname().equals("Select Project"))
					&& ItwProjectModuleBean.getProjectname() == null) {

				List<ItwModuleBean> ItwModuleBean = addprepareListofItwModule(itwModuleService
						.listItwModule(langId));

				ItwProjectBean = prepareListofItwProject(
						itwProjectService.listItwProjects(langId),
						ItwProjectModuleBean.getProjectid());

				model.put("ItwProjectModule",
						ItwProjectModuleBean.getProjectname());
				model.put("ItwProject", ItwProjectBean);
				model.put("ItwModule", ItwModuleBean);
				model.put("ItwProjectModuleUsers", strings);

			}
			if (ItwProjectModuleBean.getProjectname().equals("Select Project")
					&& ItwProjectModuleBean.getProjectname() == null) {

				List<ItwProjectBean> ItwProjectBean = prepareListofItwProject(itwProjectService
						.listItwProjects(langId));
				List<ItwModuleBean> ItwModuleBean = addprepareListofItwModule(itwModuleService
						.listItwModule(langId));
				model.put("ItwModule", ItwModuleBean);
				model.put("ItwProject", ItwProjectBean);
			} else {

			}
			return new ModelAndView("addItwProjectModules", model);
		}
		try {
			List<ItwModule> listItwModule1;
			String[] splitIdRoles = ItwProjectModuleBean.getModulename().split(
					",");

			for (int j = 0; j < splitIdRoles.length; j++) {

				listItwModule1 = itwModuleService
						.getItwModuleByShortName(splitIdRoles[j]);
				Iterator<ItwModule> modulevalue = listItwModule1.iterator();

				while (modulevalue.hasNext()) {
					ItwModule ItwModule = (ItwModule) modulevalue.next();
					ItwProjectModule itwProjectModule = prepareModelItwProjectModuleforUpdate(
							ItwProjectModuleBean, langId);
					itwProjectModule.setModuleid(ItwModule.getId());
					itwProjectModuleService
							.addItwProjectModule(itwProjectModule);
				}

			}
		}

		catch (RuntimeException runtimeException) {

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				result.rejectValue(
						"projectname",
						"projectname.unique",
						"Project Name and Module Name combination already in use, enter a different Project Name and Module Name");

				ItwProjectModule newItwProjectModule = itwProjectModuleService
						.getItwProjectModule(ItwProjectModuleBean.getId());
				List<ItwModule> ItwModule = itwModuleService
						.getItwModuleByShortName(ItwProjectModuleBean
								.getProjectname());

				System.out.println(ItwProjectModuleBean.getProjectname());
				Iterator<ItwModule> iterator = ItwModule.iterator();
				while (iterator.hasNext()) {
					ItwModule ItwModule2 = (ItwModule) iterator.next();
					newItwProjectModule.setModuleid(ItwModule2.getId());
				}

				ItwProjectModuleBean newUserBean = prepareItwProjectModuleBean(newItwProjectModule);
				newUserBean.setId(value);
				model.put("itwClient", newUserBean);

				List<ItwModuleBean> ItwModuleBean = prepareListofItwModule(
						itwModuleService.listItwModule(langId),
						ItwProjectModuleBean.getModulename());

				String string = null;
				String temp = new String(ItwProjectModuleBean.getProjectname());
				strings = new ArrayList<ItwProjectModuleBean>();
				ItwProjectModuleBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwProjectModuleBean();
					string = tempArray[i];
					bean.setModulename(string);
					strings.add(bean);

				}

				model.put("itwProjectModule", ItwProjectModuleBean);
				model.put("ItwProject", ItwProjectBean);
				model.put("ItwModule", ItwModuleBean);
				model.put("itwnewProjectModule", strings);
				return new ModelAndView("editItwProjectModules", model);
			}
		}
		return new ModelAndView(
				"redirect:/itwProjectModuleConfigarationList.html");
	}

	@RequestMapping(value = "/addItwProjectModules", method = RequestMethod.GET)
	public ModelAndView addItwProjectModule(
			@ModelAttribute("command") ItwProjectModuleBean ItwProjectModuleBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwProjectModule");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwProjectBean> ItwProjectBean = addprepareListofItwProject(itwProjectService
				.listItwProjects(langId));
		List<ItwModuleBean> ItwModuleBean = addprepareListofItwModule(itwModuleService
				.listItwModule(langId));


		if (ItwProjectBean != null) {
			model.put("ItwProject", ItwProjectBean);
		}
		else
		{
			model.put("ItwProject", null);
		}
		if (ItwModuleBean != null) {
			model.put("ItwModule", ItwModuleBean);
		}
		else
		{
			model.put("ItwModule", null);
		}
		if (ItwProjectBean== null) {
			
		}
		if (ItwModuleBean == null) {
			
		}

		return new ModelAndView("addItwProjectModules", model);

	}

	@RequestMapping(value = "/deleteItwProjectModules", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwProjectModuleBean ItwProjectModuleBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();

		String shortNametemp = "ProjectModule ID "
				+ ItwProjectModuleBean.getId()
				+ " already in use, cannot delete";

		// try{
		itwProjectModuleService
				.deleteItwProjectModule(prepareModelItwProjectModule(
						ItwProjectModuleBean, langId));

		return new ModelAndView(
				"redirect:/itwProjectModuleConfigarationList.html");

	}

	@RequestMapping(value = "/editItwProjectModules", method = RequestMethod.GET)
	public ModelAndView editItwProjectModule(
			@ModelAttribute("command") ItwProjectModuleBean newItwProjectModuleBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwProjectModule",
				prepareItwProjectModuleBean(itwProjectModuleService
						.getItwProjectModule(newItwProjectModuleBean.getId())));

		model.put("ItwModule", ItwModuleBean);
		model.put("ItwProject", ItwProjectBean);
		return new ModelAndView("editItwProjectModules", model);
	}

	private ItwProjectModule prepareModelItwProjectModule(
			ItwProjectModuleBean itwProjectModuleBean, int langId) {

		ItwProjectModule itwProjectModule = new ItwProjectModule();
		List<ItwProject> listItwProject = itwProjectService
				.getItwProjectByShortName(itwProjectModuleBean.getProjectname());
		Iterator<ItwProject> iterator = listItwProject.iterator();
		while (iterator.hasNext()) {
			ItwProject ItwProject = (ItwProject) iterator.next();
			itwProjectModule.setProjectid(ItwProject.getId());
		}

		List<ItwModule> listItwModule = itwModuleService
				.getItwModuleByShortName(itwProjectModuleBean.getModulename());
		Iterator<ItwModule> roleiterator = listItwModule.iterator();
		while (roleiterator.hasNext()) {
			ItwModule itwRole = (ItwModule) roleiterator.next();
			itwProjectModule.setModuleid(itwRole.getId());
		}
		itwProjectModule.setId(itwProjectModuleBean.getId());
		itwProjectModule.setLangId(langId);
		itwProjectModuleBean.setId(itwProjectModuleBean.getId());
		return itwProjectModule;
	}

	private ItwProjectModule prepareModelItwProjectModuleforUpdate(
			ItwProjectModuleBean itwProjectModuleBean, int langId) {
		ItwProjectModule itwProjectModule = new ItwProjectModule();

		List<ItwProject> listItwProject = itwProjectService
				.getItwProjectByShortName(itwProjectModuleBean.getProjectname());
		Iterator<ItwProject> iterator = listItwProject.iterator();
		while (iterator.hasNext()) {
			ItwProject ItwProject = (ItwProject) iterator.next();
			itwProjectModule.setProjectid(ItwProject.getId());
		}

		List<ItwModule> listItwModule = itwModuleService
				.getItwModuleByShortName(itwProjectModuleBean.getProjectname());
		Iterator<ItwModule> roleiterator = listItwModule.iterator();
		while (iterator.hasNext()) {
			ItwModule itwRole = (ItwModule) roleiterator.next();
			itwProjectModule.setModuleid(itwRole.getId());
		}
		itwProjectModule.setId(itwProjectModuleBean.getId());
		itwProjectModule.setLangId(langId);
		itwProjectModuleBean.setId(itwProjectModuleBean.getId());
		return itwProjectModule;
	}

	private List<ItwProjectModuleBean> prepareListofItwProjectModule(
			List<ItwProjectModule> itwProjectModuleBean) {
		List<ItwProjectModuleBean> beans = null;
		if (itwProjectModuleBean != null && !itwProjectModuleBean.isEmpty()) {
			beans = new ArrayList<ItwProjectModuleBean>();
			ItwProjectModuleBean bean = null;
			for (ItwProjectModule itwProjectModule : itwProjectModuleBean) {
				bean = new ItwProjectModuleBean();
				bean.setId(itwProjectModule.getId());
				ItwModule Module = new ItwModule();
				roles = new ArrayList<ItwModule>();
				Module = itwModuleService.getItwModule(itwProjectModule
						.getModuleid());
				ItwProject listItwProject = itwProjectService
						.getItwProject(itwProjectModule.getProjectid());
				bean.setModulename(Module.getShortname());
				bean.setLangId(itwProjectModule.getLangId());
				bean.setProjectname(listItwProject.getShortname());
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwProjectModuleBean prepareItwProjectModuleBean(
			ItwProjectModule itwProjectModule) {
		ItwProjectModuleBean bean = new ItwProjectModuleBean();
		bean = new ItwProjectModuleBean();
		bean.setId(itwProjectModule.getId());
		ItwModule Role = new ItwModule();
		roles = new ArrayList<ItwModule>();
		Role = itwModuleService.getItwModule(itwProjectModule.getModuleid());
		bean.setModuleid(Role.getId());
		bean.setLangId(itwProjectModule.getLangId());
		bean.setProjectid(itwProjectModule.getProjectid());
		ItwProject listItwProject = itwProjectService
				.getItwProject(itwProjectModule.getProjectid());

		ItwModuleBean = prepareListofItwModule(
				itwModuleService.listItwModule(bean.getLangId()),
				bean.getModuleid());

		ItwProjectBean = prepareListofItwProject(
				itwProjectService.listItwProjects(bean.getLangId()),
				bean.getProjectid());

		bean.setProjectname(listItwProject.getShortname());
		bean.setModulename(Role.getShortname());

		return bean;
	}

	private List<ItwProjectBean> prepareListofItwProject(
			List<ItwProject> ItwProjects, Integer temp) {

		List<ItwProjectBean> beans = null;
		if (ItwProjects != null && !ItwProjects.isEmpty()) {
			beans = new ArrayList<ItwProjectBean>();
			ItwProjectBean bean = null;
			for (ItwProject ItwProject : ItwProjects) {
				bean = new ItwProjectBean();

				if (!(temp != null && temp.equals(ItwProject.getId()))) {
					bean.setId(ItwProject.getId());
					bean.setShortname(ItwProject.getShortname());
					bean.setLangId(ItwProject.getLangId());
					beans.add(bean);
				}

			}
		}
		return beans;
	}

	private List<ItwModuleBean> prepareListofItwModule(
			List<ItwModule> ItwModules, Integer temp) {
		List<ItwModuleBean> beans = null;
		if (ItwModules != null && !ItwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule ItwModule : ItwModules) {
				bean = new ItwModuleBean();

				if (!(temp != null && temp.equals(ItwModule.getId()))) {
					bean.setId(ItwModule.getId());

					bean.setShortname(ItwModule.getShortname());
					bean.setLangid(ItwModule.getLangid());
					beans.add(bean);

				}
			}
		}
		return beans;
	}

	@RequestMapping(value = "/downloadItwProjectModule", method = RequestMethod.GET)
	public String getDownloadPage() {
		return "downloadpage";
	}

	private List<ItwModuleBean> addprepareListofItwModule(
			List<ItwModule> ItwModules) {
		List<ItwModuleBean> beans = null;
		if (ItwModules != null && !ItwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule ItwModule : ItwModules) {
				bean = new ItwModuleBean();
				bean.setId(ItwModule.getId());
				bean.setShortname(ItwModule.getShortname());
				bean.setLangid(ItwModule.getLangid());
				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwProjectBean> addprepareListofItwProject(
			List<ItwProject> ItwProjects) {
		List<ItwProjectBean> beans = null;
		if (ItwProjects != null && !ItwProjects.isEmpty()) {
			beans = new ArrayList<ItwProjectBean>();
			ItwProjectBean bean = null;
			for (ItwProject ItwProject : ItwProjects) {
				bean = new ItwProjectBean();
				bean.setId(ItwProject.getId());
				bean.setShortname(ItwProject.getShortname());
				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwProjectBean> prepareListofItwProject(
			List<ItwProject> ItwProjects) {
		List<ItwProjectBean> beans = null;
		if (ItwProjects != null && !ItwProjects.isEmpty()) {
			beans = new ArrayList<ItwProjectBean>();
			ItwProjectBean bean = null;
			for (ItwProject ItwProject : ItwProjects) {
				bean = new ItwProjectBean();
				bean.setId(ItwProject.getId());
				bean.setShortname(ItwProject.getShortname());
				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwModuleBean> prepareListofItwModule(
			List<ItwModule> ItwModules) {
		List<ItwModuleBean> beans = null;
		if (ItwModules != null && !ItwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule ItwModule : ItwModules) {
				bean = new ItwModuleBean();
				bean.setId(ItwModule.getId());
				bean.setShortname(ItwModule.getShortname());

				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwModuleBean> prepareListofItwModule(
			List<ItwModule> ItwModules, String temp) {
		List<ItwModuleBean> beans = null;
		if (ItwModules != null && !ItwModules.isEmpty()) {
			beans = new ArrayList<ItwModuleBean>();
			ItwModuleBean bean = null;
			for (ItwModule ItwModule : ItwModules) {
				bean = new ItwModuleBean();

				if (!(temp != null && temp.hashCode()==ItwModule.getShortname().hashCode())) {
					bean.setId(ItwModule.getId());

					bean.setShortname(ItwModule.getShortname());
					bean.setLangid(ItwModule.getLangid());
					beans.add(bean);
				}
			}
		}
		return beans;
	}

}
