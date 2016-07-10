package com.agileidc.itw.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.ItwCompanyBean;
import com.agileidc.itw.dao.ItwCompanyDAOTemp;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwUserIcon;
import com.agileidc.itw.service.ItwCompanyService;
import com.agileidc.itw.web.CompanyValidator;

@Controller
public class ItwCompanyController {

	@Autowired
	private ItwCompanyService itwCompanyService;
	@Autowired
	private ItwCompanyDAOTemp itwCompanyDAOTemp;

	@RequestMapping(value = "/saveItwCompany", method = RequestMethod.POST)
	public ModelAndView saveItwCompany(
			@ModelAttribute("command") ItwCompanyBean itwCompanyBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		CompanyValidator CompanyValidator = new CompanyValidator();
		CompanyValidator.validate(itwCompanyBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("addItwCompany");
		} else {
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwCompany itwCompany = prepareModel(itwCompanyBean, request,
						file);
				ItwUserIcon itwUserIcon = new ItwUserIcon();
				itwUserIcon.setIcon(itwCompany.getLogo());
				itwCompany.setLangId(langId.intValue());
				itwCompany.setLogo(null);

				itwCompanyService.addItwCompany(itwCompany, itwUserIcon);
			} catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Company (name) is being added for a new company in
				// table ITW_COMPANY
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("id", "id.unique",
							"Company Id is already in use, enter a different User Id");
					Map<String, Object> model = new HashMap<String, Object>();

					return new ModelAndView("addItwCompany", model);
				}

			}
			return new ModelAndView("redirect:/companyConfigList.html");
		}

	}

	@RequestMapping(value = "/itwCompanys", method = RequestMethod.GET)
	public ModelAndView listItwCompanys() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("companyConfigListPage", model);
	}

	@RequestMapping(value = "/addItwCompany", method = RequestMethod.GET)
	public ModelAndView addItwCompany(
			@ModelAttribute("command") ItwCompanyBean itwCompanyBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("addItwCompany", model);
	}

	@RequestMapping(value = "/editItwCompany", method = RequestMethod.GET)
	public ModelAndView editItwCompany(
			@ModelAttribute("command") ItwCompanyBean itwCompanyBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "";

		ItwCompany itwCompanytemp = itwCompanyService
				.getItwCompany(itwCompanyBean.getId());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwCompany", prepareItwCompanyBean(itwCompanyService
				.getItwCompany(itwCompanyBean.getId())));
		try {
			InputStream inputStream = null;
			OutputStream outputStream = null;
			fileName = request.getRealPath("") + "/images/" + "img"
					+ itwCompanyBean.getId().toString() + "company.png";
			outputStream = new FileOutputStream(fileName);

			ItwUserIcon itwUserIcon = itwCompanyDAOTemp.getItwCompany(
					itwCompanytemp.getLogoId(), outputStream);

			outputStream.flush();
			outputStream.close();
			model.put("filepath", "images/" + "img"
					+ itwCompanyBean.getId().toString() + "company.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("editItwCompany", model);
	}

	@RequestMapping(value = "/updateItwCompany", method = RequestMethod.POST)
	public ModelAndView updateCompany(
			@ModelAttribute("command") ItwCompanyBean itwCompanyBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		CompanyValidator CompanyValidator = new CompanyValidator();
		CompanyValidator.validate(itwCompanyBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("editItwCompany");
		} else {

			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			ItwCompany itwCompanytemp = itwCompanyService
					.getItwCompany(itwCompanyBean.getId());
			ItwCompany itwCompany = prepareModelItwCompanyforUpdate(itwCompanyBean);
			System.out.println("after prepare mode for update of company 1");
			if (file != null) {
				System.out.println("after prepare mode for update of company 2");
				if (file.getSize() > 0) {
					System.out.println("after prepare mode for update of company 3");

					try {
						System.out.println("after prepare mode for update of company 4");
						Blob blob = Hibernate.createBlob(file.getInputStream());
						itwCompany.setLogo(blob);

					} catch (RuntimeException runtimeException) {
						// catch unique constraint violation exception in case a
						// duplicate company name is being added for a existing
						// company in
						// table ITW_COMPANY
						if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
							System.out.println("after prepare mode for update of company 5");
							result.rejectValue("shortname", "shortname.unique",
									"Company already in use, enter a different Companys");
							Map<String, Object> model = new HashMap<String, Object>();
							ItwCompanyBean itwCompanyBean1 = prepareItwCompanyBeanForAdd();
							model.remove("itwCompanys1");
							model.put("itwCompanys1", itwCompanyBean1);
							return new ModelAndView("addItwCompany", model);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					System.out.println("after prepare mode for update of company 6");
					ItwUserIcon itwUserIcon = new ItwUserIcon();
					itwUserIcon.setIcon(itwCompany.getLogo());
					itwCompany.setLogo(null);
					itwCompany.setLangId(langId.intValue());
					System.out.println("after prepare mode for update of company 7");
					itwCompanyService.addItwCompany(itwCompany, itwUserIcon);

				} catch (HibernateException hibernateEx) {
					System.out.println("after prepare mode for update of company 8");
				}
			}
			System.out.println("after prepare mode for update of company 9");

		}
		System.out.println("after prepare mode for update of company 10");
		return new ModelAndView("redirect:/companyConfigList.html");
	}

	@RequestMapping(value = "/companyConfigList", method = RequestMethod.GET)
	public ModelAndView CompanyConfigList(HttpServletRequest request) {
		System.out.println("started Company config List page");
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwCompanyBean> itwCompanyBean = prepareListofItwCompanys(itwCompanyService
				.listItwCompanys(langId.intValue()));
		java.util.Collections.sort(itwCompanyBean,
				ItwCompanyBean.Comparators.ID);
		if (itwCompanyBean != null) {
			java.util.Collections.sort(itwCompanyBean,
					ItwCompanyBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwCompanyBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			System.out.println("value of page variable issssss" + page);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("companyConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwCompany", method = RequestMethod.GET)
	public ModelAndView deleteItwCompany(
			@ModelAttribute("command") ItwCompanyBean itwCompanyBean,
			BindingResult result, HttpServletRequest request) {
		String CompanyNametemp = "Company ID " + itwCompanyBean.getId()
				+ " already in use, cannot delete";
		try {
			System.out.println("Run Time");
			itwCompanyService
					.deleteItwCompany(prepareModelItwCompanyforDelete(itwCompanyBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// company id is being deleted which is in use in table ITW_COMPANY
			// as a
			// foreign key
			System.out.println("caught runtime exception isss"
					+ runtimeException.toString());

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				List<ItwCompanyBean> itwCompanyBeanList = prepareListofItwCompanys(itwCompanyService
						.listItwCompanys(langId.intValue()));
				if (itwCompanyBean != null) {
					java.util.Collections.sort(itwCompanyBeanList,
							ItwCompanyBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwCompanyBeanList);
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
						CompanyNametemp);
				return new ModelAndView("companyConfigListPage", model);
			}
			System.out.println("Out of if ");

		}

		return new ModelAndView("redirect:/companyConfigList.html");
	}

	private ItwCompany prepareModel(ItwCompanyBean itwCompanyBean,
			HttpServletRequest request, MultipartFile file) {

		ItwCompany itwCompany = new ItwCompany();

		itwCompany.setName(itwCompanyBean.getName());
		itwCompany.setId(itwCompanyBean.getId());
		itwCompany.setContact(itwCompanyBean.getContact());
		itwCompany.setDescription(itwCompanyBean.getDescription());
		itwCompany.setEmail(itwCompanyBean.getEmail());

		itwCompany.setPhone(itwCompanyBean.getPhone());
		itwCompany
				.setRegistrationNumber(itwCompanyBean.getRegistrationNumber());
		itwCompany.setWebsite(itwCompanyBean.getWebsite());
		if (file.getSize() > 0) {

			System.out.println("Image selected by user...1");

			try {
				System.out.println("Image selected by user...3");
				Blob blob = Hibernate.createBlob(file.getInputStream());
				itwCompany.setLogo(blob);

			} catch (IOException e) {
				System.out.println("Image selected by user...4");
				e.printStackTrace();
			}

			System.out.println("Image selected by user...5");
		} else {
			System.out.println("image not selected by user....1");

			InputStream inputStream = null;
			try {
				System.out.println("image not selected by user....2");
				System.out.println("path for default image is "
						+ request.getRealPath("") + "/images/blnkuser.png");
				inputStream = new FileInputStream(request.getRealPath("")
						+ "/images/blnkuser.png");
				try {
					System.out
							.println("no image selected for userrrr so default image being loaded");
					Blob blob = Hibernate.createBlob(inputStream);
					itwCompany.setLogo(blob);

				} catch (IOException e) {
					System.out.println("image not selected by user....3");
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("image not selected by user....4");
				// TODO Auto-generated catch block
				System.out
						.println("File not found exception for default image if user does not select the image");
				e.printStackTrace();
			}
			System.out.println("image not selected by user....5");
		}
		System.out.println("after else part....");
		itwCompany.setId(null);
		return itwCompany;
	}

	private ItwCompanyBean prepareItwCompanyBean(ItwCompany itwCompany) {

		ItwCompanyBean bean = new ItwCompanyBean();

		bean.setName(itwCompany.getName());
		bean.setId(itwCompany.getId());
		bean.setContact(itwCompany.getContact());
		bean.setDescription(itwCompany.getDescription());
		bean.setEmail(itwCompany.getEmail());
		bean.setLogo(itwCompany.getLogo());
		bean.setPhone(itwCompany.getPhone());
		bean.setRegistrationNumber(itwCompany.getRegistrationNumber());
		bean.setWebsite(itwCompany.getWebsite());
		bean.setLogoId(itwCompany.getLogoId());
		return bean;
	}

	private List<ItwCompanyBean> prepareListofItwCompanys(
			List<ItwCompany> itwCompanys) {
		List<ItwCompanyBean> beans = null;
		if (itwCompanys != null && !itwCompanys.isEmpty()) {

			beans = new ArrayList<ItwCompanyBean>();
			ItwCompanyBean bean = null;
			for (ItwCompany itwCompany : itwCompanys) {

				bean = new ItwCompanyBean();
				bean.setId(itwCompany.getId());

				bean.setName(itwCompany.getName());

				bean.setContact(itwCompany.getContact());
				bean.setDescription(itwCompany.getDescription());
				bean.setEmail(itwCompany.getEmail());
				bean.setLogo(itwCompany.getLogo());
				bean.setPhone(itwCompany.getPhone());
				bean.setRegistrationNumber(itwCompany.getRegistrationNumber());
				bean.setWebsite(itwCompany.getWebsite());

				bean.setLogoId(itwCompany.getLogoId());

				beans.add(bean);

			}
		}
		return beans;

	}

	private ItwCompany prepareModelItwCompanyforUpdate(
			ItwCompanyBean itwCompanyBean) {

		ItwCompany itwCompany = new ItwCompany();
		itwCompany.setId(itwCompanyBean.getId());
		itwCompany.setName(itwCompanyBean.getName());
		itwCompany.setContact(itwCompanyBean.getContact());
		itwCompany.setDescription(itwCompanyBean.getDescription());
		itwCompany.setEmail(itwCompanyBean.getEmail());
		itwCompany.setPhone(itwCompanyBean.getPhone());
		itwCompany
				.setRegistrationNumber(itwCompanyBean.getRegistrationNumber());
		itwCompany.setWebsite(itwCompanyBean.getWebsite());
		itwCompany.setLogoId(itwCompanyBean.getLogoId());
		return itwCompany;
	}

	private ItwCompany prepareModelItwCompanyforDelete(
			ItwCompanyBean itwCompanyBean) {
		ItwCompany itwCompany = new ItwCompany();
		itwCompany.setId(itwCompanyBean.getId());
		return itwCompany;
	}

	private ItwCompanyBean prepareItwCompanyBeanForAdd() {
		ItwCompanyBean bean = new ItwCompanyBean();

		return bean;
	}
}
