<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
	
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="updateItwReleases.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Edit Releases</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

					<tr>
						<td align="left">Id</td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwReleases.id}"
								placeholder="Enter ID" readonly="true" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left">Release Name</td>
						<td>&nbsp;</td>
						<td><form:input path="shortname"
								value="${itwReleases.shortname}" placeholder="Enter Release Name" /></td>
						<td><form:errors path="shortname" cssClass="error" /></td>
						
						
					</tr>
					
					<tr>
						<td align="left"><spring:message
								code="release.releasedescription" text="Release Description" /></td>
						<td>&nbsp;</td>
					   <td><textarea cols="99" rows="4" name="releasedescription"	id="releasedescription" style="width: 92%"><c:out value="${itwReleases.releasedescription}"/></textarea></td>
						<td><form:errors path="releasedescription" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left"><spring:message code="release.Date"
								text="Release Date" /></td>
						<td>&nbsp;</td>
						<td><form:input path="releasedate"
								value="${itwReleases.releasedate}"
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="MM/dd/yyyy" /></td>
						<i class="icon-calendar"></i>
						<td><form:errors path="releasedate" cssClass="error" /></td>

					</tr>
					
					
					<tr>
						<td align="left">Active</td>
						<td>&nbsp;</td>
						
						<c:if test="${itwReleases.active  == 'Yes' }">

						<td> <form:select name="active" path="active"
								style="width:100px">
								
								
								<form:option selected="" value="${itwReleases.active}"></form:option>

								<form:option value="No">No </form:option>

                               

						</form:select></td>
						 </c:if> 
						 <c:if test="${itwReleases.active  == 'No' }">

						<td> <form:select name="active" path="active"
								style="width:100px">
								
								
								<form:option selected="" value="${itwReleases.active}"></form:option>

								<form:option value="Yes">Yes</form:option>

                               

						</form:select></td>
						 </c:if> 
					</tr>
				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Update Releases" />
				<a class="btn btn-primary btn-small" href="itwReleasesConfigList.html">Back</a>
				
			</form:form>
		</div>
	</div>

</body>
</html>
