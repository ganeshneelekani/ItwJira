<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT Workbook | Bug List</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/smartpaginator.css" />
<!--[if lte IE 7]>
<style>
.content { margin-right: -1px; } /* this 1px negative margin can be placed on any of the columns in this layout with the same corrective effect. */
ul.nav a { zoom: 1; }  /* the zoom property gives IE the hasLayout trigger it needs to correct extra whiltespace between the links */
</style>
<![endif]-->
</head>
<style>
.headerlist {
	margin-top: 7px;
}

.headerlist ul li {
	line-height: 0.8;
}

.page th {
	font-size: 14px;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/smartpaginator.js"></script>
<script type="text/javascript">
	var rec_count = 0;

	<c:forEach items="${ItwTaskBugs}" var="itwTaskBugs">
	rec_count = rec_count + 1;
	</c:forEach>
	$(document).ready(function() {
		$('#green').smartpaginator({
			totalrecords : rec_count,
			recordsperpage : 5,
			datacontainer : 'mt',
			dataelement : 'tr',
			initval : 0,
			next : 'Next',
			prev : 'Prev',
			first : 'First',
			last : 'Last',
			theme : 'green'
		});
	});
</script>
<body>

	<div class="container">
		<div class="header">
			<a href="index.html"><img src="images/logo.png" alt="IT workbook"
				name="IT workbook" id="IT workbook" /></a>
				   
			<div class="headerlist">
				<ul>
					<li><a href="mainPage.html">Main Page</a></li>
					<li><a href="doSignOut.html">Logout</a></li>
				</ul>
			</div>
		</div>
		<div class="content" style="width: 87.5%; height: 600px">
			<div class="pageborderleft"></div>
			<div class="page">
				<div class="pageheader">
					Bug List <span
						style="font-size: 12px; color: #fff; float: right; margin-top: 8px"><i>Real
							programmers don't need documentation, they use debuggers
							instead...</i></span>
				</div>
				<div class="pagecontent">
					<form action="addItwTaskBug.html">
						<p>
							<table width="93%">
								<tr>
									<th align="left" style="color: #CE2020; font-size: 14px"
										colspan="5"><script type="text/javascript">
											document
													.write("Current Date and Time "
															+ Date());
										</script></th>
								</tr>
							</table>

							<table width="93%" cellspacing="0" cellpadding="0" border="0"
								class="table" id="mt" style="text-align: center">


								<tr>
									<th>Lock Status</th>
									<th>Bug-ID</th>
									<th>Bug-Desc</th>
									<th>Type</th>
									<th>Project</th>
									<th>Severity</th>
									<th>Priority</th>
									<th>OS</th>
									<th>Assignee</th>
									<th>Status</th>
									<th>Efforts</th>
									<th>Action</th>
								</tr>

								<c:forEach items="${ItwTaskBugs}" var="itwTaskBug">
									<tr>

										<c:if test="${itwTaskBug.lock1 == 'Y'}">
											<td><a href="#"><img src="images/lock.png" /></a></td>
										</c:if>
										<c:if test="${itwTaskBug.lock1 == 'N'}">
											<td><a href="#"><img src="images/edit.png" /></a></td>
										</c:if>

										<td><c:out value="${itwTaskBug.id}" /></td>
										<td><c:out value="${itwTaskBug.shortname}" /></td>
										<td><c:out value="${itwTaskBug.type1display}" /></td>
										<td><c:out value="${itwTaskBug.projectiddisplay}" /></td>

										<c:if test="${itwTaskBug.severityid == 1}">
											<td align="center">
												<table style="border-collapse: collapse">
													<tr>

														<td style="border: none; color: #F00"><b><c:out
																	value="${itwTaskBug.severityiddisplay}" /></b></td>
													</tr>
												</table>
											</td>
										</c:if>
										<c:if test="${itwTaskBug.severityid == 2}">

											<td align="center">
												<table style="border-collapse: collapse">
													<tr>

														<td style="border: none; color: #ff7200"><b><c:out
																	value="${itwTaskBug.severityiddisplay}" /></b></td>
													</tr>
												</table>
											</td>
										</c:if>
										<c:if test="${itwTaskBug.severityid == 3}">

											<td align="center">
												<table style="border-collapse: collapse">
													<tr>

														<td style="border: none; color: #089b18"><b><c:out
																	value="${itwTaskBug.severityiddisplay}" /></b></td>
													</tr>
												</table>
											</td>
										</c:if>
										<td><c:out value="${itwTaskBug.priorityiddisplay}" /></td>
										<td><c:out value="${itwTaskBug.platformiddisplay}" /></td>
										<td><c:out value="${itwTaskBug.assigneeiddisplay}" /></td>
										<td><c:out value="${itwTaskBug.statusiddisplay}" /></td>
										<td><c:out value="${itwTaskBug.efforts}" /></td>
										<c:if
											test="${itwTaskBug.currentloggedinuserid == itwTaskBug.assigneeiddisplay}">
											<td align="center" class="edit"><a
												href="editItwTaskBug.html?id=${itwTaskBug.id}">Edit</a> | <a
												href="viewItwTaskBug.html?id=${itwTaskBug.id}">View</a></td>
										</c:if>
										<c:if
											test="${itwTaskBug.currentloggedinuserid != itwTaskBug.assigneeiddisplay}">
											<c:if
											test="${itwTaskBug.lock1 == 'Y'}">
											<td align="center" class="edit"> <a
												href="viewItwTaskBug.html?id=${itwTaskBug.id}">View</a></td>
												</c:if>
												<c:if
											test="${itwTaskBug.lock1 == 'N'}">
											<td align="center" class="edit"><a
												href="editItwTaskBug.html?id=${itwTaskBug.id}">Edit</a> | <a
												href="viewItwTaskBug.html?id=${itwTaskBug.id}">View</a></td>
												</c:if>
										</c:if>
									</tr>

								</c:forEach>

							</table>

							<table cellpadding="4" width="93%">
								<tr>
									<th align="left" width="100%" valign="top"
										style="color: #333; font-size: 12px"><b><i><script>
											document.write(rec_count);
										</script> issues found.</i></b></th>

									<td><a href="addItwTaskBug.html"><input type="button"
											class="save" value="Create Bug"></a></td>
								</tr>
							</table>


						</p>
					</form>
				</div>
			</div>
			<div class="pageborderright" style="top: -344px"></div>
		</div>
	</div>

	<ul id="navigationMenu">
		<li><a class="comment" href="displayConfigPage.html"><span>Configuration</span></a></li>
		<li><a class="contact" href="mainPage.html"><span>Tweets</span></a></li>
	</ul>

</body>
</html>
