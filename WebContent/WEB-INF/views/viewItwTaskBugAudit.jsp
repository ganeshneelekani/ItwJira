
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

.css-treeview input                                             ~ ul {
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
#offlineUsers, #onlineUsers {
height:135px;
overflow:scroll;
}
#chatUsers {
height:260px;
overflow:scroll;
}

#offlineUsers a, #onlineUsers a, #chatUsers a {
font-size:11px;
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
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
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
	font-family: Verdana, Arial, sans-serif;
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
</head>
<body>
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Verdana', Arial, sans-serif; font-size: 11px;">

			<div>

				<p
					style="font-family: 'Verdana', Arial, sans-serif; font-size: 14pt; font-weight: bold; margin-bottom: 20px;">
					View Issue History ID ${itwTaskBugAudit.id} <a class="btn btn-primary btn-small"
						href="issueHistoryListPage.html?id=${itwTaskBugAudit.taskId}" style="color: #fff; float: right">Issue History 
						List</a>
				</p>


			</div>
			<div id="tabs">
				<ul>
					<li style="line-height: 15px;"><a href="#Issue-Details">Issue 
							Details</a></li>
					<li style="line-height: 15px;"><a href="#Attach-Documents">Documents</a></li>
					<li style="line-height: 15px;"><a href="#BugChat">Bug Chat</a></li>
					<li style="line-height: 15px;"><a href="#ObjectBrowser">Object
							Browser</a></li>
					<li style="line-height: 15px;"><a href="#Capture-Image">Image/Video</a></li>

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

								<h5>Issue Details</h5>
								<tr>
									<td colspan="2"><form:errors path="*" cssClass="error"
											style="font-family: 'Verdana', Arial, sans-serif; font-size: 11px; " />
									</td>
									<td><form:hidden path="id" /></td>
								</tr>


								<tr>
									<td>Shortname</td>

									<td><form:input path="shortname" readonly="true"
											value="${itwTaskBugAudit.shortname}" placeholder="Enter Shortname" /></td>

									<td>Type</td>

									<td><form:select path="type1" disabled="true"
											value="${itwTaskBugAudit.type1}">
											<c:set var="type1">${itwTaskBugAudit.type1}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwType1List}"
												var="itwType1list">
												<c:choose>
													<c:when test="${itwType1list.id == type1}">
														<option value="${itwType1list.id}" selected>${itwType1list.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwType1list.id}">${itwType1list.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>

									<td>Efforts Estimated</td>

									<td><form:input path="efforts" readonly="true"
											value="${itwTaskBugAudit.efforts}" placeholder="Enter efforts" /></td>
								</tr>
								<tr>
									<td>Severity</td>

									<td><form:select path="severityid" multiple="false"
											disabled="true" value="${itwTaskBugAudit.severityid}">
											<c:set var="severityidtemp">${itwTaskBugAudit.severityid}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwSeverityIdList}"
												var="itwseverityidlist">
												<c:choose>
													<c:when test="${itwseverityidlist.id == severityidtemp}">
														<option value="${itwseverityidlist.id}" selected>${itwseverityidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwseverityidlist.id}">${itwseverityidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>


									<td>Priority</td>

									<td><form:select path="priorityid" disabled="true"
											value="${itwTaskBugAudit.priorityid}">
											<c:set var="priorityidtemp">${itwTaskBugAudit.priorityid}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwPriorityidList}"
												var="itwPriorityidlist">
												<c:choose>
													<c:when test="${itwPriorityidlist.id == priorityidtemp}">
														<option value="${itwPriorityidlist.id}" selected>${itwPriorityidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwPriorityidlist.id}">${itwPriorityidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>


									<td>Dead Line</td>

									<td><form:input path="deadlinedisplay" readonly="true"
											class="listgrid-criteria-input datepicker" type="text"
											value="${itwTaskBugAudit.deadlinedisplay}"
											placeholder="Enter Deadline" /></td>
								</tr>
								<tr>
									<td>Platform</td>

									<td><form:select path="platformid" disabled="true"
											value="${itwTaskBugAudit.platformid}">
											<c:set var="platformidtemp">${itwTaskBugAudit.platformid}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwPlatformidList}"
												var="itwPlatformidlist">
												<c:choose>
													<c:when test="${itwPlatformidlist.id == platformidtemp}">
														<option value="${itwPlatformidlist.id}" selected>${itwPlatformidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwPlatformidlist.id}">${itwPlatformidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>


									<!--  <td>Module</td>

									<td><form:select path="moduleid" disabled="true"
											value="${itwTaskBug.moduleid}">
											<c:set var="moduleidtemp">${itwTaskBug.moduleid}</c:set>
											<c:forEach items="${itwTaskBug.itwModuleidList}"
												var="itwModuleidlist">
												<c:choose>
													<c:when test="${itwModuleidlist.id == moduleidtemp}">
														<option value="${itwModuleidlist.id}" selected>${itwModuleidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwModuleidlist.id}">${itwModuleidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
 -->

									<td>Project</td>

									<td><form:select path="projectid" disabled="true"
											value="${itwTaskBugAudit.projectid}">
											<c:set var="projectiddisplaytemp">${itwTaskBugAudit.projectid}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwProjectidList}"
												var="itwProjectidlist">
												<c:choose>
													<c:when
														test="${itwProjectidlist.id == projectiddisplaytemp}">
														<option value="${itwProjectidlist.id}" selected>${itwProjectidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwProjectidlist.id}">${itwProjectidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>

									<td>Status</td>

									<td><form:select path="statusid" disabled="true"
											value="${itwTaskBugAudit.statusid}">
											<c:set var="statusiddisplaytemp">${itwTaskBugAudit.statusid}</c:set>
											<c:forEach items="${itwTaskBugAudit.itwStatusidList}"
												var="itwStatusidlist">
												<c:choose>
													<c:when test="${itwStatusidlist.id == statusiddisplaytemp}">
														<option value="${itwStatusidlist.id}" selected>${itwStatusidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwStatusidlist.id}">${itwStatusidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>

								</tr>
								<tr>


									<td>Assignee</td>

									<td colspan="2"><form:input path="assigneeiddisplay"
											readonly="true" value="${itwTaskBugAudit.assigneeiddisplay}" /></td>

								</tr>

								<tr>
									<td valign="top">Resolution</td>
									<td colspan="9"><textarea cols="99" rows="4"
											name="resolution" readonly="true" id="resolution"
											style="width: 92%"><c:out value="${itwTaskBugAudit.resolution}" /></textarea></td>

								</tr>
								<tr>

									<td valign="top">Summary</td>
									<td colspan="9"><textarea text-align="left" cols="99"
											rows="4" name="summary" readonly="true" id="summary"
											style="width: 92%"><c:out value="${itwTaskBugAudit.summary}" /></textarea></td>
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
				<div id="BugChat">
					<p>
					<div class="container-fluid">
						<table width="100%">
							<tr>
								
								<td>
									<div class="chatcontent"
										style="margin: 0px 20px; background: #fff; border-radius: 12px;">

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
						<c:forEach items="${itwTaskBugAudit.itwModuleList}" var="itwModule">
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

			</div>
			<!-- -------------End of tabs div------------- -->

		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			initSessionMonitor();
			getMyMessages();
			setInterval(function() {
				getMyMessages()
			}, 5000);
					});

		
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

		

		function newPopup(url) {
			popupWindow = window
					.open(
							url,
							'popUpWindow',
							'height=100,width=100,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=yes')
		}

	
	

		function getMyMessages() {
			var issueId = ${itwTaskBugAudit.taskId};
			$.ajax({
						
						type : "POST",
						url : "getAllMessagesForId.html" + "?id=" + issueId,
						dataType : "json",
						success : function(result) {
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
											+ " Sender : "
											+ result[i].senderId
											+ "</th>"
											+ "</tr>"
											+ "<tr>"
											+ "<th align='left'>"
											+ " Reciever : "
											+ result[i].receiverId
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
									if (i == 0) {
										$("#inbox").html(oo);
									} else {
										$("#inbox").append(oo);
									}
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

									if (i == 0) {
										$("#inbox").html(oo);
									} else {
										$("#inbox").append(oo);
									}
								}
							}
							setNiceScrollPosition();
							var objDiv = document.getElementById("chatmsg");
							objDiv.scrollTop = objDiv.scrollHeight;
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

		/* Drag & Drop Example Code
		$("#example-advanced .file, #example-advanced .folder").draggable({
			helper : "clone",
			opacity : .75,
			refreshPositions : true, // Performance?
			revert : "invalid",
			revertDuration : 300,
			scroll : true
		});*/

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
								}
							});
				});

		$("form#reveal").submit(function() {
			var nodeId = $("#revealNodeId").val()

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
