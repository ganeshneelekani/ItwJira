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

import com.agileidc.itw.bean.ItwRoleBean;
import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.bean.ItwUserRolesBean;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserRoles;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwRolesService;
import com.agileidc.itw.service.ItwUserRolesService;
import com.agileidc.itw.service.ItwUserService;
import com.agileidc.itw.web.UserRolesValidator;

@Controller
public class ItwUserRoleController {

	@Autowired
	private ItwUserService itwUserService;
	@Autowired
	private ItwUserRolesService itwUserRoleService;
	@Autowired
	private ItwRolesService itwRolesService;

	List<ItwUserRolesBean> strings, UserId;
	List<ItwUserBean> itwUserBean = null;
	List<ItwRoleBean> ItwRoleBean = null;
	List<ItwUser> Users = new ArrayList<ItwUser>();
	List<ItwRoles> Roles = null;
	List<ItwRoles> roles = null;
	Integer id;
	Integer uservalue = 0;

	@RequestMapping(value = "/itwUserRolesConfigarationList", method = RequestMethod.GET)
	public ModelAndView platformsConfigList(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();

		// String langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwUserRolesBean> ItwUserRolesBean = prepareListofItwUserRoles(itwUserRoleService
				.listItwUserRoles(langId));
		if (ItwUserRolesBean != null) {
			// Collections.sort(ItwUserRolesBean,
			// ItwUserRolesBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					ItwUserRolesBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("UserRolesConfigListPage", model);
	}

	@RequestMapping(value = "/saveItwUserRoles", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") ItwUserRolesBean saveItwUserRolesBean,
			BindingResult result, HttpServletRequest request) {

		String langDesc = request.getParameter("langDesc");
		Map<String, Object> model = new HashMap<String, Object>();
		UserRolesValidator shortsValidator = new UserRolesValidator();
		shortsValidator.validate(saveItwUserRolesBean, result);
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		if (result.hasErrors()) {

			if (saveItwUserRolesBean.getRolename() != null
					&& (saveItwUserRolesBean.getUsername() == null || saveItwUserRolesBean
							.getUsername().equals("Select User"))) {

				List<ItwUserBean> itwUserBean = prepareListofItwUser(
						itwUserService.listItwUsers(langId),
						saveItwUserRolesBean.getUserid(), langId);

				List<ItwRoleBean> ItwRoleBean = prepareListofItwRoles(
						itwRolesService.listItwRoles(langId),
						saveItwUserRolesBean.getRoleid());

				String string = null;
				String temp = new String(saveItwUserRolesBean.getRolename());
				strings = new ArrayList<ItwUserRolesBean>();
				ItwUserRolesBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwUserRolesBean();
					string = tempArray[i];
					bean.setRolename(string);
					strings.add(bean);

				}

				model.put("itwUserRoles", saveItwUserRolesBean.getUsername());
				model.put("itwUser", itwUserBean);
				model.put("itwRoles", ItwRoleBean);
				model.put("itwUserRolesUsers", strings);
			}

			if (!(saveItwUserRolesBean.getUsername() == null || saveItwUserRolesBean
					.getUsername().equals("Select User"))
					&& saveItwUserRolesBean.getRolename() == null) {

				List<ItwRoleBean> ItwRoleBean = addprepareListofItwRoles(itwRolesService
						.listItwRoles(langId));

				itwUserBean = prepareListofItwUser(itwUserService
						.listItwUsers(langId));

				model.put("itwUserRole", saveItwUserRolesBean.getUsername());
				model.put("itwUser", itwUserBean);
				model.put("itwRoles", ItwRoleBean);

			}
			if (saveItwUserRolesBean.getUsername().equals("Select User")
					&& saveItwUserRolesBean.getRolename() == null) {

			
				List<ItwUserBean> itwUserBean = prepareListofItwUser(itwUserService
						.listItwUsers(langId));
				List<ItwRoleBean> ItwRoleBean = addprepareListofItwRoles(itwRolesService
						.listItwRoles(langId));

				model.put("itwRoles", ItwRoleBean);
				model.put("itwUser", itwUserBean);
			} else {
			}
			return new ModelAndView("addItwUserRoles", model);
		} else {

			try {

				List<ItwRoles> listItwRoles1;
				String[] splitIdRoles = saveItwUserRolesBean.getRolename()
						.split(",");
				for (int j = 0; j < splitIdRoles.length; j++) {
					listItwRoles1 = itwRolesService.getItwRoleByShortName(
							splitIdRoles[j], langId);
					Iterator<ItwRoles> rolevalue = listItwRoles1.iterator();

					while (rolevalue.hasNext()) {
						ItwRoles itwRoles = (ItwRoles) rolevalue.next();
						ItwUserRoles itwUserRole = prepareModelItwUserRoles(
								saveItwUserRolesBean, langId);
						itwUserRole.setRoleid(itwRoles.getId());
						itwUserRoleService.addItwUserRoles(itwUserRole);
					}

				}

			} catch (RuntimeException runtimeException) {
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue(
							"rolename",
							"rolename.unique",
							"Username and Rolename combination already in use, enter a different Username and Roles");

					List<ItwRoles> ItwRole = itwRolesService
							.getItwRoleByShortName(
									saveItwUserRolesBean.getRolename(), langId);
					Iterator<ItwRoles> iterator = ItwRole.iterator();
					while (iterator.hasNext()) {
						ItwRoles ItwRole2 = (ItwRoles) iterator.next();
						saveItwUserRolesBean.setRoleid(ItwRole2.getId());
					}

					List<ItwUser> newItwUser = itwUserService
							.getItwUserByShortName(saveItwUserRolesBean
									.getUsername());

					Iterator<ItwUser> itwUser = newItwUser.iterator();
					while (itwUser.hasNext()) {
						ItwUser itwUser1 = (ItwUser) itwUser.next();
						saveItwUserRolesBean.setUserid(itwUser1.getId());
					}

					List<ItwUserBean> itwUserBean = prepareListofItwUser(itwUserService
							.listItwUsers(langId));

					List<ItwRoleBean> ItwRoleBean = prepareListofItwRoles(
							itwRolesService.listItwRoles(langId),
							saveItwUserRolesBean.getRolename());

					String string = null;
					String temp = new String(saveItwUserRolesBean.getRolename());
					strings = new ArrayList<ItwUserRolesBean>();
					ItwUserRolesBean bean = null;
					String[] tempArray = temp.split(",");
					for (int i = 0; i < tempArray.length; i++) {
						bean = new ItwUserRolesBean();
						string = tempArray[i];
						bean.setRolename(string);
						strings.add(bean);
					}
					model.put("itwUserRole", saveItwUserRolesBean.getUsername());
					model.put("itwUser", itwUserBean);
					model.put("itwRoles", ItwRoleBean);
					model.put("itwnewUserRole", strings);

					return new ModelAndView("addItwUserRoles", model);
				}
			}

			String langType = saveItwUserRolesBean.getLangDesc();

			return new ModelAndView(
					"redirect:/itwUserRolesConfigarationList.html");
		}

	}

	

	@RequestMapping(value = "/updateItwUserRoles", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("command") ItwUserRolesBean ItwUserRolesBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();
		UserRolesValidator shortsValidator = new UserRolesValidator();
		shortsValidator.validate(ItwUserRolesBean, result);

		Integer value = ItwUserRolesBean.getId();

		if (result.hasErrors()) {

			if (ItwUserRolesBean.getRolename() != null
					&& (ItwUserRolesBean.getUsername() == null || ItwUserRolesBean
							.getUsername().equals("Select User"))) {

				List<ItwUserBean> itwUserBean = prepareListofItwUser(
						itwUserService.listItwUsers(langId),
						ItwUserRolesBean.getRoleid(), langId);

				List<ItwRoleBean> ItwRoleBean = prepareListofItwRoles(
						itwRolesService.listItwRoles(langId),
						ItwUserRolesBean.getRoleid());
				String string = null;
				String temp = new String(ItwUserRolesBean.getRolename());
				strings = new ArrayList<ItwUserRolesBean>();
				ItwUserRolesBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwUserRolesBean();
					string = tempArray[i];
					bean.setRolename(string);
					strings.add(bean);

				}
				model.put("itwUserRoles", ItwUserRolesBean.getUsername());
				model.put("itwUser", itwUserBean);
				model.put("itwRoles", ItwRoleBean);
				model.put("itwUserRolesUsers", strings);
			}

			if (!(ItwUserRolesBean.getUsername() == null || ItwUserRolesBean
					.getUsername().equals("Select User"))
					&& ItwUserRolesBean.getRolename() == null) {

				List<ItwRoleBean> ItwRoleBean = addprepareListofItwRoles(itwRolesService
						.listItwRoles(langId));

				itwUserBean = prepareListofItwUser(
						itwUserService.listItwUsers(langId),
						ItwUserRolesBean.getUserid(), langId);

				model.put("itwUserRoles", ItwUserRolesBean.getUsername());
				model.put("itwUser", itwUserBean);
				model.put("itwRoles", ItwRoleBean);

				model.put("itwUserRolesUsers", strings);

			}
			if (ItwUserRolesBean.getUsername().equals("Select User")
					&& ItwUserRolesBean.getRolename() == null) {

				List<ItwUserBean> itwUserBean = prepareListofItwUser(itwUserService
						.listItwUsers(langId));
				List<ItwRoleBean> ItwRoleBean = addprepareListofItwRoles(itwRolesService
						.listItwRoles(langId));

				model.put("itwRoles", ItwRoleBean);
				model.put("itwUser", itwUserBean);
			} else {
			}
			return new ModelAndView("editItwUserRoles", model);
		}
		try {
			List<ItwRoles> listItwRoles1;
		
			
			listItwRoles1 = itwRolesService.getItwRoleByShortName(ItwUserRolesBean.getRolename(), langId);
			Iterator<ItwRoles> rolevalue = listItwRoles1.iterator();
			while (rolevalue.hasNext()) {
				ItwRoles itwRoles = (ItwRoles) rolevalue.next();
				ItwUserRoles itwUserRole = prepareModelItwUserRolesforUpdate(
						ItwUserRolesBean, langId);
				itwUserRole.setRoleid(itwRoles.getId());
				itwUserRoleService.addItwUserRoles(itwUserRole);
			}
		
		
		}

		catch (RuntimeException runtimeException) {

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				result.rejectValue(
						"username",
						"username.unique",
						"Username and Rolename combination already  in use, enter a different UserName and Role Name");

				// Integer langId = (Integer)
				// request.getSession().getAttribute("langId");

				ItwUserRoles newItwUserRoles = itwUserRoleService
						.getItwUserRoles(ItwUserRolesBean.getId());
				List<ItwRoles> itwRoles = itwRolesService
						.getItwRoleByShortName(ItwUserRolesBean.getRolename(),
								langId);

				Iterator<ItwRoles> iterator = itwRoles.iterator();
				while (iterator.hasNext()) {
					ItwRoles itwRoles2 = (ItwRoles) iterator.next();
					newItwUserRoles.setRoleid(itwRoles2.getId());
				}
				ItwUserRolesBean newUserBean = prepareItwUserRolesBean(
						newItwUserRoles, langId);
				newUserBean.setId(value);
				model.put("itwClient", newUserBean);

				String string = null;
				String temp = new String(ItwUserRolesBean.getRolename());
				strings = new ArrayList<ItwUserRolesBean>();
				ItwUserRolesBean bean = null;
				String[] tempArray = temp.split(",");
				for (int i = 0; i < tempArray.length; i++) {
					bean = new ItwUserRolesBean();
					string = tempArray[i];
					bean.setRolename(string);
					strings.add(bean);

				}
				model.put("itwUserRole", ItwUserRolesBean);
				model.put("itwUser", itwUserBean);
				model.put("itwRoles", ItwRoleBean);
				model.put("itwnewUserRole", strings);

				return new ModelAndView("editItwUserRoles", model);
			}
		}

		return new ModelAndView("redirect:/itwUserRolesConfigarationList.html");
	}

	@RequestMapping(value = "/addItwUserRoles", method = RequestMethod.GET)
	public ModelAndView addItwUserRoles(
			@ModelAttribute("command") ItwUserRolesBean ItwUserRolesBean,
			BindingResult result, HttpServletRequest request) {
		// langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwUserBean> itwUserBean = addprepareListofItwUser(itwUserService
				.listItwUsers(langId));

		List<ItwRoleBean> ItwRoleBean = addprepareListofItwRoles(itwRolesService
				.listItwRoles(langId));

		if (itwUserBean != null && ItwRoleBean != null) {

			model.put("itwUser", itwUserBean);
			model.put("itwRoles", ItwRoleBean);

		} else {
			model.put("itwUser", null);
			model.put("itwRoles", null);
		}

		return new ModelAndView("addItwUserRoles", model);

	}

	@RequestMapping(value = "/deleteItwUserRoles", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") ItwUserRolesBean ItwUserRolesBean,
			BindingResult result, HttpServletRequest request) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		String langDesc = request.getParameter("langDesc");
		Map<String, Object> model = new HashMap<String, Object>();
		String shortNametemp = "UserRole ID " + ItwUserRolesBean.getId()
				+ " already in use, cannot delete";

		// try{
		itwUserRoleService.deleteItwUserRoles(prepareModelItwUserRoles(
				ItwUserRolesBean, langId));

		return new ModelAndView(
				"redirect:/itwUserRolesConfigarationList.html?langDesc="
						+ langDesc);

	}

	@RequestMapping(value = "/editItwUserRoles", method = RequestMethod.GET)
	public ModelAndView editItwUserRoles(
			@ModelAttribute("command") ItwUserRolesBean ItwUserRolesBean,
			BindingResult result, HttpServletRequest request) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();

		model.put(
				"itwUserRole",
				prepareItwUserRolesBean(itwUserRoleService
						.getItwUserRoles(ItwUserRolesBean.getId()), langId));

		model.put("itwRoles", ItwRoleBean);
		model.put("itwUser", itwUserBean);
		Iterator<ItwUserBean> iterator = itwUserBean.iterator();
		while (iterator.hasNext()) {
			ItwUserBean itwUserBean = (ItwUserBean) iterator.next();
			System.out.println(itwUserBean.getUserid());
		}

		return new ModelAndView("editItwUserRoles", model);
	}

	private ItwUserRoles prepareModelItwUserRoles(
			ItwUserRolesBean ItwUserRolesBean, int langId) {
		// Integer langId = (Integer)
		// request.getSession().getAttribute("langId");

		ItwUserRoles itwUserRole = new ItwUserRoles();
		List<ItwUser> listItwUser = itwUserService
				.getItwUserByShortName(ItwUserRolesBean.getUsername());
		Iterator<ItwUser> iterator = listItwUser.iterator();
		while (iterator.hasNext()) {
			ItwUser itwUser = (ItwUser) iterator.next();
			itwUserRole.setUserid(itwUser.getId());
		}

		List<ItwRoles> listItwRoles = itwRolesService.getItwRoleByShortName(
				ItwUserRolesBean.getRolename(), langId);
		Iterator<ItwRoles> roleiterator = listItwRoles.iterator();
		while (roleiterator.hasNext()) {
			ItwRoles itwRole = (ItwRoles) roleiterator.next();
			itwUserRole.setRoleid(itwRole.getId());
		}

		itwUserRole.setId(ItwUserRolesBean.getId());
		itwUserRole.setLangId(langId);
		ItwUserRolesBean.setId(null);
		return itwUserRole;
	}

	private ItwUserRoles prepareModelItwUserRolesforUpdate(
			ItwUserRolesBean itwUserRolesBean, int langId) {

		ItwUserRoles itwUserRole = new ItwUserRoles();
		System.out
				.println("itwUserRolesBean.getUsername() is......................66666666666666:"
						+ itwUserRolesBean.getUsername() + ":");
		List<ItwUser> listItwUser = itwUserService
				.getItwUserByShortName(itwUserRolesBean.getUsername());
		Iterator<ItwUser> iterator = listItwUser.iterator();
		while (iterator.hasNext()) {
			ItwUser itwUser = (ItwUser) iterator.next();
			
			itwUserRole.setUserid(itwUser.getId());
		}

		List<ItwRoles> listItwRoles = itwRolesService.getItwRoleByShortName(
				itwUserRolesBean.getRolename(), langId);
		Iterator<ItwRoles> roleiterator = listItwRoles.iterator();
		while (iterator.hasNext()) {
			ItwRoles itwRole = (ItwRoles) roleiterator.next();
			itwUserRole.setRoleid(itwRole.getId());
		}

		itwUserRole.setId(itwUserRolesBean.getId());
		itwUserRole.setLangId(langId);

		itwUserRolesBean.setId(itwUserRole.getId());

		return itwUserRole;
	}

	private List<ItwUserRolesBean> prepareListofItwUserRoles(
			List<ItwUserRoles> itwUserRoles) {
		List<ItwUserRolesBean> beans = null;
		if (itwUserRoles != null && !itwUserRoles.isEmpty()) {
			beans = new ArrayList<ItwUserRolesBean>();
			ItwUserRolesBean bean = null;
			for (ItwUserRoles itwUserRole : itwUserRoles) {
				bean = new ItwUserRolesBean();
				bean.setId(itwUserRole.getId());
				ItwRoles Role = new ItwRoles();
				roles = new ArrayList<ItwRoles>();
				Role = itwRolesService.getItwRoles(itwUserRole.getRoleid());
				ItwUser listItwUser = itwUserService.getItwUser(itwUserRole
						.getUserid());
				bean.setUsername(listItwUser.getUserid());
				bean.setLangId(itwUserRole.getLangId());
				bean.setRolename(Role.getRolename());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwUserRolesBean prepareItwUserRolesBean(ItwUserRoles itwUserRole,
			int langId) {
		ItwUserRolesBean bean = new ItwUserRolesBean();
		bean = new ItwUserRolesBean();
		bean.setId(itwUserRole.getId());
		ItwRoles Role = new ItwRoles();
		roles = new ArrayList<ItwRoles>();

		Role = itwRolesService.getItwRoles(itwUserRole.getRoleid());
		bean.setRoleid(Role.getId());
		bean.setLangId(itwUserRole.getLangId());
		bean.setUserid(itwUserRole.getUserid());

		ItwUser listItwUser = itwUserService
				.getItwUser(itwUserRole.getUserid());

		ItwRoleBean = prepareListofItwRoles(
				itwRolesService.listItwRoles(bean.getLangId()),
				bean.getRoleid());

		itwUserBean = prepareListofItwUser(
				itwUserService.listItwUsers(bean.getLangId()),
				bean.getUserid(), langId);

		bean.setRolename(Role.getRolename());
		System.out.println(":::::::" + listItwUser.getUserid() + "::::::");
		bean.setUsername(listItwUser.getUserid());

		return bean;
	}

	private List<ItwUserBean> prepareListofItwUser(List<ItwUser> itwUsers,
			Integer temp, int langId) {

		List<ItwUserBean> beans = null;
		if (itwUsers != null && !itwUsers.isEmpty()) {
			beans = new ArrayList<ItwUserBean>();
			ItwUserBean bean = null;
			for (ItwUser itwUser : itwUsers) {
				bean = new ItwUserBean();

				if (!(temp != null && temp.equals(itwUser.getId()))) {
					bean.setId(itwUser.getId());

					bean.setUserid(itwUser.getUserid());
					bean.setLangid(langId);
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	private List<ItwRoleBean> prepareListofItwRoles(List<ItwRoles> itwRoless,
			Integer temp) {
		List<ItwRoleBean> beans = null;
		if (itwRoless != null && !itwRoless.isEmpty()) {
			beans = new ArrayList<ItwRoleBean>();
			ItwRoleBean bean = null;
			for (ItwRoles itwRoles : itwRoless) {
				bean = new ItwRoleBean();

				if (!(temp != null && temp.equals(itwRoles.getId()))) {
					bean.setId(itwRoles.getId());

					bean.setRolename(itwRoles.getRolename());
					bean.setLangid(itwRoles.getLangid());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	@RequestMapping(value = "/downloadItwUserRole", method = RequestMethod.GET)
	public String getDownloadPage() {

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/downloadpage.jsp
		return "downloadpage";
	}

	private List<ItwRoleBean> addprepareListofItwRoles(List<ItwRoles> itwRoless) {
		List<ItwRoleBean> beans = null;
		if (itwRoless != null && !itwRoless.isEmpty()) {
			beans = new ArrayList<ItwRoleBean>();
			ItwRoleBean bean = null;
			for (ItwRoles itwRoles : itwRoless) {
				bean = new ItwRoleBean();
				bean.setId(itwRoles.getId());
				bean.setRolename(itwRoles.getRolename());

				bean.setLangid(itwRoles.getLangid());

				beans.add(bean);

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
				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwRoleBean> prepareListofItwRoles(List<ItwRoles> itwRoless) {
		List<ItwRoleBean> beans = null;
		if (itwRoless != null && !itwRoless.isEmpty()) {
			beans = new ArrayList<ItwRoleBean>();
			ItwRoleBean bean = null;
			for (ItwRoles itwRoles : itwRoless) {
				bean = new ItwRoleBean();
				bean.setId(itwRoles.getId());
				bean.setRolename(itwRoles.getRolename());

				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwRoleBean> prepareListofItwRoles(List<ItwRoles> itwRoless,
			String temp) {
		List<ItwRoleBean> beans = null;
		if (itwRoless != null && !itwRoless.isEmpty()) {
			beans = new ArrayList<ItwRoleBean>();
			ItwRoleBean bean = null;
			for (ItwRoles itwRoles : itwRoless) {
				bean = new ItwRoleBean();

				if (!(temp != null && temp.hashCode()==itwRoles.getRolename().hashCode())) {
					bean.setId(itwRoles.getId());
					bean.setRolename(itwRoles.getRolename());
					bean.setLangid(itwRoles.getLangid());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

}
