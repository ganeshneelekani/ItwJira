<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
</style>
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>


</head>
<body>
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="saveItwStatusTypes.html"
				enctype="multipart/form-data">

				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Create New StatusType</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

					<tr>
						<td align="left" valign="top">Status</td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwStatusTypes.shortname}"
								placeholder="Enter StatusTypes" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StatusdefaultValue" text="Default Status" /></td>
						<td>&nbsp;</td>
						<td><form:select NAME="defaultvalue" path="defaultvalue">
								<OPTION VALUE="Y">Y</OPTION>
								<OPTION VALUE="N">N</OPTION>

							</form:select></td>
						<td><form:errors path="defaultvalue" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StatusPrecedingName" text="Preceding Status" /></td>
						<td>&nbsp</td>



						<c:choose>
							<c:when test="${not empty preceding}">
								<td><form:select name="precedingname" path="precedingname">

										<c:forEach items="${preceding}" var="itwPreced">

											<form:option value="${itwPreced.shortname}" />
											<c:out value="${itwPreced.shortname}" />

										</c:forEach>
									</form:select></td>

								<br />
							</c:when>

							<c:otherwise>
								<td><form:input path="precedingId" value="-1"
										placeholder="Default value is set to -1"
										style="float:left; margin-right: 6px; width: 254px;"
										readonly="true" />Preceding Id is default set to -1</td>
								<td><form:errors path="precedingId" cssClass="error" /></td>
								<br />
							</c:otherwise>
						</c:choose>
					<tr>
						<td align="left" valign="top"><spring:message code="title.ChartStatus"
								text="Chart Status" /></td>
						<td>&nbsp;</td>
						<td><form:select name="statusname" path="statusname">

								<c:forEach items="${itwChartStatuslist}" var="itwChart">


									<form:option value="${itwChart.shortname}" />
									<c:out value="${itwChart.shortname}" />
									<br>

								</c:forEach>

							</form:select></td>
						<td><form:errors path="statusname" cssClass="error" /></td>
					</tr>


				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Save StatusType" />
				<a class="btn btn-danger btn-small" href="addItwStatusTypes.html">Reset</a>
				<a class="btn btn-primary btn-small"
					href="itwStatusTypesConfigList.html">Back</a>
			</form:form>
		</div>
	</div>
</body>
</html>
