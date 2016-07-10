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

import net.sf.jasperreports.engine.JRDataSource;

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
import com.agileidc.itw.bean.ItwStatusTypesBean;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwModuleService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.web.ItwModuleValidator;

@Controller
public class ItwModuleController {

	@Autowired
	private ItwModuleService itwModuleService;
	@Autowired
	private ItwProjectService itwProjectService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;

	@RequestMapping(value = "/itwModuleConfigList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {
		System.out.println("started Module config List page");
		Map<String, Object> model = new HashMap<String, Object>();

		//String langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute(
				"langId");
		List<ItwModuleBean> itwModuleBean = prepareListofItwModule(itwModuleService
				.listItwModule(langId));
		if (itwModuleBean != null) {
			
			PagedListHolder pagedListHolder = new PagedListHolder(itwModuleBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);

		} else {
			model.put("pagedListHolder", null);
		}
		return new ModelAndView("itwModuleConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwModule", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result, HttpServletRequest request) {
		ItwModuleValidator shortsValidator = new ItwModuleValidator();
		shortsValidator.validate(itwModuleBean, result);

		if (result.hasErrors()) {
			return new ModelAndView("addItwModule");
		} else {
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			ItwModule itwModule = prepareModelItwModule(itwModuleBean);
			itwModule.setLangid(langId.intValue());
			try {
				itwModuleService.addItwModule(itwModule);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate shortname is being added for a new module in
				// table ITW_MODULE
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Module  is already in use, enter a different Module");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwModuleBean itwModuleBean1 = prepareItwModuleBeanForAdd();

					model.remove("itwModule1");
					model.put("itwModule1", itwModuleBean1);
					return new ModelAndView("addItwModule", model);
				}
			}

			return new ModelAndView(
					"redirect:/itwModuleConfigList.html");
		}

	}

	private ItwModuleBean prepareItwModuleBeanForAdd() {
		ItwModuleBean bean = new ItwModuleBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwModule", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result, HttpServletRequest request) {
		ItwModuleValidator shortsValidator = new ItwModuleValidator();
		shortsValidator.validate(itwModuleBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwModule");
		} else {
			ItwModule itwModuletemp = itwModuleService
					.getItwModule(itwModuleBean.getId());
			String tempShortname = itwModuleBean.getShortname();
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwModule itwModule = prepareModelItwModuleforUpdate(itwModuleBean);
				itwModule.setLangid(langId.intValue());
				itwModuleService.addItwModule(itwModule);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate module shortname is being added for a existing module in
				// table ITW_MODULE
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Module shortname already in use, enter a different Module");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwModuleBean tempBean = prepareItwModuleBean(itwModuleService.getItwModule(itwModuletemp.getId()));
					
					tempBean.setShortname(tempShortname);
					//ItwModuleBean itwModuleBean1 = prepareItwModuleBeanForAdd();
					model.remove("itwModule");
					model.put("itwModule", tempBean);
					return new ModelAndView("editItwModule", model);
				}
			}
			return new ModelAndView(
					"redirect:/itwModuleConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwModule", method = RequestMethod.GET)
	public ModelAndView addItwModule(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwModule");

		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("addItwModule", model);

	}

	@RequestMapping(value = "/deleteItwModule", method = RequestMethod.GET)
	public ModelAndView deleteItwModule(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result, HttpServletRequest request) {

	//	String langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		//itwModuleBean.setLangType(langDesc);
		String shortNametemp = "Module ID " + itwModuleBean.getId()
				+ " already in use, cannot delete";

		//langDesc = request.getParameter("langDesc");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwProjectBean> itwProjectBean = prepareListofItwProjects(itwProjectService
				.listItwProjects(langId));
		boolean setValue = false;
		
		try {
			
				itwModuleService
						.deleteItwModule(prepareModelItwModule(itwModuleBean));
			
		} catch (RuntimeException runtimeException) {
			// TODO: handle exception

			System.out.println("entered runtime exception catch block 1");
		
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model1 = new HashMap<String, Object>();
				List<ItwModuleBean> itwModuleBeanList = prepareListofItwModule(itwModuleService
						.listItwModule(langId));
				if (itwModuleBean != null) {
					// java.util.Collections.sort(itwModuleBeanList,
					// ItwModuleBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwModuleBeanList);
					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);
					
					pagedListHolder.setPage(page);

					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);

					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}
			

			System.out.println(shortNametemp);
			result.rejectValue("shortname", "shortname.inuse", shortNametemp);
			/*return new ModelAndView(
					"redirect:/itwModuleConfigList.html",
					model);*/
			return new ModelAndView("itwModuleConfigListPage", model);

		}
		return new ModelAndView("redirect:/itwModuleConfigList.html"
				);
	}

	@RequestMapping(value = "/editItwModule", method = RequestMethod.GET)
	public ModelAndView editItwModule(
			@ModelAttribute("command") ItwModuleBean itwModuleBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwModule", prepareItwModuleBean(itwModuleService
				.getItwModule(itwModuleBean.getId())));

		return new ModelAndView("editItwModule", model);
	}

	private ItwModule prepareModelItwModule(ItwModuleBean itwModuleBean) {

		ItwModule itwModule = new ItwModule();
		itwModule.setShortname(itwModuleBean.getShortname());
		itwModule.setId(itwModuleBean.getId());
				itwModuleBean.setId(null);
				return itwModule;
	}

	private ItwModule prepareModelItwModuleforUpdate(ItwModuleBean itwModuleBean) {
		ItwModule itwModule = new ItwModule();

		itwModule.setId(itwModuleBean.getId());
		itwModule.setShortname(itwModuleBean.getShortname());
		
		itwModuleBean.setId(null);

		return itwModule;
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

	private ItwModuleBean prepareItwModuleBean(ItwModule itwModule) {
		ItwModuleBean bean = new ItwModuleBean();
		bean.setShortname(itwModule.getShortname());
		bean.setLangid(itwModule.getLangid());
		bean.setId(itwModule.getId());
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

	@RequestMapping(value = "/downloadItwModulexls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView) {

		System.out.println("----------1----------------");
		JRDataSource datasource = itwModuleService.getDataSource();
		System.out.println("----------2----------------");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", datasource);
		System.out.println("----------3----------------");
		modelAndView = new ModelAndView("xlsItwModuleReport", parameterMap);
		System.out.println("----------4----------------");
		return modelAndView;
	}

	@RequestMapping(value = "/downloadItwModulepdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView) {

		JRDataSource datasource = itwModuleService.getDataSource();

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", datasource);

		modelAndView = new ModelAndView("pdfItwModuleReport", parameterMap);

		return modelAndView;
	}

	@RequestMapping(value = "/downloadItwModule", method = RequestMethod.GET)
	public String getDownloadPage() {

		return "downloadItwModulepage";
	}
}
