
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>
<html>

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

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="Simplicity Itself" />

<title><spring:message code="title.itworkbook"
		text="default text" /></title>

<link href="css/bootstrap.min.css" rel="stylesheet"></link>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 50px;
}

.sidebar-nav {
	padding: 9px 0;
}

.canvas {
	float: right;
	margin-right: 100px;
}

#menu,#menu ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#menu:before,#menu:after {
	content: "";
	display: table;
}

#menu:after {
	clear: both;
}

#menu {
	zoom: 1;
}

#menu li {
	float: left;
	position: relative;
}

#menu a {
	float: left;
	padding: 10px 30px;
	color: #000;
	text-decoration: none;
	font-size: 9pt;
}

#menu li:hover>a {
	color: #A60C0C;
}

* html #menu li a:hover { /* IE6 only */
	color: #fafafa;
}

#menu ul {
	margin: 20px 0 0 0;
	_margin: 0; /*IE6 only*/
	opacity: 0;
	visibility: hidden;
	position: absolute;
	top: 38px;
	left: 16px;
	z-index: 1;
	background: #900000;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, .3);
	border: 1px solid #500000;
	border-radius: 3px;
	transition: all .2s ease-in-out;
}

#menu ul a {
    color:#fff; 
}

#menu li:hover>ul {
	opacity: 1;
	visibility: visible;
	margin: 0;
}

#menu ul ul {
	top: 0;
	left: 150px;
	margin: 0 0 0 20px;
	_margin: 0; /*IE6 only*/
	box-shadow: -1px 0 0 rgba(255, 255, 255, .3);
}

#menu ul li {
	float: none;
	display: block;
	border: 0;
	_line-height: 0; /*IE6 only*/
	box-shadow: 0 1px 0 #500000, 0 1px 0 #500000;
}

#menu ul li:last-child {
	box-shadow: none;
}

#menu ul a {
	padding: 3px 6px;
	width: 130px;
	_height: 10px; /*IE6 only*/
	display: block;
	white-space: nowrap;
	float: none;
	text-transform: none;
	font-size: 11px;
}

#menu ul a:hover {
	background-color: #E84040;
	color:#fff;
}

#menu ul li:first-child>a {
	border-radius: 3px 3px 0 0;
}

#menu ul li:first-child>a:after {
	content: '';
	position: absolute;
	left: 40px;
	top: -6px;
	border-left: 6px solid transparent;
	border-right: 6px solid transparent;
	border-bottom: 5px solid #500000;
}

#menu ul ul li:first-child a:after {
	left: -6px;
	top: 50%;
	margin-top: -6px;
	border-left: 0;
	border-bottom: 6px solid transparent;
	border-top: 6px solid transparent;
	border-right: 6px solid #500000;
}

#menu ul li:first-child a:hover:after {
	border-bottom-color: #E84040;
}

#menu ul ul li:first-child a:hover:after {
	border-right-color: #0299d3;
	border-bottom-color: transparent;
}

#menu ul li:last-child>a {
	border-radius: 0 0 3px 3px;
}

.dropdown {
	float: right;
	width: auto;
	margin-right: 10px;
}
</style>
<!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
<link href="css/bootstrap-responsive.min.css" rel="stylesheet"></link>
<script src="js/tagcanvas.js" type="text/javascript" async=""></script>
<script type="text/javascript">
	//         
	var oopts = {
		textFont : 'Impact,Arial Black,sans-serif',
		textHeight : 20,
		maxSpeed : 0.1,
		decel : 0.9,
		depth : 0.99,
		outlineColour : '#f6f',
		outlineThickness : 3,
		pulsateTo : 0.2,
		pulsateTime : 0.5,
		wheelZoom : false
	}, ttags = 'taglist', lock, shape = 'sphere';
	window.onload = function() {
		TagCanvas.textFont = 'Trebuchet MS, Helvetica, sans-serif';
		TagCanvas.textColour = '#00f';
		TagCanvas.textHeight = 25;
		TagCanvas.outlineColour = '#ff9999';
		TagCanvas.maxSpeed = 0.03;
		TagCanvas.minBrightness = 0.2;
		TagCanvas.depth = 0.92;
		TagCanvas.pulsateTo = 0.6;
		TagCanvas.initial = [ 0.1, -0.1 ];
		TagCanvas.decel = 0.98;
		TagCanvas.reverse = true;
		TagCanvas.hideTags = false;
		TagCanvas.shadow = '#ccf';
		TagCanvas.shadowBlur = 3;
		TagCanvas.weight = false;
		TagCanvas.imageScale = null;
		TagCanvas.fadeIn = 1000;
		try {
			TagCanvas.Start('tagcanvas', 'taglist');
			TagCanvas.Start('smallCanvas', 'moreTags', oopts);
			f('options');
		} catch (e) {
			document.getElementsByTagName('canvas')[0].style.border = '0';
		}
	};
	//
</script>
<style>
button.btn.btn-small,input.btn.btn-small[type="submit"] {
	padding: 6px 10px;
}

.span3 {
	width: 334px;
	margin: 15% 0 0 37%;
}

.nav-collapse.collapse {
	margin-left: 74px;
}
.hero-unit {
    font-size: 8pt;
}
</style>
</head>

<body>


	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid" style="padding-left: 52px">
			 <a class="brand" href="/" style="padding:3px 0"><img src="images/clientlogo.png" alt="IT Workbook" title="IT Workbook"></a>
				
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				
				  <a class="brand" href="/" style="position: absolute; top: -14px; left:131px"><img
					src="images/logo.png" alt="IT Workbook" title="IT Workbook"></a>  
					<div class="dropdown">
					<ul id="menu" class="nav" style="float: right">
						<li><a href="#">Language : <b style="color: #A60C0C"><spring:message
										code="language.selected" text="default text" /></b></a>
							<ul>
								<li><a href="?language=en">English</a></li>
								<!-- <li><a href="?language=hi_IN">हिन्दी</a></li> -->
								<li><a href="?language=de">Deutsch</a></li>
								<li><a href="?language=fr">français</a></li>
								<li><a href="?language=kn">ಕನ್ನಡ</a></li>
								<li><a href="?language=zh_CN">简体中文 </a></li>

							</ul></li>
							 <li>
                         <a href="#" style="padding:3px 0"><img src="images/agileidclogo.png" alt="IT Workbook" title="IT Workbook"></a>
                        </li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid" style="padding-left: 0;">


		<div class="hero-unit span12"
			style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt; width:269px; margin:15% 0 0 40%">
           <table>
            <tr>
             <td>
			<form:form method="POST" action="savechangePass.html">
				<form:errors path="j_username" cssClass="error" />
				<spring:message code="signin.enter.userid" var="var_enter_userid" />
				<form:input path="j_username" value="${userLoginBean.j_username}"
					placeholder="${var_enter_userid}"
					style="float:left; margin-right: 6px; width: 254px;" />
                </td>
                </tr>
			    <tr>
                <td> 
				<spring:message code="signin.enter.oldpassword"
					var="var_enter_oldpassword" />
				<form:password path="j_password" value="${userLoginBean.j_password}"
					placeholder="${var_enter_oldpassword}" onpaste="return false;"
					style="float:left; margin-right: 6px; width: 254px;" />
			    </td>
			    </tr>
			    <tr>
			    <td>
				<spring:message code="signin.enter.newpassword"
					var="var_enter_newpassword" />
				<form:password path="newpassword" value="${userLogin.newpassword}"
					placeholder="${var_enter_newpassword}" onpaste="return false;"
					style="float:left; margin-right: 6px; width: 254px;" />
					</td>
					</tr>
			    <tr>
					<td>
				<spring:message code="signin.enter.confirmpassword"
					var="var_enter_confirmpassword" />
				<form:password path="confirmpassword"
					value="${userLogin.confirmpassword}"
					placeholder="${var_enter_confirmpassword}" onpaste="return false;"
					style="float:left; margin-right: 6px; width: 254px;" />
					</td>
					</tr>
					<tr>
					<td colspan="2">
                 <form:errors path="confirmpassword" cssClass="error" />
				<input type="submit" class="btn btn-info btn-small" value="Change Password"
					style="float: right; padding:7.5px 10px; margin-right:7px" />

			</form:form>
			</td>
			</tr>
			</table>
		</div>
	</div>

	<div class="footer" style="width: 100%; text-align: center; font-size: 11px; position: fixed; bottom: 16px;">
		<a href="#" style="color: #fff; text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2)">© 2014 AgileIDC</a>
	</div>
<style>
input[type="text"], input[type="password"] {
    margin-bottom: 0;
}
</style>
</body>
</html>
