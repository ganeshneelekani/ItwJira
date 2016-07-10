<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- // use our pagedListHolder --%>
<c:if test="${!empty pagedListHolder}">
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
</c:if>
<%-- // create link for pages, "~" will be replaced later on with the proper page number --%>

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


<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
<body>
	<c:url
		value="itwUserRolesConfigarationList.html"
		var="pagedLink">
		<c:param name="action" value="list" />
		<c:param name="p" value="~" />
	</c:url>
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	
	<div class="container-fluid">


		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">

			<table>
				<tr>

					<c:if test="${!empty pagedListHolder}">
						<td><tg:paging pagedListHolder="${pagedListHolder}"
								pagedLink="${pagedLink}" />
					</c:if>
					
					<form:form>
						<form:errors path="*" cssClass="error" />
						<table class="table table-striped">
							<thead>

								<tr>
									<td
										style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
										<spring:message code="userRoles.header" text="User Roles List" />
									</td>

							
								</tr>
								<tr>

									<th><spring:message code="id.id" text="default text" /></th>
									<th><spring:message code="userRoles.rolename"
											text="default text" /></th>
									<th colspan="4"><spring:message code="userRoles.username"
											text="default text" /></th>



								</tr>
							</thead>

							<tbody>
								<c:if test="${!empty pagedListHolder}">
									<c:forEach items="${pagedListHolder.pageList}"
										var="itwuserRoles">

										<tr>

											<td align="center"><c:out value="${itwuserRoles.id}" /></td>
											<td><c:out value="${itwuserRoles.rolename}" /></td>
											<td><form:errors path="rolename" cssClass="error" /></td>
											<td><c:out value="${itwuserRoles.username}" /></td>
											<td><a class="btn btn-success btn-small"
												href="editItwUserRoles.html?id=${itwuserRoles.id}">
													<spring:message code="button.edit" text="default text" />
											</a> <a class="btn btn-danger btn-small"
												href="deleteItwUserRoles.html?id=${itwuserRoles.id}">
													<spring:message code="button.delete" text="default text" />
											</a></td>

										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</form:form>

					<c:if test="${empty pagedListHolder}">
								No userRoles Present
				</c:if>
				</tr>
			</table>
			<br>
			<a class="btn btn-primary btn-small"
				href="addItwUserRoles.html?langDesc=${pageContext.response.locale}">
				<spring:message code="create.userRoles" text="default text" />
			</a>
		</div>
		
	</div>

</body>
</html>
