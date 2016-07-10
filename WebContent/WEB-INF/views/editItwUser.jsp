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

	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<!-- {!end layout} -->
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
	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;">

			<form:form method="POST" action="updateItwUser.html"
				enctype="multipart/form-data">

				<form:errors path="*" cssClass="error"
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;" />
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Update User</p>


				<div class="span2" style="margin-left:0">
					<div class="thumbnail "
						style="margin-right: 10px;" align="left">
						<img src="${filepath}" alt="Upload Image" />

					</div>
				</div>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt; width: 66%"class="table table-striped">
					<tr>

						<td align="left">User ID</td>
						
						<td><form:input path="userid" value="${itwUser.userid}"
								readonly="true" /></td>
						
						<td align="left">ID</td>
						
						<td><form:input path="id" value="${itwUser.id}"
								readonly="true" /></td>

					</tr>
					<tr>
						<td align="left">First Name</td>
						
						<td><form:input path="firstname" value="${itwUser.firstname}"
								placeholder="Enter Firstname" /></td>
						
						<td align="left">Last Name</td>
						
						<td><form:input path="lastname" value="${itwUser.lastname}"
								placeholder="Enter Lastname" /></td>
					</tr>
					<tr>
						<td align="left">Email ID</td>
						
						<td><form:input path="emailid" value="${itwUser.emailid}"
								placeholder="Enter Email ID" /></td>
						
						<td align="left">User Status</td>
						
						<td width="10%"><c:if test="${itwUser.enabled  == 1}">
								<table width="100%">
									<tr>
										<td align="left" width="5%" style="border-top: 0">Enable</td>
										<td style="border-top: 0"><form:radiobutton path="enabled" checked="checked"
												value="1" /></td>


										<td align="left" width="5%" style="border-top: 0">Disable</td>
										<td style="border-top: 0"><form:radiobutton path="enabled" value="0" /></td>
									<tr>
								</table>
							</c:if> <c:if test="${itwUser.enabled  == 0}">
								<table width="100%">
									<tr>
										<td align="left" style="border-top: 0">Enable</td>
										<td style="border-top: 0"><form:radiobutton path="enabled" value="1" /></td>


										<td align="left" style="border-top: 0">Disable</td>
										<td style="border-top: 0"><form:radiobutton path="enabled" checked="checked"
												value="0" /></td>
									<tr>
								</table>
							</c:if></td>

					</tr>

					<tr>
						<td align="left">Gender</td>
						
						<td><form:select path="genderid">
								<c:set var="gendertemp">${itwUser.genderid}</c:set>
								<c:forEach items="${itwUser.genderList}" var="genderlist">
									<c:choose>
										<c:when test="${genderlist.id == gendertemp}">
											<option value="${genderlist.id}" selected>${genderlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${genderlist.id}">${genderlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
					
						<td align="left">Role ID</td>
						
						<td><form:select path="primaryroleid"
								value="${itwUser.primaryroleid}">
								<c:set var="primaryroleidtemp">${itwUser.primaryroleid}</c:set>
								<c:forEach items="${itwUser.itwPrimaryroleidList}"
									var="itwPrimaryroleidlist">
									<c:choose>
										<c:when test="${itwPrimaryroleidlist.id == primaryroleidtemp}">
											<option value="${itwPrimaryroleidlist.id}" selected>${itwPrimaryroleidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwPrimaryroleidlist.id}">${itwPrimaryroleidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
					</tr>
					
					<tr>
						<td align="left">Type ID</td>
						
						<td><form:select path="typeid" value="${itwUser.typeid}">
								<c:set var="typeidtemp">${itwUser.typeid}</c:set>
								<c:forEach items="${itwUser.itwTypeidList}" var="itwTypeidlist">
									<c:choose>
										<c:when test="${itwTypeidlist.id == typeidtemp}">
											<option value="${itwTypeidlist.id}" selected>${itwTypeidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwTypeidlist.id}">${itwTypeidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
						
						<td align="left">Company ID</td>
						

						<td><form:select path="companyid">
								<c:set var="companyidtemp">${itwUser.companyid}</c:set>
								<c:forEach items="${itwUser.itwCompanyidList}"
									var="itwCompanyidlist">
									<c:choose>
										<c:when test="${itwCompanyidlist.id == companyidtemp}">
											<option value="${itwCompanyidlist.id}" selected>${itwCompanyidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwCompanyidlist.id}">${itwCompanyidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>

					</tr>



					<tr>
						<td align="left">Icon</td>
						
						<td width="50%"><input type="file" name="file" id="file"
							style="height: auto"></input></td>

						
						<td align="left">Icon ID</td>
						
						<td><form:input path="iconid" value="${itwUser.iconid}"
								readonly="true" /></td>

					</tr>
<tr>
						<td align="left">User Role</td>
						
						<td colspan="3">
						<table width="60%">
							<c:if test="${itwUser.authority  == 'ROLE_USER'}">

								<tr>

									<td align="left" width="21%" style="border-top: 0">Normal User</td>
									<td style="border-top: 0"><form:radiobutton path="authority" checked="checked"
											value="ROLE_USER" /></td>
									<td align="left" width="10%" style="border-top: 0">Administrator</td>
									<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_ADMIN" /></td>
									<td align="left" width="10%" style="border-top: 0">Manager</td>
									<td style="border-top: 0"><form:radiobutton path="authority"
											value="ROLE_MANAGER" /></td>
								</tr>

							</c:if>

							<c:if test="${itwUser.authority  == 'ROLE_ADMIN'}">

								<tr>
									<td align="left" width="10%" style="border-top: 0">Normal&nbsp;User</td>
									<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_USER" /></td>


									<td align="left" width="10%" style="border-top: 0">Administrator</td>
									<td style="border-top: 0"><form:radiobutton path="authority" checked="checked"
											value="ROLE_ADMIN" /></td>
									<td align="left" width="10%" style="border-top: 0">Manager</td>
									<td style="border-top: 0"><form:radiobutton path="authority"
											value="ROLE_MANAGER" /></td>
								</tr>

							</c:if>
							<c:if test="${itwUser.authority  == 'ROLE_MANAGER'}">

								<td align="left" width="10%" style="border-top: 0">Normal&nbsp;User</td>
								<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_USER" /></td>


								<td align="left" width="10%" style="border-top: 0">Administrator</td>
								<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_ADMIN" /></td>
								<td align="left" width="10%" style="border-top: 0">Manager</td>
								<td style="border-top: 0"><form:radiobutton path="authority" checked="checked"
										value="ROLE_MANAGER" /></td>

							</c:if>
							
						</table>
						</td>
					</tr>
				</table>
				<div style="float: right; padding-right: 324px">
				<input type="submit" class="btn btn-success btn-small"
					value="Update User" />
				<a class="btn btn-primary btn-small" href="userConfigList.html">Back</a>
				</div>
			</form:form>
		</div>
	</div>
<style>
input[type="text"], input[type="password"], select, textarea {
    margin: 0 40px 0 0;
}
input[type="radio"] {
    position: relative;
    top: -3px;
}
</style>
</body>
</html>
