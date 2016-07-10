package com.agileidc.itw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwSeverityBean;
import com.agileidc.itw.dao.ItwSeverityDAOTemp;
import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwSeverityColour;
import com.agileidc.itw.service.ItwSeverityColourService;
import com.agileidc.itw.service.ItwSeverityService;
import com.agileidc.itw.web.IdList;
import com.agileidc.itw.web.SeverityValidator;

@Controller
public class ItwSeverityController {

	@Autowired
	private ItwSeverityService itwSeverityService;
	@Autowired
	private ItwSeverityDAOTemp itwSeverityDAOTemp;

	@Autowired
	private ItwSeverityColourService itwSeverityColourService;

	@RequestMapping(value = "/saveItwSeverity", method = RequestMethod.POST)
	public ModelAndView saveItwSeverity(
			@ModelAttribute("command") ItwSeverityBean saveitwSeverityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		SeverityValidator SeverityValidator = new SeverityValidator();
		SeverityValidator.validate(saveitwSeverityBean, result);
		if (result.hasErrors()) {
			
			List<ItwSeverityColour> itwSeverityColourList = itwSeverityColourService
					.listItwSeverityColours(langId);

			List<IdList> itwcolourCodeIdList = new ArrayList<IdList>();
			for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
				IdList idList = new IdList();
				idList.setId(itwSeverityColour.getId());
				idList.setValue(itwSeverityColour.getShortname());

				itwcolourCodeIdList.add(idList);

			}
			saveitwSeverityBean.setItwcolorcodeidList(itwcolourCodeIdList);
					
			
			model.put(
					"itwSeverityBean",saveitwSeverityBean);
			
			
						
			return new ModelAndView("addItwSeverity",model);
		
		} else {
			try {

				ItwSeverity itwSeverity = prepareModel(saveitwSeverityBean, request);

				itwSeverity.setLangId(langId);
				itwSeverity.setColorcodeid(saveitwSeverityBean.getColourCodeId());

				itwSeverityService.addItwSeverity(itwSeverity);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Severity  is already in use, enter a different Severity");
					

					
					
					return new ModelAndView("addItwSeverity", model);
				}

			}
			return new ModelAndView("redirect:/itwSeverityConfigList.html");
		}

	}

	@RequestMapping(value = "/itwSeveritys", method = RequestMethod.GET)
	public ModelAndView listItwSeveritys() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("itwSeverityConfigListPage", model);
	}

	@RequestMapping(value = "/addItwSeverity", method = RequestMethod.GET)
	public ModelAndView addItwSeverity(
			@ModelAttribute("command") ItwSeverityBean itwSeverityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwSeverityColour> itwSeverityColourList = itwSeverityColourService
				.listItwSeverityColours(langId);

		List<IdList> itwcolourCodeIdList = new ArrayList<IdList>();
		for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
			IdList idList = new IdList();
			idList.setId(itwSeverityColour.getId());
			idList.setValue(itwSeverityColour.getShortname());

			itwcolourCodeIdList.add(idList);

		}
		itwSeverityBean.setItwcolorcodeidList(itwcolourCodeIdList);

		model.put("itwSeverity", itwSeverityBean);

		return new ModelAndView("addItwSeverity", model);
	}

	@RequestMapping(value = "/editItwSeverity", method = RequestMethod.GET)
	public ModelAndView editItwSeverity(
			@ModelAttribute("command") ItwSeverityBean itwSeverityBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put(
				"itwSeverity",
				prepareItwSeverityBean(itwSeverityService
						.getItwSeverity(itwSeverityBean.getId()), request));

		return new ModelAndView("editItwSeverity", model);
	}

	@RequestMapping(value = "/updateItwSeverity", method = RequestMethod.POST)
	public ModelAndView updateSeverity(
			@ModelAttribute("command") ItwSeverityBean updateitwSeverityBean,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		
		System.out.println(updateitwSeverityBean.getSla()+"            ");

		
		SeverityValidator SeverityValidator = new SeverityValidator();
		SeverityValidator.validate(updateitwSeverityBean, result);
		if (result.hasErrors()) {
			Integer colorid = null;
			List<ItwSeverityColour> itwSeverityColourList = itwSeverityColourService
					.listItwSeverityColours(langId);
			for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
			
				if (itwSeverityColour.getId().hashCode()==updateitwSeverityBean.getColourCodeId().hashCode()){
	
					colorid=itwSeverityColour.getId();
				}
			} 
			ItwSeverityBean bean=prepareItwSeverityBean(itwSeverityService
					.getItwSeverity(updateitwSeverityBean.getId()), request);
			
					
					bean.setShortname(updateitwSeverityBean.getShortname());
			bean.setSla(updateitwSeverityBean.getSla());
			model.put(
					"itwSeverity",bean);
			
			model.put("colourcodeiddisplaytemp", colorid);
						
			return new ModelAndView("editItwSeverity",model);
		
			
		}else {
		
		ItwSeverity itwSeverity = prepareModelItwSeverityforUpdate(updateitwSeverityBean);
	
		itwSeverity.setLangId(langId);
		

			try {

			
				itwSeverityService.addItwSeverity(itwSeverity);

			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Severity already in use, enter a different Severity");
					
					ItwSeverityBean itwSeverityBean1 = prepareItwSeverityBeanForAdd();
					model.remove("itwPlatForms1");
					model.put("itwPlatForms1", itwSeverityBean1);
					return new ModelAndView("editItwSeverity", model);
				}
			}
		}
		return new ModelAndView("redirect:/itwSeverityConfigList.html");
	}

	@RequestMapping(value = "/itwSeverityConfigList", method = RequestMethod.GET)
	public ModelAndView SeverityConfigList(HttpServletRequest request) {
		System.out.println("started Severity config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwSeverityBean> itwSeverityBean = prepareListofItwSeveritys(
				itwSeverityService.listItwSeveritys(), request);
		java.util.Collections.sort(itwSeverityBean,
				ItwSeverityBean.Comparators.ID);
		if (itwSeverityBean != null) {
			java.util.Collections.sort(itwSeverityBean,
					ItwSeverityBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwSeverityBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("itwSeverityConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwSeverity", method = RequestMethod.GET)
	public ModelAndView deleteItwSeverity(
			@ModelAttribute("command") ItwSeverityBean itwSeverityBean,
			BindingResult result, HttpServletRequest request) {
		String SeverityShortnametemp = "Severity ID " + itwSeverityBean.getId()
				+ " already in use, cannot delete";
		try {
			System.out.println("Run Time");
			itwSeverityService
					.deleteItwSeverity(prepareModelItwSeverityforDelete(itwSeverityBean));
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
				List<ItwSeverityBean> itwSeverityBeanList = prepareListofItwSeveritys(
						itwSeverityService.listItwSeveritys(), request);
				if (itwSeverityBean != null) {
					java.util.Collections.sort(itwSeverityBeanList,
							ItwSeverityBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwSeverityBeanList);
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
						SeverityShortnametemp);
				return new ModelAndView("itwSeverityConfigListPage", model);
			}
			System.out.println("Out of if ");

		}

		return new ModelAndView("redirect:/itwSeverityConfigList.html");
	}

	private ItwSeverity prepareModel(ItwSeverityBean itwSeverityBean,
			HttpServletRequest request) {

		ItwSeverity itwSeverity = new ItwSeverity();

		itwSeverity.setShortname(itwSeverityBean.getShortname());
		itwSeverity.setId(itwSeverityBean.getId());
		itwSeverity.setColorcode(itwSeverityBean.getColorcode());
		itwSeverity.setSla(itwSeverityBean.getSla());
		return itwSeverity;
	}

	private ItwSeverityBean prepareItwSeverityBean(ItwSeverity itwSeverity,
			HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		ItwSeverityBean bean = new ItwSeverityBean();

		bean.setShortname(itwSeverity.getShortname());
		bean.setId(itwSeverity.getId());
		bean.setColorcode(itwSeverity.getColorcode());

		List<ItwSeverityColour> itwSeverityColourList = itwSeverityColourService
				.listItwSeverityColours(langId);
		String colorcodename = null;

		for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
			if (itwSeverityColour.getId().intValue() == new Integer(
					itwSeverity.getColorcodeid()).intValue()) {
				colorcodename = itwSeverityColour.getShortname();
			}
		}
		bean.setColorcodename(colorcodename);

		List<IdList> itwcolourCodeIdList = new ArrayList<IdList>();
		for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
			IdList idList = new IdList();
			idList.setId(itwSeverityColour.getId());
			idList.setValue(itwSeverityColour.getShortname());

			itwcolourCodeIdList.add(idList);

		}
		bean.setItwcolorcodeidList(itwcolourCodeIdList);
		bean.setSla(itwSeverity.getSla());
		bean.setColourCodeId(itwSeverity.getColorcodeid());

		return bean;
	}

	private List<ItwSeverityBean> prepareListofItwSeveritys(
			List<ItwSeverity> itwSeveritys, HttpServletRequest request) {
		List<ItwSeverityBean> beans = null;
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwSeverityColour> itwSeverityColourList = itwSeverityColourService
				.listItwSeverityColours(langId);
		if (itwSeveritys != null && !itwSeveritys.isEmpty()) {

			beans = new ArrayList<ItwSeverityBean>();
			ItwSeverityBean bean = null;
			for (ItwSeverity itwSeverity : itwSeveritys) {

				bean = new ItwSeverityBean();
				bean.setId(itwSeverity.getId());

				bean.setShortname(itwSeverity.getShortname());

				bean.setColorcode(itwSeverity.getColorcode());
				String colorCodename = null;
				for (ItwSeverityColour itwSeverityColour : itwSeverityColourList) {
					if (itwSeverityColour.getId().intValue() == new Integer(
							itwSeverity.getColorcodeid()).intValue()) {
						colorCodename = itwSeverityColour.getShortname();
					}
				}
				bean.setColorcodename(colorCodename);

				bean.setIconId(itwSeverity.getIconId());
				bean.setSla(itwSeverity.getSla());
				beans.add(bean);

			}
		}
		return beans;

	}

	private ItwSeverity prepareModelItwSeverityforUpdate(
			ItwSeverityBean itwSeverityBean) {
		System.out.println("Inside Update");

		ItwSeverity itwSeverity = new ItwSeverity();

		itwSeverity.setId(itwSeverityBean.getId());
		itwSeverity.setSla(itwSeverityBean.getSla());
		itwSeverity.setShortname(itwSeverityBean.getShortname());
		itwSeverity.setColorcode(itwSeverityBean.getColorcode());
		itwSeverity.setColorcodeid(itwSeverityBean.getColourCodeId());
		itwSeverity.setIconId(itwSeverityBean.getIconId());

		return itwSeverity;
	}

	private ItwSeverity prepareModelItwSeverityforDelete(
			ItwSeverityBean itwSeverityBean) {
		System.out.println("Bean Id is =========" + itwSeverityBean.getId());

		ItwSeverity itwSeverity = new ItwSeverity();
		itwSeverity.setId(itwSeverityBean.getId());
		System.out.println("Severity Id is =========" + itwSeverity.getId());

		return itwSeverity;
	}

	private ItwSeverityBean prepareItwSeverityBeanForAdd() {
		ItwSeverityBean bean = new ItwSeverityBean();

		return bean;
	}
}
