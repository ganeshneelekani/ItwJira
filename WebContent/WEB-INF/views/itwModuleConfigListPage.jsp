
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

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

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

<link href="css/bootstrap-responsive.min.css" rel="stylesheet">


</head>
<body>
	<c:if test="${!empty pagedListHolder}">
		<c:url 	value="itwModuleConfigList.html?langDesc=${pageContext.response.locale}"
			var="pagedLink">
			<c:param name="action" value="list" />
			<c:param name="p" value="~" />
		</c:url>
	</c:if>
	
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="container-fluid">


		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<table>
				<tr>
					<td><tg:paging pagedListHolder="${pagedListHolder}"
							pagedLink="${pagedLink}" /> 
							<form:form>
							<form:errors path="*" cssClass="error" />
							<table class="table table-striped">
								<thead>

									<tr>
										<td
											style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight:bold" colspan="2"><b><spring:message
												code="title.ListofModule" text="default text" /></b></td>

										<td colspan="2"><a class="btn btn-primary btn-small" href="addItwModule.html" style="float:right"><spring:message
					code="content.createModule" text="default text" /> </a> 
										</td>
									</tr>
									<tr>

										<th width="50px">ID</th>
										<th><spring:message code="content.Module"
												text="default text" />
										<th>
									</tr>
								</thead>

								<tbody>
								<c:if test="${!empty pagedListHolder}">
									<c:forEach items="${pagedListHolder.pageList}" var="itwModule">

										<tr>

											<td align="center"><c:out value="${itwModule.id}" /></td>
											<td><c:out value="${itwModule.shortname}" /></td>


											<td><a class="btn btn-success btn-small"
												href="editItwModule.html?id=${itwModule.id}"><spring:message
														code="content.edit" text="default text" /></a> <a
												class="btn btn-danger btn-small"
												href="deleteItwModule.html?id=${itwModule.id}&langDesc=${pageContext.response.locale}" onclick = "if (! confirm('Do you really want to Delete ?')) { return false; }"><spring:message
														code="content.delete" text="default text" /></a></td>

										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${empty pagedListHolder}">

										<tr>
											<td align="left">No records found</td>

										</tr>

									</c:if>
								</tbody>
							</table>
						</form:form>
						</td>
				</tr>
			</table>


			
		</div>

	</div>

</body>
</html>
