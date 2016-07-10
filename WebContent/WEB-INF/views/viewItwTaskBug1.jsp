
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page import="com.agileidc.itw.model.ItwUser"%>
<%@page import="javax.swing.JOptionPane"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<script type="text/javascript" src="js/jquery-1-10-1.js"></script>
<script type="text/javascript" src="js/session-warning.js"></script>
<link rel="stylesheet" href="css/jquery.treetable.css" />
<link rel="stylesheet" href="css/jquery.treetable.theme.default.css" />

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

.css-treeview input                                              ~ ul {
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
<style>
.createIssue input,select,textarea {
	margin: 0 40px 0 20px;
}
</style>
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

#offlineUsers,#onlineUsers {
	height: 135px;
	overflow: scroll;
}

#chatUsers {
	height: 260px;
	overflow: scroll;
}

#offlineUsers a,#onlineUsers a,#chatUsers a {
	font-size: 11px;
}
</style>

<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
<script>
	jQuery(document).ready(function() {
		// binds form submission and fields to the validation engine
		$(".datepicker").datepicker();
		jQuery("#formID").validationEngine();
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="Simplicity Itself" />

<title>IT Work Book</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
</link>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<link rel="stylesheet"
	href="css/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
.online {
	background-image: url(images/online.png);
	background-repeat: no-repeat;
	background-position: 1% center;
	padding: 0 0 0 15px;
}

.offline {
	background-image: url(images/offline.png);
	background-repeat: no-repeat;
	background-position: 1% center;
	padding: 0 0 0 15px;
}

.nav-header {
	padding: 8px 0 8px 15px;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	background-color: #F9F9F9;
	margin-bottom: 4px;
}

.chatleft img {
	position: relative;
	left: 1px;
	top: -1px;
}

.chatright img {
	position: relative;
	right: 1px;
	top: -1px;
}

.chatcenter {
	background-image: url(images/chatcenter.png);
	background-repeat: repeat-x;
	height: 37px;
	width: 70%;
}

.chatcenter1 {
	background-image: url(images/chatcenter1.png);
	background-repeat: repeat-x;
	height: 37px;
	width: 70%;
}

#chatmsg {
	overflow: auto;
	height: 100px;
}
</style>

<script src="js/jquery_004.js"></script>
<script>
	$(document).ready(function() {
		$("#chatmsg").niceScroll();
		var objDiv = document.getElementById("chatmsg");
		objDiv.scrollTop = objDiv.scrollHeight;
	});
</script>

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
button.btn.btn-small,input.btn.btn-small[type="submit"] {
	padding: 2px 10px;
}

.span3 {
	width: 334px;
}

.nav-collapse.collapse {
	margin-left: 74px;
}

.ui-widget {
	font-family: Helvetica, Arial, sans-serif;
	font-size: 11px;
}

.hero-unit {
	font-size: 18px;
	font-weight: 200;
	line-height: 14px;
	color: inherit;
}

label,input,button,select,textarea {
	font-size: 11px;
}

table.treetable {
	font-size: 11px;
}

.btn-small {
	font-size: 11px;
}

.ui-widget-content a {
	color: #fff;
}
</style>
<script src="js/jquery.treetable.js"></script>
<style type="text/css">
#content {
	width: 100%;
	margin: 0 auto;
	overflow: hidden
}

#content a {
     color: #333;
}

#content a:hover {
     text-decoration: none;
}

#content h3 {
     line-height: 20px;
     background: #F0F0F0;
}

#content h3:hover {
     background: #C6E1EC;
}

.demo {
	margin: 1.5em 0;
	padding: 10px;
	position: relative;
	overflow: hidden;
}

.collapse p {
	padding: 0 10px 1em;
}

.switch {
	position: absolute;
	top: -0.5em;
	right: 1.5em;
	padding: 3px;
}

.post .switch {
	position: static;
	text-align: right;
}

.post .main {
	margin-bottom: .3em;
	padding-bottom: 0;
}

.other li,.summary {
	margin-bottom: .3em;
	padding: 1em;
	border: 1px solid #e8e7e8;
	background-color: #f8f7f8
}

.other ul {
	list-style-type: none;
	text-align: center
}

.expand a {
	display: block;
	padding: 3px 10px
}

.expand a:link,.expand a:visited {
	border-width: 1px;
	background-image: url(img/arrow-down.gif);
	background-repeat: no-repeat;
	background-position: 98% 50%;
}

.expand a:hover,.expand a:active,.expand a:focus {
	
}

.expand a.open:link,.expand a.open:visited {
	border: 1px solid #999;
}

.row:nth-of-type(odd) {
    background: #e0e0e0;
}

</style>
<!--[if lte IE 6]>
<style type="text/css">
h3 a, .demo {position:relative; height:1%}
</style>
<![endif]-->

<!--[if lte IE 6]>
<script type="text/javascript">
   try { document.execCommand( "BackgroundImageCache", false, true); } catch(e) {};
</script>
<![endif]-->
<!--[if !lt IE 6]><!-->
<script type="text/javascript" src="js/expand.js"></script>
<script type="text/javascript">$(function() {
    $("#content h3.expand").toggler();
    $("#content div.demo").expandAll({trigger: "h3.expand", ref: "h3.expand"});
    $("#content div.other").expandAll({
      expTxt : "[Show]", 
      cllpsTxt : "[Hide]",
      ref : "ul.collapse",
      showMethod : "show",
      hideMethod : "hide"
    });
    $("#content div.post").expandAll({
      expTxt : "[Read this entry]", 
      cllpsTxt : "[Hide this entry]",
      ref : "div.collapse", 
      localLinks: "p.top a"    
    });    
});
//--><!]]>
</script>
<!--<![endif]-->
</head>
<body>
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<div>

				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold; margin-bottom: 20px;">
					View Issue ID ${itwTaskBug.id} <a class="btn btn-primary btn-small"
						href="issueList1.html?projectId=${projectId}" style="color: #fff; float: right">Issue
						List</a>
						<c:if
								test="${itwTaskBug.currentloggedinuserid == itwTaskBug.assigneeiddisplay}">
								<a class="btn btn-danger btn-small" 
									href="editItwTaskBug1.html?id=${itwTaskBug.id}&&projectId=${projectId}" style="float:right; margin-right:5px">Edit</a>
								<a class="btn btn-info btn-small"
											href="issueHistoryListPageInEdit.html?id=${itwTaskBug.id}&&projectId=${projectId}" style="float:right; margin-right:5px">History</a>
						</c:if>
					 
				</p>


			</div>
			<div id="tabs">
				<ul>
					<li style="line-height: 15px;"><a href="#Issue-Details">Issue
							Details</a></li>
					<li style="line-height: 15px;"><a href="#Attach-Documents">Attach
							Documents</a></li>
					<li style="line-height: 15px;"><a href="#BugChat">Bug Chat</a></li>
					<li style="line-height: 15px;"><a href="#ObjectBrowser">Object
							Browser</a></li>
					<li style="line-height: 15px;"><a href="#Capture-Image">Capture
							Image/Video</a></li>
					<li style="line-height: 15px;"><a href="#RCA-Details">RCA
							Details</a></li>
					
				</ul>
				<div id="Issue-Details">
					<p>
						<form:form method="POST" action="updateItwTaskBug1.html">

							<style>
.createIssue input,select,textarea {
	margin: 0 40px 0 20px;
}
</style>
							

							<table
								style="font-family: 'Helvetica', Arial, sans-serif; font-size: 11px;"
								class="createIssue" cellpadding="4" width="100%">

								<tr>
									<td><h5 style="margin:0">Issue Details</h5></td>
								</tr>
								<tr>
									<td colspan="2"><form:errors path="*" cssClass="error"
											style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt; " />
									</td>
									<td><form:hidden path="id" /></td>
								</tr>


								<tr>
									<td>Short Description</td>

									<td><form:input path="shortname" readonly="true"
											value="${itwTaskBug.shortname}" placeholder="Enter Shortname" /></td>

									<td>Type</td>

									<td><form:input path="type1display" readonly="true"
											value="${itwTaskBug.type1display}" /></td>

									<td style="line-height:16px">Efforts Estimated<br>(in hours)</td>

									<td><form:input path="efforts" readonly="true"
											value="${itwTaskBug.efforts}" placeholder="Enter efforts" /></td>
								</tr>
								<tr>
									<td>Severity</td>

									<td><form:input path="severityiddisplay" readonly="true"
											value="${itwTaskBug.severityiddisplay}" /></td>

									<td>Priority</td>

									<td><form:input path="priorityiddisplay" readonly="true"
											value="${itwTaskBug.priorityiddisplay}" /></td>


									<td>Dead Line</td>

									<td><form:input path="deadlinedisplay" readonly="true"
											class="listgrid-criteria-input datepicker" type="text"
											value="${itwTaskBug.deadlinedisplay}"
											placeholder="Enter Deadline" /></td>
								</tr>
								<tr>
									<td>Platform</td>
									<td><form:input path="platformiddisplay" readonly="true"
											value="${itwTaskBug.platformiddisplay}" /></td>

									<td>Project</td>


									<td><form:input path="projectiddisplay" readonly="true"
											value="${itwTaskBug.projectiddisplay}" /></td>



									<td>Status</td>

									<td><form:input path="statusiddisplay" readonly="true"
											value="${itwTaskBug.statusiddisplay}" /></td>

								</tr>

								<tr>


									<td>Assignee</td>

									<td><form:input path="assigneeiddisplay" readonly="true"
											value="${itwTaskBug.assigneeiddisplay}" /></td>
									<td>Stages</td>

									<td><form:input path="stagesiddisplay" readonly="true"
											value="${itwTaskBug.stagesiddisplay}" /></td>
									<td>Releases</td>

									<td><form:input path="releasesiddisplay" readonly="true"
											value="${itwTaskBug.releasesiddisplay}" /></td>
										</tr>	
									<tr>
									
									 <td>Version</td>
									<td><form:input path="version" readonly="true"
											value="${itwTaskBug.version}" placeholder="version" /></td>
						       
									 <td>URL</td>
									<td><form:input path="url" readonly="true"
											value="${itwTaskBug.url}" placeholder="url" /></td>
										
									<td>Depends On</td>
									<td><form:input path="releasesiddisplay" readonly="true"
											value="${itwTaskBug.dependsOn}" /></td>
											
											

								</tr>

								 <tr>
                         <td>Approved By</td>
					      <td><form:select path="approvedBy"  readonly="true">
								
								<c:forEach items="${ItwUserBean}"
												var="itwUserBean"  >
									
								             	<c:choose>
													<c:when
														test="${itwUserBean.id == approvalname}">
														<option value="${itwUserBean.id}" selected >${itwUserBean.userid}</option>
													</c:when>
													<c:otherwise>
														
													</c:otherwise>
												</c:choose>
									
								
											</c:forEach>
								
								</form:select> </td>
								</tr>
								<tr>

									<td valign="top">Long Description</td>
									<td colspan="9"><textarea cols="99" rows="3"
											name="summary" readonly="true" id="summary"
											style="width: 92%">
											<c:out value="${itwTaskBug.summary}" />
										</textarea></td>
								</tr>
								
								
<tr>
									<td valign="top">Comment</td>
									<td colspan="9"><textarea cols="99" rows="3"
											name="resolution" readonly="true" id="resolution"
											style="width: 92%">
											<c:out value="${itwTaskBug.resolution}" />
										</textarea></td>

								</tr>
								

							<tr>
									<td colspan="10">

										<section class="ac-container">
											<div>
												<input id="ac-0" name="accordion-1" type="checkbox" /> <label
													for="ac-0">Comment History </label>
												<article class="ac-small">

													<table style="border-bottom: 1px solid #666" width="100%">
														<tr>
															<td>
															<div id="content">
															<div class="demo">    
															<c:set var="loopVal" scope="request"
																	value="${1}" /> <c:forEach items="${commentsHistory}"
																	var="commentsHistory">
																	
																		
																			<h3 class="expand" style="font-size:8pt">Comment ${loopVal} :
																				${commentsHistory.lastupdatedbydisplay} |
																				${commentsHistory.lastUpdateDisplayTimeInGMT}</h3>
																			<c:set var="loopVal" scope="request"
																				value="${loopVal + 1}" />
																			<div class="collapse" style="height:auto">
																				<table style="border-bottom: 1px solid #666"
																					width="100%">
																					<tr>
																						<td colspan="9">
																						<textarea cols="99" rows="4"
																								readonly="true" style="width: 96%; margin-bottom:10px">${commentsHistory.resolution}</textarea>

																						</td>
																					</tr>
																				</table>
																			</div>
																</c:forEach>
																</div>
																</div>
																</td>
														</tr>
													</table>
												</article>
											</div>
										</section>
									</td>
								</tr>

								<tr>
									<td colspan="10">

										<section class="ac-container">
											<div>
												<input id="ac-200" name="accordion-10" type="checkbox" /> <label
													for="ac-200">Issue Status Tracking </label>
												<article class="ac-small">

													<div id="Elapse-Time">


														<table class="table table-striped">

															<thead>


																<tr>
																	<c:if test="${!empty pagedListHolder1}">
																		<td colspan="14"><tg:paging
																				pagedListHolder="${pagedListHolder1}"
																				pagedLink="${pagedLink}" /></td>
																	</c:if>
																	<td></td>
																</tr>
																<tr>




																	<th>Previous Status</th>
																	<th>Time</th>
																	<th>Next Status</th>
																	<th>Time</th>
																	<th>Elapse Time</th>
																	<th>Rework</th>
																	<th>Rework Count</th>
																	



																</tr>
															</thead>

															<tbody>
															
																<c:if test="${!empty pagedListHolder1}">

																	<c:set var="ReworkCount" scope="request" value="${0}" />
																	

																	<c:forEach items="${pagedListHolder1.pageList}"
																		var="defectFlow">
																		<tr>
																		
																		
																			<c:if test="${defectFlow.reworkCount > 0}">

																				<td style="color: red"><c:out
																						value="${defectFlow.previousState}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.previousTime}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.nextState}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.nextTime}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.elapse}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.reworkFlag}" /></td>
																				<td style="color: red"><c:out
																						value="${defectFlow.reworkCount}" /></td>

																			</c:if>
																			<c:if test="${defectFlow.reworkCount == 0}">

																				<td style="color: green"><c:out
																						value="${defectFlow.previousState}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.previousTime}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.nextState}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.nextTime}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.elapse}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.reworkFlag}" /></td>
																				<td style="color: green"><c:out
																						value="${defectFlow.reworkCount}" /></td>

																			</c:if>


																		</tr>

																	</c:forEach>
																</c:if>
																<c:if test="${empty pagedListHolder1.pageList}">
																	<tr>
																		<td>No Issue records found...</td>
																	</tr>

																</c:if>
															</tbody>
														</table>

													</div>
												</article>
											</div>
										</section>

									</td>
								</tr>

								<tr>
									<td colspan="10">

										<section class="ac-container">
											<div>
												<input id="ac-300" name="accordion-1" type="checkbox" /> <label
													for="ac-300">Rework Effort Calculation </label>
												<article class="ac-small">

													<div id="Rework-Time-">


														<table class="table table-striped">

															<thead>

																<tr>

																	<th>Previous Status</th>
																	<th>Next Status</th>
																	<th>Rework Time</th>

																</tr>
															</thead>

															<tbody>
																<c:if test="${!empty itwReworkBeanList}">

																	<c:forEach items="${itwReworkBeanList}"
																		var="itwReworkBeanListTemp">
																		<tr>

																			<td style="color: red"><c:out
																					value="${itwReworkBeanListTemp.previousStateDisplay}" /></td>
																			<td style="color: red"><c:out
																					value="${itwReworkBeanListTemp.nextStateDisplay}" /></td>
																			<td style="color: red"><c:out
																					value="${itwReworkBeanListTemp.reworkDisplay}" /></td>


																		</tr>

																	</c:forEach>
																</c:if>
																<c:if test="${empty itwReworkBeanList}">
																	<tr>
																		<td>No data found...</td>
																	</tr>

																</c:if>
															</tbody>
														</table>

													</div>

													<div id="Grand-Rework-Time-">


														<table class="table table-striped">

															<thead>

																<tr>

																	<th>Grand Total Rework Time</th>


																</tr>
															</thead>

															<tbody>
																<c:if test="${!empty grandTotal}">


																	<tr>

																		<td style="color: red"><c:out
																				value="${grandTotal}" /></td>


																	</tr>

																</c:if>
																<c:if test="${empty grandTotal}">
																	<tr>
																		<td>No data found...</td>
																	</tr>

																</c:if>
															</tbody>
														</table>

													</div>
												</article>
											</div>
										</section>

									</td>
								</tr>

							</table>


						</form:form>
					</p>


				</div>
				<div id="Attach-Documents">

					<h5>List of Documents</h5>
					<c:if test="${!empty documentList}">
						<table class="table table-striped">
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>&nbsp;</th>
							</tr>
							<c:forEach items="${documentList}" var="document">
								<tr>
									<td><c:out value="${document.name}" /></td>
									<td><c:out value="${document.description}" /></td>
									<td><a class="btn btn-primary btn-small"
										href="downloadDoc.html?id=${document.id}" target="_blank">View</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty documentList}">
						<table class="table table-striped">
							<tr>
								<th>Name</th>
								<th>Description</th>
							</tr>

							<tr>
								<td>No Documents Attached</td>
								<td></td>

							</tr>

						</table>
					</c:if>

				</div>
				<div id="BugChat" style="margin-left: -23px">
					<p>
					<div class="container-fluid">
						<table width="100%">
							<tr>
								<td width="20%" valign="top">
									<table width="100%">
										<tr>
											<td>
												<div class="well sidebar-nav span2"
													style="margin: 0; width: 100%; margin-bottom: 30px;">
													<ul class="nav nav-list">
														<li class="nav-header" style="margin: 0 -8px; color: #A60c0c">Online
															Users</li>
														<li><div
																style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;"
																id="onlineUsers"></div></li>

													</ul>
												</div>
											</td>
										</tr>

										<tr>
											<td>
												<div class="well sidebar-nav span2"
													style="margin: 0; width: 100%">
													<ul class="nav nav-list">
														<li class="nav-header" style="margin: 0 -8px; color: #A60c0c">Offline
															Users</li>
														<li><div
																style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;"
																id="offlineUsers"></div></li>
													</ul>
												</div>
											</td>
										</tr>
									</table>
								</td>
								<td>
									<div class="well sidebar-nav span6"
										style="margin: 0 10px; width: 100%">
										<div class="chatcontent"
											style="margin: 0px 9px; background: #fff; border-radius: 12px;">

											<div class="viewmsg"
												style="min-height: 340px; overflow: hidden;" id="chatmsg"
												tabindex="5000">
												<table width="100%" height="340px">
													<tr>
														<td valign="bottom" align="center">

															<table id="inbox" width="90%">


															</table>

														</td>
													</tr>
												</table>
											</div>

											<br>
											<div class="typemsg" style="padding: 0px 20px;">
												<table width="100%">
													<tr>


														<td width="99%" colspan="2"><textarea name="message"
																id="message" rows="2" style="width: 88%"></textarea></td>
														<td>&nbsp;</td>
														<td width="1%"><a id="sendButtonId"
															class="btn btn-danger btn-small" href="#">Send</a></td>

													</tr>


												</table>
											</div>
										</div>
									</div>
								</td>
								<td width="22%" valign="top">
									<div class="well sidebar-nav span2"
										style="margin: 0 0 0 20px; width: 100%; height: 422px;">
										<ul class="nav nav-list" style="padding: 0">
											<table style="margin: 0 auto">
												<tr>
													<td>&nbsp;Add User to Bug Chat Group</td>
													<td align="right"><a class="btn btn-success btn-small"
														href="javascript:addUser();">&nbsp;&nbsp;Add&nbsp;&nbsp;</a>
													</td>
												</tr>
												<tr>
													<td colspan="2"><input type="text" id="users"
														readonly="true" name="to" style="margin: 0px" /></td>

												</tr>
												<tr>
													<td><input type="hidden" id="usersList"
														name="sendToUsers"></input></td>

												</tr>
												<tr>
													<td colspan="2">
														<table width="100%">
															<tr>
																<td>
																	<div class="well sidebar-nav span2"
																		style="margin: 0; width: 100%">
																		<ul class="nav nav-list">
																			<li class="nav-header" style="margin: 0 -8px; color: #A60c0c">Current
																				Chat Users</li>
																			<li><div
																					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;"
																					id="chatUsers"></div></li>

																		</ul>
																	</div>
																</td>
															</tr>

														</table>
													</td>
												</tr>

											</table>
											<li>
												<table cellpadding="3" style="font-size: 9px" width="100%">

													<!--  <tr style="border-bottom: 1px solid #ccc">
													<td width="1%"><img src="images/suser1.png"></td>
													<td><b>Name</b><br>www.email.com</td>
													<td align="right"><a href="#"><img
															src="images/deluser.png"></a></td>
												</tr>
												<tr style="border-bottom: 1px solid #ccc">
													<td width="1%"><img src="images/suser2.png"></td>
													<td><b>Name</b><br>www.email.com</td>
													<td align="right"><a href="#"><img
															src="images/deluser.png"></a></td>
												</tr>
												<tr style="border-bottom: 1px solid #ccc">
													<td width="1%"><img src="images/suser3.png"></td>
													<td><b>Name</b><br>www.email.com</td>
													<td align="right"><a href="#"><img
															src="images/deluser.png"></a></td>
												</tr>  -->
												</table>
											</li>
										</ul>
									</div>
								</td>
							</tr>
						</table>
					</div>

					</p>


				</div>
				<div id="ObjectBrowser">
					<h5>List of Modules</h5>
					<table class="table">
						<tr>
							<th>Module Name</th>
							<th>Module Tree</th>


						</tr>
						<c:forEach items="${itwTaskBug.itwModuleList}" var="itwModule">
							<tr>
								<td width="12%"><c:out value="${itwModule.moduleName}" /></td>
								<td>
									<div id="main">
										<table class="example-advanced" id="${itwModule.id}">
											<caption style="text-align: left;">
												<a href="#"
													onclick="jQuery('#'+${itwModule.id}).treetable('expandAll'); "
													style="color: #000;">Expand all</a>
												&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
													onclick="jQuery('#'+${itwModule.id}).treetable('collapseAll');"
													style="color: #000;">Collapse all</a>
											</caption>
											<thead>
												<tr>
													<th>Name</th>
													<th>Type</th>
													<th>Locked</th>
													<th>Locked By</th>

												</tr>
											</thead>
											<tbody>

												<c:forEach items="${itwModule.itwModuleTreeList}"
													var="itwModuleTree">
													<c:if test="${itwModuleTree.actualRoot == 1}">
														<tr data-tt-id='${itwModuleTree.id}'>
															<td><span class='folder'>${itwModuleTree.nodeName}</span></td>
															<td>${itwModuleTree.nodeType}</td>
															<td>${itwModuleTree.lockedStatus}</td>
															<td>${itwModuleTree.lockedBy}</td>

														</tr>
													</c:if>
													<c:if test="${itwModuleTree.actualRoot != 1}">
														<c:if test="${itwModuleTree.lockedStatus == 'Y'}">
															<tr style="color: #F00" data-tt-id='${itwModuleTree.id}'
																data-tt-parent-id='${itwModuleTree.parentId}'>
																<td><span class='${itwModuleTree.nodeType}'>${itwModuleTree.nodeName}</span></td>
																<td>${itwModuleTree.nodeType}</td>
																<td>${itwModuleTree.lockedStatus}</td>
																<td>${itwModuleTree.lockedBy}</td>


															</tr>
														</c:if>
														<c:if test="${itwModuleTree.lockedStatus == 'N'}">
															<tr data-tt-id='${itwModuleTree.id}'
																data-tt-parent-id='${itwModuleTree.parentId}'>
																<td><span class='${itwModuleTree.nodeType}'>${itwModuleTree.nodeName}</span></td>
																<td>${itwModuleTree.nodeType}</td>
																<td>${itwModuleTree.lockedStatus}</td>
																<td>${itwModuleTree.lockedBy}</td>

															</tr>
														</c:if>
													</c:if>
												</c:forEach>

											</tbody>
										</table>

									</div>
								</td>

							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="Capture-Image">

					<h5>List of Images</h5>
					<c:if test="${!empty images}">
						<table class="table table-striped">
							<tr>
								<th>Description</th>
								<th>Action</th>



								<th>&nbsp;</th>
							</tr>
							<c:forEach items="${images}" var="image">
								<tr>
									<td><c:out value="${image.description}" /></td>

									<td><a class="btn btn-primary btn-small"
										href="downloadImg.html?id=${image.id}" target="_blank">View</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty images}">
						<table class="table table-striped">
							<tr>
								<th>Description</th>
								<th>Action</th>
							</tr>

							<tr>
								<td>No Images Captured</td>
								<td></td>
							</tr>

						</table>
					</c:if>
				</div>
				<!-- 	<div id="Knowledge">
					<p>Under Construction.....</p>

				</div> -->
				<div id="RCA-Details">
					<div>

						<table class="table table-striped">



							<tbody>
								<c:if test="${!empty itwRcaBean}">
									<c:forEach items="${itwRcaBean}" var="itwRca">
										<tr>
											<td width="20%">Task Id</td>

											<td><c:out value="${itwRca.taskId}" /></td>
										</tr>
										<tr>
											<td>Injected Stage</td>

											<td><c:out value="${itwRca.injectedStageIdDisplay}" /></td>
										</tr>
										<tr>
											<td>Injected By</td>
											<td><c:out value="${itwRca.injectedByDisplay}" /></td>
										</tr>
										<tr>
											<td>Detected Stage</td>
											<td><c:out value="${itwRca.detectedStageIdDisplay}" /></td>
										</tr>
										<tr>
											<td>Detected By</td>
											<td><c:out value="${itwRca.detectedByDisplay}" /></td>


										</tr>
										<tr>
											<td>Feedback Given To</td>
											<td><c:out value="${itwRca.feedbackGivenToDisplay}" /></td>


										</tr>
										<tr>
											<td>Corrective Action Taken</td>
											<td><c:out value="${itwRca.correctiveActionTaken}" /></td>


										</tr>
										<tr>
											<td>Root Cause Analysis Details</td>
											<td><c:out value="${itwRca.rcaDetails}" /></td>


										</tr>

									</c:forEach>
								</c:if>
								<c:if test="${empty itwRcaBean}">
									<tr>
										<td>RCA Details is not added for this Issue...</td>
									</tr>

								</c:if>
							</tbody>
						</table>

					</div>

				</div>
			</div>
			<!-- -------------End of tabs div------------- -->

		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			initSessionMonitor();
			getOnlineUsers();
			/*getAllUsers();*/
			getMyMessages();
			setInterval(function() {
				getMyMessages()
			}, 500);
			setInterval(function() {
				getOnlineUsers()
			}, 5000);
			setInterval(function() {
				getOfflineUsers()
			}, 5000);
			setInterval(function() {
				refreshTreeNodes()
			}, 1000);
			setInterval(function() {
				refreshChatUsers()
			}, 1000);
			/*setInterval(function() {
				getAllUsers()
			}, 5000);*/

		});

		function selectUser(string) {
			$("#users").attr('value', string);
		}

		function addImage(issueId) {
			alert("inside addImage");
			document.getElementById("imageSubmitForm").submit();

			alert("after addimage submit");
		}

		function addDocument() {
			$.ajax({
				type : "POST",
				url : "savedoc.html",
				data : "to=" + $("#users option:selected").val() + "&message="
						+ $("#message").val(),
				success : function() {
					$("#msgStatus").html("Message Sent Successfully...");
					var objDiv = document.getElementById("chatmsg");
					objDiv.scrollTop = objDiv.scrollHeight;
					$('#message').val('');
				},
				error : function(e) {
					alert("error" + e.toSource());
					$("#msgStatus").html("Error : " + e);
				}
			});
		}
		function refreshTreeNodes() {
			$
					.ajax({
						type : "POST",
						url : "getNodes.html",
						dataType : "json",
						success : function(result) {

							for ( var i = 0; i < result.length; i++) {

								if (typeof result[i].lockedBy === "undefined") {
									result[i].lockedBy = '';
								}

								$('#' + result[i].id + '-c').replaceWith(
										'<td id=\'' + result[i].id + '-c\'>'
												+ result[i].lockedStatus
												+ '</td>');
								$('#' + result[i].id + '-d').replaceWith(
										'<td id=\'' + result[i].id + '-d\'>'
												+ result[i].lockedBy + '</td>');

								if (result[i].lockedStatus == 'Y') {
									if (result[i].lockedBy == '${userSession.itwUser.userid}') {
										var tempstr1 = '<td id="' + result[i].id + '-e\"><a class="btn btn-success btn-small" href="javascript\:sendNodeForCheckin(';
										var tempstr2 = tempstr1 + "'"
												+ result[i].id + "'";
										var tempstr3 = tempstr2 + ');\"'
												+ '>Check In</a> ';
										var tempstr4 = tempstr3
												+ '<a class="btn btn-danger btn-small" href="javascript\:sendNodeForUnLock(';
										var tempstr5 = tempstr4 + "'"
												+ result[i].id + "'";
										var tempstr6 = tempstr5 + ');\"'
												+ '>Undo Check Out</a>  </td> ';

										$('#' + result[i].id + '-e')
												.replaceWith(tempstr6);
									} else {

										$('#' + result[i].id + '-e')
												.replaceWith(
														'<td id="' + result[i].id + '-e\"></td>');
									}
								}
								if (result[i].lockedStatus == 'N') {
									var tempstr11 = '<td id="' + result[i].id + '-e\"><a class="btn btn-info btn-small" href="javascript\:sendNodeForCheckout(';
									var tempstr21 = tempstr11 + "'"
											+ result[i].id + "'";
									var tempstr31 = tempstr21 + ');\"'
											+ '>Check Out</a>  </td>  ';
									$('#' + result[i].id + '-e').replaceWith(
											tempstr31);
								}
							}

						},
						error : function(e) {
							alert("error" + e.toSource());
						}
					});
		}

		function sendNodeForCheckout(treeid) {
			$.ajax({
				type : "POST",
				url : "getNodes.html",
				dataType : "json",
				success : function(result) {

					var j = 0;

					for ( var i = 0; i < result.length; i++) {

						if (result[i].id == treeid) {
							j = i;
						}
						;
					}
					if (result[j].lockedStatus == "Y") {

						$('#' + treeid + '-c').replaceWith(
								'<td id=\'' + treeid + '-c\'>Y</td>');
						$('#' + treeid + '-d').replaceWith(
								'<td id=\'' + treeid + '-d\'>'
										+ result[j].lockedBy + '</td>');

						$('#' + treeid + '-e').replaceWith(
								'<td id=\'' + treeid + '-e\'></td>');
					} else {
						sendNodeForCheckout1(treeid);
					}
					;
				},
				error : function(e) {
					alert("error" + e.toSource());
				}
			});
		}

		function sendNodeForCheckout1(treeid) {

			$
					.ajax({
						type : "POST",
						url : "sendNodeForCheckout.html",
						data : "id=" + treeid + "&issueid=" + $("#id").val(),

						success : function(result) {

							$('#' + treeid + '-c').replaceWith(
									'<td id=\'' + treeid + '-c\'>Y</td>');
							$('#' + treeid + '-d')
									.replaceWith(
											'<td id=\'' + treeid + '-d\'>${userSession.itwUser.userid}</td>');
							var tempstr1 = '<td id="' + treeid + '-e\"><a class="btn btn-success btn-small" href="javascript\:sendNodeForCheckin(';
							var tempstr2 = tempstr1 + "'" + treeid + "'";
							var tempstr3 = tempstr2 + ');\"' + '>Check In</a> ';
							var tempstr4 = tempstr3
									+ '<a class="btn btn-danger btn-small" href="javascript\:sendNodeForUnLock(';
							var tempstr5 = tempstr4 + "'" + treeid + "'";
							var tempstr6 = tempstr5 + ');\"'
									+ '>Undo Check Out</a>  </td> ';

							$('#' + treeid + '-e').replaceWith(tempstr6);

						},
						error : function(e) {
							alert("error" + e.toSource());
							$("#msgStatus").html("Error : " + e);
						}
					});
		}

		function sendNodeForCheckin(treeid) {

			$
					.ajax({
						type : "POST",
						url : "sendNodeForCheckin.html",
						data : "id=" + treeid + "&issueid=" + $("#id").val(),
						success : function() {

							$('#' + treeid + '-c').replaceWith(
									'<td id=\'' + treeid + '-c\'>N</td>');
							$('#' + treeid + '-d').replaceWith(
									'<td id=\'' + treeid + '-d\'></td>');
							var tempstr1 = '<td id="' + treeid + '-e\"><a class="btn btn-info btn-small" href="javascript\:sendNodeForCheckout(';
							var tempstr2 = tempstr1 + "'" + treeid + "'";
							var tempstr3 = tempstr2 + ');\"'
									+ '>Check Out</a>  </td>  ';
							$('#' + treeid + '-e').replaceWith(tempstr3);

						},
						error : function(e) {
							alert("error" + e.toSource())
							$("#msgStatus").html("Error : " + e);
						}
					});
		}

		function sendNodeForUnLock(treeid) {

			$
					.ajax({
						type : "POST",
						url : "sendNodeForUnLock.html",
						data : "id=" + treeid + "&issueid=" + $("#id").val(),
						success : function() {

							$('#' + treeid + '-c').replaceWith(
									'<td id=\'' + treeid + '-c\'>N</td>');
							$('#' + treeid + '-d').replaceWith(
									'<td id=\'' + treeid + '-d\'></td>');
							var tempstr1 = '<td id="' + treeid + '-e\"><a class="btn btn-info btn-small" href="javascript\:sendNodeForCheckout(';
							var tempstr2 = tempstr1 + "'" + treeid + "'";
							var tempstr3 = tempstr2 + ');\"'
									+ '>Check Out</a> </td> ';
							$('#' + treeid + '-e').replaceWith(tempstr3);

						},
						error : function(e) {
							alert("error" + e.toSource())
							$("#msgStatus").html("Error : " + e);
						}
					});
		}
		function sendMessage() {
			$.ajax({
				type : "POST",
				url : "sendMessage.html",
				data : "to=" + $("#users option:selected").val() + "&message="
						+ $("#message").val() + "&id=" + $("#id").val(),
				success : function() {
					$("#msgStatus").html("Message Sent Successfully...");
					var objDiv = document.getElementById("chatmsg");
					objDiv.scrollTop = objDiv.scrollHeight;
					$('#message').val('');
				},
				error : function(e) {
					alert("error" + e.toSource());
					$("#msgStatus").html("Error : " + e);
				}
			});
		}

		function newPopup(url) {
			popupWindow = window
					.open(
							url,
							'popUpWindow',
							'height=100,width=100,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=yes')
		}

		function addUser() {
			
			
				$.ajax({
					type : "POST",
					url : "addUserToChatWindow.html",
					data : "to=" + $("#users").val() + "&id=" + $("#id").val(),
					success : function() {
						
					},
					error : function(e) {
						alert("error" + e.toSource());
						
					}
				});
			
			
			
		}
		function deleteUser(userIdtoDelete) {
			alert("deleting this user" +userIdtoDelete );
			
			$.ajax({
				type : "POST",
				url : "deleteUserFromChatWindow.html",
				data : "toDelete=" + userIdtoDelete + "&id=" + $("#id").val(),
				success : function() {
					
				},
				error : function(e) {
					alert("error" + e.toSource());
					
				}
			});
		
		
		
	}
		function refreshChatUsers() {
			$
					.ajax({
						type : "POST",
						url : "getChatUsers.html" ,
						data : "id=" + $("#id").val(),
						success : function(result) {
							if (result.length == 0) {
								var oo2 = "<li style='font-size:11px; color:blue'> No Users in Chat "
									+   "</li>";
								$("#chatUsers").html(oo2);
								//$("#sendButtonId").attr("disable")
								document.getElementById("sendButtonId").href="#"; 
								document.getElementById("sendButtonId").style.opacity="0.5";
								
							} else {
								document.getElementById("sendButtonId").href="javascript:sendMessage();";
								document.getElementById("sendButtonId").style.opacity="1";
								
								for ( var i = 0; i < result.length; i++) {

									var oo1 = "<li style='font-size:11px; color:blue'> "
										+ result[i].userId + " <a class='btn btn-danger btn-small' href='javascript:deleteUser('" + result[i].userId  +  "');' style='float:right'>Delete</a></li>";

										var tempstr1 = "<li style='font-size:11px; color:blue'> "
											+ result[i].userId + " <a class='btn btn-danger btn-small' href='javascript:deleteUser(";
										var tempstr2 = tempstr1 + '"' + result[i].userId + '"';
										var tempstr3 = tempstr2 + ");' style='float:right'>Delete</a></li>";
																		
									if (i == 0) {
										$("#chatUsers").html(tempstr3);
									} else {
										$("#chatUsers").append("" + tempstr3);
									}
								}
							}
						},
						error : function(e) {
							alert("error" + e.toSource());
						}
					});
		}

		function getOnlineUsers() {
			$
					.ajax({
						type : "POST",
						url : "getOnlineUsers.html",
						dataType : "json",
						success : function(result) {
							if (result.length == 0) {
								var oo = "Online Users None <br>";

								$("#onlineUsers").html(oo);

							} else {
								for ( var i = 0; i < result.length; i++) {

									var oo = "<li class='online'> <a href=javascript:selectUser('"
											+ result[i].userid
											+ "')><font color='green'>"
											+ result[i].userid
											+ "</font></a> </li>";
									if (i == 0) {
										$("#onlineUsers").html(oo);
									} else {
										$("#onlineUsers").append("" + oo);
									}
								}
							}
						},
						error : function(e) {
							alert("error" + e.toSource());
						}
					});
		}
		function getOfflineUsers() {
			$
					.ajax({
						type : "POST",
						url : "getOfflineUsers.html",
						dataType : "json",
						success : function(result) {

							if (result.length == 0) {
								var oo = "Offline Users None <br>";

								$("#offlineUsers").html(oo);

							} else {
								for ( var i = 0; i < result.length; i++) {

									var oo = "<li class='offline'><a href=#"
											+ "><font color='grey'>"
											+ result[i].userid
											+ "</font></a></li> ";
									if (i == 0) {
										$("#offlineUsers").html(oo);
									} else {
										$("#offlineUsers").append("" + oo);
									}
								}
							}

						},
						error : function(e) {
							alert("error" + e.toSource());
						}
					});
		}

		function getMyMessages() {
			$
					.ajax({
						type : "POST",
						url : "getMyMessages.html" + "?id=" + $("#id").val(),
						dataType : "json",
						success : function(result) {

							if (result.length == 0) {
								

							} else {
							for ( var i = 0; i < result.length; i++) {
								if (i == 0) {
									$("#maxVal").val(result[i].id);
								} else if (i == result.length - 1) {
									$("#minVal").val(result[i].id);
								}

								var time = "";
								time = result[i].time;
								if (result[i].senderId != result[i].receiverId) {
									var oo = " <tr>"
											+ "<td class='to' align='left' " + 
							"style='float: left; padding: 10px'>"
											+ "<div class='viewmsg'  " +
								"style='padding: 10px; background: #87c2ff; border-radius: 10px;'>"
											+ "<table>"
											+ "<tr>"
											+ "<th align='left'>"
											+ result[i].senderId
											+ "</th>"
											+ "</tr>"
											+ "<tr>"
											+ "<td>"
											+ result[i].message
											+ "</td>"
											+ "</tr>"
											+ "</table>"
											+ "</div>"
											+ "<div class='msgtime' " + 
								"style='margin-right: 20px; text-align: left; font-size:8px; color:#999; font-weight:bold;'>"
											+ result[i].time + "</div>"
											+ "</td>" + "</tr> ";
									
										$("#inbox").append(oo);
									
								} else {
									var oo = " <tr>"
											+ "<td class='to' align='right' " + 
							"style='float: right; padding: 10px'>"
											+ "<div class='viewmsg'  " +
								"style='padding: 10px; background: #7ae46b; border-radius: 10px;'>"
											+ "<table>"
											+ "<tr>"
											+ "<th align='left'>"
											+ "<b>me</b>"
											+ "</th>"
											+ "</tr>"
											+ "<tr>"
											+ "<td>"
											+ result[i].message
											+ "</td>"
											+ "</tr>"
											+ "</table>"
											+ "</div>"
											+ "<div class='msgtime' " + 
								"style='margin-right: 4px; text-align: right; font-size:8px; color:#999; font-weight:bold;'>"
											+ result[i].time + "</div>"
											+ "</td>" + "</tr> ";

									
										$("#inbox").append(oo);
									
								}
							}
							setNiceScrollPosition();
							var objDiv = document.getElementById("chatmsg");
							objDiv.scrollTop = objDiv.scrollHeight;
							}
						},
						error : function(e) {
							alert("error" + e.toSource());
						}
					});
		}
		function setNiceScrollPosition() {
			var objDiv = document.getElementById("chatmsg");
			objDiv.scrollTop = objDiv.scrollHeight;
		}
		function getMyPrevMessages() {
			$.ajax({
				type : "POST",
				url : "getPrev.html",
				dataType : "json",
				data : "minVal=" + $("#minVal").val(),
				success : function(result) {
					for ( var i = 0; i < result.length; i++) {
						if (i == 0) {
							$("#maxVal").val(result[i].id);
						} else if (i == result.length - 1) {
							$("#minVal").val(result[i].id);
						}
						var time = "";
						time = result[i].time;
						var oo = "<tr bgcolor='gray' border='2' >" + "<td>"
								+ "<tr><td>" + result[i].message + "</td></tr>"
								+ "<tr><td> by : " + result[i].senderId
								+ " on " + time + "</td>" + "</tr>" + "</td>"
								+ "</tr>";
						if (i == 0) {
							$("#inbox").html(oo);
						} else {
							$("#inbox").append(oo);
						}
					}
				},
				error : function(e) {
					alert("error" + e.toSource());
				}
			});
		}

		function getMyNextMessages() {
			$.ajax({
				type : "POST",
				url : "getMyMessages.html",
				dataType : "json",
				data : "maxVal=" + $("#maxVal").val(),
				success : function(result) {
					for ( var i = 0; i < result.length; i++) {
						if (i == 0) {
							$("#maxVal").val(result[i].id);
						} else if (i == result.length - 1) {
							$("#minVal").val(result[i].id);
						}
						var time = "";
						time = result[i].time;
						var oo = "<tr bgcolor='gray' border='2' >" + "<td>"
								+ "<tr><td>" + result[i].message + "</td></tr>"
								+ "<tr><td> by : " + result[i].senderId
								+ " on " + time + "</td>" + "</tr>" + "</td>"
								+ "</tr>";

						if (i == 0) {
							$("#inbox").html(oo);
						} else {
							$("#inbox").append(oo);
						}
					}
				},
				error : function(e) {
					alert("error" + e.toSource());
				}
			});
		}

		function getAllUsers() {

			$.ajax({
				type : "POST",
				url : "getAllUsers.html",
				dataType : "json",
				success : function(result) {
					if (result.length == 0) {
						$("#users").html("No Users Logged In");
						}
					for ( var i = 0; i < result.length; i++) {
						var oo = "<option name='"+result[i].userid+"'>"
								+ result[i].userid
						"</option>";
						if (i == 0) {
							$("#users").html(oo);
						} else {
							$("#users").append(oo);
						}
					}
				},
				error : function(e) {
					alert("error" + e.toSource());
				}
			});
		}
	</script>

	<script>
		$("#example-basic").treetable({
			expandable : true
		});

		$("#example-basic-static").treetable();

		$("#example-basic-expandable").treetable({
			expandable : true
		});

		$(".example-advanced").treetable({
			expandable : true
		});

		// Highlight selected row
		$(".example-advanced tbody").on("mousedown", "tr", function() {
			$(".selected").not(this).removeClass("selected");
			$(this).toggleClass("selected");
		});

		
		$(".example-advanced .folder").each(
				function() {
					$(this).parents(".example-advanced tr").droppable(
							{
								accept : ".file, .folder",
								drop : function(e, ui) {
									var droppedEl = ui.draggable.parents("tr");
									$(".example-advanced").treetable("move",
											droppedEl.data("ttId"),
											$(this).data("ttId"));
								},
								hoverClass : "accept",
								over : function(e, ui) {
									var droppedEl = ui.draggable.parents("tr");
									if (this != droppedEl[0]
											&& !$(this).is(".expanded")) {
										$(".example-advanced").treetable(
												"expandNode",
												$(this).data("ttId"));
									}
								
								}});
				});

		$("form#reveal").submit(function() {
			var nodeId = $("#revealNodeId").val();

			try {
				$(".example-advanced").treetable("reveal", nodeId);
			} catch (error) {
				alert(error.message);
			}

			return false;
		}); 
	</script>
</body>
</html>
