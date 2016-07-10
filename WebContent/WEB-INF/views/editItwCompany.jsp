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

<title>IT Work Book</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
</link>

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

			<form:form method="POST" action="updateItwCompany.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Edit Company</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
<div class="span2" style="margin-left:0">
						<div class="thumbnail "
							style="margin-right: 10px;">
							<img src="${filepath}" alt="Upload Image" /></td>
						</div>
					</div>


					</tr>
					<tr>
						<td align="left">Id</td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwCompany.id}"
								readonly="true" style=" height: 15px"  /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left">Name</td>
						<td>&nbsp;</td>
						<td><form:input path="name"
								value="${itwCompany.name}" placeholder="Enter name" /></td>
						<td><form:errors path="name" cssClass="error" /></td>



					</tr>


					<tr>
						<td align="left">Description</td>
						<td>&nbsp;</td>
						<td><form:input path="description"
								value="${itwCompany.description}"
								placeholder="Enter description" /></td>
						<td><form:errors path="description" cssClass="error" /></td>
					</tr>


					<tr>
						<td align="left">Contact</td>
						<td>&nbsp;</td>
						<td><form:input path="contact" value="${itwCompany.contact}"
								placeholder="Enter Contact" /></td>
						<td><form:errors path="contact" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left">Phone</td>
						<td>&nbsp;</td>
						<td><form:input path="phone" value="${itwCompany.phone}"
								placeholder="Enter Phone" /></td>
						<td><form:errors path="phone" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left">Email ID</td>
						<td>&nbsp;</td>
						<td><form:input path="email" value="${itwCompany.email}"
								placeholder="Enter Email ID" /></td>
						<td><form:errors path="email" cssClass="error" /></td>
					</tr>
<tr>
						<td align="left">Website</td>
						<td>&nbsp;&nbsp;</td>
						<td><form:input path="website" value="${itwCompany.website}"
								placeholder="Enter website" /></td>
								
						<td><form:errors path="website" cssClass="error" /></td>


					</tr>
					<tr>
						<td align="left">Registration Number</td>
						<td>&nbsp;&nbsp;</td>
						<td><form:input path="registrationNumber" value="${itwCompany.registrationNumber}"
								placeholder="Enter registrationNumber" /></td>
							
						<td><form:errors path="registrationNumber" cssClass="error" /></td>
					</tr>
					
					<tr>
						<td align="left">Company Logo</td>
						<td>&nbsp;&nbsp;</td>
						<td><input type="file" name="file" id="file" style=" height: auto"  ></input></td>
					</tr>
					
				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value="Update Company" />
				<a class="btn btn-primary btn-small" href="companyConfigList.html">Back</a>
				
			</form:form>
		</div>
	</div>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
.span2 {
	width: 86px;
}
</style>
</body>
</html>
