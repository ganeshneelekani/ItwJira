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
	

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">
	
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">
<p
					style="font-family: 'Verdana', Arial, sans-serif; font-size: 12pt; font-weight: bold; margin-bottom: 20px;">
					<b>RCA Details </b>  <a class="btn btn-primary btn-small"
						href="itwRcaList.html" style="color: #fff; float: right">Back</a>
				</p>
			
			<table class="table table-striped">

				<tbody>
					<c:if test="${!empty itwRcaBean1}">
						
							<tr>
							<td width="20%">Task Id </td>
							
								<td><c:out value="${itwRcaBean1.taskId}" /></td>
							</tr>
							<tr>
							<td>Injected Stage</td>

								<td><c:out value="${itwRcaBean1.injectedStageIdDisplay}" /></td>
							</tr>
							<tr>
							<td>Injected By</td>
								<td><c:out value="${itwRcaBean1.injectedByDisplay}" /></td>
							</tr>
							<tr>
							<td>Detected Stage</td>
								<td><c:out value="${itwRcaBean1.detectedStageIdDisplay}" /></td>
							</tr>
							<tr>
							<td>Detected By</td>
								<td><c:out value="${itwRcaBean1.detectedByDisplay}" /></td>


							</tr>
							<tr>
							<td>Root Cause Analysis Details</td>
								<td><c:out value="${itwRcaBean1.rcaDetails}" /></td>


							</tr>

						
					</c:if>
					<c:if test="${empty itwRcaBean1}">
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
