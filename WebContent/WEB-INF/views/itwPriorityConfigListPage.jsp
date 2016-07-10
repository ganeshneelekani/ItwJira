<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />



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

</head>
<body>
	<c:url value="itwPriorityConfigList.html" var="pagedLink">
		<c:param name="action" value="list" />
		<c:param name="p" value="~" />
	</c:url>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<div class="container-fluid">


		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<table>
				<tr>
					<c:if test="${!empty pagedListHolder}">
						<td colspan="14"><tg:paging
								pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" /></td>
					</c:if>
					<form:form>
						<form:errors path="*" cssClass="error" />
						<table class="table table-striped" style="width:auto">
							<thead>

								<tr>
									<td colspan="2"
										style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;"><b>List
										of Priorities</b></td>

									<td colspan="1"><a class="btn btn-primary btn-small"
						href="addItwPriority.html" style="float:right">Create Priority</a></td>
								</tr>
								<tr>
									<th width="50px">ID</th>
									<th>Name</th>
									<th>Action</th>
								</tr>
							</thead>

							<tbody>
								<c:if test="${!empty pagedListHolder}">
									<c:forEach items="${pagedListHolder.pageList}"
										var="itwPriority">

										<tr>

											<td align="center"><c:out value="${itwPriority.id}" /></td>
											<td><c:out value="${itwPriority.shortname}" /></td>
											<td><a class="btn btn-success btn-small"
												href="editItwPriority.html?id=${itwPriority.id}">Edit</a> <a
												class="btn btn-danger btn-small"
												href="deleteItwPriority.html?id=${itwPriority.id}" onclick = "if (! confirm('Do you really want to Delete ?')) { return false; }">Delete</a></td>

										</tr>
									</c:forEach>

								</c:if>
								<c:if test="${empty pagedListHolder.pageList}">
									<tr>
										<td>No records found...</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</form:form>
				
				</tr>
			

			</table>
			<br>

		</div>

	</div>

</body>
</html>
