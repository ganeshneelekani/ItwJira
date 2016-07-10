package com.agileidc.itw.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import com.agileidc.itw.web.ItwChangePasswordValidator;
import com.agileidc.itw.web.ItwUserValidator;
import com.agileidc.itw.web.MailUtil;
import com.agileidc.itw.web.UserSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.agileidc.itw.bean.ItwTweetBean;
import com.agileidc.itw.bean.ItwUserBean;
import com.agileidc.itw.bean.MainPageBean;
import com.agileidc.itw.bean.UserLoginBean;
import com.agileidc.itw.dao.ItwTaskDAOTemp;
import com.agileidc.itw.helper.Encrypt;
import com.agileidc.itw.helper.TimeDiff;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwLangTypes;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwTweets;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;
import com.agileidc.itw.model.ItwUserLogs;
import com.agileidc.itw.model.ItwUserTypes;
import com.agileidc.itw.service.ItwLangTypesService;
import com.agileidc.itw.service.ItwMessageService;
import com.agileidc.itw.service.ItwProjectService;
import com.agileidc.itw.service.ItwTweetsService;
import com.agileidc.itw.service.ItwUserLogsService;
import com.agileidc.itw.service.ItwUserService;
import com.agileidc.itw.web.LoginValidator;

@Controller
public class SignOnController {

	@Autowired
	private ItwUserService itwUserService;
	@Autowired
	private ItwProjectService itwProjectService;
	@Autowired
	private ItwTweetsService itwTweetsService;
	@Autowired
	private ItwTaskDAOTemp itwTaskDAOTemp;
	@Autowired
	private ItwMessageService itwMessageService;
	@Autowired
	private ItwLangTypesService itwLangTypesService;

	@Autowired
	private ItwUserLogsService itwUserLogsService;

	@RequestMapping(value = "/signOn", method = RequestMethod.GET)
	public ModelAndView showSignOn(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {
	
		return new ModelAndView("signOn");
	}

	@RequestMapping(value = "/savechangePass", method = RequestMethod.POST)
	public ModelAndView savechangePass(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result,HttpServletRequest request, HttpServletResponse response) {
		
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		String pass = Encrypt.md5(userLoginBean.getJ_password());
		String uid = userLoginBean.getJ_username();

		String newPass = userLoginBean.getNewpassword();
		String confPass = userLoginBean.getConfirmpassword();
		
		ItwChangePasswordValidator itwChangePasswordValidator = new ItwChangePasswordValidator();
		itwChangePasswordValidator.validate(userLoginBean, result);

		if (result.hasErrors()) {
			model.put("error", "true");
		result.rejectValue("confirmpassword",
				"confirmpassword.required", "Password does not match");

		return new ModelAndView("changePass");
		}

		List<ItwUser> itwUsers = itwUserService.getUsers(uid, pass);
		if (itwUsers != null && !itwUsers.isEmpty()) {

			if (newPass.equals(confPass)||newPass!=null||confPass!=null) {

				String npass = Encrypt.md5(newPass);

				itwUserService.updateItwUserPass(uid, npass, 0);
				return new ModelAndView("signOn");

			} else {

				model.put("error", "true");
				result.rejectValue("j_username",
						"j_username.required", "Password does not match");

				return new ModelAndView("changePass");

			}

		}
		model.put("error", "true");
		result.rejectValue("j_username", "userid.required",
				"Invalid user id or password");

		return new ModelAndView("changePass");
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.GET)
	public ModelAndView changePass(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {
		System.out.println("===Change Password==========");
		return new ModelAndView("changePass");
	}

	@RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
	public ModelAndView forgotPass(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {
	
		return new ModelAndView("forgotPass");
	}

	@RequestMapping(value = "/mailOneTimePass", method = RequestMethod.POST)
	public ModelAndView mailOneTimePass(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		
		String uid = userLoginBean.getJ_username();

		List<ItwUser> itwUsers = itwUserService.getAllUsers(uid);
		if (itwUsers != null && !itwUsers.isEmpty()) {

			for (ItwUser itwUser : itwUsers) {

				char[] alphNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
						.toCharArray();

				Random rnd = new Random();

				StringBuilder sb = new StringBuilder("");
				for (int i = 0; i < 6; i++)
					sb.append(alphNum[rnd.nextInt(alphNum.length)]);

				String ontimepass = sb.toString();

				String npass = Encrypt.md5(ontimepass);

				itwUserService.updateItwUserPass(uid, npass, 1);

				String mailid = itwUser.getEmailid();
				String[] recipients = new String[] { mailid };
				String[] bccRecipients = new String[] { mailid };
				String subject = "Mail from IT WorkBook Application";
				String messageBody = ("Hi "
						+ uid
						+ " "
						+ ",\n "
						+ "Your One time pass word is : "+ontimepass
						+  ".  \n Thanks \n From System Administrator \n IT WorkBook Team.");
				
				System.out.println("OnTime Passwoord Is=="+ontimepass);

			
				new MailUtil().sendMail(recipients, bccRecipients, subject,
						messageBody);
				
				

				model.put("error", "true");
				result.rejectValue("j_username", "userid.required",
						"Your one time password is sent to your mail Id. Use this password to login");
				return new ModelAndView("signOn");
			}

		}

		model.put("error", "true");
		result.rejectValue("j_username", "j_username.required",
				"Enter proper User Id");

		return new ModelAndView("forgotPass");

	}

	@RequestMapping(value = "/doSignOn", method = RequestMethod.POST)
	public ModelAndView doSignOn(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		LoginValidator userValidator = new LoginValidator();
		userValidator.validate(userLoginBean, result);

		if (result.hasErrors()) {

			return new ModelAndView("signOn");
		} else {
			String encPassword = Encrypt.md5(userLoginBean.getPassword());
			String lowerCaseUserid = userLoginBean.getUserid().toLowerCase();
			ItwUser tmp_itwUser = null;
			Integer langId = (Integer) request.getSession().getAttribute(
					"langId");
			List<ItwUser> itwUsers = itwUserService.listItwUsers(langId
					.intValue());
			boolean userfound = false;
			if (itwUsers != null && !itwUsers.isEmpty()) {

				for (ItwUser itwUser : itwUsers) {

					if ((itwUser.getUserid().equals(lowerCaseUserid))
							&& (itwUser.getPassword().equals(encPassword))) {
						userfound = true;
						tmp_itwUser = itwUser;
						break;
					}
				}
			}
			if (userfound) {
				com.agileidc.itw.web.UserSession userSession = new com.agileidc.itw.web.UserSession(
						tmp_itwUser);
				//get stuff out of session you want before invalidating it.
				HttpSession currentSession = request.getSession();
				
				//now invalidate it
				currentSession.invalidate();

				//get new session and stuff the data back in
				HttpSession newSession = request.getSession(true);
				
				newSession.setAttribute("userSession", userSession);
				
				currentSession = request.getSession(true);
				System.out.println( "The session id that is current after login is 22222222 : " +currentSession.getId());
				//request.getSession().setAttribute("userSession", userSession);
				String userid = userSession.getItwUser().getUserid();
				itwUserService.changeMyOnlineStatus(userid, 1);

				return new ModelAndView("redirect:/mainPage1.html");
			} else {

				result.rejectValue("userid",
						"mainerror.invalid.user.or.password",
						"Email id and password do not match");
				return new ModelAndView("signOn");
			}
		}
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

	@RequestMapping(value = "/doSignOut", method = RequestMethod.GET)
	public ModelAndView doSignOut(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
				.getSession().getAttribute("userSession");
		String userid = null;
		if (userSession != null) {
			userid = userSession.getItwUser().getUserid();
			itwUserService.changeMyOnlineStatus(userid, 0);
		}
		request.getSession().removeAttribute("userSession");
		request.getSession().invalidate();
		ItwUserLogs itwUserLogs = new ItwUserLogs();

		java.util.Date date = new java.util.Date();
		Timestamp logoutTime = new Timestamp(date.getTime());
		itwUserLogs.setLogouttime(logoutTime);
		itwUserLogs.setLastupdatedtime(logoutTime);

		itwUserLogs.setOnlinestatus("N");
		itwUserLogs.setUserid(userid);

		itwUserLogs.setLogoutreason("Manual Logout");

		itwUserLogsService.updateItwUserLogs(itwUserLogs);
		return new ModelAndView("redirect:/home.html");

	}

	public void doSignOutDueToTimeOut(HttpSession session) {
		com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) session
				.getAttribute("userSession");

		String userid = userSession.getItwUser().getUserid();
		itwUserService.changeMyOnlineStatus(userid, 0);
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginfailed(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("error", "true");
		result.rejectValue("j_username", "userid.required",
				"Userid or password is invalid");

		return new ModelAndView("signOn", model);

	}

	@RequestMapping(value = "/disabledUser", method = RequestMethod.GET)
	public ModelAndView disabledUser(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", "true");
		result.rejectValue(
				"j_username",
				"userid.required",
				"User ID has been disabled, please contact the System Administrator for enabling the User ID.");
		return new ModelAndView("signOn", model);

	}

	@RequestMapping(value = "/crossedLoginLimit", method = RequestMethod.GET)
	public ModelAndView crossedLoginLimit(
			@ModelAttribute("command") UserLoginBean userLoginBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", "true");
		result.rejectValue(
				"j_username",
				"userid.required",
				"You crossed login failed limit, user Id is disabled,please contact the System Administrator for enabling the User ID.");
		return new ModelAndView("signOn", model);

	}

	@RequestMapping(value = "/mainPage1", method = RequestMethod.GET)
	public ModelAndView mainPage1(Principal principal,
			HttpServletRequest request) {
		String LAST_USERNAME_KEY = "LAST_USERNAME";

		
		String userid = principal.getName();
		ItwUser tmp_itwUser = null;

		Integer langId = null;
		LocaleResolver localeResolver = RequestContextUtils
				.getLocaleResolver(request);

		if (localeResolver != null) {
			// get current locale
			Locale locale = localeResolver.resolveLocale(request);
			List<ItwLangTypes> listItwLangTypes = itwLangTypesService
					.listItwLangTypes();

			for (ItwLangTypes itwLangType : listItwLangTypes) {
				if (itwLangType.getLangDesc().equals(locale.toString())) {
					langId = itwLangType.getId();
				}
			}

			

		}
		List<ItwUser> itwUsers = itwUserService.listItwUsers(langId.intValue());
		boolean userfound = false;

		if (itwUsers != null && !itwUsers.isEmpty()) {

			for (ItwUser itwUser : itwUsers) {

				if (itwUser.getUserid().equals(userid)) {
					userfound = true;
					tmp_itwUser = itwUser;
					break;
				}
			}
		}
		if (userfound) {
			if (tmp_itwUser.getEnabled() == 1
					&& tmp_itwUser.getOnetimepass() == 0) {

				com.agileidc.itw.web.UserSession userSession = new com.agileidc.itw.web.UserSession(
						tmp_itwUser);
				
				HttpSession currentSession = request.getSession();
				
				  Map<String, HttpSession> userSessions =  UserSession.getUsersSessions();
				  HttpSession sessionToInvalidate =   userSessions.get(userid);
				  if (sessionToInvalidate != null) {
				//now invalidate it
					  sessionToInvalidate.invalidate();
				  }
				//get new session and stuff the data back in
				HttpSession newSession = request.getSession(true);
				
				newSession.setAttribute("userSession", userSession);
				newSession.setAttribute("langId", langId);
				currentSession = request.getSession();
			
				//request.getSession().setAttribute("userSession", userSession);
				userid = userSession.getItwUser().getUserid();
				itwUserService.changeMyOnlineStatus(userid, 1);

				List<ItwProject> itwProjectList = itwProjectService
						.listItwProjects(1);
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("ItwProjects1",
						prepareListofItwProject(itwProjectList));
				String projectId = request.getParameter("projectId");
				newSession.setAttribute("userProjects", prepareListofItwProject(itwProjectList));

				List<ItwTweetBean> itwTweetsBean = null;
				if (projectId != null) {
					if (projectId.equals("All")) {

						itwTweetsBean = prepareListofItwTweets(
								itwTweetsService.listItwTweets(), model,
								request);
					} else {

						itwTweetsBean = prepareListofItwTweets(
								itwTweetsService.listItwTweetsByProjectId(new Integer(
										projectId)), model, request);
					}
				}

				else {

					itwTweetsBean = prepareListofItwTweets(
							itwTweetsService.listItwTweets(), model, request);
				}

				if (itwTweetsBean != null) {
					PagedListHolder pagedListHolder = new PagedListHolder(
							itwTweetsBean);

					int page = ServletRequestUtils.getIntParameter(request,
							"p", 0);

					pagedListHolder.setPage(page);
					int pageSize = 15;
					pagedListHolder.setPageSize(pageSize);
					model.put("pagedListHolder", pagedListHolder);

				}

				ItwUserLogs itwUserLogs = prepareModelItwUsrLogs(tmp_itwUser,
						principal, request);

				itwUserLogsService.addItwUserLogs(itwUserLogs);

				ItwUser itwUser = new ItwUser();

				itwUser.setFailedcount(0);
				itwUser.setEnabled(1);
				itwUser.setUserid(userid);
				itwUserService.updateItwUser(itwUser);

				// ============================mail check

				return new ModelAndView("mainPage1", model);
			}
			if (tmp_itwUser.getOnetimepass() == 1) {
				return new ModelAndView("redirect:/changePass.html");

			}
			if (tmp_itwUser.getFailedcount() == 3) {

				return new ModelAndView("redirect:/crossedLoginLimit.html");

			} else {
				return new ModelAndView("redirect:/disabledUser.html");
			}

		} else {

			request.getSession().setAttribute(LAST_USERNAME_KEY, userid);
			return new ModelAndView("redirect:/loginfailed.html");
		}
	}

	private List<ItwTweetBean> prepareListofItwTweets(
			List<ItwTweets> itwTweets, Map<String, Object> model,
			HttpServletRequest request) {
		List<ItwTweetBean> beans = null;

		if (itwTweets != null && !itwTweets.isEmpty()) {

			beans = new ArrayList<ItwTweetBean>();
			ItwTweetBean bean = null;
			String fileName = "";
			String filePath = "filepath";
			for (ItwTweets itwTweet : itwTweets) {
				ItwUser itwUser = itwUserService.getItwUser(itwTweet
						.getUserid());
				bean = new ItwTweetBean();

				bean.setId(itwTweet.getId());
				bean.setUserid(itwTweet.getUserid());
				bean.setTweetmsg(itwTweet.getTweetmsg());
				bean.setUsername(itwUser.getFirstname() + " "
						+ itwUser.getLastname());

				java.util.Date d1 = new java.util.Date();
				java.util.Date d0 = itwTweet.getTweettime();

				long[] diff = TimeDiff.getTimeDifference(d0, d1);
				String tempdaysago = "";
				String temphoursago = "";
				String tempminutesago = "";
				String tempsecondssago = "";

				if (diff[0] > 1) {
					tempdaysago = diff[0] + " days ";
				}

				if (diff[0] == 1) {
					tempdaysago = diff[0] + " day ";
				}

				if (diff[1] > 1) {
					temphoursago = diff[1] + " hours ";
				}

				if (diff[1] == 1) {
					temphoursago = diff[1] + " hour ";
				}

				if (diff[2] > 1) {
					tempminutesago = diff[2] + " minutes ";
				}

				if (diff[2] == 1) {
					tempminutesago = diff[2] + " minute ";
				}
				if ((diff[3] > 1) && (diff[2] == 0) && (diff[1] == 0)
						&& (diff[0] == 0)) {
					tempsecondssago = diff[3] + " seconds ";
				}

				if ((diff[3] == 1) && (diff[2] == 0) && (diff[1] == 0)
						&& (diff[0] == 0)) {
					tempsecondssago = diff[3] + " second ";
				}

				bean.setDaysagodisplay(tempdaysago + temphoursago
						+ tempminutesago + tempsecondssago + " ago...");

				try {
					InputStream inputStream = null;
					OutputStream outputStream = null;
					fileName = request.getRealPath("") + "/images/" + "img"
							+ itwTweet.getUserid().toString() + "bug.png";
					outputStream = new FileOutputStream(fileName);

					ItwUserIcon itwUserIcon = itwTaskDAOTemp.getItwUser(
							itwUser.getIconid(), outputStream);

					outputStream.flush();
					outputStream.close();
					String fpath = "images/" + "img"
							+ itwTweet.getUserid().toString() + "bug.png";

					bean.setFilepath1(fpath);
				} catch (IOException e) {
					e.printStackTrace();
				}

				beans.add(bean);

			}
		}
		return beans;
	}

	public ItwUserLogs prepareModelItwUsrLogs(ItwUser itwUser,
			Principal principal, HttpServletRequest request) {

		ItwUserLogs itwUserLogs = new ItwUserLogs();

		itwUserLogs.setUserid(itwUser.getUserid());
		itwUserLogs.setIpaddress(request.getRemoteAddr());

		itwUserLogs.setBrowserdetails(request.getHeader("user-agent"));
		itwUserLogs.setOnlinestatus("Y");
		java.util.Date date = new java.util.Date();
		Timestamp logoutTime = new Timestamp(date.getTime());
		itwUserLogs.setLogintime(logoutTime);
		itwUserLogs.setCreationdate(logoutTime);
		itwUserLogs.setSession1(request.getSession().getId());

		return itwUserLogs;
	}
}
