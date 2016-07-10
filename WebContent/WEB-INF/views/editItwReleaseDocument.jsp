<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

			<form:form method="POST" action="updateItwReleaseDocument.html">


				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="content.EditReleaseDocument"
						text="default text" />
				</p>

				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 8pt;">
					<tr>
						<td align="left"><spring:message code="id.id"
								text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="id" value="${itwReleaseDocument.id}"
								readonly="true" style=" height: 15px" /></td>
						<td><form:errors path="id" cssClass="error" /></td>

					</tr>

                      <tr>
						<td align="left"><spring:message
								code="ReleaseDocument.title" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="title"id="title" style="width: 92%" ><c:out value="${itwReleaseDocument.title}"/></textarea></td>
						<td><form:errors path="title" cssClass="error" /></td>
                    </tr>

					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.createdBy" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="createdBy"
								value="${itwReleaseDocument.createdBy}" /></td>
						<td><form:errors path="createdBy" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.revisedBy" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="revisedBy"
								value="${itwReleaseDocument.revisedBy}" /></td>
						<td><form:errors path="revisedBy" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.sourceCodeVersion" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="sourceCodeVersion"
								value="${itwReleaseDocument.sourceCodeVersion}" /></td>
						<td><form:errors path="sourceCodeVersion" cssClass="error" /></td>

					</tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.envoirnmentEffected" text="default text" /></td>
						<td>&nbsp;</td>
						<!-- <td><form:textarea path="envoirnmentEffected"
								value="${itwReleaseDocument.envoirnmentEffected}" /></td>-->

						<td><textarea cols="99" rows="4" name="envoirnmentEffected"	id="envoirnmentEffected" style="width: 92%"><c:out value="${itwReleaseDocument.envoirnmentEffected}"/></textarea></td>
						<td><form:errors path="envoirnmentEffected" cssClass="error" /></td>
					</tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.buisnessImpact" text="default text" /></td>
						<td>&nbsp;</td>	<td><textarea cols="99" rows="4" name="buisnessImpact"
								id="buisnessImpact" style="width: 92%"><c:out value="${itwReleaseDocument.buisnessImpact}" /></textarea></td>
						<td><form:errors path="buisnessImpact" cssClass="error" /></td>


					</tr>
					

					<tr>

						<td><form:input path="releaseid"
								value="${itwReleaseDocument.releaseid}" style=" height: 15px"
								type="hidden" /></td>
						<td><form:errors path="releaseid" cssClass="error"
								type="hidden" /></td>

					</tr>
					
					


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.releaseName" text="default text" /></td>
						<td></td>
						<td><form:select name="releaseName" path="releaseName">
								<form:option value="${itwReleaseDocument.releaseName}" />
								<c:out value="${itwReleaseDocument.releaseName}" />

								<c:forEach items="${itwReleases}" var="itwUser">

									<form:option value="${itwUser.shortname}" />
									<c:out value="${itwUser.shortname}" />

								</c:forEach>
							</form:select></td>
                        
						<td><form:errors path="releaseName" cssClass="error" /></td>
					</tr>

					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.releaseDate" text="default text" /></td>
						<td>&nbsp;</td>
						<td><form:input path="releaseDate"
								value="${itwReleaseDocument.releaseDate}"
								class="listgrid-criteria-input datepicker" type="text"
								placeholder="MM/dd/yyyy" /></td>
						<i class="icon-calendar"></i>
						<td><form:errors path="releaseDate" cssClass="error" /></td>

					</tr>
                     <tr>
						<td align="left"><spring:message
								code="ReleaseDocument.introduction" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="introduction" id="introduction" style="width: 92%" ><c:out value="${itwReleaseDocument.introduction}"/></textarea></td>
						<td><form:errors path="introduction" cssClass="error" /></td>


					</tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.scope" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="scope"id="scope" style="width: 92%"><c:out value="${itwReleaseDocument.scope}"/></textarea></td>
						<td><form:errors path="scope" cssClass="error" /></td>
                     </tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.systemRequirements" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="systemRequirements"id="systemRequirements" style="width: 92%" ><c:out value="${itwReleaseDocument.systemRequirements}"/></textarea></td>
						<td><form:errors path="systemRequirements" cssClass="error" /></td>
                   </tr>
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.prerequisites" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="prerequisites" id="prerequisites" style="width: 92%" ><c:out value="${itwReleaseDocument.systemRequirements}"/></textarea></td>
						<td><form:errors path="prerequisites" cssClass="error" /></td>
                	</tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.objectImpacted" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="objectImpacted"id="objectImpacted" style="width: 92%" readonly="readonly"><c:out value="${itwReleaseDocument.objectImpacted}"/></textarea></td>
						<td><form:errors path="objectImpacted" cssClass="error" /></td>
                   </tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.operatingsystemssupported" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="operatingsystemssupported" id="operatingsystemssupported" style="width: 92%"><c:out value="${itwReleaseDocument.operatingsystemssupported}"/></textarea></td>
						<td><form:errors path="operatingsystemssupported" cssClass="error" /></td>
                     </tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.knownIssues" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="knownIssues"id="knownIssues" style="width: 92%" ><c:out value="${itwReleaseDocument.knownIssues}"/></textarea></td>
						<td><form:errors path="knownIssues" cssClass="error" /></td>
                   </tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.assumptionDependencies" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="assumptionDependencies"id="assumptionDependencies" style="width: 92%" ><c:out value="${itwReleaseDocument.assumptionDependencies}"/></textarea></td>
						<td><form:errors path="assumptionDependencies" cssClass="error" /></td>
					</tr>

<!-- 
					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.defects" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="defects"id="defects" style="width: 92%"  readonly="readonly"><c:out value="${itwReleaseDocument.defects}"/></textarea></td>
						<td><form:errors path="defects" cssClass="error" /></td>
                    </tr>
                   
         -->                   
                <tr>
						<td align="left"><spring:message
								code="ReleaseDocument.defects" text="default text" /></td>
						<td>&nbsp;</td>
						
                         <td>
                        
                         <table border="1" >
                         <tr><td>&nbsp;
                             	BUG ID &nbsp;

							</td>
							<td>&nbsp;
                             	DEFECT&nbsp;

							</td></tr>
                          <c:forEach items="${itwReleasesDefects}" var="itwUser"> 
                          <tr><td>&nbsp;
                             	<c:out value=" ${itwUser.id}  " />&nbsp;

							</td>
							<td>&nbsp;
                             	<c:out value=" ${itwUser.shortname} " />&nbsp;

							</td></tr>
							 	</c:forEach>
                         </table>
                        
				</td>
				</tr>	<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.specialInstructions" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="specialInstructions" id="specialInstructions" style="width: 92%" ><c:out value="${itwReleaseDocument.specialInstructions}"/></textarea></td>
						<td><form:errors path="specialInstructions" cssClass="error" /></td>
					</tr>


					<tr>
						<td align="left"><spring:message
								code="ReleaseDocument.releaseProcessInsrtuctions" text="default text" /></td>
						<td>&nbsp;</td>
						<td><textarea cols="99" rows="4" name="releaseProcessInsrtuctions" id="releaseProcessInsrtuctions" style="width: 92%" ><c:out value="${itwReleaseDocument.releaseProcessInsrtuctions}"/></textarea></td>
						<td><form:errors path="releaseProcessInsrtuctions" cssClass="error" /></td>
                   </tr>


				</table>

				<tr>
					<td><br> <spring:message code="button.update"
							var="button_update" /> <input type="submit"
						class="btn btn-success btn-small" value="${button_update}" /> <a
						class="btn btn-primary btn-small"
						href="itwReleaseDocumentConfigList.html?langDesc=${pageContext.response.locale}">
							<spring:message code="button.back" text="default text" />
					</a> 
			</form:form>
		</div>
	</div>

</body>
</html>
