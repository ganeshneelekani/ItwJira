<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- // use our pagedListHolder --%>
<jsp:useBean id="pagedListHolder" scope="request"
	type="org.springframework.beans.support.PagedListHolder" />
<%-- // create link for pages, "~" will be replaced later on with the proper page number --%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta shortname="viewport" content="width=device-width, initial-scale=1.0" />
<meta shortname="colorcode" content="" />
<meta shortname="author" content="Simplicity Itself" />

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
	<c:url value="itwSeverityConfigList.html" var="pagedLink">
		<c:param name="action" value="list" />
		<c:param name="p" value="~" />
	</c:url>
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<div class="container-fluid">


		<div class="hero-unit span12"
			style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 8pt;">
		
			<table>
				<tr>
					<td><tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" /> 

						<table class="table table-striped">
							<thead>

								<tr>
									<td
										style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 10pt; font-weight: bold;" colspan="2"><b>List
										of Severity</b></td>

									<td colspan="3"><a class="btn btn-primary btn-small" href="addItwSeverity.html" style="float:right">Create
				Severity</a></td>
								</tr>
								<tr>

									<th width="50px">ID</th>
									<th>Name</th>
									<th>SLA(InHour)</th>
									

									<th>Action</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${pagedListHolder.pageList}" var="itwSeverity">

									<tr>

										<td align="center"><c:out value="${itwSeverity.id}" /></td>
										<td><c:out value="${itwSeverity.shortname}" /></td>
										<td><c:out value="${itwSeverity.sla}" /></td>
									
										<td><a class="btn btn-success btn-small"
											href="editItwSeverity.html?id=${itwSeverity.id}">Edit</a> <a
											class="btn btn-danger btn-small"
											href="deleteItwSeverity.html?id=${itwSeverity.id}" onclick = "if (! confirm('Do you really want to Delete ?')) { return false; }">Delete</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div></td>
				</tr>
			</table>
			
		</div>
	
	</div>

</body>
</html>
