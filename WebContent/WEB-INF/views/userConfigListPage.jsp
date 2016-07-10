<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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
	<c:url value="userConfigList.html" var="pagedLink">
		<c:param name="action" value="list" />
		<c:param name="p" value="~" />
	</c:url>
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	
	<div class="container-fluid">


		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
			
			<table>
				<tr>
					<td><tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" /> 

						<table class="table table-striped">
							<thead>

								<tr>
									<td
										style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;" colspan="2"><b>List
										of Users</b></td>

									<td colspan="7" ><a class="btn btn-primary btn-small" href="addItwUser1.html" style="float:right">Create
				User</a></td>
								</tr>
								<tr>

									<th width="50px">ID</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Gender</th>

									<th>Email ID</th>
									<th>Primary Role</th>
									<th>User Type</th>
									<th>Company</th>

									<th>Action</th>

								</tr>
							</thead>

							<tbody>
								<c:forEach items="${pagedListHolder.pageList}" var="itwUser">

									<tr>

										<td align="center"><c:out value="${itwUser.id}" /></td>
										<td><c:out value="${itwUser.firstname}" /></td>
										<td><c:out value="${itwUser.lastname}" /></td>
										<td><c:out value="${itwUser.gender}" /></td>

										<td><c:out value="${itwUser.emailid}" /></td>
										<td><c:out value="${itwUser.primaryroleiddisplay}" /></td>
										<td><c:out value="${itwUser.typeiddisplay}" /></td>
										<td><c:out value="${itwUser.companyiddisplay}" /></td>
										<td><a class="btn btn-success btn-small"
											href="editItwUser.html?id=${itwUser.id}">Edit</a> <a
											class="btn btn-danger btn-small"
											href="deleteItwUser.html?id=${itwUser.id}" onclick = "if (! confirm('Do you really want to Delete ?')) { return false; }">Delete</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div></td>
				</tr>
			</table>
			
		</div>
		
	</div>
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
