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
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<!-- {!end layout} -->

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="saveItwUser.html"
				enctype="multipart/form-data">

				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					Create New User</p>
				<p>
					<form:errors path="*" cssClass="error"
						style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;" />
				</p>
				
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt; width: 68%"
					class="table table-striped" cellpadding="4">
					<tr>
						<td align="left">User Id</td>
						
						<td><form:input path="userid" value="${itwUser.userid}"
								placeholder="Enter User ID" /></td>

						
						<td align="left">Email ID</td>
						
						<td><form:input path="emailid" value="${itwUser.emailid}"
								placeholder="Enter Email ID" /></td>


					</tr>
					<tr>
						<td align="left">Password</td>
						
						<td><form:password path="password"
								value="${itwUser.password}" placeholder="Enter Password" /></td>
						
						<td align="left">Confirm Password</td>
						
						<td><form:password path="confirmpassword"
								value="${itwUser.confirmpassword}"
								placeholder="Enter Confirm Password" /></td>
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
						<td align="left">Gender</td>
						
						<td><form:select path="genderid">
								<form:option value="0" label="Select Gender" />
								<form:option value="1" label="Male" />
								<form:option value="2" label="Female" />
							</form:select></td>
						
						<td align="left">Role</td>
						
						<td><form:select path="primaryroleid"
								value="${itwUser.primaryroleid}">
								<form:option value="0" label="Select Role" />
								<form:options items="${itwUser1.itwPrimaryroleidListofValues}" />
							</form:select></td>
					</tr>

					<tr>
						<td align="left">User Type</td>
						
						<td><form:select path="typeid">
								<form:option value="0" label="Select Type" />
								<form:options items="${itwUser1.itwTypeidListofValues}" />
							</form:select></td>
						
						<td align="left">Company</td>
						
						<td><form:select path="companyid"
								value="${itwUser.companyid}">
								<form:option value="0" label="Select Company" />
								<form:options items="${itwUser1.itwCompanyidListofValues}" />
							</form:select></td>

					</tr>
					<tr>
						<td align="left">Photograph</td>

						<td width="10%"><input type="file" name="file" id="file"
							style="height: auto"></input></td>
</tr><tr>
						<td>User Status</td>
						<td>
						    <table>
						    <tr>
							<td align="left" style="border-top: 0">Enable</td>
							<td style="border-top: 0"><form:radiobutton path="enabled" value="1"
									checked="checked" /></td>
							<td style="border-top: 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="left" style="border-top: 0">Disable</td>
							<td style="border-top: 0"><form:radiobutton path="enabled" value="0" /></td>
									</tr>
							</table>
						</td>
						<td width="15%">Choose User Authority</td>
						<td>
							<table>
							<tr>
							

						<td style="border-top: 0">User</td>
						<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_USER"
								checked="checked" /></td><td style="border-top: 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td style="border-top: 0">Administrator</td>
						<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_ADMIN" /></td><td style="border-top: 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td style="border-top: 0">Manager</td>
						<td style="border-top: 0"><form:radiobutton path="authority" value="ROLE_MANAGER" /></td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>

						

					</tr>

				</table>
				<div style="float: right; padding-right: 388px">
				<input type="submit" class="btn btn-success btn-small"
					value="Save User" />
				<a class="btn btn-danger btn-small" href="addItwUser.html">Reset</a>
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
