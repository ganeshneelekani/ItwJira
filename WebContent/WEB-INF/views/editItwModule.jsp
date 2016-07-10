
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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

			<form:form method="POST" action="updateItwModule.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="Edit Module" text="Edit Module" />
				</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

					<tr>
						<td align="left" valign="top">Id</td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwModule.id}"
								placeholder="Enter ID" readonly="true" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top"><spring:message code="content.Module"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwModule.shortname}" placeholder="Enter Module" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>
						<form:input path="langType" value="${pageContext.response.locale}"
							type="hidden" />
						<form:input path="langid" value="${itwModule.langid}"
							type="hidden" />

					</tr>

				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value=<spring:message code="content.UpdateModule" text="default text" /> />
				<a class="btn btn-primary btn-small"
					href="itwModuleConfigList.html?id=${itwModule.id}"><spring:message
						code="content.Back" text="default text" /></a>
			</form:form>
		</div>
	</div>

</body>
</html>
