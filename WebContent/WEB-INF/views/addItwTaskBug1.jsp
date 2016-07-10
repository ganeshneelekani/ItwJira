<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
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
</head>
<body>
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<!-- {!end layout} -->

	<div class="container-fluid">
		<div class="hero-unit span14">

			<form:form method="POST" action="saveItwTaskBug1.html?projectId=${projectId}" >

				<!--  <div style="float: right; padding-right: 60px">
					<input type="submit" class="btn btn-success btn-small"
						value="Save Issue" /> 
					 	<a class="btn btn-danger btn-small"
						href="addItwTaskBug1.html">Reset</a>  <a
						class="btn btn-primary btn-small" href="issueList1.html?projectId=${projectId}">Back</a>
				</div> -->
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Create New Issue</p>

				<style>
.createIssue input,select,textarea {
	margin: 0 40px 0 0px;
}
</style>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt; width:86%"
					class="table table-striped" cellpadding="4">
					<tr>
						<td colspan="2" style="background: transparent; border-top:0"><form:errors path="*" cssClass="error"
								style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; " />
						</td>
					</tr>

					<tr>
						<td>Short Description</td>
						<td><form:input path="shortname"
								value="${itwTaskBug.shortname}" placeholder="Enter Shortname" /></td>
						<td>Type</td>
						<td><form:select path="type1">
								<form:option value="0" label="Select Type" />
								<c:set var="type1">${itwTaskBug1.type1}</c:set>
								<c:forEach items="${itwTaskBug1.itwType1List}"
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
						<td>Status</td>
						<td colspan="2"><form:select path="statusid"
								value="${itwTaskBug.statusid}">
								
								<c:set var="statusiddisplaytemp">${itwTaskBug1.statusid}</c:set>
								<c:forEach items="${itwTaskBug1.itwStatusidList}"
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
						<td>Severity</td>
						<td><form:select path="severityid" multiple="false"
								value="${itwTaskBug.severityid}">
								
								<c:set var="severityidtemp">${itwTaskBug1.severityidtemp}</c:set>
								<c:forEach items="${itwTaskBug1.itwSeverityIdList}"
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
						<td><form:select path="priorityid" multiple="false"
								value="${itwTaskBug.priorityid}">
								
								<c:set var="priorityidtemp">${itwTaskBug1.priorityidtemp}</c:set>
								<c:forEach items="${itwTaskBug1.itwPriorityidList}"
									var="itwpriorityidlist">
									<c:choose>
										<c:when test="${itwpriorityidlist.id == priorityidtemp}">
											<option value="${itwpriorityidlist.id}" selected>${itwpriorityidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwpriorityidlist.id}">${itwpriorityidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
						<!--  	<td>Deadline</td>
						<td><form:input path="deadlinedisplay" value=""
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="MM/DD/YYYY" /></td> -->


						<td>Release</td>
						<td colspan="2"><form:select path="releaseid" multiple="false"
								value="${itwTaskBug.releaseid}">
								
								<c:set var="releaseidtemp">${itwTaskBug1.releaseidtemp}</c:set>
								<c:forEach items="${itwTaskBug1.itwReleasesidList}"
									var="itwreleaseidlist">
									<c:choose>
										<c:when test="${itwreleaseidlist.id == releaseidtemp}">
											<option value="${itwreleaseidlist.id}" selected>${itwreleaseidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwreleaseidlist.id}">${itwreleaseidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>		

					</tr>

					<tr>
						<td>Platform</td>
						<td><form:select path="platformid" multiple="false"
								value="${itwTaskBug.platformid}">
								
								<c:set var="platformidtemp">${itwTaskBug1.platformidtemp}</c:set>
								<c:forEach items="${itwTaskBug1.itwPlatformidList}"
									var="itwplatformidlist">
									<c:choose>
										<c:when test="${itwplatformidlist.id == platformidtemp}">
											<option value="${itwplatformidlist.id}" selected>${itwplatformidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwplatformidlist.id}">${itwplatformidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
						<!-- <td>Module</td>

						<td><form:select path="moduleid"
								value="${itwTaskBug.moduleid}">
								<form:option value="0" label="Select Module" />
								<c:set var="moduleidtemp">${itwTaskBug1.moduleid}</c:set>
								<c:forEach items="${itwTaskBug1.itwModuleidList}"
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
						<td><form:select path="projectid"
								value="${projectId}">
								
								<c:set var="projectiddisplaytemp">${projectId}</c:set>
								<c:forEach items="${itwTaskBug1.itwProjectidList}"
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
							
						<td>Depends On</td>
							<td><form:select name="dependsOn" path="dependsOn">
								<form:option value="0" label="Select Depend" />
								<c:forEach items="${itwTaskBugDependOn}" var="itwTaskBug">

									<form:option value="${itwTaskBug.id}" />
									<c:out value="${itwTaskBug.id}" />

								</c:forEach>
							</form:select>
						<td><form:errors path="dependsOn" cssClass="error" /></td>
					</tr>

					<tr>
						<td>Assign To</td>
						<td><form:select path="assigneeid"
								value="${itwTaskBug.assigneeid}">
							<!--  	<form:option value="0" label="Select Assignee" /> -->
								<c:set var="assigneeidtemp">${itwTaskBug1.assigneeid}</c:set>
								<c:forEach items="${itwTaskBug1.itwAssigneeidList}"
									var="itwAssigneeidlist">
									<c:choose>
										<c:when test="${itwAssigneeidlist.id == assigneeidtemp}">
											<option value="${itwAssigneeidlist.id}" selected>${itwAssigneeidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwAssigneeidlist.id}">${itwAssigneeidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>

						<td>Stages</td>
						<td colspan="4"><form:select path="stagesId"
								value="${itwTaskBug.stagesId}">
								
								<c:set var="stagetemp">${itwTaskBug1.stagesId}</c:set>
								<c:forEach items="${itwTaskBug1.itwStagesidList}"
									var="itwStagesidlist">
									<c:choose>
										<c:when test="${itwStagesidlist.id == stageidtemp}">
											<option value="${itwStagesidlist.id}" selected>${itwStagesidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwStagesidlist.id}">${itwStagesidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>							

					</tr>
					<tr>
					
					 <td>Version</td>
									<td><form:input path="version" 
											value="${itwTaskBug.version}" placeholder="version" /></td>
					<td>URL</td>
						<td><form:input path="url"
								value="${itwTaskBug.url}" placeholder="Enter URL" /></td>

				</tr>
					<!--	<tr>
						<td valign="top">Resolution</td>
						<td colspan="9"><form:textarea cols="99" rows="4"
								path="resolution" value="${itwTaskBug.resolution}"
								style="width: 92%" /></td>
					</tr> -->
					<!-- 
					<tr>
					<td>Approved By</td>
					<td><form:select style="width:100px" path="approvedBy"><option>Username</option>
								
								<c:forEach items="${ItwUserBean}"
												var="itwUserBean"  >
									<form:option value="${itwUserBean.id}" >${itwUserBean.userid}</form:option>
											</c:forEach>
								
								</form:select> </td>
								
					</tr>
					 -->
					<tr>
						<td valign="top">Long Description</td>
						<td colspan="9"><form:textarea cols="99" rows="4"
								path="summary" value="${itwTaskBug.summary}" style="width: 92%" /></td>
					</tr>
					
					
					
				</table>
				<div style="float: right; padding-right: 170px">
					<input type="submit" class="btn btn-success btn-small"
						value="Save Issue" /> 
					<!--  	<a class="btn btn-danger btn-small"
						href="addItwTaskBug1">Reset</a> --> <a
						class="btn btn-primary btn-small" href="issueList1.html?projectId=${projectId}">Back</a>
				</div>
			</form:form>
		</div>
	</div>
<style>
	select, textarea, input[type="text"]
{
margin-bottom:0;
}
</style>

</body>
</html>
