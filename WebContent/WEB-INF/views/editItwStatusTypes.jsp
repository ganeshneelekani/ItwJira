<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

</head>
<body>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="updateItwStatusTypes.html"
				enctype="multipart/form-data">

				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Edit Status Types</p>

				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

					<tr>
						<td align="left" valign="top">Id</td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwStatusTypes.id}"
								placeholder="Enter ID" readonly="true" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top">Status</td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwStatusTypes.shortname}"
								placeholder="Enter StatusType" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StatusdefaultValue" text="Default Status" /></td>
						<td>&nbsp;</td>
						<td><form:select NAME="defaultvalue" path="defaultvalue">

								<c:choose>
									<c:when test="${itwStatusTypes.defaultvalue == 'N'}">
										<option value="${itwStatusTypes.defaultvalue}" selected>${itwStatusTypes.defaultvalue}</option>
										<OPTION VALUE="Y">Y</OPTION>
									</c:when>
									<c:otherwise>
										<option value="${itwStatusTypes.defaultvalue}" selected>${itwStatusTypes.defaultvalue}</option>
										<OPTION VALUE="N">N</OPTION>

									</c:otherwise>
								</c:choose>


							</form:select></td>

						<td><form:errors path="defaultvalue" cssClass="error" /></td>
					</tr>


					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StatusPreceding" text="Preceding Status" /></td>
						<td>&nbsp;</td>

						<td><div id="ScrollCB"
								style="height: 200px; width: 300px; overflow: scroll">



								<c:forEach items="${itwpreceding}" var="itwstatus">


									<input type="checkbox" id="${itwstatus}" name="precedingname"
										value="${itwstatus}" checked="checked">
									<c:out value="${itwstatus}" />
									<br>


								</c:forEach>
								<c:forEach items="${tempitwStatusTypes}" var="itwstatus">


									<input type="checkbox" id="${itwstatus}" name="precedingname"
										value="${itwstatus}">
									<c:out value="${itwstatus}" />
									<br>


								</c:forEach>

								<c:forEach items="${newpreceding}" var="itwstatus">


									<input type="checkbox" id="${itwstatus.shortname}"
										name="precedingname" value="${itwstatus.shortname}">
									<c:out value="${itwstatus.shortname}" />
									<br>


								</c:forEach>
								<c:forEach items="${rootStatus}" var="itwstatus">


									<input type="checkbox" id="${itwstatus.shortname}"
										name="precedingname" value="${itwstatus.shortname}">
									<c:out value="${itwstatus.shortname}" />
									<br>


								</c:forEach>

							</div>
						<td><form:errors path="precedingname" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.ChartStatus" text="Chart Status" /></td>
						<td>&nbsp;</td>
						<td><form:select NAME="statusname" path="statusname">
								<c:forEach items="${itwChartStatuslist}" var="itwstatus">


									<c:choose>
										<c:when test="${itwstatus.shortname == statusname}">
											<option value="${itwstatus.shortname}" selected>${itwstatus.shortname}</option>

										</c:when>
										<c:otherwise>
											<option value="${itwstatus.shortname}">${itwstatus.shortname}</option>


										</c:otherwise>
									</c:choose>
								</c:forEach>

							</form:select></td>

						<td><form:errors path="statusname" cssClass="error" /></td>
					</tr>

				</table>

				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Update StatusType" />
				<a class="btn btn-primary btn-small"
					href="itwStatusTypesConfigList.html">Back</a>
			</form:form>
		</div>
	</div>
</body>
</html>
