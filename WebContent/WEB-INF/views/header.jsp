<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<link rel="SHORTCUT ICON" href="images/s1.ico"> 
<link rel="stylesheet" href="css/jquery.treetable.css" />
<link rel="stylesheet" href="css/jquery.treetable.theme.default.css" />
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 50px;
}

.sidebar-nav {
	padding: 9px 0;
}

.canvas {
	float: right;
	margin-right: 100px;
}

#menu,#menu ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#menu:before,#menu:after {
	content: "";
	display: table;
}

#menu:after {
	clear: both;
}

#menu {
	zoom: 1;
}

#menu li {
	float: left;
	position: relative;
}

#menu a {
	float: left;
	padding: 10px 30px;
	color: #000;
	text-decoration: none;
	font-size: 9pt;
}

#menu li:hover>a {
	color: #A60C0C;
}

* html #menu li a:hover { /* IE6 only */
	color: #fafafa;
}

#menu ul {
	margin: 20px 0 0 0;
	_margin: 0; /*IE6 only*/
	opacity: 0;
	visibility: hidden;
	position: absolute;
	top: 38px;
	left: 16px;
	z-index: 1;
	background: #900000;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, .3);
	border: 1px solid #500000;
	border-radius: 3px;
	transition: all .2s ease-in-out;
}


#menu ul a {
    color:#fff; 
}

#menu li:hover>ul {
	opacity: 1;
	visibility: visible;
	margin: 0;
}

#menu ul ul {
	top: 0;
	left: 150px;
	margin: 0 0 0 20px;
	_margin: 0; /*IE6 only*/
	box-shadow: -1px 0 0 rgba(255, 255, 255, .3);
}

#menu ul li {
	float: none;
	display: block;
	border: 0;
	_line-height: 0; /*IE6 only*/
	box-shadow: 0 1px 0 #500000, 0 1px 0 #500000;
}

#menu ul li:last-child {
	box-shadow: none;
}

#menu ul a {
	padding: 3px 6px;
	width: auto;
	_height: 10px; /*IE6 only*/
	display: block;
	white-space: nowrap;
	float: none;
	text-transform: none;
	font-size: 11px;
}

#menu ul a:hover {
	background-color: #E84040;
	color:#fff;
}

#menu ul li:first-child>a {
	border-radius: 3px 3px 0 0;
}

#menu ul li:first-child>a:after {
	content: '';
	position: absolute;
	left: 40px;
	top: -6px;
	border-left: 6px solid transparent;
	border-right: 6px solid transparent;
	border-bottom: 5px solid #500000;
}

#menu ul ul li:first-child a:after {
	left: -6px;
	top: 50%;
	margin-top: -6px;
	border-left: 0;
	border-bottom: 6px solid transparent;
	border-top: 6px solid transparent;
	border-right: 6px solid #500000;
}

#menu ul li:first-child a:hover:after {
	border-bottom-color: #E84040;
}

#menu ul ul li:first-child a:hover:after {
	border-right-color: #0299d3;
	border-bottom-color: transparent;
}

#menu ul li:last-child>a {
	border-radius: 0 0 3px 3px;
}

.dropdown {
	float: right;
	width: auto;
	margin-right: 10px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="Simplicity Itself" />

<title>IT Work Book</title>

<link href="css/bootstrap.min.css" rel="stylesheet"></link>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 50px;
}

.sidebar-nav {
	padding: 9px 0;
}

.canvas {
	float: right;
	margin-right: 100px;
}
</style>

<link href="css/bootstrap-responsive.min.css" rel="stylesheet"></link>
<script src="js/tagcanvas.js" type="text/javascript" async=""></script>
<script type="text/javascript">
	//         
	var oopts = {
		textFont : 'Impact,Arial Black,sans-serif',
		textHeight : 20,
		maxSpeed : 0.1,
		decel : 0.9,
		depth : 0.99,
		outlineColour : '#f6f',
		outlineThickness : 3,
		pulsateTo : 0.2,
		pulsateTime : 0.5,
		wheelZoom : false
	}, ttags = 'taglist', lock, shape = 'sphere';
	window.onload = function() {
		TagCanvas.textFont = 'Trebuchet MS, Helvetica, sans-serif';
		TagCanvas.textColour = '#00f';
		TagCanvas.textHeight = 25;
		TagCanvas.outlineColour = '#ff9999';
		TagCanvas.maxSpeed = 0.03;
		TagCanvas.minBrightness = 0.2;
		TagCanvas.depth = 0.92;
		TagCanvas.pulsateTo = 0.6;
		TagCanvas.initial = [ 0.1, -0.1 ];
		TagCanvas.decel = 0.98;
		TagCanvas.reverse = true;
		TagCanvas.hideTags = false;
		TagCanvas.shadow = '#ccf';
		TagCanvas.shadowBlur = 3;
		TagCanvas.weight = false;
		TagCanvas.imageScale = null;
		TagCanvas.fadeIn = 1000;
		try {
			TagCanvas.Start('tagcanvas', 'taglist');
			TagCanvas.Start('smallCanvas', 'moreTags', oopts);
			f('options');
		} catch (e) {
			document.getElementsByTagName('canvas')[0].style.border = '0';
		}
	};
	//
</script>
<style>
.span3 {
	width: 334px;
}

.nav-collapse.collapse {
	margin-left: 74px;
}
</style>
<style>
/*
		 * CSS3 Treeview. No JavaScript
	     * @version 1.0
		 * @author Martin Ivanov
		 * @url developer's website: http://wemakesites.net/
	     * @url developer's twitter: https://twitter.com/#!/wemakesitesnet
		 * @url developer's blog http://acidmartin.wordpress.com/
		 **/

/*
		 * This solution works with all modern browsers and Internet Explorer 9+. 
		 * If you are interested in purchasing a JavaScript enabler for IE8 
		 * for the CSS3 Treeview, please, check this link:
		 * http://experiments.wemakesites.net/miscellaneous/acidjs-css3-treeview/
		 **/
.css-treeview ul,.css-treeview li {
	padding: 0;
	margin: 0;
	list-style: none;
}

.css-treeview input {
	position: absolute;
	opacity: 0;
}

.css-treeview {
	font: normal 11px "Segoe UI", Arial, Sans-serif;
	-moz-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}

.css-treeview a {
	color: #00f;
	text-decoration: none;
}

.css-treeview a:hover {
	text-decoration: underline;
}

.css-treeview input+label+ul {
	margin: 0 0 0 22px;
}

.css-treeview input                                                    
	   ~ ul {
	display: none;
}

.css-treeview label,.css-treeview label::before {
	cursor: pointer;
}

.css-treeview input:disabled+label {
	cursor: default;
	opacity: .6;
}

.css-treeview input:checked:not                                        
	              (:disabled               
	                                       ) ~ ul {
	display: block;
}

.css-treeview label,.css-treeview label::before {
	background: url("images/icons.png") no-repeat;
}

.css-treeview label,.css-treeview a,.css-treeview label::before {
	display: inline-block;
	height: 16px;
	line-height: 16px; ,
	vertical-align: middle;
}

.css-treeview label {
	background-position: 18px 0;
}

.css-treeview label::before {
	content: "";
	width: 16px;
	margin: 0 22px 0 0;
	vertical-align: middle;
	background-position: 0 -32px;
}

.css-treeview input:checked+label::before {
	background-position: 0 -16px;
}

/* webkit adjacent element selector bugfix */
@media screen and (-webkit-min-device-pixel-ratio:0) {
	.css-treeview {
		-webkit-animation: webkit-adjacent-element-selector-bugfix infinite 1s;
	}
	@
	-webkit-keyframes webkit-adjacent-element-selector-bugfix {from {
		padding:0;
		
	}
	to {
		padding: 0;
	}
}
}
</style>
<title>IT WorkBook Application</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid" style="padding-left: 52px">
				<a class="brand" href="home.html" style="padding: 3px 0"><img
					src="images/clientlogo.png" alt="IT Workbook" title="IT Workbook"></a>
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right"></p>
					<ul class="nav" style="font-size: 12px;">
						<li style="width: 52px;">&nbsp;<a class="brand" href="home.html"
							style="position: absolute; top: -14px; margin-left: -10px"><img
								src="images/logo.png" alt="IT Workbook" title="IT Workbook"></a></li>

						<li><ul id="menu" class="nav"
								style="float: right; font-size: 12px;">


								<li><a href="#"><spring:message code="tweets"
											text="Tweets" /></a>

									<ul>
										<li><a href="mainPage1.html?projectId=All"><spring:message
													code="all.project.tweets" text="All Projects" /></a></li>
										<c:set var="counter" value="0" />
										<c:forEach items="${ItwProjects1}" var="ItwProjecttemp">

											<li><a
												href="mainPage1.html?projectId=${ItwProjecttemp.id}">${ItwProjecttemp.projectname}</a></li>
										</c:forEach>

									</ul></li>


							</ul></li>

						<security:authorize
							ifAnyGranted="ROLE_USER,ROLE_MANAGER,ROLE_ADMIN">


							<li><ul id="menu" class="nav"
									style="float: right; font-size: 12px;">


									<li><a href="#"><spring:message code="issue.list"
												text="Issue List" /></a>

										<ul>
											<li><a href="issueList1.html?reccount=10&&projectId=0">All
													Projects</a></li>

											<c:forEach items="${ItwProjects1}" var="ItwProjecttemp">

												<li><a
													href="issueList1.html?reccount=10&&projectId=${ItwProjecttemp.id}">${ItwProjecttemp.projectname}</a></li>
											</c:forEach>

										</ul></li>


								</ul></li>

							<!--  	<li><a href="issueList1.html?reccount=10"><spring:message
									code="issue.list" text="default text" /></a></li>

                          </security:authorize>
                         
                        <security:authorize ifAnyGranted="ROLE_MANAGER"> 
                           
						<li><a href="issueListHistory.html"><spring:message
									code="knowledge.list" text="default text" /></a></li>
									
						<li><a href="piaChartDemo.html">Chart Reports</a></li>
									
						</security:authorize> -->


							<!-- 	<li><a href="itwRcaList.html">RCA Details</a></li> -->
					</ul>



					<ul id="menu" class="nav" style="float: right; font-size: 12px;">
						<li><a href="#" style="color: #A60c0c;">${userSession.itwUser.userid}</a>

							<ul>

								<li><a href="changePass.html"><spring:message
											code="change.password" text="Change Password" /></a></li>
								<li><a href="doSignOut.html"><spring:message
											code="header.logout" text="default text" /></a></li>

							</ul></li>
						<li><a href="http://www.agileidc.com" target="_blank" style="padding: 3px 0"><img
								src="images/agileidclogo.png" alt="IT Workbook"
								title="IT Workbook"></a></li>
					</ul>

					<ul class="nav" style="float: right; font-size: 12px;">


						<li><a href="#"><spring:message code="title.language"
									text="default text" /> : <b style="color: #A60c0c"><spring:message
										code="language.selected" text="default text" /></b></a></li>

					</ul>



					<ul id="menu" class="nav" style="float: right; font-size: 12px;">

						<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
							<li><a href="userConfigList.html"><spring:message
										code="title.configuration" text="default text" /></a>
								<ul>
									<li><a href="userConfigList.html"><spring:message
												code="Manage.Users" text="Manage Users" /></a></li>
									<li><a href="roleConfigList.html"><spring:message
												code="Configure.Roles" text="Configure Roles" /></a></li>
									<li><a href="ItwClientConfigList.html"><spring:message
												code="Configure.Client" text="Configure Clients" /></a></li>
									<li><a href="itwPlatFormsConfigList.html"><spring:message
												code="Configure.Platforms" text="Configure Platforms" /></a></li>
									<li><a href="itwPriorityConfigList.html"><spring:message
												code="Configure.Priority" text="Configure Priorities" /></a></li>
									<li><a href="projectConfigList.html"><spring:message
												code="Configure.Projects" text="Configure Projects" /></a></li>
									<li><a href="companyConfigList.html"><spring:message
												code="Configure.Company" text="Configure Companies" /></a></li>
									<li><a href="itwReleasesConfigList.html"><spring:message
												code="Configure.Releases" text="Configure Releases" /></a></li>
									<li><a href="itwSeverityConfigList.html"><spring:message
												code="Configure.Severity" text="Configure Severities" /></a></li>
									<li><a href="itwBrowserTypesConfigList.html"><spring:message
												code="Configure.Browser" text="Configure Browsers" /></a></li>
									<li><a href="itwStagesTypesConfigList.html"><spring:message
												code="Configure.Stages" text="Configure Stages" /></a></li>
									<li><a href="itwModuleConfigList.html"><spring:message
												code="Configure.Modules" text="Configure Modules" /></a></li>
									<li><a href="itwStatusTypesConfigList.html"><spring:message
												code="Configure.Status" text="Configure Statuses" /></a></li>

									<!-- <li><a href="itwUserRolesConfigarationList.html"><spring:message
											code="Configure.UserRoles" text="default text" /></a></li> -->
									<!-- <li><a href="itwProjectModuleConfigarationList.html"><spring:message
											code="Configure.ProjectModules" text="Link Project/Modules" /></a></li>-->
									<!-- 	<li><a href="itwChartStatusConfigList.html"><spring:message
											code="Configure.ChartStatus" text="View Chart Status" /></a></li> -->
									<li><a href="itwUserLogsList.html"><spring:message
												code="Configure.UserLogs" text="View User Logs" /></a></li>
									<li><a href="itwReleaseDocumentConfigList.html"><spring:message
												code="Configure.ReleaseDocument" text="Release Document" /></a></li>

									<li><a href="itwReleaseProcessConfigList.html"><spring:message
												code="Configure.ReleaseProcess" text="View Release Process" /></a></li>
									
									<!-- Ganesh removed for demo 
									<li><a
										href="itwUserGroupConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
												code="Configure.UserGroupConfiguration" text="User Group Configuration" /></a></li>
                                     -->
								</ul></li>

						</security:authorize>
					</ul>
				</div>
				<div class="dropdown-menu">

					<div class="js-first-tabstop" tabindex="0"></div>
					<div class="dropdown-caret right">
						<span class="caret-outer"></span> <span class="caret-inner"></span>
					</div>
					<ul class="nav">
						<li><a href="userConfigList.html">Manage Users</a></li>
						<li><a href="roleConfigList.html">Configure Roles</a></li>
						<li><a href="itwPlatFormsConfigList.html">Configure
								Platforms</a></li>
						<li><a href="itwPriorityConfigList.html">Configure
								Priority</a></li>
						<li><a href="projectConfigList.html">Configure Projects</a></li>
						<li><a href="companyConfigList.html">Configure Company</a></li>
						<li><a href="itwReleasesConfigList.html">Configure
								Releases</a></li>
						<li><a href="itwSeverityConfigList.html">Configure
								Severity</a></li>
						<li><a href="itwBrowserTypesConfigList.html">Configure
								Browser</a></li>
						<li><a href="itwStagesTypesConfigList.html">Configure
								Stages</a></li>
						<li><a href="itwModuleConfigList.html">Configure Modules</a></li>
						<li><a href="itwStatusTypesConfigList.html">Configure
								Status</a></li>
						<li><a href="itwReleaseProcessConfigList.html"><spring:message
									code="Configure.ReleaseProcess" text="View Release Process" /></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>