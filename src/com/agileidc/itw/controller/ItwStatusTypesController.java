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

import com.agileidc.itw.bean.ItwChartStatusBean;
import com.agileidc.itw.bean.ItwStatusTypesBean;
import com.agileidc.itw.helper.DefaultValidException;
import com.agileidc.itw.model.ItwChartStatus;
import com.agileidc.itw.model.ItwStatusTypes;
import com.agileidc.itw.service.ItwChartStatusService;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwStatusTypesService;
import com.agileidc.itw.web.ItwStatusTypesValidator;

@Controller
public class ItwStatusTypesController {
	@Autowired
	private ItwStatusTypesService itwStatusService;

	@Autowired
	private ItwChartStatusService itwChartStatusService;
	@Autowired
	private ItwLangTypesService itwLangTypesService;
	List<String> listofPreceeding = null;

	@RequestMapping(value = "/itwStatusTypesConfigList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		System.out.println(langId + "  LANG ID  ");
		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwStatusTypesBean> itwStatusBean = prepareListofItwStatusTypes(
				itwStatusService.listItwStatusTypes(langId), langId);

		if (itwStatusBean != null) {

			PagedListHolder pagedListHolder = new PagedListHolder(itwStatusBean);
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
		return new ModelAndView("itwStatusTypesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwStatusTypes", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwStatusTypesBean saveitwStatusBean,
			BindingResult result, HttpServletRequest request) {
		boolean defaultvalue = false;
		String tempdefaultValue = saveitwStatusBean.getDefaultvalue();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwStatusTypesValidator shortsValidator = new ItwStatusTypesValidator();
		shortsValidator.validate(saveitwStatusBean, result);
		Map<String, Object> model = new HashMap<String, Object>();

		if (result.hasErrors()) {

			model.put("itwStatusTypes", saveitwStatusBean);

			List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
					itwStatusService.listItwStatusTypes(langId), langId);
			model.put("Statusname", saveitwStatusBean.getPrecedingname());
			if (itwTempStatusBeanList != null) {
				model.put("preceding", itwTempStatusBeanList);

			} else {
				model.put("preceding", null);
			}

			model.put(
					"itwStatusTypeslist",
					prepareListofItwStatusTypes(
							itwStatusService.listItwStatusTypes(langId), langId));

			model.put("itwChartStatuslist",
					prepareListofItwChartStatus(itwChartStatusService
							.listItwChartStatus(langId.intValue())));
			return new ModelAndView("addItwStatusTypes", model);
		} else {

			ItwStatusTypes itwStatus = prepareModelItwStatusTypes(
					saveitwStatusBean, langId);

			try {

				List<ItwStatusTypesBean> newitwStatusBean = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				if (newitwStatusBean != null) {
					Iterator<ItwStatusTypesBean> iterator = newitwStatusBean
							.iterator();
					while (iterator.hasNext()) {
						ItwStatusTypesBean newitwStatusTypesBean = (ItwStatusTypesBean) iterator
								.next();

						if (newitwStatusTypesBean.getDefaultvalue().equals("Y")
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
						List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
								itwStatusService.listItwStatusTypes(langId),
								langId);

						if (itwTempStatusBeanList != null) {
							model.put("preceding", itwTempStatusBeanList);

						} else {
							model.put("preceding", null);
						}
						model.put(
								"itwStatusTypeslist",
								prepareListofItwStatusTypes(itwStatusService
										.listItwStatusTypes(langId), langId));
						model.put(
								"itwChartStatuslist",
								prepareListofItwChartStatus(itwChartStatusService
										.listItwChartStatus(langId.intValue())));
						return new ModelAndView("addItwStatusTypes", model);
					}

				}

				itwStatusService.addItwStatusTypes(itwStatus);
			} catch (RuntimeException runtimeException) {

				System.out.println(runtimeException.toString()
						+ " EXCEPTION OCCURED ");
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Status  is already in use, enter a different Status");

					ItwStatusTypesBean itwStatusBean1 = prepareItwStatusTypesBeanForAdd();
					model.put("itwStatusTypes", saveitwStatusBean);

					model.put(
							"itwStatusTypeslist",
							prepareListofItwStatusTypes(
									itwStatusService.listItwStatusTypes(langId),
									langId));
					List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
							itwStatusService.listItwStatusTypes(langId), langId);
					model.put("Statusname",
							saveitwStatusBean.getPrecedingname());
					if (itwTempStatusBeanList != null) {
						model.put("preceding", itwTempStatusBeanList);

					} else {
						model.put("preceding", null);
					}

					model.remove("itwStatus1");
					model.put("itwStatus1", itwStatusBean1);
					model.put("itwChartStatuslist",
							prepareListofItwChartStatus(itwChartStatusService
									.listItwChartStatus(langId.intValue())));
					return new ModelAndView("addItwStatusTypes", model);
				}
			}
		}
		return new ModelAndView("redirect:/itwStatusTypesConfigList.html");

	}

	private ItwStatusTypesBean prepareItwStatusTypesBeanForAdd() {
		ItwStatusTypesBean bean = new ItwStatusTypesBean();

		return bean;
	}

	@RequestMapping(value = "/updateItwStatusTypes", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwStatusTypesBean updateitwStatusBean,
			BindingResult result, HttpServletRequest request) {
		List<String> stringStatus = new ArrayList<String>();
		List<String> tempStatus = new ArrayList<String>();
		boolean defaultvalue = false;
		String tempdefaultValue = updateitwStatusBean.getDefaultvalue();
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println(updateitwStatusBean.getId());
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwStatusTypesValidator shortsValidator = new ItwStatusTypesValidator();
		shortsValidator.validatestatus(updateitwStatusBean, result);
		if (result.hasErrors()) {

			if (updateitwStatusBean.getPrecedingname() == null) {

				List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				model.put(
						"itwStatusTypeslist",
						prepareListofItwStatusTypes(
								itwStatusService.listItwStatusTypes(langId),
								langId));
				model.put("itwChartStatuslist",
						prepareListofItwChartStatus(itwChartStatusService
								.listItwChartStatus(langId.intValue())));

				if (itwTempStatusBeanList != null) {

					model.put("newpreceding", itwTempStatusBeanList);

				} else {
					model.put("newpreceding", null);
				}
			}
			if (updateitwStatusBean.getPrecedingname() != null) {
				String Status = updateitwStatusBean.getPrecedingname();
				System.out.println(Status + "Status");

				ItwStatusTypesBean bean = prepareItwStatusTypesBean(
						itwStatusService.getItwStatusTypes(updateitwStatusBean
								.getId()), langId);

				List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				StringBuffer temp = new StringBuffer();
				Iterator<ItwStatusTypesBean> iterator = itwTempStatusBeanList
						.iterator();
				while (iterator.hasNext()) {
					ItwStatusTypesBean itwStatusTypesBean2 = (ItwStatusTypesBean) iterator
							.next();
					temp.append(",").append(itwStatusTypesBean2.getShortname())
							.append(",");
				}
				String[] StatusSplit = Status.split(",");
				for (int i = 0; i < StatusSplit.length; i++) {
					String string = StatusSplit[i];
					temp.delete(temp.indexOf(string), temp.indexOf(string)
							+ string.length());
					stringStatus.add(string);

				}
				String[] strings = temp.toString().split(",");
				for (int i = 0; i < strings.length; i++) {
					if (!strings[i].equals("")) {

						String string = strings[i];
						System.out.println(string);

						tempStatus.add(string);
					}
				}
				model.put("itwChartStatuslist",
						prepareListofItwChartStatus(itwChartStatusService
								.listItwChartStatus(langId.intValue())));
				model.put("itwStatusTypes", bean);
				model.put("tempitwStatusTypes", tempStatus);
				model.put("itwpreceding", stringStatus);
				model.put("itwStatusTypes", bean);
				model.put("tempitwStatusTypes", tempStatus);
			}

			model.put("itwStatusTypes", updateitwStatusBean);
			return new ModelAndView("editItwStatusTypes", model);
		} else {
			try {
				List<ItwStatusTypesBean> newitwStatusBean = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				Iterator<ItwStatusTypesBean> iterator = newitwStatusBean
						.iterator();
				while (iterator.hasNext()) {
					ItwStatusTypesBean newitwStatusTypesBean = (ItwStatusTypesBean) iterator
							.next();

					if (newitwStatusTypesBean.getDefaultvalue().equals("Y")
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

						ItwStatusTypesBean itwStatusBean1 = prepareItwStatusTypesBeanForAdd();
						model.put("itwStatusTypes", updateitwStatusBean);

						List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
								itwStatusService.listItwStatusTypes(langId),
								langId);
						model.put("Statusname",
								updateitwStatusBean.getPrecedingname());

						String Status = updateitwStatusBean.getPrecedingname();
						System.out.println(Status + "Status");
						ItwStatusTypesBean bean = prepareItwStatusTypesBean(
								itwStatusService.getItwStatusTypes(updateitwStatusBean
										.getId()), langId);

						StringBuffer temp = new StringBuffer();
						Iterator<ItwStatusTypesBean> newiterator = itwTempStatusBeanList
								.iterator();
						while (newiterator.hasNext()) {
							ItwStatusTypesBean itwStatusTypesBean2 = (ItwStatusTypesBean) newiterator
									.next();
							temp.append(",")
									.append(itwStatusTypesBean2.getShortname())
									.append(",");

						}

						String[] StatusSplit = Status.split(",");
						for (int i = 0; i < StatusSplit.length; i++) {
							String string = StatusSplit[i];
							temp.delete(temp.indexOf(string),
									temp.indexOf(string) + string.length());
							stringStatus.add(string);

						}

						String[] strings = temp.toString().split(",");
						for (int i = 0; i < strings.length; i++) {
							if (!strings[i].equals("")) {

								String string = strings[i];
								System.out.println(string);
								tempStatus.add(string);
							}
						}

						model.put("itwStatusTypes", bean);
						model.put("tempitwStatusTypes", tempStatus);
						model.put("itwpreceding", stringStatus);
						model.put("itwStatusTypes", bean);
						model.put("tempitwStatusTypes", tempStatus);
						if (itwTempStatusBeanList != null) {
							model.put("preceding", itwTempStatusBeanList);

						} else {
							model.put("preceding", null);
						}

						model.put(
								"itwStatusTypeslist",
								prepareListofItwStatusTypes(itwStatusService
										.listItwStatusTypes(langId), langId));
						model.put(
								"itwChartStatuslist",
								prepareListofItwChartStatus(itwChartStatusService
										.listItwChartStatus(langId.intValue())));
						model.remove("itwStatus1");
						model.put("itwStatus1", itwStatusBean1);
						return new ModelAndView("editItwStatusTypes", model);
					}

				}

				ItwStatusTypes itwStatus = prepareModelItwStatusTypesforUpdate(
						updateitwStatusBean, langId);
				System.out.println("Inside Update");
				itwStatusService.addItwStatusTypes(itwStatus);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("shortname", "shortname.unique",
							"Status already in use, enter a different Status");

					String Status = updateitwStatusBean.getPrecedingname();
					System.out.println(Status + "Status");
					ItwStatusTypesBean bean = prepareItwStatusTypesBean(
							itwStatusService.getItwStatusTypes(updateitwStatusBean
									.getId()), langId);

					List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
							itwStatusService.listItwStatusTypes(langId), langId);

					StringBuffer temp = new StringBuffer();
					Iterator<ItwStatusTypesBean> iterator = itwTempStatusBeanList
							.iterator();
					while (iterator.hasNext()) {
						ItwStatusTypesBean itwStatusTypesBean2 = (ItwStatusTypesBean) iterator
								.next();
						temp.append(",")
								.append(itwStatusTypesBean2.getShortname())
								.append(",");

					}

					String[] StatusSplit = Status.split(",");
					for (int i = 0; i < StatusSplit.length; i++) {
						String string = StatusSplit[i];
						temp.delete(temp.indexOf(string), temp.indexOf(string)
								+ string.length());
						stringStatus.add(string);

					}

					String[] strings = temp.toString().split(",");
					for (int i = 0; i < strings.length; i++) {
						if (!strings[i].equals("")) {

							String string = strings[i];
							System.out.println(string);

							tempStatus.add(string);
						}
					}
					model.put("itwStatusTypes", bean);
					model.put("tempitwStatusTypes", tempStatus);
					model.put("itwpreceding", stringStatus);
					model.put("itwStatusTypes", bean);
					model.put("tempitwStatusTypes", tempStatus);
				}

				model.put("itwStatusTypes", updateitwStatusBean);
				return new ModelAndView("editItwStatusTypes", model);
			}
			model.put("itwChartStatuslist",
					prepareListofItwChartStatus(itwChartStatusService
							.listItwChartStatus(langId.intValue())));
			return new ModelAndView("redirect:/itwStatusTypesConfigList.html");
		}
	}

	@RequestMapping(value = "/addItwStatusTypes", method = RequestMethod.GET)
	public ModelAndView addItwStatusTypes(
			@ModelAttribute("command") ItwStatusTypesBean itwStatusBean,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Inside addItwStatusTypes");

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		model.put(
				"itwStatusTypeslist",
				prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId));

		model.put("itwChartStatuslist",
				prepareListofItwChartStatus(itwChartStatusService
						.listItwChartStatus(langId.intValue())));
		List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
				itwStatusService.listItwStatusTypes(langId), langId);

		if (itwTempStatusBeanList != null) {
			model.put("preceding", itwTempStatusBeanList);

		} else {
			model.put("preceding", null);
		}

		return new ModelAndView("addItwStatusTypes", model);

	}

	@RequestMapping(value = "/deleteItwStatusTypes", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwStatusTypesBean itwStatusBean,
			BindingResult result, HttpServletRequest request) {
		boolean tempValue = false;
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		String shortNametemp = "Status ID " + itwStatusBean.getId()
				+ " already in use as precedence, cannot delete";

		ItwStatusTypesBean bean = prepareItwStatusTypesBean(
				itwStatusService.getItwStatusTypes(itwStatusBean.getId()),
				langId);

		List<ItwStatusTypesBean> newitwStatusBean = prepareListofItwStatusTypes(
				itwStatusService.listItwStatusTypes(langId), langId);

		Iterator<ItwStatusTypesBean> iterator = newitwStatusBean.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypesBean itwStatusTypesBean = (ItwStatusTypesBean) iterator
					.next();

			String[] precedingId = itwStatusTypesBean.getPrecedingId().split(
					",");
			for (int i = 0; i < precedingId.length; i++) {
				String string = precedingId[i];

				if (!precedingId[i].equals("")) {
					if (string.hashCode() == itwStatusBean.getId().toString()
							.hashCode()) {
						tempValue = true;
					}
				}
			}

		}

		try {
			if (!tempValue) {
				itwStatusService
						.deleteItwStatusTypes(prepareModelItwStatusTypes(
								itwStatusBean, langId));
			} else {

				throw new RuntimeException();
			}
		} catch (RuntimeException runtimeException) {
			Map<String, Object> model = new HashMap<String, Object>();
			List<ItwStatusTypesBean> itwStatusBeanList = prepareListofItwStatusTypes(
					itwStatusService.listItwStatusTypes(langId), langId);
			if (itwStatusBean != null) {
				PagedListHolder pagedListHolder = new PagedListHolder(
						itwStatusBeanList);
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
			return new ModelAndView("itwStatusTypesConfigListPage", model);

		}

		return new ModelAndView("redirect:/itwStatusTypesConfigList.html");
	}

	@RequestMapping(value = "/editItwStatusTypes", method = RequestMethod.GET)
	public ModelAndView editItwStatusTypes(
			@ModelAttribute("command") ItwStatusTypesBean edititwStatusBean,
			BindingResult result, HttpServletRequest request) {

		List<String> stringStatus = new ArrayList<String>();
		List<String> tempStatus = new ArrayList<String>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		String Status = request.getParameter("precedingname");

		ItwStatusTypesBean bean = prepareItwStatusTypesBean(
				itwStatusService.getItwStatusTypes(edititwStatusBean.getId()),
				langId);

		ItwStatusTypesValidator shortsValidator = new ItwStatusTypesValidator();
		shortsValidator.validatestatus(edititwStatusBean, result);

		if (result.hasErrors()) {
			if (edititwStatusBean.getPrecedingname() == null) {
				List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				System.out.println(edititwStatusBean.getPrecedingname());

				if (itwTempStatusBeanList != null) {

					model.put("newpreceding", itwTempStatusBeanList);

				} else {
					model.put("newpreceding", null);
				}

			}
			if (edititwStatusBean.getPrecedingname() != null) {

				List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
						itwStatusService.listItwStatusTypes(langId), langId);

				StringBuffer temp = new StringBuffer();
				Iterator<ItwStatusTypesBean> iterator = itwTempStatusBeanList
						.iterator();
				while (iterator.hasNext()) {
					ItwStatusTypesBean itwStatusTypesBean2 = (ItwStatusTypesBean) iterator
							.next();

				}

				model.put("rootStatus", itwTempStatusBeanList);
				model.put("itwpreceding", stringStatus);
				model.put("Statusname", listofPreceeding);

			}
			model.put("itwChartStatuslist",
					prepareListofItwChartStatus(itwChartStatusService
							.listItwChartStatus(langId.intValue())));

			model.put("itwStatusTypes", bean);

			return new ModelAndView("editItwStatusTypes", model);
		}

		else {

			List<ItwStatusTypesBean> itwTempStatusBeanList = prepareListofItwStatusTypes(
					itwStatusService.listItwStatusTypes(langId), langId);

			StringBuffer temp = new StringBuffer();
			Iterator<ItwStatusTypesBean> iterator = itwTempStatusBeanList
					.iterator();
			while (iterator.hasNext()) {
				ItwStatusTypesBean itwStatusTypesBean2 = (ItwStatusTypesBean) iterator
						.next();
				temp.append(",").append(itwStatusTypesBean2.getShortname())
						.append(",");

			}

			String[] StatusSplit = Status.split(",");
			for (int i = 0; i < StatusSplit.length; i++) {
				String string = StatusSplit[i];
				temp.delete(temp.indexOf(string),
						temp.indexOf(string) + string.length());
				stringStatus.add(string);

			}

			String[] strings = temp.toString().split(",");
			for (int i = 0; i < strings.length; i++) {
				if (!strings[i].equals("")) {

					String string = strings[i];
					System.out.println(string);

					tempStatus.add(string);
				}
			}

			model.put("itwChartStatuslist",
					prepareListofItwChartStatus(itwChartStatusService
							.listItwChartStatus(langId.intValue())));
			model.put("statusname", bean.getStatusname());
			model.put("itwStatusTypes", bean);
			model.put("tempitwStatusTypes", tempStatus);
			model.put("itwpreceding", stringStatus);

			model.put("Statusname", bean.getPrecedingname());

			if (itwTempStatusBeanList != null) {
				model.put("preceding", itwTempStatusBeanList);
			} else {
				model.put("preceding", null);
			}
			return new ModelAndView("editItwStatusTypes", model);
		}
	}

	private ItwStatusTypes prepareModelItwStatusTypes(
			ItwStatusTypesBean itwStatusBean, Integer langId) {

		System.out.println(itwStatusBean.getStatusname() + "  STATUS NAME ");
		ItwStatusTypes itwStatusTypes = new ItwStatusTypes();

		StringBuffer buffer = new StringBuffer();

		if (itwStatusBean.getPrecedingname() != null) {
			List<ItwStatusTypes> listItwStatusType = itwStatusService
					.getItwStatusTypesByShortName(itwStatusBean
							.getPrecedingname());

			Iterator<ItwStatusTypes> iterator = listItwStatusType.iterator();
			while (iterator.hasNext()) {
				ItwStatusTypes itwStatus = (ItwStatusTypes) iterator.next();
				buffer.append(",").append(itwStatus.getId().toString())
						.append(",");

				itwStatusTypes.setPrecedingId(buffer.toString());

			}
		} else {
			itwStatusTypes.setPrecedingId(itwStatusBean.getPrecedingId());
		}

		List<ItwChartStatus> listitwChart = itwChartStatusService
				.getItwChartStatusByShortName(itwStatusBean.getStatusname());
		if (listitwChart != null) {
			Iterator<ItwChartStatus> iterator = listitwChart.iterator();
			while (iterator.hasNext()) {
				ItwChartStatus itwChartStatus = (ItwChartStatus) iterator
						.next();
				itwStatusTypes.setStatusType(itwChartStatus.getId());
			}
		}

		itwStatusTypes.setLangId(langId);
		itwStatusTypes.setShortname(itwStatusBean.getShortname());

		itwStatusTypes.setId(itwStatusBean.getId());
		itwStatusTypes.setDefaultvalue(itwStatusBean.getDefaultvalue());
		itwStatusBean.setId(null);
		return itwStatusTypes;
	}

	private ItwStatusTypes prepareModelItwStatusTypesforUpdate(
			ItwStatusTypesBean updateitwStatusBean, Integer langId) {

		ItwStatusTypes itwStatus = new ItwStatusTypes();
		StringBuffer buffer = new StringBuffer();
		listofPreceeding = new ArrayList<String>();
		String[] arrayPrecedingName = updateitwStatusBean.getPrecedingname()
				.split(",");
		for (int i = 0; i < arrayPrecedingName.length; i++) {
			if (!arrayPrecedingName[i].equals("")) {
				String string = arrayPrecedingName[i];
				List<ItwStatusTypes> listItwStatusType = itwStatusService
						.getItwStatusTypesByShortName(string);

				Iterator<ItwStatusTypes> iterator = listItwStatusType
						.iterator();
				while (iterator.hasNext()) {
					ItwStatusTypes newitwStatus = (ItwStatusTypes) iterator
							.next();
					buffer.append(",").append(newitwStatus.getId().toString())
							.append(",");

					itwStatus.setPrecedingId(buffer.toString());
				}

			}

		}

		List<ItwChartStatus> listitwChart = itwChartStatusService
				.getItwChartStatusByShortName(updateitwStatusBean
						.getStatusname());
		if (listitwChart != null) {
			Iterator<ItwChartStatus> iterator = listitwChart.iterator();
			while (iterator.hasNext()) {
				ItwChartStatus itwChartStatus = (ItwChartStatus) iterator
						.next();
				itwStatus.setStatusType(itwChartStatus.getId());
			}
		}

		itwStatus.setId(updateitwStatusBean.getId());
		itwStatus.setShortname(updateitwStatusBean.getShortname());
		itwStatus.setLangId(langId);
		itwStatus.setDefaultvalue(updateitwStatusBean.getDefaultvalue());
		// itwStatus.setPrecedingId(itwStatusBean.getPrecedingId());
		// updateitwStatusBean.setId(null);

		return itwStatus;
	}

	// /Need to modify
	@SuppressWarnings("unchecked")
	private List<ItwStatusTypesBean> prepareListofItwStatusTypes(
			List<ItwStatusTypes> itwStatus, Integer langId) {
		List<ItwStatusTypesBean> beans = null;
		StringBuffer buffer;
		ItwStatusTypes newItwStatusTypes = new ItwStatusTypes();

		if (itwStatus != null && !itwStatus.isEmpty()) {
			beans = new ArrayList<ItwStatusTypesBean>();
			ItwStatusTypesBean bean = null;
			for (ItwStatusTypes itwStatusTypes : itwStatus) {
				bean = new ItwStatusTypesBean();
				buffer = new StringBuffer();

				if (!(itwStatusTypes.getPrecedingId().equals(null))) {

					String[] arrayPrecedingName = itwStatusTypes
							.getPrecedingId().split(",");
					for (int i = 0; i < arrayPrecedingName.length; i++) {

						if (!arrayPrecedingName[i].equals("")) {
							String string = arrayPrecedingName[i];

							newItwStatusTypes = itwStatusService
									.getItwStatusTypes(Integer.parseInt(string));

							if (newItwStatusTypes != null) {
								buffer.append(newItwStatusTypes.getShortname())
										.append(",");
								bean.setPrecedingname(buffer.substring(0,
										buffer.length() - 1));
							} else {
								bean.setPrecedingname("ROOT");
							}
						}

					}
				}

				bean.setStatusType(itwStatusTypes.getStatusType());
				bean.setLangId(langId);
				bean.setPrecedingId(itwStatusTypes.getPrecedingId());
				bean.setId(itwStatusTypes.getId());
				bean.setShortname(itwStatusTypes.getShortname());
				bean.setDefaultvalue(itwStatusTypes.getDefaultvalue());
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwStatusTypesBean prepareItwStatusTypesBean(
			ItwStatusTypes itwStatus, Integer langId) {
		ItwStatusTypesBean bean = new ItwStatusTypesBean();
		listofPreceeding = new ArrayList<String>();
		String[] arrayPrecedingName = itwStatus.getPrecedingId().split(",");
		for (int i = 0; i < arrayPrecedingName.length; i++) {

			if (!arrayPrecedingName[i].equals("")) {
				String string = arrayPrecedingName[i];

				ItwStatusTypes newItwStatusTypes = itwStatusService
						.getItwStatusTypes(Integer.parseInt(string.toString()));

				if (newItwStatusTypes != null) {
					listofPreceeding.add(newItwStatusTypes.getShortname());
				}
			}

		}
		ItwChartStatus listitwChart = itwChartStatusService
				.getItwChartStatus(itwStatus.getStatusType());
		bean.setStatusname(listitwChart.getShortName());
		bean.setShortname(itwStatus.getShortname());
		bean.setLangId(langId);
		bean.setId(itwStatus.getId());
		bean.setPrecedingId(itwStatus.getPrecedingId());
		bean.setDefaultvalue(itwStatus.getDefaultvalue());

		return bean;
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
}
