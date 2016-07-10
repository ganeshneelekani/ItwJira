
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/jquery.treetable.css" />
<link rel="stylesheet" href="css/jquery.treetable.theme.default.css" />
<style>
/*
		 * CSS3 Treeview. No JavaScript
	     * @version 1.0
		 * @author Martin Ivanov
		 * @url developer's website: http://wemakesites.net/
	     * @url developer's twitter: https://twitter.com/#!/wemakesitesnet
		 * @url developer's blog http://acidmartin.wordpress.com/
		 **/

/*
		 * This solution works with all modern browsers and Internet Explorer 9+. 
		 * If you are interested in purchasing a JavaScript enabler for IE8 
		 * for the CSS3 Treeview, please, check this link:
		 * http://experiments.wemakesites.net/miscellaneous/acidjs-css3-treeview/
		 **/
.css-treeview ul,.css-treeview li {
	padding: 0;
	margin: 0;
	list-style: none;
}

.css-treeview input {
	position: absolute;
	opacity: 0;
}

.css-treeview {
	font: normal 11px "Segoe UI", Arial, Sans-serif;
	-moz-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}

.css-treeview a {
	color: #00f;
	text-decoration: none;
}

.css-treeview a:hover {
	text-decoration: underline;
}

.css-treeview input+label+ul {
	margin: 0 0 0 22px;
}

.css-treeview input                                            ~ ul {
	display: none;
}

.css-treeview label,.css-treeview label::before {
	cursor: pointer;
}

.css-treeview input:disabled+label {
	cursor: default;
	opacity: .6;
}

.css-treeview input:checked:not                                        
	  (:disabled               
	                           ) ~ ul {
	display: block;
}

.css-treeview label,.css-treeview label::before {
	background: url("images/icons.png") no-repeat;
}

.css-treeview label,.css-treeview a,.css-treeview label::before {
	display: inline-block;
	height: 16px;
	line-height: 16px; ,
	vertical-align: middle;
}

.css-treeview label {
	background-position: 18px 0;
}

.css-treeview label::before {
	content: "";
	width: 16px;
	margin: 0 22px 0 0;
	vertical-align: middle;
	background-position: 0 -32px;
}

.css-treeview input:checked+label::before {
	background-position: 0 -16px;
}

/* webkit adjacent element selector bugfix */
@media screen and (-webkit-min-device-pixel-ratio:0) {
	.css-treeview {
		-webkit-animation: webkit-adjacent-element-selector-bugfix infinite 1s;
	}
	@
	-webkit-keyframes webkit-adjacent-element-selector-bugfix {from {
		padding:0;
		
	}
	to {
		padding: 0;
	}
}
}
</style>
<title><spring:message code="title.configuration"
		text="default text" /></title>
		<style>
button.btn.btn-small,input.btn.btn-small[type="submit"] {
	padding: 2px 10px;
}

.span3 {
	width: 334px;
}

.nav-collapse.collapse {
	margin-left: 74px;
}

.ui-widget {
	font-family: Verdana, Arial, sans-serif;
	font-size: 11px;
}

.hero-unit {
	font-size: 18px;
	font-weight: 200;
	line-height: 14px;
	color: inherit;
}

label,input,button,select,textarea {
	font-size: 11px;
}

table.treetable {
	font-size: 11px;
}

.btn-small {
	font-size: 11px;
}

.ui-widget-content a {
	color: #fff;
}
</style>
<script src="js/jquery.treetable.js"></script>
</head>
<body>


	<ul class="nav nav-list">
		<li class="nav-header"><spring:message code="config.mgmt"
				text="default text" /></li>
		<li><a href="userConfigList.html"><spring:message
					code="Manage.Users" text="default text" /></a></li>
		<li><a
			href="roleConfigList.html"><spring:message
					code="Configure.Roles" text="default text" /></a></li>
		<li><a
			href="ItwClientConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Client" text="default text" /></a></li>

		<li class="activetweets"><a
			href="itwPlatFormsConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Platforms" text="default text" /></a></li>
		<li><a
			href="itwPriorityConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Priority" text="default text" /></a></li>
		<li><a
			href="projectConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Projects" text="default text" /></a></li>
		<li><a
			href="companyConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Company" text="default text" /></a></li>
		<li><a
			href="itwReleasesConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Releases" text="default text" /></a></li>
		<li><a
			href="itwSeverityConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Severity" text="default text" /></a></li>
		<li><a
			href="itwBrowserTypesConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Browser" text="default text" /></a></li>
		<li><a
			href="itwStagesTypesConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Stages" text="default text" /></a></li>
		<li><a
			href="itwModuleConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Modules" text="default text" /></a></li>

		<li><a
			href="itwStatusTypesConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Status" text="default text" /></a></li>

		<li><a
			href="itwUserRolesConfigarationList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.UserRoles" text="default text" /></a></li>
<li><a
			href="itwUserGroupConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Projects" text="User Group Configuration" /></a></li>					

		<li><a href="downloadItwRole.html">Download</a></li>
	</ul>


	<!-- <ul>

		<table id="example-advanced">
			
			<thead>
				<tr>
					<th>Menu</th>
					
				</tr>
			</thead>
			<tbody>
						<tr  data-tt-id='menuroot'>
							<td><span class='folder'>Configuration</span></td>
							
						</tr>
				
						<tr  data-tt-id='userConfig'
								data-tt-parent-id='menuroot'>
							<td><a href="userConfigList.html"><spring:message
					code="Manage.Users" text="default text" /></a></td>
							
						</tr>
						<tr  data-tt-id='userConfig'
								data-tt-parent-id='menuroot'>
							<td><a
			href="itwSeverityConfigList.html?langDesc=${pageContext.response.locale}"><spring:message
					code="Configure.Severity" text="default text" /></td>
							
						</tr>
					
					

			</tbody>
		</table>

	</ul>  -->
	
	<script>
		$("#example-basic").treetable({
			expandable : true
		});

		$("#example-basic-static").treetable();

		$("#example-basic-expandable").treetable({
			expandable : true
		});

		$("#example-advanced").treetable({
			expandable : true
		});

		// Highlight selected row
		$("#example-advanced tbody").on("mousedown", "tr", function() {
			$(".selected").not(this).removeClass("selected");
			$(this).toggleClass("selected");
		});

		/* Drag & Drop Example Code
		$("#example-advanced .file, #example-advanced .folder").draggable({
			helper : "clone",
			opacity : .75,
			refreshPositions : true, // Performance?
			revert : "invalid",
			revertDuration : 300,
			scroll : true
		});*/

		$("#example-advanced .folder").each(
				function() {
					$(this).parents("#example-advanced tr").droppable(
							{
								accept : ".file, .folder",
								drop : function(e, ui) {
									var droppedEl = ui.draggable.parents("tr");
									$("#example-advanced").treetable("move",
											droppedEl.data("ttId"),
											$(this).data("ttId"));
								},
								hoverClass : "accept",
								over : function(e, ui) {
									var droppedEl = ui.draggable.parents("tr");
									if (this != droppedEl[0]
											&& !$(this).is(".expanded")) {
										$("#example-advanced").treetable(
												"expandNode",
												$(this).data("ttId"));
									}
								}
							});
				});

		$("form#reveal").submit(function() {
			var nodeId = $("#revealNodeId").val()

			try {
				$("#example-advanced").treetable("reveal", nodeId);
			} catch (error) {
				alert(error.message);
			}

			return false;
		});
	</script>
</body>
</html>