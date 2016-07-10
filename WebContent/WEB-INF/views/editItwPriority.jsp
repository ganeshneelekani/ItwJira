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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta shortname="viewport"
	content="width=device-width, initial-scale=1.0" />
<meta shortname="colorcode" content="" />

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
			style="font-family:  'Helvetica' Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="updateItwPriority.html">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Edit Priority</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
					
					<tr>
						<td align="left" valign="top">Id</td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwPriority.id}"
								readonly="true" style=" height: 15px" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top">Priority</td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwPriority.shortname}" placeholder="Enter Priority" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left" valign="top">Color Code</td>
						<td>&nbsp;</td>
						<td><form:input path="colorcode"
								value="${itwPriority.colorcode}" placeholder="Enter colorcode" /></td>
						<td><form:errors path="colorcode" cssClass="error" /></td>
					</tr>
				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Update Priority" />
				<a class="btn btn-primary btn-small"
					href="itwPriorityConfigList.html">Back</a>

			</form:form>
		</div>
	</div>

</body>
</html>
