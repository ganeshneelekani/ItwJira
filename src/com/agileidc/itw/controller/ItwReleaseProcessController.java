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

import com.agileidc.itw.bean.ItwReleaseProcessBean;
import com.agileidc.itw.dao.ItwReleaseProcessDAOTemp;
import com.agileidc.itw.model.ItwReleaseProcess;
import com.agileidc.itw.service.ItwReleaseProcessService;
import com.agileidc.itw.web.ItwReleaseProcessValidator;

@Controller
public class ItwReleaseProcessController {

	@Autowired
	private ItwReleaseProcessService itwReleaseProcessService;
	@Autowired
	private ItwReleaseProcessDAOTemp itwReleaseProcessDAOTemp;

	MultipartFile tempupdatefile;
	MultipartFile tempsavefile;
	Blob tempEditBlob;

	@RequestMapping(value = "/saveItwReleaseProcess", method = RequestMethod.POST)
	public ModelAndView saveItwReleaseProcess(
			@ModelAttribute("command") ItwReleaseProcessBean itwReleaseProcessBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		//ItwReleaseProcessValidator ItwReleaseProcessValidator = new ItwReleaseProcessValidator();
		//ItwReleaseProcessValidator.validate(itwReleaseProcessBean, result);
		/*if (result.hasErrors()) {
			return new ModelAndView("addItwReleaseProcess");
		} else {*/

			try {

				ItwReleaseProcess itwReleaseProcess = prepareModel(
						itwReleaseProcessBean, request, file);

				itwReleaseProcess.setLangId(langId);

				itwReleaseProcessService
						.addItwReleaseProcess(itwReleaseProcess);
			} catch (RuntimeException runtimeException) {

				System.out.println("-----------dksjnfdksj-------------"
						+ runtimeException.toString());

				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("step", "step.unique",
							"step  is already in use, enter a different step");
					Map<String, Object> model = new HashMap<String, Object>();

					return new ModelAndView("addItwReleaseProcess", model);
				}

			}
			return new ModelAndView(
					"redirect:/itwReleaseProcessConfigList.html");
	//	}

	}

	@RequestMapping(value = "/itwReleaseProcesss", method = RequestMethod.GET)
	public ModelAndView listItwReleaseProcesss() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("ReleaseProcessConfigListPage", model);
	}

	@RequestMapping(value = "/addItwReleaseProcess", method = RequestMethod.GET)
	public ModelAndView addItwReleaseProcess(
			@ModelAttribute("command") ItwReleaseProcessBean itwReleaseProcessBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("addItwReleaseProcess", model);
	}

	@RequestMapping(value = "/editItwReleaseProcess", method = RequestMethod.GET)
	public ModelAndView editItwReleaseProcess(
			@ModelAttribute("command") ItwReleaseProcessBean itwReleaseProcessBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "";
		ItwReleaseProcessBean itwReleaseProcesstempbean = prepareItwReleaseProcessBean(itwReleaseProcessService
				.getItwReleaseProcess(itwReleaseProcessBean.getId()));

		tempEditBlob = itwReleaseProcesstempbean.getFile();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itwReleaseProcess", itwReleaseProcesstempbean);
		try {

			InputStream inputStream = null;
			OutputStream outputStream = null;
			fileName = request.getRealPath("") + "/images/" + "img"
					+ itwReleaseProcesstempbean.getStep().toString()
					+ "ReleaseProcess.png";
			outputStream = new FileOutputStream(fileName);
			ItwReleaseProcess itwReleaseProcess = itwReleaseProcessDAOTemp
					.getItwReleaseProcess(itwReleaseProcesstempbean.getStep(),
							outputStream);
			
			outputStream.flush();
			outputStream.close();
			model.put("filepath", "images/" + "img"
					+ itwReleaseProcesstempbean.getStep().toString()
					+ "ReleaseProcess.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("editItwReleaseProcess", model);
	}

	@RequestMapping(value = "/updateItwReleaseProcess", method = RequestMethod.POST)
	public ModelAndView updateReleaseProcess(
			@ModelAttribute("command") ItwReleaseProcessBean itwReleaseProcessBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		ItwReleaseProcess newtempitwReleaseProcess = new ItwReleaseProcess();

		String fileName = "";
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		ItwReleaseProcess newitwReleaseProcess = prepareModelItwReleaseProcessforUpdate(
				itwReleaseProcessBean, file, request);
		newitwReleaseProcess.setLangId(langId);

		try {

			InputStream inputStream = null;
			OutputStream outputStream = null;
			fileName = request.getRealPath("") + "/images/" + "img"
					+ newitwReleaseProcess.getStep().toString()
					+ "ReleaseProcess.png";
			outputStream = new FileOutputStream(fileName);

			newtempitwReleaseProcess = itwReleaseProcessDAOTemp
					.getItwReleaseProcess(newitwReleaseProcess.getStep(),
							outputStream);
			outputStream.flush();
			outputStream.close();
			model.put("filepath", "images/" + "img"
					+ newitwReleaseProcess.getStep().toString()
					+ "ReleaseProcess.png");

			itwReleaseProcessService.addItwReleaseProcess(newitwReleaseProcess);

		} catch (RuntimeException runtimeException) {

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				result.rejectValue("shortname", "shortname.unique",
						"ReleaseProcess already in use, enter a different ReleaseProcesss");
				result.rejectValue("step", "step.unique",
						"step already in use, enter a different step");
				ItwReleaseProcessBean itwReleaseProcessBean1 = prepareItwReleaseProcessBeanForAdd();
				model.remove("itwReleaseProcesss1");
				model.put("itwReleaseProcesss1", itwReleaseProcessBean1);
				return new ModelAndView("addItwReleaseProcess", model);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/itwReleaseProcessConfigList.html");
	}

	@RequestMapping(value = "/itwReleaseProcessConfigList", method = RequestMethod.GET)
	public ModelAndView ReleaseProcessConfigList(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwReleaseProcessBean> itwReleaseProcessBean = prepareListofItwReleaseProcesss(itwReleaseProcessService
				.listItwReleaseProcess(langId.intValue()));

		if (itwReleaseProcessBean != null) {

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwReleaseProcessBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("itwReleaseProcessConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwReleaseProcess", method = RequestMethod.GET)
	public ModelAndView deleteItwReleaseProcess(
			@ModelAttribute("command") ItwReleaseProcessBean itwReleaseProcessBean,
			BindingResult result, HttpServletRequest request) {
		String ReleaseProcessNametemp = "ReleaseProcess ID "
				+ itwReleaseProcessBean.getId()
				+ " already in use, cannot delete";
		try {
			itwReleaseProcessService
					.deleteItwReleaseProcess(prepareModelItwReleaseProcessforDelete(itwReleaseProcessBean));
		}

		catch (RuntimeException runtimeException) {

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				Map<String, Object> model = new HashMap<String, Object>();
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				List<ItwReleaseProcessBean> itwReleaseProcessBeanList = prepareListofItwReleaseProcesss(itwReleaseProcessService
						.listItwReleaseProcess(langId.intValue()));
				if (itwReleaseProcessBean != null) {

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwReleaseProcessBeanList);
					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);
					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}

				result.rejectValue("shortname", "shortname.inuse",
						ReleaseProcessNametemp);
				return new ModelAndView("itwReleaseProcessConfigList", model);
			}

		}

		return new ModelAndView("redirect:/itwReleaseProcessConfigList.html");
	}

	private ItwReleaseProcess prepareModel(
			ItwReleaseProcessBean itwReleaseProcessBean,
			HttpServletRequest request, MultipartFile file) {

		ItwReleaseProcess itwReleaseProcess = new ItwReleaseProcess();
		itwReleaseProcess.setId(itwReleaseProcessBean.getId());
		itwReleaseProcess
				.setDescription(itwReleaseProcessBean.getDescription());
		itwReleaseProcess.setStep(itwReleaseProcessBean.getStep());
		itwReleaseProcess.setLangId(itwReleaseProcessBean.getLangId());
		if (file.getSize() > 0) {

			try {
				Blob blob = Hibernate.createBlob(file.getInputStream());
				itwReleaseProcess.setFile(blob);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(request.getRealPath("")
						+ "/images/blnkuser.png");
				try {
					Blob blob = Hibernate.createBlob(inputStream);
					itwReleaseProcess.setFile(blob);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		itwReleaseProcess.setId(null);
		return itwReleaseProcess;
	}

	private ItwReleaseProcessBean prepareItwReleaseProcessBean(
			ItwReleaseProcess itwReleaseProcess) {

		ItwReleaseProcessBean bean = new ItwReleaseProcessBean();

		bean.setId(itwReleaseProcess.getId());
		bean.setStep(itwReleaseProcess.getStep());
		bean.setDescription(itwReleaseProcess.getDescription());
		bean.setFile(itwReleaseProcess.getFile());


return bean;
	}

	private List<ItwReleaseProcessBean> prepareListofItwReleaseProcesss(
			List<ItwReleaseProcess> itwReleaseProcesss) {
		List<ItwReleaseProcessBean> beans = null;
		if (itwReleaseProcesss != null && !itwReleaseProcesss.isEmpty()) {

			beans = new ArrayList<ItwReleaseProcessBean>();
			ItwReleaseProcessBean bean = null;
			for (ItwReleaseProcess itwReleaseProcess : itwReleaseProcesss) {

				bean = new ItwReleaseProcessBean();
				bean.setId(itwReleaseProcess.getId());

				bean.setDescription(itwReleaseProcess.getDescription());
				bean.setStep(itwReleaseProcess.getStep());
				bean.setFile(itwReleaseProcess.getFile());
				beans.add(bean);

			}
		}
		return beans;

	}

	private ItwReleaseProcess prepareModelItwReleaseProcessforUpdate(
			ItwReleaseProcessBean itwReleaseProcessBean, MultipartFile file,
			HttpServletRequest request) {

		ItwReleaseProcess itwReleaseProcess = new ItwReleaseProcess();
		itwReleaseProcess.setId(itwReleaseProcessBean.getId());
		itwReleaseProcess.setStep(itwReleaseProcessBean.getStep());
		itwReleaseProcess
				.setDescription(itwReleaseProcessBean.getDescription());
		if (file.getSize() > 0) {

			try {
				Blob blob = Hibernate.createBlob(file.getInputStream());
				itwReleaseProcess.setFile(blob);
				tempupdatefile = file;

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			try {

				Blob blob = Hibernate.createBlob(tempupdatefile
						.getInputStream());
				itwReleaseProcess.setFile(blob);

			} catch (Exception e) {


				itwReleaseProcess.setFile(tempEditBlob);
			}
		}

		return itwReleaseProcess;
	}

	private ItwReleaseProcess prepareModelItwReleaseProcessforDelete(
			ItwReleaseProcessBean itwReleaseProcessBean) {
		ItwReleaseProcess itwReleaseProcess = new ItwReleaseProcess();
		itwReleaseProcess.setId(itwReleaseProcessBean.getId());
		return itwReleaseProcess;
	}

	private ItwReleaseProcessBean prepareItwReleaseProcessBeanForAdd() {
		ItwReleaseProcessBean bean = new ItwReleaseProcessBean();

		return bean;
	}

}
