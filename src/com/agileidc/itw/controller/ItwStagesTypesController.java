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

import com.agileidc.itw.bean.ItwStagesTypesBean;
import com.agileidc.itw.bean.ItwStagesTypesBean;
import com.agileidc.itw.helper.DefaultValidException;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwStagesTypesService;
import com.agileidc.itw.web.ItwStagesTypesValidator;
import com.agileidc.itw.web.ItwStagesTypesValidator;

@Controller
public class ItwStagesTypesController {
	@Autowired
	private ItwStagesTypesService itwStagesService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;
	List<String> listofPreceeding = null;

	@RequestMapping(value = "/itwStagesTypesConfigList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		System.out.println(langId + "  LANG ID  ");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwStagesTypesBean> itwStagesBean = prepareListofItwStagesTypes(
				itwStagesService.listItwStagesTypes(langId), langId);

		if (itwStagesBean != null) {

			PagedListHolder pagedListHolder = new PagedListHolder(itwStagesBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		}

		else {

			model.put("pagedListHolder", null);

		}
		return new ModelAndView("itwStagesTypesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwStagesTypes", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwStagesTypesBean saveitwStagesBean,
			BindingResult result, HttpServletRequest request) {
		boolean defaultvalue = false;
		String tempdefaultValue = saveitwStagesBean.getDefaultvalue();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwStagesTypesValidator shortsValidator = new ItwStagesTypesValidator();
		shortsValidator.validate(saveitwStagesBean, result);
		Map<String, Object> model = new HashMap<String, Object>();

		if (result.hasErrors()) {

			model.put("itwStagesTypes", saveitwStagesBean);

			model.put(
					"itwStagesTypeslist",
					prepareListofItwStagesTypes(
							itwStagesService.listItwStagesTypes(langId), langId));
			List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
					itwStagesService.listItwStagesTypes(langId), langId);
			model.put("stagesname", saveitwStagesBean.getPrecedingname());
			if (itwTempStagesBeanList != null) {
				model.put("preceding", itwTempStagesBeanList);

			} else {
				model.put("preceding", null);
			}

			return new ModelAndView("addItwStagesTypes", model);
		} else {

			ItwStagesTypes itwStages = prepareModelItwStagesTypes(
					saveitwStagesBean, langId);
			try {

				List<ItwStagesTypesBean> newitwStagesBean = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				if (newitwStagesBean != null) {
					Iterator<ItwStagesTypesBean> iterator = newitwStagesBean
							.iterator();
					while (iterator.hasNext()) {
						ItwStagesTypesBean newitwStagesTypesBean = (ItwStagesTypesBean) iterator
								.next();

						if (newitwStagesTypesBean.getDefaultvalue().equals("Y")
								&& tempdefaultValue.equals("Y")) {
							defaultvalue = true;
						}
					}
				}
				if (defaultvalue) {

					try {

						throw new DefaultValidException();
					} catch (DefaultValidException e) {
						result.rejectValue("defaultValue",
								"defaultValue.unique",
								"Already Y is present in database ,Choose N ");
						List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
								itwStagesService.listItwStagesTypes(langId),
								langId);

						if (itwTempStagesBeanList != null) {
							model.put("preceding", itwTempStagesBeanList);

						} else {
							model.put("preceding", null);
						}
						model.put(
								"itwStagesTypeslist",
								prepareListofItwStagesTypes(itwStagesService
										.listItwStagesTypes(langId), langId));
						return new ModelAndView("addItwStagesTypes", model);
					}

				}

				itwStagesService.addItwStagesTypes(itwStages);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS

				System.out.println(runtimeException.toString()
						+ " EXCEPTION OCCURED ");
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Stages  is already in use, enter a different Stages");

					ItwStagesTypesBean itwStagesBean1 = prepareItwStagesTypesBeanForAdd();
					model.put("itwStagesTypes", saveitwStagesBean);

					model.put(
							"itwStagesTypeslist",
							prepareListofItwStagesTypes(
									itwStagesService.listItwStagesTypes(langId),
									langId));
					List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
							itwStagesService.listItwStagesTypes(langId), langId);
					model.put("stagesname",
							saveitwStagesBean.getPrecedingname());
					if (itwTempStagesBeanList != null) {
						model.put("preceding", itwTempStagesBeanList);

					} else {
						model.put("preceding", null);
					}

					model.remove("itwStages1");
					model.put("itwStages1", itwStagesBean1);
					return new ModelAndView("addItwStagesTypes", model);
				}
			}
		}
		return new ModelAndView("redirect:/itwStagesTypesConfigList.html");

	}

	private ItwStagesTypesBean prepareItwStagesTypesBeanForAdd() {
		ItwStagesTypesBean bean = new ItwStagesTypesBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwStagesTypes", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwStagesTypesBean updateitwStagesBean,
			BindingResult result, HttpServletRequest request) {
		List<String> stringStages = new ArrayList<String>();
		List<String> tempStages = new ArrayList<String>();
		boolean defaultvalue = false;
		String tempdefaultValue = updateitwStagesBean.getDefaultvalue();
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println(updateitwStagesBean.getId());
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwStagesTypesValidator shortsValidator = new ItwStagesTypesValidator();
		shortsValidator.validateStage(updateitwStagesBean, result);
		if (result.hasErrors()) {

			if (updateitwStagesBean.getPrecedingname() == null) {

				model.put(
						"itwStagesTypeslist",
						prepareListofItwStagesTypes(
								itwStagesService.listItwStagesTypes(langId),
								langId));
				List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				if (itwTempStagesBeanList != null) {

					model.put("newpreceding", itwTempStagesBeanList);

				} else {
					model.put("newpreceding", null);
				}

			}
			if (updateitwStagesBean.getPrecedingname() != null) {
				String Stages = updateitwStagesBean.getPrecedingname();
				System.out.println(Stages + "Stages");

				ItwStagesTypesBean bean = prepareItwStagesTypesBean(
						itwStagesService.getItwStagesTypes(updateitwStagesBean
								.getId()), langId);

				List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				StringBuffer temp = new StringBuffer();
				Iterator<ItwStagesTypesBean> iterator = itwTempStagesBeanList
						.iterator();
				while (iterator.hasNext()) {
					ItwStagesTypesBean itwStagesTypesBean2 = (ItwStagesTypesBean) iterator
							.next();
					temp.append(",").append(itwStagesTypesBean2.getShortname())
							.append(",");

				}

				String[] StagesSplit = Stages.split(",");
				for (int i = 0; i < StagesSplit.length; i++) {
					String string = StagesSplit[i];
					temp.delete(temp.indexOf(string), temp.indexOf(string)
							+ string.length());
					stringStages.add(string);

				}

				String[] strings = temp.toString().split(",");
				for (int i = 0; i < strings.length; i++) {
					if (!strings[i].equals("")) {
						String string = strings[i];
						tempStages.add(string);
					}
				}

				model.put("itwStagesTypes", bean);
				model.put("tempitwStagesTypes", tempStages);
				model.put("itwpreceding", stringStages);
				model.put("itwStagesTypes", bean);
				model.put("tempitwStagesTypes", tempStages);
			}

			model.put("itwStagesTypes", updateitwStagesBean);
			return new ModelAndView("editItwStagesTypes", model);
		} else {
			try {

				List<ItwStagesTypesBean> newitwStagesBean = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				Iterator<ItwStagesTypesBean> iterator = newitwStagesBean
						.iterator();
				while (iterator.hasNext()) {
					ItwStagesTypesBean newitwStagesTypesBean = (ItwStagesTypesBean) iterator
							.next();

					if (newitwStagesTypesBean.getDefaultvalue().equals("Y")
							&& tempdefaultValue.equals("Y")) {
						defaultvalue = true;

					}

				}
				if (defaultvalue) {

					try {

						throw new DefaultValidException();
					} catch (DefaultValidException e) {
						result.rejectValue("defaultValue",
								"defaultValue.unique",
								"Already Y is present in database ,Choose N ");

						ItwStagesTypesBean itwStagesBean1 = prepareItwStagesTypesBeanForAdd();
						model.put(
								"itwStagesTypeslist",
								prepareListofItwStagesTypes(itwStagesService
										.listItwStagesTypes(langId), langId));
						model.put("itwStagesTypes", updateitwStagesBean);

						List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
								itwStagesService.listItwStagesTypes(langId),
								langId);
						model.put("stagesname",
								updateitwStagesBean.getPrecedingname());

						String Stages = updateitwStagesBean.getPrecedingname();
						System.out.println(Stages + "Stages");
						ItwStagesTypesBean bean = prepareItwStagesTypesBean(
								itwStagesService.getItwStagesTypes(updateitwStagesBean
										.getId()), langId);

						StringBuffer temp = new StringBuffer();
						Iterator<ItwStagesTypesBean> newiterator = itwTempStagesBeanList
								.iterator();
						while (newiterator.hasNext()) {
							ItwStagesTypesBean itwStagesTypesBean2 = (ItwStagesTypesBean) newiterator
									.next();
							temp.append(",")
									.append(itwStagesTypesBean2.getShortname())
									.append(",");

						}

						String[] StagesSplit = Stages.split(",");
						for (int i = 0; i < StagesSplit.length; i++) {
							String string = StagesSplit[i];
							temp.delete(temp.indexOf(string),
									temp.indexOf(string) + string.length());
							stringStages.add(string);

						}

						String[] strings = temp.toString().split(",");
						for (int i = 0; i < strings.length; i++) {
							if (!strings[i].equals("")) {
								String string = strings[i];
								tempStages.add(string);
							}
						}

						model.put("itwStagesTypes", bean);
						model.put("tempitwStagesTypes", tempStages);
						model.put("itwpreceding", stringStages);
						model.put("itwStagesTypes", bean);
						model.put("tempitwStagesTypes", tempStages);
						if (itwTempStagesBeanList != null) {
							model.put("preceding", itwTempStagesBeanList);

						} else {
							model.put("preceding", null);
						}

						model.remove("itwStages1");
						model.put("itwStages1", itwStagesBean1);
						return new ModelAndView("editItwStagesTypes", model);
					}

				}

				ItwStagesTypes itwStages = prepareModelItwStagesTypesforUpdate(
						updateitwStagesBean, langId);
				System.out.println("Inside Update");
				itwStagesService.addItwStagesTypes(itwStages);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Stages already in use, enter a different Stages");

					String Stages = updateitwStagesBean.getPrecedingname();
					ItwStagesTypesBean bean = prepareItwStagesTypesBean(
							itwStagesService.getItwStagesTypes(updateitwStagesBean
									.getId()), langId);

					List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
							itwStagesService.listItwStagesTypes(langId), langId);

					StringBuffer temp = new StringBuffer();
					Iterator<ItwStagesTypesBean> iterator = itwTempStagesBeanList
							.iterator();
					while (iterator.hasNext()) {
						ItwStagesTypesBean itwStagesTypesBean2 = (ItwStagesTypesBean) iterator
								.next();
						temp.append(",")
								.append(itwStagesTypesBean2.getShortname())
								.append(",");

					}

					String[] StagesSplit = Stages.split(",");
					for (int i = 0; i < StagesSplit.length; i++) {
						String string = StagesSplit[i];
						temp.delete(temp.indexOf(string), temp.indexOf(string)
								+ string.length());
						stringStages.add(string);

					}

					String[] strings = temp.toString().split(",");
					for (int i = 0; i < strings.length; i++) {
						if (!strings[i].equals("")) {

							String string = strings[i];
							tempStages.add(string);
						}
					}

					model.put("itwStagesTypes", bean);
					model.put("tempitwStagesTypes", tempStages);
					model.put("itwpreceding", stringStages);
					model.put("itwStagesTypes", bean);
					model.put("tempitwStagesTypes", tempStages);
				}

				model.put("itwStagesTypes", updateitwStagesBean);
				return new ModelAndView("editItwStagesTypes", model);
			}

			return new ModelAndView("redirect:/itwStagesTypesConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwStagesTypes", method = RequestMethod.GET)
	public ModelAndView addItwStagesTypes(
			@ModelAttribute("command") ItwStagesTypesBean itwStagesBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwStagesTypes");

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		model.put(
				"itwStagesTypeslist",
				prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId));

		List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
				itwStagesService.listItwStagesTypes(langId), langId);

		if (itwTempStagesBeanList != null) {
			model.put("preceding", itwTempStagesBeanList);

		} else {
			model.put("preceding", null);
		}

		return new ModelAndView("addItwStagesTypes", model);

	}

	@RequestMapping(value = "/deleteItwStagesTypes", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwStagesTypesBean itwStagesBean,
			BindingResult result, HttpServletRequest request) {
		boolean tempValue = false;
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		String shortNametemp = "Stages ID " + itwStagesBean.getId()
				+ " already in use as precedence, cannot delete";

		ItwStagesTypesBean bean = prepareItwStagesTypesBean(
				itwStagesService.getItwStagesTypes(itwStagesBean.getId()),
				langId);

		List<ItwStagesTypesBean> newitwStagesBean = prepareListofItwStagesTypes(
				itwStagesService.listItwStagesTypes(langId), langId);

		Iterator<ItwStagesTypesBean> iterator = newitwStagesBean.iterator();
		while (iterator.hasNext()) {
			ItwStagesTypesBean itwStagesTypesBean = (ItwStagesTypesBean) iterator
					.next();

			String[] precedingId = itwStagesBean.getPrecedingId().split(",");
			for (int i = 0; i < precedingId.length; i++) {
				String string = precedingId[i];

				if (!precedingId[i].equals("")) {
					if (string.hashCode() == itwStagesBean.getId().toString()
							.hashCode()) {
						tempValue = true;
					}
				}
			}

		}

		try {
			if (!tempValue) {
				itwStagesService
						.deleteItwStagesTypes(prepareModelItwStagesTypes(
								itwStagesBean, langId));
			} else {

				throw new RuntimeException();
			}
		} catch (RuntimeException runtimeException) {
			Map<String, Object> model = new HashMap<String, Object>();
			List<ItwStagesTypesBean> itwStagesBeanList = prepareListofItwStagesTypes(
					itwStagesService.listItwStagesTypes(langId), langId);
			if (itwStagesBean != null) {
				PagedListHolder pagedListHolder = new PagedListHolder(
						itwStagesBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				System.out.println("value of page variable issssss" + page);
				pagedListHolder.setPage(page);

				int pageSize = 15;
				pagedListHolder.setPageSize(pageSize);

				model.put("pagedListHolder", pagedListHolder);
			} else {
				model.put("pagedListHolder", null);
			}

			result.rejectValue("shortname", "shortname.inuse", shortNametemp);
			return new ModelAndView("itwStagesTypesConfigListPage", model);

		}

		return new ModelAndView("redirect:/itwStagesTypesConfigList.html");
	}

	@RequestMapping(value = "/editItwStagesTypes", method = RequestMethod.GET)
	public ModelAndView editItwStagesTypes(
			@ModelAttribute("command") ItwStagesTypesBean edititwStagesBean,
			BindingResult result, HttpServletRequest request) {

		List<String> stringStages = new ArrayList<String>();
		List<String> tempStages = new ArrayList<String>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		String Stages = request.getParameter("precedingname");

		ItwStagesTypesBean bean = prepareItwStagesTypesBean(
				itwStagesService.getItwStagesTypes(edititwStagesBean.getId()),
				langId);

		Map<String, Object> model = new HashMap<String, Object>();

		ItwStagesTypesValidator shortsValidator = new ItwStagesTypesValidator();
		shortsValidator.validateStage(edititwStagesBean, result);

		if (result.hasErrors()) {
			if (edititwStagesBean.getPrecedingname() == null) {
				List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				System.out.println(edititwStagesBean.getPrecedingname());

				if (itwTempStagesBeanList != null) {

					model.put("newpreceding", itwTempStagesBeanList);

				} else {
					model.put("newpreceding", null);
				}

			}
			if (edititwStagesBean.getPrecedingname() != null) {

				List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
						itwStagesService.listItwStagesTypes(langId), langId);

				StringBuffer temp = new StringBuffer();
				Iterator<ItwStagesTypesBean> iterator = itwTempStagesBeanList
						.iterator();
				while (iterator.hasNext()) {
					ItwStagesTypesBean itwStagesTypesBean2 = (ItwStagesTypesBean) iterator
							.next();

				}

				model.put("rootStages", itwTempStagesBeanList);
				model.put("itwpreceding", stringStages);
				model.put("Stagesname", listofPreceeding);

			}
			model.put("itwStagesTypes", bean);
			return new ModelAndView("editItwStagesTypes", model);
		}

		else {

			List<ItwStagesTypesBean> itwTempStagesBeanList = prepareListofItwStagesTypes(
					itwStagesService.listItwStagesTypes(langId), langId);

			StringBuffer temp = new StringBuffer();
			Iterator<ItwStagesTypesBean> iterator = itwTempStagesBeanList
					.iterator();
			while (iterator.hasNext()) {
				ItwStagesTypesBean itwStagesTypesBean2 = (ItwStagesTypesBean) iterator
						.next();
				temp.append(",").append(itwStagesTypesBean2.getShortname())
						.append(",");

			}

			String[] StagesSplit = Stages.split(",");
			for (int i = 0; i < StagesSplit.length; i++) {
				String string = StagesSplit[i];
				temp.delete(temp.indexOf(string),
						temp.indexOf(string) + string.length());
				stringStages.add(string);

			}

			String[] strings = temp.toString().split(",");
			for (int i = 0; i < strings.length; i++) {
				if (!strings[i].equals("")) {
					String string = strings[i];
					tempStages.add(string);
				}
			}

			model.put("itwStagesTypes", bean);
			model.put("tempitwStagesTypes", tempStages);
			model.put("itwpreceding", stringStages);
			model.put("itwStagesTypes", bean);
			model.put("tempitwStagesTypes", tempStages);
			model.put("stagesname", bean.getPrecedingname());

			if (itwTempStagesBeanList != null) {
				model.put("preceding", itwTempStagesBeanList);
			} else {
				model.put("preceding", null);
			}
			return new ModelAndView("editItwStagesTypes", model);
		}
	}

	private ItwStagesTypes prepareModelItwStagesTypes(
			ItwStagesTypesBean itwStagesBean, Integer langId) {

		ItwStagesTypes itwStagesTypes = new ItwStagesTypes();
		StringBuffer buffer = new StringBuffer();
		if (itwStagesBean.getPrecedingname() != null) {
			List<ItwStagesTypes> listItwStagesType = itwStagesService
					.getItwStagesTypesByShortName(itwStagesBean
							.getPrecedingname());

			Iterator<ItwStagesTypes> iterator = listItwStagesType.iterator();
			while (iterator.hasNext()) {
				ItwStagesTypes itwStages = (ItwStagesTypes) iterator.next();
				buffer.append(",").append(itwStages.getId().toString())
						.append(",");

				itwStagesTypes.setPrecedingId(buffer.toString());

			}
		} else {
			itwStagesTypes.setPrecedingId(itwStagesBean.getPrecedingId());
		}
		itwStagesTypes.setLangId(langId);
		itwStagesTypes.setShortname(itwStagesBean.getShortname());
		itwStagesTypes.setLangId(langId);
		itwStagesTypes.setId(itwStagesBean.getId());
		itwStagesTypes.setDefaultvalue(itwStagesBean.getDefaultvalue());
		itwStagesBean.setId(null);
		return itwStagesTypes;
	}

	private ItwStagesTypes prepareModelItwStagesTypesforUpdate(
			ItwStagesTypesBean updateitwStagesBean, Integer langId) {

		ItwStagesTypes itwStages = new ItwStagesTypes();
		StringBuffer buffer = new StringBuffer();
		listofPreceeding = new ArrayList<String>();
		String[] arrayPrecedingName = updateitwStagesBean.getPrecedingname()
				.split(",");
		for (int i = 0; i < arrayPrecedingName.length; i++) {
			if (!arrayPrecedingName[i].equals("")) {
				String string = arrayPrecedingName[i];
				List<ItwStagesTypes> listItwStagesType = itwStagesService
						.getItwStagesTypesByShortName(string);

				Iterator<ItwStagesTypes> iterator = listItwStagesType
						.iterator();
				while (iterator.hasNext()) {
					ItwStagesTypes newitwStages = (ItwStagesTypes) iterator
							.next();
					buffer.append(",").append(newitwStages.getId().toString())
							.append(",");

					itwStages.setPrecedingId(buffer.toString());
				}

			}

		}

		itwStages.setId(updateitwStagesBean.getId());
		itwStages.setShortname(updateitwStagesBean.getShortname());
		itwStages.setLangId(langId);
		itwStages.setDefaultvalue(updateitwStagesBean.getDefaultvalue());
		// itwStages.setPrecedingId(itwStagesBean.getPrecedingId());
		// updateitwStagesBean.setId(null);

		return itwStages;
	}

	// /Need to modify
	@SuppressWarnings("unchecked")
	private List<ItwStagesTypesBean> prepareListofItwStagesTypes(
			List<ItwStagesTypes> itwStages, Integer langId) {
		List<ItwStagesTypesBean> beans = null;
		StringBuffer buffer;
		ItwStagesTypes newItwStagesTypes = new ItwStagesTypes();
		if (itwStages != null && !itwStages.isEmpty()) {
			beans = new ArrayList<ItwStagesTypesBean>();
			ItwStagesTypesBean bean = null;
			for (ItwStagesTypes itwStagesTypes : itwStages) {
				bean = new ItwStagesTypesBean();
				buffer = new StringBuffer();

				if (!(itwStagesTypes.getPrecedingId().equals(null))) {

					String[] arrayPrecedingName = itwStagesTypes
							.getPrecedingId().split(",");
					for (int i = 0; i < arrayPrecedingName.length; i++) {

						if (!arrayPrecedingName[i].equals("")) {
							String string = arrayPrecedingName[i];

							newItwStagesTypes = itwStagesService
									.getItwStagesTypes(Integer.parseInt(string));

							if (newItwStagesTypes != null) {
								buffer.append(newItwStagesTypes.getShortname())
										.append(",");
								bean.setPrecedingname(buffer.substring(0,
										buffer.length() - 1));
							} else {
								bean.setPrecedingname("ROOT");
							}
						}

					}
				}
				bean.setPrecedingId(itwStagesTypes.getPrecedingId());
				bean.setLangId(langId);
				bean.setId(itwStagesTypes.getId());
				bean.setShortname(itwStagesTypes.getShortname());
				bean.setDefaultvalue(itwStagesTypes.getDefaultvalue());
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwStagesTypesBean prepareItwStagesTypesBean(
			ItwStagesTypes itwStages, Integer langId) {

		ItwStagesTypesBean bean = new ItwStagesTypesBean();
		listofPreceeding = new ArrayList<String>();
		String[] arrayPrecedingName = itwStages.getPrecedingId().split(",");
		for (int i = 0; i < arrayPrecedingName.length; i++) {

			if (!arrayPrecedingName[i].equals("")) {
				String string = arrayPrecedingName[i];

				ItwStagesTypes newItwStagesTypes = itwStagesService
						.getItwStagesTypes(Integer.parseInt(string.toString()));

				if (newItwStagesTypes != null) {
					listofPreceeding.add(newItwStagesTypes.getShortname());
				}
			}

		}

		bean.setShortname(itwStages.getShortname());
		bean.setLangId(langId);
		bean.setId(itwStages.getId());
		bean.setPrecedingId(itwStages.getPrecedingId());
		bean.setDefaultvalue(itwStages.getDefaultvalue());

		return bean;
	}
}
