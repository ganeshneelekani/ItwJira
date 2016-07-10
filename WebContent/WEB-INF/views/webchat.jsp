
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page import="com.agileidc.itw.model.ItwUser"%>
<%@page import="javax.swing.JOptionPane"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IT Work Book</title>
<script type="text/javascript" src="js/jquery-1-10-1.js"></script>
<script type="text/javascript" src="js/session-warning.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		initSessionMonitor();
		getOnlineUsers();
		getAllUsers();
		getMyMessages();
		setInterval(function() {
			getMyMessages()
		}, 10000);
		setInterval(function() {
			getOnlineUsers()
		}, 10000);
		setInterval(function() {
			getOfflineUsers()
		}, 10000);
	});

	function selectUser(string) {
		$("#users").val(string).attr('selected', true);
	}

	function sendMessage() {
		$.ajax({
			type : "POST",
			url : "sendMessage.html",
			data : "to=" + $("#users option:selected").val() + "&message="
					+ $("#message").val(),
			success : function() {
				$("#msgStatus").html("Message Sent Successfully...");
			},
			error : function(e) {
				alert("error" + e.toSource());
				$("#msgStatus").html("Error : " + e);
			}
		});
	}

	function getOnlineUsers() {
		$.ajax({
			type : "POST",
			url : "getOnlineUsers.html",
			dataType : "json",
			success : function(result) {
				if (result.length == 0) {
					var oo = "Online Users None <br>";
				
					$("#onlineUsers").html(oo);
				 
				} else {
					for ( var i = 0; i < result.length; i++) {
						var oo = "<a href=javascript:selectUser('"
								+ result[i].userId + "')><font color='green'>"
								+ result[i].userId + "</font></a> <br>";
						if (i == 0) {
							$("#onlineUsers").html(oo);
						} else {
							$("#onlineUsers").append("" + oo);
						}
					}
				}
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}
	function getOfflineUsers() {
		$.ajax({
			type : "POST",
			url : "getOfflineUsers.html",
			dataType : "json",
			success : function(result) {
				
				if (result.length == 0) {
					var oo = "Offline Users None <br>";
				
					$("#offlineUsers").html(oo);
				 
				} else {
					for ( var i = 0; i < result.length; i++) {
						var oo = "<a href=javascript:selectUser('"
								+ result[i].userId + "')><font color='grey'>"
								+ result[i].userId + "</font></a> <br>";
						if (i == 0) {
							$("#offlineUsers").html(oo);
						} else {
							$("#offlineUsers").append("" + oo);
						}
					}
				}
			
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}

	function getMyMessages() {
		$.ajax({
			type : "POST",
			url : "getMyMessages.html",
			dataType : "json",
			success : function(result) {
				for ( var i = 0; i < result.length; i++) {
					if (i == 0) {
						$("#maxVal").val(result[i].id);
					} else if (i == result.length - 1) {
						$("#minVal").val(result[i].id);
					}
					var time = "";
					time = result[i].time;
					var oo = "<tr bgcolor='gray' border='2' >" + "<td>"
							+ "<tr><td>" + result[i].message + "</td></tr>"
							+ "<tr><td> by : " + result[i].senderId + " on "
							+ time + "</td>" + "</tr>" + "</td>" + "</tr>";
					if (i == 0) {
						$("#inbox").html(oo);
					} else {
						$("#inbox").append(oo);
					}
				}
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}

	function getMyPrevMessages() {
		$.ajax({
			type : "POST",
			url : "getPrev.html",
			dataType : "json",
			data : "minVal=" + $("#minVal").val(),
			success : function(result) {
				for ( var i = 0; i < result.length; i++) {
					if (i == 0) {
						$("#maxVal").val(result[i].id);
					} else if (i == result.length - 1) {
						$("#minVal").val(result[i].id);
					}
					var time = "";
					time = result[i].time;
					var oo = "<tr bgcolor='gray' border='2' >" + "<td>"
							+ "<tr><td>" + result[i].message + "</td></tr>"
							+ "<tr><td> by : " + result[i].senderId + " on "
							+ time + "</td>" + "</tr>" + "</td>" + "</tr>";
					if (i == 0) {
						$("#inbox").html(oo);
					} else {
						$("#inbox").append(oo);
					}
				}
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}

	function getMyNextMessages() {
		$.ajax({
			type : "POST",
			url : "getMyMessages.html",
			dataType : "json",
			data : "maxVal=" + $("#maxVal").val(),
			success : function(result) {
				for ( var i = 0; i < result.length; i++) {
					if (i == 0) {
						$("#maxVal").val(result[i].id);
					} else if (i == result.length - 1) {
						$("#minVal").val(result[i].id);
					}
					var time = "";
					time = result[i].time;
					var oo = "<tr bgcolor='gray' border='2' >" + "<td>"
							+ "<tr><td>" + result[i].message + "</td></tr>"
							+ "<tr><td> by : " + result[i].senderId + " on "
							+ time + "</td>" + "</tr>" + "</td>" + "</tr>";

					if (i == 0) {
						$("#inbox").html(oo);
					} else {
						$("#inbox").append(oo);
					}
				}
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}

	function getAllUsers() {
		$.ajax({
			type : "POST",
			url : "getAllUsers.html",
			dataType : "json",
			success : function(result) {
				for ( var i = 0; i < result.length; i++) {
					var oo = "<option name='"+result[i].userid+"'>"
							+ result[i].userid
					"</option>";
					if (i == 0) {
						$("#users").html(oo);
					} else {
						$("#users").append(oo);
					}
				}
			},
			error : function(e) {
				alert("error" + e.toSource());
			}
		});
	}
</script>
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
	color: #999;
	text-decoration: none;
	text-shadow: 0 1px 0 #000;
	font-size: 12px;
}

#menu li:hover>a {
	color: #fafafa;
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
	left: 0;
	z-index: 1;
	background: #444;
	background: linear-gradient(#444, #111);
	box-shadow: 0 -1px 0 rgba(255, 255, 255, .3);
	border-radius: 3px;
	transition: all .2s ease-in-out;
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
	box-shadow: 0 1px 0 #111, 0 2px 0 #666;
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
	background-color: #0186ba;
	background-image: linear-gradient(#04acec, #0186ba);
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
	border-bottom: 6px solid #444;
}

#menu ul ul li:first-child a:after {
	left: -6px;
	top: 50%;
	margin-top: -6px;
	border-left: 0;
	border-bottom: 6px solid transparent;
	border-top: 6px solid transparent;
	border-right: 6px solid #3b3b3b;
}

#menu ul li:first-child a:hover:after {
	border-bottom-color: #04acec;
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
}

.nav-collapse.collapse {
	margin-left: 74px;
}
</style>
</head>



<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<div class="well sidebar-nav span2">
		<ul class="nav nav-list">
			<table class="table table-striped">
				<tr>
					<td>
						<li class="nav-header"><spring:message code="online.users"
								text="Online Users" /></li>
					</td>
				</tr>
				<tr>
					<td>
						<li></li>
					</td>
				</tr>
				<tr>
					<td>
						<li><div
								style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;"
								id="onlineUsers"></div></li>
					</td>
				</tr>
				<tr>
					<td>
						<li></li>
					</td>
				</tr>
				<tr>
					<td>
						<li class="nav-header"><spring:message code="offline.users"
								text="Offline Users" /></li>
					</td>
				</tr>
				<tr>
					<td>
						<li></li>
					</td>
				</tr>
				<tr>
					<td>
						<li><div
								style="font-family: 'Helvetica', Arial, sans-serif; font-size: 9pt;"
								id="offlineUsers"></div></li>
					</td>
				</tr>

			</table>
		</ul>
	</div>



	<div class="container-fluid">
		<div class="well span8">
			<h5>
				Welcome..
				<%
				com.agileidc.itw.web.UserSession userSession = (com.agileidc.itw.web.UserSession) request
						.getSession().getAttribute("userSession");
			%>
				<%=userSession.getItwUser().getFirstname() + " "
					+ userSession.getItwUser().getLastname()%>
			</h5>
			<table>
				<tr>
					<td>
						<form>
							<table>
								<tr>
									<td>Select User :</td>
									<td><select id="users" name="to"></select></td>
									<td><a href="javascript:getAllUsers();"><font
											color="red">refresh Userlist</font></a></td>
								</tr>
								<tr>
									<td><textarea name="message" id="message" COLS="50"
											ROWS="8"></textarea></td>
									<td>
										<div id="msgStatus"></div>
									</td>
								</tr>
								<tr>
									<td><a href="javascript:sendMessage();"><font
											color="blue">SEND</font></a></td>
								</tr>
							</table>
						</form>
					</td>

				</tr>

				<tr>
					<td>
						<h3>Your Inbox</h3>
						<table id="inbox" border="2" height="400px" width="600px"
							bgcolor="green">

						</table>


					</td>
				</tr>

				<tr>
					<td>
						<form action="getPrev.htm">
							<table>
								<tr>
									<td><input type="hidden" id="minVal" name="minVal" /></td>
									<td><input type="button" value="previous"
										onclick="getMyPrevMessages();" /></td>
								</tr>
							</table>
						</form>
					</td>
					<td>
						<form action="getNext.htm">
							<table>
								<tr>
									<td><input type="hidden" id="maxVal" name="maxVal" /></td>
									<td><input type="button" value="next"
										onclick="getMyNextMessages();" /></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>



			</table>
		</div>
	</div>
</body>

</html>