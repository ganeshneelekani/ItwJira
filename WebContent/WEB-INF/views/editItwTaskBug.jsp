<%@ page contentType="text/html"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT Workbook | View Bug</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!--[if lte IE 7]>
<style>
.content { margin-right: -1px; } /* this 1px negative margin can be placed on any of the columns in this layout with the same corrective effect. */
ul.nav a { zoom: 1; }  /* the zoom property gives IE the hasLayout trigger it needs to correct extra whiltespace between the links */
</style>
<![endif]-->
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
</head>
<style>
.headerlist {
	margin-top: 7px;
}

.headerlist ul li {
	line-height: 0.8;
}

.page th {
	font-size: 14px;
}
</style>
<body>

	<div class="container">
		<div class="header">
			<a href="index.html"><img src="images/logo.png" alt="IT workbook"
				name="IT workbook" id="IT workbook" /></a>
			<div class="headerlist">
				<ul>
					<li><a href="issueList.html">Issue List</a></li>
					<li><a href="doSignOut.html">Logout</a></li>
				</ul>
			</div>
		</div>
		<form:form method="POST" action="updateItwTaskBug.html"
			enctype="multipart/form-data">


			<div class="content" style="width: 87.5%; height: 600px">
				<div class="pageborderleft"></div>
				<div class="page">
					<div class="pageheader">
						Edit Bug
						<div
							style="position: absolute; margin-top: -56px; margin-left: 152px;">
							<img src="images/bug.png" />
						</div>
					</div>
					<div class="pagecontent">
						<p>
							<form:errors path="*" cssClass="error" />
							<table width="94%%" cellpadding="10">
								<tr>
									<th style="font-size: 18px" align="left">Bug Id</th>
									<th colspan="8" style="font-size: 18px" align="left"><span
										path="id" readonly="true">${itwTaskBug.id}</span></th>
								</tr>

								<tr>
									<form:hidden path="id" />
									<td>Shortname</td>
									<td>:</td>
									<td><form:input path="shortname"
											value="${itwTaskBug.shortname}" placeholder="Enter Shortname" /></td>
									<td>Type</td>
									<td>:</td>
									<td><form:select path="type1">
											<c:set var="type1">${itwTaskBug.type1}</c:set>
											<c:forEach items="${itwTaskBug.itwType1List}"
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
									<td>:</td>
									<td><form:input path="efforts"
											value="${itwTaskBug.efforts}" placeholder="Enter efforts" /></td>
								</tr>
								<tr>
									<td>Severity</td>
									<td>:</td>
									<td><form:select path="severityid" multiple="false">
											<c:set var="severityidtemp">${itwTaskBug.severityid}</c:set>
											<c:forEach items="${itwTaskBug.itwSeverityIdList}"
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
									<td>:</td>
									<td><form:select path="priorityid">
											<c:set var="priorityidtemp">${itwTaskBug.priorityid}</c:set>
											<c:forEach items="${itwTaskBug.itwPriorityidList}"
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
									<td>:</td>
									<td><form:input path="deadlinedisplay" value=""
											class="validate[required] text-input datepicker" type="date" /></td>
								</tr>
								<tr>
									<td>Platform</td>
									<td>:</td>
									<td><form:select path="platformid">
											<c:set var="platformidtemp">${itwTaskBug.platformid}</c:set>
											<c:forEach items="${itwTaskBug.itwPlatformidList}"
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

									<td>View Attachment</td>
									<td>:</td>

									<td width="20px"><a
										href="${pageContext.request.contextPath}/download/${itwTaskBug.name}.html?id=${itwTaskBug.id}"><img
											src="${pageContext.request.contextPath}/images/save_icon.gif"
											border="0" title="Download this document" /></a></td>
									<td>Update Attachment</td>
									<td>:</td>
									<td><input type="file" name="file" id="file"></input></td>
								</tr>
								<tr>
									<td>Status</td>
									<td>:</td>
									<td><form:select path="statusiddisplay">
											<c:set var="statusiddisplaytemp">${itwTaskBug.statusiddisplay}</c:set>
											<c:forEach items="${itwTaskBug.itwStatusidList}"
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
									<td>Module</td>
									<td>:</td>
									<td><form:select path="moduleiddisplay" value="Module 1"
											style="width: 142px">
											<form:option value="1" label="Module1" />
										</form:select></td>
								</tr>
								<tr>
									<td>Project</td>
									<td>:</td>
									<td><form:select path="projectiddisplay">
											<c:set var="projectiddisplaytemp">${itwTaskBug.projectiddisplay}</c:set>
											<c:forEach items="${itwTaskBug.itwProjectidList}"
												var="itwProjectidlist">
												<c:choose>
													<c:when test="${itwProjectidlist.id == projectiddisplaytemp}">
														<option value="${itwProjectidlist.id}" selected>${itwProjectidlist.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${itwProjectidlist.id}">${itwProjectidlist.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td>Assignee</td>
									<td>:</td>
									<td colspan="2"><form:input path="assigneeiddisplay"
											value="${itwTaskBug.assigneeiddisplay}"
											cssStyle=" width : 268px;" /></td>
								</tr>
								<tr>
									<td valign="top">Resolution</td>
									<td valign="top">:</td>
									<td colspan="7"><textarea name="resolution"
											name="resolution" cols="99" rows="6">${itwTaskBug.resolution}</textarea>
									</td>
								</tr>
								<tr>
									<td valign="top">Summary</td>
									<td valign="top">:</td>
									<td colspan="7"><textarea name="summary" name="summary"
											cols="99" rows="6">${itwTaskBug.summary}</textarea></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td align="right" colspan="7"><a href="issueList.html"><input
											type="button" value="Back" class="save" onclick="" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<input
										type="submit" value="Save" class="save" /></td>
								</tr>
							</table>

						</p>
					</div>
				</div>
				<div class="pageborderright" style="top: -514px;"></div>
			</div>
		</form:form>
	</div>
	<ul id="navigationMenu">
		<li><a class="comment" href="displayConfigPage.html"><span>Configuration</span></a></li>
		<li><a class="contact" href="mainPage.html"><span>Tweets</span></a></li>
	</ul>
</body>
</html>
