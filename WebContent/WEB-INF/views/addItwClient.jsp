
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<div class="hero-unit span12"
			style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 10pt;">

			<form:form method="POST" action="saveItwClient.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family:  'Helvetica', Arial, sans-serif; font-size: 10pt; font-weight: bold;">
					<spring:message code="content.createClient"
						text="default text" />
				</p>
				<table
					style="font-family: 'Lucida Grande', Helvetica, Arial, sans-serif; font-size: 8pt;">

					<tr>
						<td align="left" valign="top"><spring:message code="content.Client"
								text="default text" /></td>
						<td>&nbsp</td>
						<spring:message code="content.EnterClient"
							var="var_enter_client" />
						<td><form:input path="clientname"
								value="${itwclient.clientname}"
								placeholder="${var_enter_client}"
								style="float:left; margin-right: 6px; width: 254px;" /></td>
						<td><form:errors path="clientname" cssClass="error" /></td>

						<form:input path="langType" value="${pageContext.response.locale}"
						type='hidden'	 />

					</tr>



				</table>
				<br>
				<input type="submit" class="btn btn-success btn-small"
					value=<spring:message code="content.saveClient" text="default text" /> />
				<a class="btn btn-danger btn-small" href="addItwClient.html"><spring:message
						code="content.Reset" text="default text" /></a>
				<a class="btn btn-primary btn-small"
					href="ItwClientConfigList.html?id=${itwclient.id}&langDesc=${pageContext.response.locale}"><spring:message
						code="content.Back" text="default text" /></a>
			</form:form>
		</div>
	</div>

</body>
</html>
