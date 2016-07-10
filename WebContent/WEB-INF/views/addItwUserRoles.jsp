<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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
	<!-- {!begin layout} -->


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">

			<form:form method="POST" action="saveItwUserRoles.html">

				<br>
				<br>
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 14pt; font-weight: bold;">
					<spring:message code="create.UserRoles" text="Create User Role" />
				</p>
				<table
					style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 11pt;">






					<tr>
						<td align="left"><spring:message code="content.UserName"
								text="default text" /></td>
						<td></td>
						
						
						<td><form:select name="username" path="username">
							
								
															
								<c:forEach items="${itwUser}" var="itw">
									<!-- <form:option value="${itw.userid}" />
									<c:out value="${itw.userid}" />  -->
									
									<c:choose>
													<c:when test="${itw.userid == itwUserRole}">
														<option value="${itw.userid}" selected>${itw.userid}</option>
													</c:when>
													<c:otherwise>
														<option value="${itw.userid}">${itw.userid}</option>
													</c:otherwise>
												</c:choose>

								</c:forEach>
							</form:select>
						<td><form:errors path="username" cssClass="error" /></td>
					</tr>


					
					<form:input path="langDesc" value="${pageContext.response.locale}"
						type="hidden" />
					<tr>
						<td align="left"><spring:message code="content.RoleName"
								text="default text" /></td>
						<td></td>

						<td><div id="ScrollCB"
								style="height: 100px; width: 200px; overflow: scroll">




								<c:forEach items="${itwnewUserRole}" var="itwUser">

									<input type="checkbox" id="${itwUser.rolename}" name="rolename"
										value="${itwUser.rolename}" checked="checked">
									<c:out value="${itwUser.rolename}"  />

									<br>
								</c:forEach>
							

								<c:forEach items="${itwRoles}" var="itwUser">

									<input type="checkbox" id="${itwUser.rolename}" name="rolename"
										value="${itwUser.rolename}" >
									<c:out value="${itwUser.rolename}" />

									<br>
								</c:forEach>

							</div></td>
						<td><form:errors path="rolename" cssClass="error" /></td>
					</tr>
				</table>
				<br>
				<spring:message code="button.save" var="button_save" />
				<input type="submit" class="btn btn-success btn-small"
					value="${button_save}" />
				<a class="btn btn-danger btn-small"
					href="addItwUserRoles.html?langDesc=${pageContext.response.locale}">
					<spring:message code="button.reset" text="default text" />
				</a>
				<a class="btn btn-primary btn-small"
					href="itwUserRolesConfigarationList.html?langDesc=${pageContext.response.locale}">
					<spring:message code="button.back" text="default text" />
				</a>
			</form:form>
		</div>
	</div>

</body>
</html>
