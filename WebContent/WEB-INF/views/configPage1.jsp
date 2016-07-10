<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
</head>
<body>
	<!-- {!begin layout} -->
	
	
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<!-- {!end layout} -->

	<div class="well sidebar-nav span3">
		<ul class="nav nav-list">
			<li class="nav-header">Configuration Management</li>
			<li><a href="userConfigList.html">Manage Users</a></li>
			<li><a href="http://www.apple.com/">Configure Roles</a></li>
			<li><a href="http://www.apple.com/">Configure Platforms</a></li>
			<li><a href="http://www.apple.com/">Configure Priority</a></li>
			<li><a href="http://www.apple.com/">Configure Projects</a></li>
			<li><a href="http://www.apple.com/">Configure Company</a></li>
			<li><a href="http://www.apple.com/">Configure Releases</a></li>
			<li><a href="http://www.apple.com/">Configure Severity</a></li>
			<li><a href="http://www.apple.com/">Configure Browser</a></li>
			<li><a href="http://www.apple.com/">Configure Stages</a></li>
			<li><a href="http://www.apple.com/">Configure Modules</a></li>
			<li><a href="http://www.apple.com/">Configure Status</a></li>

		</ul>
	</div>



</body>
</html>
