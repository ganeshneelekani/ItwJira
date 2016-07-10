
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
		<div class="hero-unit span12"
			style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="updateItwStagesTypes.html"
				enctype="multipart/form-data">
			
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="content.EditStagesTypes" text="default text" />
				</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">






					<tr>
						<td align="left" valign="top">Id</td>
						<td>&nbsp</td>
						<td><form:input path="id" value="${itwStagesTypes.id}"
								placeholder="Enter ID" readonly="true" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top"><spring:message code="content.Stages"
								text="default text" /></td>
						<td>&nbsp</td>
						<td><form:input path="shortname"
								value="${itwStagesTypes.shortname}"
								placeholder="Enter StagesType" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>


					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StagesdefaultValue" text="Default Stage" /></td>
						<td>&nbsp;</td>
						<td><form:select NAME="defaultvalue" path="defaultvalue">

								<c:choose>
									<c:when test="${itwStagesTypes.defaultvalue == 'N'}">
										<option value="${itwStagesTypes.defaultvalue}" selected>${itwStagesTypes.defaultvalue}</option>
										<OPTION VALUE="Y">Y</OPTION>
									</c:when>
									<c:otherwise>
										<option value="${itwStagesTypes.defaultvalue}" selected>${itwStagesTypes.defaultvalue}</option>
										<OPTION VALUE="N">N</OPTION>

									</c:otherwise>
								</c:choose>


							</form:select></td>

						<td><form:errors path="defaultvalue" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="content.StagesPreceding" text="Preceding Stage" /></td>
						<td>&nbsp</td>

						<td><div id="ScrollCB"
								style="height: 200px; width: 300px; overflow: scroll">



								<c:forEach items="${itwpreceding}" var="itwstages">


									<input type="checkbox" id="${itwstages}" name="precedingname"
										value="${itwstages}" checked="checked">
									<c:out value="${itwstages}" />
									<br>


								</c:forEach>
								<c:forEach items="${tempitwStagesTypes}" var="itwstages">


									<input type="checkbox" id="${itwstages}" name="precedingname"
										value="${itwstages}">
									<c:out value="${itwstages}" />
									<br>


								</c:forEach>
								<c:forEach items="${newpreceding}" var="itwstages">


									<input type="checkbox" id="${itwstages.shortname}"
										name="precedingname" value="${itwstages.shortname}">
									<c:out value="${itwstages.shortname}" />
									<br>


								</c:forEach>
								<c:forEach items="${rootStages}" var="itwstages">


									<input type="checkbox" id="${itwstages.shortname}"
										name="precedingname" value="${itwstages.shortname}">
									<c:out value="${itwstages.shortname}" />
									<br>


								</c:forEach>
							</div>
						<td><form:errors path="precedingname" cssClass="error" /></td>
					</tr>




				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value=<spring:message
					code="content.UpdateStagesType" text="default text" /> />
				<a class="btn btn-primary btn-small"
					href="itwStagesTypesConfigList.html?id=${itwStagesTypes.id}"><spring:message
						code="content.Back" text="default text" /></a>

			</form:form>
		</div>
	</div>

</body>
</html>
