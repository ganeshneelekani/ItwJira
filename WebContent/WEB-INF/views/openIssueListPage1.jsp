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

<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
</head>
<body>

	<script type="text/javascript">
		function setValue() {
			alert("Calling java script on change");

			$.ajax({
				type : "GET",
				url : "issueList1.html",
				data : "reccount=" + $("#reccount option:selected").val() +"&action=list&p=1" ,
				success : function() {
					
				},
				error : function(e) {
					alert("error" + e.toSource());
					
				}
			});
			
		}
		function addDocument() {
			
		}
	</script>
	<c:if test="${!empty pagedListHolder}">
		<c:url value="issueList1.html" var="pagedLink">
			<c:param name="action" value="list" />
			<c:param name="p" value="~" />
			<c:param name="projectId" value="${projectId}"/>
		</c:url>
	</c:if>

	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



	<div class="container-fluid">

		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<table class="table table-striped">

				<thead>

					<tr>
						<td
							style="font-family: 'Helvetica', Arial, sans-serif; font-size: 12pt; font-weight: bold;"
							valign="middle"><b>Issues</b>
							
							
							</td>
							
						
							
						<td colspan="10" align="right">
							<table class="paginationtable">
								<tr>	
									<td style="background: transparent;"><form:form>
											<form:errors path="*" cssClass="error" />

										</form:form></td>
									<td style="background: transparent;"><tg:paging pagedListHolder="${pagedListHolder}"
											pagedLink="${pagedLink}" /></td>
											
								    <td style="background: transparent;"><a class="btn btn-primary btn-small"
										href="issueList1.html?projectId=${projectId}">Back</a></td>
										
									<!-- <td>
										<form method="post" action="issueList1.html" name="issueForm">
											<select id="reccount" name="reccount"
												onchange="javascript:setValue();">

												<option value="10">10</option>
												<option value="15">15</option>
												<option value="50">50</option>
											</select> 
											<input type="hidden" name="dropdown" id="dropdown">

											</form>
									</td>  -->
								</tr>
							</table>
						</td>
					</tr>
					<tr>


					</tr>
					<tr>

						<th>Issue-ID</th>
						<th>Short Name</th>
						<!-- <th>Lock Status</th>  -->
						<th>Type</th>
						<th>Project</th>
						<th>Severity</th>
						<th>Priority</th>
						<!--  	<th>OS</th> -->
						<!--	<th>Created By</th> -->
						<th>Assigned To</th>
						<th>Current Status</th>
						<th>Current Stage</th>
						<!--	<th>Efforts</th> -->
						<th>Action</th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty pagedListHolder}">
						<c:forEach items="${pagedListHolder.pageList}" var="itwTaskBug">
							<tr>
								<td style="color:${itwTaskBug.severityColor}" width="5%"><c:out
										value="${itwTaskBug.id}" /></td>

								<td><c:out value="${itwTaskBug.shortname}" /></td>
								<!-- 	<c:if test="${itwTaskBug.lock1 == 'Y'}">
									<td align="center">Locked</td>
								</c:if>
								<c:if test="${itwTaskBug.lock1 == 'N'}">
									<td align="center">Editable</td>
								</c:if>  -->
								<td><c:out value="${itwTaskBug.type1display}" /></td>
								<td><c:out value="${itwTaskBug.projectiddisplay}" /></td>


								<td style="color:${itwTaskBug.severityColor}"><c:out
										value="${itwTaskBug.severityiddisplay}" /></td>
								<td><c:out value="${itwTaskBug.priorityiddisplay}" /></td>
								<!--	<td><c:out value="${itwTaskBug.platformiddisplay}" /></td> -->
								<!--	<td><c:out value="${itwTaskBug.createdbydisplay}" /></td> -->
								<c:if test="${itwTaskBug.assigneeiddisplay != 'Unassigned'}">
									<td><c:out value="${itwTaskBug.assigneeiddisplay}" /></td>
								</c:if>
								<c:if test="${itwTaskBug.assigneeiddisplay == 'Unassigned'}">
									<td style="color: #F00"><c:out value="Unassigned" /></td>
								</c:if>
								<td><c:out value="${itwTaskBug.statusiddisplay}" /></td>
								<td><c:out value="${itwTaskBug.stagesiddisplay}" /></td>
								<!--	<td><c:out value="${itwTaskBug.efforts}" /></td>  -->
								
									<td><a class="btn btn-success btn-small"
										href="openItwTaskBug1.html?id=${itwTaskBug.id}&&projectId=${projectId}">Open</a> 
								
								
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
			<!-- <a class="btn btn-primary btn-small" href="addItwTaskBug1.html">Create
				Issue</a>  -->
		</div>

	</div>
	<script type="text/javascript">
		function editItwTaskBug(issueId) {
			alert("called editItwTaskBug");
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
						editItwTaskBug1(issueid);
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

		function editItwTaskBug1(issueId) {

			$.ajax({
				type : "POST",
				url : "editItwTaskBug1.html",
				data : "id=" + issueId,

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
.table th,.table td {
	padding: 4px;
	vertical-align: middle;
}

.btn-small {
	padding: 0px 10px;
	font-size: 10px;
}

.paginationtable {
	float: right;
}
</style>
</body>
</html>
