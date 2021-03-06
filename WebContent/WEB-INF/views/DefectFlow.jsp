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
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<c:if test="${!empty pagedListHolder}">
		<c:url value="defectFlow.html?id=${taskId1}" var="pagedLink">
			<c:param name="action" value="list" />
			<c:param name="p" value="~" />
		</c:url>
	</c:if>
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	
	<div class="container-fluid">

		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
			
			 
			<table class="table table-striped">

				<thead>
				<p
					style="font-family: 'Verdana', Arial, sans-serif; font-size: 10pt; font-weight: bold; margin-bottom: 20px;">
					Defect ${taskId1} Flow <a class="btn btn-primary btn-small"
						href="defectWorkflowList.html" style="color: #fff; float: right">Issue
						List</a>
				</p>

					<tr>
						<c:if test="${!empty pagedListHolder}">
							<td colspan="14"><tg:paging pagedListHolder="${pagedListHolder}"
									pagedLink="${pagedLink}" /></td>
						</c:if>
						<td>
							
						</td>
					</tr>
					<tr>
						
						
						
						
						<th>Previous Status</th>
						<th>Time</th>
						<th>Next Status</th>
						<th>Time</th>
						<th>Elapse (In hours)</th>
						
						
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty pagedListHolder}">
						<c:forEach items="${pagedListHolder.pageList}" var="defectFlow">
							<tr>
								
								<td><c:out value="${defectFlow.previousState}" /></td>
								<td><c:out value="${defectFlow.previousTime}" /></td>
								<td><c:out value="${defectFlow.nextState}" /></td>
								<td><c:out value="${defectFlow.nextTime}" /></td>
								<td><c:out value="${defectFlow.elapse}" /></td>
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
	<script type="text/javascript">
		function edititwTaskBugAudit(issueId) {
			alert("called edititwTaskBugAudit");
			$.ajax({
				type : "POST",
				url : "getTask1.html" + "?id=" + issueId, 
				dataType : "json",
				success : function(result) {
					alert("id is " + result[0].id + "\n" + "lock is "
							+ result[0].lock1 + "\n" + "shortname is "
							+ result[0].shortname + "\n" + "assigneeid is "
							+ result[0].assigneeid + "\n");

					if (result[0].lock1 == 'N') {
						edititwTaskBugAudit1(issueid);
					} else {
						alert("The Issue Id '" + result[0].id + "'"
								+ " is already locked by "
								+ result[0].assigneeid)
					}

				},
				error : function(e) {
					alert("error" + e.toSource());
				}
			});
		}

		function edititwTaskBugAudit1(issueId) {

			$
					.ajax({
						type : "POST",
						url : "edititwTaskBugAudit1.html",
						data : "id=" + issueId ,

						success : function(result) {


						},
						error : function(e) {
							alert("error" + e.toSource());
							$("#msgStatus").html("Error : " + e);
						}
					});
		}
	</script>
<style>
.table th, .table td {
padding: 3px;
vertical-align: middle;
}
.btn-small {
padding: 0px 10px;
font-size: 10px;
}
</style>
</body>
</html>
