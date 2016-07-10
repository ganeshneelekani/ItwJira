package com.agileidc.itw.controller;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import oracle.sql.CLOB;

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
import com.agileidc.itw.bean.ItwReleaseDocumentBean;
import com.agileidc.itw.bean.ItwReleaseProcessBean;
import com.agileidc.itw.bean.ItwReleasesBean;
import com.agileidc.itw.bean.ItwTaskBugBean;
import com.agileidc.itw.model.ItwCheckOutData;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwModuleTree;
import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwReleaseProcess;
import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.service.ItwCheckOutDataService;
import com.agileidc.itw.service.ItwModuleService;
import com.agileidc.itw.service.ItwModuleTreeService;
import com.agileidc.itw.service.ItwReleaseDocumentService;
import com.agileidc.itw.service.ItwReleaseProcessService;
import com.agileidc.itw.service.ItwReleasesService;
import com.agileidc.itw.service.ItwTaskBugService;
import com.agileidc.itw.web.ReleaseDocumentValidator;

@Controller
public class ItwReleaseDocumentController {

	@Autowired
	private ItwReleaseDocumentService itwReleaseDocumentService;
	@Autowired
	private ItwReleasesService itwReleasesService;
	@Autowired
	private ItwTaskBugService itwTaskBugService;
	@Autowired
	private ItwCheckOutDataService itwCheckOutDataService;
	@Autowired
	private ItwModuleTreeService itwModuleTreeService;
	@Autowired
	private ItwModuleService itwModuleService;
	
	@Autowired
	private ItwReleaseProcessService itwReleaseProcessService;
	// String langDesc = null;
	List<ItwReleaseDocumentBean> strings;
	List<ItwModuleBean> itwModuleBean = null;
	List<ItwReleasesBean> itwReleasesBean = null;
	List<ItwTaskBug> listitwTaskBugsDefects;
	
	@RequestMapping(value = "/saveItwReleaseDocument", method = RequestMethod.POST)
	public ModelAndView saveItwReleaseDocument(
			@ModelAttribute("command") ItwReleaseDocumentBean itwReleaseDocumentBean,
			BindingResult result, HttpServletRequest request) {
		// langDesc = request.getParameter("langDesc");
		ReleaseDocumentValidator ReleaseDocumentValidator = new ReleaseDocumentValidator();
		ReleaseDocumentValidator.validate(itwReleaseDocumentBean, result);
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		if (result.hasErrors()) {

			if (itwReleaseDocumentBean.getReleaseName() != null) {
				List<ItwReleasesBean> itwReleasesBean = addprepareListofItwReleases(itwReleasesService
						.listItwReleases(langId));

				model.put("itwReleases", itwReleasesBean);
			}

			model.put("itwReleaseDocumentModules", strings);

			if (itwReleaseDocumentBean.getReleaseName() == null) {
				result.rejectValue("releaseName", "releaseName.inuse",
						"Release are not configured , Please configure release");
			}
			return new ModelAndView("addItwReleaseDocument", model);

		} else {
			try {

				itwReleaseDocumentBean.setReleaseName(itwReleaseDocumentBean
						.getReleaseName());
				System.out.println(itwReleaseDocumentBean.getReleaseName());
				ItwReleaseDocument itwReleaseDocument = prepareModel(
						itwReleaseDocumentBean, langId);

				itwReleaseDocumentService
						.addItwReleaseDocument(itwReleaseDocument);
			} catch (RuntimeException runtimeException) {

				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

					result.rejectValue("shortname", "shortname.unique",
							"ShortName already in use, enter a different ShortName");

					return new ModelAndView("addItwReleaseDocument", model);
				}

			}
			return new ModelAndView(
					"redirect:/itwReleaseDocumentConfigList.html");
		}

	}

	@RequestMapping(value = "/itwReleaseDocuments", method = RequestMethod.GET)
	public ModelAndView listItwReleaseDocuments() {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("itwReleaseDocumentConfigListPage", model);
	}

	@RequestMapping(value = "/addItwReleaseDocument", method = RequestMethod.GET)
	public ModelAndView addItwReleaseDocument(
			@ModelAttribute("command") ItwReleaseDocumentBean itwReleaseDocumentBean,
			BindingResult result, HttpServletRequest request) {
		// langDesc = request.getParameter("langDesc");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwReleases> listItwReleases = itwReleasesService
				.listItwReleases(langId);

		if (listItwReleases != null) {
			model.put("itwReleases", listItwReleases);
		}

		else {
			model.put("itwReleases", null);
		}

		return new ModelAndView("addItwReleaseDocument", model);
	}

	@RequestMapping(value = "/editItwReleaseDocument", method = RequestMethod.GET)
	public ModelAndView editItwReleaseDocument(
			@ModelAttribute("command") ItwReleaseDocumentBean itwReleaseDocumentBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();

		ItwReleaseDocumentBean itwReleaseDocumentBean2 = prepareItwReleaseDocumentBean(
				itwReleaseDocumentService.getItwReleaseDocument(itwReleaseDocumentBean
						.getId()), request);

		model.put("itwReleaseDocument", itwReleaseDocumentBean2);

		if (itwReleasesBean != null) {

			model.put("itwReleases", itwReleasesBean);

		} else {

			model.put("itwReleases", null);
		}

		if (listitwTaskBugsDefects != null) {
			model.put("itwReleasesDefects", listitwTaskBugsDefects);
		} else {
			model.put("itwReleasesDefects", null);
		}
		return new ModelAndView("editItwReleaseDocument", model);
	}

	@RequestMapping(value = "/updateItwReleaseDocument", method = RequestMethod.POST)
	public ModelAndView updateReleaseDocument(
			@ModelAttribute("command") ItwReleaseDocumentBean itwReleaseDocumentBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ReleaseDocumentValidator releaseDocumentValidator = new ReleaseDocumentValidator();
		releaseDocumentValidator.validate(itwReleaseDocumentBean, result);

		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();

			if (itwReleaseDocumentBean.getReleaseName() != null) {

				List<ItwReleasesBean> itwReleasesBean = prepareListofItwReleases(itwReleasesService
						.listItwReleases(langId));

				model.put("itwReleaseDocumentModules", strings);
				model.put("itwReleases", itwReleasesBean);

			}

			// model.put("itwReleaseDocument", itwReleaseDocumentBean2);

			else if (itwReleaseDocumentBean.getReleaseName() == null) {

				List<ItwReleasesBean> itwReleasesBean = prepareListofItwReleases(itwReleasesService
						.listItwReleases(langId));
				result.rejectValue("releaseName", "releaseName.inuse",
						"Release are not configured , Please configure release");
				model.put("itwReleases", itwReleasesBean);

			} else {

			}

			model.put("itwReleaseDocument", itwReleaseDocumentBean);
			return new ModelAndView("editItwReleaseDocument", model);

		} else {

			ItwReleaseDocument itwReleaseDocument = new ItwReleaseDocument();
			
			ItwReleaseDocumentBean itwReleaseDocumentBean2 = prepareItwReleaseDocumentBean(
					itwReleaseDocumentService.getItwReleaseDocument(itwReleaseDocumentBean
							.getId()), request);
			
			
			itwReleaseDocument = prepareModelItwReleaseDocumentforUpdate(
					itwReleaseDocumentBean, langId);

			
			itwReleaseDocument.setDefects(itwReleaseDocumentBean2.getDefects());
			itwReleaseDocumentService.addItwReleaseDocument(itwReleaseDocument);

			return new ModelAndView(
					"redirect:/itwReleaseDocumentConfigList.html");
		}
	}

	@RequestMapping(value = "/itwReleaseDocumentConfigList", method = RequestMethod.GET)
	public ModelAndView ReleaseDocumentConfigList(HttpServletRequest request) {
		System.out.println("started ReleaseDocument config List page");
		Integer langId = (Integer) request.getSession().getAttribute("langId");

		Map<String, Object> model = new HashMap<String, Object>();
		List<ItwReleaseDocumentBean> itwReleaseDocumentBean = prepareListofItwReleaseDocuments(itwReleaseDocumentService
				.listItwReleaseDocuments(langId));

		if (itwReleaseDocumentBean != null) {
			// java.util.Collections.sort(itwReleaseDocumentBean,
			// ItwReleaseDocumentBean.Comparators.ID);

			PagedListHolder pagedListHolder = new PagedListHolder(
					itwReleaseDocumentBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 5;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("ItwReleaseDocumentConfigListPage", model);
	}

	@RequestMapping(value = "/deleteItwReleaseDocument", method = RequestMethod.GET)
	public ModelAndView deleteItwReleaseDocument(
			@ModelAttribute("command") ItwReleaseDocumentBean itwReleaseDocumentBean,
			BindingResult result, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		// itwReleaseDocumentBean.setLangDesc(langDesc);

		String ReleaseDocumentNametemp = "ReleaseDocument ID "
				+ itwReleaseDocumentBean.getId()
				+ " already in use, cannot delete";

		try {
			System.out.println("Run Time");
			itwReleaseDocumentService
					.deleteItwReleaseDocument(prepareModelItwReleaseDocumentforDelete(itwReleaseDocumentBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// role id is being deleted which is in use in table ITW_USERS as a
			// foreign key

			if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				System.out.println("entered runtime exception catch block 2"
						+ runtimeException.toString());
				Map<String, Object> model = new HashMap<String, Object>();
				List<ItwReleaseDocumentBean> itwReleaseDocumentBeanList = prepareListofItwReleaseDocuments(itwReleaseDocumentService
						.listItwReleaseDocuments(langId));
				if (itwReleaseDocumentBean != null) {
					// java.util.Collections.sort(itwReleaseDocumentBeanList,
					// ItwReleaseDocumentBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwReleaseDocumentBeanList);
					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);
					System.out.println("value of page variable issssss" + page);
					pagedListHolder.setPage(page);
					int pageSize = 5;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}

				result.rejectValue("shortname", "shortname.inuse",
						ReleaseDocumentNametemp);
				return new ModelAndView("itwReleaseDocumentConfigList", model);
			}

		}

		return new ModelAndView("redirect:/itwReleaseDocumentConfigList.html");
	}

	private ItwReleaseDocument prepareModel(
			ItwReleaseDocumentBean itwReleaseDocumentBean, int langId) {
		listitwTaskBugsDefects = new ArrayList<ItwTaskBug>();
		String releaseDate = itwReleaseDocumentBean.getReleaseDate();
		ItwReleaseDocument itwReleaseDocument = new ItwReleaseDocument();
		// String endDate = itwReleaseDocumentBean.getEnddate();
		System.out.println(releaseDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		StringBuffer bufferdefect = new StringBuffer();
		java.util.Date newreleaseDate = null;


		ItwReleases itwReleases = new ItwReleases();
		List<ItwReleases> listItwReleases = itwReleasesService
				.getItwReleaseByShortName(itwReleaseDocumentBean
						.getReleaseName());
		for (ItwReleases itwReleasess : listItwReleases) {
			itwReleaseDocument.setReleaseid(itwReleasess.getId());
		}
		itwReleases = itwReleasesService.getItwReleases(itwReleaseDocument
				.getReleaseid());
		StringBuffer buffer = new StringBuffer();
		List<ItwTaskBug> listitwTaskBugs = itwTaskBugService
				.getItwTaskBugReleaseId(itwReleases.getId());

		
		Iterator<ItwTaskBug> iterator = listitwTaskBugs.iterator();
		bufferdefect.append(" ").append("BUG ID").append("    ").append("BUG NAME").append("\n");
		while (iterator.hasNext()) {

			ItwTaskBug itwTaskBug = (ItwTaskBug) iterator.next();
			List<ItwCheckOutData> checkOutDatas = itwCheckOutDataService
					.getItwCheckOutDataOnIssueId(itwTaskBug.getId());

			
			bufferdefect.append("-").append("    ").append(itwTaskBug.getId()).append("    ").append(itwTaskBug.getShortname())
					.append("\n");
			System.out.println(bufferdefect.toString()
					+ "-----------------------");
			itwReleaseDocument.setDefects(bufferdefect.toString());
			listitwTaskBugsDefects.add(itwTaskBug);

			Iterator<ItwCheckOutData> iterato2r = checkOutDatas.iterator();
			while (iterato2r.hasNext()) {

				try {
					ItwCheckOutData itwCheckOutData = (ItwCheckOutData) iterato2r
							.next();

					List<ItwModuleTree> listItwModuleTree = itwModuleTreeService
							.getItwModuleTreeNodeName(itwCheckOutData
									.getNodeId());
					Iterator<ItwModuleTree> iterator2 = listItwModuleTree
							.iterator();
					while (iterator2.hasNext()) {
						ItwModuleTree itwModuleTree = (ItwModuleTree) iterator2
								.next();

						ItwModule itwModule = itwModuleService
								.getItwModule(itwModuleTree.getModuleId());
						
						// Ganesh temporary addeded
						if (!buffer.toString().contains(
								itwCheckOutData.getNodeId() + "-"
										+ itwModule.getShortname())) {

							buffer.append("-").append(itwCheckOutData.getId()).append("    ")
									.append(itwCheckOutData.getNodeId())
									.append("-")  
									.append(itwModule.getShortname())
									.append("\n");

						}
					}

				} catch (Exception e) {

					System.out.println("EXCEPTION HANDLED");
				}
			}
		}
		itwReleaseDocument.setDefects(bufferdefect.toString());

		try {
			newreleaseDate = sdf1.parse(releaseDate);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = new Date(newreleaseDate.getTime());

		itwReleaseDocument.setSourceCodeVersion(itwReleaseDocumentBean
				.getSourceCodeVersion());
		itwReleaseDocument.setEnvoirnmentEffected(itwReleaseDocumentBean
				.getEnvoirnmentEffected());
		itwReleaseDocument.setBuisnessImpact(itwReleaseDocumentBean
				.getBuisnessImpact());
		itwReleaseDocument.setObjectImpacted(itwReleaseDocumentBean
				.getObjectImpacted());
		System.out.println(buffer.toString() + "********************");

		itwReleaseDocument.setObjectImpacted(buffer.toString());
		itwReleaseDocument.setRevisedBy(itwReleaseDocumentBean.getRevisedBy());
		itwReleaseDocument.setCreatedBy(itwReleaseDocumentBean.getCreatedBy());

		itwReleaseDocument.setReleaseDate(sqlStartDate);
		itwReleaseDocument.setReleaseName(itwReleaseDocumentBean
				.getReleaseName());
		itwReleaseDocument.setLangId(langId);
		itwReleaseDocument.setIntroduction(itwReleaseDocumentBean
				.getIntroduction());
		itwReleaseDocument.setScope(itwReleaseDocumentBean.getScope());
		itwReleaseDocument.setSystemRequirements(itwReleaseDocumentBean
				.getSystemRequirements());
		itwReleaseDocument.setOperatingsystemssupported(itwReleaseDocumentBean
				.getOperatingsystemssupported());
		itwReleaseDocument.setPrerequisites(itwReleaseDocumentBean
				.getPrerequisites());
		itwReleaseDocument.setKnownIssues(itwReleaseDocumentBean
				.getKnownIssues());
		itwReleaseDocument.setAssumptionDependencies(itwReleaseDocumentBean
				.getAssumptionDependencies());
		itwReleaseDocument.setSpecialInstructions(itwReleaseDocumentBean
				.getSpecialInstructions());
		itwReleaseDocument.setReleaseProcessInsrtuctions(itwReleaseDocumentBean
				.getReleaseProcessInsrtuctions());
		// itwReleaseDocument.setDefects(itwReleaseDocumentBean.getDefects());
		itwReleaseDocument.setTitle(itwReleaseDocumentBean.getTitle());

		System.out.println(itwReleaseDocument.getBuisnessImpact());
		System.out.println(itwReleaseDocument.getCreatedBy());
		System.out.println(itwReleaseDocument.getEnvoirnmentEffected());
		System.out.println(itwReleaseDocument.getObjectImpacted());
		System.out.println(itwReleaseDocument.getRevisedBy());
		System.out.println(itwReleaseDocument.getSourceCodeVersion());
		System.out.println(itwReleaseDocument.getLangId());
		System.out.println(itwReleaseDocument.getReleaseDate());
		System.out.println(itwReleaseDocument.getReleaseid());

		System.out.println(itwReleaseDocumentBean.getReleaseName());

		List<ItwReleases> listItwReleases2 = itwReleasesService
				.getItwReleaseByShortName(itwReleaseDocumentBean
						.getReleaseName());

		Iterator<ItwReleases> iterator2 = listItwReleases2.iterator();
		while (iterator.hasNext()) {
			ItwReleases itwReleases2 = (ItwReleases) iterator2.next();
			itwReleaseDocument.setReleaseid(itwReleases2.getId());
		}

		System.out.println(itwReleaseDocument.getBuisnessImpact());
		System.out.println(itwReleaseDocument.getCreatedBy());
		System.out.println(itwReleaseDocument.getEnvoirnmentEffected());
		System.out.println(itwReleaseDocument.getObjectImpacted());
		System.out.println(itwReleaseDocument.getRevisedBy());
		System.out.println(itwReleaseDocument.getSourceCodeVersion());
		System.out.println(itwReleaseDocument.getLangId());
		System.out.println(itwReleaseDocument.getReleaseDate());
		System.out.println(itwReleaseDocument.getReleaseid());

		return itwReleaseDocument;
	}

	private ItwReleaseDocumentBean prepareItwReleaseDocumentBean(
			ItwReleaseDocument itwReleaseDocument, HttpServletRequest request) {

		java.sql.Date startDate = itwReleaseDocument.getReleaseDate();
		listitwTaskBugsDefects = new ArrayList<ItwTaskBug>();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String startDateStr = df.format(startDate);

		ItwReleaseDocumentBean bean = new ItwReleaseDocumentBean();

		ItwReleases itwReleases = new ItwReleases();
		itwReleases = itwReleasesService.getItwReleases(itwReleaseDocument
				.getReleaseid());
		StringBuffer buffer = new StringBuffer();

		StringBuffer bufferdefect = new StringBuffer();
		List<ItwTaskBug> listitwTaskBugs = itwTaskBugService
				.getItwTaskBugReleaseId(itwReleases.getId());

		Iterator<ItwTaskBug> iterator = listitwTaskBugs.iterator();
		while (iterator.hasNext()) {

			ItwTaskBug itwTaskBug = (ItwTaskBug) iterator.next();
			List<ItwCheckOutData> checkOutDatas = itwCheckOutDataService
					.getItwCheckOutDataOnIssueId(itwTaskBug.getId());

			bufferdefect.append("*").append(itwTaskBug.getShortname())
					.append("\n");
			listitwTaskBugsDefects.add(itwTaskBug);
			bean.setDefects(bufferdefect.toString());
			Iterator<ItwCheckOutData> iterato2r = checkOutDatas.iterator();
			while (iterato2r.hasNext()) {

				try {
					ItwCheckOutData itwCheckOutData = (ItwCheckOutData) iterato2r
							.next();

					List<ItwModuleTree> listItwModuleTree = itwModuleTreeService
							.getItwModuleTreeNodeName(itwCheckOutData
									.getNodeId());
					Iterator<ItwModuleTree> iterator2 = listItwModuleTree
							.iterator();
					while (iterator2.hasNext()) {
						ItwModuleTree itwModuleTree = (ItwModuleTree) iterator2
								.next();

						ItwModule itwModule = itwModuleService
								.getItwModule(itwModuleTree.getModuleId());

						// Ganesh temporary addeded
						if (!buffer.toString().contains(
								itwCheckOutData.getNodeId() + "-"
										+ itwModule.getShortname())) {

							buffer.append("*")
									.append(itwCheckOutData.getNodeId())
									.append("-")
									.append(itwModule.getShortname())
									.append("\n");

						}
					}

				} catch (Exception e) {

					System.out.println("EXCEPTION HANDLED");
				}
			}
		}

		bean.setTitle(itwReleaseDocument.getTitle());
		bean.setSourceCodeVersion(itwReleaseDocument.getSourceCodeVersion());
		bean.setEnvoirnmentEffected(itwReleaseDocument.getEnvoirnmentEffected());
		bean.setBuisnessImpact(itwReleaseDocument.getBuisnessImpact());
		bean.setObjectImpacted(buffer.toString());
		bean.setRevisedBy(itwReleaseDocument.getRevisedBy());
		bean.setCreatedBy(itwReleaseDocument.getCreatedBy());
		bean.setReleaseid(itwReleaseDocument.getReleaseid());
		bean.setId(itwReleaseDocument.getId());
		bean.setLangId(itwReleaseDocument.getLangId());
		bean.setReleaseName(itwReleases.getShortname());

		bean.setIntroduction(itwReleaseDocument.getIntroduction());
		bean.setScope(itwReleaseDocument.getScope());
		bean.setSystemRequirements(itwReleaseDocument.getSystemRequirements());
		bean.setOperatingsystemssupported(itwReleaseDocument
				.getOperatingsystemssupported());
		bean.setPrerequisites(itwReleaseDocument.getPrerequisites());
		bean.setKnownIssues(itwReleaseDocument.getKnownIssues());
		bean.setAssumptionDependencies(itwReleaseDocument
				.getAssumptionDependencies());
		bean.setSpecialInstructions(itwReleaseDocument.getSpecialInstructions());
		bean.setReleaseProcessInsrtuctions(itwReleaseDocument
				.getReleaseProcessInsrtuctions());
		bean.setReleaseProcessInsrtuctions(itwReleaseDocument
				.getReleaseProcessInsrtuctions());
bean.setDefects(itwReleaseDocument.getDefects());
		itwReleasesBean = prepareListofItwReleases(
				itwReleasesService.listItwReleases(itwReleaseDocument
						.getLangId()), itwReleases.getShortname());

		bean.setReleaseDate(startDateStr);
		return bean;

	}

	private List<ItwReleaseDocumentBean> prepareListofItwReleaseDocuments(
			List<ItwReleaseDocument> itwReleaseDocuments) {
		List<ItwReleaseDocumentBean> beans = null;
	
		listitwTaskBugsDefects = new ArrayList<ItwTaskBug>();
		if (itwReleaseDocuments != null && !itwReleaseDocuments.isEmpty()) {

			beans = new ArrayList<ItwReleaseDocumentBean>();
			ItwReleaseDocumentBean bean = null;
			for (ItwReleaseDocument itwReleaseDocument : itwReleaseDocuments) {

				java.sql.Date startDate = itwReleaseDocument.getReleaseDate();

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String sdate = df.format(startDate);

				bean = new ItwReleaseDocumentBean();
				bean.setId(itwReleaseDocument.getId());
				bean.setTitle(itwReleaseDocument.getTitle());
				bean.setSourceCodeVersion(itwReleaseDocument
						.getSourceCodeVersion());
				bean.setEnvoirnmentEffected(itwReleaseDocument
						.getEnvoirnmentEffected());
				bean.setBuisnessImpact(itwReleaseDocument.getBuisnessImpact());
				bean.setObjectImpacted(itwReleaseDocument.getObjectImpacted());
				bean.setRevisedBy(itwReleaseDocument.getRevisedBy());
				bean.setCreatedBy(itwReleaseDocument.getCreatedBy());
				bean.setReleaseid(itwReleaseDocument.getReleaseid());
				bean.setReleaseDate(sdate);
				bean.setReleaseName(itwReleaseDocument.getReleaseName());
				System.out.println(sdate);
				bean.setLangId(itwReleaseDocument.getLangId());
				
				

				ItwReleases itwReleases = new ItwReleases();
				itwReleases = itwReleasesService
						.getItwReleases(itwReleaseDocument.getReleaseid());

				bean.setReleaseName(itwReleases.getShortname());
				bean.setIntroduction(itwReleaseDocument.getIntroduction());
				bean.setScope(itwReleaseDocument.getScope());
				bean.setSystemRequirements(itwReleaseDocument
						.getSystemRequirements());
				bean.setOperatingsystemssupported(itwReleaseDocument
						.getOperatingsystemssupported());
				bean.setPrerequisites(itwReleaseDocument.getPrerequisites());
				bean.setKnownIssues(itwReleaseDocument.getKnownIssues());
				bean.setAssumptionDependencies(itwReleaseDocument
						.getAssumptionDependencies());
				bean.setSpecialInstructions(itwReleaseDocument
						.getSpecialInstructions());
				bean.setReleaseProcessInsrtuctions(itwReleaseDocument
						.getReleaseProcessInsrtuctions());
				bean.setDefects(itwReleaseDocument.getDefects());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwReleaseDocument prepareModelItwReleaseDocumentforUpdate(
			ItwReleaseDocumentBean itwReleaseDocumentBean, Integer langId) {
		System.out.println("Inside Update");

		String startDate = itwReleaseDocumentBean.getReleaseDate();

		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date startDate1 = null;

		try {
			startDate1 = sdf1.parse(startDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Inside Update 2");
		java.sql.Date releasedate = new Date(startDate1.getTime());

		ItwReleaseDocument itwReleaseDocument = new ItwReleaseDocument();

		List<ItwReleases> listItwReleases = itwReleasesService
				.getItwReleaseByShortName(itwReleaseDocumentBean
						.getReleaseName());
		for (ItwReleases itwReleases : listItwReleases) {
			itwReleaseDocument.setReleaseid(itwReleases.getId());
		}

		itwReleaseDocument.setTitle(itwReleaseDocumentBean.getTitle());
		itwReleaseDocument.setSourceCodeVersion(itwReleaseDocumentBean
				.getSourceCodeVersion());
		itwReleaseDocument.setEnvoirnmentEffected(itwReleaseDocumentBean
				.getEnvoirnmentEffected());
		itwReleaseDocument.setBuisnessImpact(itwReleaseDocumentBean
				.getBuisnessImpact());
		itwReleaseDocument.setObjectImpacted(itwReleaseDocumentBean
				.getObjectImpacted());
		itwReleaseDocument.setRevisedBy(itwReleaseDocumentBean.getRevisedBy());
		itwReleaseDocument.setCreatedBy(itwReleaseDocumentBean.getCreatedBy());
		itwReleaseDocument.setReleaseName(itwReleaseDocumentBean
				.getReleaseName());
		itwReleaseDocument.setReleaseDate(releasedate);

		itwReleaseDocument.setLangId(langId);
		itwReleaseDocument.setIntroduction(itwReleaseDocumentBean
				.getIntroduction());
		itwReleaseDocument.setScope(itwReleaseDocumentBean.getScope());
		itwReleaseDocument.setSystemRequirements(itwReleaseDocumentBean
				.getSystemRequirements());
		itwReleaseDocument.setOperatingsystemssupported(itwReleaseDocumentBean
				.getOperatingsystemssupported());
		itwReleaseDocument.setPrerequisites(itwReleaseDocumentBean
				.getPrerequisites());
		itwReleaseDocument.setKnownIssues(itwReleaseDocumentBean
				.getKnownIssues());
		itwReleaseDocument.setAssumptionDependencies(itwReleaseDocumentBean
				.getAssumptionDependencies());
		itwReleaseDocument.setSpecialInstructions(itwReleaseDocumentBean
				.getSpecialInstructions());
		itwReleaseDocument.setReleaseProcessInsrtuctions(itwReleaseDocumentBean
				.getReleaseProcessInsrtuctions());
		
		
		
	//	itwReleaseDocument.setDefects(itwReleaseDocumentBean.getDefects());

		System.out.println(releasedate);
		itwReleaseDocument.setLangId(langId);

		itwReleaseDocument.setId(itwReleaseDocumentBean.getId());
		return itwReleaseDocument;
	}

	private ItwReleaseDocument prepareModelItwReleaseDocumentforDelete(
			ItwReleaseDocumentBean itwReleaseDocumentBean) {

		ItwReleaseDocument itwReleaseDocument = new ItwReleaseDocument();
		itwReleaseDocument.setId(itwReleaseDocumentBean.getId());
		return itwReleaseDocument;
	}

	private List<ItwReleasesBean> prepareListofItwReleases(
			List<ItwReleases> itwReleasess, String temp) {
		List<ItwReleasesBean> beans = null;
		if (itwReleasess != null && !itwReleasess.isEmpty()) {
			beans = new ArrayList<ItwReleasesBean>();
			ItwReleasesBean bean = null;
			for (ItwReleases itwReleases : itwReleasess) {
				bean = new ItwReleasesBean();

				if (!(temp != null && temp.hashCode() == itwReleases
						.getShortname().hashCode())) {
					bean.setId(itwReleases.getId());

					bean.setShortname(itwReleases.getShortname());
					bean.setLangid(itwReleases.getLangId());
					beans.add(bean);

				}

			}
		}
		return beans;
	}

	private List<ItwReleasesBean> addprepareListofItwReleases(
			List<ItwReleases> itwReleasess) {
		List<ItwReleasesBean> beans = null;
		if (itwReleasess != null && !itwReleasess.isEmpty()) {
			beans = new ArrayList<ItwReleasesBean>();
			ItwReleasesBean bean = null;
			for (ItwReleases itwReleases : itwReleasess) {
				bean = new ItwReleasesBean();
				bean.setId(itwReleases.getId());
				bean.setShortname(itwReleases.getShortname());

				bean.setLangid(itwReleases.getLangId());

				beans.add(bean);

			}
		}
		return beans;
	}

	private List<ItwReleasesBean> prepareListofItwReleases(
			List<ItwReleases> itwReleasess) {
		List<ItwReleasesBean> beans = null;
		if (itwReleasess != null && !itwReleasess.isEmpty()) {
			beans = new ArrayList<ItwReleasesBean>();
			ItwReleasesBean bean = null;
			for (ItwReleases itwReleases : itwReleasess) {
				bean = new ItwReleasesBean();
				bean.setId(itwReleases.getId());
				bean.setShortname(itwReleases.getShortname());
				bean.setLangid(itwReleases.getLangId());

				beans.add(bean);

			}
		}
		return beans;
	}

	@RequestMapping(value = "/downloadItwReleaseDocumentpdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			HttpServletRequest request) {

		String id = request.getParameter("id");

		System.out.println("ID " + id);
		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		// SalesDAO dataprovider = new SalesDAO();

		JRDataSource datasource = itwReleaseDocumentService
				.getDataSource(Integer.parseInt(id));
		
		List<ItwReleaseProcessBean> itwReleaseProcessBean = prepareListofItwReleaseProcess(itwReleaseProcessService
				.listItwReleaseProcess(1));
		
		
		JRDataSource firstSubreportDatasource = new JRBeanCollectionDataSource(itwReleaseProcessBean);
		 
		

		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		// JRDataSource datasource = dataprovider.getDataSource();
		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", datasource); 
		parameterMap.put("JasperCustomSubReportDatasource", firstSubreportDatasource);
		
		modelAndView = new ModelAndView("pdfItwReleaseDocumentReport",
				parameterMap);
		
		
		// Return the View and the Model combined
		return modelAndView;
	}
	private List<ItwReleaseProcessBean> prepareListofItwReleaseProcess(
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
				try {
					bean.setImagefile(itwReleaseProcess.getFile().getBinaryStream());
				} catch (SQLException e) {
					
					
					System.out.println("EXCEPTION OCCURED "+e.toString());
					e.printStackTrace();
				}
				beans.add(bean);

			}
		}
		return beans;

	}
}
