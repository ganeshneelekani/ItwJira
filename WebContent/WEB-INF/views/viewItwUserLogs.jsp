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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="Simplicity Itself" />

<title>IT Work Book</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
</link>
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>

<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>


</head>
<body>
	<c:if test="${!empty pagedListHolder}">
		<c:url value="viewItwUserLogs.html" var="pagedLink">
			<c:param name="userid" value="${userid}" />
            <c:param name="action" value="list" />
		<c:param name="p" value="~" />
		</c:url>
	</c:if>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">

		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold">

			<p
				style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold; margin-bottom: 10px;">
				  Log Details of ${userid} <a class="btn btn-primary btn-small"
					href="itwUserLogsList.html" style="color: #fff; float: right">User
					Log List</a>
			</p>
					<table class="table table-striped"  style="font-size:8pt; font-weight: normal">

				<thead>

					<tr>
						<td colspan="2"
							style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt; font-weight: bold; ">Login History</td>

					</tr>
					<tr>
						<c:if test="${!empty pagedListHolder}">
							<td colspan="14"><tg:paging
									pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" /></td>
						</c:if>
					</tr>
					<tr>

						<th>User Log Id</th>
						<th>IP Address</th>

						<th>Browser Details</th>
						<th>Login Time</th>
						<th>Logout Time</th>
						<th>Online Status</th>
						<th>Logout Reason</th>

					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty pagedListHolder}">
						<c:forEach items="${pagedListHolder.pageList}"
							var="itwUserLogsBeans">
							<tr>
								<td width="5%"><c:out value="${itwUserLogsBeans.id}" /></td>

								<td><c:out value="${itwUserLogsBeans.ipaddress}" /></td>


								<td><c:out value="${itwUserLogsBeans.browserdetails}" /></td>


								<td><c:out value="${itwUserLogsBeans.logintime}" /></td>
								<td><c:out value="${itwUserLogsBeans.logouttime}" /></td>
								<td><c:out value="${itwUserLogsBeans.onlinestatus}" /></td>
								<td><c:out value="${itwUserLogsBeans.logoutreason}" /></td>

							</tr>

						</c:forEach>
					</c:if>
					<c:if test="${empty pagedListHolder.pageList}">
						<tr>
							<td>No Issue records found...</td>
						</tr>

					</c:if>
				</tbody>
			</table>

		</div>

	</div>

</body>
</html>
