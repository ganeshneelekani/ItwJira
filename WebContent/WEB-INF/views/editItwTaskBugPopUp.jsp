

<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page import="com.agileidc.itw.model.ItwUser"%>
<%@page import="javax.swing.JOptionPane"%>

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

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

<link href="css/bootstrap-responsive.min.css" rel="stylesheet"></link>

</head>
<body onblur="window.focus();">
	<div id="Capture-Image">
		<form:form method="POST"
			action="captureImage.html?id=${itwTaskBug.id}" commandName="image">
			<form:errors path="*" cssClass="error" />
			<table
				style="font-family: 'Verdana', Arial, sans-serif; font-size: 11px;"
				class="createIssue">

				<tr>
					<td width="1%"><a class="btn btn-danger btn-small"
						href="javascript:addImage1(<%=request.getParameter("id")%>);">Capture
							Now</a></td>

				</tr>

			</table>
		</form:form>
	</div>

	<script type="text/javascript">
		function addImage1(issueId) {

			window.close();
			var doc = window.opener.document, theForm = doc
					.getElementById("imageSubmitForm");

			theForm.submit();

		}
	</script>
</body>
</html>