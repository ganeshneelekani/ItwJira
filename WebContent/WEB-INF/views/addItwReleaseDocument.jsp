<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">

			<form:form method="POST" action="saveItwReleaseDocument.html">

				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="create.ReleaseDocument" text="default text" />
				</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
					
					
					
					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.title" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="title"
								value="${itwReleaseDocument.title}"
								placeholder="Enter Title"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="title" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.createdBy" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="createdBy"
								value="${itwReleaseDocument.createdBy}"
								placeholder="Enter Name of Creator" /></td>
						<td><form:errors path="createdBy" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.revisedBy" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="revisedBy"
								value="${itwReleaseDocument.revisedBy}"
								placeholder="Enter Name of Revisor" /></td>
						<td><form:errors path="revisedBy" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.sourceCodeVersion" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="sourceCodeVersion"
								value="${itwReleaseDocument.sourceCodeVersion}"
								placeholder="Enter Name of SourceCode Version" /></td>
						<td><form:errors path="sourceCodeVersion" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.envoirnmentEffected" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="envoirnmentEffected"
								value="${itwReleaseDocument.envoirnmentEffected}"
								placeholder="Enter Name of Envoirnment Effected"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="envoirnmentEffected" cssClass="error" /></td>

					</tr>

					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.buisnessImpact" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="buisnessImpact"
								value="${itwReleaseDocument.buisnessImpact}"
								placeholder="Enter Name of Business Impact"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="buisnessImpact" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.releaseName" text="default text" /></td>
						<td></td>
						<td><form:select name="releaseName" path="releaseName">
								<c:forEach items="${itwReleases}" var="itwRelease">

									<form:option value="${itwRelease.shortname}" />
									<c:out value="${itwRelease.shortname}" />

								</c:forEach>
							</form:select>
						<td><form:errors path="releaseName" cssClass="error" /></td>
					</tr>
					<tr>

						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.releaseDate" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="releaseDate"
								value="${itwReleaseDocument.releaseDate}"
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="MM/DD/YYYY" /></td>
						<i class="icon-calendar"></i>
						<td><form:errors path="releaseDate" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.introduction" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="introduction"
								value="${itwReleaseDocument.introduction}"
								placeholder="Enter introduction"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="introduction" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message code="ReleaseDocument.scope"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="scope"
								value="${itwReleaseDocument.scope}" placeholder="Enter scope"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="scope" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.systemRequirements" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="systemRequirements"
								value="${itwReleaseDocument.systemRequirements}"
								placeholder="Enter systemRequirements"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="systemRequirements" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.operatingsystemssupported"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="operatingsystemssupported"
								value="${itwReleaseDocument.operatingsystemssupported}"
								placeholder="Enter operating systems supported"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="operatingsystemssupported"
								cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.prerequisites" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="prerequisites"
								value="${itwReleaseDocument.prerequisites}"
								placeholder="Enter prerequisites"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="prerequisites" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.knownIssues" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="knownIssues"
								value="${itwReleaseDocument.knownIssues}"
								placeholder="Enter knownIssues"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="knownIssues" cssClass="error" /></td>

					</tr>




					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.assumptionDependencies"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="assumptionDependencies"
								value="${itwReleaseDocument.assumptionDependencies}"
								placeholder="EnterassumptionDependencies"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="assumptionDependencies"
								cssClass="error" /></td>

					</tr>


					<tr>
						<td align="left" valign="top"><spring:message
								code="ReleaseDocument.specialInstructions" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:textarea path="specialInstructions"
								value="${itwReleaseDocument.specialInstructions}"
								placeholder="specialInstructions"
								style="width:200%; height:200px " /></td>
						<td><form:errors path="specialInstructions" cssClass="error" /></td>
					</tr>

					


					<tr>

						<td valign="top"><form:input path="releaseid"
								value="${itwReleaseDocument.releaseid}" style=" height: 15px"
								type="hidden" /></td>
						<td><form:errors path="releaseid" cssClass="error"
								type="hidden" /></td>

					</tr>

				</table>
				<br>
				<spring:message code="button.save" var="button_save" />
				<input type="submit" class="btn btn-success btn-small"
					value="${button_save}" />
				<a class="btn btn-danger btn-small"
					href="addItwReleaseDocument.html"> <spring:message
						code="button.reset" text="default text" />
				</a>
				<a class="btn btn-primary btn-small"
					href="itwReleaseDocumentConfigList.html"> <spring:message
						code="button.back" text="default text" />
				</a>
			</form:form>
		</div>
	</div>

</body>
</html>
