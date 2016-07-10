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

import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.bean.ItwUserGroupBean;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserGroup;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwUserGroupService;
import com.agileidc.itw.service.ItwUserService;
import com.agileidc.itw.web.UserGroupValidator;

@Controller
public class ItwUserGroupController {

	@Autowired
	private ItwUserService itwUserService;
	@Autowired
	private ItwUserGroupService itwUserGroupService;

	@Autowired
	private ItwLangTypesService itwLangTypesService;
	// String langDesc = null;
	List<ItwUserGroupBean> strings;
	List<ItwUserBean> itwUserBean = null;

	@RequestMapping(value = "/saveItwUserGroup", method = RequestMethod.POST)
	public ModelAndView saveItwUserGroup(
			@ModelAttribute("command") ItwUserGroupBean itwUserGroupBean,
			BindingResult result, HttpServletRequest request) {
		// langDesc = request.getParameter("langDesc");
		UserGroupValidator userGrouptValidator = new UserGroupValidator();
		userGrouptValidator.validate(itwUserGroupBean, result);
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		if (result.hasErrors()) {

			if (itwUserGroupBean.getUsernames() != null) {
				strings = new ArrayList<ItwUserGroupBean>();
				String string = null;
				String temp = new String(itwUserGroupBean.getUsernames());
				System.out.println(itwUserGroupBean.getUsernames()
						+ " USER name  ");
				ItwUserGroupBean bean = null;
				String[] tempArray = temp.split(",");
				
				StringBuffer buffer=new StringBuffer();
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwUserGroupBean();

					ItwUser usertemp = itwUserService.getItwUser(new Integer(
							tempArray[i]));

					System.out.println(usertemp.getId().toString()
							+ "-----------------");
					buffer.append(usertemp.getId().toString()).append(",");
					bean.setUsernames(usertemp.getId().toString());
					bean.setUsernamesdisplay(usertemp.getUserid());
					strings.add(bean);
				}

				System.out.println(temp + "   Temp value   ");

				List<ItwUserBean> itwUserBean1 = prepareListofItwUser(
						itwUserService.listItwUsers(langId), buffer.substring(0, buffer.length()-1));

				model.put("itwUserGroupUsers", strings);
				model.put("itwUser", itwUserBean1);

			}
			if (itwUserGroupBean.getUsernames() == null) {
				List<ItwUserBean> itwUserBean2 = prepareListofItwUser(itwUserService
						.listItwUsers(langId));
				model.put("itwUser", itwUserBean2);
			} else {
				// model.put("itwUser", null);
			}
			return new ModelAndView("addItwUserGroup", model);

		} else {
			try {

				String s2 = itwUserGroupBean.getUsernames().replace('[', '\0')
						.replace(']', '\0');

				System.out.println(s2 + "OOOOOOOOOOO");

				itwUserGroupBean.setUsernames(s2);

				ItwUserGroup itwUserGroup = prepareModel(itwUserGroupBean,
						langId);
				System.out.println("aBEFOR ADD");
				itwUserGroupService.addItwUserGroup(itwUserGroup);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a new role in
				// table ITW_ROLES

				System.out.println("EXCEPTion occured+ru" + runtimeException);
				strings = new ArrayList<ItwUserGroupBean>();
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("groupname", "groupname.unique",
							"Group already in use, enter a different Group Name");

					String string = null;
					String temp = new String(itwUserGroupBean.getUsernames());
					System.out.println(itwUserGroupBean.getUsernames()
							+ " USER name  ");
					ItwUserGroupBean bean = null;
					String[] tempArray = temp.split(",");
					for (int i = 0; i < tempArray.length; i++) {
						bean = new ItwUserGroupBean();

						ItwUser usertemp = itwUserService
								.getItwUser(new Integer(tempArray[i]));

						System.out.println(usertemp.getId().toString()
								+ "-----------------");

						bean.setUsernames(usertemp.getId().toString());
						bean.setUsernamesdisplay(usertemp.getUserid());
						strings.add(bean);
					}

					System.out.println(temp + "   Temp value   ");

					List<ItwUserBean> itwUserBean1 = prepareListofItwUser(
							itwUserService.listItwUsers(langId), temp);

					model.put("itwUserGroupUsers", strings);
					model.put("itwUser", itwUserBean1);

					return new ModelAndView("addItwUserGroup", model);
				}

			}
			return new ModelAndView("redirect:/itwUserGroupConfigList.html");
		}

	}

	@RequestMapping(value = "/itwUserGroups", method = RequestMethod.GET)
	public ModelAndView listItwUserGroups() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("ItwUserGroupConfigListPage", model);
	}

	@RequestMapping(value = "/addItwUserGroup", method = RequestMethod.GET)
	public ModelAndView addItwUserGroup(
			@ModelAttribute("command") ItwUserGroupBean itwUserGroupBean,
			BindingResult result, HttpServletRequest request) {
		// langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwUserBean> itwUserBean = addprepareListofItwUser(itwUserService
				.listItwUsers(langId));

		if (itwUserBean != null) {

			model.put("itwUser", itwUserBean);

		} else {
			model.put("itwUser", null);
		}
		return new ModelAndView("addItwUserGroup", model);
	}

	@RequestMapping(value = "/editItwUserGroup", method = RequestMethod.GET)
	public ModelAndView editItwUserGroup(
			@ModelAttribute("command") ItwUserGroupBean itwUserGroupBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();

		ItwUserGroupBean itwUserGroupBean2 = prepareItwUserGroupBean(
				itwUserGroupService.getItwUserGroup(itwUserGroupBean.getId()),
				request);

		model.put("itwUserGroupUsers", strings);
		model.put("itwUserGroup", itwUserGroupBean2);

		if (itwUserBean != null) {

			model.put("itwUser", itwUserBean);

		} else {
			model.put("itwUser", null);

		}

		return new ModelAndView("editItwUserGroup", model);
	}

	@RequestMapping(value = "/updateItwUserGroup", method = RequestMethod.POST)
	public ModelAndView updateUserGroup(
			@ModelAttribute("command") ItwUserGroupBean itwUserGroupBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		UserGroupValidator userGrouptValidator = new UserGroupValidator();
		userGrouptValidator.validate(itwUserGroupBean, result);
		Map<String, Object> model = new HashMap<String, Object>();

		if (result.hasErrors()) {

			if (itwUserGroupBean.getUsernames() != null) {
				strings = new ArrayList<ItwUserGroupBean>();
				String string = null;
				String temp = new String(itwUserGroupBean.getUsernames());
				System.out.println(itwUserGroupBean.getUsernames()
						+ " USER name  ");
				ItwUserGroupBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwUserGroupBean();

					ItwUser usertemp = itwUserService.getItwUser(new Integer(
							tempArray[i]));

					System.out.println(usertemp.getId().toString()
							+ "-----------------");

					bean.setUsernames(usertemp.getId().toString());
					bean.setUsernamesdisplay(usertemp.getUserid());
					strings.add(bean);
				}

				System.out.println(temp + "   Temp value   ");

				List<ItwUserBean> itwUserBean1 = prepareListofItwUser(
						itwUserService.listItwUsers(langId), temp);

				model.put("itwUserGroupUsers", strings);
				model.put("itwUser", itwUserBean1);

			}
			if (itwUserGroupBean.getUsernames() == null) {
				List<ItwUserBean> itwUserBean2 = prepareListofItwUser(itwUserService
						.listItwUsers(langId));
				model.put("itwUser", itwUserBean2);
			} else {
				// model.put("itwUser", null);
			}

			return new ModelAndView("editItwUserGroup", model);

		} else {

			ItwUserGroup itwUserGrouptemp = itwUserGroupService
					.getItwUserGroup(itwUserGroupBean.getId());
			ItwUserGroup itwUserGroup = prepareModelItwUserGroupforUpdate(itwUserGroupBean);
			try {
				itwUserGroupService.addItwUserGroup(itwUserGroup);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a new role in
				// table ITW_ROLES

				System.out.println("EXCEPTion occured+ru" + runtimeException);
				strings = new ArrayList<ItwUserGroupBean>();
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("groupname", "groupname.unique",
							"Group already in use, enter a different Group Name");

					String string = null;
					String temp = new String(itwUserGroupBean.getUsernames());
					System.out.println(itwUserGroupBean.getUsernames()
							+ " USER name  ");
					ItwUserGroupBean bean = null;
					String[] tempArray = temp.split(",");
					for (int i = 0; i < tempArray.length; i++) {
						bean = new ItwUserGroupBean();

						ItwUser usertemp = itwUserService
								.getItwUser(new Integer(tempArray[i]));

						System.out.println(usertemp.getId().toString()
								+ "-----------------");

						bean.setUsernames(usertemp.getId().toString());
						bean.setUsernamesdisplay(usertemp.getUserid());
						strings.add(bean);
					}

					System.out.println(temp + "   Temp value   ");

					List<ItwUserBean> itwUserBean1 = prepareListofItwUser(
							itwUserService.listItwUsers(langId), temp);

					model.put("itwUserGroupUsers", strings);
					model.put("itwUser", itwUserBean1);

					return new ModelAndView("addItwUserGroup", model);
				}

			}

			return new ModelAndView("redirect:/itwUserGroupConfigList.html");
		}
	}

	@RequestMapping(value = "/itwUserGroupConfigList", method = RequestMethod.GET)
	public ModelAndView userGrouptConfigList(HttpServletRequest request) {
		System.out.println("started userGroupt config List page");
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		System.out.println("====================");

		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwUserGroupBean> itwUserGroupBean = prepareListofItwUserGroups(itwUserGroupService
				.listItwUserGroups(langId));

		if (itwUserGroupBean != null) {
			// java.util.Collections.sort(itwUserGroupBean,
			// ItwUserGroupBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwUserGroupBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}
		System.out.println("+++++++++++++++++++++");
		return new ModelAndView("ItwUserGroupConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwUserGroup", method = RequestMethod.GET)
	public ModelAndView deleteItwUserGroup(
			@ModelAttribute("command") ItwUserGroupBean itwUserGroupBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		// itwUserGroupBean.setLangDesc(langDesc);

		String userGrouptNametemp = "UserGroup ID " + itwUserGroupBean.getId()
				+ " already in use, cannot delete";

		try {
			System.out.println("Run Time");
			itwUserGroupService
					.deleteItwUserGroup(prepareModelItwUserGroupforDelete(itwUserGroupBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// role id is being deleted which is in use in table ITW_USERS as a
			// foreign key

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				List<ItwUserGroupBean> itwUserGroupBeanList = prepareListofItwUserGroups(itwUserGroupService
						.listItwUserGroups(langId));
				if (itwUserGroupBean != null) {
					// java.util.Collections.sort(itwUserGroupBeanList,
					// ItwUserGroupBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwUserGroupBeanList);
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
						userGrouptNametemp);
				return new ModelAndView("ItwUserGroupConfigListPage", model);
			}

		}

		return new ModelAndView("redirect:/itwUserGroupConfigList.html");
	}

	private ItwUserGroup prepareModel(ItwUserGroupBean itwUserGroupBean,
			int langId) {

		ItwUserGroup itwUserGroup = new ItwUserGroup();

		itwUserGroup.setGroupname(itwUserGroupBean.getGroupname());
		itwUserGroupBean.setId(itwUserGroupBean.getId());
		itwUserGroup.setLangId(langId);

		System.out.println(itwUserGroupBean.getUsernames());

		String[] splitId = itwUserGroupBean.getUsernames().split(",");

		StringBuffer idUserStr = new StringBuffer();

		for (int j = 0; j < splitId.length; j++) {
			ItwUser listItwUser = itwUserService.getItwUser(new Integer(
					splitId[j]));

			idUserStr = idUserStr.append(",").append(listItwUser.getId()).append(",");

		}

		// itwUserGroup.setClientname(idClient.substring(0,
		// idUserStr.length() - 1));
		itwUserGroup
				.setUsernames(idUserStr.substring(0, idUserStr.length() - 1));

		return itwUserGroup;
	}

	private ItwUserGroupBean prepareItwUserGroupBean(ItwUserGroup itwUserGroup,
			HttpServletRequest request) {

		String string = null;
		String temp = new String(itwUserGroup.getUsernames());
		StringBuffer buffer = new StringBuffer();
		strings = new ArrayList<ItwUserGroupBean>();
		ItwUserGroupBean bean = new ItwUserGroupBean();

		String[] splitId = itwUserGroup.getUsernames().split(",");

		ItwUser itwUser = new ItwUser();
		for (int i = 0; i < splitId.length; i++) {
			ItwUserGroupBean newbean = new ItwUserGroupBean();
			itwUser = itwUserService.getItwUser(Integer.parseInt(splitId[i]));
			newbean.setUsernames(itwUser.getId().toString());
			newbean.setUsernamesdisplay(itwUser.getUserid());
			buffer.append(newbean.getUsernames()).append(",");

			strings.add(newbean);
		}

		String[] tempArray = buffer.substring(0, buffer.length() - 1)
				.split(",");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		for (int i = 0; i < tempArray.length; i++) {
			bean = new ItwUserGroupBean();
			string = tempArray[i];
			System.out.println(string);

			itwUserBean = prepareListofItwUser(
					itwUserService.listItwUsers(langId),
					buffer.substring(0, buffer.length() - 1));

			bean.setLangId(itwUserGroup.getLangId());

			bean.setGroupname(itwUserGroup.getGroupname());
			bean.setId(itwUserGroup.getId());

		}

		return bean;
	}

	private List<ItwUserGroupBean> prepareListofItwUserGroups(
			List<ItwUserGroup> itwUserGroups) {
		List<ItwUserGroupBean> beans = null;
		if (itwUserGroups != null && !itwUserGroups.isEmpty()) {

			beans = new ArrayList<ItwUserGroupBean>();
			ItwUserGroupBean bean = null;
			for (ItwUserGroup itwUserGroup : itwUserGroups) {

				bean = new ItwUserGroupBean();
				bean.setId(itwUserGroup.getId());
				bean.setGroupname(itwUserGroup.getGroupname());

				// bean.setClientid(itwUserGroup.getClientid());
				System.out.println(itwUserGroup.getGroupname());
				bean.setLangId(itwUserGroup.getLangId());
				System.out.println(itwUserGroup.getUsernames() + "GAnes");
				String[] splitId = itwUserGroup.getUsernames().split(",");
				List<ItwUser> modules = new ArrayList<ItwUser>();
				ItwUser module = new ItwUser();
				for (int i = 0; i < splitId.length; i++) {
					module = itwUserService.getItwUser(Integer
							.parseInt(splitId[i]));
					modules.add(module);
				}
				StringBuffer buffer = new StringBuffer();
				Iterator<ItwUser> iterator = modules.iterator();
				while (iterator.hasNext()) {
					ItwUser itwUser = (ItwUser) iterator.next();
					buffer.append(itwUser.getUserid()).append(",");
				}

				bean.setUsernames(buffer.substring(0, buffer.length() - 1));
				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwUserGroup prepareModelItwUserGroupforUpdate(
			ItwUserGroupBean itwUserGroupBean) {
		System.out.println("Inside Update");

		ItwUserGroup itwUserGroup = new ItwUserGroup();
		itwUserGroup.setGroupname(itwUserGroupBean.getGroupname());

		itwUserGroup.setLangId(itwUserGroupBean.getLangId());
		itwUserGroup.setId(itwUserGroupBean.getId());

		String tempStr = itwUserGroupBean.getUsernames();
		StringBuffer idUserStr = new StringBuffer();

		String[] parts = tempStr.split(",");
		int i = parts.length;

		for (int j = 0; j < i; j++) {
			List<ItwUser> listItwUser = itwUserService
					.getItwUserByShortName(parts[j]);
			for (ItwUser itwUser : listItwUser) {
				idUserStr = idUserStr.append(itwUser.getId()).append(",");
			}
		}

		itwUserGroup
				.setUsernames(idUserStr.substring(0, idUserStr.length() - 1));
		itwUserGroupBean.setId(null);
		return itwUserGroup;
	}

	private ItwUserGroup prepareModelItwUserGroupforDelete(
			ItwUserGroupBean itwUserGroupBean) {

		ItwUserGroup itwUserGroup = new ItwUserGroup();
		itwUserGroup.setId(itwUserGroupBean.getId());
		return itwUserGroup;
	}

	private List<ItwUserBean> prepareListofItwUser(List<ItwUser> itwUsers,
			String temp) {

		List<ItwUserBean> beans = null;
		if (itwUsers != null && !itwUsers.isEmpty()) {
			beans = new ArrayList<ItwUserBean>();
			ItwUserBean bean = null;
			for (ItwUser itwUser : itwUsers) {
				bean = new ItwUserBean();

				if (!(temp != null && temp.contains(itwUser.getId().toString()))) {
					bean.setId(itwUser.getId());

					bean.setUserid(itwUser.getUserid());
					bean.setLangid(itwUser.getLangid());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	private List<ItwUserBean> addprepareListofItwUser(List<ItwUser> itwUsers) {
		List<ItwUserBean> beans = null;
		if (itwUsers != null && !itwUsers.isEmpty()) {
			beans = new ArrayList<ItwUserBean>();
			ItwUserBean bean = null;
			for (ItwUser itwUser : itwUsers) {
				bean = new ItwUserBean();
				bean.setId(itwUser.getId());
				bean.setUserid(itwUser.getUserid());
				bean.setLangid(itwUser.getLangid());
				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwUserBean> prepareListofItwUser(List<ItwUser> itwUsers) {
		List<ItwUserBean> beans = null;
		if (itwUsers != null && !itwUsers.isEmpty()) {
			beans = new ArrayList<ItwUserBean>();
			ItwUserBean bean = null;
			for (ItwUser itwUser : itwUsers) {
				bean = new ItwUserBean();
				bean.setId(itwUser.getId());
				bean.setUserid(itwUser.getUserid());
				bean.setLangid(itwUser.getLangid());

				beans.add(bean);

			}
		}
		return beans;
	}

}
