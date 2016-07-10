<%@ page contentType="text/html"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT Workbook | Create Bug</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!--[if lte IE 7]>
<style>
.content { margin-right: -1px; } /* this 1px negative margin can be placed on any of the columns in this layout with the same corrective effect. */
ul.nav a { zoom: 1; }  /* the zoom property gives IE the hasLayout trigger it needs to correct extra whiltespace between the links */
</style>
<![endif]-->
<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/themes/base/jquery-ui.css">
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
<script>
    jQuery(document).ready(function(){
        // binds form submission and fields to the validation engine
        $( ".datepicker" ).datepicker();
        jQuery("#formID").validationEngine();
    });
</script>
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
<body>

	<div class="container">
		<div class="header">
			<a href="index.html"><img src="images/logo.png" alt="IT workbook"
				name="IT workbook" id="IT workbook" /></a>
			<div class="headerlist">
				<ul>
					<li><a href="issueList.html">Issue List</a></li>
					<li><a href="doSignOut.html">Logout</a></li>
				</ul>
			</div>
		</div>
		<form:form method="POST" action="saveItwTaskBug.html"  enctype="multipart/form-data">


			<div class="content" style="width: 87.5%; height: 600px">
				<div class="pageborderleft"></div>
				<div class="page">
					<div class="pageheader">
						Create Bug
						<div
							style="position: absolute; margin-top: -56px; margin-left: 152px;">
							<img src="images/bug.png" />
						</div>
					</div>
					<div class="pagecontent">
						<p>
							<form:errors path="*" cssClass="error"  />
						<table width="94%%" cellpadding="10">
							<tr>
								<td>Shortname</td>
								<td>:</td>
								<td><form:input path="shortname"
										value="${itwTaskBug.shortname}" placeholder="Enter Shortname" /></td>
								<td>Type</td>
								<td>:</td>
								<td><form:select path="type1display"
										value="${itwTaskBug.type1display}">
										<form:option value="NONE" label="Select Type" />
										<form:options items="${itwTaskBug1.itwType1ListofValues}" />
									</form:select></td>
								<td>Efforts Estimated</td>
								<td>:</td>
								<td><form:input path="efforts"
										value="${itwTaskBug.efforts}" placeholder="Enter efforts" /></td>
							</tr>
							<tr>
								<td>Severity</td>
								<td>:</td>
								<td><form:select path="severityiddisplay"
										value="${itwTaskBug.severityiddisplay}">
										<form:option value="NONE" label="Select Severity" />
										<form:options items="${itwTaskBug1.itwSeverityidListofValues}" />
									</form:select></td>
								<td>Priority</td>
								<td>:</td>
								<td><form:select path="priorityiddisplay"
										value="${itwTaskBug.priorityiddisplay}">
										<form:option value="NONE" label="Select Priority" />
										<form:options items="${itwTaskBug1.itwPriorityidListofValues}" />
									</form:select></td>
								<td>Dead Line</td>
								<td>:</td>
								<td><form:input path="deadlinedisplay"
										value="" class="validate[required] text-input datepicker" type="date"  /></td>
							</tr>
							<tr>
								<td>Platform</td>
								<td>:</td>
								<td><form:select path="platformiddisplay"
										value="${itwTaskBug.platformiddisplay}">
										<form:option value="NONE" label="Select Platform" />
										<form:options items="${itwTaskBug1.itwPlatformidListofValues}" />
									</form:select></td>
<td>Module</td>
								<td>:</td>
								<td ><form:select path="moduleiddisplay" value="${itwTaskBug.moduleiddisplay}" style="width: 142px">
								<form:option value="0" label="Select Module" /> 
								<form:option value="1" label="Module 1"/>
								<form:option value="2" label="Module 2"/>
								</form:select></td>
								<td>Add Attachment</td>
								<td>:</td>
								<td><input type="file" name="file" id="file"></input></td>
							</tr>
							<tr>
								<td>Status</td>
								<td>:</td>
								<td><form:select path="statusiddisplay"
										value="${itwTaskBug.statusiddisplay}">
										<form:option value="NONE" label="Select Status" />
										<form:options items="${itwTaskBug1.itwStatusidListofValues}" />
									</form:select></td>
								
							</tr>
							<tr>
								<td>Project</td>
								<td>:</td>
								<td><form:select path="projectiddisplay"
										value="${itwTaskBug.projectiddisplay}">
										<form:option value="NONE" label="Select Project" />
										<form:options items="${itwTaskBug1.itwProjectidListofValues}" />
									</form:select></td>
								<td>Assignee</td>
								<td>:</td>
								<td colspan="2"><form:input    path="assigneeiddisplay"  readonly="true"
										value="${itwTaskBug1.assigneeiddisplay}" cssStyle=" width : 268px;"/></td>
							</tr>
							<tr>
								<td valign="top">Resolution</td>
								<td valign="top">:</td>
								<td colspan="7"><form:textarea cols="99" rows="6"
										path="resolution" value="${itwTaskBug.resolution}" /></td>
							</tr>
							<tr>
								<td valign="top">Summary</td>
								<td valign="top">:</td>
								<td colspan="7"><form:textarea cols="99" rows="6"
										path="summary" value="${itwTaskBug.summary}" /></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td align="right" colspan="7"><a href="issueList.html"><input
										type="button" value="Back" class="save" onclick="" /></a>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Save"
									class="save" /></td>
							</tr>
						</table>

						</p>
					</div>
				</div>
				<div class="pageborderright" style="top: -514px;"></div>
			</div>
		</form:form>
	</div>
	<ul id="navigationMenu">
		<li><a class="comment" href="displayConfigPage.html"><span>Configuration</span></a></li>
		<li><a class="contact" href="mainPage.html"><span>Tweets</span></a></li>
	</ul>
</body>
</html>
