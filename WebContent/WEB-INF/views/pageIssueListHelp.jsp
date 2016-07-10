
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

</head>
<body>
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">

			<form:form method="POST" action="updateItwModule.html"
				enctype="multipart/form-data">
				
				<p
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 14pt; font-weight: bold; ">
				This page provided list of issues that has created by the user
				</p>
				<table
					style="font-family: 'Helvetica', Arial, sans-serif; font-size: 10pt;">

					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Issue Id</td>
						<td>&nbsp;</td>
						<td>- &nbsp;</td>
						<td>Provides generated issue Id</td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Short Description</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Provides  Description about the Bug/Task</td>

					</tr>
					
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Type</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td> Shows created issue is Task/Bug.</td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Project</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td> Shows for which project Task/Bug is created.</td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Severity</td>
						<td>&nbsp;</td>
						<td>- &nbsp;</td>
						<td> Shows Severity of the Task/Bug.</td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Priority</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td> Shows Priority of the  Task/Bug.</td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Assigned To</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Shows the username who logged-in </td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Current Status</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Shows  current status of the  Task/Bug. </td>

					</tr>
					
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Current Status</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Shows  current status of the  Task/Bug. ex-Issue Closed,Development</td>

					</tr>
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Current Stage</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Shows the current stage of the Task/Bug() ex-Inprogress, Development,Testing</td>

					</tr>
					
				
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Edit(Button)</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>User want to edit and Task/Bug. </td>
					</tr>
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">View(Button)</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>User wants to view  Task/Bug. </td>

					</tr>
					
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Search (Button)</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Provides search option for Id, Severity,Username</td>

					</tr>
					<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">IssueClosed(Button)</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>Provides list of issues which closed.</td>

					</tr>
					
						<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Create Issue(Button)</td>
						<td>&nbsp;</td>
							<td>- &nbsp;</td>
						<td>If user want to create new Issue.</td>

					</tr>
					
					
						<tr>
						<td align="left" style="color: #3a92c8; font-weight: Bold">Page help(link)</td>
						<td>&nbsp;</td>
						<td>- &nbsp;</td>
						<td>Provides redirect to the help page</td>

					</tr>

				</table>
				<br>
			
				<a class="btn btn-primary btn-small"
					href="issueList1.html?projectId=0"><spring:message
						code="content.Back" text="default text" /></a>
			</form:form>
		</div>
	</div>

</body>
</html>
