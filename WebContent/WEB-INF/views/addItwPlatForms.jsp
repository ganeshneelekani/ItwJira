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
	
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">

			<form:form method="POST" action="saveItwPlatForms.html"
				enctype="multipart/form-data">
				
				<p style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
				Create New Platform
				</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
					
					<tr>
						<td align="left" valign="top">Platform Name</td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwPlatForms.shortname}" placeholder="Enter Platform" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>

					</tr>


				
				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Save PlatForm" />
				<a class="btn btn-danger btn-small" href="addItwPlatForms.html">Reset</a>
				<a class="btn btn-primary btn-small" href="itwPlatFormsConfigList.html">Back</a>
			</form:form>
		</div>
	</div>

</body>
</html>
