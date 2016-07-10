package com.agileidc.itw.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.agileidc.itw.bean.DefectFlowBean;
import com.agileidc.itw.bean.EmployeeBean;
import com.agileidc.itw.bean.ItwDocumentsBean;
import com.agileidc.itw.bean.ItwImageBean;
import com.agileidc.itw.bean.ItwIssueParkBean;
import com.agileidc.itw.bean.ItwMessageBean;
import com.agileidc.itw.bean.ItwRcaBean;
import com.agileidc.itw.bean.ItwReworkBean;
import com.agileidc.itw.bean.ItwRoleBean;
import com.agileidc.itw.bean.ItwSeverityBean;
import com.agileidc.itw.bean.ItwTaskBugAuditBean;
import com.agileidc.itw.bean.ItwTaskBugBean;
import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.bean.ItwUserLogsBean;
import com.agileidc.itw.bean.MainPageBean;
import com.agileidc.itw.dao.ItwTaskDAOTemp;
import com.agileidc.itw.dao.SalesDAO;
import com.agileidc.itw.helper.Encrypt;
import com.agileidc.itw.helper.JavaPojo_JSONCaster;
import com.agileidc.itw.model.Document;
import com.agileidc.itw.model.Employee;
import com.agileidc.itw.model.ItwActivityType;
import com.agileidc.itw.model.ItwChatGroupIssue;
import com.agileidc.itw.model.ItwCheckOutData;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwImage;
import com.agileidc.itw.model.ItwIssuePark;
import com.agileidc.itw.model.ItwMessage;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwModuleTree;
import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRca;
import com.agileidc.itw.model.ItwRcaFeedback;
import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwSeverityColour;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwStatusTypes;
import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.model.ItwTaskBugAudit;
import com.agileidc.itw.model.ItwTweets;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;
import com.agileidc.itw.model.ItwUserLogs;
import com.agileidc.itw.model.ItwUserTypes;
import com.agileidc.itw.service.DocumentService;
import com.agileidc.itw.service.EmployeeService;
import com.agileidc.itw.service.ItwActivityTypeService;
import com.agileidc.itw.service.ItwChatGroupIssueService;
import com.agileidc.itw.service.ItwCompanyService;
import com.agileidc.itw.service.ItwImageService;
import com.agileidc.itw.service.ItwIssueParkService;
import com.agileidc.itw.service.ItwMessageService;
import com.agileidc.itw.service.ItwModuleService;
import com.agileidc.itw.service.ItwModuleTreeService;
import com.agileidc.itw.service.ItwPlatFormsService;
import com.agileidc.itw.service.ItwPriorityService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.service.ItwRcaFeedbackService;
import com.agileidc.itw.service.ItwRcaService;
import com.agileidc.itw.service.ItwReleasesService;
import com.agileidc.itw.service.ItwRolesService;
import com.agileidc.itw.service.ItwSeverityColourService;
import com.agileidc.itw.service.ItwSeverityService;
import com.agileidc.itw.service.ItwStagesTypesService;
import com.agileidc.itw.service.ItwStatusTypesService;
import com.agileidc.itw.service.ItwTaskBugAuditService;
import com.agileidc.itw.service.ItwTaskBugService;
import com.agileidc.itw.service.ItwTweetsService;
import com.agileidc.itw.service.ItwUserLogsService;
import com.agileidc.itw.service.ItwUserService;
import com.agileidc.itw.service.ItwUserTypesService;
import com.agileidc.itw.web.IdList;
import com.agileidc.itw.web.ItwDocumentValidator;
import com.agileidc.itw.web.ItwRoleValidator;
import com.agileidc.itw.web.ItwTaskBugValidator;
import com.agileidc.itw.web.ItwTaskBugValidatorDuringEdit;
import com.agileidc.itw.web.ItwUserForJson;
import com.agileidc.itw.web.ItwUserValidator;
import com.agileidc.itw.web.ModuleList;
import com.agileidc.itw.web.UpdateItwUserValidator;
import com.agileidc.itw.web.UserSession;

@Controller
public class ItwMainController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ItwUserService itwUserService;
	@Autowired
	private ItwTaskBugService itwTaskBugService;
	@Autowired
	private ItwActivityTypeService itwActivityTypeService;
	@Autowired
	private ItwRolesService itwRolesService;
	@Autowired
	private ItwUserTypesService itwUserTypesService;
	@Autowired
	private ItwProjectService itwProjectService;
	@Autowired
	private ItwSeverityService itwSeverityService;
	@Autowired
	private ItwPriorityService itwPriorityService;
	@Autowired
	private ItwPlatFormsService itwPlatFormsService;
	@Autowired
	private ItwStatusTypesService itwStatusTypesService;
	@Autowired
	private ItwCompanyService itwCompanyService;

	@Autowired
	private ItwSeverityColourService itwSeverityColourService;

	@Autowired
	private ItwTweetsService itwTweetsService;

	@Autowired
	private ItwModuleService itwModuleService;
	@Autowired
	private ItwMessageService itwMessageService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ItwModuleTreeService itwModuleTreeService;
	@Autowired
	private ItwImageService itwImageService;

	@Autowired
	private ItwTaskDAOTemp itwTaskDAOTemp;

	@Autowired
	private ItwUserLogsService itwUserLogsService;

	@Autowired
	private ItwTaskBugAuditService itwTaskBugAuditService;

	@Autowired
	private ItwChatGroupIssueService itwChatGroupIssueService;

	@Autowired
	private ItwStagesTypesService itwStagesTypesService;

	@Autowired
	private ItwReleasesService itwReleasesService;

	@Autowired
	private ItwRcaService itwRcaService;

	@Autowired
	private ItwRcaFeedbackService itwRcaFeedbackService;

	@Autowired
	private ItwIssueParkService itwIssueParkService;

	// ============Park================

	@RequestMapping(value = "/park", method = RequestMethod.POST)
	public ModelAndView park(
			@ModelAttribute("command") ItwIssueParkBean itwIssueParkBean,
			ItwTaskBugBean itwTaskBugBean, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {

		String parkId = request.getParameter("issueId");
		String issueId = request.getParameter("id");
		String createdDate = request.getParameter("createdDate");
		String parkReason = request.getParameter("parkreason");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		String userId = userSession.getItwUser().getUserid();
		Integer uId = userSession.getItwUser().getId();

		ItwIssuePark itwIssuePark = new ItwIssuePark();
		itwIssuePark.setTaskId(new Integer(issueId));
		itwIssuePark.setReason(parkReason);
		itwIssuePark.setStatus(0);
		itwIssuePark.setId(new Integer(parkId));

		org.joda.time.DateTime createdTime = new org.joda.time.DateTime();

		java.util.Date udt = createdTime.toDate();

		itwIssuePark.setUpdatedTime(udt);

		try {
			java.util.Date date = sdf.parse(createdDate);
			itwIssuePark.setCreatedTime(date);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		itwIssuePark.setUserId(userId);
		ItwTweets itwTweets = new ItwTweets();

		itwTweets.setUserid(uId);

		String projectIdString = request.getParameter("projectid");

		Integer projectId = new Integer(projectIdString);

		itwTweets.setProjectId(projectId);
		ItwProject itwProject = itwProjectService.getItwProject(new Integer(
				request.getParameter("projectid")));

		ItwUser itwUser = itwUserService.getItwUser(uId);
		String tweetmsg = " " + itwProject.getShortname()
				+ " has been Parked by " + itwUser.getFirstname() + " "
				+ itwUser.getLastname() + " : Reason - " + parkReason;
		itwTweets.setTweetmsg(tweetmsg);

		itwIssueParkService.addItwIssuePark(itwIssuePark);

		itwTweetsService.addItwTweets(itwTweets);

		return new ModelAndView("redirect:/issueList1.html?projectId="
				+ projectId);

	}

	@RequestMapping(value = "/unPark", method = RequestMethod.POST)
	public ModelAndView unPark(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		String issueId = request.getParameter("id");
		String projectIdString = request.getParameter("projectid");

		Integer projectId = new Integer(projectIdString);

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		String userId = userSession.getItwUser().getUserid();
		Integer uId = userSession.getItwUser().getId();

		ItwIssuePark itwIssuePark = new ItwIssuePark();
		itwIssuePark.setTaskId(new Integer(issueId));
		itwIssuePark.setUserId(userId);
		itwIssuePark.setStatus(1);

		org.joda.time.DateTime createdTime = new org.joda.time.DateTime();

		java.util.Date udt = createdTime.toDate();
		itwIssuePark.setCreatedTime(new Timestamp(udt.getTime()));

		ItwTweets itwTweets = new ItwTweets();
		itwTweets.setUserid(uId);

		itwTweets.setProjectId(projectId);
		ItwProject itwProject = itwProjectService.getItwProject(new Integer(
				request.getParameter("projectid")));

		ItwUser itwUser = itwUserService.getItwUser(uId);
		String tweetmsg = " " + itwProject.getShortname()
				+ " has been UnParked by " + itwUser.getFirstname() + " "
				+ itwUser.getLastname();
		itwTweets.setTweetmsg(tweetmsg);

		itwIssueParkService.addItwIssuePark(itwIssuePark);
		itwTweetsService.addItwTweets(itwTweets);

		return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
				+ projectId + "&&id=" + new Integer(issueId));

	}

	// ==========end===================

	@RequestMapping(value = "/sendNodeForCheckin", method = RequestMethod.POST)
	public ModelAndView sendNodeForCheckin(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String treeId = request.getParameter("id");

		ItwModuleTree itwModuleTree = itwModuleTreeService
				.getItwModuleTree(treeId);
		ItwCheckOutData itwCheckOutData = new ItwCheckOutData();
		itwCheckOutData.setActionType(2); // for operation checkin value is set
											// to 2;
		itwCheckOutData.setUserId(itwModuleTree.getLockedBy());
		itwCheckOutData.setNodeId(itwModuleTree.getNodeName());
		String issueId = request.getParameter("issueid");

		itwCheckOutData.setIssueId(new Integer(issueId));
		org.joda.time.DateTime createdTime = new org.joda.time.DateTime();

		java.util.Date udt = createdTime.toDate();

		itwCheckOutData.setActionTime(udt);

		itwModuleTree.setLockedStatus("N");
		/*
		 * userSession = (com.agileidc.itw.web.UserSession) request.getSession()
		 * .getAttribute("userSession");
		 */
		itwModuleTree.setLockedBy("");

		itwModuleTreeService.addItwModuleTree(itwModuleTree, itwCheckOutData);

		return new ModelAndView("webchat");
	}

	@RequestMapping(value = "/addUserToChatWindow", method = RequestMethod.POST)
	public ModelAndView addUserToChatWindow(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String issueId = request.getParameter("id");
		String receiver = request.getParameter("to");

		String sender = userSession.getItwUser().getUserid();
		boolean addUserFound = false;
		List<ItwChatGroupIssue> itwChatGroupIssueList = itwChatGroupIssueService
				.getItwChatGroupIssueBySessionId(request.getSession().getId(),
						new Integer(issueId));
		for (ItwChatGroupIssue itwChatGroupIssue : itwChatGroupIssueList) {
			if (receiver.equalsIgnoreCase(itwChatGroupIssue.getUserId()))
				addUserFound = true;
		}
		if (!addUserFound) {

			System.out
					.println(" User is not found hence adding.......sender is "
							+ sender + " reciever is " + receiver
							+ " issue id is " + issueId);
			ItwChatGroupIssue itwChatGroupIssue = new ItwChatGroupIssue();
			itwChatGroupIssue.setIssueId(new Integer(issueId));
			itwChatGroupIssue.setSessionId(request.getSession().getId());
			itwChatGroupIssue.setUserId(receiver);

			itwChatGroupIssueService.addSenderRecieverToItwChatGroup(
					itwChatGroupIssue, sender, receiver);

		} else {

			System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

			model.put("addUserFound", addUserFound);
		}
		return new ModelAndView("webchat", model);
	}

	@RequestMapping(value = "/deleteUserFromChatWindow", method = RequestMethod.POST)
	public ModelAndView deleteUserFromChatWindow(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String issueId = request.getParameter("id");
		String toDeleteUser = request.getParameter("toDelete");

		String sender = userSession.getItwUser().getUserid();
		boolean deleteUserFound = false;
		boolean deleteUserFound1 = false;
		Integer idToDelete = null;
		Integer idToDelete1 = null;
		List<ItwChatGroupIssue> itwChatGroupIssueList = itwChatGroupIssueService
				.getItwChatGroupIssueBySessionId(request.getSession().getId(),
						new Integer(issueId));
		for (ItwChatGroupIssue itwChatGroupIssue : itwChatGroupIssueList) {
			if (toDeleteUser.equalsIgnoreCase(itwChatGroupIssue.getUserId())) {
				deleteUserFound = true;
				idToDelete = itwChatGroupIssue.getId();

			}
		}
		if (deleteUserFound) {

			System.out
					.println(" User is  found hence deleting.......sender is "
							+ sender + " deleted user  is " + toDeleteUser
							+ " issue id is " + issueId);
			ItwChatGroupIssue itwChatGroupIssue = new ItwChatGroupIssue();
			itwChatGroupIssue.setId(idToDelete);

			itwChatGroupIssueService.deleteItwChatGroupIssue(itwChatGroupIssue);
			Map<String, HttpSession> userSessions = UserSession
					.getUsersSessions();
			HttpSession sessionOfDeletedUser = userSessions.get(toDeleteUser);
			if (sessionOfDeletedUser != null) {
				List<ItwChatGroupIssue> itwChatGroupIssueList1 = itwChatGroupIssueService
						.getItwChatGroupIssueBySessionId(sessionOfDeletedUser
								.getId(), new Integer(issueId));
				for (ItwChatGroupIssue itwChatGroupIssue1 : itwChatGroupIssueList1) {
					if (sender.equalsIgnoreCase(itwChatGroupIssue1.getUserId())) {
						deleteUserFound1 = true;
						idToDelete1 = itwChatGroupIssue1.getId();

					}

				}
				if (deleteUserFound1) {

					System.out
							.println(" User is  found hence deleting.......sender is "
									+ toDeleteUser
									+ " deleted user  is "
									+ sender + " issue id is " + issueId);
					ItwChatGroupIssue itwChatGroupIssue1 = new ItwChatGroupIssue();
					itwChatGroupIssue1.setId(idToDelete1);
					itwChatGroupIssueService
							.deleteItwChatGroupIssue(itwChatGroupIssue1);
				}
			}
		}

		return new ModelAndView("webchat");
	}

	@RequestMapping(value = "/sendNodeForUnLock", method = RequestMethod.POST)
	public ModelAndView sendNodeForUnLock(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request) {
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String treeId = request.getParameter("id");

		ItwModuleTree itwModuleTree = itwModuleTreeService
				.getItwModuleTree(treeId);
		ItwCheckOutData itwCheckOutData = new ItwCheckOutData();
		itwCheckOutData.setActionType(3); // for operation undo checkout value
											// is set
											// to 3;
		itwCheckOutData.setUserId(itwModuleTree.getLockedBy());
		itwCheckOutData.setNodeId(itwModuleTree.getNodeName());
		String issueId = request.getParameter("issueid");
		itwCheckOutData.setIssueId(new Integer(issueId));
		org.joda.time.DateTime createdTime = new org.joda.time.DateTime();

		java.util.Date udt = createdTime.toDate();

		itwCheckOutData.setActionTime(udt);

		itwModuleTree.setLockedStatus("N");
		itwModuleTree.setLockedBy("");
		itwModuleTreeService.addItwModuleTree(itwModuleTree, itwCheckOutData);
		return new ModelAndView("webchat");
	}

	@RequestMapping(value = "/sendNodeForCheckout", method = RequestMethod.POST)
	public ModelAndView sendNodeForCheckout(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String treeId = request.getParameter("id");

		ItwModuleTree itwModuleTree = itwModuleTreeService
				.getItwModuleTree(treeId);

		if (itwModuleTree.getLockedStatus().equals("N")) {

			itwModuleTree.setLockedStatus("Y");
			userSession = (com.agileidc.itw.web.UserSession) request
					.getSession().getAttribute("userSession");
			itwModuleTree.setLockedBy(userSession.getItwUser().getUserid());
			ItwCheckOutData itwCheckOutData = new ItwCheckOutData();
			itwCheckOutData.setActionType(1); // for operation checkout value is
												// set
												// to 1;
			itwCheckOutData.setUserId(itwModuleTree.getLockedBy());
			itwCheckOutData.setNodeId(itwModuleTree.getNodeName());
			String issueId = request.getParameter("issueid");

			itwCheckOutData.setIssueId(new Integer(issueId));
			org.joda.time.DateTime createdTime = new org.joda.time.DateTime();

			java.util.Date udt = createdTime.toDate();

			itwCheckOutData.setActionTime(udt);

			itwModuleTreeService.addItwModuleTree(itwModuleTree,
					itwCheckOutData);
		}

		return new ModelAndView("webchat");
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ModelAndView sendMessage(
			@ModelAttribute("command") ItwMessageBean itwMessageBean,
			BindingResult result, HttpServletRequest request) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String sender = userSession.getItwUser().getUserid();
		String receiver = request.getParameter("to");
		String message = request.getParameter("message");
		String issueId = request.getParameter("id");

		itwMessageService.sendMessageToAllUsers(request.getSession().getId(),
				message, sender, new Integer(issueId));
		/*
		 * List<ItwChatGroupIssue> itwChatGroupIssueList =
		 * itwChatGroupIssueService
		 * .getItwChatGroupIssueBySessionId(request.getSession().getId(), new
		 * Integer(issueId)); for (ItwChatGroupIssue itwChatGroupIssue :
		 * itwChatGroupIssueList) { itwMessageService.sendMessageToThisUser(
		 * itwChatGroupIssue.getUserId(), message, sender, new
		 * Integer(issueId)); }
		 */
		return new ModelAndView("webchat");
	}

	@RequestMapping(value = "/getNodes", method = RequestMethod.POST)
	public void getNodes(HttpServletRequest request,
			HttpServletResponse response) {

		List itwModuleTreeList = itwModuleTreeService.listItwModuleTree();

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
							itwModuleTreeList, ItwModuleTree.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getTask1", method = RequestMethod.POST)
	public void getTask1(HttpServletRequest request,
			HttpServletResponse response) {

		String issueId = request.getParameter("id");

		ItwTaskBug itwTaskBug = itwTaskBugService.getItwTaskBug(new Integer(
				issueId));
		List itwTaskBugList = new ArrayList<ItwTaskBug>();
		itwTaskBugList.add(itwTaskBug);

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
							itwTaskBugList, ItwTaskBug.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/downloadxls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView) {

		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		SalesDAO dataprovider = new SalesDAO();

		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource datasource = dataprovider.getDataSource();

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", datasource);

		// xlsReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("xlsReport", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downloadpdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView) {

		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		SalesDAO dataprovider = new SalesDAO();

		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource datasource = dataprovider.getDataSource();

		// In order to use Spring's built-in Jasper support,
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", datasource);

		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		modelAndView = new ModelAndView("pdfReport", parameterMap);

		// Return the View and the Model combined
		return modelAndView;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String getDownloadPage() {

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/downloadpage.jsp
		return "downloadpage";
	}

	@RequestMapping(value = "/getMyMessages", method = RequestMethod.POST)
	public void getMyMessages(HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String loggedUserId = userSession.getItwUser().getUserid();
		String issueId = request.getParameter("id");

		List messages = itwMessageService.getMyLatestMessages(loggedUserId,
				new Integer(issueId));

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(messages,
							ItwMessage.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getAllMessagesForId", method = RequestMethod.POST)
	public void getAllMessagesForId(HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String loggedUserId = userSession.getItwUser().getUserid();
		String issueId = request.getParameter("id");
		System.out.println("the issue id to be fethed for all messages are"
				+ issueId);
		List<ItwMessage> messages1 = itwMessageService.getAllMessagesForId(
				loggedUserId, new Integer(issueId));
		for (ItwMessage imsg : messages1) {
			System.out.println("the message is " + imsg.getMessage()
					+ " reciever id is " + imsg.getReceiverId() + " sender id "
					+ imsg.getSenderId());
		}
		List messages = itwMessageService.getAllMessagesForId(loggedUserId,
				new Integer(issueId));

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(messages,
							ItwMessage.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
	public void getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");
		System.out.println("entered getAllUsers ................");
		String userid = userSession.getItwUser().getUserid();
		List<ItwUserLogs> users = itwUserLogsService
				.getAllOnlineUserLogs(userid);
		List usersForJson = new ArrayList();

		for (ItwUserLogs user : users) {
			ItwUserForJson userForJson1 = new ItwUserForJson();
			userForJson1.setUserId(user.getUserid());
			usersForJson.add(userForJson1);
			System.out.println("entered getAllUsers 111 ................"
					+ user.getUserid());
		}
		if (usersForJson.size() > 0) {

			System.out.println("entered getAllUsers 2222 ................");
			response.setContentType("json");
			JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
			try {
				response.getWriter().write(
						pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
								usersForJson, ItwUserForJson.class).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/getPrev", method = RequestMethod.POST)
	public void getPrev(HttpServletRequest request, HttpServletResponse response) {

		String minVal = request.getParameter("minVal");

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String userid = userSession.getItwUser().getUserid();

		List messages = itwMessageService.getMyPrevMessages(userid, minVal);

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(messages,
							ItwMessage.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getNext", method = RequestMethod.POST)
	public void getNext(HttpServletRequest request, HttpServletResponse response) {

		String maxVal = request.getParameter("maxVal");

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");

		String userid = userSession.getItwUser().getUserid();

		List messages = itwMessageService.getMyNextMessages(userid, maxVal);

		response.setContentType("json");
		JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
		try {
			response.getWriter().write(
					pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(messages,
							ItwMessage.class).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getChatUsers", method = RequestMethod.POST)
	public void getChatUsers(HttpServletRequest request,
			HttpServletResponse response) {

		String issueId = request.getParameter("id");
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");
		String sessionId = request.getSession().getId();
		if (userSession != null) {
			String userid = userSession.getItwUser().getUserid();
			List itwChatGroupIssueList = itwChatGroupIssueService
					.getItwChatGroupIssueBySessionId(sessionId, new Integer(
							issueId));
			List<ItwChatGroupIssue> itwChatGroupIssueList1 = itwChatGroupIssueService
					.getItwChatGroupIssueBySessionId(sessionId, new Integer(
							issueId));

			response.setContentType("json");
			JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
			try {
				response.getWriter().write(
						pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
								itwChatGroupIssueList, ItwChatGroupIssue.class)
								.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/getOnlineUsers", method = RequestMethod.POST)
	public void getOnlineUsers(HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");
		if (userSession != null) {
			String userid = userSession.getItwUser().getUserid();
			List onlineUsers = itwUserService.getOnlineUsers(userid);
			response.setContentType("json");
			JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
			try {
				response.getWriter().write(
						pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
								onlineUsers, ItwUserLogs.class).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/getOfflineUsers", method = RequestMethod.POST)
	public void getOfflineUsers(HttpServletRequest request,
			HttpServletResponse response) {

		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");
		if (userSession != null) {
			String userid = userSession.getItwUser().getUserid();
			// List offlineUsers = itwUserService.getOfflineUsers(userid);
			List<ItwUserLogs> onlineUsers1 = itwUserService
					.getOnlineUsers(userid);
			System.out.println("the number of online users in table is "
					+ onlineUsers1.size());
			List<ItwUser> allUsers1 = itwUserService.getAllUsers(userid);
			System.out.println("the number of users in table is "
					+ allUsers1.size());
			List allUsers2 = new ArrayList<ItwUser>();
			// allUsers.removeAll(onlineUsers);
			boolean found = false;

			for (ItwUser itwUser1 : allUsers1) {

				found = false;
				for (ItwUserLogs itwUserLogs : onlineUsers1) {
					if (itwUserLogs.getUserid().equals(itwUser1.getUserid())) {
						found = true;
						break;
					}

				}
				if (!found) {
					allUsers2.add(itwUser1);
				}

			}

			response.setContentType("json");
			JavaPojo_JSONCaster pojo_JSONCaster = new JavaPojo_JSONCaster();
			try {
				response.getWriter().write(
						pojo_JSONCaster.convert_ThisPojoList_To_JSONArray(
								allUsers2, ItwUser.class).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		Employee employee = prepareModel(employeeBean);
		employeeService.addEmployee(employee);
		return new ModelAndView("redirect:/add.html");
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}

	// to display IT WORK BOOK HOME page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/userConfigList", method = RequestMethod.GET)
	public ModelAndView userConfigList(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		// Integer langId = new Integer(
		// request.getSession().getAttribute("langId") );
		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);
		java.util.Collections.sort(itwUserBean, ItwUserBean.Comparators.ID);

		PagedListHolder pagedListHolder = new PagedListHolder(itwUserBean);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);

		pagedListHolder.setPage(page);
		int pageSize = 15;
		pagedListHolder.setPageSize(pageSize);
		model.put("pagedListHolder", pagedListHolder);

		return new ModelAndView("userConfigListPage", model);
	}

	@RequestMapping(value = "/roleConfigList", method = RequestMethod.GET)
	public ModelAndView roleConfigList(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwRoleBean> itwRoleBean = prepareListofItwRoles(itwRolesService
				.listItwRoles(langId.intValue()));
		if (itwRoleBean != null) {
			PagedListHolder pagedListHolder = new PagedListHolder(itwRoleBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
		} else {
			model.put("pagedListHolder", null);
		}

		return new ModelAndView("roleConfigListPage", model);
	}

	// First page to Add a new ITW_USERS
	@RequestMapping(value = "/addItwUser", method = RequestMethod.GET)
	public ModelAndView addItwUser(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result, HttpServletRequest request) {
		/* return new ModelAndView("addItwUser"); */
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		// Integer langId = new Integer( request.getParameter("langId") );
		ItwUserBean itwUserBean1 = prepareItwUserBeanForAdd(langId);
		model.remove("itwUser1");
		model.put("itwUser1", itwUserBean1);
		return new ModelAndView("addItwUser", model);
	}

	@RequestMapping(value = "/addItwUser1", method = RequestMethod.GET)
	public ModelAndView addItwUser1(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result, HttpServletRequest request) {
		/* return new ModelAndView("addItwUser"); */
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		// Integer langId = new Integer( request.getParameter("langId") );
		ItwUserBean itwUserBean1 = prepareItwUserBeanForAdd(langId);
		model.remove("itwUser1");
		model.put("itwUser1", itwUserBean1);
		return new ModelAndView("addItwUser", model);
	}

	@RequestMapping(value = "/addItwRole", method = RequestMethod.GET)
	public ModelAndView addItwRole(
			@ModelAttribute("command") ItwRoleBean itwRoleBean,
			BindingResult result) {
		/* return new ModelAndView("addItwUser"); */
		Map<String, Object> model = new HashMap<String, Object>();
		ItwRoleBean itwRoleBean1 = prepareItwRoleBeanForAdd();
		model.remove("itwRole1");
		model.put("itwRole1", itwRoleBean1);
		return new ModelAndView("addItwRole", model);
	}

	@RequestMapping(value = "/saveItwUser", method = RequestMethod.POST)
	public ModelAndView saveItwUser(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		ItwUserValidator itwUserValidator = new ItwUserValidator();
		itwUserValidator.validate(itwUserBean, result);

		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			// Integer langId = new Integer( request.getParameter("langId") );
			ItwUserBean itwUserBean1 = prepareItwUserBeanForAdd(langId);

			model.remove("itwUser1");
			model.put("itwUser1", itwUserBean1);
			return new ModelAndView("addItwUser", model);
		} else {

			ItwUser itwUser = prepareModelItwUser(itwUserBean, request, file);
			ItwUserIcon itwUserIcon = new ItwUserIcon();
			itwUserIcon.setIcon(itwUser.getIcon());
			itwUser.setIcon(null);
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			itwUser.setLangid(langId.intValue());
			try {
				itwUserService.addItwUser(itwUser, itwUserIcon);

			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate Userid (emailid) is being added for a new user in
				// table ITW_USERS
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("userid", "userid.unique",
							"User Id is already in use, enter a different User Id");
					Map<String, Object> model = new HashMap<String, Object>();
					langId = (Integer) request.getSession().getAttribute(
							"langId");
					// Integer langId = new Integer(
					// request.getParameter("langId") );
					ItwUserBean itwUserBean1 = prepareItwUserBeanForAdd(langId);
					model.remove("itwUser1");
					model.put("itwUser1", itwUserBean1);
					return new ModelAndView("addItwUser", model);
				}

			}
			return new ModelAndView("redirect:/userConfigList.html");

		}
	}

	@RequestMapping(value = "/saveItwRole", method = RequestMethod.POST)
	public ModelAndView saveItwRole(
			@ModelAttribute("command") ItwRoleBean itwRoleBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		ItwRoleValidator itwRoleValidator = new ItwRoleValidator();
		itwRoleValidator.validate(itwRoleBean, result);

		if (result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			ItwRoleBean itwRoleBean1 = prepareItwRoleBeanForAdd();
			model.remove("itwRole1");
			model.put("itwRole1", itwRoleBean1);
			return new ModelAndView("addItwRole", model);
		} else {
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwRoles itwRoles = prepareModelItwRole(itwRoleBean,
						langId.intValue());
				itwRolesService.addItwRoles(itwRoles);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a new role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("rolename", "rolename.unique",
							"Rolename already in use, enter a different Rolename");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwRoleBean itwRoleBean1 = prepareItwRoleBeanForAdd();
					model.remove("itwRole1");
					model.put("itwRole1", itwRoleBean1);
					return new ModelAndView("addItwRole", model);
				}

			}
			return new ModelAndView("redirect:/roleConfigList.html");

		}
	}

	@RequestMapping(value = "/editItwUser", method = RequestMethod.GET)
	public ModelAndView editItwUser1(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		String fileName = "";
		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		// Integer langId = new Integer( request.getParameter("langId") );
		itwUserBean = prepareItwUserBean(
				itwUserService.getItwUser(itwUserBean.getId()), langId);
		model.put("itwUser", itwUserBean);

		try {
			InputStream inputStream = null;
			OutputStream outputStream = null;
			fileName = request.getRealPath("") + "/images/" + "img"
					+ itwUserBean.getId().toString() + "bug.png";
			outputStream = new FileOutputStream(fileName);

			ItwUserIcon itwUserIcon = itwTaskDAOTemp.getItwUser(
					itwUserBean.getIconid(), outputStream);

			outputStream.flush();
			outputStream.close();
			model.put("filepath", "images/" + "img"
					+ itwUserBean.getId().toString() + "bug.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("editItwUser", model);
	}

	@RequestMapping(value = "/editItwRole", method = RequestMethod.GET)
	public ModelAndView editItwRole(
			@ModelAttribute("command") ItwRoleBean itwRoleBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		itwRoleBean = prepareItwRoleBean(itwRolesService
				.getItwRoles(itwRoleBean.getId()));

		model.put("itwRole", itwRoleBean);

		return new ModelAndView("editItwRole", model);
	}

	// Second page to submit to update the ITW_USERS to table ITW_USERS
	@RequestMapping(value = "/updateItwUser", method = RequestMethod.POST)
	public ModelAndView updateItwUser(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		UpdateItwUserValidator itwUserValidator = new UpdateItwUserValidator();
		itwUserValidator.validate(itwUserBean, result);

		if (result.hasErrors()) {

			String fileName = "";
			Map<String, Object> model = new HashMap<String, Object>();
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			// Integer langId = new Integer( request.getParameter("langId") );
			ItwUserBean itwUserBean1 = prepareItwUserBean(
					itwUserService.getItwUser(itwUserBean.getId()), langId);
			itwUserBean.setItwCompanyidList(itwUserBean1.getItwCompanyidList());
			itwUserBean.setItwPrimaryroleidList(itwUserBean1
					.getItwPrimaryroleidList());
			itwUserBean.setItwTypeidList(itwUserBean1.getItwTypeidList());
			itwUserBean.setGenderList(itwUserBean1.getGenderList());
			model.put("itwUser", itwUserBean);

			try {
				InputStream inputStream = null;
				OutputStream outputStream = null;
				fileName = request.getRealPath("") + "/images/" + "img"
						+ itwUserBean.getId().toString() + "bug.png";
				outputStream = new FileOutputStream(fileName);

				ItwUserIcon itwUserIcon = itwTaskDAOTemp.getItwUser(
						itwUserBean.getIconid(), outputStream);

				outputStream.flush();
				outputStream.close();
				model.put("filepath", "images/" + "img"
						+ itwUserBean.getId().toString() + "bug.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ModelAndView("editItwUser", model);

		} else {
			ItwUser itwUsertemp = itwUserService
					.getItwUser(itwUserBean.getId());
			ItwUser itwUser = prepareModelItwUserforUpdate(itwUserBean);
			itwUser.setPassword(itwUsertemp.getPassword());
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			itwUser.setLangid(langId.intValue());

			if (file.getSize() > 0) {

				try {

					Blob blob = Hibernate.createBlob(file.getInputStream());
					itwUser.setIcon(blob);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {

				ItwUserIcon itwUserIcon = new ItwUserIcon();
				itwUserIcon.setIcon(itwUser.getIcon());
				itwUser.setIcon(null);
				itwUser.setOnetimepass(0);
				itwUserService.addItwUser(itwUser, itwUserIcon);

			} catch (HibernateException hibernateEx) {

			}

			return new ModelAndView("redirect:/userConfigList.html");
		}
	}

	@RequestMapping(value = "/updateItwRole", method = RequestMethod.POST)
	public ModelAndView updateItwRole(
			@ModelAttribute("command") ItwRoleBean itwRoleBean,
			BindingResult result, HttpServletRequest request) {

		ItwRoleValidator itwRoleValidator = new ItwRoleValidator();
		itwRoleValidator.validate(itwRoleBean, result);

		if (result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			ItwRoleBean itwRoleBean1 = prepareItwRoleBeanForAdd();
			model.remove("itwRole1");
			model.put("itwRole1", itwRoleBean1);
			return new ModelAndView("addItwRole", model);
		} else {
			try {
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				ItwRoles itwRole = prepareModelItwRoleforUpdate(itwRoleBean,
						langId.intValue());
				itwRolesService.addItwRoles(itwRole);
			}

			catch (RuntimeException runtimeException) {
				// catch unique constraint violation exception in case a
				// duplicate role name is being added for a existing role in
				// table ITW_ROLES
				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					result.rejectValue("rolename", "rolename.unique",
							"Rolename already in use, enter a different Rolename");
					Map<String, Object> model = new HashMap<String, Object>();
					ItwRoleBean itwRoleBean1 = prepareItwRoleBeanForAdd();
					model.remove("itwRole1");
					model.put("itwRole1", itwRoleBean1);
					return new ModelAndView("editItwRole", model);
				}
			}
			return new ModelAndView("redirect:/roleConfigList.html");
		}
	}

	@RequestMapping(value = "/deleteItwUser", method = RequestMethod.GET)
	public ModelAndView deleteItwUser(
			@ModelAttribute("command") ItwUserBean itwUserBean,
			BindingResult result) {
		itwUserService.deleteItwUser(prepareModelItwUserforDelete(itwUserBean));
		return new ModelAndView("redirect:/userConfigList.html");
	}

	@RequestMapping(value = "/deleteItwRole", method = RequestMethod.GET)
	public ModelAndView deleteItwRole1(
			@ModelAttribute("command") ItwRoleBean itwRoleBean,
			BindingResult result, HttpServletRequest request) {
		String roleNametemp = "Role ID " + itwRoleBean.getId()
				+ " already in use, cannot delete";
		java.util.Date d1 = new java.util.Date();
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) { /* ignore */
		}

		try {
			itwRolesService
					.deleteItwRoles(prepareModelItwRoleforDelete(itwRoleBean));
		}

		catch (RuntimeException runtimeException) {
			// catch unique constraint violation exception in case a
			// role id is being deleted which is in use in table ITW_USERS as a
			// foreign key

			if (runtimeException.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {

				Map<String, Object> model = new HashMap<String, Object>();
				Integer langId = (Integer) request.getSession().getAttribute(
						"langId");
				// Integer langId = new Integer( request.getParameter("langId")
				// );
				List<ItwRoleBean> itwRoleBeanList = prepareListofItwRoles(itwRolesService
						.listItwRoles(langId.intValue()));
				if (itwRoleBean != null) {
					java.util.Collections.sort(itwRoleBeanList,
							ItwRoleBean.Comparators.ID);

					PagedListHolder pagedListHolder = new PagedListHolder(
							itwRoleBeanList);
					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);

					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);
				} else {
					model.put("pagedListHolder", null);
				}

				result.rejectValue("rolename", "rolename.inuse", roleNametemp);
				return new ModelAndView("roleConfigListPage", model);
			}
		}

		return new ModelAndView("redirect:/roleConfigList.html");
	}

	// ***********************ITW USERS END*********************************
	// *********************************************************************

	// ***********************ITW TASK BUG START****************************
	// *********************************************************************
	// List page to display the list of ITW TASK BUG
	/*
	 * @RequestMapping(value = "/issueList", method = RequestMethod.GET) public
	 * ModelAndView issueList(HttpServletRequest request) {
	 * 
	 * Map<String, Object> model = new HashMap<String, Object>();
	 * 
	 * model.put( "ItwTaskBugs",
	 * prepareListofItwTaskBugs(itwTaskBugService.listItwTaskBugs(), request));
	 * return new ModelAndView("issueListPage", model); }
	 */

	@RequestMapping(value = "/openIssues", method = RequestMethod.GET)
	public ModelAndView openIssue(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));
		model.put("projectId", new Integer(projectId));

		List<ItwTaskBugBean> newitwTaskBugBean = new ArrayList<ItwTaskBugBean>();

		if (projectId != null) {
			if (projectId == 0) {
				System.out.println("==========1===============");
				newitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listOpenItwTaskBugs(), request);
			} else {
				System.out.println("==========2===============");
				newitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listOpenItwTaskBugsForProject(new Integer(
								projectId)), request);
			}
		}

		else {
			System.out.println("==========3===============");
			newitwTaskBugBean = prepareListofItwTaskBugs(
					itwTaskBugService.listOpenItwTaskBugs(), request);
		}

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		/*
		 * java.util.Collections.sort(itwTaskBugBean,
		 * ItwTaskBugBean.Comparators.ID);
		 */
		// String reccount = null;
		/*
		 * String reccount1 = request.getParameter("reccount").toString();
		 * System.out.println("record count is currently value"+ reccount1);
		 */

		List<ItwSeverityBean> itwSeverityBean = prepareListofItwSeveritys(
				itwSeverityService.listItwSeveritys(), request);
		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);

		model.put("ItwSeverityBean", itwSeverityBean);
		model.put("ItwUserBean", itwUserBean);

		if (itwTaskBugBean != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					newitwTaskBugBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;

			// pageSize = new Integer(reccount1);
			// System.out.println("page size is now set to" + pageSize);

			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);

			model.put("ItwTaskBugs", newitwTaskBugBean);

		}

		return new ModelAndView("openIssueListPage1", model);
	}

	@RequestMapping(value = "/issueList1", method = RequestMethod.GET)
	public ModelAndView issueList1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		String projectId = request.getParameter("projectId");

		List<ItwTaskBugBean> newitwTaskBugBean = null;
		if (projectId != null) {
			if (projectId.equals("0")) {

				newitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listItwTaskBugs(), request);
			} else {

				newitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listItwTaskBugsForProject(new Integer(
								projectId)), request);
			}
		}

		else {

			newitwTaskBugBean = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
		}

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		/*
		 * java.util.Collections.sort(itwTaskBugBean,
		 * ItwTaskBugBean.Comparators.ID);
		 */
		// String reccount = null;
		/*
		 * String reccount1 = request.getParameter("reccount").toString();
		 * System.out.println("record count is currently value"+ reccount1);
		 */

		List<ItwSeverityBean> itwSeverityBean = prepareListofItwSeveritys(
				itwSeverityService.listItwSeveritys(), request);
		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		model.put("ItwSeverityBean", itwSeverityBean);
		model.put("ItwUserBean", itwUserBean);
		if (newitwTaskBugBean != null) {

			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					newitwTaskBugBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;

			// pageSize = new Integer(reccount1);
			// System.out.println("page size is now set to" + pageSize);

			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);

			model.put("ItwTaskBugs", newitwTaskBugBean);
			model.put("projectId", new Integer(projectId));

		} else {

			model.put("pagedListHolder", null);

		}

		return new ModelAndView("issueListPage1", model);
	}

	private List<MainPageBean> prepareListofItwProject(
			List<ItwProject> itwProjects) {
		List<MainPageBean> beans = new ArrayList<MainPageBean>();

		if (itwProjects != null && !itwProjects.isEmpty()) {

			MainPageBean bean = null;
			for (ItwProject itwProject : itwProjects) {
				bean = new MainPageBean();
				bean.setId(itwProject.getId());
				bean.setProjectname(itwProject.getShortname());
				beans.add(bean);

			}
		}
		return beans;
	}

	@RequestMapping(value = "/searchItwTaskBug1", method = RequestMethod.POST)
	public ModelAndView searchItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		String projectId = request.getParameter("projectId");

		String SearchedValue = null;
		String SeleactedSearch = null;
		Integer searchedId = null;
		List<ItwTaskBug> itwTaskBugid = new ArrayList<ItwTaskBug>();
		SearchedValue = itwTaskBugBean.getSearchedValue().trim();
		SeleactedSearch = itwTaskBugBean.getSelectedSearch();
		System.out.println("=============Inside search---------------");
		System.out.println(SearchedValue);
		System.out.println(SeleactedSearch);

		if (SeleactedSearch.equals("id") && !SearchedValue.equals("")
				&& !SearchedValue.equals(null)) {
			try {

				searchedId = Integer
						.parseInt(itwTaskBugBean.getSearchedValue());
			} catch (Exception e) {
				// TODO: handle exception

				ItwTaskBugValidatorDuringEdit itwTaskBugValidatorDuringEdit = new ItwTaskBugValidatorDuringEdit();
				itwTaskBugValidatorDuringEdit.validateforSearch(itwTaskBugBean,
						result);

			}
			if (new Integer(projectId) != 0) {
				itwTaskBugid = itwTaskBugService.listGetItwTaskBug(searchedId,
						SeleactedSearch, new Integer(projectId));

			} else {
				itwTaskBugid = itwTaskBugService.allListGetItwTaskBug(
						searchedId, SeleactedSearch);
			}
		}

		else if (SeleactedSearch.equals("severityid")
				&& !SearchedValue.equals("") && !SearchedValue.equals(null)) {

			System.out.println("inside if loop----------");
			List<ItwSeverity> searchitwSeverity = itwSeverityService
					.getItwSeverityByShortName(SearchedValue);
			Iterator<ItwSeverity> iterator = searchitwSeverity.iterator();
			while (iterator.hasNext()) {
				ItwSeverity itwSeverity = (ItwSeverity) iterator.next();

				itwTaskBugBean.setAssigneeid(itwSeverity.getId());
				searchedId = itwTaskBugBean.getAssigneeid();
			}
			if (new Integer(projectId) != 0) {
				itwTaskBugid = itwTaskBugService.listGetItwTaskBug(searchedId,
						SeleactedSearch, new Integer(projectId));

			} else {
				itwTaskBugid = itwTaskBugService.allListGetItwTaskBug(
						searchedId, SeleactedSearch);
			}

		} else if (SeleactedSearch.equals("assigneeid")
				&& !SearchedValue.equals("") && !SearchedValue.equals(null)) {
			System.out.println("inside if loop----------");
			List<ItwUser> searchitwUsers = itwUserService
					.getItwUserByShortName(SearchedValue);
			Iterator<ItwUser> iterator = searchitwUsers.iterator();
			while (iterator.hasNext()) {
				ItwUser itwUser = (ItwUser) iterator.next();

				itwTaskBugBean.setAssigneeid(itwUser.getId());
				searchedId = itwTaskBugBean.getAssigneeid();
			}
			if (new Integer(projectId) != 0) {
				itwTaskBugid = itwTaskBugService.listGetItwTaskBug(searchedId,
						SeleactedSearch, new Integer(projectId));

			} else {
				itwTaskBugid = itwTaskBugService.allListGetItwTaskBug(
						searchedId, SeleactedSearch);
			}
		} else {

			System.out.println("inside  last if loop----------");
			List<ItwUser> nullSearchedValue = itwUserService
					.getItwUserByShortName(SearchedValue);
			Iterator<ItwUser> iterator2 = nullSearchedValue.iterator();
			while (iterator2.hasNext()) {
				ItwUser itwUser = (ItwUser) iterator2.next();

				itwTaskBugBean.setAssigneeid(itwUser.getId());
				searchedId = itwTaskBugBean.getAssigneeid();
			}

			if (new Integer(projectId) != 0) {

				itwTaskBugid = itwTaskBugService
						.listItwTaskBugsForProject(new Integer(projectId));
			} else {
				itwTaskBugid = itwTaskBugService.listItwTaskBugs();
			}

		}
		System.out.println(searchedId);
		System.out.println(SearchedValue);
		System.out.println(SeleactedSearch);

		List<ItwTaskBugBean> newitwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugid, request);
		Map<String, Object> model = new HashMap<String, Object>();

		/*
		 * List<ItwTaskBugBean> newitwTaskBugBean = prepareListofItwTaskBugs(
		 * itwTaskBugService.getItwTaskBugReleaseId(itwTaskBugBean.getId()),
		 * request);
		 */

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		/*
		 * java.util.Collections.sort(itwTaskBugBean,
		 * ItwTaskBugBean.Comparators.ID);
		 */
		/*
		 * String reccount = null;
		 * 
		 * String reccount1 = request.getParameter("reccount").toString();
		 * System.out.println("record count is currently value"+ reccount1);
		 */
		List<ItwTaskBugBean> searchnewitwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);
		List<ItwSeverityBean> searchitwSeverityBean = prepareListofItwSeveritys(
				itwSeverityService.listItwSeveritys(), request);
		List<ItwUserBean> searchitwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);

		model.put("ItwSeverityBean", searchitwSeverityBean);
		model.put("ItwUserBean", searchitwUserBean);

		if (newitwTaskBugBean != null) {

			System.out.println(" IF PART      999999");
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					newitwTaskBugBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;

			/*
			 * pageSize = new Integer(reccount1);
			 * System.out.println("page size is now set to" + pageSize);
			 */

			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);

		} else {

			System.out.println(" ELSE PART     999");
			model.put("pagedListHolder", null);
		}

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));
		model.put("ItwTaskBugs", searchnewitwTaskBugBean);
		model.put("projectId", new Integer(projectId));
		System.out.println("=========search completed===========");
		return new ModelAndView("issueListPage1", model);
	}

	@RequestMapping(value = "/addItwTaskBug1", method = RequestMethod.GET)
	public ModelAndView addItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		ItwTaskBugBean itwTaskBugBean1 = prepareItwTaskBugBeanForAdd(request,
				itwTaskBugBean);
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		String assigneeiddisplay1 = userSession.getItwUser().getUserid();

		String projectId = request.getParameter("projectId");

		List<ItwUser> itwUserList = itwUserService
				.getItwUserByShortName(assigneeiddisplay1);
		List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
		for (ItwUser itwUser : itwUserList) {
			IdList idList = new IdList();
			idList.setId(itwUser.getId());
			idList.setValue(assigneeiddisplay1);
			itwAssigneeIdList.add(idList);

		}
		itwTaskBugBean1.setItwAssigneeidList(itwAssigneeIdList);

		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);

		model.put("ItwUserBean", itwUserBean);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		model.put("projectId", new Integer(projectId));

		List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);
		model.remove("itwTaskBug1");
		model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
		model.put("itwTaskBug1", itwTaskBugBean1);
		model.put("projectId", projectId);

		return new ModelAndView("addItwTaskBug1", model);

	}

	@RequestMapping(value = "/webchat", method = RequestMethod.GET)
	public ModelAndView webchat(HttpServletRequest request) {

		return new ModelAndView("webchat");
	}

	/*
	 * // First page to Add a new Issue
	 * 
	 * @RequestMapping(value = "/addItwTaskBug", method = RequestMethod.GET)
	 * public ModelAndView addItwTaskBug(
	 * 
	 * @ModelAttribute("command") ItwTaskBugBean itwTaskBugBean, BindingResult
	 * result, HttpServletRequest request, HttpServletResponse response) {
	 * Map<String, Object> model = new HashMap<String, Object>(); ItwTaskBugBean
	 * itwTaskBugBean1 = prepareItwTaskBugBeanForAdd(request);
	 * model.remove("itwTaskBug1"); model.put("itwTaskBug1", itwTaskBugBean1);
	 * 
	 * UserSession userSession = (UserSession) request.getSession()
	 * .getAttribute("userSession"); String assigneeiddisplay =
	 * userSession.getItwUser().getUserid(); Integer assigneeid =
	 * userSession.getItwUser().getId();
	 * itwTaskBugBean1.setAssigneeid(assigneeid);
	 * itwTaskBugBean1.setAssigneeiddisplay(assigneeiddisplay); return new
	 * ModelAndView("addItwTaskBug", model); }
	 */

	@RequestMapping(value = "/saveItwTaskBug1", method = RequestMethod.POST)
	public ModelAndView saveItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwTaskBugValidator itwTaskBugValidator = new ItwTaskBugValidator();
		itwTaskBugValidator.validate(itwTaskBugBean, result);
		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);
		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();
			ItwTaskBugBean itwTaskBugBean1 = prepareItwTaskBugBeanForCreate(
					request, itwTaskBugBean);
			itwTaskBugBean1.setSeverityid(itwTaskBugBean.getSeverityid());
			itwTaskBugBean1.setType1(itwTaskBugBean.getType1());
			itwTaskBugBean1.setPriorityid(itwTaskBugBean.getPriorityid());
			itwTaskBugBean1.setPlatformid(itwTaskBugBean.getPlatformid());
			itwTaskBugBean1.setModuleid(itwTaskBugBean.getModuleid());
			itwTaskBugBean1.setProjectid(itwTaskBugBean.getProjectid());
			itwTaskBugBean1.setStatusid(itwTaskBugBean.getStatusid());
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute("userSession");
			String assigneeiddisplay1 = userSession.getItwUser().getUserid();
			List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
			model.remove("itwTaskBug1");
			model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
			List<ItwUser> itwUserList = itwUserService
					.getItwUserByShortName(assigneeiddisplay1);
			List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
			for (ItwUser itwUser : itwUserList) {
				IdList idList = new IdList();
				idList.setId(itwUser.getId());
				idList.setValue(assigneeiddisplay1);
				itwAssigneeIdList.add(idList);

			}
			itwTaskBugBean1.setItwAssigneeidList(itwAssigneeIdList);
			// Map<String, Object> model = new HashMap<String, Object>();

			List<ItwProject> itwProjectList = itwProjectService
					.listItwProjects(1);

			model.put("ItwProjects1", prepareListofItwProject(itwProjectList));
			model.remove("itwTaskBug1");
			model.put("itwTaskBug1", itwTaskBugBean1);
			List<ItwUserBean> itwUserBean = prepareListofItwUsers(
					itwUserService.listItwUsers(langId.intValue()), langId);

			model.put("ItwUserBean", itwUserBean);
			model.put("projectId", new Integer(projectId));

			return new ModelAndView("addItwTaskBug1", model);

		} else {

			ItwTaskBug itwTaskBug = prepareModelItwTaskBug(itwTaskBugBean,
					request);
			ItwTweets itwTweets = new ItwTweets();
			itwTweets.setUserid(itwTaskBug.getCreatedby());

			itwTweets.setProjectId(itwTaskBug.getProjectid());
			ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
					.getProjectid());

			ItwUser itwUser = itwUserService.getItwUser(itwTaskBug
					.getCreatedby());
			String tweetmsg = " " + itwProject.getShortname()
					+ " has been created by " + itwUser.getFirstname() + " "
					+ itwUser.getLastname();
			itwTweets.setTweetmsg(tweetmsg);

			itwTaskBugService.addItwTaskBug(itwTaskBug, itwTweets);
			ItwTaskBugAudit itwTaskBugAudit = prepareModelItwTaskBugAudit(
					itwTaskBug, 1);// 1 means adding a new bug
			itwTaskBugAuditService.addItwTaskBugAudit(itwTaskBugAudit);

			Integer maxId = itwTaskBugService.getItwTaskMaxId(itwTaskBug
					.getAssigneeid());

			System.out.println("====Max id of Task is =======" + maxId);

			return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
					+ projectId + "&&id=" + maxId);
		}
	}

	/*
	 * // First page to update an existing issue
	 * 
	 * @RequestMapping(value = "/editItwTaskBug", method = RequestMethod.GET)
	 * public ModelAndView editItwTaskBug(
	 * 
	 * @ModelAttribute("command") ItwTaskBugBean itwTaskBugBean, BindingResult
	 * result, HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * Map<String, Object> model = new HashMap<String, Object>(); ItwTaskBugBean
	 * itwTaskBug1 = prepareItwTaskBugBeanForAdd(request); ItwTaskBugBean
	 * itwTaskBug = prepareItwTaskBugBean(
	 * itwTaskBugService.getItwTaskBug(itwTaskBugBean.getId()), itwTaskBug1,
	 * request); model.remove("itwTaskBug");
	 * 
	 * model.put("itwTaskBug", itwTaskBug); return new
	 * ModelAndView("editItwTaskBug", model); }
	 */

	// First page to update an existing issue

	@RequestMapping(value = "/openItwTaskBug1", method = RequestMethod.GET)
	public ModelAndView openItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		String issueId = request.getParameter("id");

		ItwTaskBug itwTaskBugtemp = itwTaskBugService
				.getItwTaskBug(new Integer(issueId));

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		Integer loggedInUser = userSession.getItwUser().getId();

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		if ((itwTaskBugtemp.getLock1().equals("Y"))
				&& ((itwTaskBugtemp.getAssigneeid().intValue() != loggedInUser
						.intValue()))) {
			Map<String, Object> model = new HashMap<String, Object>();

			List<ItwTaskBugBean> itwTaskBugBeanList = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
			/*
			 * java.util.Collections.sort(itwTaskBugBean,
			 * ItwTaskBugBean.Comparators.ID);
			 */
			if (itwTaskBugBean != null) {
				@SuppressWarnings("unchecked")
				PagedListHolder pagedListHolder = new PagedListHolder(
						itwTaskBugBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				int pageSize = 15;
				pagedListHolder.setPageSize(pageSize);
				model.put("pagedListHolder", pagedListHolder);
				List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listItwTaskBugs(), request);

				model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
				model.put("ItwTaskBugs", itwTaskBugBean);
				model.put("ItwProjects1",
						prepareListofItwProject(itwProjectList));

				model.put("projectId", new Integer(projectId));

			}
			result.rejectValue("id", "already.locked", "Issue Id " + issueId
					+ " is already locked");
			return new ModelAndView("issueListPage1?projectId=" + projectId,
					model);

			// return new ModelAndView("issueListPage1");
		}

		Map<String, Object> model = new HashMap<String, Object>();

		ItwTaskBugBean itwTaskBug1 = prepareItwTaskBugBeanForIntialValues(request);

		itwTaskBug1.setStatusid(itwTaskBugtemp.getStatusid());
		itwTaskBug1.setStagesId(itwTaskBugtemp.getStagesId());
		itwTaskBug1.setReleaseid(itwTaskBugtemp.getReleaseid());

		ItwTaskBugBean itwTaskBug2 = prepareItwTaskBugBeanForAdd(request,
				itwTaskBug1);

		ItwTaskBugBean itwTaskBug = prepareItwTaskBugBean(itwTaskBugtemp,
				itwTaskBug2, request);

		List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
		ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
				.getProjectid());
		String moduleIds = itwProject.getModulename();
		String[] parts = moduleIds.split(",");
		for (int i = 0; i < parts.length; i++) {
			ModuleList arg0 = new ModuleList();
			arg0.setId(new Integer(parts[i]));
			ItwModule itwModule1 = (ItwModule) itwModuleService
					.getItwModule(new Integer(parts[i]));
			String moduleName = itwModule1.getShortname();
			System.out
					.println("the loop for list of modules in a given project id is : "
							+ itwProject.getId()
							+ " the module id is : "
							+ parts[i] + "modulename is " + moduleName);

			arg0.setModuleName(moduleName);
			List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleIdAndNodeName(new Integer(
							parts[i]));
			String nodeName = null;
			for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
				nodeName = ItwModuleTree2.getId();
			}
			List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleId(new Integer(parts[i]),
							nodeName);
			arg0.setItwModuleTreeList(itwModuleTreeList);
			itwModuleList.add(arg0);
		}

		itwTaskBug.setItwModuleList(itwModuleList);

		model.remove("itwTaskBug");

		ItwDocumentsBean document1 = new ItwDocumentsBean();
		List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
				.listDocuments(new Integer(issueId)));
		ItwImageBean image = new ItwImageBean();
		List<ItwImageBean> images = prepareListofItwImages(itwImageService
				.listItwImage(new Integer(issueId)));

		List<ItwTaskBugAuditBean> itwTaskBugAuditBeanList = prepareListofItwTaskBugAudits(
				itwTaskBugAuditService.listItwTaskBugAuditsAscend(new Integer(
						issueId)), request);

		model.put("commentsHistory", itwTaskBugAuditBeanList);
		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		model.put("projectId", new Integer(projectId));
		// ==============Added For Elapse Time=============

		List<DefectFlowBean> defectFlowBeanList = defectFlow(request,
				itwTaskBugBean);

		// Added For RCA================
		List<ItwRcaBean> itwRcaBean1 = prepareListofItwRca(
				itwRcaService.listItwRcasByTaskId(new Integer(issueId)),
				request);

		for (int i1 = 0; i1 < defectFlowBeanList.size(); i1++) {
			DefectFlowBean dfBean = defectFlowBeanList.get(i1);
			if (dfBean.getReworkFlag() == null) {
				dfBean.setReworkCount(0);
				dfBean.setReworkFlag("No");
				defectFlowBeanList.set(i1, dfBean);
				System.out.println("Value set for i1 " + i1
						+ " value of prev is "
						+ defectFlowBeanList.get(i1).getPreviousStateId()
						+ "value of next is "
						+ defectFlowBeanList.get(i1).getNextStateId()
						+ "rework count is 0 " + "Rework Flag is " + "No");
			}

			int reworkCount1 = 0;
			for (int i2 = i1 + 1; i2 < defectFlowBeanList.size(); i2++) {

				if ((defectFlowBeanList.get(i1).getPreviousStateId() == defectFlowBeanList
						.get(i2).getPreviousStateId())
						&& (defectFlowBeanList.get(i1).getNextStateId() == defectFlowBeanList
								.get(i2).getNextStateId())) {

					DefectFlowBean dfBean1 = defectFlowBeanList.get(i2);
					if (dfBean1.getReworkFlag() == null) {

						reworkCount1 = reworkCount1 + 1;
						dfBean1.setReworkCount(reworkCount1);
						dfBean1.setReworkFlag("Yes");
						defectFlowBeanList.set(i2, dfBean1);
						System.out.println("Value set for i2 "
								+ i2
								+ " value of prev is "
								+ defectFlowBeanList.get(i2)
										.getPreviousStateId()
								+ "value of next is "
								+ defectFlowBeanList.get(i2).getNextStateId()
								+ "rework count is  " + reworkCount1
								+ "Rework Flag is " + "Yes");

					}
				}

			}

		}

		List<ItwReworkBean> itwReworkBeanList = reworkCalculation(defectFlowBeanList);
		long tempGrandTotal = 0;
		for (ItwReworkBean itwReworkBean1 : itwReworkBeanList) {

			tempGrandTotal = tempGrandTotal + itwReworkBean1.getTempMinutes();

		}

		int tempHours = (int) tempGrandTotal / 60;
		System.out.println("grand temp hours " + tempHours);
		int tempMins = (int) (tempGrandTotal - (tempHours * 60));
		String grandTotal = tempHours + " Hours, " + tempMins + " Minutes ";

		if (defectFlowBeanList != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder1 = new PagedListHolder(
					defectFlowBeanList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder1.setPage(page);
			int pageSize = defectFlowBeanList.size();
			pagedListHolder1.setPageSize(pageSize);
			model.put("grandTotal", grandTotal);
			model.put("pagedListHolder1", pagedListHolder1);
			model.put("defectFlowBeansList", defectFlowBeanList);
			model.put("itwReworkBeanList", itwReworkBeanList);
			model.put("taskId1", itwTaskBugBean.getId());

		}
		// ================================================

		// Added By Puneet For Park============

		List<ItwIssuePark> itwIssueParkList = itwIssueParkService
				.listItwIssuePark(new Integer(issueId));

		ItwIssueParkBean itwIssueParkBean = new ItwIssueParkBean();

		for (ItwIssuePark itwIssuePark : itwIssueParkList) {

			itwIssueParkBean.setId(itwIssuePark.getId());
			itwIssueParkBean.setCreatedTime(itwIssuePark.getCreatedTime());

			itwIssueParkBean.setReason(itwIssuePark.getReason());
			itwIssueParkBean.setStatus(itwIssuePark.getStatus());
			itwIssueParkBean.setTaskId(itwIssuePark.getTaskId());
			itwIssueParkBean.setUpdatedTime(itwIssuePark.getUpdatedTime());

		}

		System.out.println("Park Created Time is ==========="
				+ itwIssueParkBean.getCreatedTime());

		itwTaskBug.setParkCreatedTime(itwIssueParkBean.getCreatedTime());
		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.getItwStatusTypesByShortName("New Issue Created");

		Iterator<ItwStatusTypes> iterator = itwStatusTypesList.listIterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes = (ItwStatusTypes) iterator.next();
			itwTaskBug.setStatusid(itwStatusTypes.getId());
			itwTaskBug.setStatusiddisplay("New Issue Created");
			System.out.println(itwStatusTypes.getId());
		}

		model.put("itwTaskBug", itwTaskBug);
		model.put("document", document1);
		model.put("documentList", document);
		model.put("image", image);
		model.put("images", images);
		model.put("itwRcaBean", itwRcaBean1);
		model.put("itwIssueParkBean", itwIssueParkBean);
		List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);

		model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
		return new ModelAndView("openItwTaskBug1", model);
	}

	@RequestMapping(value = "/editItwTaskBug1", method = RequestMethod.GET)
	public ModelAndView editItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		String issueId = request.getParameter("id");
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwTaskBug itwTaskBugtemp = itwTaskBugService
				.getItwTaskBug(new Integer(issueId));

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		Integer loggedInUser = userSession.getItwUser().getId();

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		if ((itwTaskBugtemp.getLock1().equals("Y"))
				&& ((itwTaskBugtemp.getAssigneeid().intValue() != loggedInUser
						.intValue()))) {
			Map<String, Object> model = new HashMap<String, Object>();

			List<ItwTaskBugBean> itwTaskBugBeanList = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
			/*
			 * java.util.Collections.sort(itwTaskBugBean,
			 * ItwTaskBugBean.Comparators.ID);
			 */
			if (itwTaskBugBean != null) {
				@SuppressWarnings("unchecked")
				PagedListHolder pagedListHolder = new PagedListHolder(
						itwTaskBugBeanList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				int pageSize = 15;
				pagedListHolder.setPageSize(pageSize);
				model.put("pagedListHolder", pagedListHolder);
				List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
						itwTaskBugService.listItwTaskBugs(), request);

				model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
				model.put("ItwTaskBugs", itwTaskBugBean);
				model.put("projectId", projectId);

			}
			result.rejectValue("id", "already.locked", "Issue Id " + issueId
					+ " is already locked");
			return new ModelAndView("issueListPage1", model);

			// return new ModelAndView("issueListPage1");
		}

		Map<String, Object> model = new HashMap<String, Object>();

		ItwTaskBugBean itwTaskBug1 = prepareItwTaskBugBeanForIntialValues(request);

		itwTaskBug1.setStatusid(itwTaskBugtemp.getStatusid());
		itwTaskBug1.setStagesId(itwTaskBugtemp.getStagesId());
		itwTaskBug1.setReleaseid(itwTaskBugtemp.getReleaseid());

		ItwTaskBugBean itwTaskBug2 = prepareItwTaskBugBeanForAdd(request,
				itwTaskBug1);

		ItwTaskBugBean itwTaskBug = prepareItwTaskBugBean(itwTaskBugtemp,
				itwTaskBug2, request);

		List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
		ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
				.getProjectid());
		String moduleIds = itwProject.getModulename();
		String[] parts = moduleIds.split(",");
		for (int i = 0; i < parts.length; i++) {
			ModuleList arg0 = new ModuleList();
			arg0.setId(new Integer(parts[i]));
			ItwModule itwModule1 = (ItwModule) itwModuleService
					.getItwModule(new Integer(parts[i]));
			String moduleName = itwModule1.getShortname();
			System.out
					.println("the loop for list of modules in a given project id is : "
							+ itwProject.getId()
							+ " the module id is : "
							+ parts[i] + "modulename is " + moduleName);

			arg0.setModuleName(moduleName);
			List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleIdAndNodeName(new Integer(
							parts[i]));
			String nodeName = null;
			for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
				nodeName = ItwModuleTree2.getId();
			}
			List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleId(new Integer(parts[i]),
							nodeName);
			arg0.setItwModuleTreeList(itwModuleTreeList);
			itwModuleList.add(arg0);
		}

		itwTaskBug.setItwModuleList(itwModuleList);

		model.remove("itwTaskBug");

		ItwDocumentsBean document1 = new ItwDocumentsBean();
		List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
				.listDocuments(new Integer(issueId)));
		ItwImageBean image = new ItwImageBean();
		List<ItwImageBean> images = prepareListofItwImages(itwImageService
				.listItwImage(new Integer(issueId)));

		List<ItwTaskBugAuditBean> itwTaskBugAuditBeanList = prepareListofItwTaskBugAudits(
				itwTaskBugAuditService.listItwTaskBugAuditsAscend(new Integer(
						issueId)), request);

		model.put("commentsHistory", itwTaskBugAuditBeanList);
		// ==============Added For Elapse Time=============

		List<DefectFlowBean> defectFlowBeanList = defectFlow(request,
				itwTaskBugBean);

		// Added For RCA================
		List<ItwRcaBean> itwRcaBean1 = prepareListofItwRca(
				itwRcaService.listItwRcasByTaskId(new Integer(issueId)),
				request);

		for (int i1 = 0; i1 < defectFlowBeanList.size(); i1++) {
			DefectFlowBean dfBean = defectFlowBeanList.get(i1);
			if (dfBean.getReworkFlag() == null) {
				dfBean.setReworkCount(0);
				dfBean.setReworkFlag("No");
				defectFlowBeanList.set(i1, dfBean);
				System.out.println("Value set for i1 " + i1
						+ " value of prev is "
						+ defectFlowBeanList.get(i1).getPreviousStateId()
						+ "value of next is "
						+ defectFlowBeanList.get(i1).getNextStateId()
						+ "rework count is 0 " + "Rework Flag is " + "No");
			}

			int reworkCount1 = 0;
			for (int i2 = i1 + 1; i2 < defectFlowBeanList.size(); i2++) {

				if ((defectFlowBeanList.get(i1).getPreviousStateId() == defectFlowBeanList
						.get(i2).getPreviousStateId())
						&& (defectFlowBeanList.get(i1).getNextStateId() == defectFlowBeanList
								.get(i2).getNextStateId())) {

					DefectFlowBean dfBean1 = defectFlowBeanList.get(i2);
					if (dfBean1.getReworkFlag() == null) {

						reworkCount1 = reworkCount1 + 1;
						dfBean1.setReworkCount(reworkCount1);
						dfBean1.setReworkFlag("Yes");
						defectFlowBeanList.set(i2, dfBean1);
						System.out.println("Value set for i2 "
								+ i2
								+ " value of prev is "
								+ defectFlowBeanList.get(i2)
										.getPreviousStateId()
								+ "value of next is "
								+ defectFlowBeanList.get(i2).getNextStateId()
								+ "rework count is  " + reworkCount1
								+ "Rework Flag is " + "Yes");

					}
				}

			}

		}

		List<ItwReworkBean> itwReworkBeanList = reworkCalculation(defectFlowBeanList);
		long tempGrandTotal = 0;
		for (ItwReworkBean itwReworkBean1 : itwReworkBeanList) {

			tempGrandTotal = tempGrandTotal + itwReworkBean1.getTempMinutes();

		}

		int tempHours = (int) tempGrandTotal / 60;
		System.out.println("grand temp hours " + tempHours);
		int tempMins = (int) (tempGrandTotal - (tempHours * 60));
		String grandTotal = tempHours + " Hours, " + tempMins + " Minutes ";

		if (defectFlowBeanList != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder1 = new PagedListHolder(
					defectFlowBeanList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder1.setPage(page);
			int pageSize = defectFlowBeanList.size();
			pagedListHolder1.setPageSize(pageSize);
			model.put("grandTotal", grandTotal);
			model.put("pagedListHolder1", pagedListHolder1);
			model.put("defectFlowBeansList", defectFlowBeanList);
			model.put("itwReworkBeanList", itwReworkBeanList);
			model.put("taskId1", itwTaskBugBean.getId());

		}
		// ================================================

		// Added By Puneet For Park============

		List<ItwIssuePark> itwIssueParkList = itwIssueParkService
				.listItwIssuePark(new Integer(issueId));

		ItwIssueParkBean itwIssueParkBean = new ItwIssueParkBean();

		for (ItwIssuePark itwIssuePark : itwIssueParkList) {

			itwIssueParkBean.setId(itwIssuePark.getId());
			itwIssueParkBean.setCreatedTime(itwIssuePark.getCreatedTime());

			itwIssueParkBean.setReason(itwIssuePark.getReason());
			itwIssueParkBean.setStatus(itwIssuePark.getStatus());
			itwIssueParkBean.setTaskId(itwIssuePark.getTaskId());
			itwIssueParkBean.setUpdatedTime(itwIssuePark.getUpdatedTime());

		}

		System.out.println("Park Created Time is ==========="
				+ itwIssueParkBean.getCreatedTime());

		itwTaskBug.setParkCreatedTime(itwIssueParkBean.getCreatedTime());

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		model.put("projectId", new Integer(projectId));

		model.put("approvalname", itwTaskBug.getApprovedBy());
		model.put("itwTaskBug", itwTaskBug);
		model.put("document", document1);
		model.put("documentList", document);
		model.put("image", image);
		model.put("images", images);
		model.put("itwRcaBean", itwRcaBean1);
		model.put("itwIssueParkBean", itwIssueParkBean);
		model.put("projectId", projectId);
		List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);
		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);
		model.put("ItwUserBean", itwUserBean);
		model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
		return new ModelAndView("editItwTaskBug1", model);
	}

	// ===rework
	public List<ItwReworkBean> reworkCalculation(
			List<DefectFlowBean> defectFlowBeanList) {

		List<ItwReworkBean> itwReworkBeanList = new ArrayList<ItwReworkBean>();

		for (int i1 = 0; i1 < defectFlowBeanList.size(); i1++) {

			ItwReworkBean itwReworkBean = new ItwReworkBean();
			DefectFlowBean dfBean = defectFlowBeanList.get(i1);
			if (dfBean.getAlreadyUsed() != 2) {

				long reworkMinutes = 0;

				for (int i2 = i1 + 1; i2 < defectFlowBeanList.size(); i2++) {

					if ((defectFlowBeanList.get(i1).getPreviousStateId() == defectFlowBeanList
							.get(i2).getPreviousStateId())
							&& (defectFlowBeanList.get(i1).getNextStateId() == defectFlowBeanList
									.get(i2).getNextStateId())) {

						DefectFlowBean dfBean1 = defectFlowBeanList.get(i2);
						dfBean1.setAlreadyUsed(2);
						defectFlowBeanList.set(i2, dfBean1);
						if (dfBean1.getReworkCount() > 0) {

							reworkMinutes += dfBean1.getElapseMinutes();

						}

					}
				}

				itwReworkBean.setTempMinutes(reworkMinutes);
				int tempHours = (int) reworkMinutes / 60;
				System.out.println("temp hours " + tempHours);
				int tempMins = (int) (reworkMinutes - (tempHours * 60));

				if (defectFlowBeanList.get(i1).getPreviousState() == null) {
					itwReworkBean.setPreviousStateDisplay(" ");
				} else {
					itwReworkBean.setPreviousStateDisplay(defectFlowBeanList
							.get(i1).getPreviousState());
				}
				itwReworkBean.setNextStateDisplay(defectFlowBeanList.get(i1)
						.getNextState());

				String tempReworkTime = tempHours + " Hours, " + tempMins
						+ " Minutes ";
				itwReworkBean.setReworkDisplay(tempReworkTime);

				System.out.println(" Rework For " + dfBean.getPreviousState()
						+ " " + reworkMinutes);
				itwReworkBeanList.add(itwReworkBean);
			}

		}

		return itwReworkBeanList;
	}

	// ========

	@RequestMapping(value = "/captureImage1", method = RequestMethod.GET)
	public ModelAndView captureImage1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			ItwImageBean itwImageBean, HttpServletResponse response) {

		return new ModelAndView("editItwTaskBugPopUp");

	}

	@RequestMapping(value = "/editItwTaskBugPopUp", method = RequestMethod.GET)
	public ModelAndView editItwTaskBugPopUp(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			ItwImageBean itwImageBean, HttpServletResponse response) {

		return new ModelAndView("editItwTaskBugPopUp");

	}

	@RequestMapping(value = "/captureImage", method = RequestMethod.POST)
	public ModelAndView captureImage(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			ItwImageBean itwImageBean, HttpServletResponse response) {

		InputStream inputStream = null;

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		try {
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			Robot robot = new Robot();

			robot.delay(1000);

			BufferedImage img = robot.createScreenCapture(new Rectangle(size));

			File save_path = new File(request.getRealPath("")
					+ "/images/screen_" + itwTaskBugBean.getId() + ".jpg");
			ImageIO.write(img, "JPG", save_path);
			inputStream = new FileInputStream(request.getRealPath("")
					+ "/images/screen_" + itwTaskBugBean.getId() + ".jpg");
			Blob blob = Hibernate.createBlob(inputStream);
			ItwImage itwImage = new ItwImage();
			itwImage.setTaskId(itwTaskBugBean.getId());
			itwImage.setImage(blob);
			itwImage.setMimeType("image/jpeg");
			itwImage.setDescription(itwImageBean.getDescription());
			itwImageService.addItwImage(itwImage);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {

		}

		return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
				+ projectId + "&&id=" + itwTaskBugBean.getId()
				+ "#Capture-Image");

	}

	@RequestMapping(value = "/downloadImg")
	public ModelAndView downloadImg(
			@ModelAttribute("command") ItwImageBean itwImageBean,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) {

		ItwImage img = itwImageService.getItwImage(itwImageBean.getId());
		try {
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ img.getId() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(img.getMimeType());
			try {
				IOUtils.copy(img.getImage().getBinaryStream(), out);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/deleteItwImage")
	public ModelAndView deleteItwImage(
			@ModelAttribute("command") ItwImageBean itwImageBean,
			BindingResult result, HttpServletRequest request) {

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		itwImageService.deleteItwImage(itwImageBean.getId());
		return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
				+ projectId + "&&id=" + itwImageBean.getTaskId()
				+ "#Capture-Image");
	}

	@RequestMapping(value = "/updateOpenItwTaskBug1", method = RequestMethod.POST)
	public ModelAndView updateOpenItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		String issueId = request.getParameter("id");

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		ItwTaskBugValidatorDuringEdit itwTaskBugValidatorDuringEdit = new ItwTaskBugValidatorDuringEdit();
		itwTaskBugValidatorDuringEdit.validate(itwTaskBugBean, result);
		itwTaskBugValidatorDuringEdit.validategetReasonforOpen(itwTaskBugBean,
				result);
		if (result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();

			ItwTaskBugBean itwTaskBug1 = prepareItwTaskBugBeanForAdd(request,
					itwTaskBugBean);
			itwTaskBugBean.setItwModuleidList(itwTaskBug1.getItwModuleidList());
			itwTaskBugBean.setItwSeverityIdList(itwTaskBug1
					.getItwSeverityIdList());
			itwTaskBugBean.setItwType1List(itwTaskBug1.getItwType1List());
			itwTaskBugBean.setItwPriorityidList(itwTaskBug1
					.getItwPriorityidList());

			itwTaskBugBean.setItwPlatformidList(itwTaskBug1
					.getItwPlatformidList());
			itwTaskBugBean.setUrl(itwTaskBug1.getUrl());
			itwTaskBugBean.setItwProjectidList(itwTaskBug1
					.getItwProjectidList());
			itwTaskBugBean.setItwStatusidList(itwTaskBug1.getItwStatusidList());
			itwTaskBugBean.setItwStagesidList(itwTaskBug1.getItwStagesidList());
			itwTaskBugBean.setItwReleasesidList(itwTaskBug1
					.getItwReleasesidList());
			itwTaskBugBean.setItwAssigneeidList(itwTaskBug1
					.getItwAssigneeidList());
			itwTaskBugBean.setDependsOn(itwTaskBug1.getDependsOn());
			itwTaskBugBean.setReasonforOpen(itwTaskBug1.getReasonforOpen());
			List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
			ItwProject itwProject = itwProjectService
					.getItwProject(itwTaskBugBean.getProjectid());
			String moduleIds = itwProject.getModulename();
			String[] parts = moduleIds.split(",");
			for (int i = 0; i < parts.length; i++) {
				ModuleList arg0 = new ModuleList();
				arg0.setId(new Integer(parts[i]));
				ItwModule itwModule1 = (ItwModule) itwModuleService
						.getItwModule(new Integer(parts[i]));
				String moduleName = itwModule1.getShortname();
				System.out
						.println("the loop for list of modules in a given project id is : "
								+ itwProject.getId()
								+ " the module id is : "
								+ parts[i] + "modulename is " + moduleName);

				arg0.setModuleName(moduleName);
				List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
						.listItwModuleTreeByModuleIdAndNodeName(new Integer(
								parts[i]));
				String nodeName = null;
				for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
					nodeName = ItwModuleTree2.getId();
				}
				List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
						.listItwModuleTreeByModuleId(new Integer(parts[i]),
								nodeName);
				arg0.setItwModuleTreeList(itwModuleTreeList);
				itwModuleList.add(arg0);
			}

			itwTaskBugBean.setItwModuleList(itwModuleList);

			model.remove("itwTaskBug");

			ItwDocumentsBean document1 = new ItwDocumentsBean();
			List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
					.listDocuments(new Integer(issueId)));
			ItwImageBean image = new ItwImageBean();
			List<ItwImageBean> images = prepareListofItwImages(itwImageService
					.listItwImage(new Integer(issueId)));

			model.put("document", document1);
			model.put("documentList", document);
			model.put("image", image);
			model.put("images", images);
			model.remove("itwTaskBug");
			model.put("itwTaskBug", itwTaskBugBean);
			List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
			model.remove("itwTaskBug1");
			model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
			model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

			model.put("projectId", new Integer(projectId));

			return new ModelAndView("openItwTaskBug1", model);

		} else {

			ItwTaskBug itwTaskBug = prepareOpenModelItwTaskBugforUpdate(
					itwTaskBugBean, request);

			itwTaskBug.setId(itwTaskBugBean.getId());
			ItwTaskBug itwTaskBugtemp = itwTaskBugService
					.getItwTaskBug(itwTaskBugBean.getId());
			itwTaskBug.setCreatedby(itwTaskBugtemp.getCreatedby());
			itwTaskBug.setCreatedtime(itwTaskBugtemp.getCreatedtime());
			ItwTweets itwTweets = new ItwTweets();

			UserSession userSession = (UserSession) request.getSession()
					.getAttribute("userSession");
			ItwUser loggedInUser = userSession.getItwUser();
			ItwUser itwUser = itwUserService.getItwUser(loggedInUser.getId());
			itwTweets.setUserid(loggedInUser.getId());
			itwTweets.setProjectId(itwTaskBug.getProjectid());
			itwTweets.setIssueId(new Integer(issueId));

			ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
					.getProjectid());
			ItwUser assignedUser = itwUserService.getItwUser(itwTaskBug
					.getAssigneeid());
			ItwSeverity issueSeverity = itwSeverityService
					.getItwSeverity(itwTaskBug.getSeverityid());
			String issueSeverityStr = issueSeverity.getShortname();
			ItwPriority issuePriority = itwPriorityService
					.getItwPriority(itwTaskBug.getPriorityid());
			String issuePriorityStr = issuePriority.getShortname();

			ItwStatusTypes issueStatusTypes = itwStatusTypesService
					.getItwStatusTypes(itwTaskBug.getStatusid());
			String issueStatusTypeStr = issueStatusTypes.getShortname();
			String tweetmsg = " " + itwProject.getShortname()
					+ " has been updated by " + itwUser.getFirstname() + " "
					+ itwUser.getLastname() + ".\nThe Issue Id " + issueId
					+ " has been assigned to " + assignedUser.getFirstname()
					+ " " + assignedUser.getLastname()
					+ ".\nThe Severity of Issue is " + issueSeverityStr
					+ ".\nThe Priority of Issue is " + issuePriorityStr
					+ ".\nThe Status of Issue is " + issueStatusTypeStr + ".";

			itwTweets.setTweetmsg(tweetmsg);
			itwTaskBugService.addItwTaskBug(itwTaskBug, itwTweets);
			ItwTaskBugAudit itwTaskBugAudit = prepareModelItwTaskBugAudit(
					itwTaskBug, 2); // where 2 means update
			itwTaskBugAuditService.addItwTaskBugAudit(itwTaskBugAudit);

			if (itwTaskBugAudit.getStatusid() == 9) {

				return new ModelAndView("redirect:/addItwRca.html?projectId="
						+ projectId + "&&id=" + new Integer(issueId));

			}

			else {
				return new ModelAndView("redirect:/issueList1.html?projectId="
						+ projectId);
			}
		}
	}

	@RequestMapping(value = "/updateItwTaskBug1", method = RequestMethod.POST)
	public ModelAndView updateItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		String issueId = request.getParameter("id");

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwTaskBugValidatorDuringEdit itwTaskBugValidatorDuringEdit = new ItwTaskBugValidatorDuringEdit();
		itwTaskBugValidatorDuringEdit.validate(itwTaskBugBean, result);

		if (result.hasErrors()) {

			Map<String, Object> model = new HashMap<String, Object>();

			ItwTaskBugBean itwTaskBug1 = prepareItwTaskBugBeanForAdd(request,
					itwTaskBugBean);
			itwTaskBugBean.setItwModuleidList(itwTaskBug1.getItwModuleidList());
			itwTaskBugBean.setItwSeverityIdList(itwTaskBug1
					.getItwSeverityIdList());
			itwTaskBugBean.setItwType1List(itwTaskBug1.getItwType1List());
			itwTaskBugBean.setItwPriorityidList(itwTaskBug1
					.getItwPriorityidList());
			itwTaskBugBean.setItwPlatformidList(itwTaskBug1
					.getItwPlatformidList());
			itwTaskBugBean.setUrl(itwTaskBug1.getUrl());
			itwTaskBugBean.setItwProjectidList(itwTaskBug1
					.getItwProjectidList());
			itwTaskBugBean.setItwStatusidList(itwTaskBug1.getItwStatusidList());
			itwTaskBugBean.setItwStagesidList(itwTaskBug1.getItwStagesidList());
			itwTaskBugBean.setItwReleasesidList(itwTaskBug1
					.getItwReleasesidList());
			itwTaskBugBean.setItwAssigneeidList(itwTaskBug1
					.getItwAssigneeidList());
			itwTaskBugBean.setDependsOn(itwTaskBug1.getDependsOn());
			itwTaskBugBean.setReasonforOpen(itwTaskBug1.getReasonforOpen());
			List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
			ItwProject itwProject = itwProjectService
					.getItwProject(itwTaskBugBean.getProjectid());
			String moduleIds = itwProject.getModulename();
			String[] parts = moduleIds.split(",");
			for (int i = 0; i < parts.length; i++) {
				ModuleList arg0 = new ModuleList();
				arg0.setId(new Integer(parts[i]));
				ItwModule itwModule1 = (ItwModule) itwModuleService
						.getItwModule(new Integer(parts[i]));
				String moduleName = itwModule1.getShortname();
				System.out
						.println("the loop for list of modules in a given project id is : "
								+ itwProject.getId()
								+ " the module id is : "
								+ parts[i] + "modulename is " + moduleName);

				arg0.setModuleName(moduleName);
				List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
						.listItwModuleTreeByModuleIdAndNodeName(new Integer(
								parts[i]));
				String nodeName = null;
				for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
					nodeName = ItwModuleTree2.getId();
				}
				List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
						.listItwModuleTreeByModuleId(new Integer(parts[i]),
								nodeName);
				arg0.setItwModuleTreeList(itwModuleTreeList);
				itwModuleList.add(arg0);
			}

			itwTaskBugBean.setItwModuleList(itwModuleList);

			model.remove("itwTaskBug");

			ItwDocumentsBean document1 = new ItwDocumentsBean();
			List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
					.listDocuments(new Integer(issueId)));
			ItwImageBean image = new ItwImageBean();
			List<ItwImageBean> images = prepareListofItwImages(itwImageService
					.listItwImage(new Integer(issueId)));

			model.put("document", document1);
			model.put("documentList", document);
			model.put("image", image);
			model.put("images", images);
			model.remove("itwTaskBug");
			model.put("itwTaskBug", itwTaskBugBean);
			List<ItwTaskBugBean> dependsOnitwTaskBugBean = prepareListofItwTaskBugs(
					itwTaskBugService.listItwTaskBugs(), request);
			model.remove("itwTaskBug1");
			model.put("itwTaskBugDependOn", dependsOnitwTaskBugBean);
			List<ItwUserBean> itwUserBean = prepareListofItwUsers(
					itwUserService.listItwUsers(langId.intValue()), langId);
			model.put("ItwUserBean", itwUserBean);

			model.put("projectId", new Integer(projectId));

			return new ModelAndView("editItwTaskBug1", model);

		} else {

			ItwTaskBug itwTaskBug = prepareModelItwTaskBugforUpdate(
					itwTaskBugBean, request);

			itwTaskBug.setId(itwTaskBugBean.getId());
			ItwTaskBug itwTaskBugtemp = itwTaskBugService
					.getItwTaskBug(itwTaskBugBean.getId());
			itwTaskBug.setCreatedby(itwTaskBugtemp.getCreatedby());
			itwTaskBug.setCreatedtime(itwTaskBugtemp.getCreatedtime());
			ItwTweets itwTweets = new ItwTweets();

			UserSession userSession = (UserSession) request.getSession()
					.getAttribute("userSession");
			ItwUser loggedInUser = userSession.getItwUser();
			ItwUser itwUser = itwUserService.getItwUser(loggedInUser.getId());
			itwTweets.setUserid(loggedInUser.getId());
			itwTweets.setProjectId(itwTaskBug.getProjectid());
			itwTweets.setIssueId(new Integer(issueId));

			ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
					.getProjectid());
			ItwUser assignedUser = itwUserService.getItwUser(itwTaskBug
					.getAssigneeid());
			ItwSeverity issueSeverity = itwSeverityService
					.getItwSeverity(itwTaskBug.getSeverityid());
			String issueSeverityStr = issueSeverity.getShortname();
			ItwPriority issuePriority = itwPriorityService
					.getItwPriority(itwTaskBug.getPriorityid());
			String issuePriorityStr = issuePriority.getShortname();
			ItwStatusTypes issueStatusTypes = itwStatusTypesService
					.getItwStatusTypes(itwTaskBug.getStatusid());
			String issueStatusTypeStr = issueStatusTypes.getShortname();
			String tweetmsg = " " + itwProject.getShortname()
					+ " has been updated by " + itwUser.getFirstname() + " "
					+ itwUser.getLastname() + ".\nThe Issue Id " + issueId
					+ " has been assigned to " + assignedUser.getFirstname()
					+ " " + assignedUser.getLastname()
					+ ".\nThe Severity of Issue is " + issueSeverityStr
					+ ".\nThe Priority of Issue is " + issuePriorityStr
					+ ".\nThe Status of Issue is " + issueStatusTypeStr + ".";

			itwTweets.setTweetmsg(tweetmsg);
			itwTaskBugService.addItwTaskBug(itwTaskBug, itwTweets);
			ItwTaskBugAudit itwTaskBugAudit = prepareModelItwTaskBugAudit(
					itwTaskBug, 2); // where 2 means update
			itwTaskBugAuditService.addItwTaskBugAudit(itwTaskBugAudit);

			if (itwTaskBugAudit.getStatusid() == 9) {

				return new ModelAndView("redirect:/addItwRca.html?projectId="
						+ projectId + "&&id=" + new Integer(issueId));

			}

			else {
				return new ModelAndView("redirect:/issueList1.html?projectId="
						+ projectId);
			}
		}
	}

	@RequestMapping(value = "/savedoc", method = RequestMethod.POST)
	public ModelAndView saveItwDocument(
			@ModelAttribute("command") ItwDocumentsBean itwDocumentsBean,
			BindingResult result, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		ItwDocumentValidator itwDocumentValidator = new ItwDocumentValidator();
		itwDocumentValidator.validate(itwDocumentsBean, result);

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		if (result.hasErrors()) {

			return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
					+ projectId + "&&id=" + itwDocumentsBean.getTaskId()
					+ "#Attach-Documents");

		} else {

			Document itwDocument = null;
			try {

				itwDocument = prepareModelDoc(itwDocumentsBean, request, file);
				documentService.addDocument(itwDocument);
			} catch (RuntimeException runtimeException) {

				if (runtimeException.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				}

			}

			return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
					+ projectId + "&&id=" + itwDocument.getTaskId()
					+ "#Attach-Documents");
		}
	}

	@RequestMapping(value = "/downloadDoc")
	public ModelAndView download(
			@ModelAttribute("command") ItwDocumentsBean itwDocumentsBean,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) {

		Document doc = documentService.getDocument(itwDocumentsBean.getId());
		try {
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ doc.getName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(doc.getMimeType());
			IOUtils.copy(doc.getContent().getBinaryStream(), out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/deleteItwDocument")
	public ModelAndView deleteItwDocument(
			@ModelAttribute("command") ItwDocumentsBean itwDocumentsBean,
			BindingResult result, HttpServletRequest request) {

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		documentService.deleteDocument(itwDocumentsBean.getId());
		return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
				+ projectId + "&&id=" + itwDocumentsBean.getTaskId()
				+ "#Attach-Documents");
	}

	// ========================Knowledge=====================================

	@RequestMapping(value = "/issueListHistory", method = RequestMethod.GET)
	public ModelAndView issueListHistory(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwTaskBugBean> itwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);

		if (itwTaskBugBean != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwTaskBugBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("ItwTaskBugs", itwTaskBugBean);

		}
		return new ModelAndView("issueListHistory", model);
	}

	@RequestMapping(value = "/issueHistoryListPage", method = RequestMethod.GET)
	public ModelAndView issueHistoryListPage(HttpServletRequest request,
			ItwTaskBugBean itwTaskBugBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwTaskBugAuditBean> itwTaskBugAuditBean = prepareListofItwTaskBugAudits(
				itwTaskBugAuditService.listItwTaskBugAudits(itwTaskBugBean
						.getId()), request);

		if (itwTaskBugAuditBean != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwTaskBugAuditBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("itwTaskBugAudits", itwTaskBugAuditBean);
			model.put("taskId1", itwTaskBugBean.getId());

		}
		System.out.println("=====Befor Page=======");
		return new ModelAndView("issueHistoryListPage", model);
	}

	@RequestMapping(value = "/issueHistoryListPageInEdit", method = RequestMethod.GET)
	public ModelAndView issueHistoryListPageInEdit(HttpServletRequest request,
			ItwTaskBugBean itwTaskBugBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		model.put("projectId", new Integer(projectId));

		List<ItwTaskBugAuditBean> itwTaskBugAuditBean = prepareListofItwTaskBugAudits(
				itwTaskBugAuditService.listItwTaskBugAudits(itwTaskBugBean
						.getId()), request);

		if (itwTaskBugAuditBean != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwTaskBugAuditBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("itwTaskBugAudits", itwTaskBugAuditBean);
			model.put("taskId1", itwTaskBugBean.getId());

		}

		return new ModelAndView("issueHistoryListPageInEdit", model);
	}

	@RequestMapping(value = "/itwRcaList", method = RequestMethod.GET)
	public ModelAndView itwRcaList(HttpServletRequest request,
			ItwRcaBean itwRcaBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		Integer taskId = 1234;

		List<ItwRcaBean> itwRcaBean1 = prepareListofItwRca(
				itwRcaService.listItwRcasByTaskId(taskId), request);

		if (itwRcaBean1 != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(itwRcaBean1);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("rcaPagedListHolder", pagedListHolder);
			model.put("itwRcaBean", itwRcaBean1);

		}

		return new ModelAndView("itwRcaList", model);
	}

	@RequestMapping(value = "/addItwRca", method = RequestMethod.GET)
	public ModelAndView addItwRca(
			@ModelAttribute("command") ItwRcaBean itwRcaBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		ItwRcaBean itwRcaBean1 = prepareModelItwRcaforAdd(request, itwRcaBean);

		model.remove("itwRcaBean");
		model.put("itwRcaBean", itwRcaBean1);
		model.put("projectId", projectId);

		return new ModelAndView("addItwRca", model);

	}

	@RequestMapping(value = "/saveItwRca", method = RequestMethod.POST)
	public ModelAndView saveItwRca(
			@ModelAttribute("command") ItwRcaBean itwRcaBean,
			BindingResult result, HttpServletRequest request) {

		ItwRca itwRca = prepareModelItwRca(itwRcaBean);
		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		itwRcaService.addItwRca(itwRca);

		return new ModelAndView("redirect:/editItwTaskBug1.html?projectId="
				+ projectId + "&&id=" + itwRcaBean.getTaskId() + "#RCA-Details");

	}

	@RequestMapping(value = "/viewItwRca", method = RequestMethod.GET)
	public ModelAndView viewItwRca(
			@ModelAttribute("command") ItwRcaBean itwRcaBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();

		String rcaId = request.getParameter("id");

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		ItwRcaBean itwRcaBean1 = prepareListofItwRcaForView(
				itwRcaService.getItwRca(new Integer(rcaId)), langId);

		model.put("itwRcaBean1", itwRcaBean1);

		return new ModelAndView("viewItwRca", model);

	}

	private ItwRcaBean prepareListofItwRcaForView(ItwRca itwRca, Integer langId) {

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesTypes(langId.intValue());

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		ItwRcaBean bean = null;

		String injectedStageIdDisplay = null;
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
			if (itwStagesTypes.getId().intValue() == new Integer(
					itwRca.getInjectedStageId()).intValue()) {
				injectedStageIdDisplay = itwStagesTypes.getShortname();
			}
		}

		bean = new ItwRcaBean();

		bean.setInjectedStageIdDisplay(injectedStageIdDisplay);

		bean.setId(itwRca.getId());

		String detectedStageIdDisplay = null;
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
			if (itwStagesTypes.getId().intValue() == new Integer(
					itwRca.getDetectedStageId()).intValue()) {
				detectedStageIdDisplay = itwStagesTypes.getShortname();
			}
		}

		bean.setDetectedStageIdDisplay(detectedStageIdDisplay);

		String injectedByDisplay = null;

		for (ItwUser itwUser : itwUserList) {
			if (itwUser.getId().intValue() == new Integer(
					itwRca.getInjectedBy()).intValue()) {
				injectedByDisplay = itwUser.getUserid();

			}
		}

		bean.setInjectedByDisplay(injectedByDisplay);

		String detectedByDisplay = null;

		for (ItwUser itwUser : itwUserList) {
			if (itwUser.getId().intValue() == new Integer(
					itwRca.getDetectedBy()).intValue()) {
				detectedByDisplay = itwUser.getUserid();
			}
		}

		bean.setDetectedByDisplay(detectedByDisplay);

		bean.setTaskId(itwRca.getTaskId());
		bean.setRcaDetails(itwRca.getRcaDetails());

		return bean;
	}

	private ItwRca prepareModelItwRca(ItwRcaBean itwRcaBean) {

		ItwRca itwRca = new ItwRca();

		itwRca.setId(itwRcaBean.getId());
		itwRca.setDetectedBy(itwRcaBean.getDetectedBy());
		itwRca.setDetectedStageId(itwRcaBean.getDetectedStageId());
		itwRca.setInjectedBy(itwRcaBean.getInjectedBy());
		itwRca.setInjectedStageId(itwRcaBean.getInjectedStageId());
		itwRca.setTaskId(itwRcaBean.getTaskId());
		itwRca.setRcaDetails(itwRcaBean.getRcaDetails());
		itwRca.setFeedbackGivenTo(itwRcaBean.getFeedbackGivenTo());
		itwRca.setCorrectiveActionTaken(itwRcaBean.getCorrectiveActionTaken());

		return itwRca;
	}

	private ItwRcaBean prepareModelItwRcaforAdd(HttpServletRequest request,
			ItwRcaBean itwRcaBean) {

		String issueId = request.getParameter("id");

		ItwRcaBean bean = new ItwRcaBean();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesTypes(langId.intValue());

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<ItwRcaFeedback> itwRcaFeedbackList = itwRcaFeedbackService
				.listItwRcaFeedbacks();

		List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
		for (ItwUser itwUser : itwUserList) {
			IdList idList = new IdList();
			idList.setId(itwUser.getId());
			idList.setValue(itwUser.getUserid());
			itwAssigneeIdList.add(idList);

		}
		bean.setItwInjectedByidList(itwAssigneeIdList);
		bean.setItwDetectedByidList(itwAssigneeIdList);

		List<IdList> itwStagesTypeList = new ArrayList<IdList>();
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {

			IdList idList = new IdList();
			idList.setId(itwStagesTypes.getId());
			idList.setValue(itwStagesTypes.getShortname());

			itwStagesTypeList.add(idList);

		}

		bean.setItwInjectedStagesidList(itwStagesTypeList);
		bean.setItwDetectedStagesidList(itwStagesTypeList);

		List<IdList> feedbackGivenToList = new ArrayList<IdList>();
		for (ItwRcaFeedback itwRcaFeedback : itwRcaFeedbackList) {

			IdList idList = new IdList();
			idList.setId(itwRcaFeedback.getId());
			idList.setValue(itwRcaFeedback.getFeedbackTo());

			feedbackGivenToList.add(idList);

		}

		bean.setFeedbackGivenToList(feedbackGivenToList);

		bean.setTaskId(new Integer(issueId));

		return bean;
	}

	private List<ItwRcaBean> prepareListofItwRca(List<ItwRca> itwRcaList,
			HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesTypes(langId.intValue());

		List<ItwRcaBean> beans = null;
		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<ItwRcaFeedback> itwRcaFeedbackList = itwRcaFeedbackService
				.listItwRcaFeedbacks();

		if (itwRcaList != null && !itwRcaList.isEmpty()) {
			beans = new ArrayList<ItwRcaBean>();
			ItwRcaBean bean = null;

			for (ItwRca itwRca : itwRcaList) {

				String injectedStageIdDisplay = null;
				for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
					if (itwStagesTypes.getId().intValue() == new Integer(
							itwRca.getInjectedStageId()).intValue()) {
						injectedStageIdDisplay = itwStagesTypes.getShortname();
					}
				}

				bean = new ItwRcaBean();

				bean.setInjectedStageIdDisplay(injectedStageIdDisplay);

				bean.setId(itwRca.getId());

				String detectedStageIdDisplay = null;
				for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
					if (itwStagesTypes.getId().intValue() == new Integer(
							itwRca.getDetectedStageId()).intValue()) {
						detectedStageIdDisplay = itwStagesTypes.getShortname();
					}
				}

				bean.setDetectedStageIdDisplay(detectedStageIdDisplay);

				String injectedByDisplay = null;

				for (ItwUser itwUser : itwUserList) {
					if (itwUser.getId().intValue() == new Integer(
							itwRca.getInjectedBy()).intValue()) {
						injectedByDisplay = itwUser.getUserid();

						System.out
								.println("Injectd By ===" + injectedByDisplay);
					}
				}

				bean.setInjectedByDisplay(injectedByDisplay);

				String detectedByDisplay = null;

				for (ItwUser itwUser : itwUserList) {
					if (itwUser.getId().intValue() == new Integer(
							itwRca.getDetectedBy()).intValue()) {
						detectedByDisplay = itwUser.getUserid();
					}
				}

				bean.setDetectedByDisplay(detectedByDisplay);

				String feedbackGivenToDisplay = null;

				for (ItwRcaFeedback itwRcaFeedback : itwRcaFeedbackList) {
					if (itwRcaFeedback.getId().intValue() == new Integer(
							itwRca.getFeedbackGivenTo()).intValue()) {
						feedbackGivenToDisplay = itwRcaFeedback.getFeedbackTo();
					}
				}

				bean.setFeedbackGivenToDisplay(feedbackGivenToDisplay);

				bean.setTaskId(itwRca.getTaskId());
				bean.setRcaDetails(itwRca.getRcaDetails());

				bean.setCorrectiveActionTaken(itwRca.getCorrectiveActionTaken());

				beans.add(bean);

			}
		}
		return beans;

	}

	@RequestMapping(value = "/viewItwTaskBugAudit", method = RequestMethod.GET)
	public ModelAndView viewItwTaskBugAudit(
			@ModelAttribute("command") ItwTaskBugAuditBean itwTaskBugAuditBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		ItwTaskBugAuditBean itwTaskBugAudit1 = prepareItwTaskBugAuditBeanForAdd(langId);
		String issueId = request.getParameter("id");

		ItwTaskBugAuditBean itwTaskBugAudit = prepareItwTaskBugAuditBean(
				itwTaskBugAuditService.getItwTaskBugAudit(new Integer(issueId)),
				itwTaskBugAudit1, request);

		List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
		ItwProject itwProject = itwProjectService
				.getItwProject(itwTaskBugAudit1.getProjectid());
		String moduleIds = itwProject.getModulename();
		String[] parts = moduleIds.split(",");
		for (int i = 0; i < parts.length; i++) {
			ModuleList arg0 = new ModuleList();
			arg0.setId(new Integer(parts[i]));
			ItwModule itwModule1 = (ItwModule) itwModuleService
					.getItwModule(new Integer(parts[i]));
			String moduleName = itwModule1.getShortname();
			System.out
					.println("the loop for list of modules in a given project id is : "
							+ itwProject.getId()
							+ " the module id is : "
							+ parts[i] + "modulename is " + moduleName);

			arg0.setModuleName(moduleName);
			List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleIdAndNodeName(new Integer(
							parts[i]));
			String nodeName = null;
			for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
				nodeName = ItwModuleTree2.getId();
			}
			List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleId(new Integer(parts[i]),
							nodeName);
			arg0.setItwModuleTreeList(itwModuleTreeList);
			itwModuleList.add(arg0);
		}

		itwTaskBugAudit.setItwModuleList(itwModuleList);

		model.remove("itwTaskBug");

		ItwDocumentsBean document1 = new ItwDocumentsBean();
		List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
				.listDocuments(new Integer(issueId)));
		ItwImageBean image = new ItwImageBean();
		List<ItwImageBean> images = prepareListofItwImages(itwImageService
				.listItwImage(new Integer(issueId)));

		model.put("itwTaskBugAudit", itwTaskBugAudit);
		model.put("document", document1);
		model.put("documentList", document);
		model.put("image", image);
		model.put("images", images);

		return new ModelAndView("viewItwTaskBugAudit", model);

	}

	// =============================end======================================

	// ===========================User Log===================================

	@RequestMapping(value = "/itwUserLogsList", method = RequestMethod.GET)
	public ModelAndView itwUserLogsList(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwUserBean> itwUserBeans = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);

		if (itwUserBeans != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(itwUserBeans);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("itwUserBeans", itwUserBeans);

		}
		return new ModelAndView("itwUserLogsListPage", model);
	}

	@RequestMapping(value = "/viewItwUserLogs", method = RequestMethod.GET)
	public ModelAndView viewItwUserLogs(HttpServletRequest request,
			ItwUserLogsBean itwUserLogsBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		String userid = request.getParameter("userid");
		List<ItwUserLogsBean> itwUserLogsBeans = prepareListofItwUserLogs(itwUserLogsService
				.getAllUserLogs(userid));

		if (itwUserLogsBeans != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwUserLogsBeans);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("itwUserLogsBean1", itwUserLogsBeans);
			model.put("userid", userid);

		}

		return new ModelAndView("viewItwUserLogs", model);
	}

	// ================================end====================================

	@RequestMapping(value = "/viewItwTaskBug1", method = RequestMethod.GET)
	public ModelAndView viewItwTaskBug1(
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		Map<String, Object> model = new HashMap<String, Object>();
		ItwTaskBugBean itwTaskBug1 = prepareItwTaskBugBeanForAdd(request,
				itwTaskBugBean);
		String issueId = request.getParameter("id");

		ItwTaskBugBean itwTaskBug = prepareItwTaskBugBean(
				itwTaskBugService.getItwTaskBug(new Integer(issueId)),
				itwTaskBug1, request);
		// set project id for defect flow
		itwTaskBugBean.setProjectid(itwTaskBug.getProjectid());

		List<ModuleList> itwModuleList = new ArrayList<ModuleList>();
		ItwProject itwProject = itwProjectService.getItwProject(itwTaskBug
				.getProjectid());
		String moduleIds = itwProject.getModulename();
		String[] parts = moduleIds.split(",");
		for (int i = 0; i < parts.length; i++) {
			ModuleList arg0 = new ModuleList();
			arg0.setId(new Integer(parts[i]));
			ItwModule itwModule1 = (ItwModule) itwModuleService
					.getItwModule(new Integer(parts[i]));
			String moduleName = itwModule1.getShortname();
			System.out
					.println("the loop for list of modules in a given project id is : "
							+ itwProject.getId()
							+ " the module id is : "
							+ parts[i] + "modulename is " + moduleName);

			arg0.setModuleName(moduleName);
			List<ItwModuleTree> itwModuleTreeList1 = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleIdAndNodeName(new Integer(
							parts[i]));
			String nodeName = null;
			for (ItwModuleTree ItwModuleTree2 : itwModuleTreeList1) {
				nodeName = ItwModuleTree2.getId();
			}
			List<ItwModuleTree> itwModuleTreeList = (List<ItwModuleTree>) itwModuleTreeService
					.listItwModuleTreeByModuleId(new Integer(parts[i]),
							nodeName);
			arg0.setItwModuleTreeList(itwModuleTreeList);
			itwModuleList.add(arg0);
		}

		itwTaskBug.setItwModuleList(itwModuleList);

		model.remove("itwTaskBug");

		ItwDocumentsBean document1 = new ItwDocumentsBean();
		List<ItwDocumentsBean> document = prepareListofItwDocuments(documentService
				.listDocuments(new Integer(issueId)));
		ItwImageBean image = new ItwImageBean();
		List<ItwImageBean> images = prepareListofItwImages(itwImageService
				.listItwImage(new Integer(issueId)));

		String projectIdString = request.getParameter("projectId");

		Integer projectId = new Integer(projectIdString);

		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		model.put("ItwProjects1", prepareListofItwProject(itwProjectList));

		// ====Added For Comment History

		List<ItwTaskBugAuditBean> itwTaskBugAuditBeanList = prepareListofItwTaskBugAudits(
				itwTaskBugAuditService.listItwTaskBugAuditsAscend(new Integer(
						issueId)), request);

		// ==============Added For Elapse Time=============

		List<DefectFlowBean> defectFlowBeanList = defectFlow(request,
				itwTaskBugBean);

		// Added For RCA================
		List<ItwRcaBean> itwRcaBean1 = prepareListofItwRca(
				itwRcaService.listItwRcasByTaskId(new Integer(issueId)),
				request);

		for (int i1 = 0; i1 < defectFlowBeanList.size(); i1++) {
			DefectFlowBean dfBean = defectFlowBeanList.get(i1);
			if (dfBean.getReworkFlag() == null) {
				dfBean.setReworkCount(0);
				dfBean.setReworkFlag("No");
				defectFlowBeanList.set(i1, dfBean);
				System.out.println("Value set for i1 " + i1
						+ " value of prev is "
						+ defectFlowBeanList.get(i1).getPreviousStateId()
						+ "value of next is "
						+ defectFlowBeanList.get(i1).getNextStateId()
						+ "rework count is 0 " + "Rework Flag is " + "No");
			}

			int reworkCount1 = 0;
			for (int i2 = i1 + 1; i2 < defectFlowBeanList.size(); i2++) {

				if ((defectFlowBeanList.get(i1).getPreviousStateId() == defectFlowBeanList
						.get(i2).getPreviousStateId())
						&& (defectFlowBeanList.get(i1).getNextStateId() == defectFlowBeanList
								.get(i2).getNextStateId())) {

					DefectFlowBean dfBean1 = defectFlowBeanList.get(i2);
					if (dfBean1.getReworkFlag() == null) {

						reworkCount1 = reworkCount1 + 1;
						dfBean1.setReworkCount(reworkCount1);
						dfBean1.setReworkFlag("Yes");
						defectFlowBeanList.set(i2, dfBean1);
						System.out.println("Value set for i2 "
								+ i2
								+ " value of prev is "
								+ defectFlowBeanList.get(i2)
										.getPreviousStateId()
								+ "value of next is "
								+ defectFlowBeanList.get(i2).getNextStateId()
								+ "rework count is  " + reworkCount1
								+ "Rework Flag is " + "Yes");

					}
				}

			}

		}

		List<ItwReworkBean> itwReworkBeanList = reworkCalculation(defectFlowBeanList);
		long tempGrandTotal = 0;
		for (ItwReworkBean itwReworkBean1 : itwReworkBeanList) {

			tempGrandTotal = tempGrandTotal + itwReworkBean1.getTempMinutes();

		}

		int tempHours = (int) tempGrandTotal / 60;
		System.out.println("grand temp hours " + tempHours);
		int tempMins = (int) (tempGrandTotal - (tempHours * 60));
		String grandTotal = tempHours + " Hours, " + tempMins + " Minutes ";

		if (defectFlowBeanList != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder1 = new PagedListHolder(
					defectFlowBeanList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder1.setPage(page);
			int pageSize = defectFlowBeanList.size();
			pagedListHolder1.setPageSize(pageSize);
			model.put("grandTotal", grandTotal);
			model.put("pagedListHolder1", pagedListHolder1);
			model.put("defectFlowBeansList", defectFlowBeanList);
			model.put("itwReworkBeanList", itwReworkBeanList);
			model.put("taskId1", itwTaskBugBean.getId());

		}

		// ================================================
		model.put("approvalname", itwTaskBug.getApprovedBy());
		model.put("itwTaskBug", itwTaskBug);
		model.put("document", document1);
		model.put("documentList", document);
		model.put("image", image);
		model.put("images", images);
		model.put("images", images);
		model.put("itwRcaBean", itwRcaBean1);
		model.put("commentsHistory", itwTaskBugAuditBeanList);

		List<ItwUserBean> itwUserBean = prepareListofItwUsers(
				itwUserService.listItwUsers(langId.intValue()), langId);
		model.put("ItwUserBean", itwUserBean);

		model.put("projectId", new Integer(projectId));
		return new ModelAndView("viewItwTaskBug1", model);

	}

	// download a document from View Itw Task bug page or from edit Itw Task bug
	// page
	@RequestMapping("/download/{documentname}")
	public String download(@PathVariable("documentname") String documentname,
			@ModelAttribute("command") ItwTaskBugBean itwTaskBugBean,
			BindingResult result, HttpServletResponse response) {

		try {
			OutputStream out = response.getOutputStream();
			ItwTaskBug itwTaskBug = itwTaskDAOTemp.get(itwTaskBugBean.getId(),
					out);
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ itwTaskBug.getName() + "\"");
			response.setContentType(itwTaskBug.getMimetype());
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// ***********************ITW TASK BUG END*********************************
	// ***********************************************************************

	// Display configuration main page to list the configuration parameters
	@RequestMapping(value = "/displayConfigPage", method = RequestMethod.GET)
	public ModelAndView displayConfigPage() {

		return new ModelAndView("configPage");
	}

	@RequestMapping(value = "/displayConfigPage1", method = RequestMethod.GET)
	public ModelAndView displayConfigPage1() {

		return new ModelAndView("configPage1");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		employeeService.deleteEmployee(prepareModel(employeeBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", null);
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareEmployeeBean(employeeService
				.getEmployee(employeeBean.getId())));
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}

	// ================Defect Workflow=============================

	@RequestMapping(value = "/defectWorkflowList", method = RequestMethod.GET)
	public ModelAndView defectWorkflowList(HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwTaskBugBean> itwTaskBugBean = prepareListofItwTaskBugs(
				itwTaskBugService.listItwTaskBugs(), request);

		if (itwTaskBugBean != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					itwTaskBugBean);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("ItwTaskBugs", itwTaskBugBean);

		}
		return new ModelAndView("DefectWorkflowList", model);
	}

	private Employee prepareModel(EmployeeBean employeeBean) {
		Employee employee = new Employee();
		employee.setEmpAddress(employeeBean.getAddress());
		employee.setEmpAge(employeeBean.getAge());
		employee.setEmpName(employeeBean.getName());
		employee.setSalary(employeeBean.getSalary());
		employee.setEmpId(employeeBean.getId());
		employeeBean.setId(null);
		return employee;
	}

	@SuppressWarnings("deprecation")
	private ItwUser prepareModelItwUser(ItwUserBean itwUserBean,
			HttpServletRequest request, MultipartFile file) {
		ItwUser itwUser = new ItwUser();
		itwUser.setFirstname(itwUserBean.getFirstname());
		itwUser.setLastname(itwUserBean.getLastname());
		itwUser.setEmailid(itwUserBean.getEmailid());
		itwUser.setPassword(Encrypt.md5(itwUserBean.getPassword()));
		itwUser.setUserid(itwUserBean.getUserid());
		itwUser.setPrimaryroleid(itwUserBean.getPrimaryroleid());
		itwUser.setTypeid(itwUserBean.getTypeid());
		itwUser.setCompanyid(itwUserBean.getCompanyid());
		itwUser.setEnabled(itwUserBean.getEnabled());
		itwUser.setAuthority(itwUserBean.getAuthority());
		itwUser.setOnetimepass(0);
		itwUser.setFailedcount(0);
		if (itwUserBean.getGenderid() == 1) {
			itwUser.setGender("M");
		}
		if (itwUserBean.getGenderid() == 2) {
			itwUser.setGender("F");
		}

		itwUser.setId(itwUserBean.getId());

		if (file != null) {
			if (file.getSize() > 0) {

				try {

					Blob blob = Hibernate.createBlob(file.getInputStream());
					itwUser.setIcon(blob);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			else {

				InputStream inputStream = null;
				try {

					inputStream = new FileInputStream(request.getRealPath("")
							+ "/images/blnkuser.png");
					try {

						Blob blob = Hibernate.createBlob(inputStream);
						itwUser.setIcon(blob);

					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
				}

			}
		}
		itwUserBean.setId(null);

		return itwUser;
	}

	private ItwRoles prepareModelItwRole(ItwRoleBean itwRoleBean, int langId) {
		ItwRoles itwRole = new ItwRoles();
		itwRole.setRolename(itwRoleBean.getRolename());
		System.out.println("rolename isss..................."
				+ itwRoleBean.getRolename());
		itwRole.setLangid(langId);
		System.out.println("langid is ...." + langId);

		return itwRole;
	}

	private ItwTaskBug prepareModelItwTaskBug(ItwTaskBugBean itwTaskBugBean,
			HttpServletRequest request) {

		ItwTaskBug itwTaskBug = new ItwTaskBug();
		itwTaskBug.setShortname(itwTaskBugBean.getShortname());
		itwTaskBug.setType1(itwTaskBugBean.getType1());

		if (itwTaskBugBean.getAssigneeid() == null) {
			itwTaskBug.setLock1("N");
		}
		itwTaskBug.setLock1("Y");
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		Integer assigneeid = userSession.getItwUser().getId();

		itwTaskBug.setModuleid(itwTaskBugBean.getModuleid());

		itwTaskBug.setStatusid(itwTaskBugBean.getStatusid());
		itwTaskBug.setDependsOn(itwTaskBugBean.getDependsOn());
		itwTaskBug.setVersion(itwTaskBugBean.getVersion());
		itwTaskBug.setUrl(itwTaskBugBean.getUrl());
		itwTaskBug.setProjectid(itwTaskBugBean.getProjectid());

		itwTaskBug.setPlatformid(itwTaskBugBean.getPlatformid());
		// itwTaskBug.setApprovedBy(itwTaskBugBean.getApprovedBy());
		// itwTaskBug.setApprovedStatus("true");
		itwTaskBug.setSeverityid(itwTaskBugBean.getSeverityid());

		itwTaskBug.setPriorityid(itwTaskBugBean.getPriorityid());
		itwTaskBug.setAssigneeid(itwTaskBugBean.getAssigneeid());

		itwTaskBug.setResolution(itwTaskBugBean.getResolution());
		itwTaskBug.setSummary(itwTaskBugBean.getSummary());
		itwTaskBug.setEfforts(itwTaskBugBean.getEfforts());

		itwTaskBug.setReleaseid(itwTaskBugBean.getReleaseid());
		itwTaskBug.setStagesId(itwTaskBugBean.getStagesId());

		java.sql.Date tempDate = null;
		try {
			String str = itwTaskBugBean.getDeadlinedisplay();

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dt = formatter.parse(str);
			tempDate = new java.sql.Date(dt.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		itwTaskBug.setDeadline(tempDate);

		itwTaskBug.setCreatedby(assigneeid);

		org.joda.time.DateTime createdTime = new org.joda.time.DateTime();
		tempDate = null;
		java.util.Date udt = createdTime.toDate();

		itwTaskBug.setCreatedtime(udt);
		itwTaskBug.setLastupdatedby(assigneeid);
		itwTaskBug.setLastupdatedtime(udt);

		itwTaskBug.setBulkupload("N");

		return itwTaskBug;
	}

	private ItwTaskBug prepareModelItwTaskBugforUpdate(
			ItwTaskBugBean itwTaskBugBean, HttpServletRequest request) {
		ItwTaskBug itwTaskBug = new ItwTaskBug();
		itwTaskBug.setShortname(itwTaskBugBean.getShortname());
		itwTaskBug.setType1(itwTaskBugBean.getType1());
		itwTaskBug.setLock1("Y");
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		Integer assigneeid = userSession.getItwUser().getId();
		itwTaskBug.setAssigneeid(itwTaskBugBean.getAssigneeid());

		itwTaskBug.setModuleid(itwTaskBugBean.getModuleid());

		itwTaskBug.setStatusid(itwTaskBugBean.getStatusid());
		itwTaskBug.setStagesId(itwTaskBugBean.getStagesId());

		itwTaskBug.setReleaseid(itwTaskBugBean.getReleaseid());

		itwTaskBug.setProjectid(itwTaskBugBean.getProjectid());
		itwTaskBug.setDependsOn(itwTaskBugBean.getDependsOn());
		itwTaskBug.setVersion(itwTaskBugBean.getVersion());
		itwTaskBug.setUrl(itwTaskBugBean.getUrl());
		itwTaskBug.setPlatformid(itwTaskBugBean.getPlatformid());

		itwTaskBug.setSeverityid(itwTaskBugBean.getSeverityid());
		itwTaskBug.setPriorityid(itwTaskBugBean.getPriorityid());
		itwTaskBug.setResolution(itwTaskBugBean.getResolution());
		itwTaskBug.setSummary(itwTaskBugBean.getSummary());
		itwTaskBug.setEfforts(itwTaskBugBean.getEfforts());
		itwTaskBug.setCreatedby(itwTaskBugBean.getCreatedby());
		itwTaskBug.setCreatedtime(itwTaskBugBean.getCreatedtime());
		org.joda.time.DateTime updatedTime = new org.joda.time.DateTime();

		java.util.Date udt = updatedTime.toDate();

		itwTaskBug.setLastupdatedtime(udt);

		java.sql.Date dte = null;
		try {
			String str = itwTaskBugBean.getDeadlinedisplay();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dt = formatter.parse(str);
			dte = new java.sql.Date(dt.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		itwTaskBug.setDeadline(dte);

		itwTaskBug.setLastupdatedby(assigneeid);
		itwTaskBug.setBulkupload("N");

		return itwTaskBug;
	}

	private ItwTaskBug prepareOpenModelItwTaskBugforUpdate(
			ItwTaskBugBean itwTaskBugBean, HttpServletRequest request) {

		System.out
				.println("===============inside update taskbug-===================== ");
		ItwTaskBug itwTaskBug = new ItwTaskBug();
		itwTaskBug.setShortname(itwTaskBugBean.getShortname());
		itwTaskBug.setType1(itwTaskBugBean.getType1());
		itwTaskBug.setLock1("Y");
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		Integer assigneeid = userSession.getItwUser().getId();
		itwTaskBug.setAssigneeid(itwTaskBugBean.getAssigneeid());

		itwTaskBug.setModuleid(itwTaskBugBean.getModuleid());

		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.getItwStatusTypesByShortName("New Issue Created");

		Iterator<ItwStatusTypes> iterator = itwStatusTypesList.listIterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes = (ItwStatusTypes) iterator.next();
			itwTaskBug.setStatusid(itwStatusTypes.getId());
			System.out.println(itwStatusTypes.getId());
		}
		itwTaskBug.setStagesId(itwTaskBugBean.getStagesId());

		itwTaskBug.setReleaseid(itwTaskBugBean.getReleaseid());

		itwTaskBug.setProjectid(itwTaskBugBean.getProjectid());
		itwTaskBug.setDependsOn(itwTaskBugBean.getDependsOn());
		itwTaskBug.setReasonforOpen(itwTaskBugBean.getReasonforOpen());
		itwTaskBug.setVersion(itwTaskBugBean.getVersion());
		itwTaskBug.setUrl(itwTaskBugBean.getUrl());
		itwTaskBug.setPlatformid(itwTaskBugBean.getPlatformid());

		itwTaskBug.setApprovedBy(itwTaskBugBean.getApprovedBy());
		itwTaskBug.setApprovedStatus("true");
		itwTaskBug.setSeverityid(itwTaskBugBean.getSeverityid());
		itwTaskBug.setPriorityid(itwTaskBugBean.getPriorityid());
		itwTaskBug.setResolution(itwTaskBugBean.getResolution());
		itwTaskBug.setSummary(itwTaskBugBean.getSummary());
		itwTaskBug.setEfforts(itwTaskBugBean.getEfforts());
		itwTaskBug.setCreatedby(itwTaskBugBean.getCreatedby());
		itwTaskBug.setCreatedtime(itwTaskBugBean.getCreatedtime());
		org.joda.time.DateTime updatedTime = new org.joda.time.DateTime();

		java.util.Date udt = updatedTime.toDate();

		itwTaskBug.setLastupdatedtime(udt);

		java.sql.Date dte = null;
		try {
			String str = itwTaskBugBean.getDeadlinedisplay();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dt = formatter.parse(str);
			dte = new java.sql.Date(dt.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		itwTaskBug.setDeadline(dte);

		itwTaskBug.setLastupdatedby(assigneeid);
		itwTaskBug.setBulkupload("N");

		return itwTaskBug;
	}

	private ItwUser prepareModelItwUserforUpdate(ItwUserBean itwUserBean) {
		ItwUser itwUser = new ItwUser();
		itwUser.setFirstname(itwUserBean.getFirstname());
		itwUser.setLastname(itwUserBean.getLastname());
		itwUser.setEmailid(itwUserBean.getEmailid());
		itwUser.setPassword(itwUserBean.getPassword());
		itwUser.setUserid(itwUserBean.getUserid());
		itwUser.setPrimaryroleid(itwUserBean.getPrimaryroleid());
		itwUser.setTypeid(itwUserBean.getTypeid());
		itwUser.setCompanyid(itwUserBean.getCompanyid());

		itwUser.setEnabled(itwUserBean.getEnabled());
		itwUser.setAuthority(itwUserBean.getAuthority());
		itwUser.setFailedcount(0);
		itwUser.setOnetimepass(itwUserBean.getOnetimepass());
		System.out.println("============Enable ======== "
				+ itwUserBean.getEnabled());
		itwUser.setIconid(itwUserBean.getIconid());
		if (itwUserBean.getGenderid() == 1) {
			itwUser.setGender("M");
		}
		if (itwUserBean.getGenderid() == 2) {
			itwUser.setGender("F");
		}
		itwUser.setId(itwUserBean.getId());
		itwUserBean.setId(null);

		return itwUser;
	}

	private ItwRoles prepareModelItwRoleforUpdate(ItwRoleBean itwRoleBean,
			int langId) {
		ItwRoles itwRole = new ItwRoles();
		itwRole.setId(itwRoleBean.getId());
		itwRole.setRolename(itwRoleBean.getRolename());
		itwRole.setLangid(langId);
		return itwRole;
	}

	private ItwUser prepareModelItwUserforDelete(ItwUserBean itwUserBean) {
		ItwUser itwUser = new ItwUser();
		itwUser.setFirstname(itwUserBean.getFirstname());
		itwUser.setLastname(itwUserBean.getLastname());
		itwUser.setEmailid(itwUserBean.getEmailid());
		itwUser.setPassword(itwUserBean.getPassword());
		itwUser.setUserid(itwUserBean.getUserid());
		itwUser.setPrimaryroleid(itwUserBean.getPrimaryroleid());
		itwUser.setTypeid(itwUserBean.getTypeid());
		itwUser.setCompanyid(itwUserBean.getCompanyid());

		itwUser.setIconid(itwUserBean.getIconid());

		itwUser.setId(itwUserBean.getId());
		itwUserBean.setId(null);

		return itwUser;
	}

	private ItwRoles prepareModelItwRoleforDelete(ItwRoleBean itwRoleBean) {
		ItwRoles itwRole = new ItwRoles();
		itwRole.setId(itwRoleBean.getId());

		itwRole.setRolename(itwRoleBean.getRolename());

		return itwRole;
	}

	private List<EmployeeBean> prepareListofBean(List<Employee> employees) {
		List<EmployeeBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for (Employee employee : employees) {
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getEmpAge());
				beans.add(bean);
			}
		}
		return beans;
	}

	private List<ItwUserBean> prepareListofItwUsers(List<ItwUser> itwUsers,
			Integer langId) {
		List<ItwUserBean> beans = null;

		if (itwUsers != null && !itwUsers.isEmpty()) {

			List<ItwRoles> itwRolesList = itwRolesService.listItwRoles(langId
					.intValue());
			List<ItwUserTypes> itwUserTypesList = itwUserTypesService
					.listItwUserTypes();

			List<ItwCompany> itwCompanysList = itwCompanyService
					.listItwCompanys(langId.intValue());

			beans = new ArrayList<ItwUserBean>();
			ItwUserBean bean = null;
			for (ItwUser itwUser : itwUsers) {
				bean = new ItwUserBean();
				bean.setId(itwUser.getId());
				bean.setFirstname(itwUser.getFirstname());
				bean.setLastname(itwUser.getLastname());
				bean.setEmailid(itwUser.getEmailid());
				bean.setCompanyid(itwUser.getCompanyid());
				String gendertemp = itwUser.getGender();
				if (gendertemp.equals("M")) {
					bean.setGender("Male");
				} else {
					bean.setGender("Female");
				}
				bean.setPrimaryroleid(itwUser.getPrimaryroleid());
				bean.setUserid(itwUser.getUserid());
				bean.setTypeid(itwUser.getTypeid());
				String companyiddisplay = null;
				String primaryroleiddisplay = null;

				for (ItwCompany itwCompany : itwCompanysList) {
					if (itwCompany.getId().intValue() == new Integer(
							itwUser.getCompanyid()).intValue()) {
						companyiddisplay = itwCompany.getName();
					}
				}
				bean.setCompanyiddisplay(companyiddisplay);
				for (ItwRoles itwRoles : itwRolesList) {
					if (itwRoles.getId().intValue() == new Integer(
							itwUser.getPrimaryroleid()).intValue()) {
						primaryroleiddisplay = itwRoles.getRolename();
					}
				}
				bean.setPrimaryroleiddisplay(primaryroleiddisplay);
				String typeiddisplay = null;
				for (ItwUserTypes itwUserTypes : itwUserTypesList) {
					if (itwUserTypes.getId().intValue() == new Integer(
							itwUser.getTypeid()).intValue()) {
						typeiddisplay = itwUserTypes.getShortName();
					}
				}
				bean.setTypeiddisplay(typeiddisplay);
				beans.add(bean);
			}
		}
		return beans;
	}

	private List<ItwRoleBean> prepareListofItwRoles(List<ItwRoles> itwRoles) {
		List<ItwRoleBean> beans = null;

		if (itwRoles != null && !itwRoles.isEmpty()) {

			beans = new ArrayList<ItwRoleBean>();
			ItwRoleBean bean = null;
			for (ItwRoles itwRole : itwRoles) {
				bean = new ItwRoleBean();
				bean.setId(itwRole.getId());
				bean.setRolename(itwRole.getRolename());
				beans.add(bean);
			}
		}
		return beans;
	}

	private List<ItwTaskBugBean> prepareListofItwTaskBugs(
			List<ItwTaskBug> itwTaskBugs, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesTypes(langId.intValue());
		List<ItwTaskBugBean> beans = null;
		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwProject> itwProjectList = itwProjectService
				.listItwProjects(langId);
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.listItwStatusTypes(langId.intValue());
		if (itwTaskBugs != null && !itwTaskBugs.isEmpty()) {
			beans = new ArrayList<ItwTaskBugBean>();
			ItwTaskBugBean bean = null;
			for (ItwTaskBug itwTaskBug : itwTaskBugs) {

				String stagesiddisplay = null;
				for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
					if (itwStagesTypes.getId().intValue() == new Integer(
							itwTaskBug.getStagesId()).intValue()) {
						stagesiddisplay = itwStagesTypes.getShortname();
					}
				}

				bean = new ItwTaskBugBean();
				bean.setStagesiddisplay(stagesiddisplay);
				bean.setId(itwTaskBug.getId());

				UserSession userSession = (UserSession) request.getSession()
						.getAttribute("userSession");
				String assigneeiddisplay1 = userSession.getItwUser()
						.getUserid();

				bean.setCurrentloggedinuserid(assigneeiddisplay1);

				bean.setShortname(itwTaskBug.getShortname());
				itwTaskBug.setApprovedBy(itwTaskBug.getApprovedBy());
				itwTaskBug.setApprovedStatus("true");
				bean.setDependsOn(itwTaskBug.getDependsOn());
				bean.setVersion(itwTaskBug.getVersion());
				bean.setUrl(itwTaskBug.getUrl());
				bean.setType1(itwTaskBug.getType1());
				bean.setReasonforOpen(itwTaskBug.getReasonforOpen());
				String type1display = null;
				for (ItwActivityType itwActivityType : itwActivityTypeList) {
					if (itwActivityType.getId().intValue() == new Integer(
							itwTaskBug.getType1()).intValue()) {
						type1display = itwActivityType.getShortname();
					}
				}
				bean.setType1display(type1display);
				bean.setLock1(itwTaskBug.getLock1());
				bean.setAssigneeid(itwTaskBug.getAssigneeid());

				String assigneeiddisplay = null;
				if (itwTaskBug.getAssigneeid() != null) {
					for (ItwUser itwUser : itwUserList) {
						if (itwUser.getId().intValue() == new Integer(
								itwTaskBug.getAssigneeid()).intValue()) {
							assigneeiddisplay = itwUser.getUserid();
						}
					}
				} else {
					assigneeiddisplay = new String("Unassigned");
				}
				bean.setAssigneeiddisplay(assigneeiddisplay);
				String createdbydisplay = null;
				for (ItwUser itwUser : itwUserList) {
					if (itwUser.getId().intValue() == new Integer(
							itwTaskBug.getCreatedby()).intValue()) {
						createdbydisplay = itwUser.getUserid();
					}
				}
				bean.setCreatedbydisplay(createdbydisplay);
				bean.setModuleid(itwTaskBug.getModuleid());
				// bean.setStatusid(itwTaskBug.getStatusid());
				itwTaskBug.setApprovedBy(itwTaskBug.getApprovedBy());
				itwTaskBug.setApprovedStatus("true");
				String statusiddisplay = null;
				for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {
					if (itwStatusTypes.getId().intValue() == new Integer(
							itwTaskBug.getStatusid()).intValue()) {
						statusiddisplay = itwStatusTypes.getShortname();
					}
				}
				bean.setStatusiddisplay(statusiddisplay);

				bean.setPriorityid(itwTaskBug.getPriorityid());

				String priorityiddisplay = null;
				for (ItwPriority itwPriority : itwPriorityList) {
					if (itwPriority.getId().intValue() == new Integer(
							itwTaskBug.getPriorityid()).intValue()) {
						priorityiddisplay = itwPriority.getShortname();
					}
				}
				bean.setPriorityiddisplay(priorityiddisplay);
				bean.setProjectid(itwTaskBug.getProjectid());
				String projectiddisplay = null;
				for (ItwProject itwProject : itwProjectList) {
					if (itwProject.getId().intValue() == new Integer(
							itwTaskBug.getProjectid()).intValue()) {
						projectiddisplay = itwProject.getShortname();
					}
				}
				bean.setProjectiddisplay(projectiddisplay);

				bean.setReleaseid(itwTaskBug.getReleaseid());
				bean.setMachinetypeid(itwTaskBug.getMachinetypeid());
				bean.setPlatformid(itwTaskBug.getPlatformid());
				String platformiddisplay = null;
				for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
					if (itwPlatForms.getId().intValue() == new Integer(
							itwTaskBug.getPlatformid()).intValue()) {
						platformiddisplay = itwPlatForms.getShortName();
					}
				}
				bean.setPlatformiddisplay(platformiddisplay);

				bean.setBrowserid(itwTaskBug.getBrowserid());
				bean.setTargetmilestoneid(itwTaskBug.getTargetmilestoneid());
				bean.setSeverityid(itwTaskBug.getSeverityid());

				String severityiddisplay = null;
				for (ItwSeverity itwSeverity : itwSeverityList) {
					if (itwSeverity.getId().intValue() == new Integer(
							itwTaskBug.getSeverityid()).intValue()) {
						severityiddisplay = itwSeverity.getShortname();
					}
				}

				bean.setSeverityiddisplay(severityiddisplay);

				bean.setAttachment(itwTaskBug.getAttachment());
				bean.setIcon(itwTaskBug.getIcon());
				bean.setMimetype(itwTaskBug.getMimetype());
				bean.setName(itwTaskBug.getName());
				bean.setSize1(itwTaskBug.getSize1());
				bean.setResolution(itwTaskBug.getResolution());
				bean.setSummary(itwTaskBug.getSummary());
				bean.setShortsummary(itwTaskBug.getShortsummary());
				bean.setEfforts(itwTaskBug.getEfforts());
				bean.setDeadline(itwTaskBug.getDeadline());
				bean.setAdditionalRef1(itwTaskBug.getAdditionalRef1());
				bean.setAdditionalRef2(itwTaskBug.getAdditionalRef2());
				bean.setAdditionalRef3(itwTaskBug.getAdditionalRef3());
				bean.setCreatedby(itwTaskBug.getCreatedby());
				bean.setCreatedtime(itwTaskBug.getCreatedtime());
				bean.setLastupdatedby(itwTaskBug.getLastupdatedby());
				bean.setLastupdatedtime(itwTaskBug.getLastupdatedtime());
				bean.setBulkupload(itwTaskBug.getBulkupload());
				bean.setBulkuploadfilename(itwTaskBug.getBulkuploadfilename());
				bean.setApprovedBy(itwTaskBug.getApprovedBy());
				bean.setApprovedBy("true");
				ItwSeverity itwSeverity = itwSeverityService
						.getItwSeverity(itwTaskBug.getSeverityid());

				ItwSeverityColour itwSeverityColour = itwSeverityColourService
						.getItwSeverityColour(itwSeverity.getColorcodeid());

				List<ItwStatusTypes> newtwStatusTypesList = itwStatusTypesService
						.getItwStatusTypesByShortName("New Issue Created");

				Iterator<ItwStatusTypes> iterator = newtwStatusTypesList
						.listIterator();
				while (iterator.hasNext()) {
					ItwStatusTypes itwStatusTypes = (ItwStatusTypes) iterator
							.next();
					bean.setStatusid(itwStatusTypes.getId());
					System.out.println(bean.getId());
				}

				bean.setSeverityColor(itwSeverityColour.getColourCode());
				beans.add(bean);

			}
		}
		return beans;
	}

	private EmployeeBean prepareEmployeeBean(Employee employee) {
		EmployeeBean bean = new EmployeeBean();
		bean.setAddress(employee.getEmpAddress());
		bean.setAge(employee.getEmpAge());
		bean.setName(employee.getEmpName());
		bean.setSalary(employee.getSalary());
		bean.setId(employee.getEmpId());
		return bean;
	}

	private ItwUserBean prepareItwUserBean(ItwUser itwUser, Integer langId) {
		ItwUserBean bean = new ItwUserBean();
		List<ItwRoles> itwRolesList = itwRolesService.listItwRoles(langId);
		List<ItwCompany> itwCompanyList = itwCompanyService
				.listItwCompanys(langId.intValue());
		List<ItwUserTypes> itwUserTypesList = itwUserTypesService
				.listItwUserTypes();
		List<IdList> itwUserTypesIdList = new ArrayList<IdList>();
		List<IdList> itwRolesIdList = new ArrayList<IdList>();
		List<IdList> itwCompanyIdList = new ArrayList<IdList>();
		List<IdList> itwGenderIdList = new ArrayList<IdList>();

		IdList idList1 = new IdList();
		idList1.setId(new Integer(1));
		idList1.setValue("Male");
		itwGenderIdList.add(idList1);
		IdList idList2 = new IdList();
		idList2.setId(new Integer(2));
		idList2.setValue("Female");
		itwGenderIdList.add(idList2);
		bean.setEnabled(itwUser.getEnabled());
		bean.setGenderList(itwGenderIdList);
		bean.setAuthority(itwUser.getAuthority());
		for (ItwCompany itwCompany : itwCompanyList) {

			IdList idList = new IdList();
			idList.setId(itwCompany.getId());
			idList.setValue(itwCompany.getName());
			itwCompanyIdList.add(idList);

		}

		bean.setItwCompanyidList(itwCompanyIdList);

		for (ItwRoles itwRoles : itwRolesList) {

			IdList idList = new IdList();
			idList.setId(itwRoles.getId());
			idList.setValue(itwRoles.getRolename());
			itwRolesIdList.add(idList);

		}
		bean.setItwPrimaryroleidList(itwRolesIdList);
		for (ItwUserTypes itwUserTypes : itwUserTypesList) {

			IdList idList = new IdList();
			idList.setId(itwUserTypes.getId());
			idList.setValue(itwUserTypes.getShortName());
			itwUserTypesIdList.add(idList);

		}
		bean.setItwTypeidList(itwUserTypesIdList);

		bean.setFirstname(itwUser.getFirstname());
		bean.setLastname(itwUser.getLastname());
		bean.setEmailid(itwUser.getEmailid());
		bean.setCompanyid(itwUser.getCompanyid());

		bean.setIconid(itwUser.getIconid());

		String stemp = itwUser.getGender();

		if (stemp.equals("M")) {

			bean.setGender("Male");
			bean.setGenderid(new Integer(1));
		} else {

			bean.setGender("Female");
			bean.setGenderid(new Integer(2));
		}
		bean.setPrimaryroleid(itwUser.getPrimaryroleid());
		bean.setUserid(itwUser.getUserid());
		bean.setTypeid(itwUser.getTypeid());
		bean.setPassword(itwUser.getPassword());
		bean.setId(itwUser.getId());
		return bean;
	}

	private ItwRoleBean prepareItwRoleBean(ItwRoles itwRole) {
		ItwRoleBean bean = new ItwRoleBean();
		bean.setId(itwRole.getId());
		bean.setRolename(itwRole.getRolename());
		return bean;
	}

	private ItwTaskBugBean prepareItwTaskBugBean(ItwTaskBug itwTaskBug,
			ItwTaskBugBean bean, HttpServletRequest request) {

		bean.setId(itwTaskBug.getId());
		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());
		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		/*
		 * Map<String, String> itwSeverityidListofValues1 = new
		 * LinkedHashMap<String, String>();
		 */

		ItwStatusTypes itwStatusTypesCurrent = itwStatusTypesService
				.getItwStatusTypes(itwTaskBug.getStatusid());

		// List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
		// .getItwStatusTypesByShortName("New Issue Created");

		/*
		 * Iterator<ItwStatusTypes> iterator =
		 * itwStatusTypesList.listIterator(); while (iterator.hasNext()) {
		 * ItwStatusTypes itwStatusTypes = (ItwStatusTypes) iterator.next();
		 * bean.setStatusid(itwStatusTypes.getId());
		 * 
		 * bean.setStatusiddisplay(itwStatusTypes.getShortname()); }
		 */

		bean.setStatusid(itwStatusTypesCurrent.getId());

		bean.setStatusiddisplay(itwStatusTypesCurrent.getShortname());
		ItwStagesTypes itwStagesTypesCurrent = itwStagesTypesService
				.getItwStagesTypes(itwTaskBug.getStagesId());

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesPrecedTypes(langId.intValue(),
						itwTaskBug.getStagesId());

		ItwReleases itwReleasesCurrent = itwReleasesService
				.getItwReleases(itwTaskBug.getReleaseid());

		List<ItwReleases> itwReleasesList = itwReleasesService
				.listItwReleasesPrecedTypes(langId.intValue(),
						itwTaskBug.getReleaseid());

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		String assigneeiddisplay1 = userSession.getItwUser().getUserid();

		bean.setCurrentloggedinuserid(assigneeiddisplay1);
		bean.setCurrentloggedinuser(userSession.getItwUser().getId());

		System.out.println("===Login User By==="
				+ bean.getCurrentloggedinuser());

		bean.setShortname(itwTaskBug.getShortname());
		bean.setDependsOn(itwTaskBug.getDependsOn());
		bean.setReasonforOpen(itwTaskBug.getReasonforOpen());
		bean.setVersion(itwTaskBug.getVersion());
		bean.setUrl(itwTaskBug.getUrl());
		bean.setType1(itwTaskBug.getType1());
		String type1display = null;
		for (ItwActivityType itwActivityType : itwActivityTypeList) {
			if (itwActivityType.getId().intValue() == new Integer(
					itwTaskBug.getType1()).intValue()) {
				type1display = itwActivityType.getShortname();
			}
		}
		bean.setReleasesId(itwTaskBug.getReleaseid());
		bean.setStagesId(itwTaskBug.getStagesId());
		bean.setType1display(type1display);
		bean.setLock1(itwTaskBug.getLock1());
		bean.setAssigneeid(itwTaskBug.getAssigneeid());

		String assigneeiddisplay = null;

		if (itwTaskBug.getAssigneeid() != null) {
			for (ItwUser itwUser : itwUserList) {
				if (itwUser.getId().intValue() == new Integer(
						itwTaskBug.getAssigneeid()).intValue()) {
					assigneeiddisplay = itwUser.getUserid();
				}
			}
		}

		System.out.println("Assignee Bean Value " + assigneeiddisplay);
		bean.setAssigneeiddisplay(assigneeiddisplay);

		bean.setModuleid(itwTaskBug.getModuleid());

		bean.setStagesId(itwTaskBug.getStagesId());
		bean.setReleaseid(itwTaskBug.getReleaseid());

		bean.setStagesiddisplay(itwStagesTypesCurrent.getShortname());

		bean.setReleasesiddisplay(itwReleasesCurrent.getShortname());

		bean.setPriorityid(itwTaskBug.getPriorityid());

		String priorityiddisplay = null;
		for (ItwPriority itwPriority : itwPriorityList) {
			if (itwPriority.getId().intValue() == new Integer(
					itwTaskBug.getPriorityid()).intValue()) {
				priorityiddisplay = itwPriority.getShortname();
			}
		}

		bean.setPriorityiddisplay(priorityiddisplay);
		bean.setProjectid(itwTaskBug.getProjectid());
		String projectiddisplay = null;
		for (ItwProject itwProject : itwProjectList) {
			if (itwProject.getId().intValue() == new Integer(
					itwTaskBug.getProjectid()).intValue()) {
				projectiddisplay = itwProject.getShortname();
			}
		}
		bean.setProjectiddisplay(projectiddisplay);
		bean.setReleaseid(itwTaskBug.getReleaseid());
		bean.setMachinetypeid(itwTaskBug.getMachinetypeid());
		bean.setPlatformid(itwTaskBug.getPlatformid());
		String platformiddisplay = null;
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			if (itwPlatForms.getId().intValue() == new Integer(
					itwTaskBug.getPlatformid()).intValue()) {
				platformiddisplay = itwPlatForms.getShortName();
			}
		}
		bean.setPlatformiddisplay(platformiddisplay);
		bean.setBrowserid(itwTaskBug.getBrowserid());
		bean.setTargetmilestoneid(itwTaskBug.getTargetmilestoneid());
		bean.setSeverityid(itwTaskBug.getSeverityid());

		String severityiddisplay = null;
		for (ItwSeverity itwSeverity : itwSeverityList) {
			if (itwSeverity.getId().intValue() == new Integer(
					itwTaskBug.getSeverityid()).intValue()) {
				severityiddisplay = itwSeverity.getShortname();
			}
		}
		bean.setSeverityiddisplay(severityiddisplay);

		bean.setAttachment(itwTaskBug.getAttachment());
		bean.setIcon(itwTaskBug.getIcon());
		bean.setMimetype(itwTaskBug.getMimetype());
		bean.setName(itwTaskBug.getName());
		bean.setSize1(itwTaskBug.getSize1());
		bean.setResolution(itwTaskBug.getResolution());

		bean.setSummary(itwTaskBug.getSummary());

		bean.setShortsummary(itwTaskBug.getShortsummary());
		bean.setEfforts(itwTaskBug.getEfforts());
		bean.setDeadline(itwTaskBug.getDeadline());
		System.out.println("====Deadline filed is befor if ===="
				+ itwTaskBug.getDeadline());
		// Added by Puneet For deadlile field mendatory
		if (itwTaskBug.getDeadline() != null) {

			System.out.println("====Deadline filed is inside if ===="
					+ itwTaskBug.getDeadline());
			java.util.Date startDate = itwTaskBug.getDeadline();

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String sdate = df.format(startDate);

			bean.setDeadlinedisplay(sdate);
		}

		bean.setAdditionalRef1(itwTaskBug.getAdditionalRef1());
		bean.setAdditionalRef2(itwTaskBug.getAdditionalRef2());
		bean.setAdditionalRef3(itwTaskBug.getAdditionalRef3());
		bean.setCreatedby(itwTaskBug.getCreatedby());

		System.out.println("===Created By===" + bean.getCreatedby());
		bean.setCreatedtime(itwTaskBug.getCreatedtime());
		bean.setLastupdatedby(itwTaskBug.getLastupdatedby());
		bean.setLastupdatedtime(itwTaskBug.getLastupdatedtime());
		bean.setBulkupload(itwTaskBug.getBulkupload());
		bean.setBulkuploadfilename(itwTaskBug.getBulkuploadfilename());
		bean.setApprovedBy(itwTaskBug.getApprovedBy());
		bean.setApprovedStatus("true");
		return bean;
	}

	private ItwUserBean prepareItwUserBeanForAdd(Integer langId) {
		ItwUserBean bean = new ItwUserBean();

		Map<String, String> itwTypeidListofValues = new LinkedHashMap<String, String>();
		Map<String, String> itwPrimaryroleidListofValues = new LinkedHashMap<String, String>();
		Map<String, String> itwCompanyidListofValues = new LinkedHashMap<String, String>();

		List<ItwRoles> itwRolesList = itwRolesService.listItwRoles(langId);
		List<ItwUserTypes> itwUserTypesList = itwUserTypesService
				.listItwUserTypes();
		List<ItwCompany> itwCompanysList = itwCompanyService
				.listItwCompanys(langId);

		for (ItwRoles itwRoles : itwRolesList) {

			itwPrimaryroleidListofValues.put(itwRoles.getId().toString(),
					itwRoles.getRolename());

		}
		bean.setItwPrimaryroleidListofValues(itwPrimaryroleidListofValues);

		for (ItwUserTypes itwUserTypes : itwUserTypesList) {

			itwTypeidListofValues.put(itwUserTypes.getId().toString(),
					itwUserTypes.getShortName());

		}
		bean.setItwTypeidListofValues(itwTypeidListofValues);

		for (ItwCompany itwCompanys : itwCompanysList) {

			itwCompanyidListofValues.put(itwCompanys.getId().toString(),
					itwCompanys.getName());

		}
		bean.setItwCompanyidListofValues(itwCompanyidListofValues);

		return bean;

	}

	private ItwRoleBean prepareItwRoleBeanForAdd() {
		ItwRoleBean bean = new ItwRoleBean();

		return bean;

	}

	private ItwTaskBugBean prepareItwTaskBugBeanForAdd(
			HttpServletRequest request, ItwTaskBugBean itwTaskBugBean) {
		ItwTaskBugBean bean = new ItwTaskBugBean();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();

		List<ItwModule> itwModuleList = itwModuleService.listItwModule(langId
				.intValue());

		List<ItwProject> itwProjectList = itwProjectService
				.listItwProjects(langId.intValue());

		ItwStatusTypes itwStatusTypesCurrent = null;

		List<ItwStatusTypes> itwStatusTypesList = null;

		ItwStagesTypes itwStagesTypesCurrent = null;

		List<ItwStagesTypes> itwStagesTypesList = null;

		// ==if is called during edit=== and else will be called while adding
		if (itwTaskBugBean.getStatusid() != null) {
			itwStatusTypesList = itwStatusTypesService
					.listItwStatusPrecedTypes(langId.intValue(),
							itwTaskBugBean.getStatusid());
			itwStatusTypesCurrent = itwStatusTypesService
					.getItwStatusTypes(itwTaskBugBean.getStatusid());
		} else {
			itwStatusTypesList = itwStatusTypesService.listItwStatusDeftTypes(
					langId.intValue(), "Y");
		}

		if (itwTaskBugBean.getStagesId() != null) {
			itwStagesTypesList = itwStagesTypesService
					.listItwStagesPrecedTypes(langId.intValue(),
							itwTaskBugBean.getStagesId());

			itwStagesTypesCurrent = itwStagesTypesService
					.getItwStagesTypes(itwTaskBugBean.getStagesId());

		} else {
			itwStagesTypesList = itwStagesTypesService
					.listItwStagesDefaultTypes(langId.intValue(), "Y");

		}

		// ==========

		List<ItwSeverity> itwSeverityCurrentList = itwSeverityService
				.listItwSeverityPreced(langId.intValue(), "Y");

		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPriority> itwPriorityCurrentList = itwPriorityService
				.listItwPriorityPreced(langId.intValue(), "Y");

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());

		List<ItwPlatForms> itwPlatFormsCurrentList = itwPlatFormsService
				.listItwPlatFormsPreced(langId.intValue(), "Y");

		List<ItwReleases> itwReleasesList = itwReleasesService
				.listItwReleasesonlyYes(langId.intValue());

		List<ItwReleases> itwReleasesCurrentList = itwReleasesService
				.listItwReleasesDeftTypes(langId.intValue(), "Y");

		// Added for assign User by puneet ===Start

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
		for (ItwUser itwUser : itwUserList) {
			IdList idList = new IdList();
			idList.setId(itwUser.getId());
			idList.setValue(itwUser.getUserid());
			itwAssigneeIdList.add(idList);

		}

		bean.setItwAssigneeidList(itwAssigneeIdList);

		// ============end========
		List<IdList> itwActivityTypeIdList = new ArrayList<IdList>();
		for (ItwActivityType itwActivityType : itwActivityTypeList) {

			IdList idList = new IdList();
			idList.setId(itwActivityType.getId());
			idList.setValue(itwActivityType.getShortname());
			itwActivityTypeIdList.add(idList);

		}
		bean.setItwType1List(itwActivityTypeIdList);
		bean.setDependsOn(itwTaskBugBean.getDependsOn());
		bean.setReasonforOpen(itwTaskBugBean.getReasonforOpen());
		bean.setUrl(itwTaskBugBean.getUrl());
		bean.setVersion(itwTaskBugBean.getVersion());

		// bean.setApprovedBy(itwTaskBugBean.getApprovedBy());
		// bean.setApprovedStatus("true");
		if (itwTaskBugBean.getStatusid() != null) {
			itwStatusTypesList = itwStatusTypesService
					.listItwStatusPrecedTypes(langId.intValue(),
							itwTaskBugBean.getStatusid());
			itwStatusTypesCurrent = itwStatusTypesService
					.getItwStatusTypes(itwTaskBugBean.getStatusid());
		}

		List<IdList> itwModuleIdList = new ArrayList<IdList>();
		for (ItwModule itwModule : itwModuleList) {
			IdList idList = new IdList();
			idList.setId(itwModule.getId());
			idList.setValue(itwModule.getShortname());
			itwModuleIdList.add(idList);

		}
		bean.setItwModuleidList(itwModuleIdList);

		List<IdList> itwProjectIdList = new ArrayList<IdList>();
		for (ItwProject itwProject : itwProjectList) {
			IdList idList = new IdList();
			idList.setId(itwProject.getId());
			idList.setValue(itwProject.getShortname());
			itwProjectIdList.add(idList);
		}
		bean.setItwProjectidList(itwProjectIdList);

		List<IdList> itwStatusTypeList = new ArrayList<IdList>();
		for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {

			IdList idList = new IdList();
			idList.setId(itwStatusTypes.getId());
			idList.setValue(itwStatusTypes.getShortname());
			itwStatusTypeList.add(idList);

		}

		if (itwStatusTypesCurrent != null) {

			IdList idList = new IdList();
			idList.setId(itwStatusTypesCurrent.getId());
			idList.setValue(itwStatusTypesCurrent.getShortname());
			itwStatusTypeList.add(idList);

		}

		bean.setItwStatusidList(itwStatusTypeList);

		List<IdList> itwStagesTypeList = new ArrayList<IdList>();
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {

			IdList idList = new IdList();
			idList.setId(itwStagesTypes.getId());
			idList.setValue(itwStagesTypes.getShortname());

			itwStagesTypeList.add(idList);

		}

		if (itwStagesTypesCurrent != null) {

			IdList idList = new IdList();
			idList.setId(itwStagesTypesCurrent.getId());
			idList.setValue(itwStagesTypesCurrent.getShortname());
			itwStagesTypeList.add(idList);

		}

		bean.setItwStagesidList(itwStagesTypeList);

		List<IdList> itwSeverityIdList = new ArrayList<IdList>();
		for (ItwSeverity itwSeverity : itwSeverityList) {
			IdList idList = new IdList();
			idList.setId(itwSeverity.getId());
			idList.setValue(itwSeverity.getShortname());
			itwSeverityIdList.add(idList);

		}

		for (ItwSeverity itwSeverity : itwSeverityCurrentList) {

			bean.setSeverityidtemp(itwSeverity.getId());
		}

		bean.setItwSeverityIdList(itwSeverityIdList);

		List<IdList> itwPriorityIdList = new ArrayList<IdList>();
		for (ItwPriority itwPriority : itwPriorityList) {
			IdList idList = new IdList();
			idList.setId(itwPriority.getId());
			idList.setValue(itwPriority.getShortname());
			itwPriorityIdList.add(idList);
		}
		for (ItwPriority itwPriority : itwPriorityCurrentList) {

			bean.setPriorityidtemp(itwPriority.getId());
		}
		bean.setItwPriorityidList(itwPriorityIdList);

		List<IdList> itwPlatFormIdList = new ArrayList<IdList>();
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			IdList idList = new IdList();
			idList.setId(itwPlatForms.getId());
			idList.setValue(itwPlatForms.getShortName());
			itwPlatFormIdList.add(idList);
		}
		for (ItwPlatForms itwPlatForms : itwPlatFormsCurrentList) {

			bean.setPlatformidtemp(itwPlatForms.getId());
		}
		bean.setItwPlatformidList(itwPlatFormIdList);

		List<IdList> itwReleasList = new ArrayList<IdList>();
		for (ItwReleases itwReleases : itwReleasesList) {

			IdList idList = new IdList();
			idList.setId(itwReleases.getId());
			idList.setValue(itwReleases.getShortname());
			itwReleasList.add(idList);

		}

		for (ItwReleases itwReleases : itwReleasesCurrentList) {

			bean.setReleaseidtemp(itwReleases.getId());
		}

		bean.setItwReleasesidList(itwReleasList);

		return bean;

	}

	private ItwTaskBugBean prepareItwTaskBugBeanForCreate(
			HttpServletRequest request, ItwTaskBugBean itwTaskBugBean) {
		ItwTaskBugBean bean = new ItwTaskBugBean();

		Integer langId = (Integer) request.getSession().getAttribute("langId");

		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();

		List<ItwModule> itwModuleList = itwModuleService.listItwModule(langId
				.intValue());

		List<ItwProject> itwProjectList = itwProjectService
				.listItwProjects(langId.intValue());

		ItwStatusTypes itwStatusTypesCurrent = null;

		List<ItwStatusTypes> itwStatusTypesList = null;

		ItwStagesTypes itwStagesTypesCurrent = null;

		List<ItwStagesTypes> itwStagesTypesList = null;

		itwStatusTypesList = itwStatusTypesService.listItwStatusDeftTypes(
				langId.intValue(), "Y");

		itwStagesTypesList = itwStagesTypesService.listItwStagesDefaultTypes(
				langId.intValue(), "Y");

		List<ItwSeverity> itwSeverityCurrentList = itwSeverityService
				.listItwSeverityPreced(langId.intValue(), "Y");

		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPriority> itwPriorityCurrentList = itwPriorityService
				.listItwPriorityPreced(langId.intValue(), "Y");

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());

		List<ItwPlatForms> itwPlatFormsCurrentList = itwPlatFormsService
				.listItwPlatFormsPreced(langId.intValue(), "Y");

		List<ItwReleases> itwReleasesList = itwReleasesService
				.listItwReleases(langId.intValue());

		List<ItwReleases> itwReleasesCurrentList = itwReleasesService
				.listItwReleasesDeftTypes(langId.intValue(), "Y");

		// Added for assign User by puneet===Start

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
		for (ItwUser itwUser : itwUserList) {
			IdList idList = new IdList();
			idList.setId(itwUser.getId());
			idList.setValue(itwUser.getUserid());
			itwAssigneeIdList.add(idList);

		}
		bean.setItwAssigneeidList(itwAssigneeIdList);

		// ============end========
		List<IdList> itwActivityTypeIdList = new ArrayList<IdList>();
		for (ItwActivityType itwActivityType : itwActivityTypeList) {

			IdList idList = new IdList();
			idList.setId(itwActivityType.getId());
			idList.setValue(itwActivityType.getShortname());
			itwActivityTypeIdList.add(idList);

		}
		bean.setItwType1List(itwActivityTypeIdList);

		List<IdList> itwModuleIdList = new ArrayList<IdList>();
		for (ItwModule itwModule : itwModuleList) {
			IdList idList = new IdList();
			idList.setId(itwModule.getId());
			idList.setValue(itwModule.getShortname());
			itwModuleIdList.add(idList);

		}
		bean.setItwModuleidList(itwModuleIdList);

		List<IdList> itwProjectIdList = new ArrayList<IdList>();
		for (ItwProject itwProject : itwProjectList) {
			IdList idList = new IdList();
			idList.setId(itwProject.getId());
			idList.setValue(itwProject.getShortname());
			itwProjectIdList.add(idList);
		}
		bean.setItwProjectidList(itwProjectIdList);

		List<IdList> itwStatusTypeList = new ArrayList<IdList>();
		for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {

			IdList idList = new IdList();
			idList.setId(itwStatusTypes.getId());
			idList.setValue(itwStatusTypes.getShortname());
			itwStatusTypeList.add(idList);

		}

		bean.setItwStatusidList(itwStatusTypeList);

		List<IdList> itwStagesTypeList = new ArrayList<IdList>();
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {

			IdList idList = new IdList();
			idList.setId(itwStagesTypes.getId());
			idList.setValue(itwStagesTypes.getShortname());

			itwStagesTypeList.add(idList);

		}

		if (itwStagesTypesCurrent != null) {

			IdList idList = new IdList();
			idList.setId(itwStagesTypesCurrent.getId());
			idList.setValue(itwStagesTypesCurrent.getShortname());
			itwStagesTypeList.add(idList);

		}

		bean.setItwStagesidList(itwStagesTypeList);

		List<IdList> itwSeverityIdList = new ArrayList<IdList>();
		for (ItwSeverity itwSeverity : itwSeverityList) {
			IdList idList = new IdList();
			idList.setId(itwSeverity.getId());
			idList.setValue(itwSeverity.getShortname());
			itwSeverityIdList.add(idList);

		}

		for (ItwSeverity itwSeverity : itwSeverityCurrentList) {

			bean.setSeverityidtemp(itwSeverity.getId());
		}

		bean.setItwSeverityIdList(itwSeverityIdList);

		List<IdList> itwPriorityIdList = new ArrayList<IdList>();
		for (ItwPriority itwPriority : itwPriorityList) {
			IdList idList = new IdList();
			idList.setId(itwPriority.getId());
			idList.setValue(itwPriority.getShortname());
			itwPriorityIdList.add(idList);
		}
		for (ItwPriority itwPriority : itwPriorityCurrentList) {

			bean.setPriorityidtemp(itwPriority.getId());
		}
		bean.setItwPriorityidList(itwPriorityIdList);

		List<IdList> itwPlatFormIdList = new ArrayList<IdList>();
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			IdList idList = new IdList();
			idList.setId(itwPlatForms.getId());
			idList.setValue(itwPlatForms.getShortName());
			itwPlatFormIdList.add(idList);
		}
		for (ItwPlatForms itwPlatForms : itwPlatFormsCurrentList) {

			bean.setPlatformidtemp(itwPlatForms.getId());
		}
		bean.setItwPlatformidList(itwPlatFormIdList);

		List<IdList> itwReleasList = new ArrayList<IdList>();
		for (ItwReleases itwReleases : itwReleasesList) {

			IdList idList = new IdList();
			idList.setId(itwReleases.getId());
			idList.setValue(itwReleases.getShortname());
			itwReleasList.add(idList);

		}

		for (ItwReleases itwReleases : itwReleasesCurrentList) {

			bean.setReleaseidtemp(itwReleases.getId());
		}

		bean.setItwReleasesidList(itwReleasList);

		return bean;

	}

	private List<ItwDocumentsBean> prepareListofItwDocuments(
			List<Document> documents) {

		List<ItwDocumentsBean> beans = null;
		if (documents != null && !documents.isEmpty()) {

			beans = new ArrayList<ItwDocumentsBean>();
			ItwDocumentsBean bean = null;

			for (Document document : documents) {

				bean = new ItwDocumentsBean();
				bean.setId(document.getId());

				bean.setName(document.getName());
				bean.setDescription(document.getDescription());
				bean.setContent(document.getContent());
				bean.setMimeType(document.getMimeType());
				bean.setTaskId(document.getTaskId());
				beans.add(bean);

			}
		}
		return beans;

	}

	private Document prepareModelDoc(ItwDocumentsBean itwDocumentsBean,
			HttpServletRequest request, MultipartFile file) {

		Document document = new Document();
		document.setName(file.getOriginalFilename());
		document.setId(itwDocumentsBean.getId());
		document.setMimeType(file.getContentType());
		document.setDescription(itwDocumentsBean.getDescription());
		document.setTaskId(itwDocumentsBean.getTaskId());

		if (file != null) {
			if (file.getSize() > 0) {

				try {

					Blob blob = Hibernate.createBlob(file.getInputStream());
					document.setContent(blob);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {

			InputStream inputStream = null;
			try {

				inputStream = new FileInputStream(request.getRealPath("")
						+ "/images/blnkuser.png");
				try {

					Blob blob = Hibernate.createBlob(inputStream);
					document.setContent(blob);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}

		}
		document.setId(null);
		return document;
	}

	private List<ItwImageBean> prepareListofItwImages(List<ItwImage> images) {

		List<ItwImageBean> beans = null;
		if (images != null && !images.isEmpty()) {

			beans = new ArrayList<ItwImageBean>();
			ItwImageBean bean = null;

			for (ItwImage image : images) {

				bean = new ItwImageBean();
				bean.setId(image.getId());
				bean.setTaskId(image.getTaskId());
				bean.setImage(image.getImage());
				bean.setMimeType(image.getMimeType());
				bean.setDescription(image.getDescription());
				beans.add(bean);

			}
		}
		return beans;

	}

	// ==========================Audit Monitoring==========================

	private ItwTaskBugAudit prepareModelItwTaskBugAudit(ItwTaskBug itwTaskBug,
			int addOrUpdateType) {

		System.out.println(" =============Inside Task Bug Audit ======== "
				+ itwTaskBug.getId());

		ItwTaskBugAudit itwTaskBugAudit = new ItwTaskBugAudit();

		itwTaskBugAudit.setShortname(itwTaskBug.getShortname());
		itwTaskBugAudit.setType1(itwTaskBug.getType1());
		itwTaskBugAudit.setLock1("N");
		itwTaskBugAudit.setAssigneeid(itwTaskBug.getAssigneeid());
		itwTaskBugAudit.setModuleid(itwTaskBug.getModuleid());
		itwTaskBugAudit.setDependsOn(itwTaskBug.getDependsOn());
		itwTaskBugAudit.setReasonforOpen(itwTaskBug.getReasonforOpen());
		itwTaskBugAudit.setVersion(itwTaskBug.getVersion());
		itwTaskBugAudit.setUrl(itwTaskBug.getUrl());
		itwTaskBugAudit.setStatusid(itwTaskBug.getStatusid());
		itwTaskBugAudit.setDeadline(itwTaskBug.getDeadline());
		itwTaskBugAudit.setProjectid(itwTaskBug.getProjectid());
		itwTaskBugAudit.setReleaseid(itwTaskBug.getReleaseid());
		itwTaskBugAudit.setMachinetypeid(itwTaskBug.getMachinetypeid());
		itwTaskBugAudit.setPlatformid(itwTaskBug.getPlatformid());
		itwTaskBugAudit.setBrowserid(itwTaskBug.getBrowserid());
		itwTaskBugAudit.setSeverityid(itwTaskBug.getSeverityid());
		itwTaskBugAudit.setCreatedby(itwTaskBug.getCreatedby());
		itwTaskBugAudit.setCreatedtime(itwTaskBug.getCreatedtime());
		itwTaskBugAudit.setLastupdatedby(itwTaskBug.getLastupdatedby());
		itwTaskBugAudit.setLastupdatedtime(itwTaskBug.getLastupdatedtime());
		itwTaskBugAudit.setPriorityid(itwTaskBug.getPriorityid());
		if (addOrUpdateType == 2) {
			itwTaskBugAudit.setResolution(itwTaskBug.getResolution());
		} else {
			itwTaskBugAudit.setResolution(itwTaskBug.getSummary());
		}
		itwTaskBugAudit.setSummary(itwTaskBug.getSummary());
		itwTaskBugAudit.setEfforts(itwTaskBug.getEfforts());
		itwTaskBugAudit.setTaskId(itwTaskBug.getId());

		itwTaskBugAudit.setBulkupload("N");

		return itwTaskBugAudit;
	}

	private List<ItwTaskBugAuditBean> prepareListofItwTaskBugAudits(
			List<ItwTaskBugAudit> itwTaskBugAudits, HttpServletRequest request) {

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwTaskBugAuditBean> beans = null;
		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId);

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());
		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.listItwStatusTypes(langId.intValue());

		if (itwTaskBugAudits != null && !itwTaskBugAudits.isEmpty()) {
			beans = new ArrayList<ItwTaskBugAuditBean>();
			ItwTaskBugAuditBean bean = null;
			for (ItwTaskBugAudit itwTaskBugAudit : itwTaskBugAudits) {
				bean = new ItwTaskBugAuditBean();
				bean.setId(itwTaskBugAudit.getId());

				UserSession userSession = (UserSession) request.getSession()
						.getAttribute("userSession");
				String assigneeiddisplay1 = userSession.getItwUser()
						.getUserid();

				bean.setCurrentloggedinuserid(assigneeiddisplay1);

				bean.setShortname(itwTaskBugAudit.getShortname());

				bean.setType1(itwTaskBugAudit.getType1());
				String type1display = null;
				for (ItwActivityType itwActivityType : itwActivityTypeList) {
					if (itwActivityType.getId().intValue() == new Integer(
							itwTaskBugAudit.getType1()).intValue()) {
						type1display = itwActivityType.getShortname();
					}
				}
				bean.setType1display(type1display);
				bean.setLock1(itwTaskBugAudit.getLock1());
				bean.setAssigneeid(itwTaskBugAudit.getAssigneeid());
				bean.setDependsOn(itwTaskBugAudit.getDependsOn());
				bean.setReasonforOpen(itwTaskBugAudit.getReasonforOpen());

				bean.setVersion(itwTaskBugAudit.getVersion());
				bean.setUrl(itwTaskBugAudit.getUrl());
				String assigneeiddisplay = null;
				if (itwTaskBugAudit.getAssigneeid() != null) {
					for (ItwUser itwUser : itwUserList) {
						if (itwUser.getId().intValue() == new Integer(
								itwTaskBugAudit.getAssigneeid()).intValue()) {
							assigneeiddisplay = itwUser.getUserid();
						}
					}
				} else {
					assigneeiddisplay = new String("Unassigned");
				}
				bean.setAssigneeiddisplay(assigneeiddisplay);
				String createdbydisplay = null;
				for (ItwUser itwUser : itwUserList) {
					if (itwUser.getId().intValue() == new Integer(
							itwTaskBugAudit.getCreatedby()).intValue()) {
						createdbydisplay = itwUser.getUserid();
					}
				}
				bean.setCreatedbydisplay(createdbydisplay);

				String lastupdatedbydisplay = null;
				for (ItwUser itwUser : itwUserList) {
					if (itwUser.getId().intValue() == new Integer(
							itwTaskBugAudit.getLastupdatedby()).intValue()) {
						lastupdatedbydisplay = itwUser.getUserid();
					}
				}
				bean.setLastupdatedbydisplay(lastupdatedbydisplay);
				bean.setModuleid(itwTaskBugAudit.getModuleid());
				bean.setStatusid(itwTaskBugAudit.getStatusid());

				String statusiddisplay = null;
				for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {
					if (itwStatusTypes.getId().intValue() == new Integer(
							itwTaskBugAudit.getStatusid()).intValue()) {
						statusiddisplay = itwStatusTypes.getShortname();
					}
				}
				bean.setStatusiddisplay(statusiddisplay);

				bean.setPriorityid(itwTaskBugAudit.getPriorityid());

				String priorityiddisplay = null;
				for (ItwPriority itwPriority : itwPriorityList) {
					if (itwPriority.getId().intValue() == new Integer(
							itwTaskBugAudit.getPriorityid()).intValue()) {
						priorityiddisplay = itwPriority.getShortname();
					}
				}
				bean.setPriorityiddisplay(priorityiddisplay);
				bean.setProjectid(itwTaskBugAudit.getProjectid());
				String projectiddisplay = null;
				for (ItwProject itwProject : itwProjectList) {
					if (itwProject.getId().intValue() == new Integer(
							itwTaskBugAudit.getProjectid()).intValue()) {
						projectiddisplay = itwProject.getShortname();
					}
				}
				bean.setProjectiddisplay(projectiddisplay);

				bean.setReleaseid(itwTaskBugAudit.getReleaseid());
				bean.setMachinetypeid(itwTaskBugAudit.getMachinetypeid());
				bean.setPlatformid(itwTaskBugAudit.getPlatformid());
				String platformiddisplay = null;
				for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
					if (itwPlatForms.getId().intValue() == new Integer(
							itwTaskBugAudit.getPlatformid()).intValue()) {
						platformiddisplay = itwPlatForms.getShortName();
					}
				}
				bean.setPlatformiddisplay(platformiddisplay);

				bean.setBrowserid(itwTaskBugAudit.getBrowserid());
				bean.setTargetmilestoneid(itwTaskBugAudit
						.getTargetmilestoneid());
				bean.setSeverityid(itwTaskBugAudit.getSeverityid());

				String severityiddisplay = null;
				for (ItwSeverity itwSeverity : itwSeverityList) {
					if (itwSeverity.getId().intValue() == new Integer(
							itwTaskBugAudit.getSeverityid()).intValue()) {
						severityiddisplay = itwSeverity.getShortname();
					}
				}
				bean.setSeverityiddisplay(severityiddisplay);

				bean.setAttachment(itwTaskBugAudit.getAttachment());
				bean.setIcon(itwTaskBugAudit.getIcon());
				bean.setMimetype(itwTaskBugAudit.getMimetype());
				bean.setName(itwTaskBugAudit.getName());
				bean.setSize1(itwTaskBugAudit.getSize1());
				bean.setResolution(itwTaskBugAudit.getResolution());
				bean.setSummary(itwTaskBugAudit.getSummary());
				bean.setShortsummary(itwTaskBugAudit.getShortsummary());
				bean.setEfforts(itwTaskBugAudit.getEfforts());
				bean.setDeadline(itwTaskBugAudit.getDeadline());
				bean.setAdditionalRef1(itwTaskBugAudit.getAdditionalRef1());
				bean.setAdditionalRef2(itwTaskBugAudit.getAdditionalRef2());
				bean.setAdditionalRef3(itwTaskBugAudit.getAdditionalRef3());
				bean.setCreatedby(itwTaskBugAudit.getCreatedby());
				bean.setCreatedtime(itwTaskBugAudit.getCreatedtime());
				bean.setLastupdatedby(itwTaskBugAudit.getLastupdatedby());
				bean.setLastupdatedtime(itwTaskBugAudit.getLastupdatedtime());
				System.out.println("the last updated date is : "
						+ itwTaskBugAudit.getLastupdatedtime().toGMTString());
				bean.setLastUpdateDisplayTimeInGMT(itwTaskBugAudit
						.getLastupdatedtime().toGMTString()
						+ "/"
						+ itwTaskBugAudit.getLastupdatedtime().toLocaleString());
				bean.setBulkupload(itwTaskBugAudit.getBulkupload());
				bean.setBulkuploadfilename(itwTaskBugAudit
						.getBulkuploadfilename());
				bean.setTaskId(itwTaskBugAudit.getTaskId());

				beans.add(bean);

			}
		}
		return beans;
	}

	private ItwTaskBugAuditBean prepareItwTaskBugAuditBeanForAdd(Integer langId) {

		ItwTaskBugAuditBean bean = new ItwTaskBugAuditBean();

		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();

		List<ItwModule> itwModuleList = itwModuleService.listItwModule(langId
				.intValue());

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());
		List<ItwProject> itwProjectList = itwProjectService
				.listItwProjects(langId.intValue());
		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.listItwStatusTypes(langId.intValue());

		List<IdList> itwActivityTypeIdList = new ArrayList<IdList>();
		for (ItwActivityType itwActivityType : itwActivityTypeList) {

			IdList idList = new IdList();
			idList.setId(itwActivityType.getId());
			idList.setValue(itwActivityType.getShortname());
			itwActivityTypeIdList.add(idList);

		}
		bean.setItwType1List(itwActivityTypeIdList);

		List<IdList> itwSeverityIdList = new ArrayList<IdList>();
		for (ItwSeverity itwSeverity : itwSeverityList) {
			IdList idList = new IdList();
			idList.setId(itwSeverity.getId());
			idList.setValue(itwSeverity.getShortname());
			itwSeverityIdList.add(idList);

		}
		bean.setItwSeverityIdList(itwSeverityIdList);

		List<IdList> itwModuleIdList = new ArrayList<IdList>();
		for (ItwModule itwModule : itwModuleList) {
			IdList idList = new IdList();
			idList.setId(itwModule.getId());
			idList.setValue(itwModule.getShortname());
			itwModuleIdList.add(idList);

		}
		bean.setItwModuleidList(itwModuleIdList);

		List<IdList> itwPriorityIdList = new ArrayList<IdList>();
		for (ItwPriority itwPriority : itwPriorityList) {
			IdList idList = new IdList();
			idList.setId(itwPriority.getId());
			idList.setValue(itwPriority.getShortname());
			itwPriorityIdList.add(idList);
		}
		bean.setItwPriorityidList(itwPriorityIdList);

		List<IdList> itwPlatFormIdList = new ArrayList<IdList>();
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			IdList idList = new IdList();
			idList.setId(itwPlatForms.getId());
			idList.setValue(itwPlatForms.getShortName());
			itwPlatFormIdList.add(idList);
		}
		bean.setItwPlatformidList(itwPlatFormIdList);

		List<IdList> itwProjectIdList = new ArrayList<IdList>();
		for (ItwProject itwProject : itwProjectList) {
			IdList idList = new IdList();
			idList.setId(itwProject.getId());
			idList.setValue(itwProject.getShortname());
			itwProjectIdList.add(idList);
		}
		bean.setItwProjectidList(itwProjectIdList);

		List<IdList> itwStatusTypeList = new ArrayList<IdList>();
		for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {
			IdList idList = new IdList();
			idList.setId(itwStatusTypes.getId());
			idList.setValue(itwStatusTypes.getShortname());
			itwStatusTypeList.add(idList);

		}
		bean.setItwStatusidList(itwStatusTypeList);

		return bean;

	}

	private ItwTaskBugAuditBean prepareItwTaskBugAuditBean(
			ItwTaskBugAudit itwTaskBugAudit, ItwTaskBugAuditBean bean,
			HttpServletRequest request) {
		Integer langId = (Integer) request.getSession().getAttribute("langId");
		bean.setId(itwTaskBugAudit.getId());
		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();
		;
		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId);

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());
		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId);

		bean.setDependsOn(itwTaskBugAudit.getDependsOn());
		bean.setReasonforOpen(itwTaskBugAudit.getReasonforOpen());

		bean.setVersion(itwTaskBugAudit.getVersion());
		bean.setUrl(itwTaskBugAudit.getUrl());

		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.listItwStatusTypes(langId.intValue());
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		String assigneeiddisplay1 = userSession.getItwUser().getUserid();

		bean.setCurrentloggedinuserid(assigneeiddisplay1);

		bean.setShortname(itwTaskBugAudit.getShortname());

		bean.setType1(itwTaskBugAudit.getType1());
		String type1display = null;
		for (ItwActivityType itwActivityType : itwActivityTypeList) {
			if (itwActivityType.getId().intValue() == new Integer(
					itwTaskBugAudit.getType1()).intValue()) {
				type1display = itwActivityType.getShortname();
			}
		}

		bean.setType1display(type1display);
		bean.setLock1(itwTaskBugAudit.getLock1());
		bean.setAssigneeid(itwTaskBugAudit.getAssigneeid());

		String assigneeiddisplay = null;
		if (itwTaskBugAudit.getAssigneeid() != null) {
			for (ItwUser itwUser : itwUserList) {
				if (itwUser.getId().intValue() == new Integer(
						itwTaskBugAudit.getAssigneeid()).intValue()) {
					assigneeiddisplay = itwUser.getUserid();
				}
			}
		}
		bean.setAssigneeiddisplay(assigneeiddisplay);

		bean.setModuleid(itwTaskBugAudit.getModuleid());
		bean.setStatusid(itwTaskBugAudit.getStatusid());

		String statusiddisplay = null;
		for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {
			if (itwStatusTypes.getId().intValue() == new Integer(
					itwTaskBugAudit.getStatusid()).intValue()) {
				statusiddisplay = itwStatusTypes.getShortname();
			}
		}
		bean.setStatusiddisplay(statusiddisplay);

		bean.setPriorityid(itwTaskBugAudit.getPriorityid());

		String priorityiddisplay = null;
		for (ItwPriority itwPriority : itwPriorityList) {
			if (itwPriority.getId().intValue() == new Integer(
					itwTaskBugAudit.getPriorityid()).intValue()) {
				priorityiddisplay = itwPriority.getShortname();
			}
		}

		bean.setPriorityiddisplay(priorityiddisplay);
		bean.setProjectid(itwTaskBugAudit.getProjectid());
		String projectiddisplay = null;
		for (ItwProject itwProject : itwProjectList) {
			if (itwProject.getId().intValue() == new Integer(
					itwTaskBugAudit.getProjectid()).intValue()) {
				projectiddisplay = itwProject.getShortname();
			}
		}
		bean.setProjectiddisplay(projectiddisplay);
		bean.setReleaseid(itwTaskBugAudit.getReleaseid());
		bean.setMachinetypeid(itwTaskBugAudit.getMachinetypeid());
		bean.setPlatformid(itwTaskBugAudit.getPlatformid());
		String platformiddisplay = null;
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			if (itwPlatForms.getId().intValue() == new Integer(
					itwTaskBugAudit.getPlatformid()).intValue()) {
				platformiddisplay = itwPlatForms.getShortName();
			}
		}
		bean.setPlatformiddisplay(platformiddisplay);
		bean.setBrowserid(itwTaskBugAudit.getBrowserid());
		bean.setTargetmilestoneid(itwTaskBugAudit.getTargetmilestoneid());
		bean.setSeverityid(itwTaskBugAudit.getSeverityid());

		String severityiddisplay = null;
		for (ItwSeverity itwSeverity : itwSeverityList) {
			if (itwSeverity.getId().intValue() == new Integer(
					itwTaskBugAudit.getSeverityid()).intValue()) {
				severityiddisplay = itwSeverity.getShortname();
			}
		}
		bean.setSeverityiddisplay(severityiddisplay);

		bean.setAttachment(itwTaskBugAudit.getAttachment());
		bean.setIcon(itwTaskBugAudit.getIcon());
		bean.setMimetype(itwTaskBugAudit.getMimetype());
		bean.setName(itwTaskBugAudit.getName());
		bean.setSize1(itwTaskBugAudit.getSize1());
		bean.setResolution(itwTaskBugAudit.getResolution());

		bean.setSummary(itwTaskBugAudit.getSummary());

		bean.setShortsummary(itwTaskBugAudit.getShortsummary());
		bean.setEfforts(itwTaskBugAudit.getEfforts());
		bean.setDeadline(itwTaskBugAudit.getDeadline());
		java.util.Date startDate = itwTaskBugAudit.getDeadline();

		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String sdate = df.format(startDate);

		bean.setDeadlinedisplay(sdate);

		bean.setAdditionalRef1(itwTaskBugAudit.getAdditionalRef1());
		bean.setAdditionalRef2(itwTaskBugAudit.getAdditionalRef2());
		bean.setAdditionalRef3(itwTaskBugAudit.getAdditionalRef3());
		bean.setCreatedby(itwTaskBugAudit.getCreatedby());
		bean.setCreatedtime(itwTaskBugAudit.getCreatedtime());
		bean.setLastupdatedby(itwTaskBugAudit.getLastupdatedby());
		bean.setLastupdatedtime(itwTaskBugAudit.getLastupdatedtime());
		bean.setBulkupload(itwTaskBugAudit.getBulkupload());
		bean.setBulkuploadfilename(itwTaskBugAudit.getBulkuploadfilename());
		bean.setTaskId(itwTaskBugAudit.getTaskId());

		return bean;
	}

	// =====================User Log===================================

	private List<ItwUserLogsBean> prepareListofItwUserLogs(
			List<ItwUserLogs> itwUserLogs) {
		List<ItwUserLogsBean> beans = null;
		if (itwUserLogs != null && !itwUserLogs.isEmpty()) {

			beans = new ArrayList<ItwUserLogsBean>();
			ItwUserLogsBean bean = null;
			for (ItwUserLogs itwUserLog : itwUserLogs) {
				bean = new ItwUserLogsBean();

				bean.setId(itwUserLog.getId());
				bean.setUserid(itwUserLog.getUserid());
				bean.setIpaddress(itwUserLog.getIpaddress());
				bean.setSession1(itwUserLog.getSession1());
				bean.setBrowserdetails(itwUserLog.getBrowserdetails());
				bean.setOnlinestatus(itwUserLog.getOnlinestatus());
				bean.setLogintime(itwUserLog.getLogintime());
				bean.setLogouttime(itwUserLog.getLogouttime());
				bean.setLastactivity(itwUserLog.getLastactivity());
				bean.setCreationdate(itwUserLog.getCreationdate());
				bean.setLastupdatedtime(itwUserLog.getLastupdatedtime());
				bean.setLogoutreason(itwUserLog.getLogoutreason());

				beans.add(bean);
			}
		}
		return beans;
	}

	private ItwUserLogsBean prepareItwUserLogsBean(ItwUserLogs itwUserLog) {

		ItwUserLogsBean bean = new ItwUserLogsBean();

		bean.setId(itwUserLog.getId());
		bean.setUserid(itwUserLog.getUserid());
		bean.setIpaddress(itwUserLog.getIpaddress());
		bean.setSession1(itwUserLog.getSession1());
		bean.setBrowserdetails(itwUserLog.getBrowserdetails());
		bean.setOnlinestatus(itwUserLog.getOnlinestatus());
		bean.setLogintime(itwUserLog.getLogintime());
		bean.setLogouttime(itwUserLog.getLogouttime());
		bean.setLastactivity(itwUserLog.getLastactivity());
		bean.setCreationdate(itwUserLog.getCreationdate());
		bean.setLastupdatedtime(itwUserLog.getLastupdatedtime());
		bean.setLogoutreason(itwUserLog.getLogoutreason());

		return bean;
	}

	private ItwTaskBugBean prepareItwTaskBugBeanForIntialValues(
			HttpServletRequest request) {
		ItwTaskBugBean bean = new ItwTaskBugBean();

		Integer langId = (Integer) request.getSession().getAttribute("langId");
		List<ItwActivityType> itwActivityTypeList = itwActivityTypeService
				.listItwActivityTypes();
		List<ItwSeverity> itwSeverityList = itwSeverityService
				.listItwSeveritys();
		;
		List<ItwModule> itwModuleList = itwModuleService.listItwModule(langId
				.intValue());

		List<ItwPriority> itwPriorityList = itwPriorityService
				.listItwPriorities(langId.intValue());

		List<ItwPlatForms> itwPlatFormsList = itwPlatFormsService
				.listItwPlatForms(langId.intValue());
		List<ItwProject> itwProjectList = itwProjectService.listItwProjects(1);

		List<ItwStatusTypes> itwStatusTypesList = itwStatusTypesService
				.listItwStatusTypes(langId.intValue());

		List<ItwStagesTypes> itwStagesTypesList = itwStagesTypesService
				.listItwStagesTypes(langId.intValue());

		List<ItwReleases> itwReleasesList = itwReleasesService
				.listItwReleases(langId.intValue());

		List<ItwUser> itwUserList = itwUserService.listItwUsers(langId
				.intValue());

		List<IdList> itwAssigneeIdList = new ArrayList<IdList>();
		for (ItwUser itwUser : itwUserList) {
			IdList idList = new IdList();
			idList.setId(itwUser.getId());
			idList.setValue(itwUser.getUserid());
			itwAssigneeIdList.add(idList);

		}
		bean.setItwAssigneeidList(itwAssigneeIdList);

		List<IdList> itwActivityTypeIdList = new ArrayList<IdList>();
		for (ItwActivityType itwActivityType : itwActivityTypeList) {

			IdList idList = new IdList();
			idList.setId(itwActivityType.getId());
			idList.setValue(itwActivityType.getShortname());
			itwActivityTypeIdList.add(idList);

		}
		bean.setItwType1List(itwActivityTypeIdList);

		List<IdList> itwSeverityIdList = new ArrayList<IdList>();
		for (ItwSeverity itwSeverity : itwSeverityList) {
			IdList idList = new IdList();
			idList.setId(itwSeverity.getId());
			idList.setValue(itwSeverity.getShortname());
			itwSeverityIdList.add(idList);

		}
		bean.setItwSeverityIdList(itwSeverityIdList);

		List<IdList> itwModuleIdList = new ArrayList<IdList>();
		for (ItwModule itwModule : itwModuleList) {
			IdList idList = new IdList();
			idList.setId(itwModule.getId());
			idList.setValue(itwModule.getShortname());
			itwModuleIdList.add(idList);

		}
		bean.setItwModuleidList(itwModuleIdList);

		List<IdList> itwPriorityIdList = new ArrayList<IdList>();
		for (ItwPriority itwPriority : itwPriorityList) {
			IdList idList = new IdList();
			idList.setId(itwPriority.getId());
			idList.setValue(itwPriority.getShortname());
			itwPriorityIdList.add(idList);
		}
		bean.setItwPriorityidList(itwPriorityIdList);

		List<IdList> itwPlatFormIdList = new ArrayList<IdList>();
		for (ItwPlatForms itwPlatForms : itwPlatFormsList) {
			IdList idList = new IdList();
			idList.setId(itwPlatForms.getId());
			idList.setValue(itwPlatForms.getShortName());
			itwPlatFormIdList.add(idList);
		}
		bean.setItwPlatformidList(itwPlatFormIdList);

		List<IdList> itwProjectIdList = new ArrayList<IdList>();
		for (ItwProject itwProject : itwProjectList) {
			IdList idList = new IdList();
			idList.setId(itwProject.getId());
			idList.setValue(itwProject.getShortname());
			itwProjectIdList.add(idList);
		}
		bean.setItwProjectidList(itwProjectIdList);

		List<IdList> itwStatusTypeList = new ArrayList<IdList>();
		for (ItwStatusTypes itwStatusTypes : itwStatusTypesList) {
			IdList idList = new IdList();
			idList.setId(itwStatusTypes.getId());
			idList.setValue(itwStatusTypes.getShortname());
			itwStatusTypeList.add(idList);

		}
		bean.setItwStatusidList(itwStatusTypeList);

		List<IdList> itwStagesTypeList = new ArrayList<IdList>();
		for (ItwStagesTypes itwStagesTypes : itwStagesTypesList) {
			IdList idList = new IdList();
			idList.setId(itwStagesTypes.getId());
			idList.setValue(itwStagesTypes.getShortname());
			itwStagesTypeList.add(idList);

		}
		bean.setItwStatusidList(itwStagesTypeList);

		List<IdList> itwReleaseList = new ArrayList<IdList>();
		for (ItwReleases itwReleases : itwReleasesList) {
			IdList idList = new IdList();
			idList.setId(itwReleases.getId());
			idList.setValue(itwReleases.getShortname());
			itwReleaseList.add(idList);

		}
		bean.setItwStatusidList(itwReleaseList);

		return bean;

	}

	public List<DefectFlowBean> defectFlow(HttpServletRequest request,
			ItwTaskBugBean itwTaskBugBean) {

		System.out.println("Project Id is ========"
				+ itwTaskBugBean.getProjectid());

		Map<String, Object> model = new HashMap<String, Object>();

		List<ItwTaskBugAudit> itwTaskBugAuditfirst = itwTaskBugAuditService
				.listItwTaskBugAuditsFirstRecord(itwTaskBugBean.getId());

		List<ItwTaskBugAudit> itwTaskBugAudits = itwTaskBugAuditService
				.listItwTaskBugAuditsAscend(itwTaskBugBean.getId());

		List<DefectFlowBean> defectFlowBeansList = new ArrayList<DefectFlowBean>();

		DefectFlowBean bean = null;
		String tempPreviousState = null;
		java.util.Date tempPreviousDate = null;

		long milliseconds1 = 0;

		int tempStatusId = 0;

		for (ItwTaskBugAudit itwTaskBugAudit : itwTaskBugAuditfirst) {

			if (tempStatusId != itwTaskBugAudit.getStatusid()) {

				bean = new DefectFlowBean();

				ItwStatusTypes itwStatusType = itwStatusTypesService
						.getItwStatusTypes(itwTaskBugAudit.getStatusid());

				bean.setPreviousState(tempPreviousState);
				bean.setPreviousTime(itwTaskBugAudit.getCreatedtime());
				bean.setPreviousTimeDisplay(itwTaskBugAudit.getCreatedtime()
						.toLocaleString());
				tempPreviousDate = itwTaskBugAudit.getCreatedtime();

				long milliseconds2 = itwTaskBugAudit.getCreatedtime().getTime();

				milliseconds1 = milliseconds2;

				long diff = milliseconds2 - milliseconds1;

				long diffHours = diff / (60 * 60 * 1000);

				long diffMinutes = diff / (60 * 1000) % 60;

				milliseconds1 = milliseconds2;

				tempPreviousState = itwStatusType.getShortname();

				bean.setNextState(itwStatusType.getShortname());
				bean.setNextTime(itwTaskBugAudit.getCreatedtime());
				bean.setNextTimeDisplay(itwTaskBugAudit.getCreatedtime()
						.toLocaleString());

				String elapse = diffHours + " hours " + diffMinutes
						+ " minutes ";

				bean.setElapse(elapse);

				bean.setElapseMinutes((diffHours * 60) + diffMinutes);

				tempStatusId = itwTaskBugAudit.getStatusid();

				defectFlowBeansList.add(bean);

			}
		}

		for (ItwTaskBugAudit itwTaskBugAudit : itwTaskBugAudits) {

			if (tempStatusId != itwTaskBugAudit.getStatusid()) {

				bean = new DefectFlowBean();

				ItwStatusTypes itwStatusType = itwStatusTypesService
						.getItwStatusTypes(itwTaskBugAudit.getStatusid());

				bean.setPreviousState(tempPreviousState);
				bean.setPreviousTime(tempPreviousDate);
				// bean.setPreviousTimeDisplay(tempPreviousDate.toLocaleString());

				long milliseconds2 = itwTaskBugAudit.getLastupdatedtime()
						.getTime();

				long diff = milliseconds2 - milliseconds1;

				long diffHours = diff / (60 * 60 * 1000);

				long diffMinutes = diff / (60 * 1000) % 60;

				milliseconds1 = milliseconds2;

				java.util.Date d1 = tempPreviousDate;
				java.util.Date d2 = itwTaskBugAudit.getLastupdatedtime();

				String elapse = null;
				try {
					long diff1 = d2.getTime() - d1.getTime();

					long diffDays = diff1 / (24 * 60 * 60 * 1000);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int day = cal.get(Calendar.DAY_OF_MONTH);

					int month = cal.get(Calendar.MONTH);

					cal.setTime(d2);

					int hour1 = cal.get(Calendar.HOUR_OF_DAY);
					int day1 = cal.get(Calendar.DAY_OF_MONTH);

					int month1 = cal.get(Calendar.MONTH);

					cal.setTime(d2);

					int days = day1 - day;
					int months = month1 - month;

					long elapseHours = 0;

					if (days == 0 && months == 0) {

						elapseHours = diffHours;
						bean.setElapseMinutes((diffHours * 60) + diffMinutes);
						elapse = elapseHours + " hours " + diffMinutes
								+ " minutes ";

					}

					long temp1;
					long temp2;

					if (days == 1 && months == 0) {

						if (hour < 22) {

							temp1 = 22 - hour;
						} else {
							temp1 = 0;
						}
						if (hour1 > 10) {

							temp2 = hour1 - 10;
						} else {
							temp2 = hour1;
						}

						elapseHours = temp2 + temp1;

						bean.setElapseMinutes((elapseHours * 60) + diffMinutes);

						elapse = elapseHours + " hours " + diffMinutes
								+ " minutes ";

					}

					if (diffDays > 1) {

						Calendar cal1 = Calendar.getInstance();

						int loopVar = (int) diffDays;

						int betweenhours = 0;
						int betweendays = 0;

						java.util.Date datB = d1;
						for (int i = 1; i < loopVar; i++) {

							cal1.setTime(datB);

							cal1.add(cal1.DAY_OF_MONTH, 1);

							datB = cal1.getTime();

							if ((!datB.toString().contains("Sat") && !datB
									.toString().contains("Sun"))) {

								betweendays += 1;

							}

						}
						betweenhours = (betweendays) * 9;
						if (hour < 22) {

							temp1 = 22 - hour;
						} else {
							temp1 = 0;
						}
						if (hour1 > 10) {

							temp2 = hour1 - 10;
						} else {
							temp2 = hour1;
						}

						elapseHours = temp2 + temp1 + betweenhours;

						bean.setElapseMinutes((elapseHours * 60) + diffMinutes);

						elapse = elapseHours + " hours " + diffMinutes
								+ " minutes ";

						System.out.println("====End=======");

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				tempPreviousState = itwStatusType.getShortname();
				tempPreviousDate = itwTaskBugAudit.getLastupdatedtime();
				bean.setPreviousStateId(tempStatusId);
				bean.setNextStateId(itwStatusType.getId());
				bean.setNextState(itwStatusType.getShortname());
				bean.setNextTime(itwTaskBugAudit.getLastupdatedtime());

				bean.setElapse(elapse);

				tempStatusId = itwTaskBugAudit.getStatusid();

				defectFlowBeansList.add(bean);
			}
		}

		if (defectFlowBeansList != null) {
			@SuppressWarnings("unchecked")
			PagedListHolder pagedListHolder = new PagedListHolder(
					defectFlowBeansList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 15;
			pagedListHolder.setPageSize(pageSize);
			model.put("pagedListHolder", pagedListHolder);
			model.put("defectFlowBeansList", defectFlowBeansList);
			model.put("taskId1", itwTaskBugBean.getId());

		}

		return defectFlowBeansList;
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

				beans.add(bean);

			}
		}
		return beans;

	}
}
