<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="updateItwProject.html">


				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="content.EditProject" text="default text" />
				</p>

				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
					<tr>
						<td align="left" valign="top"><spring:message code="id.id"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwProject.id}"
								readonly="true" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top"><spring:message code="project.shortname"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwProject.shortname}"  /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>

					</tr>
					<tr>
					<tr>
						<td align="left" valign="top"><spring:message code="project.clientName"
								text="default text" /></td>
						<td></td>
						<td><form:select name="clientname" path="clientname">
								<form:option value="${itwProject.clientname}" />
								<c:out value="${itwProject.clientname}" />

								<c:forEach items="${itwClient}" var="itwUser">

									<form:option value="${itwUser.clientname}" />
									<c:out value="${itwUser.clientname}" />

								</c:forEach>
							</form:select></td>

						<td><form:errors path="clientname" cssClass="error" /></td>
					</tr>
					<tr>
						<td><form:input path="langId" value="${itwProject.langId}"
								type="hidden" /></td>

						<form:input path="langDesc" value="${pageContext.response.locale}"
							type="hidden" />
					<tr>
						<td align="left" valign="top"><spring:message code="project.startdate"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="startdate"
								value="${itwProject.startdate}"
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="MM/dd/yyyy" /></td>
						<i class="icon-calendar"></i>
						<td><form:errors path="startdate" cssClass="error" /></td>

					</tr>

					<tr>

						<td align="left" valign="top"><spring:message code="project.enddate"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="enddate" value="${itwProject.enddate}"
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="End Date" /></td>
						<i class="icon-calendar"></i>
						<td><form:errors path="enddate" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message code="content.Module"
								text="default text" /></td>
						<td></td>
						<td><div id="ScrollCB"
								style="height: 100px; width: 200px; overflow: scroll">

								<c:forEach items="${itwProjectModules}" var="itwModule">

									<input type="checkbox" id="${itwModule.modulename}"
										name="modulename" value="${itwModule.modulename}"
										checked="checked">
									<c:out value="${itwModule.modulename}" />

									<br>

								</c:forEach>

								<c:forEach items="${itwModule}" var="itwUser">

									<input type="checkbox" id="${itwUser.shortname}"
										name="modulename" value="${itwUser.shortname}">
									<c:out value="${itwUser.shortname}" />

									<br>

								</c:forEach>
							</div></td>
						<td><form:errors path="modulename" cssClass="error" /></td>
				</table>

				<tr>
					<td><br> <spring:message code="button.update"
							var="button_update" /> <input type="submit"
						class="btn btn-success btn-small" value="${button_update}" /> <a
						class="btn btn-primary btn-small"
						href="projectConfigList.html?langDesc=${pageContext.response.locale}">
							<spring:message code="button.back" text="default text" />
					</a>
			</form:form>
		</div>
	</div>

</body>
</html>
