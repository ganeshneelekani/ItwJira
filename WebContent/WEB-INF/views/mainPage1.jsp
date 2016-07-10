<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%  
    response.setHeader("Pragma","no-cache");  
    response.setHeader("Cache-Control","no-store");  
    response.setHeader("Expires","0");  
    response.setDateHeader("Expires",-1);  
    %>  
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

#bg {
	position: fixed;
	top: -50%;
	left: -50%;
	width: 200%;
	height: 200%;
	z-index: -1;
}

#bg img {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	margin: auto;
	min-width: 50%;
	min-height: 50%;
}
</style>
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</link>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <style>
    .nav-list>li>a,.nav-list .nav-header {
	margin-right: -8px;
	margin-left: -8px;
}
    </style>
</head>
<body>
	<c:url value="mainPage1.html" var="pagedLink">
		<c:param name="action" value="list" />
		<c:param name="p" value="~" />
	</c:url>
	<!-- {!begin layout} -->
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

	<!-- {!end layout} -->

	<div class="container-fluid">
		<!-- <div class="well sidebar-nav span2">
			<ul class="nav nav-list">
				<li class="nav-header">Projects</li>

				<c:set var="counter" value="0" />
				<c:forEach items="${ItwProjects1}" var="ItwProjecttemp">
					<li><a href="mainPage1.html?projectId=${ItwProjecttemp.id}">${ItwProjecttemp.projectname}</a></li>
				</c:forEach>

			</ul>
		</div>  -->
		<div class="well span12">

			<h4>
				<b>Project Tweets </b>
			</h4>
			<c:if test="${!empty pagedListHolder}">
			
				<tg:paging pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
			</c:if>
			<div class="tweetswrapper">
			
				<c:if test="${!empty pagedListHolder}">
				
					<table width="100%" style="border-collapse: collapse">
						<c:forEach items="${pagedListHolder.pageList}" var="itwTweet">
							<!-- ------------Tweets1----------- -->
							<tr>
								<td>
									<div class="tweetshover">
										<table width="100%">
											<tr>
												
												<td valign="top" style="padding-top: 10px">
													<h4
														style="font-family: 'Helvetica', Arial, sans-serif; color: blue; font-size: 14px; margin: 0">${itwTweet.username}</h4>
													<p
														style="font-family: 'Helvetica', Arial, sans-serif; font-style: italic; font-size: 12px; line-height: 16pt">${itwTweet.tweetmsg}</p>
												</td>
												<td rowspan="2" valign="top" style="padding-top: 10px"
													width="12%">
													<div class="thumbnail "
														style="margin-right: 20px; margin-left: 10px; float:right">
														<img src="${itwTweet.filepath1}" alt="Sample Image"
															width="49" height="61">
													</div>
												</td>
											</tr>
											<tr style="border-bottom: 1px solid #ccc">
												<td colspan="2" align="left"
													style="padding-right: 10px; padding-bottom: 10px"><span
													style="font-family: 'Helvetica', Arial, sans-serif; font-style: bold; font-size: 10px;">${itwTweet.daysagodisplay}</span></td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</c:forEach>


						<!-- ------------End Tweets1----------- -->

					</table>
				</c:if>
				<c:if test="${empty pagedListHolder.pageList}">
					<table  style="border-collapse: collapse">

						<tr>
							<td>No Tweets available for the project</td>
						</tr>


					</table>
				</c:if>
			</div>



		</div>



	</div>

</body>
</html>
