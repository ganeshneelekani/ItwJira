<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	<!-- {!end layout} -->
   <div class="container-fluid">
	<div class="hero-unit span12">
		<p>
			<form:form method="POST" action="saveItwRca.html?projectId=${projectId}">
				<div style="float: right; padding-right: 60px">
					<input type="submit" class="btn btn-success btn-small" value="Save" />
				</div>

				<style>
.createIssue input,select,textarea {
	margin: 0 40px 0 20px;
}
</style>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 11px;"
					class="createIssue" cellpadding="4" width="100%">

					<h5>RCA Details</h5>
					<tr>
						<td colspan="2"><form:errors path="*" cssClass="error"
								style="font-family: 'Verdana', Arial, sans-serif; font-size: 11px; " />
						</td>
						<td><form:hidden path="id" /></td>
					</tr>
					
					

					
					<tr>
			        <td><form:label path="taskId">Task Id:</form:label></td>
			        <td><form:input path="taskId" readonly="true" value="${itwRcaBean.taskId}" /></td>
			        </tr>
					
					<tr>

						<td>Injected Stage</td>

						<td><form:select path="injectedStageId"
								value="${itwRcaBean.injectedStageId}">
								<form:option value="0" label="Select Injected Stage" />
								<c:set var="injectedStageiddisplaytemp">${itwRcaBean.injectedStageId}</c:set>
								<c:forEach items="${itwRcaBean.itwInjectedStagesidList}"
									var="itwInjectedStagesidlist">
									<c:choose>
										<c:when
											test="${itwInjectedStagesidlist.id == injectedStageiddisplaytemp}">
											<option value="${itwInjectedStagesidlist.id}" selected>${itwInjectedStagesidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwInjectedStagesidlist.id}">${itwInjectedStagesidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td>Injected By</td>

						<td><form:select path="injectedBy"
								value="${itwRcaBean.injectedBy}">
								<form:option value="0" label="Select Injected By" />
								<c:set var="injectedByiddisplaytemp">${itwRcaBean.injectedBy}</c:set>
								<c:forEach items="${itwRcaBean.itwInjectedByidList}"
									var="itwInjectedByidlist">
									<c:choose>
										<c:when
											test="${itwInjectedByidlist.id == injectedByiddisplaytemp}">
											<option value="${itwInjectedByidlist.id}" selected>${itwInjectedByidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwInjectedByidlist.id}">${itwInjectedByidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>

					</tr>
					<tr>
						<td>Detected Stage</td>

						<td><form:select path="detectedStageId"
								value="${itwRcaBean.detectedStageId}">
								<form:option value="0" label="Select Detected Stage" />
								<c:set var="detectedStageiddisplaytemp">${itwRcaBean.detectedStageId}</c:set>
								<c:forEach items="${itwRcaBean.itwDetectedStagesidList}"
									var="itwDetectedStagesidlist">
									<c:choose>
										<c:when
											test="${itwDetectedStagesidlist.id == detectedStageiddisplaytemp}">
											<option value="${itwDetectedStagesidlist.id}" selected>${itwDetectedStagesidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwDetectedStagesidlist.id}">${itwDetectedStagesidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td>Detected By</td>

						<td><form:select path="detectedBy"
								value="${itwRcaBean.detectedBy}">
								<form:option value="0" label="Select Detected By" />
								<c:set var="detectedBydisplaytemp">${itwRcaBean.detectedBy}</c:set>
								<c:forEach items="${itwRcaBean.itwDetectedByidList}"
									var="itwDetectedByidlist">
									<c:choose>
										<c:when
											test="${itwDetectedByidlist.id == detectedBydisplaytemp}">
											<option value="${itwDetectedByidlist.id}" selected>${itwDetectedByidlist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${itwDetectedByidlist.id}">${itwDetectedByidlist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>

							</form:select></td>


					</tr>
					
					<tr>
						<td>Feedback Given To</td>

						<td><form:select path="feedbackGivenTo"
								value="${itwRcaBean.feedbackGivenTo}">
								<form:option value="0" label="Select Feedback Given To" />
								<c:set var="feedbackGivenToDisplaytemp">${itwRcaBean.feedbackGivenTo}</c:set>
								<c:forEach items="${itwRcaBean.feedbackGivenToList}"
									var="feedbackGivenTolist">
									<c:choose>
										<c:when
											test="${feedbackGivenTolist.id == feedbackGivenToDisplaytemp}">
											<option value="${feedbackGivenTolist.id}" selected>${feedbackGivenTolist.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${feedbackGivenTolist.id}">${feedbackGivenTolist.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>

					</tr>
					
					<tr>
						<td valign="top">Corrective Action Taken</td>
						<td colspan="9"><form:textarea cols="99" rows="4"
								path="correctiveActionTaken" value="${itwRcaBean.correctiveActionTaken}" style="width: 92%" /></td>
					</tr>

					<tr>
						<td valign="top">Root Cause Analysis Details</td>
						<td colspan="9"><form:textarea cols="99" rows="4"
								path="rcaDetails" value="${itwRcaBean.rcaDetails}" style="width: 92%" /></td>
					</tr>

                    




				</table>

			</form:form>
		</p>


	</div>
	</div>

</body>
</html>
