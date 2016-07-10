<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica, Arial', sans-serif; font-size: 10pt;">

			<form:form method="POST" action="updateItwUserRoles.html">

				<br>
				<br>
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 14pt; font-weight: bold;">
					<spring:message code="content.EditUserRoles" text="default text" />
				</p>

				<table
					style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 9pt;">

					<tr>
						<td align="left"><spring:message code="id.id"
								text="default text" /></td>
						<td>&nbsp</td>
						<td><form:input path="id" value="${itwUserRoles.id}"
								readonly="true" style=" height: 15px" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>
					
					<tr>
						<td align="left"><spring:message code="content.UserName"
								text="default text" /></td>
						<td></td>
					
						<td><form:select name="username" path="username" >
						
								<form:option value="${itwUserRole.username}">
									<c:out value="${itwUserRole.username}" />
									<c:forEach items="${itwUser}" var="itwUser">


										<form:option value="${itwUser.userid}" />
										<c:out value="${itwUser.userid}" />
										<br>

									</c:forEach>
								</form:option>
							</form:select></td>

						<td><form:errors path="username" cssClass="error" /></td>
					</tr>
					<tr>

						<td align="left"><spring:message code="content.RoleName"
								text="default text" /></td>
						<td></td>
						<td><form:select name="rolename" path="rolename">
								<form:option value="${itwUserRole.rolename}">
									<c:out value="${itwUserRole.rolename}" />
									<c:forEach items="${itwRoles}" var="itwUser">


						<form:option value="${itwUser.rolename}" />
						<c:out value="${itwUser.rolename}" />
						<br>

					</c:forEach>
				</form:option>
							</form:select></td>
						<td><form:errors path="rolename" cssClass="error" /></td>
					</tr>
					<form:input path="langDesc" value="${pageContext.response.locale}"
						type="hidden" />

					


				</table>



				<tr>
					<td><br> <spring:message code="button.update"
							var="button_update" /> <input type="submit"
						class="btn btn-success btn-small" value="${button_update}" /> <a
						class="btn btn-primary btn-small"
						href="itwUserRolesConfigarationList.html?langDesc=${pageContext.response.locale}">
							<spring:message code="button.back" text="default text" />
					</a>
			</form:form>
		</div>
	</div>

</body>
</html>
