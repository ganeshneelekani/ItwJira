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

			<form:form method="POST" action="updateItwReleaseProcess.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message
								code="ReleaseProcess.Description" text="Enter Description" /></p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
                    

					
					<tr>
						<td align="left"><spring:message
								code="ReleaseProcess.Id" text="Enter Description" /></td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwReleaseProcess.id}"
								readonly="true" style=" height: 15px"  /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					
					<tr>
						<td align="left"><spring:message
								code="ReleaseProcess.step" text="Enter Description" /></td>
						<td>&nbsp;</td>
						<td><form:input path="step"
								value="${itwReleaseProcess.step}"
								placeholder="Enter step" /></td>
						<td><form:errors path="step" cssClass="error" /></td>
					</tr>


				<!-- 	<tr>
						<td align="left">Description</td>
						<td>&nbsp;</td>
						<td><form:input path="description"
								value="${itwReleaseProcess.description}"
								placeholder="Enter description" /></td>
						<td><form:errors path="description" cssClass="error" /></td>
					</tr>-->
	<tr>
						<td align="left"><spring:message
								code="ReleaseProcess.Description" text="Enter Description" /></td>
						<td>&nbsp;</td>	<td><textarea cols="99" rows="4" name="description"
								id="description" style="width: 92%"><c:out value="${itwReleaseProcess.description}" /></textarea></td>
						<td><form:errors path="description" cssClass="error" /></td>


					</tr>

				<tr>
				<td align="left"><spring:message
								code="ReleaseProcess.Attchment" text="Enter Description" /></td>
						<td>&nbsp;</td>
                    <td colspan="4">
							
							<img src="<c:out value="${filepath}"/>"   alt="Uploadj Image"  style="max-width:522px;" /></td>
							
				</tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseProcess.Logo" text="Enter Description" /></td>
						<td>&nbsp;&nbsp;</td>
						<td><input type="file" name="file" id="file" style=" height: auto"  ></input></td>
					</tr>
					
				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Update Itw Release Process" />
				<a class="btn btn-primary btn-small" href="itwReleaseProcessConfigList.html"><spring:message
						code="content.Back" text="default text" /></a>
				
			</form:form>
		</div>
	</div>

</body>
</html>
